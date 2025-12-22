CREATE OR REPLACE TABLE employees (
    employee_id INT,
    name VARCHAR,
    manager_id INT
);

INSERT INTO employees VALUES
(1, 'CEO', NULL),
(2, 'VP Engineering', 1),
(3, 'VP HR', 1),
(4, 'Senior Engineer', 2),
(5, 'Junior Engineer', 4),
(6, 'HR Specialist', 3);


WITH RECURSIVE hierarchy AS (
    SELECT employee_id, name, manager_id, 0 AS level, name as path
    FROM employees
    WHERE manager_id IS NULL
    UNION ALL
    SELECT e.employee_id, e.name, e.manager_id, h.level + 1, h.path || ' -> ' || e.name
    FROM employees e
    JOIN hierarchy h ON e.manager_id = h.employee_id
)
SELECT * FROM hierarchy;



WITH RECURSIVE hierarchy AS (
    SELECT employee_id, name, manager_id, 0 AS level, name as path
    FROM employees
    WHERE manager_id IS NULL
    UNION ALL
    SELECT h.employee_id, h.name, h.manager_id, h.level + 1, h.path || ' -> ' || e.name
    FROM hierarchy h
    JOIN employees e ON e.manager_id = h.employee_id
)
SELECT * FROM hierarchy;


with hierarchy as (
    select employee_id, manager_id
    from employees
    where manager_id IS NULL
)
select e.employee_id, e.manager_id
from hierarchy h
join employees e on h.employee_id = e.manager_id

