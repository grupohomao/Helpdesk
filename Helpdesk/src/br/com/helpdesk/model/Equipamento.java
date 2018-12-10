package br.com.helpdesk.model;

public class Equipamento {

    private int id;
    private String equipamentoDescricao;

    public Equipamento(int id, String equipamentoDescricao) {
        this.id = id;
        this.equipamentoDescricao = equipamentoDescricao;
    }

    public Equipamento() {
    }

    public int getId() {
        return id;
    }

    public Equipamento(String equipamentoDescricao) {
        this.equipamentoDescricao = equipamentoDescricao;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getEquipamentoDescricao() {
        return equipamentoDescricao;
    }

    public void setEquipamentoDescricao(String equipamentoDescricao) {
        this.equipamentoDescricao = equipamentoDescricao;
    }

    @Override
    public String toString() {
        return "Equipamento{" + "id=" + id + ", equipamentoDescricao=" + equipamentoDescricao + '}';
    }

}
