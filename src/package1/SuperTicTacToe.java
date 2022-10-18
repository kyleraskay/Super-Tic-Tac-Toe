package package1;

import javax.swing.*;
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

        int size = getSize();
        while(!(size>2 && size<16)) {
            if(askToContSize()) {
                size = getSize();
            }
            else {System.exit(0);}
        }
        int inARow = getInARow();
        while(!(inARow<=size && inARow>0)) {
            if(askToContInARow()) {
                inARow = getInARow();
            }
            else {System.exit(0);}
        }
        SuperTicTacToePanel.jButtonSize = size;
        SuperTicTacToeGame game = new SuperTicTacToeGame(size, inARow);
        SuperTicTacToePanel panel1 = new SuperTicTacToePanel(game);
        gameFrame.getContentPane().add(panel1);

        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        fileMenu.add(quitItem);
        gameFrame.pack();
        gameFrame.setSize(600,600);
        gameFrame.setJMenuBar(menus);
        gameFrame.setVisible(true);
    }
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
    public static boolean askToContInvalid() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Number needs to be valid (real positive number)\n Do you want to retry and enter a valid number?", "Confirm",
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
    public static boolean askToContInARow() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Number needs to be less than or equal to the size of the board  \n Do you want to retry and enter a valid number?", "Confirm",
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
