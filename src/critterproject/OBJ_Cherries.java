package critterproject;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Cherries extends SuperObject {
    public OBJ_Cherries() {
        name = "Cherries"; // Crucial for pickUpItem logic
        try {
            // Make sure cherries.png is in your res/objects folder
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cherries.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}