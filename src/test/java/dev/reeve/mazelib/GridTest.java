package dev.reeve.mazelib;

import dev.reeve.mazelib.generators.BinaryTreeGenerator;
import dev.reeve.mazelib.generators.SidewinderGenerator;
import kotlin.Pair;

import javax.swing.*;
import java.awt.*;

import static kotlin.random.RandomKt.Random;


public class GridTest extends JFrame {
	
	private final int size = 10;
	
	GridTest() {
		setSize(700, 700);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GridTest();
	}
	
	@Override
	public void paint(Graphics g) {
		/*Pair<Maze, MazePath> generated = new RecursiveBacktrackGenerator(mazePosition -> false, new Random(12535))
				.generateMaze(size, size, new MazePosition(0, 0), new MazePosition(size - 1, size - 1));
		Maze maze = generated.getFirst();
		MazePath path = generated.getSecond();*/
		
		Maze maze = new SidewinderGenerator(Random(12353)).generateMaze(size, size);
		MazePath path = new MazePath();
		
		// Replace with `Random.Default` to let the seed be different each time or leave all blank
		/*Maze maze = new RecursiveBacktrackGenerator(mazePosition -> false, Random(12353)).generateMaze(size, size);
		MazePath path = new MazePath();*/
		
		int squareSize = 30;
		int buffer = 50;
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				// square size = 30
				MazePoint point = maze.getPoints()[x][y];
				int corner = 3;
				
				if (point == null) {
					g.setColor(Color.MAGENTA);
					g.drawRect(x * squareSize, buffer + y * squareSize, squareSize, squareSize);
					continue;
				}
				
				MazePosition position = new MazePosition(x, y);
				
				if (path.contains(position))
					g.setColor(Color.GREEN);
				else
					g.setColor(Color.WHITE);
				g.fillRect(x * squareSize, buffer + y * squareSize, squareSize, squareSize);
				g.setColor(Color.BLACK);
				g.drawRect(x * squareSize, buffer + y * squareSize, squareSize, squareSize);
				
				g.drawString("" + point.getUpdateOrder(), x * squareSize + (squareSize / 3), buffer + y * squareSize + (squareSize / 2));
				
				if (point.getNorth()) {
					//g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect((x * squareSize) + corner, buffer + y * squareSize, squareSize - (2 * corner), corner);
					
				}
				
				if (point.getWest()) {
					//g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect(x * squareSize, buffer + (y * squareSize) + corner, corner, squareSize - (2 * corner));
				}
				
				if (point.getSouth()) {
					g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect((x * squareSize) + corner, buffer + (y * squareSize) + (squareSize - corner), squareSize - (2 * corner), corner);
				}
				
				if (point.getEast()) {
					g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect((x * squareSize) + (squareSize - corner), buffer + (y * squareSize) + corner, corner, squareSize - (2 * corner));
				}
			}
		}
		
		for (int x = 0; x < size; x++) {
			g.setColor(Color.BLACK);
			g.drawString("" + x, (x * squareSize) + (squareSize / 3), buffer + (size * squareSize) + (squareSize / 2));
		}
		
		for (int y = 0; y < size; y++) {
			g.setColor(Color.BLACK);
			//
			g.drawString("" + y, (size * squareSize) + (squareSize / 2), buffer + (y * squareSize) + (squareSize / 2));
		}
		
	}
}
