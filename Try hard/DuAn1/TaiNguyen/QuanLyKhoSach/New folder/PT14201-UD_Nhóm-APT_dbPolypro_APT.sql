/* Tạo database */

CREATE DATABASE dbPolypro_APT2

USE dbPolypro_APT2


CREATE TABLE NhanVien(
	id varchar(7) NOT NULL PRIMARY KEY,
	matKhau varchar(20) NOT NULL,
	hoTen nvarchar(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	vaiTro TINYINT NOT NULL
)

GO 

CREATE TABLE ChuyenDe(
	id VARCHAR(7) NOT NULL PRIMARY KEY,
	tenCD nvarchar(50) NOT NULL,
	hocPhi int NOT NULL,
	thoiLuong INT NOT NULL,
	MoTa NTEXT NULL
)


GO 

CREATE TABLE KhoaHoc(
	id INT IDENTITY NOT NULL PRIMARY KEY,
	maCD VARCHAR(7) NOT NULL FOREIGN KEY REFERENCES dbo.ChuyenDe(id),
	hocPhi int NOT NULL,
	thoiLuong INT NOT NULL,
	ngayKG date NOT NULL,
	ghiChu ntext NULL,
	ngayTao date NOT NULL DEFAULT GETDATE(),
	sendResult BIT NOT NULL DEFAULT 0
)

GO 

CREATE TABLE HocVien(
	id VARCHAR(7) NOT NULL PRIMARY KEY,
	hoTen nvarchar(50) NOT NULL,
	ngaySinh date NOT NULL,
	gioiTinh bit NOT NULL,
	dienThoai varchar(13) NOT NULL,
	email varchar(50) NOT NULL,
	ghiChu NTEXT NULL,
	ngayDK DATE NOT NULL		
)




GO

CREATE TABLE HocVien_KhoaHoc(
	id INT IDENTITY NOT NULL PRIMARY KEY,
	maKH INT NOT NULL,
	maHV varchar(7) NOT NULL,
	diem float NULL DEFAULT NULL,
	sendResult BIT NOT NULL DEFAULT 0
)

/*Insert dữ liệu*/
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'COM1012', N'Tin học cơ sở', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'COM1024', N'Tin học văn phòng', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'COM1032', N'Thiết lập và quản trị mạng máy tính', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'COM2012', N'Cơ sở dữ liệu', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'COM2032', N'Quản trị cơ sở dữ liệu với SQL Server', 1000, 30, N'')
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'COM2043', N'Quản trị Server', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'INF205', N'Điện toán đám mây', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'IOT101', N'Lập trình C cho Arduino', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'IOT102', N'Thiết bị điện tử IOT cơ bản', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'IOT201', N'Lập trình IOT cơ bản', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'IOT202', N'Lập trình IOT nâng cao', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB1013', N'Lập trình Java 1', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB1022', N'Lập trình Java 2', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB103', N'Lập trình Android cơ bản', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB1234', N'Chuyên đề 123', 1000, 30, N'')
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB201', N'Lập trình Android nâng cao', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB202', N'Thiết kế giao diện trên Android', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB301', N'Lập trình C++', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB305', N'Lập trình Game 2D', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB306', N'Lập trình Game đa nền tảng', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB307', N'Mobile Marketing', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB401', N'Lập trình Game 2D nâng cao', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB402', N'Lập trình server cho Android', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MOB403', N'Android Networking', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MUL1013', N'Thiết kế hình ảnh với Photoshop', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MUL317', N'Autocad 2D', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'MUL318', N'Cinema 4D', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'SOF203', N'Lập trình Java 3', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'SOF301', N'Lập trình Java 4', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'SOF302', N'Lập trình Java 5', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'SOF303', N'Kiểm thử cơ bản', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'SOF304', N'Kiểm thử nâng cao', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'SOF305', N'Strust & Hibernate', 1500, 45, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'WEB1013', N'Xây dựng trang web', 1000, 30, NULL)
INSERT [dbo].[ChuyenDe] ([id], [tenCD], [hocPhi], [thoiLuong], [MoTa]) VALUES (N'WEB1022', N'Quản trị Website', 1500, 45, NULL)

INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00001', N'Nguyễn Văn AB', CAST(N'1995-01-01' AS Date), 0, N'0987654321', N'nguyenvan@gmail.com.vn', N'', CAST(N'2019-05-25' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00002', N'Nguyễn Văn B', CAST(N'1995-01-02' AS Date), 1, N'0987654322', N'nguyenvanb@gmail.com', N'', CAST(N'2018-05-25' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00003', N'Nguyễn Văn C', CAST(N'1995-01-03' AS Date), 1, N'0987654323', N'nguyenvanc@gmail.com', N'', CAST(N'2019-05-25' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00004', N'Nguyễn Văn Đạt', CAST(N'1995-04-01' AS Date), 1, N'0987654324', N'nguyenvand@gmail.com', N'', CAST(N'2019-05-25' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00005', N'Nguyễn Trần Chiến', CAST(N'1995-01-05' AS Date), 0, N'0987532145', N'chien@gmail.com', N'', CAST(N'2019-04-30' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00006', N'a', CAST(N'1995-01-01' AS Date), 1, N'0123456789', N'sdfdf@gmail.com', NULL, CAST(N'2018-05-30' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00007', N'LỮ HUY CƯỜNG', CAST(N'1991-05-08' AS Date), 1, N'0928768265', N'HV01638@fpt.edu.vn', N'', CAST(N'2009-05-08' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00008', N'ĐỖ VĂN MINH', CAST(N'1992-10-24' AS Date), 1, N'0968095685', N'HV02037@fpt.edu.vn', N'', CAST(N'2010-10-24' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00009', N'NGUYỄN TẤN HIẾU', CAST(N'1998-09-15' AS Date), 1, N'0927594734', N'HV02771@fpt.edu.vn', N'', CAST(N'2016-09-15' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00010', N'NGUYỄN HỮU TRÍ', CAST(N'1997-10-27' AS Date), 1, N'0946984711', N'HV02867@fpt.edu.vn', N'', CAST(N'2015-10-27' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00011', N'TRẦN VĂN NAM', CAST(N'2000-06-03' AS Date), 1, N'0924774498', N'HV02930@fpt.edu.vn', N'', CAST(N'2018-06-03' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00012', N'ĐOÀN TRẦN NHẬT VŨ', CAST(N'1994-08-28' AS Date), 1, N'0912374818', N'HV02979@fpt.edu.vn', N'', CAST(N'2012-08-28' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00013', N'NGUYỄN HOÀNG THIÊN PHƯỚC', CAST(N'1993-04-04' AS Date), 1, N'0912499836', N'HV02983@fpt.edu.vn', N'', CAST(N'2011-04-04' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00014', N'HỒ HỮU HẬU', CAST(N'1993-02-08' AS Date), 1, N'0924984876', N'HV02988@fpt.edu.vn', N'', CAST(N'2011-02-08' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00015', N'PHAN TẤN VIỆT', CAST(N'1990-04-05' AS Date), 1, N'0924832716', N'HV03031@fpt.edu.vn', N'', CAST(N'2008-04-05' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00016', N'NGUYỄN CAO PHƯỚC', CAST(N'1990-01-28' AS Date), 1, N'0977117727', N'HV03046@fpt.edu.vn', N'', CAST(N'2008-01-28' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00017', N'HUỲNH THANH HUY', CAST(N'1994-09-06' AS Date), 1, N'0916436052', N'HV03080@fpt.edu.vn', N'', CAST(N'2012-09-06' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00018', N'NGUYỄN HOÀNG TRUNG', CAST(N'1991-09-02' AS Date), 1, N'0938101529', N'HV03088@fpt.edu.vn', N'', CAST(N'2009-09-02' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00019', N'ĐOÀN HỮU KHANG', CAST(N'1994-02-21' AS Date), 1, N'0945196719', N'HV03096@fpt.edu.vn', N'', CAST(N'2012-02-21' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00020', N'LÊ THÀNH PHƯƠNG', CAST(N'1993-02-21' AS Date), 1, N'0922948096', N'HV03104@fpt.edu.vn', N'', CAST(N'2011-02-21' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00021', N'PHẠM NGỌC NHẬT TRƯỜNG', CAST(N'1999-06-24' AS Date), 1, N'0994296169', N'HV03120@fpt.edu.vn', N'', CAST(N'2017-06-24' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00022', N'ĐẶNG BẢO VIỆT', CAST(N'1990-02-14' AS Date), 1, N'0917749344', N'HV03130@fpt.edu.vn', N'', CAST(N'2008-02-14' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00023', N'LÊ DUY BẢO', CAST(N'1996-08-08' AS Date), 1, N'0926714368', N'HV03134@fpt.edu.vn', N'', CAST(N'2014-08-08' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00024', N'NGUYỄN ANH TUẤN', CAST(N'1992-02-15' AS Date), 1, N'0920020472', N'HV03172@fpt.edu.vn', N'', CAST(N'2010-02-15' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00025', N'PHAN QUỐC QUI', CAST(N'1996-02-04' AS Date), 1, N'0930649274', N'HV03202@fpt.edu.vn', N'', CAST(N'2014-02-04' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00026', N'ĐẶNG LÊ QUANG VINH', CAST(N'1990-01-02' AS Date), 1, N'0920197355', N'HV03203@fpt.edu.vn', N'', CAST(N'2008-01-02' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00027', N'NGUYỄN MINH SANG', CAST(N'1995-05-02' AS Date), 1, N'0967006218', N'HV03205@fpt.edu.vn', N'', CAST(N'2013-05-02' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00028', N'TRẦM MINH MẪN', CAST(N'1997-02-09' AS Date), 1, N'0911183649', N'HV03222@fpt.edu.vn', N'', CAST(N'2015-02-09' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00029', N'NGUYỄN PHẠM MINH HÂN', CAST(N'2000-10-14' AS Date), 1, N'0983469892', N'HV03230@fpt.edu.vn', N'', CAST(N'2018-10-14' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00030', N'LƯU KIM HOÀNG DUY', CAST(N'1996-07-04' AS Date), 1, N'0938357735', N'HV03233@fpt.edu.vn', N'', CAST(N'2014-07-04' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00031', N'TRẦN QUANG VŨ', CAST(N'2000-03-10' AS Date), 1, N'0914531913', N'HV03251@fpt.edu.vn', N'', CAST(N'2018-03-10' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00032', N'BÙI NGỌC THUẬN', CAST(N'1995-01-26' AS Date), 1, N'0999794115', N'HV03304@fpt.edu.vn', N'', CAST(N'2013-01-26' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00033', N'HỒ VĂN HÀNH', CAST(N'1992-04-15' AS Date), 1, N'0912277868', N'HV03306@fpt.edu.vn', N'', CAST(N'2010-04-15' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00034', N'TRẦN VIẾT HÙNG', CAST(N'1999-02-24' AS Date), 1, N'0916050164', N'HV03308@fpt.edu.vn', N'', CAST(N'2017-02-24' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00035', N'NGUYỄN HOÀNG MINH ĐỨC', CAST(N'1996-12-11' AS Date), 1, N'0912234437', N'HV03325@fpt.edu.vn', N'', CAST(N'2014-12-11' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00036', N'PHAN THANH PHONG', CAST(N'1990-01-21' AS Date), 1, N'0937891282', N'HV03346@fpt.edu.vn', N'', CAST(N'2008-01-21' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00037', N'TRẦN VŨ LUÂN', CAST(N'1998-04-08' AS Date), 1, N'0962030316', N'HV03383@fpt.edu.vn', N'', CAST(N'2016-04-08' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00038', N'VŨ ĐỨC TUẤN', CAST(N'1993-02-24' AS Date), 1, N'0911637415', N'HV03389@fpt.edu.vn', N'', CAST(N'2011-02-24' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00040', N'TRƯƠNG THÀNH ĐẠT', CAST(N'1993-11-05' AS Date), 1, N'0991509408', N'HV03411@fpt.edu.vn', N'', CAST(N'2011-11-05' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00042', N'NGUYỄN NHẬT VĨNH', CAST(N'1999-07-09' AS Date), 1, N'0917886371', N'HV03454@fpt.edu.vn', N'', CAST(N'2018-07-09' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00043', N'NGUYỄN VĂN HUY', CAST(N'1990-01-22' AS Date), 1, N'0960620997', N'HV03472@fpt.edu.vn', N'', CAST(N'2008-01-22' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00044', N'NGUYỄN NHƯ NGỌC', CAST(N'1995-05-09' AS Date), 0, N'0912880267', N'HV03488@fpt.edu.vn', N'', CAST(N'2013-05-09' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00045', N'PHẠM THÀNH TÂM', CAST(N'2000-03-11' AS Date), 1, N'0918161783', N'HV03530@fpt.edu.vn', N'', CAST(N'2018-03-11' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00046', N'ĐINH TẤN CÔNG', CAST(N'2000-08-15' AS Date), 1, N'0918182551', N'HV03553@fpt.edu.vn', N'', CAST(N'2018-08-15' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00047', N'LÊ MINH ĐIỀN', CAST(N'1995-01-05' AS Date), 1, N'0948199564', N'HV03561@fpt.edu.vn', N'', CAST(N'2013-01-05' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00048', N'NGUYỄN THANH HIỀN', CAST(N'1991-07-09' AS Date), 1, N'0910545901', N'HV03596@fpt.edu.vn', N'', CAST(N'2009-07-09' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00049', N'LÊ PHẠM KIM THANH', CAST(N'1994-08-05' AS Date), 0, N'0924696779', N'HV03603@fpt.edu.vn', N'', CAST(N'2012-08-05' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00050', N'TRẦN ĐÌNH TRƯỜNG', CAST(N'1995-01-16' AS Date), 1, N'0941528106', N'HV03610@fpt.edu.vn', N'', CAST(N'2013-01-16' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00052', N'PHÍ ĐÌNH VIỆT HÙNG', CAST(N'1996-12-09' AS Date), 1, N'0939020097', N'HV03618@fpt.edu.vn', N'', CAST(N'2014-12-09' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00053', N'PHẠM NHẬT MINH', CAST(N'1997-07-18' AS Date), 1, N'0927771672', N'HV03638@fpt.edu.vn', N'', CAST(N'2015-07-18' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00054', N'LƯU THANH NGỌC', CAST(N'1993-12-01' AS Date), 0, N'0918358164', N'HV03640@fpt.edu.vn', N'', CAST(N'2011-12-01' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00056', N'TRẦN TUẤN ANH', CAST(N'1996-06-11' AS Date), 1, N'0914082094', N'HV03674@fpt.edu.vn', N'', CAST(N'2015-06-11' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00059', N'Nguyễn Tiến Trung', CAST(N'2001-06-13' AS Date), 1, N'0987654159', N'nguyentientrung@gmail.com', N'', CAST(N'2019-06-13' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00060', N'Nguyễn Tiến Trung', CAST(N'2001-06-13' AS Date), 1, N'0987514526', N'nguyentientrung2001@gmail.com', N'', CAST(N'2019-06-16' AS Date))
INSERT [dbo].[HocVien] ([id], [hoTen], [ngaySinh], [gioiTinh], [dienThoai], [email], [ghiChu], [ngayDK]) VALUES (N'HV00061', N'Vũ Tiến Trung', CAST(N'2001-06-16' AS Date), 1, N'0987426589', N'trungvtph07014@fpt.edu.vn', N'', CAST(N'2019-06-16' AS Date))

SET IDENTITY_INSERT [dbo].[KhoaHoc] ON 

INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (9, N'COM1012', 1000, 30, CAST(N'2018-01-01' AS Date), NULL, CAST(N'2018-01-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (10, N'COM1024', 1500, 45, CAST(N'2019-01-01' AS Date), N'', CAST(N'2019-06-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (11, N'COM1012', 1000, 30, CAST(N'2019-07-01' AS Date), N'', CAST(N'2019-06-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (25, N'COM1012', 1000, 30, CAST(N'2019-02-07' AS Date), N'', CAST(N'2019-06-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (34, N'COM1012', 1000, 30, CAST(N'2019-06-02' AS Date), N'', CAST(N'2019-06-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (47, N'COM1012', 1000, 30, CAST(N'2019-06-02' AS Date), N'', CAST(N'2019-06-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (60, N'INF205', 1000, 30, CAST(N'1900-01-01' AS Date), N'', CAST(N'2019-06-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (61, N'COM1032', 1000, 30, CAST(N'2019-06-24' AS Date), N'', CAST(N'2019-06-01' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (62, N'COM2012', 1500, 45, CAST(N'2019-06-03' AS Date), N'', CAST(N'2019-06-02' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (64, N'COM1012', 1000, 30, CAST(N'2019-07-09' AS Date), N'', CAST(N'2019-06-13' AS Date), 0)
INSERT [dbo].[KhoaHoc] ([id], [maCD], [hocPhi], [thoiLuong], [ngayKG], [ghiChu], [ngayTao], [sendResult]) VALUES (65, N'COM1012', 1000, 30, CAST(N'2019-07-08' AS Date), N'', CAST(N'2019-06-13' AS Date), 0)
SET IDENTITY_INSERT [dbo].[KhoaHoc] OFF

SET IDENTITY_INSERT [dbo].[HocVien_KhoaHoc] ON 

INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (13, 9, N'HV00001', 8, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (14, 9, N'HV00002', 7.5, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (15, 11, N'HV00001', 5.5, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (16, 10, N'HV00006', -1, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (17, 61, N'HV00001', 7.5, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (18, 61, N'HV00002', 7.6, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (19, 11, N'HV00002', 9.5, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (20, 10, N'HV00001', -1, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (23, 11, N'HV00003', 10, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (24, 11, N'HV00004', 6, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (25, 11, N'HV00005', 6, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (26, 62, N'HV00001', -1, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (27, 11, N'HV00007', 7.8, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (28, 11, N'HV00006', 8, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (29, 11, N'HV00011', 7.5, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (30, 11, N'HV00008', 5.3, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (31, 61, N'HV00003', 8.1, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (32, 62, N'HV00001', 0, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (33, 11, N'HV00013', 6, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (36, 64, N'HV00059', 7.8, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (37, 64, N'HV00060', 7.8, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (39, 64, N'HV00061', -1, 0)
INSERT [dbo].[HocVien_KhoaHoc] ([id], [maKH], [maHV], [diem], [sendResult]) VALUES (40, 61, N'HV00061', -1, 0)
SET IDENTITY_INSERT [dbo].[HocVien_KhoaHoc] OFF

INSERT [dbo].[NhanVien] ([id], [matKhau], [hoTen], [email], [vaiTro]) VALUES (N'NV00001', N'123456', N'Trần Đức Anh', N'anhtd081095@gmail.com', 1)
INSERT [dbo].[NhanVien] ([id], [matKhau], [hoTen], [email], [vaiTro]) VALUES (N'NV00002', N'123456', N'Vũ Đức Phước', N'phuocvdph06996@fpt.edu.vn', 0)
INSERT [dbo].[NhanVien] ([id], [matKhau], [hoTen], [email], [vaiTro]) VALUES (N'NV00003', N'123456', N'Vũ Tiến Trung', N'trungvtph07014@fpt.edu.vn', 0)


/* Code xem, thêm, sửa, xóa */

/* Bảng NhanVien*/
SELECT * FROM dbo.NhanVien


INSERT INTO dbo.NhanVien 
(
    id,
    matKhau,
    hoTen,
    vaiTro
)
VALUES
(   'NV00001',   -- maNV - varchar(10)
    '123456789',   -- matKhau - varchar(20)
    N'Nguyễn Văn A',  -- hoTen - nvarchar(50)
    0 -- vaiTro - bit
    )

UPDATE dbo.NhanVien SET hoTen = N'Nguyễn Văn B' WHERE id='NV00001'

DELETE dbo.NhanVien WHERE id = '1'

/* Bảng ChuyenDe*/
SELECT * FROM dbo.ChuyenDe

INSERT INTO dbo.ChuyenDe
(
    id,
    tenCD,
    hocPhi,
    thoiLuong,
    MoTa
)
VALUES
(   'SOF204',  -- id - varchar(7)
    N'Dự án mẫu', -- tenCD - nvarchar(50)
    200000,   -- hocPhi - int
    30,   -- thoiLuong - int
    N''  -- MoTa - ntext
    )

UPDATE dbo.ChuyenDe SET MoTa = N'Đây là môn học căn bản để các bạn làm quen với ngôn ngữ lập trình Java' WHERE id = 1;


DELETE dbo.ChuyenDe WHERE id = 100000

/* Bảng KhoaHoc*/
SELECT * FROM dbo.KhoaHoc

INSERT INTO  dbo.KhoaHoc
(
    maCD,
    hocPhi,
    thoiLuong,
    ngayKG,
    ghiChu,
    ngayTao
)
VALUES
(   'SOF204',         -- maCD - int
    200000,         -- hocPhi - int
    30,         -- thoiLuong - int
    GETDATE(), -- ngayKG - date
    N'',       -- ghiChu - nvarchar(255)
    GETDATE()  -- ngayTao - date
    )

UPDATE dbo.KhoaHoc SET ghiChu=N'Đây là ghi chú của khóa học 1' WHERE id = 1

DELETE dbo.KhoaHoc WHERE id = 111111

/* bảng HocVien*/
SELECT * FROM dbo.HocVien

INSERT INTO dbo.HocVien
(
    id,
    hoTen,
    ngaySinh,
    gioiTinh,
    dienThoai,
    email,
    ghiChu,
    ngayDK
)
VALUES
(   'HV00001',        -- maHV - varchar(7)
    N'Trần Văn Bốn',       -- toTen - nvarchar(50)
    '1998-12-22', -- ngaySinh - date
    1,      -- gioiTinh - bit
    '0123456789',        -- dienThoai - varchar(13)
    'tranvanbon@gmail.com',        -- email - varchar(50)
    N'Ngổ ngáo',       -- ghiChu - nvarchar(255)
    GETDATE()  -- ngayDK - date
    )

UPDATE dbo.HocVien SET gioiTinh = 0 WHERE id = 'HV00001'

DELETE dbo.HocVien WHERE id = '1234'

/* Bảng HocVien_KhoaHoc*/
SELECT * FROM dbo.HocVien_KhoaHoc

INSERT INTO dbo.HocVien_KhoaHoc
(
    maKH,
    maHV,
    diem
)
VALUES
(   1,  -- maKH - int
    'HV00001', -- maHV - varchar(7)
    0.0 -- diem - float
    )

UPDATE dbo.HocVien_KhoaHoc SET diem = -1 WHERE id = 1

DELETE dbo.HocVien_KhoaHoc WHERE id = 11111


/* MÃ THỦ TỤC */

/* Thống kê số người học từng năm */
IF OBJECT_ID('sp_thongKeNguoiDangKyHoc') IS NOT NULL
	DROP PROC sp_thongKeNguoiDangKyHoc
GO 
CREATE PROC sp_thongKeNguoiDangKyHoc (@year INT)
AS
SET NOCOUNT ON
BEGIN
	DECLARE @table TABLE (stt INT, thang INT,soHV int);

	INSERT @table SELECT	MONTH(ngayDK) [STT],
							MONTH(ngayDK) [Tháng], 
							COUNT(dbo.HocVien.id) [Số lượng người đăng ký] 
					FROM dbo.HocVien
					WHERE YEAR(ngayDK) = @year
					GROUP BY MONTH(ngayDK);
	DECLARE @month int = 1;
	WHILE @month <= 12
		BEGIN
			DECLARE @count INT = (SELECT COUNT(*) FROM @table WHERE thang = @month);

			IF @count = 0
				INSERT @table VALUES (@month,@month,0);

			SET @month = @month+1;
		END;

	SELECT * FROM @table ORDER BY stt;
END 

	--Gọi sp_thongKeNguoiHocTungNam:
		EXEC dbo.sp_thongKeNguoiDangKyHoc @year = 2019 -- int
		




/*	Thống kê doanh thu theo chuyên đề theo năm*/
IF OBJECT_ID('sp_thongKeDoanhThuChuyenDe') IS NOT NULL
	DROP PROC sp_thongKeDoanhThuChuyenDe
GO 
CREATE PROC sp_thongKeDoanhThuChuyenDe (@year INT)
AS
SET NOCOUNT ON 
BEGIN
	DECLARE @table TABLE (tenCD NVARCHAR(50),soKH INT,soHV INT,doanhThu INT)

	INSERT @table SELECT  dbo.ChuyenDe.tenCD [Tên chuyên đề],
							COUNT(DISTINCT dbo.KhoaHoc.id) [Số khóa học],
							COUNT(dbo.HocVien_KhoaHoc.maHV) [Số học viên],
							CASE
								WHEN COUNT(dbo.HocVien_KhoaHoc.maHV) > 0 THEN SUM(dbo.KhoaHoc.hocPhi)
								WHEN COUNT(dbo.HocVien_KhoaHoc.maHV) = 0 THEN 0
							END  [Doanh thu]
					FROM dbo.ChuyenDe
					FULL JOIN dbo.KhoaHoc ON KhoaHoc.maCD = ChuyenDe.id
					FULL JOIN dbo.HocVien_KhoaHoc ON HocVien_KhoaHoc.maKH = KhoaHoc.id
					WHERE YEAR(dbo.KhoaHoc.ngayTao) = @year
					GROUP BY dbo.ChuyenDe.tenCD,dbo.KhoaHoc.id

	DECLARE @table2 TABLE (stt INT,tenCD NVARCHAR(50),soKH INT,soHV INT,doanhThu INT)
	INSERT @table2 SELECT ROW_NUMBER() OVER (ORDER BY [@table].tenCD) [STT],[@table].tenCD,SUM([@table].soKH),SUM([@table].soHV),SUM([@table].doanhThu) FROM @table GROUP BY tenCD
	SELECT * FROM @table2
END 
	
	-- Gọi sp_thongKeDoanhThuChuyenDe:
	EXEC dbo.sp_thongKeDoanhThuChuyenDe @year = 2019 -- int


/*	Thống kê doanh thu theo chuyên đề theo thơi gian*/
IF OBJECT_ID('sp_thongKeDoanhThuChuyenDeWithTime') IS NOT NULL
	DROP PROC sp_thongKeDoanhThuChuyenDeWithTime
GO 
CREATE PROC sp_thongKeDoanhThuChuyenDeWithTime (@timeStart DATE, @timeEnd DATE)
AS
SET NOCOUNT ON 
BEGIN
	DECLARE @table TABLE (tenCD NVARCHAR(50),soKH INT,soHV INT,doanhThu INT)

	INSERT @table SELECT  dbo.ChuyenDe.tenCD [Tên chuyên đề],
							COUNT(DISTINCT dbo.KhoaHoc.id) [Số khóa học],
							COUNT(dbo.HocVien_KhoaHoc.maHV) [Số học viên],
							CASE
								WHEN COUNT(dbo.HocVien_KhoaHoc.maHV) > 0 THEN SUM(dbo.KhoaHoc.hocPhi)
								WHEN COUNT(dbo.HocVien_KhoaHoc.maHV) = 0 THEN 0
							END  [Doanh thu]
					FROM dbo.ChuyenDe
					FULL JOIN dbo.KhoaHoc ON KhoaHoc.maCD = ChuyenDe.id
					FULL JOIN dbo.HocVien_KhoaHoc ON HocVien_KhoaHoc.maKH = KhoaHoc.id
					WHERE dbo.KhoaHoc.ngayTao BETWEEN @timeStart AND @timeEnd
					GROUP BY dbo.ChuyenDe.tenCD,dbo.KhoaHoc.id

	DECLARE @table2 TABLE (stt INT,tenCD NVARCHAR(50),soKH INT,soHV INT,doanhThu INT)
	INSERT @table2 SELECT ROW_NUMBER() OVER (ORDER BY [@table].tenCD) [STT],[@table].tenCD,SUM([@table].soKH),SUM([@table].soHV),SUM([@table].doanhThu) FROM @table GROUP BY tenCD
	SELECT * FROM @table2
END 

	EXEC dbo.sp_thongKeDoanhThuChuyenDeWithTime @timeStart = '2011-1-1', -- date
	                                            @timeEnd = '2019-12-31'    -- date
	


/* Thống kê bảng điểm*/
IF OBJECT_ID('sp_bangDiem') IS NOT NULL
	DROP PROC sp_bangDiem
GO
CREATE PROC sp_bangDiem (@maKH int)
AS
BEGIN
    SELECT	ROW_NUMBER() OVER (ORDER BY dbo.HocVien_KhoaHoc.diem DESC) AS [STT],
			dbo.HocVien.id [Mã học viên],
			dbo.HocVien.hoTen [Họ tên],
			CASE
				WHEN dbo.HocVien_KhoaHoc.diem IS NULL THEN '-'
				WHEN dbo.HocVien_KhoaHoc.diem IS NOT NULL THEN CONVERT(VARCHAR(5),dbo.HocVien_KhoaHoc.diem)
			END	[Điểm],
			CASE
				WHEN dbo.HocVien_KhoaHoc.diem IS NULL THEN '-'
				WHEN dbo.HocVien_KhoaHoc.diem >= 8 THEN N'Giỏi'
				WHEN dbo.HocVien_KhoaHoc.diem >= 6.5 THEN N'Khá'
				WHEN dbo.HocVien_KhoaHoc.diem >= 5 THEN N'Trung bình'
				WHEN dbo.HocVien_KhoaHoc.diem >= 3.5 THEN N'Yếu'
				ELSE 'Kém'
			END [Xếp loại]
	FROM dbo.HocVien
	INNER JOIN dbo.HocVien_KhoaHoc ON HocVien_KhoaHoc.maHV = HocVien.id 
	WHERE dbo.HocVien_KhoaHoc.maKH = @maKH
	ORDER BY dbo.HocVien_KhoaHoc.diem DESC
END
	-- gọi sp_bangDiem:
	EXEC dbo.sp_bangDiem @maKH = 2 -- int
	





/* xóa học viên */
IF OBJECT_ID('sp_xoaHocVien') IS NOT NULL
	DROP PROC sp_xoaHocVien
GO
CREATE PROC sp_xoaHocVien (@maHV VARCHAR(7))
AS
SET NOCOUNT ON 
BEGIN
	DECLARE @countHV INT;
	DECLARE @flagDel INT;
	SET @countHV = (SELECT COUNT(*) FROM dbo.HocVien_KhoaHoc WHERE maHV = @maHV);
	IF @countHV = 0
		BEGIN
			DELETE dbo.HocVien WHERE id = @maHV;
			SET @flagDel = 1;
		END		
	ELSE
		SET @flagDel = 0;

	SELECT @flagDel	
END

	EXEC dbo.sp_xoaHocVien @maHV = 'HV00001' -- varchar(7)
	

/* Xóa khóa học */
IF OBJECT_ID('sp_xoaKhoaHoc') IS NOT NULL
	DROP PROC sp_xoaKhoaHoc
GO
CREATE PROC sp_xoaKhoaHoc(@maKH INT)
AS
SET NOCOUNT ON
BEGIN
	DECLARE @countKH INT;
	DECLARE @flagDel INT;
	SET @countKH = (SELECT COUNT(*) FROM dbo.HocVien_KhoaHoc WHERE maKH = @maKH);
	IF @countKH = 0
		BEGIN
			DELETE dbo.KhoaHoc WHERE id = @maKH;
			SET @flagDel = 1;
		END
	ELSE
		SET @flagDel = 0;

	SELECT @flagDel;
END
	
	EXEC dbo.sp_xoaKhoaHoc @maKH = 5 -- int
	
/* Xóa chuyên đề */
IF OBJECT_ID('sp_xoaChuyenDe') IS NOT NULL
	DROP PROC sp_xoaChuyenDe
GO
CREATE PROC sp_xoaChuyenDe (@maCD VARCHAR(7))
AS
SET NOCOUNT ON
BEGIN
	DECLARE @countKH INT;
	DECLARE @flagDel INT;
	SET @countKH = (SELECT COUNT(*) FROM dbo.KhoaHoc WHERE maCD = @maCD);
	IF @countKH = 0
		BEGIN
			DELETE dbo.ChuyenDe WHERE id = @maCD;
			SET @flagDel = 1;
		END
	ELSE
		SET @flagDel = 0;

	SELECT @flagDel;
END

	EXEC dbo.sp_xoaChuyenDe @maCD = 'CD123' -- varchar(7)

/* Xóa học viên của khóa học */
IF OBJECT_ID('sp_xoaHVKH') IS NOT NULL
	DROP PROC sp_xoaHVKH
GO
CREATE PROC sp_xoaHVKH (@maHVKH INT)
AS
SET NOCOUNT ON
BEGIN
    DECLARE @diem FLOAT;
	DECLARE @flagDel INT;
	SET @diem = (SELECT diem FROM dbo.HocVien_KhoaHoc WHERE id = @maHVKH);

	IF @diem < 0
		BEGIN
			DELETE dbo.HocVien_KhoaHoc WHERE id = @maHVKH;
			SET @flagDel = 1;
		END
	ELSE
		SET @flagDel = 0;

	SELECT @flagDel;
END

	EXEC dbo.sp_xoaHVKH @maHVKH = 35 -- int
	
	
/* Thêm nhân viên*/
IF OBJECT_ID('sp_insertNV') IS NOT NULL
	DROP PROC sp_insertNV
GO 
CREATE PROC sp_insertNV(@id VARCHAR(7),@pass VARCHAR(20),@hoTen NVARCHAR(50),@email VARCHAR(50),@vaiTro INT)
AS
SET NOCOUNT ON
BEGIN
	IF @vaiTro = 0 
		BEGIN
			INSERT INTO dbo.NhanVien (id,matKhau,hoTen,email,vaiTro) VALUES (@id,@pass,@hoTen,@email,@vaiTro);
		END
	ELSE
		BEGIN 
			UPDATE dbo.NhanVien SET vaiTro = 0 WHERE vaiTro = 1;
			INSERT INTO dbo.NhanVien (id,matKhau,hoTen,email,vaiTro) VALUES (@id,@pass,@hoTen,@email,@vaiTro);
		END
END

/*Chập nhật nhân viên*/
IF OBJECT_ID('sp_updateNV') IS NOT NULL
	DROP PROC sp_updateNV
GO 
CREATE PROC sp_updateNV(@pass VARCHAR(20),@hoTen NVARCHAR(50),@vaiTro INT,@id VARCHAR(7))
AS
SET NOCOUNT ON
BEGIN
	DECLARE @countTP INT;
	SET @countTP = (SELECT COUNT(*) FROM dbo.NhanVien WHERE vaiTro = 1);

	IF @countTP > 0
		BEGIN
			UPDATE dbo.NhanVien SET vaiTro = 0 WHERE vaiTro = 1;			
		END
	UPDATE dbo.NhanVien SET matKhau = @pass, hoTen=@hoTen, vaiTro = @vaiTro WHERE id = @id;
END


/*get học viên khóa học theo mã kh và năm*/
IF OBJECT_ID('sp_getHvkhByKhAndYear') IS NOT NULL
	DROP PROC sp_getHvkhByKhAndYear
GO
CREATE PROC sp_getHvkhByKhAndYear(@maKH INT,@year int)
AS
BEGIN
	SELECT dbo.HocVien_KhoaHoc.*
	FROM dbo.HocVien_KhoaHoc
	INNER JOIN dbo.KhoaHoc ON KhoaHoc.id = HocVien_KhoaHoc.maKH
	WHERE maKH = @maKH AND YEAR(dbo.KhoaHoc.ngayKG)= @year
	ORDER BY maHV
END

EXEC dbo.sp_getHvkhByKhAndYear @maKH = 9, -- int
                               @year = 2019  -- int


/*Lấy list khóa học mà học viên đã tham gia*/
IF OBJECT_ID('sp_getListKhByHv') IS NOT NULL
	DROP PROC sp_getListKhByHv
GO
CREATE PROC sp_getListKhByHv(@maHV VARCHAR(7))
AS
BEGIN
	SELECT	dbo.HocVien_KhoaHoc.id [Mã HV_KH],
			dbo.HocVien_KhoaHoc.maKH [Mã KH],
			dbo.ChuyenDe.tenCD,
			dbo.KhoaHoc.thoiLuong,
			dbo.KhoaHoc.hocPhi,
			dbo.KhoaHoc.ngayKG,
			dbo.HocVien_KhoaHoc.diem
	FROM dbo.HocVien_KhoaHoc
	INNER JOIN dbo.KhoaHoc ON KhoaHoc.id = HocVien_KhoaHoc.maKH
	INNER JOIN dbo.ChuyenDe ON ChuyenDe.id = KhoaHoc.maCD
	WHERE maHV = @maHV
	ORDER BY dbo.KhoaHoc.ngayKG DESC
END

EXEC dbo.sp_getListKhByHv @maHV = 'HV00002' -- int


/*Lấy list khóa học có thể tham gia cho học viên*/
IF OBJECT_ID('sp_getListKhForHv') IS NOT NULL
	DROP PROC sp_getListKhForHv
GO
CREATE PROC sp_getListKhForHv(@maHV VARCHAR(7))
AS
SET NOCOUNT ON
BEGIN
	DECLARE @tblKhDaThamGia TABLE(maKH int);
	INSERT @tblKhDaThamGia SELECT DISTINCT maKH FROM dbo.HocVien_KhoaHoc WHERE maHV = @maHV;

	SELECT DISTINCT dbo.KhoaHoc.id,dbo.KhoaHoc.maCD,dbo.KhoaHoc.ngayKG FROM dbo.KhoaHoc
	WHERE dbo.KhoaHoc.ngayKG > GETDATE() AND id NOT IN (SELECT * FROM @tblKhDaThamGia)
	ORDER BY dbo.KhoaHoc.ngayKG DESC
END

EXEC dbo.sp_getListKhForHv @maHV = 'HV00001' -- varchar(7)
