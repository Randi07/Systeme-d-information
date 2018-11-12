package Fokontany;

import Connexion.ConnexionBase;
import OtherFolder.FormMenuPrincipale;
import java.awt.Color;
import static java.awt.Component.TOP_ALIGNMENT;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author JAHYA
 */
public class FormPopulation1 extends javax.swing.JFrame {

    int i, val, nbligne;
    public Connection con;
    public Statement st;
    public DefaultTableModel dt;
    public ResultSet res;
    String cont;
    public Object bouton2;
    public Object bouton;
//    public Object dt;
    public DefaultTableModel dtm;
    public ResultSet rs;
    public Statement St;
    public boolean animated;
    //debut données a faire passer
    public String DesignCom = "";
    public String NbFokotany = "";
    public String HistoriqueCom = "";
    public String SuperficieCom = "";
    public String DesignFkt="";
    public String DesignDist="";
    public String SuperficieDist="";
    public String HistoriqueDist="";
    public String ID_FKT="";
    public String ID_COM="";
    public String ID_DIST="";
    //fin de données a faire passer
    public Thread t;

    public FormPopulation1() {
        initComponents();
        //ComboFokontany();

        this.setTitle("INFORMATION SUR LES POPULATIONS AU NIVEAU FOKONTANY");
        

    }

    public void ClickTablePopulation() {

        int ligneSelect = TablePopulation.getSelectedRow();
        txtNumero.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 0)));
        txtZero.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 1)));
        txtSix.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 2)));
        txtDixHuit.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 3)));
        txtSoixante.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 4)));
        txtHomme.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 5)));
        txtFemme.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 6)));
        txtTotal.setText(String.valueOf(TablePopulation.getValueAt(ligneSelect, 7)));
        txtAnnee.setText(TablePopulation.getValueAt(TablePopulation.getSelectedRow(), 8).toString());

    }

    private void ImprimerTablePopulation() {
        java.text.MessageFormat head = new java.text.MessageFormat("Info sur la population du fokontany "+DesignFkt);
        java.text.MessageFormat end = new java.text.MessageFormat("Page {0,number,integer}");
        try {
            TablePopulation.print(JTable.PrintMode.FIT_WIDTH, head, end);
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la procedure d'impression: " + e.getMessage());
        }
    }

    private void Histogramme() {
        String ZERO = txtZero.getText();
        String SIX = txtSix.getText();
        String DIXHUIT = txtDixHuit.getText();
        String SOIXANTE = txtSoixante.getText();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(new Integer(ZERO), "Effectif", "Agé de 0 à 5");
        dataset.setValue(new Integer(SIX), "Effectif", "Agé de 6 à 17");
        dataset.setValue(new Integer(DIXHUIT), "Effectif", "Agé de 18 à 60");
        dataset.setValue(new Integer(SOIXANTE), "Effectif", "+ 60 ans");

        JFreeChart chart = ChartFactory.createBarChart3D("Effectif selon les categories d'ages", "Categorie d'Age", "Effectif de la population", dataset, PlotOrientation.VERTICAL, false, true, true);

        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.BLACK);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLUE);
        ChartFrame frame = new ChartFrame("HISTOGRAMME DE LA POPULATION", chart);
        frame.setVisible(true);
        frame.setSize(550, 700);

    }

    private void Graphique() {
        String ZERO = txtZero.getText();
        String SIX = txtSix.getText();
        String DIXHUIT = txtDixHuit.getText();
        String SOIXANTE = txtSoixante.getText();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(new Integer(ZERO), "Effectif", "Agé de 0 à 5");
        dataset.setValue(new Integer(SIX), "Effectif", "Agé de 6 à 17");
        dataset.setValue(new Integer(DIXHUIT), "Effectif", "Agé de 18 à 60");
        dataset.setValue(new Integer(SOIXANTE), "Effectif", "+ 60 ans");

        JFreeChart chart = ChartFactory.createLineChart3D("Effectif selon les categories d'ages", "Categorie d'Age", "Effectif de la population", dataset, PlotOrientation.VERTICAL, false, true, true);

        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.BLACK);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLUE);
        ChartFrame frame = new ChartFrame("HISTOGRAMME DE LA POPULATION", chart);
        frame.setVisible(true);
        frame.setSize(550, 700);

    }

    private void DiagrammeCirculaire() {

        int ZERO, SIX, DIXHUIT, SOIXANTE;
        ZERO = (Integer.parseInt(txtZero.getText()));
        SIX = (Integer.parseInt(txtSix.getText()));
        DIXHUIT = (Integer.parseInt(txtDixHuit.getText()));
        SOIXANTE = (Integer.parseInt(txtSoixante.getText()));

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Agé de 0 à 5", new Integer(ZERO));
        pieDataset.setValue("Agé de 6 à 17", new Integer(SIX));
        pieDataset.setValue("Agé de 18 à 60", new Integer(DIXHUIT));
        pieDataset.setValue("+ 60 ans", new Integer(SOIXANTE));

        JFreeChart chart = ChartFactory.createPieChart3D("REPRESENTATION SOUS FORME D'UN GRAPHE CIRCULAIRE", pieDataset, true, true, true);
        PiePlot3D P = (PiePlot3D) chart.getPlot();
        P.setBackgroundAlpha(TOP_ALIGNMENT);
        ChartFrame frame = new ChartFrame("Graphe", chart);
        frame.setVisible(true);
        frame.setSize(600, 400);
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file1 = new File("graphecirculaire.png");
            ChartUtilities.saveChartAsJPEG(file1, chart, 600, 400, info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void first()
    {
        try {
            AfficherPopulation();

        } catch (Exception ex) {
            Logger.getLogger(FormPopulation1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void AfficherPopulation() throws Exception {

        try {
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("0-5");
            dt.addColumn("6-17");
            dt.addColumn("18-60");
            dt.addColumn(" + 60");
            dt.addColumn("MASCULAIN");
            dt.addColumn("FEMININ");
            dt.addColumn("TOTAL");
            dt.addColumn("ANNEE");
            TablePopulation.setModel(dt);
            GestionPopulation gcom = new GestionPopulation();
            rs = ID_FKT.equals("") ? gcom.getAll(): c.executeQuery("Select * From population where ID_FKT = '" + ID_FKT + "' order by ID_FKT asc");
            while (rs.next()) {

                String ID_POPULATION = rs.getString("ID_POPULATION");
                String ZERO = rs.getString("ZERO");
                String SIX = rs.getString("SIX");
                String DIXHUIT = rs.getString("DIXHUIT");
                String SOIXANTE = rs.getString("SOIXANTE");
                String EFFECTIF_MASCULAIN = rs.getString("EFFECTIF_MASCULAIN");
                String EFFECTIF_FEMININ = rs.getString("EFFECTIF_FEMININ");
                String EFFECTIF_TOTAL = rs.getString("EFFECTIF_TOTAL");
                String ANNEE = rs.getString("ANNEE");
                Object[] population = {ID_POPULATION, ZERO, SIX, DIXHUIT, SOIXANTE, EFFECTIF_MASCULAIN, EFFECTIF_FEMININ, EFFECTIF_TOTAL, ANNEE};
                dt.addRow(population);
                
            }
            EffacerChampPopulation();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormPopulation1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void AjoutPopulation() {
        String ID_POPULATION = txtNumero.getText();
        String ZERO = txtZero.getText();
        String SIX = txtSix.getText();
        String DIXHUIT = txtDixHuit.getText();
        String SOIXANTE = txtSoixante.getText();
        String EFFECTIF_MASCULAIN = txtFemme.getText();
        String EFFECTIF_FEMININ = txtHomme.getText();
        String EFFECTIF_TOTAL = txtTotal.getText();
        String ANNEE = txtAnnee.getText();

        ClassPopulation com = new ClassPopulation(ID_POPULATION, ZERO, SIX, DIXHUIT, SOIXANTE, EFFECTIF_MASCULAIN, EFFECTIF_FEMININ, EFFECTIF_TOTAL, ANNEE);
        com.ID_FKT = ID_FKT;
        if (EFFECTIF_MASCULAIN.equals("") || ANNEE.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            try {
                GestionPopulation gcom = new GestionPopulation();
                gcom.insert(com);
                EffacerChampPopulation();
                AfficherPopulation();
                JOptionPane.showMessageDialog(null, "Insertion de l'information reussi!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
        }
    }

    public void ModificationPopulation() {
        String ID_POPULATION = txtNumero.getText();
        String ZERO = txtZero.getText();
        String SIX = txtSix.getText();
        String DIXHUIT = txtDixHuit.getText();
        String SOIXANTE = txtSoixante.getText();
        String EFFECTIF_MASCULAIN = txtFemme.getText();
        String EFFECTIF_FEMININ = txtHomme.getText();
        String EFFECTIF_TOTAL = txtTotal.getText();
        String ANNEE = txtAnnee.getText();

        ClassPopulation com = new ClassPopulation(ID_POPULATION,ZERO, SIX, DIXHUIT, SOIXANTE, EFFECTIF_MASCULAIN, EFFECTIF_FEMININ, EFFECTIF_TOTAL, ANNEE);
        com.ID_FKT = ID_FKT;
        if (txtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller remplir les champs");
        } else {
            try {
                JOptionPane.showMessageDialog(null,"voulez vous Modifier et enregistrer l'information numero   " + ID_POPULATION + "?");
                GestionPopulation gcom = new GestionPopulation();
                gcom.update(ID_POPULATION, com);
                EffacerChampPopulation();
                AfficherPopulation();
                JOptionPane.showMessageDialog(null, "Modification de l'information  numero   " + ID_POPULATION +" reusi");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " Annulation de la Modification de l'information  numero   " + ID_POPULATION);
                System.out.println(ex);
                ex.printStackTrace();

            }
        }
    }

    private void SupprimerPopulation() {
        String NUMERO = txtNumero.getText();
        if (txtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuiller selectionner un champ pour effectuer cette suppression");
        } else {
            try {

                JOptionPane.showMessageDialog(null,"Voulez vous supprimer l'enregistrement  du fokontany " + ID_FKT + " numero " + NUMERO + " ?");
                GestionPopulation gv = new GestionPopulation();
                gv.Supprimer(NUMERO);
                EffacerChampPopulation();
                AfficherPopulation();
                JOptionPane.showMessageDialog(null, " L'enregistrement du fokontany " + ID_FKT + " numero " + NUMERO+ " a ete supprime avec succes! ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " Annulation de la suppression de l'enregistrement du fokontany " + ID_FKT + " numero" + NUMERO + "! ");
                System.out.println();
            }
        }

    }

    public void EffacerChampPopulation() {

        txtNumero.setText("");
        txtZero.setText("");
        txtSix.setText("");
        txtDixHuit.setText("");
        txtSoixante.setText("");
        txtFemme.setText("");
        txtHomme.setText("");
        txtTotal.setText("");
        txtAnnee.setText("");
        txtComboRecherche.setText("");
    }
/*
    private void recherchetout() {
        String champ = txtComboRecherche.getText();
        String[] titre = {"CODE", "FOKONTANY", "COMMUNE", "0-5 ans ", "6-17 ans", "18-60 ans", " +60 ans", "MASCULAIN", "FEMININ", "TOTAL", "DATE"};
        Object[][] enreg = new Object[1][1];
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            //Etablissement de la connection à localhost et à la base de données siramm
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sirmania", "root", "");

            //Acceder à la base de données SIRMANIA
            //Création de la variable stm pour lancer la requete SQL
            Statement stm = con.createStatement();
            ResultSet rs0 = stm.executeQuery("SELECT count(*)as nbligne FROM population");
            rs0.next();
            int ligne = rs0.getInt("nbligne");
            enreg = new Object[ligne][titre.length];
            ResultSet rs1 = stm.executeQuery("SELECT * FROM population where ID_POPULATION like '%" + champ + "%' or ID_FKT like '%" + champ + "%' or ANNEE like '%" + champ + "%'");

            int i = 0;
            while (rs1.next()) {
                //Resultat rech
                enreg[i][0] = (Object) rs1.getString("ID_POPULATION");
                enreg[i][1] = (Object) rs1.getString("ID_FKT");
                enreg[i][2] = (Object) rs1.getString("ZERO");
                enreg[i][3] = (Object) rs1.getString("SIX");
                enreg[i][4] = (Object) rs1.getString("DIXHUIT");
                enreg[i][5] = (Object) rs1.getString("SOIXANTE");
                enreg[i][6] = (Object) rs1.getString("EFFECTIF_MASCULAIN");
                enreg[i][7] = (Object) rs1.getString("EFFECTIF_FEMININ");
                enreg[i][8] = (Object) rs1.getString("EFFECTIF_TOTAL");
                enreg[i][9] = (Object) rs1.getString("ANNEE");

                i++;
                txtNumero.setText(rs1.getString(1));
                txtZero.setText(rs1.getString(2));
                txtSix.setText(rs1.getString(3));
                txtDixHuit.setText(rs1.getString(4));
                txtSoixante.setText(rs1.getString(5));
                txtHomme.setText(rs1.getString(6));
                txtFemme.setText(rs1.getString(7));
                txtTotal.setText(rs1.getString(8));
                txtAnnee.setText(rs1.getString(9));

            }
            TablePopulation.setModel(new DefaultTableModel(enreg, titre));
            jScrollPane1.setViewportView(TablePopulation);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (champ.equals("")) {
            try {
                AfficherPopulation();
                EffacerChampPopulation();

                // TODO add your handling code here:
            } catch (Exception ex) {
                Logger.getLogger(FormPopulation1.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }           // TODO add your handling code here:  
    }
*/
    private void Calcule() {
        int i, j, k, l,m, resultat;
        String Chaine1, Chaine2, Chaine3, Chaine4, Chaine5, Chaine6;

        // récuperation de données au format String... 
        Chaine1 = txtZero.getText();
        Chaine2 = txtSix.getText();
        Chaine3 = txtDixHuit.getText();
        Chaine4 = txtSoixante.getText();
        Chaine5 = txtHomme.getText();

        // converson en int 
        i = Integer.parseInt(Chaine1);
        j = Integer.parseInt(Chaine2);
        k = Integer.parseInt(Chaine3);
        l = Integer.parseInt(Chaine4);
        m = Integer.parseInt(Chaine5);
        
        // addition 
        resultat = i + j + k + l;
        
        // conversion en chaine 
        Integer rr = new Integer(resultat);
        Chaine6 = rr.toString();

        // on affiche le résultat 
        if (m>resultat)
        {
            txtHomme.setText("");
            JOptionPane.showMessageDialog(null, "L'effectif masculin doit etre inferieur au nombre total de la population!");
        }
        else {
        m = resultat-m;
        txtTotal.setText(Chaine6);
        txtFemme.setText(String.valueOf(m));}

   /* public void rechercheEntredeuxDate() {
        String[] titre = {"CODE", "FOKONTANY", "COMMUNE", "0-5 ans ", "6-17 ans", "18-60 ans", " +60 ans", "MASCULAIN", "FEMININ", "TOTAL", "DATE REC"};
        Object[][] enreg = new Object[1][1];
        int nbligne;
        int i = 0;
        try {

            GestionPopulation gp = new GestionPopulation();
            ResultSet rs0 = gp.nombretrouverDeuxDate(date1, date2);
            rs0.next();
            nbligne = rs0.getInt(1);
            enreg = new Object[nbligne][titre.length];
            ResultSet rs1 = gp.getAllnombretrouverDeuxDate(date1, date2);
            while (rs1.next()) {
                enreg[i][0] = (Object) rs1.getString("ID_POPULATION");
                enreg[i][1] = (Object) rs1.getString("ID_FKT");
                enreg[i][2] = (Object) rs1.getString("ID_COM");
                enreg[i][3] = (Object) rs1.getString("ZERO");
                enreg[i][4] = (Object) rs1.getString("SIX");
                enreg[i][5] = (Object) rs1.getString("DIXHUIT");
                enreg[i][6] = (Object) rs1.getString("SOIXANTE");
                enreg[i][7] = (Object) rs1.getString("EFFECTIF_MASCULAIN");
                enreg[i][8] = (Object) rs1.getString("EFFECTIF_FEMININ");
                enreg[i][9] = (Object) rs1.getString("EFFECTIF_TOTAL");
                enreg[i][10] = (Object) rs1.getString("ANNEE");

                i++;
            }
            TablePopulation.setModel(new DefaultTableModel(enreg, titre));
        } catch (Exception ex) {

        }
    }
*/
   }
    private void Deplacement(int i) {
        try {//represente ligne et colonne i
            if (i == 0) {
                btnSuivant.setEnabled(true);
                btnPrecedent.setEnabled(false);
            } else if (i == TablePopulation.getRowCount() - 1) {//dernier enregistrement
                btnSuivant.setEnabled(false);
                btnPrecedent.setEnabled(true);
            } else {
                btnSuivant.setEnabled(true);
                btnPrecedent.setEnabled(true);
            }
            txtNumero.setText(TablePopulation.getValueAt(i, 0).toString());
            txtZero.setText(TablePopulation.getValueAt(i, 1).toString());
            txtSix.setText(TablePopulation.getValueAt(i, 2).toString());
            txtDixHuit.setText(TablePopulation.getValueAt(i, 3).toString());
            txtSoixante.setText(TablePopulation.getValueAt(i, 4).toString());
            txtHomme.setText(TablePopulation.getValueAt(i, 5).toString());
            txtFemme.setText(TablePopulation.getValueAt(i, 6).toString());
            txtTotal.setText(TablePopulation.getValueAt(i, 7).toString());
            txtAnnee.setText(TablePopulation.getValueAt(i, 8).toString());

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
        TablePopulation = new javax.swing.JTable();
        btnPrecedent = new javax.swing.JButton();
        btnSuivant = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDixHuit = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSoixante = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtHomme = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtFemme = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtZero = new javax.swing.JTextField();
        txtSix = new javax.swing.JTextField();
        txtAnnee = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnFermer = new javax.swing.JButton();
        btnAjout = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnImprimer = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        txtComboRecherche = new javax.swing.JTextField();
        btnComboRecherche = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("LISTE DES DONNEES ENREGISTREES IMPRIMABLE"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablePopulation.setAutoCreateRowSorter(true);
        TablePopulation.setModel(new javax.swing.table.DefaultTableModel(
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
        TablePopulation.setToolTipText("Cliquer une ligne pour afficher, modifier, supprimer l'enregistrement.");
        TablePopulation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePopulationMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePopulation);
        TablePopulation.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 840, 160));

        btnPrecedent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/button_prev.png"))); // NOI18N
        btnPrecedent.setText("Precedent");
        btnPrecedent.setToolTipText("Appuyer pour voir l'enregistrement precedent.");
        btnPrecedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecedentActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrecedent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, -1));

        btnSuivant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/button_next_1.png"))); // NOI18N
        btnSuivant.setText("Suivant");
        btnSuivant.setToolTipText("Appuyer pour voir l'enregistrement suivant.");
        btnSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuivantActionPerformed(evt);
            }
        });
        jPanel2.add(btnSuivant, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 120, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 870, 230));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("SAISIE DES INFORMATIONS"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNumero.setEditable(false);
        txtNumero.setToolTipText("Code ou numero d'enregistrement de l'effectif population.");
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });
        jPanel6.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 200, -1));

        jLabel9.setText("Code D'enregistrement");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 150, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Information sur la Population"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Age de 0-5 ans");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 24, 100, 20));

        jLabel4.setText("Age de 6-17 ans");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, -1));

        jLabel5.setText("Age de 18-60 ans");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 170, 20));

        txtDixHuit.setToolTipText(" Ce champ doit remplir  par de/des chiffres");
        txtDixHuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDixHuitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDixHuitKeyReleased(evt);
            }
        });
        jPanel5.add(txtDixHuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 200, -1));

        jLabel11.setText("Année");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 150, -1));

        jLabel7.setText("Age + de 60 ans");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, 20));

        txtSoixante.setToolTipText(" Ce champ doit remplir  par de/des chiffres");
        txtSoixante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoixanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoixanteKeyReleased(evt);
            }
        });
        jPanel5.add(txtSoixante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 200, -1));

        jLabel21.setText("Effectif  Masculain ");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 160, 20));

        txtHomme.setToolTipText(" Ce champ doit remplir  par de/des chiffres");
        txtHomme.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHommeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHommeKeyReleased(evt);
            }
        });
        jPanel5.add(txtHomme, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 200, -1));

        jLabel22.setText("Effectif  Feminin");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 150, 20));

        txtFemme.setEditable(false);
        txtFemme.setToolTipText(" Ce champ doit remplir  par de/des chiffres");
        txtFemme.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFemmeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFemmeKeyReleased(evt);
            }
        });
        jPanel5.add(txtFemme, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 200, -1));

        jLabel23.setText("Effectif Total");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 120, 20));

        txtTotal.setEditable(false);
        txtTotal.setToolTipText(" Ce champ doit remplir  par de/des chiffres");
        jPanel5.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 200, -1));

        txtZero.setToolTipText(" Ce champ doit remplir  par de/des chiffres");
        txtZero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtZeroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtZeroKeyReleased(evt);
            }
        });
        jPanel5.add(txtZero, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 200, -1));

        txtSix.setToolTipText(" Ce champ doit remplir  par de/des chiffres");
        txtSix.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSixKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSixKeyReleased(evt);
            }
        });
        jPanel5.add(txtSix, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 200, -1));

        txtAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnneeActionPerformed(evt);
            }
        });
        txtAnnee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnneeKeyReleased(evt);
            }
        });
        jPanel5.add(txtAnnee, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 200, -1));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 410, 310));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 410));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("ENREGISTREMENT DE L'OPERATION ET RECHERCHE"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("OPERATION SUR L'ENREGISTREMENT"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFermer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/fermer-icon.png"))); // NOI18N
        btnFermer.setText("RETOUR");
        btnFermer.setToolTipText("Appuyer si vous voulez fermer cette fenetre");
        btnFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFermerActionPerformed(evt);
            }
        });
        jPanel9.add(btnFermer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 190, 30));

        btnAjout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/add.gif"))); // NOI18N
        btnAjout.setText("INSERTION");
        btnAjout.setToolTipText("Appuyer pour ajouter et enregistrer votre donnees");
        btnAjout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutActionPerformed(evt);
            }
        });
        jPanel9.add(btnAjout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 30));

        btnModifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/A_PencilDyn_Sm_N.png"))); // NOI18N
        btnModifier.setText("MODIFICATION");
        btnModifier.setToolTipText("Appuyer pour modifier ce donnees");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });
        jPanel9.add(btnModifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 190, 30));

        btnSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconSupprimer.png"))); // NOI18N
        btnSupprimer.setText("SUPPRESSION");
        btnSupprimer.setToolTipText("Appuyer si vous voulez supprimer ce donnees");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });
        jPanel9.add(btnSupprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 190, -1));

        btnImprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer-icon.png"))); // NOI18N
        btnImprimer.setText("IMPRESSION");
        btnImprimer.setToolTipText("Appuyer si vous voulez imprimer les donnees deja enregistrees");
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimerActionPerformed(evt);
            }
        });
        jPanel9.add(btnImprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 190, 30));

        btnActualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/refresh.png"))); // NOI18N
        btnActualiser.setText("ACTUALISER");
        btnActualiser.setToolTipText("Appuyer si vous voulez rafraichir la liste");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });
        jPanel9.add(btnActualiser, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 190, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/30.jpg"))); // NOI18N
        jButton2.setText("ACCEUIL");
        jButton2.setActionCommand("DETAILS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 190, 40));

        jPanel10.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 410, 220));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("RECHERCHE SUR LES DONNEES DEJA ENREGISTREES"));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtComboRecherche.setToolTipText("Saisir votre recherche ici puis lancer a l'aide de la boutton RECHERCHER");
        jPanel11.add(txtComboRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 220, 30));

        btnComboRecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/IconeSearch.png"))); // NOI18N
        btnComboRecherche.setText("RECHERCHER");
        btnComboRecherche.setToolTipText("Appuyer pour lancer la recherche");
        btnComboRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboRechercheActionPerformed(evt);
            }
        });
        jPanel11.add(btnComboRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 150, 30));

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 410, 140));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 430, 410));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 640));
        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnComboRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboRechercheActionPerformed
        try {
            String champ = txtComboRecherche.getText();
            
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("CODE");
            dt.addColumn("0-5");
            dt.addColumn("6-17");
            dt.addColumn("18-60");
            dt.addColumn(" + 60");
            dt.addColumn("MASCULAIN");
            dt.addColumn("FEMININ");
            dt.addColumn("TOTAL");
            dt.addColumn("ANNEE");
            TablePopulation.setModel(dt);
            rs = ID_FKT.equals("") ? c.executeQuery("SELECT * FROM population ZERO = '" + champ + "' or SIX = '" + champ + "' or DIXHUIT = '" + champ + "' or SOIXANTE = '" + champ + "' or EFFECTIF_MASCULAIN = '" + champ + "' or EFFECTIF_FEMININ = '" + champ + "' or EFFECTIF_TOTAL = '" + champ + "' or ANNEE = '" + champ + "'") : c.executeQuery("SELECT * FROM population where ID_FKT = '" + ID_FKT + "' AND (ZERO = '" + champ + "' or SIX = '" + champ + "' or DIXHUIT = '" + champ + "' or SOIXANTE = '" + champ + "' or EFFECTIF_MASCULAIN = '" + champ + "' or EFFECTIF_FEMININ = '" + champ + "' or EFFECTIF_TOTAL = '" + champ + "' or ANNEE = '" + champ + "')");
            while (rs.next()) {
                String ID_POPULATION = rs.getString("ID_POPULATION");
                String ZERO = rs.getString("ZERO");
                String SIX = rs.getString("SIX");
                String DIXHUIT = rs.getString("DIXHUIT");
                String SOIXANTE = rs.getString("SOIXANTE");
                String EFFECTIF_MASCULAIN = rs.getString("EFFECTIF_MASCULAIN");
                String EFFECTIF_FEMININ = rs.getString("EFFECTIF_FEMININ");
                String EFFECTIF_TOTAL = rs.getString("EFFECTIF_TOTAL");
                String ANNEE = rs.getString("ANNEE");
                Object[] population = {ID_POPULATION, ZERO, SIX, DIXHUIT, SOIXANTE, EFFECTIF_MASCULAIN, EFFECTIF_FEMININ, EFFECTIF_TOTAL, ANNEE};
                dt.addRow(population);
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        }

        private void Rien() {
    }//GEN-LAST:event_btnComboRechercheActionPerformed

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        try {
            AfficherPopulation();
        } catch (Exception ex) {
            Logger.getLogger(FormPopulation1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualiserActionPerformed

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
        ImprimerTablePopulation();
    }//GEN-LAST:event_btnImprimerActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        SupprimerPopulation();
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        ModificationPopulation();
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnAjoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutActionPerformed
        if(!ID_FKT.equals("")) AjoutPopulation();
        else JOptionPane.showMessageDialog(null, "Operation interdit!Pour ajouter, veuillez entrées dépuis le 'Voir ces districts' du menu principal");
    }//GEN-LAST:event_btnAjoutActionPerformed

    private void btnFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFermerActionPerformed
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
    }//GEN-LAST:event_btnFermerActionPerformed

    private void txtSixKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSixKeyReleased
        ControleChamp(1);
    }//GEN-LAST:event_txtSixKeyReleased

    private void txtSixKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSixKeyPressed

    }//GEN-LAST:event_txtSixKeyPressed

    private void txtZeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtZeroKeyReleased
        
        ControleChamp(0);
    }//GEN-LAST:event_txtZeroKeyReleased

    private void txtZeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtZeroKeyPressed
      
    }//GEN-LAST:event_txtZeroKeyPressed

    private void txtFemmeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFemmeKeyReleased
        //ControleChampFemme();
    }//GEN-LAST:event_txtFemmeKeyReleased

    private void txtFemmeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFemmeKeyPressed
        //ControleChampFemme();
    }//GEN-LAST:event_txtFemmeKeyPressed

    private void txtHommeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHommeKeyReleased
        ControleChamp(3);
        Calcule();
    }//GEN-LAST:event_txtHommeKeyReleased

    private void txtHommeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHommeKeyPressed
        
    }//GEN-LAST:event_txtHommeKeyPressed

    private void txtSoixanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoixanteKeyReleased
        ControleChamp(4);
        
    }//GEN-LAST:event_txtSoixanteKeyReleased

    private void txtSoixanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoixanteKeyPressed
        
    }//GEN-LAST:event_txtSoixanteKeyPressed

    private void txtDixHuitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDixHuitKeyReleased
        ControleChamp(2);
    }//GEN-LAST:event_txtDixHuitKeyReleased

    private void txtDixHuitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDixHuitKeyPressed
        ControleChamp(2);
    }//GEN-LAST:event_txtDixHuitKeyPressed

    private void txtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyPressed
        
    }//GEN-LAST:event_txtNumeroKeyPressed

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void btnSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuivantActionPerformed
        Suivant();
    }//GEN-LAST:event_btnSuivantActionPerformed

    private void btnPrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrecedentActionPerformed
        Precedent();
    }//GEN-LAST:event_btnPrecedentActionPerformed

    private void TablePopulationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePopulationMouseClicked
        ClickTablePopulation();
    }//GEN-LAST:event_TablePopulationMouseClicked

    private void txtAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnneeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FormMenuPrincipale a = new FormMenuPrincipale(); 
        a.setVisible(true);        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void ControleChamp(int a) {

        String ChampCodeDoss = a==0 ? txtZero.getText(): a==1 ? txtSix.getText(): a==2 ? txtDixHuit.getText(): a==3 ? txtHomme.getText():txtSoixante.getText();
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
        switch (a)
                    {
                case 0:
                    txtZero.setText(ChampCodeDoss);
                case 1:
                    txtSix.setText(ChampCodeDoss);
                case 2:
                    txtDixHuit.setText(ChampCodeDoss);
                case 3:
                    txtHomme.setText(ChampCodeDoss);
                default:
                    txtSoixante.setText(ChampCodeDoss);
          }
            
    }
/*
    private void ControleChampFemme() {

        String ChampCodeDoss = txtFemme.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 10;
        if (txtFemme.getText().matches("[0-9]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre inferieur ou egale a 10 chiffres pas des caracteres, merci!!");
            }
            txtFemme.setText(ChampCodeDoss);
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            txtFemme.setText(ChampCodeDoss);
            JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre un/des chiffre(s), merci!!");

        }
    }
*/

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
            java.util.logging.Logger.getLogger(FormPopulation1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPopulation1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPopulation1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPopulation1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FormPopulation1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePopulation;
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnAjout;
    private javax.swing.JButton btnComboRecherche;
    private javax.swing.JButton btnFermer;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnPrecedent;
    private javax.swing.JButton btnSuivant;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAnnee;
    private javax.swing.JTextField txtComboRecherche;
    private javax.swing.JTextField txtDixHuit;
    private javax.swing.JTextField txtFemme;
    private javax.swing.JTextField txtHomme;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSix;
    private javax.swing.JTextField txtSoixante;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtZero;
    // End of variables declaration//GEN-END:variables
}
