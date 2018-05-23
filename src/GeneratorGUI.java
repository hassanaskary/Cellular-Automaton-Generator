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
    JButton startBtn, stopBtn, stepBtn, randomBtn, clearBtn, aboutBtn;
    JLabel populationInfo, birthInfo, deathInfo, populationInfoValue, birthInfoValue, deathInfoValue, preset;
    boolean runStatus;
    JComboBox presetInitialCondiotions;
    String[] presets;

    GeneratorGUI() {
        frame = new JFrame("Cellular Automaton Generator");
        frame.setSize(600, 600);
        //frame.pack();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generator = new Generator();

        gridPanel = new GridPanel(generator);
        //gridPanel.setBackground(Color.white);
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
        preset = new JLabel("Preset Initial Conditions");

        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");
        stepBtn = new JButton("Step");
        randomBtn = new JButton("Random");
        clearBtn = new JButton("Clear");
        aboutBtn = new JButton("About");

        presets = new String[]{"None", "Glider", "Lightweight spaceship", "Pulsar", "Blinker"};
        presetInitialCondiotions = new JComboBox(presets);

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
        gbc.gridx = 4;
        gbc.gridy = 1;
        controlInfoPanel.add(preset, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        controlInfoPanel.add(randomBtn, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        controlInfoPanel.add(clearBtn, gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        controlInfoPanel.add(presetInitialCondiotions, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
        controlInfoPanel.add(aboutBtn, gbc);

        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        stepBtn.addActionListener(this);
        randomBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        aboutBtn.addActionListener(this);
        presetInitialCondiotions.addActionListener(this);

        frame.add(controlInfoPanel, BorderLayout.SOUTH);

        runStatus = false;

        frame.setVisible(true);
    }

    private void aboutPageInit() {
        JFrame aboutPage = new JFrame("About");
        aboutPage.setSize(500, 400);
        aboutPage.setDefaultCloseOperation(aboutPage.DISPOSE_ON_CLOSE);
        aboutPage.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        aboutPage.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridBagLayout());
        JScrollPane scrPane = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        aboutPage.add(scrPane);

        int width = aboutPage.getWidth() - 100;

        GridBagConstraints gbc1 = new GridBagConstraints();

        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(5,5,5,5);

        JLabel aboutPageHeading = new JLabel("<html><h1>Cellular Automaton Generator</h1>" +
                "<p>Made by Hassan Askary<br></br>" +
                "BS Computer and Information Sciences 2017-2021<br></br>" +
                "Department of Computer and Information Sciences<br></br>" +
                "Pakistan Institute of Engineering and Applied Sciences</p></html>");

        ImageIcon imageicon1 = new ImageIcon("Resources/Die_hard.gif");
        ImageIcon imageicon2 = new ImageIcon("Resources/Gospers_glider_gun.gif");

        String pt1 = "<html><body width ='" + width + "'><h2>Cellular Automaton</h2>";
        JLabel image1 = new JLabel(imageicon1);
        String pt12 = "<html><body width ='" + width + "'><p>A cellular automaton (plural: cellular automata, abbreviation: CA) is a discrete model studied in computer science, mathematics, physics, complexity science, theoretical biology and microstructure modeling. Cellular automata are also called cellular spaces, tessellation automata, homogeneous structures, cellular structures, tessellation structures, and iterative arrays.</p><br></br>" +
                "<p>A cellular automaton consists of a regular grid of cells, each in one of a finite number of states, such as on and off. The grid can be in any finite number of dimensions. For each cell, a set of cells called its neighborhood is defined relative to the specified cell. An initial state (time t = 0) is selected by assigning a state for each cell. A new generation is created (advancing t by 1), according to some fixed rule (generally, a mathematical function) that determines the new state of each cell in terms of the current state of the cell and the states of the cells in its neighborhood.</p><br></br>" +
                "<p>The concept was originally discovered in the 1940s by Stanislaw Ulam and John von Neumann while they were contemporaries at Los Alamos National Laboratory. While studied by some throughout the 1950s and 1960s, it was not until the 1970s and Conway's Game of Life, a two-dimensional cellular automaton, that interest in the subject expanded beyond academia. In the 1980s, Stephen Wolfram engaged in a systematic study of one-dimensional cellular automata, or what he calls elementary cellular automata; his research assistant Matthew Cook showed that one of these rules is Turing-complete. Wolfram published A New Kind of Science in 2002, claiming that cellular automata have applications in many fields of science. These include computer processors and cryptography.</p>";
        String pt2 = "<html><body width = '" + width + "'><h2>Conway's Game of Life</h2>";
        JLabel image2 = new JLabel(imageicon2);
        String pt3 = "<html><body width = '" + width + "'><p>The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.</p><br></br>" +
                "<p>The game is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves, or, for advanced players, by creating patterns with particular properties.</p>" +
                "<h3>Rules</h3>" +
                "<p>The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead, (or populated and unpopulated, respectively). Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:</p><br></br>" +
                "<ol>" +
                "<li>Any live cell with fewer than two live neighbors dies, as if by under population.</li>" +
                "<li>Any live cell with two or three live neighbors lives on to the next generation.</li>" +
                "<li>Any live cell with more than three live neighbors dies, as if by overpopulation.</li>" +
                "<li>Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.</li>" +
                "</ol><br></br>" +
                "<p>The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed; births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick. Each generation is a pure function of the preceding one. The rules continue to be applied repeatedly to create further generations.</p>";

        JLabel aboutPageContent1 = new JLabel(pt1);
        JLabel aboutPageContent12 = new JLabel(pt12);
        JLabel aboutPageContent2 = new JLabel(pt2);
        JLabel aboutPageContent3 = new JLabel(pt3);

        gbc1.gridx = 0;
        gbc1.gridy = 0;
        panel.add(aboutPageHeading, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 1;
        panel.add(aboutPageContent1, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 2;
        panel.add(image1, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 3;
        panel.add(aboutPageContent12, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 4;
        panel.add(aboutPageContent2, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 5;
        panel.add(image2, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 6;
        panel.add(aboutPageContent3, gbc1);

        aboutPage.setVisible(true);
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
        runStatus = false;
        frame.repaint();
    }

    private void clearGrid() {
        generator.clearConfiguration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        runStatus = false;
        frame.repaint();
    }

    private void drawGlider() {
        generator.GliderConfiguration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        runStatus = false;
        frame.repaint();
    }

    private void drawPulsar() {
        generator.PulsarConfiguration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        runStatus = false;
        frame.repaint();
    }

    private void drawLightweightSpaceship() {
        generator.LightweightSpaceshipConfiguration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        runStatus = false;
        frame.repaint();
    }

    private void drawBlinker() {
        generator.BlinkerConfiguration();
        populationInfoValue.setText(""+generator.getPopulation());
        birthInfoValue.setText(""+generator.getBirth());
        deathInfoValue.setText(""+generator.getDeath());
        runStatus = false;
        frame.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        //System.out.println(mouseEvent.getX() + ", " + mouseEvent.getY());

        cellWidth = (double)gridPanel.getWidth() / generator.getColumns();
        cellHeight = (double)gridPanel.getHeight() / generator.getRows();

        column = Math.min(generator.getColumns() - 1, (int)(mouseEvent.getX() / cellWidth));
        row = Math.min(generator.getRows() - 1, (int)(mouseEvent.getY() / cellHeight));

        //System.out.println("RowsCOlumn = "+ row + ", " + column);

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
            runStatus = false;
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
        else if(actionEvent.getSource() == aboutBtn) {
           aboutPageInit();
        }
        else if(actionEvent.getSource() == presetInitialCondiotions) {
            String selectedItem = (String) presetInitialCondiotions.getSelectedItem();
            if(selectedItem.equals("Glider")) {
                drawGlider();
                //System.out.println("JCOMBOBOX GLIDER");
            }
            else if(selectedItem.equals("Lightweight spaceship")) {
                drawLightweightSpaceship();
                //System.out.println("JCOMBOBOX LIGHTWEIGHT SPACESHIP");
            }
            else if(selectedItem.equals("Pulsar")) {
                drawPulsar();
                //System.out.println("JCOMBOBOX PULSAR");
            }
            else if(selectedItem.equals("Blinker")) {
                drawBlinker();
                //System.out.println("JCOMBOBOX BLINKER");
            }
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
