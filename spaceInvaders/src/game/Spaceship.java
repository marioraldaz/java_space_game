package game;

import utilidades.StdDraw;

public class Spaceship {
	private double x=0;
	private double y=-90;
	private double hp=10;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getHp() {
		return hp;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}
	
	public void draw() {
		StdDraw.picture(x, y, "nave.png");
	}
}
