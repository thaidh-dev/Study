use master
go
DROP DATABASE IF EXISTS test_spring_boot
go
create database test_spring_boot
go
use test_spring_boot
go

delete from ghi_nhan
go
delete from nhan_vien
go
delete from phong_ban
go

select * from phong_ban
go
select * from nhan_vien
go
select * from ghi_nhan
go


-- insert --
INSERT into phong_ban (ten_phong) VALUES 
(N'Bảo vệ'),
(N'Căn tin'),
(N'Dụng cụ'),
(N'Giám đốc'),
(N'Hành chính'),
(N'Kế toán'),
(N'Nhà để xe'),
(N'Vệ sinh')
go

INSERT into nhan_vien (ten_nhan_vien, gioi_tinh, ngay_sinh, email, sdt, luong, ghi_chu, id_phong_ban) VALUES 
(N'Đào Văn Thành', 1, CAST(0x07210B00 AS Date), N'a1@gmail.com', N'0966250190', 5000000.0000, N'', 1),
(N'Nguyễn Thị Vân', 0, CAST(0x07210B00 AS Date), N'a2@gmail.com', N'0966250191', 5000000.0000, N'', 3),
(N'Nguyễn Tiến Thành', 1, CAST(0x07210B00 AS Date), N'a3@gmail.com', N'0966250192', 5000000.0000, N'', 2),
(N'Bùi Đức Chính', 1, CAST(0x07210B00 AS Date), N'a4@gmail.com', N'0966250193', 5000000.0000, N'', 2),
(N'Bùi Tiến Dũng', 1, CAST(0x07210B00 AS Date), N'a5@gmail.com', N'0966250194', 5000000.0000, N'', 2),
(N'Trần Đức Nam', 1, CAST(0x07210B00 AS Date), N'a6@gmail.com', N'0966250195', 5000000.0000, N'', 3),
(N'Lê Trung Dũng', 1, CAST(0x07210B00 AS Date), N'a7@gmail.com', N'0966250196', 5000000.0000, N'', 1),
(N'Mầu Thị Hoài Anh', 0, CAST(0x9F230B00 AS Date), N'anhmth@gmail.com', N'0768474722', 979879.0000, NULL, 5),
(N'Đoàn Hiền Linh', 0, CAST(0xB8220B00 AS Date), N'linhdh@gmail.com', N'0978586744', 999999.0000, NULL, 2),
(N'Hoàng Thanh Huyền', 0, CAST(0xEE1F0B00 AS Date), N'huyenht@gmail.com', N'0868688585', 998979.0000, NULL, 1),
(N'Lê Linh Chi', 0, CAST(0x12200B00 AS Date), N'chill@gmail.com', N'0857676743', 9909099.0000, NULL, 6),
(N'Lê Thị Hải Yến', 0, CAST(0xD8150B00 AS Date), N'yenlth@gmail', N'0879787969', 9475757.0000, N'Mới vào', 2),
(N'Dương Hồng Thái', 1, CAST(0x07210B00 AS Date), N'thaidh@gmail.com', N'0966250199', 7000000.0000, NULL, 7)
go

INSERT into ghi_nhan (loai_ghi_nhan, ly_do, ngay_ghi_nhan, id_nhan_vien) VALUES 
(1, N'Đi làm đúng giờ', CAST(0x333F0B00 AS Date), 1),
(0, N'Lười', CAST(0x2E3F0B00 AS Date), 2),
(0, N'Chăm', CAST(0x4A3F0B00 AS Date), 3),
(0, N'Vui Tính', CAST(0xAD3F0B00 AS Date), 4),
(1, N'Kỹ năng tốt', CAST(0x30400B00 AS Date), 5),
(1, N'Hay về sớm', CAST(0xA73F0B00 AS Date), 6),
(0, N'Đi muộn', CAST(0xE23F0B00 AS Date), 7),
(1, N'Nói nhiều', CAST(0x00400B00 AS Date), 3),
(1, N'Làm việc riêng', CAST(0x5A3F0B00 AS Date), 2),
(0, N'Đi làm không đều', CAST(0x31400B00 AS Date), 4),
(0, N'Lười làm', CAST(0x30400B00 AS Date), 2),
(1, N'Học cũng nhanh đấy', CAST(0x30400B00 AS Date), 6),
(1, N'Bắt gôn hay đấy, à mà wait ...', CAST(0x30400B00 AS Date), 5),
(0, N'Uống hết cafe của công ty', CAST(0x31400B00 AS Date), 1),
(1, N'Uống hết lipton của công ty', CAST(0x31400B00 AS Date), 1),
(1, N'sdsdsds', CAST(0x31400B00 AS Date), 1),
(1, N'Đi làm đều', CAST(0x31400B00 AS Date), 8),
(1, N'Nhân viên lâu năm', CAST(0x31400B00 AS Date), 4),
(0, N'Làm lâu mà vẫn hay bị kỷ luật', CAST(0x31400B00 AS Date), 4),
(1, N'Cũng xinh đấy', CAST(0x31400B00 AS Date), 8),
(1, N'Chăm ngoan', CAST(0x31400B00 AS Date), 8),
(0, N'Nói nhiều', CAST(0x31400B00 AS Date), 8),
(1, N'Lễ phép', CAST(0x31400B00 AS Date), 8),
(1, N'Học cũng nhanh đấy', CAST(0x31400B00 AS Date), 8),
(0, N'Ngu', CAST(0x31400B00 AS Date), 10),
(1, N'Kiến thức cơ bản tốt', CAST(0x31400B00 AS Date), 7),
(0, N'Chửi đồng nghiệp', CAST(0x31400B00 AS Date), 11),
(0, N'Không hòa đồng', CAST(0x31400B00 AS Date), 11),
(1, N'Ngày đầu đi làm đúng giờ', CAST(0x31400B00 AS Date), 12),
(1, N'Lau dọn tốt', CAST(0x31400B00 AS Date), 11),
(1, N'Mua cafe cho cả công ty', CAST(0x31400B00 AS Date), 8),
(1, N'Mất lái', CAST(0x32400B00 AS Date), 13),
(0, N'Ngơ', CAST(0x32400B00 AS Date), 8),
(0, N'Giao tiếp kém', CAST(0x31400B00 AS Date), 12),
(0, N'Đĩ', CAST(0x31400B00 AS Date), 9),
(1, N'Xinh', CAST(0x33400B00 AS Date), 8)
go