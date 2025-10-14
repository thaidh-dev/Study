-- Create a table with an ARRAY column
CREATE OR REPLACE TABLE orders_with_items (
    order_id INT,
    customer_name STRING,
    items ARRAY
);


-- Correct way: use SELECT instead of VALUES
INSERT INTO orders_with_items (order_id, customer_name, items)
SELECT 1, 'Alice', ARRAY_CONSTRUCT('apple', 'banana', 'cherry')
UNION ALL
SELECT 2, 'Bob', ARRAY_CONSTRUCT('milk', 'bread');

INSERT INTO orders_with_items (order_id, customer_name, items)
SELECT 3, 'Charlie', PARSE_JSON('["pen", "notebook", "eraser"]');



select array_slice(items, 1, 3) from orders_with_items;
select items[0] from orders_with_items;




