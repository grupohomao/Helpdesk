package br.com.helpdesk.model;

/**
 * Chamado [MODEL] Classe responsável por realizar o constrole, gerenciamento e
 * regra de negócios dos dados dos chamados.
 *
 * @author Ricardo Guntzell
 */
public class Chamado {

    private int id;
    private int idOcorrencia;
    private int idEquipamento;
    private int idCliente;
    private int idFuncionario;
    private String chamadoSituacao;
    private String chamadoDescricao;
    private String chamadoData;

    private Ocorrencia Ocorrencia;
    private Equipamento Equipamento;
    private Cliente Cliente;
    private Funcionario Funcionario;

    public Chamado() {
    }

    public Chamado(int idOcorrencia, int idEquipamento, int idCliente, int idFuncionario, String chamadoSituacao, String chamadoDescricao, String chamadoData) {
        this.idOcorrencia = idOcorrencia;
        this.idEquipamento = idEquipamento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.chamadoSituacao = chamadoSituacao;
        this.chamadoDescricao = chamadoDescricao;
        this.chamadoData = chamadoData;
    }

    public Chamado(int idOcorrencia, int idEquipamento, int idCliente, int idFuncionario, String chamadoDescricao, String chamadoSituacao) {
        this.idOcorrencia = idOcorrencia;
        this.idEquipamento = idEquipamento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.chamadoDescricao = chamadoDescricao;
        this.chamadoSituacao = chamadoSituacao;
    }

    public Chamado(int idOcorrencia, int idEquipamento, int idCliente, int idFuncionario, String chamadoDescricao) {
        this.idOcorrencia = idOcorrencia;
        this.idEquipamento = idEquipamento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.chamadoDescricao = chamadoDescricao;
    }

    public Chamado(int id, String chamadoSituacao, String chamadoDescricao) {
        this.id = id;
        this.chamadoSituacao = chamadoSituacao;
        this.chamadoDescricao = chamadoDescricao;
    }
    
    

    public Chamado(int id, int idOcorrencia, int idEquipamento, int idCliente, int idFuncionario, String chamadoDescricao) {
        this.id = id;
        this.idOcorrencia = idOcorrencia;
        this.idEquipamento = idEquipamento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.chamadoDescricao = chamadoDescricao;
    }

    public Chamado(int id, Ocorrencia Ocorrencia, Equipamento Equipamento, Cliente Cliente, Funcionario Funcionario, String chamadoDescricao) {
        this.id = id;
        this.Ocorrencia = Ocorrencia;
        this.Equipamento = Equipamento;
        this.Cliente = Cliente;
        this.Funcionario = Funcionario;
        this.chamadoDescricao = chamadoDescricao;
    }

    public Chamado(int id, Ocorrencia Ocorrencia, Equipamento Equipamento, Cliente Cliente, Funcionario Funcionario, String chamadoDescricao, String chamadoSituacao) {
        this.id = id;
        this.Ocorrencia = Ocorrencia;
        this.Equipamento = Equipamento;
        this.Cliente = Cliente;
        this.Funcionario = Funcionario;
        this.chamadoDescricao = chamadoDescricao;
        this.chamadoSituacao = chamadoSituacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(int idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getChamadoSituacao() {
        return chamadoSituacao;
    }

    public void setChamadoSituacao(String chamadoSituacao) {
        this.chamadoSituacao = chamadoSituacao;
    }

    public String getChamadoDescricao() {
        return chamadoDescricao;
    }

    public void setChamadoDescricao(String chamadoDescricao) {
        this.chamadoDescricao = chamadoDescricao;
    }

    public String getChamadoData() {
        return chamadoData;
    }

    public void setChamadoData(String chamadoData) {
        this.chamadoData = chamadoData;
    }

    public Ocorrencia getOcorrencia() {
        return Ocorrencia;
    }

    public void setOcorrencia(Ocorrencia Ocorrencia) {
        this.Ocorrencia = Ocorrencia;
    }

    public Equipamento getEquipamento() {
        return Equipamento;
    }

    public void setEquipamento(Equipamento Equipamento) {
        this.Equipamento = Equipamento;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public Funcionario getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(Funcionario Funcionario) {
        this.Funcionario = Funcionario;
    }

    @Override
    public String toString() {
        return "Chamado{" + "id=" + id + ", idOcorrencia=" + idOcorrencia + ", idEquipamento=" + idEquipamento + ", idCliente=" + idCliente + ", idFuncionario=" + idFuncionario + ", chamadoSituacao=" + chamadoSituacao + ", chamadoDescricao=" + chamadoDescricao + ", chamadoData=" + chamadoData + ", Ocorrencia=" + Ocorrencia + ", Equipamento=" + Equipamento + ", Cliente=" + Cliente + ", Funcionario=" + Funcionario + '}';
    }

}
