select*from Sach
-- Lấy mã sách, tiêu đề sách, số trang, số lượng sách có trong thư viện (có alias tiêu đề)
select ma_sach as'Mã sách', tieu_de as'Tiêu đề', so_trang as'Số trang', so_luong_sach_con_trong_kho as'Số lượng sách còn trong kho' from sach

-- Thông tin sách được nhập kho sau ngày 1.10.2017
select*from sach where Ngay_nhap_kho>'10/1/2017'

-- thông tin sách của tác giả có mã TG166 có số trang > 200
select*from sach where Ma_tac_gia='TG166' and So_trang>200

-- đếm số sách của từng tác giả theo mã tác giả
select count(tieu_de), ma_tac_gia from sach
group by ma_tac_gia

-- trung bình giá tiền sách theo từng thể loại
select avg(gia_tien) as'Giá tiền trung bình', ma_loai from sach
group by Ma_loai

-- tổng số sách của từng vị trí đặt
select count(tieu_de) as'Tổng số sách', ma_vt from sach
group by ma_vt

-- thông tin sách đắt nhất
select top 1*from sach order by gia_tien desc

-- thông tin tiêu đề, giá tiền của các cuốn sách có giá lớn hơn giá trung bình sách
select tieu_de, gia_tien from sach where gia_tien>(select avg(gia_tien) from sach)

select*from Tac_gia 
-- tất cả các tác giả họ nguyễn và sắp xếp theo thứ tự từ a-z họ tên tác giả
select*from tac_gia where Ten_tac_gia like N'Nguyễn%'
order by Ten_tac_gia asc

-- sắp xếp sách theo thứ tự tăng dần của số trang và thuộc mã nxb KD
select*from sach where Ma_NXB = 'KD'
order by so_trang asc




