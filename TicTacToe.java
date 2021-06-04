import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * TicTacToeTester.java: Simulates a Tic-Tac-Toe Games and prints a players stats
 *
 * @author Adolfo Sanpedro Gante
 * @version 2.0
 */

public class TicTacToe {
   //Creates the TicTacToe Board using a multi-dimensional array
   public static char[][] board = new char[][] {
       {' ',' ',' '},
       {' ',' ',' '},
       {' ',' ',' '},
     };
   //Creates the first player object to save their name and symbol
   public static Player player1 = new Player();
   //Creates second player object to save their name and symbol
   public static Player player2 = new Player();

   public static GUIBoard guiGameBoard;

   public static void main(String[] args) {
     //Asks User for Player 1 name
     //Throws Exception if that name is already in use
     while (true) {
       try {
         //ask4PlayerName(1);
         Jask4PlayerName(1);
         break;
       }
       catch (PlayerInputException e) {
         //System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null,e.getMessage());
       }
     }

     //Asks User for Player 1 symbol
     //Throws Exception if that symbol is already in use
     while (true) {
       try {
         //ask4PlayerSymbol(1);
         Jask4PlayerSymbol(1);
         break;
       }
       catch (PlayerInputException e) {
         //System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null,e.getMessage());
       }
     }

     //Asks User for Player 2 name
     //Throws Exception if that name is already in use
     while (true) {
       try {
         //ask4PlayerName(2);
         Jask4PlayerName(2);
         break;
       }
       catch (PlayerInputException e) {
         //System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null,e.getMessage());
       }
     }

     //Asks User for Player 2 symbol
     //Throws Exception if that symbol is already in use
     while (true) {
       try {
         //ask4PlayerSymbol(2);
         Jask4PlayerSymbol(2);
         break;
       }
       catch (PlayerInputException e) {
         //System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, e.getMessage());
       }
     }

     //Shows the TicTacToe GUI
     guiGameBoard = new GUIBoard();

     //Main Game Loop
     while (true) {
       //Sub Game Loops
       while (true) {
         //Loop asks player 1 for a move untill a valid move is entered.
         while (true) {
           try {
             getPlayerMove(player1);
             //Stops Player 1 move.
             break;
           }
           catch (InvalidMoveException e) {
             JOptionPane.showMessageDialog(null,e.getMessage());
           }
         }

         if (isWinOrTie(player1, player2)) {
           break;
         }

         //Loop asks player 2 for a move untill a valid move is entered.
         while (true) {
           try {
             getPlayerMove(player2);
             //Stops Player 2 move.
             break;
           }
           catch (InvalidMoveException e) {
             JOptionPane.showMessageDialog(null,e.getMessage());
           }
         }

         if (isWinOrTie(player2, player1)) {
           break;
         }
       }
       while (true) {
         try {
           playAnother();
           break;
         } catch (KeepPlayingInputException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
         }
       }
     }
   }

   /**
    * Asks player if they with to keep playing
    * @throws KeepPlayingInputException if players enter a non yes or no anwser exception tell them to enter yes or no.
    */
   public static void playAnother() throws KeepPlayingInputException{
     String play = JOptionPane.showInputDialog(null, "Play again? Yes/No");
     play = play.toUpperCase();
     //Checks if user input is valid if not throws KeepPlayingInputException
     if (play.equals("N") || play.equals("NO")) {
       System.exit(0);
     }
     else if (play.equals("Y") || play.equals("YES")) {
       //Resets the tic tac toe board
       int squareNum = 1;
       for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
           board[i][j] = ' ';
           guiGameBoard.squares[i][j].setText(Integer.toString(squareNum));
           squareNum++;
         }
       }
     }
     else {
       throw new KeepPlayingInputException (play);
     }
   }

   /**
    * Determine if a winner or tie has been found and prints the best response
    * @param  player1 A Player Object
    * @param  player2 Another Player Object
    * @return         true or false
    */
   public static boolean isWinOrTie(Player player1, Player player2) {
     if (checkTie(board, 0)) {
       JOptionPane.showMessageDialog(null, player1.getName() + " and " + player2.getName() + " have tied!");
       return true;
     }
     else if (checkWinConditions(board, player1, 0)) {
       JOptionPane.showMessageDialog(null, player1.getName() + " Wins!");
       return true;
     }
     return false;
   }

   //Win Conditions

   /**
    * Checks all win conditions in a single method
    * @param  board  The board being checked for win or tie conditions
    * @param  player The player that will be the winner if a win condition is met
    * @param  cnt    A count used in the sub win condition methods
    * @return        true or false
    */
   public static boolean checkWinConditions(char[][] board, Player player, int cnt) {
     //Checks every single win or tie condition if any return true a winner or tie has been found
     return checkRows(board, player, cnt) || checkColumns(board, player, cnt) || checkDialog1(board, player, cnt)
             || checkDialog2(board, player);
   }

   /**
    * Checks if a tie as happened
    * @param  board The board the tic tac toe game is being played on
    * @param  cnt   A count that is used to determine if the boards 9 squares have been filled
    * @return       true or false
    */
   public static boolean checkTie(char[][] board, int cnt) {
     //Index's through the board or 2D array to check if all 9 squares have been filled if they have then a tie has happened
     for (char[] chars : board) {
       for (int j = 0; j < board[0].length; j++) {
         if (chars[j] != ' ') {
           cnt++;
         }
       }
     }
     return cnt == 9;
   }

   /**
    * A recursive method that checks a 2D arrays rows for a win
    * @param  board  The 2D array being checked
    * @param  player The player's symbol being checked for a win
    * @param  cnt    A count used to index through the 2D array
    * @return        true or false
    */
   public static boolean checkRows(char[][] board, Player player, int cnt) {
     //If the cnt is equal to the number of rows then no win condition has been found
     if (cnt == board.length) {
       return false;
     }
     //Index's through all rows in a 2D array looking a row filled by a players symbol
     for (int i = 0; i < board.length; i++) {
       if (board[cnt][i] != player.getSymbol()) {
         cnt++;
         return checkRows(board, player, cnt);
       }
     }
     return true;
   }

   //Win with vertical (column) win
   /**
    * A recursive method that checks a 2D arrays columns for a win
    * @param  board  The 2D array being checked
    * @param  player The player's symbol being checked for a win
    * @param  cnt    A count used to index through the 2D array
    * @return        true or false
    */
   public static boolean checkColumns(char[][] board, Player player, int cnt) {
     //If the cnt is equal to the number of columns then no win condition has been found
     if (cnt == board[0].length) {
       return false;
     }
     //Index's through all rows in a 2D array looking a column filled by a players symbol
     for (int i = 0; i < board[0].length; i++) {
       if (board[i][cnt] != player.getSymbol()) {
         cnt++;
         return checkColumns(board, player, cnt);
       }
     }
     return true;
   }


   /**
    * A recursive method that checks a 2D array for a diagonal win, Win with topLeft to bottomRight diagonal
    * @param  board  The 2D array being checked
    * @param  player The player's symbol being checked for a win
    * @param  cnt    A count used to index through the 2D array
    * @return        true or false
    */
   public static boolean checkDialog1(char[][] board, Player player, int cnt) {
     //If the count is equal to the board length then the recursive method was able to check all square part of the diagonal
     if (cnt == board.length) {
       return true;
     }
     //If a single square in the diagonal doesn't equal a players symbol then the player cannot win with a diagonal
     if (board[cnt][cnt] != player.getSymbol()) {
         return false;
     }
     //Updates count and checks the next square in the diagonal
     cnt++;
     return checkDialog1(board, player, cnt);
   }

   /**
    * Checks for a diagonal win in a 2D Array, Win with bottomLeft to topRight diagonal
    * @param  board  The 2D array being checked
    * @param  player The player's symbol being checked for a win
    * @return        true or false
    */
   public static boolean checkDialog2(char[][] board, Player player) {
     //Checks all three squares in the diagonal to the players symbol
     return board[0][2] == player.getSymbol() && board[1][1] == player.getSymbol() && board[2][0] == player.getSymbol();
   }

   //Helper Methods

   /**
    * Asks player for thier move aka square number, check if that move has been played thows exception if it has.
    * @param  player               Player 1 or Player 2
    * @throws InvalidMoveException if player enter anything other than a number from 1 - 9 or if that square has been played.
    */
   public static void getPlayerMove(Player player) throws InvalidMoveException {
     //Asks for Player 1 move
     int move = Integer.parseInt(JOptionPane.showInputDialog(null, player.getName() + " enter a square number."));

     //Checks if player's move has been played
     if (checkPlayerMove(move)) {
         setSymbolOnSquare(move, player.getSymbol());
     } else {
       throw new InvalidMoveException(move);
     }
   }

   /**
    * Super ugly switch statment to set the Text on the JLabel associated with the players move. Updates the 2D board array aswell.
    * @param squareNum The players move/square number
    * @param symbol    The Players symbol (X,O)
    */
   public static void setSymbolOnSquare(int squareNum, char symbol) {
     switch (squareNum) {
       case 1:
         guiGameBoard.squares[0][0].setText(Character.toString(symbol));
         board[0][0] = symbol;
         break;
       case 2:
         guiGameBoard.squares[0][1].setText(Character.toString(symbol));
         board[0][1] = symbol;
         break;
       case 3:
         guiGameBoard.squares[0][2].setText(Character.toString(symbol));
         board[0][2] = symbol;
         break;
       case 4:
         guiGameBoard.squares[1][0].setText(Character.toString(symbol));
         board[1][0] = symbol;
         break;
       case 5:
         guiGameBoard.squares[1][1].setText(Character.toString(symbol));
         board[1][1] = symbol;
         break;
       case 6:
         guiGameBoard.squares[1][2].setText(Character.toString(symbol));
         board[1][2] = symbol;
         break;
       case 7:
         guiGameBoard.squares[2][0].setText(Character.toString(symbol));
         board[2][0] = symbol;
         break;
       case 8:
         guiGameBoard.squares[2][1].setText(Character.toString(symbol));
         board[2][1] = symbol;
         break;
       case 9:
         guiGameBoard.squares[2][2].setText(Character.toString(symbol));
         board[2][2] = symbol;
         break;
       default:
         break;
     }
   }

   /**
    * Asks for player name using JOptionPane throws Exception if name entered is in use.
    * @param  player               player 1 or 2
    * @throws PlayerInputException Throws when name entered is in use
    */
   public static void Jask4PlayerName(int player) throws PlayerInputException {
    //Asks user for player's name
    String name = JOptionPane.showInputDialog(null, "Enter player " + (player == 1 ? "1: " : "2: "));

    //Sets player name
    if (player == 1) {
      player1.setName(name);
    }
    else if (player == 2) {
      player2.setName(name);
    }

    //Throws exception if a player has chosen the same name
    if (player1.getName().equals(player2.getName())) {
      throw new PlayerInputException(name);
    }
   }

   /**
    * Asks for player symbol using JOptionPane throws Exception if symbol entered is in use.
    * @param  player               player 1 or 2
    * @throws PlayerInputException Throws when symbol entered is in use
    */
   public static void Jask4PlayerSymbol(int player) throws PlayerInputException {
     //Asks user for player's symbol
     String symbolString = JOptionPane.showInputDialog(null, "Enter player " + (player == 1 ? "1: " : "2: ") + "symbol (ex. X, O or A, B): ");
     char symbolChar = symbolString.charAt(0);

     //Sets player symbol
     if (player == 1) {
       player1.setSymbol(symbolChar);
     }
     else if (player == 2) {
       player2.setSymbol(symbolChar);
     }

     //Throws exception if a player has chosen the same symbol
     if (player1.getSymbol() == player2.getSymbol()) {
       throw new PlayerInputException(symbolChar);
     }
   }

   /**
    * Super ugly switch statement that checks if players move has been played
    * @param  move the square number
    * @return      true or false
    */
   public static boolean checkPlayerMove(int move) {
     switch (move) {
       case 1:
         return (board[0][0] == ' ');
       case 2:
         return (board[0][1] == ' ');
       case 3:
         return (board[0][2] == ' ');
       case 4:
         return (board[1][0] == ' ');
       case 5:
         return (board[1][1] == ' ');
       case 6:
         return (board[1][2] == ' ');
       case 7:
         return (board[2][0] == ' ');
       case 8:
         return (board[2][1] == ' ');
       case 9:
         return (board[2][2] == ' ');
       default:
         break;
     }
     return false;
   }
 }
