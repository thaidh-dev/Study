-- Step 1: Create the table
CREATE or replace TABLE MY_TARGETS (
    Year INT PRIMARY KEY,
    Target INT
);

-- Step 2: Insert the values
INSERT INTO MY_TARGETS (Year, Target) VALUES
(2025, 36000),
(2026, 39000),
(2027, 44000),
(2028, 58000),
(2029, 56000),
(2030, 61000);

select * from my_targets


merge into MY_TARGETS using ( 
  select 
    2028 as YEAR, 
    48000 as TARGET
) as NEW_TARGET on MY_TARGETS.YEAR = NEW_TARGET.YEAR 
when matched then update 
  set MY_TARGETS.TARGET = NEW_TARGET.TARGET;

-- When used, Snowflake truncates the target table first (removes all existing rows).
-- Then it inserts the new rows.
-- Requires the role to have DELETE privilege on the table.
-- Unlike CREATE OR REPLACE, this does not drop the table—it only clears and reloads data.
insert overwrite into MY_TARGETS (YEAR, TARGET) 
values 
  (2028, 48000);
