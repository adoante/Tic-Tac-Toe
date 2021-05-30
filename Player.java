/**
 * Player.java: Creates a tic-tac-toe player with a name, symbol (ex. X, O, A, or B),
 *
 * @author Adolfo Sanpedro Gante
 * @version 1.0
 */

public class Player {
  //Instance variables
  private String name = " ";
  private char symbol = ' ';

  /**
   * Creates a player object with a name and symbol
   * @param name   The Players Name
   * @param symbol A Players symbol when playing tic tac toe (X,O)
   */
  public Player (String name, char symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  /**
   * No Arg Constructor
   */
  public Player () {

  }

  //Setter and Getters

  /**
   * Sets the Player name
   * @param name A Players Name
   */
  public void setName (String name) {
    this.name = name;
  }
  /**
   * Sets the Player Symbol when playing tic tac toe (O,X)
   * @param symbol a single character from A - Z or 0 - 9
   */
  public void setSymbol (char symbol) {
    this.symbol = symbol;
  }

  /**
   * Gets the name of a Player
   * @return Player name
   */
  public String getName () {
    return this.name;
  }
  /**
   * Gets a player symbol
   * @return Player Symbol
   */
  public char getSymbol () {
    return this.symbol;
  }

  // toString and equals

  /**
   * Prints a Players name and Symbol
   * @return Name and Symbol
   */
  public String toString () {
    return ("Name: " + name + ", Symbol: " + symbol);
  }

  /**
   * Checks if two players are equal
   * @param  anObject A Player Object
   * @return          true or false
   */
  public boolean equals (Object anObject) {
    if (anObject == null) {
      return false;
    }
    if (getClass() != anObject.getClass()) {
      return false;
    }
    Player otherPlayer = (Player) anObject;
    return (name.equals(otherPlayer) && symbol == otherPlayer.getSymbol());
  }
}
