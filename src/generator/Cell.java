package generator;

public class Cell {
    private final int WIDTH = 10;
    private int x;
    private int y;

    private int state;
    private int previousState;

    int getX() {
        return this.x;
    }
    int getY() {
        return this.y;
    }
    int getState() {
        return this.state;
    }
    int getPreviousState() {
        return this.previousState;
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
    void setPreviousState(int previousState) {
        this.previousState = previousState;
    }
}
