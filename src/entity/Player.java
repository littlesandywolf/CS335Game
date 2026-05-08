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
	public boolean isJumping = false;
	public int jumpCounter = 0;
	public String name = "Critter";
	public String color = "Orange";
	public int jumpHeight = 0;
	public int jumpSpeed = 4;
	public int maxJumpHeight = 40;
	public boolean falling = false;
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
			
			String[] colors = {"Orange", "Gray", "Multicolored", "Rainbow"};
			int randomIndex = (int)(Math.random()*colors.length);
			color = colors[randomIndex];
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterup1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterup2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterdown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterdown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterleft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterleft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterright1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/" + color + "/critterright2.png"));
				
						
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
	            if(gp.currentHappiness>= 100) {
	                gp.gameState = gp.gameOverState;
	            }
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

	    // RUN
	    if (keyH.runPressed) {
	        speed = 12;
	    } else {
	        speed = 4;
	    }

	    // START JUMP
	    if(keyH.jumpPressed && !isJumping && !falling) {
	        isJumping = true;
	    }

	    // JUMP UP
	    if(isJumping) {

	        worldY -= jumpSpeed;
	        jumpHeight += jumpSpeed;

	        if(jumpHeight >= maxJumpHeight) {
	            isJumping = false;
	            falling = true;
	        }
	    }

	    // FALL DOWN
	    else if(falling) {

	        worldY += jumpSpeed;
	        jumpHeight -= jumpSpeed;

	        if(jumpHeight <= 0) {
	            falling = false;
	            jumpHeight = 0;
	        }
	    }

	    // MOVEMENT
	    if(keyH.upPressed || keyH.downPressed ||
	       keyH.leftPressed || keyH.rightPressed) {

	        if(keyH.upPressed) {
	            direction = "up";
	        }
	        else if(keyH.downPressed) {
	            direction = "down";
	        }
	        else if(keyH.leftPressed) {
	            direction = "left";
	        }
	        else if(keyH.rightPressed) {
	            direction = "right";
	        }

	        // CHECK COLLISION
	        collisionOn = false;

	        gp.cChecker.checkTile(this);

	        int objIndex = gp.cChecker.checkObject(this, true);

	        // OBSTACLE COLLISION
	        if(objIndex != 999 &&
	           gp.obj[objIndex] != null &&
	           gp.obj[objIndex].name.equals("Obstacle")) {

	            // Ignore obstacle collision while jumping
	            if(isJumping || falling) {

	                gp.ui.showMessage("Nice Jump!", Color.cyan);

	            } else {

	                collisionOn = true;

	                gp.ui.showMessage("Press SPACE to Jump!", Color.orange);
	            }
	        }

	        // PICKUP ITEMS
	        pickUpItem(objIndex);

	        // MOVE PLAYER
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

	        // SPRITE ANIMATION
	        spriteCounter++;

	        if(spriteCounter > 12) {

	            if(spriteNum == 1) {
	                spriteNum = 2;
	            }
	            else if(spriteNum == 2) {
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

    
	if(color.equals("Rainbow")) {
			
		int trailX = screenX;
		int trailY = screenY;
			
		if(direction.equals("up")) {
			trailY = screenY + 35;
		}
		else if(direction.equals("down")) {
			trailY = screenY - 15;
		}
		else if(direction.equals("left")) {
			trailX = screenX + 35;
		}
		else if(direction.equals("right")) {
			trailX = screenX - 15;
		}
			
		g2.setColor(Color.RED);
		g2.fillRect(trailX, trailY, 26, 26);

		g2.setColor(Color.ORANGE);
		g2.fillRect(trailX + 6, trailY + 6, 22, 22);

		g2.setColor(Color.YELLOW);
		g2.fillRect(trailX + 12, trailY + 12, 18, 18);

		g2.setColor(Color.GREEN);
		g2.fillRect(trailX + 18, trailY + 18, 15, 15);

		g2.setColor(Color.CYAN);
		g2.fillRect(trailX + 24, trailY + 24, 12, 12);

		g2.setColor(Color.BLUE);
		g2.fillRect(trailX + 30, trailY + 30, 9, 9);
		
		

		g2.setColor(new Color(148, 0, 211)); // violet
		g2.fillRect(trailX + 36, trailY + 36, 6, 6);
	}
		
	g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  
	}
}

