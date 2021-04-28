package UI;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;

public class StatisticsWindow extends JFrame {
    StatisticsWindow() {
        super("Statistics Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        Panel up = new Panel();
        up.setLayout(new BoxLayout(up, BoxLayout.LINE_AXIS));
        String[] elements = new String[] {"Name", "Type", "RamzorColor", "Sick Percent", "Given Vaccine Dose", "Deeds", "Current Population"};
        JComboBox<String> colSelect = new JComboBox(elements);
        up.add(colSelect);
        up.add(new TextField("Filter"));
        contentPane.add(up);
        contentPane.add(new JTable("Stats Table"));
        Panel down = new Panel();
        down.setLayout(new GridLayout(1,3));
        down.add(new JButton("Save"));
        down.add(new JButton("Add Sick"));
        down.add(new JButton("Vaccinate"));
        contentPane.add(down);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        StatisticsWindow mine = new StatisticsWindow();
        mine.setVisible(true);
    }
}
