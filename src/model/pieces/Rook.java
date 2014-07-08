package model.pieces;

import model.Move;
import model.Player;
import model.Square;

import java.util.ArrayList;
import java.util.Set;

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
