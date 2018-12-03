package br.com.helpdesk.model.pessoa;

import br.com.helpdesk.model.Email;
import br.com.helpdesk.model.Endereco;
import br.com.helpdesk.model.Telefone;
import java.util.InputMismatchException;

/**
 * PessoaJuridica [MODEL][PESSOA] Classe responsável por realizar o constrole,
 * gerenciamento e regra de negócios dos dados de PessoaJuridica.
 *
 * @author Ricardo Guntzell
 */
public class PessoaJuridica extends Pessoa {

    private String cnpj;
    private String ie;
    private String ramo;

    /*Atributo auxiliar*/
    private String resposta;

    public PessoaJuridica(String cnpj, String ie, String ramo, String nome, Email email, Telefone telefone, Endereco endereco) {
        super(nome, email, telefone, endereco);
        this.cnpj = cnpj;
        this.ie = ie;
        this.ramo = ramo;
    }

    public PessoaJuridica(String nome, Email email, Telefone telefone, Endereco endereco) {
        super(nome, email, telefone, endereco);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    private boolean validaCNPJ() {
        //Remove a possível máscara.
        this.cnpj = this.cnpj.replace(".", "").replace("/", "").replace("-", "");

        // considera-se erro CNPJ's formados por uma sequência de numeros iguais
        if (this.cnpj.equals("00000000000000") || this.cnpj.equals("11111111111111")
                || this.cnpj.equals("22222222222222") || this.cnpj.equals("33333333333333")
                || this.cnpj.equals("44444444444444") || this.cnpj.equals("55555555555555")
                || this.cnpj.equals("66666666666666") || this.cnpj.equals("77777777777777")
                || this.cnpj.equals("88888888888888") || this.cnpj.equals("99999999999999")
                || (this.cnpj.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (this.cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (this.cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == this.cnpj.charAt(12)) && (dig14 == this.cnpj.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public boolean validadPessoaJuridica() {
        boolean status = false;

        //Bloco descrição do CNPJ.
        if (!this.validaCNPJ()) {
            this.setResposta("Seu CNPJ é inválido!");
        } else {
            status = true;
        }

        return status;
    }

    @Override
    public String definePessoa() {
        return "pessoa_juridica";
    }
}
