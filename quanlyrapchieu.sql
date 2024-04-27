-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS quan_ly_rap_phim;
USE quan_ly_rap_phim;

-- Tạo bảng Vai Trò
CREATE TABLE IF NOT EXISTS vai_tro (
    ma_vai_tro INT AUTO_INCREMENT PRIMARY KEY,
    ten_vai_tro NVARCHAR(255) NOT NULL
);

-- Tạo bảng Người Dùng
CREATE TABLE IF NOT EXISTS nguoi_dung (
    ma_nguoi_dung INT AUTO_INCREMENT PRIMARY KEY,
    ten_dang_nhap NVARCHAR(255) NOT NULL,
    mat_khau VARCHAR(255) NOT NULL,
    ho_ten NVARCHAR(255) NOT NULL,
    dia_chi NVARCHAR(255),
    so_dien_thoai VARCHAR(12),
    ngay_sinh DATE,
    email VARCHAR(255),
    ma_vai_tro INT,
    FOREIGN KEY (ma_vai_tro) REFERENCES vai_tro(ma_vai_tro)
);

-- Tạo bảng Thể Loại
CREATE TABLE IF NOT EXISTS the_loai (
	ma_the_loai INT AUTO_INCREMENT PRIMARY KEY,
    ten_the_loai NVARCHAR(50) NOT NULL
);

-- Tạo bảng Phim
CREATE TABLE IF NOT EXISTS phim (
    ma_phim INT AUTO_INCREMENT PRIMARY KEY,
    ten_phim NVARCHAR(255) NOT NULL,
    anh NVARCHAR(255) NOT NULL,
    dao_dien NVARCHAR(255) NOT NULL,
    dien_vien NVARCHAR(255) NOT NULL,
    thoi_luong NVARCHAR(50) NOT NULL,
    khoi_chieu VARCHAR(100) NOT NULL,
    mo_ta NVARCHAR(500)
);

-- Tạo bảng Phim_The_Loai để quản lý mối quan hệ nhiều-nhiều giữa Phim và Thể Loại
CREATE TABLE IF NOT EXISTS phim_the_loai (
    ma_phim INT NOT NULL,
    ma_the_loai INT NOT NULL,
    PRIMARY KEY (ma_phim, ma_the_loai),
    FOREIGN KEY (ma_phim) REFERENCES phim(ma_phim),
    FOREIGN KEY (ma_the_loai) REFERENCES the_loai(ma_the_loai)
);

-- Tạo bảng Phòng Chiếu
CREATE TABLE IF NOT EXISTS phong_chieu (
    ma_phong INT AUTO_INCREMENT PRIMARY KEY,
    ten_phong NVARCHAR(255) NOT NULL,
    suc_chua INT NOT NULL
);

-- Tạo bảng Ghế
CREATE TABLE IF NOT EXISTS ghe (
	ma_ghe INT AUTO_INCREMENT PRIMARY KEY,
	ten_ghe VARCHAR(20) NOT NULL
);

-- Tạo bảng Suất Chiếu
CREATE TABLE IF NOT EXISTS suat_chieu (
    ma_suat_chieu INT AUTO_INCREMENT PRIMARY KEY,
    ma_phim INT NOT NULL,
    ma_phong INT NOT NULL,    
    thoi_gian_chieu DATETIME NOT NULL,
    FOREIGN KEY (ma_phim) REFERENCES phim(ma_phim),
    FOREIGN KEY (ma_phong) REFERENCES phong_chieu(ma_phong)
);

-- Tạo bảng Loại Đồ Kèm Thêm
CREATE TABLE IF NOT EXISTS loai_do_kem_them (
    ma_loai_do_kem_them INT AUTO_INCREMENT PRIMARY KEY,
    ten_loai NVARCHAR(50) NOT NULL
);

-- Tạo bảng Món Kèm
CREATE TABLE IF NOT EXISTS mon_kem (
    ma_mon_kem INT AUTO_INCREMENT PRIMARY KEY,
    ten_mon_kem NVARCHAR(255) NOT NULL,
    ma_loai_do_kem_them INT NOT NULL,
    gia_ban DECIMAL(10, 2) NOT NULL,
    mo_ta NVARCHAR(500),
    hinh_anh NVARCHAR(255),
    FOREIGN KEY (ma_loai_do_kem_them) REFERENCES loai_do_kem_them(ma_loai_do_kem_them)
);

-- Tạo bảng Vé
CREATE TABLE IF NOT EXISTS ve (
    ma_ve INT AUTO_INCREMENT PRIMARY KEY,
    ma_suat_chieu INT NOT NULL,
    ma_ghe INT NOT NULL,
    ma_nguoi_dung INT NOT NULL,
    ma_mon_kem INT,
    gia_ve DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ma_suat_chieu) REFERENCES suat_chieu(ma_suat_chieu),
    FOREIGN KEY (ma_ghe) REFERENCES ghe(ma_ghe),
    FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung(ma_nguoi_dung),
    FOREIGN KEY (ma_mon_kem) REFERENCES mon_kem(ma_mon_kem)
);

-- Tạo bảng Hóa Đơn
CREATE TABLE IF NOT EXISTS hoa_don (
    ma_hoa_don INT AUTO_INCREMENT PRIMARY KEY,
    ma_ve INT NOT NULL,
    tong_tien DECIMAL(10, 2) NOT NULL,
    ngay_tao DATETIME NOT NULL,
    FOREIGN KEY (ma_ve) REFERENCES ve(ma_ve)
);

-- Chèn bản ghi cho Vai Trò
INSERT INTO vai_tro (ten_vai_tro) VALUES
('Admin'),
('Nhân Viên'),
('Khách Hàng');

-- Chèn bản ghi cho Admin
INSERT INTO nguoi_dung (ten_dang_nhap, mat_khau, ho_ten, ma_vai_tro, email) VALUES
('admin', '123456', 'admin', 1, 'pthehung62@gmail.com');

-- Chèn bản ghi cho Nhân Viên
INSERT INTO nguoi_dung (ten_dang_nhap, mat_khau, ho_ten, ma_vai_tro, dia_chi, so_dien_thoai, ngay_sinh, email) VALUES
('nhanvien1', 'password1', 'Nguyễn Văn A', 2, '123 Đường B, TP.HCM', '0123456789', '1985-10-01', 'nva@gmail.com'),
('nhanvien2', 'password2', 'Trần Thị B', 2, '456 Đường C, Hà Nội', '0987654321', '1990-12-15', 'ttb@gmail.com');

-- Chèn bản ghi cho Khách Hàng
INSERT INTO nguoi_dung (ten_dang_nhap, mat_khau, ho_ten, ma_vai_tro, dia_chi, so_dien_thoai, ngay_sinh, email) VALUES
('khachhang1', 'kh123456', 'Lê Thị C', 3, '789 Đường D, Đà Nẵng', '0212345678', '1995-02-23', 'ltc@gmail.com'),
('khachhang2', 'kh234567', 'Phạm Văn D', 3, '321 Đường E, Cần Thơ', '0312345678', '1988-08-08', 'pvd@gmail.com'),
('khachhang3', 'kh345678', 'Ngô Thanh E', 3, '654 Đường F, Nha Trang', '0412345678', '1992-05-15', 'nte@gmail.com'),
('khachhang4', 'kh456789', 'Đỗ Minh G', 3, '987 Đường G, Huế', '0512345678', '1999-11-30', 'dmg@gmail.com'),
('khachhang5', 'kh567890', 'Trịnh Hồng H', 3, '246 Đường H, Quảng Ninh', '0612345678', '1993-07-21', 'thh@gmail.com'),
('khachhang6', 'kh678901', 'Vũ Thị I', 3, '135 Đường I, Bình Dương', '0712345678', '1990-01-16', 'vti@gmail.com'),
('khachhang7', 'kh789012', 'Mai Quốc K', 3, '864 Đường J, Quảng Bình', '0812345678', '1987-12-25', 'mqk@gmail.com');

INSERT INTO the_loai (ten_the_loai) VALUES
('Hành động'),
('Kinh dị'),
('Tình cảm'),
('Hài hước'),
('Phiêu lưu'),
('Khoa học viễn tưởng'),
('Tài liệu'),
('Hoạt hình');

INSERT INTO phim (ten_phim, anh, dao_dien, dien_vien, thoi_luong, khoi_chieu, mo_ta) VALUES
('Chuyến Tàu Sinh Tử', 'upload/train_to_busan.jpg', 'Yeon Sang-ho', 'Gong Yoo, Ma Dong-seok', '118 phút', '2016-07-20', 'Một virus bùng phát, một chuyến tàu đến Busan trở thành nơi sinh tồn duy nhất.'),
('Vệ Binh Dải Ngân Hà', 'upload/guardians_of_the_galaxy.jpg', 'James Gunn', 'Chris Pratt, Zoe Saldana', '121 phút', '2014-08-01', 'Nhóm vệ binh quậy phá trong không gian với nhiệm vụ bảo vệ dải ngân hà.'),
('Ngoại Già Tuổi Đôi Mươi', 'upload/miss_granny.jpg', 'Hwang Dong-hyuk', 'Na Moon-hee, Shim Eun-kyung', '124 phút', '2014-01-22', 'Một bà lão 70 tuổi trở lại tuổi 20 của chính mình.'),
('Jurassic World', 'upload/jurassic_world.jpg', 'Colin Trevorrow', 'Chris Pratt, Bryce Dallas Howard', '124 phút', '2015-06-12', 'Đảo khủng long với loạt sinh vật đã tuyệt chủng được hồi sinh.'),
('Interstellar', 'upload/interstellar.jpg', 'Christopher Nolan', 'Matthew McConaughey, Anne Hathaway', '169 phút', '2014-11-07', 'Cuộc hành trình xuyên không gian để tìm kiếm hành tinh mới cho loài người.'),
('Inception', 'upload/inception.jpg', 'Christopher Nolan', 'Leonardo DiCaprio, Joseph Gordon-Levitt', '148 phút', '2010-07-16', 'Một kẻ lừa đảo được thuê để thâm nhập vào giấc mơ của một doanh nhân.'),
('The Matrix', 'upload/the_matrix.jpg', 'Lana Wachowski, Lilly Wachowski', 'Keanu Reeves, Laurence Fishburne', '136 phút', '1999-03-31', 'Một trình lập trình viên được khai sáng về thực tế ảo của nhân loại.'),
('Avatar', 'upload/avatar.jpg', 'James Cameron', 'Sam Worthington, Zoe Saldana', '162 phút', '2009-12-18', 'Một cựu chiến binh được gửi đến Pandora, nơi anh ta trở nên xung đột giữa hai thế giới.'),
('Titanic', 'upload/titanic.jpg', 'James Cameron', 'Leonardo DiCaprio, Kate Winslet', '195 phút', '1997-12-19', 'Câu chuyện bi thảm của con tàu Titanic qua mối tình của Jack và Rose.'),
('Gladiator', 'upload/gladiator.jpg', 'Ridley Scott', 'Russell Crowe, Joaquin Phoenix', '155 phút', '2000-05-05', 'Một chiến binh La Mã trả thù cái chết của gia đình mình.'),
('Mad Max: Fury Road', 'upload/mad_max_fury_road.jpg', 'George Miller', 'Tom Hardy, Charlize Theron', '120 phút', '2015-05-15', 'Cuộc rượt đuổi hoang dã trong một thế giới hậu tận thế.'),
('Pulp Fiction', 'upload/pulp_fiction.jpg', 'Quentin Tarantino', 'John Travolta, Uma Thurman', '154 phút', '1994-10-14', 'Các câu chuyện giao nhau của tội phạm ở Los Angeles.'),
('Fight Club', 'upload/fight_club.jpg', 'David Fincher', 'Brad Pitt, Edward Norton', '139 phút', '1999-10-15', 'Một nhân viên văn phòng chán nản tạo ra một câu lạc bộ chiến đấu ngầm.'),
('The Godfather', 'upload/the_godfather.jpg', 'Francis Ford Coppola', 'Marlon Brando, Al Pacino', '175 phút', '1972-03-24', 'Ép buộc gia nhập gia đình Mafia, Michael Corleone dần dịch chuyển từ bên lề đến nắm quyền lực.'),
('The Dark Knight', 'upload/the_dark_knight.jpg', 'Christopher Nolan', 'Christian Bale, Heath Ledger', '152 phút', '2008-07-18', 'Batman đối đầu với tên Joker tàn bạo để bảo vệ thành phố Gotham.'),
('Forrest Gump', 'upload/forrest_gump.jpg', 'Robert Zemeckis', 'Tom Hanks, Robin Wright', '142 phút', '1994-07-06', 'Câu chuyện về một người đàn ông với chỉ số IQ thấp nhưng đã trải qua nhiều sự kiện lịch sử Mỹ.'),
('The Lord of the Rings: The Fellowship of the Ring', 'upload/lotr_fellowship.jpg', 'Peter Jackson', 'Elijah Wood, Ian McKellen', '178 phút', '2001-12-19', 'Cuộc hành trình của Frodo và chiếc nhẫn quyền năng.'),
('Star Wars: The Force Awakens', 'upload/star_wars_force_awakens.jpg', 'J.J. Abrams', 'Daisy Ridley, John Boyega', '138 phút', '2015-12-18', 'Sức mạnh mới thức tỉnh và cuộc chiến chống lại kẻ thù cũ.'),
('La La Land', 'upload/la_la_land.jpg', 'Damien Chazelle', 'Ryan Gosling, Emma Stone', '128 phút', '2016-12-09', 'Câu chuyện tình yêu giữa một nhạc sĩ jazz và một diễn viên trẻ.'),
('Django Unchained', 'upload/django_unchained.jpg', 'Quentin Tarantino', 'Jamie Foxx, Christoph Waltz', '165 phút', '2012-12-25', 'Một nô lệ được giải phóng trên con đường trả thù chủ cũ của mình.');

INSERT INTO phim_the_loai (ma_phim, ma_the_loai) VALUES
(1, 1), -- Chuyến Tàu Sinh Tử có thể loại Hành Động (1)
(1, 2), -- Chuyến Tàu Sinh Tử có thể loại Kinh Dị (2)
(2, 6), -- Vệ Binh Dải Ngân Hà có thể loại Khoa Học Viễn Tưởng (6)
(2, 4), -- Vệ Binh Dải Ngân Hà có thể loại Hài (4)
(3, 3), -- Ngoại Già Tuổi Đôi Mươi có thể loại Tình Cảm (3)
(4, 6), -- Jurassic World có thể loại Khoa Học Viễn Tưởng (6)
(5, 6), -- Interstellar có thể loại Khoa Học Viễn Tưởng (6)
(6, 1), -- Inception có thể loại Hành Động (1)
(6, 5), -- Inception có thể loại Phiêu Lưu (5)
(7, 6), -- The Matrix có thể loại Khoa Học Viễn Tưởng (6)
(8, 1), -- Avatar có thể loại Hành Động (1)
(8, 6), -- Avatar có thể loại Khoa Học Viễn Tưởng (6)
(9, 3), -- Titanic có thể loại Tình Cảm (3)
(10, 1), -- Gladiator có thể loại Hành Động (1)
(11, 1), -- Mad Max: Fury Road có thể loại Hành Động (1)
(12, 4), -- Pulp Fiction có thể loại Hài (4)
(12, 7), -- Pulp Fiction có thể loại Tài Liệu (7)
(13, 1), -- Fight Club có thể loại Hành Động (1)
(14, 5), -- The Godfather có thể loại Phiêu Lưu (5)
(14, 7), -- The Godfather có thể loại Tài Liệu (7)
(15, 1), -- The Dark Knight có thể loại Hành Động (1)
(15, 6), -- The Dark Knight có thể loại Khoa Học Viễn Tưởng (6)
(16, 3), -- Forrest Gump có thể loại Tình cảm (3)
(17, 5), -- The Lord of the Rings có thể loại Phiêu lưu (5)
(17, 6), -- The Lord of the Rings có thể loại Khoa học viễn tưởng (6)
(18, 6), -- Star Wars: The Force Awakens có thể loại Khoa học viễn tưởng (6)
(19, 3), -- La La Land có thể loại Tình cảm (3)
(19, 7), -- La La Land có thể loại Tài liệu (7)
(20, 1); -- Django Unchained có thể loại Hành động(1)

INSERT INTO phong_chieu (ten_phong, suc_chua) VALUES
('Phòng Chiếu 1', 100),
('Phòng Chiếu 2', 100),
('Phòng Chiếu 3', 100),
('Phòng Chiếu 4', 100);

INSERT INTO ghe (ten_ghe) VALUES
('Ghế 1'), ('Ghế 2'), ('Ghế 3'), ('Ghế 4'), ('Ghế 5'),
('Ghế 6'), ('Ghế 7'), ('Ghế 8'), ('Ghế 9'), ('Ghế 10'),
('Ghế 11'), ('Ghế 12'), ('Ghế 13'), ('Ghế 14'), ('Ghế 15'),
('Ghế 16'), ('Ghế 17'), ('Ghế 18'), ('Ghế 19'), ('Ghế 20'),
('Ghế 21'), ('Ghế 22'), ('Ghế 23'), ('Ghế 24'), ('Ghế 25'),
('Ghế 26'), ('Ghế 27'), ('Ghế 28'), ('Ghế 29'), ('Ghế 30');

INSERT INTO suat_chieu (ma_phim, ma_phong, thoi_gian_chieu) VALUES
(1, 1, '2024-05-01 09:00:00'),
(2, 1, '2024-05-01 12:00:00'),
(3, 1, '2024-05-01 15:00:00'),
(4, 1, '2024-05-01 18:00:00'),
(5, 2, '2024-05-02 09:30:00'),
(6, 2, '2024-05-02 12:30:00'),
(7, 2, '2024-05-02 15:30:00'),
(8, 2, '2024-05-02 18:30:00'),
(9, 3, '2024-05-03 09:45:00'),
(10, 3, '2024-05-03 12:45:00'),
(11, 3, '2024-05-03 15:45:00'),
(12, 3, '2024-05-03 18:45:00'),
(13, 4, '2024-05-04 10:00:00'),
(14, 4, '2024-05-04 13:00:00'),
(15, 4, '2024-05-04 16:00:00'),
(16, 4, '2024-05-04 19:00:00'),
(17, 1, '2024-06-01 09:00:00'),
(18, 1, '2024-06-01 12:00:00'),
(19, 1, '2024-06-01 15:00:00'),
(20, 1, '2024-06-01 18:00:00'),
(17, 2, '2024-06-02 09:30:00'),
(18, 2, '2024-06-02 12:30:00'),
(19, 2, '2024-06-02 15:30:00'),
(20, 2, '2024-06-02 18:30:00'),
(17, 3, '2024-06-03 09:45:00'),
(18, 3, '2024-06-03 12:45:00'),
(19, 3, '2024-06-03 15:45:00'),
(20, 3, '2024-06-03 18:45:00'),
(17, 4, '2024-06-04 10:00:00'),
(18, 4, '2024-06-04 13:00:00'),
(19, 4, '2024-06-04 16:00:00'),
(20, 4, '2024-06-04 19:00:00');

INSERT INTO loai_do_kem_them (ten_loai) VALUES
('Nước Ngọt'),
('Bỏng Ngô'),
('Combo Đồ Ăn');

INSERT INTO mon_kem (ten_mon_kem, ma_loai_do_kem_them, gia_ban, mo_ta, hinh_anh) VALUES
('Coca Cola', 1, 20000, 'Lon Coca Cola 330ml', 'food_and_drink/coca_cola.jpg'),
('Pepsi', 1, 20000, 'Lon Pepsi 330ml', 'food_and_drink/pepsi.jpg'),
('Bỏng Ngô Vị Phô Mai', 2, 30000, 'Túi bỏng ngô vị phô mai 100g', 'food_and_drink/popcorn_cheese.jpg'),
('Bỏng Ngô Vị Bơ', 2, 30000, 'Túi bỏng ngô vị bơ 100g', 'food_and_drink/popcorn_butter.jpg'),
('Combo 1', 3, 50000, 'Combo 1 lon nước ngọt và 1 túi bỏng ngô vị bơ', 'food_and_drink/combo1.jpg'),
('Combo 2', 3, 70000, 'Combo 2 lon nước ngọt và 1 túi bỏng ngô vị phô mai', 'food_and_drink/combo2.jpg');

-- Chèn bản ghi cho bảng Vé
INSERT INTO ve (ma_suat_chieu, ma_ghe, ma_nguoi_dung, ma_mon_kem, gia_ve) VALUES
(1, 1, 4, NULL, 90000),
(1, 2, 5, NULL, 90000),
(2, 3, 6, 1, 90000),
(2, 4, 7, 2, 90000),
(3, 5, 8, 3, 90000),
(3, 6, 9, 4, 90000),
(4, 7, 10, 5, 90000);

-- Chèn bản ghi cho bảng Hóa Đơn
INSERT INTO hoa_don (ma_ve, tong_tien, ngay_tao) VALUES
(1, 90000, '2024-05-01 10:30:00'),
(2, 90000, '2024-05-01 13:30:00'),
(3, 110000, '2024-05-02 10:45:00'),
(4, 110000, '2024-05-02 13:45:00'),
(5, 120000, '2024-05-03 11:00:00'),
(6, 120000, '2024-05-03 14:00:00'),
(7, 140000, '2024-05-04 11:15:00');
