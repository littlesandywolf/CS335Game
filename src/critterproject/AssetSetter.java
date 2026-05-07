package critterproject;

import java.util.Random;

public class AssetSetter {

    GamePanel gp;
    Random random = new Random();

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // Set all game objects
    public void setObject() {

        // Spider
        spawnSpider(0);

        // Cherries
        for(int i = 1; i <= 5; i++) {
            spawnCherries(i);
        }

        
        for(int i = 6; i <= 15; i++) {
            spawnObstacle(i);
        }
    }

    // =========================
    // SPIDER SPAWN
    // =========================
    public void spawnSpider(int index) {

        boolean placed = false;

        while(!placed) {

            int col = random.nextInt(gp.maxWorldCol);
            int row = random.nextInt(gp.maxWorldRow);

            int tileNum = gp.tileM.mapTileNum[col][row];

            // Spawn only on walkable tiles
            if(!gp.tileM.tile[tileNum].collision) {

                gp.obj[index] = new OBJ_spider();

                gp.obj[index].worldX = col * gp.tileSize;
                gp.obj[index].worldY = row * gp.tileSize;

                System.out.println("Spider spawned at Col: " + col + " Row: " + row);

                placed = true;
            }
        }
    }

    // =========================
    // OBSTACLE SPAWN
    // =========================
    public void spawnObstacle(int index) {

        boolean placed = false;

        while(!placed) {

            int col = random.nextInt(gp.maxWorldCol);
            int row = random.nextInt(gp.maxWorldRow);

            int tileNum = gp.tileM.mapTileNum[col][row];

            // Only spawn on walkable tiles
            if(!gp.tileM.tile[tileNum].collision) {

                gp.obj[index] = new OBJ_Obstacle();

                // Place obstacle
                gp.obj[index].worldX = col * gp.tileSize;
                gp.obj[index].worldY = row * gp.tileSize;

                // Prevent spawning directly on player
                if(Math.abs(gp.obj[index].worldX - gp.player.worldX) > 100
                        || Math.abs(gp.obj[index].worldY - gp.player.worldY) > 100) {

                    System.out.println("Obstacle spawned at Col: " + col + " Row: " + row);

                    placed = true;
                }
            }
        }
    }

    // =========================
    // CHERRY SPAWN
    // =========================
    public void spawnCherries(int index) {

        boolean placed = false;

        while(!placed) {

            int col = random.nextInt(gp.maxWorldCol);
            int row = random.nextInt(gp.maxWorldRow);

            int tileNum = gp.tileM.mapTileNum[col][row];

            // Spawn only on walkable tiles
            if(!gp.tileM.tile[tileNum].collision) {

                gp.obj[index] = new OBJ_Cherries();

                gp.obj[index].worldX = col * gp.tileSize;
                gp.obj[index].worldY = row * gp.tileSize;

                System.out.println("Cherry spawned at Col: " + col + " Row: " + row);

                placed = true;
            }
        }
    }
}
