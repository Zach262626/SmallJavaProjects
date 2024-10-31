import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TicTacToeController implements ActionListener {
    private TicTacToeModel model;
    private TicTacToeView view;
    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o instanceof JButton button) {
            if (button.getText().equals("Change Column")) {

                int y = Integer.parseInt(view.getTextField(button.getText()));
                System.out.println(y);
                if (y > 0) {
                    model.setCols(y);
                    model.reset();
                }
            }else if(button.getText().equals("Change Row")) {
                int x = Integer.parseInt(view.getTextField(button.getText()));
                if (x > 0) {
                    model.setRows(x);
                    model.reset();
                }
            }else if(button.getText().equals("Change In A Row To Win")) {
                int inARow = Integer.parseInt(view.getTextField(button.getText()));
                if (inARow <= model.getRows() && inARow <= model.getCols() && inARow >= 0) {
                    model.setInARow(inARow);
                    model.reset();
                }
            }else {
                int[] index = view.findIndex(button);
                System.out.println(index[0] + " " + index[1]);
                model.play(index[0], index[1]);
            }
        }else {
            JMenuItem item = (JMenuItem) o;
            if (item.getText().equals("Reset")) {
                model.reset();
            }else {
                System.exit(0);
            }
        }

    }
}
