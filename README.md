# MazeLib
A library for the JVM for maze algorithms

A neat little library I plan to upkeep in my free time, as I learn about different maze algorithms

As of now, there are four algorithms (Generator/Solver):
* (G)   BinaryTree (North-Eastern bias, y = 0 & x = max - 1) (~25% dead ends)
* (G)   Sidewinder (Upward bias, y = 0) (~25% dead ends)
* (G/S) RecursiveBacktrack (Unbiased, more memory needed `vs HuntAndKill`) (~10% dead ends)
* (G)   HuntAndKill (Unbiased, more time needed `vs RecursiveBacktrack`) (~10% dead ends)

A little visual demo is provided in the tests dir (java/dev.reeve.mazelib/GridTest). It uses RecursiveBacktrack by default, but it's trivial to switch to another.

Some code to get a 2x2 going:

Code shown is Kotlin, but it should work with any language on the JVM.
```Kotlin
import dev.reeve.mazelib.MazePosition
import dev.reeve.mazelib.generators.RecursiveBacktrackGenerator
import kotlin.random.Random

val generator = RecursiveBacktrackGenerator(random = Random(567))

val result = generator.generateMaze(2, 2, MazePosition(0, 0), MazePosition(1, 1))

val maze = result.first
println(maze.points.joinToString("\n") { it.joinToString() })
// MazePoint{sides=[true, false, false, false], position=MazePosition{x=0, y=0}, updateOrder=0}, MazePoint{sides=[true, false, false, false], position=MazePosition{x=0, y=1}, updateOrder=3}
// MazePoint{sides=[false, true, true, false], position=MazePosition{x=1, y=0}, updateOrder=1}, MazePoint{sides=[false, false, true, true], position=MazePosition{x=1, y=1}, updateOrder=2}
// Sides are [EAST, SOUTH, WEST, NORTH] (there are convenience variables linked to their names)

val path = result.second
println(path.joinToString())
// MazePosition{x=0, y=0}, MazePosition{x=1, y=0}, MazePosition{x=1, y=1}
```
