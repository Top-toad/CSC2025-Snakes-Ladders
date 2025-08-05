import java.util.Scanner;
import java.util.Random;

/**
 * My second 2025 Computer Science (CSC223) Assessment. I have decided to make a game of Text-Based Snakes and Ladders
 *
 * NCEA assessment code: AS91897
 * @author Aren Prior
 * @version 30/7/25
 */

// This class runs the Snakes and Ladders board game (via the constructor). The animation of the game is rendered to the terminal output. 
public class ComSciAssessmentSnakesandLaddersV10
{
    private final int APLAYER = 1;
    private final int EPLAYER = 2;
    private final int BOTHPLAYERS= 3;
    private final int BOARDHEIGHT= 20;
    private final int BOARDWIDTH= 20;
    private final int EMPTY= 0;
    private final int CLEAR= 4;
    private final int BORDER = 5;
    private final int VERTBORDER = 6;
    private final int STARTOFLADDER= 7;
    private final int STARTOFSNAKE= 8;
    private final int ENDOFSNAKE= 9;
    private final int ENDOFLADDER= 10;
    private final int LINKINGLEFT= 11;
    private final int LINKINGRIGHT= 12;
    private final int LINKINGVERTICALSNAKE= 13;
    private final int LINKINGVERTICALLADDER= 14;
    private final int ENDSPACE= 15;

    private int whichPlayer = 1;
    private int gameTurnNum= 1;
    private int currentAXPos= 1;
    private int currentEXPos= 1;
    private int currentAYPos= 1;
    private int currentEYPos= 1;
    private int bothPlayersShowState= 0;

    private boolean gameStillGoing = true;
    private boolean goodInput = true;
    private boolean firstTurn = true;
    private boolean playerAFinishedMove = false;
    private boolean playerEFinishedMove = false;
    private boolean bothPlayersMet = false;

    private int[][] gameBoard = new int [21][21];

    /**
     * Constructor for objects of class ComSciAssessmentSnakesandLaddersV10
     */
    public ComSciAssessmentSnakesandLaddersV10()
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

        //setting up ENDSPACE
        gameBoard[19][1]= ENDSPACE;

        //setting up STARTOFSNAKE
        gameBoard[3][3]= STARTOFSNAKE;
        gameBoard[3][15]= STARTOFSNAKE;
        gameBoard[9][15]= STARTOFSNAKE;
        gameBoard[11][7]= STARTOFSNAKE;
        gameBoard[13][15]= STARTOFSNAKE;
        gameBoard[17][5]= STARTOFSNAKE;
        gameBoard[17][13]= STARTOFSNAKE;
        gameBoard[19][5]= STARTOFSNAKE;
        gameBoard[19][13]= STARTOFSNAKE;

        //setting up ENDOFSNAKE
        gameBoard[1][7]= ENDOFSNAKE;
        gameBoard[1][13]= ENDOFSNAKE;
        gameBoard[3][13]= ENDOFSNAKE;
        gameBoard[7][9]= ENDOFSNAKE;
        gameBoard[9][17]= ENDOFSNAKE;
        gameBoard[13][1]= ENDOFSNAKE;
        gameBoard[13][11]= ENDOFSNAKE;
        gameBoard[17][7]= ENDOFSNAKE;
        gameBoard[17][15]= ENDOFSNAKE;

        //Setting up STARTOFLADDER
        gameBoard[1][19]= STARTOFLADDER;
        gameBoard[1][11]= STARTOFLADDER;
        gameBoard[3][1]= STARTOFLADDER;
        gameBoard[3][19]= STARTOFLADDER;
        gameBoard[7][1]= STARTOFLADDER;
        gameBoard[9][9]= STARTOFLADDER;
        gameBoard[13][7]= STARTOFLADDER;
        gameBoard[15][17]= STARTOFLADDER;
        gameBoard[17][11]= STARTOFLADDER;

        //Setting up ENDOFLADDER
        gameBoard[3][17]= ENDOFLADDER;
        gameBoard[5][7]= ENDOFLADDER;
        gameBoard[7][5]= ENDOFLADDER;
        gameBoard[7][15]= ENDOFLADDER;
        gameBoard[11][3]= ENDOFLADDER;
        gameBoard[11][13]= ENDOFLADDER;
        gameBoard[15][5]= ENDOFLADDER;
        gameBoard[19][19]= ENDOFLADDER;
        gameBoard[19][9]= ENDOFLADDER;

        //Setting up LINKINGLEFT
        gameBoard[2][19]= LINKINGLEFT;
        gameBoard[2][18]= LINKINGLEFT;
        gameBoard[2][15]= LINKINGLEFT;
        gameBoard[2][14]= LINKINGLEFT;
        gameBoard[2][11]= LINKINGLEFT;
        gameBoard[4][10]= LINKINGLEFT;
        gameBoard[4][9]= LINKINGLEFT;
        gameBoard[4][8]= LINKINGLEFT;
        gameBoard[4][19]= LINKINGLEFT;
        gameBoard[6][18]= LINKINGLEFT;
        gameBoard[6][17]= LINKINGLEFT;
        gameBoard[6][16]= LINKINGLEFT;
        gameBoard[14][7]= LINKINGLEFT;
        gameBoard[14][6]= LINKINGLEFT;
        gameBoard[18][11]= LINKINGLEFT;
        gameBoard[18][10]= LINKINGLEFT;
        gameBoard[8][15]= LINKINGLEFT;
        gameBoard[4][14]= LINKINGLEFT;
        gameBoard[16][13]= LINKINGLEFT;
        gameBoard[14][12]= LINKINGLEFT;
        gameBoard[16][5]= LINKINGLEFT;
        gameBoard[16][4]= LINKINGLEFT;
        gameBoard[16][3]= LINKINGLEFT;
        gameBoard[14][2]= LINKINGLEFT;

        //Setting up LINKINGRIGHT
        gameBoard[2][3]= LINKINGRIGHT;
        gameBoard[2][4]= LINKINGRIGHT;
        gameBoard[2][5]= LINKINGRIGHT;
        gameBoard[2][6]= LINKINGRIGHT;
        gameBoard[4][1]= LINKINGRIGHT;
        gameBoard[4][2]= LINKINGRIGHT;
        gameBoard[4][3]= LINKINGRIGHT;
        gameBoard[6][4]= LINKINGRIGHT;
        gameBoard[8][1]= LINKINGRIGHT;
        gameBoard[10][2]= LINKINGRIGHT;
        gameBoard[10][9]= LINKINGRIGHT;
        gameBoard[10][10]= LINKINGRIGHT;
        gameBoard[10][11]= LINKINGRIGHT;
        gameBoard[10][12]= LINKINGRIGHT;
        gameBoard[16][17]= LINKINGRIGHT;
        gameBoard[18][18]= LINKINGRIGHT;
        gameBoard[10][7]= LINKINGRIGHT;
        gameBoard[8][8]= LINKINGRIGHT;
        gameBoard[12][15]= LINKINGRIGHT;
        gameBoard[10][16]= LINKINGRIGHT;
        gameBoard[18][5]= LINKINGRIGHT;
        gameBoard[18][6]= LINKINGRIGHT;
        gameBoard[18][13]= LINKINGRIGHT;
        gameBoard[18][14]= LINKINGRIGHT;

        //Setting up LINKINGVERTICALSNAKE
        gameBoard[2][7]= LINKINGVERTICALSNAKE;
        gameBoard[2][13]= LINKINGVERTICALSNAKE;
        gameBoard[8][14]= LINKINGVERTICALSNAKE;
        gameBoard[7][14]= LINKINGVERTICALSNAKE;
        gameBoard[6][14]= LINKINGVERTICALSNAKE;
        gameBoard[5][14]= LINKINGVERTICALSNAKE;
        gameBoard[4][13]= LINKINGVERTICALSNAKE;
        gameBoard[10][8]= LINKINGVERTICALSNAKE;
        gameBoard[9][8]= LINKINGVERTICALSNAKE;
        gameBoard[8][9]= LINKINGVERTICALSNAKE;
        gameBoard[12][16]= LINKINGVERTICALSNAKE;
        gameBoard[11][16]= LINKINGVERTICALSNAKE;
        gameBoard[10][17]= LINKINGVERTICALSNAKE;
        gameBoard[16][12]= LINKINGVERTICALSNAKE;
        gameBoard[15][12]= LINKINGVERTICALSNAKE;
        gameBoard[14][11]= LINKINGVERTICALSNAKE;
        gameBoard[16][2]= LINKINGVERTICALSNAKE;
        gameBoard[15][2]= LINKINGVERTICALSNAKE;
        gameBoard[14][1]= LINKINGVERTICALSNAKE;
        gameBoard[18][7]= LINKINGVERTICALSNAKE;
        gameBoard[18][15]= LINKINGVERTICALSNAKE;

        //Setting up LINKINGVERTICALLADDER
        gameBoard[2][17]= LINKINGVERTICALLADDER;
        gameBoard[4][7]= LINKINGVERTICALLADDER;
        gameBoard[2][10]= LINKINGVERTICALLADDER;
        gameBoard[3][10]= LINKINGVERTICALLADDER;
        gameBoard[4][4]= LINKINGVERTICALLADDER;
        gameBoard[5][4]= LINKINGVERTICALLADDER;
        gameBoard[6][5]= LINKINGVERTICALLADDER;
        gameBoard[8][2]= LINKINGVERTICALLADDER;
        gameBoard[9][2]= LINKINGVERTICALLADDER;
        gameBoard[10][3]= LINKINGVERTICALLADDER;
        gameBoard[4][18]= LINKINGVERTICALLADDER;
        gameBoard[5][18]= LINKINGVERTICALLADDER;
        gameBoard[6][15]= LINKINGVERTICALLADDER;
        gameBoard[10][13]= LINKINGVERTICALLADDER;
        gameBoard[14][5]= LINKINGVERTICALLADDER;
        gameBoard[16][18]= LINKINGVERTICALLADDER;
        gameBoard[17][18]= LINKINGVERTICALLADDER;
        gameBoard[18][19]= LINKINGVERTICALLADDER;
        gameBoard[18][9]= LINKINGVERTICALLADDER;

        while (gameStillGoing){

            System.out.println("Snakes and Ladders - Capital S is the start of a snake, and Capital L is the start of a ladder");

            if(gameTurnNum== 1){
                gameBoard[1][1]= EPLAYER;
            }
            else{
                gameBoard[1][1]= EMPTY;
            }

            if (whichPlayer == APLAYER) {
                System.out.println("A's move");
                if(bothPlayersMet){
                    gameBoard[currentAYPos][currentAXPos]= EPLAYER;
                }
            }

            if (whichPlayer == EPLAYER) {
                System.out.println("E's move");
                if(bothPlayersMet){
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                }
            }
            System.out.println("Press 1 and press enter to roll the die");
            makeGoodInput();

            int finalRoll= dieRoll();// sets finalRoll to the value of the returned int in method dieRoll
            if(whichPlayer == APLAYER) {
                counterMovementA(finalRoll);//parses in finalRoll (the int above this line) to counterMovementA
            }
            if(whichPlayer == EPLAYER) {
                counterMovementE(finalRoll);//parses in finalRoll (the int above this line) to counterMovementA
            }

            if(playerAFinishedMove){
                if(currentAXPos== 3 && currentAYPos== 3){//APlayer snake detection
                    currentAXPos= 7;
                    currentAYPos= 1;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 15 && currentAYPos== 3){
                    currentAXPos= 13;
                    currentAYPos= 1;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 15 && currentAYPos== 9){
                    currentAXPos= 13;
                    currentAYPos= 3;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 7 && currentAYPos== 11){
                    currentAXPos= 9;
                    currentAYPos= 7;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 15 && currentAYPos== 13){
                    currentAXPos= 17;
                    currentAYPos= 9;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 5 && currentAYPos== 17){
                    currentAXPos= 1;
                    currentAYPos= 13;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 13 && currentAYPos== 17){
                    currentAXPos= 11;
                    currentAYPos= 13;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 5 && currentAYPos== 19){
                    currentAXPos= 7;
                    currentAYPos= 17;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                if(currentAXPos== 13 && currentAYPos== 19){
                    currentAXPos= 15;
                    currentAYPos= 17;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }//APlayer snake detection

                if(currentAXPos== 19 && currentAYPos== 1){//APlayer ladder detection
                    currentAXPos= 17;
                    currentAYPos= 3;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 11 && currentAYPos== 1){
                    currentAXPos= 7;
                    currentAYPos= 5;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 1 && currentAYPos== 3){
                    currentAXPos= 5;
                    currentAYPos= 7;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 19 && currentAYPos== 3){
                    currentAXPos= 15;
                    currentAYPos= 7;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 1 && currentAYPos== 7){
                    currentAXPos= 3;
                    currentAYPos= 11;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 9 && currentAYPos== 9){
                    currentAXPos= 13;
                    currentAYPos= 11;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 7 && currentAYPos== 13){
                    currentAXPos= 5;
                    currentAYPos= 15;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 17 && currentAYPos== 15){
                    currentAXPos= 19;
                    currentAYPos= 19;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                if(currentAXPos== 11 && currentAYPos== 17){
                    currentAXPos= 9;
                    currentAYPos= 19;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }//APlayer ladder detection
            }

            if(playerEFinishedMove){
                if(currentEXPos== 3 && currentEYPos== 3){//EPlayer snake detection
                    currentEXPos= 7;
                    currentEYPos= 1;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 15 && currentEYPos== 3){
                    currentEXPos= 13;
                    currentEYPos= 1;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 15 && currentEYPos== 9){
                    currentEXPos= 13;
                    currentEYPos= 3;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 7 && currentEYPos== 11){
                    currentEXPos= 9;
                    currentEYPos= 7;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 15 && currentEYPos== 13){
                    currentEXPos= 17;
                    currentEYPos= 9;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 5 && currentEYPos== 17){
                    currentEXPos= 1;
                    currentEYPos= 13;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 13 && currentEYPos== 17){
                    currentEXPos= 11;
                    currentEYPos= 13;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 5 && currentEYPos== 19){
                    currentEXPos= 7;
                    currentEYPos= 17;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                if(currentEXPos== 13 && currentEYPos== 19){
                    currentEXPos= 15;
                    currentEYPos= 17;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }//EPlayer snake detection

                if(currentEXPos== 19 && currentEYPos== 1){//EPlayer ladder detection
                    currentEXPos= 17;
                    currentEYPos= 3;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 11 && currentEYPos== 1){
                    currentEXPos= 7;
                    currentEYPos= 5;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 1 && currentEYPos== 3){
                    currentEXPos= 5;
                    currentEYPos= 7;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 19 && currentEYPos== 3){
                    currentEXPos= 15;
                    currentEYPos= 7;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 1 && currentEYPos== 7){
                    currentEXPos= 3;
                    currentEYPos= 11;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 9 && currentEYPos== 9){
                    currentEXPos= 13;
                    currentEYPos= 11;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 7 && currentEYPos== 13){
                    currentEXPos= 5;
                    currentEYPos= 15;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 17 && currentEYPos== 15){
                    currentEXPos= 19;
                    currentEYPos= 19;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                if(currentEXPos== 11 && currentEYPos== 17){
                    currentEXPos= 9;
                    currentEYPos= 19;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }//EPlayer ladder detection
            }

            gameBoard[3][3]= STARTOFSNAKE;
            gameBoard[3][15]= STARTOFSNAKE;
            gameBoard[9][15]= STARTOFSNAKE;
            gameBoard[11][7]= STARTOFSNAKE;
            gameBoard[13][15]= STARTOFSNAKE;
            gameBoard[17][5]= STARTOFSNAKE;
            gameBoard[17][13]= STARTOFSNAKE;
            gameBoard[19][5]= STARTOFSNAKE;
            gameBoard[19][13]= STARTOFSNAKE;

            gameBoard[1][19]= STARTOFLADDER;
            gameBoard[1][11]= STARTOFLADDER;
            gameBoard[3][1]= STARTOFLADDER;
            gameBoard[3][19]= STARTOFLADDER;
            gameBoard[7][1]= STARTOFLADDER;
            gameBoard[9][9]= STARTOFLADDER;
            gameBoard[13][7]= STARTOFLADDER;
            gameBoard[15][17]= STARTOFLADDER;
            gameBoard[17][11]= STARTOFLADDER;

            if(currentAXPos==currentEXPos && currentAYPos==currentEYPos){
                gameBoard[currentAYPos][currentAXPos]= BOTHPLAYERS;
                bothPlayersMet= true;
            }
            printBoard();

            System.out.println("The amount of turns played so far is "+gameTurnNum);

            gameTurnNum ++;

            if (whichPlayer == APLAYER){
                whichPlayer = EPLAYER;
            }
            else{
                whichPlayer = APLAYER;
            }

            //gameStillGoing= false;// DEBUG**************************************
        }//gameStillGoing

        if(whichPlayer == APLAYER){
            System.out.println("Player E Won");//opposite player won because the players got swtiched just above
        }

        if(whichPlayer == EPLAYER){
            System.out.println("Player A Won");
        }

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
                else if(gameBoard[y][x] == STARTOFSNAKE) {
                    System.out.print(" S ");
                }
                else if(gameBoard[y][x] == STARTOFLADDER) {
                    System.out.print(" L ");
                }
                else if(gameBoard[y][x] == ENDOFSNAKE) {
                    System.out.print(" s ");
                }
                else if(gameBoard[y][x] == ENDOFLADDER) {
                    System.out.print(" l ");
                }
                else if(gameBoard[y][x] == ENDSPACE){
                    System.out.print(" X ");
                }
                else if(gameBoard[y][x] == LINKINGLEFT){
                    System.out.print(" ← ");
                }
                else if(gameBoard[y][x] == LINKINGRIGHT){
                    System.out.print(" → ");
                }
                else if(gameBoard[y][x] == LINKINGVERTICALSNAKE){
                    System.out.print(" ↓ ");
                }
                else if(gameBoard[y][x] == LINKINGVERTICALLADDER){
                    System.out.print(" ↑ ");
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
        catch (InterruptedException ie) //such as ctrl +c on the terminal
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
        if(whichPlayer== APLAYER){
            System.out.println(" Player A rolled a "+finalRoll);
        }
        if(whichPlayer== EPLAYER){
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
        boolean whereACounter= true;
        boolean moveUpOne= false;
        playerAFinishedMove= false;

        int finalAXPos= 0;
        int finalAYPos = 0;

        int howMuchToMove = finalRoll;
        int howMuchFurtherToMove= 0;

        if(!bothPlayersMet){
            if(gameTurnNum == 1){

            }
            else{
                gameBoard[currentAYPos][currentAXPos]= EMPTY;
            }

            if(currentAXPos== 7 && currentAYPos == 1){//makes sure that ENDOFSNAKE is spawned back in, after a player has passed over it
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 13 && currentAYPos == 1){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 13 && currentAYPos == 3){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 9 && currentAYPos == 7){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 17 && currentAYPos == 9){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 1 && currentAYPos == 13){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 11 && currentAYPos == 13){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 7 && currentAYPos == 17){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            if(currentAXPos== 15 && currentAYPos == 17){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }

            if(currentAXPos== 17 && currentAYPos == 3){//makes sure that ENDOFLADDER is spawned back in, after a player has passed over it
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 7 && currentAYPos == 5){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 5 && currentAYPos == 7){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 15 && currentAYPos == 7){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 3 && currentAYPos == 11){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 13 && currentAYPos == 11){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 5 && currentAYPos == 15){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 19 && currentAYPos == 19){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            if(currentAXPos== 9 && currentAYPos == 19){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
        }
        bothPlayersMet = false;

        while(whereACounter){
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
                        if(currentAYPos == 19){
                            gameStillGoing= false;
                            howMuchToMove= 0;
                        }
                        else{
                            moveUpOne= true;
                        }
                        howMuchFurtherToMove =howMuchToMove;
                        howMuchFurtherToMove ++;
                        for(;howMuchFurtherToMove > 0; currentAXPos +=2){
                            howMuchFurtherToMove --;
                        }
                        howMuchToMove= 0;
                    }
                }
                if(currentAYPos== 19 && currentAXPos == 1){
                    gameStillGoing= false;
                }//code is exiting out of the for loop too soon to see that there is supposed to be a win state
            }
            playerAFinishedMove= true;
            whereACounter= false;
        }

        if(moveUpOne){
            currentAYPos += 2;
        }

        gameBoard[currentAYPos][currentAXPos] = APLAYER;

    }

    void counterMovementE(int finalRoll){
        boolean whereECounter= true;
        boolean moveUpOne= false;
        playerEFinishedMove= false;

        int finalEXPos= 0;
        int finalEYPos = 0;

        int howMuchToMove = finalRoll;
        int howMuchFurtherToMove= 0;

        if(!bothPlayersMet){
            if(gameTurnNum == 1){

            }
            else{
                gameBoard[currentEYPos][currentEXPos]= EMPTY;
            }

            if(currentEXPos== 7 && currentEYPos == 1){//makes sure that ENDOFSNAKE is spawned back in, after a player has passed over it
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 13 && currentEYPos == 1){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 13 && currentEYPos == 3){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 9 && currentEYPos == 7){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 17 && currentEYPos == 9){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 1 && currentEYPos == 13){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 11 && currentEYPos == 13){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 7 && currentEYPos == 17){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            if(currentEXPos== 15 && currentEYPos == 17){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }

            if(currentEXPos== 17 && currentEYPos == 3){//makes sure that ENDOFLADDER is spawned back in, after a player has passed over it
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 7 && currentEYPos == 5){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 5 && currentEYPos == 7){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 15 && currentEYPos == 7){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 3 && currentEYPos == 11){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 13 && currentEYPos == 11){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 5 && currentEYPos == 15){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 19 && currentEYPos == 19){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            if(currentEXPos== 9 && currentEYPos == 19){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
        }
        bothPlayersMet = false;
        
        while(whereECounter){
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
                        if(currentEYPos == 19){
                            gameStillGoing= false;
                            howMuchToMove= 0;
                        }
                        else{
                           moveUpOne= true; 
                        }
                        howMuchFurtherToMove= howMuchToMove;
                        howMuchFurtherToMove ++;
                        for(;howMuchFurtherToMove > 0; currentEXPos +=2){
                            howMuchFurtherToMove --;
                        }
                        howMuchToMove= 0;
                    }
                }
                if(currentEYPos== 19 && currentEXPos == 1){
                    gameStillGoing= false;
                }//code is exiting out of the for loop too soon to see that there is supposed to be a win state
            }
            playerEFinishedMove= true;
            whereECounter= false;
        }

        if(moveUpOne){
            currentEYPos += 2;
        }

        gameBoard[currentEYPos][currentEXPos] = EPLAYER;

    }
}
