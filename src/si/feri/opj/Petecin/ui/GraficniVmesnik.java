package si.feri.opj.Petecin.ui;

import si.feri.opj.Petecin.cepljenje.*;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.Collator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GraficniVmesnik {

    private JFrame frame;
    private JTextField serijskastevilkatextField;
    private JTextField rokuporabetextField;
    private JTextField rokuporabetextField_1;
    private JTextField nazivtextField;
    private JTextField krajtextField;
    private JTextField telefonskastevilkatextField;
    private JTextField emailtextField;
    private JTextField nazivtextField1;
    private JTextField telefonskastevilkatextField_1;
    private JTextField emailtextField_1;
    private JTextField nazivtextField3;
    private JTextField registrskastevilkatextField;
    private JTextField znamkatextField;
    private JTextField textFieldRegistrskaStevilka;
    private JTextField textFieldZnamka;
    private JTextField serijskastevilkatextField_1;
    private DefaultComboBoxModel<Cepivo> model = new DefaultComboBoxModel<Cepivo>();
    private DefaultComboBoxModel<Ustanova> ust = new DefaultComboBoxModel<Ustanova>();
    private DefaultComboBoxModel<CepilniCenter> cepcenter = new DefaultComboBoxModel<CepilniCenter>();
    private DefaultComboBoxModel<Ambulanta> amb = new DefaultComboBoxModel<Ambulanta>();
    private DefaultComboBoxModel<PrevoznoSredstvo> prevoz = new DefaultComboBoxModel<PrevoznoSredstvo>();
    private DefaultListModel<Avto> seznamAvto = new DefaultListModel<Avto>();
    private DefaultListModel<Kombi> seznamKombi = new DefaultListModel<Kombi>();
    private DefaultListModel<Cepivo> listCepivAvto = new DefaultListModel<Cepivo>();
    private DefaultListModel<Cepivo> listCepivKombi = new DefaultListModel<Cepivo>();
    private JTextField textFieldNaziv2;

    ArrayList<Cepivo> cepivo = new ArrayList<Cepivo>();
    ArrayList<Ustanova> ustanova = new ArrayList<Ustanova>();
    ArrayList<PrevoznoSredstvo> prevoznosredstvo = new ArrayList<PrevoznoSredstvo>();
    ArrayList<CepilniCenter> cpcenter = new ArrayList<CepilniCenter>();
    ArrayList<Ambulanta> ambulanta = new ArrayList<Ambulanta>();
    ArrayList<Avto> arrayAvto = new ArrayList<Avto>();
    ArrayList<Kombi> arrayKombi = new ArrayList<Kombi>();
    ArrayList<Cepivo> cepivoavto = new ArrayList<Cepivo>();
    ArrayList<Cepivo> cepivokombi = new ArrayList<Cepivo>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GraficniVmesnik window = new GraficniVmesnik();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GraficniVmesnik() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent arg0) {

                LocalDateTime cas = LocalDateTime.now();

                try{
                    FileWriter x = new FileWriter("dogodki.txt", true);
                    x.write(cas + ": zagon programa - ZAZNAMEK" + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        FileWriter x = new FileWriter("dogodki.txt", true);
                        x.write(cas + ": zagon programa ni uspe�en - KRITI?NO" + "\n");
                        x.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                //CEPIVO
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("Cepiva.ser")));

                    ArrayList<Cepivo> cepiva = (ArrayList<Cepivo>) inputstream.readObject();
                    for(Cepivo c: cepiva) {
                        cepivo.add(c);
                        model.addElement(c);
                    }

                    System.out.println("Cepiva so uspe�no pridobljena!");
                    inputstream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                    LocalDateTime cas1 = LocalDateTime.now();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(cepivo);

                //USTANOVA
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("Ustanova.ser")));

                    ArrayList<Ustanova> ustanove = (ArrayList<Ustanova>) inputstream.readObject();
                    for(Ustanova u: ustanove) {
                        ustanova.add(u);
                        ust.addAll(ustanova);
                    }
                    System.out.println("Ustanove so uspe�no pridobljene!");
                    inputstream.close();
                } catch (FileNotFoundException e) {
                    try {
                        FileWriter x = new FileWriter("dogodki.txt", true);
                        x.write(LocalDateTime.now() + ": ne najde datoteke z ustanovami - ZAZNAMEK" + "\n");
                        x.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {

                    Collections.sort(ustanova, Comparator.comparing(Ustanova::getNaziv));   //sortiranje
                    //Collator.getInstance(new Locale("sl", "SI"))
                    System.out.println("Seznam ustanov: " + ustanova);
                    ust.addAll(ustanova);
                    FileWriter x = new FileWriter("dogodki.txt", true);
                    x.write(LocalDateTime.now() + ": sortiranje ustanov po nazivih je uspesno - ZAZNAMEK" + "\n");
                    x.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //CEPILNI CENTER
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("CepilniCenter.ser")));

                    ArrayList<CepilniCenter> cpcentri = (ArrayList<CepilniCenter>) inputstream.readObject();
                    for(CepilniCenter cpc: cpcentri) {
                        cpcenter.add(cpc);
                        cepcenter.addAll(cpcenter);
                    }
                    System.out.println("Cepilni centri so uspe�no pridobljeni!");
                    inputstream.close();
                } catch (FileNotFoundException e) {
                    try {
                        FileWriter x = new FileWriter("dogodki.txt", true);
                        x.write(LocalDateTime.now() + ": ne najde datoteke s cepilnimi centri - ZAZNAMEK" + "\n");
                        x.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {

                    Collections.sort(ustanova, Comparator.comparing(Ustanova::getNaziv));   //sortiranje
                    //Collator.getInstance(new Locale("sl", "SI"))
                    System.out.println("Seznam ustanov: " + ustanova);
                    ust.addAll(ustanova);
                    FileWriter x = new FileWriter("dogodki.txt", true);
                    x.write(LocalDateTime.now() + ": sortiranje ustanov po nazivih je uspesno - OPOZORILO" + "\n");
                    x.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //AMBULANTA
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("Ambulanta.ser")));

                    ArrayList<Ambulanta> ambulante = (ArrayList<Ambulanta>) inputstream.readObject();
                    for(Ambulanta am: ambulante) {
                        ambulanta.add(am);
                        amb.addAll(ambulanta);
                    }
                    System.out.println("Ambulante so uspe�no pridobljene!");
                    inputstream.close();
                } catch (FileNotFoundException e) {
                    try {
                        FileWriter x = new FileWriter("dogodki.txt", true);
                        x.write(LocalDateTime.now() + ": ne najde datoteke z ambulantami - OPOZORILO" + "\n");
                        x.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {

                    Collections.sort(ustanova, Comparator.comparing(Ustanova::getNaziv));   //sortiranje
                    //Collator.getInstance(new Locale("sl", "SI"))
                    System.out.println("Seznam ustanov: " + ustanova);
                    ust.addAll(ustanova);
                    FileWriter x = new FileWriter("dogodki.txt", true);
                    x.write(LocalDateTime.now() + ": sortiranje ustanov po nazivih je uspesno - ZAZNAMEK" + "\n");
                    x.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //PREVOZNO SREDSTVO
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("PrevoznoSredstvo.ser")));

                    ArrayList<PrevoznoSredstvo> prevoznasredstva = (ArrayList<PrevoznoSredstvo>) inputstream.readObject();
                    for(PrevoznoSredstvo ps: prevoznasredstva) {
                        prevoznosredstvo.add(ps);
                        prevoz.addElement(ps);
                    }

                    System.out.println("Prevozna sredstva so uspe�no pridobljena!");
                    inputstream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(prevoznosredstvo);

                //AVTO
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("Avto.ser")));

                    ArrayList<Avto> avti = (ArrayList<Avto>) inputstream.readObject();
                    for(Avto a: avti) {
                        arrayAvto.add(a);
                        seznamAvto.addElement(a);
                    }

                    System.out.println("Avtomobili so uspe�no pridobljeni!");
                    inputstream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(arrayAvto);

                //KOMBI
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("Kombi.ser")));

                    ArrayList<Kombi> kombiji = (ArrayList<Kombi>) inputstream.readObject();
                    for(Kombi k: kombiji) {
                        arrayKombi.add(k);
                        seznamKombi.addElement(k);
                    }

                    System.out.println("Kombiji so uspe�no pridobljeni!");
                    inputstream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(arrayKombi);

                //CEPIVO AVTO
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("CepivoAvto.ser")));

                    ArrayList<Cepivo> cepivaavto = (ArrayList<Cepivo>) inputstream.readObject();
                    for(Cepivo k: cepivaavto) {
                        cepivoavto.add(k);
                        listCepivAvto.addElement(k);
                    }

                    System.out.println("Seznam cepiv za avto je uspe�no pridobljen!");
                    inputstream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(cepivoavto);

                //CEPIVO KOMBI
                try {
                    ObjectInputStream inputstream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("CepivoKombi.ser")));

                    ArrayList<Cepivo> cepivakombi = (ArrayList<Cepivo>) inputstream.readObject();
                    for(Cepivo k: cepivakombi) {
                        cepivokombi.add(k);
                        listCepivKombi.addElement(k);
                    }

                    System.out.println("Seznam cepiv za avto je uspe�no pridobljen!");
                    inputstream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(cepivokombi);
            }


        });

        frame.setBounds(100, 100, 951, 733);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //****************************C E P I V A - P A N E L************************************************

        JPanel cepivapanel = new JPanel();
        tabbedPane.addTab("Cepiva", null, cepivapanel, null);
        GridBagLayout gbl_cepivapanel = new GridBagLayout();
        gbl_cepivapanel.columnWidths = new int[]{154, 154, 165, 0, 0, 299, 233, 0};
        gbl_cepivapanel.rowHeights = new int[]{37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 0};
        gbl_cepivapanel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_cepivapanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        cepivapanel.setLayout(gbl_cepivapanel);

        JLabel dodajcepivonaziv = new JLabel("DODAJ CEPIVO");
        GridBagConstraints gbc_dodajcepivonaziv = new GridBagConstraints();
        gbc_dodajcepivonaziv.fill = GridBagConstraints.BOTH;
        gbc_dodajcepivonaziv.insets = new Insets(0, 0, 5, 5);
        gbc_dodajcepivonaziv.gridx = 1;
        gbc_dodajcepivonaziv.gridy = 1;
        cepivapanel.add(dodajcepivonaziv, gbc_dodajcepivonaziv);

        JLabel serijskastevilkanaziv = new JLabel("Serijska stevilka");
        GridBagConstraints gbc_serijskastevilkanaziv = new GridBagConstraints();
        gbc_serijskastevilkanaziv.fill = GridBagConstraints.BOTH;
        gbc_serijskastevilkanaziv.insets = new Insets(0, 0, 5, 5);
        gbc_serijskastevilkanaziv.gridx = 1;
        gbc_serijskastevilkanaziv.gridy = 2;
        cepivapanel.add(serijskastevilkanaziv, gbc_serijskastevilkanaziv);

        JLabel vecodmerkovnaziv = new JLabel("Vec odmerkov");
        GridBagConstraints gbc_vecodmerkovnaziv = new GridBagConstraints();
        gbc_vecodmerkovnaziv.anchor = GridBagConstraints.WEST;
        gbc_vecodmerkovnaziv.insets = new Insets(0, 0, 5, 5);
        gbc_vecodmerkovnaziv.gridx = 5;
        gbc_vecodmerkovnaziv.gridy = 2;
        cepivapanel.add(vecodmerkovnaziv, gbc_vecodmerkovnaziv);

        serijskastevilkatextField = new JTextField();
        GridBagConstraints gbc_serijskastevilkatextField = new GridBagConstraints();
        gbc_serijskastevilkatextField.gridwidth = 2;
        gbc_serijskastevilkatextField.insets = new Insets(0, 0, 5, 5);
        gbc_serijskastevilkatextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_serijskastevilkatextField.gridx = 1;
        gbc_serijskastevilkatextField.gridy = 3;
        cepivapanel.add(serijskastevilkatextField, gbc_serijskastevilkatextField);
        serijskastevilkatextField.setColumns(10);

        JRadioButton daRadioBtn = new JRadioButton("DA");
        GridBagConstraints gbc_daRadioBtn = new GridBagConstraints();
        gbc_daRadioBtn.insets = new Insets(0, 0, 5, 5);
        gbc_daRadioBtn.gridx = 5;
        gbc_daRadioBtn.gridy = 3;
        cepivapanel.add(daRadioBtn, gbc_daRadioBtn);

        JRadioButton neRadioBtn = new JRadioButton("NE");
        GridBagConstraints gbc_neRadioBtn = new GridBagConstraints();
        gbc_neRadioBtn.insets = new Insets(0, 0, 5, 5);
        gbc_neRadioBtn.gridx = 5;
        gbc_neRadioBtn.gridy = 4;
        cepivapanel.add(neRadioBtn, gbc_neRadioBtn);

        ButtonGroup vecOdmerkov = new ButtonGroup();					//buttongroup za DA in NE
        vecOdmerkov.add(daRadioBtn);
        vecOdmerkov.add(neRadioBtn);

        JLabel rokuporabenaziv = new JLabel("Rok uporabe");
        GridBagConstraints gbc_rokuporabenaziv = new GridBagConstraints();
        gbc_rokuporabenaziv.insets = new Insets(0, 0, 5, 5);
        gbc_rokuporabenaziv.fill = GridBagConstraints.BOTH;
        gbc_rokuporabenaziv.gridx = 1;
        gbc_rokuporabenaziv.gridy = 5;
        cepivapanel.add(rokuporabenaziv, gbc_rokuporabenaziv);

        JLabel tipcepivanaziv = new JLabel("Tip cepiva");
        GridBagConstraints gbc_tipcepivanaziv = new GridBagConstraints();
        gbc_tipcepivanaziv.fill = GridBagConstraints.BOTH;
        gbc_tipcepivanaziv.insets = new Insets(0, 0, 5, 5);
        gbc_tipcepivanaziv.gridx = 5;
        gbc_tipcepivanaziv.gridy = 5;
        cepivapanel.add(tipcepivanaziv, gbc_tipcepivanaziv);

        rokuporabetextField = new JTextField();
        GridBagConstraints gbc_rokuporabetextField = new GridBagConstraints();
        gbc_rokuporabetextField.gridwidth = 2;
        gbc_rokuporabetextField.insets = new Insets(0, 0, 5, 5);
        gbc_rokuporabetextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_rokuporabetextField.gridx = 1;
        gbc_rokuporabetextField.gridy = 6;
        cepivapanel.add(rokuporabetextField, gbc_rokuporabetextField);
        rokuporabetextField.setColumns(10);

        JComboBox<String> tipcepivacomboBox = new JComboBox<String>();								//sortiranje po slovenski abecedi
        DefaultComboBoxModel<String> tipcepiva = new DefaultComboBoxModel<String>();
        ArrayList<String> tip = new ArrayList<>();
        tip.add("PFIZER");
        tip.add("�");
        tip.add("MODERNA");
        tip.add("ASTRAZENECA");
        Collections.sort(tip, Collator.getInstance(new Locale("sl", "SI")));
        tipcepiva.addAll(tip);
        tipcepivacomboBox.setModel(tipcepiva);

        GridBagConstraints gbc_tipcepivacomboBox = new GridBagConstraints();
        gbc_tipcepivacomboBox.insets = new Insets(0, 0, 5, 5);
        gbc_tipcepivacomboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_tipcepivacomboBox.gridx = 5;
        gbc_tipcepivacomboBox.gridy = 6;
        cepivapanel.add(tipcepivacomboBox, gbc_tipcepivacomboBox);

        JButton dodajbtnNewButton = new JButton("DODAJ");
        GridBagConstraints gbc_dodajbtnNewButton = new GridBagConstraints();
        gbc_dodajbtnNewButton.gridheight = 5;
        gbc_dodajbtnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_dodajbtnNewButton.gridx = 6;
        gbc_dodajbtnNewButton.gridy = 2;
        cepivapanel.add(dodajbtnNewButton, gbc_dodajbtnNewButton);

        JLabel spremenicepivoNewLabel = new JLabel("SPREMENI CEPIVO");
        GridBagConstraints gbc_spremenicepivoNewLabel = new GridBagConstraints();
        gbc_spremenicepivoNewLabel.anchor = GridBagConstraints.WEST;
        gbc_spremenicepivoNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_spremenicepivoNewLabel.gridx = 1;
        gbc_spremenicepivoNewLabel.gridy = 8;
        cepivapanel.add(spremenicepivoNewLabel, gbc_spremenicepivoNewLabel);

        ButtonGroup vecOdmerkov1 = new ButtonGroup();

        JLabel lblIzberiCepivo = new JLabel("Izberi cepivo");
        GridBagConstraints gbc_lblIzberiCepivo = new GridBagConstraints();
        gbc_lblIzberiCepivo.anchor = GridBagConstraints.EAST;
        gbc_lblIzberiCepivo.insets = new Insets(0, 0, 5, 5);
        gbc_lblIzberiCepivo.gridx = 1;
        gbc_lblIzberiCepivo.gridy = 9;
        cepivapanel.add(lblIzberiCepivo, gbc_lblIzberiCepivo);

        JComboBox<Cepivo> cepivacomboBox = new JComboBox<>();
        GridBagConstraints gbc_cepivacomboBox = new GridBagConstraints();
        gbc_cepivacomboBox.gridwidth = 4;
        gbc_cepivacomboBox.insets = new Insets(0, 0, 5, 5);
        gbc_cepivacomboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_cepivacomboBox.gridx = 2;
        gbc_cepivacomboBox.gridy = 9;
        cepivapanel.add(cepivacomboBox, gbc_cepivacomboBox);

        JLabel serijskastevilkanaziv_1 = new JLabel("Serijska stevilka");
        GridBagConstraints gbc_serijskastevilkanaziv_1 = new GridBagConstraints();
        gbc_serijskastevilkanaziv_1.anchor = GridBagConstraints.WEST;
        gbc_serijskastevilkanaziv_1.insets = new Insets(0, 0, 5, 5);
        gbc_serijskastevilkanaziv_1.gridx = 1;
        gbc_serijskastevilkanaziv_1.gridy = 10;
        cepivapanel.add(serijskastevilkanaziv_1, gbc_serijskastevilkanaziv_1);

        JLabel vecodmerkovnaziv_1 = new JLabel("Vec odmerkov");
        GridBagConstraints gbc_vecodmerkovnaziv_1 = new GridBagConstraints();
        gbc_vecodmerkovnaziv_1.anchor = GridBagConstraints.WEST;
        gbc_vecodmerkovnaziv_1.insets = new Insets(0, 0, 5, 5);
        gbc_vecodmerkovnaziv_1.gridx = 4;
        gbc_vecodmerkovnaziv_1.gridy = 10;
        cepivapanel.add(vecodmerkovnaziv_1, gbc_vecodmerkovnaziv_1);

        JRadioButton daRadioBtn_1 = new JRadioButton("DA");
        GridBagConstraints gbc_daRadioBtn_1 = new GridBagConstraints();
        gbc_daRadioBtn_1.anchor = GridBagConstraints.WEST;
        gbc_daRadioBtn_1.insets = new Insets(0, 0, 5, 5);
        gbc_daRadioBtn_1.gridx = 5;
        gbc_daRadioBtn_1.gridy = 10;
        cepivapanel.add(daRadioBtn_1, gbc_daRadioBtn_1);
        vecOdmerkov1.add(daRadioBtn_1);

        serijskastevilkatextField_1 = new JTextField();
        serijskastevilkatextField_1.setColumns(10);
        GridBagConstraints gbc_serijskastevilkatextField_1 = new GridBagConstraints();
        gbc_serijskastevilkatextField_1.gridwidth = 2;
        gbc_serijskastevilkatextField_1.insets = new Insets(0, 0, 5, 5);
        gbc_serijskastevilkatextField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_serijskastevilkatextField_1.gridx = 1;
        gbc_serijskastevilkatextField_1.gridy = 11;
        cepivapanel.add(serijskastevilkatextField_1, gbc_serijskastevilkatextField_1);

        JRadioButton neRadioBtn_1 = new JRadioButton("NE");
        GridBagConstraints gbc_neRadioBtn_1 = new GridBagConstraints();
        gbc_neRadioBtn_1.anchor = GridBagConstraints.WEST;
        gbc_neRadioBtn_1.insets = new Insets(0, 0, 5, 5);
        gbc_neRadioBtn_1.gridx = 5;
        gbc_neRadioBtn_1.gridy = 11;
        cepivapanel.add(neRadioBtn_1, gbc_neRadioBtn_1);
        vecOdmerkov1.add(neRadioBtn_1);

        JLabel rokuporabenaziv_1 = new JLabel("Rok uporabe");
        GridBagConstraints gbc_rokuporabenaziv_1 = new GridBagConstraints();
        gbc_rokuporabenaziv_1.anchor = GridBagConstraints.WEST;
        gbc_rokuporabenaziv_1.insets = new Insets(0, 0, 5, 5);
        gbc_rokuporabenaziv_1.gridx = 1;
        gbc_rokuporabenaziv_1.gridy = 12;
        cepivapanel.add(rokuporabenaziv_1, gbc_rokuporabenaziv_1);

        JButton spremenibtnNewButton = new JButton("SPREMENI");
        GridBagConstraints gbc_spremenibtnNewButton = new GridBagConstraints();
        gbc_spremenibtnNewButton.gridheight = 5;
        gbc_spremenibtnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_spremenibtnNewButton.gridx = 6;
        gbc_spremenibtnNewButton.gridy = 9;
        cepivapanel.add(spremenibtnNewButton, gbc_spremenibtnNewButton);

        JLabel tipcepivanaziv_1 = new JLabel("Tip cepiva");
        GridBagConstraints gbc_tipcepivanaziv_1 = new GridBagConstraints();
        gbc_tipcepivanaziv_1.gridwidth = 2;
        gbc_tipcepivanaziv_1.anchor = GridBagConstraints.WEST;
        gbc_tipcepivanaziv_1.insets = new Insets(0, 0, 5, 5);
        gbc_tipcepivanaziv_1.gridx = 4;
        gbc_tipcepivanaziv_1.gridy = 12;
        cepivapanel.add(tipcepivanaziv_1, gbc_tipcepivanaziv_1);

        rokuporabetextField_1 = new JTextField();
        GridBagConstraints gbc_rokuporabetextField_1 = new GridBagConstraints();
        gbc_rokuporabetextField_1.gridwidth = 2;
        gbc_rokuporabetextField_1.insets = new Insets(0, 0, 5, 5);
        gbc_rokuporabetextField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_rokuporabetextField_1.gridx = 1;
        gbc_rokuporabetextField_1.gridy = 13;
        cepivapanel.add(rokuporabetextField_1, gbc_rokuporabetextField_1);
        rokuporabetextField_1.setColumns(10);

        JComboBox<TipCepiva> tipcepivacomboBox_1 = new JComboBox<TipCepiva>();
        tipcepivacomboBox_1.setModel(new DefaultComboBoxModel(TipCepiva.values()));
        GridBagConstraints gbc_tipcepivacomboBox_1 = new GridBagConstraints();
        gbc_tipcepivacomboBox_1.gridwidth = 2;
        gbc_tipcepivacomboBox_1.insets = new Insets(0, 0, 5, 5);
        gbc_tipcepivacomboBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_tipcepivacomboBox_1.gridx = 4;
        gbc_tipcepivacomboBox_1.gridy = 13;
        cepivapanel.add(tipcepivacomboBox_1, gbc_tipcepivacomboBox_1);

        JLabel odstranicepivoNewLabel = new JLabel("ODSTRANI CEPIVO");
        GridBagConstraints gbc_odstranicepivoNewLabel = new GridBagConstraints();
        gbc_odstranicepivoNewLabel.anchor = GridBagConstraints.WEST;
        gbc_odstranicepivoNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_odstranicepivoNewLabel.gridx = 1;
        gbc_odstranicepivoNewLabel.gridy = 15;
        cepivapanel.add(odstranicepivoNewLabel, gbc_odstranicepivoNewLabel);

        JComboBox<Cepivo> serijskastevilkacomboBox_1 = new JComboBox<Cepivo>();
        GridBagConstraints gbc_serijskastevilkacomboBox_1 = new GridBagConstraints();
        gbc_serijskastevilkacomboBox_1.gridwidth = 5;
        gbc_serijskastevilkacomboBox_1.insets = new Insets(0, 0, 5, 5);
        gbc_serijskastevilkacomboBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_serijskastevilkacomboBox_1.gridx = 1;
        gbc_serijskastevilkacomboBox_1.gridy = 16;
        cepivapanel.add(serijskastevilkacomboBox_1, gbc_serijskastevilkacomboBox_1);

        JButton odstranibtnNewButton_1 = new JButton("ODSTRANI");
        GridBagConstraints gbc_odstranibtnNewButton_1 = new GridBagConstraints();
        gbc_odstranibtnNewButton_1.insets = new Insets(0, 0, 5, 0);
        gbc_odstranibtnNewButton_1.gridx = 6;
        gbc_odstranibtnNewButton_1.gridy = 16;
        cepivapanel.add(odstranibtnNewButton_1, gbc_odstranibtnNewButton_1);

        JButton odstranibtnNewButton_2 = new JButton("ODSTRANI VSE");
        GridBagConstraints gbc_odstranibtnNewButton_2 = new GridBagConstraints();
        gbc_odstranibtnNewButton_2.gridx = 6;
        gbc_odstranibtnNewButton_2.gridy = 17;
        cepivapanel.add(odstranibtnNewButton_2, gbc_odstranibtnNewButton_2);

        //*********************************	DODAJ CEPIVO ************************************
        dodajbtnNewButton.addActionListener(new ActionListener() {		//anonimni razred
            public void actionPerformed(ActionEvent e) {
                try {
                    int serijska = Integer.parseInt(serijskastevilkatextField.getText());
                    LocalDate rok = LocalDate.parse(rokuporabetextField.getText());

                    boolean odmerki;
                    if (daRadioBtn.isSelected()) {
                        odmerki = true;
                    } else if (neRadioBtn.isSelected()) {
                        odmerki =  false;
                    } else {
                        System.out.println("nedefinirano");
                        odmerki = false;
                    }

                    TipCepiva tip = TipCepiva.valueOf(tipcepivacomboBox.getSelectedItem().toString());

                    Cepivo c = new Cepivo (serijska, rok, odmerki, tip);

                    cepivo.add(c);
                    System.out.println(cepivo);
                    model.addElement(c);


                    serijskastevilkatextField.setText(null);
                    rokuporabetextField.setText(null);


                }catch (Exception x) {
                    JOptionPane.showMessageDialog(frame, "Podatkov niste vnesli pravilno.");
                    serijskastevilkatextField.setText(null);
                    rokuporabetextField.setText(null);
                    try {
                        FileWriter y = new FileWriter("dogodki.txt", true);
                        y.write(LocalDateTime.now() + ": podatki so narobe vne�eni - OPOZORILO" + "\n");
                        y.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }

                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("Cepiva.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(cepivo);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //*********************************	SPREMENI CEPIVO *******************************************
        cepivacomboBox.setModel(model);
        spremenibtnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int serijska = Integer.parseInt(serijskastevilkatextField_1.getText());
                LocalDate rok = LocalDate.parse(rokuporabetextField_1.getText());

                boolean odmerki;
                if (daRadioBtn_1.isSelected()) {
                    odmerki = true;
                } else if (neRadioBtn_1.isSelected()) {
                    odmerki =  false;
                } else {
                    System.out.println("nedefinirano");
                    odmerki = false;
                }

                TipCepiva tip = TipCepiva.valueOf(tipcepivacomboBox.getSelectedItem().toString());

                Cepivo c = new Cepivo (serijska, rok, odmerki, tip);

                int x = cepivacomboBox.getSelectedIndex();

                cepivo.set(x, c);

                cepivacomboBox.setSelectedItem(c);

                System.out.println(cepivo);

                model.removeElementAt(x);
                model.addElement(c);

                serijskastevilkatextField_1.setText("");
                rokuporabetextField_1.setText("");

                try {
                    FileWriter o = new FileWriter("dogodki.txt", true);
                    o.write(LocalDateTime.now() + ": uspesno ste spremenili cepivo - ZAZNAMEK" + "\n");
                    o.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("Cepiva.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(cepivo);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //*********************************	ODSTRANI CEPIVO *******************************************

        serijskastevilkacomboBox_1.setModel(model);
        odstranibtnNewButton_1.addActionListener(new Samostojni(serijskastevilkacomboBox_1, cepivo));			//samostojni razred
        odstranibtnNewButton_2.addActionListener(new Notranji());										// notranji razred



        //**************************** U S T A N O V A - P A N E L ***********************************************

        JPanel ustanovapanel = new JPanel();
        tabbedPane.addTab("Ustanova", null, ustanovapanel, null);
        GridBagLayout gbl_ustanovapanel = new GridBagLayout();
        gbl_ustanovapanel.columnWidths = new int[]{37, 100, 200, 200, 200, 0, 205, 0};
        gbl_ustanovapanel.rowHeights = new int[]{37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 0, 37, 37, 37, 0};
        gbl_ustanovapanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_ustanovapanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        ustanovapanel.setLayout(gbl_ustanovapanel);

        JLabel dodajustanovonaziv = new JLabel("DODAJ USTANOVO");
        GridBagConstraints gbc_dodajustanovonaziv = new GridBagConstraints();
        gbc_dodajustanovonaziv.gridheight = 4;
        gbc_dodajustanovonaziv.fill = GridBagConstraints.BOTH;
        gbc_dodajustanovonaziv.insets = new Insets(0, 0, 5, 5);
        gbc_dodajustanovonaziv.gridx = 1;
        gbc_dodajustanovonaziv.gridy = 1;
        ustanovapanel.add(dodajustanovonaziv, gbc_dodajustanovonaziv);

        JLabel nazivnaziv = new JLabel("Naziv");
        GridBagConstraints gbc_nazivnaziv = new GridBagConstraints();
        gbc_nazivnaziv.fill = GridBagConstraints.VERTICAL;
        gbc_nazivnaziv.insets = new Insets(0, 0, 5, 5);
        gbc_nazivnaziv.gridx = 2;
        gbc_nazivnaziv.gridy = 1;
        ustanovapanel.add(nazivnaziv, gbc_nazivnaziv);

        JLabel lblVrstaAmbulante = new JLabel("Vrsta ambulante");
        GridBagConstraints gbc_lblVrstaAmbulante = new GridBagConstraints();
        gbc_lblVrstaAmbulante.insets = new Insets(0, 0, 5, 5);
        gbc_lblVrstaAmbulante.gridx = 3;
        gbc_lblVrstaAmbulante.gridy = 1;
        ustanovapanel.add(lblVrstaAmbulante, gbc_lblVrstaAmbulante);

        JLabel lblTelefonskaStevilka = new JLabel("Telefonska stevilka");
        GridBagConstraints gbc_lblTelefonskaStevilka = new GridBagConstraints();
        gbc_lblTelefonskaStevilka.fill = GridBagConstraints.VERTICAL;
        gbc_lblTelefonskaStevilka.insets = new Insets(0, 0, 5, 5);
        gbc_lblTelefonskaStevilka.gridx = 4;
        gbc_lblTelefonskaStevilka.gridy = 1;
        ustanovapanel.add(lblTelefonskaStevilka, gbc_lblTelefonskaStevilka);

        nazivtextField = new JTextField();
        nazivtextField.setColumns(10);
        GridBagConstraints gbc_nazivtextField = new GridBagConstraints();
        gbc_nazivtextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_nazivtextField.insets = new Insets(0, 0, 5, 5);
        gbc_nazivtextField.gridx = 2;
        gbc_nazivtextField.gridy = 2;
        ustanovapanel.add(nazivtextField, gbc_nazivtextField);

        JRadioButton rdbtnAmbulanta = new JRadioButton("Ambulanta");
        GridBagConstraints gbc_rdbtnAmbulanta = new GridBagConstraints();
        gbc_rdbtnAmbulanta.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnAmbulanta.gridx = 3;
        gbc_rdbtnAmbulanta.gridy = 2;
        ustanovapanel.add(rdbtnAmbulanta, gbc_rdbtnAmbulanta);

        JRadioButton rdbtnCepilniCenter = new JRadioButton("Cepilni center");
        GridBagConstraints gbc_rdbtnCepilniCenter = new GridBagConstraints();
        gbc_rdbtnCepilniCenter.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnCepilniCenter.gridx = 3;
        gbc_rdbtnCepilniCenter.gridy = 3;
        ustanovapanel.add(rdbtnCepilniCenter, gbc_rdbtnCepilniCenter);

        ButtonGroup u = new ButtonGroup();					//buttongroup za ambulanta in cepilni center
        u.add(rdbtnAmbulanta);
        u.add(rdbtnCepilniCenter);

        telefonskastevilkatextField = new JTextField();
        telefonskastevilkatextField.setColumns(10);
        GridBagConstraints gbc_telefonskastevilkatextField = new GridBagConstraints();
        gbc_telefonskastevilkatextField.insets = new Insets(0, 0, 5, 5);
        gbc_telefonskastevilkatextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_telefonskastevilkatextField.gridx = 4;
        gbc_telefonskastevilkatextField.gridy = 2;
        ustanovapanel.add(telefonskastevilkatextField, gbc_telefonskastevilkatextField);

        JLabel lblEmail = new JLabel("Email");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail.gridx = 4;
        gbc_lblEmail.gridy = 3;
        ustanovapanel.add(lblEmail, gbc_lblEmail);

        JButton dodajustanovoNewButton_1 = new JButton("DODAJ");
        GridBagConstraints gbc_dodajustanovoNewButton_1 = new GridBagConstraints();
        gbc_dodajustanovoNewButton_1.gridheight = 4;
        gbc_dodajustanovoNewButton_1.insets = new Insets(0, 0, 5, 0);
        gbc_dodajustanovoNewButton_1.gridx = 6;
        gbc_dodajustanovoNewButton_1.gridy = 1;
        ustanovapanel.add(dodajustanovoNewButton_1, gbc_dodajustanovoNewButton_1);

        JLabel krajnaziv = new JLabel("Kraj");
        GridBagConstraints gbc_krajnaziv = new GridBagConstraints();
        gbc_krajnaziv.fill = GridBagConstraints.VERTICAL;
        gbc_krajnaziv.insets = new Insets(0, 0, 5, 5);
        gbc_krajnaziv.gridx = 2;
        gbc_krajnaziv.gridy = 3;
        ustanovapanel.add(krajnaziv, gbc_krajnaziv);

        krajtextField = new JTextField();
        krajtextField.setColumns(10);
        GridBagConstraints gbc_krajtextField = new GridBagConstraints();
        gbc_krajtextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_krajtextField.insets = new Insets(0, 0, 5, 5);
        gbc_krajtextField.gridx = 2;
        gbc_krajtextField.gridy = 4;
        ustanovapanel.add(krajtextField, gbc_krajtextField);

        emailtextField = new JTextField();
        emailtextField.setColumns(10);
        GridBagConstraints gbc_emailtextField = new GridBagConstraints();
        gbc_emailtextField.insets = new Insets(0, 0, 5, 5);
        gbc_emailtextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailtextField.gridx = 4;
        gbc_emailtextField.gridy = 4;
        ustanovapanel.add(emailtextField, gbc_emailtextField);

        JLabel nazivnaziv_1 = new JLabel("Naziv");
        GridBagConstraints gbc_nazivnaziv_1 = new GridBagConstraints();
        gbc_nazivnaziv_1.insets = new Insets(0, 0, 5, 5);
        gbc_nazivnaziv_1.gridx = 3;
        gbc_nazivnaziv_1.gridy = 6;
        ustanovapanel.add(nazivnaziv_1, gbc_nazivnaziv_1);

        JLabel krajnaziv_1 = new JLabel("Kraj");
        GridBagConstraints gbc_krajnaziv_1 = new GridBagConstraints();
        gbc_krajnaziv_1.insets = new Insets(0, 0, 5, 5);
        gbc_krajnaziv_1.gridx = 4;
        gbc_krajnaziv_1.gridy = 6;
        ustanovapanel.add(krajnaziv_1, gbc_krajnaziv_1);

        JLabel lblVrstaAmbulante_1 = new JLabel("Vrsta ambulante");
        GridBagConstraints gbc_lblVrstaAmbulante_1 = new GridBagConstraints();
        gbc_lblVrstaAmbulante_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblVrstaAmbulante_1.gridx = 5;
        gbc_lblVrstaAmbulante_1.gridy = 6;
        ustanovapanel.add(lblVrstaAmbulante_1, gbc_lblVrstaAmbulante_1);

        nazivtextField1 = new JTextField();
        nazivtextField1.setColumns(10);
        GridBagConstraints gbc_nazivtextField1 = new GridBagConstraints();
        gbc_nazivtextField1.insets = new Insets(0, 0, 5, 5);
        gbc_nazivtextField1.fill = GridBagConstraints.HORIZONTAL;
        gbc_nazivtextField1.gridx = 3;
        gbc_nazivtextField1.gridy = 7;
        ustanovapanel.add(nazivtextField1, gbc_nazivtextField1);

        JLabel spremeniustanovoNewLabel = new JLabel("SPREMENI USTANOVO");
        GridBagConstraints gbc_spremeniustanovoNewLabel = new GridBagConstraints();
        gbc_spremeniustanovoNewLabel.gridheight = 4;
        gbc_spremeniustanovoNewLabel.anchor = GridBagConstraints.WEST;
        gbc_spremeniustanovoNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_spremeniustanovoNewLabel.gridx = 1;
        gbc_spremeniustanovoNewLabel.gridy = 6;
        ustanovapanel.add(spremeniustanovoNewLabel, gbc_spremeniustanovoNewLabel);

        JTextField krajtextField1 = new JTextField();
        krajtextField1.setColumns(10);
        GridBagConstraints gbc_krajtextField1 = new GridBagConstraints();
        gbc_krajtextField1.insets = new Insets(0, 0, 5, 5);
        gbc_krajtextField1.fill = GridBagConstraints.HORIZONTAL;
        gbc_krajtextField1.gridx = 4;
        gbc_krajtextField1.gridy = 7;
        ustanovapanel.add(krajtextField1, gbc_krajtextField1);

        JButton spremeniustanovoNewButton_1 = new JButton("SPREMENI");
        GridBagConstraints gbc_spremeniustanovoNewButton_1 = new GridBagConstraints();
        gbc_spremeniustanovoNewButton_1.gridheight = 4;
        gbc_spremeniustanovoNewButton_1.insets = new Insets(0, 0, 5, 0);
        gbc_spremeniustanovoNewButton_1.gridx = 6;
        gbc_spremeniustanovoNewButton_1.gridy = 6;
        ustanovapanel.add(spremeniustanovoNewButton_1, gbc_spremeniustanovoNewButton_1);

        JComboBox comboBox_2 = new JComboBox();
        GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
        gbc_comboBox_2.gridheight = 2;
        gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_2.gridx = 2;
        gbc_comboBox_2.gridy = 7;
        ustanovapanel.add(comboBox_2, gbc_comboBox_2);

        JRadioButton rdbtnAmbulanta_1 = new JRadioButton("Ambulanta");
        GridBagConstraints gbc_rdbtnAmbulanta_1 = new GridBagConstraints();
        gbc_rdbtnAmbulanta_1.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnAmbulanta_1.gridx = 5;
        gbc_rdbtnAmbulanta_1.gridy = 7;
        ustanovapanel.add(rdbtnAmbulanta_1, gbc_rdbtnAmbulanta_1);

        JLabel lblTelefonskaStevilka_1 = new JLabel("Telefonska stevilka");
        GridBagConstraints gbc_lblTelefonskaStevilka_1 = new GridBagConstraints();
        gbc_lblTelefonskaStevilka_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblTelefonskaStevilka_1.gridx = 3;
        gbc_lblTelefonskaStevilka_1.gridy = 8;
        ustanovapanel.add(lblTelefonskaStevilka_1, gbc_lblTelefonskaStevilka_1);

        JLabel lblEmail_1 = new JLabel("Email");
        GridBagConstraints gbc_lblEmail_1 = new GridBagConstraints();
        gbc_lblEmail_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail_1.gridx = 4;
        gbc_lblEmail_1.gridy = 8;
        ustanovapanel.add(lblEmail_1, gbc_lblEmail_1);

        JRadioButton rdbtnCepilniCenter_1 = new JRadioButton("Cepilni center");
        GridBagConstraints gbc_rdbtnCepilniCenter_1 = new GridBagConstraints();
        gbc_rdbtnCepilniCenter_1.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnCepilniCenter_1.gridx = 5;
        gbc_rdbtnCepilniCenter_1.gridy = 8;
        ustanovapanel.add(rdbtnCepilniCenter_1, gbc_rdbtnCepilniCenter_1);

        ButtonGroup ustanova_1 = new ButtonGroup();					//buttongroup za ambulanta in cepilni center
        ustanova_1.add(rdbtnAmbulanta_1);
        ustanova_1.add(rdbtnCepilniCenter_1);

        telefonskastevilkatextField_1 = new JTextField();
        telefonskastevilkatextField_1.setColumns(10);
        GridBagConstraints gbc_telefonskastevilkatextField_1 = new GridBagConstraints();
        gbc_telefonskastevilkatextField_1.insets = new Insets(0, 0, 5, 5);
        gbc_telefonskastevilkatextField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_telefonskastevilkatextField_1.gridx = 3;
        gbc_telefonskastevilkatextField_1.gridy = 9;
        ustanovapanel.add(telefonskastevilkatextField_1, gbc_telefonskastevilkatextField_1);

        emailtextField_1 = new JTextField();
        emailtextField_1.setColumns(10);
        GridBagConstraints gbc_emailtextField_1 = new GridBagConstraints();
        gbc_emailtextField_1.insets = new Insets(0, 0, 5, 5);
        gbc_emailtextField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailtextField_1.gridx = 4;
        gbc_emailtextField_1.gridy = 9;
        ustanovapanel.add(emailtextField_1, gbc_emailtextField_1);

        JLabel odstraniustanovoNewLabel_1 = new JLabel("ODSTRANI USTANOVO");
        GridBagConstraints gbc_odstraniustanovoNewLabel_1 = new GridBagConstraints();
        gbc_odstraniustanovoNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_odstraniustanovoNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_odstraniustanovoNewLabel_1.gridx = 1;
        gbc_odstraniustanovoNewLabel_1.gridy = 11;
        ustanovapanel.add(odstraniustanovoNewLabel_1, gbc_odstraniustanovoNewLabel_1);

        JComboBox odstraniustanovocomboBox = new JComboBox();
        GridBagConstraints gbc_odstraniustanovocomboBox = new GridBagConstraints();
        gbc_odstraniustanovocomboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_odstraniustanovocomboBox.gridwidth = 2;
        gbc_odstraniustanovocomboBox.insets = new Insets(0, 0, 5, 5);
        gbc_odstraniustanovocomboBox.gridx = 2;
        gbc_odstraniustanovocomboBox.gridy = 11;
        ustanovapanel.add(odstraniustanovocomboBox, gbc_odstraniustanovocomboBox);

        JButton odstranibtnNewButton_1_1 = new JButton("ODSTRANI");
        GridBagConstraints gbc_odstranibtnNewButton_1_1 = new GridBagConstraints();
        gbc_odstranibtnNewButton_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_odstranibtnNewButton_1_1.gridx = 4;
        gbc_odstranibtnNewButton_1_1.gridy = 11;
        ustanovapanel.add(odstranibtnNewButton_1_1, gbc_odstranibtnNewButton_1_1);

        JButton odstranibtnNewButton_3 = new JButton("ODSTRANI VSE");
        GridBagConstraints gbc_odstranibtnNewButton_3 = new GridBagConstraints();
        gbc_odstranibtnNewButton_3.gridwidth = 2;
        gbc_odstranibtnNewButton_3.insets = new Insets(0, 0, 5, 0);
        gbc_odstranibtnNewButton_3.gridx = 5;
        gbc_odstranibtnNewButton_3.gridy = 11;
        ustanovapanel.add(odstranibtnNewButton_3, gbc_odstranibtnNewButton_3);


        //*********************************	DODAJ USTANOVO ************************************

        dodajustanovoNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String naziv = nazivtextField.getText();
                String kraj = krajtextField.getText();
                String telefonska = telefonskastevilkatextField.getText();
                String email = emailtextField.getText();

                Ustanova u = null;

                if (rdbtnAmbulanta.isSelected()) {
                    u = new Ambulanta (naziv, new Kraj(kraj), telefonska);
                    Ambulanta a = new Ambulanta(naziv, new Kraj(kraj), telefonska);
                    amb.addElement(a);
                    ambulanta.add(a);
                    ustanova.add(u);
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("Ambulanta.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(ambulanta);
                        outputstream.flush();
                        outputstream.close();
                        FileWriter o = new FileWriter("dogodki.txt", true);
                        o.write(LocalDateTime.now() + ": uspesno ste dodali ambulanto - ZAZNAMEK" + "\n");
                        o.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else if (rdbtnCepilniCenter.isSelected()) {
                    u = new CepilniCenter (naziv, new Kraj (kraj), email);
                    CepilniCenter cpc = new CepilniCenter(naziv, new Kraj (kraj), email);
                    cepcenter.addElement(cpc);
                    cpcenter.add(cpc);
                    ustanova.add(u);
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("CepilniCenter.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(cpcenter);
                        outputstream.flush();
                        outputstream.close();
                        FileWriter o = new FileWriter("dogodki.txt", true);
                        o.write(LocalDateTime.now() + ": uspesno ste dodali cepilnicenter - ZAZNAMEK" + "\n");
                        o.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    System.out.println("nedefinirano");
                }
                ust.addElement(u);
                System.out.println(ustanova);
                nazivtextField.setText("");
                krajtextField.setText("");
                emailtextField.setText("");
                telefonskastevilkatextField.setText("");
                try {
                    FileWriter o = new FileWriter("dogodki.txt", true);
                    o.write(LocalDateTime.now() + ": uspesno ste dodali ustanovo - ZAZNAMEK" + "\n");
                    o.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    ObjectOutputStream outputstream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("Ustanova.ser")));
                    outputstream.writeObject(ustanova);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //*********************************	SPREMENI USTANOVO *******************************************
        comboBox_2.setModel(ust);

        spremeniustanovoNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String naziv = nazivtextField1.getText();
                String kraj = krajtextField1.getText();
                String telefonska = telefonskastevilkatextField_1.getText();
                String email = emailtextField_1.getText();

                Ustanova u = null;

                if (rdbtnAmbulanta_1.isSelected()) {
                    u = new Ambulanta (naziv, new Kraj(kraj), telefonska);
                    Ambulanta a = new Ambulanta (naziv, new Kraj(kraj), telefonska);

                    int x = comboBox_2.getSelectedIndex();
                    comboBox_2.setSelectedItem(u);
                    ust.removeElementAt(x);
                    ust.addElement(u);
                    ustanova.set(x, u);
                    ambulanta.set(x, a);
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("Ambulanta.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(ambulanta);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else if (rdbtnCepilniCenter_1.isSelected()) {
                    u = new CepilniCenter (naziv, new Kraj (kraj), email);
                    CepilniCenter z = new CepilniCenter (naziv, new Kraj (kraj), email);

                    cepcenter.addElement((CepilniCenter) u);

                    int x = comboBox_2.getSelectedIndex();
                    comboBox_2.setSelectedItem(u);

                    ust.removeElementAt(x);
                    ust.addElement(u);
                    ustanova.set(x, u);
                    cpcenter.set(x, z);

                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("CepilniCenter.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(cpcenter);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                nazivtextField1.setText("");
                krajtextField1.setText("");
                emailtextField_1.setText("");
                telefonskastevilkatextField_1.setText("");
                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("Ustanova.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(ustanova);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //*********************************	ODSTRANI USTANOVO *******************************************

        odstraniustanovocomboBox.setModel(ust);

        odstranibtnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(odstraniustanovocomboBox.getSelectedIndex() >= 0) {
                    ust.removeElementAt(odstraniustanovocomboBox.getSelectedIndex());
                    ustanova.remove(odstraniustanovocomboBox.getSelectedIndex());
                }
                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("Ustanova.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(ustanova);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        odstranibtnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ust.removeAllElements();
                for(int i = 0; i < ust.getSize(); i++) {
                    ustanova.remove(i);
                }
                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("Ustanova.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(ustanova);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        //**************************** P R E V O Z N O   S R E D S T V O - P A N E L ***********************************************

        JPanel prevoznosredstvopanel = new JPanel();
        tabbedPane.addTab("Prevozno Sredstvo", null, prevoznosredstvopanel, null);
        GridBagLayout gbl_prevoznosredstvopanel = new GridBagLayout();
        gbl_prevoznosredstvopanel.rowHeights = new int[]{37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 0};
        gbl_prevoznosredstvopanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gbl_prevoznosredstvopanel.columnWeights = new double[]{1.0};
        gbl_prevoznosredstvopanel.columnWidths = new int[]{282};
        prevoznosredstvopanel.setLayout(gbl_prevoznosredstvopanel);

        JLabel dodajprevoznosredstvolblNewLabel = new JLabel("DODAJ PREVOZNO SREDSTVO");
        GridBagConstraints gbc_dodajprevoznosredstvolblNewLabel = new GridBagConstraints();
        gbc_dodajprevoznosredstvolblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_dodajprevoznosredstvolblNewLabel.gridx = 0;
        gbc_dodajprevoznosredstvolblNewLabel.gridy = 0;
        prevoznosredstvopanel.add(dodajprevoznosredstvolblNewLabel, gbc_dodajprevoznosredstvolblNewLabel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        prevoznosredstvopanel.add(panel, gbc_panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel nazivnaziv_2 = new JLabel("Naziv");
        panel.add(nazivnaziv_2);
        nazivtextField3 = new JTextField();
        nazivtextField3.setColumns(10);
        panel.add(nazivtextField3);

        JLabel lblRegistrskaStevilka = new JLabel("Registrska stevilka");
        panel.add(lblRegistrskaStevilka);
        registrskastevilkatextField = new JTextField();
        registrskastevilkatextField.setColumns(10);
        panel.add(registrskastevilkatextField);

        JLabel lblZnamka = new JLabel("Znamka");
        panel.add(lblZnamka);
        znamkatextField = new JTextField();
        znamkatextField.setColumns(10);
        panel.add(znamkatextField);

        JLabel kapacitetaNewLabel = new JLabel("Kapaciteta");
        panel.add(kapacitetaNewLabel);

        JSpinner spinner = new JSpinner();
        panel.add(spinner);

        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 2;
        prevoznosredstvopanel.add(panel_2, gbc_panel_2);

        JLabel lblNewLabel = new JLabel("Kateri tip cepiva sprejema:");
        panel_2.add(lblNewLabel);

        JComboBox<TipCepiva> comboBoxTipCepiva = new JComboBox<TipCepiva>();
        comboBoxTipCepiva.setModel(new DefaultComboBoxModel(TipCepiva.values()));
        panel_2.add(comboBoxTipCepiva);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.gridy = 3;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.gridx = 0;
        prevoznosredstvopanel.add(panel_1, gbc_panel_1);

        JLabel lbltipprevoznegasredstva = new JLabel("Tip prevoznega sredstva");
        panel_1.add(lbltipprevoznegasredstva);

        JRadioButton rdbtnAvto = new JRadioButton("Avto");
        panel_1.add(rdbtnAvto);

        JRadioButton rdbtnKombi = new JRadioButton("Kombi");
        panel_1.add(rdbtnKombi);

        ButtonGroup avtokombi = new ButtonGroup();					//buttongroup za avto kombi
        avtokombi.add(rdbtnAvto);
        avtokombi.add(rdbtnKombi);

        JLabel lblMoznostRazsiritve = new JLabel("Moznost razsiritve");
        panel_1.add(lblMoznostRazsiritve);

        JRadioButton rdbtnDa = new JRadioButton("DA");
        panel_1.add(rdbtnDa);

        JRadioButton rdbtnNe = new JRadioButton("NE");
        panel_1.add(rdbtnNe);

        ButtonGroup moznostrazsiritve = new ButtonGroup();					//buttongroup za DA in NE
        moznostrazsiritve.add(rdbtnDa);
        moznostrazsiritve.add(rdbtnNe);

        JButton btnDodajPrevoznoSredstvo = new JButton("DODAJ");
        GridBagConstraints gbc_btnDodajPrevoznoSredstvo = new GridBagConstraints();
        gbc_btnDodajPrevoznoSredstvo.insets = new Insets(0, 0, 5, 0);
        gbc_btnDodajPrevoznoSredstvo.gridx = 0;
        gbc_btnDodajPrevoznoSredstvo.gridy = 4;
        prevoznosredstvopanel.add(btnDodajPrevoznoSredstvo, gbc_btnDodajPrevoznoSredstvo);

        JLabel lblSpremeniPrevoznoSredstvo = new JLabel("SPREMENI PREVOZNO SREDSTVO");
        GridBagConstraints gbc_lblSpremeniPrevoznoSredstvo = new GridBagConstraints();
        gbc_lblSpremeniPrevoznoSredstvo.gridy = 5;
        gbc_lblSpremeniPrevoznoSredstvo.insets = new Insets(0, 0, 5, 0);
        gbc_lblSpremeniPrevoznoSredstvo.gridx = 0;
        prevoznosredstvopanel.add(lblSpremeniPrevoznoSredstvo, gbc_lblSpremeniPrevoznoSredstvo);

        JPanel panel_6 = new JPanel();
        GridBagConstraints gbc_panel_6 = new GridBagConstraints();
        gbc_panel_6.insets = new Insets(0, 0, 5, 0);
        gbc_panel_6.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_6.gridx = 0;
        gbc_panel_6.gridy = 6;
        prevoznosredstvopanel.add(panel_6, gbc_panel_6);

        JComboBox<PrevoznoSredstvo> comboBoxPrevoznoSredstvo = new JComboBox<PrevoznoSredstvo>();
        panel_6.add(comboBoxPrevoznoSredstvo);

        JPanel panel_3 = new JPanel();
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.gridy = 7;
        gbc_panel_3.insets = new Insets(0, 0, 5, 0);
        gbc_panel_3.gridx = 0;
        prevoznosredstvopanel.add(panel_3, gbc_panel_3);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel nazivnaziv_2_1 = new JLabel("Naziv");
        panel_3.add(nazivnaziv_2_1);

        textFieldNaziv2 = new JTextField();
        panel_3.add(textFieldNaziv2);
        textFieldNaziv2.setColumns(10);

        JLabel lblRegistrskaStevilka_1 = new JLabel("Registrska stevilka");
        panel_3.add(lblRegistrskaStevilka_1);

        textFieldRegistrskaStevilka = new JTextField();
        textFieldRegistrskaStevilka.setColumns(10);
        panel_3.add(textFieldRegistrskaStevilka);

        JLabel lblZnamka_1 = new JLabel("Znamka");
        panel_3.add(lblZnamka_1);

        textFieldZnamka = new JTextField();
        textFieldZnamka.setColumns(10);
        panel_3.add(textFieldZnamka);

        JLabel kapacitetaNewLabel_1 = new JLabel("Kapaciteta");
        panel_3.add(kapacitetaNewLabel_1);

        JSpinner spinnerKapaciteta = new JSpinner();
        panel_3.add(spinnerKapaciteta);

        JPanel panel_2_1 = new JPanel();
        GridBagConstraints gbc_panel_2_1 = new GridBagConstraints();
        gbc_panel_2_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_2_1.gridx = 0;
        gbc_panel_2_1.gridy = 8;
        prevoznosredstvopanel.add(panel_2_1, gbc_panel_2_1);

        JLabel lblNewLabel_1 = new JLabel("Kateri tip cepiva sprejema:");
        panel_2_1.add(lblNewLabel_1);

        JComboBox comboBoxTipCepiva1 = new JComboBox();
        comboBoxTipCepiva1.setModel(new DefaultComboBoxModel(TipCepiva.values()));

        panel_2_1.add(comboBoxTipCepiva1);

        JPanel panel_4 = new JPanel();
        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
        gbc_panel_4.gridy = 9;
        gbc_panel_4.insets = new Insets(0, 0, 5, 0);
        gbc_panel_4.gridx = 0;
        prevoznosredstvopanel.add(panel_4, gbc_panel_4);

        JLabel lbltipprevoznegasredstva_1 = new JLabel("Tip prevoznega sredstva");
        panel_4.add(lbltipprevoznegasredstva_1);

        JRadioButton rdbtnAvto_1 = new JRadioButton("Avto");
        panel_4.add(rdbtnAvto_1);

        JRadioButton rdbtnKombi_1 = new JRadioButton("Kombi");
        panel_4.add(rdbtnKombi_1);

        ButtonGroup avtokombi1 = new ButtonGroup();					//buttongroup za avto kombi
        avtokombi1.add(rdbtnAvto_1);
        avtokombi1.add(rdbtnKombi_1);

        JLabel lblMoznostRazsiritve_1 = new JLabel("Moznost razsiritve");
        panel_4.add(lblMoznostRazsiritve_1);

        JRadioButton rdbtnDa_1 = new JRadioButton("DA");
        panel_4.add(rdbtnDa_1);

        JRadioButton rdbtnNe_1 = new JRadioButton("NE");
        panel_4.add(rdbtnNe_1);

        ButtonGroup moznostrazsiritve_1 = new ButtonGroup();					//buttongroup za DA in NE
        moznostrazsiritve_1.add(rdbtnDa_1);
        moznostrazsiritve_1.add(rdbtnNe_1);

        JPanel panel_5 = new JPanel();
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.gridy = 10;
        gbc_panel_5.insets = new Insets(0, 0, 5, 0);
        gbc_panel_5.gridx = 0;
        prevoznosredstvopanel.add(panel_5, gbc_panel_5);

        JButton btnSpremeni = new JButton("SPREMENI");
        panel_5.add(btnSpremeni);

        JPanel panel_8 = new JPanel();
        GridBagConstraints gbc_panel_8 = new GridBagConstraints();
        gbc_panel_8.insets = new Insets(0, 0, 5, 0);
        gbc_panel_8.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_8.gridx = 0;
        gbc_panel_8.gridy = 11;
        prevoznosredstvopanel.add(panel_8, gbc_panel_8);

        JLabel lblOdstraniPrevoznoSredstvo = new JLabel("ODSTRANI PREVOZNO SREDSTVO");
        panel_8.add(lblOdstraniPrevoznoSredstvo);

        JPanel panel_7 = new JPanel();
        GridBagConstraints gbc_panel_7 = new GridBagConstraints();
        gbc_panel_7.insets = new Insets(0, 0, 5, 0);
        gbc_panel_7.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_7.gridx = 0;
        gbc_panel_7.gridy = 12;
        prevoznosredstvopanel.add(panel_7, gbc_panel_7);

        JComboBox comboBoxPrevoznoSredstvo_1 = new JComboBox();
        panel_7.add(comboBoxPrevoznoSredstvo_1);

        JPanel panel_9 = new JPanel();
        GridBagConstraints gbc_panel_9 = new GridBagConstraints();
        gbc_panel_9.insets = new Insets(0, 0, 5, 0);
        gbc_panel_9.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_9.gridx = 0;
        gbc_panel_9.gridy = 13;
        prevoznosredstvopanel.add(panel_9, gbc_panel_9);

        JButton btnOdstrani = new JButton("ODSTRANI");
        panel_9.add(btnOdstrani);

        JButton btnOdstraniVse = new JButton("ODSTRANI VSE");
        panel_9.add(btnOdstraniVse);

        //*********************************	DODAJ PREVOZNO SREDSTVO ************************************

        btnDodajPrevoznoSredstvo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String naziv = nazivtextField3.getText();
                String registrska = registrskastevilkatextField.getText();
                String znamka = znamkatextField.getText();
                int kapaciteta = (Integer) spinner.getValue();

                TipCepiva tip = (TipCepiva) comboBoxTipCepiva.getSelectedItem();


                if (rdbtnAvto.isSelected()) {
                    Avto ps = new Avto (naziv, registrska, znamka, kapaciteta, tip);

                    arrayAvto.add(ps);

                    seznamAvto.addElement(ps);
                    prevoz.addElement(ps);
                    prevoznosredstvo.add(ps);

                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("Avto.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(arrayAvto);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else if (rdbtnKombi.isSelected()) {
                    boolean razsiritev = false;
                    if (rdbtnDa.isSelected()) {
                        razsiritev = true;
                    } else if (rdbtnNe.isSelected()) {
                        razsiritev = false;
                    } else {
                        System.out.println("Nedefinirano.");
                    }
                    Kombi ps = new Kombi (naziv, registrska, znamka, kapaciteta, tip, razsiritev);

                    arrayKombi.add(ps);

                    seznamKombi.addElement(ps);
                    prevoz.addElement(ps);
                    prevoznosredstvo.add(ps);
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("Kombi.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(arrayKombi);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    System.out.println("Nedefinirano.");
                }

                nazivtextField3.setText("");
                registrskastevilkatextField.setText("");
                znamkatextField.setText("");

                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("PrevoznoSredstvo.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(prevoznosredstvo);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //*********************************	SPREMENI PREVOZNO SREDSTVO *******************************************
        comboBoxPrevoznoSredstvo.setModel(prevoz);
        btnSpremeni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String naziv = textFieldNaziv2.getText();
                String registrska = textFieldRegistrskaStevilka.getText();
                String znamka = textFieldZnamka.getText();
                int kapaciteta = (Integer) spinnerKapaciteta.getValue();

                TipCepiva tip = (TipCepiva) comboBoxTipCepiva.getSelectedItem();

                PrevoznoSredstvo ps = null;

                if (rdbtnAvto_1.isSelected()) {
                    ps = new Avto (naziv, registrska, znamka, kapaciteta, tip);
                    int x = comboBoxPrevoznoSredstvo.getSelectedIndex();
                    arrayAvto.set(x, (Avto) ps);

                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("Avto.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(arrayAvto);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else if (rdbtnKombi_1.isSelected()) {
                    boolean razsiritev = false;
                    if (rdbtnDa_1.isSelected()) {
                        razsiritev = true;
                    } else if (rdbtnNe_1.isSelected()) {
                        razsiritev = false;
                    } else {
                        System.out.println("Nedefinirano.");
                    }
                    ps = new Kombi (naziv, registrska, znamka, kapaciteta, tip, razsiritev);
                    int x = comboBoxPrevoznoSredstvo.getSelectedIndex();
                    arrayKombi.set(x, (Kombi) ps);

                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("Kombi.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(arrayKombi);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    System.out.println("Nedefinirano.");
                }

                int x = comboBoxPrevoznoSredstvo.getSelectedIndex();
                comboBoxPrevoznoSredstvo.setSelectedItem(prevoznosredstvo);
                prevoz.removeElementAt(x);
                prevoz.addElement(ps);
                prevoznosredstvo.set(x, ps);
                textFieldNaziv2.setText("");
                textFieldRegistrskaStevilka.setText("");
                textFieldZnamka.setText("");

                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("PrevoznoSredstvo.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(prevoznosredstvo);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        //*********************************	ODSTRANI PREVOZNO SREDSTVO *******************************************

        comboBoxPrevoznoSredstvo_1.setModel(prevoz);

        btnOdstrani.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(comboBoxPrevoznoSredstvo_1.getSelectedIndex() >= 0) {
                    prevoz.removeElementAt(comboBoxPrevoznoSredstvo_1.getSelectedIndex());
                    prevoznosredstvo.remove(comboBoxPrevoznoSredstvo_1.getSelectedIndex());
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("PrevoznoSredstvo.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(prevoznosredstvo);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        btnOdstraniVse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prevoz.removeAllElements();
                for(int i = 0; i < prevoz.getSize(); i++) {
                    prevoznosredstvo.remove(i);
                }
                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("PrevoznoSredstvo.ser");
                    GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                    ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                    outputstream.writeObject(prevoznosredstvo);
                    outputstream.flush();
                    outputstream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        //**************************** C E P I L N I   C E N T E R - P A N E L ***********************************************

        JPanel CepivaVCepilnemCentrupanel = new JPanel();
        tabbedPane.addTab("Cepiva v cepilnem centru", null, CepivaVCepilnemCentrupanel, null);
        CepivaVCepilnemCentrupanel.setLayout(new BorderLayout(0, 0));

        JButton btndodajvcepilnicenter = new JButton("DODAJ V CEPILNI CENTER");
        CepivaVCepilnemCentrupanel.add(btndodajvcepilnicenter, BorderLayout.WEST);

        JButton btnodstraniizcepilnegacentra = new JButton("ODSTRANI IZ CEPILNEGA CENTRA");
        CepivaVCepilnemCentrupanel.add(btnodstraniizcepilnegacentra, BorderLayout.EAST);

        JPanel centerceppanel = new JPanel();
        CepivaVCepilnemCentrupanel.add(centerceppanel, BorderLayout.CENTER);
        GridBagLayout gbl_centerceppanel = new GridBagLayout();
        gbl_centerceppanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0};
        gbl_centerceppanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_centerceppanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        gbl_centerceppanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        centerceppanel.setLayout(gbl_centerceppanel);

        JLabel lblCepilniCenter = new JLabel("CEPILNI CENTER");
        lblCepilniCenter.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblCepilniCenter = new GridBagConstraints();
        gbc_lblCepilniCenter.insets = new Insets(0, 0, 5, 0);
        gbc_lblCepilniCenter.gridwidth = 19;
        gbc_lblCepilniCenter.gridx = 0;
        gbc_lblCepilniCenter.gridy = 9;
        centerceppanel.add(lblCepilniCenter, gbc_lblCepilniCenter);

        JComboBox<CepilniCenter> nazivCepilniCentercomboBox = new JComboBox<CepilniCenter>();
        GridBagConstraints gbc_nazivCepilniCentercomboBox = new GridBagConstraints();
        gbc_nazivCepilniCentercomboBox.insets = new Insets(0, 0, 5, 0);
        gbc_nazivCepilniCentercomboBox.gridwidth = 19;
        gbc_nazivCepilniCentercomboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_nazivCepilniCentercomboBox.gridx = 0;
        gbc_nazivCepilniCentercomboBox.gridy = 10;
        centerceppanel.add(nazivCepilniCentercomboBox, gbc_nazivCepilniCentercomboBox);

        JLabel lblIzberiSiCepivo = new JLabel("Izberi si cepivo");
        lblIzberiSiCepivo.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblIzberiSiCepivo = new GridBagConstraints();
        gbc_lblIzberiSiCepivo.gridwidth = 19;
        gbc_lblIzberiSiCepivo.insets = new Insets(0, 0, 5, 0);
        gbc_lblIzberiSiCepivo.gridx = 0;
        gbc_lblIzberiSiCepivo.gridy = 11;
        centerceppanel.add(lblIzberiSiCepivo, gbc_lblIzberiSiCepivo);

        JComboBox<Cepivo> cepivocomboBox = new JComboBox<Cepivo>();
        GridBagConstraints gbc_cepivocomboBox = new GridBagConstraints();
        gbc_cepivocomboBox.insets = new Insets(0, 0, 5, 0);
        gbc_cepivocomboBox.gridwidth = 19;
        gbc_cepivocomboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_cepivocomboBox.gridx = 0;
        gbc_cepivocomboBox.gridy = 12;
        centerceppanel.add(cepivocomboBox, gbc_cepivocomboBox);

        //*********************************	DODAJ CEPIVO V CEPILNI CENTER ************************************
        nazivCepilniCentercomboBox.setModel(cepcenter);
        cepivocomboBox.setModel(model);

        btndodajvcepilnicenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cepivocomboBox.getSelectedIndex() >=0 -1 && nazivCepilniCentercomboBox.getSelectedIndex() >=0 -1) {
                    Cepivo x = model.getElementAt(cepivocomboBox.getSelectedIndex());	//izbrano cepivo
                    CepilniCenter y = cepcenter.getElementAt(nazivCepilniCentercomboBox.getSelectedIndex()); //izbran cepilni center
                    y.dodajCepivo(x);
                    System.out.println("Cepivo " + x + " je dodano.");
                    System.out.println(y.toString());

                    int w = nazivCepilniCentercomboBox.getSelectedIndex();
                    nazivCepilniCentercomboBox.setSelectedItem(y);
                    cepcenter.removeElementAt(w);
                    cepcenter.addElement(y);

                    try {
                        FileWriter o = new FileWriter("dogodki.txt", true);
                        o.write(LocalDateTime.now() + ": uspesno ste dodali cepivo v cepilni center - ZAZNAMEK" + "\n");
                        o.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });


        //*********************************	ODSTRANI IZ CEPILNEGA CENTRA ************************************

        btnodstraniizcepilnegacentra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cepivocomboBox.getSelectedIndex() >=0 && nazivCepilniCentercomboBox.getSelectedIndex() >=0 -1) {
                    Cepivo x = model.getElementAt(cepivocomboBox.getSelectedIndex());	//izbrano cepivo
                    CepilniCenter y = cepcenter.getElementAt(nazivCepilniCentercomboBox.getSelectedIndex()); //izbran cepilni center
                    y.odstraniCepivo(x);
                    System.out.println("Cepivo " + x + " je odstranjeno.");
                    System.out.println(y.toString());

                    int w = nazivCepilniCentercomboBox.getSelectedIndex();
                    nazivCepilniCentercomboBox.setSelectedItem(y);
                    cepcenter.removeElementAt(w);
                    cepcenter.addElement(y);

                    try {
                        FileWriter o = new FileWriter("dogodki.txt", true);
                        o.write(LocalDateTime.now() + ": uspesno ste odstranili cepivo iz cepilnega centra - ZAZNAMEK" + "\n");
                        o.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        //**************************** L O G I S T I K A - P A N E L ***********************************************

        JPanel Logistikapanel = new JPanel();
        tabbedPane.addTab("Logistika", null, Logistikapanel, null);
        Logistikapanel.setLayout(new BorderLayout(0, 0));

        JPanel centerpanel = new JPanel();
        Logistikapanel.add(centerpanel, BorderLayout.CENTER);
        GridBagLayout gbl_centerpanel = new GridBagLayout();
        gbl_centerpanel.columnWidths = new int[]{0, 0, 0};
        gbl_centerpanel.rowHeights = new int[]{37, 37, 37, 37, 37, 37, 0};
        gbl_centerpanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_centerpanel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        centerpanel.setLayout(gbl_centerpanel);

        JLabel lblNewLabel_3 = new JLabel("Kam zelite natovoriti/raztovoriti cepivo?");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_3.anchor = GridBagConstraints.SOUTH;
        gbc_lblNewLabel_3.gridwidth = 2;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 0;
        centerpanel.add(lblNewLabel_3, gbc_lblNewLabel_3);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("AVTO");
        GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
        gbc_rdbtnNewRadioButton_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton_1.gridx = 0;
        gbc_rdbtnNewRadioButton_1.gridy = 1;
        centerpanel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("KOMBI");
        GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
        gbc_rdbtnNewRadioButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnNewRadioButton.gridx = 1;
        gbc_rdbtnNewRadioButton.gridy = 1;
        centerpanel.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

        JLabel lblIzborCepilniCenter = new JLabel("Izberite cepilni center:");
        GridBagConstraints gbc_lblIzborCepilniCenter = new GridBagConstraints();
        gbc_lblIzborCepilniCenter.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblIzborCepilniCenter.insets = new Insets(0, 0, 5, 5);
        gbc_lblIzborCepilniCenter.gridx = 0;
        gbc_lblIzborCepilniCenter.gridy = 2;
        centerpanel.add(lblIzborCepilniCenter, gbc_lblIzborCepilniCenter);
        lblIzborCepilniCenter.setHorizontalAlignment(SwingConstants.CENTER);

        JComboBox<CepilniCenter> comboBox_4 = new JComboBox<CepilniCenter>();
        GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
        gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_4.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox_4.gridx = 1;
        gbc_comboBox_4.gridy = 2;
        centerpanel.add(comboBox_4, gbc_comboBox_4);

        JButton btnNewButton = new JButton("NATOVORI");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnNewButton.gridwidth = 2;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 3;
        centerpanel.add(btnNewButton, gbc_btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("Izberite ambulanto:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 4;
        centerpanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

        JComboBox<Ambulanta> comboBoxAmbulanta = new JComboBox<Ambulanta>();
        GridBagConstraints gbc_comboBoxAmbulanta = new GridBagConstraints();
        gbc_comboBoxAmbulanta.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxAmbulanta.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxAmbulanta.gridx = 1;
        gbc_comboBoxAmbulanta.gridy = 4;
        centerpanel.add(comboBoxAmbulanta, gbc_comboBoxAmbulanta);

        JButton btnNewButton_1 = new JButton("RAZTOVORI");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnNewButton_1.gridwidth = 2;
        gbc_btnNewButton_1.gridx = 0;
        gbc_btnNewButton_1.gridy = 5;
        centerpanel.add(btnNewButton_1, gbc_btnNewButton_1);

        JList<Avto> listAvto = new JList<Avto>();
        listAvto.setModel(seznamAvto);
        Logistikapanel.add(listAvto, BorderLayout.WEST);

        JList<Cepivo> listCepiva = new JList<Cepivo>();
        listCepiva.setModel(model);
        Logistikapanel.add(listCepiva, BorderLayout.NORTH);

        JList<Kombi> listKombi = new JList<Kombi>();
        listKombi.setModel(seznamKombi);
        Logistikapanel.add(listKombi, BorderLayout.EAST);

        rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listKombi.setEnabled(false);
                listAvto.setEnabled(true);
                listKombi.clearSelection();
            }
        });


        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listAvto.setEnabled(false);
                listKombi.setEnabled(true);
                listAvto.clearSelection();
            }
        });

        ButtonGroup prevozno = new ButtonGroup();					//buttongroup za kombi in avto
        prevozno.add(rdbtnNewRadioButton_1);
        prevozno.add(rdbtnNewRadioButton);


        //*********************************	NATOVORI ************************************
        comboBox_4.setModel(cepcenter);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listAvto.getSelectedIndex() >= 0) {
                    Avto avto = listAvto.getSelectedValue();
                    Cepivo cepivo = listCepiva.getSelectedValue();
                    avto.natovoriCepivo(cepivo);
                    System.out.println(avto.toString());
                    listCepivAvto.addElement(cepivo); //seznam cepiv natovorjenih na avto
                    cepivoavto.add(cepivo);
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("CepivoAvto.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(cepivoavto);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                } else if (listKombi.getSelectedIndex() >= 0) {
                    Kombi kombi = listKombi.getSelectedValue();
                    Cepivo cepivo = listCepiva.getSelectedValue();
                    kombi.natovoriCepivo(cepivo);
                    System.out.println(kombi.toString());
                    listCepivKombi.addElement(cepivo);
                    cepivokombi.add(cepivo);
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream("CepivoKombi.ser");
                        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                        ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                        outputstream.writeObject(cepivokombi);
                        outputstream.flush();
                        outputstream.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Nic niste izbrali.");
                }

            }
        });


        //*********************************	RAZTOVORI ************************************

        comboBoxAmbulanta.setModel(amb);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ambulanta x = amb.getElementAt(comboBoxAmbulanta.getSelectedIndex()); 			//izbrana ambulanta
                PrevoznoSredstvo y = prevoz.getElementAt(listKombi.getSelectedIndex()); //izbrano prevozno sredstvo

                x.prevzamiCepiva(y);

                System.out.println(x + " prevzame naslednje cepivo: " + model.getElementAt(listAvto.getSelectedIndex()));

            }
        });
        //**************************** I Z P I S - P A N E L ***********************************************

        JPanel izpispanel = new JPanel();
        tabbedPane.addTab("Izpis", null, izpispanel, null);
        izpispanel.setLayout(new CardLayout(0, 0));

        JPanel kombipanel = new JPanel();
        izpispanel.add(kombipanel, "2");
        GridBagLayout gbl_kombipanel = new GridBagLayout();
        gbl_kombipanel.columnWidths = new int[]{300, 300, 300, 0};
        gbl_kombipanel.rowHeights = new int[]{171, 40, 40, 50, 50, 0, 0};
        gbl_kombipanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_kombipanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        kombipanel.setLayout(gbl_kombipanel);

        JLabel lblKombi = new JLabel("KOMBI");
        lblKombi.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblKombi = new GridBagConstraints();
        gbc_lblKombi.fill = GridBagConstraints.BOTH;
        gbc_lblKombi.insets = new Insets(0, 0, 5, 5);
        gbc_lblKombi.gridx = 1;
        gbc_lblKombi.gridy = 1;
        kombipanel.add(lblKombi, gbc_lblKombi);

        JLabel lblSeznamCepiv_2 = new JLabel("Seznam cepiv:");
        GridBagConstraints gbc_lblSeznamCepiv_2 = new GridBagConstraints();
        gbc_lblSeznamCepiv_2.fill = GridBagConstraints.VERTICAL;
        gbc_lblSeznamCepiv_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeznamCepiv_2.gridx = 1;
        gbc_lblSeznamCepiv_2.gridy = 2;
        kombipanel.add(lblSeznamCepiv_2, gbc_lblSeznamCepiv_2);

        JList<Cepivo> seznamCepivKombi = new JList<Cepivo>();
        seznamCepivKombi.setModel(listCepivKombi);
        GridBagConstraints gbc_seznamCepivKombi = new GridBagConstraints();
        gbc_seznamCepivKombi.insets = new Insets(0, 0, 5, 5);
        gbc_seznamCepivKombi.fill = GridBagConstraints.BOTH;
        gbc_seznamCepivKombi.gridx = 1;
        gbc_seznamCepivKombi.gridy = 3;
        kombipanel.add(seznamCepivKombi, gbc_seznamCepivKombi);

        JButton btnAvto = new JButton("Pojdi na AVTO");

        GridBagConstraints gbc_btnAvto = new GridBagConstraints();
        gbc_btnAvto.insets = new Insets(0, 0, 5, 5);
        gbc_btnAvto.gridx = 1;
        gbc_btnAvto.gridy = 4;
        kombipanel.add(btnAvto, gbc_btnAvto);

        JPanel avtopanel = new JPanel();
        izpispanel.add(avtopanel, "1");
        GridBagLayout gbl_avtopanel = new GridBagLayout();
        gbl_avtopanel.columnWidths = new int[]{300, 300, 300, 0};
        gbl_avtopanel.rowHeights = new int[]{171, 40, 40, 50, 50, 0, 0};
        gbl_avtopanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_avtopanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        avtopanel.setLayout(gbl_avtopanel);

        JLabel lblAvto = new JLabel("AVTO");
        lblAvto.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblAvto = new GridBagConstraints();
        gbc_lblAvto.insets = new Insets(0, 0, 5, 5);
        gbc_lblAvto.fill = GridBagConstraints.BOTH;
        gbc_lblAvto.gridx = 1;
        gbc_lblAvto.gridy = 1;
        avtopanel.add(lblAvto, gbc_lblAvto);

        JLabel lblSeznamCepiv = new JLabel("Seznam cepiv:");
        GridBagConstraints gbc_lblSeznamCepiv = new GridBagConstraints();
        gbc_lblSeznamCepiv.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeznamCepiv.fill = GridBagConstraints.VERTICAL;
        gbc_lblSeznamCepiv.gridx = 1;
        gbc_lblSeznamCepiv.gridy = 2;
        avtopanel.add(lblSeznamCepiv, gbc_lblSeznamCepiv);

        JList<Cepivo> seznamCepivAvto = new JList<Cepivo>();
        seznamCepivAvto.setModel(listCepivAvto);
        GridBagConstraints gbc_seznamCepivAvto = new GridBagConstraints();
        gbc_seznamCepivAvto.insets = new Insets(0, 0, 5, 5);
        gbc_seznamCepivAvto.fill = GridBagConstraints.BOTH;
        gbc_seznamCepivAvto.gridx = 1;
        gbc_seznamCepivAvto.gridy = 3;
        avtopanel.add(seznamCepivAvto, gbc_seznamCepivAvto);

        JButton btnKombi = new JButton("Pojdi na KOMBI");

        GridBagConstraints gbc_btnKombi = new GridBagConstraints();
        gbc_btnKombi.insets = new Insets(0, 0, 5, 5);
        gbc_btnKombi.gridx = 1;
        gbc_btnKombi.gridy = 4;
        avtopanel.add(btnKombi, gbc_btnKombi);


        //*********************************	CARD LAYOUT ************************************


        btnAvto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CardLayout x = (CardLayout) izpispanel.getLayout();
                x.show(izpispanel, "1");
            }
        });
        btnKombi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout x = (CardLayout) izpispanel.getLayout();
                x.show(izpispanel, "2");
            }
        });


    }

    class Notranji implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            model.removeAllElements();
            for(int i = 0; i < model.getSize(); i++) {
                cepivo.remove(i);
            }
            try {
                FileOutputStream fileoutputstream = new FileOutputStream("Cepiva.ser");
                GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
                ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
                outputstream.writeObject(cepivo);
                outputstream.flush();
                outputstream.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}