package br.com.helpdesk.dal;

import br.com.helpdesk.model.pessoa.PessoaJuridica;
import br.com.helpdesk.model.usuario.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * PessoaJuridicaDAO [DAL] Classe responsável por realizar o constrole e
 * gerenciamento dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class PessoaJuridicaDAO extends Conexao {

    public PessoaJuridicaDAO(PessoaJuridica pj) {
    }

    /**
     * <b>verificaPessoaFisica</b>
     * Método responsável por verificar se a pessoa já está cadastrada.
     *
     * @param pj (Object).
     * @return Boolean.
     */
    public Boolean verificaPessoaJuridica(PessoaJuridica pj) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;
        String cnpj = null;

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
            pst.setString(1, pj.getCnpj());

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            int linhasRetornadas = 0;

            while (rs.next()) {
                cnpj = rs.getString("pessoa_fj");
                linhasRetornadas++;
            }

            //1º condição, verifica na inclusão.
            //2º condição, verifica na alteração.            
            if (linhasRetornadas > 0) {
                JOptionPane.showMessageDialog(null, "Já existe uma pessoa jurídica cadastrada.");
                return true;
            } else if (linhasRetornadas > 0 && pj.getCnpj().equals(cnpj)) {
                JOptionPane.showMessageDialog(null, "Já existe uma pessoa jurídica cadastrada.");
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
     * @param pj (Object) PessoaJuridica.
     * @param usu (Object) Usuario.
     * @throws java.sql.SQLException
     * @return int
     */
    public int inclui(PessoaJuridica pj, Usuario usu) throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o status.
        boolean status = this.verificaPessoaJuridica(pj);

        //Inicializa o retorno do id da pessoa.
        int idPessoa = 0;

        if (status) {
            JOptionPane.showMessageDialog(null, "Já existe uma pessoa jurídica cadastrada.");
            return 0;
        } else {

            try {
                //Inicia a transação.
                this.getConexao().setAutoCommit(false);

                //Query.
                String sql = "";
                sql += "INSERT INTO Pessoas ";
                sql += "(id_usuario, pessoa_fj, pessoa_nome, pessoa_ramo, pessoa_ie) ";
                sql += "VALUES (?,?,?,?,?)";

                //Prepara a instrução SQL
                pst = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                //Informa o tipo de dado e o dado a ser registrado no BD.
                pst.setInt(1, usu.getId());
                pst.setString(2, pj.getCnpj());
                pst.setString(3, pj.getNome());
                pst.setString(4, pj.getRamo());
                pst.setString(5, pj.getIe());

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
                    JOptionPane.showMessageDialog(null, "Ocorreu uma falha na inclusão da pessoa jurídica, contate o suporte!");
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
