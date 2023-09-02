CREATE TABLE role (
    role_id NUMBER(2,0) PRIMARY KEY,
    role_type varchar(30)
);
--==============================================================================

CREATE TABLE users (
  id integer PRIMARY KEY,
  role_id NUMBER(2,0) NOT NULL ,
  username varchar(30) NOT NULL,
  password varchar(255) NOT NULL,
  email varchar(50) NOT NULL,
  first_name varchar(30) NOT NULL,
  last_name varchar(30) NOT NULL,
  address varchar(100) NOT NULL,
  phone varchar(11) NOT NULL,
  civil_id varchar(12) NOT NULL,
  is_verified NUMBER(1,0) DEFAULT 0,
  created_at timestamp,
  updated_at timestamp,
  CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role(role_id)
);

CREATE SEQUENCE users_sequence START WITH 1 INCREMENT BY 100;

CREATE OR REPLACE TRIGGER users_on_insert
  BEFORE INSERT ON users
  FOR EACH ROW
BEGIN
  SELECT users_sequence.nextval
  INTO :new.id
  FROM dual;
END;

--==============================================================================
CREATE TABLE token (
    id INTEGER PRIMARY KEY,
    token VARCHAR(255),
    expire_date date,
    expired number(1,0) DEFAULT 0,
    revoked number(1,0) DEFAULT 0,
    user_id INTEGER NOT NULL,
    CONSTRAINT FK_TOKEN_USER_ID FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE SEQUENCE token_sequence;

CREATE OR REPLACE TRIGGER token_on_insert
  BEFORE INSERT ON token
  FOR EACH ROW
BEGIN
  SELECT token_sequence.nextval
  INTO :new.id
  FROM dual;
END;

--==============================================================================
CREATE TABLE category (
  id integer PRIMARY KEY,
  category varchar(30) NOT NULL
);
CREATE SEQUENCE category_sequence;

CREATE OR REPLACE TRIGGER category_on_insert
  BEFORE INSERT ON category
  FOR EACH ROW
BEGIN
  SELECT category_sequence.nextval
  INTO :new.id
  FROM dual;
END;

--==============================================================================

CREATE TABLE book (
  id integer PRIMARY KEY,
  category_id integer NOT NULL,
  title varchar(30) NOT NULL,
  description varchar(500) NOT NULL,
  is_borrowed NUMBER(1,0) DEFAULT 0,
  is_removed NUMBER(1,0) DEFAULT 0,
  created_at timestamp,
  updated_at timestamp,
  CONSTRAINT fk_product_Category FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE SEQUENCE book_sequence;

CREATE OR REPLACE TRIGGER book_on_insert
  BEFORE INSERT ON book
  FOR EACH ROW
BEGIN
  SELECT book_sequence.nextval
  INTO :new.id
  FROM dual;
END;

--==============================================================================

CREATE TABLE borrowed_book (
    borrow_id INTEGER PRIMARY KEY,
    user_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    borrow_date DATE,
    return_date DATE,
    CONSTRAINT fk_bb_User FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_bb_Book FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE SEQUENCE borrowed_book_seq;

CREATE OR REPLACE TRIGGER borrowed_book_on_insert
    BEFORE INSERT ON borrowed_book
    FOR EACH ROW
BEGIN
 SELECT borrowed_book_seq.nextval
 INTO :new.borrow_id
 FROM dual;
END;
 --==============================================================================





