package model.pieces;

import model.Board;
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
  public abstract String getTextualRepresentation();

  protected Player player;
  protected Board board;
  protected String textualRepresentation;
  protected Square currentPosition;
  protected List<Square> threatenedSquares = new ArrayList<>();
  protected List<Square> moveableSquares = new ArrayList<>();


  public AbstractPiece(Player player) {
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

  public boolean isSameColor(Piece piece){
    return !(piece.isWhite() ^ this.isWhite());
  }

  public Square getCurrentPositionOnBoard() {
    return currentPosition;
  }

  public void setCurrentPositionOnBoard(Square position) {
    this.currentPosition = position;
  }
}
