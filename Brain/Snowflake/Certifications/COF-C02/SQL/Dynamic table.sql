CREATE OR REPLACE DYNAMIC TABLE dynamic_table
  TARGET_LAG = '5 minutes'   -- freshness target
  WAREHOUSE = COMPUTE_WH
AS
SELECT count(*) abc
FROM GARDEN_PLANTS.FLOWERS.FLOWER_DETAILS;


select * from dynamic_table

INSERT INTO GARDEN_PLANTS.FLOWERS.FLOWER_DETAILS (PLANT_NAME, ROOT_DEPTH_CODE)
VALUES
  ('Rose', 'M'),
  ('Tulip', 'S'),
  ('Sunflower', 'D'),
  ('Daisy', 'S'),
  ('Lily', 'M'),
  ('Marigold', 'S'),
  ('Orchid', 'M'),
  ('Peony', 'D');
