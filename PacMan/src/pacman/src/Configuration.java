package pacman.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.ho.yaml.Yaml;

public class Configuration extends HashMap<String, Object> {

	private static final long serialVersionUID = -7323921253507045989L;
	
	private File cfile;
	
	public Configuration() {
		cfile = new File(System.getProperty("user.home") + "/.pacman/config.yml");
		load();
	}
	
	public Object get(Object key, Object def) {
		if (containsKey(key)) {
			return super.get(key);
		} else {
			super.put(String.valueOf(key), def);
			save();
			return def;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		if (cfile.exists()) {
			try {
				this.putAll((HashMap<String, Object>) Yaml.load(cfile));
			} catch (FileNotFoundException e) {}
		}
	}
	
	private void save() {
		try {
			if (!cfile.exists()) {
				new File(System.getProperty("user.home") + "/.pacman").mkdir();
				cfile.createNewFile();
			}
			Yaml.dump(this, cfile, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
