create or replace row access policy thaidh as (thaidh_name string) 
returns boolean -> thaidh_name like 'A%'


select * from TEST_2.PUBLIC.EMPLOYEES;


alter table TEST_2.PUBLIC.EMPLOYEES add row access policy thaidh on (emp_name)

SHOW ROW ACCESS POLICIES;

ALTER TABLE TEST_2.PUBLIC.EMPLOYEES DROP ROW ACCESS POLICY thaidh;
DROP ROW ACCESS POLICY thaidh;


show masking_policies