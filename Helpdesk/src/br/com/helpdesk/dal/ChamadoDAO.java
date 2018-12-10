package br.com.helpdesk.dal;

import br.com.helpdesk.model.Chamado;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Equipamento;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.Ocorrencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * ChamadoDAO [DAL] Classe responsável por realizar o constrole e gerenciamento
 * dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class ChamadoDAO extends Conexao {

    public ChamadoDAO() {
    }

    /**
     * <b>inclui</b>
     * Método responsável por incluir os dados na tabela de níveis.
     *
     * @param cha (Object).
     * @throws java.sql.SQLException
     * @return Boolean
     */
    public Boolean inclui(Chamado cha) throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "INSERT INTO Chamados ";
            sql += "(id_ocorrencia, id_equipamento, id_cliente, id_funcionario, chamado_situacao, chamado_descricao) ";
            sql += "VALUES (?,?,?,?,?,?)";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setInt(1, cha.getIdOcorrencia());
            pst.setInt(2, cha.getIdEquipamento());
            pst.setInt(3, cha.getIdCliente());
            pst.setInt(4, cha.getIdFuncionario());
            pst.setString(5, cha.getChamadoSituacao());
            pst.setString(6, cha.getChamadoDescricao());
   
            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Chamado registrado!");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu uma falha na inclusão, contate o suporte!");
            }
        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
                this.getConexao().rollback();
            }
        } finally {
            //Fecha a conexão.
            if (pst != null) {
                pst.close();
            }
            //Finaliza as transações.
            this.getConexao().setAutoCommit(true);
        }
        return true;
    }

    /**
     * <b>lista</b>
     * Método responsável por listar os usuários cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public ArrayList<Chamado> lista() throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<Chamado> chaLista = new ArrayList<>();

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT ";
            sql += "cha.id_chamado, ";
            sql += "cha.id_ocorrencia, ";
            sql += "oco.ocorrencia_descricao, ";
            sql += "equip.equipamento_descricao, ";
            sql += "cha.id_equipamento, ";
            sql += "cha.id_cliente, ";
            sql += "cha.id_funcionario, ";
            sql += "pes.pessoa_nome AS cliente, ";
            sql += "cha.id_funcionario, ";
            sql += "cha.chamado_descricao, ";
            sql += "cha.chamado_situacao, ";
            sql += "cha.chamado_data ";
            sql += "FROM ";
            sql += "Pessoas pes ";
            sql += "INNER JOIN ";
            sql += "Clientes cli ON pes.id_pessoa = cli.id_pessoa ";
            sql += "INNER JOIN ";
            sql += "Chamados cha ON cli.id_cliente = cha.id_cliente ";
            sql += "INNER JOIN ";
            sql += "Ocorrencias oco ON oco.id_ocorrencia = cha.id_ocorrencia ";
            sql += "INNER JOIN ";
            sql += "Equipamentos equip ON equip.id_equipamento = cha.id_equipamento ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Ocorrencia oco = new Ocorrencia(rs.getInt("id_ocorrencia"), rs.getString("ocorrencia_descricao"));
                Equipamento equip = new Equipamento(rs.getInt("id_equipamento"), rs.getString("equipamento_descricao"));

                Cliente cli = new ClienteDAO().listaClienteId(rs.getInt("id_cliente"));

                Funcionario fun = new FuncionarioDAO(null).listaFuncionarioId(rs.getInt("id_cliente"));

                Chamado cha = new Chamado(
                        rs.getInt("id_chamado"),
                        oco,
                        equip,
                        cli,
                        fun,
                        rs.getString("chamado_descricao"),
                        rs.getString("chamado_situacao")
                );
                chaLista.add(cha);
            }

        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                System.out.println("#Rollback efetuado na transação. \n" + ex);
                this.getConexao().rollback();
            }
        } finally {
            //Fecha a conexão.
            if (pst != null) {
                pst.close();
            }
            //Finaliza as transações.
            this.getConexao().setAutoCommit(true);
        }

        return chaLista;
    }
    
     /**
     * <b>altera</b>
     * Método responsável por alterar os dados na tabela de níveis conforme
     * nível passado.
     *
     * @param niv (Object).
     * @return Boolean.
     * @throws java.sql.SQLException
     */
    public Boolean altera(Chamado cha) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "";
            sql += "UPDATE Chamados SET chamado_descricao = ?, ";
            sql += "chamado_Situacao = ? ";
            sql += "WHERE id_chamado = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, cha.getChamadoDescricao());
            pst.setString(2, cha.getChamadoSituacao());
            pst.setInt(3, cha.getId());

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Chamado alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu uma falha na atualização, contate o suporte!");
            }
        } catch (SQLException ex) {
            //Caso aconteça uma exceção, é efetuado o roolback.
            if (this.getConexao() != null) {
                JOptionPane.showMessageDialog(null, "#Rollback efetuado na transação. \n" + ex);
                this.getConexao().rollback();
            }
        } finally {
            //Fecha a conexão.
            if (pst != null) {
                pst.close();
            }
            //Finaliza as transações.
            this.getConexao().setAutoCommit(true);
        }
        return true;
    }


}
