import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;



public class Ai2048 {
	
   
   
   static void playLeft(TwoThousandFourtyEight board) {
		while (board.left()) {
			// loop until the board is full!
		}
	}

	static void playRandom(TwoThousandFourtyEight board) {
   
	  while(TwoDimensional2048.numUnoccupied(board.getBoard()) != 0) {
		  String option = "";
	      
	      if(canMoveLeft(board))
	         option += "l";
	      if(canMoveRight(board))
	         option += "r";
	      if(canMoveUp(board))
	         option += "u";
	      if(canMoveDown(board))
	         option += "d";
	         
	       
	      if(option.length() == 0) {
	         return;
	      }
	      
	      int j = Rnd2048.randNum(option.length());
	      
	      
	      switch(option.charAt(j)){
	         case 'l':
	            board.left();
	         case 'r':
	            board.right();
	         case 'u':
	            board.up();
	         case 'd':
	            board.down();
	        
	      }
	  }
   }

	static void playAI(TwoThousandFourtyEight board, String filePath) {
     /**
	if(canMoveLeft(board))
         board.left();
      else if(canMoveDown(board))
         board.down();
      else if(canMoveRight(board))
         board.right();
      else if(canMoveUp(board))
         board.up();
      else
         return;
      **/
		
		
		learn(board, 3, 10, 4,filePath, false);
		
		
		//NerualNet net1 = new NerualNet(20,20, board, true, "/Users/capsaicinkid/Desktop/ai/", 0);
		//while(TwoDimensional2048.numUnoccupied(board.getBoard()) != 0) {	
			//int method = Rnd2048.randNum(2);
			//switch(method) {
			//case 0:
				//moveAI(board);
			//case 1:
			//net1.move(board);
			
			//}
		//}
		
   
	}
	
	public static void run(TwoThousandFourtyEight b1, String filePath, int par1, int par2) {
		//String filePath = "/Users/capsaicinkid/Desktop/ai/";	
			NerualNet net1 = new NerualNet(par1,par2, b1, false, filePath, 0);//creates object
		   
		   
		   while(TwoDimensional2048.numUnoccupied(b1.getBoard()) != 0) {//playCode
			   net1.move(b1);
		   }
		   
		  ;
		   ReaderAndWriter.write(filePath + "/learned/ran/max.txt", String.valueOf(b1.getScore()));
	}
	
	
	public static void doRandom(TwoThousandFourtyEight board, int amtOfTimes, int par1, int par2,String filePath, double sum) {
		for(int i =0; i< amtOfTimes; i++) {
			   TwoThousandFourtyEight  b1 =  board.copy();//creates copy of board
			   
			   NerualNet net1 = new NerualNet(par1,par2, b1, true, filePath, i);//creates object
			   
			   
			   while(TwoDimensional2048.numUnoccupied(b1.getBoard()) != 0) {//playCode
				   net1.move(b1);
			   }
			   sum += b1.getScore();
			   ReaderAndWriter.write(filePath+"learning/"+i+"/max.txt",String.valueOf(b1.getScore()));
		   }
	}
	
	
	public static int maxRand = 1000000;
	
	
   public static void learn(TwoThousandFourtyEight board, int amtOfTimes, int par1, int par2,String filePath, boolean feedData) {
	   
	  // String filePath = "/Users/capsaicinkid/Desktop/ai/";
	   double sum = 0;
	   double avg = 0.0;
	   
	   
	   if(!feedData) {
		   
		   doRandom(board, amtOfTimes, par1,par2, filePath, sum);
	   }
	   //System.out.println("Here");
	   
	   
	   
	   int read=1;
	   
	   
	   if(ReaderAndWriter.learnedExists(filePath)) {
		   
		   read += Integer.parseInt(ReaderAndWriter.read(filePath+"learned/ran/times.txt"));
		   for(int i = 0; i < read; i++) {
			 //add part here about getting old learned
			   
			 int readF = ReaderAndWriter.filesInDir(filePath+"learned/roads");
			 for(int j = 0; j < readF; j++) {//get the road amount
				 //System.out.println("Here:"+amtOfTimes);
				 ReaderAndWriter.createDirectory(filePath+"learning/"+amtOfTimes+"/roads");
				 ReaderAndWriter.write(filePath+"learning/"+amtOfTimes+"/roads/"+ j+".txt",ReaderAndWriter.read(filePath+"learned/roads/"+j + ".txt" ));
				 ReaderAndWriter.write(filePath+"learning/"+amtOfTimes+"/max.txt",ReaderAndWriter.read(filePath+"learned/ran/max.txt"));
				 
			 }
			 amtOfTimes++;
		   }
	   }
	   
	   	
	   
	   
	   
	   
	   avg = sum / amtOfTimes;
	   
	  
	   ArrayList<ArrayList<Double>> bayes = getBayesianMatrix(filePath, amtOfTimes, avg);
	   HashMap<Integer, ArrayList<Double>> roads = ReaderAndWriter.readLearnedRoads(filePath, amtOfTimes);
	   
	   
	   
	   //printBayes(bayes);
	   
	   
	   
	   ArrayList<Integer> index = new ArrayList<Integer>();
	   ArrayList<Double> max = new ArrayList<Double>();
	  
	 
		   //determines what square in the bayesian matrix is most at play
		   for(int j = 0; j < bayes.get(0).size(); j++) {
			   max.add(0.0);
			   index.add(0);
			   for(int i =0; i < bayes.size(); i++) {
				   if(bayes.get(i).get(j) > max.get(j)) {
					   max.set(j, bayes.get(i).get(j));
					   index.set(j, i);
				   }
			   }
			   
			   
		   }
		   ReaderAndWriter.createDirectory(filePath+"learned/roads");
		   ReaderAndWriter.createDirectory(filePath+"learned/ran");
		  
		   
		   ArrayList<Double> avgRoadVal = ReaderAndWriter.readLearned(filePath, amtOfTimes);
		   
		   for(int i = 0; i < index.size(); i++) {
			   
			   Random rand = new Random();
			   double roadDiff =0; 
	   		   double newVal = 1;
			   switch(index.get(i)) {
			   	
			   		case 0:
			   			roadDiff=maxRand - avgRoadVal.get(i);
			   			newVal = rand.nextInt((int)Math.round(roadDiff)) + avgRoadVal.get(i);
			   			
			   			newVal = avgRoadVal.get(i) + Math.pow(1, -amtOfTimes);
			   			
			   			
			   			
			   			ReaderAndWriter.write(filePath+"learned/roads/"+i+".txt", String.valueOf(newVal));
			   			
			   		case 1:
			   			if(Math.round(avgRoadVal.get(i)) > 0) {
			   				newVal= rand.nextInt((int)Math.round(avgRoadVal.get(i)));
			   				
			   			}
			   			else {
			   				//double abs = Math.abs(Math.round(avgRoadVal.get(i)));
			   				//newVal = 0;
			   				newVal*=Math.signum(Math.round(avgRoadVal.get(i)));
			   				
			   			}
			   			
			   			ReaderAndWriter.write(filePath+"learned/roads/"+i+".txt", String.valueOf(newVal));
			   		case 2:
			   			
			   			if(Math.round(avgRoadVal.get(i)) > 0) {
			   				newVal= rand.nextInt((int)Math.round(avgRoadVal.get(i)));
			   				
			   			}
			   			else {
			   				//double abs = Math.abs(Math.round(avgRoadVal.get(i)));
			   				//newVal = 0;
			   				newVal*=Math.signum(Math.round(avgRoadVal.get(i)));
			   				
			   			}
			   			
			   			//newVal =(avgRoadVal.get(i) + Math.pow(1, -amtOfTimes));
			   			ReaderAndWriter.write(filePath+"learned/roads/"+i+".txt", String.valueOf(newVal));
			   		case 3:
			   			roadDiff=maxRand - avgRoadVal.get(i);
			   			
			   			
			   			newVal = rand.nextInt((int)Math.round(roadDiff)) + avgRoadVal.get(i);
			   			
			   			
			   			
			   			//newVal = avgRoadVal.get(i) + Math.pow(1, -amtOfTimes);
			   			
			   			ReaderAndWriter.write(filePath+"learned/roads/"+i+".txt", String.valueOf(newVal));
			   			
			   			
			   			
			   
			   }
			   
			   
			   
			   
		   }
		   ReaderAndWriter.write(filePath+"learned/ran/times.txt", String.valueOf(read));
		   //now run the board with the learned code and output that
	   run(board, filePath, par1, par2);
	   
   }
   
   public static void printBayes(ArrayList<ArrayList<Double>> bayes) {
	   
	   for(int i = 0; i < bayes.size(); i++) {
		   for(int j = 0; j < bayes.get(i).size(); j++) {
			   System.out.println(bayes.get(i).get(j));
		   }
	   }
	   
   }
  
   public static ArrayList<Double> getMax(String filePath, int amtOfTimes){
	   
	   HashMap<Integer, ArrayList<Double>> roads = ReaderAndWriter.readLearnedRoads(filePath, amtOfTimes);
	   ArrayList<Double> max = new ArrayList<Double>();
	   
	   
	   for(int i =0; i < roads.size(); i++) {
		   max.add(0.0);
		   for(int j =0; j < roads.get(i).size(); j++) {
			   if(roads.get(i).get(j)>max.get(i)) {
			   max.set(i, roads.get(i).get(j));
			   }
		   
		   }
		   
	   }
	   
	   return max;
	   
   }
   
 public static ArrayList<Double> getMin(String filePath, int amtOfTimes){
	   
	   HashMap<Integer, ArrayList<Double>> roads = ReaderAndWriter.readLearnedRoads(filePath, amtOfTimes);
	   ArrayList<Double> min = new ArrayList<Double>();
	   
	   
	   for(int i =0; i < roads.size(); i++) {
		   min.add((double) maxRand);
		   for(int j =0; j < roads.get(i).size(); j++) {
			   if(roads.get(i).get(j)<min.get(i)) {
			   min.set(i, roads.get(i).get(j));
			   }
		   
		   }
		   
	   }
	   
	   return min;
	   
   }
   
   
   public static ArrayList<ArrayList<Double>> getBayesianMatrix(String filePath, int amtOfTimes, double avg){
	   ArrayList<Double> avgRoadVal = ReaderAndWriter.readLearned(filePath, amtOfTimes);
	  
	   
	   HashMap<Integer, ArrayList<Double>> roads = ReaderAndWriter.readLearnedRoads(filePath, amtOfTimes);
	   
	   ArrayList<Double> roadBayesianAboveAvgtoAboveAvg = new ArrayList<Double>();
	   
	   
	   ArrayList<Double> roadBayesianBelowAvgtoBelowAvg = new ArrayList<Double>();
	   
	   
	   ArrayList<Double> roadBayesianBelowAvgtoAboveAvg = new ArrayList<Double>();
	   
	   ArrayList<Double> roadBayesianAboveAvgtoBelowAvg = new ArrayList<Double>();
	   
	   for(int i = 0; i <  avgRoadVal.size(); i++) {
		   roadBayesianAboveAvgtoAboveAvg.add(0.0);
		   roadBayesianBelowAvgtoBelowAvg.add(0.0);
		   roadBayesianBelowAvgtoAboveAvg.add(0.0);
		   roadBayesianAboveAvgtoBelowAvg.add(0.0);
	   }
	   
	   
	   
	   for(int i =0; i< amtOfTimes; i++) {
		   
		   int max =Integer.parseInt(ReaderAndWriter.read(filePath+"/learning/"+i+"/max.txt"));
		  
		   double pB = .5;//bayesian P(B)
		  
		   for(int j =0; j< avgRoadVal.size(); j++) {
			   if(max > avg) {
				   
				   if(roads.get(j).get(i) > avgRoadVal.get(i)) {
					   roadBayesianAboveAvgtoAboveAvg.set(i, roadBayesianAboveAvgtoAboveAvg.get(i) +1);
				   }
				   else {
					   roadBayesianAboveAvgtoBelowAvg.set(i, roadBayesianAboveAvgtoBelowAvg.get(i) + 1);
				   }
				   
				   
			   }
			   else {
				   if(roads.get(j).get(i) > avgRoadVal.get(i)) {
					   roadBayesianBelowAvgtoAboveAvg.set(i, roadBayesianBelowAvgtoAboveAvg.get(i) +1);
				   }
				   else {
					   roadBayesianBelowAvgtoBelowAvg.set(i, roadBayesianBelowAvgtoBelowAvg.get(i) + 1);
				   }
				   
			   }
		   
		   }
		   
		   
		   roadBayesianAboveAvgtoAboveAvg.set(i, roadBayesianAboveAvgtoAboveAvg.get(i)/avgRoadVal.size()/pB);
		   roadBayesianAboveAvgtoBelowAvg.set(i, roadBayesianAboveAvgtoBelowAvg.get(i)/avgRoadVal.size()/pB);
		   
		   roadBayesianBelowAvgtoAboveAvg.set(i, roadBayesianBelowAvgtoAboveAvg.get(i)/avgRoadVal.size()/pB);
		   roadBayesianBelowAvgtoBelowAvg.set(i, roadBayesianAboveAvgtoBelowAvg.get(i)/avgRoadVal.size()/pB);
	   }
	   
	   ArrayList<ArrayList<Double>> bayes = new ArrayList<ArrayList<Double>>();
	  
	   
	   bayes.add(roadBayesianAboveAvgtoAboveAvg);
	   bayes.add(roadBayesianAboveAvgtoBelowAvg);
	   bayes.add(roadBayesianBelowAvgtoAboveAvg);
	   bayes.add(roadBayesianBelowAvgtoBelowAvg);
	   return bayes;
   }
   
   
   
   
   
   
   
	public static void moveAI(TwoThousandFourtyEight board) {
		int[][] left = TwoDimensional2048.left(board.getBoard().clone());
		int[][] right = TwoDimensional2048.right(board.getBoard().clone());
		int[][] up = TwoDimensional2048.up(board.getBoard().clone());
		int[][] down = TwoDimensional2048.down(board.getBoard().clone());
		
		
		double[] values= new double[4];
		values[0] = (new TwoThousandFourtyEight(left)).getHighestTile();
		values[1] = (new TwoThousandFourtyEight(right)).getHighestTile();
		values[2] = (new TwoThousandFourtyEight(up)).getHighestTile();
		values[3] = (new TwoThousandFourtyEight(down)).getHighestTile();
		
		
		double max = 0;
		int index = 0;
		for(int i = 0; i < 4; i++) {
			if(values[i] > max) {
				max = values[i];
				index = i;
			}
		}
		
		switch(index) {
			case 0:
				board.left();
			case 1:
				board.right();
			case 2:
				board.up();
			case 3:
				board.down();
		}
		
		
		
		
	}
	
	
   public static boolean canMoveLeft(TwoThousandFourtyEight board){
   
    int[][] b1 = board.getBoard().clone();
    b1 = TwoDimensional2048.left(b1);
    return !TwoDimensional2048.identicalBoard(b1, board.getBoard());
    
   }
   public static boolean canMoveRight(TwoThousandFourtyEight board){
   
    int[][] b1 = board.getBoard().clone();
    b1 = TwoDimensional2048.right(b1);
    return !TwoDimensional2048.identicalBoard(b1, board.getBoard());
    
   }
   public static boolean canMoveUp(TwoThousandFourtyEight board){
   
    int[][] b1 = board.getBoard().clone();
    b1 = TwoDimensional2048.up(b1);
    return !TwoDimensional2048.identicalBoard(b1, board.getBoard());
    
   }
   public static boolean canMoveDown(TwoThousandFourtyEight board){
   
    int[][] b1 = board.getBoard().clone();
    b1 = TwoDimensional2048.down(b1);
    return !TwoDimensional2048.identicalBoard(b1, board.getBoard());
    
   }



   
}
