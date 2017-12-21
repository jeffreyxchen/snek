import java.awt.Color;
import java.util.*;

public class GrowOne extends Grow{
	private static final Color GROWONE_COLOR = Color.ORANGE;
	private final int GROW_AMOUNT = 3;
	
	public GrowOne (int px, int py) {
		super (px, py, GROWONE_COLOR);
	}
	
	@Override
	public void effectWASD(List<SnakeObj> snake) {
		for (int i = 0; i < GROW_AMOUNT; i++) {
			snake.add(new SnakeBody(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 1000, 0, Color.RED));
		}
		super.effectWASD(snake);
	}
	
	@Override
	public void effectArrows(List<SnakeObj> snake) {
		for (int i = 0; i < GROW_AMOUNT; i++) {
			snake.add(new SnakeBody(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 1000, 0, Color.BLUE));
		}
		super.effectArrows(snake);
	}
}