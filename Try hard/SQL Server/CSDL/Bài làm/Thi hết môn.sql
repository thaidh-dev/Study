create database thi_het_mon

use thi_het_mon

create table dia_diem(
ma_dia_diem nvarchar(50) not null primary key,
ten_dia_diem nvarchar(50) not null,
)

insert into dia_diem values
	('DD01', N'Miền Đông Bắc'), 
	('DD02', N'Miền Tây Bắc'),
	('DD03', N'Miền Trung'),
	('DD04', N'Tây Nguyên'),
	('DD05', N'Nam Trung Bộ'),
	('DD06', N'Hải Đảo')

create table chuc_vu(
ma_chuc_vu nvarchar(50) not null primary key,
ten_chuc_vu nvarchar(50) not null,
phu_cap int not null,
)

insert into chuc_vu values
	('CV01', N'Giám đốc', '5000000'),
	('CV02', N'Phó giám đốc', '4000000'),
	('CV03', N'Trưởng phòng', '3000000'),
	('CV04', N'Tổ trường', '1500000'),
	('CV05', N'Nhân viên', '500000')

create table phong_ban(
ma_phong_ban nvarchar(50) not null primary key,
ten_phong_ban nvarchar(50) not null,
)

insert into phong_ban values
	('PB01', N'Kế toán'),
	('PB02', N'Hành chính'),
	('PB03', N'Phát triển'),
	('PB04', N'Kế hoạch'),
	('PB05', N'Kinh doanh'),
	('PB06', N'Công trình')

create table nhan_vien(
ma_nhan_vien nvarchar(50) not null primary key,
ho_dem nvarchar(50) not null,
ten nvarchar(50) not null,
gioi_tinh nvarchar(50) not null,
ngay_sinh date not null,
dia_chi nvarchar(50) not null,
he_so_luong decimal not null,
ma_phong_ban nvarchar(50) not null foreign key references phong_ban(ma_phong_ban),
ma_chuc_vu nvarchar(50) not null foreign key references chuc_vu(ma_chuc_vu),
)

insert into nhan_vien values
	('NV0001', N'Đỗ Thị', N'Tèo', N'Nữ', '1/11/1992', N'Hà Nội', '2.34', 'PB01', 'CV04'),
	('NV0002', N'Dương Hồng', N'Thái', N'Nam', '11/24/1997', N'Hà Nội', '3.34', 'PB03', 'CV05'),
	('NV0003', N'Vũ Thanh', N'Sơn', N'Nam', '8/23/1995', N'Hà Nội', '2.65', 'PB02', 'CV03'),
	('NV0004', N'Đỗ Trung', N'Nghĩa', N'Nam', '4/13/1996', N'Hà Nội', '3.13', 'PB06', 'CV03'),
	('NV0005', N'Đoàn Việt', N'Tiến', N'Nam', '6/3/1997', N'Hà Tây', '4.1', 'PB05', 'CV02'),
	('NV0006', N'Lê Thanh', N'Huyền', N'Nữ', '1/29/1997', N'Hà Nội', '2.3', 'PB03', 'CV04'),
	('NV0007', N'Nguyên Thanh', N'Xuân', N'Nữ', '3/27/1997', N'Hà Nội', '3.5', 'PB02', 'CV01'),
	('NV0008', N'Nguyễn Văn', N'Cường', N'Nam', '4/9/1997', N'Hà Nội', '1.5', 'PB06', 'CV05'),
	('NV0009', N'Vũ Hồng', N'Duy', N'Nam', '5/6/1996', N'Hà Nội', '2.7', 'PB04', 'CV02'),
	('NV0010', N'Nguyễn Đức', N'Hoàn', N'Nam', '5/7/1996', N'Hà Nội', '3.9', 'PB04', 'CV01')

create table du_an(
ma_du_an nvarchar(50) not null primary key,
ten_du_an nvarchar(50) not null,
ngay_bat_dau date not null,
ngay_ket_thuc_du_dinh date not null,
ngay_ket_thuc_thuc_te date,
check(ngay_ket_thuc_du_dinh > ngay_bat_dau and ngay_ket_thuc_thuc_te > ngay_bat_dau),
trang_thai nvarchar(50) not null,
ma_dia_diem nvarchar(50) not null foreign key references dia_diem(ma_dia_diem),
)

insert into du_an(ma_du_an, ten_du_an, ngay_bat_dau, ngay_ket_thuc_du_dinh, trang_thai,ma_dia_diem) values
	('DA001', N'Xây dựng trường Poly', '9/13/2018', '9/13/2019', N'Chưa hoàn thành', 'DD01'),
	('DA002', N'Đốt trường', '5/6/2018', '5/6/2019', N'Chưa hoàn thành', 'DD03'),
	('DA003', N'Đốt bệnh viện', '5/8/2017', '3/4/2019', N'Chưa hoàn thành', 'DD05'),
	('DA004', N'Đốt nhà tang lễ', '6/2/2018', '6/2/2019', N'Chưa hoàn thành', 'DD02')
insert into du_an values
	('DA005', N'Đốt đồn cảnh sát', '4/3/2018', '4/3/2019', '3/5/2019', N'Hoàn thành', 'DD04'),
	('DA006', N'Đốt cầu', '9/12/2017', '9/12/2018', '3/4/2018', N'Hoàn thành', 'DD06'),
	('DA007', N'Đốt trạm xăng', '6/7/2018', '6/7/2019', '9/8/2019', N'Hoàn thành', 'DD01'),
	('DA008', N'Đốt chùa', '3/7/2016', '3/7/2017', '2/6/2017', N'Tạm ngưng', 'DD02'),
	('DA009', N'Đốt ao làng', '4/5/2015', '4/5/2017', '1/9/2017', N'Tạm ngưng', 'DD03'),
	('DA010', N'Đốt vườn', '1/9/2009', '1/9/2010', '3/4/2009', N'Tạm ngưng', 'DD04')

create table nhan_vien_du_an(
ma_nhan_vien nvarchar(50) not null foreign key references nhan_vien(ma_nhan_vien),
ma_du_an nvarchar(50) not null foreign key references du_an(ma_du_an),
ngay_tham_gia date not null,
ngay_ket_thuc date,
check(ngay_ket_thuc > ngay_tham_gia),
primary key(ma_nhan_vien, ma_du_an),
) 

insert into nhan_vien_du_an(ma_nhan_vien, ma_du_an, ngay_tham_gia) values
	('NV0001', 'DA002', '9/13/2018'),
	('NV0002', 'DA002', '5/6/2018'),
	('NV0003', 'DA003', '5/8/2017'),
	('NV0004', 'DA004', '1/7/2018'),
	('NV0005', 'DA005', '7/2/2018'),
	('NV0006', 'DA006', '9/12/2017'),
	('NV0007', 'DA007', '6/7/2018'),
	('NV0008', 'DA008', '3/7/2016'),
	('NV0009', 'DA009', '4/5/2015'),
	('NV0010', 'DA010', '1/9/2009'),
	('NV0001', 'DA010', '1/9/2009'),
	('NV0002', 'DA009', '4/5/2015'),
	('NV0003', 'DA008', '3/7/2016'),
	('NV0004', 'DA007', '6/7/2018'),
	('NV0005', 'DA006', '9/12/2017'),
	('NV0006', 'DA005', '7/2/2018'),
	('NV0007', 'DA004', '1/7/2018'),
	('NV0008', 'DA003', '5/8/2017'),
	('NV0009', 'DA002', '5/6/2018'),
	('NV0010', 'DA001', '9/13/2018')
insert into nhan_vien_du_an values 
	('NV0001', 'DA001', '11/11/2018', '12/12/2018'),
	('NV0002', 'DA003', '5/8/2017', '9/12/2017'),
	('NV0003', 'DA004', '1/7/2018', '3/19/2018'),
	('NV0004', 'DA005', '7/2/2018', '8/13/2018'),
	('NV0005', 'DA007', '6/7/2018', '8/16/2018'),
	('NV0006', 'DA002', '5/6/2018', '7/20/2018'),
	('NV0007', 'DA008', '3/7/2016', '5/9/2016'),
	('NV0008', 'DA009', '4/5/2015', '9/5/2015'),
	('NV0009', 'DA010', '1/9/2009', '1/16/2009'),
	('NV0010', 'DA006', '9/12/2017', '9/29/2017')

select*from nhan_vien_du_an
select*from nhan_vien
select*from du_an
select*from dia_diem
select*from chuc_vu
select*from phong_ban

drop table nhan_vien_du_an
drop table nhan_vien
drop table du_an
drop table dia_diem
drop table chuc_vu
drop table phong_ban


