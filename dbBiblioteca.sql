CREATE DATABASE dbBiblioteca;

USE dbBiblioteca;

CREATE TABLE roles (
	id INT PRIMARY KEY AUTO_INCREMENT,
    namerol char(5),
    is_active BOOLEAN DEFAULT TRUE
);

INSERT INTO roles (namerol) VALUES
("Admin"),
("User");

CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
    dni char(8),
    name VARCHAR(50),
    surname VARCHAR(50),
    username VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(20),
    rol INT,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
    namecategory VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE books(
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    isbn CHAR(13),
    num_pages INT,
    front_page VARCHAR(255),
    stock INT,
    category INT,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE loans(
	id INT PRIMARY KEY AUTO_INCREMENT,
    loan_date DATETIME,
    return_date_approx DATETIME,
    return_date_real DATETIME,
    estatus ENUM ("Active","Finalized") DEFAULT "Active",
    user INT,
    book INT
);

CREATE TABLE reservations(
	id CHAR(8) PRIMARY KEY,
    reserve_date DATETIME,
    reserve_code INT,
    state ENUM ("Active","Canceled","Finalized") DEFAULT "Active",
    user INT,
    book INT
);