
-- Database Creation
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE DATABASE "COS730UserModule"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

-- Schema Creation

CREATE SCHEMA "user_module"
    AUTHORIZATION postgres;
COMMENT ON SCHEMA "UserModule"
    IS 'This schema contains a collection of objects relating to the user module implementaion';

-- User Table Creation
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
	
CREATE TABLE user_module.nav_user
(
    id serial NOT NULL,
	username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
	firstname character varying(50) COLLATE pg_catalog."default" NOT NULL,
	lastname character varying(50) COLLATE pg_catalog."default" NOT NULL,
	email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cell_number character varying(12) COLLATE pg_catalog."default" NOT NULL,
	mac_address character varying(50) COLLATE pg_catalog."default" NULL,
	activated boolean NOT NULL DEFAULT true,
	is_admin boolean NOT NULL DEFAULT false,
    created_date date NOT NULL DEFAULT CURRENT_DATE,
	last_modified_date date NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT nav_user_pkey PRIMARY KEY (id),
    CONSTRAINT nav_user_email_unique UNIQUE (email),
    CONSTRAINT nav_user_username_unique UNIQUE(username),
	CONSTRAINT nav_user_email_format_check CHECK (email~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$') NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE user_module.nav_user
    OWNER to postgres;
	
CREATE INDEX nav_user_email_idx ON user_module.nav_user (email);

-- User Roles Table Creation
-- -------------------------------------------------------------------------------------------

CREATE TABLE user_module.nav_user_role
(
    username character varying(50) NOT NULL,
    role character varying(20) NOT NULL DEFAULT 'USER',
    PRIMARY KEY (username),
    CONSTRAINT fk_username FOREIGN KEY (username)
        REFERENCES user_module.nav_user (username) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE user_module.nav_user_role
    OWNER to postgres;

-- Trigger Statements
-- --------------------------------------------------------------------------------------------------------------------------------------------------------	

-- On Insert Trigger Statement
CREATE FUNCTION trigger_insert_nav_user_roles_function()
RETURNS trigger AS $$
BEGIN
  IF (select nav_user.is_admin from user_module.nav_user where nav_user.username = NEW.username) THEN
  	INSERT INTO user_module.nav_user_role(username, role) VALUES (NEW.username, 'ADMIN');
  ELSE
  	INSERT INTO user_module.nav_user_role(username) VALUES (NEW.username);
  END IF;
  RETURN NEW;
END $$ LANGUAGE 'plpgsql';
    
CREATE TRIGGER trigger_insert_nav_user_roles
AFTER INSERT ON user_module.nav_user
FOR EACH ROW
EXECUTE PROCEDURE trigger_insert_nav_user_roles_function();


-- On Update Trigger Statement
CREATE FUNCTION trigger_update_nav_user_roles_function()
RETURNS trigger AS $$
BEGIN
  IF (select nav_user.is_admin from user_module.nav_user where nav_user.username = NEW.username) THEN
  	UPDATE user_module.nav_user_role SET role = 'ADMIN' WHERE nav_user_role.username = NEW.username;
  ELSE
  	UPDATE user_module.nav_user_role SET role = 'USER' WHERE nav_user_role.username = NEW.username;
  END IF;
  RETURN NEW;
END $$ LANGUAGE 'plpgsql';
    
CREATE TRIGGER trigger_update_nav_user_roles
AFTER UPDATE ON user_module.nav_user
FOR EACH ROW
EXECUTE PROCEDURE trigger_update_nav_user_roles_function();
-- --------------------------------------------------------------------------------------------------------------------------------------------------------	

-- Insert Statements
-- --------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO user_module.nav_user (username,  password, firstname, lastname , email, cell_number, mac_address, activated, is_admin)
VALUES ('Admin', crypt('P@ssW0rd', gen_salt('bf',8)) ,'AdminName' ,'AdminLastName', 'admin@gmail.com', '+27711640514','FD-C3-65-2D-DD-25', true, true);

INSERT INTO user_module.nav_user (username, password, firstname, lastname, email, cell_number, mac_address , activated, is_admin)
VALUES ('Ted90', crypt('TedPass', gen_salt('bf',8)),'Teddy' ,'Bigg', 'ted@gmail.com', '+27833640514', 'BE-09-E0-4D-22-C1', true, false);
INSERT INTO user_module.nav_user (username, password, firstname, lastname, email, cell_number, mac_address, activated, is_admin)
VALUES ('Thando', crypt('ThandoPass', gen_salt('bf',8)),'Thandokuhle' ,'Kunene', 'tkunene@gmail.com', '+27711640514', 'F0-1B-38-FE-82-97', true, false);
INSERT INTO user_module.nav_user (username, password, firstname, lastname, email, cell_number, mac_address, activated, is_admin)
VALUES ('Stevo', crypt('StevoPass', gen_salt('bf',8)),'Steve' ,'Nash', 'nash@gmail.com', '+27700640504', '40-83-D4-10-B1-99', true, false);
INSERT INTO user_module.nav_user (username, password, firstname, lastname, email, cell_number, mac_address, activated, is_admin)
VALUES ('ChrisRk', crypt('ChrisPass', gen_salt('bf',8)),'Chris' ,'Brown', 'brown@gmail.com', '+27611440316', '7D-26-62-03-1E-F2', true, false);
-- --------------------------------------------------------------------------------------------------------------------------------------------------------

select * from user_module.nav_user_role;
select * from user_module.nav_user;
