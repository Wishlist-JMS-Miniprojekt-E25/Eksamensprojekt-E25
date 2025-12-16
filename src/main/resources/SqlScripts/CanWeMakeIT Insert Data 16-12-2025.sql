-- Brug databasen
USE canwemakeit;

INSERT INTO timeSlot 
(plannedDays, plannedStartDate, plannedFinishDate, actualFinishDate, differenceInDays, totalWorkhours, isDone)

VALUES 

-- Timeslots for aktive projects
(30,'2025-11-20','2025-12-20',NULL,NULL,0,FALSE), -- timeslotID 1 - projectID 1
(30,'2025-11-20','2025-12-20',NULL,NULL,0,FALSE), -- timeslotID 2 - projectID 2
(30,'2025-12-20','2026-01-20',NULL,NULL,0,FALSE), -- timeslotID 3 - projectID 3

-- Timeslots for aktive tasks 
(5,'2025-11-20','2025-11-25',NULL,NULL,0,FALSE), -- timeslotID 4 - projectID 1 - taskID 1
(5,'2025-11-25','2025-11-30',NULL,NULL,0,FALSE), -- timeslotID 5 - projectID 1 - taskID 2
(5,'2025-12-01','2025-12-06',NULL,NULL,0,FALSE), -- timeslotID 6 - projectID 1 - taskID 3
(5,'2025-12-07','2025-12-12',NULL,NULL,0,FALSE), -- timeslotID 7 - projectID 1 - taskID 4
(5,'2025-11-20','2025-11-25',NULL,NULL,0,FALSE), -- timeslotID 8 - projectID 2 - taskID 5
(5,'2025-11-25','2025-11-30',NULL,NULL,0,FALSE), -- timeslotID 9 - projectID 2 - taskID 6
(5,'2025-12-01','2025-12-06',NULL,NULL,0,FALSE), -- timeslotID 10 - projectID 2 - taskID 7
(5,'2025-12-07','2025-12-12',NULL,NULL,0,FALSE), -- timeslotID 11 - projectID 2 - taskID 8
(5,'2025-12-20','2025-12-25',NULL,NULL,0,FALSE), -- timeslotID 12 - projectID 3 - taskID 9
(5,'2025-12-30','2026-01-05',NULL,NULL,0,FALSE), -- timeslotID 13 - projectID 3 - taskID 10
(5,'2026-01-06','2026-01-11',NULL,NULL,0,FALSE), -- timeslotID 14 - projectID 3 - taskID 11
(5,'2026-01-11','2026-01-15',NULL,NULL,0,FALSE), -- timeslotID 15 - projectID 3 - taskID 12

-- Timeslots for aktive subtasks
(1,'2025-11-20','2025-11-20',NULL,NULL,0,FALSE), -- timeslotID 16 - taskID 1 - subtaskID 1
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 17 - taskID 1 - subtaskID 2
(1,'2025-11-22','2025-11-22',NULL,NULL,0,FALSE), -- timeslotID 18 - taskID 1 - subtaskID 3
(1,'2025-11-23','2025-11-23',NULL,NULL,0,FALSE), -- timeslotID 19 - taskID 1 - subtaskID 4
(1,'2025-11-25','2025-11-25',NULL,NULL,0,FALSE), -- timeslotID 20 - taskID 2 - subtaskID 5
(1,'2025-11-26','2025-11-26',NULL,NULL,0,FALSE), -- timeslotID 21 - taskID 2 - subtaskID 6
(1,'2025-11-27','2025-11-27',NULL,NULL,0,FALSE), -- timeslotID 22 - taskID 2 - subtaskID 7
(1,'2025-11-28','2025-11-28',NULL,NULL,0,FALSE), -- timeslotID 23 - taskID 2 - subtaskID 8
(1,'2025-12-01','2025-12-01',NULL,NULL,0,FALSE), -- timeslotID 24 - taskID 3 - subtaskID 9
(1,'2025-12-02','2025-12-02',NULL,NULL,0,FALSE), -- timeslotID 25 - taskID 3 - subtaskID 10
(1,'2025-12-03','2025-12-03',NULL,NULL,0,FALSE), -- timeslotID 26 - taskID 3 - subtaskID 11
(1,'2025-12-04','2025-12-04',NULL,NULL,0,FALSE), -- timeslotID 27 - taskID 3 - subtaskID 12
(1,'2025-12-07','2025-12-07',NULL,NULL,0,FALSE), -- timeslotID 28 - taskID 4 - subtaskID 13
(1,'2025-12-08','2025-12-08',NULL,NULL,0,FALSE), -- timeslotID 29 - taskID 4 - subtaskID 14
(1,'2025-12-09','2025-12-09',NULL,NULL,0,FALSE), -- timeslotID 30 - taskID 4 - subtaskID 15
(1,'2025-12-10','2025-12-10',NULL,NULL,0,FALSE), -- timeslotID 31 - taskID 4 - subtaskID 16
(1,'2025-12-20','2025-12-20',NULL,NULL,0,FALSE), -- timeslotID 32 - taskID 5 - subtaskID 17
(1,'2025-12-21','2025-12-21',NULL,NULL,0,FALSE), -- timeslotID 33 - taskID 5 - subtaskID 18
(1,'2025-12-22','2025-12-22',NULL,NULL,0,FALSE), -- timeslotID 34 - taskID 5 - subtaskID 19
(1,'2025-12-23','2025-12-23',NULL,NULL,0,FALSE), -- timeslotID 35 - taskID 5 - subtaskID 20
(1,'2025-12-30','2025-12-30',NULL,NULL,0,FALSE), -- timeslotID 36 - taskID 6 - subtaskID 21
(1,'2026-01-01','2026-01-01',NULL,NULL,0,FALSE), -- timeslotID 37 - taskID 6 - subtaskID 22
(1,'2026-01-02','2026-01-02',NULL,NULL,0,FALSE), -- timeslotID 38 - taskID 6 - subtaskID 23
(1,'2026-01-03','2026-01-03',NULL,NULL,0,FALSE), -- timeslotID 39 - taskID 6 - subtaskID 24
(1,'2026-01-06','2026-01-06',NULL,NULL,0,FALSE), -- timeslotID 40 - taskID 7 - subtaskID 25
(1,'2026-01-07','2026-01-07',NULL,NULL,0,FALSE), -- timeslotID 41 - taskID 7 - subtaskID 26
(1,'2026-01-08','2026-01-08',NULL,NULL,0,FALSE), -- timeslotID 42 - taskID 7 - subtaskID 27
(1,'2026-01-09','2026-01-09',NULL,NULL,0,FALSE), -- timeslotID 43 - taskID 7 - subtaskID 28
(1,'2025-11-20','2025-11-20',NULL,NULL,0,FALSE), -- timeslotID 44 - taskID 8 - subtaskID 29
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 45 - taskID 8 - subtaskID 30
(1,'2025-11-22','2025-11-22',NULL,NULL,0,FALSE), -- timeslotID 46 - taskID 8 - subtaskID 31
(1,'2025-11-23','2025-11-23',NULL,NULL,0,FALSE), -- timeslotID 47 - taskID 8 - subtaskID 32
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 48 - taskID 9 - subtaskID 33
(1,'2025-11-22','2025-11-22',NULL,NULL,0,FALSE), -- timeslotID 49 - taskID 9 - subtaskID 34
(1,'2025-11-23','2025-11-23',NULL,NULL,0,FALSE), -- timeslotID 50 - taskID 9 - subtaskID 35
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 51 - taskID 9 - subtaskID 36
(1,'2025-11-22','2025-11-22',NULL,NULL,0,FALSE), -- timeslotID 52 - taskID 10 - subtaskID 37
(1,'2025-11-23','2025-11-23',NULL,NULL,0,FALSE), -- timeslotID 53 - taskID 10 - subtaskID 38
(1,'2025-11-20','2025-11-20',NULL,NULL,0,FALSE), -- timeslotID 54 - taskID 10 - subtaskID 39
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 55 - taskID 10 - subtaskID 40
(1,'2025-11-22','2025-11-22',NULL,NULL,0,FALSE), -- timeslotID 56 - taskID 11 - subtaskID 41
(1,'2025-11-23','2025-11-23',NULL,NULL,0,FALSE), -- timeslotID 57 - taskID 11 - subtaskID 42
(1,'2025-11-20','2025-11-20',NULL,NULL,0,FALSE), -- timeslotID 58 - taskID 11 - subtaskID 43
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 59 - taskID 11 - subtaskID 44
(1,'2025-11-22','2025-11-22',NULL,NULL,0,FALSE), -- timeslotID 60 - taskID 12 - subtaskID 45
(1,'2025-11-23','2025-11-23',NULL,NULL,0,FALSE), -- timeslotID 61 - taskID 12 - subtaskID 46
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 62 - taskID 12 - subtaskID 47
(1,'2025-11-21','2025-11-21',NULL,NULL,0,FALSE), -- timeslotID 63 - taskID 12 - subtaskID 48

-- Timeslots for arkiveret projects
(30,'2025-08-01','2025-09-01','2025-09-01',0,27,TRUE), -- timeslotID 64 - projectID 4
(30,'2025-09-01','2025-10-01','2025-10-01',0,27,TRUE), -- timeslotID 65 - projectID 5

-- Timeslots for arkiveret tasks
(5,'2025-08-01','2025-08-06','2025-08-06',0,9,TRUE), -- timeslotID 66 - projectID 4 - taskID 13
(5,'2025-08-07','2025-08-12','2025-08-12',0,9,TRUE), -- timeslotID 67 - projectID 4 - taskID 14
(5,'2025-08-13','2025-08-18','2025-08-18',0,9,TRUE), -- timeslotID 68 - projectID 4 - taskID 15
(5,'2025-08-01','2025-08-06','2025-08-06',0,9,TRUE), -- timeslotID 69 - projectID 5 - taskID 16
(5,'2025-08-07','2025-08-12','2025-08-12',0,9,TRUE), -- timeslotID 70 - projectID 5 - taskID 17
(5,'2025-08-13','2025-08-18','2025-08-18',0,9,TRUE), -- timeslotID 71 - projectID 5 - taskID 18

-- Timeslots for arkiveret subtasks
(1,'2025-08-01','2025-08-01','2025-08-01',0,3,TRUE), -- timeslotID 72 - taskID 13 - subtaskID 49
(1,'2025-08-02','2025-08-02','2025-08-02',0,3,TRUE), -- timeslotID 73 - taskID 13 - subtaskID 50
(1,'2025-08-03','2025-08-03','2025-08-03',0,3,TRUE), -- timeslotID 74 - taskID 13 - subtaskID 51
(1,'2025-08-01','2025-08-01','2025-08-01',0,3,TRUE), -- timeslotID 75 - taskID 14 - subtaskID 52
(1,'2025-08-02','2025-08-02','2025-08-02',0,3,TRUE), -- timeslotID 76 - taskID 14 - subtaskID 53
(1,'2025-08-03','2025-08-03','2025-08-03',0,3,TRUE), -- timeslotID 77 - taskID 14 - subtaskID 54
(1,'2025-08-01','2025-08-01','2025-08-01',0,3,TRUE), -- timeslotID 78 - taskID 15 - subtaskID 55
(1,'2025-08-02','2025-08-02','2025-08-02',0,3,TRUE), -- timeslotID 79 - taskID 15 - subtaskID 56
(1,'2025-08-03','2025-08-03','2025-08-03',0,3,TRUE), -- timeslotID 80 - taskID 15 - subtaskID 57
(1,'2025-08-01','2025-08-01','2025-08-01',0,3,TRUE), -- timeslotID 81 - taskID 16 - subtaskID 58
(1,'2025-08-02','2025-08-02','2025-08-02',0,3,TRUE), -- timeslotID 82 - taskID 16 - subtaskID 59
(1,'2025-08-03','2025-08-03','2025-08-03',0,3,TRUE), -- timeslotID 83 - taskID 16 - subtaskID 60
(1,'2025-08-01','2025-08-01','2025-08-01',0,3,TRUE), -- timeslotID 84 - taskID 17 - subtaskID 61
(1,'2025-08-02','2025-08-02','2025-08-02',0,3,TRUE), -- timeslotID 85 - taskID 17 - subtaskID 62
(1,'2025-08-03','2025-08-03','2025-08-03',0,3,TRUE), -- timeslotID 86 - taskID 17 - subtaskID 63
(1,'2025-08-01','2025-08-01','2025-08-01',0,3,TRUE), -- timeslotID 87 - taskID 18 - subtaskID 64
(1,'2025-08-02','2025-08-02','2025-08-02',0,3,TRUE), -- timeslotID 88 - taskID 18 - subtaskID 65
(1,'2025-08-03','2025-08-03','2025-08-03',0,3,TRUE); -- timeslotID 89 - taskID 18 - subtaskID 66

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
('Wishlist app', 	'Byg en ønskeliste hjemmeside', 1, 1), -- projectID 1
('Turistguide app', 'Lav en turistguide', 2, 2), -- projectID 2
('Byg hus', 	'Byg et stort hus', 1, 3); -- projectID 3
	
    
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
('Userstory 1', 'add wish', 4, 1), -- projectID 1 - taskID 1
('Userstory 2', 'delete wish', 5, 1), -- projectID 1 - taskID 2
('Userstory 3', 'edit wish', 6, 1), -- projectID 1 - taskID 3
('Userstory 4', 'show wish', 7, 1), -- projectID 1 - taskID 4
('Userstory 1', 'add attraction', 8, 2), -- projectID 2 - taskID 5
('Userstory 2', 'delete attraction', 9, 2), -- projectID 2 - taskID 6
('Userstory 3', 'edit attraction', 10, 2), -- projectID 2 -taskID 7
('Userstory 4', 'show attraction', 11, 2), -- projectID 2 - taskID 8
('Task 1', 'build foundation', 12, 3), -- projectID 3 - taskID 9
('Task 2', 'place rooftiles', 13, 3), -- projectID 3 - taskID 10
('Task 3', 'build foundation', 14, 3), -- projectID 3 - taskID 11
('Task 4', 'place rooftiles', 15, 3); -- projectID 3 - taskID 12

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
('GetMapping metode', 'controller metode', 16, 1, 3), -- taskID 1 - subtaskID 1
('PostMapping metode', 'controller metode', 17, 1, 4), -- taskID 1 - subtaskID 2
('PostMapping metode', 'controller metode', 18, 1, 3), -- taskID 1 - subtaskID 3
('add wish metode', 'Repo/service metode', 19, 1, 4), -- taskID 1 - subtaskID 4
('delete wish metode', 'Repo/service metode', 20, 2, 5), -- taskID 2 - subtaskID 5 
('GetMapping metode', 'controller metode', 21, 2, 6), -- taskID 2 - subtaskID 6
('PostMapping metode', 'controller metode', 22, 2, 5), -- taskID 2 - subtaskID 7
('PostMapping metode', 'controller metode', 23, 2, 6), -- taskID 2 - subtaskID 8
('delete attraction metode', 'Repo metode', 24, 3, 5), -- taskID 3 - subtaskID 9
('delete attraction metode', 'Service metode', 25, 3, 6), -- taskID 3 - subtaskID 10 
('get needed wood', 'x amount needed', 26, 3, 7), -- taskID 3 - subtaskID 11
('assembly of wood', 'x with x and y with y', 27, 3, 7), -- taskID 3 - subtaskID 12
('prep roof', 'use x to do so', 28, 4, 8), -- taskID 4 - subtaskID 13
('prep rooftiles', 'collect all rooftiles', 29, 4, 9), -- taskID 4 - subtaskID 14 
('move rooftiles', 'to x spot on the roof', 30, 4, 8), -- taskID 4 - subtaskID 15
('GetMapping metode', 'controller metode', 31, 4, 9), -- taskID 4 - subtaskID 16
('PostMapping metode', 'controller metode', 32, 5, 1), -- taskID 5 - subtaskID 17
('PostMapping metode', 'controller metode', 33, 5, 11), -- taskID 5 - subtaskID 18
('add wish metode', 'Repo/service metode', 34, 5, 12), -- taskID 5 - subtaskID 19
('delete wish metode', 'Repo/service metode', 35, 5, 1), -- taskID 5 - subtaskID 20
('GetMapping metode', 'controller metode', 36, 6, 8), -- taskID 6 - subtaskID 21
('PostMapping metode', 'controller metode', 37, 6, 9), -- taskID 6 - subtaskID 22
('PostMapping metode', 'controller metode', 38, 6, 8), -- taskID 6 - subtaskID 23
('delete attraction metode', 'Repo metode', 39, 6, 9), -- taskID 6 - subtaskID 24
('delete attraction metode', 'Service metode', 40, 7, 12), -- taskID 7 - subtaskID 25 
('get needed wood', 'x amount needed', 41, 7, 1), -- taskID 7 - subtaskID 26
('assembly of wood', 'x with x and y with y', 42, 7, 12), -- taskID 7 - subtaskID 27
('prep roof', 'use x to do so', 43, 7, 1), -- taskID 7 - subtaskID 28
('prep rooftiles', 'collect all rooftiles', 44, 8, 2), -- taskID 8 - subtaskID 29 
('move rooftiles', 'to x spot on the roof', 45, 8, 13), -- taskID 8 - subtaskID 30
('GetMapping metode', 'controller metode', 46, 8, 2), -- taskID 8 - subtaskID 31
('PostMapping metode', 'controller metode', 47, 8, 13), -- taskID 8 - subtaskID 32
('PostMapping metode', 'controller metode', 48, 9, 2), -- taskID 9 - subtaskID 33
('add wish metode', 'Repo/service metode', 49, 9, 17), -- taskID 9 - subtaskID 34
('delete wish metode', 'Repo/service metode', 50, 9, 2), -- taskID 9 - subtaskID 35
('GetMapping metode', 'controller metode', 51, 9, 17), -- taskID 9 - subtaskID 36
('PostMapping metode', 'controller metode', 52, 10, 15), -- taskID 10 - subtaskID 37
('PostMapping metode', 'controller metode', 53, 10, 16), -- taskID 10 - subtaskID 38
('delete attraction metode', 'Repo metode', 54, 10, 15), -- taskID 10 - subtaskID 39
('delete attraction metode', 'Service metode', 55, 10, 16), -- taskID 10 - subtaskID 40 
('get needed wood', 'x amount needed', 56, 11, 2), -- taskID 11 - subtaskID 41
('assembly of wood', 'x with x and y with y', 57, 11, 14), -- taskID 11 - subtaskID 42
('prep roof', 'use x to do so', 58, 11, 2), -- taskID 11 - subtaskID 43
('prep rooftiles', 'collect all rooftiles', 59, 11, 14), -- taskID 11 - subtaskID 44 
('move rooftiles', 'to x spot on the roof', 60, 12, 2), -- taskID 12 - subtaskID 45
('GetMapping metode', 'controller metode', 61, 12, 1), -- taskID 12 - subtaskID 46
('PostMapping metode', 'controller metode', 62, 12, 5), -- taskID 12 - subtaskID 47
('PostMapping metode', 'controller metode', 63, 12, 1); -- taskID 12 - subtaskID 48

-- ----------------------------------------------------------
-- ----------------------------------------------------------

INSERT INTO archivedproject
(projectID, projectManagerID, projectName, projectDescription, timeslotID)

VALUES
(4, 1, 'test', 'test', 64), -- projectID 4
(5, 1, 'test2', 'test2', 65); -- projectID 5

-- ---------------------------------------------------------------
-- ----------------------------------------------------------------

INSERT INTO archivedtask
(taskID, taskName, taskDescription, timeslotID, projectID)

VALUES
(13, 'test', 'test', 66, 4), -- projectID 4 - taskID 13
(14, 'test2', 'test2', 67, 4), -- projectID 4 - taskID 14 
(15, 'test3', 'test3', 68, 4), -- projectID 4 - taskID 15 
(16, 'test', 'test', 69, 5), -- projectID 5 - taskID 16 
(17, 'test2', 'test2', 70, 5), -- projectID 5 - taskID 17 
(18, 'test3', 'test3', 71, 5); -- projectID 5 - taskID 18 

-- --------------------------------------------------------------
-- ---------------------------------------------------------------

INSERT INTO archivedsubtask
(subtaskID, subtaskName, subtaskDescription, timeslotID, taskID, employeeID)

VALUES
(49, 'test', 'test', 72, 13, 2), -- taskID 13 - subtaskID 49
(50, 'test2', 'test2', 73, 13, 2), -- taskID 13 - subtaskID 50
(51, 'test3', 'test3', 74, 13, 2), -- taskID 13 - subtaskID 51
(52, 'test', 'test', 75, 14, 2), -- taskID 14 - subtaskID 52
(53, 'test2', 'test2', 76, 14, 2), -- taskID 14 - subtaskID 53
(54, 'test3', 'test3', 77, 14, 2), -- taskID 14 - subtaskID 54
(55, 'test', 'test', 78, 15, 2), -- taskID 15 - subtaskID 55
(56, 'test2', 'test2', 79, 15, 2), -- taskID 15 - subtaskID 56
(57, 'test3', 'test3', 80, 15, 2), -- taskID 15 - subtaskID 57
(58, 'test', 'test', 81, 16, 2), -- taskID 16 - subtaskID 58
(59, 'test2', 'test2', 82, 16, 2), -- taskID 16 - subtaskID 59
(60, 'test3', 'test3', 83, 16, 2), -- taskID 16 - subtaskID 60
(61, 'test', 'test', 84, 17, 2), -- taskID 17 - subtaskID 61
(62, 'test2', 'test2', 85, 17, 2), -- taskID 17 - subtaskID 62
(63, 'test3', 'test3', 86, 17, 2), -- taskID 17 - subtaskID 63
(64, 'test', 'test', 87, 18, 2), -- taskID 18 - subtaskID 64
(65, 'test2', 'test2', 88, 18, 2), -- taskID 18 - subtaskID 65
(66, 'test3', 'test3', 89, 18, 2); -- taskID 18 - subtaskID 66