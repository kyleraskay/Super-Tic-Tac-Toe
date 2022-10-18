package package1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

public class SuperTicTacToePanel extends JPanel implements ActionListener {
    public static int jButtonSize;
    JButton[][] jButtonsBoard = new JButton[jButtonSize][jButtonSize];


    private Cell[][] iBoard;
    private JButton quitButton;
    private ImageIcon xIcon;
    private ImageIcon oIcon;
    private ImageIcon emptyIcon;
    private SuperTicTacToeGame game;




    SuperTicTacToePanel(SuperTicTacToeGame game) {

        this.game = game;






        this.iBoard = iBoard;
        if(game.size <=6) {
            xIcon = new ImageIcon("./src/package1/x-2.png");
        } else {
            xIcon = new ImageIcon("./src/package1/x-main.png");
        }
        if(game.size <=4) {
            emptyIcon = new ImageIcon("./src/package1/empty-1.png");
        } else {
            emptyIcon = new ImageIcon("./src/package1/empty-3.png");
        }


        oIcon = new ImageIcon("./src/package1/o.jpg");
        //emptyIcon = new ImageIcon("./src/package1/empty-1.png");
        for(int row =0; row<game.size;row++) {
            for(int col =0; col<game.size;col++) {
                jButtonsBoard[row][col] = new JButton("", emptyIcon);
                this.add(jButtonsBoard[row][col]);
                jButtonsBoard[row][col].addActionListener(this::actionPerformed);
                this.setLayout(new GridLayout(game.size,game.size));




            }
        }








    }
    private void displayBoard() {

        iBoard = game.getBoard();

        for (int row = 0; row < game.size; row++) {
            for (int col = 0; col < game.size; col++) {
                if (iBoard[row][col] == Cell.O){
                    jButtonsBoard[row][col].setIcon(emptyIcon);
                }
            }
        }
    }


    @Override

        public void actionPerformed(ActionEvent e) {
            for(int row = 0; row<game.size;row++) {
                for(int col = 0; col<game.size;col++) {
                    if(jButtonsBoard[row][col] == e.getSource()) {
                        game.select(row, col);
                        jButtonsBoard[row][col].setIcon(xIcon);

                    }



                }
            }
            displayBoard();
            if(game.getGameStatus() == GameStatus.O_WON) {
                JOptionPane.showMessageDialog(null, "O won and X" + " lost! \n The game will reset");
            }
            if(game.getGameStatus() == GameStatus.X_WON) {
                JOptionPane.showMessageDialog(null,"X won and O lost!" );
            }


    }

/*
    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            for(int row = 0; row<3;row++) {
                for(int col = 0; col<3;col++) {
                    if(jButtonsBoard[row][col] == e.getSource())
                        game.select(row,col);
                        jButtonsBoard[row][col].setIcon(xIcon);


                }
            }
            displayBoard();
            if(game.getGameStatus() == GameStatus.O_WON) {
                JOptionPane.showMessageDialog(null, "O won and X" + " lost! \n The game will reset");
            }
            if(game.getGameStatus() == GameStatus.X_WON) {
                JOptionPane.showMessageDialog(null,"X won and O lost!" );
            }


        }
    }

 */
}
