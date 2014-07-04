package model;

import exceptions.SquareNotFoundException;
import model.pieces.*;
import utils.ChessNotationUtil;
import utils.MoveUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Board {


  private Game game;
  private Square[][] squares;
  private Collection<Square> iterableSqaures;
  private ArrayList<Square> attackedByWhite;
  private ArrayList<Square> attackedByBlack;
  private Map<String, Square> squareMap = new TreeMap<>();

  private Square whiteKingSquare;
  private Square blackKingSquare;

  public Board(Game game) {

    this.game = game;
    initBoard();
  }

  private void initBoard() {
    this.squares = new Square[8][8];

    for (int row = 0; row < 8; row++) {
      for (int column = 0; column < 8; column++) {
        if (squares[row][column] == null) {
          squares[row][column] = new Square(row, column, null);
          iterableSqaures.add(squares[row][column]);
          squareMap.put(ChessNotationUtil.convertFieldIndexToChessNotation(row, column), squares[row][column]);
        }
      }
    }
  }


  /**
   * marks every attacked field for both colours, regardless of the colour actually being allowed to move there.
   * i.e the white king on E3 and a black rook on D7. Although the king on E3 is not allowed to move on any D-Row field because of the rook on D7.
   * Nevertheless a black king on C3 would also not be allowed to move on any D-Row field because of the E3 King.
   * <p>
   * So this method is used in the king class to determine by the king class whether a move on a empty field is valid
   */
  @SuppressWarnings("unchecked")
  public void markAttackedFields() throws Exception {
    ArrayList fieldsThreatenedByWhite = MoveUtil.threatenedFieldsByColourConsideringEveryAttackedField("white", squares);
    ArrayList fieldsThreatenedByBlack = MoveUtil.threatenedFieldsByColourConsideringEveryAttackedField("black", squares);

    for (Square square : iterableSqaures) {
      square.setThreatendByWhite(fieldsThreatenedByWhite.contains(square));
      square.setThreatendByBlack(fieldsThreatenedByBlack.contains(square));
    }
  }

  public void setPiece(Square square, Piece piece) throws Exception {
    square.setPiece(piece);
    piece.setCurrentPositionOnBoard(square);
  }

  /**
   * removes the figure on the current field
   * if the field is empty, an error message is printed
   *
   * @param field
   * @throws Exception
   */
  public void removeFigureInterface(String field)
          throws Exception {
    int[] fieldArray = ChessNotationUtil.convertFieldNameToIndexes(field);
    if (!this.squares[fieldArray[0]][fieldArray[1]].isEmpty())
      this.squares[fieldArray[0]][fieldArray[1]].setEmpty();
    else
      System.out
              .println("	No figure on " + field + ".\n 	Therefore no figure has been removed from "
                      + field + "\n\n");
  }

  public Square[][] getSquares() {
    return this.squares;
  }

  public Square getSquare(int row, int col) {
    return this.squares[row][col];
  }

  @SuppressWarnings("unchecked")
  public ArrayList threatendFieldsByWhite() throws Exception {/*unused*/

    return MoveUtil.threatendFieldsByActuallyAllowedMoves("white", squares);

  }

  @SuppressWarnings("unchecked")
  public ArrayList threatendFieldsByBlack() throws Exception {/*unused*/

    return MoveUtil.threatendFieldsByActuallyAllowedMoves("black", squares);

  }

  public Square getSquare(String nameInChessNotation){
    return squareMap.get(nameInChessNotation);
  }


  public Square getBlackKingSquare() {
    return blackKingSquare;
  }

  public Square getWhiteKingSquare() {
    return whiteKingSquare;
  }

  /**
   * adds the common chess starting Lineup to a Board
   *
   * @throws Exception
   */
  public void createInitialLineup() throws Exception {
Player black = game.getBlackPlayer();
Player white = game.getWhitePlayer();

    setPiece(getSquare("A8"), new Rook(black));
    setPiece(getSquare("B8"), new Knight(black));
    setPiece(getSquare("C8"), new Bishop(black));
    setPiece(getSquare("D8"), new Queen(black));
    setPiece(getSquare("E8"), new King(black));
    blackKingSquare = getSquare("E8");
    setPiece(getSquare("F8"), new Bishop(black));
    setPiece(getSquare("G8"), new Knight(black));
    setPiece(getSquare("H8"), new Rook(black));

    setPiece(getSquare("A7"), new Pawn(black));
    setPiece(getSquare("B7"), new Pawn(black));
    setPiece(getSquare("C7"), new Pawn(black));
    setPiece(getSquare("D7"), new Pawn(black));
    setPiece(getSquare("E7"), new Pawn(black));
    setPiece(getSquare("F7"), new Pawn(black));
    setPiece(getSquare("G7"), new Pawn(black));
    setPiece(getSquare("H7"), new Pawn(black));

    setPiece(getSquare("A1"), new Rook(white));
    setPiece(getSquare("B1"), new Knight(white));
    setPiece(getSquare("C1"), new Bishop(white));
    setPiece(getSquare("D1"), new Queen(white));
    setPiece(getSquare("E1"), new King(white));
    whiteKingSquare = getSquare("E1");
    setPiece(getSquare("F1"), new Bishop(white));
    setPiece(getSquare("G1"), new Knight(white));
    setPiece(getSquare("H1"), new Rook(white));

    setPiece(getSquare("A2"), new Pawn(white));
    setPiece(getSquare("B2"), new Pawn(white));
    setPiece(getSquare("C2"), new Pawn(white));
    setPiece(getSquare("D2"), new Pawn(white));
    setPiece(getSquare("E2"), new Pawn(white));
    setPiece(getSquare("F2"), new Pawn(white));
    setPiece(getSquare("G2"), new Pawn(white));
    setPiece(getSquare("H2"), new Pawn(white));
  }

  public void moveFigure(Square from, Square to) throws Exception {


    if (from.isEmpty()) {
      throw new Exception("Invalid Move: " + from.getChessNotation() + " is Empty");
    }

    if (MoveUtil.isValidMove(from, to, this)) {
      to.setPiece(from.getPiece());
      from.setEmpty();
      from.getPiece().setHasBeenMoved(true);
      game.increaseMovesCounter();
    } else {
      throw new Exception("Invalid Move: " + from.getChessNotation() + " -> " + to.getChessNotation());
    }
  }

  /**
   *
   * @param start
   * @param n
   * @param boardOrientation -1 for black player, 1 for white
   * @return
   */
  public Square getSquareNRowsAhead(Square start, int n, int boardOrientation) throws SquareNotFoundException {
    try {
      return squares[start.getRowIndex() + (boardOrientation * n)][start.getColumnIndex()];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new SquareNotFoundException();
    }
  }

  private Square getSquareNColumnsStraightToTheRight(Square start, int n, int boardOrientation) throws SquareNotFoundException {
    try {
      return squares[start.getRowIndex()][start.getColumnIndex() + (boardOrientation * n)];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new SquareNotFoundException();
    }
  }

  private Square getSquareNColumnsStraightToTheLeft(Square start, int n, int boardOrientation) throws SquareNotFoundException {
    try {
      return squares[start.getRowIndex()][start.getColumnIndex() - (boardOrientation * n)];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new SquareNotFoundException();
    }
  }

  private Square getSquareNRowsMColumnsAway(Square start, int n, int m, int boardOrientation) throws SquareNotFoundException {
    try {
      return squares[start.getRowIndex()+ (boardOrientation * n)][start.getColumnIndex() + (boardOrientation * m)];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new SquareNotFoundException();
    }
  }
}
