package model.pieces;

import model.Board;
import model.Move;
import model.Player;
import model.Square;

import javax.swing.*;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Markus on 28.06.2014.
 */
public abstract class AbstractPiece implements Piece, AttackingEntity {

  protected Player player;
  protected Board board;
  protected String textualRepresentation;
  protected Square currentPosition;
  protected boolean hasBeenMoved;

  private String imageIconsFolder = "ressource/png/";
  private String imageIconsFileSuffix = ".png";
  private ImageIcon imageIconOnDarkSquare;
  private ImageIcon imageIconOnLightSquare;

  public boolean hasBeenCaptured() {
    return hasBeenCaptured;
  }

  public void setHasBeenCaptured(boolean hasBeenCaptured) {
    this.hasBeenCaptured = hasBeenCaptured;
  }

  protected boolean hasBeenCaptured;
  protected boolean isProtectingHisKing;
  protected Set<Move> capturingMoves = new TreeSet<>();
  protected Set<Move> normalMoves = new TreeSet<>();
  protected Set<Move> allMoves = new TreeSet<>();
  protected Set<Move> otherMoves = new TreeSet<>();
  protected Set<Square> threatenedSquares = new TreeSet<>();


  public AbstractPiece(Player player, Board board) {
    this.hasBeenMoved = false;
    this.player = player;
    this.board = board;
    player.addPiece(this);
    initImageIcons();
  }

  private void initImageIcons() {
    String color = isWhite() ? "white" : "black";
    String pieceType = this.getClass().getSimpleName();
    StringBuilder sb = new StringBuilder().
            append(imageIconsFolder).
//            append(File.separator).
            append(color).append(pieceType);

    imageIconOnDarkSquare = new ImageIcon(sb.toString().concat("DarkField").concat(imageIconsFileSuffix));
    imageIconOnLightSquare = new ImageIcon(sb.toString().concat("LightField").concat(imageIconsFileSuffix));
  }

  public boolean isWhite() {
    return getPlayer().isWhite();
  }

  @Override
  public Set<Square> getAllowedSquaresToMoveOnto() {
    Set<Square> allowedSquares = new HashSet<>();
    getAllMoves().forEach(move -> allowedSquares.add(move.getTo()));
    return allowedSquares;
  }

  public boolean canValidlyCapturePiece(Piece piece) {
    return !isProtectingHisKing() && !piece.isSameColor(this) && !(piece instanceof King);
  }

  public boolean checks(Piece piece) {
    return !piece.isSameColor(this) && (piece instanceof King);
  }

  @Override
  public Set<Square> getAllThreatenedSquares() {
    return null;
  }

  @Override
  public Set<Move> getAllMoves() {
    return allMoves;
  }

  @Override
  public Set<Move> getAllNormalMoves() {
    return normalMoves;
  }

  public String getTextualRepresentation() {
    return textualRepresentation;
  }


  @Override
  public Set<Move> getAllCapturingMoves() {
    return capturingMoves;
  }

  @Override
  public Set<Move> getAllOtherMoves() {
    return otherMoves;
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

  @Override
  public boolean isValidMoveDestination(Square destination) {
    return getAllowedSquaresToMoveOnto().contains(destination);
  }

  protected void addMoveTo(Move move, Collection... collections) {
    for (Collection collection : collections) {
      collection.add(move);
    }
  }

  protected void addMoveTo(Square from, Square to, int moveType, Collection... collections) {
    Move move = new Move(from, to, moveType);
    for (Collection collection : collections) {
      collection.add(move);
    }
  }

  @Override
  public ImageIcon getImageIcon(Square square) {
    return square.isDark() ? imageIconOnDarkSquare : imageIconOnLightSquare;
  }
}
