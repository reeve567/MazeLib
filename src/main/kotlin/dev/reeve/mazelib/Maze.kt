package dev.reeve.mazelib

import dev.reeve.mazelib.solvers.IncompleteMazeException

/**
 * The core of the library, housing all the data for a maze generated by the library.
 * This can also be used to make a maze to solve using one of the algorithms included in the library.
 * [points] starts at (0, 0) and considers ([sizeX], [sizeY]) to be south-east (how close it is to south-east directly depends on your dimensions).
 * @param sizeX The width of the maze, must be greater than 1
 * @param sizeY The height of the maze, must be greater than 1
 * @param mask Should return true if the position will be excluded
 * @see dev.reeve.mazelib.generators
 * @see dev.reeve.mazelib.solvers
 */
open class Maze(
	private val sizeX: Int,
	private val sizeY: Int,
	private val mask: MazeMask = { false },
	val points: MazePoints = Array(sizeX) { arrayOfNulls(sizeY) }
) {
	init {
		require(sizeX > 1) { "Width must be > 1" }
		require(sizeY > 1) { "Height must be > 1" }
	}
	
	fun getPoint(position: MazePosition, updateOrder: Int = -1): MazePoint? {
		return points[position.x][position.y]?.also {
			if (updateOrder != -1)
				it.updateOrder = updateOrder
		}
	}
	
	fun getPointOrNew(position: MazePosition, updateOrder: Int, ifValidForNew: (MazePoint) -> Unit = {}): MazePoint {
		return getPoint(position, updateOrder) ?: MazePoint(position, updateOrder).also {
			if (isPointValidForNew(position)) {
				setPoint(it)
				ifValidForNew.invoke(it)
			} else {
				throw IncompleteMazeException("$position")
			}
		}
	}
	
	fun isPointOutOfBounds(position: MazePosition): Boolean {
		return try {
			getPoint(position)
			mask.invoke(position)
		} catch (e: IndexOutOfBoundsException) {
			true
		}
	}
	
	fun isPointSet(position: MazePosition, includeBorder: Boolean = true): Boolean {
		return try {
			getPoint(position) != null
		} catch (e: IndexOutOfBoundsException) {
			includeBorder
		}
	}
	
	fun isPointValidForNew(position: MazePosition): Boolean {
		return try {
			!mask.invoke(position) && getPoint(position) == null
		} catch (e: IndexOutOfBoundsException) {
			false
		}
	}
	
	fun setPoint(point: MazePoint) {
		points[point.position.x][point.position.y] = point
	}
	
	fun countCells(): Int {
		var count = 0
		for (x in 0 until sizeX) {
			for (y in 0 until sizeY) {
				if (!mask.invoke(MazePosition(x, y))) count++
			}
		}
		return count
	}
}
