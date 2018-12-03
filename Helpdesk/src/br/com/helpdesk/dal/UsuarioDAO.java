package br.com.helpdesk.dal;

import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.usuario.Usuario;
import br.com.helpdesk.session.Sessao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * NivelDAO [DAL] Classe responsável por realizar o constrole e gerenciamento
 * dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class UsuarioDAO extends Conexao {

    public UsuarioDAO(Usuario usu) {
    }

    /**
     * <b>login</b>
     * Método responsável por listar os níveis cadastrados.
     *
     * @param usu (Object).
     * @return Boolean.
     * @throws java.sql.SQLException
     */
    private Usuario login(Usuario usu) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;
        int idRecuperado = 0;

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

            while (rs.next()) {
                idRecuperado = rs.getInt("id_usuario");
                linhasRetornadas++;
            }

            //1º condição, verifica na inclusão.
            if (linhasRetornadas > 0 && usu.getId() <= 0) {
                JOptionPane.showMessageDialog(null, "Bem vindo... " + idRecuperado + "!");
                return usu;
            } else {
                JOptionPane.showMessageDialog(null, "Opss... Não foi encontrado nenhum usuário com esses dados!");
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

        return null;
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
    public ArrayList<Usuario> listaUsuarioSessao(Usuario usuParam) throws SQLException {

        if (this.login(usuParam) != null) {
            Sessao.getSessao().setStatusLogin(true);
        }

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
            String sql = "SELECT id_usuario, ";
            sql += "usuario_descricao FROM Usuarios ";
            sql += "WHERE 1 = 1 ";
            sql += "AND id_usuario = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado a ser interpretado.
            pst.setInt(1, usuParam.getId());

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                usu = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("usuario_descricao")
                );

                usuLista.add(usu);
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

        return usuLista;
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
            String sql = "SELECT * FROM Usuarios ";
            sql += "WHERE 1=1 ";
            sql += "AND usuario_ativo = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado a ser interpretado.
            String usuarioAtivo = "S";
            pst.setString(1, usuarioAtivo);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                usu = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("usuario_descricao")
                );

                usuLista.add(usu);
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

        return usuLista;
    }

    /**
     * <b>verificaUsuarioExiste</b>
     * Método responsável por listar os níveis cadastrados.
     *
     * @param Usuario (Object).
     * @return Boolean.
     */
    private Boolean verificaUsuarioExiste(Usuario usu) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;
        int idRecuperado = 0;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT id_usuario ";
            sql += "FROM Usuarios ";
            sql += "WHERE 1 = 1 ";
            sql += "AND usuario_descricao = ? ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, usu.getUsuarioDescricao());

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            int linhasRetornadas = 0;

            while (rs.next()) {
                idRecuperado = rs.getInt("id_usuario");
                linhasRetornadas++;
            }

            //1º condição, verifica na inclusão.
            //2º condição, verifica na alteração.            
            if (linhasRetornadas > 0 && usu.getId() <= 0) {
                JOptionPane.showMessageDialog(null, "Já existe um usuário com esse login.");
                return true;
            } else if (linhasRetornadas > 0 && usu.getId() != idRecuperado) {
                JOptionPane.showMessageDialog(null, "Já existe um usuário com esse login.");
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
     * @param usu (Object) Usuario.
     * @param cli (Object) Cliente.
     * @param fun (Object) Funcionario.
     * @throws java.sql.SQLException
     * @return boolean
     */
    public boolean inclui(Usuario usu, Cliente cli, Funcionario fun) throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o retorno do id do usuario.
        int idUsuario = 0;

        //Inicializa o status.
        boolean status = this.verificaUsuarioExiste(usu);

        if (status) {
            return false;
        }
        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "INSERT INTO Usuarios ";
            sql += "(id_nivel, usuario_descricao, usuario_senha) ";
            sql += "VALUES (?, ?, ?)";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setInt(1, usu.getNivel().getId());
            pst.setString(2, usu.getUsuarioDescricao());
            pst.setString(3, usu.getUsuarioSenha());

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            // recupera chave do objeto
            ResultSet rs = pst.getGeneratedKeys();
            while (rs.next()) {
                idUsuario = rs.getInt(1);
            }

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");

                ClienteDAO cliDAO = new ClienteDAO(cli);
                Usuario usuCli = new Usuario(idUsuario);
                cliDAO.inclui(cli, usuCli);

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
     * @param usu (Object).
     * @return Boolean.
     * @throws java.sql.SQLException
     */
    public Boolean altera(Usuario usu) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o status.
        boolean status = this.verificaUsuarioExiste(usu);

        if (status) {
            return false;
        }

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "UPDATE Usuarios SET usuario_descricao = ?, ";
            sql += "id_nivel = ? ,";
            sql += "usuario_senha = ? ";
            sql += "WHERE id_usuario = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, usu.getUsuarioDescricao());
            pst.setInt(2, usu.getNivel().getId());
            pst.setString(3, usu.getUsuarioSenha());
            pst.setInt(4, usu.getId());

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Opss, ocorreu uma falha na atualização, contate o suporte!");
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
