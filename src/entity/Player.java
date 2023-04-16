package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle(8, 16, 32, 32);
    }

    public void setDefaultValues() {

        x = 50;
        y = 50;
        speed = 4;
        direction = "down";

        // Player status
        maxLife = 2;
        life = maxLife;
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass() .getResourceAsStream("/player/u1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/u2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/d1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/d2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/l1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/l2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/r1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/r2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cCheck.checkTile(this);

            // Check monster collision
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Check bomb collision
            boolean boom = gp.cCheck.checkBoom(this);
            contactBomb(boom);

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
        }

        // Set invincible
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // Get bomb place
        if (gp.setBomb == false) {
            xBomb = gp.player.x;
            yBomb = gp.player.y;
        }

        // Game over
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
        }
    }

    public void contactMonster(int index) {

        if (index != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void contactBomb(boolean boom) {

        if (boom && gp.boom) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void setGameOver() {
        x = 50;
        y = 50;
        direction = "down";

        life = maxLife;
        invincible = false;
    }

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

        // Draw picture with 40% real picture
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}