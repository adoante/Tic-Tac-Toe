import java.util.Scanner;

/**
 * TicTacToeTester.java: Simulates a Tic-Tac-Toe Games and prints a players stats
 *
 * @author Adolfo Sanpedro Gante
 * @version 1.0
 */

public class TicTacToe {
  //Instance variables
  private static boolean game = true;           //Boolean for sub game (while) loop
  private static boolean running = true;        //Boolean for main game (while) loop

  public static void main(String[] args) {
    //Creates the TicTacToe Board using a multi-dimensional array
    char[][] board = new char[][]
      {
        {' ',' ',' '},
        {' ',' ',' '},
        {' ',' ',' '},
      };

    //Creates a scanner to get user input
    Scanner userInput = new Scanner(System.in);

    //Creates the first player object to save their name and symbol
    Player player1 = new Player();
    //Creates second player object to save their name and symbol
    Player player2 = new Player();

    //Asks user for player 1's name
    System.out.print("Enter player 1: ");
    String name = userInput.nextLine();

    //Sets player 1's name
    player1.setName(name);

    //Asks user for player 1's symbol
    System.out.print("Enter player 1 symbol (ex. X, O or A, B): ");
    char symbol = userInput.next().charAt(0);

    //Sets player 1's symbol
    player1.setSymbol(symbol);

    //Loops until Player 2 enters a name and symbol that is not equal to player 1 name
    while (true) {
      //If user tries to enter a name that has already been picked throws PlayerInputException
      try {
        //Asks user for player 2's name
        System.out.print("Enter player 2: ");
        name = userInput.next();
        userInput.nextLine();

        //Throws exception if player 2 has chosen the same name and/or symbol
        if (player1.getName().equals(name)) {
          throw new PlayerInputException(player1.getName());
        }
        else {
          //Sets player 2's name
          player2.setName(name);
        }

        //Exits loop if player names and symbols are not equal to each other.
        break;
      }
      catch (PlayerInputException e) {
        System.out.println(e.getMessage());
      }
    }

    //Loops until Player 2 enters a symbol that is not equal to player 1 symbol
    while (true) {
      //If user tries to enter a symbol that has already been picked throws PlayerInputException
      try {
        //Asks user for player 2's symbol
        System.out.print("Enter player 2 symbol (ex. X, O or A, B): ");
        symbol = userInput.next().charAt(0);
        userInput.nextLine();

        //Throws exception if player 2 has chosen the same name and/or symbol
        if (player1.getSymbol() == symbol) {
          throw new PlayerInputException(player1.getSymbol());
        }
        else {
          //Sets player 2's symbol
          player2.setSymbol(symbol);
        }

        //Exits loop if player names and symbols are not equal to each other.
        break;
      }
      catch (PlayerInputException e) {
        System.out.println(e.getMessage());
      }
    }

    //Prints the TicTacToe board
    printBoard(board);

    //Main Game (while) loop that only exits when the players want to stop playing tic-tac-toe
    while (running) {
      //Sub Game (While) Loop, loops until a win or tie condition is met
      while (game) {
        //While loop continues to ask player 1 for proper input until no exception is caught
        while (true) {
          //Try loop will catch an exception when player 1 enters an invalid move and if the chosen square is full.
          try {
            //Asks the user for their first move (a square on the TicTacToe board)
            System.out.print("Player 1 turn. Enter the square id to place your symbol: ");
            String move = userInput.nextLine();

            //Checks player 1's move if its valid and hasn't been played
            validMove(move);
            checkSquare(move, board);

            //Places player 1's symbol in their chosen square
            board[move.charAt(0) - 49][move.charAt(1) - 65] = player1.getSymbol();

            //Prints the TicTacToe board
            printBoard(board);

            //If win or tie condition end Game loop
            game = checkWinConditions(board, player1, 0);

            //Breaks out of loop and starts player 2 move
            break;

          }
          catch (StringIndexOutOfBoundsException e) {
            System.out.println("You must enter a square id.");
          }
          catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
          }
        }

        //Declares winner and breaks out of sub game loop
        if (printWinner(game, player1, player2, checkTie(board, 0))) {
          break;
        }

        //While loop continues ask player 2 for proper input until no exception is caught
        while (true) {
          //Second Try loop will catch an exception when player 2 enters an invalid move and if the chosen square is full.
          try {
            //Asks the user for their first move (a square on the TicTacToe board)
            System.out.print("Player 2 turn. Enter the square id to place your symbol: ");
            String move = userInput.nextLine();

            //Checks player 2's move if its valid and hasn't been played
            validMove(move);
            checkSquare(move, board);

            //Places player 2's symbol in their chosen square
            board[move.charAt(0) - 49][move.charAt(1) - 65] = player2.getSymbol();

            //Prints the TicTacToe board
            printBoard(board);

            //If win or tie condition end Game loop
            game = checkWinConditions(board, player2, 0);

            //Breaks out of loop and starts player 1 move
            break;

          }
          catch (StringIndexOutOfBoundsException e) {
            System.out.println("You must enter a square id.");
          }
          catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
          }
        }

        //Declares winner and breaks out of game loop
        if (printWinner(game, player2, player1, checkTie(board, 0))) {
          break;
        }
      }

      //While loop exits when input equals Y/N y/n Yes/No YES/NO is entered
      while (true) {
        //Throws KeepPlayingInputException if anything other than a Yes/No type answer is entered and keeps asking if they wish to keep playing
        try {
          //Asks user if they want to keep playing tic tac toe
          System.out.println();
          System.out.print("Would you like to play another game? Y/N : ");
          String play = userInput.nextLine();
          play = play.toUpperCase();
          //Checks if user input is valid if not throws KeepPlayingInputException
          if (play.equals("N") || play.equals("NO")) {
            //Sets the main game loop to false
            running = false;
            //Breaks into the main game loop and ends the program
            break;
          }
          else if (play.equals("Y") || play.equals("YES")) {
            //Resets the tic tac toe board
            for (int i = 0; i < board.length; i++) {
              for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
              }
            }
            //Prints the blank board
            printBoard(board);
            //Sets the sub game loop to true
            game = true;
            //Breaks into the main game loop and then goes into the sub game loop
            break;
          }
          else {
            throw new KeepPlayingInputException (play);
          }
        }
        catch (KeepPlayingInputException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  //Helper Methods

  /**
   * Prints the Winner or if a tie
   * @param  game     true or false
   * @param  player1  Player Object with player 1's variables
   * @param  player2  Player Object with player 2's variables
   * @param  checkTie true or false
   * @return          true or false
   */
  public static boolean printWinner(boolean game, Player player1, Player player2, boolean checkTie) {
    //If the sub game loop is false check if a tie has happened
    if (!game) {
      //If a tie has happened print that a Player 1 and 2 have tied
      if (checkTie) {
        System.out.println(player1.getName() + " and " + player2.getName() + " have tied!");
        return true;
      }
      //Else Print the Player 1 or 2 as the winner
      System.out.println("Winner Winner Chicken Dinner!!! " + player1.getName() + " has won!");
      return true;
    }
    return false;
  }

  /**
   * Checks if a square already has been picked and throws InvalidMoveException if it has
   * @param  move                 The Square being checked
   * @param  board                The Tic Tac Toe board the square being checked is in
   * @throws InvalidMoveException Tells user that the square has been picked already
   */
  public static void checkSquare(String move, char[][] board) throws InvalidMoveException {
    //Checks if the square entered is part of the 2D array
    if (board[move.charAt(0) - 49][move.charAt(1) - 65] != ' ') {
      throw new InvalidMoveException(move);
    }
  }

  /**
   * Checks to see if user input has entered proper input for picking a square
   * @param  move                 The user input being checked
   * @throws InvalidMoveException Tells user that what they entered isn't proper input
   */
  public static void validMove(String move) throws InvalidMoveException {
    //Checks if the first character of string is 1,2, or 3 else throw exception
    if (move.charAt(0) < '1' || move.charAt(0) > '3') {
      throw new InvalidMoveException();
    }
    //Checks if the second character of string is A,B, or C else throw exception
    if (move.charAt(1) < 'A' || move.charAt(1) > 'C') {
      throw new InvalidMoveException();
    }
  }

  /**
   * Prints the Tic Tac Toe board
   * @param board A 2D array acting as the Tic Tac Toe board
   */
  public static void printBoard(char[][] board) {
    //Prints the column labels
    System.out.println("   A B C");
    for (int i = 0; i < board.length; i++) {
      //Prints | to separate squares
      System.out.print(i + 1 + " |");
      for (int j = 0; j < board[0].length; j++) {
        //Prints the character in a square and a | to separate it from the next character
        System.out.print(board[i][j] + "|");
      }
      //Moves onto the next row to print
      System.out.println();
    }
  }

  //Win Cases

  /**
   * Checks all win conditions in a single method
   * @param  board  The board being checked for win or tie conditions
   * @param  player The player that will be the winner if a win condition is met
   * @param  cnt    A count used in the sub win condition methods
   * @return        true or false
   */
  public static boolean checkWinConditions(char[][] board, Player player, int cnt) {
    //Checks every single win or tie condition if any return true a winner or tie has been found
    return !checkRows(board, player, cnt) && !checkColumns(board, player, cnt) && !checkDialog1(board, player, cnt)
            && !checkDialog2(board, player) && !checkTie(board, cnt);
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
}
