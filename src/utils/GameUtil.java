package utils;

import logic.Moves;
import model.Board;
import model.Move;
import model.Square;
import model.pieces.Piece;

import java.util.ArrayList;

/**
 * Created by Markus on 28.06.2014.
 */
public class GameUtil {
  /**
   * Checks if the given colour is check mate.
   *
   * @param colour
   * @param field
   * @return
   * @throws Exception
   */
  public static boolean isCheckMate(String colour, Board field)
          throws Exception {

    boolean checkMate = false;

    return checkMate;
  }

  /**
   * Checks if the given colour is check.
   *
   * @param colour
   * @param square
   * @return
   * @throws Exception
   */
  public static boolean isGivenColourCheck(String colour, Square[][] square)
          throws Exception {
    String colourOpposite = "white";

    if (colour.equals("white"))
      colourOpposite = "black";

    if (MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck(
            colourOpposite, square).contains(
            BoardUtil.getKingFieldByColour(colour, square)))
      return true;
    else
      return false;

  }

  public static boolean isGivenColourCheck(String colour, Board field)
          throws Exception {
    String colourOpposite = "white";

    if (colour.equals("white"))
      colourOpposite = "black";

    if (MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck(
            colourOpposite, field.getSquares()).contains(
            BoardUtil.getKingFieldByColour(colour, field)))
      return true;
    else
      return false;

  }

  /**
   * Returns all fields that are giving check to the given colour
   *
   * @param colour
   * @param field
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public static ArrayList figuresGivingCheckToColour(String colour,
                                                     Board field) throws Exception {
    Square kingSquare = BoardUtil.getKingFieldByColour(colour, field);
    ArrayList figuresGivingCheck = new ArrayList();
    ArrayList threatedFields;
    if (colour.equals("white"))
      threatedFields = MoveUtil.moveArrayByColourOfFiguresGivingCheck(
              field, "black");
    else
      threatedFields = MoveUtil.moveArrayByColourOfFiguresGivingCheck(
              field, "white");

    for (int i = 0; i < threatedFields.size(); i++) {
      Move temp = (Move) threatedFields.get(i);
      Square currentSquare = temp.getTo();
      if ((currentSquare == kingSquare))

        figuresGivingCheck.add(temp.getFrom());
    }
    return figuresGivingCheck;

  }
}
