CREATE TABLE dim_CELL_INFO_CELLS 
using orc
partitioned by (CELL_TYPE)
as select * from dim_CELL_INFO_CELLS_temp;


CREATE TABLE dim_CELL_INFO_CELLS_temp(
    CELL_NODE_ID bigint,
    LAC varchar(20),
    CELL_ID varchar(20),
    CELL_NAME varchar(250),
    CENTER varchar(10),
    PROVINCE varchar(255),
    DISTRICT varchar(255),
    CELL_TYPE varchar(10),
    BSC_MCC varchar(250),
    BSC_MNC varchar(250),
    BSCID varchar(250),
    BSCNAME varchar(250),
    SITEID varchar(250),
    SITENAME varchar (250),
    VENDOR varchar(250),
    BRANCH varchar(250),
    CTKD varchar(50),
    DATEADDED varchar(25),
    the_ORDER int,
    TTML varchar(50),
    LONG decimal(18, 10),
    LAT decimal(18, 10),
    TVT varchar(50),
    DVT varchar(50),
    Bandwidth varchar(50),
    address_detail_english varchar(250),
    azimuth varchar(50)
) 
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = ",",
   "quoteChar"     = "\""
) 
STORED AS TEXTFILE tblproperties("skip.header.line.count"="1", "serialization.null.format"="");

load data local inpath '/home/hive/cell_info_cells.csv' into table dim_CELL_INFO_CELLS_temp;

