CREATE OR REPLACE TABLE SENSOR_READINGS (
    sensor_id     VARCHAR,
    reading_time  TIMESTAMP_NTZ,
    raw_value     VARCHAR
);
INSERT INTO SENSOR_READINGS (sensor_id, reading_time, raw_value) VALUES
('S1', DATEADD('minute', -30, CURRENT_TIMESTAMP), '123.45'),
('S1', DATEADD('minute', -20, CURRENT_TIMESTAMP), 'N/A'),
('S1', DATEADD('minute', -10, CURRENT_TIMESTAMP), '500'),
('S2', DATEADD('minute', -50, CURRENT_TIMESTAMP), '250.75'),
('S2', DATEADD('minute', -40, CURRENT_TIMESTAMP), 'ERROR'),
('S2', DATEADD('minute', -5,  CURRENT_TIMESTAMP), '300');

select * from SENSOR_READINGS

SELECT
    sensor_id,
    AVG(try_TO_NUMBER(raw_value))
FROM
    SENSOR_READINGS
GROUP BY
    sensor_id;


-------------------------------------------------------

select avg(raw_value) from sensor_readings
where raw_value != 'N/A' and raw_value != 'ERROR'



