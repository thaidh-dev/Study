create database demo
go
use demo
go

create table sinh_vien(
ma_sv int identity(1,1) not null primary key,
ten_sv nvarchar(50) not null,
)
go

create table the_muon(
ma_the_muon int identity(1,1) not null primary key,
ngay_muon date not null,
ngay_tra date not null,
check(ngay_tra>ngay_muon),
ma_sv int foreign key references sinh_vien(ma_sv),
)
go

alter table the_muon
add gia_tien money not null
select*from sinh_vien
select*from the_muon
alter table the_muon
alter column gia_sach money check(gia_sach>5)

alter table the_muon 
add gia_sach money check(gia_sach>0)

alter table the_muon 
alter column gia_tien 

update the_muon set gia_tien='50000' where ma_the_muon='1'
update the_muon set gia_sach='4' where ma_the_muon='1'

alter table the_muon
add gia money check(gia>9)

alter table the_muon
alter column gia money 

update the_muon set gia='10' where ma_the_muon='1'
alter table the_muon
drop column gia_tien

create table thai(
gia_ao money check(gia_ao>50) primary key,
gia_quan money check(gia_quan>40),
so_luong int check(so_luong>3),
ten nvarchar(50) not null,
)
go

alter table thai

drop table thai

create table thuy(
ma_ten int identity(2,5) not null primary key,
ma_khach_hang int not null,
ten nvarchar(50) not null,
)
select*from thuy
insert into thuy values('123',N'Lmao')
insert into thuy values('456',N'Lmfao')
insert into thuy values('598',N'Rofl')

drop table thuy
