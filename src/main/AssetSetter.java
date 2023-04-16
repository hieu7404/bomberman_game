package main;

import monster.Monster1;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setMonster() {

        gp.monster[0] = new Monster1(gp);
        gp.monster[0].x = gp.tileSize*4;
        gp.monster[0].y = gp.tileSize+4;

        gp.monster[1] = new Monster1(gp);
        gp.monster[1].x = gp.tileSize*14;
        gp.monster[1].y = gp.tileSize*2;

        gp.monster[2] = new Monster1(gp);
        gp.monster[2].x = gp.tileSize*9;
        gp.monster[2].y = gp.tileSize*6;

        gp.monster[3] = new Monster1(gp);
        gp.monster[3].x = gp.tileSize*14;
        gp.monster[3].y = gp.tileSize*8;

        gp.monster[4] = new Monster1(gp);
        gp.monster[4].x = gp.tileSize;
        gp.monster[4].y = gp.tileSize*8;

        gp.monster[5] = new Monster1(gp);
        gp.monster[5].x = gp.tileSize*3;
        gp.monster[5].y = gp.tileSize*5;
    }
}
