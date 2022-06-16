package main;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean up1, up2, down1, down2;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            up1 = true;
        }
        if(code == KeyEvent.VK_S) {
            down1 = true;
        }
        if(code == KeyEvent.VK_UP) {
            up2 = true;
        }
        if(code == KeyEvent.VK_DOWN) {
            down2 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            up1 = false;
        }
        if(code == KeyEvent.VK_S) {
            down1 = false;
        }
        if(code == KeyEvent.VK_UP) {
            up2 = false;
        }
        if(code == KeyEvent.VK_DOWN) {
            down2 = false;
        }
    }
}
