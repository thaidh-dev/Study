CREATE TABLE qt (i INTEGER, p CHAR(1), o INTEGER);
INSERT INTO qt (i, p, o) VALUES
    (1, 'A', 1),
    (2, 'A', 2),
    (3, 'B', 1),
    (4, 'B', 2);


SELECT i, p, o, ROW_NUMBER() OVER (PARTITION BY p ORDER BY o) AS row_num
    FROM qt
    qualify row_num = 1
    ;


-- WHERE: Filters rows before aggregation. 
-- HAVING: Filters groups after standard aggregation (GROUP BY). 
-- QUALIFY: Filters rows after window function calculation

