select * from nhatro
--demo ass2a



if object_id('sptimkiem') is not null
	drop proc sptimkiem
go
create proc sptimkiem
	@dcnt nvarchar(50),
	@dt int,
	@gia money,
	@mota nvarchar(50),
	@tennd nvarchar(20),
	@tel nvarchar(15),
	@dcnd nvarchar(50)
as
	if @dcnt is null and @dt is null and @gia is null and @mota is null and @tennd is null and @tel is null and @dcnd is null
		print 'dien thong tin cmm vao thang loz'
	else
		select distinct
			'cho thue phong tro tai ' + nhatro.diachi + ' ' + tenQH as 'DCNT',
			cast(dientich as varchar) + 'm2' as dientich,
			replace(convert(nvarchar,giaphong,1),',','.') as giaphong,
			mota,
			case
				when gioitinh ='nam' then 'A. ' + tennd
				when gioitinh = 'nu' then 'C. ' + tennd
			end as lienhe,
			dienthoai,
			nguoidung.diachi as DCND
		from nhatro join quan_huyen on nhatro.maQH=quan_huyen.maQH
			join danhgia on nhatro.mant=danhgia.maNT
			join nguoidung on nguoidung.maND=danhgia.maND
		where nhatro.diachi = @dcnt
			or dientich = @dt
			or giaphong = @gia
			or dienthoai = @tel
			or nguoidung.diachi = @dcnd

exec sptimkiem null,90,null,null,null,null,null

--2b
if object_id('searchmem') is not null
	drop function searchmem
go
create function searchmem(@tennd nvarchar(20),
							@sex nvarchar(3),
							@tel nvarchar(12),
							@quan nvarchar(30),
							@add nvarchar(50),
							@mail varchar(50))
returns nvarchar(10)
begin
	return (select mand from nguoidung
			where tenND=@tennd and
				gioitinh=@sex and
				dienthoai=@tel and
				quan=@quan and
				diachi=@add and
				mail=@mail)
end

--goi
declare @mand nvarchar(15)
set @mand=dbo.searchmem(N'Đỗ Nhật Minh',N'Nam','975892642',N'Nam Từ Liêm',N'Số 789 Ngô Đình Diệm',N'MinhDN@gmail.com')
select @mand as manguoidung

--2c
if....

create function demlike (@mant nvarchar(10))
	returns @tong table([like] int,[dislike] int)
	begin
		declare @soluong int
		set @soluong = (select count(*) from danhgia where mant=@mant)
		declare @like int
		set @like = (select count(*) from danhgia where thich = '1' and mant=@mant)
		insert @tong values(@like,@soluong-@like)
		return
	end

--goi
select * from demlike('nt02')


--2d
if OBJECT_ID('likenn') is not null
	drop view likenn
go
create view likenn
as
	select top 10 dientich, giaphong, mota, ngaydang, tenND,nguoidung.diachi,dienthoai,mail, count(thich) as likenn
	from nhatro join danhgia on nhatro.maNT=danhgia.maNT
		join nguoidung on nguoidung.maND=danhgia.maND
	group by dientich,giaphong,mota, ngaydang, tenND,nguoidung.diachi,dienthoai,mail
	order by count(thich) desc

--2e
if OBJECT_ID('spnhatro') is not null
drop proc spnhatro
go
create proc spnhatro
	@mant varchar(10)
as
if exists (select * from danhgia where mant=@mant)
	select mant,tennd,thich,noidung
	from nguoidung join danhgia on nguoidung.maND=danhgia.maND
	where mant=@mant

--goi

--3
if OBJECT_ID('deldlike') is not null
	drop proc deldlike
go
create proc deldlike
	@dislike int
as
	begin try
	begin tran
		declare @bang table (maNt nvarchar(10), dislike int)
		insert @bang select mant, count(thich) as solike
			from danhgia where thich=0 group by maNT
		save tran diem1
		delete from danhgia
		where mant = (select mant from @bang where dislike > @dislike)
	commit tran
	end try
	begin catch
	rollback tran diem1
	end catch

--goi
exec deldlike 2


select maNT,count(thich) as [so dislike]
from danhgia
where thich=0
group by maNT


select * from danhgia