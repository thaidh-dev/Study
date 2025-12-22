CREATE OR REPLACE TABLE employees (
    employee_id INT,
    employee_name STRING,
    manager_id INT
);


INSERT INTO employees (employee_id, employee_name, manager_id) VALUES
    (1, 'Alice', NULL),      -- CEO
    (2, 'Bob', 1),           -- reports to Alice
    (3, 'Charlie', 1),       -- reports to Alice
    (4, 'Diana', 2),         -- reports to Bob
    (5, 'Eve', 2),           -- reports to Bob
    (6, 'Frank', 3),         -- reports to Charlie
    (7, 'Grace', 4);         -- reports to Diana


SELECT
    LPAD(' ', 2 * (LEVEL - 1)) || employee_name AS org_chart,
    employee_id,
    manager_id,
    LEVEL AS hierarchy_level
FROM employees
START WITH manager_id IS NULL
CONNECT BY PRIOR employee_id = manager_id;


SELECT 
    SYS_CONNECT_BY_PATH(employee_name, ' -> '), 
    employee_ID, 
    manager_ID, 
    employee_name
FROM employees
    START WITH employee_name = 'Alice'
    CONNECT BY manager_ID = PRIOR employee_id
ORDER BY employee_ID;

