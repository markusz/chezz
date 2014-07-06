package model.pieces;

import exceptions.SquareNotFoundException;
import model.Move;
import model.Player;
import model.Square;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Bishop extends AbstractStraightMovingPiece {


  public Bishop(Player player) {
    super(player);
    textualRepresentation = "B";
  }

  @Override
  public void updatePossibleMoves() {

    boolean isProtectingHisKing = isProtectingHisKing();
    if (isProtectingHisKing) {
      normalMoves.clear();
    }

    List<Square> topRight = getSquaresInStraightLineUntilFirstPiece(1, 1);
    List<Square> bottomRight = getSquaresInStraightLineUntilFirstPiece(-1, 1);
    List<Square> topLeft = getSquaresInStraightLineUntilFirstPiece(1, -1);
    List<Square> bottomLeft = getSquaresInStraightLineUntilFirstPiece(-1, -1);

    List<Square> all = new LinkedList<>();
    all.addAll(topRight);
    all.addAll(bottomRight);
    all.addAll(topLeft);
    all.addAll(bottomLeft);

    for (Square square : all) {
      if (square.isEmpty() && !isProtectingHisKing) {
        //TODO set to moves
        //TODO add to threatened squares
      }
      if (!square.isEmpty() && !square.getPiece().isSameColor(this)) {
        if (square.getPiece() instanceof King) {
          //TODO opponent is check
        }else{
          if(isProtectingHisKing){
            //TODO piece is threatened
          }else{
            //TODO piece can be thrown
          }
        }
      }
    }
  }
}
