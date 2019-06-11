//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084

import java.util.ArrayList;
import java.util.Scanner;

public class CryptoWord {

    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Letters> marks = new ArrayList<>();

    public static void main(String[] args) {

        marks.add(new WhiteLet('Α', 1 ));
        marks.add(new WhiteLet('Β', 8 ));
        marks.add(new WhiteLet('Γ', 4 ));
        marks.add(new WhiteLet('Δ', 4 ));
        marks.add(new WhiteLet('Ε', 1 ));
        marks.add(new WhiteLet('Ζ', 8 ));               //eisagoume ta grammata os aspra
        marks.add(new WhiteLet('Η', 1 ));               //stin arraylist marks tupou letters
        marks.add(new WhiteLet('Θ', 8 ));
        marks.add(new WhiteLet('Ι', 1 ));
        marks.add(new WhiteLet('Κ', 2 ));
        marks.add(new WhiteLet('Λ', 3 ));
        marks.add(new WhiteLet('Μ', 3 ));
        marks.add(new WhiteLet('Ν', 1 ));
        marks.add(new WhiteLet('Ξ', 10 ));
        marks.add(new WhiteLet('Ο', 1 ));
        marks.add(new WhiteLet('Π', 2 ));
        marks.add(new WhiteLet('Ρ', 2 ));
        marks.add(new WhiteLet('Σ', 1 ));
        marks.add(new WhiteLet('Τ', 1 ));
        marks.add(new WhiteLet('Υ', 2 ));
        marks.add(new WhiteLet('Φ', 8 ));
        marks.add(new WhiteLet('Χ', 10 ));
        marks.add(new WhiteLet('Ψ', 10 ));
        marks.add(new WhiteLet('Ω', 3 ));

        String username;

        System.out.println("ΚΑΛΩΣΗΡΘΑΤΕ ΣΤΟ «Ηλεκτρονικό Κρυπτόλεξο»");
        System.out.printf("\nΕισάγετε το όνομα σας: ");   //eisodo onomatos paixti
        username = in.nextLine();

        System.out.println("Πληροφορίες παιχνιδιού.");      //tha emfanizontai oi plirofories gia to paixnidi

        Player player = new Player(username, marks);    //kali tin klasi player metaferontas onoma kai arraylist grammaton

    }

}
