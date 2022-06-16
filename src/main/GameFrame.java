package main;

import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gp;

    GameFrame() {
        gp = new GamePanel();

        this.setTitle("Pong game");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gp);
        this.pack();
        this.setVisible(true);

        gp.startGame();
    }
}
