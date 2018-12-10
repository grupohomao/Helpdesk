/*DML - Data Manipulation Language - Linguagem de Manipulação de Dados.
* São os comandos que interagem com os dados dentro das tabelas.
*/

/*Niveis - Obrigatório*/
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

/*Usuarios - Obrigatório*/
INSERT INTO Usuarios (usuario_descricao, usuario_senha, id_nivel) 
VALUES
('root', 'root', 3);

/*Ocorrências - Obrigatório*/
INSERT INTO Ocorrencias (ocorrencia_descricao) 
VALUES
('Suporte'), 
('Manutenção'),
('Visita Técnica'), 
('Devolução');

/*Equipamentos - Equipamentos*/
INSERT INTO Equipamentos (equipamento_descricao) 
VALUES
('Computador'), 
('Placa de rede'), 
('Placa de som'), 
('Placa de video'), 
('roteador'),
('Teclado'),
('Mouse'), 
('Caixa de som');

/*Cargos - Obrigatório*/
INSERT INTO Cargos (cargo_descricao) 
VALUES
('Desenvolvedor'), 
('Analista'),
('Técnico'), 
('Gerente'), 
('Auxiliar'),
('Supervisor');

