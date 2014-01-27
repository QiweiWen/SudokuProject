import java.util.ArrayList;
import java.util.Random;

public class Game {
	public Game(GameDifficulty level){
		this.level = level;
		board = new int[9][9];
		solution = new int[9][9];
		int i,j;
		Random r = new Random();
		ArrayList<Integer> seen = new ArrayList<Integer>();
		Integer temp = null;
		for (i = 0;i < 9;i++){
			for (j = 0;j < 9;j++){
				if (j != 0){
					solution[i][j] = 0;
					board[i][j] = 0;
				}else{
					while (seen.contains(temp) || temp == null){
						temp = new Integer(Math.abs(r.nextInt())%9 + 1);
					}
					seen.add(temp);
					solution[i][j] = temp.intValue();
					board[i][j] = temp.intValue();
				}
			}
		}
		genSolution(solution,board,0,1);
		
		if(level == GameDifficulty.BEGINNER){
			reduce(81 - filledBoxes[0]);
		}else if(level == GameDifficulty.INTERMEDIATE){
			reduce(81 - filledBoxes[1]);
		}else{
			reduce(81 - filledBoxes[2]);
		}
		
//		reduce(81 - filledBoxes[level]);
	}

	private boolean genSolution(int[][] initial,int[][] copy,int x,int y){
		boolean legal = false;
		if (x == 0 && y == 0){
			return true;  
		}
		int i = 0;
		int xNext = x;
		int yNext = y;
		xNext++;
		if (xNext == 9){
			xNext = 0;
			yNext = (yNext + 1)%9;
		}
		while (i < 9 && !legal){
			initial[x][y] = i + 1;
			copy[x][y] = i + 1;
			if (checkConflict(x,y,initial)){
				legal = genSolution(initial,copy,xNext,yNext);
			}
			i++;
		}
		if (legal == false){
			initial[x][y] = 0;
			copy[x][y] = 0;
		}
		return legal;
	}

	private void reduce(int target){
		int i = 0;
		int x = 0;
		int y = 0;
		int temp = 0;
		int lastX = 0;
		int lastY = 0;
		Random r = new Random();
		while (i < target){
			temp = board[x][y];
			board[x][y] = 0;
			if (otherSolution(solution,board,x,y)){
				board[x][y] = temp;
			}else{
				i++; 
			}
			lastX = x;
			lastY = y;
			while ((x == lastX && y == lastY) || board[x][y] == 0){
				x = Math.abs(r.nextInt())%9;
				y = Math.abs(r.nextInt())%9;
			}
		}
	}

	private boolean otherSolution(int[][] filled,int[][] unfilled,int x,int y){
		boolean solved = false;
		int i = y;
		int j = x;
		int newX = 0;
		int newY = 0;
		boolean found = false;
		while (i < 9 && !found){
			while (j < 9 && !found){
				if (unfilled[j][i] == 0 && !(y == i && x == j)){
					found = true;
					newX = j;
					newY = i;
				}
				j++;
			}
			j = 0;
			i++;
		}

		int number = 1;
		while (number <= 9 && !solved){
			unfilled[x][y] = number;
			if (filled[x][y] != unfilled[x][y] && checkConflict(x,y,unfilled)){
				if (found){
					solved = otherSolution(filled,unfilled,newX,newY);
				}else{
					solved = true;
				}
			}
			number++;
		}
	    unfilled[x][y] = 0;
		return solved;
	}

	public int[][] printGame(){
		return board;
	}

	public int hint(int x, int y){
		return solution[x][y];
	}

	public void place(int x, int y, int content){
		board[x][y] = content;
	}

	public int get(int x, int y){
		return board[x][y];
	}

	public boolean checkAround(int x,int y){
		return checkConflict(x,y,board);
	}

	private boolean checkConflict(int x,int y,int[][] toCheck){
		boolean legal = true;
		legal = checkArea(x,0,x,8,toCheck);
		legal = legal && checkArea(0,y,8,y,toCheck);
		int x1 = 3 * (int)(x/3);
		int y1 = 3 * (int)(y/3);
		legal = legal && checkArea(x1,y1,x1+2,y1+2,toCheck);
		return legal;
	}

	private boolean checkArea(int x1,int y1,int x2,int y2,int[][] toCheck){
		boolean legal = true;
		ArrayList<Integer> checkList = new ArrayList<Integer>();
		int i,j = 0;
		for (i = 0;i < 9;i++){
			checkList.add(new Integer(i+1));
		}
		i = x1;
		j = y1;
		Integer temp;
		while (i <= x2 && legal){
			while (j <= y2 && legal){
				if (toCheck[i][j] != 0){
					temp = new Integer(toCheck[i][j]);
					if (!checkList.contains(temp)){
						legal = false;
					}else{
						checkList.remove(temp);
					}
				}
				j++;
			}
			j = y1;
			i++;
		}
		return legal;
	}

	//the level of difficulty
	GameDifficulty level;
//	private int level;
	private int[][] board;
	private int[][] solution;
//	public static final int EXPERT = 2;
//	public static final int INTERMEDIATE = 1;
//	public static final int NOVICE = 0;
	public static final int[] filledBoxes = {50,40,25};
}
