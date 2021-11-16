CREATE table 20_DY_Fact_HTTP_MDO_TRANSACTION( 
    DATE_ID int,
    CELL_NODE_ID bigint,
    WEB_TOTAL float,
    MDO_VIDEO_COUNT float,
    WEB_RESP_SUCC float,
    WEB_RES_DL_SUM float,
    WEB_DPL_DL_SUM float,
    WEB_DL_TIME float,
    WEB_DL_DATA float,
    WEB_BROWS_SUCC float,
    WEB_BROWS_TOTAL float,
    VIDEO_STREAM_SUCC float,
    VIDEO_STREAM_TOTAL float,
    VIDEO_STREAM_START_DELAY float,
    VIDEO_STREAM_STALL float,
    VIDEO_STREAM_DATA float,
    VIDEO_STREAM_TIME float,
    VIDEO_HD_NUM float,
    VIDEO_SD_NUM float,
    VIDEO_LD_NUM float
) partitioned by (datetime_id int) stored as orc;                 


set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert into table 20_DY_Fact_HTTP_MDO_TRANSACTION partition (datetime_id) select * from 10_HR2DY_HTTP_MDO_TRANSACTION;

set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table 20_DY_Fact_HTTP_MDO_TRANSACTION partition (datetime_id) select * from 10_HR2DY_HTTP_MDO_TRANSACTION;
