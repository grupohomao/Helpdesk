package br.com.helpdesk.model.pessoa;

import br.com.helpdesk.model.Email;
import br.com.helpdesk.model.Endereco;
import br.com.helpdesk.model.Telefone;

/**
 * PessoaJuridica [MODEL][PESSOA] Classe responsável por realizar o constrole,
 * gerenciamento e regra de negócios dos dados de PessoaJuridica.
 *
 * @author Ricardo Guntzell
 */
public class PessoaJuridica extends Pessoa implements IPessoa {

    private String cnpj;
    private String ie;

    public PessoaJuridica(String cnpj, String ie, String nome, Email email, Telefone telefone, Endereco endereco) {
        super(nome, email, telefone, endereco);
        this.cnpj = cnpj;
        this.ie = ie;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    @Override
    public String getTipoPessoa() {
        return "PessoaJuridica";
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" + "cnpj=" + cnpj + ", ie=" + ie + '}';
    }

}
