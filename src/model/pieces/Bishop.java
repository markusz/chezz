package model.pieces;

import exceptions.SquareNotFoundException;
import model.Move;
import model.Player;
import model.Square;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Bishop extends AbstractPiece {


  public Bishop(Player player) {
    super(player);
    textualRepresentation = "B";
  }

  @Override
  public Set<Square> getAllThreatenedSquares() {
    return null;
  }

  @Override
  public Set<Move> getAllCapturingMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllNormalMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllOtherMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllMoves() {
    return null;
  }

  @Override
  public void updatePossibleMoves() {

    boolean isProtectingHisKing = isProtectingHisKing();
    if (isProtectingHisKing) {
      moveableSquares.clear();
    }

    List<Square> topRight = moveVerticallyAsFarAsPossible(1, 1);
    List<Square> bottomRight = moveVerticallyAsFarAsPossible(-1, 1);
    List<Square> topLeft = moveVerticallyAsFarAsPossible(1, -1);
    List<Square> bottomLeft = moveVerticallyAsFarAsPossible(-1, -1);

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

  private List<Square> moveVerticallyAsFarAsPossible(int verticalDirection, int horizontalDirection) {
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

  @Override
  public String getTextualRepresentation() {
    return null;
  }

  @Override
  public Player getPlayer() {
    return null;
  }


  @Override
  public void setHasBeenMoved(boolean hasBeenMoved) {

  }

  @Override
  public boolean isProtectingHisKing() {
    return false;
  }

  @Override
  public boolean hasBeenMoved() {
    return false;
  }
}
