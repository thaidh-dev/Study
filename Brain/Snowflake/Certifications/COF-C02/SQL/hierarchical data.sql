CREATE OR REPLACE TABLE employees (
  title VARCHAR,
  employee_id INTEGER,
  manager_id INTEGER
);

INSERT INTO employees (title, employee_id, manager_id) VALUES
  ('President', 1, NULL),         -- Top of hierarchy
  ('Vice President Engineering', 10, 1),
  ('Programmer', 100, 10),
  ('QA Engineer', 101, 10),
  ('Vice President HR', 20, 1),
  ('Health Insurance Analyst', 200, 20);

SELECT 
  employee_id,
  manager_id,
  title,
  LEVEL AS hierarchy_level
FROM employees
START WITH title = 'President'
CONNECT BY manager_id = PRIOR employee_id
ORDER BY employee_id;
