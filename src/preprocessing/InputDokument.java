package preprocessing;
import Utilitas.Bobot;
import Utilitas.NaiveBayes;
import Utilitas.Sinonim;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class InputDokument extends javax.swing.JFrame {
    double grafikNaive=0;
    double grafikQuery=0;
    connect koneksi;
    Object rowData[];
    DefaultTableModel TabelNaive;
    DefaultTableModel Sinonime;
    ListSelectionModel listSelectionModel;
    String choosertitle = "";
    String dokumen;
    ResultSet Data_latih;
    ResultSet Query_Expansion;
    ResultSet insert;
    ResultSet Data_Uji;

    boolean stopword, stemming, caseFoldng;
    ResultSet Data_latih_Positif;
    ResultSet Data_latih_Negatif;

    public InputDokument() throws SQLException, IOException {
        initComponents();
        koneksi = new connect();
        Query_Expansion = koneksi.data("SELECT * FROM query_Expansion;");
        Data_latih = koneksi.data("SELECT * FROM data_latih;");
        Data_Uji = koneksi.data("SELECT * FROM data_uji;");

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtable_data_latih = new javax.swing.JTable();
        dokument = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Tambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel_Uji = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        Tabel_QueryExpansion = new javax.swing.JTable();
        dokument1 = new javax.swing.JLabel();
        Text_uji = new javax.swing.JTextField();
        Tambah_uji = new javax.swing.JButton();
        Delete_uji = new javax.swing.JButton();
        Update_uji = new javax.swing.JButton();
        sinonime = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Stopword = new javax.swing.JCheckBox();
        gg = new javax.swing.JLabel();
        caseFolding = new javax.swing.JCheckBox();
        gg2 = new javax.swing.JLabel();
        stemming2 = new javax.swing.JCheckBox();
        dokument2 = new javax.swing.JLabel();
        akurasiNB = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        panelGlass1 = new usu.widget.glass.PanelGlass();
        akurasiQE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        buttonGlass1 = new usu.widget.ButtonGlass();
        Preprocesing = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        JumNegatif = new javax.swing.JTextField();
        jumPositif = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TNaive_Bayes = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TQueryExpansion = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        query = new usu.widget.ButtonGlass();
        jLabel1 = new javax.swing.JLabel();
        jkategori = new javax.swing.JTextField();
        InputQuery = new javax.swing.JTextField();
        QueryExpansion = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        ujiQE = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        Area_kalimat = new javax.swing.JTextArea();
        chart = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(null);

        jScrollPane1.setViewportView(Jtable_data_latih);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(220, 40, 990, 190);

        dokument.setText("Dokument      :");
        jPanel2.add(dokument);
        dokument.setBounds(30, 40, 80, 20);

        jLabel5.setText("Kategori        :");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(30, 90, 80, 20);
        jPanel2.add(jTextField1);
        jTextField1.setBounds(120, 90, 80, 20);

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField4);
        jTextField4.setBounds(120, 40, 80, 20);

        Update.setText("Update");
        jPanel2.add(Update);
        Update.setBounds(120, 170, 80, 23);

        Delete.setText("Delete");
        jPanel2.add(Delete);
        Delete.setBounds(120, 130, 80, 23);

        Tambah.setText("Add");
        jPanel2.add(Tambah);
        Tambah.setBounds(30, 130, 80, 23);

        Tabel_Uji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dokumen Uji"
            }
        ));
        jScrollPane2.setViewportView(Tabel_Uji);
        if (Tabel_Uji.getColumnModel().getColumnCount() > 0) {
            Tabel_Uji.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(220, 260, 990, 180);

        Tabel_QueryExpansion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dokumen"
            }
        ));
        jScrollPane6.setViewportView(Tabel_QueryExpansion);
        if (Tabel_QueryExpansion.getColumnModel().getColumnCount() > 0) {
            Tabel_QueryExpansion.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel2.add(jScrollPane6);
        jScrollPane6.setBounds(220, 470, 990, 170);

        dokument1.setText("Dokument      :");
        jPanel2.add(dokument1);
        dokument1.setBounds(30, 260, 80, 20);

        Text_uji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_ujiActionPerformed(evt);
            }
        });
        jPanel2.add(Text_uji);
        Text_uji.setBounds(120, 260, 80, 20);

        Tambah_uji.setText("Add");
        jPanel2.add(Tambah_uji);
        Tambah_uji.setBounds(30, 300, 80, 23);

        Delete_uji.setText("Delete");
        jPanel2.add(Delete_uji);
        Delete_uji.setBounds(120, 300, 80, 23);

        Update_uji.setText("Update");
        jPanel2.add(Update_uji);
        Update_uji.setBounds(120, 340, 80, 23);

        sinonime.setText("sinonime");
        sinonime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinonimeActionPerformed(evt);
            }
        });
        jPanel2.add(sinonime);
        sinonime.setBounds(33, 470, 160, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/preprocessing/back.jpg"))); // NOI18N
        jPanel2.add(jLabel2);
        jLabel2.setBounds(0, -30, 1360, 730);

        jTabbedPane1.addTab("Input Dokument", jPanel2);

        jPanel1.setLayout(null);

        Stopword.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        Stopword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopwordActionPerformed(evt);
            }
        });
        jPanel1.add(Stopword);
        Stopword.setBounds(170, 40, 21, 21);

        gg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gg.setText("Stemming     :");
        jPanel1.add(gg);
        gg.setBounds(40, 100, 110, 20);

        caseFolding.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        caseFolding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseFoldingActionPerformed(evt);
            }
        });
        jPanel1.add(caseFolding);
        caseFolding.setBounds(170, 70, 20, 20);

        gg2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gg2.setText("Casefolding    :");
        jPanel1.add(gg2);
        gg2.setBounds(40, 70, 100, 20);

        stemming2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        stemming2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stemming2ActionPerformed(evt);
            }
        });
        jPanel1.add(stemming2);
        stemming2.setBounds(170, 100, 21, 21);

        dokument2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dokument2.setText("Stopword      :");
        jPanel1.add(dokument2);
        dokument2.setBounds(40, 40, 100, 20);
        jPanel1.add(akurasiNB);
        akurasiNB.setBounds(1040, 270, 180, 50);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Akurasi");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(930, 270, 130, 50);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Akurasi");

        buttonGlass1.setText("GRAFIK");
        buttonGlass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGlass1ActionPerformed(evt);
            }
        });

        Preprocesing.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Preprocesing.setText("proses");
        Preprocesing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreprocesingActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("LATIH POSITIF");

        jumPositif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumPositifActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("LATIH NEGATIF");

        javax.swing.GroupLayout panelGlass1Layout = new javax.swing.GroupLayout(panelGlass1);
        panelGlass1.setLayout(panelGlass1Layout);
        panelGlass1Layout.setHorizontalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGlass1Layout.createSequentialGroup()
                .addContainerGap(907, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(akurasiQE, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelGlass1Layout.createSequentialGroup()
                        .addComponent(Preprocesing, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonGlass1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGlass1Layout.createSequentialGroup()
                        .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jumPositif, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(JumNegatif))
                        .addGap(3, 3, 3)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGlass1Layout.setVerticalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumPositif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JumNegatif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Preprocesing, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGlass1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(akurasiQE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(panelGlass1);
        panelGlass1.setBounds(0, 10, 1270, 630);

        TNaive_Bayes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dokumen", "P(P|Dokumen)", "P(N|Dokumen)", "Kategori", "Kategori"
            }
        ));
        jScrollPane5.setViewportView(TNaive_Bayes);
        if (TNaive_Bayes.getColumnModel().getColumnCount() > 0) {
            TNaive_Bayes.getColumnModel().getColumn(0).setMaxWidth(30);
            TNaive_Bayes.getColumnModel().getColumn(4).setMaxWidth(100);
            TNaive_Bayes.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jPanel1.add(jScrollPane5);
        jScrollPane5.setBounds(210, 40, 1010, 220);

        TQueryExpansion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dokumen", "P(P|Dkumen)", "P(N|Dokumen)", "Kategori", "kategori"
            }
        ));
        jScrollPane3.setViewportView(TQueryExpansion);
        if (TQueryExpansion.getColumnModel().getColumnCount() > 0) {
            TQueryExpansion.getColumnModel().getColumn(0).setMaxWidth(30);
            TQueryExpansion.getColumnModel().getColumn(4).setMaxWidth(100);
            TQueryExpansion.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(210, 350, 1010, 220);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Synonyme of Recocnition Query Expansion");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(510, 310, 400, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("MULTINOMIAL NAIVE BAYES");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(560, 10, 270, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/preprocessing/back.jpg"))); // NOI18N
        jPanel1.add(background);
        background.setBounds(0, -10, 1366, 690);

        jTabbedPane1.addTab("Preprocessing", jPanel1);

        jInternalFrame1.setVisible(true);
        jInternalFrame1.getContentPane().setLayout(null);

        query.setText("QUERY");
        query.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(query);
        query.setBounds(170, 70, 120, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("KATEGORI");
        jInternalFrame1.getContentPane().add(jLabel1);
        jLabel1.setBounds(960, 70, 80, 30);
        jInternalFrame1.getContentPane().add(jkategori);
        jkategori.setBounds(1060, 70, 110, 30);
        jInternalFrame1.getContentPane().add(InputQuery);
        InputQuery.setBounds(300, 70, 650, 30);

        QueryExpansion.setText("submit");
        QueryExpansion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QueryExpansionActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(QueryExpansion);
        QueryExpansion.setBounds(170, 590, 80, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Query Expansion");
        jInternalFrame1.getContentPane().add(jLabel11);
        jLabel11.setBounds(170, 30, 120, 30);
        jInternalFrame1.getContentPane().add(ujiQE);
        ujiQE.setBounds(300, 39, 20, 21);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        jInternalFrame1.getContentPane().add(jComboBox1);
        jComboBox1.setBounds(330, 40, 40, 20);

        Area_kalimat.setColumns(20);
        Area_kalimat.setRows(5);
        jScrollPane4.setViewportView(Area_kalimat);

        jInternalFrame1.getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(300, 110, 650, 96);

        chart.setBackground(new java.awt.Color(0, 102, 255));
        chart.setForeground(new java.awt.Color(204, 255, 255));
        chart.setLayout(new java.awt.BorderLayout());
        jInternalFrame1.getContentPane().add(chart);
        chart.setBounds(160, 230, 1020, 350);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/preprocessing/back.jpg"))); // NOI18N
        jInternalFrame1.getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1340, 650);

        jTabbedPane1.addTab("Pengujian", jInternalFrame1);

        jPanel3.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1363, 839));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PreprocesingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreprocesingActionPerformed
        connect koneksi = new connect();
        ResultSet Data_latih_Positif = koneksi.data("SELECT * FROM data_latih where kategori='positif' limit " + jumPositif.getText() + " ;");
        ResultSet Data_latih_Negatif = koneksi.data("SELECT * FROM data_latih where kategori='negatif' limit " + JumNegatif.getText() + " ;");
        ResultSet Data_Uji = koneksi.data("SELECT * FROM data_uji;");
        Object rowData1[] = new Object[6];
        TabelNaive = (DefaultTableModel) TNaive_Bayes.getModel();
        int akurasi = 1;
        NaiveBayes n;
        try {
            n = new NaiveBayes(Data_latih_Positif, Data_latih_Negatif, Data_Uji);
            n.preprocessing(stopword, caseFoldng, stemming);
            n.Klasifikasi();
            for (int i = 0; i < n.getDok().getDok().size(); i++) {
                rowData1[0] = n.getDok().getDok().get(i).getIdDok();
                rowData1[1] = n.getDok().getDok().get(i).getText();
                rowData1[2] = n.getP_D().get(i);
                rowData1[3] = n.getN_D().get(i);
                rowData1[4] = n.getHasil().get(i);
                rowData1[5] = n.getDok().getDok().get(i).getKat();
                TabelNaive.addRow(rowData1);
                if (n.getDok().getDok().get(i).getKat().equalsIgnoreCase(n.getHasil().get(i))) {
                    akurasi++;
                } else {
            //        System.out.println(" tidak sama " + i);
                }
            }
            akurasiNB.setText(String.valueOf(((double) akurasi / (double) n.getDok().getDok().size()) * 100 + " %"));
            System.out.println("akurasi" + akurasi);
            System.out.println("akurasi : " + ((double) akurasi / (double) n.getDok().getDok().size()) * 100 + " %");
            grafikNaive =(double)akurasi/(double) n.getDok().getDok().size() * 100 ;
        } catch (SQLException ex) {
            Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        }
        //---------------------------------------------------------------------------------
        //===============================================================================
        //--------------------------------------------------------------------------------
        ResultSet QE_Uji;
        ResultSet QE_Positif = koneksi.data("SELECT * FROM data_latih where kategori='positif' limit " + jumPositif.getText() + " ;");
        if (stopword== caseFoldng==stemming==true) {
                QE_Uji = koneksi.data("SELECT * FROM query_expansion;");
        } else {QE_Uji = koneksi.data("SELECT * FROM qe_nonpre;");}
        ResultSet QE_Negatif = koneksi.data("SELECT * FROM data_latih where kategori='negatif' limit " + JumNegatif.getText() + " ;");
        rowData = new Object[6];
        Sinonime = (DefaultTableModel) TQueryExpansion.getModel();
        int akurasi2 = 1;
        NaiveBayes QE;
        try {
            QE = new NaiveBayes(QE_Positif, QE_Negatif, QE_Uji);
            QE.preprocessing(stopword, caseFoldng, stemming);
            QE.Klasifikasi();
            for (int i = 0; i < QE.getDok().getDok().size(); i++) {
                rowData[0] = QE.getDok().getDok().get(i).getIdDok();
                rowData[1] = QE.getDok().getDok().get(i).getText();
                rowData[2] = QE.getP_D().get(i);
                rowData[3] = QE.getN_D().get(i);
                rowData[4] = QE.getHasil().get(i);
                rowData[5] = QE.getDok().getDok().get(i).getKat();
                Sinonime.addRow(rowData);
                if (QE.getDok().getDok().get(i).getKat().equalsIgnoreCase(QE.getHasil().get(i))) {
                    akurasi2++;
                } else {
 //                   System.out.println(" tidak sama " + i);
                }
            }
            akurasiQE.setText(String.valueOf(((double) akurasi2 / (double) QE.getDok().getDok().size()) * 100 + " %"));
            System.out.println("akurasi" + akurasi2);
            System.out.println("akurasi : " + ((double) akurasi2 / (double) QE.getDok().getDok().size()) * 100 + " %");
            grafikQuery=((double) akurasi2 / (double) QE.getDok().getDok().size()) * 100;
        } catch (SQLException ex) {
            Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PreprocesingActionPerformed

    private void stemming2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stemming2ActionPerformed
        if (stemming2.isSelected() == true) {
            stemming = true;
        } else {
            stemming = false;
        }
    }//GEN-LAST:event_stemming2ActionPerformed

    private void caseFoldingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseFoldingActionPerformed
        if (caseFolding.isSelected() == true) {
            caseFoldng = true;
        } else {
            caseFoldng = false;
        }
    }//GEN-LAST:event_caseFoldingActionPerformed

    private void StopwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopwordActionPerformed
        if (Stopword.isSelected() == true) {
            stopword = true;
        } else {
            stopword = false;
        }
    }//GEN-LAST:event_StopwordActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void Text_ujiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_ujiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_ujiActionPerformed

    private void QueryExpansionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QueryExpansionActionPerformed

        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        barChartData.setValue(grafikNaive, "Persenasi Akurasi", "Naive Bayes");
        barChartData.setValue(grafikQuery, "Persenasi Akurasi", "Query Expansion");
       
        JFreeChart BarChart = ChartFactory.createBarChart("AKURASI", "Jumlah Data", "Persenasi Akurasi", barChartData, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot barCHRT = BarChart.getCategoryPlot();
        barCHRT.setRangeGridlinePaint(Color.ORANGE);

        ChartPanel barPanel = new ChartPanel(BarChart);
        chart.removeAll();
        chart.add(barPanel, BorderLayout.CENTER);
        chart.validate();

    }//GEN-LAST:event_QueryExpansionActionPerformed

    private void sinonimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinonimeActionPerformed
        DefaultTableModel sinonim = (DefaultTableModel) Tabel_QueryExpansion.getModel();
        Object data[] = new Object[3];
        try {
            Sinonim json = new Sinonim();
//            json.termUnix(koneksi);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_sinonimeActionPerformed

    private void queryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryActionPerformed
//    connect koneksi= new connect();
        try {
            koneksi.insert("DELETE FROM query");
            koneksi.insert("INSERT INTO query (dokumen,kategori) VALUES ('" + InputQuery.getText() + "','positif')");
        } catch (SQLException ex) {
            Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet Data_latih_Positif = koneksi.data("SELECT * FROM data_latih where kategori='positif' limit 50  ;");
        ResultSet Data_latih_Negatif = koneksi.data("SELECT * FROM data_latih where kategori='negatif' limit 50 ;");
        ResultSet Data_query = koneksi.data("SELECT * FROM query;");
        ResultSet Data_sinonim = koneksi.data("SELECT * FROM sinonim;");

        try {
           
               if (ujiQE.isSelected() == true) {
                  HashMap<String, ArrayList<String>> hasSin = new HashMap<String, ArrayList<String>>();
            while (Data_sinonim.next()) {
                StringTokenizer tjl = new StringTokenizer(Data_sinonim.getString("sinonim"));
                int n = tjl.countTokens();
                ArrayList<String> kata = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    kata.add(tjl.nextToken());
                }
                hasSin.put(Data_sinonim.getString("kata"), kata);
            }
            ArrayList<String> kata2 = new ArrayList<>();
            while (Data_query.next()) {
                String kalimat = "";
                StringTokenizer uji_query = new StringTokenizer(Data_query.getString("dokumen"));
                int n = uji_query.countTokens();
                for (int i = 0; i < n; i++) {
                    String data = uji_query.nextToken();
                    if (hasSin.containsKey(data)) {
                        kalimat =kalimat+" "+ data;
                        try {

                            for (int j = 0; j < 10; j++) {
                                System.out.println(" --" + hasSin.get(data).get(j));
                                kalimat += hasSin.get(data).get(j) + " ";
                            }
                        } catch (Exception e) {
                        }
                    }
                    else{ kalimat=kalimat+" "+data;
                    
                    }
                  }
                System.out.println("" + kalimat);
                try {
                    koneksi.insert("DELETE FROM query_uji");
                    koneksi.insert("INSERT INTO query_uji (dokumen,kategori) VALUES ('" + kalimat + "','positif')");
                    
                } catch (SQLException ex) {
                 Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        }        ResultSet Data_query_uji = koneksi.data("SELECT * FROM query_uji;");
                 NaiveBayes naive = new NaiveBayes(Data_latih_Positif, Data_latih_Negatif, Data_query_uji);
                 naive.preprocessing(true, true, true);
                 naive.Klasifikasi();
                 jkategori.setText(String.valueOf(naive.getHasil().get(0)));
                 Area_kalimat.removeAll();
                 Area_kalimat.setText(kalimat);
            }   
                } else {
                   NaiveBayes naive = new NaiveBayes(Data_latih_Positif, Data_latih_Negatif, Data_query);
                   naive.preprocessing(true, true, true);
                   naive.Klasifikasi();
                    jkategori.setText(String.valueOf(naive.getHasil().get(0)));
                    Area_kalimat.removeAll();
                    Area_kalimat.setText(String.valueOf(naive.getDok().getDok().get(0).getText()));
                }

            
           

        } catch (SQLException ex) {
            Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputDokument.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_queryActionPerformed

    private void buttonGlass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGlass1ActionPerformed
//        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
//        barChartData.setValue(10, "Persenasi Akurasi", "10-10");
//        barChartData.setValue(20, "Persenasi Akurasi", "20-20");
//        barChartData.setValue(30, "Persenasi Akurasi", "30-30");
//        
//        
//        JFreeChart BarChart = ChartFactory.createBarChart("AKURASI", "Jumlah Data", "Persenasi Akurasi", barChartData, PlotOrientation.VERTICAL, false, true, false);
//        CategoryPlot barCHRT = BarChart.getCategoryPlot();
//        barCHRT.setRangeGridlinePaint(Color.ORANGE);
//
//        ChartPanel barPanel = new ChartPanel(BarChart);
//        chart.removeAll();
//        chart.add(barPanel, BorderLayout.CENTER);
//        chart.validate();
//
//        
    }//GEN-LAST:event_buttonGlass1ActionPerformed

    private void jumPositifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumPositifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumPositifActionPerformed
    public static void main(String args[]) throws SQLException, IOException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputDokument kkk;
                try {
                    kkk = new InputDokument();
                    kkk.setVisible(true);
                    kkk.setSize(1366, 768);
                    kkk.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    kkk.tabel_query_expansion();
                    kkk.tabel_Data_Latih();
                    kkk.tabel_Data_Uji();
                } catch (Exception e) {
                }
            }
        });
    }
    //--------------------TABEL QUERY EXPANSION-------------------------------

    public void tabel_query_expansion() {
        DefaultTableModel query = (DefaultTableModel) Tabel_QueryExpansion.getModel();
        rowData = new Object[16];
        try {
            while (Query_Expansion.next()) {
                rowData[0] = Query_Expansion.getInt("id");
                rowData[1] = Query_Expansion.getString("dokumen");
                query.addRow(rowData);
            }
            Query_Expansion.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //--------------------------------------------------------------------
    public void tabel_Data_Latih() throws SQLException {
        DefaultTableModel T_latih = (DefaultTableModel) Jtable_data_latih.getModel();
        Data data = new Data();
        String nama []={"id","Dokumen Latih","Kategori"};
        T_latih.setDataVector(data.getDataLatih(), nama);
        Jtable_data_latih.setAutoResizeMode(Jtable_data_latih.AUTO_RESIZE_ALL_COLUMNS);
        Jtable_data_latih.getColumnModel().getColumn(0).setMaxWidth(30);
        Jtable_data_latih.getColumnModel().getColumn(2).setMaxWidth(100);
           }

    public void tabel_Data_Uji() {
        DefaultTableModel tabelUji = (DefaultTableModel) Tabel_Uji.getModel();
        listSelectionModel = Tabel_Uji.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        Tabel_Uji.setSelectionModel(listSelectionModel);
        rowData = new Object[16];
        try {
            while (Data_Uji.next()) {
                rowData[0] = Data_Uji.getInt("id");
                rowData[1] = Data_Uji.getString("dokumen");
                tabelUji.addRow(rowData);
            }
            Data_Uji.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Area_kalimat;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Delete_uji;
    private javax.swing.JTextField InputQuery;
    private javax.swing.JTable Jtable_data_latih;
    private javax.swing.JTextField JumNegatif;
    private javax.swing.JButton Preprocesing;
    private javax.swing.JButton QueryExpansion;
    private javax.swing.JCheckBox Stopword;
    private javax.swing.JTable TNaive_Bayes;
    private javax.swing.JTable TQueryExpansion;
    private javax.swing.JTable Tabel_QueryExpansion;
    private javax.swing.JTable Tabel_Uji;
    private javax.swing.JButton Tambah;
    private javax.swing.JButton Tambah_uji;
    private javax.swing.JTextField Text_uji;
    private javax.swing.JButton Update;
    private javax.swing.JButton Update_uji;
    private javax.swing.JTextField akurasiNB;
    private javax.swing.JTextField akurasiQE;
    private javax.swing.JLabel background;
    private usu.widget.ButtonGlass buttonGlass1;
    private javax.swing.JCheckBox caseFolding;
    private javax.swing.JPanel chart;
    private javax.swing.JLabel dokument;
    private javax.swing.JLabel dokument1;
    private javax.swing.JLabel dokument2;
    private javax.swing.JLabel gg;
    private javax.swing.JLabel gg2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jkategori;
    private javax.swing.JTextField jumPositif;
    private usu.widget.glass.PanelGlass panelGlass1;
    private usu.widget.ButtonGlass query;
    private javax.swing.JButton sinonime;
    private javax.swing.JCheckBox stemming2;
    private javax.swing.JCheckBox ujiQE;
    // End of variables declaration//GEN-END:variables
}

class SharedListSelectionHandler implements ListSelectionListener {

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();
        System.out.println("Event for indexes "
                + firstIndex + " - " + lastIndex
                + "; isAdjusting is " + isAdjusting
                + "; selected indexes:");

        if (lsm.isSelectionEmpty()) {
            System.out.println(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    System.out.println(" -> " + i);
                }
            }
        }
        //System.out.println(newline);
        //output.setCaretPosition(output.getDocument().getLength());
    }

}
