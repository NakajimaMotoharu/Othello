import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		// Main
		// Line
		for (int i = Othello.BOX_START_HEIGHT; i <= Othello.BOX_START_HEIGHT + Othello.MAP_SIZE * Othello.BOX_SIZE; i = i + Othello.BOX_SIZE){
			g.drawLine(Othello.BOX_START_WIDTH, i, Othello.BOX_START_WIDTH + Othello.MAP_SIZE * Othello.BOX_SIZE, i);
		}
		for (int i = Othello.BOX_START_WIDTH; i <= Othello.BOX_START_WIDTH + Othello.MAP_SIZE * Othello.BOX_SIZE; i = i + Othello.BOX_SIZE){
			g.drawLine(i, Othello.BOX_START_HEIGHT, i, Othello.BOX_START_HEIGHT + Othello.MAP_SIZE * Othello.BOX_SIZE);
		}
		// Player point
		g.setColor(Color.red);
		g.fillRect(Othello.BOX_START_WIDTH + Othello.x * Othello.BOX_SIZE, Othello.BOX_START_HEIGHT + Othello.y * Othello.BOX_SIZE, Othello.BOX_SIZE, Othello.BOX_SIZE);
		// Write oval
		g.setColor(Color.black);
		for (int i = 0; i < Othello.MAP_SIZE; i = i + 1){
			for (int j = 0; j < Othello.MAP_SIZE; j = j + 1){
				if (Othello.getMap(i, j) == 1){
					g.fillOval(Othello.BOX_START_WIDTH + i * Othello.BOX_SIZE, Othello.BOX_START_HEIGHT + j * Othello.BOX_SIZE, Othello.BOX_SIZE, Othello.BOX_SIZE);
				} else if (Othello.getMap(i, j) == 2){
					g.drawOval(Othello.BOX_START_WIDTH + i * Othello.BOX_SIZE, Othello.BOX_START_HEIGHT + j * Othello.BOX_SIZE, Othello.BOX_SIZE, Othello.BOX_SIZE);
				}
			}
		}

		// Info
		g.setColor(Color.red);
		g.drawString("FPS: " + WindowData.oldFps, 10, 10);
		g.drawString("(x, y) = (" + Othello.x + ", " + Othello.y + ")", 10, 20);
		g.drawString("Data: " + Othello.getMap(Othello.x, Othello.y), 10, 30);
		if (Othello.defaultColor == 1){
			g.drawString("COLOR: BLACK", 10, 40);
		} else {
			g.drawString("COLOR: WHITE", 10, 40);
		}
		if (Othello.fPush(Othello.x, Othello.y, Othello.defaultColor)){
			g.drawString("PUSH: true", 10, 50);
		} else {
			g.drawString("PUSH: false", 10, 50);
		}
		if (Ai.use){
			g.drawString("AI: ON", 10, 60);
		} else {
			g.drawString("AI: OFF", 10, 60);
		}
		g.drawString("BLACK: " + Othello.getNum(1), 10, 70);
		g.drawString("WHITE: " + Othello.getNum(2), 10, 80);

		g.drawString("DEBUG", 10, 100);
		for (int i = 0; i < Othello.MAP_SIZE; i = i + 1){
			g.drawString(Othello.makeMapLine(i), 10, 110 + i * 10);
		}

		// usage
		g.drawString("MOVING: W/A/S/D", 300, 10);
		g.drawString("PUSH: SPACE", 300, 20);
		g.drawString("AI MODE: F", 300, 30);
		g.drawString("CHANGE COLOR: C", 300, 40);
		g.drawString("SKIP: R", 300, 50);

		// Fps add
		WindowData.nowFps = WindowData.nowFps + 1;
	}
}
