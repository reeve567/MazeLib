package dev.reeve.mazelib.generators;

import dev.reeve.mazelib.Maze;
import dev.reeve.mazelib.MazeDirection;
import dev.reeve.mazelib.MazePosition;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.Random;

@Getter
public abstract class MazeGenerator {
	static int test = 0;
	
	public abstract Maze generateMaze(int sizeX, int sizeY);
	
	protected MazeDirection generateDirection(@NonNull Maze maze, @NonNull Random random, @NonNull MazePosition position) {
		// check to make sure they aren't in the right-most row and that the cell to the right isn't already set
		val east = !maze.isPointSet(position.inDirection(MazeDirection.EAST));
		// check to make sure they aren't in the bottom row and that the cell below isn't already set
		val south = !maze.isPointSet(position.inDirection(MazeDirection.SOUTH));
		// check to make sure they aren't in the left-most row and that the cell to the left isn't already set
		val west = !maze.isPointSet(position.inDirection(MazeDirection.WEST));
		// check to make sure they aren't in the top row and that the cell above isn't already set
		val north = !maze.isPointSet(position.inDirection(MazeDirection.NORTH));
		
		val list = MazeDirection.getDirections(east, south, west, north);
		
		// no places to go
		if (list.isEmpty()) return null;
		else if (list.size() == 1) return list.get(0);
		
		return list.get(random.nextInt(list.size()));
	}
}
