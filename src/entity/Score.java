package entity;

import main.GamePanel;

import java.awt.*;

public class Score extends Entity {
    public Score(GamePanel gp) {
        height = 50;

        y = height/2+10;
        x = gp.WIDTH/2;
    }

    public void draw(Graphics2D g2, int[] scores) {
        String scoreString = scores[0] + " - " + scores[1];
        Font fontText = new Font("Serif", Font.PLAIN, height);

        g2.setColor(Color.white);
        g2.setFont(fontText);
        g2.drawString(scoreString, x, y);
    }
}
