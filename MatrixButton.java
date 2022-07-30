import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
/**
 * MatrixButton - Class to hold a matrix of players
 * @author Alisher Beisembekov
 * @version 01/03/2021
 * Project
 */
class MatrixButton extends JButton {
   //Attributes
   private final int row;
   private final int col;
   /**
    * Constructor, which activates matrix button
    * @param t is an icon(image) of player
    * @param col is a column position of player
    * @param row is a row position of player
    */
   public MatrixButton(Icon t, int col, int row) {
      super(t);
      this.row = row;
      this.col = col;
   }
   /**
    * Method, which returns a row position of player
    * @return (row + 1), a row position of player
    */
   public int getRow() {
      return row + 1;
   }
   /**
    * Method, which returns a column position of player
    * @return (col + 1), a column position of player
    */
   public int getCol() {
      return col + 1;
   }
}
