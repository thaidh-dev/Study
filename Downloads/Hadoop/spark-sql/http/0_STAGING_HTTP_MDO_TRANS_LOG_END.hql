create table 0_STAGING_HTTP_MDO_TRANS_LOG_END(
    TheTimestamp bigint,
    transactionStart varchar(100),
    transactionEnd varchar(100),
    osLatency varchar(4000),
    internalLatency varchar(4000),
    ClientIPAddress varchar(4000),
    destinationIpAddress varchar(4000),
    osHttpResponse varchar(4000),
    deviceHttpResponse varchar(4000),
    averageThroughputBytesPerSecond varchar(4000),
    clientRequestHost varchar(4000),
    clientRequestURIPathAndQueryString varchar(65535),
    originContentType varchar(4000),
    responseTime varchar(4000),
    videoPostOptNetworkRespDuration varchar(4000),
    videoWatchedDuration varchar(4000),
    headerBytesToDevice varchar(4000),
    bodyBytesToDevice varchar(4000),
    SubscriberID varchar(4000),
    deviceType varchar(4000),
    cellTowerId varchar(4000),
    serverHostname varchar(4000),
    CL1 varchar(4000)
) row format delimited fields terminated by '|' lines terminated by '\n' stored as textfile;

load data inpath '/noc_mdo_spark/http_min.csv' into table 0_STAGING_HTTP_MDO_TRANS_LOG_END;


