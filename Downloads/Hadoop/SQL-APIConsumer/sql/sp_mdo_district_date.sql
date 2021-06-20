use testdb
go

alter proc sp_mdo_district_date
	@offset int = 0, 
	@limit int = 100, 
	@acceptPartial bit = 0,
	--@offset varchar(max) = '0', 
	--@limit varchar(max) = '100', 
	--@acceptPartial varchar(max) = 'false',
	@project varchar(max) = 'DEFAULT',
	@dateIdFilter varchar(max) = null,
	@nocFilter varchar(max) = null,
	@provineFilter varchar(max) = null,
	@districtFilter varchar(max) = null
as begin
	declare @sql nvarchar(max);
	declare @filter nvarchar(max) = ' Where 1=1 ';

	if @dateIdFilter is not null
		set @filter = @filter + ' and c.DATE_ID = '	+ CAST(@dateIdFilter as varchar(max));
	if @nocFilter is not null
		set @filter = @filter + ' and b.NOC = ''' + @nocFilter + '''';
	if @provineFilter is not null
		set @filter = @filter + ' and b.Province = ''' + @provineFilter + '''';
	if @districtFilter is not null
		set @filter = @filter + ' and b.District = ''' + @districtFilter + '''';

	set @sql = 'SELECT c.DATE_ID, b.TTML, b.NOC, b.Province, b.District, SUM (WEB_TOTAL) WEB_TOTAL, SUM (WEB_RESP_SUCC) WEB_RESP_SUCC, 100*SUM (WEB_RESP_SUCC)/SUM (WEB_TOTAL) W_RES_SR FROM FACT_HTTP_MDO_TRANS_LOG_END_HOUR a inner join DIM_CELL_INFO_CELLS_CURRENT  b on a.CELL_NODE_ID = b.CELL_NODE_ID inner join  DIM_DATE_TIME c on a.DATETIME_ID = c.DATETIME_ID ' + @filter + ' GROUP BY GROUPING SETS ((b.TTML), (b.NOC), (b.Province), (b.District)), (c.DATE_ID) ORDER BY c.DATE_ID, b.TTML, b.NOC, b.Province, b.District;'

	DECLARE @Results AS TABLE
	(
		Context varchar(max)
	)
	INSERT INTO @Results
	--exec APICaller_GET_Example
	exec APIKylinQuery
		'http://192.168.101.27:7070/kylin/api/query', 
		'ADMIN', 
		'KYLIN',
		@sql,
		@offset,
		@limit,
		@acceptPartial,
		@project

	MERGE mdo_district_date AS Target
	USING (
		select date_id, isnull(ttml, '') ttml, isnull(noc, '') noc, isnull(province, '') province, isnull(district, '') district, 
			convert(bigint, SUBSTRING(WEB_TOTAL, 1, CHARINDEX('.', WEB_TOTAL) - 1)) WEB_TOTAL, 
			convert(bigint, SUBSTRING(WEB_RESP_SUCC, 1, CHARINDEX('.', WEB_RESP_SUCC) - 1)) WEB_RESP_SUCC, 
			W_RES_SR	
		from (select context from @Results where context <> '[]') tb 
		outer apply OPENJSON (Context)  
		WITH (
			date_id			int				'$.DATE_ID',
			ttml			varchar(max)	'$.TTML',
			noc				varchar(max)	'$.NOC',
			province		varchar(max)	'$.PROVINCE',
			district		varchar(max)	'$.DISTRICT',
			WEB_TOTAL		varchar(max)	'$.WEB_TOTAL',
			WEB_RESP_SUCC	varchar(max)	'$.WEB_RESP_SUCC',
			W_RES_SR		float			'$.W_RES_SR'
		)
	) AS Source
	ON (
		Target.date_id = Source.date_id 
		and Target.ttml = Source.ttml
		and Target.noc = Source.noc
		and Target.province = Source.province
		and Target.district = Source.district
	)
	WHEN MATCHED THEN
		UPDATE SET 
			Target.WEB_TOTAL = Source.WEB_TOTAL, 
			Target.WEB_RESP_SUCC = Source.WEB_RESP_SUCC,
			Target.W_RES_SR = Source.W_RES_SR
	WHEN NOT MATCHED BY TARGET THEN
		insert (date_id, ttml, noc, province, district, WEB_TOTAL, WEB_RESP_SUCC, W_RES_SR)
		values (Source.date_id, Source.ttml, Source.noc, Source.province, Source.district, Source.WEB_TOTAL, Source.WEB_RESP_SUCC, Source.W_RES_SR)
	OUTPUT $action, Inserted.*, Deleted.*; 
end
go

use testdb
go

drop table mdo_district_date
go

create table mdo_district_date(
	id				int identity(1,1) primary key,
	date_id			int,
	ttml			varchar(50),
	noc				varchar(50),
	province		varchar(50),
	district		varchar(50),
	WEB_TOTAL		bigint,
	WEB_RESP_SUCC	bigint,
	W_RES_SR		float,
	unique(date_id, ttml, noc, province, district)
)
go

delete from mdo_district_date
select * from mdo_district_date

EXEC sp_mdo_district_date default, 7, 1, 'NOC_MDO', default, default, default, 'Son Tinh'
EXEC sp_mdo_district_date default, 7, 1, 'NOC_MDO', default, default, default, 'Ngoc Hoi'
EXEC sp_mdo_district_date default, 7, 1, 'NOC_MDO', default, default, default, 'Ha Noi'

EXEC sp_mdo_district_date default, 7, 1, 'NOC_MDO', null, null, null, null
