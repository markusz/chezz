package model.pieces;

import model.Board;
import model.Move;
import model.Player;
import model.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends AbstractPiece {

  public Pawn(Player player) {
    super(player);
    textualRepresentation = "P";
  }

  @Override
  public Set<Square> getAllThreatenedSquares() {
    return null;
  }

  @Override
  public Set<Move> getAllMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllNormalMoves() {
    int m = getPlayer().getBoardModifier();
    Square c = getCurrentPositionOnBoard();

    Set<Move> moves = new HashSet<>();
    Square oneSquareAhead = board.getSquareNRowsAhead(c, 1, m);
    Square twoSquaresAhead = board.getSquareNRowsAhead(c, 2, m);

    //TODO bedingung
    if (!hasBeenMoved() && oneSquareAhead.isEmpty()) {
      moves.add(new Move(c, oneSquareAhead, Move.NORMAL));
      if(twoSquaresAhead.isEmpty()){
        moves.add(new Move(c, twoSquaresAhead, Move.NORMAL));
      }
    }

    return moves;
  }

  @Override
  public Set<Move> getAllCapturingMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllOtherMoves() {
    return null;
  }

  /**
   * adds a field if the pawn is actually allowed to throw by the rules for pawn movement
   * and considering the fact that a king can not be thrown.
   *
   * @param row
   * @param column
   * @param colour
   * @param square
   * @return
   */
  @SuppressWarnings("unchecked")
  public static ArrayList addFieldIfPawnIsActuallyAllowedToCapture(int row, int column, String colour,
                                                                   Square[][] square) {

    ArrayList temp = new ArrayList();

    if (!(row < 0 || column < 0 || row > 7 || column > 7))
      if (square[row][column].isEmpty()) {
        ;
      } else {
        if (!square[row][column].getPiece().getColour().equals(colour) && !square[row][column].getPiece().getType().equals("king")) {
          temp.add(square[row][column]);

        } else
          ;
      }
    return temp;

  }

  /**
   * adds a field if the pawn is allowed to move to it, de facto, if the field is empty
   *
   * @param row
   * @param column
   * @param colour
   * @param square
   * @return
   */
  @SuppressWarnings("unchecked")
  public static ArrayList addFieldIfPawnIsActuallyAllowedToMove(int row, int column, String colour,
                                                                Square[][] square) {

    ArrayList temp = new ArrayList();

    if (!(row < 0 || column < 0 || row > 7 || column > 7))
      if (square[row][column].isEmpty()) {
        temp.add(square[row][column]);
      }
    return temp;

  }


  /**
   * adds a field if a pawn threateneds it
   *
   * @param row
   * @param column
   * @param colour
   * @param square
   * @return
   */
  @SuppressWarnings("unchecked")
  public static ArrayList addFieldIfPawnThreatenedsIt(int row, int column, String colour,
                                                      Square[][] square) {

    ArrayList temp = new ArrayList();

    if (!(row < 0 || column < 0 || row > 7 || column > 7))
      temp.add(square[row][column]);
    return temp;

  }


  /**
   * returns a list of moves the current pawn is actually allowed to make.
   *
   * @param row
   * @param column
   * @param moves
   * @param square
   * @return ArrayList <Field>
   */
  @SuppressWarnings("unchecked")
  public static ArrayList allFieldsThePawnIsActuallyAllowedToMove(int row, int column, ArrayList moves,
                                                                  Square[][] square) {
    String colour = square[row][column].getPiece().getColour();

    if (square[row][column].getPiece().getColour().equals("white")) {
      if (column == 6) {
        if (square[row][column - 1].isEmpty()) {
          moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column - 2, colour, square));
        }

        moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column - 1, colour, square));


        moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column - 1, colour,
                square));
        moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column - 1, colour,
                square));
      } else {
        moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column - 1, colour, square));
        moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column - 1, colour,
                square));
        moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column - 1, colour,
                square));
      }

    } else if (column == 1) {
      moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column + 1, colour, square));
      if (square[row][column + 1].isEmpty()) {
        moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column + 2, colour, square));
      }

      moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column + 1, colour,
              square));
      moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column + 1, colour,
              square));
    } else {
      moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column + 1, colour, square));
      moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column + 1, colour,
              square));
      moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column + 1, colour,
              square));
    }
    return moves;
  }

  /**
   * returns a list of fields the current pawn attacks.
   *
   * @param row
   * @param column
   * @param moves
   * @param square
   * @return ArrayList <Field>
   */
  @SuppressWarnings("unchecked")
  public static ArrayList allFieldsThePawnThreateneds(int row, int column, ArrayList moves,
                                                      Square[][] square) {
    String colour = square[row][column].getPiece().getColour();

    if (colour.equals("white")) {

      moves.addAll(addFieldIfPawnThreatenedsIt(row - 1, column - 1, colour,
              square));
      moves.addAll(addFieldIfPawnThreatenedsIt(row + 1, column - 1, colour,
              square));


    } else {


      moves.addAll(addFieldIfPawnThreatenedsIt(row - 1, column + 1, colour,
              square));
      moves.addAll(addFieldIfPawnThreatenedsIt(row + 1, column + 1, colour,
              square));
    }
    return moves;
  }


  @Override
  String getTextualRepresentation() {
    return null;
  }

  @Override
  public Player getPlayer() {
    return null;
  }

  @Override
  public boolean hasBeenMoved() {
    return false;
  }

  @Override
  public void setHasBeenMoved(boolean hasBeenMoved) {

  }


  @Override
  public String getType() {
    return null;
  }

  @Override
  public void setType(String type) {

  }

  @Override
  public String getColour() {
    return null;
  }

  @Override
  public void setColour(String colour) {

  }
}
