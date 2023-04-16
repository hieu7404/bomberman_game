package main;

import entity.Entity;
import entity.Player;
import object.Bomb;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    // SETUP
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public CollisionCheck cCheck = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Entity monster[] = new Entity[10];
    public Bomb bomb = new Bomb(this);

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int gameWinState = 4;

    // BOMB
    public boolean setBomb = false;
    public boolean boom = false;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // size
        this.setBackground(Color.black); // background
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // add keys
        this.setFocusable(true); // focusable key
    }

    public void setupGame() {

        aSetter.setMonster(); // setup monster
        gameState = titleState;
    }

    public void reStart() {
        player.setGameOver();
        aSetter.setMonster();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / FPS; // 1s:60 (ns)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        if (gameState == playState) {
            // Update monster
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();
                }
            }
            // Update player
            player.update();
            // Update bomb
            bomb.update();
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Title screen
        if (gameState == titleState) {
            // UI
            ui.draw(g2);
        }
        // Others
        else {
            // Title
            tileM.draw(g2);
            // Player
            player.draw(g2);
            // Monster
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].draw(g2);
                }
            }
            // Bomb
            bomb.draw(g2);
            // UI
            ui.draw(g2);
        }
        g2.dispose();
    }
}
