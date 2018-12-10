package br.com.helpdesk.view.cadastro;

import br.com.helpdesk.controller.DelegaCliente;
import br.com.helpdesk.model.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 * Conexao [VIEW] Classe de interface e interação com o usuário.
 *
 * @author Ricardo Guntzell
 */
public class ClienteView extends javax.swing.JFrame {

    /**
     * Creates new form NivelView
     *
     * @throws java.sql.SQLException
     */
    public ClienteView() throws SQLException {
        initComponents();

        //Inicializa o campo de código sempre desabilitado.
        jtfId.setEnabled(false);

        //Lista a tabela de Níveis.
        this.atualizaLista();
        
        jbtnRemover.setVisible(false);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * <b>atualizaLista</b>
     * Método responsável por atualizar a listagem dos níveis conforme
     * manipulação do usuário.
     */
    private void atualizaLista() throws SQLException {
        Cliente cli = new Cliente();
        String acao = "listar";

        //Carrega a listagem de Níveis em ArrayList.
        DelegaCliente delFun = new DelegaCliente();
        ArrayList<Cliente> cliLista = (ArrayList) delFun.acoes(acao, null, null, null);
        
        montaTabela(cliLista);
    }

    /**
     * <b>limpaCampos</b>
     * Método responsável por limpar os campos quando o botão de "Cancelar" é
     * acionado.
     */
    private void limpaCampos() {
        jtfId.setText("");
        jtfNome.setText("");
    }

    /**
     * <b>montaTabela</b>
     * Método responsável por construir o jTable
     *
     * @param nivMT (List)(Nivel) Uma lista de Niveis.
     */
    private void montaTabela(List<Cliente> cliMT) {

        //Objeto responsável por criar o jTable.
        DefaultTableModel dtb = (DefaultTableModel) jtbLista.getModel();

        //Zera o índice do Objeto.
        dtb.setRowCount(0);

        //Percorre o array e preenche o jTable com cada atributo do (object) Nivel.
        for (int indice = 0; indice < cliMT.size(); indice++) {
            Cliente cli = cliMT.get(indice);
            dtb.addRow(new Object[]{cli.getId(), cli.getNome(),});
        }
    }

    /**
     * <b>getNivelLinhaTabela</b>
     * Método responsável por recuperar os valores da linha seleciona do jTable.
     *
     * @param linha (int) índice da linha a ser selecionada.
     * @return (object) Nivel.
     */
    public Cliente getClienteLinhaTabela(int linha) {

        //Caso o valor seja negativo ou seja maior que o total de linhas.
        if (linha < 0 || linha > jtbLista.getRowCount()) {
            return null;
        }

        //Objeto responsável por criar o jTable.
        DefaultTableModel dtm = (DefaultTableModel) jtbLista.getModel();
        
        Cliente cli = new Cliente();

        //Percorre o jTable e seta os atributos do (object) Nivel conforme valores encontrados
        //na lista.
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    cli.setId((int) dtm.getValueAt(linha, i));
                    break;
                case 1:
                    cli.setNome((String) dtm.getValueAt(linha, i));
                    break;
            }
        }
        return cli;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnNivel = new javax.swing.JPanel();
        jlblId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfId = new javax.swing.JTextField();
        jtfNome = new javax.swing.JTextField();
        jbtnListar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbLista = new javax.swing.JTable();
        jbtnSalvar = new javax.swing.JButton();
        jbtnRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nivel");

        jlblId.setText("Código:");

        jLabel2.setText("Nome:");

        jbtnListar.setText("Listar");
        jbtnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnListarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jtbLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbLista);

        jbtnSalvar.setText("Atualizar");
        jbtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalvarActionPerformed(evt);
            }
        });

        jbtnRemover.setText("Remover");
        jbtnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNivelLayout = new javax.swing.GroupLayout(jpnNivel);
        jpnNivel.setLayout(jpnNivelLayout);
        jpnNivelLayout.setHorizontalGroup(
            jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNivelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnNivelLayout.createSequentialGroup()
                        .addComponent(jlblId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(jtfId)))
                    .addGroup(jpnNivelLayout.createSequentialGroup()
                        .addComponent(jbtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(203, Short.MAX_VALUE))
            .addGroup(jpnNivelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnListar, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jbtnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jpnNivelLayout.setVerticalGroup(
            jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNivelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblId)
                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNivelLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnNivelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * <b>jtbListaMouseClicked Evento de seleção da linha</b>
     * Método responsável por selecionar a linha e exibi-lá nos campos para o
     * usuário.
     *
     */
    private void jtbListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbListaMouseClicked
        // TODO add your handling code here:

        jbtnSalvar.setText("Atualizar");
        
        int linhaSelecionada = jtbLista.getSelectedRow();
        
        if (linhaSelecionada >= 0) {
            //this.controlaBotoes(1);
            Cliente cli = getClienteLinhaTabela(linhaSelecionada);
            if (cli != null) {
                jtfId.setText(String.valueOf(cli.getId()));
                jtfNome.setText(cli.getNome());
            }
        }
    }//GEN-LAST:event_jtbListaMouseClicked

    /**
     * <b>jbtnSalvarActionPerformed Evento de gravação ou atualização dos
     * níveis</b>
     *
     */
    private void jbtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalvarActionPerformed
        // TODO add your handling code here:

        String id = jtfId.getText();
        String descricao = jtfNome.getText();
        
        if (id.equals("")) {
            
        } else {
            String acao = "alterar";
            
            DelegaCliente delFun = new DelegaCliente();
            Cliente cli = new Cliente(Integer.parseInt(id), descricao);
            
            try {
                delFun.acoes(acao, cli, null, null);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.atualizaLista();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.limpaCampos();
        }

    }//GEN-LAST:event_jbtnSalvarActionPerformed

    /**
     * <b>jbtnListarActionPerformed Evento para atualizar a listagem de
     * níveis.</b>
     *
     */
    private void jbtnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnListarActionPerformed
        try {
            // TODO add your handling code here:
            this.atualizaLista();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnListarActionPerformed

    /**
     * <b>jbtnRemoverActionPerformed Evento para remover o tipo de nível,
     * conforme linha selecionada.</b>
     *
     */
    private void jbtnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRemoverActionPerformed
        // TODO add your handling code here:

//        String id = jtfId.getText();
//
//        if (!id.isEmpty()) {
//
//            String acao = "remover";
//
//            DelegaNivel delNivel = new DelegaNivel();
//            Nivel niv = new Nivel(Integer.parseInt(id));
//            try {
//                delNivel.acoes(acao, niv);
//            } catch (SQLException ex) {
//                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            try {
//                this.atualizaLista();
//            } catch (SQLException ex) {
//                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            this.limpaCampos();
//        } else {
//            JOptionPane.showMessageDialog(null, "Selecione o tipo de nível que desa remover!");
//        }

    }//GEN-LAST:event_jbtnRemoverActionPerformed

    /**
     * <b>jbtnCancelarActionPerformed Evento para limpar os campos.</b>
     *
     */
    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // TODO add your handling code here:
        this.limpaCampos();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClienteView().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnListar;
    private javax.swing.JButton jbtnRemover;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JLabel jlblId;
    private javax.swing.JPanel jpnNivel;
    private javax.swing.JTable jtbLista;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfNome;
    // End of variables declaration//GEN-END:variables
}