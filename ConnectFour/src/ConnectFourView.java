import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ConnectFourView extends JFrame {
    private JPanel board;
    private JButton[][] button;
    private final ConnectFourModel model;
    private final ConnectFourController controller;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem reset,exit;
    private JLabel status;
    public ConnectFourView(ConnectFourModel model) {
        super("ConnectFour");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        this.model = model;
        controller = new ConnectFourController(model, this);
        board = new JPanel();
        board.setLayout(new GridLayout(ConnectFourModel.ROWS, ConnectFourModel.COLS));
        button = new JButton[ConnectFourModel.ROWS][ConnectFourModel.COLS];
        createBoard();
        setJMenu();
        JPanel statusPanel = new JPanel();
        status = new JLabel(model.statusString());
        status.setFont(new Font("Arial", Font.PLAIN, 20));
        statusPanel.add(status);
        add(board, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
    private void setJMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        reset = new JMenuItem("Reset");
        reset.addActionListener(controller);
        exit = new JMenuItem("Exit");
        reset.addActionListener(controller);
        menu.add(reset);
        menu.add(exit);
        menuBar.add(menu);
        add(menuBar, BorderLayout.NORTH);
    }
    public void createBoard() {
        String[][] boardModel = model.getBoard();
        board.setVisible(false);
        board.removeAll();
        for (int i = (ConnectFourModel.ROWS-1); i >= 0; i--) {
            for (int j = 0; j < ConnectFourModel.COLS; j++) {
                button[i][j] = new JButton();
                button[i][j].setVisible(true);
                button[i][j].setFocusable(true);
                button[i][j].setName(boardModel[i][j]);
                button[i][j].setBackground(Color.WHITE);
                button[i][j].addActionListener(controller);
                board.add(button[i][j]);
            }
        }
        board.setVisible(true);
    }
    public void update() {
        String[][] boardModel = model.getBoard();
        status.setText(model.statusString());
        if (model.getSpacesLeft() == ConnectFourModel.ROWS * ConnectFourModel.COLS) {
            createBoard();
        }
        for (int i = 0; i < ConnectFourModel.ROWS; i++) {
            for (int j = 0; j < ConnectFourModel.COLS; j++) {
                if (!button[i][j].getName().equals(boardModel[i][j])) {
                    button[i][j].setName(boardModel[i][j]);
                    if (Objects.equals(boardModel[i][j], ConnectFourModel.PLAYER_RED)) {
                        button[i][j].setBackground(Color.RED);
                    }else {
                        button[i][j].setBackground(Color.YELLOW);
                    }
                    button[i][j].setFocusable(false);
                }
            }
        }
    }
    public int getButtonIndex(JButton buttonPressed) {
        for (int i = 0; i < ConnectFourModel.ROWS; i++) {
            for (int j = 0; j < ConnectFourModel.COLS; j++) {
                if (buttonPressed.equals(button[i][j])) {
                    return j;
                }
            }
        }
        return -1;
    }
}
