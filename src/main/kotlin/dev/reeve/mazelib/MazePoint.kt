package dev.reeve.mazelib

import dev.reeve.mazelib.solvers.RecursiveBacktrackSolver
import java.util.*

/**
 * A single point in the maze, which can be represented by a square with certain sides open.
 * Different from a [MazePosition], as it stores the sides, whereas this also stores the [MazePosition]
 * The sides of the point, true = open wall, false = closed.
 * @param updateOrder This field is mostly just used for displaying purposes, but is useful for [RecursiveBacktrackSolver]
 * @param sides If manually provided, it is required that the length of the array is 4 (hexagon grids & all coming soon hopefully?)
 * @see MazePosition
 */
class MazePoint(val position: MazePosition, var updateOrder: Int, val sides: BooleanArray = booleanArrayOf(false, false, false, false)) {
	init {
		require(sides.size == 4) { "Incorrect number of sides, expecting 4, got " + sides.size }
	}
	
	/**
	 * Just like the primary, just names all the sides.
	 * Sides first
	 */
	constructor(east: Boolean, south: Boolean, west: Boolean, north: Boolean, position: MazePosition, updateOrder: Int)
			: this(position, updateOrder, booleanArrayOf(east, south, west, north))
	
	/**
	 * Just like the primary, just names all the sides.
	 * Sides last
	 */
	constructor(position: MazePosition, updateOrder: Int, east: Boolean, south: Boolean, west: Boolean, north: Boolean)
			: this(east, south, west, north, position, updateOrder)
	
	var east: Boolean
		get() = sides[0]
		set(value) {
			sides[0] = value
		}
	var south: Boolean
		get() = sides[1]
		set(value) {
			sides[1] = value
		}
	var west: Boolean
		get() = sides[2]
		set(value) {
			sides[2] = value
		}
	var north: Boolean
		get() = sides[3]
		set(value) {
			sides[3] = value
		}
	
	/**
	 * Gives you which sides are open, using the [MazeDirection] enum instead of booleans.
	 */
	fun openSides(): Set<MazeDirection> {
		val directions = mutableSetOf<MazeDirection>()
		if (east) directions.add(MazeDirection.EAST)
		if (south) directions.add(MazeDirection.SOUTH)
		if (west) directions.add(MazeDirection.WEST)
		if (north) directions.add(MazeDirection.NORTH)
		return directions
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
	
	/**
	 * Sets the wall in the provided direction to be open
	 */
	fun setOpen(direction: MazeDirection) {
		sides[direction.ordinal] = true
	}
	
	/**
	 * Sets the wall opposite the provided direction to be open
	 */
	fun setOppositeOpen(direction: MazeDirection) {
		var index = direction.ordinal + 2
		if (index >= MazeDirection.values().size) {
			index -= MazeDirection.values().size
		}
		sides[index] = true
	}
}