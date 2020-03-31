import java.util.Scanner;

public class CPUPlayer extends Player {

	int level;
	int process;
	private String names[] = {"Bozo","Noddy","Porky","Beebot","Zoltan","Gregor","Ivan"};
	private String trashtalk[] = {	"Ouch.. you just got clowned on by Bozo.  Better luck next time!",
									"Tee hee! Noddy just took you to school!",
									"Porky just cooked your bacon at Connect 4.  Want another round in the frying pan?",
									"Face it human, you're no match for Beebot!",
									"You will never be able to best the mind of Zoltan!",
									"The might of Gregor shall not be defeated, puny Earthling.  Now go away before you invoke my cosmic wrath!",
									"Do not despair mortal, for I am the greatest Connect 4 player that ever lived!"};
	

	public CPUPlayer() {
		this("");
	}
	public CPUPlayer(String name) {
		super();
		System.out.print("Which level computer player do you want? (1-5): ");
		Scanner s = new Scanner(System.in);
		try {
			
			level = s.nextInt();  // Take a number from the user
		}
		catch(Exception e) {
			
			System.out.println("Weird input.. setting level to random");
			level = (int)(Math.random()*5)+1;
		}
		level = Math.max(1,Math.min(5,level));
		System.out.println("Loading level "+level+" computer player...");
		this.name = names[level-1];
		
	}
	
	
	public String getName() { return name; }
	public void setColor(int col) { this.color = col; }
	public void setNumber(int num) { this.number = num; }
	
	public int getMove(Board b) throws CloneNotSupportedException {
		
		int tally[] = new int[b.getColumns()+1];
		int nextmove;
		int highscore=0;
		this.process=0;
		// Choose a random column to play
		
		nextmove = (int) (Math.random()*b.getColumns())+1;
		int [][] board = b.getBoard();
		
		while(board[0][nextmove-1]!=Board.EMPTY) {
			nextmove = (int)(Math.random()*b.getColumns())+1;
		}
		
		for(int i=1; i<=b.getColumns(); i++) {
			// If that particular column is empty, set the value of the move to an extreme value
			if(b.getBoard()[0][i-1]!=Board.EMPTY) {
				tally[i] = -999999999;
			}
			else {
				// Level 1-3 were not very smart.. let's help them out a bit by adding 3
				tally[i] = lookAhead(nextBoard(b,i,color),level+3,color);
				
			
			}
		}
		
		//we already have a random value for nextmove, but let's check the tally if there are better
		highscore = tally[nextmove];
		System.out.print("Column scores: [");
		for(int i=1; i<=b.getColumns(); i++) {
			if(tally[i]>highscore) {
				nextmove = i;
				highscore = tally[i];
			}
			System.out.print(i+":"+tally[i]);
			if(i==b.getColumns()) System.out.println("]");
			else System.out.print(",");
		}
		
		System.out.println("This move required "+this.process+" contemplations");
		return nextmove;
		
	}
	
	private int lookAhead(Board b, int levels, int playcolor) throws CloneNotSupportedException {
		
		if(b==null && playcolor==color) return 0;
		if(b==null && playcolor != color) return 0;
		int result = evaluate(b);
		if(result == 1) return 1000;
		if(result == -1) return -1000;
		if(levels==0) return 0;
		
		this.process++;
		
		int columns = b.getColumns();
		int tally[] = new int[columns+1];
		int total = 0;
		
		Board newboard;
		int newcolor = getNextColor(playcolor);
		
		for(int i=1; i<=columns; i++) {
			
				newboard = nextBoard(b,i,newcolor);
				tally[i] = lookAhead(newboard,levels-1,newcolor);
				//System.out.println("Level "+(levels-1)+"."+i+" returns "+tally[i]);
				total += tally[i];
				
		}
		
		if(levels==1) {
			
			//return total;
		}
		
		int maxval = -99999999;
		int minval = 999999999;
		
		if(newcolor==color) {
			for(int i=1; i<=columns; i++) {
				if(tally[i]>maxval) {
					maxval=tally[i];
				}
			}
			return maxval;
		}
		else {
			for(int i=1; i<=columns; i++) {
				if(tally[i]<minval) {
					minval=tally[i];
				}
			}
			return minval;
		}
		
		
	}
	
	private int evaluate(Board b) {
		
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

	public void trashTalk() {
		
		System.out.println(trashtalk[level-1]);
		
	}
	
}