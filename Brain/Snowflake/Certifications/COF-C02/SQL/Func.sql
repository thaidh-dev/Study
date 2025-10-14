CREATE OR REPLACE FUNCTION my_java_udf(input_param VARCHAR)
RETURNS VARCHAR
LANGUAGE JAVA
HANDLER = 'MyClass.myMethod'
TARGET_PATH = '@my_internal_stage/my_udf_jar.jar'
AS
$$
  class MyClass {
    public static String myMethod(String input) {
      return "Hello " + input;
    }
  }
$$;

select my_java_udf('thaidh')



CREATE OR REPLACE FUNCTION my_udf()
  RETURNS STRING
  LANGUAGE PYTHON
  RUNTIME_VERSION = 3.10
  HANDLER = 'say_hello'
AS
$$
def say_hello():
    return "Hello from Snowflake!"
$$;

select my_udf()

show functions

-- VOLATILE: UDF might return different values for different rows, even for the same input (e.g. due to non-determinism and statefullness).

-- IMMUTABLE: UDF assumes that the function, when called with the same inputs, will always return the same result. This guarantee is not checked. Specifying IMMUTABLE for a UDF that returns different values for the same input will result in undefined behavior.

-- Default: VOLATILE
CREATE OR REPLACE FUNCTION random_num()
RETURNS NUMBER(19,0)
VOLATILE -- VOLATILE | IMMUTABLE
AS
$$
  RANDOM()
$$;

SELECT random_num()
FROM TABLE(GENERATOR(ROWCOUNT => 5));


