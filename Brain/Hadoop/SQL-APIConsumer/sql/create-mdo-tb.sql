USE [TestDB]
GO

/****** Object:  Table [dbo].[mdo_district_date]    Script Date: 2020-06-25 10:26:31 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[mdo_district_date](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[date_id] [int] NULL,
	[ttml] [varchar](50) NULL,
	[noc] [varchar](50) NULL,
	[province] [varchar](50) NULL,
	[district] [varchar](50) NULL,
	[WEB_TOTAL] [bigint] NULL,
	[WEB_RESP_SUCC] [bigint] NULL,
	[W_RES_SR] [float] NULL,
 CONSTRAINT [PK_mdo_district_date] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

USE [TestDB]
GO

/****** Object:  Index [PK_mdo_district_date]    Script Date: 2020-06-25 10:26:41 AM ******/
ALTER TABLE [dbo].[mdo_district_date] ADD  CONSTRAINT [PK_mdo_district_date] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO

USE [TestDB]
GO

SET ANSI_PADDING ON
GO

/****** Object:  Index [NonClusteredIndex-date_id-ttml-noc-province-district]    Script Date: 2020-06-25 10:26:45 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [NonClusteredIndex-date_id-ttml-noc-province-district] ON [dbo].[mdo_district_date]
(
	[date_id] ASC,
	[ttml] ASC,
	[noc] ASC,
	[province] ASC,
	[district] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO


