-- 1. toàn bộ thông tin về sách
select*from Sach

-- 2 Lấy mã sách, tiêu đề sách có trong thư viện
select ma_sach as'Mã sách', tieu_de as'Tiêu đề' from sach

-- 3 Lấy mã sách, tiêu đề sách có trong thư viện chỉ hiển thị 1 cột
select ma_sach +'       '+ tieu_de as'Thông tin sách' from sach

-- 4 Lấy ra thông tin 5 cuốn sách đầu tiên trong TV
select top 5*from sach

-- 5 Lấy ra mã nhà xuất bản trong bảng sách
select distinct ma_nxb from sach

---6 Lấy thông tin all cuốn sách thuộc nhà xuất bản PN
select*from sach where ma_nxb='PN'

--- 7 Lấy thông tin all cuốn sách thuộc nhà xuất bản PN của tác giả TG187
select*from sach where ma_nxb='PN' and Ma_tac_gia='TG187'

-- 8 Lấy ra mã sách, tiêu đề, số trang, ngày nhập kho của các cuốn sách nhập kho vào năm 2017
select ma_sach as'Mã sách', tieu_de as'Tiêu đề', so_trang as'Số trang' from sach where year(ngay_nhap_kho)=2017

-- 9 Lấy ra thông tin all cuốn  sách có mã NXB gồm 2 kí tự bắt đầu bằng chữ T
select*from sach where ma_nxb like 't_'

-- 10 Lấy ra thông tin all cuốn sách có tiêu đề bắt đầu bằng chữ B
select*from sach where tieu_de like 'B%'

-- 11 Lấy ra thông tin all cuốn sách tiêu đề có chứa  từ yêu
select*from sach where tieu_de like '%yêu%'

-- 12 sắp xếp cuốn sách theo thứ tự tăng dần của số trang
select*from sach order by so_trang asc 

--13  lấy ra thông tin cuốn sách có số trang nhiều nhất
select top 1 *from sach order by so_trang desc 

-- 14 đưa ra số lượng sách ít nhất trong bảng sách
select min(So_luong_sach_con_trong_kho) as'Số lượn sách ít nhất' from sach

-- 15 đưa ra số lượng trang trung bình của tất cả các cuốn sách
select avg(so_trang) as'Số lượng trang sách trung bình' from sach

--16  đưa ra trung bình số trang sách thuộc thể loại DH
select avg(so_trang) as'Trung bình số trang sách thuộc thể loại DH' from sach where Ma_loai='DH'

-- 17. Liệt kê tổng số đầu sách của mỗi loại sách (mã loại sách, tổng số sách)
 
-- 18. tên + số lượng sách ít nhất
select tieu_de, min(so_luong_sach_con_trong_kho)
from sach
group by tieu_de
having min(so_luong_sach_con_trong_kho) = (select min(so_luong_sach_con_trong_kho) from sach)

