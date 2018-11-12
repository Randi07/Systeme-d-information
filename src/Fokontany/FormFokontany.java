/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fokontany;

import Commune.FormCommune;
import Connexion.ConnexionBase;
import OtherFolder.FormMenuPrincipale;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JAHYA
 */
public class FormFokontany extends javax.swing.JFrame {

    int i, val, nbligne;    
    private Object bouton2;
    private Object bouton;
    private ResultSet rs;
    public Connection con;
    public Statement st;
    public String ID_DIST;
    public String DesignCom;
    public String SuperficieCom;
    public String HistoriqueCom;
    public String DesignDist;
    public String SuperficieDist;
    public String HistoriqueDist;
    //données public a faire passé
    public String NbFokotany = "";
    public String ID_FKT="";
    public String ID_COM;
    //fin de données a passer
    public ResultSet res;
    String cont;

//    public Object dt;
    public DefaultTableModel dtm;


    public FormFokontany() {
        initComponents();
        this.setTitle("LES FOKONTANY  DE LA REGION D'AMORON'I MANIA");
       }
    public void first()
    {
        try {
        AfficherFokontany();
        } catch (Exception ex) {
            Logger.getLogger(FormFokontany.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtDesignCom.setText(DesignCom);
        txtSuperficieCom.setText(SuperficieCom);
        txtHistoriqueCom.setText(HistoriqueCom);
        txtNbFonkotanyCom.setText(NbFokotany);
    }

 

    public void ClickTableFokontany() {
        int ligneSelect = TableFokontany.getSelectedRow();
        ID_FKT = String.valueOf(TableFokontany.getValueAt(ligneSelect, 0));
        txtNomFokontany.setText(String.valueOf(TableFokontany.getValueAt(ligneSelect, 1)));
        txtHistorique.setText(String.valueOf(TableFokontany.getValueAt(ligneSelect, 2)));
        txtSuperficie.setText(String.valueOf(TableFokontany.getValueAt(ligneSelect, 3)));

    }

    private void ImprimerTableFokontany() {
        java.text.MessageFormat head = new java.text.MessageFormat("Les fokontany de ''"+ DesignCom+"''");
        java.text.MessageFormat end = new java.text.MessageFormat("Page {0,number,integer}");
        try {
            TableFokontany.print(JTable.PrintMode.FIT_WIDTH, head, end);
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la procedure d'impression: " + e.getMessage());
        }
    }

    private void AfficherFokontany() throws Exception {

        try {
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("FOKONTANY");
            dt.addColumn("HISTORIQUE");
            dt.addColumn("SUPERFICIE");

            TableFokontany.setModel(dt);
            rs = c.executeQuery("Select * From fokontany where SUBSTRING(ID_FKT, 1, 6) like '"+ ID_COM +"' order by ID_FKT ");
            GestionFokontany gcom = new GestionFokontany();
            //rs = gcom.getAll();
            while (rs.next()) {
                ID_FKT = rs.getString("ID_FKT");
                String NOM_FKT = rs.getString("NOM_FKT");
                String HISTORIQUE_FKT = rs.getString("HISTORIQUE_FKT");
                String SUPERFICIE = rs.getString("SUPERFICIE");

                Object[] FOKONTANY = {ID_FKT,NOM_FKT, HISTORIQUE_FKT, SUPERFICIE};
                dt.addRow(FOKONTANY);
               
            }
             EffacerChampFokontany();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormFokontany.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void AjoutFokontany() {
        String NOM_FKT = txtNomFokontany.getText();
        String HISTORIQUE_FKT = txtHistorique.getText();
        String SUPERFICIE = txtSuperficie.getText();

        ClassFokontany com = new ClassFokontany(ID_COM, NOM_FKT, HISTORIQUE_FKT, SUPERFICIE);
        if (txtNomFokontany.getText().equals("") || txtSuperficie.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment ajouter?","Ajout en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionFokontany gcom = new GestionFokontany();
                gcom.insert(com);
                EffacerChampFokontany();
                AfficherFokontany();
                JOptionPane.showMessageDialog(null, " Insertion Avec Succes du Fokontany   " + NOM_FKT);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur:"+ex.getMessage());

            }}else{ JOptionPane.showMessageDialog(null, "Annulation de la suppression du Fokontany " + NOM_FKT + "! ");
}
        }
    }

    public void ModificationFokontany() {
        String NOM_FKT = txtNomFokontany.getText();
        String HISTORIQUE_FKT = txtHistorique.getText();
        String SUPERFICIE = txtSuperficie.getText();
        ClassFokontany com = new ClassFokontany(ID_FKT, NOM_FKT, HISTORIQUE_FKT, SUPERFICIE);
        if (txtNomFokontany.getText().equals("") || txtSuperficie.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier?","Modification en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionFokontany gcom = new GestionFokontany();
                gcom.update(ID_FKT, com);
                EffacerChampFokontany();
                AfficherFokontany();
                JOptionPane.showMessageDialog(null, " Modification reussi du Fokontany " + NOM_FKT);

            } catch (Exception ex) {
                
                JOptionPane.showMessageDialog(null, "Erreur:"+ex.getMessage());


            }}else{JOptionPane.showMessageDialog(null, "Annulation de la modification du Fokontany " + NOM_FKT + "! ");}
        }
    }

    private void SupprimerFokontany() {
        String NOM_FKT = txtNomFokontany.getText();
        if (ID_FKT.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ pour effectuer cette suppression");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer?","Suppression en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionFokontany gv = new GestionFokontany();
                gv.Supprimer(ID_FKT);
                EffacerChampFokontany();
                AfficherFokontany();
                JOptionPane.showMessageDialog(null, " Le Fokontany " + NOM_FKT + "  de la commune "+ ID_FKT +" a  ete supprime avec succes! ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur:"+ex.getMessage());
            }}else{JOptionPane.showMessageDialog(null, "Annulation de la suppression du Fokontany " + NOM_FKT + "! ");
}
        }


    }

    public void EffacerChampFokontany() {

        txtNomFokontany.setText("");
        txtHistorique.setText("");
        txtSuperficie.setText("");
        txtRecherche.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableFokontany = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        txtComboRecherche1 = new javax.swing.JTextField();
        btnComboRecherche1 = new javax.swing.JButton();
        btnImprimer1 = new javax.swing.JButton();
        btnAjout1 = new javax.swing.JButton();
        btnActualiser1 = new javax.swing.JButton();
        btnModifier1 = new javax.swing.JButton();
        btnSupprimer1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnFermer = new javax.swing.JButton();
        btnAjout = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnImprimer = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        txtRecherche = new javax.swing.JTextField();
        btnComboRecherche2 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        txtComboRecherche3 = new javax.swing.JTextField();
        btnComboRecherche3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtNomFokontany = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistorique = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSuperficie = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtDesignCom = new javax.swing.JLabel();
        txtNbFonkotanyCom = new javax.swing.JLabel();
        txtHistoriqueCom = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSuperficieCom = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(970, 600));
        jPanel1.setName(""); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(970, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("LISTE DES DONNEES ENREGISTREES IMPRIMABLE"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableFokontany.setAutoCreateRowSorter(true);
        TableFokontany.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableFokontany.setDragEnabled(true);
        TableFokontany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableFokontanyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableFokontany);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 930, 130));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 970, 180));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("ENREGISTREMENT DE L'OPERATION ET RECHERCHE"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("OPERATIONS LIEE SUR LE FOKONTANY"));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtComboRecherche1.setToolTipText("Saisir votre recherche ici puis lancer a l'aide de la boutton RECHERCHER");
        jPanel13.add(txtComboRecherche1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 190, 30));

        btnComboRecherche1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche1.setText("RECHERCHER");
        jPanel13.add(btnComboRecherche1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 150, 30));

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 440, 60));

        btnImprimer1.setText("SANITAIRE");
        btnImprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimer1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnImprimer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 190, 30));

        btnAjout1.setText("POPULATION");
        btnAjout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjout1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnAjout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 30));

        btnActualiser1.setText("SCOLAIRE");
        btnActualiser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiser1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnActualiser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 190, 30));

        btnModifier1.setText("SPORTIVE");
        btnModifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifier1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnModifier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, 30));

        btnSupprimer1.setText("SECURITE");
        btnSupprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimer1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnSupprimer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 93, 190, 30));

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 440, 130));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("OPERATION SUR L'ENREGISTREMENT"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFermer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/fermer-icon.png"))); // NOI18N
        btnFermer.setText("RETOUR");
        btnFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFermerActionPerformed(evt);
            }
        });
        jPanel9.add(btnFermer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 190, 30));

        btnAjout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/add.gif"))); // NOI18N
        btnAjout.setText("INSERTION");
        btnAjout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutActionPerformed(evt);
            }
        });
        jPanel9.add(btnAjout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 190, 30));

        btnModifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/A_PencilDyn_Sm_N.png"))); // NOI18N
        btnModifier.setText("MODIFICATION");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });
        jPanel9.add(btnModifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 30));

        btnSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconSupprimer.png"))); // NOI18N
        btnSupprimer.setText("SUPPRESSION");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });
        jPanel9.add(btnSupprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 190, -1));

        btnImprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer-icon.png"))); // NOI18N
        btnImprimer.setText("IMPRESSION");
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimerActionPerformed(evt);
            }
        });
        jPanel9.add(btnImprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 190, 30));

        btnActualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/refresh.png"))); // NOI18N
        btnActualiser.setText("ACTUALISER");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });
        jPanel9.add(btnActualiser, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 190, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/30.jpg"))); // NOI18N
        jButton2.setText("ACCEUIL");
        jButton2.setActionCommand("DETAILS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 190, 30));

        jPanel10.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 440, 180));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRecherche.setToolTipText("Saisir votre recherche ici puis lancer a l'aide de la boutton RECHERCHER");
        jPanel14.add(txtRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 210, 30));

        btnComboRecherche2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche2.setText("RECHERCHER");
        btnComboRecherche2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboRecherche2ActionPerformed(evt);
            }
        });
        jPanel14.add(btnComboRecherche2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 150, 30));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtComboRecherche3.setToolTipText("Saisir votre recherche ici puis lancer a l'aide de la boutton RECHERCHER");
        jPanel15.add(txtComboRecherche3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 190, 30));

        btnComboRecherche3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche3.setText("RECHERCHER");
        jPanel15.add(btnComboRecherche3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 150, 30));

        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 440, 60));

        jPanel10.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 440, 60));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 460, 400));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMATION FOKONTANY"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNomFokontany.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomFokontanyKeyReleased(evt);
            }
        });
        jPanel5.add(txtNomFokontany, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 350, -1));

        txtHistorique.setColumns(20);
        txtHistorique.setRows(5);
        jScrollPane2.setViewportView(txtHistorique);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 350, 190));

        jLabel3.setText("Historique");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 86, -1));

        jLabel2.setText("Nom");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 86, -1));

        txtSuperficie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSuperficieKeyReleased(evt);
            }
        });
        jPanel5.add(txtSuperficie, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 350, -1));

        jLabel4.setText("Superficie");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 86, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 510, 400));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMATION DE SON COMMUNE"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Historique:");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 60, -1));

        jLabel6.setText("Désignation:");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, -1));

        jLabel21.setText("Nombre fokontany:");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 110, -1));

        txtDesignCom.setText("....");
        jPanel6.add(txtDesignCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 190, -1));

        txtNbFonkotanyCom.setText(".....");
        jPanel6.add(txtNbFonkotanyCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 150, -1));

        txtHistoriqueCom.setText(".....");
        txtHistoriqueCom.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel6.add(txtHistoriqueCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 260, 50));

        jLabel8.setText("Superficie:");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        txtSuperficieCom.setText("......");
        jPanel6.add(txtSuperficieCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 120, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 660));
        jPanel1.getAccessibleContext().setAccessibleDescription("");

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFermerActionPerformed
        try {
                ConnexionBase c = new ConnexionBase();
                rs = c.executeQuery("select count(*) as NbCommune from commune order by ID_COM ");
                if (rs.next()) {
                    String NbCommune = rs.getString("NbCommune");
                    rs.close();
                    rs = c.executeQuery("select count(*) as NbFokotany from fokontany where SUBSTRING(ID_FKT, 1, 3) like '"+ ID_DIST +"' order by ID_FKT ");
                    if(rs.next()){
            String NbFokotany = rs.getString("NbFokotany");
            FormCommune a = new FormCommune();
            //a.ID_FKT =TableCommune.getValueAt(ligneSelect, 0).toString();
            a.DesignDist = DesignDist;
            a.HistoriqueDist = HistoriqueDist;
            a.NbFokotany = NbFokotany;
            a.NbCommune = NbCommune;
            a.ID_DIST = ID_DIST;
            a.SuperficieDist = SuperficieDist;
            a.first();
            a.setVisible(true);
            this.dispose();
                }}
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        
    }//GEN-LAST:event_btnFermerActionPerformed

    private void btnAjoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutActionPerformed
        AjoutFokontany();
    }//GEN-LAST:event_btnAjoutActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        ModificationFokontany();
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        SupprimerFokontany();
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
        ImprimerTableFokontany();
    }//GEN-LAST:event_btnImprimerActionPerformed

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        try {
            AfficherFokontany();
        } catch (Exception ex) {
            Logger.getLogger(FormFokontany.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualiserActionPerformed

    private void TableFokontanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableFokontanyMouseClicked
        ClickTableFokontany();
    }//GEN-LAST:event_TableFokontanyMouseClicked

    private void btnComboRecherche2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboRecherche2ActionPerformed
        // TODO add your handling code here:
        try {
            String champ = txtRecherche.getText();
            
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("CODE FOKONTANY");
            dt.addColumn("NOM FOKONTANY");
            dt.addColumn("HISTORIQUE");
            dt.addColumn("SUPERFICIE");
            TableFokontany.setModel(dt);
            rs = c.executeQuery("SELECT * FROM fokontany where SUBSTRING(ID_FKT,1,6) = " + ID_COM + " AND ID_FKT like '%" + champ + "%' or NOM_FKT like '%" + champ + "%' or HISTORIQUE_FKT like '%" + champ + "%' or SUPERFICIE like '%" + champ + "%'");
            while (rs.next()) {
                String CODE = rs.getString("ID_FKT");
                String NOM_COM = rs.getString("NOM_FKT");
                String HISTORIQUE_COM = rs.getString("HISTORIQUE_FKT");
                String SUPERFICIE = rs.getString("SUPERFICIE");

                Object[] Fokotany = {CODE, NOM_COM, HISTORIQUE_COM, SUPERFICIE};
                dt.addRow(Fokotany);
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnComboRecherche2ActionPerformed

    private void btnImprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimer1ActionPerformed
        // TODO add your handling code here:
        int ligneSelect = TableFokontany.getSelectedRow();
        if (ligneSelect==-1) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ");
        } else {
            FormSanitaire a = new FormSanitaire();
            a.ID_FKT =TableFokontany.getValueAt(ligneSelect, 0).toString();
            a.DesignFkt =TableFokontany.getValueAt(ligneSelect, 1).toString();
            a.DesignCom = DesignCom;
            a.HistoriqueCom = HistoriqueCom;
            a.ID_COM = ID_COM;
            a.NbFokotany = NbFokotany;
            a.SuperficieDist = SuperficieDist;
            a.HistoriqueDist = HistoriqueDist;
            a.DesignDist = DesignDist;
            a.ID_DIST = ID_DIST;
            a.SuperficieCom = SuperficieCom;
            a.first();
            a.setVisible(true);
            this.dispose();            
        }
    }//GEN-LAST:event_btnImprimer1ActionPerformed

    private void btnAjout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjout1ActionPerformed
        // TODO add your handling code here:
        int ligneSelect = TableFokontany.getSelectedRow();
        if (ligneSelect==-1) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ");
        } else {
            FormPopulation1 a = new FormPopulation1();
            a.ID_FKT =TableFokontany.getValueAt(ligneSelect, 0).toString();
            a.DesignFkt =TableFokontany.getValueAt(ligneSelect, 1).toString();
            a.DesignCom = DesignCom;
            a.HistoriqueCom = HistoriqueCom;
            a.ID_COM = ID_COM;
            a.NbFokotany = NbFokotany;
            a.SuperficieDist = SuperficieDist;
            a.HistoriqueDist = HistoriqueDist;
            a.DesignDist = DesignDist;
            a.ID_DIST = ID_DIST;
            a.SuperficieCom = SuperficieCom;
            a.first();
            a.setVisible(true);
            this.dispose();            
        }
    }//GEN-LAST:event_btnAjout1ActionPerformed

    private void btnActualiser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiser1ActionPerformed
        // TODO add your handling code here:
        int ligneSelect = TableFokontany.getSelectedRow();
        if (ligneSelect==-1) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ");
        } else {
            FormScolaire a = new FormScolaire();
            a.ID_FKT =TableFokontany.getValueAt(ligneSelect, 0).toString();
            a.DesignFkt =TableFokontany.getValueAt(ligneSelect, 1).toString();
            a.DesignCom = DesignCom;
            a.HistoriqueCom = HistoriqueCom;
            a.ID_COM = ID_COM;
            a.NbFokotany = NbFokotany;
            a.SuperficieDist = SuperficieDist;
            a.HistoriqueDist = HistoriqueDist;
            a.DesignDist = DesignDist;
            a.ID_DIST = ID_DIST;
            a.SuperficieCom = SuperficieCom;
            a.first();
            a.setVisible(true);
            this.dispose();            
        }
    }//GEN-LAST:event_btnActualiser1ActionPerformed

    private void btnModifier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifier1ActionPerformed
        // TODO add your handling code here:
        int ligneSelect = TableFokontany.getSelectedRow();
        if (ligneSelect==-1) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ");
        } else {
            FormSportive a = new FormSportive();
            a.ID_FKT =TableFokontany.getValueAt(ligneSelect, 0).toString();
            a.DesignFkt =TableFokontany.getValueAt(ligneSelect, 1).toString();
            a.DesignCom = DesignCom;
            a.HistoriqueCom = HistoriqueCom;
            a.ID_COM = ID_COM;
            a.NbFokotany = NbFokotany;
            a.SuperficieDist = SuperficieDist;
            a.HistoriqueDist = HistoriqueDist;
            a.DesignDist = DesignDist;
            a.ID_DIST = ID_DIST;
            a.SuperficieCom = SuperficieCom;
            a.first();
            a.setVisible(true);
            this.dispose(); 
        }
    }//GEN-LAST:event_btnModifier1ActionPerformed

    private void btnSupprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimer1ActionPerformed
        // TODO add your handling code here:
         int ligneSelect = TableFokontany.getSelectedRow();
        if (ligneSelect==-1) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ");
        } else {
            FormSecurite a = new FormSecurite();
            a.ID_FKT =TableFokontany.getValueAt(ligneSelect, 0).toString();
            a.DesignFkt =TableFokontany.getValueAt(ligneSelect, 1).toString();
            a.DesignCom = DesignCom;
            a.HistoriqueCom = HistoriqueCom;
            a.ID_COM = ID_COM;
            a.NbFokotany = NbFokotany;
            a.SuperficieDist = SuperficieDist;
            a.HistoriqueDist = HistoriqueDist;
            a.DesignDist = DesignDist;
            a.ID_DIST = ID_DIST;
            a.SuperficieCom = SuperficieCom;
            a.first();
            a.setVisible(true);
            this.dispose(); 
        }
    }//GEN-LAST:event_btnSupprimer1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FormMenuPrincipale a = new FormMenuPrincipale(); 
        a.setVisible(true);        
        this.dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtSuperficieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSuperficieKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtSuperficie.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 10;
        if (ChampCodeDoss.matches("[0-9]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre inferieur ou egale a 10 chiffres, merci!!");
            }
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre un nombre entier, merci!!");
        }
        txtSuperficie.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtSuperficieKeyReleased

    private void txtNomFokontanyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomFokontanyKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtNomFokontany.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 24;
        if (txtNomFokontany.getText().matches("[A-Z,a-z]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet doit etre inferieur ou egale a 24 caracteres, merci!!");
            }
            txtNomFokontany.setText(ChampCodeDoss);
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            txtNomFokontany.setText(ChampCodeDoss);
            JOptionPane.showMessageDialog(null, "Les caracteres qu'on peut utiliser dans ce champ sont: l'alphabet majuscule et minuscule merci!!");

        }
    }//GEN-LAST:event_txtNomFokontanyKeyReleased

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
            java.util.logging.Logger.getLogger(FormFokontany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFokontany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFokontany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFokontany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFokontany().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableFokontany;
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnActualiser1;
    private javax.swing.JButton btnAjout;
    private javax.swing.JButton btnAjout1;
    private javax.swing.JButton btnComboRecherche1;
    private javax.swing.JButton btnComboRecherche2;
    private javax.swing.JButton btnComboRecherche3;
    private javax.swing.JButton btnFermer;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnImprimer1;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnModifier1;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnSupprimer1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtComboRecherche1;
    private javax.swing.JTextField txtComboRecherche3;
    private javax.swing.JLabel txtDesignCom;
    private javax.swing.JTextArea txtHistorique;
    private javax.swing.JLabel txtHistoriqueCom;
    private javax.swing.JLabel txtNbFonkotanyCom;
    private javax.swing.JTextField txtNomFokontany;
    private javax.swing.JTextField txtRecherche;
    private javax.swing.JTextField txtSuperficie;
    private javax.swing.JLabel txtSuperficieCom;
    // End of variables declaration//GEN-END:variables
}
