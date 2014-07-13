package model;


import model.pieces.Piece;
import ui.GUISquare;
import utils.ChessNotationUtil;

import javax.swing.*;

public class Square implements Comparable<Square> {


  private boolean threatendByWhite = false;
  private boolean threatendByBlack = false;

  private Piece piece;
  private int rowIndex;
  private int columnIndex;
  private String emptyDarkIconPath = "ressource/png/emptyDark.png";
  private String emptyLightIconPath = "ressource/png/emptyLight.png";
  private ImageIcon emptyIcon;

  public GUISquare getGuiSquare() {
    return guiSquare;
  }

  public void setGuiSquare(GUISquare guiSquare) {
    this.guiSquare = guiSquare;
  }

  private GUISquare guiSquare;

  public ImageIcon getIcon() {
    return isEmpty() ? emptyIcon : piece.getImageIcon(this);
  }

  public boolean isDark() {
    return dark;
  }

  private boolean dark;

  public ChessNotation getChessNotation() {
    return chessNotation;
  }

  private ChessNotation chessNotation;

  public Square(int rowIndex, int columnIndex, Piece piece) {
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    this.chessNotation = new ChessNotation(rowIndex, columnIndex);
    this.piece = piece;
    this.dark = (rowIndex % 2 == 0 && columnIndex % 2 == 0) || (rowIndex % 2 != 0 && columnIndex % 2 != 0);
    emptyIcon = new ImageIcon(dark ? emptyDarkIconPath : emptyLightIconPath);
  }

  public int getRowIndex() {
    return this.rowIndex;
  }

  public void setRowIndex(int rowIndex) {
    this.rowIndex = rowIndex;
  }

  public int getColumnIndex() {
    return this.columnIndex;
  }

  public void setColumnIndex(int columnIndex) {
    this.columnIndex = columnIndex;
  }

  public boolean isEmpty() {
    return (this.piece == null);
  }

  public Piece getPiece() {
    return this.piece;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  public void setEmpty() {
    this.piece = null;
  }

  public boolean isThreatendByWhite() {
    return threatendByWhite;
  }

  public void setThreatendByWhite(boolean threatendByWhite) {
    this.threatendByWhite = threatendByWhite;
  }

  public boolean isThreatendByBlack() {
    return threatendByBlack;
  }

  public void setThreatendByBlack(boolean threatendByBlack) {
    this.threatendByBlack = threatendByBlack;
  }

  @Override
  public int compareTo(Square o) {

    return this.equals(o) ? 0 : 1;
  }
}
