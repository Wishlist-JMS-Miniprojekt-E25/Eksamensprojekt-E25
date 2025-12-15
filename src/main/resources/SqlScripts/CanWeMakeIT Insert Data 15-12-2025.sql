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
(30, "2028-01-15", "2028-02-15", NULL, NULL, NULL, false),
(30, "2027-03-10", "2027-04-10", NULL, NULL, NULL, false),
(30, "2027-04-10", "2027-05-10", NULL, NULL, NULL, false),
(30, "2027-05-10", "2027-06-10", NULL, NULL, NULL, false),
(30, "2027-06-10", "2027-07-10", NULL, NULL, NULL, false),
(30, "2027-07-10", "2027-08-10", NULL, NULL, NULL, false),
(30, "2027-08-10", "2027-09-10", NULL, NULL, NULL, false),
(30, "2027-10-10", "2027-11-10", NULL, NULL, NULL, false),
(30, "2027-11-15", "2027-12-15", NULL, NULL, NULL, false),
(30, "2027-12-15", "2028-01-15", NULL, NULL, NULL, false),
(30, "2028-01-15", "2028-02-15", NULL, NULL, NULL, false),
(30, "2027-03-10", "2027-04-10", NULL, NULL, NULL, false),
(30, "2027-04-10", "2027-05-10", NULL, NULL, NULL, false),
(30, "2027-05-10", "2027-06-10", NULL, NULL, NULL, false),
(30, "2027-06-10", "2027-07-10", NULL, NULL, NULL, false),
(30, "2027-07-10", "2027-08-10", NULL, NULL, NULL, false),
(30, "2027-08-10", "2027-09-10", NULL, NULL, NULL, false),
(30, "2027-10-10", "2027-11-10", NULL, NULL, NULL, false),
(30, "2027-11-15", "2027-12-15", NULL, NULL, NULL, false),
(30, "2027-12-15", "2028-01-15", NULL, NULL, NULL, false),
(30, "2028-01-15", "2028-02-15", NULL, NULL, NULL, false),
(30, "2027-03-10", "2027-04-10", NULL, NULL, NULL, false),
(30, "2027-04-10", "2027-05-10", NULL, NULL, NULL, false),
(30, "2027-05-10", "2027-06-10", NULL, NULL, NULL, false),
(30, "2027-06-10", "2027-07-10", NULL, NULL, NULL, false),
(30, "2027-07-10", "2027-08-10", NULL, NULL, NULL, false),
(30, "2027-08-10", "2027-09-10", NULL, NULL, NULL, false),
(30, "2027-10-10", "2027-11-10", NULL, NULL, NULL, false),
(30, "2027-11-15", "2027-12-15", NULL, NULL, NULL, false),
(30, "2027-12-15", "2028-01-15", NULL, NULL, NULL, false),
(30, "2028-01-15", "2028-02-15", NULL, NULL, NULL, false),
(30, "2027-03-10", "2027-04-10", NULL, NULL, NULL, false),
(30, "2027-04-10", "2027-05-10", NULL, NULL, NULL, false),
(30, "2027-05-10", "2027-06-10", NULL, NULL, NULL, false),
(30, "2027-06-10", "2027-07-10", NULL, NULL, NULL, false),
(30, "2027-07-10", "2027-08-10", NULL, NULL, NULL, false),
(30, "2027-08-10", "2027-09-10", NULL, NULL, NULL, false),
(30, "2027-10-10", "2027-11-10", NULL, NULL, NULL, false),
(30, "2028-01-15", "2028-02-15", NULL, NULL, NULL, false),
(30, "2027-07-10", "2027-08-10", NULL, NULL, NULL, false),
(30, "2027-08-10", "2027-09-10", NULL, NULL, NULL, false),
(30, "2027-10-10", "2027-11-10", NULL, NULL, NULL, false),
(30, "2028-01-15", "2028-02-15", NULL, NULL, NULL, false),
(30, "2026-06-20", "2026-07-20", "2026-07-20", 0, 240, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-08-20", "2026-09-20", "2026-09-20", 0, 240, TRUE),
(30, "2026-06-20", "2026-07-20", "2026-07-20", 0, 240, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-08-20", "2026-09-20", "2026-09-20", 0, 240, TRUE),
(30, "2026-06-20", "2026-07-20", "2026-07-20", 0, 240, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-08-20", "2026-09-20", "2026-09-20", 0, 240, TRUE),
(30, "2026-06-20", "2026-07-20", "2026-07-20", 0, 240, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-08-20", "2026-09-20", "2026-09-20", 0, 240, TRUE),
(30, "2026-06-20", "2026-07-20", "2026-07-20", 0, 240, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE),
(30, "2026-07-20", "2026-08-20", "2026-08-30", 10, 320, TRUE);


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
('Userstory 3', 'delete wish', 9, 1),
('Userstory 4', 'delete wish', 10, 1),
('Userstory 1', 'add attraction', 9, 2),
('Userstory 2', 'delete attraction', 14, 2),
('Userstory 3', 'delete attraction', 15, 2),
('Userstory 4', 'delete attraction', 16, 2),
('Task 1', 'build foundation', 17, 3),
('Task 2', 'place rooftiles', 18, 3),
('Task 3', 'build foundation', 19, 3),
('Task 4', 'place rooftiles', 20, 3);

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
(1,3),
(5,3),
(5,4),
(7,4),
(6,4),
(8,5),
(9,5),
(1,6),
(11,6),
(12,6),
(8,7),
(9,7),
(12,8),
(1,8),
(2,9),
(13,9),
(2,10),
(17,10),
(16,11),
(15,11),
(2,12),
(14,12);
-- ---------------------------------------------------------
-- ---------------------------------------------------------
INSERT INTO subtask 
(subtaskName, subtaskDescription, timeslotID, taskID, employeeID)

VALUES 
('GetMapping metode', 'controller metode', 21, 1, 3),
('PostMapping metode', 'controller metode', 22, 1, 4),
('PostMapping metode', 'controller metode', 23, 1, 3),
('add wish metode', 'Repo/service metode', 24, 1, 4),
('delete wish metode', 'Repo/service metode', 25, 2, 5),
('GetMapping metode', 'controller metode', 26, 2, 6),
('PostMapping metode', 'controller metode', 27, 2, 5),
('PostMapping metode', 'controller metode', 28, 2, 6),
('delete attraction metode', 'Repo metode', 29, 4, 5),
('delete attraction metode', 'Service metode', 30, 4, 6),
('get needed wood', 'x amount needed', 31, 4, 7),
('assembly of wood', 'x with x and y with y', 32, 4, 7),
('prep roof', 'use x to do so', 33, 5, 8),
('prep rooftiles', 'collect all rooftiles', 34, 5, 9),
('move rooftiles', 'to x spot on the roof', 35, 5, 8),
('GetMapping metode', 'controller metode', 36, 5, 9),
('PostMapping metode', 'controller metode', 37, 6, 1),
('PostMapping metode', 'controller metode', 38, 6, 11),
('add wish metode', 'Repo/service metode', 39, 6, 12),
('delete wish metode', 'Repo/service metode', 40, 6, 1),
('GetMapping metode', 'controller metode', 41, 7, 8),
('PostMapping metode', 'controller metode', 42, 7, 9),
('PostMapping metode', 'controller metode', 43, 7, 8),
('delete attraction metode', 'Repo metode', 44, 7, 9),
('delete attraction metode', 'Service metode', 45, 8, 12),
('get needed wood', 'x amount needed', 46, 8, 1),
('assembly of wood', 'x with x and y with y', 47, 8, 12),
('prep roof', 'use x to do so', 48, 8, 1),
('prep rooftiles', 'collect all rooftiles', 49, 9, 2),
('move rooftiles', 'to x spot on the roof', 50, 9, 13),
('GetMapping metode', 'controller metode', 51, 9, 2),
('PostMapping metode', 'controller metode', 52, 9, 13),
('PostMapping metode', 'controller metode', 53, 10, 2),
('add wish metode', 'Repo/service metode', 54, 10, 17),
('delete wish metode', 'Repo/service metode', 55, 10, 2),
('GetMapping metode', 'controller metode', 56, 10, 17),
('PostMapping metode', 'controller metode', 57, 11, 15),
('PostMapping metode', 'controller metode', 58, 11, 16),
('delete attraction metode', 'Repo metode', 59, 11, 15),
('delete attraction metode', 'Service metode', 60, 11, 16),
('get needed wood', 'x amount needed', 61, 12, 2),
('assembly of wood', 'x with x and y with y', 62, 12, 14),
('prep roof', 'use x to do so', 63, 12, 2),
('prep rooftiles', 'collect all rooftiles', 64, 12, 14),
('move rooftiles', 'to x spot on the roof', 65, 12, 2),
('GetMapping metode', 'controller metode', 66, 3, 1),
('PostMapping metode', 'controller metode', 67, 3, 5),
('PostMapping metode', 'controller metode', 68, 3, 1),
('delete attraction metode', 'Repo metode', 69, 3, 5);

-- ----------------------------------------------------------
-- ----------------------------------------------------------

INSERT INTO archivedproject
(projectID, projectManagerID, projectName, projectDescription, timeslotID)

VALUES
(4, 1, 'test', 'test', 70),
(5, 1, 'test2', 'test2', 71);

-- ---------------------------------------------------------------
-- ----------------------------------------------------------------

INSERT INTO archivedtask
(taskID, taskName, taskDescription, timeslotID, projectID)

VALUES
(13, 'test', 'test', 72, 4),
(14, 'test2', 'test2', 73, 4),
(15, 'test3', 'test3', 74, 4),
(16, 'test', 'test', 75, 5),
(17, 'test2', 'test2', 76, 5),
(18, 'test3', 'test3', 77, 5);

-- --------------------------------------------------------------
-- ---------------------------------------------------------------

INSERT INTO archivedsubtask
(subtaskID, subtaskName, subtaskDescription, timeslotID, taskID, employeeID)

VALUES
(50, 'test', 'test', 78, 13, 2),
(51, 'test2', 'test2', 79, 13, 2),
(52, 'test3', 'test3', 80, 13, 2),
(53, 'test', 'test', 81, 13, 2),
(54, 'test2', 'test2', 82, 13, 2),
(55, 'test3', 'test3', 83, 13, 2),
(56, 'test', 'test', 84, 13, 2),
(57, 'test2', 'test2', 85, 13, 2),
(58, 'test3', 'test3', 86, 13, 2),
(59, 'test', 'test', 87, 13, 2),
(60, 'test2', 'test2', 88, 13, 2),
(61, 'test3', 'test3', 89, 13, 2),
(62, 'test', 'test', 78, 90, 2),
(63, 'test2', 'test2', 91, 13, 2),
(64, 'test3', 'test3', 92, 13, 2),
(65, 'test', 'test', 78, 93, 2),
(66, 'test2', 'test2', 78, 94, 2),
(67, 'test3', 'test3', 78, 95, 2);