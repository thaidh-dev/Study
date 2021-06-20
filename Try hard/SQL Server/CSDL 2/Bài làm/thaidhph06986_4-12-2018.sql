create database thi_sql2
go
use thi_sql2
go
create table bao (
matobao nvarchar(50) not null primary key,
tenbao nvarchar(50) not null,
diachi nvarchar(50) not null 
)
go
create table docgia (
madg nvarchar(50) not null primary key,
hoten nvarchar(50) not null,
ngaysinh date not null,
gioitinh nvarchar(50) not null,
diachi nvarchar(50) not null
)
go
create table datmua (
madg nvarchar(50) foreign key references docgia(madg),
matobao nvarchar(50) foreign key references bao(matobao),
quy int not null,
nam int not null,
soluong int not null,
dongia money not null
primary key (madg, matobao)
)
go
-- Câu 2
create proc sp_bao
@matobao nvarchar(50),
@tenbao nvarchar(50),
@diachi nvarchar(50)
as
if @matobao is null and @tenbao is null and @diachi is null
print N'Không được để trống'
else
insert bao values (@matobao, @tenbao , @diachi)

exec sp_bao 'b1', N'Dân trí', N'Hà Nội'
exec sp_bao 'b2', N'Hà Nội mới', N'Hà Nội'
exec sp_bao 'b3', N'An Ninh', N'Hà Nội'

select*from bao

create proc sp_docgia
@madg nvarchar(50),
@hoten nvarchar(50),
@ngaysinh date,
@gioitinh nvarchar(50),
@diachi nvarchar(50)
as
if @madg is null and @hoten is null and @ngaysinh is null and @gioitinh is null and @diachi is null
print N'Không được để trống'
else
insert docgia values (@madg, @hoten, @ngaysinh ,@gioitinh, @diachi)

exec sp_docgia 'dg1', N'Dương Hồng Thái', '11/24/1997', N'Nam', N'Hà Nội'
exec sp_docgia 'dg2', N'Lê Thanh Huyền', '3/7/1997', N'Nữ', N'Hà Nội'
exec sp_docgia 'dg3', N'Nguyễn Văn Cường', '4/8/1997', N'Nam', N'Hà Nội'

select*from docgia


create proc sp_datmua
@madg nvarchar(50),
@matobao nvarchar(50),
@quy int,
@nam int,
@soluong int,
@dongia money
as
if @madg is null and @matobao is null and @quy is null and @nam is null and @soluong is null and @dongia is null
print N'Không được để trống'
else
insert datmua values (@madg, @matobao , @quy ,@nam, @soluong, @dongia)

exec sp_datmua 'dg1', 'b2', 4, 2018, 3, '20000'
exec sp_datmua 'dg2', 'b3', 3, 2018, 3, '30000'
exec sp_datmua 'dg3', 'b1', 2, 2018, 1, '10000'


select*from datmua


-- Câu 3:
create function fn_cau3
(
@madg nvarchar(50),
@hoten nvarchar(50),
@ngaysinh date,
@gioitinh nvarchar(50),
@diachi nvarchar(50)
)
returns nvarchar(50)
begin 
return (select madg from docgia
where madg = @madg and hoten = @hoten and ngaysinh = @ngaysinh and gioitinh = @gioitinh and diachi = @diachi)
end 

select dbo.fn_cau3 ('dg1', N'Dương Hồng Thái', '11/24/1997', N'Nam', N'Hà Nội')

-- câu 4
create view cau4_w
as
select top 2 datmua.madg, hoten, matobao, soluong from docgia
join datmua on datmua.madg = docgia.madg
order by soluong desc

select *from cau4_w where madg = 'dg1'

-- câu 5
create proc sp_cau5
@timemin int,
@timemax int
as
begin try
begin tran
declare @bang table
(ma_doc_gia nvarchar(50))
insert @bang 
select madg from datmua where nam between @timemin and @timemax

delete from datmua
where madg in (select ma_doc_gia from @bang)

delete from docgia
where madg in (select ma_doc_gia from @bang)
commit tran
end try
begin catch
rollback
end catch

exec sp_cau5 2017, 2019

select * from bao
select * from datmua
select * from docgia

drop proc sp_cau5










use ap
go
drop database thi_sql2
go