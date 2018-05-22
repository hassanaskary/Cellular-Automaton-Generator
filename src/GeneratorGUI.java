import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GeneratorGUI implements MouseListener, ActionListener, Runnable {
    JFrame frame;
    GridPanel gridPanel;
    double cellWidth, cellHeight;
    int column, row;
    Generator generator;
    JPanel controlInfoPanel;
    JButton startBtn, stopBtn, stepBtn, randomBtn, clearBtn;
    JLabel populationInfo, birthInfo, deathInfo, populationInfoValue, birthInfoValue, deathInfoValue;
    boolean runStatus;

    GeneratorGUI() {
        frame = new JFrame("Cellular Automaton Generator");
        frame.setSize(600, 600);
        //frame.pack();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generator = new Generator();

        gridPanel = new GridPanel(generator);
        frame.add(gridPanel, BorderLayout.CENTER);
        gridPanel.addMouseListener(this);

        controlInfoPanel = new JPanel();
        controlInfoPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        populationInfo = new JLabel("Population: ");
        birthInfo = new JLabel("Birth: ");
        deathInfo = new JLabel("Death: ");
        populationInfoValue = new JLabel(""+generator.getPopulation());
        birthInfoValue = new JLabel(""+generator.getBirth());
        deathInfoValue = new JLabel(""+generator.getDeath());

        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");
        stepBtn = new JButton("Step");
        randomBtn = new JButton("Random");
        clearBtn = new JButton("Clear");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        controlInfoPanel.add(populationInfo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        controlInfoPanel.add(populationInfoValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        controlInfoPanel.add(birthInfo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        controlInfoPanel.add(birthInfoValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        controlInfoPanel.add(deathInfo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        controlInfoPanel.add(deathInfoValue, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        controlInfoPanel.add(startBtn, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        controlInfoPanel.add(stopBtn, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 1;
        controlInfoPanel.add(stepBtn, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        controlInfoPanel.add(randomBtn, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        controlInfoPanel.add(clearBtn, gbc);

        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        stepBtn.addActionListener(this);
        randomBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        frame.add(controlInfoPanel, BorderLayout.SOUTH);

        runStatus = false;

        frame.setVisible(true);
    }

    private void generateAndDraw() {
        generator.computeGeneration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        frame.repaint();
    }

    private void randomInitialCondition() {
        generator.randomConfiguration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        frame.repaint();
    }

    private void clearGrid() {
        generator.clearConfiguration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        frame.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX() + ", " + mouseEvent.getY());

        cellWidth = (double)gridPanel.getWidth() / generator.getColumns();
        cellHeight = (double)gridPanel.getHeight() / generator.getRows();

        column = Math.min(generator.getColumns() - 1, (int)(mouseEvent.getX() / cellWidth));
        row = Math.min(generator.getRows() - 1, (int)(mouseEvent.getY() / cellHeight));

        System.out.println(row + ", " + column);

        if(generator.getState(row, column) == 0) {
            generator.setState(row, column, 1);
        }
        else if(generator.getState(row, column) == 1) {
            generator.setState(row, column,0);
        }

        frame.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == stepBtn) {
            generateAndDraw();
        }
        else if(actionEvent.getSource() == startBtn) {
            if(runStatus == false) {
                runStatus = true;
                Thread t = new Thread(this);
                t.start();
            }
        }
        else if(actionEvent.getSource() == stopBtn) {
            runStatus = false;
        }
        else if(actionEvent.getSource() == randomBtn) {
            randomInitialCondition();
        }
        else if(actionEvent.getSource() == clearBtn) {
            clearGrid();
        }
    }

    @Override
    public void run() {
        while(runStatus == true) {
            generateAndDraw();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
