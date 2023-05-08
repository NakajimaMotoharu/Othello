public class Ai {
	// Ai on off
	public static boolean use = false;
	// Ai turn
	public static boolean turn = false;

	public static void autoPush(int c){
		for (int i = 0; i < Othello.MAP_SIZE; i = i + 1){
			for (int j = 0; j < Othello.MAP_SIZE; j = j + 1){
				if (Othello.fPush(i, j, c)){
					Othello.push(i, j, c);
					turn = false;
					return;
				}
			}
		}

		turn = false;
	}
}
