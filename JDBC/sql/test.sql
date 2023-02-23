USE day04_test01_bookstore;
INSERT INTO books VALUES (0, '蛊真人', '蛊真人', 56, 0, 13, 'updfs/dfdsf');

SELECT * FROM books;

UPDATE books SET title=?,author=?,price=?,stock=?,img_path=? WHERE id=?;
SELECT * FROM books WHERE id = ?;
DELETE FROM books WHERE id = ?;

SELECT * FROM books LIMIT 4,4;

SELECT * FROM books WHERE price>=? AND price<=? LIMIT ?,?;