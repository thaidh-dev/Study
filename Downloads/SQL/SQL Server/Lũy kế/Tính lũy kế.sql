use master
go

if db_id('tinh_luy_ke') is not null 
	drop database tinh_luy_ke
go

if db_id('tinh_luy_ke') IS NULL
	create database tinh_luy_ke
go

use tinh_luy_ke
go

create table bao_cao (
	ngay_thang date not null,
	doanh_thu int not null
)
go

insert into bao_cao values 
('2006-10-26', 12000),
('2006-10-28', 2500),
('2006-10-31', -15000),
('2006-11-03', 34000),
('2006-11-04', -5000),
('2006-11-06', 7200),
('2006-11-11', 11000)
go

SELECT ngay_thang, doanh_thu, SUM(doanh_thu) OVER (ORDER BY ngay_thang) AS luy_ke FROM bao_cao

SELECT ngay_thang, 
	A1.doanh_thu, 
	(SELECT SUM(doanh_thu) FROM bao_cao A2 WHERE A1.ngay_thang >= A2.ngay_thang) AS luy_ke 
FROM bao_cao A1
ORDER BY ngay_thang











