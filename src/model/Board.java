package model;

import model.pieces.*;
import utils.ChessNotationUtil;
import utils.MoveUtil;

import java.util.ArrayList;

public class Board {


  private int moveCounter = 0;
  private Square[][] squares;
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
    ArrayList fieldWhite = MoveUtil.threatenedFieldsByColourConsideringEveryAttackedField("white", squares);
    ArrayList fieldBlack = MoveUtil.threatenedFieldsByColourConsideringEveryAttackedField("black", squares);
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (fieldWhite.contains(squares[i][j]))
          squares[i][j].setThreatendByWhite(true);
        else
          squares[i][j].setThreatendByWhite(false);

        if (fieldBlack.contains(squares[i][j]))
          squares[i][j].setThreatendByBlack(true);
        else
          squares[i][j].setThreatendByBlack(false);
      }
    }
  }


  public void addFigure(int row, int column, Piece piece) throws Exception {
    this.squares[row][column].setPiece(piece);
  }

  public void addFigureInterface(String field, Piece piece)
          throws Exception {
    int[] fieldArray = ChessNotationUtil.convertFieldNameToIndexes(field);
    this.squares[fieldArray[0]][fieldArray[1]] = new Square(fieldArray[0], fieldArray[1], piece);
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
    if (!this.squares[fieldArray[0]][fieldArray[1]].getEmpty())
      this.squares[fieldArray[0]][fieldArray[1]].setEmpty();
    else
      System.out
              .println("	No figure on " + field + ".\n 	Therefore no figure has been removed from "
                      + field + "\n\n");
  }

  public Square[][] getFieldArray() {
    return this.squares;
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

    addFigure(0, 1, new Pawn(Player.SECOND.getId()));
    addFigure(1, 1, new Pawn(Player.SECOND.getId()));
    addFigure(2, 1, new Pawn(Player.SECOND.getId()));
    addFigure(3, 1, new Pawn(Player.SECOND.getId()));
    addFigure(4, 1, new Pawn(Player.SECOND.getId()));
    addFigure(5, 1, new Pawn(Player.SECOND.getId()));
    addFigure(6, 1, new Pawn(Player.SECOND.getId()));
    addFigure(7, 1, new Pawn(Player.SECOND.getId()));
    addFigure(0, 0, new Rook(Player.SECOND.getId()));
    addFigure(1, 0, new Knight(Player.SECOND.getId()));
    addFigure(2, 0, new Bishop(Player.SECOND.getId()));
    addFigure(3, 0, new Queen(Player.SECOND.getId()));
    addFigure(4, 0, new King(Player.SECOND.getId()));
    addFigure(5, 0, new Bishop(Player.SECOND.getId()));
    addFigure(6, 0, new Knight(Player.SECOND.getId()));
    addFigure(7, 0, new Rook(Player.SECOND.getId()));


    addFigure(0, 6, new Pawn(Player.FIRST.getId()));
    addFigure(1, 6, new Pawn(Player.FIRST.getId()));
    addFigure(2, 6, new Pawn(Player.FIRST.getId()));
    addFigure(3, 6, new Pawn(Player.FIRST.getId()));
    addFigure(4, 6, new Pawn(Player.FIRST.getId()));
    addFigure(5, 6, new Pawn(Player.FIRST.getId()));
    addFigure(6, 6, new Pawn(Player.FIRST.getId()));
    addFigure(7, 6, new Pawn(Player.FIRST.getId()));
    addFigure(0, 7, new Rook(Player.FIRST.getId()));
    addFigure(1, 7, new Knight(Player.FIRST.getId()));
    addFigure(2, 7, new Bishop(Player.FIRST.getId()));
    addFigure(3, 7, new Queen(Player.FIRST.getId()));
    addFigure(4, 7, new King(Player.FIRST.getId()));
    addFigure(5, 7, new Bishop(Player.FIRST.getId()));
    addFigure(6, 7, new Knight(Player.FIRST.getId()));
    addFigure(7, 7, new Rook(Player.SECOND.getId()));
  }

  public void moveFigure(String startingField, String destinationField)
          throws Exception {
    int[] start = ChessNotationUtil.convertFieldNameToIndexes(startingField);
    int[] destination = ChessNotationUtil.convertFieldNameToIndexes(destinationField);
    if (squares[start[0]][start[1]].getEmpty()) {
      System.out.println("	Invalid Move: " + startingField
              + " is Empty.\n        <No changes have been made>");
    } else if (MoveUtil.ValidMoveByMovementRules(start[0], start[1],
            destination[0], destination[1], this)) {
      this.addFigure(destination[0], destination[1],
              this.squares[start[0]][start[1]].getPiece().getType(),
              this.squares[start[0]][start[1]].getPiece().getColour());
      this.squares[start[0]][start[1]].setEmpty();
      increaseMovesCounter();
      if (!this.squares[destination[0]][destination[1]].getPiece().getHasBeenMoved())
        this.squares[destination[0]][destination[1]].getPiece().setHasBeenMoved(true);
    } else {
      System.out.println("	Invalid Move: The "
              + this.squares[start[0]][start[1]].getPiece().getColour()
              + " "
              + this.squares[start[0]][start[1]].getPiece().getType()
              + " on " + startingField + " is not allowed to move to "
              + destinationField);
      System.out.println("	  <No changes have been made>");
    }
  }
}