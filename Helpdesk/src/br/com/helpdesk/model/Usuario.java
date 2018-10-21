/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdesk.model;

/**
 *
 * @author rrica
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
    
        /**
     * <b>definePessoa</b> Classe responsável definir se a pessoa é física ou
     * jurídica.
     *
     * @param tipo int. 1 = PF ou 2 = PJ.
     * @return Object Pessoa.
     */
//    public Object defineUsuario(int tipo) {
//
//        switch (tipo) {
//            case 1:
//                PessoaFisica pf = new PessoaFisica();
//                this.Pessoa = pf;
//
//            case 2:
//                PessoaJuridica pj = new PessoaJuridica();
//                this.Pessoa = pj;
//
//            default:
//                return null;
//        }
//    }
    
}
