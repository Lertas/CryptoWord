//Psychia Eleni icsd12210
//Lertas Giorgos icsd11084

import javax.swing.*;

abstract class Letters extends JButton { //klasi grammaton

    private char letter;      //gramma tis alfavita
    private int ponte;      //pontos grammatos
    private boolean clicked; //metavliti pou krataei to an einai klikarismeno kapoio koumpi

    public Letters(char letter, int ponte) {
        this.letter = letter;
        this.ponte = ponte;
        super.setText("<html><span style='font-size:16px'>" + letter + "</span><span style='font-size:10px'>" + ponte);
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setPonte(int point){
        this.ponte = point;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
        super.setText("<html><span style='font-size:16px'>" + letter + "</span><span style='font-size:10px'>" + ponte);
    }

    public int getPonte(){
        return this.ponte;
    }

    public abstract int calcPonte();





}
