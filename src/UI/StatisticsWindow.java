package UI;

import Country.Map;
import IO.StatisticsFile;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class StatisticsWindow extends JFrame {
    private Map my_map;
    private String[][] my_data;
    StatisticsWindow(Map map) {
        super("Statistics Window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        my_map = map;
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        Panel up = new Panel();
        up.setLayout(new BoxLayout(up, BoxLayout.LINE_AXIS));
        String[] elements = {"Name", "Type", "RamzorColor", "Sick Percent", "Given Vaccine Dose", "Dead", "Current Population"};
        JComboBox<String> colSelect = new JComboBox(elements);
        up.add(colSelect);
        up.add(new TextField("Filter"));
        contentPane.add(up, BorderLayout.PAGE_START);


        String[][] data = new String[map.getSettlements().length][7];
        for(int i = 0; i < data.length; i++) {
            data[i][0] = map.getSettlements()[i].getName();
            data[i][1] = map.getSettlements()[i].getClass().getSimpleName();
            data[i][2] = map.getSettlements()[i].getRamzorColor().toString();
            data[i][3] = String.valueOf(map.getSettlements()[i].getSickPercent());
            data[i][4] = String.valueOf(map.getSettlements()[i].getGivenVaccineDose());
            data[i][5] = String.valueOf(map.getSettlements()[i].getDead());
            data[i][6] = String.valueOf(map.getSettlements()[i].getPeople().size());
        }
        my_data = data;
        JTable statsTable = new JTable(data, elements);
        JScrollPane js = new JScrollPane(statsTable);
        contentPane.add(js);

        Panel down = new Panel();
        down.setLayout(new GridLayout(1,3));
        JButton save = new JButton("Save");
        save.addActionListener(this::saveCSV);
        down.add(save);
        JButton addSick = new JButton("Add Sick");
        down.add(addSick);
        JButton vaccinate = new JButton("Vaccinate");
        down.add(vaccinate);
        contentPane.add(down, BorderLayout.PAGE_END);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void saveCSV(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
            StatisticsFile statisticsFile = new StatisticsFile(selectedFile);
            try{
            statisticsFile.writeInCvs(my_data);
        }catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
}
