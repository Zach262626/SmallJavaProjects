import java.util.ArrayList;

public class TicTacToeModel {
    public enum Status {X_WON, O_WON, TIE, UNDECIDED};
    private String board[][];
    private int rows;
    private int cols;
    private int lineToWin;
    private int squareRemaining;
    private Status status;
    private final String EMPTY = " ";
    private final String Player_X = "X";
    private final String Player_O = "O";
    private String currentPlayer;
    private ArrayList<TicTacToeView> views;
    public TicTacToeModel(int rows, int cols, int lineToWin) {
        this.lineToWin = lineToWin;
        status = Status.UNDECIDED;
        this.rows = rows;
        this.cols = cols;
        this.board = new String[rows][cols];
        squareRemaining = rows * cols;
        currentPlayer = Player_X;
        views = new ArrayList<>();
        createBoard(rows, cols);
    }
    public void addView(TicTacToeView view) {
        views.add(view);
    }
    public void play(int x, int y) {
        System.out.println("Player " + currentPlayer + " is playing at " + x + ", " + y);
        if (emptySquare(x, y)) {
            setSquare(x, y, currentPlayer);
            updateStatus(x, y);
            if (status == Status.X_WON) {
                System.out.println("X wins");
            }else if (status == Status.O_WON) {
                System.out.println("O wins");
            }else if (status == Status.TIE) {
                System.out.println("TIE");
            }
            printBoard();
            if (currentPlayer.equals(Player_X)) {
                currentPlayer = Player_O;
            }else {
                currentPlayer = Player_X;
            }
        }else {
            System.out.println("Not A Valid Option");
        }
        notifyView();
    }

    /**
     * This method checks if the player won the game or if the game is a tie
     *
     * @param x is the row the current player played
     * @param y is the column the current player played
     */
    private void updateStatus(int x, int y) {
        if ((inBound(x, y+1) && board[x][y+1].equals(currentPlayer)) || (inBound(x, y-1) && board[x][y-1].equals(currentPlayer))) {
            int inARow = 1;
            int i = 1;

            while (inBound(x, y-i) && board[x][y-i].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            i = 1;
            while (inBound(x, y+i) && board[x][y+i].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            if (inARow >= lineToWin) {
                if (currentPlayer.equals(Player_X)) {
                    status = Status.X_WON;
                }else {
                    status = Status.O_WON;
                }
                return;
            }
        }
        if ((inBound(x+1, y) && board[x+1][y].equals(currentPlayer)) || (inBound(x-1, y) && board[x-1][y].equals(currentPlayer))) {
            int inARow = 1;
            int i = 1;
            while (inBound(x-i, y) && board[x-i][y].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            i = 1;
            while (inBound(x+i, y) && board[x+i][y].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            if (inARow >= lineToWin) {
                if (currentPlayer.equals(Player_X)) {
                    status = Status.X_WON;
                }else {
                    status = Status.O_WON;
                }
                return;
            }
        }
        if ((inBound(x+1, y+1) && board[x+1][y+1].equals(currentPlayer)) || (inBound(x-1, y-1) && board[x-1][y-1].equals(currentPlayer))) {
            int inARow = 1;
            int i = 1;
            while (inBound(x-i, y-i) && board[x-i][y-i].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            i = 1;
            while (inBound(x+i, y+i) && board[x+i][y+i].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            if (inARow >= lineToWin) {
                if (currentPlayer.equals(Player_X)) {
                    status = Status.X_WON;
                }else {
                    status = Status.O_WON;
                }
                return;
            }
        }
        if ((inBound(x-1, y+1) && board[x-1][y+1].equals(currentPlayer)) || (inBound(x+1, y-1) && board[x+1][y-1].equals(currentPlayer))) {
            int inARow = 1;
            int i = 1;
            while (inBound(x-i, y+i) && board[x-i][y+i].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            i = 1;
            while (inBound(x+i, y-i) && board[x+i][y-i].equals(currentPlayer)) {
                inARow += 1;
                i += 1;
            }
            if (inARow >= lineToWin) {
                if (currentPlayer.equals(Player_X)) {
                    status = Status.X_WON;
                }else {
                    status = Status.O_WON;
                }
                return;
            }
        }
        if (squareRemaining == 0) {
            status = Status.TIE;
        }
    }
    public Status getStatus() {
        return status;
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    public void reset() {
        status = Status.UNDECIDED;
        this.board = new String[rows][cols];
        currentPlayer = Player_X;
        squareRemaining = rows * cols;
        createBoard(rows, cols);
        notifyView();
    }
    private void createBoard(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
    public String getSquare(int x, int y) {
        return board[x][y];
    }
    public void setSquare(int x, int y, String value) {
        board[x][y] = value;
        squareRemaining -= 1;
    }
    public boolean emptySquare(int x, int y) {
        return board[x][y].equals(EMPTY);
    }
    private boolean inBound(int x, int y) {
        System.out.print("inBound: " + x + " " + y + ": ");
        if (x < 0 || y < 0 || x >= rows || y >= cols) {
            System.out.println(false);
            return false;
        }else {
            System.out.println(true);
            return true;
        }
    }

    private void printBoard() {
        for (int i = 0; i < rows; i++) {
            System.out.print("-");
            for (int j = 0; j < cols; j++) {
                System.out.print("--");
            }
            System.out.print("\n|");
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println("");
        }
        for (int j = 0; j < cols; j++) {
            System.out.print("--");
        }
        System.out.println("-");
    }
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public int getSquareRemaining() {
        return squareRemaining;
    }
    public void setRows(int x) {
        rows = x;
    }
    public void setCols(int y) {
        cols = y;
    }
    public void setInARow(int inARow) {
        lineToWin = inARow;
    }
    public void notifyView() {
        for (TicTacToeView view : views) {
            view.update();
        }
    }
}
