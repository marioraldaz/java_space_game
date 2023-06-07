package game;

import utilidades.StdDraw;

public class Bullet {
	private double x=0;
	private double y=0;

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
	
	Bullet(double x,double y){
		this.x=x;
		this.y=y;
	}
	public void move() {
		this.y+=10;
	}
	public void draw() {
		StdDraw.picture(x, y, "bullet.png");
	}
	public void drawInverted() {
		StdDraw.picture(x, y, "bullet.png", 180);
	}
	public boolean hit(Enemy e) {
		if((e.getX()>(this.x-7) && e.getX()<(this.x+7)) && (e.getY()>(this.y-7) && e.getY()<(this.y+7)))
			return true;
		else
			return false;
	}
	public void initialPos(Spaceship ship) {
		this.x=ship.getX();
		this.y=ship.getY();
	}
}
