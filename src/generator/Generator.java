package generator;

import java.util.Random;

public class Generator {
    Cell[][] grid;
    
    private int rows;
    private int columns;
    
    private int neighbours;
    
    Random rand = new Random();
    
    /*
     * This is the default constructor of Generator class.
     * It initializes the grid with random states of either 0 or 1.
     * The edges of the grid are ignored.
     */
    
    Generator() {
    	rows = 100;
    	columns = 100;
    	grid = new Cell[rows][columns];
    	
    	for(int i = 1; i < rows-1; i++) {
    		for(int j = 1; j < columns-1; j++) {
    			grid[i][j] = new Cell(i, j, rand.nextInt(1));
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
    
    void computeGeneration() {
    	for(int i = 1; i < rows-1; i++) {
    		for(int j = 1; j < columns-1; j++) {
    			
    			for(int m = -1; m <= 1; m++) {
    				for(int n = -1; n <= 1; n++) {
    					neighbours += grid[i+m][j+n].getState();
    				}
    			}
    			
    			neighbours = neighbours - grid[i][j].getState();
    			
    			if(grid[i][j].getState() == 1 && neighbours < 2) {
    				grid[i][j].setNextState(0);
    			}
    			else if(grid[i][j].getState() == 1 && neighbours > 3) {
    				grid[i][j].setNextState(0);
    			}
    			else if(grid[i][j].getState() == 0 && neighbours == 3) {
    				grid[i][j].setNextState(1);
    			}
    			else {
    				grid[i][j].setNextState(grid[i][j].getState());
    			}
    			
    			transferStates();
    		}
    	}
    }

    /*
     * This method copies the value of nextState variable of Cell class
     * into State variable of Cell class. The drawing of the automaton will
     * be done using the State variable.
     */
    
    void transferStates() {
    	for(int i = 1; i < rows-1; i++) {
    		for(int j = 1; j < columns-1; j++) {
    			grid[i][j].setState(grid[i][j].getNextState());
    		}
    	}
    }
}
