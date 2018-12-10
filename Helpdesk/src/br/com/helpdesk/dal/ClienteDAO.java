package br.com.helpdesk.dal;

import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;
import br.com.helpdesk.model.usuario.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * ClienteDAO [DAL] Classe responsável por realizar o constrole e gerenciamento
 * dos dados com o banco de dados.
 *
 * @author Ricardo Guntzell
 */
public class ClienteDAO extends Conexao {

    public ClienteDAO(Cliente cli) {

    }

    ClienteDAO() {
    }

    /**
     * <b>inclui</b>
     * Método responsável por incluir os dados na tabela de níveis.
     *
     * @param cli (object) Cliente.
     * @param usu (object) Usuario.
     * @throws java.sql.SQLException
     * @return Boolean
     */
    public Boolean inclui(Cliente cli, Usuario usu) throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        String pessoaFJ = null;//Inicializa o tipo de pessoa.
        boolean status = true;//Inicializa o status.

        //Inicializa o retorno do id da pessoa.
        int idPessoa = 0;

        //Bloco para identificar o tipo de pessoa e por fim, efetuar o registro.
        if (cli.getPf() instanceof PessoaFisica) {
            pessoaFJ = "F";
            PessoaFisica pf = new PessoaFisica(cli.getPf().getCpf(), cli.getPf().getProfissao(), cli.getPf().getNome(), cli.getPf().getEmail(), cli.getPf().getTelefone(), cli.getPf().getEndereco());
            PessoaFisicaDAO pfDAO = new PessoaFisicaDAO(pf);

            idPessoa = pfDAO.inclui(pf, usu);
        } else {
            pessoaFJ = "J";

            PessoaJuridica pj = new PessoaJuridica(cli.getPj().getCnpj(), cli.getPj().getIe(), cli.getPj().getRamo(), cli.getPj().getNome(), cli.getPj().getEmail(), cli.getPj().getTelefone(), cli.getPj().getEndereco());
            PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO(pj);

            idPessoa = pjDAO.inclui(pj, usu);
        }

        if (idPessoa > 0) {
            try {
                //Inicia a transação.
                this.getConexao().setAutoCommit(false);

                //Query.
                String sql = "";
                sql += "INSERT INTO Clientes ";
                sql += "(id_pessoa) ";
                sql += "VALUES (?)";

                //Prepara a instrução SQL
                pst = this.getConexao().prepareStatement(sql);

                //Informa o tipo de dado e o dado a ser registrado no BD.
                pst.setInt(1, idPessoa);

                //Recupera o número de linhas afetadas.
                int retorno = pst.executeUpdate();

                //Registra os dados no BD.
                if (retorno > 0) {
                    this.getConexao().commit();
                    //JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
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
        } else {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha na inclusão do cliente, contate o suporte!");
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
    public ArrayList<Cliente> lista() throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        //Inicializa o array para armazenar as tuplas que retornarão do BD.
        ArrayList<Cliente> cliLista = new ArrayList<>();

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT ";
            sql += "cli.id_cliente, pes.id_pessoa, pes.pessoa_nome ";
            sql += "FROM ";
            sql += "Pessoas pes ";
            sql += "INNER JOIN ";
            sql += "Clientes cli ON pes.id_pessoa = cli.id_pessoa ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                Cliente fun = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("pessoa_nome")
                );

                cliLista.add(fun);
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

        return cliLista;
    }

    /**
     * <b>listaClienteId</b>
     * Método responsável por listar os usuários cadastrados.
     *
     * @throws java.sql.SQLException
     * @return ArrayList
     */
    public Cliente listaClienteId(int idCliente) throws SQLException {
        //Inicializa a instrução preparada.
        PreparedStatement pst = null;
        Cliente cli = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "SELECT ";
            sql += "cli.id_cliente, pes.id_pessoa, pes.pessoa_nome ";
            sql += "FROM ";
            sql += "Pessoas pes ";
            sql += "INNER JOIN ";
            sql += "Clientes cli ON pes.id_pessoa = cli.id_pessoa ";
            sql += "WHERE cli.id_cliente = ? ";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);
            
            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setInt(1, idCliente);
            
            //Armazena o objeto de retorno da consulta.
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                cli = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("pessoa_nome")
                );
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

        return cli;
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
    private void alteraNomeCliente(String nome, int codigo) throws SQLException {

        //Inicializa a instrução preparada.
        PreparedStatement pst = null;

        try {
            //Inicia a transação.
            this.getConexao().setAutoCommit(false);

            //Query.
            String sql = "UPDATE Pessoas pes ";
            sql += "INNER JOIN ";
            sql += "Clientes cli ON pes.id_pessoa = cli.id_pessoa  ";
            sql += "SET pes.pessoa_nome = ? ";
            sql += "WHERE ";
            sql += "cli.id_cliente = ?";

            //Prepara a instrução SQL
            pst = this.getConexao().prepareStatement(sql);

            //Informa o tipo de dado e o dado a ser registrado no BD.
            pst.setString(1, nome);
            pst.setInt(2, codigo);

            //Recupera o número de linhas afetadas.
            int retorno = pst.executeUpdate();

            //Registra os dados no BD.
            if (retorno > 0) {
                this.getConexao().commit();
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
            } else {
                //JOptionPane.showMessageDialog(null, "Ocorreu uma falha na atualização, contate o suporte!");
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
    }

    /**
     * <b>altera</b>
     * Método responsável por alterar os dados de clientes.
     *
     * Será implementado futuramente.
     *
     * @param cli
     * @throws java.sql.SQLException
     */
    public void altera(Cliente cli) throws SQLException {
        this.alteraNomeCliente(cli.getNome(), cli.getId());
    }
}
