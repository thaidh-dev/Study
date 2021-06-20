create database thi

use thi

create table hang (
ma_hang nvarchar(50) not null primary key,
ten nvarchar(50) not null,
mo_ta nvarchar(50) not null
)

insert into hang values
('s01', 'ao', 'kha dep'),
('s02', 'o to', 'kha dep'),
('s03', 'xe may', 'kha dep'),
('s04', 'xe dap', 'kha dep'),
('s05', 'tui', 'kha dep'),
('s06', 'vi', 'kha dep'),
('s07', 'quan', 'kha dep')


select * from hang

drop table sach

drop database thi
