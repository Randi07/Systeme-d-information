/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtherFolder;

import Connexion.ConnexionBase;
import District.FormDistrict;
import Fokontany.FormPopulation1;
import Fokontany.FormSanitaire;
import Fokontany.FormScolaire;
import Fokontany.FormSecurite;
import Fokontany.FormSportive;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.util.Date;*/
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author JAHYA
 */
public class FormMenuPrincipale extends javax.swing.JFrame {

    /**
     * Creates new form FormMenuPrincipale
     */
    public Connection con;
    public Statement st;
    public DefaultTableModel dt;
    public ResultSet rs;
    public Integer radioG = 0;
    public Integer radioD;
    private String ID = "";
    private String DESIGN= "";
    
    public FormMenuPrincipale() {
        initComponents();
        DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("DESIGNATION");
            TableRecherche.setModel(dt);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtHistoriqueCom = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAnneeDebut = new javax.swing.JTextField();
        txtAnneeFin = new javax.swing.JTextField();
        radio01 = new javax.swing.JRadioButton();
        radio02 = new javax.swing.JRadioButton();
        radio03 = new javax.swing.JRadioButton();
        radio00 = new javax.swing.JRadioButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRecherche = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtRecherche = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtHistoriqueCom1 = new javax.swing.JLabel();
        txtAnneeDebut1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/LogoPrint.png"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("VOIR CES DISTRICT");
        jButton1.setToolTipText("Acceuil");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 40));

        jButton2.setText("POPULATION");
        jButton2.setToolTipText("Appuyer pour voir les differents Fokontany");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, 40));

        jButton3.setText("SANTE");
        jButton3.setToolTipText("Appuyer pour voir les differentes communes");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 130, 40));

        jButton4.setText("EDUCATION");
        jButton4.setToolTipText("Appuyer pour voir l'information sur la region");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 130, 40));

        jButton5.setText("SECURITE");
        jButton5.setToolTipText("Appuyer pour voir les differents dossiers");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 130, 40));

        jButton6.setText("SPORT");
        jButton6.setToolTipText("Appuyer pour deconnecter");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 130, 40));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REPRESENTATION GRAPHIQUE DE DONNEES DE LA POPULATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Année début :");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 80, 20));

        txtHistoriqueCom.setText(".....");
        txtHistoriqueCom.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel6.add(txtHistoriqueCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 260, 50));

        jLabel8.setText("Année fin :");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 70, 20));

        txtAnneeDebut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnneeDebutKeyReleased(evt);
            }
        });
        jPanel6.add(txtAnneeDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 80, -1));

        txtAnneeFin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnneeFinKeyReleased(evt);
            }
        });
        jPanel6.add(txtAnneeFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 70, -1));

        radio01.setText("District");
        radio01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio01MouseClicked(evt);
            }
        });
        jPanel6.add(radio01, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        radio02.setText("Commune");
        radio02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio02MouseClicked(evt);
            }
        });
        jPanel6.add(radio02, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        radio03.setText("Fokontany");
        radio03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio03MouseClicked(evt);
            }
        });
        jPanel6.add(radio03, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        radio00.setSelected(true);
        radio00.setText("Region");
        radio00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio00MouseClicked(evt);
            }
        });
        jPanel6.add(radio00, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/print.png"))); // NOI18N
        jButton8.setText("Sortir (pdf)");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 160, 30));

        TableRecherche.setModel(new javax.swing.table.DefaultTableModel(
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
        TableRecherche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableRechercheMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableRecherche);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 120));

        jLabel1.setText("Rechercher:");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 70, 20));

        txtRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheKeyReleased(evt);
            }
        });
        jPanel6.add(txtRecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 190, 30));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONOGRAPHIE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Année :");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 50, 20));

        txtHistoriqueCom1.setText(".....");
        txtHistoriqueCom1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(txtHistoriqueCom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 260, 50));

        txtAnneeDebut1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnneeDebut1KeyReleased(evt);
            }
        });
        jPanel7.add(txtAnneeDebut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 80, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/print.png"))); // NOI18N
        jButton7.setText("Sortir (pdf)");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 160, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/important.gif"))); // NOI18N
        jLabel2.setText("Attention: Cette operation requiere beaucoup de puissance, donc ca peut prendre quelque minutes");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 520, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 720, 380));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FormPopulation1 a = new FormPopulation1();
            
            a.first();
            a.setVisible(true);
            this.dispose(); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            FormSportive a = new FormSportive();
            
            a.first();
            a.setVisible(true);
            this.dispose();       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FormDistrict a = new FormDistrict();
            
            a.setVisible(true);
            this.dispose(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void radio01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio01MouseClicked
        // TODO add your handling code here:
        radioG=1;
        radio00.setSelected(false);
        radio01.setSelected(true);
        radio02.setSelected(false);
        radio03.setSelected(false);
        TableRecherche.removeAll();
    }//GEN-LAST:event_radio01MouseClicked

    private void radio02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio02MouseClicked
        // TODO add your handling code here:
        radioG=2;
        radio00.setSelected(false);
        radio02.setSelected(true);
        radio01.setSelected(false);
        radio03.setSelected(false);
        TableRecherche.removeAll();
    }//GEN-LAST:event_radio02MouseClicked

    private void radio03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio03MouseClicked
        // TODO add your handling code here:
        radioG=3;
        radio00.setSelected(false);
        radio03.setSelected(true);
        radio01.setSelected(false);
        radio02.setSelected(false);
        TableRecherche.removeAll();
    }//GEN-LAST:event_radio03MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(ID.equals("") && radioG!=0)
        {
           JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!");  
        }
        else{
            DESIGN = radioG==0 ? "Amorin'i mania":DESIGN;
            String anneeDebut = txtAnneeDebut.getText();
            String anneeFin = txtAnneeFin.getText();
        if(!anneeFin.equals("") && !anneeDebut.equals("") && Integer.parseInt(anneeDebut)<=Integer.parseInt(anneeDebut) && Integer.parseInt(anneeDebut)>=1990 && Integer.parseInt(anneeDebut)<=2020 && (Integer.parseInt(anneeFin)-Integer.parseInt(anneeDebut))<5)
        {
            try {
            
            ConnexionBase c = new ConnexionBase();
            ResultSet rs0 = c.executeQuery("select * from population where ID_FKT like '"+ ID +"%' and (ANNEE>='"+ anneeDebut +"' and ANNEE<='"+ anneeFin +"') order by ANNEE");
            DefaultCategoryDataset dataset= new DefaultCategoryDataset();
            while(rs0.next()){
            dataset.setValue(rs0.getInt("ZERO"),"[0-5]", rs0.getString("ANNEE"));
            dataset.setValue(rs0.getInt("SIX"),"[6-17]", rs0.getString("ANNEE"));
            dataset.setValue(rs0.getInt("DIXHUIT"),"[18-59]", rs0.getString("ANNEE"));
            dataset.setValue(rs0.getInt("SOIXANTE"),"[60-Et plus]", rs0.getString("ANNEE"));
            dataset.setValue(rs0.getInt("EFFECTIF_FEMININ"),"Femme", rs0.getString("ANNEE"));
            dataset.setValue(rs0.getInt("EFFECTIF_MASCULAIN"),"Homme", rs0.getString("ANNEE"));
            dataset.setValue(rs0.getInt("EFFECTIF_TOTAL"),"Total", rs0.getString("ANNEE"));
            }
            String titre1 = "Démographe: '"+ DESIGN +"'";
            JFreeChart chart = ChartFactory.createBarChart(titre1, "1 ANNEE:[0;5],[6;17],[18;59],[60;+], Femme, Homme, Total",anneeDebut+" - "+anneeFin, dataset, PlotOrientation.VERTICAL, false, true, false);
            // pour diagramm en bar ecrit createBarChart createLineChart
            CategoryPlot p=chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.black);
            ChartFrame frame= new ChartFrame("Representation graphique de la population", chart);
            frame.setVisible(true);
            frame.setSize(600,500);int dRes = JOptionPane.showConfirmDialog(null, "Voulez-vous sortir un pdf aussi?","Traitement en cours",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dRes == JOptionPane.YES_OPTION){
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File f = new File("Diagramme.png");
            String rep = f.getAbsolutePath();
                ChartUtilities.saveChartAsPNG(f, chart, 500, 400, info);
                try {
            Document document = new Document();
            PdfWriter Writer = PdfWriter.getInstance(document, new FileOutputStream("Diagramme.pdf"));
            document.open();
            Image image = Image.getInstance("Diagramme.png");
            document.add((Element) image);
            document.close();
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la creation du fichier: \n" + e);
        }
                Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                desktop.open(new File("Diagramme.pdf"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur: vous ne disposez pas une application qui ouvre un pdf:\n"+e+"Le fichier se trouve dans '" +rep+"'");
            }
        }  
            }        
            } catch (Exception e)
		{System.err.println(e.getMessage()); //e.getMessage est pour afficher ou se trouve l'erreur 
		}
        }
        else
        {
           JOptionPane.showMessageDialog(null, "Veuillez respecter le format suivant: 1989 < annee debut <=! annee fin+4 < 2021 !"); 
           txtAnneeFin.setText("");
           txtAnneeDebut.setText("");
        }}
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtAnneeDebutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnneeDebutKeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtAnneeDebut.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 4;
        if (ChampCodeDoss.matches("[0-9]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre egale a 4 chiffres, merci!");
            }
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre un nombre entier a 4 chiffre, merci!");
        }
        txtAnneeDebut.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtAnneeDebutKeyReleased

    private void txtAnneeDebut1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnneeDebut1KeyReleased
        // TODO add your handling code here:
        String ChampCodeDoss = txtAnneeDebut1.getText();
        int CCD = ChampCodeDoss.length();
        int valeur = 4;
        if (ChampCodeDoss.matches("[0-9]*")) {
            if (CCD > valeur) {
                ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
                JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre egale a 4 chiffres, merci!");
            }
        } else {
            ChampCodeDoss = ChampCodeDoss.substring(0, CCD - 1);
            JOptionPane.showMessageDialog(null, "Cet enregistrement doit etre un nombre entier a 4 chiffre, merci!");
        }
        txtAnneeDebut1.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtAnneeDebut1KeyReleased

    private void txtAnneeFinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnneeFinKeyReleased
        // TODO add your handling code here:
         String ChampCodeDoss = txtAnneeFin.getText();
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
        txtAnneeFin.setText(ChampCodeDoss);
    }//GEN-LAST:event_txtAnneeFinKeyReleased

    private void radio00MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio00MouseClicked
        // TODO add your handling code here:
        radioG=0;
        radio01.setSelected(false);
        radio00.setSelected(true);
        radio02.setSelected(false);
        radio03.setSelected(false);
        TableRecherche.removeAll();
    }//GEN-LAST:event_radio00MouseClicked

    private void txtRechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheKeyReleased
        // TODO add your handling code here:
        if(radioG!=0){
        String q = txtRecherche.getText();
        String p = radioG==1 ? "DIST": radioG==2 ? "COM":"FKT";
        String t = radioG==1 ? "district": radioG==2 ? "commune":"fokontany";
        try {
            ConnexionBase c = new ConnexionBase();
            DefaultTableModel dt = new DefaultTableModel();

            dt.addColumn("CODE");
            dt.addColumn("DESIGNATION");
            TableRecherche.setModel(dt);
            rs = c.executeQuery("Select ID_"+p+", NOM_"+p+" From "+t+" where NOM_"+p+" like '%"+q+"%'");
            
            while (rs.next()) {

                String ID = rs.getString("ID_"+p);
                String DESIGN = rs.getString("NOM_"+p);
                Object[] ligne = {ID,DESIGN};
                dt.addRow(ligne);
               
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(FormMenuPrincipale.class.getName()).log(Level.SEVERE, null, ex);
        }}
        else{
        txtRecherche.setText("");
        JOptionPane.showMessageDialog(null,"Vous pouvez tout de suite cliquer sur ''Sortir pdf''");
        }

    }//GEN-LAST:event_txtRechercheKeyReleased

    private void TableRechercheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableRechercheMouseClicked
        // TODO add your handling code here:
        ID = String.valueOf(TableRecherche.getValueAt(TableRecherche.getSelectedRow(), 0));
        DESIGN = String.valueOf(TableRecherche.getValueAt(TableRecherche.getSelectedRow(), 1));
    }//GEN-LAST:event_TableRechercheMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String anneeMon = txtAnneeDebut1.getText();
        if(!anneeMon.equals("") && Integer.parseInt(anneeMon)>=1990 && Integer.parseInt(anneeMon)<=2020)
        {
            try {
                // Step 1
                Rectangle rec = new Rectangle(1300, 2000);
            Document document = new Document(rec);
    // step 2
            PdfWriter.getInstance(document, new FileOutputStream("Monographie_"+anneeMon+".pdf"));
    // step 3
            document.open();
    // step 4
            
            PdfPTable table = new PdfPTable(3);
            table.addCell("DISTRICT");
            table.addCell("HISTORIQUE");
            table.addCell("SUPERFICIE");
            ConnexionBase c = new ConnexionBase();
            rs = c.executeQuery("Select NOM_DIST, HISTORIQUE_DIST, SUPERFICIE from district");
            while (rs.next()) {
            table.addCell(rs.getString("NOM_DIST"));
            table.addCell(rs.getString("HISTORIQUE_DIST"));
            table.addCell(rs.getString("SUPERFICIE"));
                }
            rs.close();

    // Step 5
    Font a = new Font(Font.FontFamily.TIMES_ROMAN, 14);
            document.add(new Paragraph("                                                                                MONOGRAPHIE DE L'ANNEE "+anneeMon,new Font(Font.FontFamily.TIMES_ROMAN, 22)));
            document.add(new Paragraph("                                                                \n",a));
            document.add(new Paragraph("                                                                                                                                                      REGION AMORIN'I MANIA",a));
            document.add(new Paragraph("                                                                \n",a));
            document.add(table);
            Integer i=0,j=0,k=0;
                ResultSet rs1 = c.executeQuery("Select ID_DIST, NOM_DIST from district");
            while (rs1.next()) {
                i++;k=0;
            document.add(new Paragraph("                                                                \n",a));
            document.add(new Paragraph(i+") DISTRICT DE "+rs1.getString("NOM_DIST"),a));
            document.add(new Paragraph("                                                                \n",a));
            PdfPTable table1 = new PdfPTable(8);
            table1.addCell("COMMUNE");
            table1.addCell("HISTORIQUE");
            table1.addCell("DRN7");
            table1.addCell("DRN35");
            table1.addCell("DRN41");
            table1.addCell("DDIST");
            table1.addCell("DREG");
            table1.addCell("SUPERFICIE");
            ConnexionBase c1 = new ConnexionBase();
            ResultSet rs2 = c1.executeQuery("Select * from commune where ID_COM like '"+ rs1.getString("ID_DIST") +"%'");
            while (rs2.next()) {
            table1.addCell(rs2.getString("NOM_COM"));
            table1.addCell(rs2.getString("HISTORIQUE_COM"));
            table1.addCell(rs2.getString("DRN7"));
            table1.addCell(rs2.getString("DRN35"));
            table1.addCell(rs2.getString("DRN41"));
            table1.addCell(rs2.getString("DDIST"));
            table1.addCell(rs2.getString("DREG"));
            table1.addCell(rs2.getString("SUPERFICIE"));
                }
            rs2.close();
            c1.close();
            document.add(table1);
            ConnexionBase c3 = new ConnexionBase();
            ResultSet rs3 = c3.executeQuery("Select ID_COM, NOM_COM from commune");
            while (rs3.next()) {
                k++;j=0;
                document.add(new Paragraph("                                                                \n",a));
            document.add(new Paragraph("            "+i+"."+k+") COMMUNE DE "+rs3.getString("NOM_COM"),a));
            document.add(new Paragraph("                                                                \n",a));
            PdfPTable table2 = new PdfPTable(3);
            table2.addCell("FOKONTANY");
            table2.addCell("HISTORIQUE");
            table2.addCell("SUPERFICIE");
            ConnexionBase c4 = new ConnexionBase();
            ResultSet rs4 = c4.executeQuery("Select * from fokontany where ID_FKT like '"+ rs3.getString("ID_COM") +"%'");
            while (rs4.next()) {
            table2.addCell(rs4.getString("NOM_FKT"));
            table2.addCell(rs4.getString("HISTORIQUE_FKT"));
            table2.addCell(rs4.getString("SUPERFICIE"));
                }
            rs4.close();
            c4.close();
            document.add(table2);
            ConnexionBase c5 = new ConnexionBase();
            ResultSet rs5= c5.executeQuery("Select ID_FKT, NOM_FKT from fokontany");
            while (rs5.next()) {
                j++;
                document.add(new Paragraph("                                                                \n",a));
            document.add(new Paragraph("                        "+i+"."+k+"."+j+") FOKONTANY DE "+rs5.getString("NOM_FKT"),a));
            document.add(new Paragraph("                                                                \n",a));
            PdfPTable table3 = new PdfPTable(5);
            table3.addCell("POPULATION");
            table3.addCell("SANITAIRE");
            table3.addCell("SCOLAIRE");
            table3.addCell("SECURITE");
            table3.addCell("SPORTIVE");
            ConnexionBase c6 = new ConnexionBase();
            ResultSet rs6 = c6.executeQuery("Select * from population where ID_FKT = '"+ rs5.getString("ID_FKT") +"' and ANNEE='"+anneeMon+"'");
            String tmp1 = "";
            if(rs6.next()) {
            tmp1="[0-5]: "+rs6.getString("ZERO")+"\n[6-17]: "+rs6.getString("SIX")+"\n[18-59]: "+rs6.getString("DIXHUIT")+"\n[60-Plus]: "+rs6.getString("SOIXANTE")+"\nHomme: "+rs6.getString("EFFECTIF_MASCULAIN")+"\nFemme: "+rs6.getString("EFFECTIF_FEMININ")+"\nTotal: "+rs6.getString("EFFECTIF_TOTAL");
            }
            table3.addCell(tmp1);
            rs6.close();
            c6.close();
            ConnexionBase c7 = new ConnexionBase();
            ResultSet rs7 = c7.executeQuery("Select * from sanitaire where ID_FKT = '"+ rs5.getString("ID_FKT") +"' and ANNEE='"+anneeMon+"'");
            if(rs7.next()) {
            tmp1="INFRASTRUCTURE: "+rs7.getString("INFRAS_SANITAIRE")+"\nNOMBRE: "+rs7.getString("NOMBRE_INFRAS_SANITAIRE")+"\nNATURE: "+rs7.getString("NATURE_INFRAS_SANITAIRE")+"\nEMPLOYES: "+rs7.getString("EMPLOYES")+"\nNOMBRE: "+rs7.getString("NOMBRE_EMPLOYES")+"\nETAT: "+rs7.getString("ETAT_INFRAS_SANITAIRE")+"\nREMARQUE: "+rs7.getString("REMARQUE"); 
            }
            table3.addCell(tmp1);
            rs7.close();
            c7.close();
            ConnexionBase c8 = new ConnexionBase();
            ResultSet rs8 = c8.executeQuery("Select * from scolaire where ID_FKT = '"+ rs5.getString("ID_FKT") +"' and ANNEE='"+anneeMon+"'");
            if(rs8.next())
            {
            tmp1="CATEGORIE: "+rs8.getString("CATEG_SCOLAIRE")+"\nNATURE: "+rs8.getString("NATURE_SCOLAIRE")+"\nNOMBRE: "+rs8.getString("NOMBRE_SCOLAIRE")+"\nETAT: "+rs8.getString("ETAT_SCOLAIRE")+"\nGARCON: "+rs8.getString("ELEVE_MASCULAIN")+"\nFILLE: "+rs8.getString("ELEVE_FEMININ")+"\nTOTAL: "+rs8.getString("EFFECTIF_TOTAL")+"\nANNEE SCOLAIRE: "+rs8.getString("ANNEE_SCOLAIRE");
            }
            table3.addCell(tmp1);
            rs8.close();
            c8.close();
            ConnexionBase c9 = new ConnexionBase();
            ResultSet rs9 = c9.executeQuery("Select * from securite where ID_FKT = '"+ rs5.getString("ID_FKT") +"' and ANNEE='"+anneeMon+"'");
            if(rs9.next())
            {
            tmp1="CATEGORIE: "+rs9.getString("CATEG_SEC")+"\nNOMBRE: "+rs9.getString("NOMBRE")+"\nREMARQUE: "+rs9.getString("REMARQUE");
            }
            table3.addCell(tmp1);
            rs9.close();
            c9.close();
            ConnexionBase c10 = new ConnexionBase();
            ResultSet rs10 = c10.executeQuery("Select * from sportive where ID_FKT = '"+ rs5.getString("ID_FKT") +"' and ANNEE='"+anneeMon+"'");
            
            if(rs10.next())
            {
            tmp1="CATEGORIE: "+rs10.getString("CATEG_SPORT")+"\nTERRAIN: "+rs10.getString("EXISTANCE_TERRAIN")+"\nETAT: "+rs10.getString("ETAT_TERRAIN")+"\nNOMBRE: "+rs10.getString("NOMBRE_TERRAIN")+"\nREMARQUE: "+rs10.getString("REMARQUE_SPORT");
            }
            table3.addCell(tmp1);
            rs10.close();
            c10.close();
            
            document.add(table3);
                        
            }
            rs5.close();
            c5.close();
            }
            }
            rs1.close();
            c.close();
    // step 6
            document.close();
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            final File f = new File("tmp.txt");
            String rep = f.getAbsolutePath();
            Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                desktop.open(new File("Monographie_"+ anneeMon +".pdf"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur: vous ne disposez pas une application qui ouvre un pdf:\n"+e+"Le fichier se trouve dans '" +rep+"'");
            }     
            
        }}
        else
        {
            JOptionPane.showMessageDialog(null, "Veuillez respecter le format suivant: 1989 < annee  < 2021 !"); 
           txtAnneeDebut1.setText("");
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        FormSanitaire a = new FormSanitaire();
            
            a.first();
            a.setVisible(true);
            this.dispose();       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         FormScolaire a = new FormScolaire();
            
            a.first();
            a.setVisible(true);
            this.dispose(); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        FormSecurite a = new FormSecurite();
            
            a.first();
            a.setVisible(true);
            this.dispose(); 
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(FormMenuPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenuPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenuPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenuPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenuPrincipale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableRecherche;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radio00;
    private javax.swing.JRadioButton radio01;
    private javax.swing.JRadioButton radio02;
    private javax.swing.JRadioButton radio03;
    private javax.swing.JTextField txtAnneeDebut;
    private javax.swing.JTextField txtAnneeDebut1;
    private javax.swing.JTextField txtAnneeFin;
    private javax.swing.JLabel txtHistoriqueCom;
    private javax.swing.JLabel txtHistoriqueCom1;
    private javax.swing.JTextField txtRecherche;
    // End of variables declaration//GEN-END:variables
}
