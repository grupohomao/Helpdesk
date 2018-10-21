/*DML - Data Manipulation Language - Linguagem de Manipulação de Dados.
* São os comandos que interagem com os dados dentro das tabelas.
*/

/*Tipos*/
INSERT INTO Levels (level_name, level_power) 
VALUES('Developer', 10), ('Administrator', 9);
INSERT INTO Levels (level_name, level_power) 
VALUES('Editor', 8), ('Operator', 7);

INSERT INTO Mail_Type (mail_type_type) 
VALUES('Pessoal'), ('Comercial'), ('Default');
SELECT * FROM Mail_Type;

INSERT INTO Phone_Type (phone_type_type) 
VALUES('Celular'), ('Fixo'), ('Default');
