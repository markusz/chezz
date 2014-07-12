package model.pieces;

import exceptions.SquareNotFoundException;
import model.Move;
import model.Player;
import model.Square;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Markus on 06.07.2014.
 */
public abstract class AbstractStraightMovingPiece extends AbstractPiece {
  public AbstractStraightMovingPiece(Player player) {
    super(player);
  }

  public static int UP = 1;
  public static int DOWN = -1;
  public static int LEFT = -1;
  public static int RIGHT = 1;
  public static int STAY = 0;

  protected List<Square> getSquaresInStraightLineUntilFirstPiece(int verticalDirection, int horizontalDirection) {
    List<Square> reachableFields = new LinkedList<>();
    for (int steps = 1; steps < 8; steps++) {
      try {
        Square to = board.getSquareNRowsMColumnsAway(currentPosition, verticalDirection * steps, horizontalDirection * steps, player.getBoardModifier());
        reachableFields.add(to);

        if (!to.isEmpty()) {
          return reachableFields;
        }
      } catch (SquareNotFoundException e) {
        return reachableFields;
      }
    }
    return reachableFields;
  }

  public void updatePossibleMovesForDirections(int[][] directions) {

    List<Square> all = new LinkedList<>();

    for (int i = 0; i < directions.length; i++) {
      int columnDirection = directions[i][0];
      int rowDirection = directions[i][1];

      all.addAll(getSquaresInStraightLineUntilFirstPiece(columnDirection, rowDirection));
    }

    for (Square square : all) {
      threatenedSquares.add(square);
      if(!isProtectingHisKing){
        if(square.isEmpty()){
          Move move = new Move(currentPosition, square, Move.NORMAL);
          addMoveTo(move, normalMoves, allMoves);
        }
        else{
          if(!square.getPiece().isSameColor(this)){
            Move move = new Move(currentPosition, square, Move.CAPTURE);
            addMoveTo(move, capturingMoves, allMoves);
          }
        }
      }
    }
  }
}
