if OBJECT_ID('tuan') is not null
drop proc tuan
go
create proc tuan
@ma_nguoi_dung nvarchar(10),
@ten_nguoi_dung nvarchar(200),
@gioi_tinh nvarchar(5),
@dien_thoai int ,
@dia_chi nvarchar(200),
@mail nvarchar(200)
as
if @ma_nguoi_dung is not null or @ten_nguoi_dung is not null or @gioi_tinh is not null or @dien_thoai is not null or @dia_chi is not null or @mail is not null
print 'hehe'
insert nguoi_dung values(@ma_nguoi_dung,@ten_nguoi_dung,@gioi_tinh,@dien_thoai,@dia_chi,@mail)
--duoc
exec tuan'p106','phamchautuan','nam','06562','234hamnghi','chau@1'
exec tuan'p107','phamchautuan','nam','06562','234hamnghi','chau@1'
--khong dc
exec tuan'p106','phamchautuan','nam','06562','234hamnghi',null


drop proc tuan


if OBJECT_ID('bua') is not null
drop proc bua
go
create proc bua
@ma_nha_tro nvarchar(20),
@ten_nha_tro nvarchar(100),
@gia_phong int,
@dia_chi nvarchar(100),
@mo_ta nvarchar(100),
@dang_tin nvarchar(100),
@ma_loai_nha nvarchar(20),
@ma_quan_huyen nvarchar(20)
as 
if @ma_nha_tro is not null or @ten_nha_tro is not null or @gia_phong is not null or @dia_chi is not null or @mo_ta is not null or @dang_tin is not null or @ma_loai_nha is not null or @ma_quan_huyen is not null
print 'hehe'
insert nha_tro values(@ma_nha_tro,@ten_nha_tro,@gia_phong,@dia_chi,@mo_ta,@dang_tin,@ma_loai_nha,@ma_quan_huyen)
--duoc
exec bua 'p136','nhatuan','100000','123 ton that tong','re trai nhe','tim nha','p12','101'
--khong duoc
exec bua 'p136','nhatuan','100000','123 ton that tong','re trai nhe','tim nha','p12',null
select*from nha_tro
if OBJECT_ID('gacon') is not null
drop proc gacon
go
create proc gacon
@ma_nha_tro nvarchar(10),
@ma_nguoi_dung nvarchar(10),
@noi_dung nvarchar(200),
@like bit 
as 
if @ma_nha_tro is not null or @ma_nguoi_dung is not null or @noi_dung is not null or @like is not null
print'kaka'
insert danh_gia values(@ma_nha_tro,@ma_nguoi_dung,@noi_dung,@like)
--duoc
exec gacon'p131','p101','kaka',1
--khong duoc
exec gacon'p131','p101','kaka',null
select*from danh_gia
