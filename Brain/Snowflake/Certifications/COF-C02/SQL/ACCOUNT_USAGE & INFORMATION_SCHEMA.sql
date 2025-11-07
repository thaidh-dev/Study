select * from snowflake.account_usage.access_history

select * from SNOWFLAKE.ACCOUNT_USAGE.TABLES;

select * from test_2.information_schema.columns
where table_schema = 'PUBLIC';

-- store the storage utilization information even for dropped tables
select * from snowflake.account_usage.table_storage_metrics;

ls @UTIL_DB.PUBLIC.MY_INTERNAL_STAGE
SELECT * 
FROM DIRECTORY( @UTIL_DB.PUBLIC.MY_INTERNAL_STAGE );



select * from snowflake.account_usage.STAGE_STORAGE_USAGE_HISTORY
select * from snowflake.ACCOUNT_USAGE.DATABASE_STORAGE_USAGE_HISTORY

select * from snowflake.account_usage.tables

select * from snowflake.account_usage.storage_usage
order by usage_date desc


select * from snowflake.account_usage.query_history
select query_retry_cause from snowflake.account_usage.query_history

select * from snowflake.account_usage.policy_references

-- current database
select * from test.information_schema.table_storage_metrics
-- Shows all tables across the account
select * from snowflake.account_usage.table_storage_metrics

-- This Account Usage view can be used to query Snowflake data loading history for the last 365 days (1 year). The view displays load activity for both COPY INTO <table> statements and continuous data loading using Snowpipe. The view avoids the 10,000 row limitation of the LOAD_HISTORY view.
select * from snowflake.account_usage.copy_history
This Information Schema view enables you to retrieve the history of data loaded into tables using the COPY INTO <table> command within the last 14 days. The view displays one row for each file loaded.
select * from snowflake.account_usage.load_history
select * from snowflake.information_schema.load_history



select * from snowflake.account_usage.materialized_view_refresh_history

-- To return daily average storage usage for internal stages
select * from snowflake.account_usage.stage_storage_usage_history






show organization accounts 
show organization accounts like 'dht'
show organization accounts history
SHOW MANAGED ACCOUNTS




