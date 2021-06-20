use master
go

drop database java5_lab2_bai2
go

create database java5_lab2_bai2
go

use java5_lab2_bai2
go

create table sinh_vien (
    id int identity(1,1) primary key,
    ho_ten nvarchar(50) not null,
    diem decimal(18,1) not null,
    nganh varchar(10) not null
)
go

insert into sinh_vien(ho_ten, diem, nganh) values
(N'Dương Hồng Thái', 7, 'CNTT'),
(N'Nguyễn Văn Cường', 10, 'IT'),
(N'Lê Thanh Huyền', 7, 'VP'),
(N'Dương Văn Ninh', 8, 'CNTT'),
(N'Nguyễn Thị Dung', 2, 'TKTW'),
(N'Đào Mạnh Hải', 3, 'DH'),
(N'Lý Trung Đức', 9, 'CNTT')
go

select * from sinh_vien

