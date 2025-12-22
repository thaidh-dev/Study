CREATE FILE FORMAT my_parquet_format
  TYPE = parquet;

CREATE OR REPLACE TABLE my_table
USING TEMPLATE (
  SELECT ARRAY_AGG(OBJECT_CONSTRUCT(*))
  FROM TABLE(
    INFER_SCHEMA(
      LOCATION => '@TEST.PUBLIC.MY_INT_STAGE',
      FILE_FORMAT => 'my_parquet_format'
    )
  )
);

select * from my_table;
