package br.com.helpdesk.session;

import br.com.helpdesk.model.usuario.Usuario;
import br.com.helpdesk.view.Login;
import br.com.helpdesk.view.Menu;
import javax.swing.JOptionPane;

/**
 * Sessao [SESSION] Classe responsável por realizar o constrole da sessão.
 *
 * @author Ricardo Guntzell
 */
public class Sessao {

    /*Variável estática que conterá a instancia da classe*/
    private static Sessao instancia;

    private Usuario usuarioSessao;
    private boolean statusLogin;

    /*Atributos de Telas*/
    private final Login login = new Login();
    private Menu menu;

    /* Construtor privado (suprime o construtor público padrão).*/
    private Sessao() {
        this.statusLogin = false;
    }

    public boolean getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(boolean statusLogin) {
        this.statusLogin = statusLogin;
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    public void setMenu(boolean permissao) {
        this.menu = new Menu(permissao);
    }

    /**
     * <b>getSessao</b>
     * Método público estático de acesso único ao objeto(Sessao).
     *
     * @return static Sessao.
     */
    public static Sessao getSessao() {
        if (instancia == null) {
            instancia = new Sessao();
        }
        return instancia;
    }

    /**
     * <b>defineUsuarioSessao</b>
     * Método que define o usuário para a sessão que se estabelecer.
     *
     * @param usu (object).
     */
    private void defineUsuarioSessao(Usuario usu) {
        this.usuarioSessao = usu;
    }

    /**
     * <b>solicitaLogin</b>
     * Método que invoca a tela de Login.
     */
    public void solicitaLogin() {
        this.login.setVisible(true);
    }

    /**
     * <b>solicitaMenu</b>
     * Método que invoca a tela de Menu. Será realizado uma identificação no
     * status do login para poder proceder.
     *
     * @param usu (Object). Define o usuário da sessão.
     */
    public void solicitaMenu(Usuario usu) {
        if (this.statusLogin) {
            this.defineUsuarioSessao(usu);

            this.login.setVisible(false);

            if (this.getUsuarioSessao().getNivel().getForca() > 5) {
                this.setMenu(true);
            } else {
                JOptionPane.showMessageDialog(null, "Você não tem permissão para acessar por essa plataforma, por favor, acesso o módulo web!");
                System.exit(0);
                this.setMenu(false);
            }

            this.menu.setVisible(true);
        }
    }
}
