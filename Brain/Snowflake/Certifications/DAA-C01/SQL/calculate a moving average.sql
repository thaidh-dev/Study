-- CREATE OR REPLACE TABLE PRODUCT_SALES (
--     product_id    VARCHAR,
--     sale_date     DATE,
--     sales_amount  NUMBER(10,2)
-- );

-- INSERT INTO PRODUCT_SALES (product_id, sale_date, sales_amount) VALUES
-- ('P1', '2024-01-01', 100),
-- ('P1', '2024-01-02', 150),
-- ('P1', '2024-01-04', 200),   -- missing 2024-01-03
-- ('P1', '2024-01-07', 250),   -- missing 2024-01-05, 2024-01-06
-- ('P2', '2024-01-01', 300),
-- ('P2', '2024-01-03', 350),
-- ('P2', '2024-01-04', 400);

-- SELECT
--     product_id,
--     sale_date,
--     AVG(sales_amount) OVER (
--         PARTITION BY product_id
--         ORDER BY
--             sale_date ASC RANGE BETWEEN INTERVAL '6 day' PRECEDING
--             AND CURRENT ROW
--     ) AS moving_average
-- FROM
--     product_sales;


-- SELECT
--     product_id,
--     sale_date,
--     AVG(sales_amount) OVER (
--         PARTITION BY product_id
--         ORDER BY
--             -- ROWS → the frame is defined in terms of a fixed number of rows relative to the current row.
--             -- 2 PRECEDING → start the frame 2 rows before the current row.
--             -- CURRENT ROW → end the frame at the current row.
--             sale_date ROWS BETWEEN 2 PRECEDING
--             AND CURRENT ROW
--     ) AS moving_average
-- FROM
--     product_sales;








WITH date_range AS (
    SELECT 
        MIN(sale_date) AS start_date, 
        MAX(sale_date) AS end_date
    FROM PRODUCT_SALES
),
all_dates AS (
    -- https://datageek.blog/2024/02/27/generating-data-in-snowflake/
    -- ⚠️ Problem: seq4() is not gap-free
    -- seq4() generates a unique sequence of numbers, but it’s not guaranteed to be continuous — meaning some numbers can be skipped (e.g., 0, 1, 3, 4, skipping 2).
    -- Because Snowflake runs queries in parallel across many servers (MPP = massively parallel processing). Each worker might generate parts of the sequence independently, which can cause gaps in the sequence.
    -- So if you use seq4() directly, you might skip some dates.
    -- ✅ Solution: Use ROW_NUMBER() instead
    SELECT DATEADD(day, row_number() over (order by seq4()) - 1, start_date) AS sale_date
    FROM date_range,
    TABLE(GENERATOR(ROWCOUNT => 10))
    qualify sale_date <= end_date
), 
products AS (
    SELECT DISTINCT product_id FROM PRODUCT_SALES
),
all_product_dates AS (
    SELECT p.product_id, d.sale_date
    FROM products p
    CROSS JOIN all_dates d
),
filled_sales AS (
    -- Join to the original sales and fill missing with 0
    SELECT
        a.product_id,
        a.sale_date,
        COALESCE(s.sales_amount, 0) AS sales_amount
    FROM all_product_dates a
    LEFT JOIN product_sales s ON a.product_id = s.product_id AND a.sale_date = s.sale_date
)

-- ✅ Calculate 3-day moving average
SELECT
    product_id,
    sale_date,
    sales_amount,
    AVG(sales_amount) OVER (
        PARTITION BY product_id
        ORDER BY sale_date
        ROWS BETWEEN 3 PRECEDING AND CURRENT ROW
        -- range BETWEEN 3 PRECEDING AND CURRENT ROW
    ) AS moving_avg_7d
FROM filled_sales
ORDER BY product_id, sale_date;




-- ROWS BETWEEN = count rows.
-- SUM(sales) OVER (
--   ORDER BY date
--   ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
-- )

-- RANGE BETWEEN = compare values.
-- SUM(sales) OVER (
--   ORDER BY date
--   RANGE BETWEEN INTERVAL '7 days' PRECEDING AND CURRENT ROW
-- )




SELECT SEQ4() FROM table(GENERATOR(ROWCOUNT => 20));





-------------------------------------------------------------------------------------------------

CREATE or replace TABLE store_sales (
    store_id INT,
    sales_date DATE,
    revenue INT
);
INSERT INTO store_sales (store_id, sales_date, revenue) VALUES
(1, '2025-01-01', 100),
(1, '2025-01-02', 120),
(1, '2025-01-03', 130),
(1, '2025-01-05', 140),
(1, '2025-01-06', 150),
(1, '2025-01-08', 160),
(2, '2025-01-01', 200),
(2, '2025-01-02', 210),
(2, '2025-01-04', 220),
(2, '2025-01-07', 230);
INSERT INTO store_sales (store_id, sales_date, revenue) VALUES
(1, '2025-01-01', 100),
(1, '2025-01-02', 120),
(1, '2025-01-03', 130),
(1, '2025-01-04', 140),
(1, '2025-01-05', 150),
(1, '2025-01-06', 160),
(2, '2025-01-01', 200),
(2, '2025-01-02', 210),
(2, '2025-01-03', 220),
(2, '2025-01-04', 230);



SELECT store_id,
       sales_date,
       revenue,
       AVG(revenue) OVER (
           PARTITION BY store_id
           ORDER BY sales_date
           ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
       ) AS moving_avg_rows
FROM store_sales;




SELECT store_id,
       sales_date,
       revenue,
       AVG(revenue) OVER (
           PARTITION BY store_id
           ORDER BY sales_date
           RANGE BETWEEN INTERVAL '2 days' PRECEDING AND CURRENT ROW
       ) AS moving_avg_rows
FROM store_sales;






