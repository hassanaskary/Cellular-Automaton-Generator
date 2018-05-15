package generator;

import java.util.Random;

public class Generator {
    Cell[][] grid;
    Cell[][] nextGrid;
    
    private int rows;
    private int columns;
    
    private int neighbours;
    
    Random rand = new Random();
    
    Generator() {
    	rows = 50;
    	columns = 50;
    	grid = new Cell[rows][columns];
    	nextGrid = new Cell[rows][columns];
    	
    	for(int i = 1; i < rows-1; i++) {
    		for(int j = 1; j < columns-1; j++) {
    			grid[i][j] = new Cell(i, j, rand.nextInt(1));
    		}
    	}
    	
    	applyRuleSet();
    }
    
    void applyRuleSet() {
    	for(int i = 1; i < rows-1; i++) {
    		for(int j = 1; j < columns-1; j++) {
    			
    			for(int m = -1; m <= 1; m++) {
    				for(int n = -1; n <= 1; n++) {
    					neighbours += grid[i+m][j+n].getState();
    				}
    			}
    			
    			neighbours = neighbours - grid[i][j].getState();
    			
    		}
    	}
    }
}
