/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdesk.model;

import br.com.helpdesk.model.pessoa.IPessoa;

/**
 * <b>Cliente</b> Classe responsável por realizar a regra de negócios com os
 * dados de cliente.
 *
 * @author Ricardo Guntzell.
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
    
}

//    /**
//     * <b>definePessoa</b> Classe responsável definir se a pessoa é física ou
//     * jurídica.
//     *
//     * @param tipo int. 1 = PF ou 2 = PJ.
//     * @return Object Pessoa.
//     */
//    public Object definePessoa(int tipo) {
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
//
//}
