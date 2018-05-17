package generator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class GeneratorGUI {
    JFrame frame;
    JPanel mainPanel, controlPanel, gridPanel;
    ////////////////////////////////
    JLabel[][] cellLabels;
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
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(100, 100));
        mainPanel.add(controlPanel, BorderLayout.WEST);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        runButton = new JButton("Run");
        controlPanel.add(runButton);
        controlPanel.setBackground(Color.red);
        //////////////////////////////////////////////////////////////////////
        gridPanel.setLayout(new GridLayout(50, 50, 20, 20));
        Random rand = new Random();
        Border lineBorder = BorderFactory.createLineBorder(Color.blue, 5);
        cellLabels = new JLabel[100][100];
        gridPanel.add(new JButton("helsjaf"), 0, 0);/*
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                cellLabels[i][j] = new JLabel();
                cellLabels[i][j].setBorder(lineBorder);
                cellLabels[i][j].setBackground(Color.blue);
                cellLabels[i][j].setText("hello");
                gridPanel.add(cellLabels[i][j]);
            }
        } */
        //////////////////////////////////////////////////////////////////////

        frame.setSize(1000,700);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
