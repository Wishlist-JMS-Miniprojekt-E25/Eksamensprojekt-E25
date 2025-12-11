package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.repository.EmployeeRepository;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class ProjectRepositoryIntegrationTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void testGetProjectByID() {

        Project project = projectRepository.getProjectByID(1);

        assertNotNull(project);
        assertEquals(1, project.getProjectID());
        assertEquals("Test project 1", project.getProjectName());
        assertEquals("test description", project.getProjectDescription());

        assertNotNull(project.getProjectManager());
        assertEquals(1, project.getProjectManager().getEmployeeID());
        assertEquals("Simon", project.getProjectManager().getEmployeeName());

        assertNotNull(project.getTimeslot());
        assertEquals(1, project.getTimeslot().getTimeslotID());
        assertEquals(30, project.getTimeslot().getPlannedDays());
        assertEquals(Date.valueOf("2026-03-20"), project.getTimeslot().getPlannedStartDate());
        assertEquals(Date.valueOf("2026-04-20"), project.getTimeslot().getPlannedFinishDate());
    }

    @Test
    void testGetArchivedProjectByID() {

        Project archivedProject = projectRepository.getArchivedProjectByID(2);

        assertNotNull(archivedProject);
        assertEquals(2, archivedProject.getProjectID());
        assertEquals("Test archived project 2", archivedProject.getProjectName());
        assertEquals("test description", archivedProject.getProjectDescription());

        assertNotNull(archivedProject.getProjectManager());
        assertEquals(1, archivedProject.getProjectManager().getEmployeeID());
        assertEquals("Simon", archivedProject.getProjectManager().getEmployeeName());

        assertNotNull(archivedProject.getTimeslot());
        assertEquals(4, archivedProject.getTimeslot().getTimeslotID());
        assertEquals(30, archivedProject.getTimeslot().getPlannedDays()); // fra din SQL
        assertEquals(Date.valueOf("2026-06-20"), archivedProject.getTimeslot().getPlannedStartDate());
        assertEquals(Date.valueOf("2026-07-20"), archivedProject.getTimeslot().getPlannedFinishDate());
        assertEquals(Date.valueOf("2026-07-20"), archivedProject.getTimeslot().getActualFinishDate());
        assertEquals(0, archivedProject.getTimeslot().getDifferenceInDays());
        assertEquals(240, archivedProject.getTimeslot().getTotalWorkhours());
        assertTrue(archivedProject.getTimeslot().isDone());
    }

    @Test
    void testDeleteProjectByID() {

        Project projectBefore = projectRepository.getProjectByID(1);
        assertNotNull(projectBefore);

        projectRepository.deleteProjectByID(1);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            projectRepository.getProjectByID(1);
        });
    }

    @Test
    void testAddProject() {

        Integer managerID = 1;            // skal findes i test DB
        Integer timeslotID = 1;           // skal findes i test DB
        String name = "New Test Project";
        String desc = "Description for test project";


        Project created = projectRepository.addProject(managerID, name, desc, timeslotID);


        assertNotNull(created);
        assertTrue(created.getProjectID() > 0);


        Project fetched = projectRepository.getProjectByID(created.getProjectID());


        assertEquals(name, fetched.getProjectName());
        assertEquals(desc, fetched.getProjectDescription());
        assertEquals(managerID, fetched.getProjectManager().getEmployeeID());
        assertEquals(timeslotID, fetched.getTimeslot().getTimeslotID());
    }

    @Test
    void testAssignEmployeesToProject() {

        Project project = projectRepository.addProject(1, "test name", "test description", 1);

        List<Integer> employeeIDeezNutz = List.of(1, 2);

        projectRepository.assignEmployeesToProject(project.getProjectID(), employeeIDeezNutz);

        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM projectEmployee WHERE projectID = ?",
                Integer.class,
                project.getProjectID()
        );

        assertEquals(2, count);
    }

    @Test
    void testEditProject() {

        Integer managerID = 1;     // Skal findes i test DB
        Integer timeslotID = 1;    // Skal findes i test DB
        Project original = projectRepository.addProject(
                managerID,
                "Original Name",
                "Original Description",
                timeslotID
        );


        Timeslot newTimeslot = projectRepository.createTimeslot(
                10,
                Date.valueOf("2024-01-01"),
                Date.valueOf("2024-01-11")
        );


        Project edited = new Project();
        edited.setProjectName("Updated Name");
        edited.setProjectDescription("Updated Description");
        edited.setTimeslot(newTimeslot);

        projectRepository.editProject(edited, original.getProjectID());

        Project fetched = projectRepository.getProjectByID(original.getProjectID());

        assertEquals("Updated Name", fetched.getProjectName());
        assertEquals("Updated Description", fetched.getProjectDescription());
        assertEquals(newTimeslot.getTimeslotID(), fetched.getTimeslot().getTimeslotID());
    }

    @Test
    void testRemoveEmployeeFromProject() {

        Project project = projectRepository.addProject(1, "P", "D", 1);

        jdbc.update("INSERT INTO projectEmployee (employeeID, projectID) VALUES (?, ?)", 1, project.getProjectID());
        jdbc.update("INSERT INTO projectEmployee (employeeID, projectID) VALUES (?, ?)", 2, project.getProjectID());


        projectRepository.removeEmployeeFromProject(project.getProjectID(), 1);


        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM projectEmployee WHERE projectID = ?",
                Integer.class,
                project.getProjectID()
        );

        assertEquals(1, count);
    }

    @Test
    void testFinalizeTimeslot() {

        Timeslot t = projectRepository.createTimeslot(
                5,
                Date.valueOf("2024-01-01"),
                Date.valueOf("2024-01-06")
        );


        t.setDone(true);
        t.setTotalWorkhours(37);
        t.setActualFinishDate(Date.valueOf("2024-01-07"));
        t.setDifferenceInDays(1);


        projectRepository.finalizeTimeslot(t, t.getTimeslotID());


        Timeslot fetched = projectRepository.getTimeslotByID(t.getTimeslotID());

        assertTrue(fetched.isDone());
        assertEquals(37, fetched.getTotalWorkhours());
        assertEquals(Date.valueOf("2024-01-07"), fetched.getActualFinishDate());
        assertEquals(1, fetched.getDifferenceInDays());
    }

    @Test
    void testUpdateTotalWorkhours() {

        Timeslot t = projectRepository.createTimeslot(3,
                Date.valueOf("2024-01-01"),
                Date.valueOf("2024-01-04")
        );

        t.setTotalWorkhours(50);


        projectRepository.updateTotalWorkhoursForTimeslot(t, t.getTimeslotID());

        Timeslot fetched = projectRepository.getTimeslotByID(t.getTimeslotID());
        assertEquals(50, fetched.getTotalWorkhours());
    }
}