use testdb
go

alter ASSEMBLY [API_Consumer]
FROM  N'C:\CLR\API_Consumer.dll'
go

drop proc APICaller_GET_Example;
go

CREATE PROCEDURE [dbo].[APICaller_GET_Example]
	@URL nvarchar(250),
	@username nvarchar(250),
	@pass nvarchar(250),
	@sql nvarchar(max),
	@offset nvarchar(250),
	@limit nvarchar(250),
	@acceptPartial nvarchar(250),
	@project nvarchar(250)
as EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_GET_Example]
go



DECLARE @Results AS TABLE
(
	Context varchar(max)
)
INSERT INTO @Results
EXEC [dbo].[APICaller_GET_Example] 
	'http://192.168.101.27:7070/kylin/api/query', 
	'ADMIN', 
	'KYLIN',
	'select datetime_id, date_id from dim_date_time;', 
	0,
	7,
	'true',
	'NOC_MDO'


--Result: column per value.
SELECT [DATETIME_ID], [DATE_ID]		
FROM (SELECT Context from @Results) tb
OUTER APPLY OPENJSON (context)  
WITH (
	[datetime_id] varchar(50) '$.DATETIME_ID', 
	[date_ID] varchar(50) '$.DATE_ID'
);
go

-- [{"Name": "Content-Type","Value" :"application/json; charset=utf-8"},{"Name": "Authorization","Value" :"Basic QURNSU46S1lMSU4="}]

--SELECT c.DATE_ID, b.TTML, b.NOC, b.Province, b.District, SUM (WEB_TOTAL) WEB_TOTAL, SUM (WEB_RESP_SUCC) WEB_RESP_SUCC, 100*SUM (WEB_RESP_SUCC)/SUM (WEB_TOTAL) W_RES_SR FROM FACT_HTTP_MDO_TRANS_LOG_END_HOUR a inner join  DIM_CELL_INFO_CELLS_CURRENT  b on a.CELL_NODE_ID = b.CELL_NODE_ID inner join  DIM_DATE_TIME  c on a.DATETIME_ID = c.DATETIME_ID GROUP BY GROUPING SETS ((b.TTML), (b.NOC), (b.Province), (b.District)), (c.DATE_ID) ORDER BY c.DATE_ID, b.TTML, b.NOC, b.Province, b.District;

EXEC [dbo].[APICaller_GET_Example] 
	'http://192.168.101.27:7070/kylin/api/query', 
	'ADMIN', 
	'KYLIN',
	'SELECT 
		c.DATE_ID, 
		b.TTML, 
		b.NOC, 
		b.Province, 
		b.District, 
		SUM (WEB_TOTAL) WEB_TOTAL,
		SUM (WEB_RESP_SUCC) WEB_RESP_SUCC,
		100*SUM (WEB_RESP_SUCC)/SUM (WEB_TOTAL) W_RES_SR
	FROM FACT_HTTP_MDO_TRANS_LOG_END_HOUR  a
	inner join  DIM_CELL_INFO_CELLS_CURRENT  b on a.CELL_NODE_ID = b.CELL_NODE_ID
	inner join  DIM_DATE_TIME  c on a.DATETIME_ID = c.DATETIME_ID
	GROUP BY
		GROUPING SETS ((b.TTML), (b.NOC), (b.Province), (b.District)), (c.DATE_ID)
	ORDER BY c.DATE_ID, b.TTML, b.NOC, b.Province, b.District;', 
	'0',
	'7',
	'true',
	'NOC_MDO'

EXEC [dbo].[APICaller_GET_Example] 
	'http://192.168.101.27:7070/kylin/api/query', 
	'ADMIN', 
	'KYLIN',
	'SELECT c.DATE_ID, b.TTML, b.NOC, b.Province, b.District, SUM (WEB_TOTAL) WEB_TOTAL, SUM (WEB_RESP_SUCC) WEB_RESP_SUCC, 100*SUM (WEB_RESP_SUCC)/SUM (WEB_TOTAL) W_RES_SR FROM FACT_HTTP_MDO_TRANS_LOG_END_HOUR a inner join  DIM_CELL_INFO_CELLS_CURRENT  b on a.CELL_NODE_ID = b.CELL_NODE_ID inner join  DIM_DATE_TIME  c on a.DATETIME_ID = c.DATETIME_ID GROUP BY GROUPING SETS ((b.TTML), (b.NOC), (b.Province), (b.District)), (c.DATE_ID) ORDER BY c.DATE_ID, b.TTML, b.NOC, b.Province, b.District;', 
	null,
	'7',
	'true',
	'NOC_MDO'


