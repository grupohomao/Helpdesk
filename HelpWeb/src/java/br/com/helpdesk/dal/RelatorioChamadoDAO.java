package br.com.helpdesk.dal;

import br.com.helpdesk.model.usuario.RelatorioChamado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * RelatorioChamadoDAO [DAL] Classe responsável por realizar o constrole e
 * gerenciamento dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class RelatorioChamadoDAO extends Conexao {

    /**
     * <b>recuperaFuncionarioChamado</b>
     * Método responsável por recuperar o funcionário do chamado.
     *
     * @param idFuncionario
     * @throws java.sql.SQLException
     * @return String
     */
    public String recuperaFuncionarioChamado(int idFuncionario) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        String funcionarioDescricao = null;
        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT ";
            sql += "fun.id_funcionario, pes.id_pessoa, pes.pessoa_nome ";
            sql += "FROM ";
            sql += "Pessoas pes ";
            sql += "INNER JOIN ";
            sql += "Funcionarios fun ON pes.id_pessoa = fun.id_pessoa ";
            sql += "WHERE 1=1 ";
            sql += "AND fun.id_funcionario = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado a ser interpretado.
            pst.setInt(1, idFuncionario);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                funcionarioDescricao = rs.getString("pessoa_nome");
            }

        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                System.out.println("#Rollback efetuado na transação. \n" + ex);
                this.getConexao().rollback();
            }
        } finally {
            //Fecha a conexão.
            if (pst != null) {
                pst.close();
            }
            //Finaliza as transações.
            this.getConexao().setAutoCommit(true);
        }

        return funcionarioDescricao;
    }

    /**
     * <b>lista</b>
     * Método responsável por listar os usuários cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public ArrayList<RelatorioChamado> lista() throws SQLException {
 
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<RelatorioChamado> relChaLista = new ArrayList<>();

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT ";
            sql += "cha.id_chamado, ";
            sql += "pes.pessoa_nome AS cliente, ";
            sql += "cha.id_funcionario, ";
            sql += "cha.chamado_descricao, ";
            sql += "cha.chamado_situacao, ";
            sql += "cha.chamado_data ";
            sql += "FROM ";
            sql += "Pessoas pes ";
            sql += "INNER JOIN ";
            sql += "Clientes cli ON pes.id_pessoa = cli.id_pessoa ";
            sql += "INNER JOIN ";
            sql += "Chamados cha ON cli.id_cliente = cha.id_cliente ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String func = this.recuperaFuncionarioChamado(rs.getInt("id_funcionario"));

                RelatorioChamado relCha = new RelatorioChamado(
                        rs.getInt("id_chamado"),
                        rs.getString("cliente"),
                        func,
                        rs.getString("chamado_descricao"),
                        rs.getString("chamado_situacao"),
                        rs.getString("chamado_data")
                );

                relChaLista.add(relCha);
            }

        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                System.out.println("#Rollback efetuado na transação. \n" + ex);
                this.getConexao().rollback();
            }
        } finally {
            //Fecha a conexão.
            if (pst != null) {
                pst.close();
            }
            //Finaliza as transações.
            this.getConexao().setAutoCommit(true);
        }

        return relChaLista;
    }

}
