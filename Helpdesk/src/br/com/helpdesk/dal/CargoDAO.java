package br.com.helpdesk.dal;

import br.com.helpdesk.model.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * NivelDAO [DAL] Classe responsável por realizar o constrole e gerenciamento
 * dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class CargoDAO extends Conexao {

    public CargoDAO() {

    }

    /**
     * <b>listar</b>
     * Método responsável por listar os cargos cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public Cargo listaCargoId(int idCargo) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<Cargo> CargoLista = new ArrayList<>();

        //Obejeto Nivel que será adiocionado ao array.
        Cargo car = new Cargo();

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT * FROM Cargos ";
            sql += "WHERE 1=1 ";
            sql += "AND id_cargo = ? ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado a ser interpretado.
            pst.setInt(1, idCargo);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                car = new Cargo(
                        rs.getInt("id_cargo"),
                        rs.getString("cargo_descricao")
                );

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

        return car;
    }

    /**
     * <b>listar</b>
     * Método responsável por listar os cargos cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public ArrayList<Cargo> listaTodos() throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<Cargo> CargoLista = new ArrayList<>();

        //Obejeto Nivel que será adiocionado ao array.
        Cargo car = new Cargo();

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT * FROM Cargos ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                car = new Cargo(
                        rs.getInt("id_cargo"),
                        rs.getString("cargo_descricao")
                );
                CargoLista.add(car);
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

        return CargoLista;
    }

}
