package br.com.helpdesk.controller;

import br.com.helpdesk.model.usuario.RelatorioChamado;
import br.com.helpdesk.dal.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DelegaRelatorioChamado [CONTROLLER] Classe responsável por realizar o
 * constrole das ações de chamado.
 *
 * @author Ricardo Guntzell
 */
public class DelegaRelatorioChamado {

    /**
     * <b>acoes</b>
     * Método responsável por gerenciar as ações solicitadas pelo usuário.
     *
     * @param acao (String) tipo de ação a ser executada.
     * @return ArrayList OR NULL.
     * @throws java.sql.SQLException
     */
    public ArrayList<RelatorioChamado> acoes(String acao) throws SQLException {

        RelatorioChamadoDAO relChaDAO = new RelatorioChamadoDAO();

        switch (acao) {
            case "listar":
                return relChaDAO.lista();
        }
        return null;
    }
}
