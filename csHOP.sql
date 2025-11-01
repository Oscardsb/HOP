
DROP database if exists csHOP;
CREATE DATABASE csHOP;
use csHOP;

create table usuario(
	id INT auto_increment primary key,
    nombreC VARCHAR(100),
    username VARCHAR(100) NOT NULL UNIQUE,
	correo VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(1024) NOT NULL,
    salt VARCHAR(5) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
	fregistro DATETIME DEFAULT current_timestamp
);

INSERT INTO usuario(correo,clave,salt,username,nombreC) VALUES('oscardsb@gmail.com','926e27b76d9e5b7808b45dbaf6d74aa839a0a1265d014b929bf1292f1c645ee1','abcde','Oscardsb','Oscar Solis Barrientos');


SELECT * FROM usuario;
SELECT correo, salt FROM usuario;

SELECT salt FROM usuario WHERE correo = 'oscardsb@gmail.com';

SELECT * FROM usuario WHERE correo LIKE   '%@gmail%';

SELECT * FROM usuario WHERE fregistro  > current_date();

UPDATE usuario SET correo = 'pruebacambio@gmail.com' WHERE id = 2;

DELETE FROM usuario where id = 4;