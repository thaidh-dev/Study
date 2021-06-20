create view 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END_HOUR as
SELECT 
    cast(DATETIME_ID / 100 as int) DATE_ID,
    CELL_NODE_ID,
    cast(SUM(CASE WHEN contentType <> 'video' and bytesToDevice is not null THEN 8 * bytesToDevice / 1024 ELSE NULL END) as bigint) HTTPS_WEB_DL_DATA,
    cast(SUM(CASE WHEN contentType <> 'video' and bytesToDevice is not null THEN (transactionEnd - transactionStart) / 1000 ELSE NULL END) as bigint) HTTPS_WEB_DL_TIME,
    cast(SUM(CASE WHEN contentType = 'video' and bytesToDevice is not null THEN 8 * bytesToDevice / 1024 ELSE NULL END) as bigint) HTTPS_VIDEO_STREAM_DATA,
    cast(SUM(CASE WHEN contentType = 'video' and  AVERAGETHROUGHPUTBYTESPERSECOND is not null and AVERAGETHROUGHPUTBYTESPERSECOND <> 0 THEN bytesToDevice / AVERAGETHROUGHPUTBYTESPERSECOND ELSE NULL END) as bigint) HTTPS_VIDEO_STREAM_TIME,
    cast(SUM (
        CASE
            WHEN     
                contentType = 'video' 
                and AVERAGETHROUGHPUTBYTESPERSECOND is not null 
                and AVERAGETHROUGHPUTBYTESPERSECOND <> 0
                AND 8 * bytesToDevice / 1024 / bytesToDevice / AVERAGETHROUGHPUTBYTESPERSECOND >= 2500
            THEN 1
            ELSE 0
        END
    ) as bigint) HTTPS_VIDEO_HD_NUM,
    cast(SUM (
        CASE
            WHEN     
                contentType = 'video' 
                and AVERAGETHROUGHPUTBYTESPERSECOND is not null 
                and AVERAGETHROUGHPUTBYTESPERSECOND <> 0
                AND 8 * bytesToDevice / 1024 / bytesToDevice / AVERAGETHROUGHPUTBYTESPERSECOND >= 700
                AND 8 * bytesToDevice / 1024 / bytesToDevice / AVERAGETHROUGHPUTBYTESPERSECOND < 2500
            THEN 1
            ELSE 0
        END
    ) as bigint) HTTPS_VIDEO_SD_NUM,
    cast(SUM (
        CASE 
            WHEN     
                contentType = 'video' 
                and AVERAGETHROUGHPUTBYTESPERSECOND is not null 
                and AVERAGETHROUGHPUTBYTESPERSECOND <> 0
                AND 8 * bytesToDevice / 1024 / bytesToDevice / AVERAGETHROUGHPUTBYTESPERSECOND < 700
            THEN 1
            ELSE 0
        END
    ) as bigint) HTTPS_VIDEO_LD_NUM,
    cast(SUM(CASE WHEN contentType = 'video' THEN 1 ELSE NULL END ) as bigint) HTTPS_VIDEO_STREAM_SUCC,
    DATETIME_ID

FROM 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END 
GROUP BY DATETIME_ID, CELL_NODE_ID;