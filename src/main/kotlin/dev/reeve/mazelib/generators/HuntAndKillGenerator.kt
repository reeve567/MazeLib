package dev.reeve.mazelib.generators

import dev.reeve.mazelib.Maze
import dev.reeve.mazelib.MazeMask
import dev.reeve.mazelib.MazePosition
import kotlin.random.Random

/**
 * Generate a maze with a random walk, and then looping over all cells when it runs out.
 * @param mask Passed to [Maze]
 */
class HuntAndKillGenerator(var mask: MazeMask = { false }, random: Random = Random) : MazeGenerator(random) {
	override fun generateMaze(sizeX: Int, sizeY: Int): Maze {
		val maze = Maze(sizeX, sizeY, mask)
		var countLeft = maze.countCells()
		var pos: MazePosition? = randomPosition(sizeX, sizeY, mask)
		var order = 0
		
		while (countLeft > 0) {
			if (pos == null) {
				// hunt
				x@for (x in 0 until sizeX) {
					y@for (y in 0 until sizeY) {
						val huntPos = MazePosition(x, y)
						
						if (maze.isPointSet(huntPos)) continue
						
						val dir = generateSetDirection(maze, huntPos)
						
						if (dir != null) {
							pos = huntPos.inDirection(dir)
							break@x
						}
					}
				}
			} else {
				// kill
				val turnOrder = order++
				val point = maze.getPointOrNew(pos, turnOrder) {
					countLeft--
				}
				
				val dir = generateUnsetDirection(maze, pos)
				
				pos = if (dir != null) {
					point.setOpen(dir)
					
					val nextPos = pos.inDirection(dir)
					val nextPoint = maze.getPointOrNew(nextPos, turnOrder) {
						countLeft--
					}
					nextPoint.setOppositeOpen(dir)
					nextPos
				} else {
					null
				}
			}
		}
		
		return maze
	}
}