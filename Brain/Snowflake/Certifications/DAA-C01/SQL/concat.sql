SELECT CONCAT_WS(',', 'one', 'two', 'three', null);
SELECT CONCAT(',', 'one', 'two', 'three', null);

select 
    concat(c_mktsegment), 
    concat_ws(', ', c_mktsegment)
from SNOWFLAKE_SAMPLE_DATA.TPCH_SF1.CUSTOMER

select 
    listagg(distinct c_mktsegment, ', ')
from SNOWFLAKE_SAMPLE_DATA.TPCH_SF1.CUSTOMER


select * from SNOWFLAKE_SAMPLE_DATA.TPCH_SF1.CUSTOMER


CREATE OR REPLACE TABLE demo (val STRING);

INSERT INTO demo VALUES
  ('apple'),
  (NULL),
  ('orange'),
  (''),
  ('banana');

-- apple,orange,,banana
SELECT LISTAGG(val, ',') AS result FROM demo;

-------------------------------------------------------------------

SELECT RTRIM('$12.05.00', '0.');



