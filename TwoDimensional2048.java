import java.util.Scanner;
import java.util.Arrays;

public class TwoDimensional2048 {


///All the return values are set to default. You need to replace it with the correct format.

    static boolean validateBoard(int[][] b){
      for (int i = 0; i < b.length; i++) {
         //if (!OneDimensional2048.validateRow(b[i])) {
         return false;
         //} 
      }
     return true;
    }
    static int[][] blankBoard(int x, int y) {
      //this is done        
        return new int[x][y];
    }
    static boolean identicalBoard(int[][] a, int[][] b) {
      return Arrays.deepEquals(a, b);
    }
    static int numUnoccupied(int[][] b) {
    int x = 0;
      for (int i = 0; i < b.length; i++) {
         for (int j = 0; j < b[i].length; j++) {
            if (b[i][j] == 0) {
               x++;
               }
            }
         }
        return x;
    }
    static int[] randCoord(int[][] b, int offset) {
    int n = 0;
      for (int i = 0; i < b.length; i++) {
         for (int j = 0; j < b[i].length; j++) {
            if (b[i][j] == 0) {
               n++;
               }
            if (n == offset + 1) {
               int[] A = {i, j};
               return A;
               }
            }
         }
                                                      
        return new int[]{0, 0};
    }
    static boolean addNewValue(int[][] b) {
      int x = Rnd2048.randValue();
      int y = Rnd2048.randNum(numUnoccupied(b));
      int [] coords  = randCoord(b, y);
      b[coords[0]][coords[1]] = x;
        return true;
    }
    /**
    static int[][] combineLeft(int[][] b) {
    int[][] B = copyBoard(b);
      for (int i = 0; i < b.length; i++) {
         OneDimensional2048.combineLeft(B[i]);
         }
        return B;
    }
    
    
    
    static int[][] rotateLeft(int[][] b) {
    int[] readable = new int[b.length];
    int[][] otherCases = new int[b[0].length][b.length];
      for (int i = b[0].length-1; i>=0; i--) {
         readable = new int [b.length];
            for(int j = 0; j < b.length; j++){
               readable[j] = b[j][i];
               }
               otherCases[b[0].length-1-i] = readable;
               }
               return otherCases;
    }
    static int[][] left(int[][] b) {
      int[][] x = combineLeft(b);
        return x;
    }
    static int[][] right(int[][] b) {
    int [][] x = copyBoard(b);
     x = rotateLeft(x);
     x = rotateLeft(x);
     x = combineLeft(x);
     x = rotateLeft(x);
     x = rotateLeft(x);
        return x;  //check this if asserts don't pass
    }
    static int[][] up(int[][] b) {
      int [][] x = copyBoard(b);
      x = rotateLeft(x);
      x = combineLeft(x);
      x = rotateLeft(x);
      x = rotateLeft(x);
      x = rotateLeft(x);
      return x;
    }
    static int[][] down(int[][] b) {
    int [][] x = copyBoard(b);
      x = rotateLeft(x);
      x = rotateLeft(x);
      x = rotateLeft(x);
      x = combineLeft(x);
      x = rotateLeft(x);
        return x;
    }**/
    
    static int[][] combineLeft(int[][] b) {
        for(int i = 0; i< b.length; i++){
         b[i] = combineLeftOneD(b[i]);
        }
        return b;
    }
    static int[][] rotateLeft(int[][] b) {
        int[][] og = b.clone();
        
        int[][] newB = new int[b[1].length][b.length];
        
        for(int i = 0; i < og.length; i++){
        
            
            int[] row = og[og.length - i -1];
            /**
            System.out.println("Row");

            System.out.println(Arrays.toString(row));
            **/
            for(int j = 0; j < og[i].length; j++){
              // System.out.println(Arrays.toString(og[i]));
               
               
               newB[newB.length - j-1][i] = og[i][j];
               
                          
            
            
            
            }
        }
        
        
        return newB;
    }
    
    
    static int[][] left(int[][] b) {
    
        return combineLeft(b);
    }
    static int[][] right(int[][] b) {
        b = rotateLeft(b);
        b = rotateLeft(b);
        b = combineLeft(b);
        b = rotateLeft(b);
        b = rotateLeft(b);
        return b;
    }
    static int[][] up(int[][] b) {
        
        b = rotateLeft(b);
        
        b = combineLeft(b);
        for(int i = 0; i < b.length; i++){
         b[i] = invertRow(b[i]);
        }
        
        b = rotateLeft(b);
        
        for(int i = 0; i < b.length; i++){
         b[i] = invertRow(b[i]);
        }

        
        
        return b;
    }
    static int[][] down(int[][] b) {
        
        b = rotateLeft(b);
        b = rotateLeft(b);
        b = rotateLeft(b);
        b = combineLeft(b);
        for(int i = 0; i < b.length; i++){
         b[i] = invertRow(b[i]);
        }
        b = rotateLeft(b);
        b = rotateLeft(b);
        b = rotateLeft(b);
        
        for(int i = 0; i < b.length; i++){
         b[i] = invertRow(b[i]);
        }

        return b;
    }

public static int[] moveLeftOneD(int[] row) {
         
        /**if(!validateRow(row)){
            return row;
        }
        **/
        for(int j = row.length - 1; j >0; j--){

         for(int i = row.length - 1; i >0; i--){
            
               if(row[i - 1] == 0){
              
               row[i - 1] = row[i];
               row[i] = 0;
              
               }
         }    
        } 
        
        
       return row;
    }

    public static int[] combineLeftOneD(int[] row) {
       row = moveLeftOneD(row);
        
          for(int i = 0; i <row.length; i++){
            
             if(i+1 < row.length && row[i] == row[i+1]){
              
               row[i] = 2*row[i];
               row[i+1] = 0;
               }
              row = moveLeftOneD(row);
         } 
        
        row = moveLeftOneD(row);
       return row;
    }
    public static int[] invertRow(int[] row){
     int[] og = row.clone();
     for(int i = 0; i < og.length; i++){
         row[i] = og[og.length - 1 -i]; 
     }
     return row;
    }
    

    //////////////////////////////////////////////////////////////////////optional methods
    static int numMax(int[][] b){
        return 0;
    }
    static int numOccupied(int[][] b){
        
        
        return 0;
    }
    static boolean addValue(int[][] b, int x, int y,int val){
        return true;
    }
    static int[][] copyBoard(int[][] b){
    int[][] B = new int[b.length][b[0].length];
      for (int i = 0; i < b.length; i++) {
         for (int j = 0; j < b[i].length; j++) {
            B[i][j] = b[i][j];
            }
         }
        return B;
    }
    static void printBoard(int[][] b){
      for (int i = 0; i < b.length; i++) {
         for (int j = 0; j < b[i].length; j++) {
            System.out.print(b[i][j] + " ");
            if (j == b[i].length - 1) {
               System.out.println();
               }
            }
         }

    }
}