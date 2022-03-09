package dev.reeve.mazelib.solvers

import dev.reeve.mazelib.*

/**
 * A maze solver using the same algorithm as the generator by the name
 * Another way to do it might be using the [MazePoint.updateOrder] field, and checking the numbers between start & end, then looking for the biggest number at each path choice (provided it isn't greater than end)
 * Could just be a static function, but inheritance is nice and changing it to an `object` doesn't look too nice for Java users *afaik*
 */
class RecursiveBacktrackSolver : MazeSolver() {
	override fun generatePath(maze: Maze, start: MazePosition, end: MazePosition): MazePath {
		val path = MazePath(start)
		
		while (path.peek() != end) {
			val currentPos = path.peek()
			val open = maze.getPoint(currentPos)?.openSides() ?: throw IncompleteMazeException("$currentPos")
			
			if (open.isEmpty()) {
				path.pop()
				continue
			}
			
			path.add(currentPos.inDirection(open.random()))
		}
		
		return path
	}
}