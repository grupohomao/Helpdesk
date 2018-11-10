package br.com.helpdesk.model;

import br.com.helpdesk.model.usuario.Usuario;
import br.com.helpdesk.model.pessoa.IPessoa;

/**
 * Cliente [MODEL] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Cliente.
 *
 * @author Ricardo Guntzell
 */
public class Cliente {

    private int codigo;
    private Usuario usuario;
    private String ativo;
    private IPessoa pessoa;

    public Cliente(int codigo, Usuario usuario, String ativo, IPessoa pessoa) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.ativo = ativo;
        this.pessoa = pessoa;
    }

    public Cliente(Usuario usuario, String ativo, IPessoa pessoa) {
        this.usuario = usuario;
        this.ativo = ativo;
        this.pessoa = pessoa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public IPessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(IPessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codigo + ", usuario=" + usuario + ", ativo=" + ativo + ", pessoa=" + pessoa + '}';
    }

}
