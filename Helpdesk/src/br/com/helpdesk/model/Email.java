package br.com.helpdesk.model;

/**
 * Email [MODEL] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Email.
 *
 * @author Ricardo Guntzell
 */
public class Email {

    private int id;
    private String descricao;
    private String tipo;

    public Email(int id, String descricao, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
