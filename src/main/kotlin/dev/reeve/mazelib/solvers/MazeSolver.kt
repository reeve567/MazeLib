package dev.reeve.mazelib.solvers

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazePath
import dev.reeve.mazelib.MazePosition
import dev.reeve.mazelib.generators.MazeGenerator

/**
 * The base class for a solving algorithm
 * @see MazeGenerator
 */
abstract class MazeSolver {
	abstract fun generatePath(maze: Maze, start: MazePosition, end: MazePosition): MazePath
}