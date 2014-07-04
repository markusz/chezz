package model.pieces;

import model.Move;
import model.Player;
import model.Square;

import java.util.ArrayList;
import java.util.Set;

public class Rook extends AbstractPiece {

  public Rook(Player player){
    super(player);
    textualRepresentation = "R";
  }

    /**
     * Lists fields of attacked fields considering the type of opponent figure (king or not king) standing on the field.
     * <p/>
     * adds field in the current direction (one while-loop for each direction) as long as the field is empty.
     * <p/>
     * if a non empty field is found the method checks the colour and type of the figure on it.
     * <p/>
     * if it has a different colour and is not a king the field is added.
     * otherwise a break operation is performed and the loop ends.
     *
     * @param row    = row of the starting field
     * @param column = column of the starting field
     * @param moves  = the arraylist the moves are added and that is returned at the end
     * @param square  = the Field[][] the operations are performed on
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList fieldsActuallyAllowedForRookToMove(int row, int column, ArrayList moves,
                                                               Square[][] square) {
        String colour = square[row][column].getPiece().getColour();
        int puffer_row = row;
        int puffer_column = column;
        while (row + 1 < 8) {
            if (square[row + 1][column].isEmpty()) {
                moves.add(square[row + 1][column]);
                row++;
            } else {
                if (!square[row + 1][column].getPiece().getColour().equals(colour) && !square[row + 1][column].getPiece().getType().equals("king")) {
                    moves.add(square[row + 1][column]);
                    break;
                } else
                    break;
            }
        }

        row = puffer_row;

        while (row - 1 > -1) {
            if (square[row - 1][column].isEmpty()) {
                moves.add(square[row - 1][column]);
                row--;
            } else {
                if (!square[row - 1][column].getPiece().getColour().equals(colour)
                        && !square[row - 1][column].getPiece().getType().equals("king")) {
                    moves.add(square[row - 1][column]);
                    break;
                } else
                    break;
            }
        }

        row = puffer_row;

        while (column + 1 < 8) {
            if (square[row][column + 1].isEmpty()) {
                moves.add(square[row][column + 1]);
                column++;
            } else {
                if (!square[row][column + 1].getPiece().getColour().equals(colour)
                        && !square[row][column + 1].getPiece().getType().equals("king")) {
                    moves.add(square[row][column + 1]);
                    break;
                } else
                    break;
            }
        }

        column = puffer_column;

        while (column - 1 > -1) {
            if (square[row][column - 1].isEmpty()) {
                moves.add(square[row][column - 1]);
                column--;
            } else {
                if (!square[row][column - 1].getPiece().getColour().equals(colour)
                        && !square[row][column - 1].getPiece().getType().equals("king")) {
                    moves.add(square[row][column - 1]);
                    break;
                } else
                    break;
            }
        }

        column = puffer_column;
        return moves;

    }

    /**
     * Lists fields of attacked fields not considering the type of opponent figure standing on the field.
     * <p/>
     * adds field in the current direction (one while-loop for each direction) as long as the field is empty.
     * <p/>
     * if a non empty field is found the method checks the colour of the figure on it.
     * <p/>
     * if it has the same colour like the figure moving,a break operation is performed and the loop ends.
     * if it's colour is different to the figure's, the field is added. afterwards a break operation is performed and the loop ends;
     *
     * @param row    = row of the starting field
     * @param column = column of the starting field
     * @param moves  = the arraylist the moves are added and that is returned at the end
     * @param square  = the Field[][] the operations are performed on
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList fieldsThreatenedByTheRook(int row, int column,
                                                      ArrayList moves, Square[][] square) {
        String colour = square[row][column].getPiece().getColour();
        int puffer_row = row;
        int puffer_column = column;
        while (row + 1 < 8) {
            if (square[row + 1][column].isEmpty()) {
                moves.add(square[row + 1][column]);
                row++;
            } else {
                if (!square[row + 1][column].getPiece().getColour().equals(colour)) {
                    moves.add(square[row + 1][column]);
                    break;
                } else
                    break;
            }
        }

        row = puffer_row;

        while (row - 1 > -1) {
            if (square[row - 1][column].isEmpty()) {
                moves.add(square[row - 1][column]);
                row--;
            } else {
                if (!square[row - 1][column].getPiece().getColour().equals(colour)) {
                    moves.add(square[row - 1][column]);
                    break;
                } else
                    break;
            }
        }

        row = puffer_row;

        while (column + 1 < 8) {
            if (square[row][column + 1].isEmpty()) {
                moves.add(square[row][column + 1]);
                column++;
            } else {
                if (!square[row][column + 1].getPiece().getColour().equals(colour)) {
                    moves.add(square[row][column + 1]);
                    break;
                } else
                    break;
            }
        }

        column = puffer_column;

        while (column - 1 > -1) {
            if (square[row][column - 1].isEmpty()) {
                moves.add(square[row][column - 1]);
                column--;
            } else {
                if (!square[row][column - 1].getPiece().getColour().equals(colour)) {
                    moves.add(square[row][column - 1]);
                    break;
                } else
                    break;
            }
        }

        column = puffer_column;
        return moves;

    }

    /**
     * Lists fields of attacked fields, ignoring the position of the enemy king
     * <p/>
     * If an enemy king is found, the field is skipped without adding it to the list
     * no breaking operation is performed.
     * <p/>
     * Does not affect anything else. Fields, where the rook can capture a figure that is not a king are still added.
     *
     * @param row    = row of the starting field
     * @param column = column of the starting field
     * @param moves  = the arraylist the moves are added and that is returned at the end
     * @param square  = the Field[][] the operations are performed on
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList fieldsThreatendByTheRookSkippingKingsPosition(int row, int column,
                                                                          ArrayList moves, Square[][] square) {
        String colour = square[row][column].getPiece().getColour();
        int puffer_row = row;
        int puffer_column = column;


        while (row + 1 < 8) {
            if (square[row + 1][column].isEmpty()) {
                moves.add(square[row + 1][column]);
                row++;
            } else {
                if (!square[row + 1][column].getPiece().getColour().equals(colour)) {
                    if ((square[row + 1][column].getPiece().getType().equals("king"))) {
                        row++;
                    } else {
                        moves.add(square[row + 1][column]);
                        break;
                    }
                } else {
                    moves.add(square[row + 1][column]); //added 9.4
                    break;
                }
            }

        }
        column = puffer_column;
        row = puffer_row;

        while (row - 1 > -1) {
            if (square[row - 1][column].isEmpty()) {
                moves.add(square[row - 1][column]);
                row--;
            } else {
                if (!square[row - 1][column].getPiece().getColour().equals(colour)) {
                    if (square[row - 1][column].getPiece().getType().equals("king")) {
                        row--;
                    } else {
                        moves.add(square[row - 1][column]);
                        break;
                    }
                } else {
                    moves.add(square[row - 1][column]);//added 9.4
                    break;
                }
            }

        }
        column = puffer_column;
        row = puffer_row;

        while (column + 1 < 8) {
            if (square[row][column + 1].isEmpty()) {
                moves.add(square[row][column + 1]);
                column++;
            } else {
                if (!square[row][column + 1].getPiece().getColour().equals(colour)) {
                    if (square[row][column + 1].getPiece().getType().equals("king"))
                        column++;
                    else {
                        moves.add(square[row][column + 1]);
                        break;
                    }
                } else {
                    moves.add(square[row][column + 1]);//added 9.4
                    break;
                }
            }

        }
        row = puffer_row;
        column = puffer_column;

        while (column - 1 > -1) {
            if (square[row][column - 1].isEmpty()) {
                moves.add(square[row][column - 1]);
                column--;
            } else {
                if (!square[row][column - 1].getPiece().getColour().equals(colour)) {
                    if (square[row][column - 1].getPiece().getType().equals("king"))
                        column--;
                    else {
                        moves.add(square[row][column - 1]);
                        break;
                    }
                } else {
                    moves.add(square[row][column - 1]);//added 9.4
                    break;
                }
            }

        }
        row = puffer_row;
        column = puffer_column;
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
  public Set<Square> getAllThreatenedSquares() {
    return null;
  }

  @Override
  public Set<Move> getAllMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllNormalMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllCapturingMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllOtherMoves() {
    return null;
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
