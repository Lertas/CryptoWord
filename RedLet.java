//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084

import java.awt.*;

public class RedLet extends Letters { //klasi me kokkina grammata , upoklasi

    public RedLet(char letter, int ponte) {

        super(letter, ponte);

        super.setBackground(Color.RED);
    }

    @Override
    public int calcPonte() {
        return super.getPonte()*2;
    }


}