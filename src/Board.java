
// David's Connect 4 game
// March 2020 

public class Board implements Cloneable {

	public static final int EMPTY = 0;
	public static final int RED = 1;
	public static final int YELLOW = 2;
	
	int rows;
	int cols;
	int [][] board;
	int turn;
	int round;
	
	public Board(int r, int c) {
		
		rows = r;
		cols = c;
		board = new int[r][c];
		turn = RED;
		round = 1;
		
	}
	
	public Board(Board b) {
		
		rows = b.rows;
		cols = b.cols;
		board = new int[rows][cols];
		turn = b.turn;
		round = 1;
		
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				
				board[r][c] = b.getBoard()[r][c];
				
			}
		}
		
	}
	
	public int getColumns() {
		
		return this.cols;
	}
	
	public int[][] getBoard() {
		
		return this.board;
	}
	
	public Object clone() throws CloneNotSupportedException {
		
		//try {
		  //      return (Board) super.clone();
		//} catch (CloneNotSupportedException e) {
		        return new Board(this);
		    
		//}
		
	}
	
	// Change players
	private void switchPlayer() {
		
		this.turn = ((this.turn+2)%2)+1;
		if(this.turn==RED) round++;
		
	}
	
	// Return whose turn it is
	public int getTurn() {
		
		return this.turn;
		
	}
	
	public int getRound() {
		
		return this.round;
	}
	
	public boolean move(int col, int color) {
		
		if(turn != color) return false;
		if(col < 1 || col > cols) return false;
		if(board[0][col-1]!=EMPTY) return false;
		
		col -= 1; // to adjust for the 0th row
		int rowfree=-1;
			
		for(int i=0; i<board.length; i++) {
			if(board[i][col]==EMPTY) rowfree = i;
		}
		if(rowfree == -1) return false;
		
		
		// Make the move
		board[rowfree][col] = turn;
		switchPlayer();
		
		return true;
	}
	
	
	public void printBoard() {
		
		
		for(int i=1; i<=this.cols; i++) {
			System.out.print("----");
		}
		System.out.print("-\n ");
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				System.out.print("|");
				if(board[r][c] == RED)
					System.out.print(" R ");
				else if(board[r][c] == YELLOW)
					System.out.print(" Y ");
				else if(board[r][c] == EMPTY)
					System.out.print("   ");
				else
					System.out.print(" "+board[r][c]+" ");
			}
			System.out.print("|\n ");
			for(int i=1; i<=this.cols; i++) {
				System.out.print("----");
			}
			System.out.print("-\n ");
		}
		
		for(int n=1; n<=this.cols; n++) {
			System.out.print("|");
			System.out.print(" "+n+" ");
		}
		System.out.print("\n\n");
	} // end printBoard()
	
	public boolean validMove(int color, int col) {
		
		int ycount=0;
		int rcount=0;
		
		if(board[0][col-1] != EMPTY) return false;
		
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
		
				if(board[r][c] == YELLOW)
					ycount++;
				else if(board[r][c] == RED)
					rcount++;
			}
		}
		
		if(Math.max(ycount, rcount)>Math.min(ycount, rcount) 
				&& color == Math.max(ycount, rcount))
			return false;
		
		return true;
	}
	
	
	
	
	
	
	// Legacy code for validating a board
	
	public boolean validBoard() {
		
		// Test for null board
		if(board == null) return false;
		
		// Test for incorrect number of pieces
		
		int countR = 0;
		int countY = 0;
		
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				if(board[r][c]==RED) countR++;
				else if(board[r][c]==YELLOW) countY++;
			}
		}
		
		if(Math.abs(countR-countY)>=1) return false;
		
		// Test for incorrect placement
		
		//boolean valid = true;
		for(int c=0; c<board[0].length; c++) {
			for(int r=0; r<board.length; r++) {
				if(board[r][c] != EMPTY) {
					for(int rr=r+1; rr<board.length; rr++) {
						if(board[rr][c]==EMPTY) {
							return false;
						}
					}
				}
			}
		}
				
				
		if(board.length != rows) return false;
		if(board[0].length != cols) return false;
	
		if(checkWinner()==EMPTY) return false;
		
		return true;
	}

	public int checkWinner() {
		
		// Check columns for winner
		int streak;
		int streak_val;
		
		for(int r=0; r<board.length; r++) {
			streak = 1;
			streak_val = EMPTY;
			for(int c=0; c<board[0].length; c++) {
				
				if(board[r][c] == EMPTY) {
					streak_val = EMPTY;
					streak = 1;
				}
				else {
					
					if(streak_val == board[r][c]) {
						streak++;
						if(streak >= 4) return streak_val;
					}
					else {
						
						streak = 1;
						streak_val = board[r][c];
					}
				}
			}
		}
		
		// Check rows for winner
		
		for(int c=0; c<board[0].length; c++) {
			streak = 1;
			streak_val = EMPTY;
			for(int r=0; r<board.length; r++) {
				
				if(board[r][c] == EMPTY) {
					streak_val = EMPTY;
					streak = 1;
				}
				else {
					
					if(streak_val == board[r][c]) {		
						streak++;
						if(streak >= 4) return streak_val;
					}
					else {	
						streak = 1;
						streak_val = board[r][c];
					}
				}
			}
		}
		
		// Check diagonals down for winner
		
				for(int c=0; c<board[0].length-3; c++) {
					for(int r=0; r<board.length-3; r++) {
						
						streak = 1;
						streak_val = EMPTY;
						
						for(int r2=r, c2=c; r2<board.length && c2<board[0].length; c2++, r2++) {
							if(board[r2][c2] == EMPTY) {
								streak_val = EMPTY;
								streak = 1;
							}
							else {
								
								if(streak_val == board[r2][c2]) {		
									streak++;
									if(streak >= 4) {
										return streak_val;
									}
								}
								else {	
									streak = 1;
									streak_val = board[r2][c2];
								}
							}
						}
					}
				}
		
				// Check diagonals up for winner
				
				for(int c=0; c<board[0].length-3; c++) {
					
					for(int r=3; r<board.length; r++) {
						
						streak = 1;
						streak_val = EMPTY;
						
						for(int r2=r, c2=c; r2>=0 && c2<board[0].length; c2++, r2--) {
							if(board[r2][c2] == EMPTY) {
								streak_val = EMPTY;
								streak = 1;
							}
							else {
								
								if(streak_val == board[r2][c2]) {		
									streak++;
									if(streak >= 4) {
										return streak_val;
									}
								}
								else {	
									streak = 1;
									streak_val = board[r2][c2];
								}
							}
						}
					}
				}		
				
		return EMPTY;
		
	}
	
	
		
}
