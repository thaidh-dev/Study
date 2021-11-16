CREATE VIEW 10_Staging2HR_OTHER_QUIC_MDO_TRANS_LOG_END_sub_1
as select
    TheTimestamp,
    cast(TheTimestamp / 10000 as int) as DATETIME_ID,
    clientIPAddress as clientIPAddress,
    SubscriberID as MSISDN,

    applicationProtocol as applicationProtocol,
    cast(transactionStart as float) as transactionStart,
    cast(transactionEnd as float) as transactionEnd,

    serverIPAddress as serverIPAddress,
    cast(bytesToClient as float) as bytesToClient,
    contentType as contentType,
    dpiFamily as dpiFamily,
    domain as domain,
    domainClassification as domainClassification,
    deviceType as deviceType,
    serverHostname as serverHostname,
    cellTowerId as cellTowerId,

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
from 0_STAGING_OTHER_QUIC_MDO_TRANS_LOG_END;