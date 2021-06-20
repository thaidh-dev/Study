create database ph06986_testgd1
go
use ph06986_testgd1
go

create table danhmuc(
maloai varchar(10) not null primary key,
tenloai nvarchar(100) null,
)
go

select*from danhmuc
insert into danhmuc values('01',N'Thể thao')
insert into danhmuc values('02',N'Giải trí')
insert into danhmuc values('03',N'Kinh tế')

create table tintuc(
matin varchar(10) not null primary key,
tieude nvarchar(255) not null,
anhdd varchar(100) not null,
tinvt nvarchar(255) null,
tinct text null,
maloai varchar(10) null foreign key references danhmuc(maloai),
)
go

select*from tintuc
insert into tintuc values('123',N'Ánh Viên đoạt huy chương vàng','ing.0001',N'Ánh Viên đạt huy chương',N'Ngày hôm qua Ánh Viên đạt huy chương','01')
insert into tintuc values('456',N'Nhóm nhac nam ra MV mới','ing.0002',N'Da Lab ra MV mới',N'Hôm qua Da Lab ra MV mới','02')
insert into tintuc values('789',N'Giá xăng tăng','ing.003',N'Giá xăng lại tăng',N'Giá xăng tăng 5000 đ/l','03')

