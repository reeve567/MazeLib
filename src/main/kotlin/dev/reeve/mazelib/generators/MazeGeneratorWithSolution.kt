package dev.reeve.mazelib.generators

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazePath
import dev.reeve.mazelib.MazePosition
import java.util.*

abstract class MazeGeneratorWithSolution(random: Random) : MazeGenerator(random) {
	abstract fun generateMaze(sizeX: Int, sizeY: Int, start: MazePosition, end: MazePosition): Pair<Maze, MazePath>
}