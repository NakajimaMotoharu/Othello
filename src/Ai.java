public class Ai {
	// AIの使用判別フラグ
	public static boolean use = false;
	// AIのターン判別フラグ
	public static boolean turn = false;
	// AIの配置位置保存場所
	public static int x = 0, y = 0;

	// AIの動作関数
	public static void autoPush(int c){
		// 一番多くひっくりかえせる場所に配置
		int max = 0, tmpX = 0, tmpY = 0;
		boolean frag = false;

		for (int i = 0; i < Othello.MAP_SIZE; i = i + 1){
			for (int j = 0; j < Othello.MAP_SIZE; j = j + 1){
				if (Othello.fPush(i, j, c)){
					frag = true;
					if (max <= Othello.calcPush(i, j, c)){
						max = Othello.calcPush(i, j, c);
						tmpX = i;
						tmpY = j;
					}
				}
			}
		}

		if (frag){
			Othello.push(tmpX, tmpY, c);
		}

		turn = false;
	}
}
