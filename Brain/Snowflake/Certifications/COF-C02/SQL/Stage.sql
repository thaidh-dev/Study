LIST @UTIL_DB.PUBLIC.MY_INTERNAL_STAGE;
select * from directory(@UTIL_DB.PUBLIC.MY_INTERNAL_STAGE);



-- Query the INFER_SCHEMA function.
-- Create a file format for Parquet
CREATE OR REPLACE FILE FORMAT my_csv_format TYPE = CSV;


SELECT *
  FROM TABLE(
    INFER_SCHEMA(
      LOCATION=>'@TEST.PUBLIC.MY_STAGE',
      FILE_FORMAT => 'my_csv_format'
      )
    );

DESC STAGE UTIL_DB.PUBLIC.MY_INTERNAL_STAGE

---------------
ls @TEST.PUBLIC.MY_STAGE
desc stage TEST.PUBLIC.MY_STAGE

select METADATA$FILENAME, t.$1, t.$2, t.$3  
from @TEST.PUBLIC.my_stage/sales_summary.csv_0_0_0.csv.gz t


ls @~

show stages in account

desc stage TEST.PUBLIC.MY_INT_STAGE

select GET_STAGE_LOCATION(@TEST.PUBLIC.MY_STAGE)


