-- https://medium.com/@dhakalsuman07/compare-whether-two-tables-contain-the-same-data-or-not-using-hash-agg-in-snowflake-81ece404c728

CREATE OR REPLACE TABLE sales (
  country STRING,
  state STRING,
  sales NUMBER
);

INSERT INTO sales (country, state, sales) VALUES
  ('USA', 'Texas', 100),
  ('USA', 'Florida', 200),
  ('Canada', 'Ontario', 150),
  ('Canada', 'Quebec', 120);


SELECT
  country,
  state,
  SUM(sales) AS total_sales
FROM sales
GROUP BY cube (country, state)
-- ORDER BY country, state
except
SELECT
  country,
  state,
  SUM(sales) AS total_sales
FROM sales
GROUP BY GROUPING SETS (
  (country, state),  -- detailed breakdown by both
  (country),         -- subtotal by country
  (state)            -- subtotal by state
)
ORDER BY country, state

with a as (
    SELECT
      country,
      state,
      SUM(sales) AS total_sales
    FROM sales
    GROUP BY cube (country, state)
    ORDER BY country, state
), 
b as (
    SELECT
      country,
      state,
      SUM(sales) AS total_sales
    FROM sales
    GROUP BY GROUPING SETS (
      (country, state),  -- detailed breakdown by both
      (country),         -- subtotal by country
      (state)            -- subtotal by state
    )
    ORDER BY country, state
)
SELECT HASH_AGG(*) = (SELECT HASH_AGG(*) FROM b) AS IS_SAME
FROM a;

---------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE TABLE TABLE_A (
    ID INT,
    NAME STRING,
    AGE INT
);

INSERT INTO TABLE_A (ID, NAME, AGE) VALUES
(1, 'Alice', 25),
(2, 'Bob', 30),
(3, 'Charlie', 35);

CREATE OR REPLACE TABLE TABLE_B (
    ID INT,
    NAME STRING,
    AGE INT
);

INSERT INTO TABLE_B (ID, NAME, AGE) VALUES
(1, 'Alice', 25),
(2, 'Bob', 30),
(3, 'Charlie', 35),
(4, 'Diana', 28);

SELECT HASH_AGG(*) = (SELECT HASH_AGG(*) FROM TABLE_B where id != 4) AS IS_SAME
FROM TABLE_A;


