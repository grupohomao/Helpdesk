package br.com.helpdesk.dal;

import br.com.helpdesk.model.Nivel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * NivelDAO [DAL] Classe responsável por realizar o constrole e gerenciamento
 * dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class NivelDAO extends Conexao {

    public NivelDAO(Nivel niv) {

    }

    /**
     * <b>listar</b>
     * Método responsável por listar os níveis cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public ArrayList<Nivel> lista() throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<Nivel> nivelLista = new ArrayList<>();

        //Obejeto Nivel que será adiocionado ao array.
        Nivel niv;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT * FROM Niveis ";
            sql += "WHERE 1=1 ";
            sql += "AND nivel_forca < 10 ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                niv = new Nivel(
                        rs.getInt("id_nivel"),
                        rs.getString("nivel_descricao"),
                        rs.getInt("nivel_forca")
                );

                nivelLista.add(niv);
            }

        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
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

        return nivelLista;
    }

    /**
     * <b>verificaNivel</b>
     * Método responsável por verificar se o nível já está cadastrado.
     *
     * @param Nivel (Object).
     * @return Boolean.
     */
    private Boolean verificaNivel(Nivel niv) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;
        int idRecuperado = 0;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT id_nivel ";
            sql += "FROM Niveis ";
            sql += "WHERE 1 = 1 ";
            sql += "AND nivel_descricao = ? ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, niv.getDescricao());

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            int linhasRetornadas = 0;

            while (rs.next()) {
                idRecuperado = rs.getInt("id_nivel");
                linhasRetornadas++;
            }

            //1º condição, verifica na inclusão.
            //2º condição, verifica na alteração.            
            if (linhasRetornadas > 0 && niv.getId() <= 0) {
                JOptionPane.showMessageDialog(null, "Já existe um nível com essa nomenclatura.");
                return true;
            } else if (linhasRetornadas > 0 && niv.getId() != idRecuperado) {
                JOptionPane.showMessageDialog(null, "Já existe um nível com essa nomenclatura.");
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
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
     * Método responsável por incluir os dados na tabela de níveis.
     *
     * @param niv (Object).
     * @throws java.sql.SQLException
     * @return Boolean
     */
    public Boolean inclui(Nivel niv) throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o status.
        boolean status = this.verificaNivel(niv);

        if (status) {
            return false;
        }

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "INSERT INTO Niveis ";
            sql += "(nivel_descricao, nivel_forca) ";
            sql += "VALUES (?,?)";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, niv.getDescricao());
            pst.setInt(2, niv.getForca());

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Tipo de nível adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu uma falha na inclusão, contate o suporte!");
            }
        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
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
     * <b>altera</b>
     * Método responsável por alterar os dados na tabela de níveis conforme
     * nível passado.
     *
     * @param niv (Object).
     * @return Boolean.
     * @throws java.sql.SQLException
     */
    public Boolean altera(Nivel niv) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o status.
        boolean status = this.verificaNivel(niv);

        if (status) {
            return false;
        }

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "UPDATE Niveis SET nivel_descricao = ?, ";
            sql += "nivel_forca = ? ";
            sql += "WHERE id_nivel = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, niv.getDescricao());
            pst.setInt(2, niv.getForca());
            pst.setInt(3, niv.getId());

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Tipo de nível alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu uma falha na atualização, contate o suporte!");
            }
        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
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
     * <b>remove</b>
     * Método responsável por remover os dados na tabela de níveis conforme
     * nível passado.
     *
     * @param niv (Object).
     * @return Boolean.
     * @throws java.sql.SQLException
     */
    public Boolean remove(Nivel niv) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "DELETE FROM Niveis ";
            sql += "WHERE id_nivel = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setInt(1, niv.getId());

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Tipo de nível removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu uma falha na exclusão, contate o suporte!");
            }
        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
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
}
