CREATE or replace TABLE nurses (
  ID INTEGER,
  full_name VARCHAR,
  medical_license VARCHAR,   -- LVN, RN, etc.
  radio_license VARCHAR      -- Technician, General, Amateur Extra
  )
  ;

INSERT INTO nurses
    (ID, full_name, medical_license, radio_license)
  VALUES
    (201, 'Thomas Leonard Vicente', 'LVN', 'Technician'),
    (202, 'Tamara Lolita VanZant', 'LVN', 'Technician'),
    (341, 'Georgeann Linda Vente', 'LVN', 'General'),
    (471, 'Andrea Renee Nouveau', 'RN', 'Amateur Extra')
    ;


SELECT medical_license, radio_license, sum(id)
FROM nurses
GROUP BY GROUPING SETS (
    (medical_license, radio_license)
);

SELECT medical_license, radio_license, sum(id)
FROM nurses
GROUP BY GROUPING SETS (
    (medical_license, radio_license), 
    () -- () (empty grouping) → represents the grand total across all rows.
);



select COUNT(*), medical_license, radio_license 
from nurses
group by medical_license, radio_license

select * from nurses
where full_name like 'Andre%'


select regexp_like(full_name, 'andre.*', 'i') from nurses


------------------------------------------

CREATE OR REPLACE TABLE aggr2 (
    col_x INT,
    col_y INT,
    col_z INT
);

INSERT INTO aggr2 VALUES
(1, 2, 1),
(1, 2, 3),
(2, 1, 10),
(2, 2, 11),
(2, 2, 3);

-- GROUPING_ID returns 0 or 1.
-- 0 → the column is grouped (not aggregated away).
-- 1 → the column is not grouped (it was aggregated to NULL in that row).
SELECT col_x,
       SUM(col_z),
       GROUPING_ID(col_x)
FROM aggr2
GROUP BY col_x
ORDER BY col_x;

-- gid = 0: No columns are aggregated (detail rows).
-- gid = 1: col_y is aggregated (NULL), but col_x is kept.
-- gid = 2: col_x is aggregated (NULL), but col_y is kept.
-- gid = 3: Both col_x and col_y are aggregated (grand total).
SELECT col_x, col_y,
       SUM(col_z),
       GROUPING_ID(col_x),
       GROUPING_ID(col_y),
       GROUPING_ID(col_x, col_y) as gid
FROM aggr2
GROUP BY GROUPING SETS ((col_x), (col_y), ())
ORDER BY col_x, col_y;

SELECT col_x, col_y,
       SUM(col_z),
       GROUPING_ID(col_x, col_y) as gid
FROM aggr2
GROUP BY cube (col_x, col_y) 
ORDER BY col_x, col_y nulls last;

SELECT col_x, col_y,
       SUM(col_z),
       GROUPING_ID(col_x, col_y) as gid
FROM aggr2
GROUP BY rollup (col_x, col_y)
ORDER BY col_x, col_y;

------------------------------------------------------------------------------------

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



