CREATE TABLE 0_STAGING_QUIC_MDO_TRANS_LOG_END(
    applicationProtocol varchar(100),
    column1 varchar(10),
    column2 varchar(10),

    TheTimestamp bigint,
    column3 varchar(10),
    column4 varchar(10),

    transactionStart varchar(800),
    column5 varchar(10),
    column6 varchar(10),

    transactionEnd varchar(800),
    column7 varchar(10),
    column8 varchar(10),

    clientIPAddress varchar(800),
    column9 varchar(10),
    column10 varchar(10),

    serverIPAddress varchar(800),
    column11 varchar(10),
    column12 varchar(10),

    bytesToClient varchar(800),
    column13 varchar(10),
    column14 varchar(10),

    SubscriberID varchar(800),
    column15 varchar(10),
    column16 varchar(10),

    contentType varchar(800),
    column17 varchar(10),
    column18 varchar(10),

    dpiFamily varchar(800),
    column19 varchar(10),
    column20 varchar(10),

    domain varchar(800),
    column21 varchar(10),
    column22 varchar(10),

    domainClassification varchar(800),
    column23 varchar(10),
    column24 varchar(10),

    deviceType varchar(800),
    column25 varchar(10),
    column26 varchar(10),

    serverHostname varchar(800),
    column27 varchar(10),
    column28 varchar(10),

    cellTowerId varchar(800),
    column29 varchar(10),
    column30 varchar(10)
) 
row format delimited fields terminated by '|' lines terminated by '\n' stored as textfile;


load data local inpath '/home/hive/other-quic_min.csv' into table 0_STAGING_OTHER_QUIC_MDO_TRANS_LOG_END;


