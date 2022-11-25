drop table Schedule ;
drop table Master;
drop table Procedure_to_pet;
drop table Procedure_table;
drop table Pet;
drop table User_table;
drop  table Role_table;
drop table Status;
drop table pettype;
drop table Master_to_Procedure;

alter session set current_schema = SYSTEM;
CREATE  TABLE Master (
	id NUMBER(20)  GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	name_master nvARCHAR2(20) NOT NULL,
	surname_master nvarchar2(20) not null,
	description nvarchar2(60) not null,
	CONSTRAINT pk_Master_Id_master PRIMARY KEY (id)
);


CREATE TABLE Procedure_table (
id NUMBER(20) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	name_procedure VARCHAR(20) NOT NULL,
	Price NUMBER(10,2) NOT NULL,
	photo NVARCHAR2(100) NOT NULL,
	description nvarchar2(60) not null,
	CONSTRAINT pk_Procedure_id PRIMARY KEY (id)
);


CREATE  TABLE User_table (
	id NUMBER(20) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	full_name NVARCHAR2(30) NOT NULL,
	email NVARCHAR2(30) NOT NULL,
	login NVARCHAR2(64) NOT NULL,
	password NVARCHAR2(64) NOT NULL,
	id_role NUMBER(20) default 2 NOT NULL,
	CONSTRAINT pk_User_id PRIMARY KEY (id)
);


CREATE TABLE Role_table(
id NUMBER(20) ,
Role_name NVARCHAR2(30) NOT NULL,
	CONSTRAINT pk_Role_id PRIMARY KEY (id )
);




CREATE  TABLE Pet (
	id NUMBER(20)GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	pet_type_id NUMBER(20) NOT NULL,
	age NUMBER(20) NOT NULL,
	id_owner NUMBER(20) NOT NULL,
	nickname nvarchar2(30) not null,
	CONSTRAINT pk_Pet_id PRIMARY KEY (id)
);


--Расписание сделать
CREATE  TABLE Schedule (
	id NUMBER(20) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	pet_id  NUMBER(20) NOT NULL,
	master_id  NUMBER(20) NOT NULL,
	procedure_id  NUMBER(20) NOT NULL,
	owner_id  NUMBER(20) NOT NULL,
	date_ DATE NOT NULL,
	status_id  NUMBER(20) NOT NULL,
	CONSTRAINT pk_Schedule_Record_id PRIMARY KEY (id)
);

CREATE  TABLE Status (
	id NUMBER(20) NOT NULL,
	status_name NVARCHAR2(20) NOT NULL,
	CONSTRAINT pk_Status_id PRIMARY KEY (id)
);

CREATE  TABLE pettype(
	id NUMBER(20) NOT NULL,
	pet_name NVARCHAR2(20) NOT NULL,
	CONSTRAINT pk_Pet_id_id PRIMARY KEY (id)
);


create table Master_to_Procedure(
    id number(20) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    master_id number(20) not null,
    procedure_id number(20) not null,
    CONSTRAINT pk_Master_to_pet_id PRIMARY KEY (id)
);

CREATE  TABLE Procedure_to_pet (
	id NUMBER(20) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	pet_id NUMBER(20) NOT NULL,
	procedure_id NUMBER(20) NOT NULL,
	CONSTRAINT pk_Procedure_to_pet_id PRIMARY KEY (id)
);

ALTER TABLE Pet ADD FOREIGN KEY (pet_type_id) REFERENCES pettype(id);

ALTER TABLE Pet ADD FOREIGN KEY (id_owner) REFERENCES User_table(id);

ALTER TABLE Schedule ADD FOREIGN KEY (pet_id) REFERENCES Pet(id);

ALTER TABLE Schedule ADD FOREIGN KEY (Master_id) REFERENCES Master(id);

ALTER TABLE Schedule ADD FOREIGN KEY (Procedure_id) REFERENCES Procedure_table(id);

ALTER TABLE Schedule ADD FOREIGN KEY (Status_id) REFERENCES Status(id);

ALTER TABLE Procedure_to_pet ADD FOREIGN KEY (Pet_id) REFERENCES Pet(id);

ALTER TABLE Procedure_to_pet ADD FOREIGN KEY (procedure_id) REFERENCES Procedure_table(id);

Alter table User_table add foreign key (id_role) references  Role_table(id);

Alter table Master_to_Procedure add foreign  key(procedure_id) references PROCEDURE_TABLE(id);

Alter table Master_to_Procedure add foreign key(master_id) references MASTER(id);

begin
    ADD_USER('new1','rece','jji','uujuju');
end;
call ADD_USER('new','rece','jji','jji');
call Authorization_user('jji','jji',);