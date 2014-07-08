package model.pieces;

import model.Player;

public class Bishop extends AbstractStraightMovingPiece {


  public Bishop(Player player) {
    super(player);
    textualRepresentation = "B";
  }

  @Override
  public void updatePossibleMoves() {
    int[][] directions = {
            {AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.RIGHT},
            {AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.RIGHT},
            {AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.LEFT},
            {AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.LEFT}
    };

    updatePossibleMovesForDirections(directions);
  }
}
