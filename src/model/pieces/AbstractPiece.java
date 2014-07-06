package model.pieces;

import model.Board;
import model.Move;
import model.Player;
import model.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Markus on 28.06.2014.
 */
public abstract class AbstractPiece implements Piece {

  protected Player player;
  protected Board board;
  protected String textualRepresentation;
  protected Square currentPosition;
  protected boolean hasBeenMoved;
  protected boolean isProtectingHisKing;
  protected List<Move> threats = new ArrayList<>();
  protected List<Move> normalMoves = new ArrayList<>();


  public AbstractPiece(Player player) {
    this.hasBeenMoved = false;
    this.board = board;
    this.player = player;
  }

  public boolean isWhite() {
    return getPlayer().isWhite();
  }

  @Override
  public Set<Square> getAllowFieldsToMoveOnto() {
    Set<Square> allowedSquares = new HashSet<>();
    getAllMoves().forEach(move -> allowedSquares.add(move.getTo()));
    return allowedSquares;
  }

  public boolean canValidlyCapturePiece(Piece piece) {
    return !isProtectingHisKing() && !piece.isSameColor(this) && !(piece instanceof King);
  }

  @Override
  public Set<Square> getAllThreatenedSquares() {
    return null;
  }

  @Override
  public Set<Move> getAllMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllNormalMoves() {
    return null;
  }

  public String getTextualRepresentation() {
    return textualRepresentation;
  }


  @Override
  public Set<Move> getAllCapturingMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllOtherMoves() {
    return null;
  }

  public boolean isSameColor(Piece piece) {
    return !(piece.isWhite() ^ this.isWhite());
  }

  public Square getCurrentPositionOnBoard() {
    return currentPosition;
  }

  public void setCurrentPositionOnBoard(Square position) {
    this.currentPosition = position;
  }

  public Player getPlayer() {
    return player;
  }

  public void setHasBeenMoved(boolean hasBeenMoved) {
    this.hasBeenMoved = hasBeenMoved;
  }

  public boolean hasBeenMoved() {
    return hasBeenMoved;
  }

  public boolean isProtectingHisKing() {
    return isProtectingHisKing;
  }
}
