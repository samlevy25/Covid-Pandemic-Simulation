package UI;

import Country.Map;
import Country.Settlement;
import IO.StatisticsFile;
import Population.Healthy;
import Population.Person;
import Population.Sick;
import Simulation.Clock;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        //table.setValueAt("aa", 0, 0);
        contentPane.add(js);

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
                Settlement settlementSelected = map.getSettlements()[statsTable.getSelectedRow()];

                int numberOfSick = (int) (settlementSelected.getNumOfHealthy() * 0.01);


                for (int i = 0 ; i < numberOfSick; i++)
                {
                   int randomNumber = (int)Math.floor(Math.random()*(2+1));
                   if (randomNumber == 0) // get SouthAfricanVariant
                   {
                       Healthy currentHealthy = (Healthy) settlementSelected.getHealthyPerson().get(0); // take the first person Healthy in the list of HealthyPeople
                       settlementSelected.isSick(currentHealthy); // update lists
                       Sick newSick = new Sick(currentHealthy.getAge(), currentHealthy.getLocation(), currentHealthy.getSettlement(),new SouthAfricanVariant(), Clock.now());
                       // get sick

                   }
                   else if (randomNumber == 1) // get ChineseVariant
                    {
                        Healthy currentHealthy = (Healthy) settlementSelected.getHealthyPerson().get(0); // take the first person Healthy in the list of HealthyPeople
                        settlementSelected.isSick(currentHealthy); // update lists
                        Sick newSick = new Sick(currentHealthy.getAge(), currentHealthy.getLocation(), currentHealthy.getSettlement(),new ChineseVariant(), Clock.now());
                        // get sick

                    }
                   else // get BritishVariant
                    {
                        Healthy currentHealthy = (Healthy) settlementSelected.getHealthyPerson().get(0); // take the first person Healthy in the list of HealthyPeople
                        settlementSelected.isSick(currentHealthy); // update lists
                        Sick newSick = new Sick(currentHealthy.getAge(), currentHealthy.getLocation(), currentHealthy.getSettlement(),new ChineseVariant(), Clock.now());
                        // get sick

                    }


                }
                System.out.println("hey");
                //System.out.println(settlementSelected.numOfSicks());
                data[statsTable.getSelectedRow()][3] = String.valueOf(settlementSelected.getSickPercent());
            }
        });
        down.add(addSick);
        JButton vaccinate = new JButton("Vaccinate");
        vaccinate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String number = JOptionPane.showInputDialog("Please enter number of Dose Vaccine to add :");
                data[statsTable.getSelectedRow()][4] = String.valueOf((Integer.parseInt(number)+Integer.parseInt(data[statsTable.getSelectedRow()][4])));
            }
        });

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
