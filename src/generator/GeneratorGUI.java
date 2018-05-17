package generator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GeneratorGUI {
    JFrame frame;
    JPanel mainPanel, controlPanel;
    ////////////////////////////////
    JPanel panel;
    /////////////////////////
    JButton runButton;

    GeneratorGUI() {
        init();
    }

    void init() {
        frame = new JFrame("Cellular Automaton Generator");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.setContentPane(mainPanel);
        controlPanel = new JPanel();
        panel = new JPanel();
        panel.setLayout(new GridLayout(100, 100));
        mainPanel.add(controlPanel, BorderLayout.WEST);
        mainPanel.add(panel, BorderLayout.CENTER);
        runButton = new JButton("Run");
        controlPanel.add(runButton);
        controlPanel.setBackground(Color.red);
        //////////////////////////////////////////////////////////////////////
        JLabel label = new JLabel();
        Border lineBorder = BorderFactory.createLineBorder(Color.black, 5);
        label.setBorder(lineBorder);
        panel.add(label);
        //////////////////////////////////////////////////////////////////////

        frame.setSize(1000,700);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
