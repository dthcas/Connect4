import java.util.Scanner;

public class Player {

	String name;
	int color;
	int number;
	
	public Player() {
		
		number = 0;
		color = 0;
	}
	
	public Player(int n) {

		this.number = n;
		this.color = 0;
	}
	
	public Player(int num, String name) {
		
		this.name = name;
		this.number = num;
		this.color = 0;
	}
	
	public Player(int num, String name, int color) {
	
		this.name = name;
		this.number = num;
		this.color = color;
		
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getColor() {
		
		return color;
	}
	
	public void setColor(int c) {
		
		color = c;
	}
	
	public int getMove(Board b) throws CloneNotSupportedException {
		
		Scanner s = new Scanner(System.in);
		int move=0;
		int cols = b.getColumns();
		
		while(move<=0 || move > cols) {
			System.out.print(this.name + "'s move: ");
			move = s.nextInt();
			
			if(move<=0 || move > cols)
				System.out.println("Invalid move, please enter a number between 1 and" + cols);
		}
		
		return move;
		
	}
	
}
