package model.pieces;

import exceptions.SquareNotFoundException;
import model.Board;
import model.Move;
import model.Player;
import model.Square;

public class Knight extends AbstractStraightMovingPiece {

  public Knight(Player player, Board board) {
    super(player, board);
    textualRepresentation = "k";
  }

  @Override
  public void updatePossibleMoves() {
    int[][] steps = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
    for (int i = 0; i < steps.length; i++) {
      try {
        Square to = board.getSquareNRowsMColumnsAway(currentPosition, steps[i][0], steps[i][1], player.getBoardModifier());
        threatenedSquares.add(to);
        if(!isProtectingHisKing()){
          if(to.isEmpty()){
            Move move = new Move(currentPosition, to, Move.NORMAL);
            addMoveTo(move, normalMoves, allMoves);
          }else{
            if(canValidlyCapturePiece(to.getPiece())){
              Move move = new Move(currentPosition, to, Move.CAPTURE);
              addMoveTo(move, capturingMoves, allMoves);
            }
          }
        }
      } catch (SquareNotFoundException e) {
        continue;
      }
    }
  }
}
