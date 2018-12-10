package br.com.helpdesk.view.cadastro;

import br.com.helpdesk.controller.DelegaChamado;
import br.com.helpdesk.dal.ClienteDAO;
import br.com.helpdesk.dal.EquipamentoDAO;
import br.com.helpdesk.dal.FuncionarioDAO;
import br.com.helpdesk.dal.OcorrenciaDAO;
import br.com.helpdesk.model.Chamado;
import br.com.helpdesk.model.Cliente;
import br.com.helpdesk.model.Equipamento;
import br.com.helpdesk.model.Funcionario;
import br.com.helpdesk.model.Ocorrencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Conexao [VIEW] Classe de interface e interação com o usuário.
 *
 * @author Ricardo Guntzell
 */
public class ChamadoView extends javax.swing.JFrame {

    /**
     * Creates new form NivelView
     *
     * @throws java.sql.SQLException
     */
    public ChamadoView() throws SQLException {
        initComponents();

        //Inicializa o campo de código sempre desabilitado.
        jtfId.setEnabled(false);
        jcboSituacao.setEnabled(false);

        //Lista a tabela de Níveis.
        this.atualizaLista();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.montaCBOCliente();
        this.montaCBOFuncionario();
        this.montaCBOOcorrencias();
        this.montaCBOEquipamentos();
    }

    private ArrayList<Cliente> recuperaClientes() throws SQLException {
        ArrayList<Cliente> cliLista = new ClienteDAO(null).lista();

        return cliLista;
    }

    private ArrayList<Funcionario> recuperaFuncionarios() throws SQLException {
        ArrayList<Funcionario> funLista = new FuncionarioDAO(null).lista();

        return funLista;
    }

    private ArrayList<Ocorrencia> recuperaOcorrencias() throws SQLException {
        ArrayList<Ocorrencia> ocoLista = new OcorrenciaDAO().lista();

        return ocoLista;
    }

    private ArrayList<Equipamento> recuperaEquipamentos() throws SQLException {

        ArrayList<Equipamento> equipLista = new EquipamentoDAO().lista();

        return equipLista;
    }

    private void montaCBOCliente() throws SQLException {
        Cliente cli = new Cliente();
        int indice = 0;

        ArrayList<Cliente> cliList = this.recuperaClientes();

        for (indice = 0; indice < cliList.size(); indice++) {
            cli = cliList.get(indice);
            jcboCliente.addItem(String.valueOf(cli.getNome()));
        }
    }

    private void montaCBOFuncionario() throws SQLException {
        Funcionario fun = new Funcionario();
        int indice = 0;

        ArrayList<Funcionario> funList = this.recuperaFuncionarios();

        for (indice = 0; indice < funList.size(); indice++) {
            fun = funList.get(indice);
            jcboFuncionario.addItem(String.valueOf(fun.getNome()));
        }
    }

    private void montaCBOOcorrencias() throws SQLException {
        Ocorrencia oco = new Ocorrencia();
        int indice = 0;

        ArrayList<Ocorrencia> ocoList = this.recuperaOcorrencias();

        for (indice = 0; indice < ocoList.size(); indice++) {
            oco = ocoList.get(indice);
            jcboOcorrencia.addItem(String.valueOf(oco.getOcorrenciaDescricao()));
        }
    }

    private void montaCBOEquipamentos() throws SQLException {
        Equipamento equip = new Equipamento();

        int indice = 0;

        ArrayList<Equipamento> equipList = this.recuperaEquipamentos();

        for (indice = 0; indice < equipList.size(); indice++) {
            equip = equipList.get(indice);
            jcboEquipamento.addItem(String.valueOf(equip.getEquipamentoDescricao()));
        }
    }

    /**
     * <b>atualizaLista</b>
     * Método responsável por atualizar a listagem dos níveis conforme
     * manipulação do usuário.
     */
    private void atualizaLista() throws SQLException {
        Chamado cha = new Chamado();
        String acao = "listar";

        //Carrega a listagem de Níveis em ArrayList.
        DelegaChamado delCha = new DelegaChamado();
        ArrayList<Chamado> chaLista = delCha.acoes(acao, null);

        montaTabela(chaLista);
    }

    /**
     * <b>limpaCampos</b>
     * Método responsável por limpar os campos quando o botão de "Cancelar" é
     * acionado.
     */
    private void limpaCampos() {
        jtfId.setText("");
        jtxtaProblema.setText("");

        jcboOcorrencia.setEnabled(true);
        jcboEquipamento.setEnabled(true);
        jcboCliente.setEnabled(true);
        jcboFuncionario.setEnabled(true);

        jcboOcorrencia.setSelectedIndex(0);
        jcboEquipamento.setSelectedIndex(0);
        jcboCliente.setSelectedIndex(0);
        jcboFuncionario.setSelectedIndex(0);
        jcboSituacao.setSelectedIndex(0);

        jbtnSalvar.setText("Salvar");
    }

    /**
     * <b>montaTabela</b>
     * Método responsável por construir o jTable
     *
     * @param nivMT (List)(Nivel) Uma lista de Niveis.
     */
    private void montaTabela(List<Chamado> chaMT) {

        //Objeto responsável por criar o jTable.
        DefaultTableModel dtb = (DefaultTableModel) jtbLista.getModel();

        //Zera o índice do Objeto.
        dtb.setRowCount(0);

        //Percorre o array e preenche o jTable com cada atributo do (object) Nivel.
        for (int indice = 0; indice < chaMT.size(); indice++) {
            Chamado cha = chaMT.get(indice);
            dtb.addRow(new Object[]{
                cha.getId(),
                cha.getOcorrencia().getOcorrenciaDescricao(),
                cha.getEquipamento().getEquipamentoDescricao(),
                cha.getCliente().getNome(),
                cha.getFuncionario().getNome(),
                cha.getChamadoDescricao(),
                cha.getChamadoSituacao()
            });
        }
    }

    /**
     * <b>getNivelLinhaTabela</b>
     * Método responsável por recuperar os valores da linha seleciona do jTable.
     *
     * @param linha (int) índice da linha a ser selecionada.
     * @return (object) Nivel.
     */
    public Chamado getChamadoLinhaTabela(int linha) {

        //Caso o valor seja negativo ou seja maior que o total de linhas.
        if (linha < 0 || linha > jtbLista.getRowCount()) {
            return null;
        }

        //Objeto responsável por criar o jTable.
        DefaultTableModel dtm = (DefaultTableModel) jtbLista.getModel();

        Chamado cha = new Chamado();

        //Percorre o jTable e seta os atributos do (object) Nivel conforme valores encontrados
        //na lista.
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    cha.setId((int) dtm.getValueAt(linha, i));
                    break;
                case 1:
                    Ocorrencia oco = new Ocorrencia(String.valueOf(dtm.getValueAt(linha, i)));
                    cha.setOcorrencia(oco);
                    break;
                case 2:
                    Equipamento equip = new Equipamento(String.valueOf(dtm.getValueAt(linha, i)));
                    cha.setEquipamento(equip);
                    break;
                case 3:
                    Cliente cli = new Cliente(String.valueOf(dtm.getValueAt(linha, i)));
                    cha.setCliente(cli);
                    break;
                case 4:
                    Funcionario fun = new Funcionario(String.valueOf(dtm.getValueAt(linha, i)));
                    cha.setFuncionario(fun);
                    break;
                case 5:
                    cha.setChamadoDescricao((String) dtm.getValueAt(linha, i));
                    break;
                case 6:
                    cha.setChamadoSituacao((String) dtm.getValueAt(linha, i));
                    break;
            }
        }
        return cha;
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
        jbtnListar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbLista = new javax.swing.JTable();
        jbtnSalvar = new javax.swing.JButton();
        jcboOcorrencia = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcboEquipamento = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jcboFuncionario = new javax.swing.JComboBox<>();
        jcboCliente = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtaProblema = new javax.swing.JTextArea();
        jlblId1 = new javax.swing.JLabel();
        jcboSituacao = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nivel");

        jlblId.setText("Código:");

        jLabel2.setText("Ocorrência:");

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
                "Código", "Ocorrencia", "Equipamento", "Cliente", "Responsável", "Problema", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
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

        jbtnSalvar.setText("Salvar");
        jbtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalvarActionPerformed(evt);
            }
        });

        jcboOcorrencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboOcorrenciaActionPerformed(evt);
            }
        });

        jLabel4.setText("Equipamento:");

        jLabel5.setText("Cliente:");

        jcboEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboEquipamentoActionPerformed(evt);
            }
        });

        jLabel6.setText("Responsável:");

        jcboFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboFuncionarioActionPerformed(evt);
            }
        });

        jcboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboClienteActionPerformed(evt);
            }
        });

        jtxtaProblema.setColumns(20);
        jtxtaProblema.setRows(5);
        jScrollPane2.setViewportView(jtxtaProblema);

        jlblId1.setText("Descrição do Problema:");

        jcboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aberto", "Fechado" }));
        jcboSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboSituacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNivelLayout = new javax.swing.GroupLayout(jpnNivel);
        jpnNivel.setLayout(jpnNivelLayout);
        jpnNivelLayout.setHorizontalGroup(
            jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNivelLayout.createSequentialGroup()
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNivelLayout.createSequentialGroup()
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpnNivelLayout.createSequentialGroup()
                                .addComponent(jbtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpnNivelLayout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnNivelLayout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcboEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnNivelLayout.createSequentialGroup()
                                    .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlblId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jcboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jcboOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(58, 58, 58)
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnNivelLayout.createSequentialGroup()
                                .addComponent(jlblId1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jcboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNivelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnNivelLayout.setVerticalGroup(
            jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNivelLayout.createSequentialGroup()
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNivelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblId)
                            .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNivelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblId1)
                            .addComponent(jcboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnNivelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jcboEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jcboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jcboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnNivelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jcboOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jcboSituacao.setEnabled(true);

        int linhaSelecionada = jtbLista.getSelectedRow();

        if (linhaSelecionada >= 0) {
            //this.controlaBotoes(1);
            Chamado cha = getChamadoLinhaTabela(linhaSelecionada);
            if (cha != null) {
                jcboOcorrencia.setEnabled(false);
                jcboEquipamento.setEnabled(false);
                jcboCliente.setEnabled(false);
                jcboFuncionario.setEnabled(false);

                jtfId.setText(String.valueOf(cha.getId()));
                jcboOcorrencia.setSelectedItem(cha.getOcorrencia().getOcorrenciaDescricao());
                jcboEquipamento.setSelectedItem(cha.getEquipamento().getEquipamentoDescricao());
                jtxtaProblema.setText(String.valueOf(cha.getChamadoDescricao()));

                System.out.println(cha.getChamadoSituacao());

                if (cha.getChamadoSituacao() == "S") {
                    jcboSituacao.setSelectedItem("Aberto");
                } else {
                    jcboSituacao.setSelectedItem("Fechado");
                }
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
        String problema = jtxtaProblema.getText();
        String situacao = "";
        if (jcboSituacao.getSelectedItem().equals("Aberto")) {
            situacao = "S";
        } else {
            situacao = "N";
        }

        if (!jtxtaProblema.getText().isEmpty()) {
            if (id.equals("")) {
                String acao = "incluir";

                //Bloco para recuperar o cliente via formulário.
                Cliente cliRec = new Cliente();

                ArrayList<Cliente> cliLista = null;
                try {
                    cliLista = this.recuperaClientes();
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                cliRec = cliLista.get(jcboCliente.getSelectedIndex());
                //Fim do bloco.

                //Bloco para recuperar o Funcionario via formulário.
                Funcionario funRec = new Funcionario();

                ArrayList<Funcionario> funLista = null;
                try {
                    funLista = this.recuperaFuncionarios();
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                funRec = funLista.get(jcboFuncionario.getSelectedIndex());
                //Fim do bloco.

                //Bloco para recuperar o Ocorrencia via formulário.
                Ocorrencia ocoRec = new Ocorrencia();

                ArrayList<Ocorrencia> ocoLista = null;
                try {
                    ocoLista = this.recuperaOcorrencias();
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                ocoRec = ocoLista.get(jcboOcorrencia.getSelectedIndex());
                //Fim do bloco.

                //Bloco para recuperar o Equipamento via formulário.
                Equipamento equipRec = new Equipamento();

                ArrayList<Equipamento> equipLista = null;
                try {
                    equipLista = this.recuperaEquipamentos();
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                equipRec = equipLista.get(jcboEquipamento.getSelectedIndex());
                //Fim do bloco.

                DelegaChamado delCha = new DelegaChamado();
                Chamado cha = new Chamado(
                        ocoRec.getId(),
                        equipRec.getId(),
                        cliRec.getId(),
                        funRec.getCodigo(),
                        problema,
                        situacao
                );

                try {
                    delCha.acoes(acao, cha);
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    this.atualizaLista();
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.limpaCampos();

            } else {
                String acao = "alterar";

                DelegaChamado delCha = new DelegaChamado();
                Chamado cha = new Chamado(
                        Integer.parseInt(id),
                        situacao,
                        problema
                );

                try {
                    delCha.acoes(acao, cha);
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    this.atualizaLista();
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.limpaCampos();

            }
        }else{
            JOptionPane.showMessageDialog(null, "Informe problema relatado pelo cliente!");
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
            Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnListarActionPerformed

    /**
     * <b>jbtnCancelarActionPerformed Evento para limpar os campos.</b>
     *
     */
    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // TODO add your handling code here:
        this.limpaCampos();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jcboOcorrenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboOcorrenciaActionPerformed

    }//GEN-LAST:event_jcboOcorrenciaActionPerformed

    private void jcboEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboEquipamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboEquipamentoActionPerformed

    private void jcboFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboFuncionarioActionPerformed

    private void jcboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboClienteActionPerformed

    private void jcboSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboSituacaoActionPerformed

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
            java.util.logging.Logger.getLogger(ChamadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChamadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChamadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChamadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    new ChamadoView().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ChamadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnListar;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JComboBox<String> jcboCliente;
    private javax.swing.JComboBox<String> jcboEquipamento;
    private javax.swing.JComboBox<String> jcboFuncionario;
    private javax.swing.JComboBox<String> jcboOcorrencia;
    private javax.swing.JComboBox<String> jcboSituacao;
    private javax.swing.JLabel jlblId;
    private javax.swing.JLabel jlblId1;
    private javax.swing.JPanel jpnNivel;
    private javax.swing.JTable jtbLista;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextArea jtxtaProblema;
    // End of variables declaration//GEN-END:variables
}
