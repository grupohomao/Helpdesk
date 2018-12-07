package br.com.helpdesk.dal;

import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.usuario.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * PessoaFisicaDAO [DAL] Classe responsável por realizar o constrole e
 * gerenciamento dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class PessoaFisicaDAO extends Conexao {

    public PessoaFisicaDAO(PessoaFisica pf) {
    }

    /**
     * <b>verificaPessoaFisica</b>
     * Método responsável por verificar se a pessoa já está cadastrada.
     *
     * @param pf (Object).
     * @return Boolean.
     */
    public Boolean verificaPessoaFisica(PessoaFisica pf) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;
        String cpf = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT id_pessoa ";
            sql += "FROM Pessoas ";
            sql += "WHERE 1 = 1 ";
            sql += "AND pessoa_fj = ? ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, pf.getCpf());

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            int linhasRetornadas = 0;

            while (rs.next()) {
                cpf = rs.getString("pessoa_fj");
                linhasRetornadas++;
            }

            //1º condição, verifica na inclusão.
            //2º condição, verifica na alteração.            
            if (linhasRetornadas > 0) {
                JOptionPane.showMessageDialog(null, "Já existe uma pessoa física cadastrada.");
                return true;
            } else if (linhasRetornadas > 0 && pf.getCpf().equals(cpf)) {
                JOptionPane.showMessageDialog(null, "Já existe uma pessoa física cadastrada.");
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                //JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
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

        return true;
    }

    /**
     * <b>inclui</b>
     * Método responsável por incluir os dados da Pessoa Física na tabela de
     * Pessoas.
     *
     * @param pf (Object) PessoaFisica.
     * @param usu (Object) Usuario.
     * @throws java.sql.SQLException
     * @return int
     */
    public int inclui(PessoaFisica pf, Usuario usu) throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o status.
        boolean status = this.verificaPessoaFisica(pf);

        //Inicializa o retorno do id da pessoa.
        int idPessoa = 0;

        if (status) {
            JOptionPane.showMessageDialog(null, "Já existe uma pessoa física cadastrada.");
            return 0;
        } else {

            try {
                //Inicia a transação.
                this.getConexao().setAutoCommit(false);

                //Query.
                String sql = "";
                sql += "INSERT INTO Pessoas ";
                sql += "(id_usuario, pessoa_fj, pessoa_nome, pessoa_ramo) ";
                sql += "VALUES (?,?,?,?)";

                //Prepara a instrução SQL
                pst = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                //Informa o tipo de dado e o dado a ser registrado no BD.
                pst.setInt(1, usu.getId());
                pst.setString(2, pf.getCpf());
                pst.setString(3, pf.getNome());
                pst.setString(4, pf.getProfissao());

                //Recupera o número de linhas afetadas.
                int retorno = pst.executeUpdate();

                // recupera chave do objeto
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    idPessoa = rs.getInt(1);
                }

                //Registra os dados no BD.
                if (retorno > 0) {
                    this.getConexao().commit();
                    //JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu uma falha na inclusão da pessoa física, contate o suporte!");
                }
            } catch (SQLException ex) {
                //Caso aconteça uma exceção, é efetuado o roolback.
                if (this.getConexao() != null) {
                    //JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
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
        }
        return idPessoa;
    }

}
