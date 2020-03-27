import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer(int num) {
		
		// If a name has not been provided
		//super();
		number = num;
		
		Scanner s = new Scanner(System.in);
		String tmp = "";
		while(tmp.length()==0 || tmp.length()>20) {
			System.out.print("Please enter a name for Player "+number+": ");
			tmp = s.next();
			if(tmp.length()>20)
				System.out.println("Max name length: 20 characters");
		}
		name = tmp;
	}
	
	public HumanPlayer(int num, int color) {
		
		//super();
		number = num;
		this.color = color;
		
		Scanner s = new Scanner(System.in);
		String tmp = "";
		while(tmp.length()==0 || tmp.length()>20) {
			System.out.print("Please enter a name for Player "+number+": ");
			tmp = s.next();
			if(tmp.length()>20)
				System.out.println("Max name length: 20 characters");
		}
		name = tmp;
	}

	public HumanPlayer(int num, String name) {
		super(num,name);
		// TODO Auto-generated constructor stub
	}
	
	public HumanPlayer(int num, String name, int color) {
		super(num,name,color);
	}

}
