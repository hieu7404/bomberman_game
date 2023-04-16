package main;

import entity.Entity;

public class CollisionCheck {

    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // CHECK MONSTER COLLISION
    public int checkEntity(Entity entity, Entity[] target) {

        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {

                // Get entity's solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;

                // Get target's solid area position
                target[i].solidArea.x = target[i].x + target[i].solidArea.x;
                target[i].solidArea.y = target[i].y + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if (entity != target[i]) {
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        // Get entity's solid area position
        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;

        // Get player's solid area position
        gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }

    public boolean checkBoom(Entity entity) {

        boolean checkBoom = false;

        // Get entity's solid area position
        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;

        // Get boom's area
        gp.bomb.boomAreaMid.x = gp.player.xBomb;
        gp.bomb.boomAreaMid.y = gp.player.yBomb;

        gp.bomb.boomAreaTop.x = gp.player.xBomb - gp.tileSize;
        gp.bomb.boomAreaTop.y = gp.player.yBomb;

        gp.bomb.boomAreaBottom.x = gp.player.xBomb + gp.tileSize;
        gp.bomb.boomAreaBottom.y = gp.player.yBomb;

        gp.bomb.boomAreaLeft.x = gp.player.xBomb;
        gp.bomb.boomAreaLeft.y = gp.player.yBomb - gp.tileSize;

        gp.bomb.boomAreaRight.x = gp.player.xBomb;
        gp.bomb.boomAreaRight.y = gp.player.yBomb + gp.tileSize;

        if (entity.solidArea.intersects(gp.bomb.boomAreaMid) || entity.solidArea.intersects(gp.bomb.boomAreaTop)
        || entity.solidArea.intersects(gp.bomb.boomAreaBottom) || entity.solidArea.intersects(gp.bomb.boomAreaLeft)
        || entity.solidArea.intersects(gp.bomb.boomAreaRight)) {
            checkBoom = true;
        }

        // RESET
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.bomb.boomAreaMid.x = gp.bomb.boomAreaMidDefaultX;
        gp.bomb.boomAreaMid.y = gp.bomb.boomAreaMidDefaultY;
        gp.bomb.boomAreaTop.x = gp.bomb.boomAreaTopDefaultX;
        gp.bomb.boomAreaTop.y = gp.bomb.boomAreaTopDefaultY;
        gp.bomb.boomAreaBottom.x = gp.bomb.boomAreaBottomDefaultX;
        gp.bomb.boomAreaBottom.y = gp.bomb.boomAreaBottomDefaultY;
        gp.bomb.boomAreaLeft.x = gp.bomb.boomAreaLeftDefaultX;
        gp.bomb.boomAreaLeft.y = gp.bomb.boomAreaLeftDefaultY;
        gp.bomb.boomAreaRight.x = gp.bomb.boomAreaRightDefaultX;
        gp.bomb.boomAreaRight.y = gp.bomb.boomAreaRightDefaultY;

        return checkBoom;
    }
}
