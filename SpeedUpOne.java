import java.awt.Color;
import java.util.List;

public class SpeedUpOne extends SpeedUp{
	private static final Color SPEEDUPONE_COLOR = Color.GREEN;
	private final int NEW_VEL = 12;
	
	public SpeedUpOne (int px, int py) {
		super (px, py, SPEEDUPONE_COLOR);
	}
	
	@Override
	public void effectWASD(List<SnakeObj> snake) {
		GameCourt.WASD_VELOCITY = NEW_VEL;
	}
	
	@Override
	public void effectArrows(List<SnakeObj> snake) {
		GameCourt.ARROWS_VELOCITY = NEW_VEL;
	}
}