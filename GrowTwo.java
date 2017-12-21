import java.awt.Color;
import java.util.List;

public class GrowTwo extends Grow{
	private static final Color GROWTWO_COLOR = Color.RED;
	private final int GROW_AMOUNT = 6;
	
	public GrowTwo (int px, int py) {
		super (px, py, GROWTWO_COLOR);
	}
	
	@Override
	public void effectWASD(List<SnakeObj> snake) {
		GameCourt.wasdScore += 5;
		for (int i = 0; i < GROW_AMOUNT; i++) {
			snake.add(new SnakeBody(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 1000, 0, Color.RED));
		}
		super.effectWASD(snake);
	}
	
	@Override
	public void effectArrows(List<SnakeObj> snake) {
		GameCourt.arrowsScore += 5;
		for (int i = 0; i < GROW_AMOUNT; i++) {
			snake.add(new SnakeBody(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 1000, 0, Color.BLUE));
		}
		super.effectArrows(snake);
	}
}