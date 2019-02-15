CREATE TABLE users(
	user_id int not null auto_increment,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    username varchar(20) not null,
	age int not null,
    gender boolean not null,
    email varchar(40) not null,
    password varchar(16) not null,
    activated boolean not null,
    PRIMARY KEY (user_id),
    UNIQUE(username),
    UNIQUE(email)
);

CREATE TABLE roles(
	role_id int not null auto_increment,
    role_name varchar(12) not null,
    role_description varchar(255) not null,
    PRIMARY KEY (role_id),
    UNIQUE(role_name)
);

CREATE TABLE category (
	category_id int not null auto_increment,
    category_name varchar(20) not null,
    category_description varchar(255) not null,
    PRIMARY KEY(category_id),
    UNIQUE(category_name)
);

CREATE TABLE books(
	book_id int not null auto_increment,
    book_title varchar(30) not null,
    book_author varchar(40) not null,
    category_id int not null,
    user_id int not null,
    status int not null,
    PRIMARY KEY (book_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE user_role(
	user_role_id int not null auto_increment,
	user_id int not null,
    role_id int not null,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);



