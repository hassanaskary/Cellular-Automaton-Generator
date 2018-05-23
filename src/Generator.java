import java.util.Random;

public class Generator {
    Cell[][] cells;

    private final int ROWS = 30;
    private final int COLUMNS = ROWS;

    private int neighbours, population, birth, death;

    Random rand = new Random();

    Generator() {
        cells = new Cell[ROWS][COLUMNS];

        neighbours = population = birth = death = 0;

        for(int row = 0; row < ROWS; row++) {
            for(int column = 0; column < COLUMNS; column++) {
                cells[row][column] = new Cell(row, column, 0);
            }
        }
    }

    public void computeGeneration() {
        neighbours = 0;

        for(int row = 1; row < ROWS - 1; row++) {
            for(int column = 1; column < COLUMNS - 1; column++) {

                for(int m = -1; m <= 1; m++) {
                    for(int n = -1; n <= 1; n++) {
                        neighbours += cells[row+m][column+n].getState();
                    }
                }

                neighbours -= cells[row][column].getState();

                if(cells[row][column].getState() == 1 && neighbours < 2) {
                    cells[row][column].setNextState(0);
                    death++;
                }
                else if(cells[row][column].getState() == 1 && neighbours > 3) {
                    cells[row][column].setNextState(0);
                    death++;
                }
                else if(cells[row][column].getState() == 0 && neighbours == 3) {
                    cells[row][column].setNextState(1);
                    birth++;
                }
                else {
                    cells[row][column].setNextState(cells[row][column].getState());
                }

                neighbours = 0;
            }
        }

        transferStates();
    }

    private void transferStates() {
        for(int row = 1; row < ROWS-1; row++) {
            for(int column = 1; column < COLUMNS-1; column++) {
                cells[row][column].setState(cells[row][column].getNextState());
            }
        }

        countPopulation();
    }

    public int getState(int row, int column) {
        return cells[row][column].getState();
    }
    public void setState(int row, int column, int state) {
        this.cells[row][column].setState(state);
    }

    public int getRows() {
        return this.ROWS;
    }
    public int getColumns() {
        return this.COLUMNS;
    }

    public int getPopulation() { return this.population; }
    public int getBirth() { return this.birth; }
    public int getDeath() { return this.death; }

    public void randomConfiguration() {
        for(int row = 1; row < ROWS-1; row++) {
            for(int column = 1; column < COLUMNS-1; column++) {
                cells[row][column].setState(rand.nextInt(2));
            }
        }

        countPopulation();
    }

    public void clearConfiguration() {
        for(int row = 0; row < ROWS; row++) {
            for(int column = 0; column < COLUMNS; column++) {
                cells[row][column].setState(0);
            }
        }

        birth = 0;
        death = 0;
        countPopulation();
    }

    public void GliderConfiguration() {
        clearConfiguration();

        cells[10][13].setState(1);
        cells[11][13].setState(1);
        cells[12][13].setState(1);
        cells[12][12].setState(1);
        cells[11][11].setState(1);

        countPopulation();
    }

    public void PulsarConfiguration() {
        clearConfiguration();

        cells[8][10].setState(1);
        cells[9][10].setState(1);
        cells[10][10].setState(1);
        cells[11][9].setState(1);
        cells[11][8].setState(1);
        cells[11][7].setState(1);
        cells[10][5].setState(1);
        cells[9][5].setState(1);
        cells[8][5].setState(1);
        cells[6][9].setState(1);
        cells[6][8].setState(1);
        cells[6][7].setState(1);
        cells[13][7].setState(1);
        cells[13][8].setState(1);
        cells[13][9].setState(1);
        cells[14][10].setState(1);
        cells[15][10].setState(1);
        cells[16][10].setState(1);
        cells[18][9].setState(1);
        cells[18][8].setState(1);
        cells[18][7].setState(1);
        cells[14][5].setState(1);
        cells[15][5].setState(1);
        cells[16][5].setState(1);
        cells[14][12].setState(1);
        cells[15][12].setState(1);
        cells[16][12].setState(1);
        cells[13][13].setState(1);
        cells[13][14].setState(1);
        cells[13][15].setState(1);
        cells[18][13].setState(1);
        cells[18][14].setState(1);
        cells[18][15].setState(1);
        cells[14][17].setState(1);
        cells[15][17].setState(1);
        cells[16][17].setState(1);
        cells[11][13].setState(1);
        cells[11][14].setState(1);
        cells[11][15].setState(1);
        cells[10][12].setState(1);
        cells[9][12].setState(1);
        cells[8][12].setState(1);
        cells[6][13].setState(1);
        cells[6][14].setState(1);
        cells[6][15].setState(1);
        cells[10][17].setState(1);
        cells[9][17].setState(1);
        cells[8][17].setState(1);

        countPopulation();
    }

    public void LightweightSpaceshipConfiguration() {
        clearConfiguration();

        cells[10][11].setState(1);
        cells[11][11].setState(1);
        cells[12][11].setState(1);
        cells[12][12].setState(1);
        cells[9][12].setState(1);
        cells[9][15].setState(1);
        cells[11][15].setState(1);
        cells[12][13].setState(1);
        cells[12][14].setState(1);

        countPopulation();
    }

    public void BlinkerConfiguration() {
        clearConfiguration();

        cells[9][13].setState(1);
        cells[10][13].setState(1);
        cells[11][13].setState(1);

        countPopulation();
    }

    private void countPopulation() {
        population = 0;
        for(int row = 0; row < ROWS; row++) {
            for(int column = 0; column < COLUMNS; column++) {
                if(cells[row][column].getState() == 1) {
                    population++;
                }
            }
        }
    }
}
