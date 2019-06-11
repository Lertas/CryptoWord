//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.StrictMath.abs;

public class Dashboard extends JFrame implements Vicinity {    //klasi pinaka

    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<Letters> panel = new ArrayList<>();
    private ArrayList<Letters> marks = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();

    private ArrayList<Letters> clicked = new ArrayList<>(); //lista me ta patimena koumpia
    private ArrayList<Point> clickedPoints = new ArrayList<>(); //lista me tis patimenes suntetagmenes
    private HashSet<String> str = new HashSet<>();
    private Random r = new Random();
    private Scanner in = new Scanner(System.in);
    private int ch;
    private Player p;
    private int size;

    public Dashboard(int ch, ArrayList<Letters> marks, Player p) {  //metaferontai onoma kai arraylist grammaton
        super("Ελληνικό ΚΡΥΠΤΟΛΕΞΟ");                                                            //arraylist grammaton kai onoma xristi apo player klasi
        this.marks = marks;
        this.ch = ch;
        this.p = p;
        this.size = (int) Math.sqrt(ch);

        readWord();


        fixtbl();  //ftiaksimo pinaka
        format();  //anakateuei ton pinaka

        showGUI();

    }

    private void readWord() {
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showGUI() {
        super.setSize(1000, 600);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();

        p1.setLayout(new GridLayout(size, size));
        for (int i = 0; i < ch; i++) {
            p1.add(panel.get(i));

            panel.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3) { //an pati8ei dexi klik adeiase to tamplo
                        clicked.removeAll(clicked); //adeiase tin lista me ta patimena koumpia
                        for (Letters l : panel) {
                            if (l instanceof BlueLet)
                            l.setBackground(Color.BLUE);
                            else if (l instanceof RedLet)
                                l.setBackground(Color.RED);
                            else
                            l.setBackground(Color.WHITE);
                        }
                        //adeiasma tis listas me tis clicked suntetagmenes
                        clickedPoints.removeAll(clickedPoints);
                    } else {
                        int in = panel.indexOf((Letters) e.getSource());//vres se poio simeio einai to koumpi p pati8ike

                        if (clicked.isEmpty()) { //ean den exei pati8ei kanena koumpi pata opoio 8eleis
                            ((Letters) e.getSource()).setBackground(Color.YELLOW); //kane to koumpi kitrino
                            clicked.add(((Letters) e.getSource()));
                            clickedPoints.add(points.get(in)); //vale sta clickarismena tin suntetagmeni tou koumpiou
                        }
                        //alliws ean to koumpi pou paei na pati8ei einai geitwniko me to teleutaia patimeno
                        //(diladi auto p vrisketai teleutaio stin lista twn cliecked)
                        else if (checkVicinity(clickedPoints.get(clickedPoints.size() - 1), points.get(in))) {
                            ((Letters) e.getSource()).setBackground(Color.YELLOW);//kane to koumpi kitrino
                            clicked.add(((Letters) e.getSource()));
                            clickedPoints.add(points.get(in)); //vale sta clickarismena tin suntetagmeni tou koumpiou

                        }
                        if (((Letters) e.getSource()) instanceof AnyLet) {
                            //zita gramma gia mpalanter apton xristi
                            String g = JOptionPane.showInputDialog(this, "Βάλε γράμμα μπαλαντέρ");
                            char c = g.charAt(0); //kane tin apantisi xaraktira apo string
                            ((Letters) e.getSource()).setLetter(c); //alla3e to gramma tu mpalanter
                            ((Letters) e.getSource()).setPonte(getLetter(c).getPonte());
                            Dashboard.super.revalidate(); //prosthiki neon componets sto GUI
                            Dashboard.super.repaint();
                        }


                    }
                }



                @Override
                 public void mousePressed(MouseEvent e) //kaleite otan patame ena mouse button se kapoio component
 {

                }

                @Override
                public void mouseReleased(MouseEvent e)//kaleite otan afinoume to mouse button apo kapoio component
                {

                }

                @Override
                public void mouseEntered(MouseEvent e) //kaleite otan to mouse eiserxete stin perioxi pou katalambanei ena component
                {

                }

                @Override
                public void mouseExited(MouseEvent e) //kaleite otan to mouse ekserxetai apo tin perioxi pou katalambanei ena component
                {

                }
            });
        }

        JPanel p2 = new JPanel(new GridLayout(1, 2)); //to olo para8uro exei 2 stiles, mia gia to tamplo kai mia gia tis plirofories
        super.add(p2);

        p2.add(p1);

        JPanel p3 = new JPanel(null);
        p2.add(p3);

        JButton chword = new JButton("Έλεγχος Λέξης");
        chword.setBounds(5, 500, 130, 30);
        p3.add(chword);

        chword.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 String foundstring = "";
                 int pointsf = 0;
                 //kataskeuase ena string me ta patimena grammata
                 for (Letters t : clicked) {
                     foundstring += t.getLetter();
                     pointsf += t.calcPonte();
                 }
                 System.out.println(foundstring);
                 if (words.contains(foundstring)) {
                     JOptionPane.showMessageDialog(Dashboard.this,
                             "Μπράβο! Βρήκες τη λέξη: " + foundstring);

                     JOptionPane.showMessageDialog(Dashboard.this,
                             "Μάζεψες " + pointsf + " πόντους");


                 } else {
                     JOptionPane.showMessageDialog(Dashboard.this,
                             "Δυστυχώς δεν υπάρχει λέξη: " + foundstring);
                 }
                 //epanafora tamplo
                  clicked.removeAll(clicked); //adeiase tin lista me ta patimena koumpia
                  for (Letters l : panel) {
                      if (l instanceof BlueLet)
                          l.setBackground(Color.BLUE);
                      else if (l instanceof RedLet)
                          l.setBackground(Color.RED);
                      else
                          l.setBackground(Color.WHITE);
                  }
                  //adeiasma tis listas me tis clicked suntetagmenes
                  clickedPoints.removeAll(clickedPoints);
             }
         }
        );

        JButton l1 = new JButton("Αναδιάταξη Γραμμής");
        l1.setBounds(10, 10, 150, 30);
        JTextField jt1 = new JTextField();
        jt1.setBounds(180, 10, 30, 30);
        p3.add(jt1);
        JButton l2 = new JButton("Διαγραφή Γραμμής");
        l2.setBounds(10, 60, 150, 30);
        JTextField jt2 = new JTextField();
        jt2.setBounds(180, 60, 30, 30);
        p3.add(jt2);
        JButton l3 = new JButton("Αναδιάταξη Στήλης");
        l3.setBounds(10, 110, 150, 30);
        JTextField jt3 = new JTextField();
        jt3.setBounds(180, 110, 30, 30);
        p3.add(jt3);
        JButton l4 = new JButton("Αναδιάταξη Ταμπλό");
        l4.setBounds(10, 160, 150, 30);
        JButton l5 = new JButton("Εναλλαγή Γραμμάτων");
        l5.setBounds(10, 210, 150, 30);

        l4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int temppoint;
                char tempchar;
                for(int i=0; i<50; i++) {
                    int x1 = r.nextInt(ch);
                    int x2 = r.nextInt(ch);


                    temppoint = panel.get(x2).getPonte();
                    panel.get(x2).setPonte(panel.get(x1).getPonte());
                    panel.get(x1).setPonte(temppoint);

                    tempchar = panel.get(x2).getLetter();
                    panel.get(x2).setLetter(panel.get(x1).getLetter());
                    panel.get(x1).setLetter(tempchar);
                }
                Dashboard.this.revalidate(); //pros8etei ta kainouria components sto GUI
                Dashboard.this.repaint();
            }
        });
        p3.add(l1);
        p3.add(l2);
        p3.add(l3);
        p3.add(l4);
        p3.add(l5);

        JLabel l6 = new JLabel("Στοχος : " + 500);
        l6.setBounds(10, 310, 150, 30);
        p3.add(l6);
        JLabel l7 = new JLabel("Συνολική Βαθμολογία : ");
        l7.setBounds(10, 360, 150, 30);
        p3.add(l7);
        JLabel l8 = new JLabel("Βαθμολογία Λέξης : ");
        l8.setBounds(10, 410, 150, 30);
        p3.add(l8);


        JMenuBar menuBar = new JMenuBar();

        // pros8iki epilogwn sto menu
        JMenu menu = new JMenu("Μενού");
        JMenu tools = new JMenu("Εργαλεία");

        // pros8iki epilogwn sto menu
        menuBar.add(menu);
        menuBar.add(tools);

        // pros8iki epilogwn sto menu
        JMenuItem newGame = new JMenuItem("Νέο παιχνίδι");
        JMenuItem endGame = new JMenuItem("Ακύρωση/Τερματισμός παιχνιδιού");
        JMenuItem addPlayerInformation = new JMenuItem("Εισαγωγή στοιχείων παίκτη");
        JMenuItem helpSettings = new JMenuItem("Ρυθμίσεις βοηθειών");
        JMenuItem addDictionary = new JMenuItem("Αναζήτηση αρχείου λέξεων");
        JMenuItem exit = new JMenuItem("Έξοδος");

        // pros8iki epilogwn sto menu
        menu.add(newGame);
        menu.add(endGame);
        menu.add(addPlayerInformation);
        menu.add(helpSettings);
        menu.add(addDictionary);
        menu.add(exit);

        //kleisimo para8urou
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard.this.dispose();
            }
        });
        //epanenar3i paixnidiou
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard.this.dispose();
                new Dashboard(ch, marks, p);
            }
        });

        // pros8iki epilogwn sto menu
        JMenuItem help = new JMenuItem("Βοήθεια");
        JMenuItem about = new JMenuItem("Σχετικά...");

        // pros8iki epilogwn sto menu
        tools.add(help);
        tools.add(about);
        this.setJMenuBar(menuBar);

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this,
                        "Kalws ir8ate sto Kruptole3o...!\nBreite oses perissoteres le3eis\n mporeite kai kerdiste");
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this,
                        "Kruptolexo 2017\nAntikeimenostrafis programmatismos II\nPsuxia Eleni\nLertas Georgios");

            }
        });

        super.setVisible(true);
    }


    private void fixtbl() {//sinartisi arxikopoiisis pinaka

        String s;
        Letters let;
        int x = 0, y = 0;
        int reds = r.nextInt(5);
        int blues = r.nextInt(4);
        int jokers = r.nextInt(3);


        while (panel.size() < ch) {  //oso o pinakas exei megethos mikrotero tou megethous eisodou (25 i 64 i 100)
            s = newentry();  //kali sinartisi na pari tuxaia leksi

            if (s.length() + panel.size() <= ch) {  //an to uparxon megethos tou pinaka sun to megethos tis leksis
                for (char c : s.toCharArray()) {  //gia kathe gramma tis leksis
                    if (reds > 0) {
                        panel.add(new RedLet(c, getLetter(c).getPonte())); //vazei ena kokkino gramma
                        reds--;
                    } else if (blues > 0) {
                        panel.add(new BlueLet(c, getLetter(c).getPonte())); //vazei ena mple gramma
                        blues--;
                    } else if (jokers > 0) {
                        panel.add(new AnyLet(c, getLetter(c).getPonte())); //vazei ena joker gramma
                        jokers--;
                    } else
                        panel.add(new WhiteLet(c, getLetter(c).getPonte())); //vazei to gramma kai kalei tin geletter

                    points.add(new Point(x, y));
                    y++; //auksanei to y mexri na paei 4                                   //gia na parei ton ponto tou grammatos
                    if (y == Math.sqrt(ch)) { //molis paei
                        y = 0;  //midenizei
                        x++;   //kai auksanei to x
                    }
                }
            } else {
                while (panel.size() < ch) {
                    let = marks.get(r.nextInt(marks.size())); //pairnei tuxaia kapoio gramma
                    panel.add(new WhiteLet(let.getLetter(), let.getPonte())); //kai to prosthetei mexri
                    points.add(new Point(x, y));
                    y++; //auksanei to y mexri na paei 4                                       //na simlirothei o pinakas
                    if (y == Math.sqrt(ch)) { //molis paei
                        y = 0;  //midenizei
                        x++;   //kai auksanei to x
                    }
                }
            }

        }

    }

    private String newentry() {

        String w = words.get(r.nextInt(words.size())); //pairnei tuxaia leksi apo tis arraylist words

        while (str.contains(w)) {      //an uparxei i leksi mesa tote vazei alli
            w = words.get(r.nextInt(words.size()));  //epilegontas apo tis enapominantes tuxaia
        }

        str.add(w);      //vazei ston voithitiko pinaka tin leksi
        return w;     //kai tin epistrefei

    }

    private Letters getLetter(char c) {
        //sinartisi pou pernei ena xaraktira kai epistrefei to letter me ton xaraktira afto

        for (Letters let : marks) {
            if (let.getLetter() == c) {
                return let;  //epistrefei to letter
            }
        }

        return null;  //alliws epistrefei null

    }

    private void format() {

        Collections.shuffle(panel);   //anakateuei ta grammata (imiteles)

    }


    private int scoreword(String word) { //sinartisi pou upologizei tin vathmologia tis leksis

        int scorew = 0;

        for (char c : word.toCharArray()) {    //gia kathe gramma tis leksis
            for (Letters ca : marks) {
                if (c == ca.getLetter()) {     //vrisrkei poio gramma einai stin arraylist me ta grammata
                    scorew += ca.getPonte();      //kai prosthetei tin vathmologia ton grammaton tis leksis
                }
            }
        }

        return scorew;  //epistrefei i vathmologia

    }

    private void change() {  //sinartisis pou allazei ta grammata tis leksis pou vriskei o paiktis

        for (Point p : clickedPoints) {     //gia kathe simio ton grammatn pou exoun epilextei apo ton paixti

        }


    }

    @Override
    public boolean checkVicinity(Point letter1, Point letter2) {
        if (abs(letter1.getX() - letter2.getX()) <= 1 && abs(letter1.getY() - letter2.getY()) <= 1) { //elegxos geitniasis
            //ean to x1 kai to x2 den exun apostasi megaluteri tou enos
            //kai to y1 kai to y2 den exun apostasi megaluteri tou enos tote einai
            //geitwnika grammata
            return true;
        } else return false;
    }
}
