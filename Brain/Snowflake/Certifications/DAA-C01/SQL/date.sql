SELECT TO_DATE(current_timestamp());
SELECT cast(current_timestamp() as date);
SELECT current_date();
SELECT current_timestamp();
select getdate()

select datediff(day, TO_DATE('2024-05-10'), TO_DATE(current_timestamp()))

select datediff(day, TO_DATE('2024-05-10'), cast(current_timestamp() as date))

select datediff(day, TO_DATE('2024-05-10'), current_date())

select datediff(day, TO_DATE('2024-05-10'), getdate())

select datediff(day, TO_DATE('2024-05-10'), current_timestamp())

---------------------------------------------------------------------------------------------------

SELECT TRY_TO_DATE('2024-05-10') AS valid_date, TRY_TO_DATE('Invalid') AS invalid_date;

SELECT 
    is_date(to_variant(to_date('2024-05-10'))), 
    is_date(to_variant(to_timestamp('2024-05-10'))), 
    is_date(to_variant('2024-05-10'))


select year(current_date()), month(current_date()), day(current_date())



select TO_DATE('20240510', 'yyyymmdd')
