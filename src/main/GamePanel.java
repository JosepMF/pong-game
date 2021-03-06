package main;

import entity.Ball;
import entity.Paddle;
import entity.Score;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    public final int WIDTH = 1200;
    public final int HEIGH = 700;

    public final int FPS = 60;

    public KeyHandler keyHandler = new KeyHandler();

    public Thread gameThread;

    public Paddle paddle1;
    public Paddle paddle2;

    public Score score;

    public Ball ball;

    public boolean gameOver;

    public int gameScores[] = new int[2];

    public GamePanel() {
        gameThread = new Thread(this);

        score = new Score(this);

        paddle1 = new Paddle(1, this, keyHandler);
        paddle2 = new Paddle(2, this, keyHandler);

        ball = new Ball(this);

        this.setPreferredSize(new Dimension(WIDTH, HEIGH));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);

    }

    public void startGame() {
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        checkCollitions();

        ball.update();

        paddle1.update();
        paddle2.update();

        checkCollitions();
    }

    private void checkGameOver(Graphics2D g2) {
        if(gameOver) {
                ball.x = WIDTH/2-ball.width/2;
                ball.y = HEIGH/2-ball.height/2;
                gameOver = false;
        }
    }

    private void checkCollitions() {
        if(ball.x <= 0) {
            System.out.println("game over");
            gameScores[1] += 1;
            gameOver = true;
        }
        else if(ball.x+ball.width >= this.WIDTH) {
            System.out.println("you won");
            gameScores[0] += 1;
            gameOver = true;
        }

        if(ball.x + ball.width < paddle1.width && ball.y > paddle1.y && ball.y < paddle1.y + paddle1.height) {
                ball.xDirection = 1;
                if(ball.yDirection == 1) {
                    ball.yDirection = 0;
                } else if (ball.yDirection == 0) {
                    ball.yDirection = 1;
                }
        }
        else if(ball.x+ball.width >= paddle2.x && ball.y > paddle2.y && ball.y < paddle2.y + paddle2.height) {
            ball.xDirection = 0;
            if(ball.yDirection == 1) {
                ball.yDirection = 0;
            } else if (ball.yDirection == 0) {
                ball.yDirection = 1;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        ball.draw(g2);

        paddle1.draw(g2);
        paddle2.draw(g2);

        checkGameOver(g2);

        score.draw(g2, gameScores);

        g2.dispose();
    }
}
