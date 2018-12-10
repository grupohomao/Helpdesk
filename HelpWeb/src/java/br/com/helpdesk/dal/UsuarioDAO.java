package br.com.helpdesk.dal;

import br.com.helpdesk.model.Nivel;
import br.com.helpdesk.model.Usuario;
import br.com.helpdesk.session.Sessao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NivelDAO [DAL] Classe responsável por realizar o constrole e gerenciamento
 * dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class UsuarioDAO extends Conexao {

    /**
     * <b>login</b>
     * Método responsável por listar os níveis cadastrados.
     *
     * @param usu (Object).
     * @return Boolean.
     * @throws java.sql.SQLException
     */
    public boolean login(Usuario usu) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;
        boolean retorno = false;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT id_usuario ";
            sql += "FROM Usuarios ";
            sql += "WHERE 1 = 1 ";
            sql += "AND usuario_descricao = ? ";
            sql += "AND usuario_senha = ? ";
            sql += "AND usuario_ativo = 'S' ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado a ser interpretado.
            pst.setString(1, usu.getUsuarioDescricao());
            pst.setString(2, usu.getUsuarioSenha());

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            int linhasRetornadas = 0;
            int idUsuario = 0;

            while (rs.next()) {
                idUsuario = rs.getInt("id_usuario");
                linhasRetornadas++;
            }
            System.out.println(usu.getId());
            if (linhasRetornadas > 0 && usu.getId() <= 0) {
                this.listaUsuarioSessao(idUsuario);
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Fecha a conexão.
            if (pst != null) {
                pst.close();
            }
            //Finaliza as transações.
            this.getConexao().setAutoCommit(true);
        }

        return retorno;
    }

    /**
     * <b>listaUsuarioSessao</b>
     * Método responsável por listar o usuário para a sessão.
     *
     * @param usuParam (Object). Utlizado para armazenar o login e senha do
     * usuário. (Obrigatório).
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public void listaUsuarioSessao(int idUsuario) throws SQLException {

        Sessao.getSessao().setStatusLogin(true);

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Obejeto Nivel que será adiocionado ao array.
        Usuario usu = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT usu.id_usuario, usu.id_nivel, usu.usuario_descricao, usu.usuario_senha, ";
            sql += "niv.nivel_descricao, niv.nivel_forca ";
            sql += "FROM Usuarios usu INNER JOIN Niveis niv ";
            sql += "ON usu.id_nivel = niv.id_nivel ";
            sql += "WHERE 1=1 ";
            sql += "AND usu.id_usuario = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado a ser interpretado.
            pst.setInt(1, idUsuario);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Nivel niv = new Nivel(rs.getInt("id_nivel"), rs.getString("nivel_descricao"), rs.getInt("nivel_forca"));
                usu = new Usuario(
                        rs.getInt("id_usuario"),
                        niv,
                        rs.getString("usuario_descricao")
                );
            }

            //Inicializa a sessão.
            Sessao.getSessao().setUsuarioSessao(usu);

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

    }

    /**
     * <b>lista</b>
     * Método responsável por listar os usuários cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public ArrayList<Usuario> lista() throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<Usuario> usuLista = new ArrayList<>();

        //Obejeto Nivel que será adiocionado ao array.
        Usuario usu;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT usu.id_usuario, usu.id_nivel, usu.usuario_descricao, usu.usuario_senha, ";
            sql += "niv.nivel_descricao, niv.nivel_forca ";
            sql += "FROM Usuarios usu INNER JOIN Niveis niv ";
            sql += "ON usu.id_nivel = niv.id_nivel ";
            sql += "WHERE 1=1 ";
            sql += "AND usu.usuario_ativo = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado a ser interpretado.
            String usuarioAtivo = "S";
            pst.setString(1, usuarioAtivo);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            Nivel niv;

            while (rs.next()) {
                usu = new Usuario(
                        rs.getInt("id_usuario"),
                        niv = new Nivel(rs.getInt("id_nivel"), rs.getString("nivel_descricao"), rs.getInt("nivel_forca")),
                        rs.getString("usuario_descricao"),
                        rs.getString("usuario_senha")
                );

                usuLista.add(usu);
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

        return usuLista;
    }

}
