create database quan_ly_hang_hoa

use quan_ly_hang_hoa

create table khachhang (
makh nvarchar(50) not null primary key,
tenkh nvarchar(50) not null,
diachi nvarchar(50) not null,
dienthoai nvarchar(50) not null,
gioitinh nvarchar(50) not null
)

create table mathang (
mamh nvarchar(50) not null primary key,
tenmh nvarchar(50) not null,
dongia money not null
)

create table dondh (
madh nvarchar(50) not null primary key,
ngaydh date not null,
ngaygh date not null,
makh nvarchar(50) not null foreign key references khachhang(makh)
)

create table chitietdh (
madh nvarchar(50) not null foreign key references dondh(madh),
mamh nvarchar(50) not null foreign key references mathang(mamh),
soluong int not null,
primary key (madh, mamh)
)

-- câu 2:
-- khachhang
create proc sp_khachhang
@makh nvarchar(50),
@tenkh nvarchar(50),
@diachi nvarchar(50),
@dienthoai nvarchar(50),
@gioitinh nvarchar(50)
as
if @makh is null and @tenkh is null and @diachi is null and @dienthoai is null and @gioitinh is null
print N'Không được để trống'
else
insert into khachhang values (@makh, @tenkh, @diachi, @dienthoai, @gioitinh)
 
exec sp_khachhang 'kh1', N'Dương Hồng Thái', N'Hà Nội', '0966250199', N'Nam'
exec sp_khachhang 'kh2', N'Lê Thanh Huyền', N'Hà Nội', '01247622843', N'Nữ'
exec sp_khachhang 'kh3', N'Nguyễn Văn Cường', N'Hà Nội', '0978225086', N'Nam'

select * from khachhang

-- mathang
create proc sp_mathang
@mamh nvarchar(50),
@tenmh nvarchar(50),
@dongia money
as
if @mamh is null and @tenmh is null and @dongia is null
print N'Không được để trống'
else
insert into mathang values (@mamh, @tenmh, @dongia)

exec sp_mathang 'mh1', N'Gạo', N'20000'
exec sp_mathang 'mh2', N'Áo len', 30000
exec sp_mathang 'mh3', N'Quần bò', 10000

select*from mathang

-- dondh
create proc sp_dondh
@madh nvarchar(50),
@ngaydh date,
@ngaygh date,
@makh nvarchar(50)
as
if @madh is null and @ngaydh is null and @ngaygh is null and @makh is null
print N'Không được để trống'
else
insert into dondh values (@madh, @ngaydh, @ngaygh, @makh)

exec sp_dondh 'dh1', '5/15/2018', '5/20/2018', 'kh2'
exec sp_dondh 'dh2', '6/20/2018', '6/25/2018', 'kh1'
exec sp_dondh 'dh3', '2/10/2018', '2/20/2018', 'kh3'

select*from dondh

-- chitietdh
create proc sp_chitietdh
@madh nvarchar(50),
@mamh nvarchar(50),
@soluong int
as
if @madh is null and @mamh is null and @soluong is null
print N'Không được để trống'
else
insert into chitietdh values (@madh, @mamh, @soluong)

exec sp_chitietdh 'dh2', 'mh1', 50
exec sp_chitietdh 'dh3', 'mh3', 20
exec sp_chitietdh 'dh1', 'mh2', 30

select *from chitietdh

-- Câu 3:
create function fn_bai3
(
@makh nvarchar(50),
@tenkh nvarchar(50),
@diachi nvarchar(50),
@dienthoai nvarchar(50),
@gioitinh nvarchar(50)
)
returns nvarchar(50)
begin
return (
select makh from khachhang
where makh = @makh and tenkh = @tenkh and diachi = @diachi and dienthoai = @dienthoai and gioitinh = @gioitinh
)
end 

select dbo.fn_bai3 ('kh1', N'Dương Hồng Thái', N'Hà Nội', '0966250199', N'Nam')

-- Câu 4:
create view bai4_w
as
select top 2 tenkh, tenmh, ngaydh, ngaygh, soluong, dongia, dongia*soluong as 'thanhtien' from mathang
join chitietdh on mathang.mamh = chitietdh.mamh
join dondh on chitietdh.madh = dondh.madh
join khachhang on dondh.makh = khachhang.makh
order by thanhtien desc

select * from bai4_w
