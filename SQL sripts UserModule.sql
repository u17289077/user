
-- Database Creation
----------------------------------------------------------------------------------------------------------------------------------------------------------
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
----------------------------------------------------------------------------------------------------------------------------------------------------------
	
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
	
-- Insert Statements
----------------------------------------------------------------------------------------------------------------------------------------------------------

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
----------------------------------------------------------------------------------------------------------------------------------------------------------

select * from user_module.nav_user;

---------------------------------------------------------------------------------------------
{
	"username":"testguy",
	"password":"testpass",
	"firstname":"test",
	"lastname":"guy",
	"email":"guy@gmail.com",
	"cell_number":"+27788670914",
	"mac_address":"06-35-B1-5A-B1-63"
}