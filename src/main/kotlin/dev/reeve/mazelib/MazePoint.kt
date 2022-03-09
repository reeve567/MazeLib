package dev.reeve.mazelib

import java.util.ArrayList
import java.util.Arrays

/**
 * A single point in the maze, which can be represented by a square with open sides.
 * The sides of the point, true = open wall, false = closed.
 */
class MazePoint(val position: MazePosition, var updateOrder: Int, val sides: BooleanArray = booleanArrayOf(false, false, false, false)) {
	
	init {
		require(sides.size == 4) { "Incorrect number of sides, expecting 4, got " + sides.size }
	}
	
	constructor(east: Boolean, south: Boolean, west: Boolean, north: Boolean, position: MazePosition, updateOrder: Int)
			: this(position, updateOrder, booleanArrayOf(east, south, west, north))
	
	var east: Boolean
		get() = sides[0]
		set(value) { sides[0] = value }
	var south: Boolean
		get() = sides[1]
		set(value) { sides[1] = value }
	var west: Boolean
		get() = sides[2]
		set(value) { sides[2] = value }
	var north: Boolean
		get() = sides[3]
		set(value) { sides[3] = value }
	
	fun openSides(): Array<MazeDirection> {
		val directions = ArrayList<MazeDirection>()
		if (north) directions.add(MazeDirection.NORTH)
		if (south) directions.add(MazeDirection.SOUTH)
		if (east) directions.add(MazeDirection.EAST)
		if (west) directions.add(MazeDirection.WEST)
		return directions.toTypedArray()
	}
	
	override fun toString(): String {
		return "MazePoint{" +
				"sides=" + Arrays.toString(sides) +
				", position=" + position +
				", updateOrder=" + updateOrder +
				'}'
	}
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is MazePoint) return false
		
		if (!sides.contentEquals(other.sides)) return false
		if (position != other.position) return false
		if (updateOrder != other.updateOrder) return false
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = sides.contentHashCode()
		result = 31 * result + position.hashCode()
		result = 31 * result + updateOrder
		return result
	}
	
	fun setOpen(direction: MazeDirection) {
		sides[direction.ordinal] = true
	}
	
	fun setOppositeOpen(direction: MazeDirection) {
		var index = direction.ordinal + 2
		if (index >= MazeDirection.values().size) {
			index -= MazeDirection.values().size
		}
		sides[index] = true
	}
}