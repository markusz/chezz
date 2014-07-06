package model;

import utils.ChessNotationUtil;

/**
 * Created by Markus on 06.07.2014.
 */
public class ChessNotation {
  private int rowNumber;
  private char columnLetter;

  public String getChessNotationName() {
    return chessNotationName;
  }

  private String chessNotationName;

  @Override
  public String toString() {
    return this.chessNotationName;
  }

  public ChessNotation(int rowIndex, int columnIndex) {
    this.rowNumber = ChessNotationUtil.convertRowArrayIndexToRowNumber(rowIndex);
    this.columnLetter = ChessNotationUtil.convertColumnArrayIndexToLetter(columnIndex);
    this.chessNotationName = ChessNotationUtil.convertFieldIndexToChessNotation(rowIndex, columnIndex);
  }

  public int getRowNumber() {
    return rowNumber;
  }

  public char getColumnLetter() {
    return columnLetter;
  }
}