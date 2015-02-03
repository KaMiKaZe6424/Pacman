package pacman.main;

import java.util.ArrayList;
import java.util.List;

import pacman.obj.Collidable;
import pacman.obj.Field;
import pacman.src.Configuration;

public class Game {
	
	private static List<Collidable> objReg;
	private static long maxFps;
	
	private static Field field;
	
	private static Configuration config;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		config = new Configuration();
		maxFps = (long) config.get("maxFps", 30L);
		objReg = new ArrayList<Collidable>();
		setupField();
		
		
		
		update.start();
	}
	
	public static Configuration getConfiguration() {
		return config;
	}
	
	public static void addObjectToRegistry(Collidable c) {
		objReg.add(c);
	}
	
	public static void removeObjectFromRegistry(Collidable c) {
		objReg.remove(c);
	}
	
	public static List<Collidable> getObjectRegistry() {
		return objReg;
	}
	
	private void setupField() {
		field = new Field((int) config.get("columns", 10), (int) config.get("lines", 10));
		field.setVisible(true);
	}
	
	public static Thread update = new Thread(new Runnable() {
		
		@SuppressWarnings("static-access")
		@Override
		public void run() {
			while (true) {
				for (Collidable c : objReg) {
					c.update();
				}
				field.update();
				
				try {
					update.sleep(1000 / maxFps);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	});

	public static Field getField() {
		return field;
	}
	
}
