CREATE OR REPLACE TABLE objectagg_example(g NUMBER, k VARCHAR(30), v VARIANT);
INSERT INTO objectagg_example SELECT 0, 'name', 'Joe'::VARIANT;
INSERT INTO objectagg_example SELECT 0, 'age', 21::VARIANT;
INSERT INTO objectagg_example SELECT 1, 'name', 'Sue'::VARIANT;
INSERT INTO objectagg_example SELECT 1, 'zip', 94401::VARIANT;

SELECT * FROM objectagg_example;

SELECT OBJECT_AGG(k, v) 
FROM objectagg_example 
GROUP BY g;

SELECT OBJECT_CONSTRUCT(k, v) AS oc 
FROM objectagg_example


