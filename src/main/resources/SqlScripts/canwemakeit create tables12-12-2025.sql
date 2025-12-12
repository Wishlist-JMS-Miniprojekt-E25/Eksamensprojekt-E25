-- Brug databasen
USE canwemakeit;

-- Drop eksisterende tabeller hvis de findes ()
DROP TABLE IF EXISTS archivedProject;
DROP TABLE IF EXISTS archivedTask;
DROP TABLE IF EXISTS archivedSubtask;
DROP TABLE IF EXISTS subtask;
DROP TABLE IF EXISTS taskEmployee;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS projectEmployee;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS timeSlot;


-- Opret tabeller
CREATE TABLE timeSlot
(
	timeSlotID 			INT AUTO_INCREMENT PRIMARY KEY,
    plannedDays			INT,
    plannedStartDate 	DATE,
    plannedFinishDate 	DATE,
    actualFinishDate 	DATE,
    differenceInDays	INT,
    totalWorkhours      INT,
    isDone				boolean
);

CREATE TABLE Employee
(
    employeeID     		INT AUTO_INCREMENT PRIMARY KEY,
    employeeName 		VARCHAR(250),
    userName			VARCHAR(250),
    userPassword		VARCHAR(250)
);

    
CREATE TABLE project
(
    projectID   		INT AUTO_INCREMENT PRIMARY KEY,
    projectManagerID	INT, FOREIGN KEY(projectManagerID) references employee (employeeID) ON DELETE CASCADE, 
    projectName 		VARCHAR(100) NOT NULL,
    projectDescription 	VARCHAR(500),
    timeSlotID 			INT, FOREIGN KEY (timeSlotID) references timeSlot (timeSlotID) 
);

CREATE TABLE projectEmployee
(
	employeeID     		INT,
    FOREIGN KEY (employeeID) references employee (employeeID) ON DELETE CASCADE,

    projectID 			INT,
    FOREIGN KEY (projectID) references project (projectID) ON DELETE CASCADE
);


CREATE TABLE task
(
    taskID      		INT AUTO_INCREMENT PRIMARY KEY,
    taskName 			VARCHAR(250) NOT NULL,
    taskDescription 	VARCHAR(500) NOT NULL,
    
    timeSlotID 			INT,
    FOREIGN KEY (timeSlotID) references timeSlot (timeSlotID), 
    
    projectID 			INT,
    FOREIGN KEY (projectID) references project (projectID) ON DELETE CASCADE

);

CREATE TABLE archivedSubtask
(
	archivedSubtaskID	INT AUTO_increment PRIMARY KEY,
    subtaskID 			INT,
    subtaskName			VARCHAR(250),
    subtaskDescription	VARCHAR(500),
    timeslotID			INT,
    taskID				INT,
    employeeID			INT,
    FOREIGN KEY(timeslotID) references timeslot (timeslotID),
    FOREIGN KEY(employeeID) references employee (employeeID)
    );
    
    CREATE TABLE archivedProject
(
	archivedProjectID	INT AUTO_increment PRIMARY KEY,
    projectID 			INT,
    projectManagerID	INT,
    projectName     	VARCHAR(250),
    projectDescription	VARCHAR(500),
    timeslotID			INT,
    FOREIGN KEY(projectManagerID) references employee (employeeID),
    FOREIGN KEY(timeslotID) references timeslot (timeslotID)
    );
    
    CREATE TABLE archivedTask
(
	archivedTaskID		INT AUTO_increment PRIMARY KEY,
    taskID 				INT,
    taskName			VARCHAR(250),
    taskDescription		VARCHAR(500),
    timeslotID			INT,
    projectID			INT,
    FOREIGN KEY(timeslotID) references timeslot (timeslotID)
    );

CREATE TABLE taskEmployee
(
	employeeID     		INT,
    FOREIGN KEY (employeeID) references employee (employeeID) ON DELETE CASCADE,

    taskID 			INT,
    FOREIGN KEY (taskID) references task (taskID) ON DELETE CASCADE
);
	

CREATE TABLE subtask
(
	subtaskID 			INT AUTO_INCREMENT PRIMARY KEY,
    subtaskName			VARCHAR(250) NOT NULL,
    subtaskDescription 	VARCHAR(500) NOT NULL,
    
    timeSlotID 			INT,
    FOREIGN KEY (timeSlotID) references timeSlot (timeSlotID), 
    
    taskID 				INT,
    FOREIGN KEY (taskID) references task (taskID) ON DELETE CASCADE, 
    
    employeeID     		INT,
    FOREIGN KEY (employeeID) references employee (employeeID) ON DELETE CASCADE
    
);
