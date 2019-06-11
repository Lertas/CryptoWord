//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084
import java.awt.*;

public class BlueLet extends Letters {  //klasi me mple grammata , upoklasi

    public BlueLet(char letter, int ponte) {

        super(letter, ponte);

        super.setBackground(Color.BLUE);
    }

    @Override
    public int calcPonte() {
        return super.getPonte();
        }


}