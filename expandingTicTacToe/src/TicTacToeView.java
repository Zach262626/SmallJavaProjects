import javax.swing.*;
import java.awt.*;

public class TicTacToeView extends JFrame {
    private TicTacToeModel model;
    private TicTacToeController controller;
    private JPanel board;
    private JButton boardSquare[][];
    private JTextField columnChange, rowChange, inARowChange;
    private JLabel gameStatus;
    public TicTacToeView(TicTacToeModel model) {
        super("TicTacToe");
        this.model = model;
        model.addView(this);
        controller = new TicTacToeController(model, this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(900, 900);
        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Options");
        JMenuItem newGame = new JMenuItem("Reset");
        newGame.addActionListener(controller);
        JMenuItem exitGame = new JMenuItem("Exit");
        exitGame.addActionListener(controller);
        fileMenu.add(newGame);
        fileMenu.add(exitGame);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        //change col, row and to win
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(1, 6));
        rightPanel.setSize(200, 50);
        columnChange = new JTextField();
        columnChange.setName("Change Column");
        columnChange.setSize(60, 10);
        rowChange = new JTextField();
        rowChange.setSize(60, 10);
        rowChange.setName("Change Row");
        inARowChange = new JTextField(0);
        inARowChange.setSize(60, 10);
        inARowChange.setName("Change In A Row To Win");
        JButton rightButton1 = new JButton("Change Column");
        JButton rightButton2 = new JButton("Change Row");
        JButton rightButton3 = new JButton("Change In A Row To Win");
        rightButton1.addActionListener(controller);
        rightButton2.addActionListener(controller);
        rightButton3.addActionListener(controller);
        rightPanel.add(columnChange);
        rightPanel.add(rightButton1);
        rightPanel.add(rowChange);
        rightPanel.add(rightButton2);
        rightPanel.add(inARowChange);
        rightPanel.add(rightButton3);

        board = new JPanel();
        gameStatus = new JLabel("Player X's Turn");
        gameStatus.setForeground(Color.BLACK);
        gameStatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
        JPanel gamePanel = new JPanel();
        gamePanel.add(gameStatus);
        gamePanel.setLayout(new GridLayout(1, 2));
        gamePanel.setSize(200, 50);
        gamePanel.setVisible(true);
        board.setVisible(false);
        createBoard(model.getRows(), model.getCols());
        board.setVisible(true);

        this.setLayout(new BorderLayout());
        this.add(rightPanel, BorderLayout.SOUTH);
        this.add(gamePanel, BorderLayout.NORTH);
        this.setVisible(true);
    }
    public void createBoard(int row, int col) {
        board.removeAll();
        board.setVisible(true);
        board.setLayout(new GridLayout(row, col));
        boardSquare = new JButton[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boardSquare[i][j] = new JButton(model.getSquare(i, j));
                boardSquare[i][j].addActionListener(controller);
                boardSquare[i][j].setSize(50,50);
                boardSquare[i][j].setVisible(true);
                board.add(boardSquare[i][j]);
            }
        }
        add(board, BorderLayout.CENTER);
    }
    public void update() {
        gameStatus.setText(model.getCurrentPlayer() + "'s Turn");
        if ((model.getRows() * model.getCols()) == model.getSquareRemaining()) {
            board.setVisible(false);
            createBoard(model.getRows(), model.getCols());
        }else if (model.getStatus() == TicTacToeModel.Status.X_WON) {
            gameStatus.setText("Player X Won");
        }else if (model.getStatus() == TicTacToeModel.Status.O_WON) {
            gameStatus.setText("Player O");
        }else if (model.getStatus() == TicTacToeModel.Status.TIE) {
            gameStatus.setText("TIE");
        }
        for (int i = 0; i < boardSquare.length; i++) {
            for (int j = 0; j < boardSquare[i].length; j++) {
                if (!boardSquare[i][j].getText().equals(model.getSquare(i, j))) {
                    boardSquare[i][j].setText(model.getSquare(i, j));
                }
            }
        }
    }
    public int[] findIndex(JButton button) {
        for (int i = 0; i < boardSquare.length; i++) {
            for (int j = 0; j < boardSquare[i].length; j++) {
                if (boardSquare[i][j].equals(button)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
    public String getTextField(String buttonName) {
        if (buttonName.equals("Change Column")) {
            return columnChange.getText();
        }else if(buttonName.equals("Change Row")) {
            return rowChange.getText();
        }else {
            return inARowChange.getText();
        }
    }
    public static void main(String[] args) {
        TicTacToeModel game = new TicTacToeModel(3,3,3);
        TicTacToeView view = new TicTacToeView(game);
    }
}
