import java.util.Scanner;//imports the scanner- this checks for keyboard input
import java.util.Random;//imports the .random util- this allows generation of random values

/**
 * My second 2025 Computer Science (CSC223) Assessment. I have decided to make a game of Text-Based Snakes and Ladders
 *
 * NCEA assessment code: AS91897
 * @author Aren Prior
 * @version 31/7/25
 */

// This class runs the Snakes and Ladders board game (via the constructor). The animation of the game is rendered to the terminal output. 
public class ComSciAssessmentSnakesandLaddersV11
{
    //final int variables used in the code
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

    //int variables used in the code
    private int whichPlayer = 1;
    private int gameTurnNum= 1;
    private int currentAXPos= 1;
    private int currentEXPos= 1;
    private int currentAYPos= 1;
    private int currentEYPos= 1;

    //booleans used in the code 
    private boolean gameStillGoing = true;
    private boolean goodInput = true;
    private boolean playerAFinishedMove = false;
    private boolean playerEFinishedMove = false;
    private boolean bothPlayersMet = false;

    //the main 2D array of ints for the game, it is 21,21 because I needed to leave room for the borders, as well as empty spaces between the board spaces
    private int[][] gameBoard = new int [21][21];

    /**
     * Constructor for objects of class ComSciAssessmentSnakesandLaddersV11
     */
    public ComSciAssessmentSnakesandLaddersV11()
    {
        //variables to assist in the for loops
        int boardHeight;
        int boardWidth;

        //Setting up EMPTY (the playable spaces) First fills up the entire board with EMPTY, and then other variables (below) slowly overwrite the unneeded EMPTY spaces
        for(boardHeight = 0; boardHeight < BOARDHEIGHT; ++boardHeight) {
            for(boardWidth = 0; boardWidth < BOARDWIDTH; ++boardWidth) {
                gameBoard[boardHeight][boardWidth] = EMPTY;
            }
        }

        //setting up CLEAR (the spaces inbetween the playable spaces) Makes all the gaps in between the columns (vertical), while making sure there is still room for EMPTY (the playable spaces)
        for(boardHeight = 0; boardHeight < BOARDHEIGHT; boardHeight ++) {
            for(boardWidth = 0; boardWidth < BOARDWIDTH; boardWidth += 2) {
                gameBoard[boardHeight][boardWidth] = CLEAR;
            }
        }

        //setting up CLEAR (the spaces inbetween the playable spaces) Makes all the gaps in between the rows (horizontal), while making sure there is still room for EMPTY (the playable spaces)
        for(boardHeight = 0; boardHeight < BOARDHEIGHT; boardHeight += 2) {
            for(boardWidth = 0; boardWidth < BOARDWIDTH; boardWidth ++) {
                gameBoard[boardHeight][boardWidth] = CLEAR;
            }
        }

        //setting up BORDER (the horizontal border around the edge)
        for(boardHeight = 0; boardHeight <= BOARDHEIGHT; boardHeight ++) {
            for(boardWidth = 0; boardWidth <= BOARDWIDTH; boardWidth ++) {
                gameBoard[0][boardWidth] = BORDER;
                gameBoard[20][boardWidth] = BORDER;
            }
        }

        //setting up VERTBORDER (the vertical border around the edge)
        for(boardHeight = 0; boardHeight <= BOARDHEIGHT; boardHeight ++) {
            gameBoard[boardHeight][0] = VERTBORDER;
            gameBoard[boardHeight][20] = VERTBORDER;
        }

        //setting up ENDSPACE (Assigning where ENDSPACE is on the board array)
        gameBoard[19][1]= ENDSPACE;

        //setting up STARTOFSNAKE (Assigning where the start of the snakes are on the board array)(9 snakes on the board)
        gameBoard[3][3]= STARTOFSNAKE;
        gameBoard[3][15]= STARTOFSNAKE;
        gameBoard[9][15]= STARTOFSNAKE;
        gameBoard[11][7]= STARTOFSNAKE;
        gameBoard[13][15]= STARTOFSNAKE;
        gameBoard[17][5]= STARTOFSNAKE;
        gameBoard[17][13]= STARTOFSNAKE;
        gameBoard[19][5]= STARTOFSNAKE;
        gameBoard[19][13]= STARTOFSNAKE;

        //setting up ENDOFSNAKE (Assigning where the end of the snakes are on the board array)
        gameBoard[1][7]= ENDOFSNAKE;
        gameBoard[1][13]= ENDOFSNAKE;
        gameBoard[3][13]= ENDOFSNAKE;
        gameBoard[7][9]= ENDOFSNAKE;
        gameBoard[9][17]= ENDOFSNAKE;
        gameBoard[13][1]= ENDOFSNAKE;
        gameBoard[13][11]= ENDOFSNAKE;
        gameBoard[17][7]= ENDOFSNAKE;
        gameBoard[17][15]= ENDOFSNAKE;

        //Setting up STARTOFLADDER (Assigning where the start of the ladders are on the board array)(9 ladders on the board)
        gameBoard[1][19]= STARTOFLADDER;
        gameBoard[1][11]= STARTOFLADDER;
        gameBoard[3][1]= STARTOFLADDER;
        gameBoard[3][19]= STARTOFLADDER;
        gameBoard[7][1]= STARTOFLADDER;
        gameBoard[9][9]= STARTOFLADDER;
        gameBoard[13][7]= STARTOFLADDER;
        gameBoard[15][17]= STARTOFLADDER;
        gameBoard[17][11]= STARTOFLADDER;

        //Setting up ENDOFLADDER (Assigning where the end of the ladders are on the board array)
        gameBoard[3][17]= ENDOFLADDER;
        gameBoard[5][7]= ENDOFLADDER;
        gameBoard[7][5]= ENDOFLADDER;
        gameBoard[7][15]= ENDOFLADDER;
        gameBoard[11][3]= ENDOFLADDER;
        gameBoard[11][13]= ENDOFLADDER;
        gameBoard[15][5]= ENDOFLADDER;
        gameBoard[19][19]= ENDOFLADDER;
        gameBoard[19][9]= ENDOFLADDER;

        //Setting up LINKINGLEFT (Assigning where the left pointing arrows are on the board array) (it is called LINKINGLEFT because it links snakes/ladders)
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

        //Setting up LINKINGRIGHT (Assigning where the right pointing arrows are on the board array) (it is called LINKINGRIGHT because it links snakes/ladders)
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

        //Setting up LINKINGVERTICALSNAKE (Assigning where the downwards pointing arrows are on the board array) (it is called LINKINGVERTICALSNAKE because snakes only need down arrows to show the "link", and ladders only need up arrows)
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

        //Setting up LINKINGVERTICALLADDER (Assigning where the downwards pointing arrows are on the board array) (it is called LINKINGVERTICALLADDER because ladders only need up arrows to show the "link", and snakes only need down arrows)
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

        while (gameStillGoing){//main loop that keeps the whole game going after each "turn"
            //user information text
            System.out.println("Snakes and Ladders");
            System.out.println("INSTRUCTIONS: Capital S is the start of a snake, lowercase s is the end. Capital L is the start of a ladder, lowercase l is the end, A is Player A, E is Player E, and Æ is both players sharing a space");
            System.out.println("INSTRUCTIONS: o is an empty ('normal') space, X is the end space (goal), and the arrows show where the snakes/ ladders lead (with up arrows for ladders, and down arrows for snakes)");
        
            if(gameTurnNum== 1){//if it is the first turn in the game, because APLAYER moves first, the first space must have to show the EPLAYER the first time the board is rendered
                gameBoard[1][1]= EPLAYER;
            }
            else{//otherwise the first space must always be empty (because there is no way to return to it)
                gameBoard[1][1]= EMPTY;
            }

            if (whichPlayer == APLAYER) {//if APLAYER is playing, gives the user that information
                System.out.println("A's move");
                if(bothPlayersMet){//if both players met on the same space last turn, and it is APLAYER's turn then the space that they both met on must be rendered as EPLAYER for that turn
                    gameBoard[currentAYPos][currentAXPos]= EPLAYER;
                }
            }

            if (whichPlayer == EPLAYER) {//if EPLAYER is playing, gives the user that information
                System.out.println("E's move");
                if(bothPlayersMet){//if both players met on the same space last turn, and it is EPLAYER's turn then the space that they both met on must be rendered as APLAYER for that turn
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                }
            }
            //user information
            System.out.println("Press 1 and press enter to roll the die");
            makeGoodInput();//filters the users input

            int finalRoll= dieRoll();// sets finalRoll to the value of the returned int in method dieRoll (this method rolls the die randomly, and also does a little 'animation' while it is rolling)
            if(whichPlayer == APLAYER) {//if the current player is APLAYER, then we want to calculate counter movement for APLAYER
                counterMovementA(finalRoll);//parses in finalRoll to counterMovementA
            }
            if(whichPlayer == EPLAYER) {//if the current player is EPLAYER, then we want to calculate counter movement for EPLAYER
                counterMovementE(finalRoll);//parses in finalRoll to counterMovementE
            }

            if(playerAFinishedMove){//if APLAYER finished moving, then we can calculate if they are on a snake/ ladder
                if(currentAXPos== 3 && currentAYPos== 3){//APlayer snake detection (if APLAYER is on these coordinates in the array, then they must be on a snake, so we move them down to the end of that snake, and tell the players that happened
                    currentAXPos= 7;
                    currentAYPos= 1;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 15 && currentAYPos== 3){
                    currentAXPos= 13;
                    currentAYPos= 1;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 15 && currentAYPos== 9){
                    currentAXPos= 13;
                    currentAYPos= 3;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 7 && currentAYPos== 11){
                    currentAXPos= 9;
                    currentAYPos= 7;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 15 && currentAYPos== 13){
                    currentAXPos= 17;
                    currentAYPos= 9;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 5 && currentAYPos== 17){
                    currentAXPos= 1;
                    currentAYPos= 13;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 13 && currentAYPos== 17){
                    currentAXPos= 11;
                    currentAYPos= 13;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 5 && currentAYPos== 19){
                    currentAXPos= 7;
                    currentAYPos= 17;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }
                else if(currentAXPos== 13 && currentAYPos== 19){
                    currentAXPos= 15;
                    currentAYPos= 17;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Snake!");
                }//APlayer snake detection

                if(currentAXPos== 19 && currentAYPos== 1){//APlayer ladder detection (if APLAYER is on these coordinates in the array, then they must be on a ladder, so we move them up to the end of that ladder, and tell the players that happened
                    currentAXPos= 17;
                    currentAYPos= 3;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 11 && currentAYPos== 1){
                    currentAXPos= 7;
                    currentAYPos= 5;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 1 && currentAYPos== 3){
                    currentAXPos= 5;
                    currentAYPos= 7;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 19 && currentAYPos== 3){
                    currentAXPos= 15;
                    currentAYPos= 7;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 1 && currentAYPos== 7){
                    currentAXPos= 3;
                    currentAYPos= 11;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 9 && currentAYPos== 9){
                    currentAXPos= 13;
                    currentAYPos= 11;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 7 && currentAYPos== 13){
                    currentAXPos= 5;
                    currentAYPos= 15;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 17 && currentAYPos== 15){
                    currentAXPos= 19;
                    currentAYPos= 19;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }
                else if(currentAXPos== 11 && currentAYPos== 17){
                    currentAXPos= 9;
                    currentAYPos= 19;
                    gameBoard[currentAYPos][currentAXPos]= APLAYER;
                    System.out.println("Player A hit a Ladder!");
                }//APlayer ladder detection
            }//playerAFinishedMove

            if(playerEFinishedMove){//if EPLAYER finished moving, then we can calculate if they are on a snake/ ladder 
                if(currentEXPos== 3 && currentEYPos== 3){//EPlayer snake detection (if EPLAYER is on these coordinates in the array, then they must be on a snake, so we move them down to the end of that snake, and tell the players that happened
                    currentEXPos= 7;
                    currentEYPos= 1;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 15 && currentEYPos== 3){
                    currentEXPos= 13;
                    currentEYPos= 1;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 15 && currentEYPos== 9){
                    currentEXPos= 13;
                    currentEYPos= 3;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 7 && currentEYPos== 11){
                    currentEXPos= 9;
                    currentEYPos= 7;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 15 && currentEYPos== 13){
                    currentEXPos= 17;
                    currentEYPos= 9;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 5 && currentEYPos== 17){
                    currentEXPos= 1;
                    currentEYPos= 13;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 13 && currentEYPos== 17){
                    currentEXPos= 11;
                    currentEYPos= 13;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 5 && currentEYPos== 19){
                    currentEXPos= 7;
                    currentEYPos= 17;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }
                else if(currentEXPos== 13 && currentEYPos== 19){
                    currentEXPos= 15;
                    currentEYPos= 17;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Snake!");
                }//EPlayer snake detection

                if(currentEXPos== 19 && currentEYPos== 1){//EPlayer ladder detection (if EPLAYER is on these coordinates in the array, then they must be on a ladder, so we move them up to the end of that ladder, and tell the players that happened
                    currentEXPos= 17;
                    currentEYPos= 3;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 11 && currentEYPos== 1){
                    currentEXPos= 7;
                    currentEYPos= 5;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 1 && currentEYPos== 3){
                    currentEXPos= 5;
                    currentEYPos= 7;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 19 && currentEYPos== 3){
                    currentEXPos= 15;
                    currentEYPos= 7;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 1 && currentEYPos== 7){
                    currentEXPos= 3;
                    currentEYPos= 11;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 9 && currentEYPos== 9){
                    currentEXPos= 13;
                    currentEYPos= 11;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 7 && currentEYPos== 13){
                    currentEXPos= 5;
                    currentEYPos= 15;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 17 && currentEYPos== 15){
                    currentEXPos= 19;
                    currentEYPos= 19;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }
                else if(currentEXPos== 11 && currentEYPos== 17){
                    currentEXPos= 9;
                    currentEYPos= 19;
                    gameBoard[currentEYPos][currentEXPos]= EPLAYER;
                    System.out.println("Player E hit a Ladder!");
                }//EPlayer ladder detection
            }//playerEFinishedMove

            //sets the positions in the array that are supposed to be STARTOFSNAKE back to STARTOFSNAKE, if they have been set to either APLAYER or EPLAYER
            gameBoard[3][3]= STARTOFSNAKE;
            gameBoard[3][15]= STARTOFSNAKE;
            gameBoard[9][15]= STARTOFSNAKE;
            gameBoard[11][7]= STARTOFSNAKE;
            gameBoard[13][15]= STARTOFSNAKE;
            gameBoard[17][5]= STARTOFSNAKE;
            gameBoard[17][13]= STARTOFSNAKE;
            gameBoard[19][5]= STARTOFSNAKE;
            gameBoard[19][13]= STARTOFSNAKE;

            //sets the positions in the array that are supposed to be STARTOFLADDER back to STARTOFLADDER, if they have been set to either APLAYER or EPLAYER
            gameBoard[1][19]= STARTOFLADDER;
            gameBoard[1][11]= STARTOFLADDER;
            gameBoard[3][1]= STARTOFLADDER;
            gameBoard[3][19]= STARTOFLADDER;
            gameBoard[7][1]= STARTOFLADDER;
            gameBoard[9][9]= STARTOFLADDER;
            gameBoard[13][7]= STARTOFLADDER;
            gameBoard[15][17]= STARTOFLADDER;
            gameBoard[17][11]= STARTOFLADDER;

            if(currentAXPos==currentEXPos && currentAYPos==currentEYPos){//checks to see if both players are on the same space
                gameBoard[currentAYPos][currentAXPos]= BOTHPLAYERS;//if they are, then we set that space to BOTHPLAYERS
                bothPlayersMet= true;//and set this to true, so that we can know to reset the BOTHPLAYERS space to either APLAYER or EPLAYER next turn
            }
            printBoard();//prints the board out

            System.out.println("The amount of turns played so far is "+gameTurnNum);//user information

            gameTurnNum ++;//adds to the total number of turns played

            if (whichPlayer == APLAYER){//changes it to the other players turn
                whichPlayer = EPLAYER;
            }
            else{
                whichPlayer = APLAYER;
            }

        }//gameStillGoing

        //displays who won the game, once someone ended the game by winning
        if(whichPlayer == APLAYER){
            System.out.println("Player E Won");//'opposite' player won because the players got swtiched just above
        }

        if(whichPlayer == EPLAYER){
            System.out.println("Player A Won");//'opposite' player won because the players got swtiched just above
        }

    }

    void printBoard() {//prints out the board
        for(int y = BOARDHEIGHT; y >= 0; --y) {
            System.out.println();//makes sure everything dosen't get printed on the same line
            for(int x = 0; x <= BOARDWIDTH; ++x) {
                if(gameBoard[y][x] == EMPTY){//this set of loops checks to see what charcters are in the array, and prints them out where they are
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

        System.out.println();//adds another line, so that text cannot be printed on the same line as the bottom row of board
    }

    int dieRoll() {//randomizes the die roll, and also creates a little 'animation', while the die is rolling
        Random random = new Random();//creates a new variable of type Random called random
        int min = 1;
        int max = 6;
        int finalRoll = random.nextInt(max - min + 1) + min;//generates a random number between 1-6, using min and max for the parameters of 1-6, and setting that random number to finalRoll
        System.out.println("Rolling.... Please wait");//user information
        System.out.print(" 0 ");
        try//these sets of try/ catch statements make the program sleep for 1000ms at a time, before printing out another '0' making a sort of four second long animation
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ie) //a bit like ctrl +c on the terminal (would clear the terminal and the program would crash if there was an exception)
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
        if(whichPlayer== APLAYER){//checks what player rolled that number
            System.out.println(" Player A rolled a "+finalRoll);
        }
        if(whichPlayer== EPLAYER){
            System.out.println(" Player E rolled a "+finalRoll);
        }
        return finalRoll;//returns the random roll from 1-6
    }

    boolean makeGoodInput(){//checks to make sure user input is good
        Scanner keyboard;//sets up scanner called keyboard
        keyboard= new Scanner(System.in);
        boolean wantGoodInput = true;
        boolean goodInput = false;
        int input;

        while(wantGoodInput){//we still want a good input
            while(!keyboard.hasNextInt()) {//while the keyboard dosen't have a number
                keyboard.nextLine();//clear the keyboard
                System.out.println("That's not a valid entry");//tell the user
            }//!keyboard.hasNextInt
            input= keyboard.nextInt();//we know that the input is a number
            if(input == 1){//checks if that number is 1
                goodInput= true;
                wantGoodInput= false;
            }
            else{//otherwise asks again
                System.out.println("That's not a valid entry");
            }
        }//wantGoodInput
        return goodInput;//returns that the user has inputted the correct thing to roll the die
    }

    void counterMovementA(int finalRoll){//Calculates where APLAYER's counter is supposed to be after they roll the die (parses in finalRoll)
        boolean whereACounter= true;//We are still looking for where the counter is
        boolean moveUpOne= false;//if they need to move up a row
        playerAFinishedMove= false;//if they have finished moving for this turn

        int howMuchToMove = finalRoll;//how much to move as set by how much was rolled on the die
        int howMuchFurtherToMove= 0;//how much further to move if they move up a row

        if(!bothPlayersMet){//if both players did meet last turn then we don't want to overwrite the other player on the board, by setting the last position of APLAYER back to whatever it was by defult
            if(gameTurnNum == 1){//if it is the first turn, then because APLAYER plays first we don't want the first space to be set back to EMPTY, because EPLAYER will still be on the first space after the first turn

            }
            else{//otherwise we can set the current (where APLAYER would have been rendered for the past turn) Y and X postions to EMPTY, to return the space that APLAYER would have last occupied back to defult
                gameBoard[currentAYPos][currentAXPos]= EMPTY;
            }

            if(currentAXPos== 7 && currentAYPos == 1){//makes sure that ENDOFSNAKE is spawned back in, after APLAYER has passed over it (because if APLAYER was on any of these coordinates last turn, then they must have been on the end of a snake)
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 13 && currentAYPos == 1){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 13 && currentAYPos == 3){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 9 && currentAYPos == 7){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 17 && currentAYPos == 9){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 1 && currentAYPos == 13){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 11 && currentAYPos == 13){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 7 && currentAYPos == 17){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }
            else if(currentAXPos== 15 && currentAYPos == 17){
                gameBoard[currentAYPos][currentAXPos]= ENDOFSNAKE;
            }

            if(currentAXPos== 17 && currentAYPos == 3){//makes sure that ENDOFLADDER is spawned back in, after APLAYER has passed over it (because if APLAYER was on any of these coordinates last turn, then they must have been on the end of a ladder)
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 7 && currentAYPos == 5){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 5 && currentAYPos == 7){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 15 && currentAYPos == 7){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 3 && currentAYPos == 11){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 13 && currentAYPos == 11){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 5 && currentAYPos == 15){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 19 && currentAYPos == 19){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
            else if(currentAXPos== 9 && currentAYPos == 19){
                gameBoard[currentAYPos][currentAXPos]= ENDOFLADDER;
            }
        }//!bothPlayersMet
        bothPlayersMet = false;//sets bothPlayersMet to false because they must have already been shown as meeting on the same space, so we can go back to resetting spaces where players have been in the past to defult

        while(whereACounter){//we are still looking for where APLAYER is supposed to be according to die roll
            if(currentAYPos== 1 || currentAYPos== 5 || currentAYPos== 9 || currentAYPos== 13 || currentAYPos== 17){//if APLAYER is moving left to right (because snakes and ladders reverses directions each row)
                for(;howMuchToMove > 0; currentAXPos += 2){//if the die roll has more than 0 in it still, then APLAYER will move one space along at a time (displayed as += 2 because it has to move two spaces, because of the empty spaces in between the spaces)
                    howMuchToMove --;//removes one from the remaining howMuchToMove everytime the player moves one space 
                    if(currentAXPos == 19){//if APLAYER is at the end of a row
                        moveUpOne= true;//then APLAYER needs to move up one row
                        howMuchFurtherToMove =howMuchToMove;//because howMuchToMove is no longer being taken away from each time we move the player, we need to set the current value of howMuchToMove to howMuchFurtherToMove
                        howMuchFurtherToMove ++;//then add one to howMuchFurtherToMove, because above one more than needed got taken away from howMuchToMove (we do this to avoid the player rolling a 5, and then only moving 4 after moving up a row)
                        for(;howMuchFurtherToMove > 0; currentAXPos -=2){//then because we are now moving the counter the opposite direction, one row above (-= 2, because we need space for the empty spaces in between rows)
                            howMuchFurtherToMove --;//continue to minus how many spaces there is left to move, until there is nothing left
                        }
                        howMuchToMove= 0;//set howMuchToMove to 0, to get out of the for loop
                    }
                }
            }

            if(currentAYPos== 3 || currentAYPos== 7 || currentAYPos== 11 || currentAYPos== 15 || currentAYPos== 19){//if APLAYER is moving right to left (because snakes and ladders reverses directions each row)
                for(;howMuchToMove > 0; currentAXPos -= 2){//if the die roll has more than 0 in it still, then APLAYER will move one space along at a time (displayed as -= 2 because it has to move two spaces, because of the empty spaces in between the spaces)
                    howMuchToMove --;//removes one from the remaining howMuchToMove everytime the player moves one space
                    if(currentAXPos == 1){//if APLAYER is at the end of a row
                        if(currentAYPos == 19){//if they are at the end of a row, and they are currently on the top row, then they must be on the winning space
                            gameStillGoing= false;//we can stop the main game loop, because a player won
                            howMuchToMove= 0;//exiting out of the for loop
                        }
                        else{//otherwise they must not be on the top row, so we can move them up to the row above
                            moveUpOne= true;
                        }
                        howMuchFurtherToMove =howMuchToMove;//because howMuchToMove is no longer being taken away from each time we move the player, we need to set the current value of howMuchToMove to howMuchFurtherToMove
                        howMuchFurtherToMove ++;//then add one to howMuchFurtherToMove, because above one more than needed got taken away from howMuchToMove (we do this to avoid the player rolling a 5, and then only moving 4 after moving up a row)
                        for(;howMuchFurtherToMove > 0; currentAXPos +=2){//then because we are now moving the counter the opposite direction, one row above (+= 2, because we need space for the empty spaces in between rows)
                            howMuchFurtherToMove --;//continue to minus how many spaces there is left to move, until there is nothing left
                        }
                        howMuchToMove= 0;//set howMuchToMove to 0, to get out of the for loop
                    }
                }
                if(currentAYPos== 19 && currentAXPos == 1){//code is exiting out of the for loop too soon to see that there is supposed to be a win state- this if statement fixes a big where if you land perfectly on the win space, the game won't count it until next turn
                    gameStillGoing= false;
                }
            }
            playerAFinishedMove= true;//the player has finished thier move
            whereACounter= false;//we no longer need to look for where thier counter is
        }

        if(moveUpOne){//if we need to move up a row
            currentAYPos += 2;//then we move them up a row, adding two because we need to account for the empty space in between rows
        }

        gameBoard[currentAYPos][currentAXPos] = APLAYER;//sets the calcuated positions to APLAYER

    }

    void counterMovementE(int finalRoll){//Calculates where EPLAYER's counter is supposed to be after they roll the die (parses in finalRoll)
        boolean whereECounter= true;//We are still looking for where the counter is
        boolean moveUpOne= false;//if they need to move up a row
        playerEFinishedMove= false;//if they have finished moving for this turn

        int howMuchToMove = finalRoll;//how much to move as set by how much was rolled on the die
        int howMuchFurtherToMove= 0;//how much further to move if they move up a row

        if(!bothPlayersMet){//if both players did meet last turn then we don't want to overwrite the other player on the board, by setting the last position of EPLAYER back to whatever it was by defult
            gameBoard[currentEYPos][currentEXPos]= EMPTY;//we can set the current (where EPLAYER would have been rendered for the past turn) Y and X postions to EMPTY, to return the space that EPLAYER would have last occupied back to defult
            if(currentEXPos== 7 && currentEYPos == 1){//makes sure that ENDOFSNAKE is spawned back in, after EPLAYER has passed over it (because if EPLAYER was on any of these coordinates last turn, then they must have been on the end of a snake)
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 13 && currentEYPos == 1){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 13 && currentEYPos == 3){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 9 && currentEYPos == 7){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 17 && currentEYPos == 9){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 1 && currentEYPos == 13){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 11 && currentEYPos == 13){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 7 && currentEYPos == 17){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }
            else if(currentEXPos== 15 && currentEYPos == 17){
                gameBoard[currentEYPos][currentEXPos]= ENDOFSNAKE;
            }

            if(currentEXPos== 17 && currentEYPos == 3){//makes sure that ENDOFLADDER is spawned back in, after EPLAYER has passed over it (because if EPLAYER was on any of these coordinates last turn, then they must have been on the end of a ladder)
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 7 && currentEYPos == 5){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 5 && currentEYPos == 7){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 15 && currentEYPos == 7){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 3 && currentEYPos == 11){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 13 && currentEYPos == 11){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 5 && currentEYPos == 15){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 19 && currentEYPos == 19){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
            else if(currentEXPos== 9 && currentEYPos == 19){
                gameBoard[currentEYPos][currentEXPos]= ENDOFLADDER;
            }
        }//!bothPlayersMet
        bothPlayersMet = false;//sets bothPlayersMet to false because they must have already been shown as meeting on the same space, so we can go back to resetting spaces where players have been in the past to defult

        while(whereECounter){//we are still looking for where EPLAYER is supposed to be according to die roll
            if(currentEYPos== 1 || currentEYPos== 5 || currentEYPos== 9 || currentEYPos== 13 || currentEYPos== 17){//if EPLAYER is moving left to right (because snakes and ladders reverses directions each row)
                for(;howMuchToMove > 0; currentEXPos += 2){//if the die roll has more than 0 in it still, then EPLAYER will move one space along at a time (displayed as -= 2 because it has to move two spaces, because of the empty spaces in between the spaces)
                    howMuchToMove --;//removes one from the remaining howMuchToMove everytime the player moves one space
                    if(currentEXPos == 19){//if EPLAYER is at the end of a row
                        moveUpOne= true;//then EPLAYER needs to move up one row
                        howMuchFurtherToMove =howMuchToMove;//because howMuchToMove is no longer being taken away from each time we move the player, we need to set the current value of howMuchToMove to howMuchFurtherToMove
                        howMuchFurtherToMove ++;//then add one to howMuchFurtherToMove, because above one more than needed got taken away from howMuchToMove (we do this to avoid the player rolling a 5, and then only moving 4 after moving up a row)
                        for(;howMuchFurtherToMove > 0; currentEXPos -=2){//then because we are now moving the counter the opposite direction, one row above (-= 2, because we need space for the empty spaces in between rows)
                            howMuchFurtherToMove --;//continue to minus how many spaces there is left to move, until there is nothing left
                        }
                        howMuchToMove= 0;//set howMuchToMove to 0, to get out of the for loop
                    }
                }
            }

            if(currentEYPos== 3 || currentEYPos== 7 || currentEYPos== 11 || currentEYPos== 15 || currentEYPos== 19){//if EPLAYER is moving right to left (because snakes and ladders reverses directions each row)
                for(;howMuchToMove > 0; currentEXPos -= 2){//if the die roll has more than 0 in it still, then EPLAYER will move one space along at a time (displayed as -= 2 because it has to move two spaces, because of the empty spaces in between the spaces)
                    howMuchToMove --;//removes one from the remaining howMuchToMove everytime the player moves one space
                    if(currentEXPos == 1){//if EPLAYER is at the end of a row
                        if(currentEYPos == 19){//if they are at the end of a row, and they are currently on the top row, then they must be on the winning space
                            gameStillGoing= false;//we can stop the main game loop, because a player won
                            howMuchToMove= 0;//exiting out of the for loop
                        }
                        else{//otherwise they must not be on the top row, so we can move them up to the row above
                            moveUpOne= true; 
                        }
                        howMuchFurtherToMove= howMuchToMove;//because howMuchToMove is no longer being taken away from each time we move the player, we need to set the current value of howMuchToMove to howMuchFurtherToMove
                        howMuchFurtherToMove ++;//then add one to howMuchFurtherToMove, because above one more than needed got taken away from howMuchToMove (we do this to avoid the player rolling a 5, and then only moving 4 after moving up a row)
                        for(;howMuchFurtherToMove > 0; currentEXPos +=2){//then because we are now moving the counter the opposite direction, one row above (+= 2, because we need space for the empty spaces in between rows)
                            howMuchFurtherToMove --;//continue to minus how many spaces there is left to move, until there is nothing left
                        }
                        howMuchToMove= 0;//set howMuchToMove to 0, to get out of the for loop
                    }
                }
                if(currentEYPos== 19 && currentEXPos == 1){//code is exiting out of the for loop too soon to see that there is supposed to be a win state- this if statement fixes a big where if you land perfectly on the win space, the game won't count it until next turn
                    gameStillGoing= false;
                }//code is exiting out of the for loop too soon to see that there is supposed to be a win state
            }
            playerEFinishedMove= true;//the player has finished thier move
            whereECounter= false;//we no longer need to look for where thier counter is
        }

        if(moveUpOne){//if we need to move up a row
            currentEYPos += 2;//then we move them up a row, adding two because we need to account for the empty space in between rows
        }

        gameBoard[currentEYPos][currentEXPos] = EPLAYER;//sets the calcuated positions to EPLAYER

    }
}
