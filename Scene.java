import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Scene extends JPanel {
    private ArrayList<Meteo> meteos = new ArrayList<Meteo>();
    private Random random = new Random();
    public Scene(int number) {
        setBackground(Color.BLACK);

        for (int i = 0; i < number; i++) {
			int x = random.nextInt(650) + 20;
			int y = random.nextInt(450) + 20;
			int dx = random.nextInt(5) - 2;
			int dy = random.nextInt(5) - 2;

			// ไม่ให้ลูกใดหยุด
			if (dx == 0 && dy == 0) {
				dx = 1;
			}

			Meteo meteor = new Meteo(x, y, dx, dy, (i % 10) + 1, this);
			meteos.add(meteor);
			meteor.start();
		}
    }

    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Meteo meteor : meteos) {
			if (!meteor.isAlive()) { //เช็คว่า meteo เดรสยัง
        		continue;
    		}

			if (meteor.isExploding()){ //เช็คว่า meteo ชนกันเอง ถ้าชนวาด gif
				g.drawImage(
                    meteor.getExplosionImage(),meteor.getX(),meteor.getY(),meteor.getSize(),meteor.getSize(),this);
			} else {
				g.drawImage(meteor.getImage(), meteor.getX(), meteor.getY(), this);
			}
			
		}
	}

    public synchronized boolean isColliding(Meteo current) {
		if (current.isExploding()){ //ไม่ให้ตัวที่กำลังระเบิดไปชนกับตัวอื่น
			return false;
		}

		for (Meteo meteor : meteos) {
			if (meteor != current
					&& !meteor.isExploding()
					&& Math.abs(current.getX() - meteor.getX()) < current.getSize()
					&& Math.abs(current.getY() - meteor.getY()) < current.getSize()) {

				meteor.explode();

				return true;
			}
		}
		return false;
	}
}