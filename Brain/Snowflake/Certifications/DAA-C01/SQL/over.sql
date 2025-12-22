CREATE OR REPLACE TABLE sales (
    country STRING,
    sales_amount NUMBER(10,2)
);

INSERT INTO sales (country, sales_amount) VALUES
('USA', 100000),
('USA', 150000),
('USA', 250000),
('Japan', 200000),
('Japan', 300000),
('UK', 100000),
('UK', 200000),
('Canada', 150000),
('Canada', 150000),
('Germany', 250000);


SELECT
    country,
    SUM(sales_amount) AS total_sales,
    SUM(sales_amount) * 100.0 / SUM(SUM(sales_amount)) OVER () AS pct_of_global
FROM sales
GROUP BY country
ORDER BY pct_of_global DESC;

select sum(sales_amount) from sales

select 500000 / 1850000 













