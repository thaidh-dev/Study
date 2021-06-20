create database student
go
use student
go

create table student (
id int identity(1, 1) not null primary key,
name nvarchar(50) not null,
dia_chi nvarchar(50) not null,
parent_name nvarchar(50) not null,
phone nvarchar(50) not null,
nghe nvarchar(50) not null,	
)

insert into student values
(N'Dương Hồng L', N'Hà Nội', N'Nguyễn Thị Dung', '0966250199', N'Lập trình viên'),
(N'Dương Hồng R', N'Hà Nội', N'Nguyễn Thị U', '09676486894', N'Giáo viên'),
(N'Dương Hồng P', N'Nam Định', N'Nguyễn Thị F', '0966377583', N'Nội trợ'),
(N'Dương Hồng I', N'Nghệ An', N'Nguyễn Thị O', '4756454348', N'Trông xe'),
(N'Dương Hồng L', N'Hà Nội', N'Nguyễn Thị Dung', '0966250199', N'Xe ôm'),
(N'Nguyễn Văn K', N'Khánh Hòa', N'Nguyễn Thị W', '57884759', N'Bán trà đá'),
(N'Dương Hồng L', N'Hà Nội', N'Nguyễn Thị Dung', '0966250199', N'Bán trà đá'),
(N'Nguyễn Văn A', N'Hà Tĩnh', N'Nguyễn Thị A', '7684736844', N'Xe ôm'),
(N'Nguyễn Văn C', N'Lào Cai', N'Nguyễn Thị I', '684795749', N'Thợ xây')

select*from student

update student set name = 'Duong Hong Thai', dia_chi = 'Ha Noi' where id = 1

drop table student
