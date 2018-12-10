package br.com.helpdesk.model;

import br.com.helpdesk.model.usuario.Usuario;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;

/**
 * Funcionario [MODEL] Classe responsável por realizar o constrole,
 * gerenciamento e regra de negócios dos dados de Funcionario.
 *
 * @author Ricardo Guntzell
 */
public class Funcionario {

    private int codigo;
    private String nome;
    private Usuario usuario;
    private Cargo cargo;
    private String ativo;
    private PessoaFisica pf;
    private PessoaJuridica pj;

    public Funcionario(int codigo, Usuario usuario, Cargo cargo, String ativo, PessoaFisica pf, PessoaJuridica pj) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.cargo = cargo;
        this.ativo = ativo;
        this.pf = pf;
        this.pj = pj;
    }

    public Funcionario(Usuario usuario, Cargo cargo, PessoaFisica pf, PessoaJuridica pj) {
        this.usuario = usuario;
        this.cargo = cargo;
        this.pf = pf;
        this.pj = pj;
    }

    public Funcionario() {
    }

    public Funcionario(int codigo, String nome, Cargo cargo) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargo = cargo;
    }       

    public Funcionario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Funcionario(String nome) {
        this.nome = nome;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "Funcionario{" + "codigo=" + codigo + ", usuario=" + usuario + ", cargo=" + cargo + ", ativo=" + ativo + ", pf=" + pf + ", pj=" + pj + '}';
    }
    
}
