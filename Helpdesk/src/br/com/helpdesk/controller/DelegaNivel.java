package br.com.helpdesk.controller;

import br.com.helpdesk.dal.NivelDAO;
import br.com.helpdesk.model.usuario.Nivel;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DelegaNivel [CONTROLLER] Classe responsável por realizar o constrole das
 * ações de todas as classes.
 *
 * @author Ricardo Guntzell
 */
public class DelegaNivel {

    /**
     * <b>acoes</b>
     * Método responsável por gerenciar as ações solicitadas pelo usuário.
     *
     * @param acao (String) tipo de ação a ser executada.
     * @param nivel (object) Nivel.
     * @return ArrayList OR NULL.
     * @throws java.sql.SQLException
     */
    public ArrayList<Nivel> acoes(String acao, Nivel nivel) throws SQLException {
        NivelDAO nivDAO = new NivelDAO(nivel);

        switch (acao) {

            case "listarTodos":
                return nivDAO.lista();

            case "incluir":
                nivDAO.inclui(nivel);
                break;

            case "alterar":
                nivDAO.altera(nivel);
                break;

            case "remover":
                nivDAO.remove(nivel);
                break;
        }
        return null;
    }
}
