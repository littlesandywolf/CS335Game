package critterproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.GridLayout;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
	public boolean runPressed, jumpPressed;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (gp.gameState == gp.gameOverState) {
	        if (code == KeyEvent.VK_SPACE) {
	        	gp.resetGame();  // We need to clear the stats
	            gp.gameState = gp.titleState;
	   
	        }
	    }
		
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
	            JPanel panel = new JPanel();
	            
	            panel.setLayout(new GridLayout(0, 1));
	            
	            panel.add(new JLabel("HOW TO PLAY THE CRITTER GAME!"));
	            panel.add(new JLabel(" "));
	            
	            panel.add(new JLabel("USE THE W KEY TO MOVE UP"));
	            panel.add(new JLabel("USE THE A KEY TO MOVE LEFT"));
	            panel.add(new JLabel("USE THE S KEY TO MOVE DOWN"));
	            panel.add(new JLabel("USE THE D KEY TO MOVE RIGHT"));
	            
	            panel.add(new JLabel("HOLD SHIFT WITH A MOVEMENT KEY TO RUN!"));
	            
	            panel.add(new JLabel("PRESS SPACE TO JUMP"));
	            
	            panel.add(new JLabel("Collect cherries to gain happiness & WIN the game!"));
	            
	            panel.add(new JLabel("Avoid spiders or else..."));
	            
	            panel.add(new JLabel("Test out your skills by jumping rocks!"));
	            

	            
	            panel.add(new JLabel("Have Fun!"));
	            panel.add(new JLabel(" "));

	            panel.add(new JLabel("Possible Critter Colors:"));

	            JLabel orangeCat = new JLabel(
	                "Orange Dreamsicle",
	                new ImageIcon(getClass().getResource("/player/Orange/critterdown1.png")),
	                JLabel.LEFT
	            );

	            JLabel grayCat = new JLabel(
	                "Gray Gangsta",
	                new ImageIcon(getClass().getResource("/player/Gray/critterdown1.png")),
	                JLabel.LEFT
	            );

	            JLabel multicoloredCat = new JLabel(
	                "Multicolored Menace",
	                new ImageIcon(getClass().getResource("/player/Multicolored/critterdown1.png")),
	                JLabel.LEFT
	            );

	            JLabel rainbowCat = new JLabel(
	                "RARE Rainbow (Includes a special rainbow trail!)",
	                new ImageIcon(getClass().getResource("/player/Rainbow/critterdown1.png")),
	                JLabel.LEFT
	            );

	            panel.add(orangeCat);
	            panel.add(grayCat);
	            panel.add(multicoloredCat);
	            panel.add(rainbowCat);

	            JOptionPane.showMessageDialog(
	                null,
	                panel,
	                "Game Instructions Yippee",
	                JOptionPane.INFORMATION_MESSAGE
	            );

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
			if(code == KeyEvent.VK_SHIFT) {
			    runPressed = true;
			}

			if(code == KeyEvent.VK_SPACE) {
			    jumpPressed = true;
			}
			
	    }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_SHIFT) {
		    runPressed = false;
		}

		if(code == KeyEvent.VK_SPACE) {
		    jumpPressed = false;
		}
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
