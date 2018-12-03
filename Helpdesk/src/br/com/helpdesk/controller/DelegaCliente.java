package br.com.helpdesk.controller;

import br.com.helpdesk.dal.ClienteDAO;
import br.com.helpdesk.dal.PessoaFisicaDAO;
import br.com.helpdesk.dal.PessoaJuridicaDAO;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DelegaCliente [CONTROLLER] Classe responsável por realizar o constrole das
 * ações do Cliente.
 *
 * @author Ricardo Guntzell
 */
public class DelegaCliente {

    /**
     * <b>acoes</b>
     * Método responsável por gerenciar as ações solicitadas pelo usuário.
     *
     * @param acao (String) tipo de ação a ser executada.
     * @param cli (object) Cliente.
     * @param pf (object) PessoaFisica.
     * @param pj (object) PessoaJuridica.
     * @return ArrayList OR NULL.
     * @throws java.sql.SQLException
     */
    public ArrayList<Cliente> acoes(String acao, Cliente cli, PessoaFisica pf, PessoaJuridica pj) throws SQLException {
        ClienteDAO cliDAO = new ClienteDAO(cli);
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO(pf);
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO(pj);

        switch (acao) {

            case "incluir":
                //cliDAO.inclui(Cliente cli, Pessoa);
                break;
        }
        return null;
    }
}
