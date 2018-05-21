import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    Generator generator;
    double cellWidth;
    double cellHeight;

    GridPanel(Generator in) {
        this.generator = in;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        cellWidth = (double)this.getWidth() / generator.getColumns();
        cellHeight = (double)this.getHeight() / generator.getRows();

        System.out.println("width "+generator.getColumns());
        System.out.println("height "+generator.getRows());
        System.out.println("GridPanel width "+this.getWidth());
        System.out.println("GridPanel height "+this.getHeight());


        /*
         * columns or vertical lines is cells[0].length it is with reference to x coordinate
         * rows or horizontal lines is cells.length it is with reference to y coordinate
         */

        g.setColor(Color.BLACK);

        for(int row = 0; row < generator.getRows(); row++) {
            for(int column = 0; column < generator.getColumns(); column++) {

                if(generator.getState(row, column) == 1) {
                    g.fillRect((int)Math.round(column * cellWidth), (int)Math.round(row * cellHeight), (int)cellWidth, (int)cellHeight);
                }
            }
        }

        g.setColor(Color.lightGray);

        // Vertical line drawing
        for(int x = 0; x < generator.getColumns() + 1; x++) {
            g.drawLine((int)Math.round(x * cellWidth), 0, (int)Math.round(x * cellWidth), this.getHeight());
        }
        // Horizontal line drawing
        for(int y = 0; y < generator.getRows() + 1; y++) {
            g.drawLine(0,(int)Math.round(y * cellHeight), this.getWidth(), (int)Math.round(y * cellHeight));
        }
    }
}
