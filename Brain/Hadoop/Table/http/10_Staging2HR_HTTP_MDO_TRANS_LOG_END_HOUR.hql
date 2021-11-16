create view 10_Staging2HR_HTTP_MDO_TRANS_LOG_END_HOUR as
SELECT      
    cast(DATETIME_ID / 100 as int) DATE_ID,
    CELL_NODE_ID,
    cast(SUM (1) as float) WEB_TOTAL,
    cast(SUM(CASE WHEN ORIGINCONTENTTYPE  like '%video%' THEN 1 ELSE 0 END) as float) MDO_VIDEO_COUNT,
    cast(SUM (
        CASE
            WHEN 
                DEVICEHTTPRESPONSE LIKE '1%'
                OR DEVICEHTTPRESPONSE LIKE '2%'
                OR DEVICEHTTPRESPONSE LIKE '3%'
                OR DEVICEHTTPRESPONSE LIKE '4%'
                OR DEVICEHTTPRESPONSE LIKE '5%'
            THEN
                1
            ELSE
                0
        END
    ) as float) WEB_RESP_SUCC,
    cast(SUM ((nvl(osLatency,0) + nvl(InternalLatency,0)) / 1000) as float) WEB_AVG_RESP_DELAY,
    cast(SUM ((nvl(transactionEnd,0) - nvl(transactionStart,0)) / 1000) as float) WEB_AVG_DISPLAY_DELAY,
    cast(SUM ((nvl(transactionEnd,0) - nvl(transactionStart,0)) / 1000) as float) WEB_DL_TIME, 
    cast(8 * (SUM (nvl(headerBytesToDevice,0) + nvl(bodyBytesToDevice,0))) / 1024 as float) WEB_DL_DATA,
    cast(SUM (CASE WHEN osHttpResponse IN (200, 300) THEN 1 ELSE 0 END) as float) WEB_BROWS_SUCC,
    cast(SUM (
        CASE
            WHEN osHttpResponse IN (100, 200, 300, 400, 500)
            THEN
                1
            ELSE
                0
        END
    ) as float) WEB_BROWS_TOTAL,
    cast(SUM (
        CASE
            WHEN osHttpResponse IN (200, 300) AND ORIGINCONTENTTYPE  like '%video%' 
            THEN 1 
            ELSE 0
        END
    ) as float) AS VIDEO_STREAM_SUCC,
    cast(SUM (
        CASE
            WHEN 
                osHttpResponse IN (100, 200, 300, 400, 500)
                AND ORIGINCONTENTTYPE  like '%video%'
            THEN
                1
            ELSE
                0
        END
    ) as float) VIDEO_STREAM_TOTAL,
    cast(SUM (
        CASE
            WHEN ORIGINCONTENTTYPE  like '%video%'
            THEN (nvl(osLatency,0) + nvl(InternalLatency,0)) / 1000
            ELSE NULL
        END
    ) as float) VIDEO_STREAM_START_DELAY,
    cast(SUM (
        CASE
            WHEN ORIGINCONTENTTYPE  like '%video%' AND (videoPostOptNetworkRespDuration - videoWatchedDuration) > 0
            THEN
                1
            ELSE
                NULL
        END
    ) as float) VIDEO_STREAM_STALL,
    cast(SUM (
        CASE
            WHEN ORIGINCONTENTTYPE  like '%video%'
            THEN 8 * (nvl(headerBytesToDevice,0) + nvl(bodyBytesToDevice,0)) / 1024
            ELSE NULL
        END
    ) as float) VIDEO_STREAM_DATA,
    cast(SUM (
        CASE
            WHEN ORIGINCONTENTTYPE  like '%video%' THEN (nvl(transactionEnd,0) - nvl(transactionStart,0)) / 1000
            ELSE NULL
        END
    ) as float) VIDEO_STREAM_TIME,
    cast(SUM (
        CASE
            WHEN 
                ORIGINCONTENTTYPE  like '%video%' AND osHttpResponse IN (200, 300)
                AND 8 * (headerBytesToDevice + bodyBytesToDevice) / (1024 * (transactionEnd - transactionStart) / 1000) >= 2500
            THEN
                1
            ELSE
                0
        END
    ) as float) VIDEO_HD_NUM,
    cast(SUM (
        CASE
            WHEN     
                ORIGINCONTENTTYPE like '%video%' AND osHttpResponse IN (200, 300)
                AND 8 * (headerBytesToDevice + bodyBytesToDevice) / (1024 * (transactionEnd - transactionStart) / 1000) < 2500 
                AND 8 * (headerBytesToDevice + bodyBytesToDevice) / (1024 * (transactionEnd - transactionStart) / 1000) >= 700
            THEN
                1 
            ELSE
                0
        END
    ) as float) VIDEO_SD_NUM,
    cast(SUM (
        CASE
            WHEN
                ORIGINCONTENTTYPE  like '%video%' AND osHttpResponse IN (200, 300)
                AND 8 * (headerBytesToDevice + bodyBytesToDevice) / (1024 * (transactionEnd - transactionStart) / 1000) < 700
            THEN
                1
            ELSE
                0
        END
    ) as float) VIDEO_LD_NUM,
    DATETIME_ID
FROM 10_Staging2HR_HTTP_MDO_TRANS_LOG_END 
GROUP BY DATETIME_ID, CELL_NODE_ID;


