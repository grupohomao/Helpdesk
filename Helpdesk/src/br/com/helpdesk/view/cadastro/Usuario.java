/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdesk.view.cadastro;

/**
 *
 * @author muril
 */
public class Usuario extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroUsuarios
     */
    public Usuario() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        listListaUsuarios = new java.awt.List();
        jButtonIncluirUsuario = new javax.swing.JButton();
        jButtonAlterarUsuario = new javax.swing.JButton();
        jButtonExcluirUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HELP DESK");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Nome:. ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 30, 43, 15);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Login:. ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 70, 50, 15);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("E-Mail:. ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 110, 50, 20);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Telefone:.");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 150, 54, 15);

        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(jTextField1);
        jTextField1.setBounds(90, 20, 410, 30);

        jTextField2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(jTextField2);
        jTextField2.setBounds(90, 60, 410, 30);

        jTextField3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(jTextField3);
        jTextField3.setBounds(90, 100, 410, 30);

        jTextField4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(jTextField4);
        jTextField4.setBounds(90, 140, 410, 30);

        jTextField5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(jTextField5);
        jTextField5.setBounds(90, 180, 410, 30);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Senha:. ");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 190, 50, 15);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("NIVEL DO USUÁRIO (Acessos permitidos)");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 220, 320, 15);

        jRadioButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton1.setText("Supervisor");
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(30, 240, 90, 30);

        jRadioButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton2.setText("Analista");
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(120, 240, 70, 30);

        jRadioButton3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton3.setText("Tecnico");
        getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(200, 240, 90, 30);

        jRadioButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton4.setText("Cliente");
        getContentPane().add(jRadioButton4);
        jRadioButton4.setBounds(290, 240, 100, 30);
        getContentPane().add(listListaUsuarios);
        listListaUsuarios.setBounds(30, 350, 790, 140);

        jButtonIncluirUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonIncluirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/helpdesk/view/img/user_add.png"))); // NOI18N
        jButtonIncluirUsuario.setText("         Incluir");
        getContentPane().add(jButtonIncluirUsuario);
        jButtonIncluirUsuario.setBounds(30, 280, 150, 60);

        jButtonAlterarUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonAlterarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/helpdesk/view/img/user_alert.png"))); // NOI18N
        jButtonAlterarUsuario.setText("        Alterar");
        getContentPane().add(jButtonAlterarUsuario);
        jButtonAlterarUsuario.setBounds(190, 280, 150, 60);

        jButtonExcluirUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonExcluirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/helpdesk/view/img/user_delete.png"))); // NOI18N
        jButtonExcluirUsuario.setText("         Excluir");
        getContentPane().add(jButtonExcluirUsuario);
        jButtonExcluirUsuario.setBounds(350, 280, 150, 60);

        setSize(new java.awt.Dimension(860, 553));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterarUsuario;
    private javax.swing.JButton jButtonExcluirUsuario;
    private javax.swing.JButton jButtonIncluirUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private java.awt.List listListaUsuarios;
    // End of variables declaration//GEN-END:variables
}