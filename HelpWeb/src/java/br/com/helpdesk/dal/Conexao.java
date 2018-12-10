package br.com.helpdesk.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexao[DAL] Classe responsável por realizar o constrole e gerenciamento da
 * conexão com o BD.
 *
 *
 * @author Ricardo.Guntzell
 */
public abstract class Conexao {

    protected static Connection conexao = null;

    private final String servidor = "localhost";
    private final String porta = "3306";
    private final String sgbd = "mysql";
    private final String database = "helpdesk";
    private final String tipoConexao = "jdbc";
    private final String usuario = "root";
    private final String senha = "";

    /** <b>Construtor</b>
     */
    public Conexao() {
        this.conecta();
    }

    /**
     * <b>conecta</b>
     * Método responsável por realizar a conexão no BD.
     */
    private void conecta() {
        try {

            String url = this.tipoConexao + ":";
            url += this.sgbd + "://";
            url += this.servidor + ":";
            url += this.porta + "/";
            url += this.database;

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            this.conexao = DriverManager.getConnection(url, this.usuario, this.senha);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConexao() {
        return conexao;
    }
}
