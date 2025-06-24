import java.util.Scanner;

/**
 * My second 2025 Computer Science (CSC223) Assessment. I have decided to make a game of Snakes and Ladders- This code is for that game of Snakes and Ladders
 *
 * NCEA assessment code: AS91897
 * @author Aren Prior
 * @version 16/6/25
 */
public class ComSciAssessmentSnakesandLaddersV2
{
    private boolean gameStillGoing = true;

    private final int APLAYER = -1;
    private final int EPLAYER = -2;
    private final int BOARDHEIGHT= 20;
    private final int BOARDWIDTH= 20;
    private final int EMPTY= 0;
    private final int CLEAR= -4;
    private final int BORDER = -5;
    private final int VERTBORDER = -6;

    private int iWhichPlayer = 1;

    private int[][] gameBoard = new int [21][21];

    /**
     * Constructor for objects of class ComSciAssessmentSnakesandLaddersV2
     */
    public ComSciAssessmentSnakesandLaddersV2()
    {

        int boardHeight;
        int boardWidth;

        //setting up CLEAR (the spaces inbetween the playable spaces)
        for(boardHeight = 0; boardHeight < BOARDHEIGHT; boardHeight ++) {
            for(boardWidth = 0; boardWidth < BOARDWIDTH; boardWidth += 2) {
                gameBoard[boardHeight][boardWidth] = CLEAR;
            }
        }

        //setting up CLEAR (the spaces inbetween the playable spaces)
        for(boardHeight = 0; boardHeight < BOARDHEIGHT; boardHeight += 2) {
            for(boardWidth = 0; boardWidth < BOARDWIDTH; boardWidth ++) {
                gameBoard[boardHeight][boardWidth] = CLEAR;
            }
        }

        //setting up BORDER
        for(boardHeight = 0; boardHeight <= BOARDHEIGHT; boardHeight ++) {
            for(boardWidth = 0; boardWidth <= BOARDWIDTH; boardWidth ++) {
                gameBoard[0][boardWidth] = BORDER;
                gameBoard[20][boardWidth] = BORDER;
            }
        }

        //setting up VERTBORDER
        for(boardHeight = 0; boardHeight <= BOARDHEIGHT; boardHeight ++) {
            gameBoard[boardHeight][0] = VERTBORDER;
            gameBoard[boardHeight][20] = VERTBORDER;
        }

        while (gameStillGoing){
            System.out.println("Snakes and Ladders");

            if (iWhichPlayer == 1) {
                System.out.println("A's move");
            }

            if (iWhichPlayer == 2) {
                System.out.println("E's move");
            }
            printBoard();
            gameStillGoing= false;// DEBUG**************************************
        }//gameStillGoing
    }

    void printBoard() {
        for(int y = BOARDHEIGHT; y >= 0; --y) {
            System.out.println();
            for(int x = 0; x <= BOARDWIDTH; ++x) {
                if (gameBoard[y][x] == APLAYER) {
                    System.out.print(" A ");
                } else if (gameBoard[y][x] == EPLAYER) {
                    System.out.print(" E ");}
                else if(gameBoard[y][x] == CLEAR) {
                    System.out.print ("   ");
                }
                else if(gameBoard[y][x] == BORDER) {
                    System.out.print ("---");
                }
                else if(gameBoard[y][x] == VERTBORDER) {
                    System.out.print ("|");
                }

            }
        }
        int index= 0;
        int col= 0;
        for(int row = 0; row < BOARDHEIGHT; ++row) {
            if(row%2==0){//checks for even row
                for( col=0; col<10; col++){
                    index= row * 10 + col;
                }
                System.out.println(index+"*");
            }
            else{//add row
                for(col=9; col>=0; col--){
                    index= row * 10 + col;
                }
                System.out.println(index);
            } 
        
        }
        System.out.println();
    }
}
