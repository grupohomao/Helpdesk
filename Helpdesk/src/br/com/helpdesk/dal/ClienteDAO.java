package br.com.helpdesk.dal;

import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;
import br.com.helpdesk.model.usuario.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

            PessoaJuridica pj = new PessoaJuridica(cli.getPj().getCnpj(), cli.getPj().getIe(), cli.getPf().getProfissao(), cli.getPf().getNome(), cli.getPf().getEmail(), cli.getPf().getTelefone(), cli.getPf().getEndereco());
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
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
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

}
