/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdesk.model;

import br.com.helpdesk.model.pessoa.IPessoa;

/**
 *
 * @author rrica
 */
public class Funcionario {
    private int codigo;
    private Usuario usuario;
    private Cargo cargo;
    private String ativo;
    private IPessoa pessoa;

    public Funcionario(int codigo, Usuario usuario, Cargo cargo, String ativo, IPessoa pessoa) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.cargo = cargo;
        this.ativo = ativo;
        this.pessoa = pessoa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
