EXPLAIN USING TEXT SELECT * FROM TEST.PUBLIC.CUSTOMERS WHERE CUSTOMER_ID = 1;


GlobalStats:
    partitionsTotal=1
    partitionsAssigned=1
    bytesAssigned=2048
Operations:
1:0     ->Result  CUSTOMERS.CUSTOMER_ID, CUSTOMERS.NAME, CUSTOMERS.EMAIL, 'XXX-XX-XXXX'  
1:1          ->Filter  CUSTOMERS.CUSTOMER_ID = 1  
1:2               ->TableScan  TEST.PUBLIC.CUSTOMERS  CUSTOMER_ID, NAME, EMAIL  {partitionsTotal=1, partitionsAssigned=1, bytesAssigned=2048}



SELECT SYSTEM$EXPLAIN_PLAN_JSON(LAST_QUERY_ID());
{
  "GlobalStats": {
    "partitionsTotal": 1,
    "partitionsAssigned": 1,
    "bytesAssigned": 2048
  },
  "Operations": [
    [
      {
        "id": 0,
        "operation": "Result",
        "expressions": [
          "CUSTOMERS.CUSTOMER_ID",
          "CUSTOMERS.NAME",
          "CUSTOMERS.EMAIL",
          "'XXX-XX-XXXX'"
        ]
      },
      {
        "id": 1,
        "operation": "Filter",
        "expressions": [
          "CUSTOMERS.CUSTOMER_ID = 1"
        ],
        "parentOperators": [
          0
        ]
      },
      {
        "id": 2,
        "operation": "TableScan",
        "objects": [
          "TEST.PUBLIC.CUSTOMERS"
        ],
        "expressions": [
          "CUSTOMER_ID",
          "NAME",
          "EMAIL"
        ],
        "partitionsAssigned": 1,
        "partitionsTotal": 1,
        "bytesAssigned": 2048,
        "parentOperators": [
          1
        ]
      }
    ]
  ]
}