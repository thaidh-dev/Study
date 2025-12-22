CREATE OR REPLACE TABLE quarterly_sales(
  empid INT,
  amount INT,
  quarter TEXT)
  AS SELECT * FROM VALUES
    (1, 10000, '2023_Q1'),
    (1, 400, '2023_Q1'),
    (2, 4500, '2023_Q1'),
    (2, 35000, '2023_Q1'),
    (1, 5000, '2023_Q2'),
    (1, 3000, '2023_Q2'),
    (2, 200, '2023_Q2'),
    (2, 90500, '2023_Q2'),
    (1, 6000, '2023_Q3'),
    (1, 5000, '2023_Q3'),
    (2, 2500, '2023_Q3'),
    (2, 9500, '2023_Q3'),
    (3, 2700, '2023_Q3'),
    (1, 8000, '2023_Q4'),
    (1, 10000, '2023_Q4'),
    (2, 800, '2023_Q4'),
    (2, 4500, '2023_Q4'),
    (3, 2700, '2023_Q4'),
    (3, 16000, '2023_Q4'),
    (3, 10200, '2023_Q4');



SELECT * FROM quarterly_sales
PIVOT(SUM(amount) FOR quarter IN (ANY ORDER BY quarter))
ORDER BY empid;


-----------------------------------------------------------------

CREATE OR REPLACE TABLE employees (
    employee_id INT,
    first_name STRING,
    last_name STRING,
    department STRING,
    salary NUMBER
);

INSERT INTO employees (employee_id, first_name, last_name, department, salary) VALUES
(1, 'Alice', 'Smith', 'HR', 5000),
(2, 'Bob', NULL, 'IT', 6000),
(3, NULL, 'Johnson', 'IT', NULL),
(4, 'Diana', 'Lee', NULL, 5500),
(5, NULL, NULL, NULL, NULL);

SELECT * FROM employees


-- Counting NULLs in all columns of a table:
select 
    col_name, 
    count(*) 
from employees
unpivot  (
    value for col_name in (first_name, last_name, department)
)
-- where value is null
group by col_name    


SELECT 
    COUNT_IF(first_name IS NULL) AS first_name_null_count
FROM employees;

SELECT SNOWFLAKE.CORE.NULL_COUNT(
  SELECT first_name FROM employees
);
