import java.util.Scanner;
import java.util.Random;

/**
 * My second 2025 Computer Science (CSC223) Assessment. I have decided to make a game of Snakes and Ladders- This code is for that game of Snakes and Ladders
 *
 * NCEA assessment code: AS91897
 * @author Aren Prior
 * @version 22/7/25
 */
public class ComSciAssessmentSnakesandLaddersV6
{
    private boolean gameStillGoing = true;

    private final int APLAYER = -1;
    private final int EPLAYER = -2;
    private final int BOTHPLAYERS= -3;
    private final int BOARDHEIGHT= 20;
    private final int BOARDWIDTH= 20;
    private final int EMPTY= 0;
    private final int CLEAR= -4;
    private final int BORDER = -5;
    private final int VERTBORDER = -6;

    private int whichPlayer = 1;
    private int gameTurnNum= 1;
    private int currentAXPos= 1;
    private int currentEXPos= 1;
    private int currentAYPos= 1;
    private int currentEYPos= 1;
    private int bothPlayersShowState= 0;

    private boolean bothPlayersShown = false;
    private boolean goodInput;
    private boolean firstTurn;

    private int[][] gameBoard = new int [21][21];

    /**
     * Constructor for objects of class ComSciAssessmentSnakesandLaddersV6
     */
    public ComSciAssessmentSnakesandLaddersV6()
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

            if(gameTurnNum== 1){
                gameBoard[1][1]= EPLAYER;
            }
            else{
                gameBoard[1][1]= EMPTY;
            }

            if (whichPlayer == 1) {
                System.out.println("A's move");
                if(bothPlayersShowState==1){
                    gameBoard[currentAYPos][currentAXPos]= EPLAYER;
                    bothPlayersShown = true;
                }
                if(bothPlayersShowState==2){
                    gameBoard[currentAYPos][currentAXPos]= EMPTY;
                    bothPlayersShowState = 0;
                }
            }

            if (whichPlayer == 2) {
                System.out.println("E's move");
                if(bothPlayersShowState==1){
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    bothPlayersShown = true;
                }
                if(bothPlayersShowState==2){
                    gameBoard[currentAYPos][currentAXPos]= EMPTY;
                    bothPlayersShowState = 0;
                }
            }
            System.out.println("Press 1 and press enter to roll the die");
            makeGoodInput();

            int finalRoll= dieRoll();// sets finalRoll to the value of the returned int in method dieRoll
            if(whichPlayer == 1) {
                counterMovementA(finalRoll);//parses in finalRoll (the int above this line) to counterMovementA
            }
            if(whichPlayer == 2) {
                counterMovementE(finalRoll);//parses in finalRoll (the int above this line) to counterMovementA
            }
            if(currentAXPos==currentEXPos && currentAYPos==currentEYPos){
                gameBoard[currentAYPos][currentAXPos]= BOTHPLAYERS;
                bothPlayersShowState= 1;
            }
            printBoard();

            System.out.println("The amount of turns played so far is "+gameTurnNum);

            gameTurnNum ++;

            if (whichPlayer == 1){
                whichPlayer = 2;
            }
            else{
                whichPlayer = 1;
            }

            if(bothPlayersShown){
                bothPlayersShowState= 2;
            }

            //gameStillGoing= false;// DEBUG**************************************
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
                else if(gameBoard[y][x] == BOTHPLAYERS){
                    System.out.print(" Æ ");}
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

    void counterMovementA(int finalRoll){
        boolean whereACounterX= true;
        boolean moveUpOne= false;

        int finalAXPos= 0;
        int finalAYPos = 0;

        int howMuchToMove = finalRoll;
        int howMuchFurtherToMove= 0;

        if(gameTurnNum == 1){

        }
        else{
            gameBoard[currentAYPos][currentAXPos]= EMPTY;
        }

        while(whereACounterX){
            if(currentAYPos== 1 || currentAYPos== 5 || currentAYPos== 9 || currentAYPos== 13 || currentAYPos== 17){
                for(;howMuchToMove > 0; currentAXPos += 2){
                    howMuchToMove --;
                    if(currentAXPos == 19){
                        moveUpOne= true;
                        howMuchFurtherToMove =howMuchToMove;
                        howMuchFurtherToMove ++;
                        for(;howMuchFurtherToMove > 0; currentAXPos -=2){
                            howMuchFurtherToMove --;
                        }
                        howMuchToMove= 0;
                    }
                }
            }

            if(currentAYPos== 3 || currentAYPos== 7 || currentAYPos== 11 || currentAYPos== 15 || currentAYPos== 19){
                for(;howMuchToMove > 0; currentAXPos -= 2){
                    howMuchToMove --;
                    if(currentAXPos == 1){
                        moveUpOne= true;
                        howMuchFurtherToMove =howMuchToMove;
                        howMuchFurtherToMove ++;
                        for(;howMuchFurtherToMove > 0; currentAXPos +=2){
                            howMuchFurtherToMove --;
                        }
                        howMuchToMove= 0;
                    }
                }
            }
            whereACounterX= false;
        }

        if(moveUpOne){
            currentAYPos += 2;
        }

        gameBoard[currentAYPos][currentAXPos] = APLAYER;

    }

    void counterMovementE(int finalRoll){
        boolean whereECounterX= true;
        boolean moveUpOne= false;

        int finalEXPos= 0;
        int finalEYPos = 0;

        int howMuchToMove = finalRoll;
        int howMuchFurtherToMove= 0;

        if(gameTurnNum == 1){

        }
        else{
            gameBoard[currentEYPos][currentEXPos]= EMPTY;
        }
        while(whereECounterX){
            if(currentEYPos== 1 || currentEYPos== 5 || currentEYPos== 9 || currentEYPos== 13 || currentEYPos== 17){
                for(;howMuchToMove > 0; currentEXPos += 2){
                    howMuchToMove --;
                    if(currentEXPos == 19){
                        moveUpOne= true;
                        howMuchFurtherToMove =howMuchToMove;
                        howMuchFurtherToMove ++;
                        for(;howMuchFurtherToMove > 0; currentEXPos -=2){
                            howMuchFurtherToMove --;
                        }
                        howMuchToMove= 0;
                    }
                }
            }

            if(currentEYPos== 3 || currentEYPos== 7 || currentEYPos== 11 || currentEYPos== 15 || currentEYPos== 19){
                for(;howMuchToMove > 0; currentEXPos -= 2){
                    howMuchToMove --;
                    if(currentEXPos == 1){
                        moveUpOne= true;
                        howMuchFurtherToMove =howMuchToMove;
                        howMuchFurtherToMove ++;
                        for(;howMuchFurtherToMove > 0; currentEXPos +=2){
                            howMuchFurtherToMove --;
                        }
                        howMuchToMove= 0;
                    }
                }
            }
            whereECounterX= false;
        }

        if(moveUpOne){
            currentEYPos += 2;
        }

        gameBoard[currentEYPos][currentEXPos] = EPLAYER;

    }
}
