package package1;

public class SuperTicTacToeGame{
    private Cell[][] board;
    private GameStatus status;
    public int size;
    public int inARow;


    public SuperTicTacToeGame(int size, int inARow){
        status = GameStatus.IN_PROGRESS;
        this.size = size;
        this.inARow = inARow;

        board = new Cell[size][size];

        for (int row = 0; row < this.size; row++)    {
            for (int col = 0; col < this.size; col++)    {
                board[row][col] = Cell.EMPTY;
            }
        }

    }

    public void select (int row, int col) {
        board [row][col] = Cell.X;
        if(match())
            status  = GameStatus.X_WON;

    }
    public void reset() {

        //CHANGE LATER to correct size of board
        SuperTicTacToeGame game = new SuperTicTacToeGame(3,0);
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
    public boolean checkEachRow() {
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
    public boolean match() {


        if(checkAllRowTLtoBR())
            return true;
        if(checkAllRowTRtoBL())
            return true;
        if(checkEachColumn())
            return true;
        if(checkEachRow())
            return true;




        //MEGA MODE?? have to get all types of matches. Maybe implement later for fun
        /*

        if(checkAllRowTRtoBL() && checkAllRowTLtoBR() && checkEachRow() && checkEachColumn()) {
            return true;
        }

         */

        return false;
    }
    //OPPONENT O functions





}



