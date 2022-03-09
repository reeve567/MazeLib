package dev.reeve.mazelib.generators

import dev.reeve.mazelib.MazeDirection.Companion.getDirections
import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazePosition
import dev.reeve.mazelib.MazeDirection
import java.util.*

abstract class MazeGenerator(protected val random: Random) {
	abstract fun generateMaze(sizeX: Int, sizeY: Int): Maze?
	protected fun generateDirection(maze: Maze, position: MazePosition): MazeDirection? {
		// check to make sure they aren't in the right-most row and that the cell to the right isn't already set
		val east = !maze.isPointSet(position.inDirection(MazeDirection.EAST))
		// check to make sure they aren't in the bottom row and that the cell below isn't already set
		val south = !maze.isPointSet(position.inDirection(MazeDirection.SOUTH))
		// check to make sure they aren't in the left-most row and that the cell to the left isn't already set
		val west = !maze.isPointSet(position.inDirection(MazeDirection.WEST))
		// check to make sure they aren't in the top row and that the cell above isn't already set
		val north = !maze.isPointSet(position.inDirection(MazeDirection.NORTH))
		val list = getDirections(east, south, west, north)
		
		// no places to go
		if (list.isEmpty()) return null else if (list.size == 1) return list[0]
		return list[random.nextInt(list.size)]
	}
}