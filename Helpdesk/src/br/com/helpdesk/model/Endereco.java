package br.com.helpdesk.model;

/**
 * Endereco [MODEL] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Endereco.
 *
 * @author Ricardo Guntzell
 */
public class Endereco {

    private int id;
    private String uf;
    private String cidade;
    private String logradouro;
    private String cep;
    private String numero;
    private String complemento;

    public Endereco(int id, String uf, String cidade, String logradouro, String cep, String numero, String complemento) {
        this.id = id;
        this.uf = uf;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
