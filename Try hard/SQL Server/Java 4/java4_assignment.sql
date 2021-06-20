use master
go
drop database java4_assignment
go
create database java4_assignment
go
use java4_assignment
go

create table nguoi_dung (
	id int identity(1,1) not null primary key,
	email varchar(50) not null unique,
	ho_ten nvarchar(50) not null,
	mat_khau varchar(20) not null,
	chuc_vu bit not null default 0,
	khoa bit not null default 0,
	trang_thai bit not null default 0
)
go

insert into nguoi_dung values 
('thaidhph06986@fpt.edu.vn', N'Dương Hồng Thái', 123, 0, 0, 0),
('duonghongthai07@gmail.com', N'Nguyễn Văn Nam', 123, 0, 0, 0),
('team.vina.7@gmail.com', N'Trần Trung Đức', 123, 0, 0, 0),
('admin@gmail.com', N'ADMIN', 123, 1, 0, 0)
go

create table san_pham (
	ma_san_pham int identity(1,1) not null primary key,
	ten_san_pham nvarchar(50) not null unique,
	gia money not null,
	hinh nvarchar(50) not null,
	so_luong int not null default 1,
	check (so_luong >= 0)
)
go

insert into san_pham(ten_san_pham, gia, hinh, so_luong) values
(N'Căn hộ Pent House Tầng 16', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'Bán căn hộ 2PN tại Pearl Plaza', 250000000, N'Hinh/banChay2.jpg', 3),
(N'Nhà phố đẹp 4 tầng hiện đại', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'Mẫu thiết kế nhà phố đẹp 2 tầng hiện đại', 500000000, N'Hinh/banChay4.jpg', 0),
(N'Nhà phố đẹp 4 tầng hiện đại tiện nghi', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'Bán căn hộ Pent House tầng 10 tiện nghi', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a1', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b2', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c3', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd4', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e5', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f6', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a7', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b8', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c9', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd10', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e11', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f12', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a13', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b14', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c15', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd16', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e17', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f18', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a19', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b20', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c21', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd22', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e23', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f24', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a25', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b26', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c27', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd28', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e29', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f30', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a31', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b32', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c33', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd34', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e35', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f36', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a37', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b38', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c39', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd40', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e41', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f42', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a43', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b44', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c45', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd46', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e47', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f48', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a49', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b50', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c51', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd52', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e53', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f54', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a55', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b56', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c57', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd58', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e59', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f60', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a61', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b62', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c63', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd64', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e65', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f66', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a67', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b68', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c69', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd70', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e71', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f72', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a73', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b74', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c75', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd76', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e77', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f78', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a79', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b80', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c81', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'd82', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e83', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f84', 2700000000, N'Hinh/banChay6.jpg', 4),
(N'a85', 3200000000, N'Hinh/bán chạy 1.png', 0),
(N'b86', 250000000, N'Hinh/banChay2.jpg', 3),
(N'c87', 5000000000, N'Hinh/banChay3.jpg', 1),
(N'88', 500000000, N'Hinh/banChay4.jpg', 0),
(N'e89', 5000000000, N'Hinh/banChay5.jpg', 2),
(N'f90', 2700000000, N'Hinh/banChay6.jpg', 4)
go

create table phieu_mua (
	id int identity(1,1) not null primary key,
	ma_phieu varchar(50) not null default 'p',
	trang_thai bit default 1,
	email varchar(50) not null foreign key references nguoi_dung(email)
)
go

insert into phieu_mua(ma_phieu, trang_thai, email) values 
('p1', 0, 'thaidhph06986@fpt.edu.vn'),
('p2', 0, 'thaidhph06986@fpt.edu.vn'),
('p3', 0, 'thaidhph06986@fpt.edu.vn'),
('p4', 0, 'duonghongthai07@gmail.com'),
('p5', 0, 'duonghongthai07@gmail.com'),
('p6', 0, 'duonghongthai07@gmail.com'),
('p7', 1, 'duonghongthai07@gmail.com')
go

create function fn_so_luong_hang_trong_kho(@ma_san_pham varchar(50))
returns int
as begin
	return (
		select so_luong from san_pham
		where ma_san_pham = @ma_san_pham
	)
end
go

create table phieu_mua_chi_tiet (
	id int not null foreign key references phieu_mua(id),
	ma_san_pham int not null foreign key references san_pham(ma_san_pham),
	so_luong_da_mua int not null,
	primary key (id, ma_san_pham),
	check (so_luong_da_mua > 0 and so_luong_da_mua <= dbo.fn_so_luong_hang_trong_kho(ma_san_pham)),
)
go

insert into phieu_mua_chi_tiet values
(1, 2, 1),
(1, 5, 1),
(1, 3, 1),
(2, 6, 1),
(3, 2, 1),
(4, 6, 1),
(5, 2, 1),
(6, 5, 1),
(6, 3, 1),
(7, 2, 1),
(7, 5, 1)
go

create proc sp_phan_trang(@from int, @to int)
as begin
	select * from 
		(
			select top (@to)
				ma_san_pham, 
				ten_san_pham, 
				substring(convert(varchar, gia, 1), 0, len(convert(varchar, gia, 1)) - 2) gia, 
				hinh, 
				so_luong,
				ROW_NUMBER() over(order by (select(null))) as stt
			from san_pham
		) as t1
	where t1.[stt] >= @from
end
go

create proc sp_tong_so_trang
as begin
	declare @tong_so_trang int
	set @tong_so_trang = (select ceiling((cast(count(ma_san_pham) as decimal(18, 1)))/7) from san_pham)
	if @tong_so_trang = 0
		select 1
	else 
		select @tong_so_trang
end
go

/*
select * from nguoi_dung
select * from san_pham
select dbo.fn_so_luong_hang_trong_kho(2)
exec sp_phan_trang 2, 15
exec sp_tong_so_trang
*/































