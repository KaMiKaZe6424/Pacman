package pacman.obj;

import javax.swing.JPanel;

public abstract class Collidable extends JPanel {
	
	private int speed;
	
	private String direction = "r";
	
	private int px;
	private int py;
	
	private int lastx;
	private int lasty;
	
	private static final long serialVersionUID = -652719543772601926L;
	
	public Collidable(int x, int y) {
		px = x;
		py = y;
	}
	
	public abstract void update();
	
	public void setSpeed(int v) {
		speed = v;
	}
	
	public void setPosition() {
		lastx = px;
		lasty = py;
		System.out.println(direction + ", " + px + ", " + py);
		switch (direction) {
		case "u": py -= speed;
		case "d": py += speed;
		case "l": px -= speed;
		case "r": px += speed;
		}
		Field.addObjectToRender(this);
	}
	
	public final int getPosX() {
		return px;
	}
	
	public final int getPosY() {
		return py;
	}
	
	public void setDirection(String dir) {
		direction = dir;
	}
	
	public String getDirection() {
		return direction;
	}

	public abstract void draw();

	public int getLastX() {
		return lastx;
	}

	public int getLastY() {
		return lasty;
	}
	
}
