import java.util.Timer;
import java.util.TimerTask;

public class Main {
	public static void main(String[] args) {
		// init
		Othello.init();

		// create window
		GameWindow gw = new GameWindow("Othello", WindowData.WIDTH, WindowData.HEIGHT);
		gw.add(new Draw());
		gw.setVisible(true);

		// frame ctrl
		Timer reFrame = new Timer(false);
		TimerTask frameTask = new TimerTask() {
			@Override
			public void run() {
				if (Ai.turn){
					Ai.autoPush(Othello.makeNc(Othello.defaultColor));
				} else {
					Othello.passive();
				}
				gw.repaint();
			}
		};
		reFrame.schedule(frameTask, 0, 1000 / WindowData.INDEX_FPS);

		// fps ctrl
		Timer clockFps = new Timer(false);
		TimerTask clockTask = new TimerTask() {
			@Override
			public void run() {
				WindowData.oldFps = WindowData.nowFps;
				WindowData.nowFps = 0;
			}
		};
		clockFps.schedule(clockTask, 0, 1000);
	}
}
