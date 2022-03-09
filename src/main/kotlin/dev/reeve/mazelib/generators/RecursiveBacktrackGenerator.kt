package dev.reeve.mazelib.generators

import dev.reeve.mazelib.*
import java.util.*

class RecursiveBacktrackGenerator(random: Random) : MazeGenerator(random) {
	override fun generateMaze(sizeX: Int, sizeY: Int): Maze {
		return generateMaze(sizeX, sizeY, MazePosition(random.nextInt(sizeX), random.nextInt(sizeY)))
	}
	
	fun generateMaze(sizeX: Int, sizeY: Int, start: MazePosition): Maze {
		val maze = Maze(sizeX, sizeY)
		val run = MazePath(start)
		var last: MazeDirection? = null
		var order = 0
		
		while (run.isNotEmpty()) {
			if (order > 1000) error("Maze took too many tries")
			
			val currentPos = run.peek()
			val direction = generateDirection(maze, currentPos)
			
			val point = maze.getPoint(currentPos)?.also {
				if (!maze.isPointSet(currentPos))
					it.updateOrder = order++
			} ?: MazePoint(position = currentPos, updateOrder = order++)
			
			if (!maze.isPointSet(currentPos)) {
				maze.setPoint(point)
				
				if (last != null) {
					maze.setOppositeOpen(point, last)
				}
			}
			
			if (direction == null) {
				run.pop()
				continue
			}
			
			maze.setOpen(point, direction)
			
			last = direction
			run.add(currentPos.inDirection(direction))
		}
		
		return maze
	}
}