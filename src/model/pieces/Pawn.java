package model.pieces;

import model.Square;

import java.util.ArrayList;

public class Pawn extends AbstractPiece {

  public Pawn(int playerId) {
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
            if (square[row][column].getEmpty()) {
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
            if (square[row][column].getEmpty()) {
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
                if (square[row][column - 1].getEmpty()) {
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
            if (square[row][column + 1].getEmpty()) {
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
  public boolean getHasBeenMoved() {
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
