CREATE TABLE dim_CELL_INFO_CELLS_current(
    CELL_NODE_ID bigint,
    LAC varchar(20),
    CELL_ID varchar(20),
    CELL_NAME varchar(250),
    CENTER varchar(10),
    PROVINCE varchar(255),
    DISTRICT varchar(255),
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
) partitioned by (CELL_TYPE varchar(10)) stored as orc;

insert into table dim_CELL_INFO_CELLS_current 
select * from 11_dim_cell_info_cells_current;

