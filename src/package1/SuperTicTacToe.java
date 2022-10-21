package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SuperTicTacToe {
    public static void main(String[] args) {
        JMenu fileMenu;
        JMenuBar menus;
        JMenuItem quitItem;

        fileMenu = new JMenu("File");
        menus = new JMenuBar();
        menus.add(fileMenu);
        JFrame gameFrame = new JFrame("Super Tic Tac Toe");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LOOP UNTIL SIZE IS SET
        int size = getSize();
        while(!(size>2 && size<16)) {
            if(askToContSize()) {
                size = getSize();
            }
            else {System.exit(0);}
        }

        //LOOP UNTIL INAROW IS SET
        int inARow = getInARow();
        while(!(inARow<=size && inARow>2)) {
            if(askToContInARow()) {
                inARow = getInARow();
            }
            else {System.exit(0);}
        }

        SuperTicTacToePanel.jButtonSize = 600 / size;
        if(getFirst()) {
            SuperTicTacToeGame game = new SuperTicTacToeGame(size, inARow);
            SuperTicTacToePanel panel1 = new SuperTicTacToePanel(game, true);
            gameFrame.getContentPane().add(panel1);

        } else {
            SuperTicTacToeGame game = new SuperTicTacToeGame(size, inARow);
            SuperTicTacToePanel panel1 = new SuperTicTacToePanel(game, false);
            gameFrame.getContentPane().add(panel1);



        }
        /*
        SuperTicTacToeGame game = new SuperTicTacToeGame(size, inARow);
        SuperTicTacToePanel panel1 = new SuperTicTacToePanel(game);
        gameFrame.getContentPane().add(panel1);

         */

        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        fileMenu.add(quitItem);


        gameFrame.setSize(650,750);
        gameFrame.setJMenuBar(menus);
        gameFrame.setVisible(true);
        gameFrame.setLocationRelativeTo(null);
    }

    //ASKS USER FOR SIZE OF BOARD
    public static int getSize() {
        int size=0;
        try {
            size = Integer.parseInt(JOptionPane.showInputDialog("Enter in the size of the board: "));
        } catch(Exception e) {
            if(askToContInvalid()) {
                return getSize();
            }
            else {System.exit(0);}
        }
        return size;
    }

    //ASKS USER FOR NUMBER IN A ROW
    public static int getInARow() {
        int inARow =0;
        try {
            inARow = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount in a row to win: "));
        } catch(Exception e) {
            //change code eventually
            if(askToContInvalid()) {
                return getInARow();
            }
            else {System.exit(0);}
        }
        return inARow;
    }

    //ASKS USER WHO GOES FIRST
    public static boolean getFirst() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Do you want to go first?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return false;
        } else if (response == JOptionPane.YES_OPTION) {

            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        return false;

    }

    //CODE FOR SIZE LOOP TO WORK
    public static boolean askToContSize() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Number needs to be between 3 and 15 \n Do you want to retry and enter a valid number?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return false;
        } else if (response == JOptionPane.YES_OPTION) {
            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        return false;
    }

    //CODE FOR INAROW LOOP TO WORK
    public static boolean askToContInARow() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Invalid entry! Number needs to be less than or equal to the size of the board  \n Do you want to retry and enter a valid number?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return false;
        } else if (response == JOptionPane.YES_OPTION) {
            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        return false;
    }

    //CODE FOR INVALID USER INPUTS
    public static boolean askToContInvalid() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Invalid entry! Number needs to be valid quantity(real positive number between 3 and 14).\n Do you want to retry and enter a valid number?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return false;
        } else if (response == JOptionPane.YES_OPTION) {
            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        return false;
    }
}
