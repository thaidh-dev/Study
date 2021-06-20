use master
go

if db_id('database_goc') is not null 
	drop database database_goc
go

if db_id('database_goc') IS NULL
	create database database_goc
go

use database_goc
go

select db_id('database_goc')

CREATE LOGIN thaidh WITH PASSWORD = 'dht24111997'
drop login thaidh

