create database ass

use ass

create table quan_huyen (
ma_quan_huyen nvarchar(50) not null primary key,
ten_quan_huyen nvarchar(50) not null,
)

insert into quan_huyen values
	('LB', N'Long Biên'),
	(N'ĐĐ', N'Đống Đa'),
	('TX', N'Thanh Xuân'),
	('TL', N'Từ Liêm'),
	(N'BĐ', N'Ba Đình')

create table loai_nha (
ma_loai_nha nvarchar(50) not null primary key,
ten_loai_nha nvarchar(50) not null,
)

insert into loai_nha values 
	('CHTC', N'Căn hộ trung cư'),
	('NR', N'Nhà riêng'),
	('PTKK', N'Phòng trọ khép kín'),
	('KTX', N'Kí túc xá'),
	('NTCCBA', N'Nhà trọ cung cấp bữa ăn')

create table nguoi_dung (
ma_nguoi_dung nvarchar(50) primary key,
ten_nguoi_dung nvarchar(50) not null,
gioi_tinh nvarchar(50) not null,
dia_chi nvarchar(50) not null,
email nvarchar(50) not null,
sđt nvarchar(50) not null
)

insert into nguoi_dung values
	('nd1', N'Dương Hồng Thái', 'Nam', N'Hà Nội', 'duonghongthai@gmail.com', 0966250199),
	('nd2', N'Lê Thanh Huyền', N'Nữ', N'Hà Nội', 'lethanhhuyen@gmail.com', 0123456789),
	('nd3', N'Vũ Thanh Sơn', 'Nam', N'Hà Nội', 'vuthanhson@gmail.com', 0978225086),
	('nd4', N'Đỗ Trung Nghĩa', 'Nam', N'Hà Nội', 'dotrungnghia@gmai.com', 01247622843),
	('nd5', N'Nguyễn Thanh Xuân', N'Nữ', N'Hà Nội', 'nguyenthanhxuan@gmail.com', 01983647364)

create table nha_tro (
ma_nha_tro nvarchar(50) primary key,
dien_tich decimal(5,1) not null,
gia_phong_1_thang money not null,
dia_chi nvarchar(50) not null,
mo_ta nvarchar(50) not null,
ngay_dang_tin date not null,
ma_loai_nha nvarchar(50) not null foreign key references loai_nha(ma_loai_nha),
ma_quan_huyen nvarchar(50) not null foreign key references quan_huyen(ma_quan_huyen),
)

insert into nha_tro values
	('nt1', 50, 3000000, N'Hà Nội', N'Rộng, sạch đẹp', '11/25/2018', 'NR', 'LB'),
	('nt2', 10, 1000000, N'Hà Nội', N'Có điều hòa', '3/7/2018', 'PTKK', 'TX'),
	('nt3', 20, 4000000, N'Hà Nội', N'Vừa mới xây', '2/9/2018', 'KTX', 'TL'),
	('nt4', 10, 2000000, N'Hà Nội', N'Đã xây được 5 năm', '9/13/2018', 'NTCCBA', N'BĐ'),
	('nt5', 40, 2000000, N'Hà Nội', N'Tiện nghi', '4/18/2018', 'CHTC', N'ĐĐ')
select * from nha_tro
create table danh_gia (
ma_nha_tro nvarchar(50) foreign key references nha_tro(ma_nha_tro),
ma_nguoi_dung nvarchar(50) foreign key references nguoi_dung(ma_nguoi_dung),
[like/dislike] nvarchar(50) not null,
noi_dung nvarchar(50),
primary key (ma_nha_tro, ma_nguoi_dung)
)

insert into danh_gia(ma_nha_tro, ma_nguoi_dung, [like/dislike]) values
	('nt1', 'nd3', 'Like'),
	('nt3', 'nd5', 'Like'),
	('nt2', 'nd2', 'Dislike'),
	('nt4', 'nd1', 'Dislike'),
	('nt4', 'nd5', 'Like'),
	('nt1', 'nd4', 'Like')

-- Yêu cầu 3:
-- 1.
create proc sp_nguoi_dung
@ma_nguoi_dung nvarchar(50),
@ten_nguoi_dung nvarchar(50),
@gioi_tinh nvarchar(50),
@dia_chi nvarchar(50),
@email nvarchar(50),
@sđt nvarchar(50)
as
if @ma_nguoi_dung is null or @ten_nguoi_dung is null or @gioi_tinh is null or @dia_chi is null or @email is null or @sđt is null
print N'Không được để trống'
else
insert into nguoi_dung values (@ma_nguoi_dung, @ten_nguoi_dung, @gioi_tinh, @dia_chi, @email, @sđt)
exec sp_nguoi_dung 'nd9', N'Nguyễn Văn A', N'Nam', N'Hà Nội', 'nguyenvana@gmail.com', 0966250199
exec sp_nguoi_dung 'nd10', N'Nguyễn Văn B', null, N'Hà Nội', 'nguyenvanb@gmail.com'

create proc sp_nha_tro
@ma_nha_tro nvarchar(50),
@dien_tich decimal(5,1),
@gia_phong money,
@dia_chi nvarchar(50),
@mo_ta nvarchar(50),
@ngay_dang_tin date,
@ma_loai_nha nvarchar(50),
@ma_quan_huyen nvarchar(50)
as
if @ma_nha_tro is null or @dien_tich is null or @gia_phong is null or @dia_chi is null or @mo_ta is null or @ngay_dang_tin is null or @ma_loai_nha is null or @ma_quan_huyen is null
print N'Không được để trống'
else 
insert into nha_tro values (@ma_nha_tro, @dien_tich, @gia_phong, @dia_chi, @mo_ta, @ngay_dang_tin, @ma_loai_nha, @ma_quan_huyen)
exec sp_nha_tro 'nt9', 50, 1000000, N'Hà Nội', N'Đẹp', '5/3/2018', 'NR', 'LB'
exec sp_nha_tro 'nt8', 40 , 3000000, N'Hà Nội', N'Rộng', null, 'NR', 'TX'

create proc sp_danh_gia
@ma_nha_tro nvarchar(50),
@ma_nguoi_dung nvarchar(50),
@like_dislike nvarchar(50),
@noi_dung nvarchar(50)
as
if @ma_nha_tro is null or @ma_nguoi_dung is null or @like_dislike is null or @noi_dung is null
print N'Không được để trống'
else
insert into danh_gia values (@ma_nha_tro, @ma_nguoi_dung, @like_dislike, @noi_dung)
exec sp_danh_gia 'nt3', 'nd2', 'Like', N'Khá đẹp'
exec sp_danh_gia 'NR', null, 'Like', null

drop proc sp_nguoi_dung
drop proc sp_danh_gia
drop proc sp_nha_tro

-- Yêu cầu 3:
-- 2
-- a.
create proc yeu_cau_2
@quan nvarchar(50),
@dien_tich_min int,
@dien_tich_max int, 
@ngay_dang_tin int,
@gia_phong_min money,
@gia_phong_max money,
@ten_loai_nha nvarchar(50)
as
if @quan is null or @dien_tich_min is null or @dien_tich_max is null or @ngay_dang_tin is null or @gia_phong_min is null or @gia_phong_max is null or @ten_loai_nha is null
print N'Không được để trống'
else 
select N'Cho thuê phòng trọ tại' + ' ' + nha_tro.dia_chi + ' ' + ten_quan_huyen as N'Cột 1',
replace(dien_tich, '.', ',') + ' ' + 'm2' as N'Cột 2',
replace(convert(varchar, gia_phong_1_thang, 1), ',', '.') as N'Cột 3',
mo_ta as N'Cột 4',
convert(char, ngay_dang_tin, 105) as N'Cột 5',
(case gioi_tinh
when N'Nam' then 'A.' + right(ten_nguoi_dung, charindex(' ', reverse(ten_nguoi_dung)))
when N'Nữ' then 'C.' + right(ten_nguoi_dung, charindex(' ', reverse(ten_nguoi_dung)))
end) as N'Cột 6',
sđt as N'Cột 7',
nguoi_dung.dia_chi as N'Cột 8'
from nha_tro
join quan_huyen on nha_tro.ma_quan_huyen = quan_huyen.ma_quan_huyen
join loai_nha on nha_tro.ma_loai_nha = loai_nha.ma_loai_nha
join danh_gia on nha_tro.ma_nha_tro = danh_gia.ma_nha_tro
join nguoi_dung on nguoi_dung.ma_nguoi_dung = danh_gia.ma_nguoi_dung
where ten_quan_huyen = @quan and dien_tich between @dien_tich_min and @dien_tich_max and year(ngay_dang_tin) = @ngay_dang_tin and gia_phong_1_thang between @gia_phong_min and @gia_phong_max and ten_loai_nha = @ten_loai_nha

exec yeu_cau_2 N'Long Biên', 10, 100, 2018, 1000000, 4000000, N'Nhà riêng'

drop proc yeu_cau_2

-- b.
create function fn_b
(
@ma_nguoi_dung nvarchar(50),
@ten_nguoi_dung nvarchar(50),
@gioi_tinh nvarchar(50),
@dia_chi nvarchar(50),
@email nvarchar(50),
@sđt nvarchar(50)
)
returns nvarchar(50)
begin 
return (select ma_nguoi_dung from nguoi_dung
where ma_nguoi_dung = @ma_nguoi_dung and ten_nguoi_dung = @ten_nguoi_dung and gioi_tinh = @gioi_tinh and dia_chi = @dia_chi and email = @email and sđt = @sđt)
end

select dbo.fn_b ('nd1', N'Dương Hồng Thái', 'Nam', N'Hà Nội', 'duonghongthai@gmail.com', 966250199)

-- c.
create function fn_c
(@ma_nha_tro nvarchar(50))
returns @thai table
(thich int, kothich int)
begin
declare @tong int
set @tong = (select count([like/dislike]) from danh_gia
where ma_nha_tro = @ma_nha_tro)
declare @like int 
set @like = (select count([like/dislike]) from danh_gia
where [like/dislike] = 'Like' and ma_nha_tro = @ma_nha_tro)
declare @dislike int
set @dislike = @tong - @like
insert @thai
select @like, @dislike
return
end 

select *from dbo.fn_c('nt3')

drop function fn_c

-- d.
create view d_w
as
select top 4 dien_tich, gia_phong_1_thang, mo_ta, ngay_dang_tin, ten_nguoi_dung, nguoi_dung.dia_chi, sđt, email, count([like/dislike]) as lmfao from nguoi_dung
join danh_gia on danh_gia.ma_nguoi_dung = nguoi_dung.ma_nguoi_dung
join nha_tro on nha_tro.ma_nha_tro = danh_gia.ma_nha_tro
group by dien_tich, gia_phong_1_thang, mo_ta, ngay_dang_tin, ten_nguoi_dung, nguoi_dung.dia_chi, sđt, email
order by count([like/dislike]) desc

select *from d_w

-- e
create proc sp_e
@ma_nha_tro nvarchar(50)
as
select ma_nha_tro, ten_nguoi_dung, [like/dislike], noi_dung from danh_gia
join nguoi_dung on nguoi_dung.ma_nguoi_dung = danh_gia.ma_nguoi_dung
where ma_nha_tro = @ma_nha_tro

exec sp_e 'nt3'

-- 3.1
create proc sp_3_1
@so_dislike int
as
begin try
begin tran
declare @bien_bang table
(tong_dislike int, ma_nha_tro nvarchar(50))
insert @bien_bang
select count([like/dislike]), ma_nha_tro from danh_gia
where [like/dislike] = 'Dislike'
group by ma_nha_tro

delete from danh_gia
where ma_nha_tro in (select ma_nha_tro from @bien_bang
where tong_dislike > @so_dislike)
delete from nha_tro
where ma_nha_tro in (select ma_nha_tro from @bien_bang
where tong_dislike > @so_dislike)

commit tran
end try
begin catch
rollback
end catch
drop proc sp_3_1

-- 3.2
create proc sp_3_2
@timemin date, @timemax date
as
begin try 
begin tran
declare @bien_bang table
(ma_nha_tro nvarchar(50), ngay_dang_tin date)
insert @bien_bang 
select ma_nha_tro, ngay_dang_tin from nha_tro
where ngay_dang_tin between @timemin and @timemax

delete from danh_gia
where ma_nha_tro in (select ma_nha_tro from @bien_bang)

delete from nha_tro
where ma_nha_tro in (select ma_nha_tro from @bien_bang)
commit tran
end try
begin catch
rollback
end catch
exec sp_3_2 '3/1/2018', '10/1/2018'
drop proc sp_3_2






select * from danh_gia
select * from nha_tro
select * from nguoi_dung
select * from quan_huyen
select * from loai_nha

drop table danh_gia
drop table nha_tro
drop table nguoi_dung
drop table loai_nha
drop table quan_huyen

use ap
go
drop database ass
go

use ass1
go
drop database ap

use ap
go
drop database ass1


