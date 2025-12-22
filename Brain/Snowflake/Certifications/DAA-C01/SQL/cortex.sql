
SELECT SNOWFLAKE.CORTEX.COMPLETE(
  'llama3-70b',
  'Return ONLY a JSON array object with fields: id, name, email, isActive, roles, profile. Do not include any explanatory text.'
);

-- Create a table from the JSON result
CREATE OR REPLACE TABLE USER_PROFILES AS
SELECT
    t.value:id::int          AS id,
    t.value:name::string     AS name,
    t.value:email::string    AS email,
    t.value:isActive::boolean AS is_active,
    t.value:roles::array     AS roles,
    t.value:profile:avatar::string AS avatar,
    t.value:profile:bio::string    AS bio
FROM TABLE(
    FLATTEN(
        INPUT => PARSE_JSON(
            (SELECT * FROM TABLE(RESULT_SCAN(LAST_QUERY_ID())))
        )
    )
) AS t;

SELECT * FROM USER_PROFILES;