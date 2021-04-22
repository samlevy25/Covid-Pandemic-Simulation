package UI;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class MainWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Window");
        JPanel mapPanel = new JPanel();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        // create menu bar
        JMenuBar menuBar = new JMenuBar();

        // build the different menus
        JMenu fileMenu = new JMenu("File");
        JMenu simulationMenu = new JMenu("Simulation");
        JMenu helpMenu = new JMenu("Help");

        // add the menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(simulationMenu);
        menuBar.add(helpMenu);

        // add items to the menus
        JMenuItem = new JMenuItem();

        // add to the frame all this shit
        frame.add(menuBar);
        frame.add(mapPanel);
        frame.add(new JSlider(JSlider.HORIZONTAL, 0, 30, 15));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
