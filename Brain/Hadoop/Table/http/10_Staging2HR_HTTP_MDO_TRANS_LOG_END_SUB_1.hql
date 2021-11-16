CREATE VIEW 10_Staging2HR_HTTP_MDO_TRANS_LOG_END_SUB_1 AS 
SELECT 
    TheTimestamp, 
    cast(TheTimestamp/10000 as int) as DATETIME_ID,
    CASE CLIENTIPADDRESS 
        WHEN '-' THEN NULL ELSE CLIENTIPADDRESS 
    END CLIENTIPADDRESS,
    SubscriberID as MSISDN,
    cast(TRANSACTIONSTART as float) TRANSACTIONSTART,
    cast(TRANSACTIONEND as float) TRANSACTIONEND, 
    cast(OSLATENCY as float) OSLATENCY, 
    cast(INTERNALLATENCY as float) INTERNALLATENCY,
    cast(DESTINATIONIPADDRESS as float) DESTINATIONIPADDRESS,
    cast(OSHTTPRESPONSE as float) OSHTTPRESPONSE,
    cast(DEVICEHTTPRESPONSE as float) DEVICEHTTPRESPONSE,
    cast(AVERAGETHROUGHPUTBYTESPERSECOND as float) AVERAGETHROUGHPUTBYTESPERSECOND,
    CLIENTREQUESTHOST CLIENTREQUESTHOST,
    cast(CLIENTREQUESTURIPATHANDQUERYSTRING as VARCHAR(4000)) CLIENTREQUESTURIPATHANDQUERYSTRING, 
    ORIGINCONTENTTYPE ORIGINCONTENTTYPE,
    cast(RESPONSETIME as float) RESPONSETIME,
    cast(VIDEOPOSTOPTNETWORKRESPDURATION as float) VIDEOPOSTOPTNETWORKRESPDURATION,
    cast(VIDEOWATCHEDDURATION as float) VIDEOWATCHEDDURATION,
    cast(HEADERBYTESTODEVICE as float) HEADERBYTESTODEVICE,
    cast(BODYBYTESTODEVICE as float) BODYBYTESTODEVICE,
    deviceType as deviceType,
    cellTowerId as cellTowerId,
    serverHostname as serverHostname,
    cast((case
        when 
            (locate('SAC', cellTowerId) > 0 OR locate('CI', cellTowerId) > 0) 
            AND (length(cellTowerId) - length(regexp_replace(cellTowerId, '\\:', ''))) = 2
        then
            substring(cellTowerId, locate(':', cellTowerId) + 1, (locate(':', cellTowerId , locate(':', cellTowerId) + 1) - 1) - locate(':', cellTowerId)) 
    end) as varchar(800)) as lac,
    cast((case
        when 
            (locate('SAC', cellTowerId) > 0 OR locate('CI', cellTowerId) > 0) 
            AND (length(cellTowerId) - length(regexp_replace(cellTowerId, '\\:', ''))) = 2
        then
            substring(cellTowerId, locate(':', cellTowerId, locate(':', cellTowerId) + 1) + 1, length(cellTowerId) - locate(':', cellTowerId, locate(':', cellTowerId) + 1))
    end) as varchar(800)) as CELL_ID,
    cast((case
        when 
            locate('ECI', cellTowerId) > 0 
            AND locate(':', cellTowerId) > 0
        then
            conv(
                substring(
                    conv(substring(cellTowerId, locate(':', cellTowerId) + 1, length(cellTowerId)), 10, 16), 
                    length(conv(substring(cellTowerId, locate(':', cellTowerId) + 1, length(cellTowerId)), 10, 16)) - 1, 
                    length(conv(substring(cellTowerId, locate(':', cellTowerId) + 1, length(cellTowerId)), 10, 16))
                ),
                16, 
                10
            )
    end) as varchar(800)) as CELL_ID_4G,
    cast((case 
        when locate('SAC', cellTowerId) > 0 then '3G' 
        when locate('ECI', cellTowerId) > 0 then '4G' 
        when locate('CI', cellTowerId) > 0 then '2G' 
    end) as varchar(800)) as cell_type
FROM `0_STAGING_HTTP_MDO_TRANS_LOG_END`;