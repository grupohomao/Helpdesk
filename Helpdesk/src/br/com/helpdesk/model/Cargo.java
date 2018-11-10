package br.com.helpdesk.model;

/**
 * Cargo [MODEL] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados de Cargo.
 *
 * @author Ricardo Guntzell
 */
public class Cargo {

    private int codigo;
    private String descricao;
    private String ativo;

    public Cargo(int codigo, String descricao, String ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Cargo{" + "codigo=" + codigo + ", descricao=" + descricao + ", ativo=" + ativo + '}';
    }

}
