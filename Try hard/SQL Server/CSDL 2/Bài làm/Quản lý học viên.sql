create database lab_7

use lab_7

create table sinhvien (
masv nvarchar(50) not null primary key,
hoten nvarchar(50) not null,
ngaysinh date not null,
gioitinh nvarchar(50) not null,
lop nvarchar(50) not null
)

create table monhoc (
mamonhoc nvarchar(50) not null primary key,
tenmonhoc nvarchar(50) not null,
sotinchi int not null
)

create table diem (
masv nvarchar(50) not null foreign key references sinhvien(masv),
mamonhoc nvarchar(50) not null foreign key references monhoc(mamonhoc),
diemlan1 decimal(5,1) not null,
diemlan2 decimal(5,1) not null,
primary key (masv, mamonhoc)
)

-- câu 2
create proc sp_sinhvien
@masv nvarchar(50),
@hoten nvarchar(50),
@ngaysinh date,
@gioitinh nvarchar(50),
@lop nvarchar(50)
as
if @masv is null and @hoten is null and @ngaysinh is null and @gioitinh is null and @lop is null
print N'Không được để trống'
else
insert sinhvien values (@masv, @hoten, @ngaysinh, @gioitinh, @lop)

exec sp_sinhvien 'sv1', N'Dương Hồng Thái', '11/24/1997', N'Nam', 'pt14201ud'
exec sp_sinhvien 'sv2', N'Lê Thanh Huyền', '3/12/1997', N'Nữ', 'pt14202ud'
exec sp_sinhvien 'sv3', N'Nguyễn Văn Cường', '5/6/1997', N'Nam', 'pt14201ud'

select*from sinhvien

create proc sp_monhoc
@mamonhoc nvarchar(50),
@tenmonhoc nvarchar(50),
@sotinchi int
as
if @mamonhoc is null and @tenmonhoc is null and @sotinchi is null
print N'Không được để trống'
else
insert monhoc values (@mamonhoc, @tenmonhoc, @sotinchi)

exec sp_monhoc 'mh1', N'Lập trình java', 5
exec sp_monhoc 'mh2', N'SQL', 3
exec sp_monhoc 'mh3', N'Tiếng anh', 8

select*from monhoc

create proc sp_diem
@masv nvarchar(50),
@mamonhoc nvarchar(50),
@diemlan1 decimal(5,1),
@diemlan2 decimal(5,1)
as
if @masv is null and @mamonhoc is null and @diemlan1 is null and @diemlan2 is null
print N'Không được để trống'
else
insert into diem values (@masv, @mamonhoc, @diemlan1, @diemlan2)

exec sp_diem 'sv1', 'mh1', 7, 4
exec sp_diem 'sv2', 'mh2', 6, 9
exec sp_diem 'sv3', 'mh3', 2, 9

select * from diem

-- câu 3
create function fn_cau3
(
@masv nvarchar(50),
@hoten nvarchar(50),
@ngaysinh date,
@gioitinh nvarchar(50),
@lop nvarchar(50)
)
returns nvarchar(50)
begin 
return (select masv from sinhvien
where masv = @masv and hoten = @hoten and ngaysinh = @ngaysinh and gioitinh = @gioitinh and lop = @lop)
end

select dbo.fn_cau3 ('sv1', N'Dương Hồng Thái', '11/24/1997', N'Nam', 'pt14201ud')

-- câu 4
create view cau4_w
as
select diem.masv, hoten, tenmonhoc, diemlan1, diemlan2 from diem 
join sinhvien on sinhvien.masv = diem.masv
join monhoc on monhoc.mamonhoc = diem.mamonhoc
order by diemlan1, diemlan2 desc
order by diemlan2 desc

select top 2 diem.masv, hoten, tenmonhoc, diemlan2 from diem 
join sinhvien on sinhvien.masv = diem.masv
join monhoc on monhoc.mamonhoc = diem.mamonhoc
order by diemlan2 desc

select *from cau4_w


-- câu 5
create proc sp_cau5
@nhapdiem decimal(5,1)
as
begin try
begin tran
declare @bang table
(masv nvarchar(50), dtb nvarchar(50))
insert @bang
select masv, (diemlan1 + diemlan2)/2 from diem
where (diemlan1 + diemlan2)/2 > @nhapdiem
delete from diem
where masv in (select masv from @bang) 

delete from sinhvien
where masv in (select masv from @bang)
commit tran
end try
begin catch
rollback
end catch

exec sp_cau5 1
drop proc sp_cau5

select * from diem
select *from sinhvien
select *from monhoc

use ass
go
drop database lab_7
go
