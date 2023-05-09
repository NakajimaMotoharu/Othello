public class Ai {
	// AIの使用判別フラグ
	public static boolean use = false;
	// AIのターン判別フラグ
	public static boolean turn = false;
	// AIの配置位置保存場所
	public static int x = 0, y = 0;

	// AIの動作関数
	public static void autoPush(int c){
		// 左上から置ける場所を探してあったら置く(なかったらスキップ)
		for (int i = 0; i < Othello.MAP_SIZE; i = i + 1){
			for (int j = 0; j < Othello.MAP_SIZE; j = j + 1){
				if (Othello.fPush(i, j, c)){
					Othello.push(i, j, c);
					turn = false;
					x = i;
					y = j;
					return;
				}
			}
		}

		turn = false;
	}
}
