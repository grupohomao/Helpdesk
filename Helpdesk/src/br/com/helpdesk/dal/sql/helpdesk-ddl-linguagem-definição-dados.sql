/*DDL - Data Definition Language - Linguagem de Definição de Dados.
* São os comandos que interagem com os objetos do banco.
*/

/*Database*/
CREATE DATABASE helpdesk;
USE helpdesk;

/*Tabelas de Tipos(Catagorias):*/

CREATE TABLE Niveis (
    id_nivel INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nivel_descricao VARCHAR(20) NOT NULL,
    nivel_forca TINYINT(10) NOT NULL,
    nivel_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE (nivel_descricao),
    PRIMARY KEY (id_nivel)
);

/*Enderecos - Endereco das pessoas*/
CREATE TABLE Enderecos (
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

/*Telefones - Telefone das pessoas*/
CREATE TABLE Telefones (
    id_telefone INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    telefone_tipo VARCHAR(20) NOT NULL,
    telefone_ddd VARCHAR(20) NOT NULL,
    telefone_numero VARCHAR(20) NOT NULL,
    telefone_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE (telefone_numero),
    PRIMARY KEY (id_telefone)
);

/*Emails - Email das pessoas*/
CREATE TABLE Emails (
    id_email INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    email_descricao VARCHAR(100) NOT NULL,
    email_tipo VARCHAR(50) NOT NULL,
    email_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE (email_descricao),
    PRIMARY KEY (id_email)
);

/** Usuários */
CREATE TABLE Usuarios (
    id_usuario INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_nivel INT UNSIGNED NOT NULL,
    usuario_descricao VARCHAR(50) NOT NULL,
    usuario_senha VARCHAR(50) NOT NULL,
    usuario_ativo ENUM('S', 'N') DEFAULT 'S',
    usuario_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_usuario),
    UNIQUE (usuario_descricao)
);


/*Pessoa - Dados de pessoa*/
CREATE TABLE Pessoas (
    id_pessoa INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_usuario INT UNSIGNED NOT NULL,
    pessoa_fj VARCHAR(20) NOT NULL,
    pessoa_nome VARCHAR(50) NOT NULL,
    pessoa_ramo VARCHAR(40) DEFAULT NULL,
    pessoa_ie VARCHAR(20) DEFAULT NULL,
    pessoa_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    UNIQUE (pessoa_fj),
    PRIMARY KEY (id_pessoa)
);

/** Funcionários */
CREATE TABLE Funcionarios (
    id_funcionario INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    id_cargo INT UNSIGNED NOT NULL,
    funcionario_ativo ENUM('S', 'N') DEFAULT 'S',
    funcionario_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_funcionario)
);

/** Clientes */
CREATE TABLE Clientes (
    id_cliente INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pessoa INT UNSIGNED NOT NULL,
    cliente_ativo ENUM('S', 'N') DEFAULT 'S',
    cliente_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_cliente)
);

/** Ocorrências - Tipos de ocorrência */
CREATE TABLE Ocorrencias (
    id_ocorrencia INT UNSIGNED NOT NULL AUTO_INCREMENT,
    ocorrencia_descricao VARCHAR(20) NOT NULL,
    ocorrencia_ativo ENUM('S', 'N') DEFAULT 'S',
    ocorrencia_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_ocorrencia),
    UNIQUE (ocorrencia_descricao)
);

/** Cargos - Cargos de funcionário */
CREATE TABLE Cargos (
    id_cargo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    cargo_descricao VARCHAR(100) NOT NULL,
    cargo_ativo ENUM('S', 'N') DEFAULT 'S',
    cargo_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_cargo),
    UNIQUE (cargo_descricao)
);

/** Chamados */
CREATE TABLE Equipamentos (
    id_equipamento INT UNSIGNED NOT NULL AUTO_INCREMENT,
    equipamento_descricao VARCHAR(255) NOT NULL,
    equipamento_ativo ENUM('S', 'N') DEFAULT 'S',
    equipamento_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_equipamento),
    UNIQUE (equipamento_descricao)
);

/** Chamados */
CREATE TABLE Chamados (
    id_chamado INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_ocorrencia INT UNSIGNED NOT NULL,
    id_equipamento INT UNSIGNED NOT NULL,
    id_cliente INT UNSIGNED NOT NULL,
    id_funcionario INT UNSIGNED NOT NULL,
    chamado_situacao ENUM('S', 'N') DEFAULT 'S',
    chamado_descricao LONGTEXT NOT NULL,
    chamado_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (id_chamado)
);

/**Relacionamentos*/

/*Usuarios - Niveis*/
ALTER TABLE Usuarios
ADD CONSTRAINT fk_Usu_Niv_id_nivel FOREIGN KEY(id_nivel)
REFERENCES Niveis(id_nivel);

/*Pessoas - Usuários*/
ALTER TABLE Pessoas
ADD CONSTRAINT fk_usu_pes FOREIGN KEY(id_usuario)
REFERENCES Usuarios(id_usuario);

/*Pessoas - Endereços*/
ALTER TABLE Enderecos
ADD CONSTRAINT fk_end_pes FOREIGN KEY(id_pessoa)
REFERENCES Pessoas(id_pessoa);

/*Pessoas - Telefone*/
ALTER TABLE Telefones
ADD CONSTRAINT fk_tel_pes FOREIGN KEY(id_pessoa)
REFERENCES Pessoas(id_pessoa);

/*Pessoas - Emails*/
ALTER TABLE Emails
ADD CONSTRAINT fk_ema_pes FOREIGN KEY(id_pessoa)
REFERENCES Pessoas(id_pessoa);

/*Funcionarios - Pessoas*/
ALTER TABLE Funcionarios
ADD CONSTRAINT fk_fun_pes FOREIGN KEY(id_pessoa)
REFERENCES Pessoas(id_pessoa);

/*Clientes - Pessoas*/
ALTER TABLE Clientes
ADD CONSTRAINT fk_cli_pes FOREIGN KEY(id_pessoa)
REFERENCES Pessoas(id_pessoa);

/*Chamados - Equipamentos*/
ALTER TABLE Chamados
ADD CONSTRAINT fk_Cha_Equi_id_equipamento FOREIGN KEY(id_equipamento)
REFERENCES Equipamentos(id_equipamento);

/*Chamados - Ocorrencias*/
ALTER TABLE Chamados
ADD CONSTRAINT fk_oco_cha FOREIGN KEY(id_ocorrencia)
REFERENCES Ocorrencias(id_ocorrencia);
