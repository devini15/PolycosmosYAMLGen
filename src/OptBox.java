import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class OptBox {
    public static final int GENERIC = 0;
    public static final int TEXT_FIELD = 1;
    public static final int DROP_DOWN = 2;
    public static final int SLIDER = 3;
    public static final int TOGGLE = 4;

    private static final int ITEM_WIDTH = 600;
    private static final int ITEM_HEIGHT = 50;

    private final Box item;
    private int type;
    private String hint;
    private final String title;

    private final JTextField optField = new JTextField();
    public final JComboBox<String> optDown = new JComboBox<>();
    public final JSlider optSlide = new JSlider();
    private final JLabel slideLabel = new JLabel();
    private final JCheckBox optToggle = new JCheckBox();
    public final JCheckBox randBox = new JCheckBox();


    public OptBox(String title, int type){
        item = Box.createHorizontalBox();
        item.setSize(ITEM_WIDTH, ITEM_HEIGHT);
        item.setMaximumSize(new Dimension(700, 50));
        JButton hintButton = new JButton();
        hintButton.setAction(onHintButtonPressed());
        hintButton.setText("?");
        hintButton.setPreferredSize(new Dimension (50,50));
        hintButton.setEnabled(true);
        JLabel randLabel = new JLabel("Randomize  ");
        item.add(randLabel);
        item.add(randBox);
        item.add(hintButton);
        this.title = title;
        JLabel titleLabel = new JLabel(" " + title + ": ");
        item.add(titleLabel);
        hint = "No hint set";

        if(type == TEXT_FIELD) item.add(optField);
        else if(type == DROP_DOWN) item.add(optDown);
        else if(type == SLIDER){
            item.add(optSlide);
            item.add(slideLabel);
        }
        else if(type == TOGGLE) item.add(optToggle);
    }

    public void setText(String defaultValue){
        optField.setText(defaultValue);
    }

    public void setDrop(String[] options){
        for(String s : options){
            optDown.addItem(s);
        }
    }

    public void setSlide(int min, int max, int set){
        optSlide.setMinimum(min);
        optSlide.setMaximum(max);
        optSlide.setValue(set);
        optSlide.setMajorTickSpacing((max - min)/10);
        optSlide.setMinorTickSpacing(1);
        optSlide.setSnapToTicks(true);
        slideLabel.setText(set + "   ");
        optSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                slideLabel.setText(optSlide.getValue() + "   ");
            }
        });

    }

    public void setToggle(boolean selected){
        optToggle.setSelected(selected);
    }

    public void setEnabled(boolean enabled){
        optField.setEnabled(enabled);
        optDown.setEnabled(enabled);
        optSlide.setEnabled(enabled);
        optToggle.setEnabled(enabled);
        randBox.setEnabled(enabled);
    }

    public String getValue(){
        if (type == 0) return optField.getText();
        else if (type == 1) return optDown.getSelectedItem().toString();
        else if (type == 2) return String.valueOf(optSlide.getValue());
        else return "ERROR";
    }

    public void setHint(String hint){
        this.hint = hint;
    }

    public Box box() {return item;}

    public Action onHintButtonPressed(){
        return new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, hint, title, JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }
}
