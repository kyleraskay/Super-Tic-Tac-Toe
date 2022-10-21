package package1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

public class SuperTicTacToeGame{
    private Cell[][] board;
    private GameStatus status;
    public int size;
    public int inARow;
    public int turn = 0;
    ArrayList<Point> moves = new ArrayList();

    //BLANK CONSTRUCTOR FOR SUPERTICTACTOEGAME
    public SuperTicTacToeGame(int size, int inARow){

            moves.add(new Point(0, 0));
            status = GameStatus.IN_PROGRESS;
            this.size = size;
            this.inARow = inARow;

            board = new Cell[size][size];

            for (int row = 0; row < this.size; row++) {
                for (int col = 0; col < this.size; col++) {
                    board[row][col] = Cell.EMPTY;
                }
            }

            moves.add(new Point(0, 0));
            status = GameStatus.IN_PROGRESS;
            this.size = size;
            this.inARow = inARow;

            board = new Cell[size][size];

            for (int row = 0; row < this.size; row++) {
                for (int col = 0; col < this.size; col++) {
                    board[row][col] = Cell.EMPTY;
                }
            }

    }

    //MARKS PLAYER MOVE + AI RESPONSE
    public void select (int row, int col) {
        if (board[row][col] == Cell.EMPTY) {
            board[row][col] = Cell.X;
            moves.add(new Point(row, col));
        }else
            if (board[row][col] == Cell.X || board[row][col] == Cell.O) {
                JOptionPane.showMessageDialog(null, "Invalid move someone already played here. Please try again.");
                return;
            }

        if(match_x()) {
            status = GameStatus.X_WON;
        }
        if(checkDraw()) {
            status = GameStatus.CATS;
        }
        if(match_o()){
            status = GameStatus.O_WON;
        }

        //AI BRAIN
        aiBrain();
        if(match_o()){
            status = GameStatus.O_WON;
        }
        if(checkDraw()) {
            status = GameStatus.CATS;
        }
    }
    public void aiBrain() {
        Random rand = new Random();
        boolean o_Picked = false;
        if(this.status == GameStatus.IN_PROGRESS) {
            while (o_Picked == false) {
                int otherRow = rand.nextInt(size);
                int otherCol = rand.nextInt(size);

                if (board[otherRow][otherCol] != Cell.X && board[otherRow][otherCol] != Cell.O) {
                    board[otherRow][otherCol] = Cell.O;
                    moves.add(new Point(otherRow, otherCol));
                    turn += 1;
                    o_Picked = true;
                }
            }
        }


    }

    //TAKES AWAY LAST PLAYER + AI MOVE
    public void undo() {
        //UNDO PLAYER MOVES
        board[(int)(moves.get((turn * 2) - 1)).getX()][(int)(moves.get((turn * 2) - 1)).getY()] = Cell.EMPTY;
        //UNDO AI MOVES
        board[(int)(moves.get((int)(turn * 2)).getX())][(int)(moves.get((int)(turn * 2)).getY())] = Cell.EMPTY;
        turn -= 1;
    }

    //RESETS BOARD TO NEW GAME
    public void reset() {

        moves.clear();
        moves.add(new Point(0,0));
        turn = 0;
        status = GameStatus.IN_PROGRESS;

        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                board[row][col] = Cell.EMPTY;
            }
        }
    }

    public GameStatus getGameStatus(){
        return status;
    }

    public Cell[][] getBoard(){
        return board;
    }



    //CHECK FOR WIN FUNCTIONS
    public int checkRow(int row, int column) {
        if(column == this.size-(this.inARow-1)) {
            return 0;
        }
        int inARow=0;
        for(int i =0; i<this.inARow;i++) {
            if(board[row][column+i]==Cell.X) {
                inARow++;
            }
        }
        if(inARow == this.inARow)
            return 3;
        return checkRow(row, column+1);
    }

    public boolean checkEachRow_x() {
        for(int row = 0; row<=this.size-1; row++) {
            if(checkRow(row,0) == 3) {
                return true;
            }
        }
        return false;
    }
    public int checkColumn(int row, int column) {

        //checKInARow-1
        if(row == this.size-(this.inARow-1)) {
            return 0;
        }
        int inARow=0;
        for(int i =0; i<this.inARow;i++) {
            if(board[row+i][column]==Cell.X) {
                inARow++;
            }
        }
        if(inARow == this.inARow) {
            return 3;
        }

        return checkColumn(row+1, column);
    }
    public boolean checkEachColumn() {
        for(int col = 0; col<=this.size-1; col++) {
            if(checkColumn(0,col) == 3) {
                return true;
            }
        }
        return false;
    }
    public int checkDiaRowTltoBR(int row, int column) {
        if(column >= this.size-(this.inARow-1) || row >= this.size-(this.inARow-1)) {
            return 0;
        }
        int inARow=0;
        for(int i =0; i<this.inARow;i++) {
            if(board[row+i][column+i]==Cell.X) {
                inARow++;
            }
        }
        if(inARow == this.inARow) {
            return 3;
        }
        return checkDiaRowTltoBR(row+1, column+1);
    }
    public int checkDiaRowTRtoBL(int row, int column) {
        if(row >= this.size-(this.inARow-1) || column <=(this.inARow-2)) {
            return 0;
        }
        int inARow=0;
        for(int i =0; i<this.inARow;i++) {
            if(board[row+i][column-i]==Cell.X) {
                inARow++;
            }
        }
        if(inARow == this.inARow) {
            return 3;
        }
        return checkDiaRowTRtoBL(row+1, column-1);
    }
    public boolean checkAllRowTLtoBR() {
        for(int row=0; row<this.size-2; row++) {
            if(checkDiaRowTltoBR(row,0) == 3) {
                return true;
            }

        }
        for(int col=0; col<this.size-2; col++) {
            if(checkDiaRowTltoBR(0,col) == 3) {
                return true;
            }

        }
        return false;
    }
    public boolean checkAllRowTRtoBL() {
        for(int col=this.size-1; col>1; col--) {
            if(checkDiaRowTRtoBL(0,col)== 3) {
                return true;
            }
        }
        for(int row = 0; row<this.size-2; row++) {
            if(checkDiaRowTRtoBL(row, this.size-1)== 3) {
                return true;
            }
        }
        return false;
    }
    public boolean checkAllRowTRtoBL_o() {
        for(int col=this.size-1; col>1; col--) {
            if(checkDiaRowTRtoBL(0,col)== 4) {
                return true;
            }
        }
        for(int row = 0; row<this.size-2; row++) {
            if(checkDiaRowTRtoBL(row, this.size-1)== 4) {
                return true;
            }
        }
        return false;
    }
    public boolean match_x() {

        if(checkAllRowTLtoBR_x()) {
            return true;
        }
        if(checkAllRowTRtoBL_x()) {
            return true;
        }
        if(checkEachColumn_x()) {
            return true;
        }
        if(checkEachRow_x()) {
            return true;
        }
        return false;
    }

    public boolean match_o() {

        if(checkAllRowTLtoBR_o()) {
            return true;
        }
        if(checkAllRowTRtoBL_o()) {
            return true;
        }
        if(checkEachColumn_o()) {
            return true;
        }
        if(checkEachRow_o()) {
            return true;
        }
        return false;
    }
    public boolean checkDraw() {
        int count =0;
        for(int row = 0; row<this.size; row++) {
            for(int col = 0; col<this.size; col++) {
                if(board[row][col] == Cell.X) {
                    count++;
                }
                if(board[row][col] == Cell.O) {
                    count++;
                }
            }
        }
        if(count == (this.size*this.size) && !match_o() && !match_x()) {
            return true;
        }
        return false;
    }

    //MEGA MODE?? have to get all types of matches. Maybe implement later for fun
    /*

    if(checkAllRowTRtoBL() && checkAllRowTLtoBR() && checkEachRow() && checkEachColumn()) {
        return true;
    }

    */
}



