/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import br.com.helpdesk.dal.PessoaFisicaDAO;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.usuario.Usuario;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rrica
 */
public class Debug {

    public static void main(String[] args) throws SQLException {
        
        Usuario usu = new Usuario(9);
        
        PessoaFisica pf1 = new PessoaFisica("0288990", "Developer", "Ricardo", null, null, null);
        PessoaFisica pf2 = new PessoaFisica(null, "teste@mail.com", null, null, null, null);
        Cliente cli1 = new Cliente(null, pf1, null);
        Cliente cli2 = new Cliente(usu, null, null);
        Cliente cli3 = new Cliente(null, null, null);
        
        if(cli1.getPf() instanceof PessoaFisica){
        //    JOptionPane.showMessageDialog(null, "Sim");
        }else{
        //    JOptionPane.showMessageDialog(null, "Não");
        }
        
        new PessoaFisicaDAO(pf1).inclui(pf1, usu);
        
    }
}
