package dev.reeve.mazelib.solvers

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazePath
import dev.reeve.mazelib.MazePosition
import java.util.*

class RecursiveBacktrackSolver(private val random: Random) : MazeSolver() {
	override fun generatePath(maze: Maze, start: MazePosition, end: MazePosition): MazePath {
		val path = MazePath()
		
		while (path.peek() != end) {
			val currentPos = path.peek()
			val open = maze.getPoint(currentPos)?.openSides() ?: throw IncompleteMazeException()
			
			if (open.isEmpty()) {
				path.pop()
				continue
			}
			
			path.add(currentPos.inDirection(open.random()))
		}
		
		return path
	}
}