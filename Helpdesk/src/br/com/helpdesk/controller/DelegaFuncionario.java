package br.com.helpdesk.controller;

import br.com.helpdesk.dal.FuncionarioDAO;
import br.com.helpdesk.dal.PessoaFisicaDAO;
import br.com.helpdesk.dal.PessoaJuridicaDAO;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.pessoa.Pessoa;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DelegaFuncionario [CONTROLLER] Classe responsável por realizar o constrole
 * das ações do Funcionario.
 *
 * @author Ricardo Guntzell
 */
public class DelegaFuncionario {

    /**
     * <b>acoes</b>
     * Método responsável por gerenciar as ações solicitadas pelo usuário.
     *
     * @param acao (String) tipo de ação a ser executada.
     * @param fun (object) Funcionario.
     * @param pf (object) PessoaFisica.
     * @param pj (object) PessoaJuridica.
     * @return ArrayList OR NULL.
     * @throws java.sql.SQLException
     */
    public ArrayList<Funcionario> acoes(String acao, Funcionario fun, PessoaFisica pf, PessoaJuridica pj) throws SQLException {
        FuncionarioDAO funDAO = new FuncionarioDAO(fun);
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO(pf);
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO(pj);

        switch (acao) {

            case "incluir":
                //funDAO.inclui(Funcionario fun, Pessoa);
                break;

        }
        return null;
    }
}
