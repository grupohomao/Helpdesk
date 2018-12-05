/*DML - Data Manipulation Language - Linguagem de Manipulação de Dados.
* São os comandos que interagem com os dados dentro das tabelas.
*/

/*Tipos*/
INSERT INTO Niveis (nivel_descricao, nivel_forca) 
VALUES
('Selecione', 0),
('Developer', 10), 
('Administrator - DBA', 9),
('Suporte - Analista', 8), 
('Suporte - Técnico', 7),
('Suporte - Estagiário', 6),
('Gerente', 5),
('Auxiliar', 4),
('Cliente', 1);
truncate Niveis;

INSERT INTO Mail_Type (mail_type_type) 
VALUES('Pessoal'), ('Comercial'), ('Default');
SELECT * FROM Mail_Type;

INSERT INTO Phone_Type (phone_type_type) 
VALUES('Celular'), ('Fixo'), ('Default');
