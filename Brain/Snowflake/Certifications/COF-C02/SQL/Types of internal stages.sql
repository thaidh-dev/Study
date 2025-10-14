
-- https://docs.snowflake.com/en/user-guide/data-load-local-file-system-create-stage

CREATE OR REPLACE TABLE HOME_SALES (
    CITY        STRING,
    STATE       STRING,
    ZIP         STRING,
    TYPE        STRING,
    PRICE       NUMBER(12,2),
    SALE_DATE   DATE
);
INSERT INTO HOME_SALES (CITY, STATE, ZIP, TYPE, PRICE, SALE_DATE) VALUES
('Lexington', 'MA', '02420', 'Residential', 268880, '2017-03-28'),
('Belmont',   'MA', '02478', 'Residential', 350000, '2017-02-21'),
('Winchester','MA', '01890', 'Residential', 450000, '2017-01-31'),
('Boston',    'MA', '02108', 'Condo',       525000, '2017-04-15'),
('Cambridge', 'MA', '02139', 'Townhouse',   610000, '2017-05-10');

copy into @~ from HOME_SALES
single = true;

copy into @~/ngoctt
from HOME_SALES
FILE_FORMAT = (TYPE = CSV)
single = true;

ls @~;
list @~; -- user stage

list @%customers; -- table stage