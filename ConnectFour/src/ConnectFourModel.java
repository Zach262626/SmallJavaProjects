import java.util.ArrayList;
import java.util.Objects;

public class ConnectFourModel {
    public final static int ROWS = 7, COLS = 7;;
    private String[][] board;

    public int getSpacesLeft() {
        return spacesLeft;
    }

    public String statusString() {
        if (status.equals(Status.UNDECIDED)) {
            if (currentPlayer.equals(PLAYER_RED)) {
                return "Red's Turn";
            }else {
                return "Yellow's Turn";
            }
        }else if (status.equals(Status.TIE)) {
            return "TIE";
        }else if (status.equals(Status.RED_WON)) {
            return "Red Wins";
        }else {
            return "Yellow Wins";
        }
    }

    public enum Status {
        RED_WON, YELLOW_WON, TIE, UNDECIDED
    }
    public final static String PLAYER_RED = "R", PLAYER_YELLOW = "Y", EMPTY = " ";
    private String currentPlayer;
    private Status status;
    private int spacesLeft;
    private ArrayList<ConnectFourView> views;

    /**
     * Constructor for ConnectFourModel class
     */
    public ConnectFourModel() {
        views = new ArrayList<>();
        currentPlayer = PLAYER_RED;
        status = Status.UNDECIDED;
        board = new String[ROWS][COLS];
        spacesLeft = ROWS * COLS;
        setBoard();


    }

    /**
     * Resets board for a new connect four game
     */
    public void reset() {
        currentPlayer = PLAYER_RED;
        spacesLeft = ROWS * COLS;
        status = Status.UNDECIDED;
        setBoard();
        notifyViews();
    }

    /**
     * This method plays a tile at the chosen collumn
     * @param col where the current Player plays his turn
     * @return true if placement was successfull, false if column was full
     */
    public boolean play(int col) {
        int rowPlayed = -1;
        if (!inBounds(0, col)) {
            return false;
        }
        for (int i = 0; i < ROWS; i++) {
            if (board[i][col].equals(EMPTY)) {
                board[i][col] = currentPlayer;
                rowPlayed = i;
                break;
            }
        }
        //if col is full
        if (rowPlayed == -1) {
            return false;
        }
        spacesLeft -= 1;
        updateStatus(rowPlayed, col);
        if (Objects.equals(currentPlayer, PLAYER_RED)) {
            currentPlayer = PLAYER_YELLOW;
        }else if (Objects.equals(currentPlayer, PLAYER_YELLOW)) {
            currentPlayer = PLAYER_RED;
        }
        notifyViews();
        return true;

    }
    /**
     * This method plays a tile at the chosen collumn
     * @param col where the current Player plays his turn
     * @param row where the current Player plays his turn
     * @return true if placement was successfull, false if column was full
     */
    public void updateStatus(int row, int col) {
        if (spacesLeft == 0) {
            status = Status.TIE;
            return;
        }
        //**check horizontal and vertical**
        for (int i = 0; i<4; i++) {//1 for horizontal, 1 for vertical and 2 for diagonal right and 3 for diagonal left
            //check left of played button
            int posRight = col, posLeft = row;
            int inARow = 1;
            //check before of played button
            if (i==0) {
                posLeft += 1;
            }else if (i == 1) {
                posRight += 1;
            }else if (i == 2) {
                posLeft += 1;
                posRight += 1;
            }else {
                posLeft += 1;
                posRight -= 1;
            }
            while (inBounds(posLeft, posRight)) {
                if (board[posLeft][posRight].equals(currentPlayer)) {
                    inARow++;
                }else {
                    break;
                }
                if (i==0) {
                    posLeft += 1;
                }else if (i == 1) {
                    posRight += 1;
                }else if (i == 2) {
                    posLeft += 1;
                    posRight += 1;
                }else {
                    posLeft += 1;
                    posRight -= 1;
                }
            }
            updateWinner(inARow);
            //check after of played button
            posRight = col;
            posLeft = row;
            inARow = 1;
            if (i==0) {
                posLeft -= 1;
            }else if (i == 1) {
                posRight -= 1;
            }else if (i == 2) {
                posLeft -= 1;
                posRight -= 1;
            }else {
                posLeft -= 1;
                posRight += 1;
            }
            while (inBounds(posLeft, posRight)) {
                if (board[posLeft][posRight].equals(currentPlayer)) {
                    inARow++;
                }else {
                    break;
                }
                if (i==0) {
                    posLeft -= 1;
                }else if (i == 1) {
                    posRight -= 1;
                }else if (i == 2) {
                    posLeft -= 1;
                    posRight -= 1;
                }else {
                    posLeft -= 1;
                    posRight += 1;
                }
            }
            updateWinner(inARow);
        }
    }

    /**
     * checks if theres a winner and set the winner if there is one
     * @param inARow how many in a row was made
     */
    public void updateWinner(int inARow) {
        if (inARow >= 4) {
            if (currentPlayer.equals(PLAYER_RED)) {
                status = Status.RED_WON;
            }else {
                status = Status.YELLOW_WON;
            }
        }
    }
    /**
     * checks if row and column are in bound on the board
     * @param row on the board
     * @param col on the board
     * @return true if row and column are in bound
     */
    private boolean inBounds(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }
    public void setBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
    public String[][] getBoard() {
        return board;
    }

    /**
     * get the player at position
     * @param row horizontal position
     * @param col vertical position
     * @return
     */
    public String getAtPosition(int row, int col) {
        return board[row][col];
    }
    /**
     * get status of the game
     * @return
     */
    public Status getStatus() {
        return status;
    }

    public void addConnectFourView(ConnectFourView connectFourView) {
        views.add(connectFourView);
    }
    public void removeConnectFourView(ConnectFourView connectFourView) {
        views.add(connectFourView);
    }
    public void notifyViews() {
        for (ConnectFourView connectFourView : views) {
            connectFourView.update();
        }
    }

}
