package critterproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public String message = "";
    public boolean messageOn = false;
    public int messageCounter = 0;
    
    //command integer (title screen menu selection for later?)
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
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
    }

    public void drawTitleScreen() {
        //background color
        g2.setColor(new Color(40, 70, 50)); // Dark Forest Green
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
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
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));

        text = "Press enter to start game...";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        
       
    }

    public void drawPlayerUI() {
        //display critter name in the top left
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
    	
    	String displayName = "Critter: " + gp.player.name;
        
    	//measuring actual text to hug the background of text box
    	int textWidth = g2.getFontMetrics().stringWidth(displayName);
        int boxHeight = 24;
        int boxWidth = textWidth + 15; // Small padding

        //box around text
        g2.setColor(new Color(0, 0, 0, 100)); 
        g2.fillRoundRect(10, 10, boxWidth, boxHeight, 10, 10); // Rounded corners look better!

        //text inside box
        g2.setColor(Color.white);
        g2.drawString(displayName, 18, 27);
        

        /* POP-UP MESSAGES (For Signposts/Feeding)
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            messageCounter++;
            if (messageCounter > 120) { // Message lasts 2 seconds (60fps * 2)
                messageCounter = 0;
                messageOn = false;
            } 
        }*/
    }

    //helper method to center text based on screen width
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}