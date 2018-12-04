class TwoThousandFourtyEight{
   //fields
   private int[][] board;
   TwoThousandFourtyEight(int x, int y) {
   // Constructor
   board = new int[y][x];
   TwoDimensional2048.addNewValue(board);
   }

   TwoThousandFourtyEight(int[][] b) {
   // Constructor
   board = b; 
   }

   public int[][] getBoard() {
      return board;
   }
   
   
   

   public int getHighestTile() {
      int high = 0;
      for(int[] i: board){
         for(int j : i){
           if(j > high){
            high = j;
           }
         }
      }

      
      
      return high;	
   }

   public int getScore() {
      
       
       int sum = 0;
       
       for(int[] i : board){
         for(int j: i){
           //System.out.println(j);
            //System.out.println(1-j);
            if(j != 0)
               sum+= -2*(1- j);
            
         }
       }
       
       
       
       return sum;
       
   }

   public void printBoard() {
   
   for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
            System.out.print(board[i][j] + ",");
      }
      System.out.println("");
   }
   }

    public TwoThousandFourtyEight copy() {
      return new TwoThousandFourtyEight(board);
   }


    public boolean up() {
    int[][] b1 = board.clone();
    
    this.board = TwoDimensional2048.up(this.board);
    //if(!TwoDimensional2048.identicalBoard(b1, board))
      TwoDimensional2048.addNewValue(board);
    

      return TwoDimensional2048.numUnoccupied(board)!= 0;
   }

    public boolean down() {
    int[][] b1 = board.clone();
    this.board = TwoDimensional2048.down(this.board);
   // if(!TwoDimensional2048.identicalBoard(b1, board))
      TwoDimensional2048.addNewValue(board);

      return TwoDimensional2048.numUnoccupied(board)!= 0;
   }

    public boolean left() {
     //left action on board
     int[][] b1 = board.clone();
     this.board = TwoDimensional2048.left(this.board);
     
    //if(!TwoDimensional2048.identicalBoard(b1, board))
      TwoDimensional2048.addNewValue(board);

     
      return TwoDimensional2048.numUnoccupied(board)!= 0;
   }

    public boolean right() {
     //rigth action on board
     int[][] b1 = board.clone();
     this.board = TwoDimensional2048.right(this.board);
     
    //if(!TwoDimensional2048.identicalBoard(b1, board))
      TwoDimensional2048.addNewValue(board);

      return TwoDimensional2048.numUnoccupied(board)!= 0;
   }
}
