import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFourController implements ActionListener {
    private ConnectFourModel model;
    private ConnectFourView view;
    public ConnectFourController(ConnectFourModel model, ConnectFourView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {
            JButton button = (JButton) o;
            int y = view.getButtonIndex(button);
            model.play(y);
        }else {
            JMenuItem item = (JMenuItem) e.getSource();
            String text = item.getText();
            if (text.equals("Exit")) {
                System.exit(0);
            }else {
                model.reset();
            }
        }
    }
}
