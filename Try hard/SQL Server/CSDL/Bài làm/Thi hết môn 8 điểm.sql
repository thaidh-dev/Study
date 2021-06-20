-- 1
insert into nhan_vien values 
	('NV0200', N'Nguyễn Văn', 'A', 'Nam', '11/24/1997', N'Hà Nội', '3.5', 'PB01', 'CV01'),
	('NV0201', N'Nguyễn Văn', 'B', 'Nam', '12/21/1997', N'Hà Nội', '2.5', 'PB02', 'CV02')
-- 2
update du_an set trang_thai = N'Hoàn thành'
where ma_du_an = 'DA002'

-- 3
delete from nhan_vien
where ma_nhan_vien = 'NV0201'

-- 4
select ma_nhan_vien, ho_dem +' '+ ten, ngay_sinh, dia_chi from nhan_vien
where ho_dem like N'Nguyễn%'

-- 5
select ho_dem +' '+ ten, gioi_tinh, he_so_luong, phu_cap , he_so_luong*900+phu_cap from nhan_vien
inner join chuc_vu on chuc_vu.ma_chuc_vu = nhan_vien.ma_chuc_vu

-- 6
select ten_dia_diem, count(ma_du_an) from dia_diem
inner join du_an on du_an.ma_dia_diem = dia_diem.ma_dia_diem
group by ten_dia_diem

-- 7 
select ho_dem +' '+ ten, ten_du_an, ngay_tham_gia from dia_diem
inner join du_an on du_an.ma_dia_diem = dia_diem.ma_dia_diem
inner join nhan_vien_du_an on nhan_vien_du_an.ma_du_an = du_an.ma_du_an
inner join nhan_vien on nhan_vien.ma_nhan_vien = nhan_vien_du_an.ma_nhan_vien
where ten_dia_diem = N'Hải đảo'
order by ngay_tham_gia asc

-- 8
select ma_du_an, ten_du_an, ten_dia_diem, trang_thai from du_an
inner join dia_diem on dia_diem.ma_dia_diem = du_an.ma_dia_diem
where ngay_ket_thuc_du_dinh < getdate() and ngay_ket_thuc_thuc_te >ngay_ket_thuc_du_dinh

-- 9
select ten_du_an from du_an
inner join nhan_vien_du_an on nhan_vien_du_an.ma_du_an = du_an.ma_du_an
where ma_nhan_vien = null

-- 10
select ho_dem +' '+ ten, ten_chuc_vu, ten_du_an, ten_dia_diem, he_so_luong*900+phu_cap from chuc_vu
inner join nhan_vien on nhan_vien.ma_chuc_vu = chuc_vu.ma_chuc_vu
inner join nhan_vien_du_an on nhan_vien_du_an.ma_nhan_vien = nhan_vien.ma_nhan_vien
inner join du_an on du_an.ma_du_an = nhan_vien_du_an.ma_du_an
inner join dia_diem on dia_diem.ma_dia_diem = du_an.ma_dia_diem
where he_so_luong*900+phu_cap in
(select top 3 he_so_luong*900+phu_cap from chuc_vu
inner join nhan_vien on nhan_vien.ma_chuc_vu = chuc_vu.ma_chuc_vu
order by he_so_luong*900+phu_cap asc)









select*from nhan_vien_du_an
select*from nhan_vien
select*from du_an
select*from dia_diem
select*from chuc_vu
select*from phong_ban

drop table nhan_vien_du_an
drop table nhan_vien
drop table du_an
drop table dia_diem
drop table chuc_vu
drop table phong_ban