CREATE TABLE qt (i INTEGER, p CHAR(1), o INTEGER);
INSERT INTO qt (i, p, o) VALUES
    (1, 'A', 1),
    (2, 'A', 2),
    (3, 'B', 1),
    (4, 'B', 2);


SELECT i, p, o, ROW_NUMBER() OVER (PARTITION BY p ORDER BY o) AS row_num
    FROM qt
    qualify row_num = 1
    ;


-- WHERE: Filters rows before aggregation. 
-- HAVING: Filters groups after standard aggregation (GROUP BY). 
-- QUALIFY: Filters rows after window function calculation

------------------------------------------------------------------------------

CREATE OR REPLACE TABLE sales (
    region STRING,
    employee STRING,
    amount NUMBER,
    sale_date DATE
);

INSERT INTO sales (region, employee, amount, sale_date) VALUES
    ('East', 'Alice', 5000, '2025-01-01'),
    ('East', 'Alice', 7000, '2025-01-15'),
    ('East', 'Bob',   6000, '2025-01-10'),
    ('East', 'Bob',   4000, '2025-01-20'),
    ('West', 'Carol', 9000, '2025-01-05'),
    ('West', 'Carol', 9500, '2025-01-18'),
    ('West', 'Dave',  8000, '2025-01-12'),
    ('West', 'Dave',  3600, '2025-01-25'),
    ('North','Eve',   3000, '2025-01-08'),
    ('North','Eve',   2500, '2025-01-22'),
    ('North','Frank', 2000, '2025-01-11'),
    ('North','Frank', 1500, '2025-01-28');

select * from sales;

SELECT 
    region,
    employee,
    SUM(amount) AS total_sales,
    RANK() OVER (PARTITION BY region ORDER BY SUM(amount) DESC) AS sales_rank
FROM sales
GROUP BY region, employee
HAVING total_sales > 10000
QUALIFY sales_rank < 2;