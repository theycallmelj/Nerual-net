import java.util.Random;

import javax.swing.JOptionPane;

/**
*
* brief: Play tic tac toe game
*
*/

public class Play {

	public static void play() {
		
		
		
			String ans = JOptionPane.showInputDialog("I might be a while. Want to play ticktacktoe? Yes/no");
			
			
			int i = -1;
			Random rand = new Random();
			int wrong = rand.nextInt(10);
			while(ans.contains("no")) {
				
				
				i++;
				ReaderAndWriter.readSoundFile("no.wav");
				if(i > wrong) {
					//t.run();
					ans = JOptionPane.showInputDialog("I might be a while. Want to play ticktacktoe? Yes/no");
					//t.interrupt();
					wrong = rand.nextInt(10);
				}
			}
			
			if(ans.contains("yes")) {
				
				Play.playGame();
				
				
			}
		
	}
	
	
	
	
   public static void playGame()
   {
      // Create game and initialize it. First player will be 'x'
      TicTacToe game = new TicTacToe();
      
      // Print initial state of the board
      game.printBoard();
      
      
      // Play
      int[] move = new int[2];
      boolean playin = true;
      while(playin) {
      
         // Choose a move
         System.out.println("Player " + game.getMark() + " turn!");
         if ('x' == game.getMark()) {
            game.movePlayer(move);
         }
         else {
            
            game.moveAI(move);
         }
         
         // Make the move
         if (!game.placeMark(move))
            continue; // same player moves again if invalid move
         
         // Print Board
         game.printBoard();
   
         // Do we have a winner ?!
         if (game.checkForWin()) {
            System.out.println("We have a winner! Congrats player " + game.getMark() + "!");
            playin = false;
         }
         
         // It is a draw ?!
         if (game.isBoardFull()) {
            System.out.println("Appears we have a draw!");
            playin = false;
         }
         
         // No winner or draw yet => switch players
         game.changePlayer();
      }
      
      //return JOptionPane.showInputDialog("I might be a while. Want to play ticktacktoe again? Yes/no");
   }
}