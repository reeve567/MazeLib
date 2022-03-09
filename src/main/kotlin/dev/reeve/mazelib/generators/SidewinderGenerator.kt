package dev.reeve.mazelib.generators

import dev.reeve.mazelib.*
import kotlin.random.Random

/**
 * Similar to [BinaryTreeGenerator], except it only has one clear row at the top, and a bias only up.
 * Only has one exit north for each area of a row, so it may be more useful to try going the opposite direction, where there could be as many as sizeY exits from the row.
 */
class SidewinderGenerator(random: Random = Random) : MazeGenerator(random) {
	override fun generateMaze(sizeX: Int, sizeY: Int): Maze {
		val maze = Maze(sizeX, sizeY)
		
		var updateOrder = 0
		val run = MazePath()
		
		for (x in 0 until sizeX) {
			for (y in 0 until sizeY) {
				val turnOrder = updateOrder++
				val point = maze.getPointOrNew(MazePosition(x,y), turnOrder)
				run.add(point.position)
				
				if (y == 0 || (random.nextBoolean() && x != sizeX - 1))
					point.setOpen(MazeDirection.EAST)
				else {
					// Should always be a valid point returned, since this algo starts at the north-most row
					val northCarved = maze.getPointOrNew(run.random(), turnOrder)
					northCarved.setOpen(MazeDirection.NORTH)
					run.clear()
				}
			}
		}
		
		return maze
	}
}