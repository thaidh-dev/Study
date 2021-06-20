create database java3_lab5_bai2
go
use java3_lab5_bai2
go

create table student (
	ma_sv nvarchar(50) not null primary key,
	ho_ten nvarchar(50) not null,
	email nvarchar(50) not null,
	sdt nvarchar(50) not null,
	gioi_tinh bit not null,
	dia_chi nvarchar(50) not null,
)
go

insert into student values
('SV001', N'Dương Hồng Thái', 'duonghongthai07@gmail.com', '0966250199', 1, N'Hà Nội'),
('SV002', N'Vũ Hồng Sơn','vuhongson@gmail.com','0123456789', 1, N'Hà Nội'),
('SV003', N'Nguyễn Văn Cường', 'nguyenvancuong@gmail.com', '0839757363', 1, N'Hà Nội'),
('SV004', N'Nguyễn Văn A', 'nguyenvana@gmail.com', '8594757390', 0, N'Thanh Hóa'),
('SV005', N'Nguyễn Khánh Đạt', 'nguyenkhanhdat@gmail.com', '95093759375', 1, N'Ninh Bình'),
('SV006', N'Nguyễn Văn B', 'nguyenvanb@gmail.com', '9094856362', 0, N'Lào Cai'),
('SV007', N'Nguyễn Văn C', 'nguyenvanc@gmail.com', '4774334893', 0, N'Khánh Hòa')

update student set ma_sv = 'SV001' where ma_sv = 'SV0012'

select * from student where ma_sv = 'sv0012'
drop table student




