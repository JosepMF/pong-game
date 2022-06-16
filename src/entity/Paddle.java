package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Paddle extends Entity {

    public Paddle(int id, GamePanel gp, KeyHandler kh) {

        this.id = id;

        this.height = (gp.HEIGH/3);
        this.width = 50;

        this.speed = 4;

        this.gp = gp;
        this.kh = kh;

        this.setLocation();
    }

    public void setLocation() {
        if(id == 1) {
            x = 0;
            y = (gp.HEIGH / 2)-(height/2);
        } else {
            x = gp.WIDTH-width;
            y = (gp.HEIGH / 2)-(height/2);
        }
    }

    public void draw(Graphics2D g2) {
        if(id == 1) {
            g2.setColor(Color.BLUE);
        } else {
            g2.setColor(Color.RED);
        }

        g2.fillRect(x,y,width,height);
    }

    public void update() {
        if(id == 1) {
            if(kh.down1) {
                y+=speed;
            }
            if(kh.up1) {
                y-=speed;
            }
        } else {
            if(kh.down2) {
                y+=speed;
            }
            if(kh.up2) {
                y-=speed;
            }
        }
        checkCollitionsWithTheBorder();
    }

    public void checkCollitionsWithTheBorder() {
        if(y <= 0) {
            y = 0;
        }
        if(y+height >= gp.HEIGH) {
            y = gp.HEIGH-height;
        }
    }
}
