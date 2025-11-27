package org.example.eksamensprojekte25.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {
    private JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
