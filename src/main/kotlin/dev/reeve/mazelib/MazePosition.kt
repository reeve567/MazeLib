package dev.reeve.mazelib

import java.util.Objects

class MazePosition(val x: Int, val y: Int) : Cloneable {
	fun inDirection(direction: MazeDirection): MazePosition {
		return MazePosition(x + direction.x, y + direction.y)
	}
	
	public override fun clone(): MazePosition {
		return MazePosition(x, y)
	}
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is MazePosition) return false
		return x == other.x && y == other.y
	}
	
	override fun hashCode(): Int {
		return Objects.hash(x, y)
	}
	
	override fun toString(): String {
		return "MazePosition{" +
				"x=" + x +
				", y=" + y +
				'}'
	}
}