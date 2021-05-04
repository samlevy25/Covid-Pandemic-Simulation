package UI;

import Country.Map;
import Country.Settlement;
import IO.StatisticsFile;
import Population.Healthy;
import Population.Person;
import Population.Sick;
import Simulation.Clock;
import Simulation.Main;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class StatisticsWindow extends JFrame {
    private Map my_map;
    private String[][] my_data;
    JTable statsTable ;
    private String selectedComboBox;
    private TableRowSorter rs;
    private TableColumn selectedColumn = null ;
    StatisticsWindow(Map map, JPanel mainWin) {
        super("Statistics");
        JPanel mainW = mainWin;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        my_map = map;
        this.setLayout(new BorderLayout());
        Panel up = new Panel();
        up.setLayout(new BoxLayout(up, BoxLayout.LINE_AXIS));
        String[] elements = {"Name", "Type", "RamzorColor", "Sick Percent", "Vaccine Dose", "Dead", "Current Population"};
        String[] elementComboBox ={ "None", "Name","Type","RamzorColor"};
        TextField FilterText = new TextField("Filter");
        FilterText.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                rs.setRowFilter(RowFilter.regexFilter(FilterText.getText(), 0, selectedColumn.getModelIndex()));
            }
        });

        JComboBox<String> colSelect = new JComboBox(elementComboBox);
        colSelect.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectedComboBox = String.valueOf(colSelect.getSelectedItem());
                switch (selectedComboBox)
                {
                    case "None" :
                    {
                        selectedColumn = null ;
                        rs.setRowFilter(RowFilter.regexFilter(""));
                        break;
                    }
                    case "Name" :
                    {
                        selectedColumn = StatisticsWindow.this.statsTable.getColumnModel().getColumn(0);
                        break;
                    }
                    case "Type" :
                    {
                        selectedColumn = StatisticsWindow.this.statsTable.getColumnModel().getColumn(1);
                        break;
                    }
                    case "RamzorColor" :
                    {
                        selectedColumn = StatisticsWindow.this.statsTable.getColumnModel().getColumn(2);
                        break;
                    }
                }
                if (selectedComboBox == null) { return; }
            }
        });

        up.add(colSelect);
        up.add(FilterText);
        this.add(up, BorderLayout.PAGE_START);


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
        statsTable = new JTable(data, elements);
        rs = new TableRowSorter<>(statsTable.getModel());
        statsTable.setRowSorter(rs);
        statsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane js = new JScrollPane(statsTable);

        //table.setValueAt("aa", 0, 0);
        this.add(js);

        Panel down = new Panel();
        down.setLayout(new GridLayout(1,3));
        JButton save = new JButton("Save");
        save.addActionListener(this::saveCSV);
        down.add(save);
        JButton addSick = new JButton("Add Sick");
        addSick.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {


                String str = (String) statsTable.getModel().getValueAt(statsTable.getSelectedRow(), 0);
                int index = map.getSettlement(str);
                Settlement settlementSelected = map.getSettlements()[index];

                int numberOfSick = (int) (settlementSelected.getNumOfHealthy() * 0.1);

                for (int i = 0 ; i < numberOfSick; i++)
                {
                    Healthy currentHealthy = (Healthy) settlementSelected.getHealthyPerson().get(0); // take the first person Healthy in the list of HealthyPeople
                    settlementSelected.isSick(currentHealthy , Main.randomVirus(map));
                }
                data[index][3] = String.valueOf(settlementSelected.getSickPercent());
                data[index][2] = settlementSelected.getRamzorColor().toString();
                statsTable.repaint();
                mainW.repaint();
            }
        });
        down.add(addSick);
        JButton vaccinate = new JButton("Vaccinate");
        vaccinate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String set = (String) statsTable.getModel().getValueAt(statsTable.getSelectedRow(), 0);
                int index = map.getSettlement(set);
                String number = JOptionPane.showInputDialog("Please enter number of Dose Vaccine to add :");
                map.getSettlements()[index].setNumberVaccineDose(Integer.parseInt(number));
                data[statsTable.getSelectedRow()][4] = String.valueOf(map.getSettlements()[index].getGivenVaccineDose());
            }
        });

        down.add(vaccinate);
        this.add(down, BorderLayout.PAGE_END);
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

    public JTable getStatsTable() {
        return statsTable;
    }
}
