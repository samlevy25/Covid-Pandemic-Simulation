package UI;

import Country.Map;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;

public class StatisticsWindow extends JFrame {
    private Map my_map;
    StatisticsWindow(Map map) {
        super("Statistics Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        my_map = map;
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        Panel up = new Panel();
        up.setLayout(new BoxLayout(up, BoxLayout.LINE_AXIS));
        String[] elements = new String[] {"Name", "Type", "RamzorColor", "Sick Percent", "Given Vaccine Dose", "Dead", "Current Population"};
        JComboBox<String> colSelect = new JComboBox(elements);
        up.add(colSelect);
        up.add(new TextField("Filter"));
        contentPane.add(up);

        String[][] data = new String[map.getSettlements().length][7];
        for(int i = 0; i < data.length; i++) {
            data[i][0] = map.getSettlements()[i].getName();
            data[i][1] = map.getSettlements()[i].getClass().getSimpleName();
            data[i][2] = map.getSettlements()[i].getRamzorColor().toString();
            //data[i][3] = map.getSettlements()[i].getSickPercent().toString();
            //data[i][4] = map.getSettlements()[i].getGivenVaccineDose().toString();
            //data[i][5] = map.getSettlements()[i].getDead().toString();
            data[i][6] = String.valueOf(map.getSettlements()[i].getPeople().size());
        }
        JTable statsTable = new JTable(data, elements);
        contentPane.add(statsTable);

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
