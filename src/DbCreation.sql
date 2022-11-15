
CREATE  TABLE Master (
	Id_master NUMBER(20) NOT NULL,
	id_procedure NUMBER(20) NOT NULL,
	Name_master VARCHAR(40) NOT NULL,
	CONSTRAINT pk_Master_Id_master PRIMARY KEY (Id_master)
);
drop table Master;
CREATE TABLE Procedure (
	Id_procedure NUMBER(20) NOT NULL,
	Name_procedure VARCHAR(20) NOT NULL,
	Price NUMBER(10,2) NOT NULL,
	Photo NVARCHAR2(100) NOT NULL,
	CONSTRAINT pk_Procedure_id PRIMARY KEY (Id_procedure)
);
drop table Procedure;

CREATE  TABLE User_table (
	Id_user NUMBER(20) NOT NULL,
	Full_name NVARCHAR2(30) NOT NULL,
	email NVARCHAR2(30) NOT NULL,
	login NVARCHAR2(30) NOT NULL,
	password NVARCHAR2(30) NOT NULL,
	Id_role NUMBER(20) NOT NULL,
	CONSTRAINT pk_User_id PRIMARY KEY (Id_user)
);
drop table User_table;

CREATE TABLE Role_table(
Id_role NUMBER(20) NOT NULL,
Role_name NVARCHAR2(30) NOT NULL,
	CONSTRAINT pk_Role_id PRIMARY KEY (Id_role )
);
show user;

SELECT owner, table_name
  FROM all_tables where owner='C##CURSACH_USER';

CREATE  TABLE Pet (
	Id_pet NUMBER(20) NOT NULL,
	Pet_id NVARCHAR2(20) NOT NULL,
	Age NUMBER(20) NOT NULL,
	Id_owner NUMBER(20) NOT NULL,
	CONSTRAINT pk_Pet_id PRIMARY KEY (Id_pet)
);
drop table Pet;

--Расписание сделать
CREATE  TABLE Schdule (
	Record_id NUMBER(20) NOT NULL,
	Pet_id  NUMBER(20) NOT NULL,
	Master_id  NUMBER(20) NOT NULL,
	Procedure_id  NUMBER(20) NOT NULL,
	Owner_id  NUMBER(20) NOT NULL,
	Date DATE NOT NULL,
	Time TIME NOT NULL,
	Status_id  NUMBER(20) NOT NULL
	CONSTRAINT pk_Schdule_Record_id PRIMARY KEY (Record_id)
);

CREATE  TABLE Status (
	Status_id NUMBER(20) NOT NULL,
	status_name NVARCHAR2(20) NOT NULL,
	CONSTRAINT pk_Status_id PRIMARY KEY (Status_id)
);


create user C##cursach_user identified by 555;

grant all privileges to C##cursach_user;

CREATE  TABLE Pet_id (
	Pet_id NUMBER(20) NOT NULL,
	pet_name NVARCHAR2(20) NOT NULL,
	CONSTRAINT pk_Pet_id_id PRIMARY KEY (Pet_id)
);

SELECT owner, table_name
  FROM dba_tables where table_name='Pet';

CREATE  TABLE Procedure_to_pet (
	Procedure_to_pet_id NUMBER(20) NOT NULL,
	pet_id NUMBER(20) NOT NULL,
	procedure_id NUMBER(20) NOT NULL,
	CONSTRAINT pk_Procedure_to_pet_id PRIMARY KEY (Procedure_to_pet_id)
);

ALTER TABLE Master ADD FOREIGN KEY (id_procedure) REFERENCES Procedure(Id_procedure);

ALTER TABLE Pet ADD FOREIGN KEY (Id_pet) REFERENCES Pet_id(Pet_id);

ALTER TABLE Pet ADD FOREIGN KEY (Id_owner) REFERENCES User_table(Id_user);

ALTER TABLE Schdule ADD FOREIGN KEY (Pet_id) REFERENCES Pet(Id_pet);

ALTER TABLE Schdule ADD FOREIGN KEY (Master_id) REFERENCES Master(Id_master);

ALTER TABLE Schdule ADD FOREIGN KEY (Procedure_id) REFERENCES Procedure(id);

ALTER TABLE Schdule ADD FOREIGN KEY (Status_id) REFERENCES Status(id);

ALTER TABLE Procedure_to_pet ADD FOREIGN KEY (Pet_id) REFERENCES Pet_id(Pet_id);

ALTER TABLE Procedure_to_pet ADD FOREIGN KEY (procedure_id) REFERENCES Procedure(Id_procedure);
