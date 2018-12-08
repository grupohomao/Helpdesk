package br.com.helpdesk.controller;

import br.com.helpdesk.dal.UsuarioDAO;
import br.com.helpdesk.model.usuario.Usuario;
import java.sql.SQLException;

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
    public boolean acoes(String acao, Usuario usu) throws SQLException {
        UsuarioDAO usuDAO = new UsuarioDAO();
        boolean retorno = false;

        switch (acao) {

            case "logar":
                retorno = usuDAO.login(usu);
                //Sessao.getSessao().solicitaMenu(usu);
                break;
        }

        return retorno;
    }
}
