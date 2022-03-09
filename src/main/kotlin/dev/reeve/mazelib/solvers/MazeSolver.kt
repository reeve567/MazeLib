package dev.reeve.mazelib.solvers

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazePath
import dev.reeve.mazelib.MazePosition

abstract class MazeSolver {
	abstract fun generatePath(maze: Maze, start: MazePosition, end: MazePosition): MazePath
}