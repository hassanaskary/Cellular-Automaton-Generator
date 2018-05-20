package generator;

import javax.swing.*;
import java.awt.*;

public class GeneratorGUI {
    JFrame frame;
    JPanel gridPanel;
    JPanel[][] cellPanels;

    Generator generator = new Generator();

    Thread t;

    /*
     * This is the constructor. It call a function init()
     */

    GeneratorGUI() {
        init();
    }

    /*
     * This is the init() function which handles all the GUI
     * related processing of the program.
     */

    void init() {
        frame = new JFrame("Cellular Automaton Generator");
        frame.setLayout(new BorderLayout());

        gridPanel = new JPanel();

        gridPanel.setLayout(new GridLayout(generator.getRows(), generator.getColumns(), 1, 1));

        frame.add(gridPanel, BorderLayout.CENTER);

        gridPanel.setBackground(Color.darkGray);

        cellPanels = new JPanel[generator.getRows()][generator.getColumns()];

        for(int i = 0; i < generator.getRows(); i++) {
            for(int j = 0; j < generator.getColumns(); j++) {
                cellPanels[i][j] = new JPanel();
                gridPanel.add(cellPanels[i][j]);
            }
        }

        //frame.setSize(600, 485);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t = new Thread();

        /*
         * This infinite while loop calls the startGenerationDrawing() function
         * repeatedly with an interval between each call.
         */

        while(true) {
            startGenerationDrawing();
            try {
                t.sleep(150);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * This is the startGenerationDrawing() function.
     * It receives the current state of the grid from
     * the Generator class and draws it on the JFrame
     * in a JPanel. It uses JPanels to draw the grids.
     * Black indicates 1 or alive and white indicate 0
     * or dead.
     */

    private void startGenerationDrawing() {
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
        frame.validate();
        frame.repaint();
    }
}
