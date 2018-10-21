/*DQL - Data Query Language - Linguagem de Consulta de dados.
* São os comandos de consulta.
*/

/* Exemplo de Seleção para Users-Leves-Persons*/
SELECT 
    us.user_login,
    us.user_password,
    lev.level_name,
    lev.level_power
FROM
    users us
        INNER JOIN
    levels lev ON us.id_level = lev.id_level;

/* Exemplo de Seleção para Users */
SELECT 
    id_user, id_level, user_login, user_password, user_active
FROM
    Users
WHERE
    user_login = 'webmaster'
        AND user_password = 'webmaster'
        AND user_active = 'S';
    
