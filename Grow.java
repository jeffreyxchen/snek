import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Grow extends PowerUp {
	private static final int GROW_WIDTH = 10;
	private static final int GROW_HEIGHT = 10;
	
	public Grow (int px, int py, Color color) {
		super (px, py, GROW_WIDTH, GROW_HEIGHT, color);
	}
	
	@Override
	public void effectWASD(List<SnakeObj> snake) {
		GameCourt.wasdScore += 5;
		GameCourt.growPresent = false;
	}
	
	@Override
	public void effectArrows(List<SnakeObj> snake) {
		GameCourt.arrowsScore += 5;
		GameCourt.growPresent = false;
	}
	
	@Override
	public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}