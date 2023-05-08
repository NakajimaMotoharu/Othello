public class Othello {
	// Othello map setting
	public static final int MAP_SIZE = 8;
	// Othello map data
	private static int[][] map = new int[MAP_SIZE][MAP_SIZE];
	// map box size
	public static final int BOX_SIZE = 40, BOX_START_WIDTH = 200, BOX_START_HEIGHT = 100;
	// Player data
	public static int x = 0, y = 0, defaultColor = 1;
	// Directions const
	public static final int DIR_U = 0, DIR_RU = 1, DIR_R = 2, DIR_RD = 3, DIR_D = 4, DIR_LD = 5, DIR_L = 6, DIR_LU = 7, DIR_NUM = 8;

	// Othello initialisation
	public static void init(){
		for (int i = 0; i < MAP_SIZE; i = i + 1){
			for (int j = 0; j < MAP_SIZE; j = j + 1){
				map[i][j] = 0;
			}
		}
		map[MAP_SIZE / 2 - 1][MAP_SIZE / 2 - 1] = 1;
		map[MAP_SIZE / 2 - 1][MAP_SIZE / 2] = 2;
		map[MAP_SIZE / 2][MAP_SIZE / 2 - 1] = 2;
		map[MAP_SIZE / 2][MAP_SIZE / 2] = 1;
	}

	public static void passive(){
		if (IoCtrl.w && IoCtrl.pw){
			y = y - 1;
			IoCtrl.pw = false;
		}
		if (IoCtrl.a && IoCtrl.pa){
			x = x - 1;
			IoCtrl.pa = false;
		}
		if (IoCtrl.s && IoCtrl.ps){
			y = y + 1;
			IoCtrl.ps = false;
		}
		if (IoCtrl.d && IoCtrl.pd){
			x = x + 1;
			IoCtrl.pd = false;
		}
		if (IoCtrl.c && IoCtrl.pc && !(Ai.use)){
			if (defaultColor == 1){
				defaultColor = 2;
			} else {
				defaultColor = 1;
			}
			IoCtrl.pc = false;
		}
		if (IoCtrl.f && IoCtrl.pf){
			if (Ai.use){
				Ai.use = false;
			} else {
				Ai.use = true;
			}
			IoCtrl.pf = false;
		}
		if (IoCtrl.r && IoCtrl.pr && Ai.use){
			Ai.turn = true;
			IoCtrl.pr = false;
		}
		if (x < 0){
			x = 0;
		} else if (x >= MAP_SIZE){
			x = MAP_SIZE - 1;
		}
		if (y < 0){
			y = 0;
		} else if (y >= MAP_SIZE){
			y = MAP_SIZE - 1;
		}
	}

	public static void push(int x, int y, int c){
		if (fPush(x, y, c)){
			setMap(x, y, c);
			for (int i = 0; i < DIR_NUM; i = i + 1){
				dirPush(x, y, c, i);
			}
		}
	}

	public static void dirPush(int x, int y, int c, int dir){
		int nc = makeNc(c);

		if (search(x, y, c, dir)){
			if (dir == DIR_U){
				for (int i = y - 1; getMap(x, i) == nc; i = i - 1){
					setMap(x, i, c);
				}
			}
			if (dir == DIR_R){
				for (int i = x + 1; getMap(i, y) == nc; i = i + 1){
					setMap(i, y, c);
				}
			}
			if (dir == DIR_D){
				for (int i = y + 1; getMap(x, i) == nc; i = i + 1){
					setMap(x, i, c);
				}
			}
			if (dir == DIR_L){
				for (int i = x - 1; getMap(i, y) == nc; i = i - 1){
					setMap(i, y, c);
				}
			}
			if (dir == DIR_LU){
				for (int i = 1; getMap(x - i, y - i) == nc; i = i + 1){
					setMap(x - i, y - i, c);
				}
			}
			if (dir == DIR_RU){
				for (int i = 1; getMap(x + i, y - i) == nc; i = i + 1){
					setMap(x + i, y - i, c);
				}
			}
			if (dir == DIR_RD){
				for (int i = 1; getMap(x + i, y + i) == nc; i = i + 1){
					setMap(x + i, y + i, c);
				}
			}
			if (dir == DIR_LD){
				for (int i = 1; getMap(x - i, y + i) == nc; i = i + 1){
					setMap(x - i, y + i, c);
				}
			}
		}
	}

	public static boolean fPush(int x, int y, int c){
		boolean frag = false;

		if (getMap(x, y) != 0){
			return false;
		}

		for (int i = 0; i < DIR_NUM; i = i + 1){
			if (search(x, y, c, i)){
				frag = true;
			}
		}

		return frag;
	}

	public static boolean search(int x, int y, int c, int f){
		int nc = makeNc(c);

		if ((f == DIR_U) && fInMap(x, y - 1) && (getMap(x, y - 1) == nc)){
			for (int i = y - 1; i >= 0; i = i - 1){
				if (getMap(x, i) == 0){
					return false;
				} else if (getMap(x, i) == c){
					return true;
				}
			}
			return false;
		}
		if ((f == DIR_R) && fInMap(x + 1, y) && (getMap(x + 1, y) == nc)){
			for (int i = x + 1; i < MAP_SIZE; i = i + 1){
				if (getMap(i, y) == 0){
					return false;
				} else if (getMap(i, y) == c){
					return true;
				}
			}
			return false;
		}
		if ((f == DIR_D) && fInMap(x, y + 1) && (getMap(x, y + 1) == nc)){
			for (int i = y + 1; i < MAP_SIZE; i = i + 1){
				if (getMap(x, i) == 0){
					return false;
				} else if (getMap(x, i) == c){
					return true;
				}
			}
			return false;
		}
		if ((f == DIR_L) && fInMap(x - 1, y) && (getMap(x - 1, y) == nc)){
			for (int i = x - 1; i >= 0; i = i - 1){
				if (getMap(i, y) == 0){
					return false;
				} else if (getMap(i, y) == c){
					return true;
				}
			}
			return false;
		}
		if ((f == DIR_LU) && fInMap(x - 1, y - 1) && (getMap(x - 1, y - 1) == nc)){
			for (int i = 1; fInMap(x - i, y - i); i = i + 1){
				if (getMap(x - i, y - i) == 0){
					return false;
				} else if (getMap(x - i, y - i) == c){
					return true;
				}
			}
			return false;
		}
		if ((f == DIR_RU) && fInMap(x + 1, y - 1) && (getMap(x + 1, y - 1) == nc)){
			for (int i = 1; fInMap(x + i, y - i); i = i + 1){
				if (getMap(x + i, y - i) == 0){
					return false;
				} else if (getMap(x + i, y - i) == c){
					return true;
				}
			}
			return false;
		}
		if ((f == DIR_RD) && fInMap(x + 1, y + 1) && (getMap(x + 1, y + 1) == nc)){
			for (int i = 1; fInMap(x + i, y + i); i = i + 1){
				if (getMap(x + i, y + i) == 0){
					return false;
				} else if (getMap(x + i, y + i) == c){
					return true;
				}
			}
			return false;
		}
		if ((f == DIR_LD) && fInMap(x - 1, y + 1) && (getMap(x - 1, y + 1) == nc)){
			for (int i = 1; fInMap(x - i, y + i); i = i + 1){
				if (getMap(x - i, y + i) == 0){
					return false;
				} else if (getMap(x - i, y + i) == c){
					return true;
				}
			}
			return false;
		}

		return false;
	}

	public static void setMap(int x, int y, int c){
		if (fInMap(x, y)){
			map[x][y] = c;
		}
	}

	public static int getMap(int x, int y){
		if (fInMap(x, y)){
			return map[x][y];
		}
		return 3;
	}

	public static String makeMapLine(int n){
		return "" + map[0][n] + map[1][n] + map[2][n] + map[3][n] + map[4][n] + map[5][n] + map[6][n] + map[7][n];
	}

	private static boolean fInMap(int x, int y){
		return (((x >= 0) && (x < MAP_SIZE)) && ((y >= 0) &&(y < MAP_SIZE)));
	}

	public static int makeNc(int c){
		if (c == 1){
			return 2;
		} else {
			return 1;
		}
	}

	public static int getNum(int c){
		int n = 0;

		for (int i = 0; i < MAP_SIZE; i = i + 1){
			for (int j = 0; j < MAP_SIZE; j = j + 1){
				if (map[i][j] == c){
					n = n + 1;
				}
			}
		}

		return n;
	}
}
