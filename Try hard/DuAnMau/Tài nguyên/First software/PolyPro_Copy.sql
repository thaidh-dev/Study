use master
go
drop database PolyPro
go

create database PolyPro
go
use PolyPro
go

create table NhanVien (
MaNV int identity(1,1) primary key,
TaiKhoan varchar(10) not null unique,
MatKhau varchar(20) default '123',
HoTen nvarchar(50) not null,
VaiTro bit default(0)
)
go

insert into NhanVien values
('thaidh', '123', N'Dương Hồng Thái', 1),
('tuannv', '123', N'Nguyễn Văn Tuấn', 0),
('namnv', '123', N'Nguyễn Văn Nam', 0),
('nghiadt', '123', N'Đỗ Trung Nghĩa', 0),
('trangnt', '123', N'Nguyễn Thị Trang', 0),
('anhcnt', '123', N'Cao Ngọc Tuấn Anh', 0),
('hailt', '123', N'Lý Trung Hải', 0)
go

create table ChuyenDe (
MaCD int identity(1,1) primary key,
TenCD nvarchar(50) not null unique,
HocPhi money not null,
ThoiLuong int not null,
MoTa nvarchar(255) null,
check(HocPhi >= 0 and ThoiLuong >= 0)
)
go

insert into ChuyenDe values
(N'Lập trình java cơ bản', 5000000, 50, ''),
(N'Photoshop', 3000000, 50, ''),
(N'Quản trị cơ sở dữ liệu', 7000000, 50, ''),
(N'Điện toán đám mây', 10000000, 50, '')
go

create table KhoaHoc (
MaKH int identity(1,1) primary key,
MaCD int not null foreign key references ChuyenDe(MaCD),
NgayKG date not null,
HanDK date not null,
GhiChu nvarchar(255) null,
check (handk < ngaykg)
)
go

insert into KhoaHoc(MaCD, NgayKG, HanDK) values
(1, '2015/6/30', '2015/6/20'),
(2, '2015/6/30', '2015/6/20'),
(3, '2015/6/30', '2015/6/20'),
(1, '2016/6/30', '2016/6/20'),
(2, '2016/6/30', '2016/6/20'),
(3, '2016/6/30', '2016/6/20'),
(1, '2017/6/30', '2017/6/20'),
(2, '2017/6/30', '2017/6/20'),
(3, '2017/6/30', '2017/6/20'),
(1, '2018/6/30', '2018/6/20'),
(2, '2018/6/30', '2018/6/20'),
(3, '2018/6/30', '2018/6/20'),
(1, '2019/7/30', '2019/7/20'),
(1, '2019/8/30', '2019/8/20'),
(2, '2019/7/30', '2019/7/20'),
(2, '2019/8/30', '2019/8/20'),
(3, '2019/7/30', '2019/7/20'),
(3, '2019/8/30', '2019/8/20'),
(1, '2019/9/30', '2019/6/10')
go 

create function fn_select_khoahoc()
returns table
return 
(
	select makh, 
		khoahoc.macd, 
		tencd, 
		convert(varchar, ngaykg, 105) ngaykgconverted,
		convert(varchar, handk, 105) handkIsConverted, 
		handk,
		ghichu,
		ngaykg
	from khoahoc
	inner join chuyende on khoahoc.macd = chuyende.macd
)
go

create proc sp_insert_khoahoc
(
	@macd int,
	@ngaykg varchar(50),
	@handk varchar(50),
	@ghichu nvarchar(255)
)
as
	insert into KhoaHoc values 
(
	@macd, 
	convert(varchar, convert(date, @ngaykg, 105), 126),
	convert(varchar, convert(date, @handk, 105), 126),
	@ghichu
)
go

create proc sp_update_khoahoc
(
	@makh int,
	@ngaykg varchar(50),
	@handk varchar(50),
	@ghichu nvarchar(255)
)
as
	update KhoaHoc set 
		NgayKG = convert(varchar, convert(date, @ngaykg, 105), 126),
		handk = convert(varchar, convert(date, @handk, 105), 126),
		ghichu = @ghichu
	where makh = @makh
go

create function fn_HanDK(@makh int)
returns date
as begin
	return (select handk from khoahoc where makh = @makh)
end
go 

create table NguoiHoc (
MaNH int identity(1,1) primary key,
HoTen nvarchar(50) not null,
GioiTinh bit not null,
NgaySinh date not null,
DienThoai varchar(10) not null unique,
Email varchar(50) not null unique,
GhiChu nvarchar(255) null
)
go

insert into NguoiHoc(HoTen, GioiTinh, NgaySinh, DienThoai, Email, GhiChu) values
(N'Nguyễn Thị Dung', 0, '1997-11-24', '0966250191', 'nguyenvana1@gmail.com', ''),
(N'Nguyễn Thị Linh', 0, '1997-11-24', '0966250192', 'nguyenvanb2@gmail.com', ''),
(N'Nguyễn Văn Cường', 1, '1997-11-24', '0966250193', 'nguyenvanc3@gmail.com', ''),
(N'Nguyễn Thị Hoa', 0, '1997-11-24', '0966250194', 'nguyenvand4@gmail.com', ''),
(N'Nguyễn Văn Quyết', 1, '1997-11-24', '0966250195', 'nguyenvane5@gmail.com', ''),
(N'Nguyễn Văn Nam', 1, '1997-11-24', '0966250196', 'nguyenvanf6@gmail.com', ''),
(N'Nguyễn Thị Tươi', 0, '1997-11-24', '0966250197', 'nguyenvang7@gmail.com', ''),
(N'Nguyễn Thị Trà', 0, '1997-11-24', '0966250198', '8nguyenvanh@gmail.com', ''),
(N'Nguyễn Thị Hòa', 0, '1997-11-24', '0966250199', 'n9guyenvani@gmail.com', ''),
(N'Nguyễn Văn Khánh', 1, '1997-11-24', '0966250110', 'ng10uyenvank@gmail.com', ''),
(N'Nguyễn Thị Hiền Linh', 0, '1997-11-24', '0966250111', '11nguyenvanl@gmail.com', ''),
(N'Nguyễn Văn Chiến', 1, '1997-11-24', '0966250112', '12nguyenvanm@gmail.com', ''),
(N'Nguyễn Văn Long', 1, '1997-11-24', '0966250113', '13nguyenvann@gmail.com', ''),
(N'Nguyễn Thị Ngà', 0, '1997-11-24', '0966250114', '14nguyenvano@gmail.com', ''),
(N'Nguyễn Thị Huyền', 0, '1997-11-24', '0966250115', '15nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Luyến', 0, '1997-11-24', '0966250116', '16nguyenvana@gmail.com', ''),
(N'Nguyễn Thị Nga', 0, '1997-11-24', '0966250117', '17nguyenvanb@gmail.com', ''),
(N'Nguyễn Văn Tiệp', 1, '1997-11-24', '0966250118', '18nguyenvanc@gmail.com', ''),
(N'Nguyễn Thị Hoài Anh', 0, '1997-11-24', '0966250119', '19nguyenvand@gmail.com', ''),
(N'Nguyễn Văn Việt Anh', 1, '1997-11-24', '0966250120', '20nguyenvane@gmail.com', ''),
(N'Nguyễn Văn Quang', 1, '1997-11-24', '0966250121', '21nguyenvanf@gmail.com', ''),
(N'Nguyễn Thị Thảo', 0, '1997-11-24', '0966250122', '22nguyenvang@gmail.com', ''),
(N'Nguyễn Thị Hồng Linh', 0, '1997-11-24', '0966250123', '23nguyenvanh@gmail.com', ''),
(N'Nguyễn Hồng Vân', 0, '1997-11-24', '0966250124', '24nguyenvani@gmail.com', ''),
(N'Nguyễn Văn Tuấn', 1, '1997-11-24', '0966250125', '25nguyenvank@gmail.com', ''),
(N'Nguyễn Thị Ngọc', 0, '1997-11-24', '0966250126', '26nguyenvanl@gmail.com', ''),
(N'Nguyễn Văn Dương', 1, '1997-11-24', '0966250127', '27nguyenvanm@gmail.com', ''),
(N'Nguyễn Văn Hải', 1, '1997-11-24', '0966250128', '28nguyenvann@gmail.com', ''),
(N'Nguyễn Thị Hạnh', 0, '1997-11-24', '0966250129', '29nguyenvano@gmail.com', ''),
(N'Vũ Minh Nhật', 0, '1997-11-24', '0966250130', '30nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Nhung', 0, '1997-11-24', '0966250131', '31nguyenvana@gmail.com', ''),
(N'Nguyễn Thị Minh', 0, '1997-11-24', '0966250132', '32nguyenvanb@gmail.com', ''),
(N'Nguyễn Hoàng Nam', 1, '1997-11-24', '0966250133', '33nguyenvanc@gmail.com', ''),
(N'Nguyễn Thị Linh Anh', 0, '1997-11-24', '0966250134', '34nguyenvand@gmail.com', ''),
(N'Nguyễn Lâm Tùng', 1, '1997-11-24', '0966250135', '35nguyenvane@gmail.com', ''),
(N'Nguyễn Nhật Linh', 1, '1997-11-24', '0966250136', '36nguyenvanf@gmail.com', ''),
(N'Nguyễn Thị An', 0, '1997-11-24', '0966250137', '37nguyenvang@gmail.com', ''),
(N'Nguyễn Thị Xuân', 0, '1997-11-24', '0966250138', '38nguyenvanh@gmail.com', ''),
(N'Nguyễn Khánh Huyền', 0, '1997-11-24', '0966250139', '39nguyenvani@gmail.com', ''),
(N'Nguyễn Văn Phước', 1, '1997-11-24', '0966250140', '40nguyenvank@gmail.com', ''),
(N'Nguyễn Thị Cẩm Vân', 0, '1997-11-24', '0966250141', '41nguyenvanl@gmail.com', ''),
(N'Nguyễn Công Trường', 1, '1997-11-24', '0966250142', '42nguyenvanm@gmail.com', ''),
(N'Dương Văn Ninh', 1, '1997-11-24', '0966250143', '43nguyenvann@gmail.com', ''),
(N'Đoàn Hiền Linh', 0, '1997-11-24', '0966250144', '44nguyenvano@gmail.com', ''),
(N'Đào Hiền Trang', 0, '1997-11-24', '0966250145', '45nguyenvanp@gmail.com', ''),
(N'Dương Thu Trang', 0, '1997-11-24', '0966250146', '46nguyenvanl@gmail.com', ''),
(N'Nguyễn Văn Đức', 1, '1997-11-24', '0966250147', '47nguyenvanm@gmail.com', ''),
(N'Vũ Thanh Sơn', 1, '1997-11-24', '0966250148', '48nguyenvann@gmail.com', ''),
(N'Phạm Thị Oanh', 0, '1997-11-24', '0966250149', '49nguyenvano@gmail.com', ''),
(N'Phạm Thị Chinh', 0, '1997-11-24', '0966250150', '50nguyenvanp@gmail.com', ''),
(N'Cao Ngọc Giang', 0, '1997-11-24', '0966250151', '51nguyenvanl@gmail.com', ''),
(N'Nguyễn Văn Quân', 1, '1997-11-24', '0966250152', '52nguyenvanm@gmail.com', ''),
(N'Cao Ngọc Nam', 1, '1997-11-24', '0966250153', '53nguyenvann@gmail.com', ''),
(N'Nguyễn Thị Trinh', 0, '1997-11-24', '0966250154', '54nguyenvano@gmail.com', ''),
(N'Dương Thị Hà', 0, '1997-11-24', '0966250155', '55nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Luyến', 0, '1997-11-24', '0966250156', '56nguyenvanl@gmail.com', ''),
(N'Trần Văn Trung', 1, '1997-11-24', '0966250157', '57nguyenvanm@gmail.com', ''),
(N'Trần Hữu Đăng', 1, '1997-11-24', '0966250158', '58nguyenvann@gmail.com', ''),
(N'Nguyễn Thị Ngọc Ngà', 0, '1997-11-24', '0966250159', '59nguyenvano@gmail.com', ''),
(N'Nguyễn Thị Ngân', 0, '1997-11-24', '0966250160', 'n60guyenvanp@gmail.com', ''),
(N'Nguyễn THị Chi', 0, '1997-11-24', '0966250161', '61nguyenvana@gmail.com', ''),
(N'Nguyễn Thị Bình', 0, '1997-11-24', '0966250162', '62nguyenvanb@gmail.com', ''),
(N'Nguyễn Trấn Thành', 1, '1997-11-24', '0966250163', '63nguyenvanc@gmail.com', ''),
(N'Trần Thị Hạnh', 0, '1997-11-24', '0966250164', 'n64guyenvand@gmail.com', ''),
(N'Lê Minh Anh', 1, '1997-11-24', '0966250165', 'n65guyenvane@gmail.com', ''),
(N'Nguyễn Mạnh', 1, '1997-11-24', '0966250166', '66nguyenvanf@gmail.com', ''),
(N'Dương Thị Anh', 0, '1997-11-24', '0966250167', '67nguyenvang@gmail.com', ''),
(N'Nguyễn Thị Hiền', 0, '1997-11-24', '0966250168', '68nguyenvanh@gmail.com', ''),
(N'Nguyễn Thị Yến', 0, '1997-11-24', '0966250169', '69nguyenvani@gmail.com', ''),
(N'Lê Dương Minh', 1, '1997-11-24', '0966250170', '70nguyenvank@gmail.com', ''),
(N'Nguyễn Thị Uyên', 0, '1997-11-24', '0966250171', '71nguyenvanl@gmail.com', ''),
(N'Nguyễn Phan Trúc', 1, '1997-11-24', '0966250172', '72nguyenvanm@gmail.com', ''),
(N'Nguyễn Văn Vượng', 1, '1997-11-24', '0966250173', '73nguyenvann@gmail.com', ''),
(N'Nguyễn Thị Oanh Ngọc', 0, '1997-11-24', '0966250174', '74nguyenvano@gmail.com', ''),
(N'Nguyễn Thị Phương', 0, '1997-11-24', '0966250175', '75nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Quyên', 0, '1997-11-24', '0966250176', '76nguyenvana@gmail.com', ''),
(N'Nguyễn Thị Duyên', 0, '1997-11-24', '0966250177', '77nguyenvanb@gmail.com', ''),
(N'Vũ Huy Đông', 1, '1997-11-24', '0966250178', '78nguyenvanc@gmail.com', ''),
(N'Bùi Thu Hiền', 0, '1997-11-24', '0966250179', '79nguyenvand@gmail.com', ''),
(N'Trần Xuân Ích', 1, '1997-11-24', '0966250180', '80nguyenvane@gmail.com', ''),
(N'Hoàng Đình Công', 1, '1997-11-24', '0966250181', '81nguyenvanf@gmail.com', ''),
(N'Nguyễn Thị Xuân Hà', 0, '1997-11-24', '0966250182', '82nguyenvang@gmail.com', ''),
(N'Nguyễn Thị Yến Linh', 0, '1997-11-24', '0966250183', '83nguyenvanh@gmail.com', ''),
(N'Nguyễn Thị Dung', 0, '1997-11-24', '0966250184', '84nguyenvani@gmail.com', ''),
(N'Lê Phú', 1, '1997-11-24', '0966250185', '85nguyenvank@gmail.com', ''),
(N'Nguyễn Thị Hương Giang', 0, '1997-11-24', '0966250186', '86nguyenvanl@gmail.com', ''),
(N'Phạm Hoàng Long', 1, '1997-11-24', '0966250187', '87nguyenvanm@gmail.com', ''),
(N'Nguyễn Văn Dương Minh', 1, '1997-11-24', '0966250188', '88nguyenvann@gmail.com', ''),
(N'Trần Thị Bích Phương', 0, '1997-11-24', '0966250189', '89nguyenvano@gmail.com', ''),
(N'Nguyễn Thị Vy', 0, '1997-11-24', '0966250190', '90nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Mỹ Hạnh', 0, '1997-11-24', '0966251191', '91nguyenvana@gmail.com', ''),
(N'Nguyễn Thị Linh Hương', 0, '1997-11-24', '0966251192', '92nguyenvanb@gmail.com', ''),
(N'Nguyễn Văn Giang Nam', 1, '1997-11-24', '0966251193', '93nguyenvanc@gmail.com', ''),
(N'Nguyễn Thị Cẩm Ngọc Vân', 0, '1997-11-24', '0966251194', '94nguyenvand@gmail.com', ''),
(N'Nguyễn Văn Hưng', 1, '1997-11-24', '0966251195', '95nguyenvane@gmail.com', ''),
(N'Nguyễn Văn Minh Hải', 1, '1997-11-24', '0966251196', '96nguyenvanf@gmail.com', ''),
(N'Nguyễn Thị Sen', 0, '1997-11-24', '0966251197', '97nguyenvang@gmail.com', ''),
(N'Nguyễn Thị Bảo Hân', 0, '1997-11-24', '0966251198', '98nguyenvanh@gmail.com', ''),
(N'Nguyễn Thị Mi Uyên', 0, '1997-11-24', '0966241199', '99nguyenvani@gmail.com', ''),
(N'Đỗ Minh Khang', 1, '1997-11-24', '0966251100', '100nguyenvank@gmail.com', ''),
(N'Nguyễn Mai Linh', 0, '1997-11-24', '0966251101', '101nguyenvanl@gmail.com', ''),
(N'Nguyễn Đình Khoa', 1, '1997-11-24', '0966251102', '102nguyenvanm@gmail.com', ''),
(N'Nguyễn Kim Hưng', 1, '1997-11-24', '0966251103', '103nguyenvann@gmail.com', ''),
(N'Nguyễn Hà Vân Khánh', 0, '1997-11-24', '0966251104', '104nguyenvano@gmail.com', ''),
(N'Nguyễn Thị Vân Anh', 0, '1997-11-24', '0966251105', '105nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Trúc Anh', 0, '1997-11-24', '0966251106', '106nguyenvanl@gmail.com', ''),
(N'Nguyễn Minh Hải', 1, '1997-11-24', '0966251107', '107nguyenvanm@gmail.com', ''),
(N'Nguyễn Huy Đông', 1, '1997-11-24', '0966251108', '108nguyenvann@gmail.com', ''),
(N'Nguyễn Thị Thu Hiền', 0, '1997-11-24', '0966251109', '109nguyenvano@gmail.com', ''),
(N'Nguyễn Thị Quỳnh Như', 0, '1997-11-24', '0966251110', '110nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Phương Uyên', 0, '1997-11-24', '0966251111', '111nguyenvanl@gmail.com', ''),
(N'Nguyễn Văn Bảo', 1, '1997-11-24', '0966251112', 'ngu112yenvanm@gmail.com', ''),
(N'Nguyễn Bảo Bình', 1, '1997-11-24', '0966251113', '113nguyenvann@gmail.com', ''),
(N'Nguyễn Ngọc Khánh Linh', 0, '1997-11-24', '0966251114', 'n114guyenvano@gmail.com', ''),
(N'Nguyễn Thị Bảo Anh', 0, '1997-11-24', '0966251115', '115nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Huyền Khương', 0, '1997-11-24', '0966251116', 'n116guyenvanl@gmail.com', ''),
(N'Nguyễn Văn Công Nam', 1, '1997-11-24', '0966251117', '117nguyenvanm@gmail.com', ''),
(N'Hoàng Công Việt Anh', 1, '1997-11-24', '0966251118', '118nguyenvann@gmail.com', ''),
(N'Nguyễn Thị Trinh', 0, '1997-11-24', '0966251199', '119nguyenvano@gmail.com', ''),
(N'Nguyễn Thị Xuân Anh', 0, '1997-11-24', '0966251120', '120nguyenvanp@gmail.com', ''),
(N'Nguyễn Thị Lâm', 0, '1997-11-24', '0966251121', '121nguyenvana@gmail.com', ''),
(N'Trần Trung Phú', 1, '1997-11-24', '0966251122', '122nguyenvana@gmail.com', ''),
(N'Nguyễn Thị Khánh Linh', 0, '1997-11-24', '0966251123', '123nguyenvana@gmail.com', '')
go

create table HocVien (
MaNH int not null foreign key references NguoiHoc(MaNH),
MaKH int not null foreign key references KhoaHoc(MaKH),
Diem decimal(18,1) null,
NgayDK date default getdate(),
primary key (MaNH, MaKH),
check (diem >= 0 and diem <= 10),
check (NgayDK <= dbo.fn_hanDK(MaKH))
)
go

insert into HocVien(MaNH, MaKH, Diem, NgayDK) values
(1, 1, 4, '2015-6-20'),
(2, 1, 5, '2015-6-15'),
(3, 1, 8, '2015-6-15'),
(4, 1, 2, '2015-6-15'),
(5, 1, 8, '2015-6-15'),
(7, 1, 7, '2015-6-15'),
(6, 2, 4, '2015-6-20'),
(7, 2, 8, '2015-6-20'),
(8, 2, 3, '2015-6-20'),
(9, 2, 4, '2015-6-20'),
(10, 2, 9, '2015-6-20'),
(11, 3, 2, '2015-6-20'),
(12, 3, 5, '2015-6-20'),
(13, 3, 3, '2015-6-20'),
(14, 3, 1, '2015-6-20'),
(15, 3, 7, '2015-6-20'),
(7, 3, 8, '2015-6-20'),
(16, 4, 9, '2016-6-5'),
(17, 4, 10, '2016-6-5'),
(18, 4, 3, '2016-6-5'),
(19, 4, 6, '2016-6-5'),
(20, 4, 2, '2016-6-5'),
(21, 5, 4, '2016-6-20'),
(22, 5, 5, '2016-6-15'),
(23, 5, 8, '2016-6-15'),
(24, 5, 2, '2016-6-15'),
(25, 5, 8, '2016-6-15'),
(26, 6, 4, '2016-6-20'),
(27, 6, 8, '2016-6-20'),
(28, 6, 3, '2016-6-20'),
(29, 6, 4, '2016-6-20'),
(30, 6, 9, '2016-6-20'),
(31, 7, 2, '2017-6-20'),
(32, 7, 5, '2017-6-20'),
(33, 7, 3, '2017-6-20'),
(34, 7, 1, '2017-6-20'),
(35, 7, 7, '2017-6-20'),
(36, 8, 9, '2017-6-5'),
(37, 8, 10, '2017-6-5'),
(38, 8, 3, '2017-6-5'),
(39, 8, 6, '2017-6-5'),
(40, 8, 2, '2017-6-5'),
(41, 9, 4, '2017-6-20'),
(42, 9, 5, '2017-6-15'),
(43, 9, 8, '2017-6-15'),
(44, 9, 2, '2017-6-15'),
(45, 9, 8, '2017-6-15'),
(46, 10, 4, '2018-6-20'),
(47, 10, 8, '2018-6-20'),
(48, 10, 3, '2018-6-20'),
(49, 10, 4, '2018-6-20'),
(50, 10, 9, '2018-6-20'),
(51, 11, 2, '2018-6-20'),
(52, 11, 5, '2018-6-20'),
(53, 11, 3, '2018-6-20'),
(54, 11, 1, '2018-6-20'),
(55, 11, 7, '2018-6-20'),
(50, 11, 4, '2018-6-20'),
(56, 12, 9, '2018-6-5'),
(57, 12, 10, '2018-6-5'),
(58, 12, 3, '2018-6-5'),
(59, 12, 6, '2018-6-5'),
(60, 12, 2, '2018-6-5'),
(50, 12, 7, '2018-6-20')
go

insert into HocVien(MaNH, MaKH, NgayDK) values
(6, 1, '2015-6-20'),
(15, 1, '2015-6-20'),
(8, 1, '2015-6-20'),
(9, 1, '2015-6-20'),
(10, 1, '2015-6-20'),
(11, 1, '2015-6-20'),
(12, 1, '2015-6-20'),
(61, 13, '2019-6-20'),
(62, 13, '2019-6-20'),
(63, 13, '2019-6-20'),
(64, 13, '2019-6-20'),
(65, 13, '2019-6-20'),
(66, 13, '2019-6-5'),
(67, 13, '2019-6-5'),
(68, 13, '2019-6-5'),
(69, 13, '2019-6-5'),
(70, 13, '2019-6-5'),
(71, 14, '2019-6-20'),
(72, 14, '2019-6-20'),
(73, 14, '2019-6-20'),
(74, 14, '2019-6-20'),
(75, 14, '2019-6-20'),
(76, 14, '2019-6-5'),
(77, 14, '2019-6-5'),
(78, 14, '2019-6-5'),
(79, 14, '2019-6-5'),
(80, 14, '2019-6-5'),
(50, 14, '2019-6-7'),
(81, 15, '2019-6-20'),
(82, 15, '2019-6-20'),
(83, 15, '2019-6-20'),
(84, 15, '2019-6-20'),
(85, 15, '2019-6-20'),
(86, 15, '2019-6-5'),
(87, 15, '2019-6-5'),
(88, 15, '2019-6-5'),
(89, 15, '2019-6-5'),
(90, 15, '2019-6-5'),
(91, 16, '2019-6-20'),
(92, 16, '2019-6-20'),
(93, 16, '2019-6-20'),
(94, 16, '2019-6-20'),
(95, 16, '2019-6-20'),
(96, 16, '2019-6-5'),
(97, 16, '2019-6-5'),
(98, 16, '2019-6-5'),
(99, 16, '2019-6-5'),
(100, 16, '2019-6-5'),
(101, 17, '2019-6-5'),
(102, 17, '2019-6-5'),
(103, 17, '2019-6-5'),
(104, 17, '2019-6-5'),
(105, 17, '2019-6-5'),
(106, 17, '2019-6-5'),
(107, 17, '2019-6-5'),
(108, 17, '2019-6-5'),
(109, 17, '2019-6-5'),
(110, 17, '2019-6-5'),
(111, 18, '2019-6-5'),
(112, 18, '2019-6-5'),
(113, 18, '2019-6-5'),
(114, 18, '2019-6-5'),
(115, 18, '2019-6-5'),
(116, 18, '2019-6-5'),
(117, 18, '2019-6-5'),
(118, 18, '2019-6-5'),
(119, 18, '2019-6-5'),
(120, 18, '2019-6-5')
go

create function fn_select_hocvien()
returns table
return
(
	select tencd,
		Khoahoc.makh,
		nguoihoc.manh,
		hoten,
		gioitinh,
		convert(varchar, ngaysinh, 105) as ngaysinh,
		dienthoai,
		email,
		nguoihoc.GhiChu,
		convert(varchar, NgayDK, 105) as ngaydk,
		Diem,
		NgayKG,
		convert(varchar, Ngaykg, 105) as 'ngaykgConverted'
	from NguoiHoc
	left join HocVien on nguoihoc.manh = hocvien.manh
	left join KhoaHoc on khoahoc.makh = hocvien.makh
	left join chuyende on chuyende.MaCD = khoahoc.macd
)
go

create proc sp_insert_nguoihoc_hocvien 
(
	@hoten nvarchar(50),
	@gioitinh bit,
	@ngaysinh varchar(10),
	@dienthoai varchar(10),
	@email varchar(50),
	@ghichu nvarchar(255),
	@makh int
)
as begin
	insert into nguoihoc(hoten, gioitinh, ngaysinh, dienthoai, email, ghichu) values
	(
		@hoten, @gioitinh, 
		convert(varchar, convert(date, @ngaysinh, 105), 126),
		@dienthoai, @email, @ghichu)
	insert into hocvien(manh, makh) values
	((select SCOPE_IDENTITY()), @makh)
end
go

create proc sp_update_nguoihoc
(
	@hoten nvarchar(50),
	@gioitinh bit,
	@ngaysinh varchar(10),
	@dienthoai varchar(10),
	@email varchar(50),
	@ghichu nvarchar(255),
	@manh int
)
as
	update nguoihoc set 
		hoten = @hoten,
		gioitinh = @gioitinh,
		ngaysinh = convert(varchar, convert(date, @ngaysinh, 105), 126),
		DienThoai = @dienthoai,
		email = @email,
		ghichu = @ghichu
	where manh = @manh
go

create proc sp_update_diem
(
	@diem varchar(50),
	@manh int,
	@makh int
)
as
	if @diem != ''
		update HocVien set diem = cast(@diem as decimal(18,1)) where manh = @manh and makh = @makh
	else 
		update HocVien set diem = null where manh = @manh and makh = @makh
go

/*một*/
create function fn_1 (@makh int)
returns table
return 
(
	select hoten,
		Diem,
		(case
			when diem >= 0 and diem < 3 then N'Yếu'
			when diem >= 3 and diem < 5 then N'Trung bình'
			when diem >= 5 and diem < 8 then N'Khá'
			when diem >= 8 and diem <= 10 then N'Giỏi'
		end) as 'xep loai',
		convert(varchar, ngaykg, 105) 'ngaykg' 
	from HocVien
	inner join nguoihoc on nguoihoc.manh = hocvien.manh
	inner join khoahoc on khoahoc.makh = hocvien.makh
	where khoahoc.makh = @makh and diem is not null
)
go

/*hai*/
create function fn_2 (@tencd nvarchar(50))
returns table
return
(
	select year(ngaydk) 'nam', count(hocvien.manh) 'sl', convert(varchar, count(hocvien.manh)*hocphi, 1) 'dt', count(hocvien.manh)*hocphi 'money' from HocVien
	inner join khoahoc on khoahoc.makh = hocvien.makh
	inner join ChuyenDe on chuyende.MaCD = KhoaHoc.MaCD
	where TenCD = @tencd
	group by year(ngaydk), hocphi
)
go

/*ba*/
create function fn_3(@year int, @thang int)
returns table
return
(
	select @thang 'thang', count(nguoihoc.manh) 'sl' from nguoihoc
	inner join hocvien on hocvien.MaNH = NguoiHoc.MaNH
	where YEAR(ngaydk) = @year and month(ngaydk) = @thang
)
go

create function fn_3_2(@year int)
returns table
return
(
select * from dbo.fn_3(@year, 1)
union all
select * from dbo.fn_3(@year, 2)
union all
select * from dbo.fn_3(@year, 3)
union all
select * from dbo.fn_3(@year, 4)
union all
select * from dbo.fn_3(@year, 5)
union all
select * from dbo.fn_3(@year, 6)
union all
select * from dbo.fn_3(@year, 7)
union all
select * from dbo.fn_3(@year, 8)
union all
select * from dbo.fn_3(@year, 9)
union all
select * from dbo.fn_3(@year, 10)
union all
select * from dbo.fn_3(@year, 11)
union all
select * from dbo.fn_3(@year, 12)
)
go

create proc sp_find
(
	@tencd nvarchar(50),
	@ngaykg varchar(10)
)
as
	select makh 
	from khoahoc 
	inner join chuyende on chuyende.macd = khoahoc.macd 
	where 
		tencd = @tencd
	and 
		ngaykg = convert(varchar, convert(date, @ngaykg, 105), 126)
go

-- hoàn chỉnh