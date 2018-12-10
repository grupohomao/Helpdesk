package br.com.helpdesk.dal;

import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.usuario.Nivel;
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
        Usuario usuRet = null;

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
                linhasRetornadas++;
                usuRet = new Usuario(rs.getInt("id_usuario"));
            }

            //1º condição, verifica na inclusão.
            if (linhasRetornadas > 0 && usu.getId() <= 0) {
                Sessao.getSessao().setStatusLogin(true);
                JOptionPane.showMessageDialog(null, "Bem vindo... " + usu.getUsuarioDescricao() + "!");
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

        return usuRet;
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
    public Usuario listaUsuarioSessao(Usuario usuParam) throws SQLException {

        Usuario usu = this.login(usuParam);

        if (Sessao.getSessao().getStatusLogin()) {

            //Inicializa a instrução preparada.
            PreparedStatement pst = null;

            try {
                //Inicia a transação.
                this.getConexao().setAutoCommit(false);

                //Query.
                String sql = "SELECT usu.id_usuario, usu.id_nivel, usu.usuario_descricao, usu.usuario_senha, ";
                sql += "niv.nivel_descricao, niv.nivel_forca ";
                sql += "FROM Usuarios usu INNER JOIN Niveis niv ";
                sql += "ON usu.id_nivel = niv.id_nivel ";
                sql += "WHERE 1=1 ";
                sql += "AND id_usuario = ?";

                //Prepara a instrução SQL
                pst = this.getConexao().prepareStatement(sql);

                //Informa o tipo de dado a ser interpretado.
                pst.setInt(1, usu.getId());

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
        }
        return usu;
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
            sql += "AND usuario_ativo = 'S' ";

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

        //Inicializa o statusPessoa.
        boolean statusPessoa = false;

        //Se for cliente.
        if (cli != null) {
            //Se for PF.
            if (cli.getPf() instanceof PessoaFisica) {
                statusPessoa = new PessoaFisicaDAO(null).verificaPessoaFisica(cli.getPf());
            } else {
                statusPessoa = new PessoaJuridicaDAO(null).verificaPessoaJuridica(cli.getPj());
            }
            //Se for Funcionário            
        } else {
            //Se for PF.
            if (fun.getPf() instanceof PessoaFisica) {
                statusPessoa = new PessoaFisicaDAO(null).verificaPessoaFisica(fun.getPf());
            } else {
                statusPessoa = new PessoaJuridicaDAO(null).verificaPessoaJuridica(fun.getPj());
            }
        }

        if (statusPessoa) {
            JOptionPane.showMessageDialog(null, "CPF ou CNPJ já existentes!");
            return false;
        }
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

                //Se for cliente.
                if (cli instanceof Cliente) {
                    ClienteDAO cliDAO = new ClienteDAO(cli);
                    Usuario usuCli = new Usuario(idUsuario);

                    cliDAO.inclui(cli, usuCli);
                } else {
                    FuncionarioDAO funDAO = new FuncionarioDAO(fun);
                    Usuario usuFun = new Usuario(idUsuario);

                    funDAO.inclui(fun, usuFun);
                }

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

    /**
     * <b>removeLogico</b>
     * Método responsável por efetuar a exclusão lógica do usuário.
     *
     * @param usu (Object).
     * @return Boolean.
     * @throws java.sql.SQLException
     */
    public Boolean removeLogico(Usuario usu) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "UPDATE Usuarios SET usuario_ativo = 'N' ";
            sql += "WHERE id_usuario = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setInt(1, usu.getId());

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Usuário inativado com sucesso!");
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
