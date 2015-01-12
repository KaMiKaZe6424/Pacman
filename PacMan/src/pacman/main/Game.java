package pacman.main;

import java.util.ArrayList;
import java.util.List;

import pacman.obj.Collidable;
import pacman.obj.Field;

public class Game {
	
	private List<Collidable> objReg;
	private long maxFps;
	
	private static Field field;
	
	private static Configuration config;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		config = new Configuration();
		maxFps = (long) config.get("maxFps", 30);
		objReg = new ArrayList<Collidable>();
		setupField();
	}
	
	public static Configuration getConfiguration() {
		return config;
	}
	
	public void addObjectToRegistry(Collidable c) {
		objReg.add(c);
	}
	
	public void removeObjectFromRegistry(Collidable c) {
		objReg.remove(c);
	}
	
	private void setupField() {
		field = new Field((int) config.get("columns", 10), (int) config.get("lines", 10));
		field.createGrid();
	}
	
	Thread update = new Thread(new Runnable() {
		
		@SuppressWarnings("static-access")
		@Override
		public void run() {
			for (Collidable c : objReg) {
				c.update();
			}
			
			try {
				update.sleep(1000 / maxFps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	});
	
}
