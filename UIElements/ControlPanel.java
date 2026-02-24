package UIElements;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel(int rows, int cols, Dimension prefferedSize, Color bgColor, UIElements.Button... args) {
        int index = 0;
        this.setLayout(new GridLayout(rows, cols));

        for (Button button : args) {
            this.add(button, index);
            index++;
        }

        this.setPreferredSize(prefferedSize);
        this.setBackground(bgColor);
    }
}
