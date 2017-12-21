import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	
    // the state of the game logic
    public boolean playing = false; // whether the game is running 
    public static boolean growPresent = false; // whether or not a grow power up is on the map
    public static String winner;
    public static int arrowsScore;
    public static int wasdScore;
    private JLabel status;

    // Game constants
    public static final int COURT_WIDTH = 500;
    public static final int COURT_HEIGHT = 320;
    public static final int SNAKE_ORIGINAL_VELOCITY = 10;
    
    // Universal Values
    public static int WASD_VELOCITY;
    public static int ARROWS_VELOCITY;
    
    // Snake Stuff
    public SnakeHead snakeHeadWASD;
    public SnakeHead snakeHeadArrows;
    
    public SnakeBody snakeBodyWASD1;
    public SnakeBody snakeBodyWASD2;
    public SnakeBody snakeBodyWASD3;
    public SnakeBody snakeBodyWASD4;
    public SnakeBody snakeBodyWASD5;
    public SnakeBody snakeBodyWASD6;
    
    public SnakeBody snakeBodyArrows1;
    public SnakeBody snakeBodyArrows2;
    public SnakeBody snakeBodyArrows3;
    public SnakeBody snakeBodyArrows4;
    public SnakeBody snakeBodyArrows5;
    public SnakeBody snakeBodyArrows6;
    
    public static List<SnakeObj> snakeWASD;
    public static List<SnakeObj> snakeArrows;
    
    // PowerUps
    private PowerUp[][] powerUpMap;
    
    // Values for Edge Detection
    public int onEdgeWASD = 0;
    public int onEdgeArrows = 0;
    
    // Use "P" to pause and unpause the game
    public boolean pause = true;
        
    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 75;
    public static final int SNAKE_INTERVAL = 75;
    public static int WASD_INTERVAL = SNAKE_INTERVAL;
    public static int ARROW_INTERVAL = SNAKE_INTERVAL;
    public static int GAME_TIME;
    public int POWERUP_INTERVAL = 5000;

    public GameCourt(JLabel status) {
    		setBackground(Color.BLACK);
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
        
        // Arrows Snake Actions
        Timer arrowTimer = new Timer(ARROW_INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arrowTick();
            }
        });
        arrowTimer.start();
        
        // WASD Snake Actions
        Timer wasdTimer = new Timer(WASD_INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wasdTick();
            }
        });
        wasdTimer.start();
        
        // Power Up Timer
        Timer powerUpTimer = new Timer(POWERUP_INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                powerUpTick();
            }
        });
        powerUpTimer.start();
        
        Timer gameTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		if (GAME_TIME > 0) {
            			GAME_TIME--;
            		}
            }
        });
        gameTimer.start();

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // ARROW KEYS
                if (e.getKeyCode() == KeyEvent.VK_LEFT && snakeHeadArrows.getVx() == 0) {
                		snakeHeadArrows.setDirection(Direction.LEFT);
                    snakeHeadArrows.setVx(-ARROWS_VELOCITY);
                    snakeHeadArrows.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snakeHeadArrows.getVx() == 0) {
                		snakeHeadArrows.setDirection(Direction.RIGHT);
                    snakeHeadArrows.setVx(ARROWS_VELOCITY);
                    snakeHeadArrows.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snakeHeadArrows.getVy() == 0) {
                		snakeHeadArrows.setDirection(Direction.DOWN);
                    snakeHeadArrows.setVx(0);
                    snakeHeadArrows.setVy(ARROWS_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP && snakeHeadArrows.getVy() == 0) {
                	snakeHeadArrows.setDirection(Direction.UP);
                    snakeHeadArrows.setVx(0);
                    snakeHeadArrows.setVy(-ARROWS_VELOCITY);
                } 
                
                // WASD KEYS
                else if (e.getKeyCode() == KeyEvent.VK_W && snakeHeadWASD.getVy() == 0) {
                    snakeHeadWASD.setDirection(Direction.UP);
                    snakeHeadWASD.setVx(0);
                    snakeHeadWASD.setVy(-WASD_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_A && snakeHeadWASD.getVx() == 0) {
                    snakeHeadWASD.setDirection(Direction.LEFT);
                    snakeHeadWASD.setVx(-WASD_VELOCITY);
                    snakeHeadWASD.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_S && snakeHeadWASD.getVy() == 0) {
                    snakeHeadWASD.setDirection(Direction.DOWN);
                    snakeHeadWASD.setVx(0);
                    snakeHeadWASD.setVy(WASD_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_D && snakeHeadWASD.getVx() == 0) {
                    snakeHeadWASD.setDirection(Direction.RIGHT);
                    snakeHeadWASD.setVx(WASD_VELOCITY);
                    snakeHeadWASD.setVy(0);
                }
                
                // PAUSE
                else if (e.getKeyCode() == KeyEvent.VK_P) {
                    pause = !pause;
                }
                
                // Add to Snake Arrows
                else if (e.getKeyCode() == KeyEvent.VK_J) {
                		snakeArrows.add(new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 1000, 0, Color.BLUE));
                } else if (e.getKeyCode() == KeyEvent.VK_K) {
                		snakeWASD.add(new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 1000, 0, Color.RED));
                }
            }

            public void keyReleased(KeyEvent e) {
            	
            }
        });

        this.status = status;
    }

    public void reset() {
        // Reset Values
        WASD_VELOCITY = SNAKE_ORIGINAL_VELOCITY;
        ARROWS_VELOCITY = SNAKE_ORIGINAL_VELOCITY;
        GAME_TIME = 60;
        growPresent = false;
        arrowsScore = 0;
        wasdScore = 0;
        
        // Clean Map of PowerUps
        powerUpMap = new PowerUp[COURT_HEIGHT + 10][COURT_WIDTH + 10];
        
        // Initialize WASD and Arrows Snake Heads
        snakeHeadWASD = new SnakeHead(COURT_WIDTH, COURT_HEIGHT, 60, 160, Color.RED);
        snakeHeadArrows = new SnakeHead(COURT_WIDTH, COURT_HEIGHT, 430, 160, Color.BLUE);
        
        // Here are snake bodies
        snakeBodyWASD1 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 50, 160, Color.RED);
        snakeBodyWASD2 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 40, 160, Color.RED);
        snakeBodyWASD3 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 30, 160, Color.RED);
        snakeBodyWASD4 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 20, 160, Color.RED);
        snakeBodyWASD5 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 10, 160, Color.RED);
        snakeBodyWASD6 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 0, 160, Color.RED);
        
        snakeBodyArrows1 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 440, 160, Color.BLUE);
        snakeBodyArrows2 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 450, 160, Color.BLUE);
        snakeBodyArrows3 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 460, 160, Color.BLUE);
        snakeBodyArrows4 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 470, 160, Color.BLUE);
        snakeBodyArrows5 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 480, 160, Color.BLUE);
        snakeBodyArrows6 = new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 490, 160, Color.BLUE);
        
        // Initialize WASD and Arrows Snakes as LinkedLists
        snakeWASD = new LinkedList<SnakeObj>(Arrays.asList(snakeHeadWASD, snakeBodyWASD1, 
        													  snakeBodyWASD2, snakeBodyWASD3, 
        													  snakeBodyWASD4, snakeBodyWASD5, 
        													  snakeBodyWASD6));
        snakeArrows = new LinkedList<SnakeObj>(Arrays.asList(snakeHeadArrows, snakeBodyArrows1, 
        														snakeBodyArrows2, snakeBodyArrows3, 
        														snakeBodyArrows4, snakeBodyArrows5, 
        														snakeBodyArrows6));
        
        // Initialize WASD Snake State
        snakeHeadWASD.setDirection(Direction.RIGHT);
        snakeHeadWASD.setVx(WASD_VELOCITY);
        
        // Initialize Arrows Snake State
        snakeHeadArrows.setDirection(Direction.LEFT);
        snakeHeadArrows.setVx(-ARROWS_VELOCITY);
        
        // Play
        playing = true;
        status.setText(" ");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }


    void tick() {
        if (playing && pause) {
            	if (!growPresent) {
        			Random r = new Random();
        			int row = r.nextInt(COURT_HEIGHT - 10);
        			int col = r.nextInt(COURT_WIDTH - 10);
        			int decider = r.nextInt(5);
        			PowerUp newGrow;
        			
        			if (decider < 4) {
        				newGrow = new GrowOne(col, row);
        			} else {
        				newGrow = new GrowTwo(col, row);
        			}
        			
        			if (powerUpMap[row][col] == null) {
        				powerUpMap[row][col] = newGrow;
        			}
        			
        			growPresent = true;
        		}
            	
            	// Update the display
            repaint();
            
            	// Collisions
            checkWallCollision();
            checkVersusCollision();
            checkSnakeCollision();
            checkHeadOnCollision();
            	checkPowerUpCollision();
            	            	
            	// Check for end game logic
            	checkEnd();
            
        }
    }
    
    // Arrows Snake Actions
    void arrowTick() {
	    	ListIterator<SnakeObj> iterArrows = snakeArrows.listIterator(0);
	    	
	    	if (playing && pause) {
	    		while (iterArrows.hasNext()) {
		    		if (iterArrows.hasPrevious()) {
		    			 int prevIndex = iterArrows.previousIndex();
		             int prevX = snakeArrows.get(prevIndex).getPreviousX();
		             int prevY = snakeArrows.get(prevIndex).getPreviousY();
		             Direction prevDir = snakeArrows.get(prevIndex).getDirection();
		             iterArrows.next().moveBodyPrev(prevX, prevY, prevDir);
		        } else {
		             iterArrows.next().move();
		        }
		    }
	    	}
    }
    
    // WASD Snake Actions
    void wasdTick() {
    		ListIterator<SnakeObj> iterWASD = snakeWASD.listIterator(0);
    		
    		if (playing && pause) {
    			while (iterWASD.hasNext()) {
    	            if (iterWASD.hasPrevious()) {
    	                 int prevIndex = iterWASD.previousIndex();
    	                 int prevX = snakeWASD.get(prevIndex).getPreviousX();
    	                 int prevY = snakeWASD.get(prevIndex).getPreviousY();
    	                 Direction prevDir = snakeWASD.get(prevIndex).getDirection();
    	                 iterWASD.next().moveBodyPrev(prevX, prevY, prevDir);
    	            } else {
    	                 iterWASD.next().move();
    	            }
    	        }
    		}
    }
    
    // Power Up Appearance (Random)
    void powerUpTick() {
    		Random r = new Random();
		int row = r.nextInt(COURT_HEIGHT - 10);
		int col = r.nextInt(COURT_WIDTH - 10);
		int decider = r.nextInt(8);
		PowerUp newPower;
		
    		if (playing && pause) {
    			if (decider == 0) {
        			newPower = new SpeedUpOne(col, row);
        		} else if (decider == 1) {
        			newPower = new SpeedUpTwo(col, row);
        		} else {
        			newPower = new Points(col, row);
        		}

        		if (powerUpMap[row][col] == null) {
        			powerUpMap[row][col] = newPower;
        		}
    		}
    }
    
    public void winLogic(String s) {
    		playing = false;
    		if (s == "TIE") {
    			status.setText("YOU TWO CRASHED HEAD-ON");
    			if (wasdScore > arrowsScore) {
    				winner = "RED";
    			} else if (arrowsScore > wasdScore) {
    				winner = "BLUE";
    			} else {
    				winner = "TIE";
    			}
    		} else {
    			status.setText(s + " WINS!");
    			winner = s;
    		}
    		displayResults();
    }
    
    public void checkEnd() {
    		if (GAME_TIME == 0) {
    			playing = false;
    			if (wasdScore > arrowsScore) {
    				winner = "RED";
    			} else if (arrowsScore > wasdScore) {
    				winner = "BLUE";
    			} else {
    				winner = "TIE";
    			}
    			
    			displayResults();
    		}
    }
    
    public void displayResults() {
    		String resultsMessage = "Winner: " + winner + "\n\n" +
    								"Red Score: " + wasdScore + "\n" +
    								"Blue Score: " + arrowsScore + "\n\n" +
    								"Press OK to restart the game!";
		JOptionPane.showMessageDialog(null, resultsMessage);
		reset();
    }
    
    public static void addToWASD(int n) {
    		for (int i = 0; i < n; i++) {
			snakeWASD.add(new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 1000, 0, Color.RED));
		}
    }
    
    public static void addToArrows(int n) {
    		for (int i = 0; i < n; i++) {
			snakeArrows.add(new SnakeBody(COURT_WIDTH, COURT_HEIGHT, 1000, 0, Color.BLUE));
		}
    }
    
    public void checkWallCollision() {
    		int bodySize = snakeHeadWASD.getWidth();
        
        int wasdHeadPx = snakeHeadWASD.getPx();
        int wasdHeadVx = snakeHeadWASD.getVx();
        int wasdHeadPy = snakeHeadWASD.getPy();
        int wasdHeadVy = snakeHeadWASD.getVy();
        
        int arrowsHeadPx = snakeHeadArrows.getPx();
        int arrowsHeadVx = snakeHeadArrows.getVx();
        int arrowsHeadPy = snakeHeadArrows.getPy();
        int arrowsHeadVy = snakeHeadArrows.getVy();
        
    		// Checks edge collision for WASD Snake
        // onEdge is a number that increments to give the player a chance 
        	// to turn and move alongside the wall before actually colliding
        if (onEdgeWASD == 0) {
        		if (wasdHeadPx + wasdHeadVx == -bodySize || 
        			wasdHeadPx + wasdHeadVx == COURT_WIDTH || 
        			wasdHeadPy + wasdHeadVy == -bodySize + 20 || 
        			wasdHeadPy + wasdHeadVy == COURT_HEIGHT) {
        			winLogic("BLUE");
        		}
        		onEdgeWASD = 0;
        } else if (wasdHeadPx + wasdHeadVx == -bodySize || 
        			   wasdHeadPx + wasdHeadVx == COURT_WIDTH || 
        			   wasdHeadPy + wasdHeadVy == -bodySize + 20 || 
        			   wasdHeadPy + wasdHeadVy == COURT_HEIGHT) {
        		// onEdgeWASD++;
        } 
        // Checks edge collision for Arrows Snake
        if (onEdgeArrows == 0) {
        		if (arrowsHeadPx + arrowsHeadVx == -bodySize || 
        			arrowsHeadPx + arrowsHeadVx == COURT_WIDTH || 
        			arrowsHeadPy + arrowsHeadVy == -bodySize + 20 || 
        			arrowsHeadPy + arrowsHeadVy == COURT_HEIGHT) {
        			winLogic("RED");
        		}
        		onEdgeArrows = 0;
        } else if (arrowsHeadPx + arrowsHeadVx == -bodySize || 
        			   arrowsHeadPx + arrowsHeadVx == COURT_WIDTH || 
        			   arrowsHeadPy + arrowsHeadVy == -bodySize + 20 || 
        			   arrowsHeadPy + arrowsHeadVy == COURT_HEIGHT) {
        		// onEdgeArrows++;
        } 
        // Reset edge counters
        onEdgeWASD = 0;
        	onEdgeArrows = 0;
    }
    
    
    public void checkVersusCollision() {
    		// See if WASD Snake loses
    		for (int i = 1; i < snakeArrows.size(); i++) {
    			if (snakeHeadWASD.intersects(snakeArrows.get(i)) && !parallelMove(snakeHeadWASD, snakeArrows.get(i))) {
    				winLogic("BLUE");
        		}
    		}
    		// See if Arrows Snake loses
    		for (int i = 1; i < snakeWASD.size(); i++) {
    			if (snakeHeadArrows.intersects(snakeWASD.get(i)) && !parallelMove(snakeHeadArrows, snakeWASD.get(i))) {
    				winLogic("RED");
    			}
    		}
    }
    
    
    public void checkSnakeCollision() {
    		// See if Arrows Snake collides with itself
    		for (int i = 1; i < snakeArrows.size(); i++) {
			if (snakeHeadArrows.getPx() == snakeArrows.get(i).getPx() && snakeHeadArrows.getPy() == snakeArrows.get(i).getPy()) {
				winLogic("RED");
			}
		}
    		// See if WASD Snake collides with itself
    		for (int i = 1; i < snakeWASD.size(); i++) {
    			if (snakeHeadWASD.getPx() == snakeWASD.get(i).getPx() && snakeHeadWASD.getPy() == snakeWASD.get(i).getPy()) {
    				winLogic("BLUE");
    			}
    		}
    }
    
    // In this case, both snakes lose, end the game as a tie
    public void checkHeadOnCollision() {
    		if (((snakeHeadArrows.getDirection() == Direction.UP || snakeHeadWASD.getDirection() == Direction.UP) && 
    			  snakeHeadArrows.getPx() == snakeHeadWASD.getPx() || 
    			 (snakeHeadArrows.getDirection() == Direction.LEFT || snakeHeadWASD.getDirection() == Direction.UP) && 
    			  snakeHeadArrows.getPy() == snakeHeadWASD.getPy()) &&
    			parallelMove(snakeHeadArrows, snakeHeadWASD) && 
    			snakeHeadArrows.intersects(snakeHeadWASD)) {
    			winLogic("TIE");
    		}
    }
    
    public void checkPowerUpCollision() {
    		for (int i = 0; i < powerUpMap.length; i++) {
    			for (int j = 0; j < powerUpMap[0].length; j++) {
    				if (powerUpMap[i][j] != null && powerUpMap[i][j].intersects(snakeHeadWASD)) {
    					powerUpMap[i][j].effectWASD(snakeWASD);
    					powerUpMap[i][j] = null;
    				} else if (powerUpMap[i][j] != null && powerUpMap[i][j].intersects(snakeHeadArrows)) {
    					powerUpMap[i][j].effectArrows(snakeArrows);
    					powerUpMap[i][j] = null;
    				}
    			}
    		}
    }
    
    
    // Use this helper function to help collision
    // Snakes should not collide when just moving next to each other
    public boolean parallelMove(SnakeObj piece1, SnakeObj piece2) {
    		if (piece1.getDirection() == Direction.LEFT && piece2.getDirection() == Direction.LEFT ||
    			piece1.getDirection() == Direction.RIGHT && piece2.getDirection() == Direction.RIGHT ||
    			piece1.getDirection() == Direction.LEFT && piece2.getDirection() == Direction.RIGHT ||
    			piece1.getDirection() == Direction.RIGHT && piece2.getDirection() == Direction.LEFT ||
    			piece1.getDirection() == Direction.UP && piece2.getDirection() == Direction.UP ||
    			piece1.getDirection() == Direction.DOWN && piece2.getDirection() == Direction.DOWN ||
    			piece1.getDirection() == Direction.UP && piece2.getDirection() == Direction.DOWN ||
    			piece1.getDirection() == Direction.DOWN && piece2.getDirection() == Direction.UP) {
    			return true;
    		} else {
    			return false;
    		}
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw Red Snake
        for (int i = 0; i < snakeWASD.size(); i++) {
            snakeWASD.get(i).draw(g);
        }
        
        // Draw Blue Snake
        for (int i = 0; i < snakeArrows.size(); i++) {
            snakeArrows.get(i).draw(g);
        }
        
        // Draw power ups
        for (int i = 20; i < powerUpMap.length; i++) {
        		for (int j = 0; j < powerUpMap[0].length; j++) {
        			if (powerUpMap[i][j] != null) {
        				powerUpMap[i][j].draw(g);
        			}
        		}
        }
        
        // Draw divider line between stats and game field
        g.setColor(Color.WHITE);
        g.drawLine(0, 20, COURT_WIDTH, 20);
        
        // Draw game stats
        g.setColor(Color.WHITE);
        g.drawString("Time Left: " + GAME_TIME + "s    Red Score: " + wasdScore + "    Blue Score: " + arrowsScore, 10, 15);
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}