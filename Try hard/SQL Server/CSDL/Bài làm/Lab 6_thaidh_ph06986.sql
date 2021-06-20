-- Bài 1:
-- 1.
select*from khachhang

-- 2.
select top 10 makh, hodem +' '+ ten, email, Dienthhoai
from khachhang

-- 3.
select masp, tensp, dongia*soluong as'Tổng tiền tồn kho'
from sanpham

-- 4.
select MaKH, Hodem +' '+ Ten, diachi
from khachhang
where ten like 'H%'

-- 5.
select*from Khachhang where diachi like N'%Đà Nẵng%'

-- 6.
select*from SanPham where soluong >=100 and soluong <=500

-- 7.
select*from Hoadon where trangthai like N'%chưa trả' and year(ngaymua) = 2016

-- 8.
select*from Hoadon where makh = 'KH0001' 
or makh = 'KH0003' 
or makh = 'KH0006'

-- Bài 2:
-- 1.
select count(hodem) from khachhang

-- 2.
select max(dongia) as'Đơn giá sản phẩm cao nhất' from SanPham

-- 3.
select min(soluong) as'Số lượng sản phẩm thấp nhất' from sanpham

-- 4.
select sum(soluong) as'Tổng số lượng sản phẩm' from SanPham

-- 5.
select*from Hoadon where trangthai = N'chưa trả' and month(ngaymua) = 12 and year(ngaymua) = 2016

-- 6.
select count(masp) as'Số loại sản phẩm được mua trong hóa đơn', mahd from Hoadonchitiet
group by mahd

-- 7.
select count(masp) as'Số loại sản phẩm được mua trong hóa đơn', mahd from Hoadonchitiet
group by mahd
having count(masp) >=5

-- 8.
select mahd, ngaymua, makh
from Hoadon
order by ngaymua desc

-- Bài 3:
-- 1.
select count(mahd) as'Tổng số lượt mua hàng', makh
from hoadon
group by makh

-- 2.
select top 10 sum(soluong), masp from Hoadonchitiet
group by masp
order by sum(soluong) desc

-- 3. 
select sum(soluong), masp from Hoadonchitiet
group by masp

-- 4. 
select top 5 count(mahd) as'Tổng số lượt mua hàng', makh
from hoadon
group by makh
order by count(mahd) desc

-- số lượng mua trung bình của từng khách hàng
select avg(soluong), makh from Hoadonchitiet
inner join hoadon on Hoadon.Mahd = Hoadonchitiet.mahd
group by makh
 
-- tên sản phẩm của từng khách hàng mua nhiều nhất và đó là ngày nào

