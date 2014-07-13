package model.pieces;

import model.Board;
import model.Player;

public class Bishop extends AbstractStraightMovingPiece {


  public Bishop(Player player, Board board) {
    super(player, board);
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
