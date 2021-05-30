/**
 * PlayerInputException.java: Custom Exception for duplicate names or symbols.
 *
 * <p>
 * Discription: The exception should be thrown when a player enters a name/symbol thats already been entered
 * </p>
 *
 * @author Adolfo Sanpedro Gante
 * @version 1.0
 */

 public class PlayerInputException extends Exception {
   /**
    * Tells the user that the name they entered has already been picked
    * @param name User input
    */
   public PlayerInputException(String name) {
     super("Player 1 has already entered \"" + name + "\" as their name.");
   }
   /**
    * Tells the user that the symbol they entered has already been picked
    * @param name User input
    */
   public PlayerInputException(char symbol) {
     super("Player 1 has already entered \"" + symbol + "\" as their symbol.");
   }
 }
