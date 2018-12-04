import javax.swing.JOptionPane;

public class AiMain2048 {
	
	public static boolean going = true;
	public static void main(String[] args) {
		
		final int numIterations = 100;
		int totScores[] = {0, 0, 0};
		int totMax[]    = {0, 0, 0};
		String filePath =  JOptionPane.showInputDialog("Enter a directory for the nerual net values to be stored");	
		
		while(filePath.equals("")) {
			
		}
		
		Speak s = new Speak();
		s.start();
		//System.out.print("test");
		for (int j = 0 ; j < 3 ; j++) {
			
			for (int i = 0 ; i < numIterations ; i++) {
				TwoThousandFourtyEight board = new TwoThousandFourtyEight(4, 4);
				switch(j) {
				case 0: 
					Ai2048.playLeft(board);
					break;
				case 1:
					Ai2048.playRandom(board);
					break;
				case 2:
					Ai2048.playAI(board, filePath);
					break;
				}
				totScores[j] += board.getScore();
				totMax[j]    += board.getHighestTile();
			}
			switch(j) {
			case 0: 
				System.out.print("Left: "); 
				break;
			case 1: 
				System.out.print("Random: "); 
				break;
			case 2: 
				System.out.print("AI: "); 
				break;
			}
			System.out.println(totScores[j]/numIterations + " average points achieved; " + totMax[j]/numIterations + " average maximum tile achieved.");
		}
		going = false;
	}
	
	
}
