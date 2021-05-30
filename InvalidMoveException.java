/**
 * InvalidMoveException.java: Custom Exception for getting vaild input from user when asking for a square
 *
 * <p>
 * Discription: The exception should be thrown when a player enters an move that does not adhere to a proper input (invalid input)
 * </p>
 *
 * @author Adolfo Sanpedro Gante
 * @version 1.0
 */

 public class InvalidMoveException extends Exception {
   /**
    * Tells user that what they entered isn't proper input
    */
   public InvalidMoveException() {
     super("Please enter a proper input for choosing a square to fill.");
   }

   /**
    * Tells user that the square they chose is already been picked
    * @param move The Square id
    */
   public InvalidMoveException(String move) {
     super("Square \"" + move + "\" is full, please pick another.");
   }

 }
