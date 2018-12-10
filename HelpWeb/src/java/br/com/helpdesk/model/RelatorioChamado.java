package br.com.helpdesk.model;

/**
 * RelatorioChamado [MODEL] Classe responsável por realizar o constrole,
 * gerenciamento e regra de negócios do relatório de chamados.
 *
 * @author Ricardo Guntzell
 */
public class RelatorioChamado {

    private int id;
    private String chamadoCliente;
    private String chamadoFuncionario;
    private String chamadoDescricao;
    private String chamadoSituacao;
    private String chamadoData;

    public RelatorioChamado(int id, String chamadoCliente, String chamadoFuncionario, String chamadoDescricao, String chamadoSituacao, String chamadoData) {
        this.id = id;
        this.chamadoCliente = chamadoCliente;
        this.chamadoFuncionario = chamadoFuncionario;
        this.chamadoDescricao = chamadoDescricao;
        this.chamadoSituacao = chamadoSituacao;
        this.chamadoData = chamadoData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChamadoCliente() {
        return chamadoCliente;
    }

    public void setChamadoCliente(String chamadoCliente) {
        this.chamadoCliente = chamadoCliente;
    }

    public String getChamadoFuncionario() {
        return chamadoFuncionario;
    }

    public void setChamadoFuncionario(String chamadoFuncionario) {
        this.chamadoFuncionario = chamadoFuncionario;
    }

    public String getChamadoDescricao() {
        return chamadoDescricao;
    }

    public void setChamadoDescricao(String chamadoDescricao) {
        this.chamadoDescricao = chamadoDescricao;
    }

    public String getChamadoSituacao() {
        return chamadoSituacao;
    }

    public void setChamadoSituacao(String chamadoSituacao) {
        this.chamadoSituacao = chamadoSituacao;
    }

    public String getChamadoData() {
        return chamadoData;
    }

    public void setChamadoData(String chamadoData) {
        this.chamadoData = chamadoData;
    }

    @Override
    public String toString() {
        return "RelatorioChamado{" + "id=" + id + ", chamadoCliente=" + chamadoCliente + ", chamadoFuncionario=" + chamadoFuncionario + ", chamadoDescricao=" + chamadoDescricao + ", chamadoSituacao=" + chamadoSituacao + ", chamadoData=" + chamadoData + '}';
    }

}
