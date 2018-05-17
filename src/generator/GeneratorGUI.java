package generator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class GeneratorGUI {
    JFrame frame;
    JPanel controlPanel, gridPanel;
    JPanel[][] cellPanels;
    JButton runButton;

    Generator

    GeneratorGUI() {
        init();
    }

    void init() {
        frame = new JFrame("Cellular Automaton Generator");
        frame.setLayout(new BorderLayout());

        controlPanel = new JPanel();
        gridPanel = new JPanel();

        gridPanel.setLayout(new GridLayout(20, 20, 1, 1));

        frame.add(controlPanel, BorderLayout.WEST);
        frame.add(gridPanel, BorderLayout.CENTER);

        runButton = new JButton("Run");

        controlPanel.add(runButton);
        controlPanel.setBackground(Color.lightGray);

        Random rand = new Random();
        Border lineBorder = BorderFactory.createLineBorder(Color.blue, 5);

        cellPanels = new JPanel[20][20];

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                cellPanels[i][j] = new JPanel();
                cellPanels[i][j].setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                gridPanel.add(cellPanels[i][j]);
            }
        }

        frame.setSize(505, 485);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
