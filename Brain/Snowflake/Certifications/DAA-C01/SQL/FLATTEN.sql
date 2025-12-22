CREATE OR REPLACE TABLE nested_orders as
select column1 as id, PARSE_JSON(column2) as json_data
from values
(1, '{
  orders: [
    {
      order_id: 101,
      items: [
        {sku: "A1", qty: 2, details: {color: "red", size: "M"}},
        {sku: "B2", qty: 1, details: {color: "blue", size: "L"}}
      ]
    },
    {
      order_id: 102,
      items: [
        {sku: "C3", qty: 5, details: {color: "green", size: "S"}}
      ]
    }
  ]
}');

SELECT 
    o.value:order_id::int          AS order_id,
    i.value:sku::string            AS sku,
    i.value:qty::int               AS qty,
    i.value:details.color::string  AS color,
    i.value:details.size::string   AS size
FROM nested_orders t,
     LATERAL FLATTEN(input => t.json_data:orders) o,
     LATERAL FLATTEN(input => o.value:items) i;


--------------------------------------------

CREATE OR REPLACE TABLE clickstream_events as 
select column1 as session_id, column2 as event_timestamp, PARSE_JSON(column3) as properties
from values
('S1', '2024-01-15 10:00:00', '{"page": "/home", "element": "banner"}'),
('S1', '2024-01-15 10:01:00', '{"page": "/product/123", "element": "add_to_cart_button"}'),
('S1', '2024-01-15 10:02:00', '{"page": "/product/123", "element": "add_to_cart_button"}'),
('S1', '2024-01-15 10:03:00', '{"page": "/checkout", "element": "submit_order"}'),
('S1', '2024-01-15 10:04:00', '{"page": "/product/123", "element": "view_details"}'),

('S2', '2024-01-15 11:00:00', '{"page": "/home", "element": "search_box"}'),
('S2', '2024-01-15 11:01:00', '{"page": "/product/456", "element": "add_to_cart_button"}'),
('S2', '2024-01-15 11:02:00', '{"page": "/product/456", "element": "add_to_cart_button"}'),
('S2', '2024-01-15 11:03:00', '{"page": "/product/456", "element": "view_details"}'),
('S2', '2024-01-15 11:04:00', '{"page": "/checkout", "element": "submit_order"}');

with session_page_elements as (
    select 
        session_id, 
        ce.properties:page page, 
        ce.properties:element element
    from clickstream_events ce
)
select 
    session_id, 
    page, 
    element, 
    count(element) 
from session_page_elements
group by session_id, page, element
order by session_id, page

-------------------------------------------------------

CREATE OR REPLACE TABLE RAW_REVIEWS as
select parse_json(column1) as raw_json
from values
('{
  "product_id": "P1001",
  "reviews": [
    {"reviewer_name": "Alice", "rating": 5, "comment": "Great product!"},
    {"reviewer_name": "Bob", "rating": 3, "comment": "Average quality"},
    {"reviewer_name": "Charlie", "rating": 4, "comment": "Good but not perfect"}
  ]
}'),
('{
  "product_id": "P1002",
  "reviews": [
    {"reviewer_name": "David", "rating": 5, "comment": "Excellent"},
    {"reviewer_name": "Eva", "rating": 2, "comment": "Not worth it"},
    {"reviewer_name": "Frank", "rating": 5, "comment": "Loved it!"}
  ]
}')

select distinct r.value:reviewer_name
from raw_reviews rr,
lateral flatten(input => rr.raw_json:reviews) r


with PRODUCT_REVIEWERS_FLATTENED as (
    select 
        rr.raw_json:product_id product_id,
        r.value:reviewer_name reviewer_name,
        r.value:rating rating
    from raw_reviews rr,
    lateral flatten(input => rr.raw_json:reviews) r
), 
HIGH_RATED_REVIEWS as (
    select 
        product_id, 
        reviewer_name,
        rating
    from product_reviewers_flattened
    where rating > 4
),
AVERAGE_PRODUCT_RATINGS as (
    select 
        product_id, 
        avg(rating) avg_rating
    from product_reviewers_flattened
    group by product_id
)
select 
    apr.product_id, 
    hrr.reviewer_name,
    hrr.rating,
    apr.avg_rating
from AVERAGE_PRODUCT_RATINGS apr
join high_rated_reviews hrr on apr.product_id = hrr.product_id

---------------------------------------------------------------

CREATE OR REPLACE TABLE RAW_DATA as
select parse_json(column1) as json_data
from values 
('{
  "order_id": "ORD-123",
  "customer_id": "CUST-456",
  "items": [
    {
      "item_name": "Laptop",
      "item_price": 1200
    },
    {
      "item_name": "Mouse",
      "item_price": 25
    }
  ]
}')

select 
    rd.json_data:order_id,
    rd.json_data:order_id || '_' || i.index,
    i.value:item_name,
    i.value:item_price
from raw_data rd,
lateral flatten(input => rd.json_data:items) i
select 
    json_data:order_id,
    json_data:order_id || '_' || index,
    value:item_name,
    value:item_price
from raw_data,
lateral flatten(input => json_data:items)

select 
    rd.json_data:order_id order_id,
    sum(i.value:item_price)
from raw_data rd,
lateral flatten(input => rd.json_data:items) i
group by order_id


select 
    rd.json_data:order_id order_id,
    sum(i.value:item_price)
from raw_data rd,
lateral flatten(input => rd.json_data:items) i
group by 1 -- GROUP BY 1 is equivalent to GROUP BY order_id, as order_id is the first column in the SELECT list

select array_size(rd.json_data:items)
from raw_data rd


--------------------------------------

SELECT *
FROM TABLE(FLATTEN(input => parse_json('[1, ,77]'))) f;

--------------------------------------------------


CREATE OR REPLACE TABLE my_table as
select parse_json(column1) as RAW_DATA
from values 
('{ "customer_id": 123, "details": { "name": "John Doe", "city": "New York" } }');

SELECT RAW_DATA:details.name FROM my_table;
SELECT RAW_DATA['details']['name']::string FROM my_table;

--------------------------------------------------------

CREATE OR REPLACE TABLE country as
select parse_json(column1) as name
from values 
('["USA", "VN", "JP", "KOREA", "CHINA", "GERMANY", "LAO"]');

select array_contains('VN'::VARIANT, n.value) 
from country c,
lateral flatten(input => c.name) n

select array_contains('VN'::VARIANT, PARSE_JSON(c.name)::array) 
from country c

select PARSE_JSON(c.name)::array
from country c

----------------------------------------------------------

select 
    $1:PassengerId::int,
    TRY_TO_TIMESTAMP($1:Fare::string),
    $1:Name
from @TEST.PUBLIC.MY_INT_STAGE/titanic.parquet (FILE_FORMAT => 'TEST.PUBLIC.PARQUET_FORMAT')
select $1 from
@TEST.PUBLIC.MY_INT_STAGE/titanic.parquet (file_format => 'TEST.PUBLIC.PARQUET_FORMAT')

select $1:continent::varchar,
      $1:country:name::varchar,
      $1:country:city::variant
from @TEST.PUBLIC.MY_INT_STAGE/cities.parquet (file_format => 'TEST.PUBLIC.PARQUET_FORMAT')

select $1::varchar,
      $2::varchar,
      $1:country:city::variant
from @TEST.PUBLIC.MY_INT_STAGE/cities.parquet (file_format => 'TEST.PUBLIC.PARQUET_FORMAT')


SELECT t.$1, t.$2 
FROM @mystage1 (file_format => 'myformat', pattern=>'.*data.*[.]csv.gz') t;

--------------------------------------------------------------------------------------------------------------------------------

create or replace table raw_data as
select parse_json(column1) as json_data
from values ('
[
  {
    "id": 1,
    "name": "Alice",
    "city": "Seattle",
    "skills": ["SQL", "Snowflake", "Python", "JAVA"]
  },
  {
    "id": 2,
    "name": "Bob",
    "city": "Portland",
    "skills": ["Java", "AWS", "Data Modeling"]
  },
  {
    "id": 3,
    "name": "Charlie",
    "city": "San Francisco",
    "skills": ["JavaScript", "React"]
  }
]
');

select
    value:id::int as id,
    count(*) as num_skills
from raw_data,
lateral flatten(input => json_data),
lateral flatten(input => value:skills)
group by 1;




select 
    jd.value:id,
    array_size(jd.value:skills)
from raw_data rd,
lateral flatten(input => json_data) jd;

select 
    jd.value:id,
    count(jd.value:skills)
from raw_data rd,
lateral flatten(input => json_data) jd;



select * from raw_data







select 
    jd.value:id,
    array_size(get_path(jd.value, 'skills'))
from raw_data,
lateral flatten(input => json_data) jd;

select 
    jd.value:id,
    array_size(get(jd.value, 'skills'))
from raw_data,
lateral flatten(input => json_data) jd;



