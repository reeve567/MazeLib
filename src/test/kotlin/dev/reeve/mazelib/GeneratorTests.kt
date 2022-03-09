package dev.reeve.mazelib

import dev.reeve.mazelib.generators.RecursiveBacktrackGenerator
import dev.reeve.mazelib.solvers.IncompleteMazeException
import org.junit.jupiter.api.Test
import kotlin.random.Random

class GeneratorTests {
	private val random
		get() = Random(12535)
	
	@Test
	fun testRecursiveBacktrackGen() {
		val maze = Maze(
			2, 2, points = arrayOf(
				arrayOf(
					MazePoint(true, true, false, false, MazePosition(0, 0), 2),
					MazePoint(false, false, false, true, MazePosition(0, 1), 3)
				),
				arrayOf(
					MazePoint(false, true, true, false, MazePosition(1, 0), 1),
					MazePoint(false, false, false, true, MazePosition(1, 1), 0)
				),
			)
		)
		
		val results = RecursiveBacktrackGenerator(random = random).generateMaze(2, 2)
		
		for ((x, body) in results.points.withIndex()) {
			println("x: $x")
			for ((y, body) in body.withIndex()) {
				println("y: $y, {$body}")
			}
		}
		
		assert(maze.points.mapIndexed { index1, mazePoints -> mazePoints.mapIndexed { index2, mazePoint -> mazePoint == results.points[index1][index2] } }.all { it.all { it } })
	}
	
	@Test
	fun testRecursiveBacktrackGenSolution() {
		val path = MazePath(MazePosition(0, 0), MazePosition(0,1), MazePosition(1,1))
		val results = RecursiveBacktrackGenerator(random = random).generateMaze(2, 2, MazePosition(0,0), MazePosition(1, 1))
		
		println(results.second)
		
		assert(path == results.second)
	}
	
	@Test
	fun testRecursiveBacktrackGenSolutionInvalidArgs() {
		var ret = false
		
		try {
			RecursiveBacktrackGenerator(random = random).generateMaze(2, 2, MazePosition(0,0), MazePosition(2, 2))
		} catch (e: IncompleteMazeException) {
			ret = true
		}
		
		assert(ret)
	}
}