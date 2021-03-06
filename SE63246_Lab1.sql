USE [master]
GO
/****** Object:  Database [SE63246_Lab1]    Script Date: 5/27/2022 8:41:38 PM ******/
CREATE DATABASE [SE63246_Lab1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SE63246_Lab1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SE63246_Lab1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SE63246_Lab1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SE63246_Lab1_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SE63246_Lab1] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SE63246_Lab1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SE63246_Lab1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET ARITHABORT OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SE63246_Lab1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SE63246_Lab1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SE63246_Lab1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SE63246_Lab1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SE63246_Lab1] SET  MULTI_USER 
GO
ALTER DATABASE [SE63246_Lab1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SE63246_Lab1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SE63246_Lab1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SE63246_Lab1] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SE63246_Lab1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SE63246_Lab1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SE63246_Lab1] SET QUERY_STORE = OFF
GO
USE [SE63246_Lab1]
GO
/****** Object:  Table [dbo].[tbl_Promotions]    Script Date: 5/27/2022 8:41:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Promotions](
	[Id] [uniqueidentifier] ROWGUIDCOL  NOT NULL,
	[Username] [nvarchar](50) NULL,
	[Rank] [int] NULL,
	[Status] [nvarchar](50) NULL,
	[CreateDate] [date] NULL,
 CONSTRAINT [PK_tbl_Promotions] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 5/27/2022 8:41:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](max) NOT NULL,
	[Role] [nvarchar](50) NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
	[Photo] [nvarchar](max) NULL,
	[Name] [nvarchar](200) NOT NULL,
	[Email] [nvarchar](250) NULL,
	[Phone] [nvarchar](12) NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[tbl_Promotions] ([Id], [Username], [Rank], [Status], [CreateDate]) VALUES (N'7ec8c804-6ea0-43b0-bf34-345de1380abe', N'chuong2', 9, N'Active', CAST(N'2022-05-27' AS Date))
INSERT [dbo].[tbl_Promotions] ([Id], [Username], [Rank], [Status], [CreateDate]) VALUES (N'4cd94f3c-e11b-48fa-bde7-e9fe6bbd202a', N'asss', 8, N'Active', CAST(N'2022-05-27' AS Date))
GO
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'admin', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Admin', N'Active', N'avatar-den-dep-2_015639673.png', N'chuong', N'chuong@gmail.com', N'0948972755')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'admin2', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Admin', N'Active', N'avatar-den-dep-2_015639673.png', N'Admin', N'admin1@gmail.com', N'0123456789')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'asss', N'd7ef0a04f3c8055644677299a9414a75adcb15916eb48417416c9317ace2ff4f', N'Sub', N'Active', N'avatar-den-dep-2_015639673.png', N'ssssssssss', N'ssssssss', N'sssssss')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'chuong', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Sub', N'Inactive', N'avatar-den-dep-2_015639673.png', N'chuong', N'chuong', N'011123121')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'chuong123', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Admin', N'Active', N'avatar-den-dep-2_015639673.png', N'chuong', N'chuong@gffff', N'01478522222')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'chuong2', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Sub', N'Active', N'avatar-den-dep-2_015639673.png', N'chuong', N'chuong', N'0123456789')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'onemore', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Sub', N'Active', N'ec1d3ed0415dd2ce8e07f96b36816054.jpg', N'test upload', N'test@gmail.com', N'0123456789')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'sub1', N'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', N'Sub', N'Active', N'ec1d3ed0415dd2ce8e07f96b36816054.jpg', N'Sub Sub Sub', N'chuong2@gmail.com', N'0123456789')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'sub2', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Sub', N'Active', N'avatar-den-dep-2_015639673.png', N'sub2', N'sub@gmail.com', N'0123456789')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N't', N'1', N'Sub', N'Active', N'ec1d3ed0415dd2ce8e07f96b36816054.jpg', N'e', N'e', N'456789789')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N't2', N'2', N'Sub', N'Active', N'ec1d3ed0415dd2ce8e07f96b36816054.jpg', N'q', N'q', N'456789123')
INSERT [dbo].[tbl_User] ([Username], [Password], [Role], [Status], [Photo], [Name], [Email], [Phone]) VALUES (N'test upload', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'Sub', N'Active', N'ec1d3ed0415dd2ce8e07f96b36816054.jpg', N'test upload', N'abc@gmail.com', N'123123123')
GO
ALTER TABLE [dbo].[tbl_Promotions] ADD  CONSTRAINT [DF_tbl_Promotions_Id]  DEFAULT (newid()) FOR [Id]
GO
USE [master]
GO
ALTER DATABASE [SE63246_Lab1] SET  READ_WRITE 
GO
