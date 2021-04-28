package UI;
import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Simulation.Main;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
    private JPanel mapPanel = new JPanel();
    private JMenu fileMenu = new JMenu("File");
    private JMenu simulationMenu = new JMenu("Simulation");
    private JMenu helpMenu = new JMenu("Help");
    private Map myMap = null;

    public MainWindow() {
        super("Main Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(createMenuBar());
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(mapPanel);
        contentPane.add(slider);
        this.pack();
        this.setSize(600 ,600);
        this.setLocationRelativeTo(null);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load...");
        load.addActionListener(this::loadItemListener);
        JMenuItem statistics = new JMenuItem("Statistics");
        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myMap != null){
                StatisticsWindow statWindow = new StatisticsWindow(myMap);
                statWindow.setVisible(true);
            }
                else {
                    JOptionPane.showMessageDialog(new JFrame(), "You have to load a file !", "Statistics Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JMenuItem editMutations = new JMenuItem("Edit Mutations");
        editMutations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditMutationWindow editWindow = new EditMutationWindow();
                editWindow.setVisible(true);
            }
        });
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        fileMenu.add(load);
        fileMenu.add(statistics);
        fileMenu.add(editMutations);
        fileMenu.add(exit);

        JMenu simulationMenu = new JMenu("Simulation");
        JMenuItem play = new JMenuItem("Play");
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem stop = new JMenuItem("Stop");
        JMenuItem setTicksPerDay = new JMenuItem("Set Ticks Per Day");
        simulationMenu.add(play);
        simulationMenu.add(pause);
        simulationMenu.add(stop);
        simulationMenu.add(setTicksPerDay);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem about = new JMenuItem("About");
        helpMenu.add(help);
        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(simulationMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }


    private void loadItemListener(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
            SimulationFile myFile = new SimulationFile(selectedFile);
            Settlement[] s = new Settlement[0];
            try {
                s = myFile.readFromFile().toArray(new Settlement[0]);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            myMap = new Map(s, s.length);
        }
    }

}
