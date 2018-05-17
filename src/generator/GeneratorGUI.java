package generator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class GeneratorGUI {
    JFrame frame;
    JPanel mainPanel, controlPanel, gridPanel;
    ////////////////////////////////
    JPanel[][] cellPanels;
    /////////////////////////
    JButton runButton;

    GeneratorGUI() {
        init();
    }

    void init() {
        frame = new JFrame("Cellular Automaton Generator");
        frame.setLayout(new BorderLayout());
        /*
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);
        */
        controlPanel = new JPanel();
        gridPanel = new JPanel();

        gridPanel.setLayout(new GridLayout(20, 20, 1, 1));

        frame.add(controlPanel, BorderLayout.WEST);
        frame.add(gridPanel, BorderLayout.CENTER);

        runButton = new JButton("Run");

        controlPanel.add(runButton);
        controlPanel.setBackground(Color.red);

        //////////////////////////////////////////////////////////////////////
        Random rand = new Random();
        Border lineBorder = BorderFactory.createLineBorder(Color.blue, 5);

        cellPanels = new JPanel[20][20];

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                cellPanels[i][j] = new JPanel();
                cellPanels[i][j].setBackground(Color.blue);
                gridPanel.add(cellPanels[i][j]);
            }
        }

        //////////////////////////////////////////////////////////////////////

        frame.setSize(505, 485);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
