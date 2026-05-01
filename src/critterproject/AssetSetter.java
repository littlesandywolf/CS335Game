package critterproject;

import java.util.Random;

public class AssetSetter {

    GamePanel gp;
    Random random = new Random();

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // This puts the first spider on the map at the start
    public void setObject() {
        spawnSpider(0); // Place spider in slot 0 of our array
        spawnCherries(1);
    }

    // This logic handles finding a safe tile and placing the spider
    public void spawnSpider(int index) {
        boolean placed = false;

        while (!placed) {
            // 1. Pick a random coordinate within the 50x50 map
            int col = random.nextInt(gp.maxWorldCol);
            int row = random.nextInt(gp.maxWorldRow);

            // 2. Check the TileManager to see if that tile has collision
            int tileNum = gp.tileM.mapTileNum[col][row];
            
            if (!gp.tileM.tile[tileNum].collision) {
                // 3. If it's safe (Grass/Dirt/Flowers), place the spider
                gp.obj[index] = new OBJ_spider();
                gp.obj[index].worldX = col * gp.tileSize;
                gp.obj[index].worldY = row * gp.tileSize;
                
                System.out.println("Spider spawned at Col: " + col + " Row: " + row);
                
                placed = true;
            }
            // If it hits a tree/wall, the 'while' loop runs again automatically
        }   
        
    }
    
    public void spawnCherries(int index) {
        boolean placed = false;
        while (!placed) {
            int col = random.nextInt(gp.maxWorldCol);
            int row = random.nextInt(gp.maxWorldRow);

            int tileNum = gp.tileM.mapTileNum[col][row];
            
            // Ensure cherries spawn on walkable tiles (no trees/walls)
            if (!gp.tileM.tile[tileNum].collision) {
                gp.obj[index] = new OBJ_Cherries();
                gp.obj[index].worldX = col * gp.tileSize;
                gp.obj[index].worldY = row * gp.tileSize;
                placed = true;
            }
        }
    }
}