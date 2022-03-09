package dev.reeve.mazelib

import java.lang.IndexOutOfBoundsException

class Maze(private val sizeX: Int, private val sizeY: Int) {
	val points: Array<Array<MazePoint?>> = Array(sizeX) { arrayOfNulls(sizeY) }
	
	fun getPoint(position: MazePosition): MazePoint? {
		return points[position.x][position.y]
	}
	
	@Deprecated("Use without the unnecessary position field", ReplaceWith("points[position.x][position.y] = point"))
	fun setPoint(position: MazePosition, point: MazePoint?) {
		points[position.x][position.y] = point
	}
	
	fun setPoint(point: MazePoint) {
		points[point.position.x][point.position.y] = point
	}
	
	fun isPointSet(position: MazePosition): Boolean {
		return try {
			getPoint(position) != null
		} catch (e: IndexOutOfBoundsException) {
			true
		}
	}
	
	fun setOpen(point: MazePoint, direction: MazeDirection) {
		point.sides[direction.ordinal] = true
	}
	
	fun setOppositeOpen(point: MazePoint, direction: MazeDirection) {
		var index = direction.ordinal + 2
		if (index >= MazeDirection.values().size) {
			index -= MazeDirection.values().size
		}
		point.sides[index] = true
	}
}