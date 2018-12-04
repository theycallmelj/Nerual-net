import java.util.ArrayList;

public class Core {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoThousandFourtyEight board = new TwoThousandFourtyEight(4, 4);
	
		
			//Ai2048.learn(board, 3, 10, 4, "/Users/capsaicinkid/Desktop/ai/");
		
		/**
		int i = 0;
		
		while( i < 10) {
				TwoThousandFourtyEight  b1 =  board.copy();//creates copy of board
			   
			   NerualNet net1 = new NerualNet(10,4, b1, true, "/Users/capsaicinkid/Desktop/ai1/", i);//creates object
			   
			   
			   while(TwoDimensional2048.numUnoccupied(b1.getBoard()) != 0) {//playCode
				   net1.move(b1);
			   }
			  
			   
			   
			   if(b1.getScore() > 800) {
			   ReaderAndWriter.write("/Users/capsaicinkid/Desktop/ai1/learning/"+i+"/max.txt",String.valueOf(b1.getScore()));
			   i++;
			   }
		}
		
		**/
		Speak s = new Speak();
		s.run();
		Ai2048.learn(board, 9, 10, 4, "/Users/capsaicinkid/Desktop/ai3/", false);
	
		
		
		
		
		//for(int i = 0; i < net1.getHiddenNodesnodes().length; i++) {
		//System.out.println();
		
		/**for(i = 0; i < net1.getInputNodes().size(); i++) {
			System.out.println("OUT:");
			System.out.println(net1.getInputNodes().get(i).getLon());
		
		}
		**/
		//board.printBoard();
		System.out.println("done");
		/**switch(net1.makeDecision()) {
			case 0:
				board.left();
			case 1:
				board.right();
			case 2:
				board.up();
			case 3:
				board.down();
		
		}
		ArrayList<OSMNode> ns = new ArrayList<OSMNode>();
		int b =0;
		for(int i = 0; i < board.getBoard().length; i++) {
			for(int j = 0; j < board.getBoard()[i].length; j++) {
				ns.add(new OSMNode(board.getBoard()[i][j], b, "input"));
				b++;
			}
		}
		**/
		
		
		
		
	
		
		
		
	}
	
	

}
