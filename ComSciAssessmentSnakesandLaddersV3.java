import java.util.Scanner;
import java.util.Random;

/**
 * My second 2025 Computer Science (CSC223) Assessment. I have decided to make a game of Snakes and Ladders- This code is for that game of Snakes and Ladders
 *
 * NCEA assessment code: AS91897
 * @author Aren Prior
 * @version 18/6/25
 */
public class ComSciAssessmentSnakesandLaddersV3
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

    private int whichPlayer = 1;

    private boolean goodInput;

    private int[][] gameBoard = new int [21][21];

    /**
     * Constructor for objects of class ComSciAssessmentSnakesandLaddersV3
     */
    public ComSciAssessmentSnakesandLaddersV3()
    {

        int boardHeight;
        int boardWidth;

        //Setting up EMPTY (the playable spaces)
        for(boardHeight = 0; boardHeight < BOARDHEIGHT; ++boardHeight) {
            for(boardWidth = 0; boardWidth < BOARDWIDTH; ++boardWidth) {
                gameBoard[boardHeight][boardWidth] = EMPTY;
            }
        }

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

            if (whichPlayer == 1) {
                System.out.println("A's move");
            }

            if (whichPlayer == 2) {
                System.out.println("E's move");
            }
            System.out.println("Press 1 and press enter to roll the die");
            makeGoodInput();

            dieRoll();
            printBoard();


            gameStillGoing= false;// DEBUG**************************************
        }//gameStillGoing
    }

    void printBoard() {
        for(int y = BOARDHEIGHT; y >= 0; --y) {
            System.out.println();
            for(int x = 0; x <= BOARDWIDTH; ++x) {
                if(gameBoard[y][x] == EMPTY){
                    System.out.print(" o ");
                }
                else if (gameBoard[y][x] == APLAYER) {
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

        System.out.println();
    }

    int dieRoll() {
        Random random = new Random();
        int min = 1;
        int max = 6;
        int finalRoll = random.nextInt(max - min + 1) + min;
        System.out.println("Rolling.... Please wait");
        System.out.print(" 0 ");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ie)
        {

        }
        System.out.print(" 0 ");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ie)
        {

        }
        System.out.print(" 0 ");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ie)
        {

        }
        System.out.print(" 0 ");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ie)
        {

        }
        System.out.print(" 0 ");
        if(whichPlayer== 1){
            System.out.println(" Player A rolled a "+finalRoll);
        }
        if(whichPlayer== 2){
            System.out.println(" Player E rolled a "+finalRoll);
        }
        return finalRoll;
    }

    boolean makeGoodInput(){
        Scanner keyboard;
        keyboard= new Scanner(System.in);
        boolean wantGoodInput = true;
        boolean goodInput = false;
        int input;
        while(wantGoodInput){
            while(!keyboard.hasNextInt()) {
                keyboard.nextLine();
                System.out.println("That's not a valid entry");
            }//!keyboard.hasNextInt
            keyboard.nextLine();
            input= keyboard.nextInt();
            if(input == 1){
                goodInput= true;
                wantGoodInput= false;
            }
            else{
                System.out.println("That's not a valid entry");
            }
        }
        return goodInput;
    }
}
