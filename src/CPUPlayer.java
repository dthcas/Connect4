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
	
	public int getMove(Board b) {
		
		// Choose a random column to play
		if(level == 1) {
			int testmove = (int) (Math.random()*b.getColumns())+1;
			int [][] board = b.getBoard();
		
			while(board[0][testmove-1]!=Board.EMPTY) {
				testmove = (int)(Math.random()*b.getColumns())+1;
			}
			return testmove;
		}
		else {
			
			return lookAhead(b,level);
		}
	}
	
	int lookAhead(Board b, int levels) {
		
		// Add up the scores of the various columns
		int tally[] = new int[b.getColumns()];
		
		
		// temporary return value so method works
		return (int)(Math.random()*b.getColumns())+1;
	}

}
