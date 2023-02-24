USE day04_test01_bookstore;
INSERT INTO books VALUES (0, '蛊真人', '蛊真人', 56, 0, 13, 'updfs/dfdsf');

SELECT * FROM books;

UPDATE books SET title=?,author=?,price=?,stock=?,img_path=? WHERE id=?;
SELECT * FROM books WHERE id = ?;
DELETE FROM books WHERE id = ?;

SELECT * FROM books LIMIT 4,4;

SELECT * FROM books WHERE price>=? AND price<=? LIMIT ?,?;

SELECT * FROM users WHERE username = ? AND password = sha1(?);

SELECT * FROM orders WHERE user_id = ?;
SELECT * FROM users WHERE username = 'admin' AND password = sha1(123456);

SELECT * FROM users WHERE username = ?;
INSERT INTO users VALUES (?,?,sha1(?),?);

SELECT * FROM orders WHERE user_id = 1;

INSERT INTO orders(id, order_time, total_count, total_amount, state, user_id) VALUES (?,?,?,?,?,?);