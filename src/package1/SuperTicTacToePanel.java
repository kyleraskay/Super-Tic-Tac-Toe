package package1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SuperTicTacToePanel extends JPanel implements ActionListener {
    public static int jButtonSize;
    JButton[][] jButtonsBoard = new JButton[jButtonSize][jButtonSize];
    JPanel boardpanel = new JPanel();
    JPanel buttonpanel = new JPanel();
    private Cell[][] iBoard;
    private JButton undoButton;
    private ImageIcon xIcon;
    private ImageIcon oIcon;
    private ImageIcon emptyIcon;
    private SuperTicTacToeGame game;

    SuperTicTacToePanel(SuperTicTacToeGame game, boolean first) {

        this.first = first;
        this.game = game;
        this.iBoard = iBoard;

        //SETS SIZE OF IMAGE ICONS
        if(game.size <=6) {
            xIcon = new ImageIcon("./src/package1/x-2.png");
            Image image = xIcon.getImage();
            Image newimg = image.getScaledInstance(600/(game.size + 1),600/(game.size + 1), java.awt.Image.SCALE_SMOOTH);
            xIcon = new ImageIcon(newimg);
        } else {
            xIcon = new ImageIcon("./src/package1/x-main.png");
            Image image = xIcon.getImage();
            Image newimg = image.getScaledInstance(600/(game.size + 2),600/(game.size + 2), java.awt.Image.SCALE_SMOOTH);
            xIcon = new ImageIcon(newimg);
        }

        if(game.size <=5) {
            emptyIcon = new ImageIcon("./src/package1/empty-3.png");
            Image image = emptyIcon.getImage();
            Image newimg = image.getScaledInstance(600/(game.size + 1),600/(game.size + 1), java.awt.Image.SCALE_SMOOTH);
            emptyIcon = new ImageIcon(newimg);
        } else {
            emptyIcon = new ImageIcon("./src/package1/empty-3.png");
            Image image = emptyIcon.getImage();
            Image newimg1 = image.getScaledInstance(600/(game.size + 2),600/(game.size + 2), java.awt.Image.SCALE_SMOOTH);
            emptyIcon = new ImageIcon(newimg1);
        }

        oIcon = new ImageIcon("./src/package1/o-1.png");
        Image image = oIcon.getImage();
        Image newimg = image.getScaledInstance(600/(game.size + 1),600/(game.size + 1), java.awt.Image.SCALE_SMOOTH);
        oIcon = new ImageIcon(newimg);


        //INITIALIZES BLANK PANEL OF EMPTYICONS
        for(int row = 0; row < game.size;row++) {
            for (int col = 0; col < game.size; col++) {
                boardpanel.setLayout(new GridLayout(game.size, game.size));
                jButtonsBoard[row][col] = new JButton("", emptyIcon);
                boardpanel.add(jButtonsBoard[row][col]);
                jButtonsBoard[row][col].addActionListener(this::actionPerformed);
                //jButtonsBoard[row][col].setBorder(new LineBorder(Color.black, 4));
                jButtonsBoard[row][col].setPreferredSize(new Dimension(600 / game.size, 600 / game.size));
            }
        }

        //PLACES ICONS AND UNDO BUTTON ON BOARD
        add(boardpanel);
        undoButton = new JButton("Undo");
        buttonpanel.add(undoButton);
        undoButton.addActionListener(this::actionPerformed);
        add(buttonpanel);
        if(!first) {
            game.aiBrain();
            displayBoard();
        }
    }

    public boolean getFirst() {
        return first;
    }

    //UPDATES EMPTYICONS AND OICONS ON BOARD
    private void displayBoard() {

        iBoard = game.getBoard();

        for (int row = 0; row < game.size; row++) {
            for (int col = 0; col < game.size; col++) {
                if (iBoard[row][col] == Cell.EMPTY) {
                    jButtonsBoard[row][col].setIcon(emptyIcon);
                }
                if (iBoard[row][col] == Cell.O) {
                    jButtonsBoard[row][col].setIcon(oIcon);
                    jButtonsBoard[row][col].setBackground(new Color(0,0,255));
                }
            }
        }

    }

    @Override
    //SETS LOCATION OF NEW X ON BOARD
    public void actionPerformed(ActionEvent e) {
        for(int row = 0; row<game.size;row++) {
            for(int col = 0; col<game.size;col++) {
                if(jButtonsBoard[row][col] == e.getSource()) {
                    game.select(row, col);
                    jButtonsBoard[row][col].setIcon(xIcon);
                    jButtonsBoard[row][col].setBackground(new Color(255,0,0));
                    displayBoard();
                }
            }
        }
        if(undoButton == e.getSource()) {
            game.undo();
            displayBoard();
        }
        if(game.getGameStatus() == GameStatus.O_WON) {
            JOptionPane.showMessageDialog(null, "O won and X" + " lost! \n The game will now reset." + "Thank you for playing!");

            game.reset();
            displayBoard();
        }
        if(game.getGameStatus() == GameStatus.X_WON) {
            JOptionPane.showMessageDialog(null,"X won and O lost!" + "\n The game will now reset." + "\n Thank you for playing!");
            game.reset();
            displayBoard();
        }
        if(game.getGameStatus() == GameStatus.CATS) {
            JOptionPane.showMessageDialog(null,"It's a draw!" + "\n The game will now reset." + "\n Thank you for playing!" );
            game.reset();
            displayBoard();
        }

    }
}
