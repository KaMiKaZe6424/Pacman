package pacman.obj;

import javax.swing.JPanel;

public abstract class Collidable extends JPanel {
	
	private int speed;
	
	private String direction;
	
	private int px;
	private int py;
	
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
		switch (direction) {
		case "up": py -= speed;
		case "down": py += speed;
		case "left": px -= speed;
		case "right": px += speed;
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
	
}
