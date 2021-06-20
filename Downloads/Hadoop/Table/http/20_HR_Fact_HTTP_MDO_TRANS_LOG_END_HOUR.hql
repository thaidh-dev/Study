CREATE TABLE 20_HR_Fact_HTTP_MDO_TRANS_LOG_END_HOUR(
    DATE_ID int,
    CELL_NODE_ID bigint,
    WEB_TOTAL float,
    MDO_VIDEO_COUNT float,
    WEB_RESP_SUCC float,
    WEB_AVG_RESP_DELAY float,
    WEB_AVG_DISPLAY_DELAY float,
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
) PARTITIONED BY (datetime_id int) STORED AS orc;


set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert into table 20_HR_Fact_HTTP_MDO_TRANS_LOG_END_HOUR partition (datetime_id) select * from 10_Staging2HR_HTTP_MDO_TRANS_LOG_END_HOUR;

set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table 20_HR_Fact_HTTP_MDO_TRANS_LOG_END_HOUR partition (datetime_id) select * from 10_Staging2HR_HTTP_MDO_TRANS_LOG_END_HOUR;
