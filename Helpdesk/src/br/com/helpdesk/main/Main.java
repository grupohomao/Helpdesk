package br.com.helpdesk.main;

import br.com.helpdesk.session.Sessao;

/**
 * Usuario [MAIN] Classe responsável por realizar o constrole, gerenciamento das
 * telas.
 *
 * @author Ricardo Guntzell
 */
public class Main {
        
    public static void main(String[] args) {
        Sessao.getSessao().solicitaLogin();
    }
}
