package br.com.helpdesk.dal;

import br.com.helpdesk.model.Ocorrencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OcorrenciaDAO extends Conexao{
    
       /**
     * <b>listar</b>
     * Método responsável por listar os níveis cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public ArrayList<Ocorrencia> lista() throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<Ocorrencia> ocoLista = new ArrayList<>();

        //Obejeto Nivel que será adiocionado ao array.
        Ocorrencia oco;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT * FROM Ocorrencias ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                oco = new Ocorrencia(
                        rs.getInt("id_ocorrencia"),
                        rs.getString("ocorrencia_descricao")
                );

                ocoLista.add(oco);
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

        return ocoLista;
    }

    
}
