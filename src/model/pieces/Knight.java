package model.pieces;

import model.Square;

import java.util.ArrayList;

public class Knight extends AbstractPiece{

  public Knight(int identifier){

  }

    @SuppressWarnings("unchecked")
    public static ArrayList knightMovesActuallyAllowed(int row, int column, ArrayList moves,
                                                       Square[][] square) {


        //int puffer_row = row;
        //int puffer_column = column;
        String colour = square[row][column].getPiece().getColour();

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 1, column - 2, colour, square));

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 1, column + 2, colour, square));

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 1, column - 2, colour, square));

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 1, column + 2, colour, square));

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 2, column - 1, colour, square));

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 2, column + 1, colour, square));

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 2, column - 1, colour, square));

        moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 2, column + 1, colour, square));

        return moves;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList addMoveIsKnightIsActuallyAllowedToMove(int row, int column, String colour,
                                                                   Square[][] square) {

        ArrayList temp = new ArrayList();

        if (!(row < 0 || column < 0 || row > 7 || column > 7))
            if (square[row][column].getEmpty()) {
                temp.add(square[row][column]);
            } else {
                if (!square[row][column].getPiece().getColour().equals(colour) && !square[row][column].getPiece().getType().equals("king")) {
                    temp.add(square[row][column]);

                } else
                    ;
            }
        return temp;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList knightMovesCheck(int row, int column, ArrayList moves,
                                             Square[][] square) {


        //int puffer_row = row;
        //int puffer_column = column;
        String colour = square[row][column].getPiece().getColour();

        moves.addAll(knigthMovesCheckHelp(row + 1, column - 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row + 1, column + 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 1, column - 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 1, column + 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 2, column - 1, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 2, column + 1, colour, square));

        moves.addAll(knigthMovesCheckHelp(row + 2, column - 1, colour, square));

        moves.addAll(knigthMovesCheckHelp(row + 2, column + 1, colour, square));

        return moves;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList knigthMovesCheckHelp(int row, int column, String colour,
                                                 Square[][] square) {

        ArrayList temp = new ArrayList();

        if (!(row < 0 || column < 0 || row > 7 || column > 7))
            if (square[row][column].getEmpty()) {
                temp.add(square[row][column]);
            } else {
                if (!square[row][column].getPiece().getColour().equals(colour)) {
                    temp.add(square[row][column]);

                } else
                    ;
            }
        return temp;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList knightMovesAllThreatenedFields(int row, int column, ArrayList moves,
                                                           Square[][] square) {


        //int puffer_row = row;
        //int puffer_column = column;
        String colour = square[row][column].getPiece().getColour();

        moves.addAll(knigthMovesCheckHelp(row + 1, column - 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row + 1, column + 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 1, column - 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 1, column + 2, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 2, column - 1, colour, square));

        moves.addAll(knigthMovesCheckHelp(row - 2, column + 1, colour, square));

        moves.addAll(knigthMovesCheckHelp(row + 2, column - 1, colour, square));

        moves.addAll(knigthMovesCheckHelp(row + 2, column + 1, colour, square));

        return moves;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList addFieldIfKnightThreatenedsIt(int row, int column, String colour,
                                                          Square[][] square) {

        ArrayList temp = new ArrayList();

        if (!(row < 0 || column < 0 || row > 7 || column > 7)) {

            temp.add(square[row][column]);

        }
        return temp;

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
