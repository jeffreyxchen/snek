import java.awt.*;

public class SnakeObj extends GameObj {
    public static final int SIZE = 10;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private Color color;
    private int previousX;
    private int previousY;
    private Direction direction;

    /**
    * Note that, because we don't need to do anything special when constructing a Square, we simply
    * use the superclass constructor called with the correct parameters.
    */
    public SnakeObj(int courtWidth, int courtHeight, int initX, int initY, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, initX, initY, SIZE, SIZE, courtWidth, courtHeight);
        this.direction = Direction.NONE;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
    
    @Override
    public void move() {
        setPrevPos();
        
        super.move();
    }
    
    public void moveBody(Direction dir, int speed) {        
        switch (dir) {
            case UP:
                setDirection(dir);
                setPrevPos();
                setVx(0);
                setVy(-speed);
                break;
            case DOWN:
                setDirection(dir);
                setPrevPos();
                setVx(0);
                setVy(speed);
                break;
            case LEFT:
                setDirection(dir);
                setPrevPos();
                setVx(-speed);
                setVy(0);
                break;
            case RIGHT:
                setDirection(dir);
                setPrevPos();
                setVx(speed);
                setVy(0);
                break;
            case NONE:
                setVx(0);
                setVy(0);
        }
        super.move();
    }
    
    public void moveBodyPrev(int px, int py, Direction dir) {
    		this.previousX = this.getPx();
    		this.previousY = this.getPy();
    		
    		setDirection(dir);
    		
    		setPx(px);
    		setPy(py);
    }
    
    public int getPreviousX() {
        return this.previousX;
    }
    
    public int getPreviousY() {
        return this.previousY;
    }
    
    public void setPrevPos() {
		this.previousX = this.getPx();
		this.previousY = this.getPy();
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void setDirection(Direction dir) {
        this.direction = dir;
    }
    
}