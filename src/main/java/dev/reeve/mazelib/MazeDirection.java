package dev.reeve.mazelib;

import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum MazeDirection {
	EAST(1, 0),
	SOUTH(0, 1),
	WEST(-1, 0),
	NORTH(0, -1);
	
	private final int x;
	private final int y;
	
	MazeDirection(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static List<MazeDirection> getDirections(boolean east, boolean south, boolean west, boolean north) {
		val directions = new boolean[]{east, south, west, north};
		val list = new ArrayList<MazeDirection>();
		
		for (int i = 0; i < 4; i++) {
			if (directions[i])
				list.add(MazeDirection.values()[i]);
		}
		
		return list;
	}
}
