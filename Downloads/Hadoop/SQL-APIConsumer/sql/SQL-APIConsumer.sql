https://github.com/geral2/SQL-APIConsumer

create database testdb

-- step 1
USE TestDB
GO
sp_configure 'clr enabled',1
RECONFIGURE

-- step 2
ALTER DATABASE TESTDB SET TRUSTWORTHY ON

-- step 3
CREATE ASSEMBLY [System.Runtime.Serialization]
AUTHORIZATION	dbo
FROM  N'C:\Windows\Microsoft.NET\Framework64\v4.0.30319\System.Runtime.Serialization.dll'
WITH PERMISSION_SET = UNSAFE--external_access

-- nếu có lỗi 15404 thì chạy cái cmd này trước rồi mới chạy cái step 3
USE [TestDB]
GO
EXEC dbo.sp_changedbowner @loginame = N'sa', @map = false
GO
WITH PERMISSION_SET = UNSAFE--external_access

-- step 4
CREATE ASSEMBLY [Newtonsoft.Json]
AUTHORIZATION dbo
FROM  N'C:\Windows\Microsoft.NET\Framework64\v4.0.30319\Newtonsoft.Json.dll'
WITH PERMISSION_SET = UNSAFE





-- step 1 
CREATE ASSEMBLY [API_Consumer]
AUTHORIZATION dbo
FROM  N'C:\CLR\API_Consumer.dll'
WITH PERMISSION_SET = UNSAFE



-- step 2
PRINT N'Creating [dbo].[Create_HMACSHA256]...';

GO
CREATE FUNCTION [dbo].[Create_HMACSHA256]
(@message NVARCHAR (MAX) NULL, @SecretKey NVARCHAR (MAX) NULL)
RETURNS NVARCHAR (MAX)
AS
 EXTERNAL NAME [API_Consumer].[UserDefinedFunctions].[Create_HMACSHA256]

 GO
PRINT N'Creating [dbo].[GetTimestamp]...';

GO
CREATE FUNCTION [dbo].[GetTimestamp]
( )
RETURNS NVARCHAR (MAX)
AS
 EXTERNAL NAME [API_Consumer].[UserDefinedFunctions].[GetTimestamp]

GO
	PRINT N'Creating [dbo].[fn_GetBytes]...';
GO
CREATE FUNCTION [dbo].fn_GetBytes
(@value NVARCHAR (MAX) NULL )
RETURNS NVARCHAR (MAX)
AS
 EXTERNAL NAME [API_Consumer].[UserDefinedFunctions].fn_GetBytes
GO

PRINT N'Creating [dbo].[APICaller_GET]...';
GO
CREATE PROCEDURE [dbo].[APICaller_GET]
@URL NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_GET]
go

PRINT N'Creating [dbo].[APICaller_POST]...';
GO
CREATE PROCEDURE [dbo].[APICaller_POST]
@URL NVARCHAR (MAX) NULL
,@JsonBody	NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_POST]
go
PRINT N'Creating [dbo].[APICaller_POSTAuth]...';
GO

CREATE PROCEDURE [dbo].[APICaller_POSTAuth]
@URL NVARCHAR (MAX) NULL
,@Token NVARCHAR (MAX) NULL
,@JsonBody	NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_POST_Auth]
go





PRINT N'Creating [dbo].[APICaller_GETAuth]...';
GO
CREATE PROCEDURE [dbo].[APICaller_GETAuth]
@URL NVARCHAR (MAX) NULL
,@Token NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_GET_Auth]

GO

PRINT N'Creating [dbo].[APICaller_GET_Headers]...';

GO
CREATE PROCEDURE [dbo].[APICaller_GET_Headers]
@URL NVARCHAR (MAX) NULL, @Headers NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_GET_Headers]

GO

PRINT N'Creating [dbo].[APICaller_GET_Headers_BODY]...';

GO

CREATE PROCEDURE [dbo].[APICaller_GET_Headers_BODY]
@URL NVARCHAR (MAX) NULL, @JsonBody NVARCHAR (MAX) NULL, @Headers NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].APICaller_GET_JsonBody_Header
GO

PRINT N'Creating [dbo].[APICaller_POST_Headers]...';
GO
CREATE PROCEDURE [dbo].[APICaller_POST_Headers]
@URL NVARCHAR (MAX) NULL, @Headers NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].APICaller_POST_Headers

GO
PRINT N'Creating [dbo].[APICaller_GET_Extended]...';
GO
CREATE PROCEDURE [dbo].[APICaller_GET_Extended]
@URL NVARCHAR (MAX) NULL, @JsonBody NVARCHAR (MAX) NULL, @Headers NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_GET_Extended]

GO
PRINT N'Creating [dbo].[APICaller_POST_Extended]...';
GO
CREATE PROCEDURE [dbo].[APICaller_POST_Extended]
@URL NVARCHAR (MAX) NULL, @Headers NVARCHAR (MAX) NULL, @JsonBody NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].[APICaller_POST_Extended]

GO
PRINT N'Creating [dbo].[APICaller_POST_Encoded]...';
GO

CREATE PROCEDURE [dbo].APICaller_POST_Encoded
  @URL		NVARCHAR (MAX) NULL
, @Headers	NVARCHAR (MAX) NULL
, @JsonBody NVARCHAR (MAX) NULL
AS EXTERNAL NAME [API_Consumer].[StoredProcedures].APICaller_POST_Encoded
go


-- sample 
DECLARE @RoutingNumber AS VARCHAR(50) = '122242597'

--Public API: routingnumbers.info
DECLARE @Url  VARCHAR(200) = CONCAT('https://www.routingnumbers.info/api/name.json?','rn=',@RoutingNumber) 

DECLARE @Results AS TABLE
(
	Context varchar(max)
)

DECLARE @Result AS VARCHAR(MAX)

INSERT INTO @Results
EXEC  [dbo].[APICaller_GET] @Url

--Result: Row per value 

 SELECT  B.*
  FROM (
			SELECT Context 
			  from @Results
		)tb
	OUTER APPLY OPENJSON  (context) B

--Result: column per value.
SELECT 
		[name]	
		,[rn]		
		,[message]	
		,[code]	
 FROM (
			SELECT Context 
			  from @Results
		)tb
	OUTER APPLY OPENJSON  (context)  
  WITH
    ( [name]		VARCHAR(20) '$.name'
	, [rn]			VARCHAR(20) '$.rn'
	, [message]		VARCHAR(20) '$.message'
	, [code]		INT			'$.code'
    );

