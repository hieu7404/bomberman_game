package monster;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Monster1 extends Entity {

    public Monster1(GamePanel gp) {

        super(gp);
        direction = "down";
        speed = 1;
        maxLife = 1;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/monster/tholen1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monster/tholen2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monster/tho1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monster/tho2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster/thotrai1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster/thotrai2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monster/thophai1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster/thophai2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter >= 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Random a number 1 -> 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}
