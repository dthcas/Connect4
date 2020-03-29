import java.util.Scanner;

public class Connect4 {

	public static final int ROWS = 6;
	public static final int COLS = 7;
	
	public Connect4() {
		
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		boolean newgame = true;
		
		while(newgame) {
			
			Connect4 game = new Connect4();
			Scanner s = new Scanner(System.in);
			String input ="";
			// Game menu
			
			// Player menu
			
			System.out.println("Hey there, 1 Player or 2 Player? ");
			int gametype = ((s.nextInt()+1)%2)+1;
			System.out.println(gametype+" Player game chosen");
			if(gametype == 1) {	
				// One-player game
				game.onePlayerGame();
			}
			else if(gametype == 2) {
				// Two-player game
				game.twoPlayerGame();
			}
				System.out.println("Do you want to play another game? (y/n)");
				input = s.next();
				if(input.toLowerCase().substring(0, 1).equals("y"))
					continue;
				else {
					
					s.close();
					return;		
				}
		}
	}
		
	private void onePlayerGame() throws CloneNotSupportedException {
		
		Board board = new Board(ROWS,COLS);
		int firstmove = ((int)(Math.random()*2))+1;
		int secondmove;
		if(firstmove==1) secondmove = 2;
		else secondmove = 1;
		
		HumanPlayer human = new HumanPlayer(firstmove,firstmove);
		CPUPlayer computer = new CPUPlayer();
		
		computer.setColor(secondmove);
		
		int hmove;
		int cmove;
		int count=0;
		int result;
				
		while(board.checkWinner()==0) {
		
			if(firstmove==2) {
				System.out.println(computer.getName()+" the Computer is RED and goes first...");
				cmove = computer.getMove(board);
				while(count<20 && !board.move(cmove,computer.getColor())) {
					count++;
					cmove = computer.getMove(board);
				}
				count = 0;
				firstmove = 0;
				System.out.println(computer.getName()+" chooses column "+cmove);
				board.printBoard();
			}
			else if(firstmove==1) {
				board.printBoard();
				System.out.println(human.getName()+" is RED and goes first...");
				firstmove = 0;
			}
			
			// Human move
			
			hmove = human.getMove(board);
			while(count<20 && !board.move(hmove,human.getColor())) {
				count++;
				cmove = human.getMove(board);
			}
			count = 0;
			board.printBoard();
				
			result = board.checkWinner();
			if(result>0 && result==computer.getColor()) { 
				printWinner(result,computer.getName());
				return;
			}
			else if(result>0 && result==human.getColor()) { 
				printWinner(result,human.getName());
				return;
			}
			
			// Computer move
			
			cmove = computer.getMove(board);
			while(count<20 && !board.move(cmove,computer.getColor())) {
				count++;
				cmove = computer.getMove(board);
			}
			count = 0;
			firstmove = 0;
			System.out.println(computer.getName()+" chooses column "+cmove);
			board.printBoard();
			
			result = board.checkWinner();
			if(result>0 && result==computer.getColor()) { 
				printWinner(result,computer.getName());
				return;
			}
			else if(result>0 && result==human.getColor()) { 
				printWinner(result,human.getName());
				return;
			}
			
		}
	}
		
	private void printWinner(int result, String name) {
		
		if(result == Board.YELLOW) System.out.println("YELLOW has a Connect4 - "+name+" wins!");
		else if(result == Board.RED) System.out.println("RED has a Connect4 - "+name+" wins!");
		else System.out.println("The game was a DRAW");
	}



	private void twoPlayerGame() throws CloneNotSupportedException {
		
		Board board = new Board(ROWS,COLS);
		int p1col = ((int)(Math.random()*2))+1;
		int p2col = p1col%2 + 1;
	
		
		HumanPlayer p1 = new HumanPlayer(1,p1col);
		HumanPlayer p2 = new HumanPlayer(2,p2col);
		
		int p1move;
		int p2move;
		int count=0;
		int result;
		boolean firstmove = true;
		//boolean trymove;
				
		while((result = board.checkWinner())==0) {
		
			if(p2col==Board.RED && firstmove) {
				System.out.println(p2.getName()+" is RED and goes first");
				board.printBoard();
				p2move = p2.getMove(board);
				while(count<20 && !board.move(p2move,p2.getColor())) {
					count++;
					p2move = p2.getMove(board);
				}
				count = 0;
				board.printBoard();
				firstmove=false;
			}
			else if(firstmove) {
				System.out.println(p1.getName()+" is RED and goes first...");
				board.printBoard();
				firstmove = false;
			}
			
			// Player 1 move
			
			p1move = p1.getMove(board);
			while(!board.move(p1move,p1.getColor())) {
				count++;
				p1move = p1.getMove(board);
				
				if(count>50) break;
			}
			count = 0;
			board.printBoard();
			
			
			result = board.checkWinner();
			if(result>0 && result==p1.getColor()) { 
				printWinner(result,p1.getName());
				return;
			}
			else if(result>0 && result==p2.getColor()) { 
				printWinner(result,p2.getName());
				return;
			}
			
			// Player 2 move
			
			p2move = p2.getMove(board);
			while(!board.move(p2move,p2.getColor())) {
				count++;
				p2move = p2.getMove(board);
				
				if(count>50) break;
			}
			count = 0;
			board.printBoard();
			
			
			result = board.checkWinner();
			if(result>0 && result==p1.getColor()) { 
				printWinner(result,p1.getName());
				return;
			}
			else if(result>0 && result==p2.getColor()) { 
				printWinner(result,p2.getName());
				return;
			}
			
			
		}
	}
}
