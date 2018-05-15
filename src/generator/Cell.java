package generator;

public class Cell {
    // private final int WIDTH = 10;
    private int x;
    private int y;

    private int state;
    private int nextState;
    
    Cell(int x, int y, int state) {
    	this.x = x;
    	this.y = y;
    	this.state = state;
    	this.nextState = 0;
    }
    
    Cell() {
    	this.x = 0;
    	this.y = 0;
    	this.state = 0;
    	this.nextState = 0;
    }

    int getX() {
        return this.x;
    }
    int getY() {
        return this.y;
    }
    int getState() {
        return this.state;
    }
    int getNextState() {
        return this.nextState;
    }

    void setX(int x) {
        this.x = x;
    }
    void setY(int y) {
        this.y = y;
    }
    void setState(int state) {
        this.state = state;
    }
    void setNextState(int nextState) {
        this.nextState = nextState;
    }
}
