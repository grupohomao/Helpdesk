/*DDL - Data Definition Language - Linguagem de Definição de Dados.
* São os comandos que interagem com os objetos do banco.
*/

/*Database*/
CREATE DATABASE helpdesk;
USE helpdesk;

/*Tabelas de Tipos(Catagorias):*/

/*Nivels - Níveis de usuário */
CREATE TABLE Niveis (
    id_nivel TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nivel_descricao VARCHAR(20) NOT NULL,
    nivel_forca TINYINT(10) NOT NULL,
    nivel_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE(nivel_descricao),
    PRIMARY KEY (id_nivel)
);

/*Endereco - Endereco das pessoas*/
CREATE TABLE Endereco (
    id_endereco INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    endereco_uf VARCHAR(5) NOT NULL,
    endereco_cidade VARCHAR(50) NOT NULL,
    endereco_logradouro VARCHAR(50) NOT NULL,
    endereco_cep VARCHAR(10) NOT NULL,
    endereco_numero VARCHAR(10) NOT NULL,
    endereco_complemento VARCHAR(100) NOT NULL,
    endereco_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_endereco)
);

/*Telefone - Telefone das pessoas*/
CREATE TABLE Telefone (
    id_telefone INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    telefone_tipo VARCHAR(20) NOT NULL,
    telefone_ddd VARCHAR(20) NOT NULL,
    telefone_numero VARCHAR(20) NOT NULL,
    telefone_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE(telefone_numero),
    PRIMARY KEY (id_telefone)
);

/*Email - Email das pessoas*/
CREATE TABLE Email (
    id_email INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    email_descricao VARCHAR(100) NOT NULL,
    email_tipo VARCHAR(50) NOT NULL,
    email_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE (email_descricao),
    PRIMARY KEY (id_email)
);

/*Pessoa - Dados de pessoa*/
CREATE TABLE Pessoa (
    id_pessoa	 INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    telefone_tipo VARCHAR(20) NOT NULL,
    telefone_ddd VARCHAR(20) NOT NULL,
    telefone_numero VARCHAR(20) NOT NULL,
    telefone_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE(telefone_numero),
    PRIMARY KEY (id_telefone)
);

/** Usuários */
CREATE TABLE Usuarios(
id_usuario INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_nivel INT UNSIGNED NOT NULL,
usuario_nome VARCHAR(100) NOT NULL,
usuario_senha VARCHAR(100) NOT NULL,
usuario_ativo ENUM('S','N') DEFAULT 'S',
usuario_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY (id_usuario),
UNIQUE(usuario_nome)
);

/** Ocorrências - Tipos de ocorrência */
CREATE TABLE Ocorrencias(
id_ocorrencia INT UNSIGNED NOT NULL AUTO_INCREMENT,
ocorrencia_descricao VARCHAR(20) NOT NULL,
ocorrencia_ativo ENUM('S','N') DEFAULT 'S',
ocorrencia_data_inclusao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
ocorrencia_data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY (id_ocorrencia),
UNIQUE(ocorrencia_descricao)
);

/** Cargos - Cargos de funcionário */
CREATE TABLE Cargos(
id_cargo INT UNSIGNED NOT NULL AUTO_INCREMENT,
cargo_descricao VARCHAR(100) NOT NULL,
cargo_ativo ENUM('S','N') DEFAULT 'S',
cargo_data_inclusao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
cargo_data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY (id_cargo),
UNIQUE(cargo_descricao)
);

/** Funcionários */
CREATE TABLE Funcionarios(
id_funcionario INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_usuario INT UNSIGNED NOT NULL,
id_cargo INT UNSIGNED NOT NULL,
funcionario_cpf VARCHAR(20) NOT NULL,
funcionario_rg VARCHAR(20) NOT NULL,
funcionario_nome VARCHAR(100) NOT NULL,
funcionario_email VARCHAR(100) DEFAULT NULL,
funcionario_telefone VARCHAR(50) DEFAULT NULL,
funcionario_celular VARCHAR(50) DEFAULT NULL,
funcionario_endereco VARCHAR(255) NOT NULL,
funcionario_ativo ENUM('S','N') DEFAULT 'S',
funcionario_data_inclusao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
funcionario_data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY (id_funcionario),
UNIQUE(funcionario_cpf, funcionario_rg, funcionario_email)
);

/** Chamados */
CREATE TABLE Equipamentos(
id_equipamento INT UNSIGNED NOT NULL AUTO_INCREMENT,
equipamento_descricao VARCHAR(255) NOT NULL,
equipamento_ativo ENUM('S','N') DEFAULT 'S',
equipamento_data_inclusao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
equipamento_data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY (id_equipamento),
UNIQUE(equipamento_descricao)
);

/** Chamados */
CREATE TABLE Chamados(
id_chamado INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_ocorrencia INT UNSIGNED NOT NULL,
id_equipamento INT UNSIGNED NOT NULL,
chamado_situacao ENUM('S','N') DEFAULT 'N',
chamado_descricao LONGTEXT NOT NULL,
chamado_data_inclusao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
chamado_data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY (id_chamado)
);

/** Chamados_Técnicos */
CREATE TABLE Chamados_Tecnicos(
id_chamado INT UNSIGNED NOT NULL,
id_funcionario INT UNSIGNED NOT NULL
);

/** Base_Conhecimento */
CREATE TABLE Base_Conhecimento(
id_base_conhecimento INT UNSIGNED NOT NULL AUTO_INCREMENT,
id_ocorrencia INT UNSIGNED NOT NULL,
base_conhecimento_descricao LONGTEXT NOT NULL,
base_conhecimento_causa LONGTEXT NOT NULL,
base_conhecimento_efeito LONGTEXT NOT NULL,
base_conhecimento_solucao LONGTEXT NOT NULL,
base_conhecimento_data_inclusao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
base_conhecimento_data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY (id_base_conhecimento)
);

/** Base_Conhecimento_Chamados */
CREATE TABLE Base_Conhecimento_Chamados(
id_base_conhecimento INT UNSIGNED NOT NULL,
id_chamado INT UNSIGNED NOT NULL,
base_conhecimento_chamados_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

/**Relacionamentos*/

/*Usuarios - Niveis*/
ALTER TABLE Usuarios
ADD CONSTRAINT fk_Usu_Niv_id_nivel FOREIGN KEY(id_nivel)
REFERENCES Niveis(id_nivel);

/*Funcionarios - Usuarios*/
ALTER TABLE Funcionarios
ADD CONSTRAINT fk_Fun_Usu_id_usuario FOREIGN KEY(id_usuario)
REFERENCES Usuarios(id_usuario);

/*Funcionarios - Cargos*/
ALTER TABLE Funcionarios
ADD CONSTRAINT fk_Fun_Car_id_cargo FOREIGN KEY(id_cargo)
REFERENCES Cargos(id_cargo);

/*Chamados - Equipamentos*/
ALTER TABLE Chamados
ADD CONSTRAINT fk_Cha_Equi_id_equipamento FOREIGN KEY(id_equipamento)
REFERENCES Equipamentos(id_equipamento);

/*Chamados - Ocorrencias*/
ALTER TABLE Chamados
ADD CONSTRAINT fk_Cha_Oco_id_ocorrencia FOREIGN KEY(id_ocorrencia)
REFERENCES Ocorrencias(id_ocorrencia);

/*Chamados_Tecnicos - Tecnicos*/
ALTER TABLE Chamados_Tecnicos
ADD CONSTRAINT fk_CT_Cha_id_chamado FOREIGN KEY(id_chamado)
REFERENCES Chamados(id_chamado);

/*Chamados_Tecnicos - Funcionarios*/
ALTER TABLE Chamados_Tecnicos
ADD CONSTRAINT fk_CT_Fun_id_funcionario FOREIGN KEY(id_funcionario)
REFERENCES Funcionarios(id_funcionario);

/*Base_Conhecimento - Ocorrencias*/
ALTER TABLE Base_Conhecimento
ADD CONSTRAINT fk_BC_Oco_id_ocorrencia FOREIGN KEY(id_ocorrencia)
REFERENCES Ocorrencias(id_ocorrencia);

/*Base_Conhecimento_Chamados - Chamados*/
ALTER TABLE Base_Conhecimento_Chamados
ADD CONSTRAINT fk_BCC_BC_id_base_conhecimento FOREIGN KEY(id_base_conhecimento)
REFERENCES Base_Conhecimento(id_base_conhecimento);

/*Base_Conhecimento_Chamados - Chamados*/
ALTER TABLE Base_Conhecimento_Chamados
ADD CONSTRAINT fk_BCC_Cha_id_chamado FOREIGN KEY(id_chamado)
REFERENCES Chamados(id_chamado);