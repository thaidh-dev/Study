create table Fact_OTHER_QUIC_MDO_TRANS_LOG_END_HOUR(
    DATE_ID int,
    CELL_NODE_ID bigint,
    QUIC_WEB_DL_DATA float,
    QUIC_WEB_DL_TIME float,
    QUIC_VIDEO_STREAM_DATA float,
    QUIC_VIDEO_STREAM_TIME float,
    QUIC_VIDEO_HD_NUM float,
    QUIC_VIDEO_SD_NUM float,
    QUIC_VIDEO_LD_NUM float,
    QUIC_VIDEO_STREAM_SUCC float
)
PARTITIONED BY (datetime_id int) 
STORED AS orc;

set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert into table Fact_OTHER_QUIC_MDO_TRANS_LOG_END_HOUR partition (datetime_id) select * from 10_Staging2HR_OTHER_QUIC_MDO_TRANS_LOG_END_HOUR;
