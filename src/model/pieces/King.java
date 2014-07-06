package model.pieces;

import exceptions.SquareNotFoundException;
import model.Player;
import model.Square;

import java.util.ArrayList;

public class King extends AbstractPiece{

  public King(Player player){
    super(player);
    textualRepresentation = "K";
  }

  @Override
  public void updatePossibleMoves() {
    int[] dirs = {-1, 0, 1};

    for (int row = 0; row < dirs.length; row++) {
      for (int column = 0; column < dirs.length; column++) {
        int dir = dirs[column];
        if(row != 0 || column != 0){
          try {
            Square to = board.getSquareNRowsMColumnsAway(currentPosition, row, column, player.getBoardModifier());

            //TODO add to threatened list
            if(!board.isSquareAttackedByOpponentOf(to, player)){
              if(to.isEmpty() || !to.getPiece().isSameColor(this)){
                //TODO can move to field (capture or normal move);
              }
            }
          } catch (SquareNotFoundException e) {
            //fail silently
          }
        }
      }
    }
  }
}
