/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdesk.view.cadastro;

import br.com.helpdesk.controller.DelegaNivel;
import br.com.helpdesk.controller.DelegaUsuario;
import br.com.helpdesk.model.Cargo;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Email;
import br.com.helpdesk.model.Endereco;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.Telefone;
import br.com.helpdesk.model.pessoa.PessoaFisica;
import br.com.helpdesk.model.pessoa.PessoaJuridica;
import br.com.helpdesk.model.usuario.Nivel;
import br.com.helpdesk.model.usuario.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author muril
 */
public class UsuarioView extends javax.swing.JFrame {

    private DefaultFormatterFactory dff = new DefaultFormatterFactory();
    private MaskFormatter mascara = new MaskFormatter();

    /**
     * Creates new form TelaCadastroUsuarios
     *
     * @throws java.sql.SQLException
     */
    public UsuarioView() throws SQLException, ParseException {
        initComponents();

        //Inicializa o campo de código sempre desabilitado.
        jtxtId.setEnabled(false);

        //Lista a tabela de Níveis.
        this.atualizaLista();

        //Monta o combobox de Níveis.
        this.montaCBONivel();

        //Monta o combobox de Tipos de Usuários.
        this.montaCBOTipoUsuario();

    }

    /**
     * <b>atualizaLista</b>
     * Método responsável por atualizar a listagem dos usuários conforme
     * manipulação do usuário.
     */
    private void atualizaLista() throws SQLException {
        Usuario usu = new Usuario();
        String acao = "listarTodos";

        //Carrega a listagem de Níveis em ArrayList.
        DelegaUsuario delUsu = new DelegaUsuario();
        ArrayList<Usuario> usuLista = (ArrayList) delUsu.acoes(acao, usu, null, null);

        montaTabela(usuLista);
    }

    /**
     * <b>limpaCampos</b>
     * Método responsável por limpar os campos quando o botão de "Cancelar" é
     * acionado.
     */
    private void limpaCampos() {
        //Bloco 1
        jtxtId.setText("");
        jtxtLogin.setText("");
        jpwfSenha.setText("");
        jcboNivel.setSelectedIndex(0);

        //Bloco 2
        jtxtNome.setText("");
        jftxtTelefone.setText("");
        jtxtEmail.setText("");
        jcboTipoUsuario.setSelectedIndex(0);

        //Bloco 3
        btngPessoa.clearSelection();
        jftxtPessoa.setText("");
        jtxtRamo.setText("");
        jftxtIE.setText("");
    }

    /**
     * <b>desabilitaCampos</b>
     * Método responsável por desabilitar os campos desnecessários quando
     * ocorrer a atualização do usuário.
     */
    private void desabilitaCampos(boolean status) {

        if (status) {
            status = false;
        } else {
            status = true;
        }

        jtxtNome.setVisible(status);
        jlblNome.setVisible(status);

        jftxtTelefone.setVisible(status);
        jlblTelefone.setVisible(status);

        jtxtEmail.setVisible(status);
        jlblEmail.setVisible(status);

        jlblPessoa.setVisible(status);
        jftxtPessoa.setVisible(status);
        jrbtCpf.setVisible(status);
        jrbtCnpj.setVisible(status);

        jlblIE.setVisible(status);
        jftxtIE.setVisible(status);

        jlblRamo.setVisible(status);
        jtxtRamo.setVisible(status);

        jlblTipoUsuario.setVisible(status);
        jcboTipoUsuario.setVisible(status);
    }

    /**
     * <b>montaTabela</b>
     * Método responsável por construir o jTable
     *
     * @param nivMT (List)(Nivel) Uma lista de Niveis.
     */
    private void montaTabela(List<Usuario> usuMT) {

        //Objeto responsável por criar o jTable.
        DefaultTableModel dtb = (DefaultTableModel) jtbLista.getModel();

        //Zera o índice do Objeto.
        dtb.setRowCount(0);

        //Percorre o array e preenche o jTable com cada atributo do (object) Nivel.
        for (int indice = 0; indice < usuMT.size(); indice++) {
            Usuario usu = usuMT.get(indice);
            dtb.addRow(new Object[]{usu.getId(), usu.getUsuarioDescricao()});
        }
    }

    /**
     * <b>getUsuarioLinhaTabela</b>
     * Método responsável por recuperar os valores da linha seleciona do jTable.
     *
     * @param linha (int) índice da linha a ser selecionada.
     * @return (object) Usuario.
     */
    private Usuario getUsuarioLinhaTabela(int linha) {

        //Caso o valor seja negativo ou seja maior que o total de linhas.
        if (linha < 0 || linha > jtbLista.getRowCount()) {
            return null;
        }

        //Objeto responsável por criar o jTable.
        DefaultTableModel dtm = (DefaultTableModel) jtbLista.getModel();

        Usuario usu = new Usuario();

        //Percorre o jTable e seta os atributos do (object) Nivel conforme valores encontrados
        //na lista.
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    usu.setId((int) dtm.getValueAt(linha, i));
                    break;
                case 1:
                    usu.setUsuarioDescricao((String) dtm.getValueAt(linha, i));
                    break;
            }
        }
        return usu;
    }

    /**
     * <b>recuperaNiveis</b>
     * Método responsável por listar os níveis.
     *
     * @return ArrayList OR NULL.
     * @throws java.sql.SQLException
     */
    private ArrayList recuperaNiveis() throws SQLException {
        Nivel niv = new Nivel();
        String acao = "listarTodos";

        //Carrega a listagem de Níveis em ArrayList.
        DelegaNivel delNivel = new DelegaNivel();
        ArrayList<Nivel> nivLista = (ArrayList) delNivel.acoes(acao, niv);

        return nivLista;
    }

    /**
     * <b>montaCBONivel</b>
     * Método responsável por construir o combobox de Níveis.
     *
     * @throws java.sql.SQLException
     */
    private void montaCBONivel() throws SQLException {
        Nivel niv = new Nivel();
        int indice = 0;

        ArrayList<Nivel> nivLista = this.recuperaNiveis();

        for (indice = 0; indice < nivLista.size(); indice++) {
            niv = nivLista.get(indice);
            jcboNivel.addItem(String.valueOf(niv.getDescricao()));
        }
    }

    /**
     * <b>montaCBOTipoUsuario</b>
     * Método responsável por construir o combobox de Tipo de Usuário.
     *
     */
    private void montaCBOTipoUsuario() {
        jcboTipoUsuario.addItem("Selecione");
        jcboTipoUsuario.addItem("Cliente");
        jcboTipoUsuario.addItem("Funcionário");
    }

    /**
     * <b>formataTipoPessoa</b>
     * Método responsável por formatar o campo que receberá o CPF ou CNPJ.
     *
     * @param tipoPessoa (int).
     */
    private void formataTipoPessoa(int tipoPessoa) throws ParseException {
        //Reinicializa o campo.
        jftxtPessoa.setValue(null);

        //Verifica se é pessoa física(1) ou jurídica(2) e define o valor da máscara.
        if (tipoPessoa == 1) {
            this.mascara.setMask("###.###.###-##");
        } else {
            this.mascara.setMask("##.###.###/####-##");
        }

        //Seta o valor da classe padrão da máscara
        this.dff.setDefaultFormatter(this.mascara);

        //Seta o valor do campo.
        jftxtPessoa.setFormatterFactory(this.dff);
    }

//    private boolean verificaPreenchimento() {
//        boolean status = false;
//        String acao = null;
//
//        if (jtxtId.getText().equals("")) {
//            acao = "incluir";
//        } else {
//            acao = "alterar";
//        }
//
//        switch (acao) {
//            case "incluir":
//                     if(jcboTip tipoPessoa != 0 && tipoUsuario != 0)           
//                break;
//
//            case "altear":
//                break;
//
//        }
//
//        return status;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngPessoa = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jifrmUsuario = new javax.swing.JInternalFrame();
        jbtnSalvar = new javax.swing.JButton();
        jtxtEmail = new javax.swing.JTextField();
        jbtnExcluir = new javax.swing.JButton();
        jpwfSenha = new javax.swing.JPasswordField();
        jlblNivel = new javax.swing.JLabel();
        jlblNome = new javax.swing.JLabel();
        jrbtCnpj = new javax.swing.JRadioButton();
        jrbtCpf = new javax.swing.JRadioButton();
        jbtnListar = new javax.swing.JButton();
        jlblPessoa = new javax.swing.JLabel();
        jtxtNome = new javax.swing.JTextField();
        jftxtPessoa = new javax.swing.JFormattedTextField();
        jlblTelefone = new javax.swing.JLabel();
        jcboNivel = new javax.swing.JComboBox<>();
        jlblLogin = new javax.swing.JLabel();
        jlblSenha = new javax.swing.JLabel();
        jtxtLogin = new javax.swing.JTextField();
        jlblEmail = new javax.swing.JLabel();
        jlblIE = new javax.swing.JLabel();
        jftxtIE = new javax.swing.JFormattedTextField();
        jlblRamo = new javax.swing.JLabel();
        jtxtRamo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbLista = new javax.swing.JTable();
        jbtnCancelar = new javax.swing.JButton();
        jlblId = new javax.swing.JLabel();
        jtxtId = new javax.swing.JTextField();
        jftxtTelefone = new javax.swing.JFormattedTextField();
        jlblTipoUsuario = new javax.swing.JLabel();
        jcboTipoUsuario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Helpdesk");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(null);

        jifrmUsuario.setTitle("Controle de Usuários");
        jifrmUsuario.setVisible(true);

        jbtnSalvar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jbtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/helpdesk/view/img/user_add.png"))); // NOI18N
        jbtnSalvar.setText("Salvar");
        jbtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalvarActionPerformed(evt);
            }
        });

        jtxtEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jbtnExcluir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jbtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/helpdesk/view/img/user_delete.png"))); // NOI18N
        jbtnExcluir.setText("         Excluir");
        jbtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExcluirActionPerformed(evt);
            }
        });

        jlblNivel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblNivel.setText("Nível:");

        jlblNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblNome.setText("Nome:. ");

        btngPessoa.add(jrbtCnpj);
        jrbtCnpj.setText("Jurídica");
        jrbtCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtCnpjActionPerformed(evt);
            }
        });

        btngPessoa.add(jrbtCpf);
        jrbtCpf.setText("Física");
        jrbtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtCpfActionPerformed(evt);
            }
        });

        jbtnListar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jbtnListar.setText("Listar");
        jbtnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnListarActionPerformed(evt);
            }
        });

        jlblPessoa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblPessoa.setText("Tipo Pessoa:. ");

        jtxtNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jftxtPessoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jftxtPessoaMouseClicked(evt);
            }
        });
        jftxtPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jftxtPessoaActionPerformed(evt);
            }
        });

        jlblTelefone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblTelefone.setText("Telefone:.");

        jcboNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboNivelActionPerformed(evt);
            }
        });

        jlblLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblLogin.setText("Login:. ");

        jlblSenha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblSenha.setText("Senha:. ");

        jtxtLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jlblEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblEmail.setText("E-Mail:. ");

        jlblIE.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblIE.setText("IE:");

        jlblRamo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblRamo.setText("Ramo:");

        jtxtRamo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jtbLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Login"
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

        jbtnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jbtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/helpdesk/view/img/user_alert.png"))); // NOI18N
        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jlblId.setText("Código:.");

        jlblTipoUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlblTipoUsuario.setText("Tipo Usuário:.");

        jcboTipoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcboTipoUsuarioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jcboTipoUsuarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jcboTipoUsuarioMouseReleased(evt);
            }
        });
        jcboTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboTipoUsuarioActionPerformed(evt);
            }
        });
        jcboTipoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcboTipoUsuarioKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jifrmUsuarioLayout = new javax.swing.GroupLayout(jifrmUsuario.getContentPane());
        jifrmUsuario.getContentPane().setLayout(jifrmUsuarioLayout);
        jifrmUsuarioLayout.setHorizontalGroup(
            jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                        .addComponent(jbtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jifrmUsuarioLayout.createSequentialGroup()
                                        .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                        .addComponent(jlblId)
                                        .addGap(11, 11, 11)))
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtxtId, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                    .addComponent(jtxtLogin, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jpwfSenha, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                .addComponent(jlblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                .addComponent(jlblTipoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jifrmUsuarioLayout.createSequentialGroup()
                                .addComponent(jlblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jifrmUsuarioLayout.createSequentialGroup()
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblTelefone)
                                    .addComponent(jlblNome))
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtNome))
                                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jftxtTelefone)))))
                        .addGap(12, 12, 12)
                        .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jlblIE, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlblRamo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jftxtIE, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtRamo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                .addComponent(jlblPessoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jrbtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jftxtPessoa))
                        .addGap(35, 35, 35))))
        );
        jifrmUsuarioLayout.setVerticalGroup(
            jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNome)
                            .addComponent(jtxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblPessoa)
                            .addComponent(jrbtCpf)
                            .addComponent(jrbtCnpj)
                            .addComponent(jlblId)
                            .addComponent(jtxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jftxtPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jlblTelefone))
                                    .addComponent(jftxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblRamo)
                                    .addComponent(jtxtRamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jlblLogin)))
                                .addGap(17, 17, 17)
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jpwfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblSenha))
                                .addGap(18, 18, 18)
                                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlblNivel)
                                    .addComponent(jcboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblTipoUsuario)
                                    .addComponent(jcboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jftxtIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblIE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jifrmUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jifrmUsuarioLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jifrmUsuarioLayout.createSequentialGroup()
                        .addComponent(jbtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
        );

        getContentPane().add(jifrmUsuario);
        jifrmUsuario.setBounds(0, 0, 830, 520);

        setSize(new java.awt.Dimension(857, 566));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // TODO add your handling code here:
        this.limpaCampos();
        this.desabilitaCampos(false);
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jbtnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnListarActionPerformed
        try {
            // TODO add your handling code here:
            this.atualizaLista();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnListarActionPerformed

    private void jbtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalvarActionPerformed
        try {

            String acaoForm = "";

            if (jtxtId.getText().equals("")) {
                acaoForm = "incluir";
            } else {
                acaoForm = "alterar";
            }

            switch (acaoForm) {
                case "incluir":
                    //Bloco para recuperar o nível do usuário via formulário.
                    Nivel niv = new Nivel();

                    ArrayList<Nivel> nivLista = this.recuperaNiveis();
                    niv = nivLista.get(jcboNivel.getSelectedIndex());

                    Nivel nivUsu = new Nivel(niv.getId(), niv.getDescricao(), niv.getForca());

                    Usuario usu = new Usuario(nivUsu, jtxtLogin.getText(), jpwfSenha.getText());
                    //Validação do Usuário.
                    if (usu.validaUsuario()) {

                        Email mail;//Inicializa o objeto Email.
                        Telefone tel;//Inicializa o objeto Telefone.
                        Endereco end;//Inicializa o objeto Endereco.

                        //DelegaUsuario delUsu = new DelegaUsuario();
                        DelegaUsuario delUsu;//Inicializa o objeto controlador DelegaUsuario.
                        PessoaFisica pf;//Inicializa o objeto PessoaFisica.
                        PessoaJuridica pj;//Inicializa o objeto PessoaJuridica.

                        //Verifica se é pessoa física ou jurídica.
                        if (jrbtCpf.isSelected() || jrbtCnpj.isSelected()) {
                            Cliente cli;//Inicializa o objeto Cliente.                            
                            Funcionario fun;//Inicializa o objeto Funcionario.                            

                            //Se for Pessoa Física...
                            if (jrbtCpf.isSelected()) {
                                mail = new Email(jtxtEmail.getText(), "default");
                                tel = new Telefone("default", jftxtTelefone.getText(), "default");
                                end = new Endereco();
                                pf = new PessoaFisica(jftxtPessoa.getText(), jtxtRamo.getText(), jtxtNome.getText(), mail, tel, end);
                                pj = null;

                                //Valida os dados de uma pessoa física.
                                if (pf.validadPessoaFisica()) {
                                    if (jcboTipoUsuario.getSelectedItem() == "Cliente") {
                                        cli = new Cliente(usu, pf, pj);//Instância Cliente.
                                        delUsu = new DelegaUsuario();//Instância DelegaUsuario.

                                        //Chama o controlador para efetuar a ação de inclusão.
                                        delUsu.acoes(acaoForm, usu, cli, null);

                                        this.atualizaLista();
                                        this.limpaCampos();
                                    } else if (jcboTipoUsuario.getSelectedItem() == "Funcionário") {
                                        Cargo car = new Cargo();//Instância Cargo.
                                        fun = new Funcionario(usu, car, pf, pj);//Instância Funcionario.
                                        delUsu = new DelegaUsuario();//Instância do controlador.

                                        //Chama o controlador para efetuar a ação de inclusão.
                                        delUsu.acoes(acaoForm, usu, null, fun);

                                        this.atualizaLista();
                                        this.limpaCampos();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, pf.getResposta());
                                }

                                //Se for Pessoa Jurídica...
                            } else {
                                mail = new Email(jtxtEmail.getText(), "default");
                                tel = new Telefone("default", jftxtTelefone.getText(), "default");
                                end = new Endereco();
                                pj = new PessoaJuridica(jftxtPessoa.getText(), jftxtIE.getText(), jtxtRamo.getText(), jtxtNome.getText(), mail, tel, end);
                                pf = null;

                                //Valida os dados de uma pessoa jurídica.
                                if (pj.validadPessoaJuridica()) {
                                    if (jcboTipoUsuario.getSelectedItem() == "Cliente") {
                                        cli = new Cliente(usu, pf, pj);//Instância Cliente.
                                        delUsu = new DelegaUsuario();

                                        //Chama o controlador para efetuar a ação de inclusão.
                                        delUsu.acoes(acaoForm, usu, cli, null);

                                        this.atualizaLista();
                                        this.limpaCampos();
                                    } else if (jcboTipoUsuario.getSelectedItem() == "Funcionário") {
                                        Cargo car = new Cargo();//Instância Cargo.
                                        fun = new Funcionario(usu, car, pf, pj);//Instância Funcionario.
                                        delUsu = new DelegaUsuario();//Instância do controlador.

                                        //Chama o controlador para efetuar a ação de inclusão.
                                        delUsu.acoes(acaoForm, usu, null, fun);
                                        this.atualizaLista();
                                        this.limpaCampos();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, pj.getResposta());
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Informe se é pessoa física ou pessoa jurídica!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, usu.getResposta());
                    }
                    break;

                case "altear":
                    break;

            }

//            int tipoUsuario = 0;
//            int tipoPessoa = 0;
//
//            if (jcboTipoUsuario.getSelectedItem() == "Cliente") {
//                tipoUsuario = 1;
//            } else if (jcboTipoUsuario.getSelectedItem() == "Funcionário") {
//                tipoUsuario = 2;
//            }
//
//            if (jrbtCpf.isSelected()) {
//                tipoPessoa = 1;
//            } else if (jrbtCnpj.isSelected()) {
//                tipoPessoa = 2;
//            }
//
//            if (jtxtId.getText().equals("")) {
//
//                String nome = jtxtNome.getText();
//                String email = jtxtEmail.getText();
//                String telefone = jftxtTelefone.getText();
//
//                Nivel niv = new Nivel();
//                ArrayList<Nivel> nivLista = this.recuperaNiveis();
//                niv = nivLista.get(jcboNivel.getSelectedIndex());
//
//                //Usuário.
//                String id = jtxtId.getText();
//                String login = jtxtLogin.getText();
//                String senha = jpwfSenha.getText();
//                int nivel = niv.getId();
//
//                String acao = "incluir";
//
//                Nivel nivUsu = new Nivel(niv.getId());
//                Usuario usu = new Usuario(nivUsu, login, senha);
//
//                Email mail = new Email(email, "default");
//                Telefone tel = new Telefone("default", telefone, "default");
//                Endereco end = new Endereco();
//
//                String identPes = jftxtPessoa.getText();
//                String ramo = jtxtRamo.getText();
//
//                //Finalização do Usuário.                        
//                DelegaUsuario delUsu = new DelegaUsuario();
//                PessoaFisica pf;
//                PessoaJuridica pj;
//
//                boolean statusPessoaFJ = false;
//
//                if (tipoPessoa == 1) {
//                    pf = new PessoaFisica(identPes, ramo, nome, mail, tel, end);
//
////                            if (pf.validaCPF()) {
////                                JOptionPane.showMessageDialog(null, "Certo!");
////                            } else {
////                                JOptionPane.showMessageDialog(null, "Errado!");
////                            }
//                    pj = null;
//                } else {
//                    pf = null;
//
//                    String ie = jftxtIE.getText();
//                    pj = new PessoaJuridica(identPes, ie, ramo, nome, mail, tel, end);
//                }
//
////                        if (tipoUsuario == 1) {
////                            Cliente cli = new Cliente(usu, pf, pj);
////                            delUsu.acoes(acao, usu, cli, null);
////                        } else {
////                            Cargo car = new Cargo();
////                            Funcionario fun = new Funcionario(usu, car, pf, pj);
////                            delUsu.acoes(acao, usu, null, fun);
////                        }
//                this.atualizaLista();
//                this.limpaCampos();
//                //JOptionPane.showMessageDialog(null, login + "\n" + senha + "\n" + niv.getId());
//
//            } else {
////                    String acao = "alterar";
////
////                    if (login.isEmpty() || senha.equals("") || nivel < 1) {
////                        JOptionPane.showMessageDialog(null, "Opss... Você deve preencher todos os campos!");
////                    } else {
////
////                        DelegaUsuario delUsu = new DelegaUsuario();
////                        Nivel nivUsu = new Nivel(niv.getId());
////                        Usuario usu = new Usuario(Integer.parseInt(id), nivUsu, login, senha);
////
////                        try {
////                            delUsu.acoes(acao, usu, null, null);
////                        } catch (SQLException ex) {
////                            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
////                        }
////
////                        try {
////                            this.atualizaLista();
////                        } catch (SQLException ex) {
////                            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
////                        }
////                        this.desabilitaCampos(false);
////                        this.limpaCampos();
////                    }
//            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSalvarActionPerformed

    /**
     * <b>jtbListaMouseClicked Evento de seleção da linha</b>
     * Método responsável por selecionar a linha e exibi-lá nos campos para o
     * usuário.
     *
     */
    private void jtbListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbListaMouseClicked
        // TODO add your handling code here:
        int linhaSelecionada = jtbLista.getSelectedRow();

        if (linhaSelecionada >= 0) {
            //this.controlaBotoes(1);
            Usuario usu = getUsuarioLinhaTabela(linhaSelecionada);
            if (usu != null) {
                jtxtId.setText(String.valueOf(usu.getId()));
                jtxtLogin.setText(usu.getUsuarioDescricao());
            }
        }

        this.desabilitaCampos(true);
    }//GEN-LAST:event_jtbListaMouseClicked

    private void jcboTipoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcboTipoUsuarioMouseClicked
    }//GEN-LAST:event_jcboTipoUsuarioMouseClicked

    private void jcboTipoUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcboTipoUsuarioMousePressed

    }//GEN-LAST:event_jcboTipoUsuarioMousePressed

    private void jcboTipoUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcboTipoUsuarioMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboTipoUsuarioMouseReleased

    private void jcboTipoUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcboTipoUsuarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboTipoUsuarioKeyPressed

    private void jcboTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboTipoUsuarioActionPerformed
        if (this.jcboTipoUsuario.getSelectedItem().equals("Cliente")) {
            this.jcboNivel.setSelectedItem("Cliente");
        }
    }//GEN-LAST:event_jcboTipoUsuarioActionPerformed

    private void jcboNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboNivelActionPerformed
        if (this.jcboNivel.getSelectedItem().equals("Cliente")) {
            this.jcboTipoUsuario.setSelectedItem("Cliente");
        } else {
            this.jcboTipoUsuario.setSelectedItem("Funcionário");
        }
    }//GEN-LAST:event_jcboNivelActionPerformed

    private void jbtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExcluirActionPerformed

        String pessoaFJ = jftxtPessoa.getText().replace(".", "").replace("-", "");

        JOptionPane.showMessageDialog(null, pessoaFJ);

    }//GEN-LAST:event_jbtnExcluirActionPerformed

    private void jftxtPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jftxtPessoaActionPerformed

    }//GEN-LAST:event_jftxtPessoaActionPerformed

    private void jftxtPessoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jftxtPessoaMouseClicked
        if (!jrbtCpf.isSelected() && !jrbtCnpj.isSelected()) {
            JOptionPane.showMessageDialog(null, "Informe o tipo de pessoa!");
        }
    }//GEN-LAST:event_jftxtPessoaMouseClicked

    private void jrbtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtCpfActionPerformed
        try {
            this.formataTipoPessoa(1);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jrbtCpfActionPerformed

    private void jrbtCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtCnpjActionPerformed
        try {
            this.formataTipoPessoa(2);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jrbtCnpjActionPerformed

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
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    try {
                        new UsuarioView().setVisible(true);
                    } catch (ParseException ex) {
                        Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngPessoa;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnExcluir;
    private javax.swing.JButton jbtnListar;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JComboBox<String> jcboNivel;
    private javax.swing.JComboBox<String> jcboTipoUsuario;
    private javax.swing.JFormattedTextField jftxtIE;
    private javax.swing.JFormattedTextField jftxtPessoa;
    private javax.swing.JFormattedTextField jftxtTelefone;
    private javax.swing.JInternalFrame jifrmUsuario;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblIE;
    private javax.swing.JLabel jlblId;
    private javax.swing.JLabel jlblLogin;
    private javax.swing.JLabel jlblNivel;
    private javax.swing.JLabel jlblNome;
    private javax.swing.JLabel jlblPessoa;
    private javax.swing.JLabel jlblRamo;
    private javax.swing.JLabel jlblSenha;
    private javax.swing.JLabel jlblTelefone;
    private javax.swing.JLabel jlblTipoUsuario;
    private javax.swing.JPasswordField jpwfSenha;
    private javax.swing.JRadioButton jrbtCnpj;
    private javax.swing.JRadioButton jrbtCpf;
    private javax.swing.JTable jtbLista;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtId;
    private javax.swing.JTextField jtxtLogin;
    private javax.swing.JTextField jtxtNome;
    private javax.swing.JTextField jtxtRamo;
    // End of variables declaration//GEN-END:variables
}
