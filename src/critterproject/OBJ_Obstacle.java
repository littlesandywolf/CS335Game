


package critterproject;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Obstacle extends SuperObject {
    public OBJ_Obstacle() {
        name = "Obstacle"; // Crucial for pickUpItem logic
        try {
            // Make sure cherries.png is in your res/objects folder
            image = ImageIO.read(getClass().getResourceAsStream("/objects/rock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
