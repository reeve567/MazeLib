package dev.reeve.mazelib

import java.util.*

class MazePath(vararg positions: MazePosition): Stack<MazePosition>() {
	init {
		addAll(positions)
	}
}