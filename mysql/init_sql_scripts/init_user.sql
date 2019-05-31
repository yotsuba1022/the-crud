CREATE USER IF NOT EXISTS 'clu'@'localhost' IDENTIFIED WITH mysql_native_password BY 'clu123';
GRANT ALL PRIVILEGES ON the_crud.* TO 'clu'@'localhost';
