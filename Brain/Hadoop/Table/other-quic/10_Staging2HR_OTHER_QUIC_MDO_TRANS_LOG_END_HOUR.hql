create view 10_Staging2HR_OTHER_QUIC_MDO_TRANS_LOG_END_HOUR
as SELECT   
    cast(DATETIME_ID / 100 as int) DATE_ID,
    CELL_NODE_ID,
    SUM(CASE WHEN contentType <> 'video' AND bytesToClient IS NOT NULL THEN 8 * bytesToClient / 1024 ELSE NULL END) QUIC_WEB_DL_DATA,
    SUM(CASE WHEN contentType <> 'video' AND bytesToClient IS NOT NULL THEN (transactionEnd - transactionStart) / 1000 ELSE NULL END) QUIC_WEB_DL_TIME,
    SUM(CASE WHEN contentType = 'video'  AND bytesToClient IS NOT NULL THEN 8 * bytesToClient / 1024 ELSE NULL END) QUIC_VIDEO_STREAM_DATA,
    SUM(CASE WHEN contentType = 'video'  AND bytesToClient IS NOT NULL THEN (transactionEnd - transactionStart) / 1000 ELSE NULL END) QUIC_VIDEO_STREAM_TIME,
    SUM (
        CASE
            WHEN     
                contentType = 'video'  
                AND bytesToClient IS NOT NULL
                AND 8 * (bytesToClient)/ (  1024 * (transactionEnd - transactionStart) / 1000) >= 2500

            THEN 1 
            ELSE 0
        END
    ) QUIC_VIDEO_HD_NUM,
    SUM (
        CASE
            WHEN    
                contentType = 'video'  
                AND bytesToClient IS NOT NULL
                AND 8 * (bytesToClient)/ (  1024 * (transactionEnd - transactionStart) / 1000) >= 700
                AND 8 * (bytesToClient)/ (  1024 * (transactionEnd - transactionStart) / 1000) < 2500
            THEN 1
            ELSE 0
        END
    ) QUIC_VIDEO_SD_NUM,
    SUM (
        CASE
            WHEN     
                contentType = 'video'  
                AND bytesToClient IS NOT NULL
                AND 8 * (bytesToClient)/ (  1024 * (transactionEnd - transactionStart) / 1000) < 700
            THEN 1 
            ELSE 0
        END
    ) QUIC_VIDEO_LD_NUM,
    SUM(
        CASE 
            WHEN contentType = 'video'  
            AND bytesToClient IS NOT NULL 
            THEN 1 
            ELSE NULL 
        END 
    ) QUIC_VIDEO_STREAM_SUCC,
    DATETIME_ID
FROM 10_Staging2HR_OTHER_QUIC_MDO_TRANS_LOG_END 
GROUP BY DATETIME_ID,CELL_NODE_ID;
