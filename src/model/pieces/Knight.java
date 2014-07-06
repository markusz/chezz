package model.pieces;

import exceptions.SquareNotFoundException;
import model.Player;
import model.Square;

public class Knight extends AbstractStraightMovingPiece {

  public Knight(Player player){
    super(player);
    textualRepresentation = "k";
  }

  @Override
  public void updatePossibleMoves() {
    int[][] steps = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
    for (int i = 0; i < steps.length; i++) {
      try {
        Square to = board.getSquareNRowsMColumnsAway(currentPosition, steps[i][0], steps[i][1], player.getBoardModifier());
        //TODO threatens square
        if(canValidlyCapturePiece(to.getPiece())){
          //TODO capture square
        }
      } catch (SquareNotFoundException e) {
        continue;
      }
    }
  }
}
