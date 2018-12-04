/**
*
* brief: This class implement the main functionality of tic tac toe game
* source: "A Beginner Tic-Tac-Toe Class For Java" by Coders Lexicon 
* modified by: TA's CS1111
*
*/
import java.util.Scanner;

public class TicTacToe {

    private char[][] board; 
    private char currentPlayerMark;
			
    // Constructor
    public TicTacToe() {
        board = new char[3][3];
        initializeBoard();
        currentPlayerMark = 'x';
        firstMove = true;
    }
	
	
    // Set/Reset the board back to all empty values.
    private void initializeBoard() {
        // Loop through rows
        for (int i = 0; i < 3; i++) {
            // Loop through columns
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
	
	
    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("-------------");
		
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
	
	
    // Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {
        boolean isFull = true;
		
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
		
        return isFull;
    }
	
	
    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }
	
	
    // Loop through rows and see if any are winners.
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }
	
	
    // Loop through columns and see if any are winners.
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }
	
	
    // Check the two diagonals to see if either is a win. Return true if either wins.
    private boolean checkDiagonalsForWin() {
        char first = board[0][0];
        char second = board[0][board.length-1];
        boolean firstRow;
        boolean secondRow;
        
        if(first != '-'){
            firstRow = true;
        }
        else{
            firstRow = false;
        }
        
        if(second != '-'){
            secondRow = true;
        }
        else{
            secondRow = false;
        }
        
        for(int i = 0; i < board.length; i++){
       
         if(board[i][i] != first){
            firstRow = false;
         }
         
         if(board[i][board.length-1 -i] != second){
          secondRow = false;
         }
        }
        return firstRow || secondRow;
       
    }
	
	
    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }
	
	
    // Change player marks back and forth.
    public void changePlayer() {
        if (currentPlayerMark == 'x') {
            currentPlayerMark = 'o';
        }
        else {
            currentPlayerMark = 'x';
        }
    }
	
	
    // Places a mark at the cell specified by row and col with the mark of the current player.
    public boolean placeMark(int[] move) {
		
        int row = move[0];
        int col = move[1];
        // Make sure that row and column are in bounds of the board.
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }
		
        return false;
    }
    
    // Make a move by asking the user where to move
    public boolean movePlayer(int[] move) {
      Scanner scn = new Scanner(System.in);
      
      System.out.print("User moves: ");
      move[0] = scn.nextInt();
      move[1] = scn.nextInt();
               
      while(move[0] > 2 || move[1] > 2 || 
            move[0] < 0 || move[1] < 0 ||
            board[move[0]][move[1]] != '-')
      {
         System.out.print("Wrong move! Try it again: ");
         move[0] = scn.nextInt();
         move[1] = scn.nextInt();
      }
      
      return true;
    }
    public boolean firstMove;
    // Make a move using an AI agent
    public boolean moveAI(int[] move){
      // Delay the decision 1 second
      try {
         Thread.sleep (1000);
      }
	   catch (InterruptedException e) {
	   }
 
      // Pick a location
      //for (int row = 0; row < 3; row++) {
        //for (int col = 0; col < 3; col++) {
         // if(board[row][col] == '-') {
           
            //move[0] = row;
            //move[1] = col;
            
            if(!moveToWin(move))
               if(!moveToBlock(move))
                  moveRandom(move);
            
            
            if(firstMove){
            
            move[0] = 1;
            move[1] = 1;
            firstMove = false;
            }
            System.out.println("Computer moves: " + move[0] + " " + move[1]);
            return true;
         // }
       // }
      //}
      //return false;
    }
    
    // Get current player marker
    public char getMark() {
      return currentPlayerMark;
    }
    
    // Return a random value between [0, ceil-1]
    private int randNum(int ceil)
    {
      return (int)(Math.random() * ceil);
    }
    
    public void moveRandom(int[] move){
       int total = 0;
       String pos = "";
       for(int i =0; i < board.length; i++){
         for(int j =0; j < board[i].length; j++){
            if(board[i][j] == '-'){
               total += 1;
               pos+=i + ","+ j +";";
            }
         
         }
       
       }
      int num = randNum(total);
    
      for(int i = 0; i < num; i++){
           pos = pos.substring(pos.indexOf(";")+1);
      }
      
      move[0] = Integer.parseInt(pos.substring(0, pos.indexOf(",")));
      move[1] = Integer.parseInt(pos.substring(pos.indexOf(",") + 1, pos.indexOf(";")));
      

    }
    public boolean moveToBlock(int[] move){
         boolean twoInARow = false;
         boolean oneInARow = false;
         move[1] = -1;
         
         for(int i =0; i < board.length; i++){
            for(int j =0; j < board[i].length; j++){
                  
                 
                 
                 twoInARow = board[i][j] == 'x' && oneInARow;
                 if(twoInARow){
                    move[0] = i;
                    for(int k =0; k < board[i].length; k++){
                     if(board[i][k] == '-'){
                        move[1] = k;
                        return true;
                     }
                    
                    }
                    if(move[1] == -1){
                        break;
                   
                    }
                    
                    
                 }
                 
                 
                 oneInARow = board[i][j] == 'x';
                 
                 
            }
            
         }
         
         
         
         for(int j =0; j < board[0].length; j++){
            for(int i =0; i < board.length; i++){
            
                  
                 
                 
                 twoInARow = board[i][j] == 'x' && oneInARow;
                 if(twoInARow){
                    move[1] = j;
                    for(int k =0; k < board[i].length; k++){
                     if(board[k][j] == '-'){
                        move[0] = k;
                        return true;
                     }
                     if(move[1] == -1){
                        break;
                   
                     }
                    }
                    
                 }
                 
                 
                 oneInARow = board[i][j] == 'x';
                 
                 
            }
            
         }
         
         

         for(int i = 0; i < board.length; i++){
            twoInARow = board[i][i] == 'x' && oneInARow;
            if(twoInARow){
                   for(int k =0; k < board[i].length; k++){
                       if(board[k][k] == '-'){
                        move[0] = k;
                        move[1] = k;
                        return true;
                      }
                              
                 }
             break;  
                 
                        
                 

            }         
            oneInARow = board[i][i] == 'x';
            
         }
         
         oneInARow = false;
         
         for(int i = 0; i < board.length; i++){
            twoInARow = board[i][board.length - 1- i] == 'x' && oneInARow;
            if(twoInARow){
                   
                   for(int k =0; k < board[i].length; k++){
                     if(board[k][k] == '-'){
                        move[0] = board.length - 1 -k;
                        move[1] = k;
                        
                        
                        
                        return true;
                    }
                    

                 } 
                 break;
                    
 
            }
          
                        
            oneInARow = board[i][i] == 'x';
         }
         
         
         
         return twoInARow;
    }
    
  
  public boolean moveToWin(int[] move){
   boolean twoInARow = false;
   boolean oneInARow = false;
         
         for(int i =0; i < board.length; i++){
            for(int j =0; j < board[i].length; j++){
                  
                 
                 
                 twoInARow = board[i][j] == 'o' && oneInARow;
                 if(twoInARow){
                    move[0] = i;
                    for(int k =0; k < board[i].length; k++){
                     if(board[i][k] == '-'){
                        move[1] = k;
                        return true;
                     }
                    
                    }
                  
                   break;
                   
                    
                    
                 }
                 
                 
                 oneInARow = board[i][j] == 'o';
                 
                 
            }
            
         }
         
         
         
         for(int j =0; j < board[0].length; j++){
            for(int i =0; i < board.length; i++){
            
                  
                 
                 
                 twoInARow = board[i][j] == 'o' && oneInARow;
                 if(twoInARow){
                    move[1] = j;
                    for(int k =0; k < board[i].length; k++){
                     if(board[k][j] == '-'){
                        move[0] = k;
                        return true;
                     }
                     break;
                   
                     
                    }
                    
                 }
                 
                 
                 oneInARow = board[i][j] == 'o';
                 
                 
            }
            
         }
         
         

         for(int i = 0; i < board.length; i++){
            twoInARow = board[i][i] == 'o' && oneInARow;
            if(twoInARow){
                   for(int k =0; k < board[i].length; k++){
                       if(board[k][k] == '-'){
                        move[0] = k;
                        move[1] = k;
                        return true;
                      }
                              
                 }
             break;  
                 
                        
                 

            }         
            oneInARow = board[i][i] == 'o';
            
         }
         
         oneInARow = false;
         
         for(int i = 0; i < board.length; i++){
            twoInARow = board[i][board.length - 1- i] == 'o' && oneInARow;
            if(twoInARow){
                   
                   for(int k =0; k < board[i].length; k++){
                     if(board[k][k] == '-'){
                        move[0] = board.length - 1 -k;
                        move[1] = k;
                        
                        
                        
                        return true;
                    }
                    

                 } 
                 break;
                    
 
            }
          
                        
            oneInARow = board[i][i] == 'o';
         }
         
         
         
         return twoInARow;
    }  
    
}