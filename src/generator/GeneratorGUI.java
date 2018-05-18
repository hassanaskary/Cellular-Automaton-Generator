package generator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GeneratorGUI implements ActionListener {
    JFrame frame;
    JPanel controlPanel, gridPanel;
    JPanel[][] cellPanels;
    JButton runButton;

    Generator generator = new Generator();

    GeneratorGUI() {
        init();
    }

    void init() {
        frame = new JFrame("Cellular Automaton Generator");
        frame.setLayout(new BorderLayout());

        controlPanel = new JPanel();
        gridPanel = new JPanel();

        gridPanel.setLayout(new GridLayout(generator.getRows(), generator.getColumns(), 1, 1));

        frame.add(controlPanel, BorderLayout.WEST);
        frame.add(gridPanel, BorderLayout.CENTER);

        gridPanel.setBackground(Color.darkGray);

        runButton = new JButton("Run");
        runButton.addActionListener(this);

        controlPanel.add(runButton);
        controlPanel.setBackground(Color.lightGray);

        Random rand = new Random();

        cellPanels = new JPanel[generator.getRows()][generator.getColumns()];

        for(int i = 0; i < generator.getRows(); i++) {
            for(int j = 0; j < generator.getColumns(); j++) {
                cellPanels[i][j] = new JPanel();
                //cellPanels[i][j].setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                gridPanel.add(cellPanels[i][j]);
            }
        }

        frame.setSize(505, 485);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //startGenerationDrawing();
    }

    private void startGenerationDrawing() {
        //while(true) {
            System.out.println("StartGenerationDrawing!!");
            for(int i = 1; i < generator.getRows()-1; i++) {
                for(int j = 1; j < generator.getColumns()-1; j++) {
                    System.out.println("DRAWING!");
                    if(generator.getGeneration(i, j) == 1) {
                        System.out.println("BIRTH DRAWING!");
                        cellPanels[i][j].setBackground(Color.black);
                    }
                    else if(generator.getGeneration(i, j) == 0) {
                        System.out.println("DEATH DRAWING!");
                        cellPanels[i][j].setBackground(Color.white);
                    }
                }
            }

            System.out.println("Going to computeGeneration!");
            generator.computeGeneration();
        //}
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == runButton) {
            System.out.println("Run Button Clicked!");
            startGenerationDrawing();
        }
    }
}
