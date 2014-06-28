package model;

import model.pieces.*;
import sun.reflect.annotation.ExceptionProxy;
import utils.ChessNotationUtil;
import utils.MoveUtil;

import java.util.ArrayList;
import java.util.Collection;

public class Board {


  private int moveCounter = 0;
  private Square[][] squares;
  private Collection<Square> iterableSqaures;
  private ArrayList<Square> attackedByWhite;
  private ArrayList<Square> attackedByBlack;

  public Board() {
    initBoard();
  }

  private void initBoard() {
    this.squares = new Square[8][8];

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (squares[i][j] == null)
          squares[i][j] = new Square(i, j, null);
        iterableSqaures.add(squares[i][j]);
      }
    }
  }

  public void increaseMovesCounter() {
    moveCounter++;
  }

  public int getMovesCounter() {
    return moveCounter;
  }

  /**
   * marks every attacked field for both colours, regardless of the colour actually being allowed to move there.
   * i.e the white king on E3 and a black rook on D7. Although the king on E3 is not allowed to move on any D-Row field because of the rook on D7.
   * Nevertheless a black king on C3 would also not be allowed to move on any D-Row field because of the E3 King.
   * <p/>
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


  public void setPiece(int row, int column, Piece piece) throws Exception {
    this.squares[row][column].setPiece(piece);
  }

  public void setPiece(Square square, Piece piece) throws Exception {
    square.setPiece(piece);
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


  /**
   * adds the common chess starting Lineup to a Board
   *
   * @throws Exception
   */
  public void createInitialLineup() throws Exception {

    setPiece(0, 1, new Pawn(Player.SECOND.getId()));
    setPiece(1, 1, new Pawn(Player.SECOND.getId()));
    setPiece(2, 1, new Pawn(Player.SECOND.getId()));
    setPiece(3, 1, new Pawn(Player.SECOND.getId()));
    setPiece(4, 1, new Pawn(Player.SECOND.getId()));
    setPiece(5, 1, new Pawn(Player.SECOND.getId()));
    setPiece(6, 1, new Pawn(Player.SECOND.getId()));
    setPiece(7, 1, new Pawn(Player.SECOND.getId()));
    setPiece(0, 0, new Rook(Player.SECOND.getId()));
    setPiece(1, 0, new Knight(Player.SECOND.getId()));
    setPiece(2, 0, new Bishop(Player.SECOND.getId()));
    setPiece(3, 0, new Queen(Player.SECOND.getId()));
    setPiece(4, 0, new King(Player.SECOND.getId()));
    setPiece(5, 0, new Bishop(Player.SECOND.getId()));
    setPiece(6, 0, new Knight(Player.SECOND.getId()));
    setPiece(7, 0, new Rook(Player.SECOND.getId()));


    setPiece(0, 6, new Pawn(Player.FIRST.getId()));
    setPiece(1, 6, new Pawn(Player.FIRST.getId()));
    setPiece(2, 6, new Pawn(Player.FIRST.getId()));
    setPiece(3, 6, new Pawn(Player.FIRST.getId()));
    setPiece(4, 6, new Pawn(Player.FIRST.getId()));
    setPiece(5, 6, new Pawn(Player.FIRST.getId()));
    setPiece(6, 6, new Pawn(Player.FIRST.getId()));
    setPiece(7, 6, new Pawn(Player.FIRST.getId()));
    setPiece(0, 7, new Rook(Player.FIRST.getId()));
    setPiece(1, 7, new Knight(Player.FIRST.getId()));
    setPiece(2, 7, new Bishop(Player.FIRST.getId()));
    setPiece(3, 7, new Queen(Player.FIRST.getId()));
    setPiece(4, 7, new King(Player.FIRST.getId()));
    setPiece(5, 7, new Bishop(Player.FIRST.getId()));
    setPiece(6, 7, new Knight(Player.FIRST.getId()));
    setPiece(7, 7, new Rook(Player.SECOND.getId()));
  }

  public void moveFigure(Square from, Square to) throws Exception {


    if (from.isEmpty()) {
      throw new Exception("Invalid Move: " + from.getChessNotation()+" is Empty");
    }

    if (MoveUtil.isValidMove(from, to, this)) {
      to.setPiece(from.getPiece());
      from.setEmpty();
      from.getPiece().setHasBeenMoved(true);
      increaseMovesCounter();
    } else {
      throw new Exception("Invalid Move: " + from.getChessNotation() +" -> "+to.getChessNotation());
    }
  }
}
