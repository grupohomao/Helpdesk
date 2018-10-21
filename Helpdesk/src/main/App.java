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

        Endereco end1 = new Endereco("12377-555", "SP", "São Paulo", "rua bla", 777);
        Endereco end2 = new Endereco("12377-444", "SP", "São Paulo", "rua foo", 897);
        Endereco end3 = new Endereco("12377-333", "SP", "São Paulo", "rua xxx", 69);

        Telefone tel1 = new Telefone("fixo", "11", "4242-0881");
        Telefone tel2 = new Telefone("movel", "11", "95550-0041");
        Telefone tel3 = new Telefone("fixo", "11", "5550-1041");

        Usuario usu1 = new Usuario(1, "root", "root", "S");
        Usuario usu2 = new Usuario(1, "foo", "foo", "S");
        Usuario usu3 = new Usuario(1, "bla", "bla", "S");

        PessoaFisica pf1 = new PessoaFisica(
                "028.854.820-52",
                "Desenvolvedor",
                "Ricardo",
                "rricardoguntzell@gmail.com",
                tel1,
                end1
        );
        PessoaFisica pf2 = new PessoaFisica(
                "028.444.999-13",
                "DBA",
                "Will",
                "will@gmail.com",
                tel2,
                end2
        );

        PessoaJuridica pj = new PessoaJuridica(
                "11.123.456/0001-00",
                "254.873.690.747",
                "Guntzell ME",
                "guntzell@guntzell.com",
                tel3,
                end3
        );

        Cliente cli1 = new Cliente(usu1, "S", pf1);
        Cliente cli2 = new Cliente(usu2, "S", pf2);
        Cliente cli3 = new Cliente(usu3, "S", pj);

        Cargo car1 = new Cargo(1, "Supervisor", "S");
        Cargo car2 = new Cargo(2, "Tecnico", "S");

        Funcionario fun1 = new Funcionario(1, usu1, car1, "S", pj);
        Funcionario fun2 = new Funcionario(1, usu2, car2, "S", pf1);

        System.out.println(fun1.toString() + "\n" + usu1.toString() + "\n" + car1.toString() + "\n" + pj.toString() + "\n\n");
        System.out.println(fun2.toString() + "\n" + usu2.toString() + "\n" + car2.toString() + "\n" + pf1.toString() + "\n\n");

        System.out.println(cli1.toString() + "\n" + usu1.toString() + "\n" + pf1.toString() + "\n\n");
        System.out.println(cli2.toString() + "\n" + usu2.toString() + "\n" + pf2.toString() + "\n\n");

    }
}
