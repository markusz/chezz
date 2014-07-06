package model;


import model.pieces.Piece;
import utils.ChessNotationUtil;

public class Square {


  private boolean threatendByWhite = false;
  private boolean threatendByBlack = false;

  private Piece piece;
  private int rowIndex;
  private int columnIndex;

  public ChessNotation getChessNotation() {
    return chessNotation;
  }

  private ChessNotation chessNotation;

  public Square(int rowIndex, int columnIndex, Piece piece) {
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    this.chessNotation = new ChessNotation(rowIndex, columnIndex);
    this.piece = piece;
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

}
