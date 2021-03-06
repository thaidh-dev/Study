Create database demoQLSV
go
USE demoQLSV
GO
/****** Object:  Table [dbo].[lop]    Script Date: 9/24/2018 12:40:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lop](
	[malop] [nchar](10) NOT NULL,
	[tenlop] [nvarchar](100) NOT NULL,
	[siso] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[malop] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ngoaingu]    Script Date: 9/24/2018 12:40:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ngoaingu](
	[mann] [nchar](10) NOT NULL,
	[tennn] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[mann] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sinhvien]    Script Date: 9/24/2018 12:40:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sinhvien](
	[masv] [nchar](10) NOT NULL,
	[tensv] [nvarchar](100) NOT NULL,
	[ngaysinh] [date] NULL,
	[malop] [nchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[masv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sv_nn]    Script Date: 9/24/2018 12:40:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sv_nn](
	[mann] [nchar](10) NOT NULL,
	[masv] [nchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[mann] ASC,
	[masv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[sinhvien]  WITH CHECK ADD FOREIGN KEY([malop])
REFERENCES [dbo].[lop] ([malop])
GO
ALTER TABLE [dbo].[sv_nn]  WITH CHECK ADD FOREIGN KEY([mann])
REFERENCES [dbo].[ngoaingu] ([mann])
GO
ALTER TABLE [dbo].[sv_nn]  WITH CHECK ADD FOREIGN KEY([masv])
REFERENCES [dbo].[sinhvien] ([masv])
GO
ALTER TABLE [dbo].[lop]  WITH CHECK ADD CHECK  (([siso]>(0)))
GO
