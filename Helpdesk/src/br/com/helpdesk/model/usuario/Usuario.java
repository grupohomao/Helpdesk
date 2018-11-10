package br.com.helpdesk.model.usuario;

/**
 * Usuario [MODEL][USUARIO] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Usuario.
 *
 * @author Ricardo Guntzell
 */
public class Usuario {

    private int codigo;
    private int codigoNivel;
    private String usuario;
    private String senha;
    private String ativo;

    public Usuario(int codigo, int codigoNivel, String usuario, String senha, String ativo) {
        this.codigo = codigo;
        this.codigoNivel = codigoNivel;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Usuario(int codigoNivel, String usuario, String senha, String ativo) {
        this.codigoNivel = codigoNivel;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(int codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", codigoNivel=" + codigoNivel + ", usuario=" + usuario + ", senha=" + senha + ", ativo=" + ativo + '}';
    }
}
