
-- create user 'minh'@'%' identified by 'spring';
-- grant all privileges on * . * to 'minh'@'%';

-- DROP USER root@localhost;

-- SELECT user FROM mysql.user;

-- CREATE DATABASE IF NOT EXISTS QuanLySV
-- CHARACTER SET utf8mb4
-- COLLATE utf8mb4_general_ci;

-- create database student_project; 

-- use student_project;

-- create table users (
-- 	username varchar(45) primary key,
--     `password` varchar(100) not null,
--     enabled tinyint not null
-- );
-- Bảng `users`
INSERT INTO users (username, password, enabled)
VALUES 
  ('minh@fpt.edu.vn', '{noop}min123', 1),
  ('buoi@fpt.edu.vn', '{noop}buoi123', 1),
  ('an@fpt.edu.vn', '{noop}an123', 1),
  ('dung@fpt.edu.vn', '{noop}dung123', 1),
  ('english@fpt.edu.vn', '{noop}english123', 1),
  ('kiet@fpt.edu.vn', '{noop}kiet123', 1),
  ('long@fpt.edu.vn', '{noop}long123', 1),
  ('hanh@fpt.edu.vn', '{noop}hanh123', 1),
  ('hoa@fpt.edu.vn', '{noop}hoa123', 1),
  ('quynh@fpt.edu.vn', '{noop}quynh123', 1);

-- Bảng `authorities`
INSERT INTO authorities (username, authority)
VALUES
  ('minh@fpt.edu.vn', 'ROLE_STUDENT'),
  ('buoi@fpt.edu.vn', 'ROLE_STUDENT'),
  ('an@fpt.edu.vn', 'ROLE_STUDENT'),
  ('dung@fpt.edu.vn', 'ROLE_TEACHER'),
  ('english@fpt.edu.vn', 'ROLE_TEACHER'),
  ('kiet@fpt.edu.vn', 'ROLE_ADMIN'),
  ('long@fpt.edu.vn', 'ROLE_STUDENT'),
  ('hanh@fpt.edu.vn', 'ROLE_STUDENT'),
  ('hoa@fpt.edu.vn', 'ROLE_TEACHER'),
  ('quynh@fpt.edu.vn', 'ROLE_TEACHER');

-- Bảng `school`
INSERT INTO school (id, address, name, number_of_students, phone_number, principal_name, website)
VALUES
(1, 'HCM, Vietnam', 'FPT Cơ sở HCM', 5000, '0123456789', 'Nguyen Van Toan', 'fpt-hcm.edu.vn'),
(2, 'HN, Vietnam', 'FPT Cơ sở HN', 4000, '0987654321', 'Le Thi Bach', 'fpt-hn.edu.vn'),
(3, 'DN, Vietnam', 'FPT Cơ sở DN', 3000, '0123987456', 'Tran Tuan Kiet', 'fpt-dn.edu.vn'),
(4, 'CT, Vietnam', 'FPT Cơ sở CT', 3500, '0987612345', 'Pham Thi Hoa', 'fpt-ct.edu.vn'),
(5, 'VT, Vietnam', 'FPT Cơ sở VT', 2500, '0123987123', 'Nguyen Thi Quynh', 'fpt-vt.edu.vn');

-- Bảng `school_staff`
INSERT INTO school_staff (id, name, phone_number, role, email)
VALUES
(1, 'Nguyen Van Dung', '0123451234', 'Teacher', 'dung@fpt.edu.vn'),
(2, 'Tran Thi English', '0987651234', 'Teacher', 'english@fpt.edu.vn'),
(3, 'Tran Tuan Kiet', '0923220102', 'Admin', 'kiet@fpt.edu.vn'),
(4, 'Pham Van Hoa', '0923456789', 'Teacher', 'hoa@fpt.edu.vn'),
(5, 'Nguyen Thi Quynh', '0912345678', 'Teacher', 'quynh@fpt.edu.vn');

-- Bảng `subject`
INSERT INTO subject (subject_code, credits, description, school_year, semester, subject_name)
VALUES
(101, 3, 'Software Engineering', '2023-2024', 1, 'OOP'),
(102, 2, 'Database Management', '2023-2024', 1, 'DBMS'),
(103, 4, 'Web Development', '2023-2024', 2, 'WEB'),
(104, 3, 'Data Structures', '2023-2024', 2, 'DSA'),
(105, 3, 'Computer Networks', '2023-2024', 1, 'Networking');

-- Bảng `class`
INSERT INTO class (id, room, weekday1, time1, weekday2, time2, school_id, subject_id, school_staff_id)
VALUES
(1, '332', 'Monday', '09:30:00', 'Thursday', '09:30:00', 3, 101, 1),
(2, '003', 'Tuesday', '09:30:00', 'Friday', '09:30:00', 3, 101, 1),
(3, '201', 'Wednesday', '13:00:00', 'Saturday', '13:00:00', 1, 102, 2),
(4, '101', 'Monday', '10:30:00', 'Thursday', '10:30:00', 2, 103, 4),
(5, '202', 'Tuesday', '14:30:00', 'Friday', '14:30:00', 4, 104, 5);

-- Bảng `student_id_card`
INSERT INTO student_id_card (id, date_of_birth, expiry_date, issued_date, photo, student_name)
VALUES
(1, '2003-05-10', '2026-05-10', '2023-09-01', 'photo1.png', 'Nguyen Van An'),
(2, '2003-06-15', '2026-06-15', '2023-09-01', 'photo2.png', 'Le Thi Buoi'),
(3, '2004-06-15', '2027-06-15', '2024-09-01', 'photo3.png', 'Pham Khanh Minh'),
(4, '2003-08-10', '2026-08-10', '2023-09-01', 'photo4.png', 'Nguyen Huu Long'),
(5, '2003-09-20', '2026-09-20', '2023-09-01', 'photo5.png', 'Tran Thi Hanh');

-- Bảng `student`
INSERT INTO student (id, address, date_of_birth, email, enrollment_date, gender, name, phone_number, profile_picture, status, school_id, student_id_card)
VALUES
(1, 'District 1, HCM', '2003-05-10', 'an@fpt.edu.vn', '2023-09-01', 1, 'Nguyen Van An', '0123456789', NULL, 'Active', 3, 1),
(2, 'District 3, HCM', '2003-06-15', 'buoi@fpt.edu.vn', '2023-09-01', 0, 'Le Thi Buoi', '0987654321', NULL, 'Active', 3, 2),
(3, 'District 3, HCM', '2004-06-15', 'minh@fpt.edu.vn', '2024-09-01', 0, 'Pham Khanh Minh', '0987654321', NULL, 'Active', 3, 3),
(4, 'District 5, HCM', '2003-08-10', 'long@fpt.edu.vn', '2023-09-01', 1, 'Nguyen Huu Long', '0912345678', NULL, 'Active', 1, 4),
(5, 'District 7, HCM', '2003-09-20', 'hanh@fpt.edu.vn', '2023-09-01', 0, 'Tran Thi Hanh', '0934567890', NULL, 'Active', 2, 5);

-- Bảng `scoreboard`
INSERT INTO scoreboard (id, semester, score_10_percent, score_20_percent, project_score, school_year, subject_code, student_id)
VALUES
(1, 1, 8.5, 17.0, 59.5, '2023-2024', 101, 1),
(2, 1, 7.5, 15.0, 52.5, '2023-2024', 102, 2),
(3, 1, 9.5, 19.0, 66.5, '2023-2024', 101, 3),
(4, 2, 8.0, 16.0, 54.0, '2023-2024', 103, 4),
(5, 2, 6.5, 13.0, 40.0, '2023-2024', 104, 5);

INSERT INTO student_project.parent
(id, name)
VALUES
(1, 'Nguyen Van Khoa'),
(2, 'Le Thi Bich');

-- Bảng `parent_student`
INSERT INTO parent_student (student_id, parent_id)
VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 1),
(5, 1);

-- Bảng `subject_staff`
INSERT INTO subject_staff (subject_subject_code, school_staff_id)
VALUES
(101, 1),
(102, 2),
(103, 4),
(104, 5),
(105, 3);

-- Bảng `student_class`
INSERT INTO student_project.student_class (class_id, student_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(3, 5),
(4, 2),
(4, 3),
(4, 4),
(1, 5),
(2, 3),
(3, 1),
(4, 1);

-- create database student_project; 

-- use student_project;