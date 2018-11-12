/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package District;

import Commune.FormCommune;
import Connexion.ConnexionBase;
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
public class FormDistrict extends javax.swing.JFrame {

    private ResultSet rs;
    private String ID_DIST;
    public FormDistrict() {
        initComponents();
        this.setTitle("INFORMATION SUR LES DISTRICTS DE LA REGION D'AMORON'I MANIA");
        try {
            AfficherDistrict();
        } catch (Exception ex) {
            Logger.getLogger(FormDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")

 /*   private void ComboCodeRegion() {
        try {
            Connection connexion = MaConnection.seconnecter();
            Statement instruction = connexion.createStatement();
            ResultSet res = instruction.executeQuery(" Select ID_REG from region ");
            while (res.next()) {
                String typ = res.getString("ID_REG");
                Object obj = (Object) typ;
                ComboReg.addItem(typ);
            }
        } catch (Exception e) {
        }

    }
*/

    public void ClickTableDistrict() {
        int ligneSelect = TableDistrict.getSelectedRow();
        txtCode.setText(String.valueOf(TableDistrict.getValueAt(ligneSelect, 0)));
        ID_DIST =  String.valueOf(TableDistrict.getValueAt(ligneSelect, 0));
        txtNomDist.setText(String.valueOf(TableDistrict.getValueAt(ligneSelect, 1)));
        txtHistorique.setText(String.valueOf(TableDistrict.getValueAt(ligneSelect, 2)));
        txtSuperficie.setText(String.valueOf(TableDistrict.getValueAt(ligneSelect, 3)));
        

    }

    private void ImprimerTableDistrict() {
        java.text.MessageFormat head = new java.text.MessageFormat("Les districts de la region Amoron''i Mania");
        java.text.MessageFormat end = new java.text.MessageFormat("Page {0,number,integer}");
        try {
            TableDistrict.print(JTable.PrintMode.FIT_WIDTH, head, end);
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la procedure d'impression: " + e.getMessage());
        }
    }

    private void AfficherDistrict() throws Exception {

        try {
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("DISTRICT");
            dt.addColumn("HISTORIQUE");
            dt.addColumn("SUPERFICIE");

            TableDistrict.setModel(dt);
            rs = c.executeQuery("Select * From district order by ID_DIST asc");
            GestionDistrict gcom = new GestionDistrict();
            rs = gcom.getAll();
            while (rs.next()) {
                String CODE = rs.getString("ID_DIST");
                String NOM_DIST = rs.getString("NOM_DIST");
                String HISTORIQUE_DIST = rs.getString("HISTORIQUE_DIST");
                String SUPERFICIE = rs.getString("SUPERFICIE");

                Object[] DISTRICT = {CODE, NOM_DIST, HISTORIQUE_DIST,SUPERFICIE};
                dt.addRow(DISTRICT);
                
            }
            EffacerChampDistrict();
            rs.close();
            rs = c.executeQuery("select count(*) as NbDistrict from district");
            if(rs.next()) NbDistrict.setText(rs.getString("NbDistrict"));
            rs.close();
            rs = c.executeQuery("select count(*) as NbCommune from commune");
            if(rs.next()) NbCommune.setText(rs.getString("NbCommune"));
            rs.close();
            rs = c.executeQuery("select count(*) as NbFokotany from fokontany");
            if(rs.next()) NbFokotany.setText(rs.getString("NbFokotany"));
            rs.close();
            rs = c.executeQuery("select * from region");
            if(rs.next()) {
                Siege.setText(rs.getString("SIEGE"));
                Superficie.setText(rs.getString("SUPERFICIE") + " km2");
                Historique.setText(rs.getString("HISTORIQUE_REG"));
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void AjoutDistrict() {
        String CODE = txtCode.getText();
        String NOM_DIST = txtNomDist.getText();
        String HISTORIQUE_DIST = txtHistorique.getText();
        String SUPERFICIE = txtSuperficie.getText();

        ClassDistrict com = new ClassDistrict(CODE, NOM_DIST, HISTORIQUE_DIST, SUPERFICIE);
        if (txtNomDist.getText().equals("") || txtHistorique.getText().equals("") || txtSuperficie.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment ajouter?","ajout en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionDistrict gcom = new GestionDistrict();
                gcom.insert(com);
                AfficherDistrict();
                JOptionPane.showMessageDialog(null, " Insertion Avec Succes du District   " + NOM_DIST + "  ");

            } catch (Exception ex) {
                
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());

            }
            }else{
            JOptionPane.showMessageDialog(null, " Annulation de l'enregistrement du district " + NOM_DIST + "! ");}
        }
    }

    public void ModificationDistrict() {
        String NOM_DIST = txtNomDist.getText();
        String HISTORIQUE_DIST = txtHistorique.getText();
        String SUPERFICIE = txtSuperficie.getText();

        ClassDistrict com = new ClassDistrict(ID_DIST, NOM_DIST, HISTORIQUE_DIST, SUPERFICIE);
        if (txtSuperficie.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier?","modification en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionDistrict gcom = new GestionDistrict();
                gcom.update(ID_DIST, com);
                AfficherDistrict();
                JOptionPane.showMessageDialog(null, " Modification reussi du District " + NOM_DIST + "! ");

            } catch (Exception ex) {
                
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());

            }
            }else{JOptionPane.showMessageDialog(null, "Annulation de la modification du District " + NOM_DIST + "! ");}
        }
    }

    private void SupprimerDistrict() {
        int ligneSelect = TableDistrict.getSelectedRow();
        String NOM_DIST = String.valueOf(TableDistrict.getValueAt(ligneSelect, 1));
        if (ligneSelect==-1) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ");
           } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer?","Suppression en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionDistrict gv = new GestionDistrict();
                gv.Supprimer(ID_DIST);
                AfficherDistrict();
                JOptionPane.showMessageDialog(null, " Le District " + NOM_DIST + "  a ete supprime avec succes! ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " Il y a un erreur lors de la suppression du District " + NOM_DIST + "!\n"+ex.getMessage());
            }
            }else{
                JOptionPane.showMessageDialog(null, "Annulation de la suppression du District " + NOM_DIST);
            }
        }


    }

    public void EffacerChampDistrict() {

        txtCode.setText("");
        txtNomDist.setText("");
        txtHistorique.setText("");
        txtSuperficie.setText("");
        txtRecherche.setText("");
    }
/*
private void recherchetout() {
        String champ = txtRecherche.getText();
        String[] titre = {"CODE", "REGION", "DISTRICT", "HISTORIQUE", "NOM", "PRENOM", "SEXE", "PHONE", "ADRESSE", "SUPERFICIE", "DATE"};
        Object[][] enreg = new Object[1][1];
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            //Etablissement de la connection à localhost et à la base de données siramm
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sirmania", "root", "");

            //Acceder à la base de données SIRMANIA
            //Création de la variable stm pour lancer la requete SQL
            Statement stm = con.createStatement();
            ResultSet rs0 = stm.executeQuery("SELECT count(*)as nbligne FROM district");
            rs0.next();
            int ligne = rs0.getInt("nbligne");
            enreg = new Object[ligne][titre.length];
            ResultSet rs1 = stm.executeQuery("SELECT * FROM district where ID_DIST like '%" + champ + "%' or NOM_DIST like '%" + champ + "%' or HISTORIQUE_DIST like '%" + champ + "%' or SUPERFICIE like '%" + champ + "%'");

            int i = 0;
            while (rs1.next()) {
                //Resultat rech
                enreg[i][0] = (Object) rs1.getString("ID_DIST");
                enreg[i][1] = (Object) rs1.getString("NOM_DIST");
                enreg[i][2] = (Object) rs1.getString("HISTORIQUE_DIST");
                enreg[i][3] = (Object) rs1.getString("SUPERFICIE");

                i++;
                txtCode.setText(rs1.getString(1));
                txtNomDist.setText(rs1.getString(2));
                txtHistorique.setText(rs1.getString(3));
                txtSuperficie.setText(rs1.getString(4));

            }
            TableDistrict.setModel(new DefaultTableModel(enreg, titre));
            jScrollPane1.setViewportView(TableDistrict);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (champ.equals("")) {
            try {
                AfficherDistrict();
                EffacerChampDistrict();

                // TODO add your handling code here:
            } catch (Exception ex) {
                Logger.getLogger(FormDistrict.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }           // TODO add your handling code here:  
    }*/
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableDistrict = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NbDistrict = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        NbCommune = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NbFokotany = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Siege = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Superficie = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Historique = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtRecherche = new javax.swing.JTextField();
        btnComboRecherche = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnAjout = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnImprimer = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtNomDist = new javax.swing.JTextField();
        txtSuperficie = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistorique = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(920, 400));
        jPanel1.setName(""); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(920, 400));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("LISTE DES DONNEES ENREGISTREES IMPRIMABLE"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableDistrict.setAutoCreateRowSorter(true);
        TableDistrict.setModel(new javax.swing.table.DefaultTableModel(
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
        TableDistrict.setDragEnabled(true);
        TableDistrict.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDistrictMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableDistrict);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 900, 130));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 920, 160));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("A PROPOS DU REGION AMORON'I MANIA"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre de district:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        NbDistrict.setText("jLabel5");
        jPanel3.add(NbDistrict, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 130, -1));

        jLabel5.setText("Nombre de commune:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        NbCommune.setText("jLabel6");
        jPanel3.add(NbCommune, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        jLabel6.setText("Nombre de fokotany:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        NbFokotany.setText("jLabel7");
        jPanel3.add(NbFokotany, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        jLabel7.setText("Siège:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        Siege.setText("jLabel8");
        jPanel3.add(Siege, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 90, -1));

        jLabel8.setText("Superficie:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        Superficie.setText("jLabel9");
        jPanel3.add(Superficie, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 100, -1));

        jLabel9.setText("Historique:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        Historique.setText("jLabel10");
        jPanel3.add(Historique, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 80, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 90));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("ENREGISTREMENT DE L'OPERATION ET RECHERCHE"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRecherche.setToolTipText("Saisir votre recherche ici puis lancer a l'aide de la boutton RECHERCHER");
        jPanel12.add(txtRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, 30));

        btnComboRecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche.setText("RECHERCHER");
        btnComboRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboRechercheActionPerformed(evt);
            }
        });
        jPanel12.add(btnComboRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 150, 30));

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 440, 80));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("OPERATION SUR L'ENREGISTREMENT"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel9.add(btnSupprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 190, -1));

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/about.png"))); // NOI18N
        jButton1.setText("OUVRIR");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 190, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/30.jpg"))); // NOI18N
        jButton2.setText("ACCEUIL");
        jButton2.setActionCommand("DETAILS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 190, 30));

        jPanel10.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 440, 190));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 460, 310));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMATION DISTRICT"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNomDist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomDistKeyReleased(evt);
            }
        });
        jPanel5.add(txtNomDist, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 290, -1));

        txtSuperficie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSuperficieKeyReleased(evt);
            }
        });
        jPanel5.add(txtSuperficie, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 290, -1));

        txtHistorique.setColumns(20);
        txtHistorique.setRows(5);
        jScrollPane2.setViewportView(txtHistorique);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 290, 120));

        jLabel3.setText("Historique");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 120));

        jLabel2.setText("Nom");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, 20));

        jLabel20.setText("Superficie");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 130, 20));
        jPanel5.add(txtCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 290, -1));

        jLabel4.setText("Code");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 460, 310));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 570));
        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutActionPerformed
        AjoutDistrict();
    }//GEN-LAST:event_btnAjoutActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        ModificationDistrict();
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        SupprimerDistrict();
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
        ImprimerTableDistrict();
    }//GEN-LAST:event_btnImprimerActionPerformed

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        try {
            AfficherDistrict();
        } catch (Exception ex) {
            Logger.getLogger(FormDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualiserActionPerformed

    private void TableDistrictMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableDistrictMouseClicked
        ClickTableDistrict();
    }//GEN-LAST:event_TableDistrictMouseClicked

    private void btnComboRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboRechercheActionPerformed
        // TODO add your handling code here:
        try {
            String champ = txtRecherche.getText();
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("DISTRICT");
            dt.addColumn("HISTORIQUE");
            dt.addColumn("SUPERFICIE");

            TableDistrict.setModel(dt);
            rs = c.executeQuery("SELECT * FROM district where ID_DIST like '%" + champ + "%' or NOM_DIST like '%" + champ + "%' or HISTORIQUE_DIST like '%" + champ + "%' or SUPERFICIE like '%" + champ + "%'");
            
            while (rs.next()) {
                String CODE = rs.getString("ID_DIST");
                String NOM_DIST = rs.getString("NOM_DIST");
                String HISTORIQUE_DIST = rs.getString("HISTORIQUE_DIST");
                String SUPERFICIE = rs.getString("SUPERFICIE");

                Object[] DISTRICT = {CODE, NOM_DIST, HISTORIQUE_DIST,SUPERFICIE};
                dt.addRow(DISTRICT);
                EffacerChampDistrict();
            }
        } catch (Exception ex) {
            Logger.getLogger(FormDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnComboRechercheActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int ligneSelect = TableDistrict.getSelectedRow();
        if (ligneSelect!=-1) {
            ID_DIST = String.valueOf(TableDistrict.getValueAt(ligneSelect, 0));
            String DesignCom =  TableDistrict.getValueAt(ligneSelect, 1).toString();
            String SuperficieCom =  TableDistrict.getValueAt(ligneSelect, 3).toString();
            String HistoriqueCom = TableDistrict.getValueAt(ligneSelect, 2).toString();
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
            a.DesignDist = DesignCom;
            a.HistoriqueDist = HistoriqueCom;
            a.NbFokotany = NbFokotany;
            a.NbCommune = NbCommune;
            a.ID_DIST = ID_DIST;
            a.SuperficieDist = SuperficieCom + " km2";
            a.first();
            a.setVisible(true);
            this.dispose();
                }}
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FormMenuPrincipale a = new FormMenuPrincipale(); 
        a.setVisible(true);        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNomDistKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomDistKeyReleased
        // TODO add your handling code here:
        
        String ChampCodeDoss = txtNomDist.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 24;
        if (txtNomDist.getText().matches("[A-Z,a-z]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet doit etre inferieur ou egale a 24 caracteres, merci!!");
            }
            txtNomDist.setText(ChampCodeDoss);
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            txtNomDist.setText(ChampCodeDoss);
            JOptionPane.showMessageDialog(null, "Les caracteres qu'on peut utiliser dans ce champ sont: l'alphabet majuscule et minuscule merci!!");

        }
    }//GEN-LAST:event_txtNomDistKeyReleased

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
            java.util.logging.Logger.getLogger(FormDistrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDistrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDistrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDistrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDistrict().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Historique;
    private javax.swing.JLabel NbCommune;
    private javax.swing.JLabel NbDistrict;
    private javax.swing.JLabel NbFokotany;
    private javax.swing.JLabel Siege;
    private javax.swing.JLabel Superficie;
    private javax.swing.JTable TableDistrict;
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnAjout;
    private javax.swing.JButton btnComboRecherche;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextArea txtHistorique;
    private javax.swing.JTextField txtNomDist;
    private javax.swing.JTextField txtRecherche;
    private javax.swing.JTextField txtSuperficie;
    // End of variables declaration//GEN-END:variables
}
