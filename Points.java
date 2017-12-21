import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Points extends PowerUp {
	private static final int POINTS_WIDTH = 15;
	private static final int POINTS_HEIGHT = 15;
	
	public Points (int px, int py) {
		super (px, py, POINTS_WIDTH, POINTS_HEIGHT, Color.YELLOW);
	}
	
	@Override
	public void effectWASD(List<SnakeObj> snake) {
		GameCourt.wasdScore += 25;
	}
	
	@Override
	public void effectArrows(List<SnakeObj> snake) {
		GameCourt.arrowsScore += 25;
	}
	
	@Override
	public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}