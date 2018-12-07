package br.com.helpdesk.model.pessoa;

import br.com.helpdesk.model.Email;
import br.com.helpdesk.model.Endereco;
import br.com.helpdesk.model.Telefone;
import java.util.InputMismatchException;

/**
 * PessoaFisica [MODEL][PESSOA] Classe responsável por realizar o constrole,
 * gerenciamento e regra de negócios dos dados de PessoaFisica.
 *
 * @author Ricardo Guntzell
 */
public class PessoaFisica extends Pessoa {

    protected String cpf;
    protected String profissao;

    /*Atributo auxiliar*/
    public static String resposta;

    public PessoaFisica(String cpf, String profissao, String nome, Email email, Telefone telefone, Endereco endereco) {
        super(nome, email, telefone, endereco);
        this.cpf = cpf;
        this.profissao = profissao;
    }

    public PessoaFisica(String nome, Email email, Telefone telefone, Endereco endereco) {
        super(nome, email, telefone, endereco);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    private boolean validaCPF() {
        //Remove a possível máscara.
        this.cpf = this.cpf.replace(".", "").replace("-", "");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (this.cpf.equals("00000000000")
                || this.cpf.equals("11111111111")
                || this.cpf.equals("22222222222") || this.cpf.equals("33333333333")
                || this.cpf.equals("44444444444") || this.cpf.equals("55555555555")
                || this.cpf.equals("66666666666") || this.cpf.equals("77777777777")
                || this.cpf.equals("88888888888") || this.cpf.equals("99999999999")
                || (this.cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == this.cpf.charAt(9)) && (dig11 == this.cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public boolean validadPessoaFisica() {
        boolean status = false;

        //Bloco descrição do CPF.
        if (!this.validaCPF()) {
            this.setResposta("Seu CPF é inválido!");
        } else if (this.getNome().length() < 5) {
            this.setResposta("Nome precisa conter ao menos 5 caracteres!");
        } else {
            status = true;
        }

        return status;
    }

    @Override
    public String definePessoa() {
        return "pessoa_fisica";
    }

    @Override
    public String toString() {
        return "PessoaFisica{" + "cpf=" + cpf + ", profissao=" + profissao + '}';
    }

}
