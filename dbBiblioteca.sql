CREATE DATABASE dbBiblioteca;

USE dbBiblioteca;

CREATE TABLE roles (
	idrol INT PRIMARY KEY AUTO_INCREMENT,
    namerol char(5),
    is_active BOOLEAN DEFAULT TRUE
);

INSERT INTO roles (namerol) VALUES
("Admin"),
("User");

CREATE TABLE users(
	iduser INT PRIMARY KEY AUTO_INCREMENT,
    dni char(8),
    name VARCHAR(50),
    surname VARCHAR(50),
    username VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(20),
    rol INT,
	CONSTRAINT FK_user_roles FOREIGN KEY (rol) REFERENCES roles (idrol),
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE categories(
	idcategory INT PRIMARY KEY AUTO_INCREMENT,
    namecategory VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE books(
	idbook INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    isbn CHAR(13),
    num_pages INT,
    front_page VARCHAR(255),
    stock INT,
    category INT,
    CONSTRAINT FK_books_category FOREIGN KEY (category) REFERENCES categories (idcategory),
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE loans(
	idloan INT PRIMARY KEY AUTO_INCREMENT,
    loan_date DATETIME,
    return_date_approx DATETIME,
    return_date_real DATETIME,
    estatus ENUM ("Active","Finalized") DEFAULT "Active",
    user INT,
    book INT,
    CONSTRAINT FK_loans_user FOREIGN KEY (user) REFERENCES users (iduser),
    CONSTRAINT FK_loans_book FOREIGN KEY (book) REFERENCES books (idbook)
);

CREATE TABLE reservations(
	idreservation INT PRIMARY KEY AUTO_INCREMENT,
    reserve_date DATETIME,
    reserve_code INT,
    state ENUM ("Active","Canceled","Finalized") DEFAULT "Active",
    user INT,
    book INT,
    CONSTRAINT FK_reservation_user FOREIGN KEY (user) REFERENCES users (iduser),
    CONSTRAINT FK_reservation_book FOREIGN KEY (book) REFERENCES books (idbook)
);