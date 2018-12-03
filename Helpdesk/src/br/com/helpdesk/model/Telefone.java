package br.com.helpdesk.model;

/**
 * Telefone [MODEL] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Telefone.
 *
 * @author Ricardo Guntzell
 */
public class Telefone {

    private int id;
    private String tipo;
    private String ddd;
    private String numero;

    public Telefone(int id, String tipo, String ddd, String numero) {
        this.id = id;
        this.tipo = tipo;
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone(String tipo, String ddd, String numero) {
        this.tipo = tipo;
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone() {
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
