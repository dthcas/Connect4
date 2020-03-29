import java.util.Scanner;

public class CPUPlayer extends Player {

	int level;
	String names[] = {"Bozo","Jeezy","Noddy","Porky","Ronald","Bryce","Eduardo","Pierce","Zabat","Ivan"};

	public CPUPlayer() {
		this("");
	}
	public CPUPlayer(String name) {
		super();
		System.out.print("Which level computer player do you want? (1-10): ");
		Scanner s = new Scanner(System.in);
		level = s.nextInt();  // Take a number from the user
		level = Math.max(1,Math.min(10,level));
		System.out.println("Loading computer player...");
		this.name = names[level-1];
		
	}
	
	
	public String getName() { return name; }
	public void setColor(int col) { this.color = col; }
	public void setNumber(int num) { this.number = num; }
	
	public int getMove(Board b) throws CloneNotSupportedException {
		
		int tally[] = new int[b.getColumns()+1];
		int nextmove;
		int highscore=0;
		// Choose a random column to play
		
		nextmove = (int) (Math.random()*b.getColumns())+1;
		int [][] board = b.getBoard();
		
		while(board[0][nextmove-1]!=Board.EMPTY) {
			nextmove = (int)(Math.random()*b.getColumns())+1;
		}
		
		for(int i=1; i<=b.getColumns(); i++) {
			if(b.getBoard()[0][i-1]!=Board.EMPTY) {
				tally[i] = -999999999;
			}
			else {
				tally[i] = lookAhead(nextBoard(b,i,color),level,color);
			}
		}
		
		//we already have a random value for nextmove, but let's check the tally if there are better
		highscore = tally[nextmove];
		for(int i=1; i<=b.getColumns(); i++) {
			if(tally[i]>highscore) {
				nextmove = i;
				highscore = tally[i];
			}
		}
		
		return nextmove;
		
	}
	
	private int lookAhead(Board b, int levels, int playcolor) throws CloneNotSupportedException {
		
		if(b==null) return 0;
		if(levels==0) return 0;
		if(evaluate(b)==-1) return -1;
		
		int columns = b.getColumns();
		int tally[] = new int[columns+1];
		int total = 0;
		
		Board newboard;
		
		for(int i=1; i<=columns; i++) {
			
				newboard = nextBoard(b,i,getNextColor(playcolor));
				tally[i] += lookAhead(newboard,levels-1,getNextColor(playcolor));
		}
			
		for(int i=1; i<=columns; i++) {
			total += tally[i];	
		}
		
		return total;
	}
	
	private int evaluate(Board b) {
		
		if(level == 0) return 0;
		if(b == null) return 0;

		
		int result = b.checkWinner();
		int opcol;
		
		if(color==Board.RED) opcol = Board.YELLOW;
		else opcol = Board.RED;
		
		if(result==color) return 1;
		else if(result==opcol) return -1;
		
		return 0;
		
	}
	
	private Board nextBoard(Board b, int column, int color) throws CloneNotSupportedException {
		
		if(b.getBoard()[0][column-1] != Board.EMPTY) return null;
		
		Board newboard;
		newboard = (Board) b.clone();
		if(newboard.move(column,color)) {
			
			return newboard;
		}
		
		return null;
	}
	
	int getNextColor(int color) {
		
		return ((color+2)%2)+1;
	}

}

		


