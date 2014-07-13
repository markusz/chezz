package model.pieces;

import exceptions.SquareNotFoundException;
import model.Board;
import model.Move;
import model.Player;
import model.Square;

public class Pawn extends AbstractPiece {

  public Pawn(Player player, Board board) {
    super(player, board);
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

      threatenedSquares.add(squareLeftAcross);
      if (!squareLeftAcross.isEmpty() && canValidlyCapturePiece(squareLeftAcross.getPiece())) {
        Move move = new Move(currentPosition, squareLeftAcross, Move.CAPTURE);
        addMoveTo(move, capturingMoves, allMoves);
      }
      if(pieceIsOnRequiredRowForEnPassant && !isProtectingHisKing() && squareLeftAcross.isEmpty() && !squareToLeft.getPiece().isSameColor(this) && (squareToLeft.getPiece() instanceof Pawn)){
        Move move = new Move(currentPosition, squareLeftAcross, Move.EN_PASSANT);
        addMoveTo(move, capturingMoves, otherMoves, allMoves);
      }
    } catch (SquareNotFoundException e) {/*fail silently*/}

    try {
      Square squareRightAcross = board.getSquareNRowsMColumnsAway(currentPosition, 1, 1, player.getBoardModifier());
      Square squareToRight = board.getSquareNRowsMColumnsAway(currentPosition, 0, 1, player.getBoardModifier());

      threatenedSquares.add(squareRightAcross);
      if (!squareRightAcross.isEmpty() && canValidlyCapturePiece(squareRightAcross.getPiece())) {
        Move move = new Move(currentPosition, squareRightAcross, Move.CAPTURE);
        addMoveTo(move, capturingMoves, allMoves);
      }
      if(pieceIsOnRequiredRowForEnPassant && !isProtectingHisKing() && squareRightAcross.isEmpty() && !squareToRight.getPiece().isSameColor(this) && (squareToRight.getPiece() instanceof Pawn)){
        Move move = new Move(currentPosition, squareRightAcross, Move.EN_PASSANT);
        addMoveTo(move, capturingMoves, otherMoves, allMoves);
      }
    } catch (SquareNotFoundException e) {/*fail silently*/}
  }

  private void updateNormalMoves(int boardModifier, Square from) {
    if (!isProtectingHisKing()) {
      try {
        Square oneSquareAhead = board.getSquareNRowsAhead(from, 1, boardModifier);
        if (oneSquareAhead.isEmpty()) {
          Move move = new Move(currentPosition, oneSquareAhead, Move.NORMAL);
          addMoveTo(move, normalMoves, allMoves);
        }

        if (!hasBeenMoved()) {
          Square twoSquaresAhead = board.getSquareNRowsAhead(from, 2, boardModifier);
          if (oneSquareAhead.isEmpty() && twoSquaresAhead.isEmpty()) {
            Move move = new Move(currentPosition, twoSquaresAhead, Move.NORMAL);
            addMoveTo(move, normalMoves, allMoves);
          }
        }
      } catch (SquareNotFoundException e) {
        //fail silently
      }
    }
  }
}
