import javax.swing.*;

public class Button extends JButton {
    public Button(String name, Runnable callBack) {
        super(name);
        this.addActionListener(e -> callBack.run());
    }
}
