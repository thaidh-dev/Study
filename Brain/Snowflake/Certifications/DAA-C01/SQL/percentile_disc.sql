create or replace table aggr(k int, v decimal(10,2));
insert into aggr (k, v) values
    (0, 10),
    (0, 20),
    (0, 30),
    (0, 40),
    (0, 50),
    (0, 60),
    (0, 70),
    (0, 80),
    (0, 90),
    (0, 100),
    (1, 10),
    (1, 20),
    (2, 10),
    (2, 20),
    (2, 25),
    (2, 30),
    (3, 60),
    (4, NULL);

select 
    k, 
    percentile_disc(0.3) within group (order by v) -- find the value at the 30th percentile
from aggr
group by k
order by k


select 
    median(v), -- giá trị chính giữa khi sắp xếp toàn bộ dữ liệu (bỏ NULL), với số lượng phần tử chẵn, nó sẽ nằm “giữa” hai giá trị trung tâm.
    avg(v) 
from aggr



percentile_cont
Example: Dataset = [10, 20, 30, 40]
50th percentile (0.5) → returns 25 (the midpoint between 20 and 30).

PERCENTILE_DISC
Example: Dataset = [10, 20, 30, 40]
50th percentile (0.5) → returns 20 (the smallest value ≥ 50% cumulative).

----------------------------------------------------------------------------------------------

create or replace table t_values(col int);
insert into t_values(col) values (1), (5), (2), (4);

select percentile_cont(0.6) within group (order by col) from t_values;
select percentile_disc(0.6) within group (order by col) from t_values;


select 1 + 0.6 * (4 - 1);
select 2 + 0.8 * (4 - 2);
