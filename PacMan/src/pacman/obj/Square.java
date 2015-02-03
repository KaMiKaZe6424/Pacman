package pacman.obj;

import java.awt.Color;
import java.awt.Graphics;

import pacman.main.Game;

public class Square {
	
	public int x;
	public int y;
	
	static int size = 30;
	
	private Field f;
	
	Collidable contains = null;
	
	private boolean top;
	private boolean left;
	private boolean bottom;
	private boolean right;
	
	public Square(int x, int y, Field f) {
		this.f = f;
		this.x = x;
		this.y = y;
	}
	
	public void paintField() {
		Graphics g = f.getFieldGraphics();
		if (top) {
			g.drawRect(x*size, y*size, 30, 5);
		}
		if (left) {
			g.drawRect(x*size, y*size, 5, 30);
		}
		if (bottom) {
			g.drawRect(x*size, y*size + 25, 30, 5);
		}
		if (right) {
			g.drawRect(x*size + 25, y*size, 5, 30);
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isWall(String dir) {
		if (y == 1 || y == f.getLines() || x % f.getColumns() == 1 || x % f.getColumns() == 0) {
			return true;
		}
		switch (dir) {
		case "u": return top;
		case "d": return bottom;
		case "l": return left;
		case "r": return right;
		default: return false;
		}
	}

	public void paintBG(Color c) {
		Game.getField().getFieldGraphics().setColor(c);
		Game.getField().getFieldGraphics().fillRect((x-1)*size, (y-1)*size, size, size);
	}
}
