-- Create a small table
CREATE OR REPLACE TABLE numbers_odd(val INT);
INSERT INTO numbers_odd VALUES (10), (20), (30), (40), (50);

-- Median calculation
SELECT MEDIAN(val) AS median_val, AVG(val) AS avg_val
FROM numbers_odd;

-- Explanation:
-- Values = [10, 20, 30, 40, 50] (5 rows → odd count).
-- Median = middle value = 30.
-- Average = (10+20+30+40+50)/5 = 30. 👉 In this case, median = average.


--------------------------------------

-- Create another table
CREATE OR REPLACE TABLE numbers_even(val INT);
INSERT INTO numbers_even VALUES (2), (4), (6), (8);

-- Median calculation
SELECT MEDIAN(val) AS median_val, AVG(val) AS avg_val
FROM numbers_even;


-- Explanation:
-- Values = [2, 4, 6, 8] (4 rows → even count).
-- Median = average of the two middle values (4 and 6) = 5.
-- Average = (2+4+6+8)/4 = 5. 👉 Here, median = average, but that’s not always true with skewed data.


--------------------------------------


percentile_cont
Example: Dataset = [10, 20, 30, 40]
50th percentile (0.5) → returns 25 (the midpoint between 20 and 30).

PERCENTILE_DISC
Example: Dataset = [10, 20, 30, 40]
50th percentile (0.5) → returns 20 (the smallest value ≥ 50% cumulative).

