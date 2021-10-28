use AdventureWorks2019;
select BusinessEntityID, PhoneNumberTypeID, PhoneNumber from Person.PersonPhone;

select * from Person.PersonPhone;
select PhoneNumberTypeID, max(BusinessEntityID) as max, count(*) as 'count' from Person.PersonPhone group by PhoneNumberTypeID;
select PhoneNumberTypeID, count(*) as 'count', max(BusinessEntityID) as max from Person.PersonPhone group by PhoneNumberTypeID;
select PhoneNumberTypeID, sum(BusinessEntityID) as 'sum'from Person.PersonPhone group by PhoneNumberTypeID;


select * from Person.PhoneNumberType;

create table thaidh (
	PhoneNumberTypeID int not null,
	count int not null,
	max int not null,
	name varchar(50) not null
)

select * from thaidh;
delete from thaidh;
drop table thaidh;

select HumanResources.Employee.BusinessEntityID, LoginID, JobTitle, HumanResources.Department.DepartmentID, HumanResources.Department.Name from HumanResources.Employee
left join HumanResources.EmployeeDepartmentHistory on HumanResources.Employee.BusinessEntityID = HumanResources.EmployeeDepartmentHistory.BusinessEntityID
right join HumanResources.Department on HumanResources.EmployeeDepartmentHistory.DepartmentID = HumanResources.Department.DepartmentID;

select * from HumanResources.EmployeeDepartmentHistory
where BusinessEntityID = 4;

select HumanResources.Employee.BusinessEntityID, LoginID, JobTitle, HumanResources.EmployeeDepartmentHistory.DepartmentID from HumanResources.Employee
left join HumanResources.EmployeeDepartmentHistory on HumanResources.Employee.BusinessEntityID = HumanResources.EmployeeDepartmentHistory.BusinessEntityID

select * from HumanResources.Employee;
