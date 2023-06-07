package game;

import java.util.Random;

import utilidades.StdDraw;

public class Enemy {
	
	private double x=0;
	private double y=0;
	private int hp=1;

	
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
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	Enemy(double x,double y,int hp){
		this.hp=hp;
		this.y=y;
		this.x=x;
	}
	
	public void draw(){
		StdDraw.picture(x, y, "enemy.png");
	}
	public void moveDown(double movY) {
		this.y+=-movY;
	}
	public void moveX(double movX) {
		this.x+=movX;
	}
	
	}
