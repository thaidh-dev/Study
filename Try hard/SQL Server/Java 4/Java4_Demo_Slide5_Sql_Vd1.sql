create database java4_demo_slide5_sql_vd1
go
use java4_demo_slide5_sql_vd1
go

create table users (
username nvarchar(50) not null primary key,
pass nvarchar(50) not null,
lastname nvarchar(50) not null,
isAdmin bit not null
)

insert into users values
('nam', '123', 'van nam', 1),
('long', '123', 'van long', 0),
('van', '123', 'thi van', 1),
('cuong', '123', 'van cuong', 0),
('huyen', '123', 'thi huyen', 1),
('thai', '123', 'van thai', 1),
('nghia', '123', 'van nghia', 0)

select * from users
