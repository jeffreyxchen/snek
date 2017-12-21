import java.awt.*;

public class SnakeHead extends SnakeObj {

    private Color color;
    private int previousX;
    private int previousY;

    /**
    * Note that, because we don't need to do anything special when constructing a Square, we simply
    * use the superclass constructor called with the correct parameters.
    */
    public SnakeHead(int courtWidth, int courtHeight, int initX, int initY, Color color) {
        super(courtWidth, courtHeight, initX, initY, color);

        this.color = color;
        this.previousX = initX;
        this.previousY = initY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
    
    @Override
    public void move() {
        super.move();
    }
}