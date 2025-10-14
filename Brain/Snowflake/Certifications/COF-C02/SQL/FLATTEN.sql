CREATE OR REPLACE TABLE CUSTOMERS as
select column1 as ID, parse_json(column2) as info 
from values
(1, '{
    name: "Alice",
    purchases: [
        {item: "Laptop", price: 1200},
        {item: "Mouse", price: 25}
    ]
}'),
(2, '{
    name: "Bob",
    purchases: [
        {item: "Phone", price: 800},
        {item: "Headphones", price: 150}
    ]
}');

drop table customers;
select * from customers;

SELECT 
    c.ID,
    c.INFO:name::string AS CUSTOMER_NAME,
    f.value:item::string AS ITEM,
    f.value:price::number AS PRICE
FROM CUSTOMERS c,
LATERAL FLATTEN(INPUT => c.INFO:purchases) f;

SELECT 
    c.ID,
    c.INFO:name::string AS CUSTOMER_NAME,
    c.INFO:purchases[0]:item::string AS ITEM,
    c.info:purchases[0]:price::number AS PRICE,
    c.info:purchases[1]:item::string AS ITEM,
    c.info:purchases[1]:price::number AS PRICE,
    c.info['PuRchases'][1]['item']::string AS ITEM_2,
    c.info['purchases'][1]:item::string AS ITEM_3
FROM CUSTOMERS c;

SELECT * FROM TABLE(FLATTEN(INPUT => PARSE_JSON('[1, ,77]'))) f;


SELECT * FROM TABLE(FLATTEN(INPUT => PARSE_JSON('{"a":1, "b":[77,88]}'), PATH => 'b')) f;

------------------------------------

CREATE TABLE my_data (
    id INT,
    details VARIANT
);

INSERT INTO my_data (id, details)
SELECT 1, PARSE_JSON('{"name": "Alice", "age": 30, "city": "New York"}')
UNION ALL
SELECT 2, PARSE_JSON('{"name": "Bob", "occupation": "Engineer", "skills": ["Python", "SQL"]}');

SELECT
    details:name::STRING AS name,
    details['age']::INT AS age,
    details:occupation::STRING AS occupation
FROM my_data;

-------------------------------------------------

SELECT GET(details, 'name') FROM test_2.public.my_data;
SELECT details:name FROM test_2.public.my_data;

SELECT 
    get(
        get(
            get_path(info, 'purchases'), 
            0
        ), 
        'item'
    ) item, 
    get(get(get_path(info, 'purchases'), 0), 'price') price
FROM util_db.public.customers; -- For array elements

SELECT GET_PATH(my_variant_column, 'level1_key.level2_key') FROM my_table;



select value FROM LATERAL FLATTEN(input => parse_json('[1, 2, 3]'));


