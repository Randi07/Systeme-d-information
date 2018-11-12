package Fokontany;

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
public class FormSportive extends javax.swing.JFrame {

    int i, val, nbligne;
    public Connection con;
    public Statement st;
    public DefaultTableModel dt;
    public ResultSet res;
    String cont;
    public Object bouton2;
    public Object bouton;
    //debut données a faire passer
   public String DesignCom = "";
   public String DesignFkt="";
    public String NbFokotany = "";
    public String HistoriqueCom = "";
    public String SuperficieCom = "";
    public String DesignDist="";
    public String SuperficieDist="";
    public String HistoriqueDist="";
    public String ID_FKT="";
    public String ID_COM="";
    public String ID_DIST="";
    //fin de données a faire passer
//    public Object dt;
    public DefaultTableModel dtm;
    public ResultSet rs;
    public Statement St;
    public boolean animated;
    public Thread t;

    public FormSportive() {
        initComponents();
        //ComboFokontany();
        this.setTitle("INFORMATION SUR LES INFRASTRUCTURES SPORTIVE AU NIVEAU FOKONTANY");
    }
    public void first(){try {
            AfficherSportive();

        } catch (Exception ex) {
            Logger.getLogger(FormSportive.class.getName()).log(Level.SEVERE, null, ex);
        }}
    public void ClickTableSportive() {

        int ligneSelect = TableSportive.getSelectedRow();
        txtNumero.setText(String.valueOf(TableSportive.getValueAt(ligneSelect, 0)));
        ComboCategorie.setSelectedItem(String.valueOf(TableSportive.getValueAt(ligneSelect, 1)));
        ComboTerrain.setSelectedItem(String.valueOf(TableSportive.getValueAt(ligneSelect, 2)));
        ComboEtat.setSelectedItem(String.valueOf(TableSportive.getValueAt(ligneSelect, 3)));
        txtNombre.setText(String.valueOf(TableSportive.getValueAt(ligneSelect, 4)));
        txtRemarque.setText(String.valueOf(TableSportive.getValueAt(ligneSelect, 5)));
        txtAnnee.setText(TableSportive.getValueAt(TableSportive.getSelectedRow(), 6).toString());

    }

    private void ImprimerTableSportive() {
        java.text.MessageFormat head = new java.text.MessageFormat("Info sur le sport du fokontany "+DesignFkt);
        java.text.MessageFormat end = new java.text.MessageFormat("Page {0,number,integer}");
        try {
            TableSportive.print(JTable.PrintMode.FIT_WIDTH, head, end);
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la procedure d'impression: " + e.getMessage());
        }
    }

    private void AfficherSportive() throws Exception {

        try {
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("CATEGORIE");
            dt.addColumn("TERRAIN");
            dt.addColumn("ETAT");
            dt.addColumn("NOMBRE TERRAIN");
            dt.addColumn("REMARQUE");
            dt.addColumn("ANNEE");
            TableSportive.setModel(dt);
            GestionSport gcom = new GestionSport();
            rs = ID_FKT.equals("") ? gcom.getAll(): c.executeQuery("Select * From sportive where ID_FKT = '" + ID_FKT + "' order by ID_SPORT asc");
            
            while (rs.next()) {

                String ID_SPORT = rs.getString("ID_SPORT");
                String CATEG_SPORT = rs.getString("CATEG_SPORT");
                String EXISTANCE_TERRAIN = rs.getString("EXISTANCE_TERRAIN");
                String ETAT_TERRAIN = rs.getString("ETAT_TERRAIN");
                String NOMBRE_TERRAIN = rs.getString("NOMBRE_TERRAIN");
                String REMARQUE_SPORT = rs.getString("REMARQUE_SPORT");
                String ANNEE = rs.getString("ANNEE");
                Object[] sportive = {ID_SPORT, CATEG_SPORT, EXISTANCE_TERRAIN, ETAT_TERRAIN, NOMBRE_TERRAIN, REMARQUE_SPORT, ANNEE};
                dt.addRow(sportive);
               
            }
             EffacerChampSportive();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    private void AjoutSportive() {
        String ID_SPORT = txtNumero.getText();
        String CATEG_SPORT = ComboCategorie.getSelectedItem().toString();
        String EXISTANCE_TERRAIN = ComboTerrain.getSelectedItem().toString();
        String ETAT_TERRAIN = ComboEtat.getSelectedItem().toString();
        String NOMBRE_TERRAIN = txtNombre.getText();
        String REMARQUE_SPORT = txtRemarque.getText();
        String ANNEE = txtAnnee.getText();

        ClassSport com = new ClassSport(ID_SPORT,CATEG_SPORT, EXISTANCE_TERRAIN, ETAT_TERRAIN, NOMBRE_TERRAIN, REMARQUE_SPORT, ANNEE);
        if (NOMBRE_TERRAIN.equals("") || REMARQUE_SPORT.equals("") || ANNEE.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment ajouter?","ajout en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionSport gcom = new GestionSport();
                gcom.insert(com);
                AfficherSportive();
                JOptionPane.showMessageDialog(null, "Insertion de l'information reussi");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }}else{JOptionPane.showMessageDialog(null, " Annulation de l'enregistrement de l'information");}
        }
    }

    public void ModificationSportive() {
        String ID_SPORT = txtNumero.getText();
        String CATEG_SPORT = ComboCategorie.getSelectedItem().toString();
        String EXISTANCE_TERRAIN = ComboTerrain.getSelectedItem().toString();
        String ETAT_TERRAIN = ComboEtat.getSelectedItem().toString();
        String NOMBRE_TERRAIN = txtNombre.getText();
        String REMARQUE_SPORT = txtRemarque.getText();
        String ANNEE = txtAnnee.getText();

        ClassSport com = new ClassSport(ID_SPORT,CATEG_SPORT, EXISTANCE_TERRAIN, ETAT_TERRAIN, NOMBRE_TERRAIN, REMARQUE_SPORT, ANNEE);
        if (NOMBRE_TERRAIN.equals("") || REMARQUE_SPORT.equals("") || ANNEE.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier?","Modification en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionSport gcom = new GestionSport();
                gcom.update(ID_SPORT, com);
                AfficherSportive();
                JOptionPane.showMessageDialog(null, "Modification de l'information  numero   " + ID_SPORT + "  dans la commune " + ID_COM + " du Fokontany " + ID_FKT + " reusi");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                
            }
            }else{JOptionPane.showMessageDialog(null, " Annulation de modification de l'information  numero   " + ID_SPORT + "  dans la commune " + ID_COM + " du Fokontany " + ID_FKT + " ");
}
        }
    }

    private void SupprimerSportive() {
        String NUMERO = txtNumero.getText();
        if (txtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner une ligne");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer?","Suppression en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionSport gv = new GestionSport();
                gv.Supprimer(NUMERO);
                AfficherSportive();
                JOptionPane.showMessageDialog(null, " L'enregistrement dans le fokontany " + ID_FKT + " numero " + NUMERO + " de la commune " + ID_COM + " a ete supprime avec succes! ");
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
                
            }}else{JOptionPane.showMessageDialog(null, " Annulation de la suppression de l'enregistrement du fokontany " + ID_FKT + " numero" + NUMERO + " de la commune " + ID_COM + "! ");
}
        }

    }

    public void EffacerChampSportive() {

        txtNumero.setText("");
        ComboCategorie.setSelectedIndex(0);
        ComboTerrain.setSelectedIndex(0);
        ComboEtat.setSelectedIndex(0);
        txtNombre.setText("");
        txtRemarque.setText("");
        txtRecherche.setText("");
        txtAnnee.setText("");
    }
/*
    private void recherchetout() {
        String champ = txtRecherche.getText();
        String[] titre = {"CODE", "FOKONTANY", "COMMUNE", "CATEGORIE ", "TERRAIN", "ETAT TERRAIN", " NOMBRE TERRAIN", "REMARQUE", "DATE"};
        Object[][] enreg = new Object[1][1];
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            //Etablissement de la connection à localhost et à la base de données siramm
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sirmania", "root", "");

            //Acceder à la base de données SIRMANIA
            //Création de la variable stm pour lancer la requete SQL
            Statement stm = con.createStatement();
            ResultSet rs0 = stm.executeQuery("SELECT count(*)as nbligne FROM sportive");
            rs0.next();
            int ligne = rs0.getInt("nbligne");
            enreg = new Object[ligne][titre.length];
            ResultSet rs1 = stm.executeQuery("SELECT * FROM sportive where ID_SPORT like '%" + champ + "%' or ID_FKT like '%" + champ + "%' or ID_COM like '%" + champ + "%' or ETAT_TERRAIN like '%" + champ + "%' or EXISTANCE_TERRAIN like '%" + champ + "%' or CATEG_SPORT like '%" + champ + "%' or ANNEE like '%" + champ + "%'");

            int i = 0;
            while (rs1.next()) {
                //Resultat rech
                enreg[i][0] = (Object) rs1.getString("ID_SPORT");
                enreg[i][1] = (Object) rs1.getString("ID_FKT");
                enreg[i][2] = (Object) rs1.getString("ID_COM");
                enreg[i][3] = (Object) rs1.getString("CATEG_SPORT");
                enreg[i][4] = (Object) rs1.getString("EXISTANCE_TERRAIN");
                enreg[i][5] = (Object) rs1.getString("ETAT_TERRAIN");
                enreg[i][6] = (Object) rs1.getString("NOMBRE_TERRAIN");
                enreg[i][7] = (Object) rs1.getString("REMARQUE_SPORT");
                enreg[i][8] = (Object) rs1.getString("ANNEE");
                i++;
                txtNumero.setText(rs1.getString(1));
                ComboFkt.setSelectedItem(rs1.getString(2));
                ComboCom.setSelectedItem(rs1.getString(3));
                ComboCategorie.setSelectedItem(rs1.getString(4));
                ComboTerrain.setSelectedItem(rs1.getString(5));
                ComboEtat.setSelectedItem(rs1.getString(6));
                txtNombre.setText(rs1.getString(7));
                txtRemarque.setText(rs1.getString(8));
                ((JTextField) jDateRec.getDateEditor().getUiComponent()).setText(rs1.getString(9));

            }
            TableSportive.setModel(new DefaultTableModel(enreg, titre));
            jScrollPane1.setViewportView(TableSportive);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (champ.equals("")) {
            try {
                AfficherSportive();
                EffacerChampSportive();

                // TODO add your handling code here:
            } catch (Exception ex) {
                Logger.getLogger(FormSportive.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }           // TODO add your handling code here:  
    }

    public void RechercheEntredeuxDate() {
        String[] titre = {"CODE", "FOKONTANY", "COMMUNE", "CATEGORIE ", "TERRAIN", "ETAT TERRAIN", " NOMBRE TERRAIN", "REMARQUE", "DATE"};
        Object[][] enreg = new Object[1][1];
        int nbligne;
        int i = 0;
        String date1 = ((JTextField) (jDateRec1.getDateEditor().getUiComponent())).getText();
        String date2 = ((JTextField) (jDateRec2.getDateEditor().getUiComponent())).getText();
        try {

            GestionSport gp = new GestionSport();
            ResultSet rs0 = gp.nombretrouverDeuxDate(date1, date2);
            rs0.next();
            nbligne = rs0.getInt(1);
            enreg = new Object[nbligne][titre.length];
            ResultSet rs1 = gp.getAllnombretrouverDeuxDate(date1, date2);
            while (rs1.next()) {
                enreg[i][0] = (Object) rs1.getString("ID_SPORT");
                enreg[i][1] = (Object) rs1.getString("ID_FKT");
                enreg[i][2] = (Object) rs1.getString("ID_COM");
                enreg[i][3] = (Object) rs1.getString("CATEG_SPORT");
                enreg[i][4] = (Object) rs1.getString("EXISTANCE_TERRAIN");
                enreg[i][5] = (Object) rs1.getString("ETAT_TERRAIN");
                enreg[i][6] = (Object) rs1.getString("NOMBRE_TERRAIN");
                enreg[i][7] = (Object) rs1.getString("REMARQUE_SPORT");
                enreg[i][8] = (Object) rs1.getString("ANNEE");

                i++;
            }
            TableSportive.setModel(new DefaultTableModel(enreg, titre));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
*/
    private void Deplacement(int i) {
        try {//represente ligne et colonne i
            if (i == 0) {
                btnSuivant.setEnabled(true);
                btnPrecedent.setEnabled(false);
            } else if (i == TableSportive.getRowCount() - 1) {//dernier enregistrement
                btnSuivant.setEnabled(false);
                btnPrecedent.setEnabled(true);
            } else {
                btnSuivant.setEnabled(true);
                btnPrecedent.setEnabled(true);
            }
            txtNumero.setText(TableSportive.getValueAt(i, 0).toString());
            ComboCategorie.setSelectedItem(TableSportive.getValueAt(i, 1).toString());
            ComboTerrain.setSelectedItem(TableSportive.getValueAt(i, 2).toString());
            ComboEtat.setSelectedItem(TableSportive.getValueAt(i, 3).toString());
            txtNombre.setText(TableSportive.getValueAt(i, 4).toString());
            txtRemarque.setText(TableSportive.getValueAt(i, 5).toString());
            txtAnnee.setText(TableSportive.getValueAt(i, 6).toString());

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"Erreur deplacement client\n"+e.getMessage());
            
        }
    }

    private void Precedent() {
        try {
            i--;
            Deplacement(i);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur debut\n" + e.getMessage());
            
        }
    }

    private void Suivant() {
        try {
            i++;
            Deplacement(i);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur debut\n" + e.getMessage());
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableSportive = new javax.swing.JTable();
        btnPrecedent = new javax.swing.JButton();
        btnSuivant = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ComboEtat = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        ComboCategorie = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        ComboTerrain = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRemarque = new javax.swing.JTextArea();
        txtAnnee = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        Fermer = new javax.swing.JButton();
        Ajout = new javax.swing.JButton();
        Modifier = new javax.swing.JButton();
        Supprimer = new javax.swing.JButton();
        Imprimer = new javax.swing.JButton();
        Actualiser = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        txtRecherche = new javax.swing.JTextField();
        btnComboRecherche = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("LISTE DES DONNEES ENREGISTREES IMPRIMABLE"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableSportive.setAutoCreateRowSorter(true);
        TableSportive.setModel(new javax.swing.table.DefaultTableModel(
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
        TableSportive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableSportiveMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableSportive);
        TableSportive.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 880, 160));

        btnPrecedent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/button_prev.png"))); // NOI18N
        btnPrecedent.setText("Precedent");
        btnPrecedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecedentActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrecedent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, -1));

        btnSuivant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/button_next_1.png"))); // NOI18N
        btnSuivant.setText("Suivant");
        btnSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuivantActionPerformed(evt);
            }
        });
        jPanel2.add(btnSuivant, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 120, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 900, 230));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("SAISIE DES INFORMATIONS"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNumero.setEditable(false);
        jPanel6.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 200, -1));

        jLabel9.setText("Numero D'enregistrement");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Information sur l'Iinfrastructure Sport"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Categorie");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, -1));

        jLabel4.setText("Existance Terrain de");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 160, -1));

        jLabel5.setText("Etat Terrain");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 100, 20));

        ComboEtat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bonne", "Moyenne", "Mauvaise" }));
        jPanel5.add(ComboEtat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 200, -1));

        jLabel11.setText("Annee");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 130, -1));

        ComboCategorie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(ComboCategorie, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 200, -1));

        jLabel7.setText("Nombre Terrain");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, 20));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        jPanel5.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 200, -1));

        jLabel21.setText("Remarque");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 120, 50));

        ComboTerrain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Foot", "Basket", "Volley", "Hand", "Omnisport", "Athletisme" }));
        jPanel5.add(ComboTerrain, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 200, -1));

        txtRemarque.setColumns(20);
        txtRemarque.setRows(5);
        jScrollPane2.setViewportView(txtRemarque);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 200, -1));

        txtAnnee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnneeKeyReleased(evt);
            }
        });
        jPanel5.add(txtAnnee, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 200, -1));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 420, 310));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 380));

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("ENREGISTREMENT DE L'OPERATION ET RECHERCHE"));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("OPERATION SUR L'ENREGISTREMENT"));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fermer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/fermer-icon.png"))); // NOI18N
        Fermer.setText("RETOUR");
        Fermer.setToolTipText("Appuyer si vous voulez fermer cette fenetre");
        Fermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FermerActionPerformed(evt);
            }
        });
        jPanel14.add(Fermer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 190, 30));

        Ajout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/add.gif"))); // NOI18N
        Ajout.setText("INSERTION");
        Ajout.setToolTipText("Appuyer pour ajouter et enregistrer votre donnees");
        Ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutActionPerformed(evt);
            }
        });
        jPanel14.add(Ajout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 190, 30));

        Modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/A_PencilDyn_Sm_N.png"))); // NOI18N
        Modifier.setText("MODIFICATION");
        Modifier.setToolTipText("Appuyer pour modifier ce donnees");
        Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierActionPerformed(evt);
            }
        });
        jPanel14.add(Modifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 190, 30));

        Supprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconSupprimer.png"))); // NOI18N
        Supprimer.setText("SUPPRESSION");
        Supprimer.setToolTipText("Appuyer si vous voulez supprimer ce donnees");
        Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerActionPerformed(evt);
            }
        });
        jPanel14.add(Supprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 190, -1));

        Imprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer-icon.png"))); // NOI18N
        Imprimer.setText("IMPRESSION");
        Imprimer.setToolTipText("Appuyer si vous voulez imprimer les donnees deja enregistrees");
        Imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimerActionPerformed(evt);
            }
        });
        jPanel14.add(Imprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 190, 30));

        Actualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/refresh.png"))); // NOI18N
        Actualiser.setText("ACTUALISER");
        Actualiser.setToolTipText("Appuyer si vous voulez rafraichir la liste");
        Actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualiserActionPerformed(evt);
            }
        });
        jPanel14.add(Actualiser, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 190, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/30.jpg"))); // NOI18N
        jButton2.setText("ACCEUIL");
        jButton2.setActionCommand("DETAILS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 190, 40));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 440, 220));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRecherche.setToolTipText("Ecrire ici votre requette! votre  recherche va declancer automatiquement .");
        txtRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheKeyReleased(evt);
            }
        });
        jPanel15.add(txtRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 250, 30));

        btnComboRecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche.setText("RECHERCHER");
        btnComboRecherche.setToolTipText("Appuyer pour lancer la recherche");
        btnComboRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboRechercheActionPerformed(evt);
            }
        });
        jPanel15.add(btnComboRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 150, 30));

        jPanel13.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 440, 100));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 460, 380));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));
        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FermerActionPerformed
    if(ID_COM.equals(""))
        {
            FormMenuPrincipale a = new FormMenuPrincipale(); 
        a.setVisible(true);        
        this.dispose();
        }
        else
        {
            
            try {
                ConnexionBase c = new ConnexionBase();
                rs = c.executeQuery("select count(*) as NbFokotany from fokontany where SUBSTRING(ID_FKT, 1, 6) like '"+ ID_COM +"'");
                    if(rs.next()){
            String NbFokotany = rs.getString("NbFokotany");
            FormFokontany a = new FormFokontany();
            //a.ID_FKT =TableCommune.getValueAt(ligneSelect, 0).toString();
            a.DesignCom = DesignCom;
            a.HistoriqueCom = HistoriqueCom;
            a.SuperficieCom = SuperficieCom;
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
    }//GEN-LAST:event_FermerActionPerformed

    private void AjoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutActionPerformed
        if(!ID_FKT.equals(""))  AjoutSportive();
        else JOptionPane.showMessageDialog(null, "Operation interdit!Pour ajouter, veuillez entrées dépuis le 'Voir ces districts' du menu principal");
    }//GEN-LAST:event_AjoutActionPerformed

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
        ModificationSportive();
    }//GEN-LAST:event_ModifierActionPerformed

    private void SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerActionPerformed
        SupprimerSportive();
    }//GEN-LAST:event_SupprimerActionPerformed

    private void ImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimerActionPerformed
        ImprimerTableSportive();
    }//GEN-LAST:event_ImprimerActionPerformed

    private void ActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualiserActionPerformed
        first();
    }//GEN-LAST:event_ActualiserActionPerformed

    private void txtRechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheKeyReleased
       
    }//GEN-LAST:event_txtRechercheKeyReleased

    private void btnPrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrecedentActionPerformed
        Precedent();
    }//GEN-LAST:event_btnPrecedentActionPerformed

    private void btnSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuivantActionPerformed
        Suivant();
    }//GEN-LAST:event_btnSuivantActionPerformed

    private void TableSportiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSportiveMouseClicked
        ClickTableSportive();
    }//GEN-LAST:event_TableSportiveMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FormMenuPrincipale a = new FormMenuPrincipale(); 
        a.setVisible(true);        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnComboRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboRechercheActionPerformed
        // TODO add your handling code here:
        try {
            String champ = txtRecherche.getText();
            
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("CODE");
            dt.addColumn("CATEGORIE");
            dt.addColumn("TERRAIN");
            dt.addColumn("ETAT");
            dt.addColumn("NOMBRE TERRAIN");
            dt.addColumn("REMARQUE");
            dt.addColumn("ANNEE");
            TableSportive.setModel(dt);
            rs =ID_FKT.equals("") ?  c.executeQuery("SELECT * FROM sportive where CATEG_SPORT like '%" + champ + "%' or EXISTANCE_TERRAIN like '%" + champ + "%' or ETAT_TERRAIN like '%" + champ + "%' or NOMBRE_TERRAIN = '" + champ + "' or REMARQUE_SPORT like '%" + champ + "%' or ANNEE = '" + champ + "'") : c.executeQuery("SELECT * FROM sportive where ID_FKT = '" + ID_FKT + "' AND (CATEG_SPORT like '%" + champ + "%' or EXISTANCE_TERRAIN like '%" + champ + "%' or ETAT_TERRAIN like '%" + champ + "%' or NOMBRE_TERRAIN = '" + champ + "' or REMARQUE_SPORT like '%" + champ + "%' or ANNEE = '" + champ + "')");
            while (rs.next()) {
               String ID_SPORT = rs.getString("ID_SPORT");
                String CATEG_SPORT = rs.getString("CATEG_SPORT");
                String EXISTANCE_TERRAIN = rs.getString("EXISTANCE_TERRAIN");
                String ETAT_TERRAIN = rs.getString("ETAT_TERRAIN");
                String NOMBRE_TERRAIN = rs.getString("NOMBRE_TERRAIN");
                String REMARQUE_SPORT = rs.getString("REMARQUE_SPORT");
                String ANNEE = rs.getString("ANNEE");
                Object[] sportive = {ID_SPORT, CATEG_SPORT, EXISTANCE_TERRAIN, ETAT_TERRAIN, NOMBRE_TERRAIN, REMARQUE_SPORT, ANNEE};
                dt.addRow(sportive);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnComboRechercheActionPerformed

    private void txtAnneeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnneeKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtAnnee.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 4;
        if (ChampCodeDoss.matches("[0-9]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre egale a 4 chiffres, merci!!");
            }
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre un nombre entier a 4 chiffre, merci!!");
        }
        txtAnnee.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtAnneeKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtNombre.getText();
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
        txtNombre.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtNombreKeyReleased

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
            java.util.logging.Logger.getLogger(FormSportive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSportive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSportive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSportive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new FormSportive().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualiser;
    private javax.swing.JButton Ajout;
    private javax.swing.JComboBox ComboCategorie;
    private javax.swing.JComboBox ComboEtat;
    private javax.swing.JComboBox ComboTerrain;
    private javax.swing.JButton Fermer;
    private javax.swing.JButton Imprimer;
    private javax.swing.JButton Modifier;
    private javax.swing.JButton Supprimer;
    private javax.swing.JTable TableSportive;
    private javax.swing.JButton btnComboRecherche;
    private javax.swing.JButton btnPrecedent;
    private javax.swing.JButton btnSuivant;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtAnnee;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRecherche;
    private javax.swing.JTextArea txtRemarque;
    // End of variables declaration//GEN-END:variables
}
