//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private String name;          //dilono onoma kai skor paixti
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    private static Scanner in = new Scanner(System.in);

    public Player(String name, ArrayList<Letters> marks) {     //metaferontai onoma kai arraylist grammaton apo tin main

        this.name = name;

        int choice, ch = 0;

        do {
            System.out.printf("Επιλέξτε επίπεδο δυσκολίας (1 – 5x5, 2 – 8x8, 3 - 10x10): ");
            choice = in.nextInt();           //diavazo tin epilogi tou xristi gia megethos tou pinaka

            if (choice == 1) {
                ch = 25;
            } else if (choice == 2) {
                ch = 64;
            } else if (choice == 3) {
                ch = 100;
            } else {
                System.out.println("Λάθος πληκτρολόγηση.");
            }

        } while (choice < 1 || choice > 3);     //diavazei mexri na oristei swsta i epilogi

        Dashboard dashboard = new Dashboard(ch, marks, this);   //kali tin klasi dashboard metaferontas megethos pinaka
                                                               // arraylist grammaton kai onoma xristi
    }

}
