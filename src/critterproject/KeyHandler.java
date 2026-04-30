package critterproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (gp.gameState == gp.titleState) {
	        if (code == KeyEvent.VK_ENTER) {
	            
	        	String input = JOptionPane.showInputDialog(gp, "What is your critter's name?", "Name Entry", JOptionPane.QUESTION_MESSAGE);

	            // 2. Process the input (Handle cancel/empty)
	            if (input != null && !input.trim().isEmpty()) {
	                gp.player.name = input;
	            } else {
	                gp.player.name = "Buddy"; // Default if they hit cancel
	            }

	            // 3. Move to the game
	            gp.gameState = gp.playState;
	        }
	    }
	    
	    // IF YOU ARE ALREADY PLAYING
	    else if (gp.gameState == gp.playState) {
	    	if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
				
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
	    }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}
	
}
