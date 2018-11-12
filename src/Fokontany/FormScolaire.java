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
public class FormScolaire extends javax.swing.JFrame {
    int i, val, nbligne;
    public Connection con;
    public Statement st;
    public DefaultTableModel dt;
    public ResultSet res;
    String cont;
    //debut données a faire passer
     public String DesignCom = "";
    public String NbFokotany = "";
    public String HistoriqueCom = "";
    public String SuperficieCom = "";
    public String DesignDist="";
    public String SuperficieDist="";
    public String HistoriqueDist="";
    public String DesignFkt="";
    public String ID_FKT="";
    public String ID_COM="";
    public String ID_DIST="";
    //fin de données a faire passer
    public Object bouton2;
    public Object bouton;
//    public Object dt;
    public DefaultTableModel dtm;
    public ResultSet rs;
    public Statement St;
    public boolean animated;
    public Thread t;

    public FormScolaire() {
        initComponents();
        //ComboFokontany();

        this.setTitle("INFORMATION SUR LA SCOLARITE AU NIVEAU FOKONTANY");
        

    }

    public void first()
    {
        try {
            AfficherScolaire();

        } catch (Exception ex) {
            Logger.getLogger(FormScolaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ClickTableScolaire() {

        int ligneSelect = TableScolaire.getSelectedRow();
        txtNumero.setText(String.valueOf(TableScolaire.getValueAt(ligneSelect, 0)));
        ComboCategorie.setSelectedItem(String.valueOf(TableScolaire.getValueAt(ligneSelect, 1)));
        ComboNature.setSelectedItem(String.valueOf(TableScolaire.getValueAt(ligneSelect, 2)));
        txtNombreInfras.setText(String.valueOf(TableScolaire.getValueAt(ligneSelect, 3)));
        ComboEtat.setSelectedItem(String.valueOf(TableScolaire.getValueAt(ligneSelect, 4)));
        txtMasculain.setText(String.valueOf(TableScolaire.getValueAt(ligneSelect, 5)));
        txtFeminin.setText(String.valueOf(TableScolaire.getValueAt(ligneSelect, 6)));
        txtTotal.setText(String.valueOf(TableScolaire.getValueAt(ligneSelect, 7)));
        txtAnneeScolaire.setText(String.valueOf(TableScolaire.getValueAt(ligneSelect, 8)));
        txtAnnee.setText(TableScolaire.getValueAt(TableScolaire.getSelectedRow(), 9).toString());

    }

    private void AfficherScolaire() throws Exception {

        try {
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("CATEGORIE");
            dt.addColumn("NATURE");
            dt.addColumn("NOMBRE INFRAS");
            dt.addColumn("ETAT");
            dt.addColumn("NOMBRE MASCULAIN");
            dt.addColumn("NOMBRE FEMININ");
            dt.addColumn("TOTAL");
            dt.addColumn("ANNEE SCOLAIRE");
            dt.addColumn("ANNEE");
            TableScolaire.setModel(dt);
            GestionScolaire gcom = new GestionScolaire();
            rs = ID_FKT.equals("") ? gcom.getAll(): c.executeQuery("Select * From scolaire where ID_FKT = '" + ID_FKT + "' order by ID_FKT asc");
            rs = gcom.getAll();
            while (rs.next()) {

                String ID_SCOLAIRE = rs.getString("ID_SCOLAIRE");
                String CATEG_SCOLAIRE = rs.getString("CATEG_SCOLAIRE");
                String NATURE_SCOLAIRE = rs.getString("NATURE_SCOLAIRE");
                String NOMBRE_SCOLAIRE = rs.getString("NOMBRE_SCOLAIRE");
                String ETAT_SCOLAIRE = rs.getString("ETAT_SCOLAIRE");
                String ELEVE_MASCULAIN = rs.getString("ELEVE_MASCULAIN");
                String ELEVE_FEMININ = rs.getString("ELEVE_FEMININ");
                String EFFECTIF_TOTAL = rs.getString("EFFECTIF_TOTAL");
                String ANNEE_SCOLAIRE = rs.getString("ANNEE_SCOLAIRE");
                String ANNEE = rs.getString("ANNEE");
                Object[] scolaire = {ID_SCOLAIRE, CATEG_SCOLAIRE, NATURE_SCOLAIRE, NOMBRE_SCOLAIRE, ETAT_SCOLAIRE, ELEVE_MASCULAIN, ELEVE_FEMININ, EFFECTIF_TOTAL, ANNEE_SCOLAIRE, ANNEE};
                dt.addRow(scolaire);
                
            }
            EffacerChampScolaire();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormScolaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void AjoutScolaire() {
        String ID_SCOLAIRE = txtNumero.getText();
        String CATEG_SCOLAIRE = ComboCategorie.getSelectedItem().toString();
        String NATURE_SCOLAIRE = ComboNature.getSelectedItem().toString();
        String NOMBRE_SCOLAIRE = txtNombreInfras.getText();
        String ETAT_SCOLAIRE = ComboEtat.getSelectedItem().toString();
        String ELEVE_MASCULAIN = txtMasculain.getText();
        String ELEVE_FEMININ = txtFeminin.getText();
        String EFFECTIF_TOTAL = txtTotal.getText();
        String ANNEE_SCOLAIRE = txtAnneeScolaire.getText();
        String ANNEE = txtAnnee.getText();

        ClassScolaire com = new ClassScolaire(ID_SCOLAIRE, CATEG_SCOLAIRE, NATURE_SCOLAIRE, NOMBRE_SCOLAIRE, ETAT_SCOLAIRE, ELEVE_MASCULAIN, ELEVE_FEMININ, EFFECTIF_TOTAL, ANNEE_SCOLAIRE, ANNEE);
        com.ID_FKT = ID_FKT;
        if (txtNombreInfras.getText().equals("") || txtMasculain.getText().equals("") || txtFeminin.getText().equals("") || txtAnneeScolaire.getText().equals("") || txtAnnee.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment ajouter?","ajout en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
                try {GestionScolaire gcom = new GestionScolaire();
                gcom.insert(com);
                AfficherScolaire();
                JOptionPane.showMessageDialog(null, "Insertion de l'information reussi");
} catch (Exception ex) {
                
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            } else {
                JOptionPane.showMessageDialog(null, " Annulation de l'enregistrement de l'information");
               

            }
        }
    }

    public void ModificationScolaire() {
        String ID_SCOLAIRE = txtNumero.getText();
        String CATEG_SCOLAIRE = ComboCategorie.getSelectedItem().toString();
        String NATURE_SCOLAIRE = ComboNature.getSelectedItem().toString();
        String NOMBRE_SCOLAIRE = txtNombreInfras.getText();
        String ETAT_SCOLAIRE = ComboEtat.getSelectedItem().toString();
        String ELEVE_MASCULAIN = txtMasculain.getText();
        String ELEVE_FEMININ = txtFeminin.getText();
        String EFFECTIF_TOTAL = txtTotal.getText();
        String ANNEE_SCOLAIRE = txtAnneeScolaire.getText();
        String ANNEE = txtAnnee.getText();
        ClassScolaire com = new ClassScolaire(ID_SCOLAIRE, CATEG_SCOLAIRE, NATURE_SCOLAIRE, NOMBRE_SCOLAIRE, ETAT_SCOLAIRE, ELEVE_MASCULAIN, ELEVE_FEMININ, EFFECTIF_TOTAL, ANNEE_SCOLAIRE, ANNEE);
        if (txtNombreInfras.getText().equals("") || txtMasculain.getText().equals("") || txtFeminin.getText().equals("") || txtAnneeScolaire.getText().equals("") || txtAnnee.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "voulez vous modifier et enregistrer l'information numero   " + ID_SCOLAIRE + " dans la commune " + ID_COM + " du Fokontany " + ID_FKT + "?","modification en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
                try {GestionScolaire gcom = new GestionScolaire();
                gcom.insert(com);
                AfficherScolaire();
                JOptionPane.showMessageDialog(null, "Modification de l'information  numero   " + ID_SCOLAIRE + "  dans la commune " + ID_COM + " du Fokontany " + ID_FKT + " reussi");
} catch (Exception ex) {
                
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            } else {
                JOptionPane.showMessageDialog(null, " Annulation de la modification de l'information");
               

            }
        }
    }

    private void SupprimerScolaire() {
        String NUMERO = txtNumero.getText();

        if (txtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ pour effectuer cette suppression");
        } else {
            int dRes = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer l'enregistrement  du fokontany " + ID_FKT + " numero " + NUMERO + " de la commune " + ID_COM + " ?","suppresion en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            try {
                GestionScolaire gv = new GestionScolaire();
                gv.Supprimer(NUMERO);
                AfficherScolaire();
                JOptionPane.showMessageDialog(null, " L'enregistrement dans le fokontany " + ID_FKT + " numero " + NUMERO + " de la commune " + ID_COM + " a ete supprime avec succes! ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                
            }
        }
            else {JOptionPane.showMessageDialog(null, " Annulation de la suppression de l'enregistrement du fokontany " + ID_FKT + " numero" + NUMERO + " de la commune " + ID_COM + "! ");}

    }
    }

    public void EffacerChampScolaire() {

        txtNumero.setText("");
        ComboNature.setSelectedIndex(0);
        ComboCategorie.setSelectedIndex(0);
        ComboEtat.setSelectedIndex(0);
        txtNombreInfras.setText("");
        txtMasculain.setText("");
        txtFeminin.setText("");
        txtAnneeScolaire.setText("");
        txtAnnee.setText("");
        txtRecherche.setText("");
        txtTotal.setText("");
    }
/*
    private void recherchetout() {
        String champ = txtRecherche.getText();
        String[] titre = {"CODE", "FOKONTANY", "COMMUNE", "CATEGORIE ", "NATURE INFRAS", "NOMBRE INFRAS", " ETAT", "MASCULAIN", "FEMININ", "TOTAL", "ANNEE SCOLAIRE", "DATE"};
        Object[][] enreg = new Object[1][1];
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            //Etablissement de la connection à localhost et à la base de données siramm
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sirmania", "root", "");

            //Acceder à la base de données SIRMANIA
            //Création de la variable stm pour lancer la requete SQL
            Statement stm = con.createStatement();
            ResultSet rs0 = stm.executeQuery("SELECT count(*)as nbligne FROM scolaire");
            rs0.next();
            int ligne = rs0.getInt("nbligne");
            enreg = new Object[ligne][titre.length];
            ResultSet rs1 = stm.executeQuery("SELECT * FROM scolaire where ID_SCOLAIRE like '%" + champ + "%' or ID_FKT like '%" + champ + "%' or ID_COM like '%" + champ + "%' or ETAT_SCOLAIRE like '%" + champ + "%' or NATURE_SCOLAIRE like '%" + champ + "%' or CATEG_SCOLAIRE like '%" + champ + "%'or ANNEE_SCOLAIRE like '%" + champ + "%' or ANNEE like '%" + champ + "%'");

            int i = 0;
            while (rs1.next()) {
                //Resultat rech
                enreg[i][0] = (Object) rs1.getString("ID_SCOLAIRE");
                enreg[i][1] = (Object) rs1.getString("ID_FKT");
                enreg[i][2] = (Object) rs1.getString("ID_COM");
                enreg[i][3] = (Object) rs1.getString("CATEG_SCOLAIRE");
                enreg[i][4] = (Object) rs1.getString("NATURE_SCOLAIRE");
                enreg[i][5] = (Object) rs1.getString("ETAT_SCOLAIRE");
                enreg[i][6] = (Object) rs1.getString("ELEVE_MASCULAIN");
                enreg[i][7] = (Object) rs1.getString("ELEVE_MASCULAIN");
                enreg[i][8] = (Object) rs1.getString("ELEVE_FEMININ");
                enreg[i][9] = (Object) rs1.getString("EFFECTIF_TOTAL");
                enreg[i][10] = (Object) rs1.getString("ANNEE_SCOLAIRE");
                enreg[i][11] = (Object) rs1.getString("ANNEE");

                i++;
                txtNumero.setText(rs1.getString(1));
                ComboCategorie.setSelectedItem(rs1.getString(4));
                ComboNature.setSelectedItem(rs1.getString(5));
                txtNombreInfras.setText(rs1.getString(6));
                ComboEtat.setSelectedItem(rs1.getString(7));
                txtMasculain.setText(rs1.getString(8));
                txtMasculain.setText(rs1.getString(9));
                txtTotal.setText(rs1.getString(10));
                txtAnnee.setText(rs1.getString(11));

            }
            TableScolaire.setModel(new DefaultTableModel(enreg, titre));
            jScrollPane1.setViewportView(TableScolaire);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (champ.equals("")) {
            try {
                AfficherScolaire();
                EffacerChampScolaire();

                // TODO add your handling code here:
            } catch (Exception ex) {
                Logger.getLogger(FormScolaire.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }           // TODO add your handling code here:  
    }
    */

    /*public void rechercheEntredeuxDate() {
        String[] titre = {"CODE", "FOKONTANY", "COMMUNE", "CATEGORIE ", "NATURE INFRAS", "NOMBRE INFRAS", " ETAT", "MASCULAIN", "FEMININ", "TOTAL", "ANNEE SCOLAIRE", "DATE"};
        Object[][] enreg = new Object[1][1];
        int nbligne;
        int i = 0;
        try {

            GestionScolaire gp = new GestionScolaire();
            ResultSet rs0 = gp.nombretrouverDeuxDate(date1, date2);
            rs0.next();
            nbligne = rs0.getInt(1);
            enreg = new Object[nbligne][titre.length];
            ResultSet rs1 = gp.getAllnombretrouverDeuxDate(date1, date2);
            while (rs1.next()) {
                enreg[i][0] = (Object) rs1.getString("ID_SCOLAIRE");
                enreg[i][1] = (Object) rs1.getString("ID_FKT");
                enreg[i][2] = (Object) rs1.getString("ID_COM");
                enreg[i][3] = (Object) rs1.getString("CATEG_SCOLAIRE");
                enreg[i][4] = (Object) rs1.getString("NATURE_SCOLAIRE");
                enreg[i][5] = (Object) rs1.getString("ETAT_SCOLAIRE");
                enreg[i][6] = (Object) rs1.getString("ELEVE_MASCULAIN");
                enreg[i][7] = (Object) rs1.getString("ELEVE_MASCULAIN");
                enreg[i][8] = (Object) rs1.getString("ELEVE_FEMININ");
                enreg[i][9] = (Object) rs1.getString("EFFECTIF_TOTAL");
                enreg[i][10] = (Object) rs1.getString("ANNEE_SCOLAIRE");
                enreg[i][11] = (Object) rs1.getString("ANNEE");

                i++;
            }
            TableScolaire.setModel(new DefaultTableModel(enreg, titre));
        } catch (Exception ex) {

        }
    }
*/
    private void Deplacement(int i) {
        try {//represente ligne et colonne i
            if (i == 0) {
                btnSuivant.setEnabled(true);
                btnPrecedent.setEnabled(false);
            } else if (i == TableScolaire.getRowCount() - 1) {//dernier enregistrement
                btnSuivant.setEnabled(false);
                btnPrecedent.setEnabled(true);
            } else {
                btnSuivant.setEnabled(true);
                btnPrecedent.setEnabled(true);
            }
            txtNumero.setText(TableScolaire.getValueAt(i, 0).toString());
            ComboCategorie.setSelectedItem(TableScolaire.getValueAt(i, 1).toString());
            ComboNature.setSelectedItem(TableScolaire.getValueAt(i, 2).toString());
            txtNombreInfras.setText(TableScolaire.getValueAt(i, 3).toString());
            ComboEtat.setSelectedItem(TableScolaire.getValueAt(i, 4).toString());
            txtMasculain.setText(TableScolaire.getValueAt(i, 5).toString());
            txtFeminin.setText(TableScolaire.getValueAt(i, 6).toString());
            txtTotal.setText(TableScolaire.getValueAt(i, 7).toString());
            txtAnneeScolaire.setText(TableScolaire.getValueAt(i, 8).toString());
            txtAnnee.setText(TableScolaire.getValueAt(i, 9).toString());

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
        TableScolaire = new javax.swing.JTable();
        btnPrecedent = new javax.swing.JButton();
        btnSuivant = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreInfras = new javax.swing.JTextField();
        ComboEtat = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        ComboCategorie = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtMasculain = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtFeminin = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        ComboNature = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        txtAnnee = new javax.swing.JTextField();
        txtAnneeScolaire = new javax.swing.JTextField();
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

        TableScolaire.setAutoCreateRowSorter(true);
        TableScolaire.setModel(new javax.swing.table.DefaultTableModel(
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
        TableScolaire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableScolaireMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableScolaire);
        TableScolaire.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 870, 140));

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 900, 230));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("SAISIE DES INFORMATIONS"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNumero.setEditable(false);
        jPanel6.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 200, -1));

        jLabel9.setText("Numero D'enregistrement");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Information sur l'Iinfrastructure Scolaire"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Categorie");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, -1));

        jLabel4.setText("Nature");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 100, -1));

        jLabel5.setText("Nombre Infrastructure");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 170, 20));

        txtNombreInfras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreInfrasKeyReleased(evt);
            }
        });
        jPanel5.add(txtNombreInfras, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 200, -1));

        ComboEtat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bonne", "Moyenne", "Mauvaise" }));
        jPanel5.add(ComboEtat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 200, -1));

        jLabel11.setText("Année");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 150, -1));

        ComboCategorie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Prescolaire", "Primaire", "Secondaire I Cycle", "Secondaire II Cycle", "Université" }));
        jPanel5.add(ComboCategorie, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 200, -1));

        jLabel7.setText("Etat");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 170, 20));

        jLabel21.setText("Effectif Eleve  Masculain ");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 160, 20));

        txtMasculain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMasculainKeyReleased(evt);
            }
        });
        jPanel5.add(txtMasculain, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 200, -1));

        jLabel22.setText("Effectif Eleve Feminin");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 150, 20));

        txtFeminin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFemininKeyReleased(evt);
            }
        });
        jPanel5.add(txtFeminin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 200, -1));

        jLabel23.setText("Année Scolaire");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 120, 20));

        txtTotal.setEditable(false);
        jPanel5.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 200, -1));

        ComboNature.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Publique", "Prive", "Technique" }));
        jPanel5.add(ComboNature, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 200, -1));

        jLabel28.setText("Effectif Total");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 120, 20));

        txtAnnee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnneeKeyReleased(evt);
            }
        });
        jPanel5.add(txtAnnee, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 200, -1));

        txtAnneeScolaire.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnneeScolaireKeyReleased(evt);
            }
        });
        jPanel5.add(txtAnneeScolaire, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 200, -1));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 410, 300));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 370));

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
        jPanel14.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 190, 40));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 440, 220));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRecherche.setToolTipText("Ecrire ici votre requette! votre  recherche va declancer automatiquement .");
        txtRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheKeyReleased(evt);
            }
        });
        jPanel15.add(txtRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, 30));

        btnComboRecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche.setText("RECHERCHER");
        btnComboRecherche.setToolTipText("Appuyer pour lancer la recherche");
        btnComboRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboRechercheActionPerformed(evt);
            }
        });
        jPanel15.add(btnComboRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 150, 30));

        jPanel13.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 440, 100));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 460, 370));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 600));
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

        if(!ID_FKT.equals(""))  AjoutScolaire();
        else JOptionPane.showMessageDialog(null, "Operation interdit!Pour ajouter, veuillez entrées dépuis le 'Voir ces districts' du menu principal"); 
    }//GEN-LAST:event_AjoutActionPerformed

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
        ModificationScolaire();
    }//GEN-LAST:event_ModifierActionPerformed

    private void SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerActionPerformed
        SupprimerScolaire();
    }//GEN-LAST:event_SupprimerActionPerformed

    private void ImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimerActionPerformed
        java.text.MessageFormat head = new java.text.MessageFormat("Info sur l''éducation du fokontany "+DesignFkt);
        java.text.MessageFormat end = new java.text.MessageFormat("Page {0,number,integer}");
        try {
            TableScolaire.print(JTable.PrintMode.FIT_WIDTH, head, end);
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la procedure d'impression: " + e.getMessage());
        }
    }//GEN-LAST:event_ImprimerActionPerformed

    private void ActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualiserActionPerformed
        try {
            AfficherScolaire();
        } catch (Exception ex) {
            Logger.getLogger(FormSanitaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ActualiserActionPerformed

    private void txtRechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheKeyReleased
        
    }//GEN-LAST:event_txtRechercheKeyReleased

    private void btnPrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrecedentActionPerformed
        Precedent();
    }//GEN-LAST:event_btnPrecedentActionPerformed

    private void btnSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuivantActionPerformed
        Suivant();
    }//GEN-LAST:event_btnSuivantActionPerformed

   
    private void TableScolaireMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableScolaireMouseClicked
        ClickTableScolaire();
    }//GEN-LAST:event_TableScolaireMouseClicked

    private void Calcule() {
        int i, j, resultat;
        String Chaine1, Chaine2, Chaine3;

        // récuperation de données au format String... 
        Chaine1 = txtMasculain.getText();
        Chaine2 = txtFeminin.getText();
        
        

        // converson en int 
        i = Integer.parseInt(Chaine1);
        j = Integer.parseInt(Chaine2);
        

        // addition 
        resultat = i + j;

        // conversion en chaine 
        Integer rr = new Integer(resultat);
        Chaine3 = rr.toString();

        // on affiche le résultat 
        txtTotal.setText(Chaine3);
    }
    private void txtFemininKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFemininKeyReleased
        String ChampCodeDoss = txtFeminin.getText();
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
        txtFeminin.setText(ChampCodeDoss);
        Calcule();
    }//GEN-LAST:event_txtFemininKeyReleased

    private void btnComboRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboRechercheActionPerformed
        // TODO add your handling code here:
        try {
            String champ = txtRecherche.getText();
            
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("CODE");
            dt.addColumn("CATEGORIE");
            dt.addColumn("NATURE");
            dt.addColumn("NOMBRE INFRAS");
            dt.addColumn("ETAT");
            dt.addColumn("NOMBRE MASCULAIN");
            dt.addColumn("NOMBRE FEMININ");
            dt.addColumn("TOTAL");
            dt.addColumn("ANNEE SCOLAIRE");
            dt.addColumn("ANNEE");
            TableScolaire.setModel(dt);
            rs =ID_FKT.equals("") ?  c.executeQuery("SELECT * FROM scolaire where CATEG_SCOLAIRE like '%" + champ + "%' or NOMBRE_SCOLAIRE = '" + champ + "' or NATURE_SCOLAIRE like '%" + champ + "%' or ETAT_SCOLAIRE like '%" + champ + "%' or ELEVE_MASCULAIN = '" + champ + "' or ELEVE_FEMININ = '" + champ + "' or EFFECTIF_TOTAL = '" + champ + "' or ANNEE_SCOLAIRE like '%" + champ + "%' or ANNEE = '" + champ + "'") :  c.executeQuery("SELECT * FROM scolaire where ID_FKT = '" + ID_FKT + "' AND (CATEG_SCOLAIRE like '%" + champ + "%' or NOMBRE_SCOLAIRE = '" + champ + "' or NATURE_SCOLAIRE like '%" + champ + "%' or ETAT_SCOLAIRE like '%" + champ + "%' or ELEVE_MASCULAIN = '" + champ + "' or ELEVE_FEMININ = '" + champ + "' or EFFECTIF_TOTAL = '" + champ + "' or ANNEE_SCOLAIRE like '%" + champ + "%' or ANNEE = '" + champ + "')");
            while (rs.next()) {
               String ID_SCOLAIRE = rs.getString("ID_SCOLAIRE");
                String CATEG_SCOLAIRE = rs.getString("CATEG_SCOLAIRE");
                String NATURE_SCOLAIRE = rs.getString("NATURE_SCOLAIRE");
                String NOMBRE_SCOLAIRE = rs.getString("NOMBRE_SCOLAIRE");
                String ETAT_SCOLAIRE = rs.getString("ETAT_SCOLAIRE");
                String ELEVE_MASCULAIN = rs.getString("ELEVE_MASCULAIN");
                String ELEVE_FEMININ = rs.getString("ELEVE_FEMININ");
                String EFFECTIF_TOTAL = rs.getString("EFFECTIF_TOTAL");
                String ANNEE_SCOLAIRE = rs.getString("ANNEE_SCOLAIRE");
                String ANNEE = rs.getString("ANNEE");
                Object[] scolaire = {ID_SCOLAIRE, CATEG_SCOLAIRE, NATURE_SCOLAIRE, NOMBRE_SCOLAIRE, ETAT_SCOLAIRE, ELEVE_MASCULAIN, ELEVE_FEMININ, EFFECTIF_TOTAL, ANNEE_SCOLAIRE, ANNEE};
                dt.addRow(scolaire);
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnComboRechercheActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FormMenuPrincipale a = new FormMenuPrincipale(); 
        a.setVisible(true);        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNombreInfrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreInfrasKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtNombreInfras.getText();
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
        txtNombreInfras.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtNombreInfrasKeyReleased

    private void txtMasculainKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasculainKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtMasculain.getText();
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
        txtMasculain.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtMasculainKeyReleased

    private void txtAnneeScolaireKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnneeScolaireKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtAnneeScolaire.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 9;
        if (ChampCodeDoss.matches("[0-9,-,/]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre inferieur ou egale a 9 caracteres,ex:2013-2014 ou 2013/2014, merci!!");
            }
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre un deux annee separe par - ou /, merci!!");
        }
        txtAnneeScolaire.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtAnneeScolaireKeyReleased

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
            java.util.logging.Logger.getLogger(FormScolaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormScolaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormScolaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormScolaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FormScolaire().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualiser;
    private javax.swing.JButton Ajout;
    private javax.swing.JComboBox ComboCategorie;
    private javax.swing.JComboBox ComboEtat;
    private javax.swing.JComboBox ComboNature;
    private javax.swing.JButton Fermer;
    private javax.swing.JButton Imprimer;
    private javax.swing.JButton Modifier;
    private javax.swing.JButton Supprimer;
    private javax.swing.JTable TableScolaire;
    private javax.swing.JButton btnComboRecherche;
    private javax.swing.JButton btnPrecedent;
    private javax.swing.JButton btnSuivant;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JTextField txtAnnee;
    private javax.swing.JTextField txtAnneeScolaire;
    private javax.swing.JTextField txtFeminin;
    private javax.swing.JTextField txtMasculain;
    private javax.swing.JTextField txtNombreInfras;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRecherche;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
