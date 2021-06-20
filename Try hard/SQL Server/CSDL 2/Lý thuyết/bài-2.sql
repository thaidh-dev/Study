declare @totaldue MONey
set @totaldue =(select sum(Invoicetotal-Paymenttotal-credittotal)
FRom Invoices)
IF @totaldue>10000
Select Vendorname, invoicenumber,Invoiceduedate,
Invoicetotal-Paymenttotal-credittotal as balances
from Vendors
join Invoices on vendors.vendorid=Invoices.vendorid
Order by InvoiceDueDate desc
else
PRInt N' du no chua thanh toan hon 100000'
-- bai2 : làm việc với biến bảng
--taoh biến bảng
declare @VENDORS TABLE
(VENDORID INT,
VENDORNAME NVARCHAR(50))
--TRUY XUẤT BIẾN BẢNG
SELECT*FROM @VENDORS
--chèn dữ liệu
insert @VENDORS 
select Vendorid ,vendorname
from Vendors
where VendorState ='CA'
--update
update @VENDORS 
set VENDORNAME='tuan'
where VENDORID=4
--delete
delete from @VENDORS
where VENDORID=6
--viết câu lệch select trả về tất cả các cột của vendors vad invoices
declare @VENDORS TABLE
(VENDORID INT,
VENDORNAME NVARCHAR(50))
insert @VENDORS 
select Vendorid ,vendorname
from Vendors
where VendorState ='CA'
select*
from Vendors
join Invoices on Vendors.VendorID=Invoices.VendorID
--kết nối trong C1
select*from @VENDORS AS ABC JOIN INVOICES
ON ABC.VENDORID=Invoices.VendorID
--tính tổng từ 1 đến 10
declare @a int ,@tong int
set @a =1
set @tong =0
while (@a<=10) 
begin 
select @tong =@tong+@a
set @a=@a+1
End
select @tong as tinhtong

