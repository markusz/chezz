package model.pieces;

import model.Player;

public class Rook extends AbstractStraightMovingPiece {

  public Rook(Player player){
    super(player);
    textualRepresentation = "R";
  }

  @Override
  public void updatePossibleMoves() {
    int[][] directions = {
            {AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.STAY},
            {AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.STAY},
            {AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.RIGHT},
            {AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.LEFT}
    };

    updatePossibleMovesForDirections(directions);
  }
}
