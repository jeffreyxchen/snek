import static org.junit.Assert.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import org.junit.Test;

public class GameTest {

	private SnakeHead snakeHeadWASD = new SnakeHead(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 60, 160, Color.RED);
    private SnakeHead snakeHeadArrows = new SnakeHead(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 430, 160, Color.BLUE);
    
	private List<SnakeObj> snakeWASD = new LinkedList<SnakeObj>(Arrays.asList(snakeHeadWASD));
    private List<SnakeObj> snakeArrows = new LinkedList<SnakeObj>(Arrays.asList(snakeHeadArrows));
    
    private Grow growOne = new GrowOne(0, 0);
	private Grow growTwo = new GrowTwo(0, 0);
	
	private SpeedUp speedOne = new SpeedUpOne(0, 0);
	private SpeedUp speedTwo = new SpeedUpTwo(0, 0);
	
	private Points points = new Points(0, 0);
	
	public void resetGlobals() {
		GameCourt.WASD_VELOCITY = GameCourt.SNAKE_ORIGINAL_VELOCITY;
        GameCourt.ARROWS_VELOCITY = GameCourt.SNAKE_ORIGINAL_VELOCITY;
        GameCourt.arrowsScore = 0;
        GameCourt.wasdScore = 0;
	}
	
	@Test
	public void testSnakeBegin() {
		assertEquals("WASD starts with head", 1, snakeWASD.size());
		assertEquals("Arrows starts with head", 1, snakeArrows.size());
	}
	
	@Test
	public void testWASDSnakeGrow() {
		growOne.effectWASD(snakeWASD);
		assertEquals("WASD GrowOne (1)", snakeWASD.size(), 4);
		
		growTwo.effectWASD(snakeWASD);
		assertEquals("WASD GrowTwo (2)", snakeWASD.size(), 10);
		
		resetGlobals();
	}
	
	@Test
	public void testArrowsSnakeGrow() {
		growTwo.effectArrows(snakeArrows);
		assertEquals("Arrows GrowTwo (1)", snakeArrows.size(), 7);
		
		growOne.effectArrows(snakeArrows);
		assertEquals("Arrows GrowOne (2)", snakeArrows.size(), 10);
		
		resetGlobals();
	}
	
	@Test
	public void testSnakeGrowthIndependent() {
		growOne.effectWASD(snakeWASD);
		assertEquals("WASD GrowOne normal", snakeWASD.size(), 4);
		assertEquals("Arrows size unaffected", snakeArrows.size(), 1);
		
		growTwo.effectArrows(snakeArrows);
		assertEquals("WASD size unaffected", snakeWASD.size(), 4);
		assertEquals("Arrows GrowTwo normal", snakeArrows.size(), 7);
		
		resetGlobals();
	}
	
	@Test
	public void testWASDSnakeSpeed() {	
		speedOne.effectWASD(snakeWASD);
		assertEquals("WASD SpeedOne", GameCourt.WASD_VELOCITY, 12);
		
		speedTwo.effectWASD(snakeWASD);
		assertEquals("WASD SpeedTwo", GameCourt.WASD_VELOCITY, 15);
		
		resetGlobals();
	}
		
	@Test
	public void testArrowsSnakeSpeed() {
		speedOne.effectArrows(snakeArrows);
		assertEquals("Arrows SpeedOne", GameCourt.ARROWS_VELOCITY, 12);
		
		speedTwo.effectArrows(snakeArrows);
		assertEquals("Arrows SpeedTwo", GameCourt.ARROWS_VELOCITY, 15);
		
		speedOne.effectArrows(snakeArrows);
		assertEquals("Arrows SpeedOne again reverts speed", GameCourt.ARROWS_VELOCITY, 12);
		
		resetGlobals();
	}
	
	@Test
	public void testSnakeSpeedIndependent() {
		speedOne.effectWASD(snakeWASD);
		assertEquals("WASD speed normal", GameCourt.WASD_VELOCITY, 12);
		assertEquals("Arrows speed unaffected", GameCourt.ARROWS_VELOCITY, 10);
		
		speedTwo.effectArrows(snakeArrows);
		assertEquals("WASD size unaffected", GameCourt.WASD_VELOCITY, 12);
		assertEquals("Arrows GrowTwo normal", GameCourt.ARROWS_VELOCITY, 15);
		
		resetGlobals();
	}
	
	@Test
	public void testWASDSnakeScore() {
		points.effectWASD(snakeWASD);
		assertEquals("WASD points total = 25", GameCourt.wasdScore, 25);
		
		growOne.effectWASD(snakeWASD);
		assertEquals("WASD points total = 30", GameCourt.wasdScore, 30);
		
		growTwo.effectWASD(snakeWASD);
		assertEquals("WASD points total = 40", GameCourt.wasdScore, 40);
		
		resetGlobals();
	}
	
	@Test
	public void testArrowsSnakeScore() {
		growOne.effectArrows(snakeArrows);
		assertEquals("Arrows points total = 5", GameCourt.arrowsScore, 5);
		
		points.effectArrows(snakeArrows);
		assertEquals("Arrows points total = 30", GameCourt.arrowsScore, 30);
		
		growTwo.effectArrows(snakeArrows);
		assertEquals("Arrows points total = 40", GameCourt.arrowsScore, 40);
		
		resetGlobals();
	}
	
	@Test
	public void testSnakeScoreIndependent() {
		growOne.effectArrows(snakeArrows);
		points.effectArrows(snakeArrows);
		growTwo.effectWASD(snakeWASD);
		
		assertEquals("Arrows points = 30", GameCourt.arrowsScore, 30);
		assertEquals("WASD points = 10", GameCourt.wasdScore, 10);
        
        resetGlobals();
	}
}
