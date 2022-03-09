package dev.reeve.mazelib.solvers

/**
 * Thrown more than it should be, this is primarily used when something went horribly wrong when generating the maze, or the position in the maze was just out of bounds.
 * @param string Makes errors more helpful if you have a message too :)
 */
class IncompleteMazeException(string: String): IllegalArgumentException(string)