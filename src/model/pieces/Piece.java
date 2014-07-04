package model.pieces;

import model.Move;
import model.Player;
import model.Square;

import java.util.Set;

/**
 * Created by Markus on 28.06.2014.
 */
public interface Piece {

  public Player getPlayer();
  public boolean isWhite();
  public boolean hasBeenMoved();
  public Square getCurrentPositionOnBoard();
  public void setCurrentPositionOnBoard(Square square);
  public void setHasBeenMoved(boolean hasBeenMoved);
  public boolean isProtectingHisKing();
  public String getTextualRepresentation();
  public boolean isSameColor(Piece piece);
  public void updatePossibleMoves();
  public Set<Square> getAllowFieldsToMoveOnto();
  public Set<Square> getAllThreatenedSquares();
  public Set<Move> getAllMoves();
  public Set<Move> getAllNormalMoves();
  public Set<Move> getAllCapturingMoves();
  public Set<Move> getAllOtherMoves();
}
