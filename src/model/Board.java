package model;

import exceptions.SquareNotFoundException;
import model.pieces.*;
import utils.BoardUtil;
import utils.ChessNotationUtil;

import java.util.*;

public class Board {


  private Game game;
  private Square[][] squares;
  private Collection<Square> iterableSqaures = new HashSet<Square>();
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

  public void setPiece(Square square, Piece piece) throws Exception {
    square.setPiece(piece);
    piece.setCurrentPositionOnBoard(square);
  }

  public Square[][] getSquares() {
    return this.squares;
  }

  public Square getSquare(int row, int col) {
    return this.squares[row][col];
  }

  public Square getSquare(String nameInChessNotation) {
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

    if (BoardUtil.isValidMove(from, to)) {
      to.setPiece(from.getPiece());
      from.setEmpty();
      from.getPiece().setHasBeenMoved(true);
      game.increaseMovesCounter();
    } else {
      throw new Exception("Invalid Move: " + from.getChessNotation() + " -> " + to.getChessNotation());
    }
  }

  public List<Square> getSquaresInStraightLineBetween(Square from, Square to){

    List<Square> squares = new ArrayList<>();
    if(from != to){
      return squares;
    }

    //TODO add logic

    return squares;

  }

  /**
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

  public Square getSquareNColumnsStraightToTheRight(Square start, int n, int boardOrientation) throws SquareNotFoundException {
    try {
      return squares[start.getRowIndex()][start.getColumnIndex() + (boardOrientation * n)];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new SquareNotFoundException();
    }
  }

  public Square getSquareNColumnsStraightToTheLeft(Square start, int n, int boardOrientation) throws SquareNotFoundException {
    try {
      return squares[start.getRowIndex()][start.getColumnIndex() - (boardOrientation * n)];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new SquareNotFoundException();
    }
  }

  public Square getSquareNRowsMColumnsAway(Square start, int n, int m, int boardOrientation) throws SquareNotFoundException {
    try {
      return squares[start.getRowIndex() + (boardOrientation * n)][start.getColumnIndex() + (boardOrientation * m)];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new SquareNotFoundException();
    }
  }

  public boolean isSquareAttackedByOpponentOf(Square to, Player player) {
    return player.isWhite() ? attackedByBlack.contains(to) : attackedByWhite.contains(to);
  }
}
