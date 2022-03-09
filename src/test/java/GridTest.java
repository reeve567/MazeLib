import dev.reeve.mazelib.Maze;
import dev.reeve.mazelib.MazePoint;
import dev.reeve.mazelib.generators.RecursiveBacktrackGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
		Maze maze = new RecursiveBacktrackGenerator(new Random(12535)).generateMaze(size, size);
		int squareSize = 30;
		int buffer = 50;
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				// square size = 30
				MazePoint point = maze.getPoints()[x][y];
				int corner = 2;
				
				if (point == null) {
					g.setColor(Color.MAGENTA);
					g.drawRect(x * squareSize, buffer + y * squareSize, squareSize, squareSize);
					continue;
				}
				
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
