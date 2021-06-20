-- Bài 1:
-- 1.
-- a. Viết sp chấp nhận 1 tham số đầu ra là tổng giá trị các số từ 1 đến 10
if object_id ('sptong') is not null
drop proc sptong 
go
create proc sptong
@tong int output
as
declare @a int
set @a = 1
set @tong = 0
while @a <= 10
begin
select @tong = @tong + @a
set @a = @a + 1
end

-- gọi 
declare @sum int
exec sptong @sum output
select @sum as tong

--
if object_id('spbalancerange') is not null
drop proc spbalancerange
go
create proc spbalancerange
@vendorvar nvarchar(50) = '%',
@balancemin money = null,
@balancemax money = null
as
if @balancemin is null or @balancemax is null
select vendorname, invoicenumber, invoicetotal - paymenttotal - credittotal as balancedue
from vendors join invoices on vendors.vendorid = Invoices.VendorID
where vendorname like @vendorvar
order by balancedue desc
else
select vendorname, invoicenumber, invoicetotal - paymenttotal - credittotal as balancedue
from vendors join invoices on vendors.vendorid = Invoices.VendorID
where vendorname like @vendorvar and invoicetotal - paymenttotal - credittotal between @balancemin and @balancemax
order by balancedue desc
-- gọi
exec spbalancerange @vendorvar = 'z%'

exec spbalancerange @vendorvar = '%', @balancemin = 200, @balancemax = 1000

exec spbalancerange @vendorvar = '[cf]%', @balancemin = 0, @balancemax = 199 

-- Bài 2:
-- 2.
select * from vendors
select * from invoices

begin try
begin tran
update vendors set vendorname = 'abc'
where vendorid = 121
update invoices set vendorid = 121
where vendorid = 123
delete from vendors
where vendorid = 123
commit tran
end try
begin catch
rollback tran
end catch

-- 3.
if object_id('spsatnhap') is not null
drop proc spbalancerange
go
create proc spsatnhap
@id1 int = null,
@id2 int = null,
@namenew nvarchar(50)
as
if @id1 = null or @id2 = null or @namenew = null
print N'Không thực hiện giao dịch'
else
update vendors
set vendorname = @namenew
where vendorid = @id1
update invoices 
set vendorid = @id1
where vendorid = @id2
delete from vendors
where vendorid = @id2
begin try
begin tran
commit tran
exec spsatnhap @id1 = 121, @id2 = 123, @namenew = 'fedup'
commit tran
end try
begin catch
rollback tran
end catch
