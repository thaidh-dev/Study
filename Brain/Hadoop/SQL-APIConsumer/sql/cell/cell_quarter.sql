use testdb
go

drop table mdo_cell_quarter
go

create table mdo_cell_quarter(
	id							int identity(1,1) primary key,
	QUARTER_ID					varchar(7),
    TTML						varchar(50),
    NOC							varchar(10),
    PROVINCE					varchar(255),
    DISTRICT					varchar(255),
    SITENAME					varchar(250),
	CELL_NODE_ID				bigint,
	WEB_TOTAL					float,
	MDO_VIDEO_COUNT				float,
	WEB_RESP_SUCC				float,
	WEB_AVG_RESP_DELAY			float,
	WEB_AVG_DISPLAY_DELAY		float,
	WEB_DL_TIME					float,
	WEB_DL_DATA					float,
	WEB_BROWS_SUCC				float,
	WEB_BROWS_TOTAL				float,
	VIDEO_STREAM_SUCC			float,
	VIDEO_STREAM_TOTAL			float,
	VIDEO_STREAM_START_DELAY	float,
	VIDEO_STREAM_STALL			float,
	VIDEO_STREAM_DATA			float,
	VIDEO_STREAM_TIME			float,
	VIDEO_HD_NUM				float,
	VIDEO_SD_NUM				float,
	VIDEO_LD_NUM				float,
	HTTPS_WEB_DL_DATA			float,
	HTTPS_WEB_DL_TIME			float,
	HTTPS_VIDEO_STREAM_DATA		float,
	HTTPS_VIDEO_STREAM_TIME		float,
	HTTPS_VIDEO_HD_NUM			float,
	HTTPS_VIDEO_SD_NUM			float,
	HTTPS_VIDEO_LD_NUM			float,
	HTTPS_VIDEO_STREAM_SUCC		float,
	QUIC_WEB_DL_DATA			float,
	QUIC_WEB_DL_TIME			float,
	QUIC_VIDEO_STREAM_DATA		float,
	QUIC_VIDEO_STREAM_TIME		float,
	QUIC_VIDEO_HD_NUM			float,
	QUIC_VIDEO_SD_NUM			float,
	QUIC_VIDEO_LD_NUM			float,
	QUIC_VIDEO_STREAM_SUCC		float,
	W_RES_SR					float,
	W_RES_DL					float,
	W_BRO_SR					float,
	W_DPL_DL					float,
	W_DL_THP					float,
	V_STR_SR					float,
	V_STR_DL					float,
	V_STA_RT					float,
	V_STA_FR					float,
	V_DL_THP					float,
	V_HD_RT						float,
	V_SD_RT						float,
	V_LD_RT						float
	unique(QUARTER_ID, ttml, noc, province, district, sitename, cell_node_id)
)
go


alter proc sp_mdo_cell_quarter
	@offset int = 0, 
	@limit int = 100, 
	@acceptPartial bit = 0,
	@project varchar(max) = 'DEFAULT',

	--@Quarter_ID_filter					varchar(7) = null,
 --   @TTML_filter						varchar(50) = null,
 --   @NOC_filter							varchar(10) = null,
 --   @PROVINCE_filter					varchar(255) = null,
 --   @DISTRICT_filter					varchar(255) = null,
 --   @SITENAME_filter					varchar(250) = null,
	--@CELL_NODE_ID_filter				bigint = null

	@Quarter_ID_filter					varchar(max) = null,
    @TTML_filter						varchar(max) = null,
    @NOC_filter							varchar(max) = null,
    @PROVINCE_filter					varchar(max) = null,
    @DISTRICT_filter					varchar(max) = null,
    @SITENAME_filter					varchar(max) = null,
	@CELL_NODE_ID_filter				varchar(max) = null


as begin
	declare @sql nvarchar(max);
	declare @filter nvarchar(max) = ' Where 1=1 ';

	--if @Quarter_ID_filter is not null
	--	set @filter = @filter + ' and QUARTER_ID = ''' + @Quarter_ID_filter + '''';
	--if @TTML_filter is not null
	--	set @filter = @filter + ' and TTML = ''' + @TTML_filter + '''';
	--if @NOC_filter is not null
	--	set @filter = @filter + ' and NOC = ''' + @NOC_filter + '''';
	--if @PROVINCE_filter is not null
	--	set @filter = @filter + ' and PROVINCE = ''' + @PROVINCE_filter + '''';
	--if @DISTRICT_filter is not null
	--	set @filter = @filter + ' and DISTRICT = ''' + @DISTRICT_filter + '''';
	--if @SITENAME_filter is not null
	--	set @filter = @filter + ' and SITENAME = ''' + @SITENAME_filter + '''';
	--if @CELL_NODE_ID_filter is not null
	--	set @filter = @filter + ' and CELL_NODE_ID = ' + CAST(@CELL_NODE_ID_filter as varchar(max));

	if @Quarter_ID_filter is not null
		set @filter = @filter + ' and QUARTER_ID in(''' + REPLACE(@Quarter_ID_filter, ', ', ''', ''') + ''')';
	if @TTML_filter is not null
		set @filter = @filter + ' and TTML in(''' + replace(@TTML_filter, ', ', ''', ''') + ''')';
	if @NOC_filter is not null
		set @filter = @filter + ' and NOC in(''' + replace(@NOC_filter, ', ', ''', ''') + ''')';
	if @PROVINCE_filter is not null
		set @filter = @filter + ' and PROVINCE in(''' + replace(@PROVINCE_filter, ', ', ''', ''') + ''')';
	if @DISTRICT_filter is not null
		set @filter = @filter + ' and DISTRICT in(''' + replace(@DISTRICT_filter, ', ', ''', ''') + ''')';
	if @SITENAME_filter is not null
		set @filter = @filter + ' and SITENAME in(''' + replace(@SITENAME_filter, ', ', ''', ''') + ''')';
	if @CELL_NODE_ID_filter is not null
		set @filter = @filter + ' and CELL_NODE_ID in(' + @CELL_NODE_ID_filter + ')';


	set @sql = 'SELECT
        QUARTER_ID,
        TTML,
        NOC,
        PROVINCE,
        DISTRICT,
        SITENAME,
        CELL_NODE_ID,
        SUM(WEB_TOTAL) WEB_TOTAL,
        SUM(MDO_VIDEO_COUNT) MDO_VIDEO_COUNT,
        SUM(WEB_RESP_SUCC) WEB_RESP_SUCC,
        SUM(WEB_AVG_RESP_DELAY) WEB_AVG_RESP_DELAY,
        SUM(WEB_AVG_DISPLAY_DELAY) WEB_AVG_DISPLAY_DELAY,
        SUM(WEB_DL_TIME) WEB_DL_TIME,
        SUM(WEB_DL_DATA) WEB_DL_DATA,
        SUM(WEB_BROWS_SUCC) WEB_BROWS_SUCC,
        SUM(WEB_BROWS_TOTAL) WEB_BROWS_TOTAL,
        SUM(VIDEO_STREAM_SUCC) VIDEO_STREAM_SUCC,
        SUM(VIDEO_STREAM_TOTAL) VIDEO_STREAM_TOTAL,
        SUM(VIDEO_STREAM_START_DELAY) VIDEO_STREAM_START_DELAY,
        SUM(VIDEO_STREAM_STALL) VIDEO_STREAM_STALL,
        SUM(VIDEO_STREAM_DATA) VIDEO_STREAM_DATA,
        SUM(VIDEO_STREAM_TIME) VIDEO_STREAM_TIME,
        SUM(VIDEO_HD_NUM) VIDEO_HD_NUM,
        SUM(VIDEO_SD_NUM) VIDEO_SD_NUM,
        SUM(VIDEO_LD_NUM) VIDEO_LD_NUM,
        SUM(HTTPS_WEB_DL_DATA) HTTPS_WEB_DL_DATA,
        SUM(HTTPS_WEB_DL_TIME) HTTPS_WEB_DL_TIME,
        SUM(HTTPS_VIDEO_STREAM_DATA) HTTPS_VIDEO_STREAM_DATA,
        SUM(HTTPS_VIDEO_STREAM_TIME) HTTPS_VIDEO_STREAM_TIME,
        SUM(HTTPS_VIDEO_HD_NUM) HTTPS_VIDEO_HD_NUM,
        SUM(HTTPS_VIDEO_SD_NUM) HTTPS_VIDEO_SD_NUM,
        SUM(HTTPS_VIDEO_LD_NUM) HTTPS_VIDEO_LD_NUM,
        SUM(HTTPS_VIDEO_STREAM_SUCC) HTTPS_VIDEO_STREAM_SUCC,
        SUM(QUIC_WEB_DL_DATA) QUIC_WEB_DL_DATA,
        SUM(QUIC_WEB_DL_TIME) QUIC_WEB_DL_TIME,
        SUM(QUIC_VIDEO_STREAM_DATA) QUIC_VIDEO_STREAM_DATA,
        SUM(QUIC_VIDEO_STREAM_TIME) QUIC_VIDEO_STREAM_TIME,
        SUM(QUIC_VIDEO_HD_NUM) QUIC_VIDEO_HD_NUM,
        SUM(QUIC_VIDEO_SD_NUM) QUIC_VIDEO_SD_NUM,
        SUM(QUIC_VIDEO_LD_NUM) QUIC_VIDEO_LD_NUM,
        SUM(QUIC_VIDEO_STREAM_SUCC) QUIC_VIDEO_STREAM_SUCC,
        CASE
                WHEN SUM(WEB_TOTAL) > 0 THEN 100 * SUM(WEB_RESP_SUCC) / SUM(WEB_TOTAL)
                ELSE 0
        END AS W_RES_SR,
        CASE
                WHEN SUM(WEB_TOTAL) > 0 THEN 100 * SUM(WEB_AVG_RESP_DELAY) / SUM(WEB_TOTAL)
                ELSE 0
        END AS W_RES_DL,
        CASE
                WHEN SUM(WEB_BROWS_TOTAL) > 0 THEN 100 * SUM(WEB_BROWS_SUCC) / SUM(WEB_BROWS_TOTAL)
                ELSE 0
        END AS W_BRO_SR,
        CASE
                WHEN SUM(WEB_TOTAL) > 0 THEN SUM(WEB_AVG_DISPLAY_DELAY) / SUM(WEB_TOTAL)
                ELSE 0
        END AS W_DPL_DL,
        CASE
                WHEN (
                        SUM(WEB_DL_TIME) + SUM(HTTPS_WEB_DL_TIME) + SUM(QUIC_WEB_DL_TIME)
                ) > 0 THEN (
                        SUM(WEB_DL_DATA) + SUM(HTTPS_WEB_DL_DATA) + SUM(QUIC_WEB_DL_DATA)
                ) /(
                        SUM(WEB_DL_TIME) + SUM(HTTPS_WEB_DL_TIME) + SUM(QUIC_WEB_DL_TIME)
                )
                ELSE 0
        END AS W_DL_THP,
        CASE
                WHEN SUM(MDO_VIDEO_COUNT) > 0 THEN 100 * SUM(VIDEO_STREAM_SUCC) / SUM(MDO_VIDEO_COUNT)
                ELSE 0
        END AS V_STR_SR,
        CASE
                WHEN SUM(MDO_VIDEO_COUNT) > 0 THEN SUM(VIDEO_STREAM_START_DELAY) / SUM(MDO_VIDEO_COUNT)
                ELSE 0
        END AS V_STR_DL,
        CASE
                WHEN SUM(VIDEO_STREAM_SUCC) > 0 THEN 100 * SUM(VIDEO_STREAM_STALL) / SUM(VIDEO_STREAM_SUCC)
                ELSE 0
        END AS V_STA_RT,
        1 AS V_STA_FR,
        CASE
                WHEN (
                        SUM(VIDEO_STREAM_TIME) + SUM(HTTPS_VIDEO_STREAM_TIME) + SUM(QUIC_VIDEO_STREAM_TIME)
                ) > 0 THEN (
                        SUM(VIDEO_STREAM_DATA) + SUM(HTTPS_VIDEO_STREAM_DATA) + SUM(QUIC_VIDEO_STREAM_DATA)
                ) /(
                        SUM(VIDEO_STREAM_TIME) + SUM(HTTPS_VIDEO_STREAM_TIME) + SUM(QUIC_VIDEO_STREAM_TIME)
                )
                ELSE 0
        END AS V_DL_THP,
        CASE
                WHEN (
                        SUM(VIDEO_STREAM_SUCC) + SUM(HTTPS_VIDEO_STREAM_SUCC) + SUM(QUIC_VIDEO_STREAM_SUCC)
                ) > 0 THEN (
                        SUM(VIDEO_HD_NUM) + SUM(HTTPS_VIDEO_HD_NUM) + SUM(QUIC_VIDEO_HD_NUM)
                ) /(
                        SUM(VIDEO_STREAM_SUCC) + SUM(HTTPS_VIDEO_STREAM_SUCC) + SUM(QUIC_VIDEO_STREAM_SUCC)
                )
                ELSE 0
        END AS V_HD_RT,
        CASE
                WHEN (
                        SUM(VIDEO_STREAM_SUCC) + SUM(HTTPS_VIDEO_STREAM_SUCC) + SUM(QUIC_VIDEO_STREAM_SUCC)
                ) > 0 THEN (
                        SUM(VIDEO_SD_NUM) + SUM(HTTPS_VIDEO_SD_NUM) + SUM(QUIC_VIDEO_SD_NUM)
                ) /(
                        SUM(VIDEO_STREAM_SUCC) + SUM(HTTPS_VIDEO_STREAM_SUCC) + SUM(QUIC_VIDEO_STREAM_SUCC)
                )
                ELSE 0
        END AS V_SD_RT,
        CASE
                WHEN (
                        SUM(VIDEO_STREAM_SUCC) + SUM(HTTPS_VIDEO_STREAM_SUCC) + SUM(QUIC_VIDEO_STREAM_SUCC)
                ) > 0 THEN (
                        SUM(VIDEO_LD_NUM) + SUM(HTTPS_VIDEO_LD_NUM) + SUM(QUIC_VIDEO_LD_NUM)
                ) /(
                        SUM(VIDEO_STREAM_SUCC) + SUM(HTTPS_VIDEO_STREAM_SUCC) + SUM(QUIC_VIDEO_STREAM_SUCC)
                )
                ELSE 0
        END AS V_LD_RT
FROM
        (
                SELECT
                        DATE_TIME.QUARTER_ID,
                        CELL.TTML,
                        CELL.NOC,
                        CELL.PROVINCE,
                        CELL.DISTRICT,
                        CELL.SITENAME,
                        CELL.CELL_NODE_ID,
                        SUM(WEB_TOTAL) WEB_TOTAL,
                        SUM(MDO_VIDEO_COUNT) MDO_VIDEO_COUNT,
                        SUM(WEB_RESP_SUCC) WEB_RESP_SUCC,
                        SUM(WEB_AVG_RESP_DELAY) WEB_AVG_RESP_DELAY,
                        SUM(WEB_AVG_DISPLAY_DELAY) WEB_AVG_DISPLAY_DELAY,
                        SUM(WEB_DL_TIME) WEB_DL_TIME,
                        SUM(WEB_DL_DATA) WEB_DL_DATA,
                        SUM(WEB_BROWS_SUCC) WEB_BROWS_SUCC,
                        SUM(WEB_BROWS_TOTAL) WEB_BROWS_TOTAL,
                        SUM(VIDEO_STREAM_SUCC) VIDEO_STREAM_SUCC,
                        SUM(VIDEO_STREAM_TOTAL) VIDEO_STREAM_TOTAL,
                        SUM(VIDEO_STREAM_START_DELAY) VIDEO_STREAM_START_DELAY,
                        SUM(VIDEO_STREAM_STALL) VIDEO_STREAM_STALL,
                        SUM(VIDEO_STREAM_DATA) VIDEO_STREAM_DATA,
                        SUM(VIDEO_STREAM_TIME) VIDEO_STREAM_TIME,
                        SUM(VIDEO_HD_NUM) VIDEO_HD_NUM,
                        SUM(VIDEO_SD_NUM) VIDEO_SD_NUM,
                        SUM(VIDEO_LD_NUM) VIDEO_LD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_WEB_DL_DATA,
                        CAST(0 AS BIGINT) AS HTTPS_WEB_DL_TIME,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_DATA,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_TIME,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_HD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_SD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_LD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_SUCC,
                        CAST(0 AS DOUBLE) AS QUIC_WEB_DL_DATA,
                        CAST(0 AS DOUBLE) AS QUIC_WEB_DL_TIME,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_STREAM_DATA,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_STREAM_TIME,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_HD_NUM,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_SD_NUM,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_LD_NUM,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_STREAM_SUCC
                FROM
                        FACT_HTTP_MDO_TRANS_LOG_END_HOUR FACT
                        INNER JOIN DIM_CELL_INFO_CELLS_CURRENT CELL ON FACT.CELL_NODE_ID = CELL.CELL_NODE_ID
                        INNER JOIN DIM_DATE_TIME DATE_TIME ON FACT.DATETIME_ID = DATE_TIME.DATETIME_ID
                GROUP BY
                        DATE_TIME.QUARTER_ID,
                        GROUPING SETS (
                                (CELL.TTML),
                                (CELL.TTML, CELL.NOC),
                                (CELL.TTML, CELL.NOC, CELL.PROVINCE),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME,
                                        CELL.CELL_NODE_ID
                                )
                        )
                UNION
                ALL
                SELECT
                        DATE_TIME.QUARTER_ID,
                        CELL.TTML,
                        CELL.NOC,
                        CELL.PROVINCE,
                        CELL.DISTRICT,
                        CELL.SITENAME,
                        CELL.CELL_NODE_ID,
                        CAST(0 AS DOUBLE) AS WEB_TOTAL,
                        CAST(0 AS DOUBLE) AS MDO_VIDEO_COUNT,
                        CAST(0 AS DOUBLE) AS WEB_RESP_SUCC,
                        CAST(0 AS DOUBLE) AS WEB_AVG_RESP_DELAY,
                        CAST(0 AS DOUBLE) AS WEB_AVG_DISPLAY_DELAY,
                        CAST(0 AS DOUBLE) AS WEB_DL_TIME,
                        CAST(0 AS DOUBLE) AS WEB_DL_DATA,
                        CAST(0 AS DOUBLE) AS WEB_BROWS_SUCC,
                        CAST(0 AS DOUBLE) AS WEB_BROWS_TOTAL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_SUCC,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_TOTAL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_START_DELAY,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_STALL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_DATA,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_TIME,
                        CAST(0 AS DOUBLE) AS VIDEO_HD_NUM,
                        CAST(0 AS DOUBLE) AS VIDEO_SD_NUM,
                        CAST(0 AS DOUBLE) AS VIDEO_LD_NUM,
                        SUM(HTTPS_WEB_DL_DATA) HTTPS_WEB_DL_DATA,
                        SUM(HTTPS_WEB_DL_TIME) HTTPS_WEB_DL_TIME,
                        SUM(HTTPS_VIDEO_STREAM_DATA) HTTPS_VIDEO_STREAM_DATA,
                        SUM(HTTPS_VIDEO_STREAM_TIME) HTTPS_VIDEO_STREAM_TIME,
                        SUM(HTTPS_VIDEO_HD_NUM) HTTPS_VIDEO_HD_NUM,
                        SUM(HTTPS_VIDEO_SD_NUM) HTTPS_VIDEO_SD_NUM,
                        SUM(HTTPS_VIDEO_LD_NUM) HTTPS_VIDEO_LD_NUM,
                        SUM(HTTPS_VIDEO_STREAM_SUCC) HTTPS_VIDEO_STREAM_SUCC,
                        CAST(0 AS DOUBLE) AS QUIC_WEB_DL_DATA,
                        CAST(0 AS DOUBLE) AS QUIC_WEB_DL_TIME,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_STREAM_DATA,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_STREAM_TIME,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_HD_NUM,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_SD_NUM,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_LD_NUM,
                        CAST(0 AS DOUBLE) AS QUIC_VIDEO_STREAM_SUCC
                FROM
                        FACT_HTTPS_MDO_TRANS_LOG_END_HOUR FACT
                        INNER JOIN DIM_CELL_INFO_CELLS_CURRENT CELL ON FACT.CELL_NODE_ID = CELL.CELL_NODE_ID
                        INNER JOIN DIM_DATE_TIME DATE_TIME ON FACT.DATETIME_ID = DATE_TIME.DATETIME_ID
                GROUP BY
                        DATE_TIME.QUARTER_ID,
                        GROUPING SETS (
                                (CELL.TTML),
                                (CELL.TTML, CELL.NOC),
                                (CELL.TTML, CELL.NOC, CELL.PROVINCE),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME,
                                        CELL.CELL_NODE_ID
                                )
                        )
                UNION
                ALL
                SELECT
                        DATE_TIME.QUARTER_ID,
                        CELL.TTML,
                        CELL.NOC,
                        CELL.PROVINCE,
                        CELL.DISTRICT,
                        CELL.SITENAME,
                        CELL.CELL_NODE_ID,
                        CAST(0 AS DOUBLE) AS WEB_TOTAL,
                        CAST(0 AS DOUBLE) AS MDO_VIDEO_COUNT,
                        CAST(0 AS DOUBLE) AS WEB_RESP_SUCC,
                        CAST(0 AS DOUBLE) AS WEB_AVG_RESP_DELAY,
                        CAST(0 AS DOUBLE) AS WEB_AVG_DISPLAY_DELAY,
                        CAST(0 AS DOUBLE) AS WEB_DL_TIME,
                        CAST(0 AS DOUBLE) AS WEB_DL_DATA,
                        CAST(0 AS DOUBLE) AS WEB_BROWS_SUCC,
                        CAST(0 AS DOUBLE) AS WEB_BROWS_TOTAL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_SUCC,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_TOTAL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_START_DELAY,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_STALL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_DATA,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_TIME,
                        CAST(0 AS DOUBLE) AS VIDEO_HD_NUM,
                        CAST(0 AS DOUBLE) AS VIDEO_SD_NUM,
                        CAST(0 AS DOUBLE) AS VIDEO_LD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_WEB_DL_DATA,
                        CAST(0 AS BIGINT) AS HTTPS_WEB_DL_TIME,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_DATA,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_TIME,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_HD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_SD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_LD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_SUCC,
                        SUM(QUIC_WEB_DL_DATA) QUIC_WEB_DL_DATA,
                        SUM(QUIC_WEB_DL_TIME) QUIC_WEB_DL_TIME,
                        SUM(QUIC_VIDEO_STREAM_DATA) QUIC_VIDEO_STREAM_DATA,
                        SUM(QUIC_VIDEO_STREAM_TIME) QUIC_VIDEO_STREAM_TIME,
                        SUM(QUIC_VIDEO_HD_NUM) QUIC_VIDEO_HD_NUM,
                        SUM(QUIC_VIDEO_SD_NUM) QUIC_VIDEO_SD_NUM,
                        SUM(QUIC_VIDEO_LD_NUM) QUIC_VIDEO_LD_NUM,
                        SUM(QUIC_VIDEO_STREAM_SUCC) QUIC_VIDEO_STREAM_SUCC
                FROM
                        FACT_QUIC_MDO_TRANS_LOG_END_HOUR FACT
                        INNER JOIN DIM_CELL_INFO_CELLS_CURRENT CELL ON FACT.CELL_NODE_ID = CELL.CELL_NODE_ID
                        INNER JOIN DIM_DATE_TIME DATE_TIME ON FACT.DATETIME_ID = DATE_TIME.DATETIME_ID
                GROUP BY
                        DATE_TIME.QUARTER_ID,
                        GROUPING SETS (
                                (CELL.TTML),
                                (CELL.TTML, CELL.NOC),
                                (CELL.TTML, CELL.NOC, CELL.PROVINCE),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME,
                                        CELL.CELL_NODE_ID
                                )
                        )
                UNION
                ALL
                SELECT
                        DATE_TIME.QUARTER_ID,
                        CELL.TTML,
                        CELL.NOC,
                        CELL.PROVINCE,
                        CELL.DISTRICT,
                        CELL.SITENAME,
                        CELL.CELL_NODE_ID,
                        CAST(0 AS DOUBLE) AS WEB_TOTAL,
                        CAST(0 AS DOUBLE) AS MDO_VIDEO_COUNT,
                        CAST(0 AS DOUBLE) AS WEB_RESP_SUCC,
                        CAST(0 AS DOUBLE) AS WEB_AVG_RESP_DELAY,
                        CAST(0 AS DOUBLE) AS WEB_AVG_DISPLAY_DELAY,
                        CAST(0 AS DOUBLE) AS WEB_DL_TIME,
                        CAST(0 AS DOUBLE) AS WEB_DL_DATA,
                        CAST(0 AS DOUBLE) AS WEB_BROWS_SUCC,
                        CAST(0 AS DOUBLE) AS WEB_BROWS_TOTAL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_SUCC,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_TOTAL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_START_DELAY,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_STALL,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_DATA,
                        CAST(0 AS DOUBLE) AS VIDEO_STREAM_TIME,
                        CAST(0 AS DOUBLE) AS VIDEO_HD_NUM,
                        CAST(0 AS DOUBLE) AS VIDEO_SD_NUM,
                        CAST(0 AS DOUBLE) AS VIDEO_LD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_WEB_DL_DATA,
                        CAST(0 AS BIGINT) AS HTTPS_WEB_DL_TIME,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_DATA,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_TIME,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_HD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_SD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_LD_NUM,
                        CAST(0 AS BIGINT) AS HTTPS_VIDEO_STREAM_SUCC,
                        SUM(QUIC_WEB_DL_DATA) QUIC_WEB_DL_DATA,
                        SUM(QUIC_WEB_DL_TIME) QUIC_WEB_DL_TIME,
                        SUM(QUIC_VIDEO_STREAM_DATA) QUIC_VIDEO_STREAM_DATA,
                        SUM(QUIC_VIDEO_STREAM_TIME) QUIC_VIDEO_STREAM_TIME,
                        SUM(QUIC_VIDEO_HD_NUM) QUIC_VIDEO_HD_NUM,
                        SUM(QUIC_VIDEO_SD_NUM) QUIC_VIDEO_SD_NUM,
                        SUM(QUIC_VIDEO_LD_NUM) QUIC_VIDEO_LD_NUM,
                        SUM(QUIC_VIDEO_STREAM_SUCC) QUIC_VIDEO_STREAM_SUCC
                FROM
                        FACT_OTHER_QUIC_MDO_TRANS_LOG_END_HOUR FACT
                        INNER JOIN DIM_CELL_INFO_CELLS_CURRENT CELL ON FACT.CELL_NODE_ID = CELL.CELL_NODE_ID
                        INNER JOIN DIM_DATE_TIME DATE_TIME ON FACT.DATETIME_ID = DATE_TIME.DATETIME_ID
                GROUP BY
                        DATE_TIME.QUARTER_ID,
                        GROUPING SETS (
                                (CELL.TTML),
                                (CELL.TTML, CELL.NOC),
                                (CELL.TTML, CELL.NOC, CELL.PROVINCE),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME
                                ),
                                (
                                        CELL.TTML,
                                        CELL.NOC,
                                        CELL.PROVINCE,
                                        CELL.DISTRICT,
                                        CELL.PROVINCE,
                                        CELL.SITENAME,
                                        CELL.CELL_NODE_ID
                                )
                        )
        ) ' + @filter + '
GROUP BY
        TTML,
        NOC,
        PROVINCE,
        DISTRICT,
        SITENAME,
        CELL_NODE_ID,
        QUARTER_ID'
	--select @filter
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

	MERGE mdo_cell_quarter AS Target
	USING (
		select quarter_id, isnull(ttml, '') ttml, isnull(noc, '') noc, isnull(province, '') province, isnull(district, '') district, 
			isnull(sitename, '') sitename, isnull(cell_node_id, '') cell_node_id, 
			WEB_TOTAL, MDO_VIDEO_COUNT, WEB_RESP_SUCC, WEB_AVG_RESP_DELAY, WEB_AVG_DISPLAY_DELAY, WEB_DL_TIME, WEB_DL_DATA, WEB_BROWS_SUCC, 
			WEB_BROWS_TOTAL, VIDEO_STREAM_SUCC, VIDEO_STREAM_TOTAL, VIDEO_STREAM_START_DELAY, VIDEO_STREAM_STALL, VIDEO_STREAM_DATA, VIDEO_STREAM_TIME, 
			VIDEO_HD_NUM, VIDEO_SD_NUM, VIDEO_LD_NUM, HTTPS_WEB_DL_DATA, HTTPS_WEB_DL_TIME, HTTPS_VIDEO_STREAM_DATA, HTTPS_VIDEO_STREAM_TIME, 
			HTTPS_VIDEO_HD_NUM, HTTPS_VIDEO_SD_NUM, HTTPS_VIDEO_LD_NUM, HTTPS_VIDEO_STREAM_SUCC, QUIC_WEB_DL_DATA, QUIC_WEB_DL_TIME, QUIC_VIDEO_STREAM_DATA, 
			QUIC_VIDEO_STREAM_TIME, QUIC_VIDEO_HD_NUM, QUIC_VIDEO_SD_NUM, QUIC_VIDEO_LD_NUM, QUIC_VIDEO_STREAM_SUCC, W_RES_SR, W_RES_DL, W_BRO_SR, W_DPL_DL, 
			W_DL_THP, V_STR_SR, V_STR_DL, V_STA_RT, V_STA_FR, V_DL_THP, V_HD_RT, V_SD_RT, V_LD_RT
		from (select context from @Results where context <> '[]') tb 
		outer apply OPENJSON (Context)  
		WITH (
			QUARTER_ID					varchar(7)	'$.QUARTER_ID',
			TTML						varchar(50)	'$.TTML',
			NOC							varchar(10)	'$.NOC',
			PROVINCE					varchar(255)		'$.PROVINCE',
			DISTRICT					varchar(255)		'$.DISTRICT',
			SITENAME					varchar(250)		'$.SITENAME',
			CELL_NODE_ID				bigint			'$.CELL_NODE_ID',
			WEB_TOTAL					float		'$.WEB_TOTAL',
			MDO_VIDEO_COUNT				float			'$.MDO_VIDEO_COUNT',
			WEB_RESP_SUCC				float			'$.WEB_RESP_SUCC',
			WEB_AVG_RESP_DELAY			float				'$.WEB_AVG_RESP_DELAY',
			WEB_AVG_DISPLAY_DELAY		float					'$.WEB_AVG_DISPLAY_DELAY',
			WEB_DL_TIME					float		'$.WEB_DL_TIME',
			WEB_DL_DATA					float		'$.WEB_DL_DATA',
			WEB_BROWS_SUCC				float			'$.WEB_BROWS_SUCC',
			WEB_BROWS_TOTAL				float			'$.WEB_BROWS_TOTAL',
			VIDEO_STREAM_SUCC			float				'$.VIDEO_STREAM_SUCC',
			VIDEO_STREAM_TOTAL			float				'$.VIDEO_STREAM_TOTAL',
			VIDEO_STREAM_START_DELAY	float						'$.VIDEO_STREAM_START_DELAY',
			VIDEO_STREAM_STALL			float				'$.VIDEO_STREAM_STALL',
			VIDEO_STREAM_DATA			float				'$.VIDEO_STREAM_DATA',
			VIDEO_STREAM_TIME			float				'$.VIDEO_STREAM_TIME',
			VIDEO_HD_NUM				float			'$.VIDEO_HD_NUM',
			VIDEO_SD_NUM				float			'$.VIDEO_SD_NUM',
			VIDEO_LD_NUM				float			'$.VIDEO_LD_NUM',
			HTTPS_WEB_DL_DATA			float				'$.HTTPS_WEB_DL_DATA',
			HTTPS_WEB_DL_TIME			float				'$.HTTPS_WEB_DL_TIME',
			HTTPS_VIDEO_STREAM_DATA		float					'$.HTTPS_VIDEO_STREAM_DATA',
			HTTPS_VIDEO_STREAM_TIME		float					'$.HTTPS_VIDEO_STREAM_TIME',
			HTTPS_VIDEO_HD_NUM			float				'$.HTTPS_VIDEO_HD_NUM',
			HTTPS_VIDEO_SD_NUM			float				'$.HTTPS_VIDEO_SD_NUM',
			HTTPS_VIDEO_LD_NUM			float				'$.HTTPS_VIDEO_LD_NUM',
			HTTPS_VIDEO_STREAM_SUCC		float					'$.HTTPS_VIDEO_STREAM_SUCC',
			QUIC_WEB_DL_DATA			float				'$.QUIC_WEB_DL_DATA',
			QUIC_WEB_DL_TIME			float				'$.QUIC_WEB_DL_TIME',
			QUIC_VIDEO_STREAM_DATA		float					'$.QUIC_VIDEO_STREAM_DATA',
			QUIC_VIDEO_STREAM_TIME		float					'$.QUIC_VIDEO_STREAM_TIME',
			QUIC_VIDEO_HD_NUM			float				'$.QUIC_VIDEO_HD_NUM',
			QUIC_VIDEO_SD_NUM			float				'$.QUIC_VIDEO_SD_NUM',
			QUIC_VIDEO_LD_NUM			float				'$.QUIC_VIDEO_LD_NUM',
			QUIC_VIDEO_STREAM_SUCC		float					'$.QUIC_VIDEO_STREAM_SUCC',
			W_RES_SR					float		'$.W_RES_SR',
			W_RES_DL					float		'$.W_RES_DL',
			W_BRO_SR					float		'$.W_BRO_SR',
			W_DPL_DL					float		'$.W_DPL_DL',
			W_DL_THP					float		'$.W_DL_THP',
			V_STR_SR					float		'$.V_STR_SR',
			V_STR_DL					float		'$.V_STR_DL',
			V_STA_RT					float		'$.V_STA_RT',
			V_STA_FR					float		'$.V_STA_FR',
			V_DL_THP					float		'$.V_DL_THP',
			V_HD_RT						float	'$.V_HD_RT',
			V_SD_RT						float	'$.V_SD_RT',
			V_LD_RT						float	'$.V_LD_RT'			
		)
	) AS Source
	ON (
		Target.quarter_id = Source.quarter_id 
		and Target.ttml = Source.ttml
		and Target.noc = Source.noc
		and Target.province = Source.province
		and Target.district = Source.district
		and Target.sitename = Source.sitename
		and Target.cell_node_id = Source.cell_node_id
	)
	WHEN MATCHED THEN
		UPDATE SET 
			Target.WEB_TOTAL = Source.WEB_TOTAL,
			Target.MDO_VIDEO_COUNT = Source.MDO_VIDEO_COUNT,
			Target.WEB_RESP_SUCC = Source.WEB_RESP_SUCC,
			Target.WEB_AVG_RESP_DELAY = Source.WEB_AVG_RESP_DELAY,
			Target.WEB_AVG_DISPLAY_DELAY = Source.WEB_AVG_DISPLAY_DELAY,
			Target.WEB_DL_TIME = Source.WEB_DL_TIME,
			Target.WEB_DL_DATA = Source.WEB_DL_DATA,
			Target.WEB_BROWS_SUCC = Source.WEB_BROWS_SUCC,
			Target.WEB_BROWS_TOTAL = Source.WEB_BROWS_TOTAL,
			Target.VIDEO_STREAM_SUCC = Source.VIDEO_STREAM_SUCC,
			Target.VIDEO_STREAM_TOTAL = Source.VIDEO_STREAM_TOTAL,
			Target.VIDEO_STREAM_START_DELAY = Source.VIDEO_STREAM_START_DELAY,
			Target.VIDEO_STREAM_STALL = Source.VIDEO_STREAM_STALL,
			Target.VIDEO_STREAM_DATA = Source.VIDEO_STREAM_DATA,
			Target.VIDEO_STREAM_TIME = Source.VIDEO_STREAM_TIME,
			Target.VIDEO_HD_NUM = Source.VIDEO_HD_NUM,
			Target.VIDEO_SD_NUM = Source.VIDEO_SD_NUM,
			Target.VIDEO_LD_NUM = Source.VIDEO_LD_NUM,
			Target.HTTPS_WEB_DL_DATA = Source.HTTPS_WEB_DL_DATA,
			Target.HTTPS_WEB_DL_TIME = Source.HTTPS_WEB_DL_TIME,
			Target.HTTPS_VIDEO_STREAM_DATA = Source.HTTPS_VIDEO_STREAM_DATA,
			Target.HTTPS_VIDEO_STREAM_TIME = Source.HTTPS_VIDEO_STREAM_TIME,
			Target.HTTPS_VIDEO_HD_NUM = Source.HTTPS_VIDEO_HD_NUM,
			Target.HTTPS_VIDEO_SD_NUM = Source.HTTPS_VIDEO_SD_NUM,
			Target.HTTPS_VIDEO_LD_NUM = Source.HTTPS_VIDEO_LD_NUM,
			Target.HTTPS_VIDEO_STREAM_SUCC = Source.HTTPS_VIDEO_STREAM_SUCC,
			Target.QUIC_WEB_DL_DATA = Source.QUIC_WEB_DL_DATA,
			Target.QUIC_WEB_DL_TIME = Source.QUIC_WEB_DL_TIME,
			Target.QUIC_VIDEO_STREAM_DATA = Source.QUIC_VIDEO_STREAM_DATA,
			Target.QUIC_VIDEO_STREAM_TIME = Source.QUIC_VIDEO_STREAM_TIME,
			Target.QUIC_VIDEO_HD_NUM = Source.QUIC_VIDEO_HD_NUM,
			Target.QUIC_VIDEO_SD_NUM = Source.QUIC_VIDEO_SD_NUM,
			Target.QUIC_VIDEO_LD_NUM = Source.QUIC_VIDEO_LD_NUM,
			Target.QUIC_VIDEO_STREAM_SUCC = Source.QUIC_VIDEO_STREAM_SUCC,
			Target.W_RES_SR = Source.W_RES_SR,
			Target.W_RES_DL = Source.W_RES_DL,
			Target.W_BRO_SR = Source.W_BRO_SR,
			Target.W_DPL_DL = Source.W_DPL_DL,
			Target.W_DL_THP = Source.W_DL_THP,
			Target.V_STR_SR = Source.V_STR_SR,
			Target.V_STR_DL = Source.V_STR_DL,
			Target.V_STA_RT = Source.V_STA_RT,
			Target.V_STA_FR = Source.V_STA_FR,
			Target.V_DL_THP = Source.V_DL_THP,
			Target.V_HD_RT = Source.V_HD_RT,
			Target.V_SD_RT = Source.V_SD_RT,
			Target.V_LD_RT = Source.V_LD_RT
	WHEN NOT MATCHED BY TARGET THEN
		insert (
			QUARTER_ID, TTML, NOC, PROVINCE, DISTRICT, SITENAME, CELL_NODE_ID, 
			WEB_TOTAL, MDO_VIDEO_COUNT, WEB_RESP_SUCC, WEB_AVG_RESP_DELAY, WEB_AVG_DISPLAY_DELAY, WEB_DL_TIME, WEB_DL_DATA, WEB_BROWS_SUCC, 
			WEB_BROWS_TOTAL, VIDEO_STREAM_SUCC, VIDEO_STREAM_TOTAL, VIDEO_STREAM_START_DELAY, VIDEO_STREAM_STALL, VIDEO_STREAM_DATA, VIDEO_STREAM_TIME, 
			VIDEO_HD_NUM, VIDEO_SD_NUM, VIDEO_LD_NUM, HTTPS_WEB_DL_DATA, HTTPS_WEB_DL_TIME, HTTPS_VIDEO_STREAM_DATA, HTTPS_VIDEO_STREAM_TIME, 
			HTTPS_VIDEO_HD_NUM, HTTPS_VIDEO_SD_NUM, HTTPS_VIDEO_LD_NUM, HTTPS_VIDEO_STREAM_SUCC, QUIC_WEB_DL_DATA, QUIC_WEB_DL_TIME, 
			QUIC_VIDEO_STREAM_DATA, QUIC_VIDEO_STREAM_TIME, QUIC_VIDEO_HD_NUM, QUIC_VIDEO_SD_NUM, QUIC_VIDEO_LD_NUM, QUIC_VIDEO_STREAM_SUCC, W_RES_SR, 
			W_RES_DL, W_BRO_SR, W_DPL_DL, W_DL_THP, V_STR_SR, V_STR_DL, V_STA_RT, V_STA_FR, V_DL_THP, V_HD_RT, V_SD_RT, V_LD_RT
		)
		values (
			Source.QUARTER_ID, Source.TTML, Source.NOC, Source.PROVINCE, Source.DISTRICT, Source.SITENAME, Source.CELL_NODE_ID, Source.WEB_TOTAL, 
			Source.MDO_VIDEO_COUNT, Source.WEB_RESP_SUCC, Source.WEB_AVG_RESP_DELAY, Source.WEB_AVG_DISPLAY_DELAY, Source.WEB_DL_TIME, Source.WEB_DL_DATA, 
			Source.WEB_BROWS_SUCC, Source.WEB_BROWS_TOTAL, Source.VIDEO_STREAM_SUCC, Source.VIDEO_STREAM_TOTAL, Source.VIDEO_STREAM_START_DELAY, 
			Source.VIDEO_STREAM_STALL, Source.VIDEO_STREAM_DATA, Source.VIDEO_STREAM_TIME, Source.VIDEO_HD_NUM, Source.VIDEO_SD_NUM, Source.VIDEO_LD_NUM, 
			Source.HTTPS_WEB_DL_DATA, Source.HTTPS_WEB_DL_TIME, Source.HTTPS_VIDEO_STREAM_DATA, Source.HTTPS_VIDEO_STREAM_TIME, Source.HTTPS_VIDEO_HD_NUM, 
			Source.HTTPS_VIDEO_SD_NUM, Source.HTTPS_VIDEO_LD_NUM, Source.HTTPS_VIDEO_STREAM_SUCC, Source.QUIC_WEB_DL_DATA, Source.QUIC_WEB_DL_TIME, 
			Source.QUIC_VIDEO_STREAM_DATA, Source.QUIC_VIDEO_STREAM_TIME, Source.QUIC_VIDEO_HD_NUM, Source.QUIC_VIDEO_SD_NUM, Source.QUIC_VIDEO_LD_NUM, 
			Source.QUIC_VIDEO_STREAM_SUCC, Source.W_RES_SR, Source.W_RES_DL, Source.W_BRO_SR, Source.W_DPL_DL, Source.W_DL_THP, Source.V_STR_SR, 
			Source.V_STR_DL, Source.V_STA_RT, Source.V_STA_FR, Source.V_DL_THP, Source.V_HD_RT, Source.V_SD_RT, Source.V_LD_RT
		)
	OUTPUT $action, Inserted.*, Deleted.*; 
end
go




exec sp_mdo_cell_QUARTER 0, 7, 1, 'noc_mdo', default, default, default, default, default, default, default 
exec sp_mdo_cell_quarter 0, 7, 1, 'noc_mdo', default, default, default, default, 'Buon Don dahdshgucgsbdgsja', 'DLBD05', 35374 
exec sp_mdo_cell_quarter 0, 7, 1, 'noc_mdo', default, default, default, default, 'Buon Don', 'DLBD05', 35374 
exec sp_mdo_cell_quarter 0, 7, 1, 'noc_mdo', default, default, default, default, null, 'DLBD05', 35374 

select * from mdo_cell_quarter


