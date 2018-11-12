/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commune;

import Connexion.ConnexionBase;
import District.FormDistrict;
import Fokontany.FormFokontany;
import OtherFolder.FormMenuPrincipale;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JAHYA
 */
public class FormCommune extends javax.swing.JFrame {

    private Object bouton2;
    private Object bouton;
    private ResultSet rs;
    public String ID_DIST;
    public String ID_COM;
    public String NbCommune;
    public String NbFokotany;
    public String SuperficieDist;
    public String HistoriqueDist;
    public String DesignDist;

    public FormCommune() {
        initComponents();
       
        this.setTitle("INFORMATION SUR LES COMMUNES DANS LA REGION D'AMORON'I MANIA");
               
    }
    public void first()
    {
        try {
        AfficherCommune();
        } catch (Exception ex) {
            Logger.getLogger(FormCommune.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtEDesign.setText(DesignDist);
        txtESuperficie.setText(SuperficieDist);
        txtEHistorique.setText(HistoriqueDist);
        txtENbCommune.setText(NbCommune);
        txtENbFokotany.setText(NbFokotany);
    }


    private void messageQuestion(String message) {
        int option = JOptionPane.showConfirmDialog(this, message, "message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.NO_OPTION) {
            Thread t = new Thread((Runnable) new PlayAnimation());
            t.start();
            bouton2.equals(false);
            bouton.equals(true);
        }
    }

    @SuppressWarnings("unchecked")

    public void ClickTableCommune() {
        int ligneSelect = TableCommune.getSelectedRow();
        ID_COM = String.valueOf(TableCommune.getValueAt(ligneSelect, 0));
        txtNomCom.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 1)));
        txtHistorique.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 2)));
        txtRN7.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 3)));
        txtRN35.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 4)));
        txtRN41.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 5)));
        txtDdist.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 6)));
        txtDreg.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 7)));
        txtSuperficie.setText(String.valueOf(TableCommune.getValueAt(ligneSelect, 8)));
//((JTextField) jDateRec.getDateEditor().getUiComponent()).setText(TableCommune.getValueAt(TableCommune.getSelectedRow(), 9).toString());

    }

    private void ImprimerTableCommune() {
        java.text.MessageFormat head = new java.text.MessageFormat("Les commune de ''"+ DesignDist +"''");
        java.text.MessageFormat end = new java.text.MessageFormat("Page {0,number,integer}");
        try {
            TableCommune.print(JTable.PrintMode.FIT_WIDTH, head, end);
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la procedure d'impression: " + e.getMessage());
        }
    }

    private void AfficherCommune() throws Exception {

        try {
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE COMMUNE");
            dt.addColumn("NOM COMMUNE");
            dt.addColumn("HISTORIQUE");
            dt.addColumn("DE RN7");
            dt.addColumn("DE RN35");
            dt.addColumn("DE RN41");
            dt.addColumn("DE DISTRICT");
            dt.addColumn("DE REGION");
            dt.addColumn("SUPERFICIE");
            TableCommune.setModel(dt);
            rs = c.executeQuery("Select * From commune WHERE SUBSTRING(ID_COM,1,3) like '" + ID_DIST + "' ORDER BY ID_COM");
            while (rs.next()) {
                ID_COM = rs.getString("ID_COM");
                String NOM_COM = rs.getString("NOM_COM");
                String HISTORIQUE_COM = rs.getString("HISTORIQUE_COM");
                String DRN7 = rs.getString("DRN7");
                String DRN35 = rs.getString("DRN35");
                String DRN41 = rs.getString("DRN41");
                String DDIST = rs.getString("DDIST");
                String DREG = rs.getString("DREG");
                String SUPERFICIE = rs.getString("SUPERFICIE");

                Object[] Commune = {ID_COM, NOM_COM, HISTORIQUE_COM, DRN7, DRN35, DRN41, DDIST, DREG, SUPERFICIE};
                dt.addRow(Commune);
            }
            
                EffacerChampCommune();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormCommune.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AjoutCommune() {
        String NOM_COM = txtNomCom.getText();
        String HISTORIQUE_COM = txtHistorique.getText();
        String DRN7 = txtRN7.getText();
        String DRN35 = txtRN35.getText();
        String DRN41 = txtRN41.getText();
        String DDIST = txtDdist.getText();
        String DREG = txtDreg.getText();
        String SUPERFICIE = txtSuperficie.getText();
                //((JTextField) (jDateRec.getDateEditor().getUiComponent())).getText();

        ClassCommune com = new ClassCommune(ID_DIST, NOM_COM, HISTORIQUE_COM, DRN7, DRN35, DRN41, DDIST, DREG, SUPERFICIE);
        if (txtNomCom.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment ajouter?","Ajout en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionCommune gcom = new GestionCommune();
                gcom.insert(com);
                EffacerChampCommune();
                JOptionPane.showMessageDialog(null, " Insertion Avec Succes de la commune   " + NOM_COM + "  ");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }}else{JOptionPane.showMessageDialog(null, "Annulation l'enregistrement de la commune " + NOM_COM + "! ");
}
        }
    }

    public void ModificationCommune() {
        
        String NOM_COM = txtNomCom.getText();
        String HISTORIQUE_COM = txtHistorique.getText();
        String DRN7 = txtRN7.getText();
        String DRN35 = txtRN35.getText();
        String DRN41 = txtRN41.getText();
        String DDIST = txtDdist.getText();
        String DREG = txtDreg.getText();
        String SUPERFICIE = txtSuperficie.getText();
                //((JTextField) (jDateRec.getDateEditor().getUiComponent())).getText();

        ClassCommune com = new ClassCommune(ID_COM, NOM_COM, HISTORIQUE_COM,DRN7, DRN35, DRN41, DDIST, DREG, SUPERFICIE);
        if (ID_COM.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier?","Modification en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionCommune gcom = new GestionCommune();
                gcom.update(ID_COM,com);;
                AfficherCommune();
                JOptionPane.showMessageDialog(null, " Modification reussi de la commune " + NOM_COM + "! ");

            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            }else{ JOptionPane.showMessageDialog(null, "Annulation du modification de la commune " + NOM_COM + "! ");
}
        }
    }

    private void SupprimerCommune() {
        String NOM_COM = txtNomCom.getText();
        
         if (ID_COM.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez Selectionner un champ a supprimer");
        } else {
             int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer?","Suppression en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
        try {
            GestionCommune gv = new GestionCommune();
            gv.Supprimer(ID_COM);
            AfficherCommune();
            JOptionPane.showMessageDialog(null, " La Commune " + NOM_COM + "  a ete supprime avec succes! ");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }}else{JOptionPane.showMessageDialog(null, "Annulation de la suppression de la commune " + NOM_COM + "! ");
}
    }
    }

    public void EffacerChampCommune() {

        ID_COM = "";
        txtNomCom.setText("");
        txtHistorique.setText("");
        txtRN7.setText("");
        txtRN35.setText("");
        txtRN41.setText("");
        txtDdist.setText("");
        txtDreg.setText("");
        txtRecherche.setText("");
        //((JTextField) jDateRec.getDateEditor().getUiComponent()).setText("");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCommune = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtRN7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtRN35 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtRN41 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDdist = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDreg = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtSuperficie = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtRecherche = new javax.swing.JTextField();
        btnComboRecherche = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnFermer = new javax.swing.JButton();
        btnAjout = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnImprimer = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtNomCom = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistorique = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEDesign = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtENbCommune = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtENbFokotany = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtESuperficie = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtEHistorique = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("LISTE DES DONNEES ENREGISTREES IMPRIMABLE"));
        jPanel7.setMinimumSize(new java.awt.Dimension(1280, 80));
        jPanel7.setName(""); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(1280, 80));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCommune.setAutoCreateRowSorter(true);
        TableCommune.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableCommune.setDragEnabled(true);
        TableCommune.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCommuneMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableCommune);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 870, 150));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 890, 180));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("A PROPOS DE LA COMMUNE"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("Distance de RN7");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 110, -1));

        txtRN7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRN7ActionPerformed(evt);
            }
        });
        txtRN7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRN7KeyReleased(evt);
            }
        });
        jPanel3.add(txtRN7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 130, -1));

        jLabel10.setText("Distance de RN35");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 110, -1));

        txtRN35.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRN35KeyReleased(evt);
            }
        });
        jPanel3.add(txtRN35, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 130, -1));

        jLabel11.setText("Distance de RN41");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, -1));

        txtRN41.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRN41KeyReleased(evt);
            }
        });
        jPanel3.add(txtRN41, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 130, -1));

        jLabel12.setText("Distance de District");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 110, -1));

        txtDdist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDdistKeyReleased(evt);
            }
        });
        jPanel3.add(txtDdist, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 130, -1));

        jLabel13.setText("Siege");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 90, 20));

        txtDreg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDregKeyReleased(evt);
            }
        });
        jPanel3.add(txtDreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 130, -1));

        jLabel14.setText("Superficie ");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, 20));

        txtSuperficie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSuperficieKeyReleased(evt);
            }
        });
        jPanel3.add(txtSuperficie, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 130, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 260, 270));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("ENREGISTREMENT DE L'OPERATION ET RECHERCHE"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRecherche.setToolTipText("Saisir votre recherche ici puis lancer a l'aide de la boutton RECHERCHER");
        jPanel12.add(txtRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 30));

        btnComboRecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche.setText("RECHERCHER");
        btnComboRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboRechercheActionPerformed(evt);
            }
        });
        jPanel12.add(btnComboRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 140, 30));

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 310, 60));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("OPERATION SUR L'ENREGISTREMENT"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFermer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/fermer-icon.png"))); // NOI18N
        btnFermer.setText("RETOUR");
        btnFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFermerActionPerformed(evt);
            }
        });
        jPanel9.add(btnFermer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 140, 30));

        btnAjout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/add.gif"))); // NOI18N
        btnAjout.setText("INSERTION");
        btnAjout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutActionPerformed(evt);
            }
        });
        jPanel9.add(btnAjout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 30));

        btnModifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/A_PencilDyn_Sm_N.png"))); // NOI18N
        btnModifier.setText("MODIFICATION");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });
        jPanel9.add(btnModifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 140, 30));

        btnSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconSupprimer.png"))); // NOI18N
        btnSupprimer.setText("SUPPRESSION");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });
        jPanel9.add(btnSupprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 140, -1));

        btnImprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer-icon.png"))); // NOI18N
        btnImprimer.setText("IMPRESSION");
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimerActionPerformed(evt);
            }
        });
        jPanel9.add(btnImprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 30));

        btnActualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/refresh.png"))); // NOI18N
        btnActualiser.setText("ACTUALISER");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });
        jPanel9.add(btnActualiser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/30.jpg"))); // NOI18N
        jButton1.setText("ACCEUIL");
        jButton1.setActionCommand("DETAILS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 140, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/about.png"))); // NOI18N
        jButton2.setText("OUVRIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 130, 30));

        jPanel10.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 310, 180));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 330, 270));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMATION COMMUNE"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNomCom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomComKeyReleased(evt);
            }
        });
        jPanel5.add(txtNomCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 190, -1));

        txtHistorique.setColumns(20);
        txtHistorique.setRows(5);
        jScrollPane2.setViewportView(txtHistorique);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 190, 190));

        jLabel3.setText("Historique");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 70, -1));

        jLabel2.setText("Nom");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 300, 270));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("A PROPOS DE SON DISTRICT"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Désignation:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtEDesign.setText(".....");
        jPanel4.add(txtEDesign, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 130, -1));

        jLabel5.setText("Nombre de commune:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        txtENbCommune.setText(".....");
        jPanel4.add(txtENbCommune, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 120, 20));

        jLabel6.setText("Nombre de fokotany:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        txtENbFokotany.setText(".....");
        jPanel4.add(txtENbFokotany, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jLabel8.setText("Superficie:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, 20));

        txtESuperficie.setText("......");
        jPanel4.add(txtESuperficie, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 100, 20));

        jLabel15.setText("Historique:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, -1));

        txtEHistorique.setText("....");
        txtEHistorique.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(txtEHistorique, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 320, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, -1));
        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFermerActionPerformed
        FormDistrict a =  new FormDistrict();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFermerActionPerformed

    private void btnAjoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutActionPerformed
        AjoutCommune();
    }//GEN-LAST:event_btnAjoutActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        ModificationCommune();
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        SupprimerCommune();
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
        ImprimerTableCommune();
    }//GEN-LAST:event_btnImprimerActionPerformed

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        try {
            AfficherCommune();
        } catch (Exception ex) {
            Logger.getLogger(FormCommune.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualiserActionPerformed

    private void TableCommuneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCommuneMouseClicked
        ClickTableCommune();
    }//GEN-LAST:event_TableCommuneMouseClicked

    private void btnComboRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboRechercheActionPerformed
        // TODO add your handling code here:
        try {
            String champ = txtRecherche.getText();
            
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("CODE COMMUNE");
            dt.addColumn("NOM COMMUNE");
            dt.addColumn("HISTORIQUE");
            dt.addColumn("DE RN7");
            dt.addColumn("DE RN35");
            dt.addColumn("DE RN41");
            dt.addColumn("DE DISTRICT");
            dt.addColumn("DE REGION");
            dt.addColumn("SUPERFICIE");
            TableCommune.setModel(dt);
            rs = c.executeQuery("SELECT * FROM commune where SUBSTRING(ID_COM,1,3) = " + ID_DIST + " AND ID_COM like '%" + champ + "%' or NOM_COM like '%" + champ + "%' or HISTORIQUE_COM like '%" + champ + "%' or SUPERFICIE like '%" + champ + "%'");
            while (rs.next()) {
                String CODE = rs.getString("ID_COM");
                String NOM_COM = rs.getString("NOM_COM");
                String HISTORIQUE_COM = rs.getString("HISTORIQUE_COM");
                String DRN7 = rs.getString("DRN7");
                String DRN35 = rs.getString("DRN35");
                String DRN41 = rs.getString("DRN41");
                String DDIST = rs.getString("DDIST");
                String DREG = rs.getString("DREG");
                String SUPERFICIE = rs.getString("SUPERFICIE");

                Object[] Commune = {CODE, NOM_COM, HISTORIQUE_COM, DRN7, DRN35, DRN41, DDIST, DREG, SUPERFICIE};
                dt.addRow(Commune);
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnComboRechercheActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FormMenuPrincipale a = new FormMenuPrincipale(); 
        a.setVisible(true);        
        this.dispose();
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int ligneSelect = TableCommune.getSelectedRow();
        if (ligneSelect!=-1) {
            ID_COM = String.valueOf(TableCommune.getValueAt(ligneSelect, 0));
            String DesignCom =  TableCommune.getValueAt(ligneSelect, 1).toString();
            String SuperficieCom =  TableCommune.getValueAt(ligneSelect, 8).toString();
            String HistoriqueCom = TableCommune.getValueAt(ligneSelect, 2).toString();
            try {
                ConnexionBase c = new ConnexionBase();
                rs = c.executeQuery("select count(*) as NbFokotany from fokontany where SUBSTRING(ID_FKT, 1, 6) like '"+ ID_COM +"'");
                    if(rs.next()){
            String NbFokotany = rs.getString("NbFokotany");
            FormFokontany a = new FormFokontany();
            //a.ID_FKT =TableCommune.getValueAt(ligneSelect, 0).toString();
            a.DesignCom = DesignCom;
            a.HistoriqueCom = HistoriqueCom;
            a.SuperficieCom = SuperficieCom + " km2";
            a.DesignDist = DesignDist;
            a.HistoriqueDist = HistoriqueDist;
            a.SuperficieDist = SuperficieDist;
            a.NbFokotany = NbFokotany;
            a.ID_DIST = ID_DIST;
            a.ID_COM = ID_COM;
            
            a.first();
            a.setVisible(true);
            this.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtRN7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRN7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRN7ActionPerformed

    private void txtRN7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRN7KeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtRN7.getText();
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
        txtRN7.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtRN7KeyReleased

    private void txtRN35KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRN35KeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtRN35.getText();
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
        txtRN35.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtRN35KeyReleased

    private void txtRN41KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRN41KeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtRN41.getText();
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
        txtRN41.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtRN41KeyReleased

    private void txtDdistKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDdistKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtDdist.getText();
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
        txtDdist.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtDdistKeyReleased

    private void txtDregKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDregKeyReleased
        // TODO add your handling code here:
         String ChampCodeDoss = txtDreg.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 24;
        if (txtDreg.getText().matches("[A-Z,a-z]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet doit etre inferieur ou egale a 24 caracteres, merci!!");
            }
            txtDreg.setText(ChampCodeDoss);
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            txtDreg.setText(ChampCodeDoss);
            JOptionPane.showMessageDialog(null, "Les caracteres qu'on peut utiliser dans ce champ sont: l'alphabet majuscule et minuscule merci!!");

        }
    }//GEN-LAST:event_txtDregKeyReleased

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

    private void txtNomComKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomComKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtNomCom.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 24;
        if (txtNomCom.getText().matches("[A-Z,a-z]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet doit etre inferieur ou egale a 24 caracteres, merci!!");
            }
            txtNomCom.setText(ChampCodeDoss);
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            txtNomCom.setText(ChampCodeDoss);
            JOptionPane.showMessageDialog(null, "Les caracteres qu'on peut utiliser dans ce champ sont: l'alphabet majuscule et minuscule merci!!");

        }
    }//GEN-LAST:event_txtNomComKeyReleased

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
            java.util.logging.Logger.getLogger(FormCommune.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCommune.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCommune.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCommune.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCommune().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableCommune;
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnAjout;
    private javax.swing.JButton btnComboRecherche;
    private javax.swing.JButton btnFermer;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtDdist;
    private javax.swing.JTextField txtDreg;
    private javax.swing.JLabel txtEDesign;
    private javax.swing.JLabel txtEHistorique;
    private javax.swing.JLabel txtENbCommune;
    private javax.swing.JLabel txtENbFokotany;
    private javax.swing.JLabel txtESuperficie;
    private javax.swing.JTextArea txtHistorique;
    private javax.swing.JTextField txtNomCom;
    private javax.swing.JTextField txtRN35;
    private javax.swing.JTextField txtRN41;
    private javax.swing.JTextField txtRN7;
    private javax.swing.JTextField txtRecherche;
    private javax.swing.JTextField txtSuperficie;
    // End of variables declaration//GEN-END:variables
}
