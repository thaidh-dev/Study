create table 0_STAGING_HTTP_MDO_TRANS_LOG_END(
    TheTimestamp bigint,
    column1 varchar(10),
    column2 varchar(10),
    
    transactionStart varchar(100),
    column3 varchar(10),
    column4 varchar(10),
    
    transactionEnd varchar(100),
    column5 varchar(10),
    column6 varchar(10),
    
    osLatency varchar(4000),
    column7 varchar(10),
    column8 varchar(10),
    
    internalLatency varchar(4000),
    column9 varchar(10),
    column10 varchar(10),
    
    ClientIPAddress varchar(4000),
    column11 varchar(10),
    column12 varchar(10),
    
    destinationIpAddress varchar(4000),
    column13 varchar(10),
    column14 varchar(10),
    
    osHttpResponse varchar(4000),
    column15 varchar(10),
    column16 varchar(10),
    
    deviceHttpResponse varchar(4000),
    column17 varchar(10),
    column18 varchar(10),
    
    averageThroughputBytesPerSecond varchar(4000),
    column19 varchar(10),
    column20 varchar(10),
    
    clientRequestHost varchar(4000),
    column21 varchar(10),
    column22 varchar(10),
    
    clientRequestURIPathAndQueryString varchar(65535),
    column23 varchar(10),
    column24 varchar(10),
    
    originContentType varchar(4000),
    column25 varchar(10),
    column26 varchar(10),
    
    responseTime varchar(4000),
    column27 varchar(10),
    column28 varchar(10),
    
    videoPostOptNetworkRespDuration varchar(4000),
    column29 varchar(10),
    column30 varchar(10),
    
    videoWatchedDuration varchar(4000),
    column31 varchar(10),
    column32 varchar(10),
    
    headerBytesToDevice varchar(4000),
    column33 varchar(10),
    column34 varchar(10),
    
    bodyBytesToDevice varchar(4000),
    column35 varchar(10),
    column36 varchar(10),
    
    SubscriberID varchar(4000),
    column37 varchar(10),
    column38 varchar(10),
    
    deviceType varchar(4000),
    column39 varchar(10),
    column40 varchar(10),
    
    cellTowerId varchar(4000),
    column41 varchar(10),
    column42 varchar(10),
    
    serverHostname varchar(4000),
    column43 varchar(10),
    column44 varchar(10),
    
    CL1 varchar(4000),
    column45 varchar(10),
    column46 varchar(10)
) 
row format delimited fields terminated by '|' lines terminated by '\n' stored as textfile;



load data local inpath '/home/hive/http_min.csv' into table 0_STAGING_HTTP_MDO_TRANS_LOG_END;




