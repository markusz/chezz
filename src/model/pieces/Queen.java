package model.pieces;

import model.Player;

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

    int[][] directions = {
            {AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.RIGHT},
            {AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.RIGHT},
            {AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.LEFT},
            {AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.LEFT},
            {AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.STAY},
            {AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.STAY},
            {AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.RIGHT},
            {AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.LEFT}
    };

    updatePossibleMovesForDirections(directions);
  }
}
