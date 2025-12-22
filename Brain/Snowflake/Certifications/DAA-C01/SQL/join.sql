-- Create Dept table
CREATE TABLE Dept (
    Department_ID INT PRIMARY KEY,
    Department_Name VARCHAR(50)
);

-- Insert data into Dept
INSERT INTO Dept (Department_ID, Department_Name) VALUES
(1, 'Dept 1'),
(2, 'Dept 2'),
(3, 'Dept 3'),
(4, 'Dept 4'),
(5, 'Dept 5');

-- Create Project table
CREATE TABLE Project (
    Project_ID INT PRIMARY KEY,
    Project_Name VARCHAR(50),
    Department_ID INT,
    FOREIGN KEY (Department_ID) REFERENCES Dept(Department_ID)
);

-- Insert data into Project
INSERT INTO Project (Project_ID, Project_Name, Department_ID) VALUES
(101, 'PRJ1', 1),
(102, 'PRJ2', 2),
(103, 'PRJ3', 3),
(104, 'PRJ4', 4);

-- Create Employee table
CREATE TABLE Employee (
    Employee_ID INT PRIMARY KEY,
    Employee_Name VARCHAR(50),
    Project_ID INT NULL,
    FOREIGN KEY (Project_ID) REFERENCES Project(Project_ID)
);

-- Insert data into Employee
INSERT INTO Employee (Employee_ID, Employee_Name, Project_ID) VALUES
(1, 'Emp 1', 101),
(2, 'Emp 2', 102),
(3, 'Emp 3', 103),
(4, 'Emp 4', NULL);


select
    d.department_id,
    d.department_name,
    p.project_id,
    p.project_name,
    e.employee_id,
    e.employee_name
from
    project p 
natural join 
    employee e 
natural join 
    dept d
order by
    employee_id;