/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import br.com.helpdesk.model.Cargo;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Endereco;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.Telefone;
import br.com.helpdesk.model.Usuario;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;

/**
 *
 * @author rrica
 */
public class App {

    public static void main(String[] args) {
        //Conexao con = new Conexao();

        Endereco end = new Endereco("12377-555", "SP", "São Paulo", "rua bla", 777);
        Telefone tel = new Telefone("fixo", "11", "95550-0041");

        Usuario usu = new Usuario(1, "root", "root", "S");
        
        PessoaFisica pf = new PessoaFisica(
                "028.854.820-52",
                "Desenvolvedor",
                "Ricardo",
                "rricardoguntzell@gmail.com",
                tel,
                end
        );

        PessoaJuridica pj = new PessoaJuridica(
                "11.123.456/0001-00",
                "254.873.690.747",
                "Guntzell ME",
                "guntzell@guntzell.com",
                tel,
                end
        );

        Cliente cli = new Cliente(usu, "S", pj);
        
        Cargo car1 = new Cargo(1, "Supervisor", "S");
        Cargo car2 = new Cargo(2, "Tecnico", "S");

        Funcionario fun1 = new Funcionario(1, usu, car1, "S", pj);
        Funcionario fun2 = new Funcionario(2, usu, car2, "S", pj);

    }
}
