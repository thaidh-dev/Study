-- Customers table
CREATE OR REPLACE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(50),
    country_code VARCHAR(5)
);

-- Countries table
CREATE OR REPLACE TABLE countries (
    country_code VARCHAR(5),
    country_name VARCHAR(50)
);

-- Insert customers
INSERT INTO customers VALUES
(1, 'Alice', 'US'),
(2, 'Bob', 'US'),
(3, 'Charlie', 'UK'),
(4, 'Diana', 'UK'),
(5, 'Eve', 'UK');

-- Insert countries (notice duplicates!)
INSERT INTO countries VALUES
('US', 'United States'),
('US', 'USA'),
('UK', 'United Kingdom'),
('UK', 'Britain');

SELECT c.customer_id, c.name, co.country_name
FROM customers c
JOIN countries co
ON c.country_code = co.country_code;


------------------------------------------------------------
select c1.c_name, c1.c_nationkey
from customer c1 join customer c2
  on c1.c_nationkey = c2.c_nationkey
where c1.c_acctbal > 3000;






