package pacman.obj;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Field extends JFrame {
	
	private static final long serialVersionUID = -1865614660647747232L;
	
	int lines;
	int columns;
	
	private Square[] sqrs;
	
	private static List<Collidable> objectsToRender = new ArrayList<Collidable>();;
	
	public Field(int c, int l) {
		lines = l;
		columns = c;
		objectsToRender.clear();
	}
	
	
	public static void addObjectToRender(Collidable collidable) {
		objectsToRender.add(collidable);
	}
	
	public static void update() {
		
	}

	@Override
	public Graphics getGraphics() {
		return super.getGraphics();
	}
	
	public void createGrid() {
		sqrs = new Square[lines * columns];
		for (int i1 = 1; i1 <= columns ; i1++) {
			for (int i2 = 1; i2 <= lines ; i2++) {
				sqrs[i1 + i2 - 2] = new Square(i1, i2, this);
			}
		}
	}
	
}
