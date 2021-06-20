create database FPL_dao_tao
go
use FPL_dao_tao
go

create table users (
tai_khoan nvarchar(50) not null primary key,
mat_khau nvarchar(50) not null,
chuc_vu nvarchar(50) not null
)

insert into users values 
('admin', 'admin', 'admin'),
('gv', 'gv', 'giang vien'),
('cbdt', 'cbdt', 'can bo dao tao')

create table student (
ma_sv nvarchar(50) not null primary key,
ten nvarchar(50) not null,
email nvarchar(50) not null,
sdt nvarchar(50) not null,
gioi_tinh bit not null,
dia_chi nvarchar(50) not null,
hinh nvarchar(100) not null,
)

insert into student values
(N'Nguyễn Tiến Thành', 'Duong Hong Thai', 'duonghongthai@gmail.com', '0966250199', 1, 'Ha Noi', 'C:\Users\Admin\Desktop\Try hard\Java\Colleges\Java 3\Task\Assignment_Java3\image\anh 1.jpg'),
('av02', 'Nguyen Van Cuong', 'nguyenvancuong@gmail.com', '097487454', 1, 'Ha Noi', 'C:\Users\Admin\Desktop\Try hard\Java\Colleges\Java 3\Task\Assignment_Java3\image\anh 2.jpg'),
('bv03', 'Le Thanh Huyen', 'lethanhhuyen@gmail.com', '09648686748', 0, 'Ha Noi', 'C:\Users\Admin\Desktop\Try hard\Java\Colleges\Java 3\Task\Assignment_Java3\image\anh 3.jpg'),
('bv04', 'Dang Van Dong', 'dangvandong@gmail.com', '094784783', 1, 'Ha Noi', 'C:\Users\Admin\Desktop\Try hard\Java\Colleges\Java 3\Task\Assignment_Java3\image\anh 4.jpg'),
('cv05', 'Nguyen Thi Dung', 'nguyenthidung@gmail.com', '08563875838', 0, 'Ha Noi', 'C:\Users\Admin\Desktop\Try hard\Java\Colleges\Java 3\Task\Assignment_Java3\image\anh 5.jpg'),
('cv06', 'Vu Thanh Son', 'vuthanhson@gmail.com', '02646858374', 1, 'Ha Noi', 'C:\Users\Admin\Desktop\Try hard\Java\Colleges\Java 3\Task\Assignment_Java3\image\anh 6.jpg'),
('av07', 'Dinh Phuong Toan', 'dinhphuongtoan@gmail.com', '03646758383', 1, 'Ha Noi', 'C:\Users\Admin\Desktop\Try hard\Java\Colleges\Java 3\Task\Assignment_Java3\image\anh 7.jpg')

create table grade (
id int identity(1, 1) primary key,
ma_sv nvarchar(50) UNIQUE foreign key references student(ma_sv),
tieng_anh decimal(18, 1) not null,
tin decimal(18, 1) not null,
gdtc decimal(18, 1) not null,
)


insert into grade values
(N'Nguyễn Tiến Thành', '9.5', 6.5, 5),
('av02', 3.5, 6, 5),
('bv03', 7.5, 5, 2),
('bv04', 5.5, 5, 6),
('cv05', 2.5, 3, 9),
('cv06', 7.5, 10, 5),
('av07', 3.5, 9.5, 2)


select id, student.ma_sv, ten, tieng_anh, tin, gdtc, round((tieng_anh + tin + gdtc)/3, 2) as 'DTB' from grade inner join student on student.ma_sv = grade.ma_sv order by 'DTb' desc

select ten, student.ma_sv, tieng_anh, tin, gdtc, round((tieng_anh + tin + gdtc)/3, 2) from grade inner join student on student.ma_sv = grade.ma_sv where student.ma_sv = 'sv01'

select ten, student.ma_sv, tieng_anh, tin, gdtc, round((tieng_anh + tin + gdtc)/3, 2) from grade inner join student on student.ma_sv = grade.ma_sv

update grade set ma_sv = 'sv02' where id = 3
update grade set ma_sv = 'sv02' where id = 8
update grade set tieng_anh = 3.5 where id = 2

select ma_sv from grade

select * from users
select * from grade
select * from student

drop table grade
drop table student
drop table users