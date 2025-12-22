CREATE OR REPLACE TABLE sales (
    order_id INT,
    order_date DATE,
    region STRING,
    sales_amount NUMBER(10,2)
);

INSERT INTO sales VALUES
(1, '2025-10-01', 'North', 100.00),
(2, '2025-10-02', 'North', 150.00),
(3, '2025-10-03', 'North', 200.00),
(4, '2025-10-01', 'South', 50.00),
(5, '2025-10-02', 'South', 75.00),
(6, '2025-10-03', 'South', 125.00);

SELECT
    order_date,
    SUM(sales_amount) OVER (ORDER BY order_date) AS running_total
FROM sales
ORDER BY order_date;



