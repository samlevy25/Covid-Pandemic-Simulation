package UI;

import Country.Map;
import Country.Settlement;
import IO.StatisticsFile;
import Population.Person;
import Simulation.Main;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StatisticsWindow extends JFrame {
    private final Map my_map; // the Map
    private final JTable statsTable ; // Table of my_data
    private String selectedComboBox;
    private final TableRowSorter<TableModel> rs;
    private TableColumn selectedColumn = null ;
    private boolean flag = true;

    /**
     * Constructor
     * Initialization of all the graphical interface of all the data with the possibility of making simulations.
     * @param map : The map
     * @param mainWin : Panel of the graphical interface
     */
    StatisticsWindow(Map map, JPanel mainWin) {
        super("Statistics");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        my_map = map;
        this.setLayout(new BorderLayout());
        Panel up = new Panel();
        up.setLayout(new BoxLayout(up, BoxLayout.LINE_AXIS));
        String[] elementComboBox ={ "None", "Name","Type","RamzorColor"};
        statsTable = new JTable(new StatModel(map.getSettlements()));
        rs = new TableRowSorter<>(statsTable.getModel());
        statsTable.setRowSorter(rs);
        statsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane js = new JScrollPane(statsTable);
        TextField FilterText = new TextField("Filter");
        FilterText.addActionListener(new ActionListener()
        {
            /**
             * Filter of the data in the table
             */
            @Override
            public void actionPerformed(ActionEvent e) //
            {
                if(!flag)
                    rs.setRowFilter(RowFilter.regexFilter(FilterText.getText(), 0, selectedColumn.getModelIndex()));
            }
        }
        );

        JComboBox<String> colSelect = new JComboBox(elementComboBox);
        colSelect.addActionListener(new ActionListener()
        {
            /**
             * Give the index the element selectionned in the combo box .
             */
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectedComboBox = String.valueOf(colSelect.getSelectedItem());

                if (selectedComboBox == null) { return; }

                switch (selectedComboBox)
                {
                    case "None" :
                    {
                        flag = true;
                        selectedColumn = null ;
                        rs.setRowFilter(RowFilter.regexFilter(""));
                        break;
                    }
                    case "Name" :
                    {

                        flag= false;
                        selectedColumn = StatisticsWindow.this.statsTable.getColumnModel().getColumn(0);
                        break;
                    }
                    case "Type" :
                    {
                        flag = false;
                        selectedColumn = StatisticsWindow.this.statsTable.getColumnModel().getColumn(1);
                        break;
                    }
                    case "RamzorColor" :
                    {
                        flag = false;
                        selectedColumn = StatisticsWindow.this.statsTable.getColumnModel().getColumn(2);
                        break;
                    }
                }
            }
        });

        up.add(colSelect);
        up.add(FilterText);
        this.add(up, BorderLayout.PAGE_START);
        this.add(js);
        Panel down = new Panel();
        down.setLayout(new GridLayout(1,3));
        JButton save = new JButton("Save");
        save.addActionListener(this::saveCSV);
        down.add(save);
        JButton addSick = new JButton("Add Sick");
        addSick.addActionListener(new ActionListener()
        {
            /**
             * Add sick in a settlement with a random virus
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = statsTable.getSelectedRow();
                if(selection != -1) {
                    int modelIndex = statsTable.getRowSorter().convertRowIndexToModel(selection);
                    String str = (String) statsTable.getModel().getValueAt(modelIndex, 0);
                    int index = map.getSettlement(str);
                    Settlement settlementSelected = map.getSettlements()[index];

                    int numberOfSick = (int) (settlementSelected.getNumOfHealthy() * 0.1);

                    for (int i = 0; i < numberOfSick; i++) {
                        Person currentHealthy = settlementSelected.getHealthyPerson().get(0); // take the first person Healthy in the list of HealthyPeople
                        settlementSelected.isSick(currentHealthy, Main.randomVirus(map));
                    }
                    statsTable.repaint();
                    mainWin.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Select a row!", "Statistics Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        down.add(addSick);
        JButton vaccinate = new JButton("Vaccinate");
        vaccinate.addActionListener(new ActionListener()
        {
            /**
             * Give number of dose of vaccine to a settlement
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = statsTable.getSelectedRow();
                if (selection != -1) {
                    int modelIndex = statsTable.getRowSorter().convertRowIndexToModel(selection);
                    String set = (String) statsTable.getModel().getValueAt(modelIndex, 0);
                    int index = map.getSettlement(set);
                    String number = JOptionPane.showInputDialog("Please enter number of Dose Vaccine to add :");
                    map.getSettlements()[index].setNumberVaccineDose(Integer.parseInt(number));
                }
                else
                    JOptionPane.showMessageDialog(new JFrame(), "Select a row!", "Statistics Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        down.add(vaccinate);
        this.add(down, BorderLayout.PAGE_END);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Save all the data modified bu add sick , vaccinate or simulation in CVS file
     */
    private void saveCSV(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
            StatisticsFile statisticsFile = new StatisticsFile(selectedFile);
            try{
            statisticsFile.writeInCvs(my_map.getSettlements());
        }catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }

    /**
     * @return Table of the data
     */
    public JTable getStatsTable() {
        return statsTable;
    }
}
