package model.pieces;

import exceptions.SquareNotFoundException;
import model.Player;
import model.Square;

import java.util.ArrayList;

public class Pawn extends AbstractPiece {

  public Pawn(Player player) {
    super(player);
    textualRepresentation = "P";
  }

  @Override
  public void updatePossibleMoves() {
    int m = getPlayer().getBoardModifier();
    Square c = getCurrentPositionOnBoard();


    updateNormalMoves(m, c);
    updateAttackMoves(m, c);
  }

  private void updateAttackMoves(int m, Square c) {
    //Little math trick to avoid unnecessary if's. Background Info: White (black) needs to be on row 5 (4) to capure en passant
    //Detemine with board orientation
    //white: 2 * 5 - (+1) = 9
    //black: 2 * 4 - (-1) = 9
    Player playingPlayer = currentPosition.getPiece().getPlayer();
    boolean pieceIsOnRequiredRowForEnPassant = 2 * currentPosition.getChessNotation().getRowNumber() - playingPlayer.getBoardModifier() == 9;

    try {
      Square squareLeftAcross = board.getSquareNRowsMColumnsAway(currentPosition, 1, -1, player.getBoardModifier());
      Square squareToLeft = board.getSquareNRowsMColumnsAway(currentPosition, 0, -1, player.getBoardModifier());

      //TODO add to threatened squares
      if (!squareLeftAcross.isEmpty() && canValidlyCapturePiece(squareLeftAcross.getPiece())) {
        //TODO can capture
      }
      if(pieceIsOnRequiredRowForEnPassant && !isProtectingHisKing() && squareLeftAcross.isEmpty() && !squareToLeft.getPiece().isSameColor(this) && (squareToLeft.getPiece() instanceof Pawn)){
        //TODO can capture en passant
      }
    } catch (SquareNotFoundException e) {/*fail silently*/}

    try {
      Square squareRightAcross = board.getSquareNRowsMColumnsAway(currentPosition, 1, 1, player.getBoardModifier());
      Square squareToRight = board.getSquareNRowsMColumnsAway(currentPosition, 0, 1, player.getBoardModifier());

      //TODO add to threatened squares
      if (!squareRightAcross.isEmpty() && canValidlyCapturePiece(squareRightAcross.getPiece())) {
        //TODO can capture
      }
      if(pieceIsOnRequiredRowForEnPassant && !isProtectingHisKing() && squareRightAcross.isEmpty() && !squareToRight.getPiece().isSameColor(this) && (squareToRight.getPiece() instanceof Pawn)){
        //TODO can capture en passant
      }
    } catch (SquareNotFoundException e) {/*fail silently*/}
  }

  private void updateNormalMoves(int boardModifier, Square from) {
    if (!isProtectingHisKing()) {
      try {
        Square oneSquareAhead = board.getSquareNRowsAhead(from, 1, boardModifier);
        if (oneSquareAhead.isEmpty()) {
          //TODO advance one square
        }

        if (!hasBeenMoved()) {
          Square twoSquaresAhead = board.getSquareNRowsAhead(from, 2, boardModifier);
          if (oneSquareAhead.isEmpty() && twoSquaresAhead.isEmpty()) {
            //todo advance two squares (only possible in the beginning)
          }
        }
      } catch (SquareNotFoundException e) {
        //fail silently
      }
    }
  }
}
