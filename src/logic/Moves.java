package logic;

import model.Board;
import model.Square;
import model.pieces.*;
import utils.BoardUtil;
import utils.ChessNotationUtil;
import utils.MoveUtil;

import java.util.ArrayList;

/**
 * @author Markus
 */
public class Moves {

  /**
   * Returns a list of fields that are threatened by the colour.
   * Important to determine Fields, the enemy King is not allowed to move on
   *
   * @param row
   * @param column
   * @param piece
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public static ArrayList listOfMovesConsideringAllThreatendFields(Piece piece, Board board) throws Exception {
    ArrayList possibleFields = new ArrayList();

//return piece
    if (piece.getType().equals("pawn")) {

      possibleFields.clear();

      return Pawn.allFieldsThePawnThreateneds(row, column, possibleFields, piece);

    } else if (piece.getType().equals("bishop")) {
      possibleFields.clear();

      return Bishop.fieldsThreatendByTheBishopSkippingKingsPosition(row, column, possibleFields, piece);

    } else if (piece.getType().equals("knight")) {
      possibleFields.clear();
      return Knight.knightMovesAllThreatenedFields(row, column, possibleFields, piece);

    } else if (piece.getType().equals("king")) {

      possibleFields.clear();
      return King.kingMovesNotConsideringAttackedFields(row, column, possibleFields, piece);

    } else if (piece.getType().equals("queen")) {

      possibleFields.clear();

      ArrayList list = new ArrayList();
      list
              .addAll(Rook.fieldsThreatendByTheRookSkippingKingsPosition(row, column, possibleFields,
                      piece));

      possibleFields.clear();
      list.addAll(Bishop.fieldsThreatendByTheBishopSkippingKingsPosition(row, column, possibleFields,
              piece));

      return list;

    } else if (piece.getType().equals("rook")) {
      possibleFields.clear();

      return Rook.fieldsThreatendByTheRookSkippingKingsPosition(row, column, possibleFields, piece);
    } else
      throw new Exception(piece.getType() + ": no such figure");
  }

  @SuppressWarnings("unchecked")
  public static ArrayList listOfMovesIgnoreKingHelpClass(int row, int column, Square[][] square)
          throws Exception {
    ArrayList possibleFields = new ArrayList();
    Square current = square[row][column];

    if (current.getType().equals("pawn")) {

      possibleFields.clear();

      return Pawn.allFieldsThePawnThreateneds(row, column, possibleFields, square);

    } else if (current.getType().equals("bishop")) {
      possibleFields.clear();

      return Bishop.fieldsThreatenedByTheBishop(row, column, possibleFields, square);

    } else if (current.getType().equals("knight")) {
      possibleFields.clear();
      return Knight.knightMovesActuallyAllowed(row, column, possibleFields, square);

    } else if (current.getType().equals("king")) {

      possibleFields.clear();
      return King.kingMovesNotConsideringAttackedFields(row, column, possibleFields, square);

    } else if (current.getType().equals("queen")) {

      possibleFields.clear();
      //System.out.println("vor der ifqueen");
      ArrayList list = new ArrayList();
      list
              .addAll(Rook.fieldsThreatenedByTheRook(row, column, possibleFields,
                      square));

      possibleFields.clear();
      list.addAll(Bishop.fieldsThreatenedByTheBishop(row, column, possibleFields,
              square));

      return list;

    } else if (current.getType().equals("rook")) {
      possibleFields.clear();
      //System.out.println("vor der ifrook");
      return Rook.fieldsThreatenedByTheRook(row, column, possibleFields, square);
    } else
      throw new Exception(current.getType() + ": no such figure");
  }

  /**
   * ArrayList of fields the figure is actually allowed to move
   *
   * @param row
   * @param column
   * @param square
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public static ArrayList listOfActuallyAllowedMoves(int row, int column, Square[][] square)
          throws Exception {
    ArrayList possibleFields = new ArrayList();
    Square current = square[row][column];

    if (current.getType().equals("pawn")) {

      possibleFields.clear();
      return Pawn.allFieldsThePawnIsActuallyAllowedToMove(row, column, possibleFields, square);

    } else if (current.getType().equals("bishop")) {
      possibleFields.clear();
      return Bishop.fieldsActuallyAllowedForBishopToMove(row, column, possibleFields, square);

    } else if (current.getType().equals("knight")) {
      possibleFields.clear();
      return Knight.knightMovesActuallyAllowed(row, column, possibleFields, square);

    } else if (current.getType().equals("king")) {

      possibleFields.clear();
      return King.kingMovesConsideringAttackedFields(row, column, possibleFields, square);

    } else if (current.getType().equals("queen")) {

      possibleFields.clear();
      ArrayList list = new ArrayList();
      list.addAll(Bishop.fieldsActuallyAllowedForBishopToMove(row, column, possibleFields,
              square));
      possibleFields.clear();
      list
              .addAll(Rook.fieldsActuallyAllowedForRookToMove(row, column, possibleFields,
                      square));

      return list;

    } else if (current.getType().equals("rook")) {
      possibleFields.clear();
      return Rook.fieldsActuallyAllowedForRookToMove(row, column, possibleFields, square);
    } else
      throw new Exception(current.getType() + ": no such figure");
  }

  @SuppressWarnings("unchecked")
  public static ArrayList listOfFieldsOnWhichAnEnemyKingIsCheck(int row, int column,
                                                                Square[][] square) throws Exception {
    ArrayList possibleFields = new ArrayList();
    Square current = square[row][column];

    if (current.getType().equals("pawn")) {

      possibleFields.clear();
      return Pawn.allFieldsThePawnThreateneds(row, column, possibleFields, square);

    } else if (current.getType().equals("bishop")) {
      possibleFields.clear();
      return Bishop.fieldsThreatenedByTheBishop(row, column, possibleFields,
              square);

    } else if (current.getType().equals("knight")) {
      possibleFields.clear();
      return Knight.knightMovesCheck(row, column, possibleFields,
              square);

    } else if (current.getType().equals("king")) {

      possibleFields.clear();
      //9.4 kingMovesConsideringAttackedFields -> kingMovesNotConsideringAttackedFields
      return King.kingMovesConsideringAttackedFields(row, column, possibleFields, square);

    } else if (current.getType().equals("queen")) {

      possibleFields.clear();
      ArrayList list = new ArrayList();
      list.addAll(Bishop.fieldsThreatenedByTheBishop(row, column,
              possibleFields, square));
      possibleFields.clear();
      list.addAll(Rook.fieldsThreatenedByTheRook(row, column, possibleFields,
              square));

      return list;

    } else if (current.getType().equals("rook")) {
      possibleFields.clear();
      return Rook.fieldsThreatenedByTheRook(row, column, possibleFields, square);
    } else
      throw new Exception(current.getType() + ": no such figure");
  }

  public static boolean isCheckByFigure(int row_figure, int column_figure,
                                        int row_king, int column_king, Square[][] square) throws Exception {

    BoardUtil.fieldFiller(square);
    if (square[row_king][column_king].getPiece() != null)
      return ((listOfFieldsOnWhichAnEnemyKingIsCheck(row_figure, column_figure, square))
              .contains(square[row_king][column_king]))
              && (!(square[row_figure][column_figure]).getPiece()
              .getColour().equals((square[row_king][column_king])
                      .getPiece().getColour()));
    else
      return false;
  }


  public static void printListOfMovesByFigure(int row, int column, Square[][] square) throws Exception { 	/*unused*/

    BoardUtil.fieldFiller(square);
    int counter = 0;
    System.out.println("\n	List of possible moves ("
            + listOfActuallyAllowedMoves(row, column, square).size() + ") for the "
            + square[row][column].getType() + " on "
            + square[row][column].getCharRow() + ""
            + (square[row][column].getIntColumn()) + "\n");
    while (counter < listOfActuallyAllowedMoves(row, column, square).size()) {
      Square temp = (Square) listOfActuallyAllowedMoves(row, column, square).get(counter);
      System.out.println(" " + (temp.getCharRow()) + ""
              + ((temp.getIntColumn())));
      counter++;
    }

  }

  public static void printListOfActuallyAllowedFieldsToMoveByFigure(String fieldString,
                                                                    Square[][] square) throws Exception {

    BoardUtil.fieldFiller(square);
    int[] fieldArray = ChessNotationUtil.convertFieldNameToIndexes(fieldString);
    int row = fieldArray[0];
    int column = fieldArray[1];
    int counter = 0;
    if (!(square[row][column].isEmpty())) {
      System.out.println("\n	List of possible moves ("
              + listOfActuallyAllowedMoves(row, column, square).size() + ") for the "
              + square[row][column].getType() + " on "
              + square[row][column].getCharRow() + ""
              + (square[row][column].getIntColumn()) + "\n");
      while (counter < listOfActuallyAllowedMoves(row, column, square).size()) {
        Square temp = (Square) listOfActuallyAllowedMoves(row, column, square).get(
                counter);
        System.out.println(" 	" + (temp.getCharRow()) + ""
                + ((temp.getIntColumn())));
        counter++;
      }
    } else
      System.out.println("	<Invalid Order: No figure on " + fieldString
              + ">");
    // System.out.println("\n");
  }


  public static void printListOfActuallyAllowedFieldsToMoveByColour(String colour,
                                                                    Square[][] square) throws Exception {

    BoardUtil.fieldFiller(square);
    int counter = 0;
    System.out
            .println("\n\n=========================================================================");
    System.out.println("\n	List of reachable fields ("
            + MoveUtil
            .threatendFieldsByActuallyAllowedMoves(colour, square).size()
            + ") for " + colour + "\n");
    while (counter < MoveUtil.threatendFieldsByActuallyAllowedMoves(
            colour, square).size()) {
      Square temp = (Square) MoveUtil
              .threatendFieldsByActuallyAllowedMoves(colour, square).get(counter);
      System.out.println("	" + (temp.getCharRow()) + ""
              + ((temp.getIntColumn())));
      counter++;
    }
    System.out.print('\n');

  }
}
