/*DQL - Data Query Language - Linguagem de Consulta de dados.
* São os comandos de consulta.
*/

use helpdesk;
 
select * from usuarios; 
select * from clientes; 
select * from pessoas;

select * from funcionarios; 

select * from chamados; 

select * from niveis; 
select * from cargos; 

SELECT 
    cli.id_cliente, pes.id_pessoa, pes.pessoa_nome
FROM
    Pessoas pes
        INNER JOIN
    Clientes cli ON pes.id_pessoa = cli.id_pessoa;
    
SELECT 
    fun.id_funcionario, fun.id_cargo, pes.id_pessoa, pes.pessoa_nome
FROM
    Pessoas pes
        INNER JOIN
    Funcionarios fun ON pes.id_pessoa = fun.id_pessoa;


SELECT 
    cha.id_chamado,
    pes.pessoa_nome AS cliente,
    cha.id_funcionario,
    cha.chamado_descricao,
    cha.chamado_situacao,
    cha.chamado_data
FROM
    Pessoas pes
        INNER JOIN
    Clientes cli ON pes.id_pessoa = cli.id_pessoa
        INNER JOIN
    Chamados cha ON cli.id_cliente = cha.id_cliente;
