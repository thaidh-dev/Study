CREATE TABLE my_clone CLONE TEST_2.PUBLIC.COUNTRY AT (OFFSET => -60*60);

-- can clone a table using time travel (can clone the data from the past of a table)
-- but cant query before the clone was created
select * from my_clone at (OFFSET => -60 * 50)
