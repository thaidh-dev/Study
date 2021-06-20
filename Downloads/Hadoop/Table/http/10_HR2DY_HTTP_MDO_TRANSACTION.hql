CREATE VIEW 10_HR2DY_HTTP_MDO_TRANSACTION AS 
SELECT   
    DATE_ID,
    CELL_NODE_ID,
    cast(SUM (WEB_TOTAL) as float)                  WEB_TOTAL,
    cast(SUM(MDO_VIDEO_COUNT) as float)             MDO_VIDEO_COUNT,
    cast(SUM(WEB_RESP_SUCC) as float)               WEB_RESP_SUCC,
    cast(SUM(WEB_AVG_RESP_DELAY) as float)          WEB_RES_DL_SUM,
    cast(SUM(WEB_AVG_DISPLAY_DELAY) as float)       WEB_DPL_DL_SUM,
    cast(SUM(WEB_DL_TIME) as float)                 WEB_DL_TIME,
    cast(SUM(WEB_DL_DATA) as float)                 WEB_DL_DATA,
    cast(SUM(WEB_BROWS_SUCC) as float)              WEB_BROWS_SUCC,
    cast(SUM(WEB_BROWS_TOTAL) as float)             WEB_BROWS_TOTAL,
    cast(SUM(VIDEO_STREAM_SUCC) as float)           VIDEO_STREAM_SUCC,
    cast(SUM(VIDEO_STREAM_TOTAL) as float)          VIDEO_STREAM_TOTAL,
    cast(SUM (VIDEO_STREAM_START_DELAY) as float)   VIDEO_STREAM_START_DELAY,
    cast(SUM (VIDEO_STREAM_STALL) as float)         VIDEO_STREAM_STALL,
    cast(SUM (VIDEO_STREAM_DATA) as float)          VIDEO_STREAM_DATA,
    cast(SUM (VIDEO_STREAM_TIME) as float)          VIDEO_STREAM_TIME,
    cast(SUM (VIDEO_HD_NUM) as float)               VIDEO_HD_NUM,
    cast(SUM (VIDEO_SD_NUM) as float)               VIDEO_SD_NUM,
    cast(SUM (VIDEO_LD_NUM) as float)               VIDEO_LD_NUM,
    DATETIME_ID
                 
FROM 20_HR_Fact_HTTP_MDO_TRANS_LOG_END_HOUR
GROUP BY DATETIME_ID, DATE_ID, CELL_NODE_ID;