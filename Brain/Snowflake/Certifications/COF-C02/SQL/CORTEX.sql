SELECT SNOWFLAKE.CORTEX.COMPLETE('snowflake-arctic', 'What are large language models?');

ALTER ACCOUNT SET CORTEX_ENABLED_CROSS_REGION = 'AZURE_US';

SELECT SNOWFLAKE.CORTEX.COMPLETE(
    'openai-gpt-4.1',
        CONCAT('Critique this review in bullet points: <review>', name, '</review>')
) FROM TEST.PUBLIC.CUSTOMERS LIMIT 10;










