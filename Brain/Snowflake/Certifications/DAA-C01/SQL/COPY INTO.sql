COPY INTO test_2.public.sensor_data
FROM
    (
        SELECT
            $1:sensor_id,
            $1:timestamp,
            $1:temperature,
            $1:humidity
        FROM
            @test.public.my_int_stage/data.json.gz (
                FILE_FORMAT => my_json_format
            )
    );


CREATE OR REPLACE FILE FORMAT my_json_format
  TYPE = 'JSON'
  STRIP_OUTER_ARRAY = TRUE;




CREATE TABLE test_2.public.sensor_data (
    sensor_id VARCHAR(50) NOT NULL PRIMARY KEY, -- Sensor identifier
    timestamp TIMESTAMP NOT NULL,   -- Time of the reading
    temperature DECIMAL(5,2),       -- Temperature value
    humidity DECIMAL(5,2)           -- Humidity value
);

select * from test_2.public.sensor_data


------------------------------------------------------------


CREATE OR REPLACE TABLE test_2.public.SALES_DATA (
    order_id      INTEGER,
    customer_id   STRING,
    amount        integer
);

COPY INTO SALES_DATA
FROM
    @test.public.my_int_stage FILE_FORMAT = (
        TYPE = CSV,
        SKIP_HEADER = 1,
        FIELD_OPTIONALLY_ENCLOSED_BY = '"',
        COMPRESSION = GZIP
    )
    FORCE = TRUE
    ON_ERROR = 'CONTINUE';



COPY INTO SALES_DATA
FROM
    @test.public.my_int_stage FILE_FORMAT = (
        TYPE = CSV,
        SKIP_HEADER = 1,
        FIELD_OPTIONALLY_ENCLOSED_BY = '"'
    ) 
    FORCE = TRUE
    ON_ERROR = 'CONTINUE';

delete from test_2.public.SALES_DATA
select * from test_2.public.SALES_DATA


----------------------------------------------
/*
Header row is present.
Delimiter is a pipe (|).
Fields are enclosed in double quotes.
timestamp column is in milliseconds since epoch (e.g., 1730000000000), convert timestamp from milliseconds to TIMESTAMP_NTZ
*/

CREATE OR REPLACE TABLE WEBSITE_TRAFFIC_RAW (
    customer_id int,
    page STRING,
    timestamp_ms timestamp_ntz,
    country STRING
);

copy into website_traffic_raw
from (
    select 
        $1::int,
        $2::string,
        to_timestamp_ntz($3),
        $4::string
    from @test.public.MY_INT_STAGE/49.csv
)
file_format = (
    type = csv
    skip_header = 1,
    field_optionally_enclosed_by = '"',
    field_delimiter = '|'
)

select * from website_traffic_raw

list @test.public.MY_INT_STAGE
select * from directory(@test.public.MY_INT_STAGE)



-------------------------------------------------------------------------------------

CREATE OR REPLACE TABLE my_table (
    id INT,
    name STRING,
    age INT
);

CREATE OR REPLACE TABLE my_table_load_errors AS
COPY INTO my_table
FROM @UTIL_DB.PUBLIC.MY_INTERNAL_STAGE/data_with_errors.csv
FILE_FORMAT = (TYPE = 'CSV' FIELD_OPTIONALLY_ENCLOSED_BY='"' SKIP_HEADER=1)
ON_ERROR = CONTINUE;
-- VALIDATION_MODE = 'RETURN_ALL_ERRORS';

select * from table(
    information_schema.copy_history(
        TABLE_NAME => 'TEST_2.PUBLIC.my_table', 
        START_TIME => DATEADD(hour, -1, CURRENT_TIMESTAMP())
    )
);
select * from table(
    information_schema.copy_history(
        TABLE_NAME => 'TEST_2.PUBLIC.my_table', 
        START_TIME => DATEADD(day, -100, CURRENT_TIMESTAMP())
    )
);

-- get job_id
SELECT *
FROM TABLE(INFORMATION_SCHEMA.QUERY_HISTORY())
WHERE query_text ILIKE 'COPY INTO%';

SELECT *
FROM SNOWFLAKE.ACCOUNT_USAGE.COPY_HISTORY

-- get the failed records
SELECT *
FROM TABLE(VALIDATE(
    TEST_2.PUBLIC.my_table,
    job_id => '01c00122-0206-cc98-0010-13db002a9736'
));




select * from snowflake.account_usage.copy_history

select * from my_table_load_errors
select * from my_table

-------------------------------------------------------------------

SELECT 
  METADATA$FILENAME,
  METADATA$FILE_ROW_NUMBER,
  t.$1, 
  t.$2
FROM @test.public.MY_INT_STAGE t


select * from table(
    INFER_SCHEMA(
        location => 'test.public.MY_INT_STAGE',
        file_format => ''
    )
)

desc stage test.public.MY_INT_STAGE


---------------------------------------------------------------

CREATE OR REPLACE TABLE my_table (
    id INT,
    name STRING,
    age INT
);

COPY INTO my_table
FROM @UTIL_DB.PUBLIC.MY_INTERNAL_STAGE/data_with_errors.csv
FILE_FORMAT = (TYPE = 'CSV' FIELD_OPTIONALLY_ENCLOSED_BY='"' SKIP_HEADER=1)
VALIDATION_MODE = 'RETURN_ERRORS';


