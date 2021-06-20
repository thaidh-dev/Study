create database qlhh
go
use qlhh
go

create table loai_hang(
ma_loai_hang int not null primary key,

ten_loai_hang nvarchar(50) not null,
)
go

create table nha_san_xuat(
ma_nsx int not null primary key,
ten_nsx nvarchar(50) not null,
)
go

create table khach_hang(
ma_khach_hang int not null primary key,
ten nvarchar(50) not null,
sdt varchar(50) not null,
dia_chi nvarchar(50) not null,
gioi_tinh nvarchar(50) not null,
ngay_sinh date not null,
)
go

create table san_pham(
ma_san_pham int not null primary key,
ten nvarchar(50) not null,
gia_nhap money not null,
so_luong int not null,
don_vi_tinh int not null,
ngay_nhap date not null,
ma_nsx int foreign key references nha_san_xuat(ma_nsx),
ma_loai_hang int foreign key references loai_hang(ma_loai_hang)
)
go

create table hoa_don(
ma_hoa_don int not null primary key,
ngay_mua_hang date not null,
ma_khach_hang int foreign key references khach_hang(ma_khach_hang),
)
go

create table san_pham_hoa_don(
ma_san_pham int foreign key references san_pham(ma_san_pham),
ma_hoa_don int foreign key references hoa_don(ma_hoa_don),
so_luong int not null,
primary key(ma_san_pham,ma_hoa_don),
)
go