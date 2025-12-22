CREATE OR REPLACE TABLE sales_office_postal_example(
  office_name VARCHAR,
  postal_code VARCHAR);

INSERT INTO sales_office_postal_example VALUES 
('sales1', '94061'),
('sales2', '94070'),
('sales3', '98116'),
('sales4', '98005');

CREATE OR REPLACE TABLE customer_postal_example(
  customer VARCHAR,
  postal_code VARCHAR);

INSERT INTO customer_postal_example VALUES 
('customer1', '94066'),
('customer2', '94061'),
('customer3', '98444'),
('customer4', '98005'),
('customer4', '98005');

SELECT * FROM sales_office_postal_example
union
SELECT * FROM customer_postal_example
