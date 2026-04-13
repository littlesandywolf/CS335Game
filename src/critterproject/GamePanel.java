package critterproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3; //scaling 16x16 cause that is too small
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	
	//how many tiles should the game screen be?
	public final int maxScreenCol= 16;
	public final int maxScreenRow= 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //can improve game's rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start(); 
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(gameThread != null) {
			
			//UPDATE: update information such as character positions
			update();
			
			//DRAW: draw the screen with the updated info
			repaint();
	}
		
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout
		
		tileM.draw(g2); //we draw tiles THEN player - don't want to hide character
		
		player.draw(g2);
		
		g2.dispose(); //dispose of this graphics context and release any system resources that it is using
		
		//in order to make white square move, we must add keyboard input	
	}
	
}
