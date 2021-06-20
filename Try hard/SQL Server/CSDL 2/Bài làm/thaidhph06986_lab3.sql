use ap

-- Bài 1:
-- 1. 
-- Tính tổng từ 1 đến 10

declare @a int, @tong int
set @a = 1
set @tong = 0
while (@a <= 10)
begin
select @tong = @a + @tong, @a = @a + 1
end
select @tong

declare @a int, @tong int
set @a = 10
set @tong = 0
while (@a <= 20)
begin
set @tong = @a + @tong
set @a = @a + 1
end 
select @tong

-- 2.
-- trả về ngày và tổng số tiền trên hóa đơn mới nhất của mỗi nhà cung cấp
select VendorID, min(InvoiceDate) as 'ngaymn' into #bangtam from Invoices
group by VendorId

select vendorname, ngaymn, invoicetotal from Vendors
join #bangtam on [#bangtam].VendorID = Vendors.VendorID
join Invoices on vendors.vendorid = invoices.vendorid
order by vendorname, ngaymn

select * from #bangtam
drop table #bangtam
select*from lmfao

-- Bài 2:
-- 1.
-- a.
-- Cột 1 kiểu decimal với 3 số sau dấu thập phân
select invoicetotal,
cast(invoicetotal as decimal(10,3)) as N'Cột 1',
cast(invoicetotal as varchar) as N'Cột 2',
cast(invoicetotal as decimal(10,3)) as N'Cột 3',
convert(varchar, invoicetotal, 1) as N'Cột 4'
from Invoices

-- b.
select 
cast(invoicetotal as varchar) as N'Cột 1',
convert(varchar, invoicetotal, 1) as N'Cột 2'
from Invoices

select
replace(cast(invoicetotal as varchar), '.', ',') as N'Cột 1',
replace(left(convert(varchar, InvoiceTotal, 1),
	len(convert(varchar, InvoiceTotal, 1))-3), ',', '.') + ',' +
	right(convert(varchar, InvoiceTotal, 1), 2) as N'Cột 2'
from invoices

-- 2.
select cast(InvoiceDate as varchar) as N'Cột 1', 
convert(varchar, InvoiceDate, 103) as N'Cột 2',
convert(varchar, InvoiceDate, 101) as N'Cột 3',
convert(varchar, InvoiceDate, 105) as N'Cột 4',
convert(varchar, InvoiceDate, 110) as N'Cột 5'
from Invoices

-- 3.
select VendorContactFName + ' ' + left(VendorContactLName, 1) + '.' as N'Cột 1',
upper(VendorContactFName + ' ' + left(VendorContactLName, 1) + '.') as N'Cột 2',
substring(VendorPhone, 7, len(right(vendorphone, len(vendorphone)-6))) as N'Cột 3'
from vendors
where left(vendorphone, 5) = '(559)'
order by VendorContactFName

