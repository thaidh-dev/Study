-- Bài 1:
-- gán giá trị cho 1 biến bằng tổng dư nợ chưa thanh toán của tất cả các hóa đơn trong bảng invoice
-- sau đó kiểm tra, nếu >0 thì ...

declare @totaldue money
set @totaldue = (select sum(invoicetotal - paymenttotal - credittotal) from Invoices)
if @totaldue > 0
print N'Tổng dư nợ chưa thanh toán của tất cả các hóa đơn = $'+convert(varchar, @totaldue, 1)
else
print N'Tất cả hóa đơn đã được thanh toán đầy đủ'

--  nếu dư nợ lớn hơn 10000 usd, trả về kết quả chứa vendorname, invoicenumber, invoiceduedate, 
-- balance(invoicetotal - paymenttotal - creadittotal) và sắp xếp giảm dần

declare @toltaldue money
set @toltaldue = (select sum(invoicetotal - paymenttotal - credittotal) from Invoices)
if @toltaldue > 10000
select vendorname, InvoiceNumber, InvoiceDueDate, InvoiceTotal - PaymentTotal - CreditTotal as 'Balance' from Invoices
inner join Vendors on Vendors.VendorID = Invoices.vendorid
order by InvoiceDueDate desc
else
print N'Dư nợ nhỏ hơn 10000 USD'

-- Bài 2:
-- Khai báo biến bảng
declare @vendors table
(vendorid int,
vendorname varchar(50))

-- Chèn
declare @vendors table
(lol int,
lmao varchar(50))

insert @vendors
select Vendorid, Vendorname from Vendors
where VendorState = 'ca'

select * from @vendors

-- Truy xuất dữ liệu
declare @vendors table
(vendorid int,
vendorname varchar(50))

insert @vendors
select VendorID, vendorname from Vendors
where VendorState = 'ca'

select * from @vendors

-- update
declare @vendors table
(vendorid int,
vendorname varchar(50))

insert @vendors
select VendorID, vendorname from Vendors
where VendorState = 'ca'

update @vendors 
set vendorname = 'thai'
where vendorid = 4

select * from @vendors

-- insert
declare @vendors table
(vendorid int, vendorname varchar(50))

insert @vendors
select VendorID, VendorName from Vendors
where VendorState = 'ca'

insert into @vendors values (122, 'duong hong thai')

select * from @vendors

-- delete
declare @vendors table
(vendorid int, vendorname varchar(50))

insert @vendors
select VendorID, VendorName from Vendors
where VendorState = 'ca'

delete from @vendors
where vendorid = 122

select * from @vendors

-- join 
declare @vendors table
(vendorid int,
vendorname varchar(50))

insert @vendors
select VendorID, vendorname from Vendors
where VendorState = 'ca'

select * from @vendors
join Invoices on Invoices.VendorID = [@vendors].vendorid

select * from vendors
