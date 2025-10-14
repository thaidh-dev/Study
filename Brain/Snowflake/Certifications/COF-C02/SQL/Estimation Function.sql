CREATE OR REPLACE TABLE mhtab1(c1 NUMBER,c2 DOUBLE,c3 TEXT,c4 DATE);
CREATE OR REPLACE TABLE mhtab2(c1 NUMBER,c2 DOUBLE,c3 TEXT,c4 DATE);

INSERT INTO mhtab1 VALUES
    (1, 1.1, 'item 1', to_date('2016-11-30')),
    (2, 2.31, 'item 2', to_date('2016-11-30')),
    (3, 1.1, 'item 3', to_date('2016-11-29')),
    (4, 44.4, 'item 4', to_date('2016-11-30'));

INSERT INTO mhtab2 VALUES
    (1, 1.1, 'item 1', to_date('2016-11-30')),
    (2, 2.31, 'item 2', to_date('2016-11-30')),
    (3, 1.1, 'item 3', to_date('2016-11-29')),
    (4, 44.4, 'item 4', to_date('2016-11-30')),
    (6, 34.23, 'item 6', to_date('2016-11-29'));

SELECT APPROXIMATE_SIMILARITY(mh) FROM
    ((SELECT MINHASH(100, *) AS mh FROM mhtab1)
    UNION ALL
    (SELECT MINHASH(100, *) AS mh FROM mhtab2));


select * from SNOWFLAKE_SAMPLE_DATA.TPCH_SF1.ORDERS;