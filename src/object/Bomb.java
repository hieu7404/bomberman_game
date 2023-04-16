package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends SuperObject {

    GamePanel gp;

    public Bomb(GamePanel gp) {

        this.gp = gp;
        try {
            bomb = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb.png"));
            firebomb = ImageIO.read(getClass().getResourceAsStream("/bomb/firebomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (gp.setBomb == true) {
            spriteCounter++;

            // Bomb
            if (spriteCounter <= 120) {
                spriteNum = 1;
            }
            // Firebomb
            else if (spriteCounter > 120 && spriteCounter <= 180) {
                spriteNum = 2;
                gp.boom = true;
            }
            else if (spriteCounter > 180) {
                spriteNum = 1;
                spriteCounter = 0;
                gp.setBomb = false;
                gp.boom = false;
            }
        }
        // SpriteNum to check bomb/firebomb
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        x = gp.player.xBomb;
        y = gp.player.yBomb;

        if (gp.setBomb == true) {
            if (spriteNum == 1) {
                image = bomb;
                g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
            }
            else if (spriteNum == 2) {
                image = firebomb;
                g2.drawImage(image, x - gp.tileSize - 16, y - gp.tileSize - 16,
                        gp.tileSize*4, gp.tileSize*4, null);
            }
        }
    }
}
