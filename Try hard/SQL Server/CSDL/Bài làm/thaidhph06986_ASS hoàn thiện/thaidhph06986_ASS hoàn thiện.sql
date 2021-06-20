create database assignmentCSDL1
go
use assignmentCSDL1
go

create table loai_sach(
ma_loai_sach nvarchar(50) not null primary key,
ten_loai_sach nvarchar(50) not null,
)
go

insert into loai_sach values 
('KT', N'Kinh tế'),
('IT', N'Công nghệ thông tin'),
('DL', N'Du lịch'),
('VH', N'Văn học'),
('NN', N'Ngoại ngữ'),
('GK', N'Giáo khoa'),
('YT', N'Y tế')
go

create table nha_xuat_ban(
ma_nxb nvarchar(50) not null primary key,
ten_nxb nvarchar(50) not null,
)
go

insert into nha_xuat_ban values
('KĐ', N'Kim Đồng'),
('VH', N'Văn học'),
('GD', N'Giáo dục'),
('BKHN',N'Bách khoa Hà Nội'),
('YT', N'Y tế'),
('KT', N'Kinh tế'),
('VHa', N'Văn hóa')
go

create table tac_gia(
ma_tac_gia int identity(1,1) not null primary key,
ten_tac_gia nvarchar(50) not null,
)
go

insert into tac_gia values
(N'Nguyễn Nhật Ánh'),
(N'Cù Huy Cận'),
(N'Lý Lan'),
(N'Rosie Nguyễn'),
(N'Trần Đăng Khoa')
go

create table nganh_hoc(
ma_nganh int identity(1,1) not null primary key,
ten_nhanh nvarchar(50) not null,
)
go

insert into nganh_hoc values
(N'Công nghệ thông tin'),
(N'Quản lý khách sạn'),
(N'Tổ chức sự kiện'),
(N'Kinh tế'),
(N'Đồ họa')
go

create table lop(
ma_lop int identity(1,1) not null primary key,
ten_lop nvarchar(50) not null,
)
go

insert into lop values
(N'PT14201-UD'),
(N'PT14202-UD'),
(N'MOB1032-KH'),
(N'OT1902-UD'),
(N'RT1234-UD')
go

create table sinh_vien(
ma_sv nvarchar(50) not null primary key,
ten_sv nvarchar(50) not null,
ngay_het_han date not null,
email nvarchar(50) not null,
sdt varchar(50) not null,
ma_nganh int foreign key references nganh_hoc(ma_nganh),
ma_lop int foreign key references lop(ma_lop),
)
go

insert into sinh_vien values
('PD12301', N'Dương Hồng Thái','11/24/2018','thaidhph06986@fpt.edu.vn','0966250199','1','1'),
('PD12345', N'Vũ Hồng Sơn','8/16/1997','vuhongson@gmail.com','0123456789','2','2'),
('PD56789', N'Vũ Hồng Duy','6/17/1992','vuhongduy@gmail.com','098234765','3','3'),
('PD14592', N'Vũ Minh Nhật','5/29/1995','vuminhnhat@gmail.com','0192856388','4','4'),
('PD60129', N'Vũ Minh Đức','6/30/1998','vuminhduc@gmail.com','0923870147','5','5')
go

create table sach(
ma_sach int identity(1,1) not null primary key,
ten_sach nvarchar(50) not null,
so_trang int check(so_trang>5),
so_luong int check(so_luong>1),
gia_tien money check(gia_tien>0),
ngay_nhap_kho date not null,
vi_tri_dat nvarchar(50) not null,
ma_tac_gia int foreign key references tac_gia(ma_tac_gia),
ma_loai_sach nvarchar(50) foreign key references loai_sach(ma_loai_sach),
ma_nxb nvarchar(50) foreign key references nha_xuat_ban(ma_nxb),
)
go

insert into sach values
(N'Harry Potter','300','5','150000','11/24/2013',N'Khu A','1','VH','VH'),
(N'Hạt giống tâm hồn','500','10','200.000','12/26/2018',N'Khu B','2','VH','VH'),
(N'Toán','400','30','18000','4/17/2018',N'Khu C','3','GK','GD'),
(N'Dạy tiếng Pháp','500','90','20000','5/20/2013',N'Khu D','4','NN','GD'),
(N'Dạy tiếng Anh','200','43','300000','4/23/2018',N'Khu E','5','NN','GD'),
(N'Nhập môn SQL','250','50','250000','5/6/2018',N' Khu A','2','IT','GD'),
(N'Chứng khoán dễ hay khó','350','30','25000','4/3/2013','Khu B','5','KT','KT'),
(N'Khám phá châu Âu','400','50','400000','4/5/2018','Khu C','3','DL','Vha'),
(N'SQL nâng cao','200','10','40000','6/9/2013','Khu D','4','IT','BKHN'),
(N'Ung thư và những điều cần biết','500','50','400000','2/5/2018','Khu E','5','YT','YT')
go

create table phieu_muon(
ma_phieu int identity(1,1) not null primary key,
ngay_muon date not null,
ngay_tra date not null,
check(ngay_tra>ngay_muon),
ma_sv nvarchar(50) foreign key references sinh_vien(ma_sv),
)
go

insert into phieu_muon values
('7/20/2018','7/23/2018','PD12301'),
('4/15/2018','4/18/2018','PD12345'),
('6/23/2018','6/30/2018','PD12345'),
('3/12/2018','3/20/2018','PD56789'),
('5/14/2018','5/25/2018','PD56789'),
('1/20/2017','1/25/2017','PD12301'),
('1/15/2017','1/17/2017','PD12301'),
('1/24/2017','1/30/2017','PD14592'),
('4/6/2009','4/10/2009','PD14592'),
('5/9/2009','5/15/2009','PD60129'),
('2/3/2009','2/7/2009','PD60129')
go

create table phieu_muon_chi_tiet(
ma_phieu int foreign key references phieu_muon(ma_phieu),
ma_sach int foreign key references sach(ma_sach),
ghi_chu nvarchar(50) not null,
primary key(ma_phieu,ma_sach),
)
go

insert into phieu_muon_chi_tiet values
('1','1',N'Chưa trả'),
('2','2',N'Chưa trả'),
('3','3',N'Đã trả sách'),
('4','4',N'Đã trả sách'),
('5','5',N'Chưa trả'),
('1','3',N'Chưa trả'),
('2','1',N'Đã trả sách'),
('3','2',N'Chưa trả'),
('4','5',N'Đã trả sách'),
('5','4',N'Chưa trả'),
('2','3',N'Đã trả sách')
go


-- 6.1 Thông tin tên sách, mã sách, giá tiền, tác giả thuộc loại sách có mã 'IT'
select ten_sach as'Tên sách', ma_sach as'Mã sách', gia_tien as'Giá tiền', ten_tac_gia as'Tên tác giả'
from sach
inner join tac_gia on sach.ma_tac_gia = tac_gia.ma_tac_gia
and ma_loai_sach = 'IT'

-- 6.2 Liệt kê các phiếu mượn gồm mã phiếu mượn, mã sách, ngày mượn, mã sinh viên có ngày mượn trong tháng 1/2017
select phieu_muon.ma_phieu as'Mã phiếu', sach.ma_sach as'Mã sách', ngay_muon as'Ngày mượn', phieu_muon.ma_sv as'Mã SV'
from sinh_vien
full outer join phieu_muon on sinh_vien.ma_sv = phieu_muon.ma_sv
full outer join phieu_muon_chi_tiet on phieu_muon_chi_tiet.ma_phieu = phieu_muon.ma_phieu
full outer join sach on phieu_muon_chi_tiet.ma_sach = sach.ma_sach
where month(ngay_muon) = 1 and year(ngay_muon) = 2017

-- 6.3 Liệt kê các phiếu mượn chưa trả sách cho thư viện theo thứ tự tăng dần của ngày mượn sách
select ma_phieu, ngay_muon from phieu_muon
where exists (select ghi_chu from phieu_muon_chi_tiet where phieu_muon.ma_phieu = phieu_muon_chi_tiet.ma_phieu and ghi_chu = N'Chưa trả')
order by ngay_muon asc

-- 6.4 Tổng số đầu sách của mỗi loại sách (mã loại, tên loại sách, tổng số lượng sách mỗi loại)
select sach.ma_loai_sach as'Mã loại sách', ten_loai_sach as'Tên loại sách', count(ten_sach) as'Số lượng sách mỗi loại' from sach
inner join loai_sach on loai_sach.ma_loai_sach = sach.ma_loai_sach
group by sach.ma_loai_sach, ten_loai_sach

-- 6.5 Đếm xem có bao nhiêu lượt sinh viên đã mượn sách
Select count(ma_phieu) as'Số lượt sinh viên mượn sách' from phieu_muon

-- 6.6 Hiển thị tất cả các quyển sách có tiêu đề chứa từ khóa 'SQL'
select ten_sach as'Tên sách' from sach where ten_sach like N'%SQL%'

-- 6.7 Hiển thị thông tin mượn sách gồm: mã sinh viên, tên sinh viên, mã phiếu mượn, tiêu đề sách, ngày mượn, ngày trả. Sắp xếp theo thứ tự ngày mượn sách
Select sinh_vien.ma_sv as'Mã sinh viên', sinh_vien.ten_sv as'Tên sinh viên', phieu_muon.ma_phieu as'Mã phiếu', ten_sach as'Tên sách', ngay_muon as'Ngày mượn', ngay_tra as'Ngày trả'
from sinh_vien
Inner join phieu_muon on sinh_vien.ma_sv = phieu_muon.ma_sv
Inner join phieu_muon_chi_tiet on phieu_muon_chi_tiet.ma_phieu = phieu_muon.ma_phieu
Inner join sach on phieu_muon_chi_tiet.ma_sach = sach.ma_sach
Order by ngay_muon asc

-- 6.8 Các đầu sách có lượt mượn lớn hơn 1 lần
select count(phieu_muon_chi_tiet.ma_sach) luotmuon, ten_sach as'Tên sách' from phieu_muon_chi_tiet
inner join sach on sach.ma_sach = phieu_muon_chi_tiet.ma_sach
group by ten_sach
having count(phieu_muon_chi_tiet.ma_sach) > 1

-- 6.9 Cập nhật lại giá tiền của các quyển sách có ngày nhập kho trước năm 2014 giảm 30%
update sach set gia_tien  = (gia_tien - gia_tien*30/100)
where ma_sach in (select ma_sach from sach where year(ngay_nhap_kho)<2014)

-- 6.10 Cập nhật lại trạng thái đã trả sách cho phiếu mượn của sinh viên có mã sinh viên là PD12301
update phieu_muon_chi_tiet set ghi_chu = N'Đã trả sách'
where ma_phieu in (
select ma_phieu from phieu_muon_chi_tiet
where exists (select ma_sv from phieu_muon where phieu_muon.ma_phieu = phieu_muon_chi_tiet.ma_phieu and ma_sv = 'PD12301')
)

-- 6.11 Lập danh sách các phiếu mượn quá hạn chưa trả gồm: mã phiếu mượn, tên sinh viên, email, danh sách các sách đã mượn, ngày mượn
select phieu_muon_chi_tiet.ma_phieu, ten_sv, email, ngay_muon, ten_sach from sinh_vien
inner join phieu_muon on phieu_muon.ma_sv = sinh_vien.ma_sv
inner join phieu_muon_chi_tiet on phieu_muon_chi_tiet.ma_phieu = phieu_muon.ma_phieu
inner join sach on sach.ma_sach = phieu_muon_chi_tiet.ma_sach
where ghi_chu = N'Chưa trả'

-- 6.12 Cập nhật lại số lượng bản sao tăng lên 5 đơn vị đối với các đầu sách có lượt mượn lớn hơn 10
update sach set so_luong = (so_luong + 5)
where ma_sach in(
select t1.[Mã sách] from (select count(phieu_muon_chi_tiet.ma_sach) as'Lượt mượn', sach.ma_sach as'Mã sách' from phieu_muon_chi_tiet
inner join sach on sach.ma_sach = phieu_muon_chi_tiet.ma_sach
group by sach.ma_sach
having count(phieu_muon_chi_tiet.ma_sach) >1) as t1
)

-- 6.13 Viêt câu lệnh xóa phiếu mượn có ngay mượn và ngày trả trước 1/1/2010
delete from phieu_muon_chi_tiet
where ma_phieu in (select ma_phieu from phieu_muon_chi_tiet where exists (select ma_phieu from phieu_muon where phieu_muon_chi_tiet.ma_phieu = phieu_muon.ma_phieu and ngay_muon < '1/1/2010' and ngay_tra < '1/10/2010'))
delete from phieu_muon
where ma_phieu in (select ma_phieu from phieu_muon where ngay_muon < '1/1/2010' and ngay_tra < '1/1/2010')

/*

select*from lop
select*from sach
select*from tac_gia
select*from phieu_muon
select*from loai_sach
select*from phieu_muon_chi_tiet

drop table phieu_muon_chi_tiet
drop table phieu_muon
drop table sinh_vien
drop table sach
drop table lop
drop table nganh_hoc
drop table tac_gia
drop table nha_xuat_ban
drop table loai_sach

*/
