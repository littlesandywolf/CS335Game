package critterproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3; //scaling 16x16 cause that is too small
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	public final int maxScreenCol= 16;
	public final int maxScreenRow= 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	//world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	public int maxHappiness = 100;
	public int currentHappiness = 50; //starting at half health
	
	public SuperObject obj[] = new SuperObject[10]; // Array to hold your items
	
	public AssetSetter aSetter = new AssetSetter(this);
	int spiderTimer = 0;
	
	int FPS = 60; 
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	public UI ui = new UI(this);
	
	//default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //can improve game's rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.gameState = titleState; // <--- MAKE SURE THIS IS 0
	}

	public void setupGame() {
	    
	    aSetter.setObject(); // This tells the AssetSetter to place your items
	    
	    // If you have music, you would start it here
	    // playMusic(0); 
	    
	    gameState = titleState; // Sets the game to the Title Screen first
	}
	
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start(); 
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval; //allocated time for a single loop is 0.01666 seconds (bc math)
		
		while(gameThread != null) {
			
			//UPDATE: update information such as character positions
			update();
			
			//DRAW: draw the screen with the updated info
			repaint();
			
		
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; //convert to millisecond bc sleep method
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
		
		
	public void update() {
		if (gameState == playState) {
	        player.update();

	        // One-minute teleportation logic
	        spiderTimer++;
	        if (spiderTimer >= 3600) { 
	            aSetter.spawnSpider(0); // Relocate the spider in slot 0
	            spiderTimer = 0;
	        }
	    }
		
	}
	
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout
		
		// TITLE SCREEN
	    if (gameState == titleState) {
	        ui.draw(g2); 
	    } 
	    // PLAYING
	    else {
	        tileM.draw(g2); // Draw tiles first
	        player.draw(g2); // Draw player second
	        ui.draw(g2);    // Draw UI last (so it's on top!)
	    }
	    
	    g2.dispose(); //dispose of this graphics context and release any system resources that it is using
		
		//in order to make white square move, we must add keyboard input	
	}
	
}
