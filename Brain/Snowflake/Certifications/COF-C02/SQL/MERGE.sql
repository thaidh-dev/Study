-- Target table
CREATE OR REPLACE TABLE target_table (
  id INT,
  description STRING
);

-- Source table
CREATE OR REPLACE TABLE source_table (
  id INT,
  description STRING
);

-- Insert sample data
INSERT INTO target_table VALUES
  (1, 'old value'),
  (2, 'keep me');

INSERT INTO source_table VALUES
  (1, 'new value'),   -- should update id=1
  (3, 'insert me');   -- should insert new row


MERGE INTO target_table t
USING source_table s
ON t.id = s.id
WHEN MATCHED THEN
  UPDATE SET t.description = s.description
WHEN NOT MATCHED THEN
  INSERT (id, description) VALUES (s.id, s.description);


SELECT * FROM target_table;

-- Output:
-- id | description
-- 1  | new value      (updated)
-- 2  | keep me        (unchanged)
-- 3  | insert me      (inserted)
