package dev.reeve.mazelib;

import lombok.Getter;
import lombok.NonNull;
import lombok.var;

@Getter
public class Maze {
	private final MazePoint[][] points;
	private final int sizeX;
	private final int sizeY;
	
	public Maze(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.points = new MazePoint[sizeX][sizeY];
	}
	
	public MazePoint getPoint(@NonNull MazePosition position) {
		return points[position.getX()][position.getY()];
	}
	
	public void setPoint(@NonNull MazePosition position, @NonNull MazePoint point) {
		points[position.getX()][position.getY()] = point;
	}
	
	public boolean isPointSet(@NonNull MazePosition position) {
		try {
			return getPoint(position) != null;
		} catch (IndexOutOfBoundsException e) {
			return true;
		}
	}
	
	public void setOpen(@NonNull MazePoint point, @NonNull MazeDirection direction) {
		point.getSides()[direction.ordinal()] = true;
	}
	
	public void setOppositeOpen(@NonNull MazePoint point, @NonNull MazeDirection direction) {
		var index = direction.ordinal() + 2;
		if (index >= MazeDirection.values().length) {
			index -= MazeDirection.values().length;
		}
		
		point.getSides()[index] = true;
	}
}
