package br.com.helpdesk.model.pessoa;

import br.com.helpdesk.model.Email;
import br.com.helpdesk.model.Endereco;
import br.com.helpdesk.model.Telefone;

/**
 * Pessoa [MODEL][PESSOA] Classe Abstrata responsável por definir as
 * responsabilidades em comum das outras classes de Pessoa.
 *
 * @author Ricardo Guntzell
 */
public abstract class Pessoa {

    protected String nome;
    protected Email email;
    protected Telefone telefone;
    protected Endereco endereco;

    public Pessoa(String nome, Email email, Telefone telefone, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public abstract String definePessoa();

}
