package critterproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.awt.FontFormatException;
import java.io.IOException;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font pixelFont;
    Font arial_40, arial_80B;
    public String message = "";
    public boolean messageOn = false;
    public int messageCounter = 0;
    public Color messageColor = Color.white; // Default color
    
    
    //command integer (title screen menu selection for later?)
    public int commandNum = 0;

    
    
    public UI(GamePanel gp) {
        this.gp = gp;
        
        try {
            // Load the font file from your resource folder
            InputStream is = getClass().getResourceAsStream("/font/PublicPixel.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            //fallback to arial
            pixelFont = new Font("Arial", Font.PLAIN, 14); 
        }
    }
    
    
    
    

    public void showMessage(String text, Color color) {
        message = text;
        messageOn = true;
        messageCounter = 0; // IMPORTANT: Reset this so the message starts fresh
        messageColor = color;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //TITLE state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //PLAY state
        if (gp.gameState == gp.playState) {
            drawPlayerUI();
        }
        
        //game over state
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
    }
    }

    public void drawTitleScreen() {
        //background color
        g2.setColor(new Color(40, 70, 50)); // Dark Forest Green
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //title name
        g2.setFont(pixelFont.deriveFont(Font.BOLD, 36F));
        String text = "The Critter Game";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //shadow on font
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //critter image on title screen
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));

        text = "Press enter to start game...";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        
       
    }
    
    
   

    public void drawPlayerUI() {
        //display critter name in the top left
    	g2.setFont(pixelFont.deriveFont(Font.PLAIN, 10F));
    	String displayName = "Critter: " + gp.player.name;
    	//measuring actual text to hug the background of text box
    	int textWidth = g2.getFontMetrics().stringWidth(displayName);
        
    	int boxWidth = textWidth + 100; // Wider to fit the bar
        int boxHeight = 55; 
        g2.setColor(new Color(0, 0, 0, 120)); 
        g2.fillRoundRect(10, 10, boxWidth, boxHeight, 10, 10);
    	
        //draw name
        g2.setColor(Color.white);
        g2.drawString(displayName, 18, 27);
        
        //happiness stat
        String label = "HAPPINESS";
        g2.setFont(pixelFont.deriveFont(Font.PLAIN, 8F));
        g2.drawString(label, 18, 42);
        
     // Bar Background (Empty/Shadow)
        g2.setColor(Color.gray);
        g2.fillRect(18, 46, 150, 10);
        
     // Happiness Fill (Calculation based on Player stats)
        // Adjust these variables based on where you store Happiness (gp.player is typical)
        g2.setColor(new Color(255, 100, 100)); // Coral Pink
        double hpScale = (double)150 / gp.maxHappiness; 
        double barWidth = hpScale * gp.currentHappiness;
        g2.fillRect(18, 46, (int)barWidth, 10);
        
        if (messageOn) {
            // Use a font size that is visible (e.g., 20F or 30F)
        	g2.setFont(pixelFont.deriveFont(Font.PLAIN, 14F)); 
            
            // Set the color to the one passed in showMessage
            g2.setColor(messageColor);
            
            
            // Position it near the center or top of the screen
            g2.drawString(message, gp.tileSize * 2, gp.tileSize * 5);

            messageCounter++;
            
            // If the message has been on screen for 2 seconds (120 frames), hide it
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            } 
        }
    }
    
    public void drawGameOverScreen() {
        // 1. DIM THE BACKGROUND
        g2.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // 2. GAME OVER TEXT
        g2.setFont(pixelFont.deriveFont(Font.BOLD, 60F));
        g2.setColor(Color.white);
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 4;
        g2.drawString(text, x, y);

        // 3. BACK TO TITLE OPTION
        g2.setFont(pixelFont.deriveFont(Font.PLAIN, 20F));
        text = "PRESS 'ENTER' FOR TITLE SCREEN";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
    }

    //helper method to center text based on screen width
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}