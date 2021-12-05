package dev.reeve.mazelib.generators;

import dev.reeve.mazelib.Maze;
import dev.reeve.mazelib.MazePoint;
import dev.reeve.mazelib.MazePosition;
import lombok.val;
import lombok.var;

import java.util.Random;
import java.util.Stack;

public class RecursiveBacktrackGenerator extends MazeGenerator {
	private final Random random;
	
	public RecursiveBacktrackGenerator() {
		this(new Random());
	}
	
	public RecursiveBacktrackGenerator(Random random) {
		this.random = random;
	}
	
	@Override
	public Maze generateMaze(int sizeX, int sizeY) {
		var updateOrder = 0;
		val maze = new Maze(sizeX, sizeY);
		var positions = new Stack<MazePosition>();
		var currentLocation = new MazePosition(random.nextInt(sizeX), random.nextInt(sizeY));
		MazePosition next;
		
		positions.push(currentLocation);
		
		while (!positions.isEmpty()) {
			if (positions.indexOf(currentLocation) != positions.lastIndexOf(currentLocation)) {
				return maze;
			}
			
			currentLocation = positions.peek();
			
			val direction = generateDirection(maze, random, currentLocation);
			
			
			if (!maze.isPointSet(currentLocation)) {
				maze.setPoint(currentLocation, new MazePoint(currentLocation, updateOrder));
			}
			
			if (direction == null) {
				positions.pop();
				continue;
			}
			
			maze.setOpen(maze.getPoint(currentLocation), direction);
			
			maze.getPoint(currentLocation).setUpdateOrder(updateOrder);
			
			next = currentLocation.inDirection(direction);
			val newPoint = new MazePoint(next, updateOrder);
			maze.setPoint(next, newPoint);
			// make sure the wall to get back to the last square in the path is open
			maze.setOppositeOpen(newPoint, direction);
			
			positions.push(next.clone());
			
			updateOrder++;
		}
		
		return maze;
	}
}
