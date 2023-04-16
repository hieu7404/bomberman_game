package object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image, image2, image3;
    public BufferedImage bomb, firebomb;
    public String name;
    public boolean collision = false;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle boomAreaMid = new Rectangle(0, 0, 48, 48);
    public Rectangle boomAreaTop = new Rectangle(0, 0, 48, 48);
    public Rectangle boomAreaBottom = new Rectangle(0, 0, 48, 48);
    public Rectangle boomAreaLeft = new Rectangle(0, 0, 48, 48);
    public Rectangle boomAreaRight = new Rectangle(0, 0, 48, 48);

    public int boomAreaMidDefaultX = boomAreaMid.x;
    public int boomAreaMidDefaultY = boomAreaMid.y;
    public int boomAreaTopDefaultX = boomAreaTop.x;
    public int boomAreaTopDefaultY = boomAreaTop.y;
    public int boomAreaBottomDefaultX = boomAreaBottom.x;
    public int boomAreaBottomDefaultY = boomAreaBottom.y;
    public int boomAreaLeftDefaultX = boomAreaLeft.x;
    public int boomAreaLeftDefaultY = boomAreaLeft.y;
    public int boomAreaRightDefaultX = boomAreaRight.x;
    public int boomAreaRightDefaultY = boomAreaRight.y;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
