create database test_2;

CREATE OR REPLACE TABLE employees (
    emp_id INT,
    emp_name STRING
);

INSERT INTO employees VALUES
(1, 'Alice'), (2, 'Bob'), (3, 'Carol'),
(4, 'David'), (5, 'Emma'), (6, 'Frank'),
(7, 'Grace'), (8, 'Hank'), (9, 'Ivy'),
(10, 'Jack');

-- SAMPLE and TABLESAMPLE are synonymous and can be used interchangeably.

-- Sample ~30% of rows, using Bernoulli method
SELECT * 
FROM employees
SAMPLE BERNOULLI (30);

-- get exactly 3 random rows
SELECT * 
FROM employees
SAMPLE BERNOULLI (3 rows);

-- Sample ~30% of rows, using Bernoulli method
-- Each row has a 30% independent chance of being included.
-- Result size may vary each run (sometimes 2 rows, sometimes 4 rows, etc.).
-- Use case: when you want a truly random sample.
SELECT * 
FROM employees
SAMPLE BERNOULLI (30);


-- Sample ~30% of rows, using System method
-- Snowflake reads data blocks (micro-partition) instead of rows.
-- Faster for large tables because it avoids row-by-row probability checks.
-- Sample size can vary a lot more than Bernoulli if rows are unevenly distributed across blocks.
-- Use case: when speed matters more than perfect randomness.
SELECT * 
FROM employees
SAMPLE SYSTEM (30);
