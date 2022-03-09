package dev.reeve.mazelib.generators

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazeDirection
import dev.reeve.mazelib.MazePosition
import kotlin.random.Random

/**
 * The simplest algorithm for a maze, but has a lot of bias.
 * Flips a coin to see if it'll end a run and connect north, or continue a run and connect east.
 * When it gets to the east-most wall, it has to go north, creating the east corridor
 * When it's at the north-most wall, it can't go north, so it will go east
 * This creates a bias that makes it easier to go up, but harder to go down (more choice).
 * @param bias Allows you to pick which sides the bias will be on, and control the flow. Using the same direction for both will get you unintended results (if it even works).
 */
class BinaryTreeGenerator(private val bias: Pair<MazeDirection, MazeDirection> = MazeDirection.NORTH to MazeDirection.EAST, random: Random = Random) :
	MazeGenerator(random) {
	override fun generateMaze(sizeX: Int, sizeY: Int): Maze {
		val maze = Maze(sizeX, sizeY)
		var order = 0
		
		for (x in 0 until sizeX) {
			for (y in 0 until sizeY) {
				val turnOrder = order++
				val pos = MazePosition(x, y)
				
				val point = maze.getPointOrNew(pos, turnOrder)
				
				val dir1 = pos.inDirection(bias.first)
				val dir2 = pos.inDirection(bias.second)
				
				if ((random.nextBoolean() || maze.isPointOutOfBounds(dir2)) && !maze.isPointOutOfBounds(dir1)) {
					val dir1Point = maze.getPoint(dir1, turnOrder)!!
					dir1Point.setOppositeOpen(bias.first)
					
					point.setOpen(bias.first)
				} else if (!maze.isPointOutOfBounds(dir2)) {
					val dir2Point = maze.getPointOrNew(dir2, turnOrder)
					dir2Point.setOppositeOpen(bias.second)
					
					point.setOpen(bias.second)
				}
			}
		}
		
		return maze
	}
}