create database qltv
go
use qltv
go

create table doc_gia(
ma_so_thanh_vien int not null primary key,
ten nvarchar(50) not null,
sdt varchar(50) not null,
dia_chi nvarchar(50) not null,
gioi_tinh nvarchar(50) not null,
ngay_sinh date not null
)
go

create table tac_gia(
ma_tac_gia int not null primary key,
ten_tac_gia nvarchar(50) not null
)
go

create table the_loai(
ma_the_loai int not null primary key,
ten_the_loai nvarchar(50) not null,
)
go

create table nha_xuat_ban(
ma_nxb int not null primary key,
ten_nxb nvarchar(50) not null,
)
go


create table sach(
ma_sach int not null primary key,
ten_sach nvarchar(50) not null,
nam_xuat_ban int not null,
so_trang int not null,
so_luong int not null,
ma_nxb int foreign key references nha_xuat_ban(ma_nxb),
ma_tac_gia int foreign key references tac_gia(ma_tac_gia),
ma_the_loai int foreign key references the_loai(ma_the_loai),
)
go

create table the_muon(
ma_the_muon int not null primary key,
ngay_muon date not null,
ma_so_thanh_vien int foreign key references doc_gia(ma_so_thanh_vien),
)
go

create table sach_the_muon(
ma_sach int not null foreign key references sach(ma_sach),
ma_the_muon int not null foreign key references the_muon(ma_the_muon),
so_luong int not null,
primary key(ma_sach,ma_the_muon),
)
go
