//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084

import java.awt.*;

public class WhiteLet extends Letters {

    public WhiteLet(char letter, int ponte) {  //klasi me aspra grammata , upoklasi

        super(letter, ponte);
        super.setBackground(Color.WHITE);
    }

    @Override
    public int calcPonte() {
        return super.getPonte();
    }


}