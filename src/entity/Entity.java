package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;
    public boolean collisionOn = false;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public boolean invincible = false;

    // CHARACTER STATUS
    public int maxLife;
    public int life;

    // BOMB
    public int xBomb, yBomb;
    boolean checkBoom = false;

    // MONSTER
    public int checkEnemy() {
        int enemy = 0;
        for (int i = 0; i < gp.monster.length; i++) {
            if (gp.monster[i] != null) {
                enemy++;
            }
        }
        return enemy;
    }

    public void setAction() {

    }

    public void update() {

        setAction();

        collisionOn = false;
        gp.cCheck.checkTile(this); // Check tile collision
        boolean contactPlayer = gp.cCheck.checkPlayer(this); // Check player collision

        // CHECK BOOM -> MONSTER
        for (int i = 0; i < gp.monster.length; i++) {
            if (gp.monster[i] != null) {
                checkBoom = gp.cCheck.checkBoom(gp.monster[i]);
                if (checkBoom == true && gp.boom == true) {
                    gp.monster[i].life -= 1;
                    if (gp.monster[i].life <= 0) {
                        gp.monster[i] = null;
                    }
                }
            }
        }

        if (contactPlayer) {
            if (gp.player.invincible == false) {
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        // If collisionOn == false, monster can move
        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
        }

        // Save spriteNum to draw pictures
        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        // Game win
        if (checkEnemy() == 0) {
            gp.gameState = gp.gameWinState;
            gp.aSetter.setMonster();
        }
    }

    // DRAW ENTITY (MONSTER,...)
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}