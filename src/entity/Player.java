package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import critterproject.GamePanel;
import critterproject.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public String name = "Critter";
	
	public final int screenX; //screen position is final; doesnt change
	public final int screenY;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8, 16, 32, 32); //collision area for player
		
		setDefaultValues();
		getPlayerImage();
		
	
	}
	
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21; 
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/critterup1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/critterup2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/critterdown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/critterdown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/critterleft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/critterleft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/critterright1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/critterright2.png"));
			
					
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void pickUpItem(int i) {
	    if (i != 999) { // 999 means no object was hit
	        String itemName = gp.obj[i].name;

	        if (itemName.equals("Cherries")) {
	            // POSITIVE EFFECT
	            gp.currentHappiness += 20; 
	            if(gp.currentHappiness > gp.maxHappiness) gp.currentHappiness = gp.maxHappiness;
	            
	            gp.ui.showMessage("Sweet cherries! +Happiness", Color.green);
	            gp.obj[i] = null; // Remove the item from the map
	        }

	        if (itemName.equals("Spider")) {
	            // NEGATIVE EFFECT
	            gp.currentHappiness -= 20;
	            if(gp.currentHappiness < 0) gp.currentHappiness = 0;
	            
	            gp.ui.showMessage("Yuck! A spider! -Happiness", Color.red);
	            gp.obj[i] = null;
	            
	            
	            if (gp.currentHappiness <= 0) {
	                gp.currentHappiness = 0;
	                gp.gameState = gp.gameOverState; // TRIGGER GAME OVER
	            }
	            
	        } 
	        
	        
	        
	    }
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				
			} else if(keyH.downPressed == true) {
				direction = "down";
				
			} else if(keyH.leftPressed == true) {
				direction = "left";
				
			} else if(keyH.rightPressed == true) {
				direction = "right";
				
			}
			
			//check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex = gp.cChecker.checkObject(this, true); // 1. Check for hits
			pickUpItem(objIndex);  
			
			//if collision is false, player can move 
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
					
				}
			}
			
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;	
				} else if(spriteNum == 2) {
					spriteNum = 1;
				} 
				
				spriteCounter = 0;
			}
		}
		                           
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
        case "up":
            image = (spriteNum == 1) ? up1 : up2;
            break;
        case "down":
            image = (spriteNum == 1) ? down1 : down2;
            break;
        case "left":
            image = (spriteNum == 1) ? left1 : left2;
            break;
        case "right":
            image = (spriteNum == 1) ? right1 : right2;
            break;
    }

    
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  
	}
}

