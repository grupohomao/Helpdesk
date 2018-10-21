/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdesk.model.pessoa;

import br.com.helpdesk.model.Endereco;
import br.com.helpdesk.model.Telefone;

/**
 *
 * @author rrica
 */
public class PessoaFisica extends Pessoa implements IPessoa {

    protected String cpf;
    protected String profissao;

    public PessoaFisica(String cpf, String profissao, String nome, String email, Telefone telefone, Endereco endereco) {
        super(nome, email, telefone, endereco);
        this.cpf = cpf;
        this.profissao = profissao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" + "cpf=" + cpf + ", profissao=" + profissao + '}';
    }

    @Override
    public String getTipoPessoa() {

        return "PessoaFisica";
    }
}