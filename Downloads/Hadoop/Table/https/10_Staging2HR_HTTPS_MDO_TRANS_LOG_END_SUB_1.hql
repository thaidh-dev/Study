CREATE VIEW 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END_sub_1 
AS SELECT 
    TheTimestamp as TheTimestamp,
    cast(TheTimestamp / 10000 as int) as DATETIME_ID,
    ClientIPAddress as ClientIPAddress,
    SubscriberID as MSISDN,

    cast(transactionStart as float) as transactionStart,
    cast(transactionEnd as float) as transactionEnd,
    destinationIpAddress as destinationIpAddress,
    cast(bytesToDevice as float) as bytesToDevice,
    transactionaborted as transactionaborted,
    sslSNI as sslSNI,
    contentType as contentType,

    deliveryMechanism as deliveryMechanism,
    cast(averageThroughputBytesPerSecond as float) as averageThroughputBytesPerSecond,
    cast(maxSessionBandwidthBytesPerSecond as float) as maxSessionBandwidthBytesPerSecond,
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

FROM 0_Staging_HTTPS_MDO_TRANS_LOG_END;