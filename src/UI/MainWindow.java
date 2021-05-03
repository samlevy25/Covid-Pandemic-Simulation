package UI;
import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Location.Point;
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

    private Map myMap;
    private final JPanel contentPane;

    public MainWindow() {
        super("Main Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600,600));
        this.setLocationRelativeTo(null);

        this.setJMenuBar(createMenuBar()); // Add the menu bar

        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 30, 15); // Add the Slider
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        contentPane.add(slider, BorderLayout.PAGE_END);
    }

    private class PanelDrawing extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            for (int i = 0; i < myMap.getSettlements().length; i++){
                Point p1 = myMap.getSettlements()[i].getLocation().getCenter();
                for (int j = 0; j < myMap.getSettlements()[i].getNeighbours().length; j++){
                    Point p2 = myMap.getSettlements()[i].getNeighbours()[j].getLocation().getCenter();
                    g.drawLine(p1.getM_x(), p1.getM_y(), p2.getM_x(), p2.getM_y());
                }
            }
            for (int i = 0; i < myMap.getSettlements().length; i++){
                int x = myMap.getSettlements()[i].getLocation().getPosition().getM_x();
                int y = myMap.getSettlements()[i].getLocation().getPosition().getM_y();
                int width = myMap.getSettlements()[i].getLocation().getSize().getWidth();
                int height = myMap.getSettlements()[i].getLocation().getSize().getHeight();
                Color color = myMap.getSettlements()[i].getRamzorColor().color;
                g.setColor(color);
                g.fillRect(x, y, width, height);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, width, height);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }
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
                StatisticsWindow statWindow = new StatisticsWindow(myMap, contentPane);
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
                EditMutationWindow editWindow = new EditMutationWindow(myMap.getVirus());
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
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem stop = new JMenuItem("Stop");
        JMenuItem setTicksPerDay = new JMenuItem("Set Ticks Per Day");
        simulationMenu.add(play);
        simulationMenu.add(pause);
        simulationMenu.add(stop);
        simulationMenu.add(setTicksPerDay);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(new JFrame(), "Welcome to our simulation program!\n" +
                        "This program allows to see the evolution of the virus development over time according to each settlements.\n" +
                        "The map allows to see the result of the simulation carried out with the possibility of adjusting its speed thanks to the slider.\n" +
                        "In the menu bar we have:\n" +
                        "- File: Loading of a simulation file, display of statistics for each city, edit mutation which changes the current virus variant.\n" +
                        "- Simulation: Play which starts a simulation according to the loaded file, pause only if a simulation is running, stop the simulation and number of ticks in a day\n" +
                        "- Statistics window: Possibility to filter the results by key-word and column, in a selected settlements we can add sicks people and the number of doses of vaccines to add.\n" +
                        "Let's simulate!");
            }
        });
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jdabout = new JDialog(new JFrame(), "About", false);
                JPanel pan = new JPanel();
                pan.add(new JLabel("<html>#About us:<br>" +
                        "- 2nd year students of computer science engineer at Shamoon College of Engineering (SCE)<br>" +
                        "<br>" +
                        "#Creators:<br>" +
                        "- Jacob Elbaz, ID: 336068895<br>" +
                        "- Samuel Levy, ID: 345112148<br>" +
                        "<br>" +
                        "#Creation date :<br>" +
                        "- May 4, 2021<html>"));
                jdabout.add(pan);
                jdabout.pack();
                jdabout.setLocationRelativeTo(null);
                jdabout.setVisible(true);
            }
        });
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
            PanelDrawing mapPanel = new PanelDrawing();
            for (int i = 0; i < myMap.getSettlements().length; i++){
                JLabel name = new JLabel(myMap.getSettlements()[i].getName());
                JButton button = new JButton();
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                int finalI = i;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        StatisticsWindow window = new StatisticsWindow(myMap, contentPane);
                        window.getStatsTable().setRowSelectionInterval(finalI, finalI);
                        window.setVisible(true);
                    }
                });

                int x = myMap.getSettlements()[i].getLocation().getPosition().getM_x();
                int y = myMap.getSettlements()[i].getLocation().getPosition().getM_y();
                int width = myMap.getSettlements()[i].getLocation().getSize().getWidth();
                int height = myMap.getSettlements()[i].getLocation().getSize().getHeight();
                name.setBounds(x+2, y,100 ,height);
                button.setBounds(x, y, width, height);
                this.add(button);
                this.add(name);
            }
            contentPane.add(mapPanel, BorderLayout.CENTER);
            this.pack();
        }
    }
}
