package br.com.helpdesk.model;


public class Ocorrencia {
    private int id;
    private String ocorrenciaDescricao;

    public Ocorrencia() {
    }   
    
    public Ocorrencia(int id, String ocorrenciaDescricao) {
        this.id = id;
        this.ocorrenciaDescricao = ocorrenciaDescricao;
    }

    public int getId() {
        return id;
    }

    public Ocorrencia(String ocorrenciaDescricao) {
        this.ocorrenciaDescricao = ocorrenciaDescricao;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getOcorrenciaDescricao() {
        return ocorrenciaDescricao;
    }

    public void setOcorrenciaDescricao(String ocorrenciaDescricao) {
        this.ocorrenciaDescricao = ocorrenciaDescricao;
    }
    
    

    @Override
    public String toString() {
        return "Ocorrencia{" + "id=" + id + ", ocorrenciaDescricao=" + ocorrenciaDescricao + '}';
    }
    
    
}
