package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utilidades.StdAudio;
import utilidades.StdDraw;

public class Interfaz {
	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 550);
		StdDraw.setXscale(-100, 100);
		StdDraw.setYscale(-100, 100);
		StdDraw.enableDoubleBuffering();
		Font smallFont = new Font("Sans Serif", Font.BOLD, 20);
		int nEnemies = 5;
		List<Enemy> enemies = new ArrayList<Enemy>();
		Spaceship ship = new Spaceship();
		boolean bulletMoving = false;
		List<Bullet> bullets = new ArrayList<Bullet>();
		Bullet bullet1 = new Bullet(ship.getX(), ship.getY());
		Bullet bullet2 = new Bullet(ship.getX(), ship.getY());

		boolean firstTime = true;
		boolean normalMode = false;
		boolean fightMode = false;
		boolean speedMode = false;
		boolean bossMode = false;
		boolean lost = true;
		boolean newEnemies = false;
		double speed = 0.3;
		int lvl = 0;
		for (;;) {
			while (lost) {
				for (int i = enemies.size() - 1; i >= 0; i--) {
					enemies.remove(i);
					nEnemies = 5;
					speed = 0.3;
					lvl = 0;
				}
				StdDraw.setPenColor(Color.black);
				StdDraw.filledSquare(0, 0, 100);
				if(!firstTime)
					lose();
				StdDraw.show();
				if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
					lost = false;
				}
				if (StdDraw.isKeyPressed(KeyEvent.VK_M) || firstTime) {
					normalMode = false;
					speedMode = false;
					bossMode = false;
					fightMode = false;
					while (!normalMode && !speedMode && !bossMode && !fightMode) {
						StdDraw.setPenColor(Color.black);
						StdDraw.filledSquare(0, 0, 100);
						StdDraw.setPenColor(Color.MAGENTA);
						menu();
						StdDraw.show();
						if (StdDraw.isKeyPressed(KeyEvent.VK_1))
							normalMode = true;
						if (StdDraw.isKeyPressed(KeyEvent.VK_2))
							speedMode = true;
						if (StdDraw.isKeyPressed(KeyEvent.VK_4))
							bossMode = true;
						if (StdDraw.isKeyPressed(KeyEvent.VK_3))
							fightMode = true;
					}
					lost = false;
					firstTime=false;
				}
				if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE))
					System.exit(0);
			}
			StdDraw.setPenColor(Color.MAGENTA);
			StdDraw.line(-100, -80, 100, -80);
			ship.draw();
			StdDraw.setFont(smallFont);
			StdDraw.text(-90, 90, "LEVEL: " + Integer.toString(lvl));
			if (bullet1Moving)
				bullet1.draw();
			if (bullet1.getY() > 100)
				bulletMoving = false;
			if (bullet2Moving)
				bullet2.draw();
			if (bullet2.getY() > 100)
				bulletMoving = false;

			if (normalMode) {
				if (newEnemies) {
					lvl++;
					for (int i = 0; i < nEnemies; i++) {
						double x = Math.random() * 95 - 90;
						enemies.add(new Enemy(x, 90, 1));
					}
					newEnemies = false;
				}

				if (enemies.size() == 0) {
					newEnemies = true;
					nEnemies++;
					speed += 0.1;
				}
			}
			if (speedMode) {
				if (newEnemies) {
					lvl++;
					for (int i = 0; i < nEnemies; i++) {
						double x = Math.random() * 95 - 90;
						enemies.add(new Enemy(x, 90, 1));
					}
					newEnemies = false;
					speed += 0.2;
				}

				if (enemies.size() == 0) {
					newEnemies = true;
					nEnemies++;
					speed += 0.1;
				}
			}

			for (int i = enemies.size() - 1; i >= 0; i--) {
				enemies.get(i).moveDown(speed);
				enemies.get(i).draw();
				if (enemies.get(i).getY() < -75)
					lost = true;
				if (bullet1.hit(enemies.get(i)) || bullet2.hit(enemies.get(i))) {
					StdAudio.playInBackground("enemieDie.wav");
					enemies.remove(i);
					bulletMoving = false;
				}
			}

			if (fightMode) {
				StdDraw.setPenColor(Color.magenta);
				StdDraw.text(0, 0, "HP: " + Double.toString(ship.getHp()));
			}

			if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !bulletMoving) {
				StdAudio.playInBackground("shot.wav");
				bullet.initialPos(ship);
				bulletMoving = true;
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && ship.getX() < 90)
				ship.setX(ship.getX() + 3);
			if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && ship.getX() > -90)
				ship.setX(ship.getX() - 3);

			if (bulletMoving)
				bullet.move();
			if (lost)
				lost = true;

			StdDraw.show();
			StdDraw.pause(30);
			StdDraw.clear();
		}

	}

	public static void menu() {
		Font bigFont = new Font("Sans Serif", Font.BOLD, 50);
		Font smallFont = new Font("Sans Serif", Font.BOLD, 30);
		StdDraw.setPenColor(Color.magenta);
		StdDraw.setFont(bigFont);
		StdDraw.text(0, 80, "WELCOME TO SPACEWARS");
		StdDraw.setFont(smallFont);
		StdDraw.text(0, 40, "PRESS 1 FOR NORMAL MODE");
		StdDraw.text(0, 10, "PRESS 2 FOR SPEED MODE");
		StdDraw.text(0, -20, "PRESS 3 FOR FIGHT MODE");
		StdDraw.text(0, -50, "PRESS 4 FOR BOSS MODE");

	}

	public static void lose() {
		StdDraw.setPenColor(Color.MAGENTA);

		StdDraw.text(0, 0, "GAME OVER");
		StdDraw.text(0, -30, "PRESS ENTER TO PLAY AGAIN");
		StdDraw.text(0, -50, "PRESS M TO GO BACK TO THE MENU");
		StdDraw.text(0, -70, "PRESS ESC TO EXIT");
		StdAudio.playInBackground("gameOver.wav");
	}

	public static void win() {
		StdDraw.setPenColor(Color.MAGENTA);

		StdDraw.text(0, 0, "YOU WIN");
		StdAudio.playInBackground("youWin.wav");
	}
}
