package main;

import object.Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public int commandNum = 0;
    BufferedImage heartFull, heartHalf, heartBlank;

    public UI(GamePanel gp) {

        this.gp = gp;

        // CREATE HUD OBJECT
        SuperObject heart = new Heart(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartBlank = heart.image3;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(new Font("Arial", Font.PLAIN, 40));
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        if (gp.gameState == gp.gameWinState) {
            drawGameWinScreen();
        }
    }

    public void drawPlayerLife() {

        // Draw max life
        int x = gp.tileSize/4;
        int y = gp.tileSize/4;
        int i = 0;
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heartBlank, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize/4;
        i = 0;

        // Draw current life
        while (i < gp.player.life) {
            g2.drawImage(heartHalf, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heartFull, x, y, gp.tileSize, gp.tileSize, null);
                i++;
            }
            x += gp.tileSize;
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F)); // Resize font
        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawTitleScreen() {

        // Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F)); // Resize font
        String text = "BOMBERMAN GAME";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;
        g2.setColor(Color.white); // Color of text
        g2.drawString(text, x, y);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F)); // Resize font
        text = "NEW GAME";
        x = getXforCenterText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawGameOverScreen() {

        g2.setColor(new Color(50, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
        g2.setColor(Color.white);

        String text = "GAME OVER";
        int x = getXforCenterText(text);
        int y = getYforCenterText(text);
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        text = "(Press Backspace to play again)";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
    }

    public void drawGameWinScreen() {

        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
        g2.setColor(Color.white);

        String text = "WIN";
        int x = getXforCenterText(text);
        int y = getYforCenterText(text);
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        text = "(Press Backspace to play again)";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
    }

    public int getXforCenterText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }

    public int getYforCenterText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        return gp.screenHeight/2 - length/2;
    }
}