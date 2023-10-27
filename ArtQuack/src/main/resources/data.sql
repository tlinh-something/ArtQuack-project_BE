INSERT INTO Category (cateID, cateName) VALUES
('1', 'Category 1'),
('2', 'Category 2'),
('3', 'Category 3');

INSERT INTO Level (levelID, levelName) VALUES
('1', 'Level 1'),
('2', 'Level 2');

INSERT INTO Instructor (rate, status, certificate, email, instructorID, name, password, role, summarize) VALUES
(4, 1, 'Certificate 1', 'instructor1@example.com', '1', 'Instructor 1', 'password1', 'role1', 'Summary 1'),
(5, 1, 'Certificate 2', 'instructor2@example.com', '2', 'Instructor 2', 'password2', 'role2', 'Summary 2');

INSERT INTO Course (rate, status, viewer, upload_date, cateID, courseID, description, instructorID, levelID, name) VALUES
(4, 1, 100, '2023-10-25', '1', '1', 'Course 1 description', '1', '1', 'Course 1'),
(5, 1, 200, '2023-10-26', '2', '2', 'Course 2 description', '2', '2', 'Course 2');

INSERT INTO Chapter (status, chapterID, chapterName, courseID) VALUES
(1, '1', 'Chapter 1', '1'),
(1, '2', 'Chapter 2', '1'),
(1, '3', 'Chapter 3', '2');

INSERT INTO Item (status, chapterID, content, itemName) VALUES
(1, '1', 'Item 1 content', 'Item 1'),
(1, '1', 'Item 2 content', 'Item 2'),
(1, '2', 'Item 3 content', 'Item 3');

INSERT INTO Post (status, date, author, cateID, content, title) VALUES
(1, '2023-10-25', 'Author 1', '1', 'Post 1 content', 'Post 1'),
(1, '2023-10-26', 'Author 2', '2', 'Post 2 content', 'Post 2');

INSERT INTO Student (status, email, name, password, role, studentID) VALUES
(1, 'student1@example.com', 'Student 1', 'password1', 'role1', '1'),
(1, 'student2@example.com', 'Student 2', 'password2', 'role2', '2');

INSERT INTO Review (rate, status, date, comment, courseID, reviewID, studentID) VALUES
(4, 1, '2023-10-25', 'Review 1 comment', '1', '1', '1'),
(5, 1, '2023-10-26', 'Review 2 comment', '2', '2', '2');

INSERT INTO Enrollment (status, courseID, studentID) VALUES
(1, '1', '1'),
(1, '1', '2'),
(1, '2', '1');

INSERT INTO Submission (grade, status, chapterID, comment, final_project, studentID, submitID) VALUES
(85, 1, '2', 'Submission 1 comment', 'Project 1', '1', '1'),
(92, 1, '3', 'Submission 2 comment', 'Project 2', '2', '2');

INSERT INTO Complete (itemID, status, date, completeID) VALUES
(1, 1, '2023-10-25', '1'),
(2, 1, '2023-10-26', '2'),
(3, 1, '2023-10-27', '3');