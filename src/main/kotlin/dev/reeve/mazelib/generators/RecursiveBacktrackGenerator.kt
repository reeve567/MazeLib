package dev.reeve.mazelib.generators

import dev.reeve.mazelib.*
import dev.reeve.mazelib.solvers.IncompleteMazeException
import java.util.*

/**
 * Generate a maze with a random walk, and backtracking when it finds a dead end.
 * @param mask Passed to [Maze]
 */
class RecursiveBacktrackGenerator(var mask: (MazePosition) -> Boolean = { false }, random: Random) : MazeGeneratorWithSolution(random) {
	override fun generateMaze(sizeX: Int, sizeY: Int): Maze {
		return generate(sizeX, sizeY, MazePosition(random.nextInt(sizeX), random.nextInt(sizeY)), null).first
	}
	
	private fun generate(sizeX: Int, sizeY: Int, start: MazePosition, end: MazePosition?): Pair<Maze, MazePath?> {
		val maze = Maze(sizeX, sizeY)
		val run = MazePath(start)
		var last: MazeDirection? = null
		var order = 0
		var path: MazePath? = null
		
		while (run.isNotEmpty()) {
			if (order > 1000) error("Maze took too many tries")
			
			val currentPos = run.peek()
			val direction = generateDirection(maze, currentPos)
			
			if (end != null && path == null && currentPos == end) {
				path = (run.clone() as MazePath)
			}
			
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
		
		return maze to path
	}
	
	override fun generateMaze(sizeX: Int, sizeY: Int, start: MazePosition, end: MazePosition): Pair<Maze, MazePath> {
		return generate(sizeX, sizeY, start, end).let { it.first to (it.second ?: throw IncompleteMazeException()) }
	}
}