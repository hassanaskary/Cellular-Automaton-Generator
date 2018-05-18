package generator;

import java.util.Random;

public class Generator {
    private Cell[][] grid;
    
    private final int ROWS = 20;
    private final int COLUMNS = 20;
    
    private int neighbours;
    
    Random rand = new Random();
    
    /*
     * This is the default constructor of Generator class.
     * It initializes the grid with random states of either 0 or 1.
     * The edges of the grid are ignored.
     */
    
    Generator() {
    	grid = new Cell[ROWS][COLUMNS];
    	
    	for(int i = 0; i < ROWS; i++) {
    		for(int j = 0; j < COLUMNS; j++) {
    			grid[i][j] = new Cell(i, j, rand.nextInt(2));
				System.out.println("Generator Initialized! " + grid[i][j].getState());
    		}
    	}
    	computeGeneration();
    }
    
    /*
     * This method computes the next generation of the grid
     * by applying the rules of Conway's Game of Life on the Moore
     * neighbourhood.
     * 
     * This method iterates through the grid and for each cell
     * counts the number of active numbers neighbours. Then it 
     * it sets value of the nextState variable of the Cell class
     * to the appropriate value of either alive (1) or dead (0) by
     * applying the rules of Life using an if-else block.
     * 
     * Lastly, this method calls another method that transfers or copies
     * the value of NextState variable of the Cell class to the state 
     * variable of the Cell class. The state variable will be used to draw
     * the automaton.
     */
    
    public void computeGeneration() {
    	neighbours = 0;
		System.out.println("INSIDE COMPUTE GENERATION!!");
    	for(int i = 1; i < ROWS-1; i++) {
    		for(int j = 1; j < COLUMNS-1; j++) {
				System.out.println("Enterting loop!");
    			for(int m = -1; m <= 1; m++) {
    				for(int n = -1; n <= 1; n++) {
						System.out.println("Calculating neighbours!");
    					neighbours += grid[i+m][j+n].getState();
						System.out.println("Neighbours! " + neighbours);
    				}
    			}
    			
    			neighbours = neighbours - grid[i][j].getState();
				System.out.println("Neighbours again " + neighbours);
    			
    			if(grid[i][j].getState() == 1 && neighbours < 2) {
					System.out.println("DEATH LONELINESS");
    				grid[i][j].setNextState(0);
					System.out.println(grid[i][j].getNextState());
    			}
    			else if(grid[i][j].getState() == 1 && neighbours > 3) {
					System.out.println("DEATH OVERPOPULATION");
    				grid[i][j].setNextState(0);
					System.out.println(grid[i][j].getNextState());
    			}
    			else if(grid[i][j].getState() == 0 && neighbours == 3) {
					System.out.println("BIRTH");
    				grid[i][j].setNextState(1);
					System.out.println(grid[i][j].getNextState());
    			}
    			else {
					System.out.println("STASIS");
    				grid[i][j].setNextState(grid[i][j].getState());
					System.out.println(grid[i][j].getNextState());
    			}

    			neighbours = 0;
    		}
    	}

		transferStates();
    }

    /*
     * This method copies the value of nextState variable of Cell class
     * into State variable of Cell class. The drawing of the automaton will
     * be done using the State variable.
     */
    
    private void transferStates() {
		System.out.println("INSIDE TRANSFER STATES");
    	for(int i = 1; i < ROWS-1; i++) {
    		for(int j = 1; j < COLUMNS-1; j++) {
				System.out.println("INSIDE TRANSFER LOOP");
    			grid[i][j].setState(grid[i][j].getNextState());
				System.out.println(grid[i][j].getState());
    		}
    	}
    }
    
    public int getGeneration(int i, int j) {
		System.out.println("Getting Generation! " + grid[i][j].getState());
    	return grid[i][j].getState();
	}
	
	public int getRows() {
    	return this.ROWS;
	}
	public int getColumns() {
    	return this.COLUMNS;
	}
}
