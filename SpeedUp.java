import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class SpeedUp extends PowerUp {
	
	private static final int SPEEDUP_WIDTH = 10;
	private static final int SPEEDUP_HEIGHT = 10;
	
	public SpeedUp (int px, int py, Color color) {
		super (px, py, SPEEDUP_WIDTH, SPEEDUP_HEIGHT, color);
	}
	
	public void resetEffect() {
		GameCourt.WASD_INTERVAL = GameCourt.SNAKE_INTERVAL;
		GameCourt.ARROW_INTERVAL = GameCourt.SNAKE_INTERVAL;
	}
	
	@Override
	public void effectWASD(List<SnakeObj> snake) {
		GameCourt.WASD_VELOCITY = 10;
	}
	
	@Override
	public void effectArrows(List<SnakeObj> snake) {
		GameCourt.ARROWS_VELOCITY = 10;
	}
	
	@Override
	public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
