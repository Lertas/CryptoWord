//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084
import java.awt.*;

public class AnyLet extends Letters {

    public AnyLet(char letter, int ponte) {  //klasi me sumvolo ? , upoklasi

        super('?', 0);

        super.setBackground(Color.WHITE);
    }

    @Override
    //epistrofi tou pontou kai ananewsi tou koumpoiou otan dextei neo gramma o mpalanter
    public int calcPonte() {
        super.setText("<html><span style='font-size:16px'>" + this.getLetter() + "</span><span style='font-size:10px'>" + getPonte());
        return super.getPonte()*2;
    }


}