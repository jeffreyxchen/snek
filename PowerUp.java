import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public abstract class PowerUp {
    private int px;
    private int py;
    private int width;
    private int height;
    private Color color;
    
    public PowerUp(int px, int py, int width, int height, Color color) {
    		this.px = px;
        this.py = py;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    // Getters
    public int getPx() {
    		return this.px;
    }
    
    public int getPy() {
    		return this.py;
    }
    
    public int getWidth() {
    		return this.width;
    }
    
    public int getHeight() {
    		return this.height;
    }
    
    public Color getColor() {
    		return this.color;
    }
    
    // Setters
    public void setPx(int px) {
    		this.px = px;
    }
    
    public void setPy(int py) {
    		this.py = py;
    }
    
    // Effect    
    public abstract void effectArrows(List<SnakeObj> snake);
    
    public abstract void effectWASD(List<SnakeObj> snake);
    
    // Draw
    public abstract void draw(Graphics g);
    
    // Collision
    public boolean intersects(SnakeHead that) {
        return (this.px + this.width >= that.getPx()
            && this.py + this.height >= that.getPy()
            && that.getPx() + that.getWidth() >= this.px 
            && that.getPy() + that.getHeight() >= this.py);
    }
}