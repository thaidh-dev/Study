CREATE OR REPLACE HYBRID TABLE application_log (
  id NUMBER PRIMARY KEY AUTOINCREMENT,
  col1 VARCHAR(20),
  col2 VARCHAR(20) NOT NULL
  );

INSERT INTO application_log (col1, col2) VALUES ('A1', 'B1');
INSERT INTO application_log (col1, col2) VALUES ('A2', 'B2');
INSERT INTO application_log (col1, col2) VALUES ('A3', 'B3');
INSERT INTO application_log (col1, col2) VALUES ('A4', 'B4');

SELECT * FROM application_log;

UPDATE application_log SET col2 = 'B3-updated' WHERE id = 3;

DELETE FROM application_log WHERE id = 4;

SELECT * FROM application_log;



select value, METADATA$FILENAME, METADATA$FILE_ROW_NUMBER 
from TEST.PUBLIC.ABC

show streams
select * from UTIL_DB.PUBLIC.EMP_STREAM