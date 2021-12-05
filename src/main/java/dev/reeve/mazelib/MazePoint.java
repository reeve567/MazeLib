package dev.reeve.mazelib;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * A single point in the maze, which can be represented by a square with open sides.
 */
public class MazePoint {
	/**
	 * The sides of the point, true = open wall, false = closed.
	 * Goes around the sides of the square clockwise, starting at the east side.
	 */
	@Getter
	private final boolean[] sides;
	@Getter
	private final MazePosition position;
	@Getter
	@Setter
	private int updateOrder;
	
	public MazePoint(MazePosition position, int updateOrder) {
		this(new boolean[]{false, false, false, false}, position, updateOrder);
	}
	
	public MazePoint(boolean[] sides, MazePosition position, int updateOrder) {
		this.position = position;
		if (sides.length != 4) {
			throw new IllegalArgumentException("Incorrect number of sides, expecting 4, got " + sides.length);
		}
		this.sides = sides;
		this.updateOrder = updateOrder;
	}
	
	public MazePoint(boolean east, boolean south, boolean west, boolean north, MazePosition position) {
		this.position = position;
		sides = new boolean[]{east, south, west, north};
	}
	
	public boolean isEastOpen() {
		return sides[0];
	}
	
	public boolean isSouthOpen() {
		return sides[1];
	}
	
	public boolean isWestOpen() {
		return sides[2];
	}
	
	public boolean isNorthOpen() {
		return sides[3];
	}
	
	@Override
	public String toString() {
		return "MazePoint{" +
				"sides=" + Arrays.toString(sides) +
				", position=" + position +
				", updateOrder=" + updateOrder +
				'}';
	}
}
