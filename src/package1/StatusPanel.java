package package1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel implements ActionListener {
    JLabel turn = new JLabel("HI");
    JLabel turnsPlayed = new JLabel("0");



    StatusPanel() {
        this.add(turn);


    }
    public void setTurn(String text) {
        turn.setText(text);
    }
    public void setNumberPlayed(int num) {
        turnsPlayed.setText(num+"");

    }









    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
