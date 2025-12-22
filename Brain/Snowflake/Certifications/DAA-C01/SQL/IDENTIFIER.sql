CREATE OR REPLACE PROCEDURE get_row_count(table_name STRING)
RETURNS INTEGER
LANGUAGE SQL
AS
$$
DECLARE
  result INTEGER;
BEGIN
  -- Bind the variable with a colon and use IDENTIFIER() for the object name
  SELECT COUNT(*)
  INTO :result
  FROM IDENTIFIER(:table_name);

  RETURN result;
END;
$$;

CALL get_row_count('SNOWFLAKE_SAMPLE_DATA.TPCH_SF1.CUSTOMER');
