-- Brug databasen
USE canwemakeit;

INSERT INTO timeSlot 
(plannedDays, plannedStartDate, plannedFinishDate, actualFinishDate, differenceInDays, totalWorkhours, isDone)

VALUES 
(30, "2025-11-20", "2025-11-25", NULL, NULL, NULL, false),
(30, "2025-11-20", "2025-12-20", NULL, NULL, NULL, false),
(30, "2025-12-20", "2026-01-20", NULL, NULL, NULL, false),
(30, "2026-02-20", "2026-03-20", NULL, NULL, NULL, false),
(30, "2026-03-20", "2026-04-20", NULL, NULL, NULL, false),
(30, "2026-04-20", "2026-05-20", NULL, NULL, NULL, false),
(30, "2026-05-20", "2026-06-20", NULL, NULL, NULL, false),
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
(30, "2027-10-10", "2027-11-10", NULL, NULL, NULL, false),
(30, "2028-01-15", "2028-02-15", "2028-02-15", 0, 240, TRUE),
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
('Wishlist app', 	'Byg en ønskeliste hjemmeside', 1, 1),
('Turistguide app', 'Lav en turistguide', 2, 2),
('Byg hus', 	'Byg et stort hus', 1, 3);
	
    
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
('Userstory 1', 'add wish', 4, 1),
('Userstory 2', 'delete wish', 5, 1),
('Userstory 3', 'delete wish', 6, 1),
('Userstory 4', 'delete wish', 7, 1),
('Userstory 1', 'add attraction', 8, 2),
('Userstory 2', 'delete attraction', 9, 2),
('Userstory 3', 'delete attraction', 10, 2),
('Userstory 4', 'delete attraction', 11, 2),
('Task 1', 'build foundation', 12, 3),
('Task 2', 'place rooftiles', 13, 3),
('Task 3', 'build foundation', 14, 3),
('Task 4', 'place rooftiles', 15, 3);

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
('GetMapping metode', 'controller metode', 16, 1, 3),
('PostMapping metode', 'controller metode', 17, 1, 4),
('PostMapping metode', 'controller metode', 18, 1, 3),
('add wish metode', 'Repo/service metode', 19, 1, 4),
('delete wish metode', 'Repo/service metode', 20, 2, 5),
('GetMapping metode', 'controller metode', 21, 2, 6),
('PostMapping metode', 'controller metode', 22, 2, 5),
('PostMapping metode', 'controller metode', 23, 2, 6),
('delete attraction metode', 'Repo metode', 24, 4, 5),
('delete attraction metode', 'Service metode', 25, 4, 6),
('get needed wood', 'x amount needed', 26, 4, 7),
('assembly of wood', 'x with x and y with y', 27, 4, 7),
('prep roof', 'use x to do so', 28, 5, 8),
('prep rooftiles', 'collect all rooftiles', 29, 5, 9),
('move rooftiles', 'to x spot on the roof', 30, 5, 8),
('GetMapping metode', 'controller metode', 31, 5, 9),
('PostMapping metode', 'controller metode', 32, 6, 1),
('PostMapping metode', 'controller metode', 33, 6, 11),
('add wish metode', 'Repo/service metode', 34, 6, 12),
('delete wish metode', 'Repo/service metode', 35, 6, 1),
('GetMapping metode', 'controller metode', 36, 7, 8),
('PostMapping metode', 'controller metode', 37, 7, 9),
('PostMapping metode', 'controller metode', 38, 7, 8),
('delete attraction metode', 'Repo metode', 39, 7, 9),
('delete attraction metode', 'Service metode', 40, 8, 12),
('get needed wood', 'x amount needed', 41, 8, 1),
('assembly of wood', 'x with x and y with y', 42, 8, 12),
('prep roof', 'use x to do so', 43, 8, 1),
('prep rooftiles', 'collect all rooftiles', 44, 9, 2),
('move rooftiles', 'to x spot on the roof', 45, 9, 13),
('GetMapping metode', 'controller metode', 46, 9, 2),
('PostMapping metode', 'controller metode', 47, 9, 13),
('PostMapping metode', 'controller metode', 48, 10, 2),
('add wish metode', 'Repo/service metode', 49, 10, 17),
('delete wish metode', 'Repo/service metode', 50, 10, 2),
('GetMapping metode', 'controller metode', 51, 10, 17),
('PostMapping metode', 'controller metode', 52, 11, 15),
('PostMapping metode', 'controller metode', 53, 11, 16),
('delete attraction metode', 'Repo metode', 54, 11, 15),
('delete attraction metode', 'Service metode', 55, 11, 16),
('get needed wood', 'x amount needed', 56, 12, 2),
('assembly of wood', 'x with x and y with y', 57, 12, 14),
('prep roof', 'use x to do so', 58, 12, 2),
('prep rooftiles', 'collect all rooftiles', 59, 12, 14),
('move rooftiles', 'to x spot on the roof', 60, 12, 2),
('GetMapping metode', 'controller metode', 61, 3, 1),
('PostMapping metode', 'controller metode', 62, 3, 5),
('PostMapping metode', 'controller metode', 63, 3, 1),
('delete attraction metode', 'Repo metode', 64, 3, 5);

-- ----------------------------------------------------------
-- ----------------------------------------------------------

INSERT INTO archivedproject
(projectID, projectManagerID, projectName, projectDescription, timeslotID)

VALUES
(4, 1, 'test', 'test', 65),
(5, 1, 'test2', 'test2', 66);

-- ---------------------------------------------------------------
-- ----------------------------------------------------------------

INSERT INTO archivedtask
(taskID, taskName, taskDescription, timeslotID, projectID)

VALUES
(13, 'test', 'test', 67, 4),
(14, 'test2', 'test2', 68, 4),
(15, 'test3', 'test3', 69, 4),
(16, 'test', 'test', 70, 5),
(17, 'test2', 'test2', 71, 5),
(18, 'test3', 'test3', 72, 5);

-- --------------------------------------------------------------
-- ---------------------------------------------------------------

INSERT INTO archivedsubtask
(subtaskID, subtaskName, subtaskDescription, timeslotID, taskID, employeeID)

VALUES
(50, 'test', 'test', 73, 13, 2),
(51, 'test2', 'test2', 74, 13, 2),
(52, 'test3', 'test3', 75, 13, 2),
(53, 'test', 'test', 76, 14, 2),
(54, 'test2', 'test2', 77, 14, 2),
(55, 'test3', 'test3', 78, 14, 2),
(56, 'test', 'test', 79, 15, 2),
(57, 'test2', 'test2', 80, 15, 2),
(58, 'test3', 'test3', 81, 15, 2),
(59, 'test', 'test', 82, 16, 2),
(60, 'test2', 'test2', 83, 16, 2),
(61, 'test3', 'test3', 84, 16, 2),
(62, 'test', 'test', 85, 17, 2),
(63, 'test2', 'test2', 86, 17, 2),
(64, 'test3', 'test3', 87, 17, 2),
(65, 'test', 'test', 88, 18, 2),
(66, 'test2', 'test2', 89, 18, 2),
(67, 'test3', 'test3', 90, 18, 2);