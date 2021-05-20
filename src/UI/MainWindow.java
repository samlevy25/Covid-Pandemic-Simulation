package UI;
import Country.Map;
import IO.SimulationFile;
import Location.Point;
import Simulation.Clock;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    private Map myMap;
    private final JPanel contentPane;
    private PanelDrawing mapPanel;
    private boolean fileLoaded = false;
    private boolean closed = false;
    private JButton[] buttons;
    private JLabel[] labels;
    private StatisticsWindow statWindow;

    public MainWindow() {
        super("Main Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600,600));
        this.setLocationRelativeTo(null);

        this.setJMenuBar(createMenuBar()); // Add the menu bar

        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // Add the Slider
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Clock.setSnooze(slider.getValue());
            }
        });
        contentPane.add(slider, BorderLayout.PAGE_END);
    }
    public boolean hasFileLoaded(){
        return fileLoaded;
    }

    public boolean isClosed(){
        return closed;
    }

    public Map getMap() {
        return myMap;
    }

    public StatisticsWindow getStatWindow() {
        return statWindow;
    }

    private class PanelDrawing extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            this.removeAll();
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
                if (fileLoaded){
                statWindow = new StatisticsWindow(myMap, contentPane);
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
                if(fileLoaded){
                    EditMutationWindow editWindow = new EditMutationWindow(myMap.getVirus());}
                else
                    JOptionPane.showMessageDialog(new JFrame(), "You have to load a file !", "Statistics Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closed = true;
                System.exit(0);
            }
        });
        fileMenu.add(load);
        fileMenu.add(statistics);
        fileMenu.add(editMutations);
        fileMenu.add(exit);

        JMenu simulationMenu = new JMenu("Simulation");
        JMenuItem play = new JMenuItem("Play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileLoaded)
                    myMap.setState(true);
                else {
                    JOptionPane.showMessageDialog(new JFrame(), "You have to load a file !", "Play Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fileLoaded)
                    JOptionPane.showMessageDialog(new JFrame(), "You have to load a file !", "Pause Error",
                            JOptionPane.ERROR_MESSAGE);
                else if(!myMap.runningSimulation())
                    JOptionPane.showMessageDialog(new JFrame(), "There is no running simulation.", "Pause Error",
                            JOptionPane.ERROR_MESSAGE);
                else
                    myMap.setState(false);
            }
        });
        JMenuItem stop = new JMenuItem("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fileLoaded)
                    JOptionPane.showMessageDialog(new JFrame(), "You have to load a file !", "Stop Error",
                            JOptionPane.ERROR_MESSAGE);
                else{
                    myMap.setState(false);
                    MainWindow.this.contentPane.remove(mapPanel);
                    for(int i = 0; i< myMap.getSettlements().length; i++){
                        MainWindow.this.remove(buttons[i]);
                        MainWindow.this.remove(labels[i]);
                    }
                    myMap = null;
                    MainWindow.this.repaint();
                }
            }
        });
        JMenuItem setTicksPerDay = new JMenuItem("Set Ticks Per Day");
        setTicksPerDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fileLoaded)
                    JOptionPane.showMessageDialog(new JFrame(), "You have to load a file !", "Set Ticks Error",
                            JOptionPane.ERROR_MESSAGE);
                else{
                    SpinnerNumberModel spinMod = new SpinnerNumberModel(Clock.getTicks_per_day(), 1, 100, 1);
                    JSpinner spinner = new JSpinner(spinMod);
                    int optDlg = JOptionPane.showOptionDialog(null, spinner, "Set Ticks Per Day",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(optDlg ==0){
                        try{
                            long ticks = (long)Float.parseFloat(spinner.getValue().toString());
                            Clock.setTicks_per_day(ticks);
                        }
                        catch (Exception exception){
                            JOptionPane.showMessageDialog(new JFrame(), "Error occurred", "Set Ticks Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
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
            try {
                myMap = myFile.readFromFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            fileLoaded = true;
            buttons = new JButton[myMap.getSettlements().length];
            labels = new JLabel[myMap.getSettlements().length];
            mapPanel = new PanelDrawing();
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

                buttons[i] = button;
                labels[i] = name;
                this.add(button);
                this.add(name);
            }
            contentPane.add(mapPanel, BorderLayout.CENTER);
            this.pack();
        }
    }
}
