SELECT ST_DWITHIN(ST_MAKEPOINT(0, 0), ST_MAKEPOINT(1, 0), 150000);
+-------------------------------------------------------------+
| ST_DWITHIN (ST_MAKEPOINT(0, 0), ST_MAKEPOINT(1, 0), 150000) |
|-------------------------------------------------------------|
| True                                                        |
+-------------------------------------------------------------+

select ST_DISTANCE(ST_MAKEPOINT(0, 0), ST_MAKEPOINT(1, 0));



----------------------------------


CREATE OR REPLACE TABLE CUSTOMER_LOCATIONS (
    CUSTOMER_ID   INT,
    LATITUDE      FLOAT,
    LONGITUDE     FLOAT
);

-- Insert some demo data
INSERT INTO CUSTOMER_LOCATIONS (CUSTOMER_ID, LATITUDE, LONGITUDE) VALUES
(1, 37.7749, -122.4194),   -- San Francisco
(2, 34.0522, -118.2437),   -- Los Angeles
(3, 40.7128, -74.0060),    -- New York
(4, 51.5074, -0.1278);     -- London


CREATE OR REPLACE TABLE CUSTOMER_GEOGRAPHY AS
SELECT
    CUSTOMER_ID,
    TO_GEOGRAPHY(ST_POINT(LONGITUDE, LATITUDE)) AS LOCATION
FROM CUSTOMER_LOCATIONS;


select * from CUSTOMER_GEOGRAPHY

SELECT
    CUSTOMER_ID,
    ST_ASWKT(LOCATION) AS LOCATION_WKT
FROM CUSTOMER_GEOGRAPHY;

SELECT
    CUSTOMER_ID,
    ST_ASTEXT(LOCATION) AS LOCATION_WKT
FROM CUSTOMER_GEOGRAPHY;

-----------------------------------------------------------------------------
-- ST_POINT(0,0) → creates a geography object (longitude/latitude on Earth).
-- So when you measure distance, Snowflake assumes those are coordinates on the globe.
-- The result is in meters (hence the big number: ~555,812 meters ≈ 556 km).
SELECT ST_Distance(
    ST_POINT(0, 0),
    ST_POINT(3, 4)
);  

-- ST_GeomFromText('POINT(0 0)') → creates a geometry object (flat Cartesian plane).
-- So when you measure distance, it’s just straight-line math (Pythagoras).
-- Distance between (0,0) and (3,4) = 5 units.
SELECT ST_Distance(
  ST_GeomFromText('POINT(0 0)'),
  ST_GeomFromText('POINT(3 4)')
);

-- Geometry = flat map math → simple Euclidean distance.
-- Geography = Earth math → curved globe, distances in meters.

--------------------------------------------------------------------------------
-- Measures the shortest distance between two geometries.
-- Think of it as: “How close are these two shapes at their nearest points?”
SELECT ST_Distance(
  ST_GeomFromText('POINT(0 0)'),
  ST_GeomFromText('POINT(3 4)')
); -- => 5

-- Returns the length of a LINESTRING.
-- Think of it as: “How long is this line?”
SELECT ST_LENGTH(
    ST_GEOMFROMTEXT('LINESTRING(0 0, 3 4)')
); -- => 5

-- What it does
-- Measures the maximum distance between two geometries.
-- Think of it as:
-- 👉 “If I walk from every point in shape A to the nearest point in shape B…
-- what is the worst (longest) distance I must walk?”
-- It compares the shapes as a whole, not just their closest points.

-- Example
-- Two lines:
-- Line A: (0,0) → (10,0)
-- Line B: (0,5) → (10,5)
SELECT ST_HAUSDORFFDISTANCE(
    TO_GEOGRAPHY('LINESTRING(0 0, 10 0)'),
    TO_GEOGRAPHY('LINESTRING(0 5, 10 5)')
); -- => 5
-- Result → 5
-- Because every point on line A is exactly 5 away from line B.







