package dev.reeve.mazelib.generators

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazeDirection
import dev.reeve.mazelib.MazeDirection.Companion.getDirections
import dev.reeve.mazelib.MazeMask
import dev.reeve.mazelib.MazePosition
import kotlin.random.Random

abstract class MazeGenerator(protected val random: Random) {
	abstract fun generateMaze(sizeX: Int, sizeY: Int): Maze
	
	protected fun randomPosition(sizeX: Int, sizeY: Int, mask: MazeMask): MazePosition {
		var found: MazePosition
		do {
			found = MazePosition(random.nextInt(sizeX), random.nextInt(sizeY))
		} while (mask.invoke(found))
		return found
	}
	
	/**
	 * Checks all the positions around the [MazePosition] to see if there are unset squares, and if so it'll return one of those
	 */
	protected fun generateUnsetDirection(maze: Maze, position: MazePosition): MazeDirection? {
		// check all the cells in specific directions around the main position
		val east = maze.isPointValidForNew(position.inDirection(MazeDirection.EAST))
		val south = maze.isPointValidForNew(position.inDirection(MazeDirection.SOUTH))
		val west = maze.isPointValidForNew(position.inDirection(MazeDirection.WEST))
		val north = maze.isPointValidForNew(position.inDirection(MazeDirection.NORTH))
		val list = getDirections(east, south, west, north)
		
		// no places to go
		if (list.isEmpty()) return null else if (list.size == 1) return list[0]
		return list.random(random)
	}
	
	/**
	 * Checks all the positions around the [MazePosition] to see if there are set squares, and if so it'll return one of those
	 */
	protected fun generateSetDirection(maze: Maze, position: MazePosition): MazeDirection? {
		// check all the cells in specific directions around the main position
		val east = maze.isPointSet(position.inDirection(MazeDirection.EAST), false)
		val south = maze.isPointSet(position.inDirection(MazeDirection.SOUTH), false)
		val west = maze.isPointSet(position.inDirection(MazeDirection.WEST), false)
		val north = maze.isPointSet(position.inDirection(MazeDirection.NORTH), false)
		val list = getDirections(east, south, west, north)
		
		// no places to go
		if (list.isEmpty()) return null else if (list.size == 1) return list[0]
		return list.random()
	}
}