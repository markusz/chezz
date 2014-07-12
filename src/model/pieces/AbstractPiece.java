package model.pieces;

import model.Board;
import model.Move;
import model.Player;
import model.Square;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  protected List<Move> threats = new ArrayList<>();
  protected List<Move> normalMoves = new ArrayList<>();


  public AbstractPiece(Player player) {
    this.hasBeenMoved = false;
    this.player = player;
    player.addPiece(this);
    initImageIcons();
  }

  private void initImageIcons() {
    String color = isWhite() ? "white" : "black";
    String pieceType = this.getClass().getSimpleName();
    StringBuilder sb = new StringBuilder().
            append(imageIconsFolder).
            append(File.separator).
            append(color).append(pieceType);

    imageIconOnDarkSquare = new ImageIcon(sb.toString().concat("DarkField").concat(imageIconsFileSuffix));
    imageIconOnLightSquare = new ImageIcon(sb.toString().concat("WhiteField").concat(imageIconsFileSuffix));
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

  @Override
  public boolean isValidMoveDestination(Square destination) {
    return getAllowedSquaresToMoveOnto().contains(destination);
  }

  @Override
  public ImageIcon getImageIcon(Square square) {
    return square.isDark() ? imageIconOnDarkSquare : imageIconOnLightSquare;
  }
}
