package model.pieces;

import model.Player;
import model.Square;

import java.util.LinkedList;
import java.util.List;

/**
 * Duplicates code from the Rook and the Bishop classes (moveDiagonally / moveStraight) since Java doesn't support multiple inheritance
 */
public class Queen extends AbstractStraightMovingPiece {

  public Queen(Player player) {
    super(player);
    textualRepresentation = "Q";
  }

  @Override
  public void updatePossibleMoves() {
    boolean isProtectingHisKing = isProtectingHisKing();
    if (isProtectingHisKing) {
      normalMoves.clear();
    }

    List<Square> upRight = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.RIGHT);
    List<Square> downRight = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.RIGHT);
    List<Square> upLeft = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.LEFT);
    List<Square> downLeft = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.LEFT);
    List<Square> up = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.STAY);
    List<Square> down = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.STAY);
    List<Square> right = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.RIGHT);
    List<Square> left = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.LEFT);

    List<Square> all = new LinkedList<>();
    all.addAll(upRight);
    all.addAll(downRight);
    all.addAll(upLeft);
    all.addAll(downLeft);
    all.addAll(up);
    all.addAll(down);
    all.addAll(left);
    all.addAll(right);

    for (Square square : all) {
      if (square.isEmpty() && !isProtectingHisKing) {
        //TODO set to moves
        //TODO add to threatened squares
      }
      if (!square.isEmpty() && !square.getPiece().isSameColor(this)) {
        if (square.getPiece() instanceof King) {
          //TODO opponent is check
        } else {
          if (isProtectingHisKing) {
            //TODO piece is threatened
          } else {
            //TODO piece can be thrown
          }
        }
      }
    }
  }
}
