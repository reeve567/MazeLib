package dev.reeve.mazelib

import java.util.*

typealias MazeMask = (MazePosition) -> Boolean
typealias MazePoints = Array<Array<MazePoint?>>

class MazePath(vararg positions: MazePosition): Stack<MazePosition>() {
	init {
		addAll(positions)
	}
}