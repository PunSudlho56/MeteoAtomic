
import java.awt.*;
import javax.swing.*;

public class Meteo implements Runnable {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int size = 45;
    private Image image;
    private Image explosionImage;
    private Scene owner;
    boolean alive = true;
    private boolean exploding = false;

    public Meteo(int x, int y, int dx, int dy, int imageNumber, Scene owner) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.owner = owner;
        image = new ImageIcon("images/" + imageNumber + ".png")
                .getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

        explosionImage = new ImageIcon("images/bomb.gif")
                .getImage();
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (alive) {
            if (owner.getWidth() <= size || owner.getHeight() <= size) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    return;
                }
                continue;
            }

            if (exploding) {
                owner.repaint();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }

                alive = false;
                owner.repaint();
                continue;
            }

            x = x + dx;
            y = y + dy;

            // ชนขอบแล้วสะท้อนกลับ
            if (x <= 0 || x + size >= owner.getWidth()) {
                dx = -dx;
                if (dx > 0) {
                    dx++;
                } else {
                    dx--;
                }
            }
            if (y <= 0 || y + size >= owner.getHeight()) {
                dy = -dy;
                if (dy > 0) {
                    dy++;
                } else {
                    dy--;
                }
            }

            owner.isColliding(this);

            owner.repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void explode() {
        exploding = true;
    }

    public boolean isExploding() {
        return exploding;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public Image getImage() {
        return image;
    }

    public Image getExplosionImage() {
        return explosionImage;
    }

}
//skgshilgfjdk