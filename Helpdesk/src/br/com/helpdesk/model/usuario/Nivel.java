package br.com.helpdesk.model.usuario;

/**
 * Nivel [MODEL][USUARIO] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Nivel.
 *
 * @author Ricardo Guntzell
 */
public class Nivel {

    private int id;
    private String descricao;
    private int forca;

    public Nivel(int id, String descricao, int forca) {
        this.id = id;
        this.descricao = descricao;
        this.forca = forca;
    }

    public Nivel(String descricao, int forca) {
        this.descricao = descricao;
        this.forca = forca;
    }

    public Nivel(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    public Nivel(int id) {
        this.id = id;
    }

    public Nivel(String descricao) {
        this.descricao = descricao;
    }
    
    public Nivel() {
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

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    @Override
    public String toString() {
        return "Nivel{" + "id=" + id + ", descricao=" + descricao + ", forca=" + forca + '}';
    }

}
