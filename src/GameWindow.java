import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener{
	// メインウィンドウコンストラクタ
	public GameWindow(String title, int width, int height){
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);

		addKeyListener(this);
	}

	// 空オーバーライド
	@Override
	public void keyTyped(KeyEvent e){
		// null
	}

	// キー押された時の処理
	@Override
	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode()){
			case KeyEvent.VK_W:
				IoCtrl.w = true;
				break;
			case KeyEvent.VK_A:
				IoCtrl.a = true;
				break;
			case KeyEvent.VK_S:
				IoCtrl.s = true;
				break;
			case KeyEvent.VK_D:
				IoCtrl.d = true;
				break;
			case KeyEvent.VK_C:
				IoCtrl.c = true;
				break;
			case KeyEvent.VK_Q:
				System.exit(0);
				break;
			case KeyEvent.VK_SPACE:
				if (Othello.fPush(Othello.x, Othello.y, Othello.defaultColor)){
					Othello.push(Othello.x, Othello.y, Othello.defaultColor);
					if (Ai.use){
						Ai.turn = true;
					}
				}
				break;
			case KeyEvent.VK_F:
				IoCtrl.f = true;
				break;
			case KeyEvent.VK_R:
				IoCtrl.r = true;
				break;
		}
	}

	// キー離された時の処理
	@Override
	public void keyReleased(KeyEvent e){
		switch (e.getKeyCode()){
			case KeyEvent.VK_W:
				IoCtrl.w = false;
				IoCtrl.pw = true;
				break;
			case KeyEvent.VK_A:
				IoCtrl.a = false;
				IoCtrl.pa = true;
				break;
			case KeyEvent.VK_S:
				IoCtrl.s = false;
				IoCtrl.ps = true;
				break;
			case KeyEvent.VK_D:
				IoCtrl.d = false;
				IoCtrl.pd = true;
				break;
			case KeyEvent.VK_C:
				IoCtrl.c = false;
				IoCtrl.pc = true;
				break;
			case KeyEvent.VK_F:
				IoCtrl.f = false;
				IoCtrl.pf = true;
				break;
			case KeyEvent.VK_R:
				IoCtrl.r = false;
				IoCtrl.pr = true;
				break;
		}
	}
}
