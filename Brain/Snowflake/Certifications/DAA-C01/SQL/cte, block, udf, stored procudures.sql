-- block
DECLARE
  counter INTEGER;
  power_of_2 INTEGER DEFAULT 1;
BEGIN
  FOR counter IN 1 TO 10 DO
    IF (counter = 7) THEN
      power_of_2 := power_of_2 * 5;
    ELSE
      power_of_2 := power_of_2 * 2;
    END IF;
  END FOR;
  RETURN power_of_2;
END;

BEGIN
    DELETE FROM COMPANY WHERE COMPANY_ID = 1;
    DELETE FROM COMPANY WHERE COMPANY_ID = 2;
    SELECT * FROM COMPANY;
    RETURN 'DONE';
END;

select * from company;





-- func
CREATE OR REPLACE FUNCTION count_sum_to(target_number INTEGER)
RETURNS VARCHAR
LANGUAGE SQL
AS
DECLARE
  sum_total INTEGER DEFAULT 0;
BEGIN
  FOR i IN 1 TO target_number DO --
    IF (i = 7) THEN
      sum_total := sum_total + i + 7;
    ELSE
      sum_total := sum_total + i;
    END IF;
  END FOR; --
  RETURN 'Counted to ' || target_number || '. Sum of all numbers: ' || sum_total;
END;

SELECT count_sum_to(10);

drop function count_sum_to(integer)

CREATE OR REPLACE FUNCTION count_sum_to(target_number INTEGER)
RETURNS VARCHAR
LANGUAGE SQL
AS
DECLARE
  sum_total INTEGER DEFAULT 0;
BEGIN
  -- FOR i IN 1 TO target_number DO --
  --   sum_total := sum_total + i;
  -- END FOR; --
  -- delete from abc where customer_id = 4;
  select * from abc;
  RETURN 'Counted to ' || target_number || '. Sum of all numbers: ' || sum_total;
END;

SELECT count_sum_to(10);


select * from abc;




CREATE OR REPLACE FUNCTION test_func()
RETURNS VARCHAR
LANGUAGE SQL
AS
$$
  DELETE FROM COMPANY WHERE COMPANY_ID = 1;
$$;

SELECT test_func();



CREATE OR REPLACE FUNCTION get_status_message(status_code INT)
RETURNS VARCHAR
LANGUAGE SQL
AS
$$
  CASE
    WHEN status_code = 1 THEN 'Active'
    WHEN status_code = 0 THEN 'Inactive'
    ELSE 'Unknown'
  END
$$;

SELECT get_status_message(1);









-- cte


WITH cte_name AS (
    -- CTE query definition (SELECT statement)
    SELECT 'thaidh';
    SELECT 'ngoctt';
)
-- Main query that uses the CTE
SELECT * FROM cte_name;








-- stored procedures
CREATE OR REPLACE PROCEDURE count_sum_to(target_number INTEGER)
RETURNS STRING
LANGUAGE SQL
AS
DECLARE
  sum_total INTEGER DEFAULT 0;
BEGIN
  FOR i IN 1 TO target_number DO
    IF (i = 7) THEN
      sum_total := sum_total + i + 7;
    ELSE
      sum_total := sum_total + i;
    END IF;
  END FOR;

  RETURN 'Counted to ' || target_number || '. Sum of all numbers: ' || sum_total;
END;

CALL count_sum_to(10);



CREATE OR REPLACE PROCEDURE test_proc()
RETURNS STRING
LANGUAGE SQL
AS
DECLARE
  result_msg STRING;
BEGIN
  -- Perform the delete
  DELETE FROM COMPANY WHERE COMPANY_ID = 1;
  DELETE FROM COMPANY WHERE COMPANY_ID = 2;

  -- Set return message
  result_msg := 'Delete executed';

  RETURN result_msg;
END;

CALL test_proc();
