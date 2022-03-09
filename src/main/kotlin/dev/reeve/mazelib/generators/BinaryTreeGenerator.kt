package dev.reeve.mazelib.generators

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazeDirection
import dev.reeve.mazelib.MazePosition
import kotlin.random.Random

class BinaryTreeGenerator(random: Random = Random) : MazeGenerator(random) {
	override fun generateMaze(sizeX: Int, sizeY: Int): Maze {
		val maze = Maze(sizeX, sizeY)
		var order = 0
		
		for (x in 0 until sizeX) {
			for (y in 0 until sizeY) {
				val turnOrder = order++
				val pos = MazePosition(x, y)
				
				val point = maze.getPointOrNew(pos, turnOrder)
				
				val north = pos.inDirection(MazeDirection.NORTH)
				val east = pos.inDirection(MazeDirection.EAST)
				
				if ((random.nextBoolean() || maze.isPointOutOfBounds(east)) && !maze.isPointOutOfBounds(north)) {
					val northPoint = maze.getPoint(north, turnOrder)!!
					northPoint.setOppositeOpen(MazeDirection.NORTH)
					
					point.setOpen(MazeDirection.NORTH)
				} else if (!maze.isPointOutOfBounds(east)) {
					val eastPoint = maze.getPointOrNew(east, turnOrder)
					eastPoint.setOppositeOpen(MazeDirection.EAST)
					
					point.setOpen(MazeDirection.EAST)
				}
			}
		}
		
		return maze
	}
}