import javafx.scene.paint.Color;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

public class Identicon {
	
	private int CANVAS_HEIGHT = 250;
	private int CANVAS_WIDTH = 250;
	
	private String name;//input
	private byte[] hash;//16byte array
	private Color color;//
	private LinkedList<Integer> grid;
	private Map paintGrid;
	
	public Identicon(String name) {
		this.name = name;
		hash = hashInput(name);
		this.color = pickColor(hash);
		grid = buildGrid(hash);
		paintGrid = getOdd(grid);
	}
	private byte[] hashInput(String value) {
		byte[] hash = null;
		try {
			MessageDigest md5    = MessageDigest.getInstance("MD5");
			InputStream   stream = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
			DigestInputStream inputStream = new DigestInputStream(stream,
			                                                      md5);
			while (inputStream.read() != -1) ;
			hash = md5.digest();
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return hash;
	}
	
	private Color pickColor(byte[] hash) {
		return Color.rgb(Math.abs(hash[0]),
		                 Math.abs(hash[1]),
		                 Math.abs(hash[2]));
	}
	
	private LinkedList<Integer> buildGrid(byte[] hash) {
		LinkedList<Integer> grid = new LinkedList<>();
		for (int i = 0; i < hash.length && i + 3 <= hash.length; i += 3) {
			grid.add(Math.abs(hash[i]));
			grid.add(Math.abs(hash[i + 1]));
			grid.add(Math.abs(hash[i + 2]));
			grid.add(Math.abs(hash[i + 1]));
			grid.add(Math.abs(hash[i]));
		}
		return grid;
	}
	
	private Map<Integer, Integer> getOdd(List list) {
		Map<Integer, Integer> oddMap = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			if ((int) list.get(i) % 2 == 0) {
				oddMap.put(i,
				           (Integer) list.get(i));
			}
			else {
				oddMap.put(i,
				           0);
			}
		}
		return oddMap;
	}
	
	/*GETTER AND SETTER*/
	public Color getColor() {
		return color;
	}
	
	public Map getPaintGrid() {
		return paintGrid;
	}
}
