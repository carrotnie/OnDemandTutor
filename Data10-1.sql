--USE ON_DEMAND_TUTOR
SET IDENTITY_INSERT Account ON;
-- Chèn các tài khoản học sinh và giảng viên
INSERT INTO Account (Id, [Name], Username, [Password], [Role], Otp)
VALUES 
(1, N'Nguyễn Văn An', 'NguyenVanAn', 'An@1', 'student', NULL),
(2, N'Trần Thị Bình', 'TranThiBinh', 'Binh@2', 'student', NULL),
(3, N'Lê Minh Cường', 'LeMinhCuong', 'Cuong@3', 'student', NULL),
(4, N'Phạm Thị Dung', 'PhamThiDung', 'Dung@4', 'student', NULL),
(5, N'Huỳnh Văn Êm', 'HuynhVanEm', 'Em@5', 'student', NULL),
(6, N'Đặng Thị Phương', 'DangThiPhuong', 'Phuong@6', 'student', NULL),
(7, N'Bùi Minh Gia', 'BuiMinhGia', 'Gia@7', 'student', NULL),
(8, N'Võ Thị Hạnh', 'VoThiHanh', 'Hanh@8', 'student', NULL),
(9, N'Hoàng Văn Khang', 'HoangVanKhang', 'Khang@9', 'student', NULL),
(10, N'Nguyễn Thị Lan', 'NguyenThiLan', 'Lan@1', 'student', NULL),
(11, N'Ngô Văn Mạnh', 'NgoVanManh', 'Manh@2', 'student', NULL),
(12, N'Trần Thị Ngọc', 'TranThiNgoc', 'Ngoc@3', 'student', NULL),
(13, N'Lê Văn Nam', 'LeVanNam', 'Nam@4', 'student', NULL),
(14, N'Nguyễn Thị Oanh', 'NguyenThiOanh', 'Oanh@5', 'student', NULL),
(15, N'Phạm Văn Phú', 'PhamVanPhu', 'Phu@6', 'student', NULL),
(16, N'Vũ Thị Quỳnh', 'VuThiQuynh', 'Quynh@7', 'student', NULL),
(17, N'Đặng Văn Rồng', 'DangVanRong', 'Rong@8', 'student', NULL),
(18, N'Trần Thị Sáu', 'TranThiSau', 'Sau@9', 'student', NULL),
(19, N'Nguyễn Văn Tuấn', 'NguyenVanTuan', 'Tuan@1', 'student', NULL),
(20, N'Bùi Thị Vân', 'BuiThiVan', 'Van@2', 'student', NULL),
(21, N'Hoàng Văn Xương', 'HoangVanXuong', 'Xuong@3', 'student', NULL),
(22, N'Phạm Thị Yến', 'PhamThiYen', 'Yen@4', 'student', NULL),
(23, N'Nguyễn Minh Tâm', 'NguyenMinhTam', 'Tam@5', 'student', NULL),
(24, N'Lê Thị Thảo', 'LeThiThao', 'Thao@6', 'student', NULL),
(25, N'Trần Văn Thắng', 'TranVanThang', 'Thang@7', 'student', NULL),
(26, N'Nguyễn Thị Uyên', 'NguyenThiUyen1', 'Uyen@8', 'student', NULL),
(27, N'Lê Văn Việt', 'LeVanViet', 'Viet@9', 'student', NULL),
(28, N'Phan Thị Phương Anh', 'PhanThiPhuongAnh', 'Anh@1', 'student', NULL),
(29, N'Lê Thị Ngọc Ánh', 'LeThiNgocAnh', 'Anh@2', 'student', NULL),
(30, N'Nguyễn Văn Bảo', 'NguyenVanBao', 'Bao@3', 'student', NULL),
(31, N'Đinh Thị Bích', 'DinhThiBich', 'Bich@4', 'student', NULL),
(32, N'Nguyễn Văn Cảnh', 'NguyenVanCanh', 'Canh@5', 'student', NULL),
(33, N'Trần Thị Duyên', 'TranThiDuyen', 'Duyen@6', 'student', NULL),
(34, N'Lê Thị Hằng', 'LeThiHang', 'Hang@7', 'student', NULL),
(35, N'Bùi Văn Hiếu', 'BuiVanHieu', 'Hieu@8', 'student', NULL),
(36, N'Phạm Thị Hồng', 'PhamThiHong', 'Hong@9', 'student', NULL),
(37, N'Nguyễn Văn Hùng', 'NguyenVanHung1', 'Hung@1', 'student', NULL),
(38, N'Hoàng Thị Kim', 'HoangThiKim', 'Kim@2', 'student', NULL),
(39, N'Trần Văn Lâm', 'TranVanLam', 'Lam@3', 'student', NULL),
(40, N'Vũ Thị Linh', 'VuThiLinh', 'Linh@4', 'student', NULL),
(41, N'Nguyễn Thị Mai', 'NguyenThiMai', 'Mai@5', 'student', NULL),
(42, N'Bùi Văn Nam', 'BuiVanNam', 'Nam@6', 'student', NULL),
(43, N'Trần Thị Ngân', 'TranThiNgan', 'Ngan@7', 'student', NULL),
(44, N'Nguyễn Văn Ôn', 'NguyenVanOn', 'On@8', 'student', NULL),
(45, N'Lê Thị Phương', 'LeThiPhuong', 'Phuong@9', 'student', NULL),
(46, N'Trần Văn Quân', 'TranVanQuan', 'Quan@1', 'student', NULL),
(47, N'Nguyễn Thị Rạng', 'NguyenThiRang', 'Rang@2', 'student', NULL),
(48, N'Bùi Văn Sơn', 'BuiVanSon', 'Son@3', 'student', NULL),
(49, N'Trần Thị Tâm', 'TranThiTam', 'Tam@4', 'student', NULL),
(50, N'Nguyễn Văn Thanh', 'NguyenVanThanh', 'Thanh@5', 'student', NULL),

-- Inserting tutor accounts with Otp as NULL
(51, N'Lê Thị Anh', 'LeThiAnh', 'Anh@6', 'tutor', NULL),
(52, N'Trần Văn Bảo', 'TranVanBao', 'Bao@7', 'tutor', NULL),
(53, N'Nguyễn Thị Cẩm', 'NguyenThiCam', 'Cam@8', 'tutor', NULL),
(54, N'Phạm Văn Đạt', 'PhamVanDat', 'Dat@9', 'tutor', NULL),
(55, N'Lê Thị Diệu', 'LeThiDieu', 'Dieu@1', 'tutor', NULL),
(56, N'Vũ Văn Giang', 'VuVanGiang', 'Giang@2', 'tutor', NULL),
(57, N'Nguyễn Văn Hải', 'NguyenHai', 'Hai@3', 'tutor', NULL),
(58, N'Bùi Thị Hoa', 'BuiThiHoa', 'Hoa@4', 'tutor', NULL),
(59, N'Lê Văn Hòa', 'LeVanHoa', 'Hoa@5', 'tutor', NULL),
(60, N'Nguyễn Thị Hương', 'NguyenThiHuong', 'Huong@6', 'tutor', NULL),
(61, N'Trần Văn Kiên', 'TranVanKien', 'Kien@7', 'tutor', NULL),
(62, N'Bùi Ngọc Loan', 'BuiLoan', 'Loan@8', 'tutor', NULL),
(63, N'Nguyễn Văn Lộc', 'NguyenVanLoc', 'Loc@9', 'tutor', NULL),
(64, N'Hoàng Thị Mai', 'HoangThiMai', 'Mai@1', 'tutor', NULL),
(65, N'Phạm Văn Mạnh', 'PhamVanManh', 'Manh@2', 'tutor', NULL),
(66, N'Trần Thị Minh', 'TranThiMinh', 'Minh@3', 'tutor', NULL),
(67, N'Lê Văn Nam', 'LeNam', 'Nam@4', 'tutor', NULL),
(68, N'Nguyễn Thị Nga', 'NguyenThiNga', 'Nga@5', 'tutor', NULL),
(69, N'Trần Văn Ngọc', 'TranVanNgoc', 'Ngoc@6', 'tutor', NULL),
(70, N'Lê Ánh Sen', 'LeSen', 'Sen@7', 'tutor', NULL),
(71, N'Nguyễn Văn Sơn', 'NguyenVanSon', 'Son@8', 'tutor', NULL),
(72, N'Châu Ngọc Anh', 'Anh', 'Anh@9', 'tutor', NULL),
(73, N'Bùi Văn Tiến', 'BuiVanTien', 'Tien@1', 'tutor', NULL),
(74, N'Nguyễn Thị Tuyết', 'NguyenThiTuyet', 'Tuyet@2', 'tutor', NULL),
(75, N'Trần Văn Vượng', 'TranVanVuong', 'Vuong@3', 'tutor', NULL),
(76, N'Lê Thị Xuân', 'LeThiXuan', 'Xuan@4', 'tutor', NULL),
(77, N'Nguyễn Văn Yên', 'NguyenVanYen', 'Yen@5', 'tutor', NULL),
(78, N'Bùi Thị Ánh', 'BuiThiAnh', 'Anh@6', 'tutor', NULL),
(79, N'Hoàng Văn Bình', 'HoangVanBinh', 'Binh@7', 'tutor', NULL),
(80, N'Lê Thị Cẩm', 'LeThiCam', 'Cam@8', 'tutor', NULL),
(81, N'Nguyễn Văn Dũng', 'NguyenVanDung', 'Dung@9', 'tutor', NULL),
(82, N'Phạm Thị Em', 'PhamThiEm', 'Em@1', 'tutor', NULL),
(83, N'Trần Văn Giang', 'TranVanGiang', 'Giang@2', 'tutor', NULL),
(84, N'Lê Kiên Hải', 'LeThiHai', 'Hai@3', 'tutor', NULL),
(85, N'Nguyễn Văn Hùng', 'NguyenVanHung', 'Hung@4', 'tutor', NULL),
(86, N'Phạm Thị Hương', 'PhamThiHuong', 'Huong@5', 'tutor', NULL),
(87, N'Bùi Trung Kiên', 'TrungKien', 'Kien@6', 'tutor', NULL),
(88, N'Lê Thị Lan', 'LeThiLan', 'Lan@7', 'tutor', NULL),
(89, N'Nguyễn Văn Minh', 'NguyenVanMinh', 'Minh@8', 'tutor', NULL),
(90, N'Hoàng Thị Ngọc', 'HoangThiNgoc', 'Ngoc@9', 'tutor', NULL),
(91, N'Phạm Văn Oanh', 'PhamVanOanh', 'Oanh@1', 'tutor', NULL),
(92, N'Trần Thị Phương', 'TranThiPhuong', 'Phuong@2', 'tutor', NULL),
(93, N'Lê Văn Quân', 'LeVanQuan', 'Quan@3', 'tutor', NULL),
(94, N'Nguyễn Thị Quỳnh', 'NguyenThiQuynh', 'Quynh@4', 'tutor', NULL),
(95, N'Trần Văn Rồng', 'TranVanRong', 'Rong@5', 'tutor', NULL),
(96, N'Lê Thị Sen', 'LeThiSen', 'Sen@6', 'tutor', NULL),
(97, N'Nguyễn Văn Tâm', 'NguyenVanTam', 'Tam@7', 'tutor', NULL),
(98, N'Bùi Thị Thảo', 'BuiThiThao', 'Thao@8', 'tutor', NULL),
(99, N'Hoàng Văn Tiến', 'HoangVanTien', 'Tien@9', 'tutor', NULL),
(100, N'Nguyễn Thị Uyên', 'NguyenThiUyen', 'Uyen@1', 'tutor', NULL);

-- Inserting 2 admin accounts with Otp as NULL
INSERT INTO Account (Id, [Name], Username, [Password], [Role], Otp)
VALUES 
(101, 'Admin One', 'adminone', '1', 'admin', NULL);

-- Inserting 5 moderator accounts with Otp as NULL
INSERT INTO Account (Id, [Name], Username, [Password], [Role], Otp)
VALUES 
(102, 'Mod One', 'modone', 'password103', 'moderator', NULL);

-- Tắt IDENTITY_INSERT cho bảng Account
SET IDENTITY_INSERT Account OFF;


-- Bật IDENTITY_INSERT cho bảng Tutor
SET IDENTITY_INSERT Tutor ON;

-- Insert vào bảng Tutor dựa trên 50 tài khoản giảng viên
INSERT INTO Tutor (Id, AccountId, Active)
VALUES
(1, 51, N'đã kiểm duyệt'),
(2, 52, N'bị từ chối'),
(3, 53, N'đã kiểm duyệt'),
(4, 54, N'bị từ chối'),
(5, 55, N'đã kiểm duyệt'),
(6, 56, N'bị từ chối'),
(7, 57, N'đã kiểm duyệt'),
(8, 58, N'bị từ chối'),
(9, 59, N'đã kiểm duyệt'),
(10, 60, N'bị từ chối'),
(11, 61, N'đã kiểm duyệt'),
(12, 62, N'chưa kiểm duyệt'),
(13, 63, N'đã kiểm duyệt'),
(14, 64, N'chưa kiểm duyệt'),
(15, 65, N'đã kiểm duyệt'),
(16, 66, N'chưa kiểm duyệt'),
(17, 67, N'đã kiểm duyệt'),
(18, 68, N'chưa kiểm duyệt'),
(19, 69, N'đã kiểm duyệt'),
(20, 70, N'chưa kiểm duyệt'),
(21, 71, N'đã kiểm duyệt'),
(22, 72, N'chưa kiểm duyệt'),
(23, 73, N'đã kiểm duyệt'),
(24, 74, N'chưa kiểm duyệt'),
(25, 75, N'đã kiểm duyệt'),
(26, 76, N'chưa kiểm duyệt'),
(27, 77, N'đã kiểm duyệt'),
(28, 78, N'chưa kiểm duyệt'),
(29, 79, N'đã kiểm duyệt'),
(30, 80, N'chưa kiểm duyệt'),
(31, 81, N'đã kiểm duyệt'),
(32, 82, N'chưa kiểm duyệt'),
(33, 83, N'đã kiểm duyệt'),
(34, 84, N'chưa kiểm duyệt'),
(35, 85, N'đã kiểm duyệt'),
(36, 86, N'chưa kiểm duyệt'),
(37, 87, N'đã kiểm duyệt'),
(38, 88, N'chưa kiểm duyệt'),
(39, 89, N'đã kiểm duyệt'),
(40, 90, N'chưa kiểm duyệt'),
(41, 91, N'đã kiểm duyệt'),
(42, 92, N'chưa kiểm duyệt'),
(43, 93, N'đã kiểm duyệt'),
(44, 94, N'chưa kiểm duyệt'),
(45, 95, N'đã kiểm duyệt'),
(46, 96, N'chưa kiểm duyệt'),
(47, 97, N'đã kiểm duyệt'),
(48, 98, N'chưa kiểm duyệt'),
(49, 99, N'đã kiểm duyệt'),
(50, 100, N'chưa kiểm duyệt');

-- Tắt IDENTITY_INSERT cho bảng Tutor
SET IDENTITY_INSERT Tutor OFF;

-- Bật IDENTITY_INSERT cho bảng Student
SET IDENTITY_INSERT Student ON;

-- Insert vào bảng Student dựa trên 50 tài khoản học sinh
INSERT INTO Student (Id, AccountId, Yob, [Location], Gender, PhoneNumber, Grade)
VALUES
(1, 1, 2006, N'101 đường Lê Lợi, Q1, TP.HCM', N'Nam', N'0823482311', 12), 
(2, 2, 2007, N'201 đường Trần Hưng Đạo, Q2, TP.HCM', N'Nam', N'0348589322', 11),
(3, 3, 2008, N'310 đường Nguyễn Huệ, Q3, TP.HCM', N'Nam', N'0234567890', 10),
(4, 4, 2006, N'40 đường Lý Tự Trọng, Q4, TP.HCM', N'Nam', N'0345678901', 12), 
(5, 5, 2007, N'50 đường Pasteur, Q5, TP.HCM', N'Nam', N'0924824913', 11),
-- 5 Nữ từ lớp 12 đến 10 (2006-2008)
(6, 6, 2008, N'60 đường Điện Biên Phủ, Q6, TP.HCM', N'Nữ', N'0326682410', 10),
(7, 7, 2006, N'02 đường Phan Đình Phùng, Q7, TP.HCM', N'Nữ', N'0673829931', 12), 
(8, 8, 2007, N'810 đường Võ Thị Sáu, Q8, TP.HCM', N'Nữ', N'0876543210', 11),
(9, 9, 2008, N'90 đường Nam Kỳ Khởi Nghĩa, Q9, TP.HCM', N'Nữ', N'0987654321', 10),
(10, 10, 2006, N'100 đường Võ Văn Tần, Q10, TP.HCM', N'Nữ', N'0432165498', 12),
-- 8 Nam từ lớp 9 đến 6 (2009-2012)
(11, 11, 2009, N'1 đường Cống Quỳnh, Q11, TP.HCM', N'Nam', N'0765432109', 9), 
(12, 12, 2010, N'20 đường Bùi Thị Xuân, Q12, TP.HCM', N'Nam', N'0234987651', 8),
(13, 13, 2011, N'13 đường Trần Quốc Thảo, Q.Bình Thạnh, TP.HCM', N'Nam', N'0879654321', 7),
(14, 14, 2012, N'40 đường Lê Lợi, Q.Tân Bình, TP.HCM', N'Nam', N'0786543210', 6),
(15, 15, 2009, N'15 đường Nguyễn Thị Minh Khai, Q.Bình Tân, TP.HCM', N'Nam', N'0897643210', 9), 
(16, 16, 2010, N'160 đường Võ Văn Kiệt, Q.Thủ Đức, TP.HCM', N'Nam', N'0214768953', 8),
(17, 17, 2011, N'10 đường Bùi Viện, Q.Tân Phú, TP.HCM', N'Nam', N'0342897651', 7),
(18, 18, 2012, N'180 đường Phạm Ngũ Lão, Q.Gò Vấp, TP.HCM', N'Nam', N'0432165498', 6),
-- 8 Nữ từ lớp 9 đến 6 (2009-2012)
(19, 19, 2009, N'90 đường Lê Thánh Tôn, Q.Phú Nhuận, TP.HCM', N'Nữ', N'0567894321', 9),
(20, 20, 2010, N'200 đường Hàm Nghi, Q1, TP.HCM', N'Nữ', N'0654312378', 8),
(21, 21, 2011, N'212 đường Tôn Đức Thắng, Q2, TP.HCM', N'Nữ', N'0987654321', 7),
(22, 22, 2012, N'20 đường Hai Bà Trưng, Q3, TP.HCM', N'Nữ', N'0432165498', 6),
(23, 23, 2009, N'40 đường Phạm Hồng Thái, Q4, TP.HCM', N'Nữ', N'0823482311', 9), 
(24, 24, 2010, N'56 đường Trần Quang Khải, Q5, TP.HCM', N'Nữ', N'0326682410', 8),
(25, 25, 2011, N'50 đường Nguyễn Thị Minh Khai, Q6, TP.HCM', N'Nữ', N'0765432109', 7),
(26, 26, 2012, N'26 đường Hoàng Sa, Q7, TP.HCM', N'Nữ', N'0313437542', 6),
-- 12 Nam từ lớp 1 đến 5 (2013-2017)
(27, 27, 2013, N'10 đường Trường Sa, Q8, TP.HCM', N'Nam', N'0924824913', 5), 
(28, 28, 2014, N'28 đường Pasteur, Q9, TP.HCM', N'Nam', N'0786543210', 4),
(29, 29, 2015, N'12 đường Lê Lợi, Q10, TP.HCM', N'Nam', N'0897643210', 3),
(30, 30, 2016, N'30 đường Nguyễn Huệ, Q11, TP.HCM', N'Nam', N'0234987651', 2),
(31, 31, 2017, N'510 đường Trần Hưng Đạo, Q12, TP.HCM', N'Nam', N'0876543210', 1),
(32, 32, 2013, N'220 đường Nguyễn Thị Minh Khai, Q.Bình Thạnh, TP.HCM', N'Nam', N'0765432109', 5), 
(33, 33, 2014, N'30 đường Trường Sa, Q.Tân Bình, TP.HCM', N'Nam', N'0987654321', 4),
(34, 34, 2015, N'40 đường Trần Quang Khải, Q.Bình Tân, TP.HCM', N'Nam', N'0348589322', 3),
(35, 35, 2016, N'55 đường Nguyễn Thị Minh Khai, Q.Thủ Đức, TP.HCM', N'Nam', N'0214768953', 2),
(36, 36, 2017, N'36 đường Phạm Hồng Thái, Q.Tân Phú, TP.HCM', N'Nam', N'0567894321', 1),
(37, 37, 2013, N'11 đường Võ Văn Kiệt, Q.Gò Vấp, TP.HCM', N'Nam', N'0432165498', 5), 
(38, 38, 2014, N'03 đường Bùi Viện, Q.Phú Nhuận, TP.HCM', N'Nam', N'0342897651', 4),
(39, 39, 2015, N'09 đường Phạm Ngũ Lão, Q1, TP.HCM', N'Nam', N'0654312378', 3),
(40, 40, 2016, N'40 đường Lê Thánh Tôn, Q2, TP.HCM', N'Nam', N'0326682410', 2),
(41, 41, 2017, N'10 đường Tôn Đức Thắng, Q3, TP.HCM', N'Nam', N'0786543210', 1),
-- 8 Nữ từ lớp 1 đến 5 (2013-2017)
(42, 42, 2013, N'04 đường Hai Bà Trưng, Q4, TP.HCM', N'Nữ', N'0123678954', 5),
(43, 43, 2014, N'98 đường Gia Lai, Q5, TP.HCM', N'Nữ', N'0213532519', 4),
(44, 44, 2015, N'40 đường Nguyễn Thị Minh Khai, Q6, TP.HCM', N'Nữ', N'0897643210', 3),
(45, 45, 2016, N'42 đường Hoàng Sa, Q7, TP.HCM', N'Nữ', N'0234987651', 2),
(46, 46, 2017, N'60 đường Trường Sa, Q8, TP.HCM', N'Nữ', N'0987654321', 1),
(47, 47, 2013, N'170 đường Pasteur, Q9, TP.HCM', N'Nữ', N'0827538249', 5), 
(48, 48, 2014, N'180 đường Lê Lợi, Q10, TP.HCM', N'Nữ', N'0567894321', 4),
(49, 49, 2015, N'89 đường Trần Hưng Đạo, Q11, TP.HCM', N'Nữ', N'0983555501', 3),
(50, 50, 2016, N'50 đường Nguyễn Huệ, Q12, TP.HCM', N'Nữ', N'0432165498', 2);

-- Tắt IDENTITY_INSERT cho bảng Student
SET IDENTITY_INSERT Student OFF;

-- Bật IDENTITY_INSERT cho bảng Admin
SET IDENTITY_INSERT Admin ON;


-- Insert vào bảng Admin
INSERT INTO Admin (Id, AccountId)
VALUES 
(1, 101);

-- Tắt IDENTITY_INSERT cho bảng Admin
SET IDENTITY_INSERT Admin OFF;

-- Bật IDENTITY_INSERT cho bảng Moderator
SET IDENTITY_INSERT Moderator ON;

-- Insert vào bảng Moderator
INSERT INTO Moderator (Id, AccountId)
VALUES 
(1, 102);

-- Tắt IDENTITY_INSERT cho bảng Moderator
SET IDENTITY_INSERT Moderator OFF;

-- Bật IDENTITY_INSERT cho bảng Subject
SET IDENTITY_INSERT Subject ON;

-- Insert vào bảng Subject
INSERT INTO Subject (Id, [Name]) VALUES
(1, N'Toán học'),
(2, N'Vật lý'),
(3, N'Hóa học'),
(4, N'Sinh học'),
(5, N'Tiếng Anh'),
(6, N'Lịch sử'),
(7, N'Địa lý'),
(8, N'Văn học'),
(9, N'Tiếng anh Toeic'),
(10, N'Tiếng anh Ielts'),
(11,N'Tiếng trung'),
(12,N'Tiếng hàn'),
(13,N'Tiếng nhật');

-- Tắt IDENTITY_INSERT cho bảng Subject
SET IDENTITY_INSERT Subject OFF;


-- Insert vào bảng TutorSubject
INSERT INTO TutorSubject (TutorId, SubjectId)
VALUES
-- Toán, Lý, Hóa
(1, 1), (1, 2), (1, 3), 
(3, 1), (3, 2), (3, 3), 
(5, 1), (5, 2), (5, 3),

-- Văn, Sử, Địa
(7, 6), (7, 7), (7, 8), 
(9, 6), (9, 7), (9, 8), 
(11, 6), (11, 7), (11, 8),

-- Tiếng Anh
(13, 5), (13, 9), (13, 10), 
(15, 5), (15, 9), (15, 10),

-- Tiếng Trung, Tiếng Hàn, Tiếng Nhật
(17, 11), (17, 12), (17, 13), 
(19, 11), (19, 12), (19, 13),

-- Sinh học, Hóa học
(21, 3), (21, 4),
(23, 3), (23, 4),
(25, 3), (25, 4),

-- Toán, Vật lý
(27, 1), (27, 2),
(29, 1), (29, 2),
(31, 1), (31, 2),

-- Lịch sử, Địa lý
(33, 6), (33, 7),
(35, 6), (35, 7),
(37, 6), (37, 7),

-- Tiếng anh Toeic, Tiếng anh Ielts
(39, 9), (39, 10),
(41, 9), (41, 10),
(43, 9), (43, 10),

-- Toán, Sinh học
(45, 1), (45, 4),
(47, 1), (47, 4),
(49, 1), (49, 4);


-- subjectId của những tutor chưa được mod duyệt 
INSERT INTO TutorSubject (TutorId, SubjectId)
VALUES
-- Toán, Lý, Hóa
(2, 1), (2, 2), (2, 3), 
(4, 1), (4, 2), (4, 3), 
(6, 1), (6, 2), (6, 3),

-- Văn, Sử, Địa
(8, 6), (8, 7), (8, 8), 
(10, 6), (10, 7), (10, 8), 
(12, 6), (12, 7), (12, 8),

-- Tiếng Anh
(14, 5), (14, 9), (14, 10), 
(16, 5), (16, 9), (16, 10),

-- Tiếng Trung, Tiếng Hàn, Tiếng Nhật
(18, 11), (18, 12), (18, 13), 
(20, 11), (20, 12), (20, 13),

-- Sinh học, Hóa học
(22, 3), (22, 4),
(24, 3), (24, 4),
(26, 3), (26, 4),

-- Toán, Vật lý
(28, 1), (28, 2),
(30, 1), (30, 2),
(32, 1), (32, 2),

-- Lịch sử, Địa lý
(34, 6), (34, 7),
(36, 6), (36, 7),
(38, 6), (38, 7),

-- Tiếng anh Toeic, Tiếng anh Ielts
(40, 9), (40, 10),
(42, 9), (42, 10),
(44, 9), (44, 10),

-- Toán, Sinh học
(46, 1), (46, 4),
(48, 1), (48, 4),
(50, 1), (50, 4);


-- Bật IDENTITY_INSERT cho bảng Class
-- Bật IDENTITY_INSERT cho bảng Class
SET IDENTITY_INSERT Class ON;
-- Insert 50 class
INSERT INTO Class (Id, TutorId, SubjectId, AmountOfSlot, StartDay, EndDay) VALUES
-- Toán, Lý, Hóa
(121,3,1,1,'2024-09-01', '2024-10-31'),
(1, 1, 1, 2, '2024-05-20', '2024-07-20'), 
(2, 1, 2, 3, '2024-07-01', '2024-08-31'), 
(3, 1, 3, 2, '2024-05-01', '2024-06-30'),
(4, 3, 1, 2, '2023-11-01', '2023-12-31'), 
(5, 3, 2, 3, '2024-02-01', '2024-03-31'), 
(6, 3, 3, 2, '2024-07-20', '2024-09-20'),
(7, 5, 1, 3, '2024-03-01', '2024-04-30'), 
(8, 5, 2, 2, '2024-06-01', '2024-07-31'), 
(9, 5, 3, 3, '2024-05-01', '2024-06-30'),

-- Văn, Sử, Địa
(10, 7, 6, 2, '2024-07-01', '2024-08-31'), 
(11, 7, 7, 3, '2024-07-01', '2024-08-31'), 
(12, 7, 8, 2, '2024-05-01', '2024-06-30'),
(13, 9, 6, 2, '2024-02-01', '2024-03-31'), 
(14, 9, 7, 3, '2024-04-01', '2024-05-31'), 
(15, 9, 8, 2, '2023-11-01', '2023-12-31'),
(16, 11, 6, 3, '2024-03-01', '2024-04-30'), 
(17, 11, 7, 2, '2024-07-01', '2024-08-31'), 
(18, 11, 8, 3, '2024-05-01', '2024-06-30'),

-- Tiếng Anh
(19, 13, 5, 2, '2024-07-01', '2024-08-31'), 
(20, 13, 9, 3, '2024-07-01', '2024-08-31'), 
(21, 13, 10, 2, '2024-05-01', '2024-06-30'),
(22, 15, 5, 2, '2024-02-01', '2024-03-31'), 
(23, 15, 9, 3, '2024-04-01', '2024-05-31'), 
(24, 15, 10, 2, '2023-11-01', '2023-12-31'),

-- Tiếng Trung, Tiếng Hàn, Tiếng Nhật
(25, 17, 11, 2, '2024-07-01', '2024-08-31'), 
(26, 17, 12, 3, '2024-07-01', '2024-08-31'), 
(27, 17, 13, 2, '2024-05-01', '2024-06-30'),
(28, 19, 11, 2, '2024-02-01', '2024-03-31'), 
(29, 19, 12, 3, '2024-04-01', '2024-05-31'), 
(30, 19, 13, 2, '2023-11-01', '2023-12-31'),

-- Sinh học, Hóa học
(31, 21, 3, 2, '2024-07-01', '2024-08-31'), 
(32, 21, 4, 3, '2024-07-01', '2024-08-31'), 
(33, 23, 3, 2, '2024-05-01', '2024-06-30'),
(34, 23, 4, 2, '2024-02-01', '2024-03-31'), 
(35, 25, 3, 3, '2024-07-01', '2024-08-31'), 
(36, 25, 4, 2, '2023-11-01', '2023-12-31'),

-- Toán, Vật lý
(37, 27, 1, 2, '2024-07-01', '2024-08-31'), 
(38, 27, 2, 3, '2024-07-01', '2024-08-31'), 
(39, 29, 1, 2, '2024-05-01', '2024-06-30'),
(40, 29, 2, 2, '2024-02-01', '2024-03-31'), 
(41, 31, 1, 3, '2024-04-01', '2024-05-31'), 
(42, 31, 2, 2, '2023-11-01', '2023-12-31'),

-- Lịch sử, Địa lý
(43, 33, 6, 2, '2024-07-01', '2024-08-31'), 
(44, 33, 7, 3, '2024-07-01', '2024-08-31'), 
(45, 35, 6, 2, '2024-05-01', '2024-06-30'),
(46, 35, 7, 2, '2024-02-01', '2024-03-31'), 
(47, 37, 6, 3, '2024-04-01', '2024-05-31'), 
(48, 37, 7, 2, '2023-11-01', '2023-12-31'),

-- Tiếng anh Toeic, Tiếng anh Ielts
(49, 39, 9, 2, '2024-07-01', '2024-08-31'), 
(50, 39, 10, 3, '2024-07-01', '2024-08-31'), 
(51, 41, 9, 2, '2024-05-01', '2024-06-30'),
(52, 41, 10, 2, '2024-02-01', '2024-03-31'), 
(53, 43, 9, 3, '2024-04-01', '2024-05-31'), 
(54, 43, 10, 2, '2023-11-01', '2023-12-31'),

-- Toán, Sinh học
(55, 45, 1, 2, '2024-07-01', '2024-08-31'), 
(56, 45, 4, 3, '2024-07-01', '2024-08-31'), 
(57, 47, 1, 2, '2024-05-01', '2024-06-30'),
(58, 47, 4, 2, '2024-02-01', '2024-03-31'), 
(59, 49, 1, 3, '2024-04-01', '2024-05-31'), 
(60, 49, 4, 2, '2023-11-01', '2023-12-31'),


--thêm class nè 
-- Insert 50 more classes with different future start dates
-- Toán, Lý, Hóa
(61, 1, 1, 2, '2024-04-01', '2024-06-30'), 
(62, 1, 2, 3, '2024-10-01', '2024-12-31'), 
(63, 1, 3, 2, '2024-10-01', '2024-11-30'),
(64, 3, 1, 2, '2024-10-15', '2024-12-14'), 
(65, 3, 2, 3, '2024-11-01', '2024-12-31'), 
(66, 3, 3, 2, '2024-11-15', '2025-01-14'),
(67, 5, 1, 3, '2024-12-01', '2025-01-31'), 
(68, 5, 2, 2, '2024-12-15', '2025-02-14'), 
(69, 5, 3, 3, '2025-01-01', '2025-02-28'),

-- Văn, Sử, Địa
(70, 7, 6, 2, '2024-09-01', '2024-10-31'), 
(71, 7, 7, 3, '2024-09-15', '2024-11-14'), 
(72, 7, 8, 2, '2024-10-01', '2024-11-30'),
(73, 9, 6, 2, '2024-10-15', '2024-12-14'), 
(74, 9, 7, 3, '2024-11-01', '2024-12-31'), 
(75, 9, 8, 2, '2024-11-15', '2025-01-14'),
(76, 11, 6, 3, '2024-12-01', '2025-01-31'), 
(77, 11, 7, 2, '2024-12-15', '2025-02-14'), 
(78, 11, 8, 3, '2025-01-01', '2025-02-28'),

-- Tiếng Anh
(79, 13, 5, 2, '2024-09-01', '2024-10-31'), 
(80, 13, 9, 3, '2024-09-15', '2024-11-14'), 
(81, 13, 10, 2, '2024-10-01', '2024-11-30'),
(82, 15, 5, 2, '2024-10-15', '2024-12-14'), 
(83, 15, 9, 3, '2024-11-01', '2024-12-31'), 
(84, 15, 10, 2, '2024-11-15', '2025-01-14'),

-- Tiếng Trung, Tiếng Hàn, Tiếng Nhật
(85, 17, 11, 2, '2024-09-01', '2024-10-31'), 
(86, 17, 12, 3, '2024-09-15', '2024-11-14'), 
(87, 17, 13, 2, '2024-10-01', '2024-11-30'),
(88, 19, 11, 2, '2024-10-15', '2024-12-14'), 
(89, 19, 12, 3, '2024-11-01', '2024-12-31'), 
(90, 19, 13, 2, '2024-11-15', '2025-01-14'),

-- Sinh học, Hóa học
(91, 21, 3, 2, '2024-09-01', '2024-10-31'), 
(92, 21, 4, 3, '2024-09-15', '2024-11-14'), 
(93, 23, 3, 2, '2024-10-01', '2024-11-30'),
(94, 23, 4, 2, '2024-10-15', '2024-12-14'), 
(95, 25, 3, 3, '2024-11-01', '2024-12-31'), 
(96, 25, 4, 2, '2024-11-15', '2025-01-14'),

-- Toán, Vật lý
(97, 27, 1, 2, '2024-09-01', '2024-10-31'), 
(98, 27, 2, 3, '2024-09-15', '2024-11-14'), 
(99, 29, 1, 2, '2024-10-01', '2024-11-30'),
(100, 29, 2, 2, '2024-10-15', '2024-12-14'), 
(101, 31, 1, 3, '2024-11-01', '2024-12-31'), 
(102, 31, 2, 2, '2024-11-15', '2025-01-14'),

-- Lịch sử, Địa lý
(103, 33, 6, 2, '2024-09-01', '2024-10-31'), 
(104, 33, 7, 3, '2024-09-15', '2024-11-14'), 
(105, 35, 6, 2, '2024-10-01', '2024-11-30'),
(106, 35, 7, 2, '2024-10-15', '2024-12-14'), 
(107, 37, 6, 3, '2024-11-01', '2024-12-31'), 
(108, 37, 7, 2, '2024-11-15', '2025-01-14'),

-- Tiếng anh Toeic, Tiếng anh Ielts
(109, 39, 9, 2, '2024-09-01', '2024-10-31'), 
(110, 39, 10, 3, '2024-09-15', '2024-11-14'), 
(111, 41, 9, 2, '2024-10-01', '2024-11-30'),
(112, 41, 10, 2, '2024-10-15', '2024-12-14'), 
(113, 43, 9, 3, '2024-11-01', '2024-12-31'), 
(114, 43, 10, 2, '2024-11-15', '2025-01-14'),

-- Toán, Sinh học
(115, 45, 1, 2, '2024-09-01', '2024-10-31'), 
(116, 45, 4, 3, '2024-09-15', '2024-11-14'), 
(117, 47, 1, 2, '2024-10-01', '2024-11-30'),
(118, 47, 4, 2, '2024-10-15', '2024-12-14'), 
(119, 49, 1, 3, '2024-11-01', '2024-12-31'), 
(120, 49, 4, 2, '2024-11-15', '2025-01-14');

SET IDENTITY_INSERT Class OFF;



SET IDENTITY_INSERT Slot ON;
-- Insert Slot
-- Toán, Lý, Hóa
INSERT INTO Slot (Id, ClassId, DayOfSlot, StartTime, EndTime) VALUES
(274, 121, N'Thứ Hai', '16:00', '18:00'),
(1, 1, N'Thứ Hai', '16:00', '18:00'),
(2, 1, N'Thứ Tư', '16:00', '18:00'),
(3, 2, N'Thứ Ba', '16:00', '18:00'),
(4, 2, N'Thứ Năm', '16:00', '18:00'),
(5, 2, N'Thứ Bảy', '08:00', '10:00'),
(6, 3, N'Thứ Ba', '18:00', '20:00'),
(7, 3, N'Thứ Năm', '18:00', '20:00'),
(8, 4, N'Thứ Tư', '18:00', '20:00'),
(9, 4, N'Thứ Sáu', '18:00', '20:00'),
(10, 5, N'Thứ Ba', '20:00', '22:00'),
(11, 5, N'Thứ Năm', '20:00', '22:00'),
(12, 5, N'Thứ Bảy', '10:00', '12:00'),
(13, 6, N'Thứ Hai', '18:00', '20:00'),
(14, 6, N'Thứ Sáu', '20:00', '22:00'),
(15, 7, N'Thứ Ba', '16:00', '18:00'),
(16, 7, N'Thứ Sáu', '16:00', '18:00'),
(17, 8, N'Thứ Tư', '18:00', '20:00'),
(18, 8, N'Thứ Sáu', '18:00', '20:00'),
(19, 9, N'Thứ Hai', '16:00', '18:00'),
(20, 9, N'Thứ Năm', '16:00', '18:00'),
(21, 9, N'Thứ Bảy', '10:00', '12:00'),

-- Văn, Sử, Địa
(22, 10, N'Thứ Hai', '16:00', '18:00'),
(23, 10, N'Thứ Tư', '16:00', '18:00'),
(24, 11, N'Thứ Ba', '16:00', '18:00'),
(25, 11, N'Thứ Năm', '16:00', '18:00'),
(26, 11, N'Thứ Bảy', '08:00', '10:00'),
(27, 12, N'Thứ Ba', '18:00', '20:00'),
(28, 12, N'Thứ Năm', '18:00', '20:00'),
(29, 13, N'Thứ Ba', '16:00', '18:00'),
(30, 13, N'Thứ Năm', '16:00', '18:00'),
(31, 14, N'Thứ Tư', '18:00', '20:00'),
(32, 14, N'Thứ Sáu', '18:00', '20:00'),
(33, 15, N'Thứ Hai', '18:00', '20:00'),
(34, 15, N'Thứ Sáu', '20:00', '22:00'),
(35, 16, N'Thứ Ba', '16:00', '18:00'),
(36, 16, N'Thứ Sáu', '16:00', '18:00'),
(37, 17, N'Thứ Tư', '18:00', '20:00'),
(38, 17, N'Thứ Sáu', '18:00', '20:00'),
(39, 18, N'Thứ Hai', '16:00', '18:00'),
(40, 18, N'Thứ Năm', '16:00', '18:00'),
(41, 18, N'Thứ Bảy', '10:00', '12:00'),

-- Tiếng Anh
(42, 19, N'Thứ Hai', '16:00', '18:00'),
(43, 19, N'Thứ Tư', '16:00', '18:00'),
(44, 20, N'Thứ Ba', '16:00', '18:00'),
(45, 20, N'Thứ Năm', '16:00', '18:00'),
(46, 20, N'Thứ Bảy', '08:00', '10:00'),
(47, 21, N'Thứ Ba', '18:00', '20:00'),
(48, 21, N'Thứ Năm', '18:00', '20:00'),
(49, 22, N'Thứ Ba', '16:00', '18:00'),
(50, 22, N'Thứ Năm', '16:00', '18:00'),
(51, 23, N'Thứ Ba', '18:00', '20:00'),
(52, 23, N'Thứ Năm', '18:00', '20:00'),
(53, 24, N'Thứ Hai', '16:00', '18:00'),
(54, 24, N'Thứ Tư', '16:00', '18:00'),
(55, 25, N'Thứ Ba', '18:00', '20:00'),
(56, 25, N'Thứ Năm', '18:00', '20:00'),

-- Tiếng Trung, Tiếng Hàn, Tiếng Nhật
(57, 26, N'Thứ Hai', '16:00', '18:00'),
(58, 26, N'Thứ Tư', '16:00', '18:00'),
(59, 27, N'Thứ Ba', '16:00', '18:00'),
(60, 27, N'Thứ Năm', '16:00', '18:00'),
(61, 27, N'Thứ Bảy', '08:00', '10:00'),
(62, 28, N'Thứ Ba', '18:00', '20:00'),
(63, 28, N'Thứ Năm', '18:00', '20:00'),
(64, 29, N'Thứ Hai', '16:00', '18:00'),
(65, 29, N'Thứ Tư', '16:00', '18:00'),
(66, 30, N'Thứ Ba', '18:00', '20:00'),
(67, 30, N'Thứ Năm', '18:00', '20:00'),
(68, 31, N'Thứ Hai', '18:00', '20:00'),
(69, 31, N'Thứ Sáu', '20:00', '22:00'),
(70, 32, N'Thứ Ba', '16:00', '18:00'),
(71, 32, N'Thứ Sáu', '16:00', '18:00'),
(72, 33, N'Thứ Tư', '18:00', '20:00'),
(73, 33, N'Thứ Sáu', '18:00', '20:00'),
(74, 34, N'Thứ Hai', '16:00', '18:00'),
(75, 34, N'Thứ Năm', '16:00', '18:00'),
(76, 34, N'Thứ Bảy', '10:00', '12:00'),

-- Sinh học, Hóa học
(77, 35, N'Thứ Hai', '16:00', '18:00'),
(78, 35, N'Thứ Sáu', '16:00', '18:00'),
(79, 36, N'Thứ Ba', '16:00', '18:00'),
(80, 36, N'Thứ Năm', '16:00', '18:00'),
(81, 36, N'Thứ Bảy', '08:00', '10:00'),
(82, 37, N'Thứ Ba', '18:00', '20:00'),
(83, 37, N'Thứ Năm', '18:00', '20:00'),
(84, 38, N'Thứ Hai', '16:00', '18:00'),
(85, 38, N'Thứ Tư', '16:00', '18:00'),
(86, 39, N'Thứ Ba', '18:00', '20:00'),
(87, 39, N'Thứ Năm', '18:00', '20:00'),
(88, 40, N'Thứ Hai', '18:00', '20:00'),
(89, 40, N'Thứ Sáu', '20:00', '22:00'),
(90, 41, N'Thứ Hai', '16:00', '18:00'),
(91, 41, N'Thứ Tư', '16:00', '18:00'),
(92, 42, N'Thứ Ba', '16:00', '18:00'),
(93, 42, N'Thứ Năm', '16:00', '18:00'),
(94, 42, N'Thứ Bảy', '08:00', '10:00'),

-- Toán, Vật lý
(95, 43, N'Thứ Ba', '18:00', '20:00'),
(96, 43, N'Thứ Năm', '18:00', '20:00'),
(97, 44, N'Thứ Hai', '16:00', '18:00'),
(98, 44, N'Thứ Tư', '16:00', '18:00'),
(99, 45, N'Thứ Ba', '18:00', '20:00'),
(100, 45, N'Thứ Năm', '18:00', '20:00'),
(101, 46, N'Thứ Hai', '18:00', '20:00'),
(102, 46, N'Thứ Sáu', '20:00', '22:00'),
(103, 47, N'Thứ Hai', '16:00', '18:00'),
(104, 47, N'Thứ Tư', '16:00', '18:00'),
(105, 48, N'Thứ Ba', '16:00', '18:00'),
(106, 48, N'Thứ Năm', '16:00', '18:00'),
(107, 48, N'Thứ Bảy', '08:00', '10:00'),

-- Lịch sử, Địa lý
(108, 49, N'Thứ Ba', '18:00', '20:00'),
(109, 49, N'Thứ Năm', '18:00', '20:00'),
(110, 50, N'Thứ Hai', '16:00', '18:00'),
(111, 50, N'Thứ Tư', '16:00', '18:00'),
(112, 51, N'Thứ Ba', '18:00', '20:00'),
(113, 51, N'Thứ Năm', '18:00', '20:00'),
(114, 52, N'Thứ Hai', '18:00', '20:00'),
(115, 52, N'Thứ Sáu', '20:00', '22:00'),
(116, 53, N'Thứ Hai', '16:00', '18:00'),
(117, 53, N'Thứ Tư', '16:00', '18:00'),
(118, 54, N'Thứ Ba', '16:00', '18:00'),
(119, 54, N'Thứ Năm', '16:00', '18:00'),
(120, 54, N'Thứ Bảy', '08:00', '10:00'),

-- Tiếng Anh Toeic, Tiếng Anh Ielts
(121, 55, N'Thứ Ba', '18:00', '20:00'),
(122, 55, N'Thứ Năm', '18:00', '20:00'),
(123, 56, N'Thứ Hai', '16:00', '18:00'),
(124, 56, N'Thứ Tư', '16:00', '18:00'),
(125, 57, N'Thứ Ba', '18:00', '20:00'),
(126, 57, N'Thứ Năm', '18:00', '20:00'),
(127, 58, N'Thứ Hai', '18:00', '20:00'),
(128, 58, N'Thứ Sáu', '20:00', '22:00'),
(129, 59, N'Thứ Hai', '16:00', '18:00'),
(130, 59, N'Thứ Tư', '16:00', '18:00'),
(131, 60, N'Thứ Ba', '16:00', '18:00'),
(132, 60, N'Thứ Năm', '16:00', '18:00'),
(133, 60, N'Thứ Bảy', '08:00', '10:00'),



--Thêm Slot nè keo 

-- Toán, Lý, Hóa
(134, 61, N'Thứ Hai', '08:00', '10:00'),
(135, 61, N'Thứ Tư', '08:00', '10:00'),
(136, 62, N'Thứ Ba', '08:00', '10:00'),
(137, 62, N'Thứ Năm', '08:00', '10:00'),
(138, 62, N'Thứ Bảy', '08:00', '10:00'),
(139, 63, N'Thứ Tư', '10:00', '12:00'),
(140, 63, N'Thứ Sáu', '10:00', '12:00'),

(141, 64, N'Thứ Hai', '10:00', '12:00'),
(142, 64, N'Thứ Tư', '10:00', '12:00'),
(143, 65, N'Thứ Ba', '10:00', '12:00'),
(144, 65, N'Thứ Năm', '10:00', '12:00'),
(145, 65, N'Thứ Bảy', '10:00', '12:00'),
(146, 66, N'Thứ Tư', '12:00', '14:00'),
(147, 66, N'Thứ Sáu', '12:00', '14:00'),

(148, 67, N'Thứ Hai', '12:00', '14:00'),
(149, 67, N'Thứ Tư', '12:00', '14:00'),
(150, 68, N'Thứ Ba', '12:00', '14:00'),
(151, 68, N'Thứ Năm', '12:00', '14:00'),
(152, 69, N'Thứ Bảy', '12:00', '14:00'),
(153, 69, N'Thứ Bảy', '14:00', '16:00'),
(154, 69, N'Chủ Nhật', '08:00', '10:00'),

-- Văn, Sử, Địa
(155, 70, N'Thứ Hai', '14:00', '16:00'),
(156, 70, N'Thứ Tư', '14:00', '16:00'),
(157, 71, N'Thứ Ba', '14:00', '16:00'),
(158, 71, N'Thứ Năm', '14:00', '16:00'),
(159, 71, N'Thứ Bảy', '14:00', '16:00'),
(160, 72, N'Thứ Tư', '16:00', '18:00'),
(161, 72, N'Thứ Sáu', '16:00', '18:00'),

(162, 73, N'Thứ Hai', '16:00', '18:00'),
(163, 73, N'Thứ Tư', '16:00', '18:00'),
(164, 74, N'Thứ Ba', '16:00', '18:00'),
(165, 74, N'Thứ Năm', '16:00', '18:00'),
(166, 74, N'Thứ Bảy', '16:00', '18:00'),
(167, 75, N'Thứ Tư', '18:00', '20:00'),
(168, 75, N'Thứ Sáu', '18:00', '20:00'),

(169, 76, N'Thứ Hai', '18:00', '20:00'),
(170, 76, N'Thứ Tư', '18:00', '20:00'),
(171, 77, N'Thứ Ba', '18:00', '20:00'),
(172, 77, N'Thứ Năm', '18:00', '20:00'),
(173, 78, N'Thứ Bảy', '18:00', '20:00'),
(174, 78, N'Thứ Bảy', '20:00', '22:00'),
(175, 78, N'Chủ Nhật', '10:00', '12:00'),

-- Tiếng Anh
(176, 79, N'Thứ Hai', '08:00', '10:00'),
(177, 79, N'Thứ Tư', '08:00', '10:00'),
(178, 80, N'Thứ Ba', '08:00', '10:00'),
(179, 80, N'Thứ Năm', '08:00', '10:00'),
(180, 80, N'Thứ Bảy', '08:00', '10:00'),
(181, 81, N'Thứ Tư', '10:00', '12:00'),
(182, 81, N'Thứ Sáu', '10:00', '12:00'),

(183, 82, N'Thứ Hai', '10:00', '12:00'),
(184, 82, N'Thứ Tư', '10:00', '12:00'),
(185, 83, N'Thứ Ba', '10:00', '12:00'),
(186, 83, N'Thứ Năm', '10:00', '12:00'),
(187, 83, N'Thứ Bảy', '10:00', '12:00'),
(188, 84, N'Thứ Tư', '12:00', '14:00'),
(189, 84, N'Thứ Sáu', '12:00', '14:00'),

-- Tiếng Trung, Tiếng Hàn, Tiếng Nhật
(190, 85, N'Thứ Hai', '12:00', '14:00'),
(191, 85, N'Thứ Tư', '12:00', '14:00'),
(192, 86, N'Thứ Ba', '12:00', '14:00'),
(193, 86, N'Thứ Năm', '12:00', '14:00'),
(194, 87, N'Thứ Bảy', '12:00', '14:00'),
(195, 87, N'Thứ Bảy', '14:00', '16:00'),
(196, 87, N'Chủ Nhật', '08:00', '10:00'),

(197, 88, N'Thứ Hai', '14:00', '16:00'),
(198, 88, N'Thứ Tư', '14:00', '16:00'),
(199, 89, N'Thứ Ba', '14:00', '16:00'),
(200, 89, N'Thứ Năm', '14:00', '16:00'),
(201, 89, N'Thứ Bảy', '14:00', '16:00'),
(202, 90, N'Thứ Tư', '16:00', '18:00'),
(203, 90, N'Thứ Sáu', '16:00', '18:00'),

-- Sinh học, Hóa học
(204, 91, N'Thứ Hai', '16:00', '18:00'),
(205, 91, N'Thứ Tư', '16:00', '18:00'),
(206, 92, N'Thứ Ba', '16:00', '18:00'),
(207, 92, N'Thứ Năm', '16:00', '18:00'),
(208, 92, N'Thứ Bảy', '16:00', '18:00'),
(209, 93, N'Thứ Tư', '18:00', '20:00'),
(210, 93, N'Thứ Sáu', '18:00', '20:00'),

(211, 94, N'Thứ Hai', '18:00', '20:00'),
(212, 94, N'Thứ Tư', '18:00', '20:00'),
(213, 95, N'Thứ Ba', '18:00', '20:00'),
(214, 95, N'Thứ Năm', '18:00', '20:00'),
(215, 95, N'Thứ Bảy', '18:00', '20:00'),
(216, 96, N'Thứ Tư', '20:00', '22:00'),
(217, 96, N'Thứ Sáu', '20:00', '22:00'),

-- Toán, Vật lý
(218, 97, N'Thứ Hai', '08:00', '10:00'),
(219, 97, N'Thứ Tư', '08:00', '10:00'),
(220, 98, N'Thứ Ba', '08:00', '10:00'),
(221, 98, N'Thứ Năm', '08:00', '10:00'),
(222, 99, N'Thứ Bảy', '08:00', '10:00'),
(223, 99, N'Thứ Bảy', '10:00', '12:00'),
(224, 99, N'Chủ Nhật', '08:00', '10:00'),

(225, 100, N'Thứ Hai', '10:00', '12:00'),
(226, 100, N'Thứ Tư', '10:00', '12:00'),
(227, 101, N'Thứ Ba', '10:00', '12:00'),
(228, 101, N'Thứ Năm', '10:00', '12:00'),
(229, 101, N'Thứ Bảy', '10:00', '12:00'),
(230, 102, N'Thứ Tư', '12:00', '14:00'),
(231, 102, N'Thứ Sáu', '12:00', '14:00'),

-- Lịch sử, Địa lý
(232, 103, N'Thứ Hai', '12:00', '14:00'),
(233, 103, N'Thứ Tư', '12:00', '14:00'),
(234, 104, N'Thứ Ba', '12:00', '14:00'),
(235, 104, N'Thứ Năm', '12:00', '14:00'),
(236, 104, N'Thứ Bảy', '12:00', '14:00'),
(237, 105, N'Thứ Tư', '14:00', '16:00'),
(238, 105, N'Thứ Sáu', '14:00', '16:00'),

(239, 106, N'Thứ Hai', '14:00', '16:00'),
(240, 106, N'Thứ Tư', '14:00', '16:00'),
(241, 107, N'Thứ Ba', '14:00', '16:00'),
(242, 107, N'Thứ Năm', '14:00', '16:00'),
(243, 107, N'Thứ Bảy', '14:00', '16:00'),
(244, 108, N'Thứ Tư', '16:00', '18:00'),
(245, 108, N'Thứ Sáu', '16:00', '18:00'),

-- Tiếng anh Toeic, Tiếng anh Ielts
(246, 109, N'Thứ Hai', '16:00', '18:00'),
(247, 109, N'Thứ Tư', '16:00', '18:00'),
(248, 110, N'Thứ Ba', '16:00', '18:00'),
(249, 110, N'Thứ Năm', '16:00', '18:00'),
(250, 111, N'Thứ Bảy', '16:00', '18:00'),
(251, 111, N'Thứ Bảy', '18:00', '20:00'),
(252, 111, N'Chủ Nhật', '10:00', '12:00'),

(253, 112, N'Thứ Hai', '18:00', '20:00'),
(254, 112, N'Thứ Tư', '18:00', '20:00'),
(255, 113, N'Thứ Ba', '18:00', '20:00'),
(256, 113, N'Thứ Năm', '18:00', '20:00'),
(257, 113, N'Thứ Bảy', '18:00', '20:00'),
(258, 114, N'Thứ Tư', '20:00', '22:00'),
(259, 114, N'Thứ Sáu', '20:00', '22:00'),

-- Toán, Sinh học
(260, 115, N'Thứ Hai', '08:00', '10:00'),
(261, 115, N'Thứ Tư', '08:00', '10:00'),
(262, 116, N'Thứ Ba', '08:00', '10:00'),
(263, 116, N'Thứ Năm', '08:00', '10:00'),
(264, 116, N'Thứ Bảy', '08:00', '10:00'),
(265, 117, N'Thứ Tư', '10:00', '12:00'),
(266, 117, N'Thứ Sáu', '10:00', '12:00'),

(267, 118, N'Thứ Hai', '10:00', '12:00'),
(268, 118, N'Thứ Tư', '10:00', '12:00'),
(269, 119, N'Thứ Ba', '10:00', '12:00'),
(270, 119, N'Thứ Năm', '10:00', '12:00'),
(271, 119, N'Thứ Bảy', '10:00', '12:00'),
(272, 120, N'Thứ Tư', '12:00', '14:00'),
(273, 120, N'Thứ Sáu', '12:00', '14:00');




SET IDENTITY_INSERT Slot OFF;


-- Insert Schedule
-- Schedule cho Student 1
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
-- Thứ Hai
(1, 1, N'thành công'),  -- Thứ Hai, slot 1
(1, 13, N'thành công'),  -- Thứ Hai, slot 2

-- Thứ Ba
(1, 15, N'thành công'),  -- Thứ Ba, slot 1
(1, 44, N'thành công'),  -- Thứ Ba, slot 2

-- Thứ Tư
(1, 31, N'thành công'),  -- Thứ Tư, slot 1
(1, 72, N'thành công'),  -- Thứ Tư, slot 2

-- Thứ Năm
(1, 4, N'thành công'),  -- Thứ Năm, slot 1
(1, 93, N'thành công'),  -- Thứ Năm, slot 2

-- Thứ Sáu
(1, 78, N'thành công'),  -- Thứ Sáu, slot 1
(1, 38, N'thành công'),  -- Thứ Sáu, slot 2

-- Thứ Bảy
(1, 94, N'thành công'),  -- Thứ Bảy, slot 1

-- Thứ Bảy
(1, 107, N'thành công');  -- Thứ Bảy, slot 2



-- Schedule cho Student 2
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(2, 2, N'thành công'), 
(2, 17, N'thành công'), 
(2, 35, N'thất bại');

-- Schedule cho Student 3
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(3, 3, N'thành công'), 
(3, 23, N'đang xử lý'), 
(3, 74, N'thành công');

-- Schedule cho Student 4
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(4, 4, N'thành công'), 
(4, 44, N'thành công'), 
(4, 105, N'đang xử lý');

-- Schedule cho Student 5
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(5, 5, N'thành công'), 
(5, 36, N'thành công'), 
(5, 123, N'đang xử lý');

-- Schedule cho Student 6
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(6, 6, N'thành công'), 
(6, 56, N'thành công'), 
(6, 132, N'thất bại');

-- Schedule cho Student 7
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(7, 7, N'thành công'), 
(7, 87, N'thành công'), 
(7, 121, N'thất bại');

-- Schedule cho Student 8
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(8, 9, N'thành công'), 
(8, 39, N'thành công'), 
(8, 126, N'đang xử lý');

-- Schedule cho Student 9
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(9, 10, N'thành công'), 
(9, 69, N'thành công'), 
(9, 125, N'đang xử lý');

-- Schedule cho Student 10
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(10, 11, N'thành công'), 
(10, 73, N'thành công'), 
(10, 89, N'thất bại');

-- Schedule cho Student 11
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(11, 12, N'thành công'), 
(11, 80, N'thành công'), 
(11, 118, N'đang xử lý');

-- Schedule cho Student 12
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(12, 13, N'thành công'), 
(12, 47, N'thành công'), 
(12, 83, N'thất bại');

-- Schedule cho Student 13
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(13, 14, N'thành công'), 
(13, 64, N'thành công'), 
(13, 100, N'đang xử lý');

-- Schedule cho Student 14
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(14, 16, N'thành công'), 
(14, 70, N'thành công'), 
(14, 127, N'thất bại');

-- Schedule cho Student 15
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(15, 18, N'thành công'), 
(15, 93, N'thành công'), 
(15, 119, N'đang xử lý');

-- Schedule cho Student 16
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(16, 19, N'thành công'), 
(16, 26, N'thành công'), 
(16, 92, N'đang xử lý');

-- Schedule cho Student 17
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(17, 20, N'thành công'), 
(17, 71, N'thành công'), 
(17, 108, N'đang xử lý');

-- Schedule cho Student 18
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(18, 21, N'thành công'), 
(18, 40, N'thành công'), 
(18, 128, N'đang xử lý');

-- Schedule cho Student 19
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(19, 22, N'thành công'), 
(19, 53, N'thành công'), 
(19, 109, N'đang xử lý');

-- Schedule cho Student 20
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(20, 24, N'thành công'), 
(20, 57, N'thành công'), 
(20, 112, N'đang xử lý');

-- Schedule cho Student 21
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(21, 25, N'thành công'), 
(21, 90, N'thành công'), 
(21, 103, N'đang xử lý');

-- Schedule cho Student 22
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(22, 27, N'thành công'), 
(22, 91, N'thành công'), 
(22, 130, N'đang xử lý');

-- Schedule cho Student 23
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(23, 28, N'thành công'), 
(23, 42, N'thành công'), 
(23, 117, N'đang xử lý');

-- Schedule cho Student 24
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(24, 29, N'thành công'), 
(24, 46, N'thành công'), 
(24, 104, N'đang xử lý');

-- Schedule cho Student 25
INSERT INTO Schedule (StudentId, SlotId, Status) VALUES
(25, 30, N'thành công'), 
(25, 48, N'thành công'), 
(25, 120, N'đang xử lý');




SET IDENTITY_INSERT Complaint ON;
-- Insert Report
INSERT INTO Complaint(Id, StudentId, SlotId, TutorId, ModId, Content, [Status]) VALUES 
(1, 1, 7, 1, 1, N'Giảng viên quá ồn ào', N'thành công'), 
(2, 2, 5, 3, 1, N'Giảng viên đến trễ.', N'thất bại'), 
(3, 1, 7, 1, 1, N'Giảng viên nói những lời nói khiếm nhã', N'thành công'), 
(4, 4, 9, 3, 1, N'Giảng viên ăn mặc không phù hợp', N'thất bại'), 
(5, 5, 11, 3, 1, N'Giảng viên thường xuyên về sớm hơn giờ quy định', N'thành công'), 
(6, 6, 12, 3, 1, N'Giảng viên không chuẩn bị bài.', N'thành công'), 
(7, 14, 14, 9, 1, N'Giảng viên không sửa bài tập', N'đang xử lý'), 
(8, 7, 16, 5, 1, N'Thời gian bị ảnh hưởng do giáo viên đến trễ và xin về sớm.', N'đang xử lý'), 
(9, 8, 18, 5, 1, N'Giảng viên giảng bài khó hiểu.', N'đang xử lý'), 
(10, 9, 20, 5, 1, N'Tài liệu lớp học không được cung cấp.', N'đang xử lý');

SET IDENTITY_INSERT Complaint OFF;


SET IDENTITY_INSERT CV ON;

INSERT INTO CV (Id, TutorId, ModId, PhoneNumber, Yob, Location, Personal_ID, Gender, Experience, Grade, CreateTime, Content, Url) VALUES
(1, 15, 1, N'0909123456', 1985, N'Q1, TP.HCM', '123123456789', N'Nam', 5, N'Cấp 1', '2023-06-01', N'Chuyên dạy Toán cấp 1', N'https://www.youtube.com/watch?v=TczQYf7-Gqk'),
(2, 3, 1, N'0909234567', 1990, N'Q2, TP.HCM', '641234567890', N'Nữ', 3, N'Cấp 1', '2023-06-01', N'Chuyên dạy Anh văn cấp 1', N'https://www.youtube.com/watch?v=UYiPWA-9sTU&list=PLXINPt-Y3OgdoOjpBBaQkLVbWfn3THmzq'),
(3, 19, 1, N'0909345678', 1988, N'Q3, TP.HCM', '189345678901', N'Nam', 7, N'Cấp 3', '2023-06-01', N'Chuyên dạy Lý cấp 3', N'https://www.youtube.com/watch?v=uEt-ita0jU4&list=PLpCHz1szzEEauvpRWHCXxS_TJDUl2vXK8'),
(4, 7, 1, N'0909456789', 1992, N'Q4, TP.HCM', '478456789012', N'Nữ', 4, N'Cấp 2', '2023-06-01', N'Chuyên dạy Hóa cấp 2', N'https://www.youtube.com/watch?v=ojnL0agb4aw&list=PLJ26mhKctq9aexcmTr5LFrVG-UxeEYI-8'),
(5, 25, 1, N'0909567890', 1987, N'Q5, TP.HCM', '186567890123', N'Nam', 6, N'Cấp 3', '2023-06-01', N'Chuyên dạy Sinh cấp 3', N'https://www.youtube.com/watch?v=O8saMLKaYQY&list=PLOVaCZ_HQkvfhF1YVkJvw41FiRuWhXjFB'),
(6, 12, 1, N'0910123456', 1989, N'Q6, TP.HCM', '182678901234', N'Nữ', 8, N'Cấp 2', '2023-06-01', N'Chuyên dạy Văn cấp 2', N'https://www.youtube.com/watch?v=SCLFlGfaFho&t=170s'),
(7, 9, 1, N'0910234567', 1991, N'Q7, TP.HCM', '186789012345', N'Nam', 5, N'Cấp 2', '2023-06-01', N'Chuyên dạy Sử cấp 2', N'https://www.youtube.com/watch?v=n4DnCd-3pao&list=PLxUdsmN71otCGuMNsoDHwmQyK83zIxvyz'),
(8, 21, 1, N'0910345678', 1986, N'Q8, TP.HCM', '486890123456', N'Nữ', 3, N'Cấp 1', '2023-06-01', N'Chuyên dạy Địa cấp 1', N'https://www.youtube.com/watch?v=6NdqgBzFEsM'),
(9, 14, 1, N'0910456789', 1993, N'Q9, TP.HCM', '178901234567', N'Nam', 7, N'Cấp 3', '2023-06-01', N'Chuyên dạy Toán cấp 3', N'https://www.youtube.com/watch?v=Ft8kNJIbdC8'),
(10, 2, 1, N'0910567890', 1984, N'Q10, TP.HCM', '486112345678', N'Nữ', 4, N'Cấp 1', '2023-06-01', N'Chuyên dạy Anh văn cấp 1', N'https://www.youtube.com/watch?v=uvE985PF_08&list=PLmQ5jM-TsmbyKzvva0Qv4J5_Ph2vIZ3YK'),
(11, 8, 1, N'0910678901', 1990, N'Q11, TP.HCM', '475223456789', N'Nữ', 6, N'Cấp 2', '2023-06-01', N'Chuyên dạy Lý cấp 2', N'https://www.youtube.com/watch?v=mfzAJ2MEwMQ&list=PL5q2T2FxzK7XTfzYENaTPB-bKnOHiiYZr'),
(12, 16, 1, N'0910789012', 1985, N'Q12, TP.HCM', '147334567890', N'Nữ', 5, N'Cấp 2', '2023-06-01', N'Chuyên dạy Hóa cấp 2', N'https://www.youtube.com/watch?v=K7qqMgF847o'),
(13, 4, 1, N'0910890123', 1992, N'Q.Bình Thạnh, TP.HCM', '478445678901', N'Nam', 7, N'Cấp 3', '2023-06-01', N'Chuyên dạy Sinh cấp 3', N'https://www.youtube.com/watch?v=VioXq3aFVhs&list=PLNA-cW37ppHux9-5lYdnVZZM6usuhiqLD'),
(14, 10, 1, N'0910901234', 1987, N'Q.Tân Bình, TP.HCM', '485556789012', N'Nữ', 3, N'Cấp 2', '2023-06-01', N'Chuyên dạy Văn cấp 2', N'https://www.youtube.com/watch?v=7N5muH3jyB0&list=PLxUdsmN71otAJpfPBdkfSLHFYdrUe30Kg'),
(15, 22, 1, N'0910123457', 1991, N'Q.Bình Tân, TP.HCM', '183667890123', N'Nữ', 4, N'Cấp 2', '2023-06-01', N'Chuyên dạy Sử cấp 2', N'https://www.youtube.com/watch?v=Ev7eJ_--Nx8'),
(16, 5, 1, N'0910234568', 1986, N'Q.Thủ Đức, TP.HCM', '189778901234', N'Nữ', 6, N'Cấp 1', '2023-06-01', N'Chuyên dạy Địa cấp 1', N'https://www.youtube.com/watch?v=P5a5L-2IEDU'),
(17, 18, 1, N'0910345679', 1993, N'Q.Tân Phú, TP.HCM', '183889012345', N'Nữ', 5, N'Cấp 3', '2023-06-01', N'Chuyên dạy Toán cấp 3', N'https://www.youtube.com/watch?v=m3alelcxLmM'),
(18, 1, 1, N'0910456780', 1984, N'Q.Gò Vấp, TP.HCM', '125990123456', N'Nữ', 7, N'Cấp 1', '2023-06-01', N'Chuyên dạy Anh văn cấp 1', N'https://www.youtube.com/watch?v=v_A90rm8gF8&list=PLmQ5jM-TsmbyKzvva0Qv4J5_Ph2vIZ3YK&index=2'),
(19, 23, 1, N'0910567891', 1990, N'Q.Phú Nhuận, TP.HCM', '486101234567', N'Nam', 3, N'Cấp 2', '2023-06-01', N'Chuyên dạy Lý cấp 2', N'https://www.youtube.com/watch?v=gm0ksjneeF4'),
(20, 6, 1, N'0910678902', 1985, N'Q1, TP.HCM', '186212345678', N'Nam', 4, N'Cấp 2', '2023-06-01', N'Chuyên dạy Hóa cấp 2', N'https://www.youtube.com/watch?v=WykeKnTvGrg'),
(21, 24, 1, N'0910789013', 1992, N'Q2, TP.HCM', '486323456789', N'Nữ', 7, N'Cấp 3', '2023-06-01', N'Chuyên dạy Sinh cấp 3', N'https://www.youtube.com/watch?v=AZy21mU_RRI&list=PLNA-cW37ppHux9-5lYdnVZZM6usuhiqLD&index=2'),
(22, 11, 1, N'0910890124', 1987, N'Q3, TP.HCM', '478434567890', N'Nam', 5, N'Cấp 2', '2023-06-01', N'Chuyên dạy Văn cấp 2', N'https://www.youtube.com/watch?v=AIrfwiHocJs&list=PLxUdsmN71otAJpfPBdkfSLHFYdrUe30Kg&index=9'),
(23, 20, 1, N'0910901235', 1991, N'Q4, TP.HCM', '213545678901', N'Nữ', 3, N'Cấp 2', '2023-06-01', N'Chuyên dạy Sử cấp 2', N'https://www.youtube.com/watch?v=99LXQSYzH-8'),
(24, 13, 1, N'0910123458', 1986, N'Q5, TP.HCM', '656789012586', N'Nam', 6, N'Cấp 1', '2023-06-01', N'Chuyên dạy Địa cấp 1', N'https://www.youtube.com/watch?v=raCJDr1hCz0'),
(25, 17, 1, N'0910234569', 1993, N'Q6, TP.HCM', '767890123152', N'Nam', 4, N'Cấp 3', '2023-06-01', N'Chuyên dạy Toán cấp 3', N'https://www.youtube.com/watch?v=m3alelcxLmM'),
(26, 45, 1, N'0987654321', 1980, N'Q7, TP.HCM', '486315972019', N'Nam', 5, N'Cấp 1', '2024-01-01', N'Chuyên dạy Văn cấp 1', N'https://www.youtube.com/watch?v=N7P39CmuSb4&list=PLxUdsmN71otAJpfPBdkfSLHFYdrUe30Kg&index=17'),
(27, 46, 1, N'0987654322', 1981, N'Q8, TP.HCM', '354862179034', N'Nữ', 4, N'Cấp 2', '2024-01-02', N'Chuyên dạy Lý cấp 2', N'https://www.youtube.com/watch?v=ruXrDHc6JOc'),
(28, 50, 1, N'0987654323', 1982, N'Q9, TP.HCM', '084932761594', N'Nữ', 6, N'Cấp 2', '2024-01-03', N'Chuyên dạy Toán cấp 2', N'https://www.youtube.com/watch?v=bQES_F6c7c8'),
(29, 47, 1, N'0987654324', 1983, N'Q10, TP.HCM', '013584629731', N'Nam', 4, N'Cấp 1', '2024-01-04', N'Chuyên dạy Sử cấp 1', N'https://www.youtube.com/watch?v=1RSwwua6ld0'),
(30, 49, 1, N'0987654325', 1984, N'Q11, TP.HCM', '019763246852', N'Nam', 3, N'Cấp 2', '2024-01-05', N'Chuyên dạy Văn cấp 2', N'https://www.youtube.com/watch?v=loe4-6FjHFM&list=PLxUdsmN71otAJpfPBdkfSLHFYdrUe30Kg&index=40'),
(31, 48, 1, N'0987654326', 1985, N'Q12, TP.HCM', '019486325741', N'Nữ', 5, N'Cấp 3', '2024-01-06', N'Chuyên dạy Sinh cấp 3', N'https://www.youtube.com/watch?v=p1pqlqwBOa8&list=PLNA-cW37ppHux9-5lYdnVZZM6usuhiqLD&index=5'),
(32, 40, 1, N'0987654327', 1986, N'Q.Bình Thạnh, TP.HCM', '097632159357', N'Nữ', 8, N'Cấp 2', '2024-01-07', N'Chuyên dạy Hóa cấp 2', N'https://www.youtube.com/watch?v=zrYEIOIkoKU'),
(33, 44, 1, N'0987654328', 1987, N'Q.Tân Bình, TP.HCM', '649325713695', N'Nữ', 9, N'Cấp 2', '2024-01-08', N'Chuyên dạy Lý cấp 2', N'https://www.youtube.com/watch?v=moYnDSp7eoo'),
(34, 42, 1, N'0987654329', 1988, N'Q.Bình Tân, TP.HCM', '012359874632', N'Nữ', 2, N'Cấp 3', '2024-01-09', N'Chuyên dạy Lý cấp 3', N'https://www.youtube.com/watch?v=9k559nGQL9k'),
(35, 43, 1, N'0987654330', 1989, N'Q.Thủ Đức, TP.HCM', '019625486325', N'Nam', 4, N'Cấp 2', '2024-01-10', N'Chuyên dạy Địa cấp 2', N'https://www.youtube.com/watch?v=f9xRzfLhjpI'),
(36, 41, 1, N'0987654331', 1990, N'Q.Tân Phú, TP.HCM', '948622257319', N'Nữ', 6, N'Cấp 1', '2024-01-11', N'Chuyên dạy Toán cấp 1', N'https://www.youtube.com/watch?v=bQES_F6c7c8'),
(37, 30, 1, N'0987654332', 1991, N'Q.Gò Vấp, TP.HCM', '456891237052', N'Nữ', 3, N'Cấp 1', '2024-01-12', N'Chuyên dạy Anh văn cấp 1', N'https://www.youtube.com/watch?v=TONDFqPDOwQ&list=PLmQ5jM-TsmbyKzvva0Qv4J5_Ph2vIZ3YK&index=3'),
(38, 35, 1, N'0987654333', 1992, N'Q.Phú Nhuận, TP.HCM', '656598712390', N'Nam', 7, N'Cấp 2', '2024-01-13', N'Chuyên dạy Hóa cấp 2', N'https://www.youtube.com/watch?v=z7zCjYcnMZY'),
(39, 33, 1, N'0987654334', 1993, N'Q1, TP.HCM', '193258621055', N'Nam', 4, N'Cấp 3', '2024-01-14', N'Chuyên dạy Địa cấp 3', N'https://www.youtube.com/watch?v=rDrSGotW8Fo'),
(40, 31, 1, N'0987654335', 1994, N'Q2, TP.HCM', '258963147012', N'Nam', 5, N'Cấp 2', '2024-01-15', N'Chuyên dạy Lý cấp 2', N'https://www.youtube.com/watch?v=r5MyscGlXf4'),
(41, 34, 1, N'0987654336', 1995, N'Q3, TP.HCM', '593276215302', N'Nữ', 4, N'Cấp 1', '2024-01-16', N'Chuyên dạy Địa cấp 1', N'https://www.youtube.com/watch?v=QgsmURpyzEc'),
(42, 32, 1, N'0987654337', 1996, N'Q4, TP.HCM', '111158632479', N'Nữ', 3, N'Cấp 2', '2024-01-17', N'Chuyên dạy Văn cấp 2', N'https://www.youtube.com/watch?v=mP9eTLAIRoM&list=PLxUdsmN71otAJpfPBdkfSLHFYdrUe30Kg&index=46'),
(43, 39, 1, N'0987654338', 1997, N'Q5, TP.HCM', '012586302486', N'Nam', 8, N'Cấp 3', '2024-01-18', N'Chuyên dạy Hóa cấp 3', N'https://www.youtube.com/watch?v=roPAfZHS-OY'),
(44, 36, 1, N'0987654339', 1998, N'Q6, TP.HCM', '159357486205', N'Nữ', 5, N'Cấp 3', '2024-01-19', N'Chuyên dạy Anh văn cấp 3', N'https://www.youtube.com/watch?v=VTZiBOAJArY'),
(45, 38, 1, N'0987654340', 1999, N'Q7, TP.HCM', '258369486201', N'Nữ', 6, N'Cấp 3', '2024-01-20', N'Chuyên dạy Văn cấp 3', N'https://www.youtube.com/watch?v=8TcSio1YcYE&list=PLxUdsmN71otAJpfPBdkfSLHFYdrUe30Kg&index=55'),
(46, 37, 1, N'0987654341', 2000, N'Q8, TP.HCM', '456890213702', N'Nam', 9, N'Cấp 2', '2024-01-21', N'Chuyên dạy Lý cấp 2', N'https://www.youtube.com/watch?v=rBIsY-9bmXM'),
(47, 26, 1, N'0987654342', 2001, N'Q9, TP.HCM', '158369254301', N'Nữ', 4, N'Cấp 2', '2024-01-22', N'Chuyên dạy Văn cấp 2', N'https://www.youtube.com/watch?v=7TgGL64AXNU'),
(48, 29, 1, N'0987654343', 2002, N'Q10, TP.HCM', '148632590368', N'Nam', 2, N'Cấp 2', '2024-01-23', N'Chuyên dạy Anh văn cấp 2', N'https://www.youtube.com/watch?v=qMJw3LKOpZE'),
(49, 28, 1, N'0987654344', 2003, N'Q11, TP.HCM', '158632590369', N'Nữ', 10, N'Cấp 3', '2024-01-24', N'Chuyên dạy Văn cấp 3', N'https://www.youtube.com/watch?v=DgYfapu29Sc&list=PLxUdsmN71otAJpfPBdkfSLHFYdrUe30Kg&index=43'),
(50, 27, 1, N'0987654345', 2004, N'Q12, TP.HCM', '346902158025', N'Nam', 6, N'Cấp 2', '2024-01-25', N'Chuyên dạy Sử cấp 2', N'https://www.youtube.com/watch?v=MjeFdeEBZBo');

SET IDENTITY_INSERT CV OFF;

-- Insert Rating
INSERT INTO Rating (TutorId, StudentId, SubjectId, Rating)
SELECT TOP 40
    ts.TutorId,
    ABS(CHECKSUM(NEWID()) % 50) + 1 AS StudentId,
    ts.SubjectId,
    ABS(CHECKSUM(NEWID()) % 10) + 1 AS Rating
FROM Tutor t
JOIN TutorSubject ts ON t.Id = ts.TutorId
WHERE t.Active = N'đã kiểm duyệt'
ORDER BY NEWID();



SET IDENTITY_INSERT Feedback ON;

-- Insert Feedback
--Feedback
INSERT INTO Feedback (Id, ModId, StudentId, SlotId, TutorId, FeedbackText)
VALUES
(1, 1, 1, 1, 1, N'Bài giảng rất tốt và dễ hiểu'),
(2, 1, 2, 5, 2, N'Giảng viên nhiệt tình và thân thiện'),
(3, 1, 3, 7, 3, N'Thời lượng bài học hợp lý'),
(4, 1, 4, 8, 4, N'Bài giảng chi tiết và dễ hiểu'),
(5, 1, 5, 10, 5, N'Thời gian học phù hợp'),
(6, 1, 6, 12, 1, N'Giảng viên nhiệt tình, bài giảng hay'),
(7, 1, 7, 14, 2, N'Bài giảng đầy đủ và chi tiết'),
(8, 1, 8, 16, 3, N'Giảng viên vui vẻ và nhiệt tình'),
(9, 1, 9, 18, 4, N'Thời gian học hợp lý'),
(10, 1, 10, 20, 5, N'Bài giảng dễ hiểu và chi tiết'),
(11, 1, 11, 22, 1, N'Giảng viên giảng bài hay và hấp dẫn'),
(12, 1, 12, 24, 2, N'Bài giảng rõ ràng và dễ hiểu'),
(13, 1, 13, 26, 3, N'Giảng viên tận tâm và nhiệt tình'),
(14, 1, 14, 28, 4, N'Bài giảng đầy đủ và chi tiết'),
(15, 1, 15, 30, 5, N'Giảng viên rất nhiệt tình'),
(16, 1, 16, 32, 1, N'Thời lượng bài học hợp lý'),
(17, 1, 17, 34, 2, N'Giảng viên vui vẻ và thân thiện'),
(18, 1, 18, 36, 3, N'Bài giảng rõ ràng và dễ hiểu'),
(19, 1, 19, 38, 4, N'Giảng viên nhiệt tình và tận tâm'),
(20, 1, 20, 40, 5, N'Thời gian học phù hợp và hợp lý');

SET IDENTITY_INSERT Feedback OFF;

SET IDENTITY_INSERT BanAccount ON;
-- Insert into BanAccount for students
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (1, 1, 1, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (2, 1, 2, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (3, 1, 3, 2, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (4, 1, 4, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (5, 1, 5, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (6, 1, 6, 1, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (7, 1, 7, 1, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (8, 1, 8, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (9, 1, 9, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (10, 1, 10, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (11, 1, 11, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (12, 1, 12, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (13, 1, 13, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (14, 1, 14, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (15, 1, 15, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (16, 1, 16, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (17, 1, 17, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (18, 1, 18, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (19, 1, 19, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (20, 1, 20, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (21, 1, 21, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (22, 1, 22, 1, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (23, 1, 23, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (24, 1, 24, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (25, 1, 25, 3, 'lock');

-- Insert into BanAccount for tutors
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (51, 1, 51, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (52, 1, 52, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (53, 1, 53, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (54, 1, 54, 3, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (55, 1, 55, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (56, 1, 56, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (57, 1, 57, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (58, 1, 58, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (59, 1, 59, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (60, 1, 60, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (61, 1, 61, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (62, 1, 62, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (63, 1, 63, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (64, 1, 64, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (65, 1, 65, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (66, 1, 66, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (67, 1, 67, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (68, 1, 68, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (69, 1, 69, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (70, 1, 70, 3, 'lock');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (71, 1, 71, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (72, 1, 72, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (73, 1, 73, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (74, 1, 74, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (75, 1, 75, 3, 'lock');

-- Insert remaining accounts for BanAccount with active status
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (26, 1, 26, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (27, 1, 27, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (28, 1, 28, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (29, 1, 29, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (30, 1, 30, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (31, 1, 31, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (32, 1, 32, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (33, 1, 33, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (34, 1, 34, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (35, 1, 35, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (36, 1, 36, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (37, 1, 37, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (38, 1, 38, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (39, 1, 39, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (40, 1, 40, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (41, 1, 41, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (42, 1, 42, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (43, 1, 43, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (44, 1, 44, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (45, 1, 45, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (46, 1, 46, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (47, 1, 47, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (48, 1, 48, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (49, 1, 49, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (50, 1, 50, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (76, 1, 76, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (77, 1, 77, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (78, 1, 78, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (79, 1, 79, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (80, 1, 80, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (81, 1, 81, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (82, 1, 82, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (83, 1, 83, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (84, 1, 84, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (85, 1, 85, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (86, 1, 86, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (87, 1, 87, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (88, 1, 88, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (89, 1, 89, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (90, 1, 90, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (91, 1, 91, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (92, 1, 92, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (93, 1, 93, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (94, 1, 94, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (95, 1, 95, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (96, 1, 96, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (97, 1, 97, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (98, 1, 98, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (99, 1, 99, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (100, 1, 100, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (101, 1, 101, 0, 'active');
INSERT INTO BanAccount (Id, ModId, AccountId, AmountOfReport, Status) VALUES (102, 1, 102, 0, 'active');

SET IDENTITY_INSERT BanAccount OFF;

SET IDENTITY_INSERT ReasonDenyCv ON;
INSERT INTO ReasonDenyCv (Id, ModId, TutorId, Reason, CreatedAt)
VALUES (1, 1, 2, N'Hình ảnh của hồ sơ bị mờ.', '2024-07-18');

INSERT INTO ReasonDenyCv (Id, ModId, TutorId, Reason, CreatedAt)
VALUES (2, 1, 4, N'Kinh nghiệm làm việc không đủ yêu cầu.', '2024-07-18');

INSERT INTO ReasonDenyCv (Id, ModId, TutorId, Reason, CreatedAt)
VALUES (3, 1, 6, N'Hình ảnh chứng chỉ quá mờ', '2024-07-18');

INSERT INTO ReasonDenyCv (Id, ModId, TutorId, Reason, CreatedAt)
VALUES (4, 1, 8, N'Thông tin liên lạc không liên hệ được.', '2024-07-18');

INSERT INTO ReasonDenyCv (Id, ModId, TutorId, Reason, CreatedAt)
VALUES (5, 1, 10, N'Không phù hợp với tiêu chí của chúng tôi.', '2024-07-18');

SET IDENTITY_INSERT ReasonDenyCv OFF;

SET IDENTITY_INSERT Wallet ON;
INSERT INTO Wallet(Id,StudentId,AdminId,Balance,Status)VALUES
(1,1,1,100000.00,N'thành công'),
(2,1,1,200000.00,N'đang xử lý');
SET IDENTITY_INSERT Wallet OFF;

SET IDENTITY_INSERT Wallet ON;
INSERT INTO Wallet(Id,StudentId,AdminId,Balance,Status)VALUES
(3,1,1,100000.00,N'thành công');
SET IDENTITY_INSERT Wallet OFF;
SET IDENTITY_INSERT Wallet ON;
INSERT INTO Wallet(Id,StudentId,AdminId,Balance,Status)VALUES
(4,1,1,100000.00,N'thành công');
SET IDENTITY_INSERT Wallet OFF;
SET IDENTITY_INSERT Wallet ON;
INSERT INTO Wallet(Id,StudentId,AdminId,Balance,Status)VALUES
(5,2,1,100000.00,N'đang xử lý'),
(6,3,1,100000.00,N'đang xử lý');
SET IDENTITY_INSERT Wallet OFF;

SET IDENTITY_INSERT Salary ON;
INSERT INTO Salary (Id,TutorId,SlotId ,AdminId, Balance,Status, CourseStatus)
VALUES
(1, 1,134,1,200000.000, N'đang xử lý', N'chưa hoàn thành'),
(2, 5,16,1,200000.000, N'đang xử lý', N'chưa hoàn thành'),
(3, 5,18,1,200000.000, N'đang xử lý', N'chưa hoàn thành'),
(4, 5,20,1,200000.000, N'đang xử lý', N'chưa hoàn thành'),
(5, 9,14,1,200000.000, N'đang xử lý', N'chưa hoàn thành');

SET IDENTITY_INSERT Salary OFF;

SET IDENTITY_INSERT Payment ON;
INSERT INTO Payment(Id,TutorId,AdminId, BankAccountNumber,Bank)
VALUES
(1, 1,1,'071000261892', N'Vietcombank');

SET IDENTITY_INSERT Payment OFF;