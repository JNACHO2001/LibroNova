create database biblioteca;
drop database biblioteca;
use biblioteca;
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) unique NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(30) UNIQUE NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE partner (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_ VARCHAR(100) NOT NULL,
    asset BOOLEAN DEFAULT TRUE
);
CREATE TABLE loan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_book INT NOT NULL,
    id_partner INT NOT NULL,
    delivery_date DATE NOT NULL,
    return_date DATE,
    return_ BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_book) REFERENCES books(id),
    FOREIGN KEY (id_partner) REFERENCES partner(id)
);

INSERT INTO books (title, author, isbn, stock) VALUES
('Cien años de soledad', 'Gabriel García Márquez', '9780307474728', 5),
('El Quijote', 'Miguel de Cervantes', '9788491050297', 3),
('1984', 'George Orwell', '9780451524935', 4);

INSERT INTO partner (name_, asset) VALUES
('Ana Pérez',  TRUE),
('Carlos Gómez',  TRUE),
('Luisa Rodríguez',  FALSE); -- socio inactivo para pruebas

select * from partner;
select * from books;

