package dev.reeve.mazelib.generators

import dev.reeve.mazelib.*
import dev.reeve.mazelib.solvers.IncompleteMazeException
import dev.reeve.mazelib.solvers.RecursiveBacktrackSolver
import kotlin.random.Random

/**
 * Generate a maze with a random walk, and backtracking when it finds a dead end.
 * @param mask [Maze.mask]
 * @see HuntAndKillGenerator
 */
class RecursiveBacktrackGenerator(var mask: MazeMask = { false }, random: Random = Random) : MazeGeneratorWithSolution(random) {
	override fun generateMaze(sizeX: Int, sizeY: Int): Maze {
		return generate(sizeX, sizeY, randomPosition(sizeX, sizeY, mask), null).first
	}
	
	private fun generate(sizeX: Int, sizeY: Int, start: MazePosition, end: MazePosition?): Pair<Maze, MazePath?> {
		val maze = Maze(sizeX, sizeY)
		val run = MazePath(start)
		var last: MazeDirection? = null
		var order = 0
		var path: MazePath? = null
		
		while (run.isNotEmpty()) {
			val currentPos = run.peek()
			val direction = generateUnsetDirection(maze, currentPos)
			
			if (end != null && path == null && currentPos == end) {
				path = (run.clone() as MazePath)
			}
			
			var new = false
			val point = maze.getPointOrNew(currentPos, -1) {
				it.updateOrder = order++
				new = true
				if (last != null) {
					it.setOppositeOpen(last!!)
				}
			}
			
			if (direction == null) {
				run.pop()
				continue
			}
			
			if (!new)
				point.updateOrder = order++
			
			point.setOpen(direction)
			
			last = direction
			run.add(currentPos.inDirection(direction))
		}
		
		return maze to path
	}
	
	/**
	 * This may add bias around the [start], so it may be worthwhile to instead use [RecursiveBacktrackSolver]
	 */
	override fun generateMaze(sizeX: Int, sizeY: Int, start: MazePosition, end: MazePosition): Pair<Maze, MazePath> {
		return generate(sizeX, sizeY, start, end).let { it.first to (it.second ?: throw IncompleteMazeException("Couldn't complete path")) }
	}
}