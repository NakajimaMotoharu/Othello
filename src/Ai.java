public class Ai {
	// AIの使用判別フラグ
	public static boolean use = false;
	// AIのターン判別フラグ
	public static boolean turn = false;
	// AIの配置位置保存場所
	public static int x = 0, y = 0;

	// AIの動作関数
	public static void autoPush(int c){
		// 一番多くひっくりかえせる場所に配置(例外あり)
		// 四隅におかれる可能性がある所には極力おかない
		// 四隅はできるだけ置く
		int max = 0, tmpX = 0, tmpY = 0, gX = 0, gY = 0;
		boolean frag = false, ng = false, good = false;

		for (int i = 0; i < Othello.MAP_SIZE; i = i + 1){
			for (int j = 0; j < Othello.MAP_SIZE; j = j + 1){
				if (Othello.fPush(i, j, c)){
					frag = true;
					if (!(searchNgPoint(i, j))){
						ng = true;
						if (max <= Othello.calcPush(i, j, c)) {
							max = Othello.calcPush(i, j, c);
							tmpX = i;
							tmpY = j;
						}
					}
					if (searchOkPoint(i, j)){
						good = true;
						gX = i;
						gY = j;
					}
				}
			}
		}

		if ((frag) && !(ng)){
			for (int i = 0; i < Othello.MAP_SIZE; i = i + 1) {
				for (int j = 0; j < Othello.MAP_SIZE; j = j + 1) {
					if (Othello.fPush(i, j, c)) {
						if (max <= Othello.calcPush(i, j, c)) {
							max = Othello.calcPush(i, j, c);
							tmpX = i;
							tmpY = j;
						}
					}
				}
			}
		}

		if (frag){
			if (good){
				Othello.push(gX, gY, c);
				x = gX;
				y = gY;
			} else {
				Othello.push(tmpX, tmpY, c);
				x = tmpX;
				y = tmpY;
			}
		}

		turn = false;
	}

	// 四隅近い場所かどうか
	private static boolean searchNgPoint(int x, int y){
		if ((x == 1) && (y == 0)){
			return true;
		}
		if ((x == 1) && (y == 1)){
			return true;
		}
		if ((x == 0) && (y == 1)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 1) && (y == 1)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 2) && (y == 1)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 2) && (y == 0)){
			return true;
		}
		if ((x == 0) && (y == Othello.MAP_SIZE - 2)){
			return true;
		}
		if ((x == 1) && (y == Othello.MAP_SIZE - 2)){
			return true;
		}
		if ((x == 1) && (y == Othello.MAP_SIZE - 1)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 2) && (y == Othello.MAP_SIZE - 1)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 2) && (y == Othello.MAP_SIZE - 2)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 1) && (y == Othello.MAP_SIZE - 2)){
			return true;
		}
		return false;
	}

	private static boolean searchOkPoint(int x, int y){
		if ((x == 0) && (y == 0)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 1) && (y == 0)){
			return true;
		}
		if ((x == 0) && (y == Othello.MAP_SIZE - 1)){
			return true;
		}
		if ((x == Othello.MAP_SIZE - 1) && (y == Othello.MAP_SIZE - 1)){
			return true;
		}
		return false;
	}
}
