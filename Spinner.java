import javax.swing.*;

public class Spinner extends JSpinner {
    public Spinner(SpinnerModel model) {
        super(model);
    }

    public int getAmount() {
        return (int) this.getValue();
    }
}