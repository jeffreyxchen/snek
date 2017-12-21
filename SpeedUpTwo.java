import java.awt.Color;
import java.util.List;

public class SpeedUpTwo extends SpeedUp{
	private static final Color SPEEDUPTWO_COLOR = Color.CYAN;
	private final int NEW_VEL = 15;
	
	public SpeedUpTwo (int px, int py) {
		super (px, py, SPEEDUPTWO_COLOR);
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