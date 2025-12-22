-- titanic.parquet
CREATE or replace EXTERNAL TABLE titanic(
    PassengerId int as (value:PassengerId::int),
    Survived boolean as (
        case
            when value:Survived::int = 1 then true 
            else false
        end
    ),
    Pclass int as (value:Pclass::int),
    Name string as (value:Name::string),
    Sex string as (value:Sex::string),
    Age int as (value:Age::int),
    SibSp int as (value:SibSp::int),
    Parch int as (value:Parch::int),
    Ticket string as (value:Ticket::string),
    Fare number(10,2) as (value:Fare::number(10,2)),
    Cabin string as (value:Cabin::string),
    Embarked string as (value:Embarked::string)
) 
LOCATION = @TEST.PUBLIC.S3_STAGE
FILE_FORMAT = (TYPE = PARQUET);

select * from titanic

-------------------------------------------------------------------------