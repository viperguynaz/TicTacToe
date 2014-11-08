// the soution
// Don Irwin
import java.util.*;
public class TicTacToe {
    public static void main(String[] args) {
        //create board
        int[][] board = new int[3][3];
        // initialize board
        InitBoard(board);
        
        // create utilities
        Scanner input = new Scanner(System.in);
        int position = 0;
        
        int numTurns = 0;
        int player = -1;
        int winner = 0;
        
        // create main playing loop
        while (numTurns < 9 && winner == 0) {
            
            // set control value to check for valid move and retry if not
            boolean validMove = false;
            while (!validMove) {
                // print input prompts
                System.out.println("Enter your move as an integer from 1..9:");
                System.out.println(" 1 | 2 | 3");
                System.out.println("---|---|---");
                System.out.println(" 4 | 5 | 6");
                System.out.println("---|---|---");
                System.out.println(" 7 | 8 | 9");
                
                // get user input from scanner
                position = input.nextInt();
                validMove = TakeTurn(board, position, player);
                
                // if move isn't valid, notify user to try again
                if (!validMove) {
                    System.out.println("Invalid move - try again!");
                }
            }
            
            // print the current state of the game board
            PrintBoard(board);
            
            // check for winner, toggle player and increment number of turns taken
            winner = WinTieLose(board);
            player = -player;
            numTurns++;
        }        
        
        // print win, lose, tie results
        if (winner < 0) {
            System.out.println("The Winner is X");
        } else if (winner > 0) {
            System.out.println("The Winner is 0");
        } else {
            System.out.println("The game was a draw :-(");
        }
        
        input.close();
    }
    
    public static void InitBoard(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }        
    }
    
    public static void PrintBoard(int[][] grid) {
        System.out.println("+---+---+---+");
        for(int i = 0; i < grid.length; i++) {
            System.out.print("|");
            for(int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    System.out.print(" X |");
                } else if (grid[i][j] > 0) {
                    System.out.print(" O |");
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println("\n+---+---+---+");
        } 
    }
    
    public static int WinTieLose(int[][] grid) {            
        return WinningRow(grid) + WinningCol(grid) + WinningDiagonal(grid);
    }
    
    public static int WinningRow(int[][] grid) {
        int result = 0;
        int sum = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                sum += grid[row][col];
            }
            if (Math.abs(sum) == 3) {
                result = sum / 3;
            }
            sum = 0;
        }        
        return result;
    }
    
    public static int WinningCol(int[][] grid) {
        int result = 0;
        int sum = 0;
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                sum += grid[row][col];
            }
            if (Math.abs(sum) == 3) {
                result = sum / 3;
            }
            sum = 0;
        }        
        return result;
    }
    
    public static int WinningDiagonal(int[][] grid) {
        int result = 0;
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < grid.length; i++) {
            sum1 += grid[i][i];
            sum2 += grid[i][grid.length - 1 - i];
        }
        if (Math.abs(sum1) == 3) {
            result = sum1 / 3;
        } else if (Math.abs(sum2) == 3) {
            result = sum2 / 3;
        }
        return result;
    }
    
    public static boolean TakeTurn(int[][] grid, int position, int player) {
        boolean result = false;
        if (position > 0 && position < 10) {
            int row = (position - 1) / 3;
            int col = (position - 1) % 3;
            if (grid[row][col] == 0) {
                result = true;
                grid[row][col] = player;
            }
        }
        return result;
    }
}
