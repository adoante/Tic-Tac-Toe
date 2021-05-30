/**
 * KeepPlayingInputException.java: Custom Exception for user input that is not Yes or No type.
 *
 * <p>
 * Discription: The exception should be thrown when a player enters an invaild anwser to countine or break out of the overall game loop
 * </p>
 *
 * @author Adolfo Sanpedro Gante
 * @version 1.0
 */

 public class KeepPlayingInputException extends Exception {
   /**
    * Tells the user that thier input is not a Yes or No
    * @param play user input
    */
   public KeepPlayingInputException(String play) {
     super("Please enter Y/N or Yes/No.");
   }
 }
