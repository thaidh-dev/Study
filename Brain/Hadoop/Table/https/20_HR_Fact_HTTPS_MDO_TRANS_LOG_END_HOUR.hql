CREATE TABLE 20_HR_Fact_HTTPS_MDO_TRANS_LOG_END_HOUR(
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
) PARTITIONED BY (datetime_id int) STORED AS orc;

set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert into table 20_HR_Fact_HTTPS_MDO_TRANS_LOG_END_HOUR partition (datetime_id) select * from 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END_HOUR;

set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table 20_HR_Fact_HTTPS_MDO_TRANS_LOG_END_HOUR partition (datetime_id) select * from 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END_HOUR;



alter table 20_HR_Fact_HTTPS_MDO_TRANS_LOG_END_HOUR rename to Fact_HTTPS_MDO_TRANS_LOG_END_HOUR;


