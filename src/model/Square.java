package model;


import model.pieces.Piece;
import utils.ChessNotationUtil;

public class Square {


  private boolean threatendByWhite = false;
  private boolean threatendByBlack = false;

  private int rowIndex;
  private int columnIndex;
  private class ChessNotation{
    private int rowIndex;
    private int columnIndex;

    public ChessNotation(int rowIndex, int columnIndex){
      this.rowIndex = rowIndex;
      this.columnIndex = columnIndex;
    }
  }
  private String chessNotationName;
  private Piece piece;

  public Square(int rowIndex, int columnIndex, Piece piece) {
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    this.piece = piece;
    this.chessNotationName = ChessNotationUtil.convertFieldIndexToChessNotation(rowIndex, columnIndex);
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

  public char getCharRow() throws Exception {
    char rowChar;

    if (this.rowIndex == 0)
      rowChar = 'A';
    else if (this.rowIndex == 1)
      rowChar = 'B';
    else if (this.rowIndex == 2)
      rowChar = 'C';
    else if (this.rowIndex == 3)
      rowChar = 'D';
    else if (this.rowIndex == 4)
      rowChar = 'E';
    else if (this.rowIndex == 5)
      rowChar = 'F';
    else if (this.rowIndex == 6)
      rowChar = 'G';
    else if (this.rowIndex == 7)
      rowChar = 'H';
    else
      throw new Exception(
              "error in assigning a char to the columns number");

    return rowChar;
  }

  public int getIntColumn() throws Exception {
    int intColumn;

    if (this.columnIndex == 0)
      intColumn = 8;
    else if (this.columnIndex == 1)
      intColumn = 7;
    else if (this.columnIndex == 2)
      intColumn = 6;
    else if (this.columnIndex == 3)
      intColumn = 5;
    else if (this.columnIndex == 4)
      intColumn = 4;
    else if (this.columnIndex == 5)
      intColumn = 3;
    else if (this.columnIndex == 6)
      intColumn = 2;
    else if (this.columnIndex == 7)
      intColumn = 1;
    else
      throw new Exception(
              "error in assigning a char to the columns number");

    return intColumn;
  }

  public boolean getEmpty() {
    return (this.piece == null);
  }

  public String getType() {
    return this.piece.getType();
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
