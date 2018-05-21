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

        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                cells[i][j] = new Cell(i, j, 0);
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
        population = 0;

        for(int row = 1; row < ROWS-1; row++) {
            for(int column = 1; column < COLUMNS-1; column++) {
                cells[row][column].setState(cells[row][column].getNextState());

                if(cells[row][column].getState() == 1) {
                    population++;
                }
            }
        }
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

                if(cells[row][column].getState() == 1) {
                    population++;
                }
            }
        }
    }
}
