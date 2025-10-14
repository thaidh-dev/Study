COPY INTO @~/1
FROM HOME_SALES
FILE_FORMAT = (TYPE = CSV)
DETAILED_OUTPUT = FALSE;

COPY INTO @~/2
FROM HOME_SALES
FILE_FORMAT = (TYPE = CSV)
DETAILED_OUTPUT = TRUE;

LIST @~/price=low;

select * from home_sales;

COPY INTO @~
FROM HOME_SALES
partition by (
    case
        when price < 400000 then 'price=low'
        else 'price=high'
    end
)
FILE_FORMAT = (TYPE = CSV)
DETAILED_OUTPUT = TRUE;
