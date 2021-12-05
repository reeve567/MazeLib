package dev.reeve.mazelib;

import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@Getter
public class MazePosition implements Cloneable {
	private final int x;
	private final int y;
	
	public MazePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public MazePosition inDirection(@NonNull MazeDirection direction) {
		return new MazePosition(x + direction.getX(), y + direction.getY());
	}
	
	@Override
	public MazePosition clone() {
		return new MazePosition(x, y);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MazePosition)) return false;
		MazePosition that = (MazePosition) o;
		return x == that.x && y == that.y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public String toString() {
		return "MazePosition{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
