select row_number() over (order by c_nationkey), *
from SNOWFLAKE_SAMPLE_DATA.TPCH_SF1.CUSTOMER


select 
    row_number() over (partition by c_mktsegment order by c_mktsegment),
    *
from SNOWFLAKE_SAMPLE_DATA.TPCH_SF1.CUSTOMER
