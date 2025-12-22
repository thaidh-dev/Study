alter task task_root suspend;
alter task task_a suspend;
alter task task_b suspend;
alter task task_c suspend;
alter task task_d suspend;
alter task task_e suspend;
alter task task_f suspend;
alter task task_g suspend;
alter task task_h suspend;
alter task task_i suspend;

DROP TASK IF EXISTS task_root;
DROP TASK IF EXISTS task_a;
DROP TASK IF EXISTS task_b;
DROP TASK IF EXISTS task_c;
DROP TASK IF EXISTS task_d;
DROP TASK IF EXISTS task_e;
DROP TASK IF EXISTS task_f;
DROP TASK IF EXISTS task_g;
DROP TASK IF EXISTS task_h;
DROP TASK IF EXISTS task_i;

CREATE or replace TASK task_root
  -- SCHEDULE = '1 MINUTE'
  AS SELECT 1;

CREATE or replace TASK task_a
  AFTER task_root
  AS SELECT 1;

CREATE or replace TASK task_b
  AFTER task_root
  AS SELECT 1;

CREATE or replace TASK task_c
  AFTER task_a, task_b
  AS SELECT 1;

CREATE or replace TASK task_d
  AFTER task_a
  AS SELECT 1;

CREATE or replace TASK task_e
  AFTER task_c
  AS SELECT 1;

CREATE or replace TASK task_f
  AFTER task_d
  AS SELECT 1/0;
  -- AS SELECT 1;

CREATE or replace TASK task_g
  AFTER task_f
  AS SELECT 1;

CREATE or replace TASK task_h
  AFTER task_b
  AS SELECT 1;

CREATE or replace TASK task_i
  AFTER task_g
  AS SELECT 1;

-- Newly created or cloned tasks are created suspended
alter task task_a resume;
alter task task_b resume;
alter task task_c resume;
alter task task_d resume;
alter task task_e resume;
alter task task_f resume;
alter task task_g resume;
alter task task_h resume;
alter task task_i resume;
alter task task_root resume;

execute task task_root;
execute task task_root retry last;

execute task task_f retry last;

----------------------------------------------------------------------------------------------------------

alter task task_1 suspend;
alter task task_2 suspend;
alter task task_3 suspend;
alter task task_4 suspend;
alter task task_5 suspend;

DROP TASK IF EXISTS task_1;
DROP TASK IF EXISTS task_2;
DROP TASK IF EXISTS task_3;
DROP TASK IF EXISTS task_4;
DROP TASK IF EXISTS task_5;

CREATE or replace TASK task_1
  AS SELECT 1;

CREATE or replace TASK task_2
  AFTER task_1
  AS SELECT 1;

CREATE or replace TASK task_3
  AFTER task_2
  AS SELECT 1;

CREATE or replace TASK task_4
  AFTER task_3
  AS SELECT 1;

CREATE or replace TASK task_5
  AFTER task_4
  AS SELECT 1;


-- Newly created or cloned tasks are created suspended
alter task task_2 resume;
alter task task_3 resume;
alter task task_4 resume;
alter task task_5 resume;
alter task task_1 resume;

execute task task_1;
execute task task_1 retry last;



---------------------------------------------------------------
create table ngoctt (
    crush number
);

drop task task_ngoctt;
CREATE or replace TASK task_ngoctt
  AS 
  begin
    insert into ngoctt values (1);
    SELECT 2/0;
    insert into ngoctt values (2);
    insert into ngoctt values (3);
  end;

execute task task_ngoctt;
execute task task_ngoctt retry last;

select * from ngoctt;
