package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity{

    public int xDirection;
    public int yDirection;

    private final Random random;

    public Ball(GamePanel gp) {

        this.random = new Random();

        this.gp = gp;

        this.width = 25;
        this.height = 25;

        this.speed = 2;

        this.setLocation();
        this.setDirection();
    }

    public void setLocation() {
        x = (gp.WIDTH/2)-(width/2);
        y = (gp.HEIGH/2)-(width/2);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval(x,y,width,height);
    }

    public void setDirection() {
        xDirection = random.nextInt() % 2;
        yDirection = random.nextInt() % 2;
        if(yDirection < 0 || xDirection < 0) {
            setDirection();
        }
    }

    public void checkCollitionsWithTheBorder() {
        if(y <= 0) {
            yDirection = 1;
        }
        if(y+height >= gp.HEIGH) {
            yDirection = 0;
        }
    }

    public void update() {
        if(yDirection == 0) {
            y -= speed;
            if(xDirection == 0) {
                x -= speed;
            }
            if(xDirection == 1) {
                x += speed;
            }
        }
        if(yDirection == 1) {
            y += speed;
            if(xDirection == 0) {
                x -= speed;
            }
            if(xDirection == 1) {
                x += speed;
            }
        }
        checkCollitionsWithTheBorder();
    }
}
