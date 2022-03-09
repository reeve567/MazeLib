package dev.reeve.mazelib

import dev.reeve.mazelib.MazeDirection
import java.util.ArrayList

/**
 * The directions you can look at neighbors with using things like [MazePosition.inDirection]
 * Order is starting at EAST, and going clockwise (ordinal of these are very important to a few functions like [MazePoint.setOppositeOpen].
 */
enum class MazeDirection(val x: Int, val y: Int) {
	EAST(1, 0), SOUTH(0, 1), WEST(-1, 0), NORTH(0, -1);
	
	companion object {
		@JvmStatic
		fun getDirections(east: Boolean, south: Boolean, west: Boolean, north: Boolean): List<MazeDirection> {
			val directions = booleanArrayOf(east, south, west, north)
			val list = ArrayList<MazeDirection>()
			for (i in 0..3) {
				if (directions[i]) list.add(values()[i])
			}
			return list
		}
	}
}