1. 2.3 Perform casting
What is the result of the following query?
select 100::float * 24.78::integer || ' hello';
select 100::float * 24.50::integer || ' hello';
select 100::float * 24.48::integer || ' hello';

A. Error
B. 2500 hello
C. 2400 hello
D. 2478 hello


2. 2.0
What is the result of the following query?
SELECT * FROM TABLE(STRTOK_SPLIT_TO_TABLE('a;b,c d,; e', ',; '));

A. Splits into five rows: a / b / c / d / e
B. Splits into two rows: a;b,c d and e
C. Returns NULL
D. Splits into four rows: 
a / b / "c d" / " e"


3. 4.0
Imagine you’re an analyst at an e-commerce company, and you want to understand order trends over time. You want to know how many orders were placed each week in the last 3 months. How can you put that into action?
System Filters Used:
:daterange → Filters the O_ORDERDATE column to only include the last 3 months.
:datebucket → Groups the orders by week.

A. :daterange in the WHERE clause, :datebucket in the GROUP BY clause
B. :daterange in the GROUP BY clause, :datebucket in the GROUP BY clause
C. :daterange in the GROUP BY clause, :datebucket in the WHERE clause
D. :daterange in the WHERE clause, :datebucket in the WHERE clause

4. 2.0
Which values would match the following Snowflake predicate?

SELECT *
FROM customer
WHERE customer_name ILIKE 'p%^_j%' ESCAPE '^';

A. Peter_join
B. Peter Join
C. Peter%Join
D. Peter^Join
E. Peter_Join

5. 1.0
At the end of each day, a retail chain prepares its revenue report:
Stored Procedure A – sp_load_sales_data Loads daily sales slips from CSV into sales_detail and cleans errors.
Stored Procedure B – sp_calculate_monthly_revenue Summarizes sales and inserts totals into revenue_summary.

👉 The company sets up two tasks:
sp_load_sales_data will be executed at midnight.
sp_calculate_monthly_revenue will be called only after sp_load_sales_data completes.

How will the company go about implementing that?

A. 
CREATE OR REPLACE TASK task_load_sales_data
  WAREHOUSE = my_wh
  SCHEDULE = 'USING CRON 0 0 * * * UTC'
AS
  CALL sp_load_sales_data();

CREATE OR REPLACE TASK task_calculate_monthly_revenue
  WAREHOUSE = my_wh
  AFTER task_load_sales_data
AS
  CALL sp_calculate_monthly_revenue();

B. 
CREATE OR REPLACE TASK task_load_sales_data
  WAREHOUSE = my_wh
  SCHEDULE = 'USING CRON 0 0 * * * UTC'
AS
  CALL sp_load_sales_data();

CREATE OR REPLACE TASK task_calculate_monthly_revenue
  WAREHOUSE = my_wh
  SCHEDULE = 'USING CRON 0 1 * * * UTC'
AS
  CALL sp_calculate_monthly_revenue();

C. 
CREATE OR REPLACE TASK task_load_sales_data
  WAREHOUSE = my_wh
  SCHEDULE = 'USING CRON 0 0 * * * UTC'
AS
  CALL sp_load_sales_data();

CREATE OR REPLACE TASK task_calculate_monthly_revenue
  WAREHOUSE = my_wh
  SCHEDULE = 'USING CRON 0 0 * * * UTC'
AS
  CALL sp_calculate_monthly_revenue();

EXECUTE TASK task_load_sales_data;
EXECUTE TASK task_calculate_monthly_revenue;

D. 
CREATE OR REPLACE TASK task_process_monthly_sales
  WAREHOUSE = my_wh
  SCHEDULE = 'USING CRON 0 0 * * * UTC'
AS
  CALL sp_load_sales_data();
  CALL sp_calculate_monthly_revenue();



6. 1.0 

A task tree in Snowflake contains 12 steps.
Step 10 fails, you fix the issue, and now want to continue running the task tree.
How do you resume execution?

A. EXECUTE TASK <task_name> RETRY LAST;
B. ALTER TASK <task_name> RESUME 10;
C. ALTER TASK <task_name> RESUME;
D. EXECUTE TASK <task_name> RETRY 10;


7. 3.0 có thể tạo đc 3 hoặc 5 câu hỏi từ đây
Given the following SQL objects:

CREATE OR REPLACE FUNCTION func_1(x FLOAT)
RETURNS FLOAT
LANGUAGE JAVASCRIPT
AS
$$
  return x + 10;
$$;

CREATE OR REPLACE FUNCTION func_2(x FLOAT)
RETURNS FLOAT
LANGUAGE JAVASCRIPT
AS
$$
    return x + 7
$$;

CREATE OR REPLACE PROCEDURE proc_1(X FLOAT)
RETURNS FLOAT
LANGUAGE JAVASCRIPT
AS
$$
    return X + 20;
$$;

CALL proc_1(func_1(func_2(10))::FLOAT);

What is the result?
A. 47
B. 0
C. null
D. Error

8. 3.1
which Snowflake object is used to run a series of SQL statements, including loops and conditional logic?
A. Stored procedure
B. UDF
C. CTE
D. Block


9. 
A Data Analyst wishes to comprehend the correlation between the number of active drivers and the average customer wait time. Customers spent less time waiting when there were more drivers.
Which SQL query best measures the correlation?

A. 
SELECT CORR(active_driver, wait_time)
FROM rides;
B.
SELECT VARIANCE(active_driver), VARIANCE(wait_time)
FROM rides;
C.
SELECT SUM(active_driver), AVG(wait_time)
FROM rides
GROUP BY DATE_TRUNC('month', event_date);
D.
SELECT SUM(active_driver), AVG(wait_time)
FROM rides;

10. 1.7
tạo 4 câu từ đây
A drone delivery company must determine the shortest distance between two warehouses located in different cities.  How can I measure the geodesic distance between them?
ST_HAUSDORFFDISTANCE
ST_LENGTH
ST_DISTANCE

11. 1.4
3 bảng, câu query có 2 cái left join
đáp án khác: 2 inner join, 1 inner join 1 left join

-- 1. Company table
CREATE OR REPLACE TABLE Company (
    company_id NUMBER PRIMARY KEY,
    company_name VARCHAR(100),
    location VARCHAR(100)
);

-- Sample data
INSERT INTO Company (company_id, company_name, location) VALUES
(1, 'TechNova Inc.', 'New York'),
(2, 'GreenWorld Ltd.', 'London'),
(3, 'FutureVision Corp.', 'Tokyo');


-- 2. Department table
CREATE OR REPLACE TABLE Department (
    department_id NUMBER PRIMARY KEY,
    department_name VARCHAR(100),
    company_id NUMBER,
    FOREIGN KEY (company_id) REFERENCES Company(company_id)
);

-- Sample data
INSERT INTO Department (department_id, department_name, company_id) VALUES
(101, 'Research & Development', 1),
(102, 'Human Resources', 1),
(201, 'Marketing', 1),
(202, 'Finance', 1),
(301, 'Engineering', 3),
(302, 'Sales', 3);


-- 3. Employee table
CREATE OR REPLACE TABLE Employee (
    employee_id NUMBER PRIMARY KEY,
    employee_name VARCHAR(100),
    position VARCHAR(100),
    department_id NUMBER,
    FOREIGN KEY (department_id) REFERENCES Department(department_id)
);

-- Sample data
INSERT INTO Employee (employee_id, employee_name, position, department_id) VALUES
(1001, 'Alice Johnson', 'Software Engineer', 101),
(1002, 'Bob Smith', 'HR Specialist', 102),
(2001, 'Charlie Brown', 'Marketing Manager', 201),
(2002, 'Diana Prince', 'Financial Analyst', 202),
(3001, 'Ethan Lee', 'Mechanical Engineer', 101),
(3002, 'Fiona Chen', 'Sales Executive', 102);


A. 
select 
    c.company_name, 
    d.department_name,
    e.employee_name
from company c
left join department d on c.company_id = d.company_id
left join employee e on d.department_id = e.department_id;


12. 
Which SQL window function correctly calculates a 4-point moving average for sales_amount per product?
A.
SELECT 
  AVG(sales_amount) OVER (
    PARTITION BY product_id
    ORDER BY sale_date
    ROWS BETWEEN 3 PRECEDING AND CURRENT ROW
  ) AS moving_avg
FROM sales;
B.
SELECT 
  SUM(sales_amount) OVER (
    PARTITION BY product_id
    ORDER BY sale_date
    ROWS BETWEEN 3 PRECEDING AND CURRENT ROW
  ) AS moving_avg
FROM sales;
C.
SELECT 
  AVG(sales_amount) OVER (
    PARTITION BY product_id
    ORDER BY sale_date
    ROWS BETWEEN 4 PRECEDING AND CURRENT ROW
  ) AS moving_avg
FROM sales;
D.
SELECT 
  SUM(sales_amount) OVER (
    PARTITION BY product_id
    ORDER BY sale_date
    ROWS BETWEEN 3 PRECEDING AND CURRENT ROW
  ) AS moving_avg
FROM sales;


13. 1
A junior developer wrote a query using a DATETIME column, but the team requires converting the value into a Snowflake timestamp type while keeping the original local time unchanged.
Which function should be used?

A. TO_TIMESTAMP()
B. TO_TIMESTAMP_NTZ()
C. TO_TIMESTAMP_LTZ()
D. TO_TIMESTAMP_TZ()

Answer: B (TO_TIMESTAMP_NTZ)


14. 4
A dashboard is created from a shared worksheet in Snowsight.
After sharing, can the owner of the worksheet still use it normally?

A. Yes, the owner can still use the worksheet.
B. No, the owner loses access after sharing.
C. The owner can only view but not edit it.
D. The owner must duplicate the worksheet to continue using it.

-- https://docs.snowflake.com/en/user-guide/ui-snowsight-dashboards#label-snowsight-dashboards-sharing
-- If the worksheet is shared with other users, those users lose access to the worksheet when you create a dashboard because the worksheet is removed when the dashboard is created. Permissions on the worksheet are revoked and links to the worksheet no longer function. For more details about sharing dashboards, see Share dashboards.
You share a worksheet with another user.
Later, you create a dashboard from that worksheet.
What happens to the other user’s access to the worksheet?

A. They keep full access to the worksheet.
B. They lose access because the worksheet is removed when the dashboard is created.
C. They can still view the worksheet but cannot edit it.
D. They can access the worksheet only through the dashboard.


15. 4
Which action in Snowsight allows you to remove a tile from a dashboard without deleting the underlying worksheet or query?
A. DELETE
B. DROP
C. UNPLACE
D. REMOVE

16. 

17. khi có nhiều user cùng edit 1 cái worksheet trong dashboard (hoặc có thể chỉ là worksheet ko thôi, ko nhớ nữa) thì sẽ thế nào

18. 2.5
A team reports that a critical query has recently become slow.
You are asked to investigate the performance issue without changing warehouses, and you must analyze historical execution details to identify the root cause (e.g., queued time, compilation time, spilling, skew, or warehouse load).
Which Snowflake view should you query to begin the investigation?

A. SNOWFLAKE.ACCOUNT_USAGE.QUERY_HISTORY
B. SNOWFLAKE.ACCOUNT_USAGE.QUERY_ACCELERATION_HISTORY
C. SNOWFLAKE.ACCOUNT_USAGE.WAREHOUSE_METERING_HISTORY
D. SNOWFLAKE.ACCOUNT_USAGE.QUERY_INSIGHTS

19. 4
You have a table that contains sensitive columns such as phone number and email.
You need to hide these sensitive columns from users by applying a policy.
What is this type of policy called?

A. Differential privacy policy
B. Projection policy
C. Join policy
D. Aggregation policy

20. 
You need to create a function in Snowflake that makes a call to an external API endpoint.
Which objects must be created to allow this function to successfully perform an outbound API call?
(Select TWO.)

A. CREATE API INTEGRATION
B. CREATE EXTERNAL FUNCTION
C. CREATE EXTERNAL ACCESS INTEGRATION
D. CREATE SECURITY INTEGRATION
E. CREATE PROCEDURE

21. 1
What is the primary use of the INFER_SCHEMA table function in Snowflake?

A. Reading column definitions from unstructured data (e.g., images, PDFs)
B. Extracting column names and types from semi-structured data stored in staged files
C. Reading column definitions directly from a JSON object already loaded into a table
D. Retrieving column metadata from an existing Snowflake table

22. 1
Imagine you receive a batch of Parquet files containing customer transaction data exported from a CRM system. You don’t know the exact schema (columns and data types) in advance, but you need to quickly create a table in Snowflake to store and analyze this data.
Which approach should you use?
A. CREATE TABLE … USING TEMPLATE with INFER_SCHEMA
B. CREATE TABLE … FROM ARCHIVE OF
C. CREATE TABLE … AS SELECT
D. CREATE TABLE … FROM SNAPSHOT SET

23. 3.4
You use an AI function in Snowflake (Cortex) to generate data in JSON format. After generating the data, how can you create a table from this generated JSON data?

Options:
A. Use RESULT_SCAN to retrieve the generated data, then create a table.
B. Use CREATE TABLE … USING TEMPLATE with INFER_SCHEMA.
C. Manually create the table, then COPY each row of data.
D. Create a temporary table, store the JSON in it, then create a new table using CREATE TABLE AS SELECT.

24. 1.3
You have an existing table in Snowflake with a large amount of data. You want to create a new table from this existing table without incurring additional storage costs. Which approach should you use?

Options:
A. Use CREATE TABLE … CLONE to create a zero-copy clone of the existing table.
B. Use CREATE TABLE … AS SELECT to copy all data into a new table.
C. Export the table to a stage and then load it back into a new table.
D. Manually create a new table and INSERT all rows from the original table.


25. 3
-- https://docs.snowflake.com/en/sql-reference/constraints-alter
Table A has a foreign key referencing table B. If you rename table B to C, what happens to the foreign key in table A?

Options:
A. The foreign key in A is automatically updated to reference table C.
B. The foreign key in A becomes invalid and must be dropped and recreated.
C. Renaming table B is not allowed until the foreign key in A is dropped.
D. The foreign key in A continues to reference the old table name B, causing errors.



26 + 27. 1
You have a table called monthly_sales with columns: emp_id, dept, jan_sales, feb_sales, mar_sales, and apr_sales. You want to transform this table so that each month’s sales appear as separate rows under a single column called month, with the corresponding sales value in another column called sales.

Options:
A. UNPIVOT
B. PIVOT
C. GROUP BY CUBE
D. GROUP BY ROLLUP

27. https://docs.snowflake.com/en/sql-reference/constructs/unpivot#examples

28. 3.2
Which of the following functions is typically used for descriptive analysis rather than predictive or regression analysis?

A. REGR_SLOPE()
B. REGR_INTERCEPT()
C. ROW_NUMBER()
D. COUNT(DISTINCT …)
E. AVG()

29. 3
You want to estimate the similarity between two large tables in Snowflake without comparing every row directly. Which technique is most appropriate for similarity estimation?

Options:
A. MinHash
B. HyperLogLog (HLL)
C. APPROX_PERCENTILE
D. JACCARD_SIMILARITY

30. 3
You need to estimate the cardinality of a very large dataset in Snowflake while keeping computation efficient. Which function or technique is most appropriate?

Options:
A. HLL
B. APPROXIMATE_SIMILARITY
C. APPROX_PERCENTILE
D. APPROX_TOP_K

31. 1.7 
What is the result of the following query?
1,2,3,4
SELECT PERCENTILE_DISC(0.6) WITHIN GROUP (ORDER BY col) FROM t_values;
A. 2
B. 3
C. 2.5
D. 6

32. cho 1 cái line chart và hỏi xem nó là positive hay negative correlation


33. 2.3
You have a column of type VARIANT that contains an array, for example:
[10, 20, 30, 40]
You want to query the last element of the array in Snowflake. Which expression correctly retrieves it?

Options:
A. GET(col, ARRAY_SIZE(col) - 1)
B. GET(col, -1)
C. col[-1]
D. col[ARRAY_SIZE(col)]


34. 2
You are running a complex analytic query that performs multiple JOINs and large aggregations on a 2-billion-row table. The query is significantly slower than expected, and the Query Profile shows substantial disk spilling, especially during the aggregation and join phases.
You need to reduce or eliminate disk spilling to improve performance.
Which approach is the most appropriate?

A. Split the processing into smaller steps, for example by replacing multiple expensive CTEs with intermediate temporary tables, so each step handles less data and reduces memory pressure.
B. Increase the size of the virtual warehouse, giving the query more memory to reduce or eliminate spilling.
C. Upgrade the Snowflake edition, for example from Standard to Enterprise, to automatically eliminate disk spilling.
D. Use a Multi-Cluster Warehouse, assuming it will reduce disk spilling by distributing the workload within a single query.

35. cho 1 cái ảnh 
(nhớ lại: có 2 bảng có row count = nhau join vs nhau
result thì lại rất lớn
)
explosion join

36. 2
You have two Snowflake tables, table_a and table_b, with identical schemas and similar data sizes.
You run the same SELECT query on both tables, but the query on table_b completes significantly faster than the one on table_a.

Assuming both queries were executed by the same user and warehouse, what is the most likely reason for the difference in performance?

Options:
A. Table_b actually contains fewer rows than expected.
B. A Row Access Policy is applied to table_a, causing additional filtering and overhead.
C. The query on table_b was executed using a multi-cluster warehouse.
D. The query on table_b used the result cache.

37. 4
Consider the following Snowflake query:

SELECT seq4(), uniform(1, 10, RANDOM(12)) 
FROM TABLE(GENERATOR(ROWCOUNT => NULL)) v 
ORDER BY 1;

How many rows will this query generate?

Options:
A. Infinite
B. 0
C. 10
D. 12



38. 1
câu nào sẽ get ra 10 rows từ 1 bảng có hàng triệu rows (chọn 2)
SELECT * FROM testtable SAMPLE (10 ROWS);
SELECT * FROM testtable SAMPLE 10;
SELECT top 10 * FROM testtable;
SELECT 10 rows * FROM testtable;
SELECT * FROM testtable limit 10;

You have a table testtable with millions of rows.
Which of the following queries will return exactly 10 rows? (Select TWO)

Options:
A. SELECT * FROM testtable SAMPLE (10 ROWS);
B. SELECT * FROM testtable SAMPLE 10;
C. SELECT TOP 10 * FROM testtable;
D. SELECT TOP 10 ROWS * FROM testtable;
E. SELECT 10 ROWS * FROM testtable;


39. 1
create file format thì cái file format đó precedence
schema statement
copy into statement
table statement
database statement

You have created a file format in Snowflake. Which usage takes the highest precedence when Snowflake determines which file format to use?

Options:
A. Schema-level file format
B. Table-level file format (default for the table)
C. COPY INTO statement specifying the file format
D. Database-level file format

40. 1
You want to query data directly from a Parquet file stored in a Snowflake stage.
Which of the following queries correctly reads and casts the columns from the Parquet file?
A. SELECT
    $1::INT,
    $2::STRING,
    TO_TIMESTAMP_NTZ($3),
    $4::STRING
FROM @TEST.PUBLIC.MY_INT_STAGE/sample.parquet (FILE_FORMAT => 'TEST.PUBLIC.PARQUET_FORMAT');
B. SELECT
    $1::INT,
    $1::STRING,
    TO_TIMESTAMP_NTZ($1::STRING),
    $1::STRING
FROM @TEST.PUBLIC.MY_INT_STAGE/sample.parquet (FILE_FORMAT => 'TEST.PUBLIC.PARQUET_FORMAT');
C. SELECT
    PARSE(col_name_1),
    PARSE(col_name_2),
    PARSE(col_name_3),
    PARSE(col_name_4)
FROM @TEST.PUBLIC.MY_INT_STAGE/sample.parquet (FILE_FORMAT => 'TEST.PUBLIC.PARQUET_FORMAT');
D. SELECT
    $1:col_name_1::INT,
    $1:col_name_2::STRING,
    TO_TIMESTAMP_NTZ($1:col_name_3::STRING),
    $1:col_name_4::STRING
FROM @TEST.PUBLIC.MY_INT_STAGE/sample.parquet (FILE_FORMAT => 'TEST.PUBLIC.PARQUET_FORMAT');


41. 2.3
Given the following data:
+-------------+---------------+--------+
| EMPLOYEE_ID | DEPARTMENT_ID | SALARY |
|-------------+---------------+--------|
|        1001 |            10 |  10000 |
|        1020 |            10 |   9000 |
|        1030 |            10 |   7000 |
|         900 |            20 |  15000 |
|        2000 |            20 |   NULL |
|        2010 |            20 |  14000 |
|        2020 |            20 |   8000 |
+-------------+---------------+--------+
What is the result of the following query?
SELECT MAX_BY(employee_id, salary) FROM employees;
A. 15000
B. 900
C. 2020
D. 8000

What is the result of the following query?
SELECT MIN_BY(employee_id, salary) FROM employees;
A. NULL
B. 7000
C. 1030
D. 900

+-------------+---------------+--------+
| EMPLOYEE_ID | DEPARTMENT_ID | SALARY |
|-------------+---------------+--------|
|        1001 |            10 |  10000 |
|        1020 |            10 |   9000 |
|        1030 |            10 |   8000 |
|         900 |            20 |  15000 |
|        2000 |            20 |   NULL |
|        2010 |            20 |  15000 |
|        2020 |            20 |   8000 |
+-------------+---------------+--------+
What is the result of the following query?
SELECT MAX_BY(employee_id, salary) FROM employees;
A. non-deterministic
B. 900
C. 2010
D. 15000

What is the result of the following query?
SELECT MIN_BY(employee_id, salary) FROM employees;
A. non-deterministic
B. 1030
C. 2020
D. 8000


42. 2.4
Your company is modernizing its analytics platform on Snowflake.
The legacy system stores all transaction data in a single denormalized One Big Table (OBT) to simplify ETL pipelines.
The data engineering team plans to migrate this model directly into Snowflake without redesigning the schema.

However, the analytics team argues that Snowflake’s architecture better supports a Dimensional Model (fact and dimension tables) for performance, extensibility, and governance.
Which statement best explains why a dimensional approach is generally preferred in Snowflake over maintaining a single large OBT?

A. Dimensional models reduce data redundancy, improve query performance through smaller fact tables, and allow Snowflake’s micro-partition pruning to eliminate unnecessary data scanning.
B. Snowflake automatically converts OBT schemas into dimensional schemas during query optimization, so using an OBT structure adds no value.
C. Snowflake’s storage costs increase dramatically when fact and dimension tables are used, so OBT is the only cost-efficient model unless clustering keys are disabled.
D. Dimensional models prevent the need to maintain any ETL logic, because Snowflake automatically handles all joins between fact and dimension tables at runtime.

✔ Notes
Correct answer: A
B is false (Snowflake never auto-converts schemas).
C is false (fact/dim does not increase storage costs).
D is false (joins must be explicitly defined; ETL still required).



43. 1
câu này có thể tạo thêm vài câu nữa từ ceil, floor, round
Given the following data:
+------+
| col  |
+------+
| 20   |
| NULL |
| 20   |
| 10   |
| 30   |
| 20   |
+------+
What is the result of the following query?
SELECT CEIL(AVG(col), 2) AS result
FROM sample_data;



44. 4
chart nào thể hiện đc cái natural cluster
Your analytics team is exploring customer behavior patterns and wants to identify natural clusters within a large dataset when visualizing the output in Snowsight.
Which type of chart is most appropriate for revealing these organically occurring groupings?

A. Line chart
B. Scatter plot
C. Bar chart
D. Heatgrid
E. Scorecard

45. có 1 câu về seed (repeatable) (quên mẹ nội dung rồi nhưng mà khá dễ)

46. 2
https://docs.snowflake.com/en/user-guide/data-quality-fixing
https://docs.snowflake.com/en/user-guide/data-quality-expectations
max, min, distinct, count
có 1 bảng 1 tỉ data
làm sao để phân tích đc max, min, distinct, count, avg 1 cách ổn nhất
quên mẹ nó đáp án rồi

t lấy data từ marketplace, data đó rất lớn, khoảng 1 tỉ dòng
làm sao để t có thể đánh giá data đó (max, min, distinct, count, avg, ...) 1 cách hiệu quả nhất

47. create task SYSTEM$STREAM_HAS_DATA

48. HASH_AGG để compare 2 tables

49. những ngôn ngữ nào hỗ trợ tạo function
tạo thành mấy câu luôn đi: func, store procedure, pyspark


