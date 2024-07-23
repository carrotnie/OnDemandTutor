--CREATE DATABASE ON_DEMAND_TUTOR
--USE ON_DEMAND_TUTOR
--DROP DATABASE ON_DEMAND_TUTOR
CREATE TABLE Account
(
  Id INT NOT NULL IDENTITY(1,1),
  [Name] NVARCHAR(100) NOT NULL,
  Username VARCHAR(100) NOT NULL UNIQUE,
  [Password] VARCHAR(64) NOT NULL,
  [Role] NVARCHAR(20) NOT NULL,--học sinh, giáo viên, quản trị viên, kiểm duyệt viên
  Otp VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (Id)
);

-- Tạo các bảng khác liên quan
CREATE TABLE Tutor
(
  Id INT NOT NULL IDENTITY(1,1),
  AccountId INT NOT NULL,
  Active NVARCHAR(20) NOT NULL, --đã kiểm duyệt, chưa kiểm duyệt, bị từ chối (moderator phụ trách) 
  PRIMARY KEY (Id),
  FOREIGN KEY (AccountId) REFERENCES Account(Id)
);

CREATE TABLE Student
(
  Id INT NOT NULL IDENTITY(1,1),
  AccountId INT NOT NULL,
  Yob INT NOT NULL, --năm sinh
  [Location] NVARCHAR(255) COLLATE Latin1_General_100_CI_AI_SC_UTF8 NOT NULL,--địa chỉ cụ thể 
  Gender NVARCHAR(4) COLLATE Latin1_General_100_CI_AI_SC_UTF8 NOT NULL,
  PhoneNumber CHAR(15),
  Grade INT NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (AccountId) REFERENCES Account(Id)
);

CREATE TABLE Admin
(
  Id INT NOT NULL IDENTITY(1,1),
  AccountId INT NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (AccountId) REFERENCES Account(Id)
);

CREATE TABLE Moderator
(
  Id INT NOT NULL IDENTITY(1,1),
  AccountId INT NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (AccountId) REFERENCES Account(Id)
);

CREATE TABLE CV
(
  Id INT NOT NULL IDENTITY(1,1),
  TutorId INT NOT NULL,
  ModId INT NOT NULL,
  PhoneNumber CHAR(15) NOT NULL,
  Yob INT NOT NULL,--year
  [Location] NVARCHAR(20) NOT NULL, --chỉ lưu trữ thành phố 
  Personal_ID CHAR(12) NOT NULL,
  Gender NVARCHAR(4) NOT NULL,
  Experience INT NOT NULL,
  Grade NVARCHAR(10) NOT NULL,
  CreateTime DATETIME DEFAULT GETDATE(),--Ngày Cv này được tutor nộp để sắp xếp cho mod duyệt những bài đăng cũ nhất
  Content NVARCHAR(300) NOT NULL,--Nội dung quảng cáo cho giáo viên được hiển thị trên trang học sinh
  [Url] NVARCHAR(255) NOT NULL, -- Thêm cột Link để lưu trữ đường dẫn
  PRIMARY KEY (Id),
  FOREIGN KEY (ModId) REFERENCES Moderator(Id),
  FOREIGN KEY (TutorId) REFERENCES Tutor(Id)
);

CREATE TABLE Subject(
	Id INT NOT NULL IDENTITY (1,1),
	[Name] NVARCHAR(50) NOT NULL,
	PRIMARY KEY (Id)
);

CREATE TABLE TutorSubject(
	TutorId INT NOT NULL, 
	SubjectId INT NOT NULL,
	PRIMARY KEY (TutorId,SubjectId),
	FOREIGN KEY (TutorId) REFERENCES Tutor(Id),
	FOREIGN KEY (SubjectId) REFERENCES Subject(Id)
);

CREATE TABLE Rating(
    Id INT NOT NULL IDENTITY(1,1),
	TutorId INT NOT NULL,
	StudentId INT NOT NULL,
	SubjectId INT NOT NULL,
	Rating INT CHECK (Rating BETWEEN 1 AND 10), -- điểm rating từ 1 đến 10
	PRIMARY KEY (Id),
	FOREIGN KEY (TutorId) REFERENCES Tutor(Id),
	FOREIGN KEY (StudentId) REFERENCES Student(Id),
	FOREIGN KEY (SubjectId) REFERENCES Subject(Id)
);

CREATE TABLE Class(
	Id INT NOT NULL IDENTITY(1,1),
	TutorId INT NOT NULL,
	SubjectId INT NOT NULL,
	AmountOfSlot INT NOT NULL,--số ngày dạy của môn đó trong tuần 
	StartDay DATE, --ngày bắt đầu lớp học
	EndDay DATE, --ngày kết thúc lớp học
	PRIMARY KEY (Id),
	FOREIGN KEY (SubjectId) REFERENCES Subject(Id),
	FOREIGN KEY (TutorId) REFERENCES Tutor(Id)
);

CREATE TABLE Slot(
	Id INT NOT NULL IDENTITY(1,1),
	ClassId INT NOT NULL,
	DayOfSlot NVARCHAR(15) NOT NULL,--thứ trong tuần Vd: Thứ 2, thứ 3, ....
	StartTime TIME NOT NULL,--giờ ví dụ: 5h30
	EndTime TIME NOT NULL,-- ví dụ: 7h30
	Price DECIMAL(10, 3) DEFAULT 200000.000,
	PRIMARY KEY (Id),
	FOREIGN KEY (ClassId) REFERENCES Class(Id)
);

CREATE TABLE Complaint
(
  Id INT NOT NULL IDENTITY(1,1),
  StudentId INT NOT NULL,
  SlotId INT NOT NULL,
  TutorId INT NOT NULL,
  ModId INT NOT NULL,
  Content NVARCHAR(2000) NOT NULL,
  CreatedAt DATETIME DEFAULT GETDATE(),--Ngày tạo sẽ tự động được lưu là thời gian hiện tại
  [Status] NVARCHAR(20) NOT NULL DEFAULT N'đang xử lý',
  PRIMARY KEY (Id),
  FOREIGN KEY (StudentId) REFERENCES Student(Id),
  FOREIGN KEY (SlotId) REFERENCES Slot(Id),
  FOREIGN KEY (TutorId) REFERENCES Tutor(Id),
  FOREIGN KEY (ModId) REFERENCES Moderator(Id)
);

CREATE TABLE Schedule
(
  StudentId INT NOT NULL,
  SlotId INT NOT NULL,
  Status NVARCHAR(20) NOT NULL DEFAULT N'đang xử lý', -- Thuộc tính hiển thị tình trạng đăng ký
  PRIMARY KEY (StudentId, SlotId),
  FOREIGN KEY (StudentId) REFERENCES Student(Id),
  FOREIGN KEY (SlotId) REFERENCES Slot(Id)
);

CREATE TABLE Feedback
(
  Id INT NOT NULL IDENTITY(1,1),
  ModId INT NOT NULL,
  StudentId INT NOT NULL,
  SlotId INT NOT NULL,
  TutorId INT NOT NULL,
  FeedbackText NVARCHAR(2000) NOT NULL,--NỘI DUNG PHẢN HỒI CỦA HỌC SINH
  CreatedAt DATETIME DEFAULT GETDATE(),--NGÀY TẠO SẼ TỰ ĐỘNG ĐƯỢC LUU LÀ THỜI GIAN HIỆN TẠI
  PRIMARY KEY(Id),
  FOREIGN KEY (ModId) REFERENCES Moderator(Id),
  FOREIGN KEY (StudentId) REFERENCES Student(Id),
  FOREIGN KEY (SlotId) REFERENCES Slot(Id),
  FOREIGN KEY (TutorId) REFERENCES Tutor(Id)
);

CREATE TABLE BanAccount
(
  Id INT NOT NULL IDENTITY(1,1),
  ModId INT NOT NULL,
  AccountId INT NOT NULL,
  AmountOfReport INT NOT NULL,
  Status VARCHAR(20) NOT NULL DEFAULT 'active',--lock
  PRIMARY KEY (Id),
  FOREIGN KEY (AccountId) REFERENCES Account(Id),
  FOREIGN KEY (ModId) REFERENCES Moderator(Id),
  CONSTRAINT chk_AmountOfReport CHECK (AmountOfReport <= 3)
);

CREATE TABLE ReasonDenyCv
(
	Id INT NOT NULL IDENTITY(1,1),
	ModId INT NOT NULL,
	TutorId INT NOT NULL,
	Reason NVARCHAR(2000) NOT NULL,
	CreatedAt DATETIME DEFAULT GETDATE(),
	FOREIGN KEY (ModId) REFERENCES Moderator(Id),
	FOREIGN KEY (TutorId) REFERENCES Tutor(Id)
);

CREATE TABLE Wallet
(
  Id INT NOT NULL IDENTITY(1,1),
  StudentId INT NULL,
  AdminId INT NULL,
  Balance DECIMAL(10,3) DEFAULT 0.0,
  Status NVARCHAR(20) DEFAULT N'đang xử lý', --thành công
  PRIMARY KEY (Id),
  FOREIGN KEY (StudentId) REFERENCES Student(Id),
  FOREIGN KEY (AdminId) REFERENCES Admin(Id)
);

CREATE TABLE Salary
(
  Id INT NOT NULL IDENTITY(1,1),
  TutorId INT NOT NULL,
  SlotId INT NOT NULL,
  AdminId INT NOT NULL,
  Balance DECIMAL(10,3) DEFAULT 0.0,
  Status NVARCHAR(20) DEFAULT N'đang xử lý', --thành công
  CourseStatus NVARCHAR(20) DEFAULT N'chưa hoàn thành',--hoàn thành
  PRIMARY KEY(Id),
  FOREIGN KEY (TutorId) REFERENCES Tutor(Id),
  FOREIGN KEY (AdminId) REFERENCES Admin(Id),
  FOREIGN KEY (SlotId) REFERENCES Slot(Id),
)
-- Tạo bảng Payment để lưu trữ thông tin thanh toán của giáo viên
CREATE TABLE Payment
(
  Id INT NOT NULL IDENTITY(1,1),
  TutorId INT NOT NULL,
  AdminId INT NOT NULL,
  BankAccountNumber VARCHAR(15),
  Bank NVARCHAR(50),
  PRIMARY KEY (Id),
  FOREIGN KEY (TutorId) REFERENCES Tutor(Id),
  FOREIGN KEY (AdminId) REFERENCES Admin(Id),
);