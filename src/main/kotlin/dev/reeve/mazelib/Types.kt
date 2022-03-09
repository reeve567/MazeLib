package dev.reeve.mazelib

import java.util.*

/**
 * Represents a mask that you can apply to shape the grid, for example, to cut out every square possible it'd just return `true`
 * @sample makeMask
 */
typealias MazeMask = (MazePosition) -> Boolean

/**
 * An example of how to use [MazeMask]
 */
private fun makeMask() {
	val one: MazeMask = {
		it.x + it.y > 2 // this would result in some sort of triangle missing in the north-west corner
	}
	val two: MazeMask = {
		it.x > 2 || it.y > 2 // this would result in a square missing in the north-west corner
	}
}

/**
 * Represents a 2D array of maze information, each point containing the side information and location
 * @see[MazePoint]
 */
typealias MazePoints = Array<Array<MazePoint?>>

/**
 * Represents an ordered array of positions in the maze
 * @param positions Initial positions to add to the stack, purely for convenience
 * @see[MazePosition]
 */
class MazePath(vararg positions: MazePosition): Stack<MazePosition>() {
	init {
		addAll(positions)
	}
}