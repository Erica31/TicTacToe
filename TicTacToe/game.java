
/**
 * Write a description of class game here.
 *
 * @author Erica Lee
 * @version Tic tac toe game!
 */
import java.util.Scanner;
public class game{
    
    static Scanner input = new Scanner(System.in);
    static String[][] board = new String[3][3];//instantiate board
    static boolean end = false; 
    static boolean playerX = true; 
    static String playerSymbol = "X"; //default beginning symbol = X
    static int round = 2;
    
    public static void main(String[] args){
        System.out.println("Round 1: ");
        createBoard();
        game();
        playAgain();
    }
    
    public static void game(){
        while(end==false){
            playRound();
            checkWin();
            if(round>10 && end==false){
                System.out.println("It's a tie!");
                end = true;
                currentBoard();
            }
        }
        playAgain();
    }
    
    public static void createBoard(){        
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[0].length; col++){
                board[row][col] = "[ ]";
            }
        }
    }
    
    public static void currentBoard(){
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[0].length; col++){
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
    
    public static void checkWin(){
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals("[X]") && board[i][1].equals("[X]") && board[i][2].equals("[X]") ||
            board[i][0].equals("[O]") && board[i][1].equals("[O]") && board[i][2].equals("[O]")) {
                showWin(board[i][0]);
            }
            if (board[0][i].equals("[X]") && board[1][i].equals("[X]") && board[2][i].equals("[X]") ||
            board[0][i].equals("[O]") && board[1][i].equals("[O]") && board[2][i].equals("[O]")) {
                showWin(board[0][i]);
            }
        }

        if ((board[0][0].equals("[X]") && board[1][1].equals("[X]") && board[2][2].equals("[X]")) ||
        (board[0][0].equals("[O]") && board[1][1].equals("[O]") && board[2][2].equals("[O]")) ||
        (board[0][2].equals("[X]") && board[1][1].equals("[X]") && board[2][0].equals("[X]")) ||
        (board[0][2].equals("[O]") && board[1][1].equals("[O]") && board[2][0].equals("[O]"))) {
            showWin(board[1][1]);
        }
    }
    
    public static void showWin(String player){
        currentBoard();
        if (player.equals("[X]")) System.out.println("Player X wins! \n");
        else System.out.println("Player O wins! \n");
        end = true;
    }
    
    public static boolean validMove(int row, int col){
        boolean valid = true;
        if(row>2||col>2||board[row][col]!="[ ]") valid=false;
        return valid;
    }
    
    public static void playAgain(){
        System.out.println("Play Again? y/n");
        String response = input.nextLine();
        response.toLowerCase();
        if ("y".equals(response)){
            end = false;
            round = 2;
            System.out.println("Round 1: ");
            createBoard();
            game();
        }else if("n".equals(response)) end = true;
    }
    
    public static void playRound(){
        currentBoard();
        if(playerX){
            System.out.print("X, make your move (row,col): ");
        }else if(!playerX){
            System.out.print("O, make your move (row,col): ");
        }
        
        String playerMove = input.nextLine();
        System.out.println();
        int playerR = Integer.parseInt(playerMove.substring(0, 1));
        int playerC = Integer.parseInt(playerMove.substring(2, 3));
        
        if(validMove(playerR, playerC)){
            if(round<10)System.out.println("Round "+round+": ");
            for(int row=0; row<board.length; row++){
                for(int col=0; col<board[0].length; col++){
                    if(row==playerR && col==playerC && playerX){
                        playerSymbol = "X";
                        playerX=false;
                    }else if(row==playerR && col==playerC && !playerX){
                        playerSymbol = "O";
                        playerX=true;
                    }
                }
            }
            board[playerR][playerC] = "["+playerSymbol+"]";
            round++;
        }else
            System.out.println("INVALID MOVE, TRY AGAIN:");  
    }  
}