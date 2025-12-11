-- Brug databasen
USE canwemakeit;

INSERT INTO timeSlot 
(plannedDays, plannedStartDate, plannedFinishDate, actualFinishDate, differenceInDays, totalWorkhours, isDone)

VALUES 
(30, "2024-11-25", "2024-12-25", "2024-12-20" , 5, 200, TRUE),
(30, "2024-12-25", "2025-01-25", "2025-01-25" , 0, 240, TRUE),
(30, "2025-11-20", "2025-11-25", NULL, NULL, NULL, false),
(30, "2025-11-20", "2025-12-20", NULL, NULL, NULL, false),
(30, "2025-12-20", "2026-01-20", NULL, NULL, NULL, false),
(30, "2026-01-20", "2026-02-20", "2026-02-25", 5, 280, TRUE),
(30, "2026-02-20", "2026-03-20", NULL, NULL, NULL, false),
(30, "2026-03-20", "2026-04-20", NULL, NULL, NULL, false),
(30, "2026-04-20", "2026-05-20", NULL, NULL, NULL, false),
(30, "2026-05-20", "2026-06-20", NULL, NULL, NULL, false),
(30, "2026-06-20", "2026-07-20", "2026-07-20", 0, 240, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-08-20", "2026-09-20", "2026-09-20", 0, 240, TRUE),
(30, "2026-10-20", "2026-11-20", NULL, NULL, NULL, false),
(30, "2026-11-10", "2026-12-10", NULL, NULL, NULL, false),
(30, "2026-12-10", "2027-01-10", NULL, NULL, NULL, false),
(30, "2027-01-10", "2027-02-10", NULL, NULL, NULL, false),
(30, "2027-02-10", "2027-03-10", NULL, NULL, NULL, false),
(30, "2027-03-10", "2027-04-10", NULL, NULL, NULL, false),
(30, "2027-04-10", "2027-05-10", NULL, NULL, NULL, false),
(30, "2027-05-10", "2027-06-10", NULL, NULL, NULL, false),
(30, "2027-06-10", "2027-07-10", NULL, NULL, NULL, false),
(30, "2027-07-10", "2027-08-10", NULL, NULL, NULL, false),
(30, "2027-08-10", "2027-09-10", NULL, NULL, NULL, false),
(30, "2027-10-10", "2027-11-10", NULL, NULL, NULL, false),
(30, "2027-11-15", "2027-12-15", NULL, NULL, NULL, false),
(30, "2027-12-15", "2028-01-15", NULL, NULL, NULL, false),
(30, "2028-01-15", "2028-02-15", NULL, NULL, NULL, false);

-- ---------------------------------------------------------
-- ---------------------------------------------------------
INSERT INTO employee 
(employeeName, userName, userPassword)

VALUES 
('Simon', 'Sim', '123'),
('Martin', 'Mar', '1234'),
('Joshua', 'Josh', '12345'),
('Slaven', 'Slav', '123456'),
('Mads', 'mads', '1234567'),
('Signe', 'signe', '12345678'),
('Ian', 'ian', '123456789'),
('David', 'david', '321'),
('Mikkel', 'mikkel', '4321'),
('Jens', 'jens', '54321'),
('Hjalte', 'hjalte', '654321'),
('Magnus', 'magnus', '7654321'),
('Jakob', 'jakob', '87654321'),
('August', 'august', '987654321'),
('Maya', 'maya', '111'),
('Maiken', 'maiken', '222'),
('Sebastian', 'sebastian', '333'),
('Bob', 'bob', '1'),
('HR', 'HR', 'HR');


-- ---------------------------------------------------------
-- ---------------------------------------------------------
INSERT INTO project
(projectName, projectDescription, projectManagerID ,timeSlotID)

VALUES 
('Wishlist app', 	'Byg en ønskeliste hjemmeside', 1, 3),
('Turistguide app', 'Lav en turistguide', 2, 5),
('Byg hus', 	'Byg et stort hus', 1, 4);
	
    
-- ---------------------------------------------------------
-- ---------------------------------------------------------
INSERT INTO projectemployee 
(employeeID, projectID)

VALUES
(1,1),
(1,2),
(2,1),
(2,3), 
(3,1),
(4,1),
(5,1),
(6,1),
(7,1),
(8,2),
(9,2),
(10,2),
(11,2),
(12,2),
(13,3),
(14,3),
(15,3),
(16,3),
(17,3);

-- ---------------------------------------------------------
-- ---------------------------------------------------------
INSERT INTO task 
(taskName, taskDescription, timeslotID, projectID)

VALUES 
('Userstory 1', 'add wish', 7, 1),
('Userstory 2', 'delete wish', 8, 1),
('Userstory 1', 'add attraction', 9, 2),
('Userstory 2', 'delete attraction', 10, 2),
('Task 1', 'build foundation', 14, 3),
('Task 2', 'place rooftiles', 15, 3);

-- ---------------------------------------------------------
-- ---------------------------------------------------------

INSERT INTO taskEmployee 
(employeeID, taskID)

VALUES 
(3,1),
(4,1),
(5,2),
(6,2),
(7,2),
(8,3),
(9,3),
(10,4),
(11,4),
(12,4),
(13,5),
(14,5),
(15,6),
(16,6),
(17,6);
-- ---------------------------------------------------------
-- ---------------------------------------------------------
INSERT INTO subtask 
(subtaskName, subtaskDescription, timeslotID, taskID, employeeID)

VALUES 
('GetMapping metode', 'controller metode', 16, 1, 3),
('PostMapping metode', 'controller metode', 17, 1, 4),
('PostMapping metode', 'controller metode', 18, 2, 5),
('add wish metode', 'Repo/service metode', 19, 2, 6),
('delete wish metode', 'Repo/service metode', 20, 2, 7),
('GetMapping metode', 'controller metode', 21, 3, 8),
('PostMapping metode', 'controller metode', 22, 3, 9),
('PostMapping metode', 'controller metode', 23, 4, 10),
('delete attraction metode', 'Repo metode', 24, 4, 11),
('delete attraction metode', 'Service metode', 25, 4, 12),
('get needed wood', 'x amount needed', 24, 5, 13),
('assembly of wood', 'x with x and y with y', 25, 5, 14),
('prep roof', 'use x to do so', 26, 6, 15),
('prep rooftiles', 'collect all rooftiles', 27, 6, 16),
('move rooftiles', 'to x spot on the roof', 28, 6, 17);

