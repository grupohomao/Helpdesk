package br.com.helpdesk.controller;

import br.com.helpdesk.dal.UsuarioDAO;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.usuario.Usuario;
import br.com.helpdesk.session.Sessao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DelegaUsuario [CONTROLLER] Classe responsável por realizar o constrole das
 * ações de todas de Usuario.
 *
 * @author Ricardo Guntzell
 */
public class DelegaUsuario {

    /**
     * <b>acoes</b>
     * Método responsável por gerenciar as ações solicitadas pelo usuário.
     *
     * @param acao (String) tipo de ação a ser executada.
     * @param usu (object) Usuario.
     * @return ArrayList OR NULL.
     * @throws java.sql.SQLException
     */
    public ArrayList<Usuario> acoes(String acao, Usuario usu, Cliente cli, Funcionario fun) throws SQLException {
        UsuarioDAO usuDAO = new UsuarioDAO(usu);

        switch (acao) {

            case "logar":
                usuDAO.listaUsuarioSessao(usu);
                Sessao.getSessao().solicitaMenu(usu);
                return null;

            case "listarTodos":
                return usuDAO.lista();

            case "incluir":
                usuDAO.inclui(usu, cli, fun);
                break;

            case "alterar":
                usuDAO.altera(usu);
                break;

            case "excluirLogico":
                usuDAO.removeLogico(usu);
                break;
        }
        return null;
    }
}
