package dev.reeve.mazelib

import java.util.ArrayList
import java.util.Arrays

/**
 * A single point in the maze, which can be represented by a square with open sides.
 * The sides of the point, true = open wall, false = closed.
 */
class MazePoint(val sides: BooleanArray = booleanArrayOf(false, false, false, false), val position: MazePosition, var updateOrder: Int) {
	
	init {
		require(sides.size == 4) { "Incorrect number of sides, expecting 4, got " + sides.size }
	}
	
	constructor(east: Boolean, south: Boolean, west: Boolean, north: Boolean, position: MazePosition, updateOrder: Int)
			: this(booleanArrayOf(east, south, west, north), position, updateOrder)
	
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
}