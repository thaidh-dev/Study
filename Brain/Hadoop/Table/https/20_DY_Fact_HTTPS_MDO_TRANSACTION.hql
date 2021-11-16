CREATE table 20_DY_Fact_HTTPS_MDO_TRANSACTION(
    DATE_ID int,
    CELL_NODE_ID bigint,
    HTTPS_WEB_DL_DATA bigint,
    HTTPS_WEB_DL_TIME bigint,
    HTTPS_VIDEO_STREAM_DATA bigint,
    HTTPS_VIDEO_STREAM_TIME bigint,
    HTTPS_VIDEO_HD_NUM bigint,
    HTTPS_VIDEO_SD_NUM bigint,
    HTTPS_VIDEO_LD_NUM bigint,
    HTTPS_VIDEO_STREAM_SUCC bigint
) partitioned by (datetime_id int) stored as orc; 

set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert into table 20_DY_Fact_HTTPS_MDO_TRANSACTION partition (datetime_id) select * from 10_HR2DY_HTTPS_MDO_TRANSACTION;


