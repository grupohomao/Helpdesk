package br.com.helpdesk.model;

import br.com.helpdesk.model.usuario.Usuario;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;

/**
 * Cliente [MODEL] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Cliente.
 *
 * @author Ricardo Guntzell
 */
public class Cliente {

    private int id;
    private Usuario usuario;
    private String ativo;
    private PessoaFisica pf;
    private PessoaJuridica pj;

    public Cliente(int id, Usuario usuario, String ativo, PessoaFisica pf, PessoaJuridica pj) {
        this.id = id;
        this.usuario = usuario;
        this.ativo = ativo;
        this.pf = pf;
        this.pj = pj;
    }

    public Cliente(Usuario usuario, PessoaFisica pf, PessoaJuridica pj) {
        this.usuario = usuario;
        this.pf = pf;
        this.pj = pj;
    }
    
    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PessoaFisica getPf() {
        return pf;
    }

    public void setPf(PessoaFisica pf) {
        this.pf = pf;
    }

    public PessoaJuridica getPj() {
        return pj;
    }

    public void setPj(PessoaJuridica pj) {
        this.pj = pj;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", usuario=" + usuario + ", ativo=" + ativo + ", pf=" + pf + ", pj=" + pj + '}';
    }
        
}
