CREATE OR REPLACE API INTEGRATION demonstration_external_api_integration_01
  API_PROVIDER = aws_api_gateway
  API_AWS_ROLE_ARN = 'arn:aws:iam::702540275215:role/service-role/HelloWorldFunction-role-s5pih2qx'
  API_ALLOWED_PREFIXES = ('https://zhrd30e0k5.execute-api.ap-southeast-1.amazonaws.com/dev/')
  ENABLED = TRUE;

DESCRIBE INTEGRATION demonstration_external_api_integration_01;

-- 2. Create the external function that calls the API
CREATE OR REPLACE EXTERNAL FUNCTION my_external_function()
  RETURNS VARIANT
  API_INTEGRATION = demonstration_external_api_integration_01
  HEADERS = ( 'Content-Type' = 'application/json' )
  AS 'https://zhrd30e0k5.execute-api.ap-southeast-1.amazonaws.com/dev/';

select my_external_function();


