package model;

import chess_game.PrintPlayingField;
import logic.Moves;
import model.pieces.*;
import utils.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"unused", "serial"})
public class Game implements Serializable {

  private boolean forceMoveOrder = true;
  private boolean fieldManipulationForbidden = true;
  private boolean whiteTurn = true;
  private Board board;
  private String log = "";

  public Game(Boolean startingLineUp, Boolean fieldManipulationForbidden,
              Boolean forceMoveOrder) throws Exception {
    if (startingLineUp) {
      this.getBoard().createInitialLineup();
    }

    this.board = new Board();
    this.fieldManipulationForbidden = fieldManipulationForbidden;
    this.forceMoveOrder = forceMoveOrder;
    this.getBoard().markAttackedFields();
  }

  public void addToLog(String move) {
    log = log + move;
  }

  public void clearLog() {
    log = "";
  }

  public String getLog() {
    return log;
  }

  /**
   * Adds a Figure of "type" and "colour" at "board" .
   * <p/>
   * if FieldManipulation, which is determined by the boolean "fieldManipulationForbidden", is allowed.
   *
   * @throws Exception
   */
  public void addFigure(Square square, Piece piece) throws Exception {
    if (!fieldManipulationForbidden) {
      square.setPiece(piece);
      this.getBoard().markAttackedFields();
    } else {
      throw new Exception("Fieldmanipulation is not allowed.");
    }
  }

  public boolean isCastlingOnWhiteKingsideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    ArrayList threatenedFieldsByBlack = MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck("black", square);
    return square[5][7].isEmpty()
            && square[6][7].isEmpty()
            && !square[7][7].isEmpty()
            && !square[4][7].isEmpty()
            && !(threatenedFieldsByBlack.contains(square[5][7]))
            && !(threatenedFieldsByBlack.contains(square[6][7]))
            && !(GameUtil.isGivenColourCheck("white", board))
            && !(square[7][7].getPiece().hasBeenMoved())
            && !(square[4][7].getPiece().hasBeenMoved());

  }

  public boolean isCastlingOnWhiteQueensideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    ArrayList threatenedFieldsByBlack = MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck("black", square);
    return square[1][7].isEmpty()
            && square[2][7].isEmpty()
            && square[3][7].isEmpty()
            && !square[0][7].isEmpty()
            && !square[4][7].isEmpty()
            && !(threatenedFieldsByBlack.contains(square[1][7]))
            && !(threatenedFieldsByBlack.contains(square[2][7]))
            && !(threatenedFieldsByBlack.contains(square[3][7]))
            && !(GameUtil.isGivenColourCheck("white", this.getBoard()))
            && !(square[0][7].getPiece().hasBeenMoved())
            && !(square[4][7].getPiece().hasBeenMoved());

  }

  public boolean isCastlingOnBlackKingsideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    ArrayList threatenedFieldsByWhite = MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck("white", square);

    return square[5][0].isEmpty()
            && square[6][0].isEmpty()
            && !square[7][0].isEmpty()
            && !square[4][0].isEmpty()
            && !(threatenedFieldsByWhite.contains(square[5][0]))
            && !(threatenedFieldsByWhite.contains(square[6][0]))
            && !(GameUtil.isGivenColourCheck("black", this.getBoard()))
            && !(square[7][0].getPiece().hasBeenMoved())
            && !(square[4][0].getPiece().hasBeenMoved());

  }

  public boolean isCastlingOnBlackQueensideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    ArrayList threatenedFieldsByWhite = MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck("white", square);

    return square[1][0].isEmpty()
            && square[2][0].isEmpty()
            && square[3][0].isEmpty()
            && !square[0][0].isEmpty()
            && !square[4][0].isEmpty()
            && !(threatenedFieldsByWhite.contains(square[1][0]))
            && !(threatenedFieldsByWhite.contains(square[2][0]))
            && !(threatenedFieldsByWhite.contains(square[3][0]))
            && !(GameUtil.isGivenColourCheck("black", this.getBoard()))
            && !(square[0][0].getPiece().hasBeenMoved())
            && !(square[4][0].getPiece().hasBeenMoved());

  }

  public void castleOnBlackQueenside() throws Exception {
    if (isCastlingOnBlackQueensideAllowed()) {
      Square c8 = getSquareByChessNotation("C8");
      Square e8 = getSquareByChessNotation("E8");
      Square d8 = getSquareByChessNotation("D8");
      Square a8 = getSquareByChessNotation("A8");

      movePiece(e8, c8);
      movePiece(a8, d8);

      whiteTurn = !whiteTurn;
      printPlayingField();
    }
  }

  public void castleOnWhiteQueenside() throws Exception {
    if (isCastlingOnWhiteQueensideAllowed()){
      Square c1 = getSquareByChessNotation("C1");
      Square e1 = getSquareByChessNotation("E1");
      Square d1 = getSquareByChessNotation("D1");
      Square a1 = getSquareByChessNotation("A1");

      movePiece(e1, c1);
      movePiece(a1, d1);

      whiteTurn = !whiteTurn;
      printPlayingField();
    }
  }

  public void castleOnBlackKingside() throws Exception {
    if (isCastlingOnBlackKingsideAllowed()) {
      Square e8 = getSquareByChessNotation("E8");
      Square f8 = getSquareByChessNotation("F8");
      Square g8 = getSquareByChessNotation("G8");
      Square h8 = getSquareByChessNotation("H8");

      movePiece(e8, g8);
      movePiece(h8, f8);

      whiteTurn = !whiteTurn;
      printPlayingField();
    }
  }

  public void castleOnWhiteKingside() throws Exception {
    if(isCastlingOnWhiteKingsideAllowed()){

      Square g1 = getSquareByChessNotation("G1");
      Square e1 = getSquareByChessNotation("E1");
      Square h1 = getSquareByChessNotation("H1");
      Square f1 = getSquareByChessNotation("F1");

      movePiece(h1, f1);
      movePiece(e1, g1);

      whiteTurn = !whiteTurn;
      printPlayingField();
    }
  }

  /**
   * Counts the Figures currently in the Game
   *
   * @return
   */
  public int countFigures() {
    Square[][] squareArray = this.board.getSquares();
    int counter = 0;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (!squareArray[i][j].isEmpty())
          counter++;
      }
    }
    return counter;
  }

  /**
   * Creates a random Game Situation with a default adding Probability of 0.3.
   * <p/>
   * Nevertheless it is ensured that a King of every Colour exists.
   *
   * @throws Exception if the second king is random on the firsts King board.
   */
  public void createRandomSituation(double probability) throws Exception {

    Random r = new Random();
    int rowKingWhite = r.nextInt(8);
    int rowKingBlack = r.nextInt(8);
    int columnKingWhite = r.nextInt(8);
    int columnKingBlack = r.nextInt(8);

    Square squareWhite = board.getSquare(rowKingWhite, columnKingWhite);
    Square squareBlack = board.getSquare(rowKingBlack, columnKingBlack);

    squareWhite.setPiece(new King(Player.FIRST.getId()));
    squareBlack.setPiece(new King(Player.SECOND.getId()));


    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        Square tempSquare = board.getSquare(i, j);
        if (Math.random() < probability && tempSquare.isEmpty()) {
          if (Math.random() < 0.5) {
            if (Math.random() < 0.4)
              tempSquare.setPiece(new Pawn(Player.FIRST.getId()));
            else if (Math.random() < 0.55)
              tempSquare.setPiece(new Rook(Player.FIRST.getId()));
            else if (Math.random() < 0.7)
              tempSquare.setPiece(new Bishop(Player.FIRST.getId()));
            else if (Math.random() < 0.85)
              tempSquare.setPiece(new Knight(Player.FIRST.getId()));
            else if (Math.random() < 1)
              tempSquare.setPiece(new Queen(Player.FIRST.getId()));
          } else {
            if (Math.random() < 0.4)
              tempSquare.setPiece(new Pawn(Player.SECOND.getId()));
            else if (Math.random() < 0.55)
              tempSquare.setPiece(new Rook(Player.SECOND.getId()));
            else if (Math.random() < 0.7)
              tempSquare.setPiece(new Bishop(Player.SECOND.getId()));
            else if (Math.random() < 0.85)
              tempSquare.setPiece(new Knight(Player.SECOND.getId()));
            else if (Math.random() < 1)
              tempSquare.setPiece(new Queen(Player.SECOND.getId()));
          }
        }
      }
    }
    this.getBoard().markAttackedFields();
  }

  /**
   * Creates a random Game Situation with a given adding Probability.
   *
   * @throws Exception
   */
  public void createRandomSituation() throws Exception {
    createRandomSituation(0.3);
  }

  /**
   * Returns the Board of the Current Game.
   *
   * @return
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Returns if Fieldmanipulation is allowed.
   *
   * @return
   */
  public boolean isFieldManipulationForbidden() {
    return fieldManipulationForbidden;
  }

  public Square getSquareByChessNotation(String notation) throws Exception {
    int[] indexes = ChessNotationUtil.convertFieldNameToIndexes(notation);
    return board.getSquare(indexes[0], indexes[1]);
  }

  /**
   * Returns if Forced Move Order is on or off.
   *
   * @return
   */
  public boolean isForceMoveOrder() {
    return forceMoveOrder;
  }

  /**
   * Enables or disables the Forced Move Order
   *
   * @param forceMoveOrder
   */
  public void setForceMoveOrder(boolean forceMoveOrder) {
    this.forceMoveOrder = forceMoveOrder;
  }

  /**
   * Loads a saved Game Situation. NOT WORKING YET.
   *
   * @param filename
   * @return
   * @throws Exception
   */
  public Game loadSituation(String filename) throws Exception { //buggy
    Game loadedGame = this;
    try {

      loadedGame = PersistenceUtil.loadGame(filename);

    } catch (Exception e) {
    }
    loadedGame.printPlayingField();
    return loadedGame;

  }

  /**
   * Moves the figure on startingfield to destinationfield
   * <p/>
   * Precondition:
   * destinationfield is not empty and Move has been validated inside movePiece method
   *
   * @param from
   * @param to
   * @throws Exception
   */
  public void movePiece(Square from, Square to) throws Exception {
    if (from.isEmpty()) {
      throw new Exception("No figure to be moved on " + from.getChessNotation());
    }

    if (!forceMoveOrder || from.getPiece().getPlayer().isWhite() == whiteTurn) {
      board.moveFigure(from, to);
      whiteTurn = !whiteTurn;
      throw new Exception("Its not your turn.");
    }
    this.getBoard().markAttackedFields();
  }

  /**
   * Performs random Moves until only the two Kings are left.
   *
   * @throws Exception
   */
  public void moveRandom() throws Exception {
    String turn = "white";

    Random r = new Random();
    while (this.countFigures() > 2) {
      int fromRowIndex = r.nextInt(8);
      int fromColumnIndex = r.nextInt(8);
      int toRowIndex = r.nextInt(8);
      int toColumnIndex = r.nextInt(8);

      Square from = board.getSquare(fromRowIndex, fromColumnIndex);
      Square to = board.getSquare(toRowIndex, toColumnIndex);
      if (!from.isEmpty() && from.getPiece().getPlayer().isWhite() == whiteTurn && (MoveUtil.isValidMove(from, to, board))) {
        movePiece(from, to);
        whiteTurn = !whiteTurn;
      }
    }
  }

  /**
   * Performs random Moves until the given Count is reached.
   *
   * @param count
   * @throws Exception
   */
  public void moveRandom(int count) throws Exception {
    for (int k = 0; k <= count; k++) {
      moveRandom();
    }
  }

  public String[] randomMove() {
    String[] moves = new String[2];

    int rowStart = (int) (Math.random() * 10000 % 8);
    int columnStart = (int) (Math.random() * 10000 % 8);
    int rowDestination = (int) (Math.random() * 10000 % 8);
    int columnDestination = (int) (Math.random() * 10000 % 8);

    char rowCharStart = 'A';
    char rowCharDestination = 'B';

    if (rowDestination == 0)
      rowCharDestination = 'A';
    else if (rowDestination == 7)
      rowCharDestination = 'H';
    else if (rowDestination == 6)
      rowCharDestination = 'G';
    else if (rowDestination == 5)
      rowCharDestination = 'F';
    else if (rowDestination == 4)
      rowCharDestination = 'E';
    else if (rowDestination == 3)
      rowCharDestination = 'D';
    else if (rowDestination == 2)
      rowCharDestination = 'C';
    else if (rowDestination == 1)
      rowCharDestination = 'B';

    if (rowStart == 0)
      rowCharStart = 'A';
    else if (rowStart == 7)
      rowCharStart = 'H';
    else if (rowStart == 6)
      rowCharStart = 'G';
    else if (rowStart == 5)
      rowCharStart = 'F';
    else if (rowStart == 4)
      rowCharStart = 'E';
    else if (rowStart == 3)
      rowCharStart = 'D';
    else if (rowStart == 2)
      rowCharStart = 'C';
    else if (rowStart == 1)
      rowCharStart = 'B';

    columnStart++;
    columnDestination++;


    moves[0] = ("" + rowCharStart + "" + columnStart);
    moves[1] = ("" + rowCharDestination + "" + columnDestination);
    return moves;

  }

  /**
   * Moves Figures depending on moveManually
   * <p/>
   * if moveManually is true, white Moves are determined by the User instead of being randomed
   * else every Move is random
   *
   * @throws Exception
   */
  public void moveRandomConfirmMoves(boolean moveManually, boolean mainMenu) throws Exception {
    String turn = "white";
    printPlayingField();

    while (this.countFigures() > 2) {
      int rowStart = (int) (Math.random() * 10000 % 8);
      int columnStart = (int) (Math.random() * 10000 % 8);
      int rowDestination = (int) (Math.random() * 10000 % 8);
      int columnDestination = (int) (Math.random() * 10000 % 8);

      char rowCharStart = 'A';
      char rowCharDestination = 'B';

      if (rowDestination == 0)
        rowCharDestination = 'A';
      else if (rowDestination == 7)
        rowCharDestination = 'H';
      else if (rowDestination == 6)
        rowCharDestination = 'G';
      else if (rowDestination == 5)
        rowCharDestination = 'F';
      else if (rowDestination == 4)
        rowCharDestination = 'E';
      else if (rowDestination == 3)
        rowCharDestination = 'D';
      else if (rowDestination == 2)
        rowCharDestination = 'C';
      else if (rowDestination == 1)
        rowCharDestination = 'B';

      if (rowStart == 0)
        rowCharStart = 'A';
      else if (rowStart == 7)
        rowCharStart = 'H';
      else if (rowStart == 6)
        rowCharStart = 'G';
      else if (rowStart == 5)
        rowCharStart = 'F';
      else if (rowStart == 4)
        rowCharStart = 'E';
      else if (rowStart == 3)
        rowCharStart = 'D';
      else if (rowStart == 2)
        rowCharStart = 'C';
      else if (rowStart == 1)
        rowCharStart = 'B';

      columnStart++;
      columnDestination++;


      String start = ("" + rowCharStart + "" + columnStart);
      String destination = ("" + rowCharDestination + "" + columnDestination);

      int[] startArray = ChessNotationUtil.convertFieldNameToIndexes(start);
      int[] destinationArray = ChessNotationUtil
              .convertFieldNameToIndexes(destination);

      if (GameUtil.isCheckMate("white", this.board) || GameUtil.isCheckMate("black", this.board)) {
        printPlayingField();
        break;
      }
      if (moveManually) {
        if (turn.equals("white")) {
          //printPlayingField();
          while (true) {
            if (mainMenu) {
              this.mainMenu();
            }
            Scanner scanner = new Scanner(System.in);
            Pattern pattern = Pattern.compile("[a-hA-H][1-8]");

            System.out.print("	Please Insert Starting Field Of Your Next Move: ");
            String startField = scanner.next();
            Matcher startMatcher = pattern.matcher(startField);
            boolean startMatches = startMatcher.matches();
            if (!startMatches) {
              System.out.println("\n	Failure. This is an invalid Input. Please insert again: \n");
              continue;
            }
            System.out.print("	Please Insert Destination Field Of Your Next Move: ");
            String destinationField = scanner.next();
            Matcher destinationMatcher = pattern.matcher(destinationField);
            boolean destinationMatches = destinationMatcher.matches();
            if (!destinationMatches) {
              System.out.println("\n	Failure. This is an invalid Input. Please insert again: \n");
              continue;
            }

            int[] startingFieldHelp = ChessNotationUtil
                    .convertFieldNameToIndexes(startField);
            int[] destinationFieldHelp = ChessNotationUtil
                    .convertFieldNameToIndexes(destinationField);

            if (!(this.board.getSquares()[startingFieldHelp[0]][startingFieldHelp[1]].isEmpty()) && MoveUtil.isValidMove(startingFieldHelp[0], startingFieldHelp[1], destinationFieldHelp[0], destinationFieldHelp[1], this.board)) {

              Square startSquare_2 = this.board.getSquares()[startingFieldHelp[0]][startingFieldHelp[1]];
              String colourStart = startSquare_2.getPiece().getColour();
              String typeStart = startSquare_2.getPiece().getType();
              Square destinationSquare_2 = this.board.getSquares()[destinationFieldHelp[0]][destinationFieldHelp[1]];
              movePiece(startField, destinationField);
              System.out.println("	" + startField + " -> " + destinationField + " (" + colourStart + " " + typeStart + ")");
              turn = "black";
              break;
            } else
              System.out.println("\n	Failure. This is an invalid Move. Please insert again: \n");
          }

        } else {
          while (true) {

            rowStart = (int) (Math.random() * 10000 % 8);
            columnStart = (int) (Math.random() * 10000 % 8);
            rowDestination = (int) (Math.random() * 10000 % 8);
            columnDestination = (int) (Math.random() * 10000 % 8);

            rowCharStart = 'A';
            rowCharDestination = 'B';

            if (rowDestination == 0)
              rowCharDestination = 'A';
            else if (rowDestination == 7)
              rowCharDestination = 'H';
            else if (rowDestination == 6)
              rowCharDestination = 'G';
            else if (rowDestination == 5)
              rowCharDestination = 'F';
            else if (rowDestination == 4)
              rowCharDestination = 'E';
            else if (rowDestination == 3)
              rowCharDestination = 'D';
            else if (rowDestination == 2)
              rowCharDestination = 'C';
            else if (rowDestination == 1)
              rowCharDestination = 'B';

            if (rowStart == 0)
              rowCharStart = 'A';
            else if (rowStart == 7)
              rowCharStart = 'H';
            else if (rowStart == 6)
              rowCharStart = 'G';
            else if (rowStart == 5)
              rowCharStart = 'F';
            else if (rowStart == 4)
              rowCharStart = 'E';
            else if (rowStart == 3)
              rowCharStart = 'D';
            else if (rowStart == 2)
              rowCharStart = 'C';
            else if (rowStart == 1)
              rowCharStart = 'B';

            columnStart++;
            columnDestination++;


            start = ("" + rowCharStart + "" + columnStart);
            destination = ("" + rowCharDestination + "" + columnDestination);

            startArray = ChessNotationUtil.convertFieldNameToIndexes(start);
            destinationArray = ChessNotationUtil
                    .convertFieldNameToIndexes(destination);

            if (!(board.getSquares()[rowStart][8 - columnStart].isEmpty()) && (board.getSquares()[rowStart][8 - columnStart].getPiece().getColour().equals(turn)) && (MoveUtil.isValidMove(startArray[0], startArray[1], destinationArray[0], destinationArray[1], board))) {
              movePiece(start, destination);
              System.out.println("	" + start + " -> " + destination + " (" + board.getSquares()[rowDestination][8 - columnDestination].getPiece().getColour() + " " + board.getSquares()[rowDestination][8 - columnDestination].getPiece().getType() + ")\n\n");
              turn = "white";
              break;
            }
          }

        }

      } else {

        if (!(board.getSquares()[rowStart][8 - columnStart].isEmpty()) && (board.getSquares()[rowStart][8 - columnStart].getPiece().getColour().equals(turn)) && (MoveUtil.isValidMove(startArray[0], startArray[1], destinationArray[0], destinationArray[1], board))) {


          movePiece(start, destination);
          System.out.println("	" + start + " -> " + destination + " (" + board.getSquares()[rowDestination][8 - columnDestination].getPiece().getColour() + " " + board.getSquares()[rowDestination][8 - columnDestination].getPiece().getType() + ")\n\n");

          Scanner scanner = new Scanner(System.in);
          String s = scanner.nextLine();


          if (turn.equals("white"))
            turn = "black";
          else
            turn = "white";
        }

      }
    }
  }

  /**
   * UNUSED
   * Prints all allowed moves for a Colour.
   * <p/>
   * Does NOT consider the fact, that at checked Situation only Moves protecting the King are allowed
   *
   * @param colour "white" or "black"
   * @throws Exception
   */
  public void printAllAllowedMovesByColour(String colour) throws Exception {
    OutputUtil.printAllAllowedMovesByColour(this.getBoard(), colour);
  }

  /**
   * Prints all allowed Moves for a Colour at a Situation, where the given Colour is not check.
   *
   * @param colour
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public void printAllMovesForColourNotCheck(String colour) throws Exception {
    ArrayList moves = MoveUtil.moveArrayByColour(this.board, colour);
    System.out.println("\n=========================================================================");
    System.out.println("\n	A List Of Allowed Moves (" + moves.size() + "):\n");
    for (int i = 0; i < moves.size(); i++) {
      Move currentMove = (Move) moves.get(i);
      System.out.println("	" + currentMove.getStartSquare().getCharRow() + "" + currentMove.getStartSquare().getIntColumn() + " -> " + currentMove.getDestinationSquare().getCharRow() + "" + currentMove.getDestinationSquare().getIntColumn() + " (" + currentMove.getStartSquare().getType() + ")");
    }
  }

  @SuppressWarnings("unchecked")
  public String printAllMovesForColourNotCheckToString(String colour) throws Exception {
    String movesString = "";
    ArrayList moves = MoveUtil.moveArrayByColour(this.board, colour);
    //movesString = movesString+("\n=========================================================================\n\n");
    //movesString = movesString+("A List Of Allowed Moves ("+moves.size()+"):\n\n");
    for (int i = 0; i < moves.size(); i++) {
      Move currentMove = (Move) moves.get(i);
      movesString = movesString + ("" + currentMove.getStartSquare().getCharRow() + "" + currentMove.getStartSquare().getIntColumn() + " -> " + currentMove.getDestinationSquare().getCharRow() + "" + currentMove.getDestinationSquare().getIntColumn() + " (" + currentMove.getStartSquare().getType() + ")\n");
    }
    return movesString;
  }

  /**
   * UNUSED
   * <p/>
   * Prints every Field between the given board and the enemy King
   * <p/>
   * Works for Bishop, Rook and Queen.
   *
   * @param field
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public void printFieldsBetween(String field) throws Exception {
    Square enemySquare = this.getBoard().getSquares()[ChessNotationUtil.convertFieldNameToIndexes(field)[0]][ChessNotationUtil.convertFieldNameToIndexes(field)[1]];
    ArrayList fields = BoardUtil.listOfFieldsBetweenAttackerAndKing(enemySquare, this.getBoard());
    for (int i = 0; i < fields.size(); i++) {
      Square current = (Square) fields.get(i);
      System.out.println(current.getCharRow() + " " + current.getIntColumn());
    }
  }

  /**
   * Prints if the current colour is check.
   *
   * @param colour
   * @throws Exception
   */
  public void printIsCheck(String colour) throws Exception {
    OutputUtil.printIsCheck(colour, this.getBoard());
  }

  /**
   * UNUSED
   * Prints if the current colour is check mate
   *
   * @param colour
   * @throws Exception
   */
  public void printIsCheckMate(String colour) throws Exception {
    OutputUtil.printIsCheckMate(colour, this.board);
  }

  /**
   * Prints a list of all Fields a Colour is actually allowed to move.
   *
   * @param colour
   * @throws Exception
   */
  public void printListOfActuallyAllowedFieldsToMoveByColour(String colour)
          throws Exception {
    Moves.printListOfActuallyAllowedFieldsToMoveByColour(colour, this.getBoard()
            .getSquares());
  }

  /**
   * Prints a List of all Fields a Figure is actually allowed to move.
   *
   * @param field
   * @throws Exception
   */
  public void printListOfActuallyAllowedFieldsToMoveByFigure(String field) throws Exception {

    int[] fieldHelp = ChessNotationUtil.convertFieldNameToIndexes(field);
    int row = fieldHelp[0];
    int column = fieldHelp[1];
    Square[][] squareArray = this.getBoard().getSquares();
    System.out
            .println("\n=========================================================================\n");
    if (!squareArray[row][column].isEmpty())
      Moves.printListOfActuallyAllowedFieldsToMoveByFigure(field, squareArray);
    else {
      System.out.println("	No figure on " + field
              + ". Therefore no possible moves could be printed");
    }


  }

  /**
   * Prints a List of Figures giving check to the given Colour.
   *
   * @param colour
   * @throws Exception
   */
  public void printListOfFiguresGivingCheckToColour(String colour)
          throws Exception {
    printPlayingField();
    OutputUtil.printListOfFiguresGivingCheckToColour(colour, this.getBoard());
  }

  /**
   * Prints all actually allowed Moves for the given Colour.
   * <p/>
   * Considers if the King is check.
   *
   * @param colour
   * @throws Exception
   */
  public void printListOfMovesAllowedForColour(String colour) throws Exception {
    if (GameUtil.isGivenColourCheck(colour, this.board)) {
      printMovesAllowedAtACheckedSituation(colour);
    } else {
      printAllMovesForColourNotCheck(colour);
    }

  }

  public String printListOfMovesAllowedForColourToString(String colour) throws Exception {
    String moves = "";
    if (GameUtil.isGivenColourCheck(colour, this.board)) {
      moves = moves + printMovesAllowedAtACheckedSituationToString(colour);
    } else {
      moves = moves + printAllMovesForColourNotCheckToString(colour);
    }

    return moves;
  }

  /**
   * UNUSED
   * Prints a List of Moves that block the Attacking Line of an attacking Rook, Bishop or Queen.
   *
   * @param attacker
   * @throws Exception
   */
  public void printListOfMovesBlockingAnAttackersLine(String attacker) throws Exception {

    int[] array = ChessNotationUtil.convertFieldNameToIndexes(attacker);
    Square attackerSquare = this.board.getSquares()[array[0]][array[1]];
    OutputUtil.printMovesToAvoidCheckMateByBlockingAttackersPath(attackerSquare, this.board);
  }

  /**
   * Prints a List of Moves that are allowed at a Check-Situation
   *
   * @param colour
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public void printMovesAllowedAtACheckedSituation(String colour) throws Exception {
    ArrayList moves = MoveUtil.movesAllowedAtACheckedSituation(colour, this.board);
    System.out.println("\n=========================================================================");
    System.out.println("\n	A List Of Allowed Moves for " + colour + " (" + moves.size() + "):\n\n");
    System.out.println("	Note:");
    System.out.println("	You Are Check, Therefore Your Allowed Moves Are Limited To The Following:\n");
    for (int i = 0; i < moves.size(); i++) {
      Move currentMove = (Move) moves.get(i);
      Square currentStart = currentMove.getStartSquare();
      Square currentDestination = currentMove.getDestinationSquare();

      System.out.println("" + currentStart.getCharRow() + "" + currentStart.getIntColumn() + " -> " + currentDestination.getCharRow() + "" + currentDestination.getIntColumn() + " (" + currentStart.getType() + ")");
    }
  }

  @SuppressWarnings("unchecked")
  public String printMovesAllowedAtACheckedSituationToString(String colour) throws Exception {
    String movesString = "";
    ArrayList moves = MoveUtil.movesAllowedAtACheckedSituation(colour, this.board);
    //movesString = movesString+("\n=========================================================================\n");
    //movesString = movesString+("A List Of Allowed Moves for "+colour+" ("+moves.size()+"):\n\n");
    //movesString = movesString+("Note:\n");
    movesString = movesString + ("You Are Check!\nTherefore your allowed\nMoves are limited to:\n\n");
    for (int i = 0; i < moves.size(); i++) {
      Move currentMove = (Move) moves.get(i);
      Square currentStart = currentMove.getStartSquare();
      Square currentDestination = currentMove.getDestinationSquare();

      movesString = movesString + ("" + currentStart.getCharRow() + "" + currentStart.getIntColumn() + " -> " + currentDestination.getCharRow() + "" + currentDestination.getIntColumn() + " (" + currentStart.getType() + ")\n");
    }
    return movesString;
  }

  /**
   * Prints the Playingfield at the current Situation.
   *
   * @throws Exception
   */
  public void printPlayingField() throws Exception {

    System.out.println("\n\n============================= " + getBoard().getMovesCounter()
            + ". Move ===================================");
    System.out.print("                                                          � Markus Ziller");
    PrintPlayingField.printCurrentSituation(this.getBoard());
    if (GameUtil.isCheckMate("white", getBoard())) {
      System.out.println("\n	White is check mate!");
    } else {

      if (GameUtil.isGivenColourCheck("white", getBoard())) {

        System.out.println("\n	White is check!");
      }
    }


    if (GameUtil.isCheckMate("black", getBoard())) {
      System.out.println("\n	Black is check mate!");
    } else {

      if (GameUtil.isGivenColourCheck("black", getBoard())) {

        System.out.println("\n	Black is check!");
      }
    }

  }

  public String printPlayingFieldString() throws Exception {
    String rep = "";

    rep = (rep + "\n\n============================= " + getBoard().getMovesCounter()
            + ". Move ===================================\n");
    rep = (rep + "                                                          � Markus Ziller\n\n\n");
    rep = (rep + PrintPlayingField.printCurrentSituationString(this.getBoard()));
    if (GameUtil.isCheckMate("white", getBoard())) {
      rep = (rep + "\n	White is check mate!\n");
    } else {

      if (GameUtil.isGivenColourCheck("white", getBoard())) {

        rep = (rep + "\n	White is check!\n");
      }
    }


    if (GameUtil.isCheckMate("black", getBoard())) {
      rep = (rep + "\n	Black is check mate!\n");
    } else {

      if (GameUtil.isGivenColourCheck("black", getBoard())) {

        rep = (rep + "\n	Black is check!\n");
      }
    }

    return rep;

  }

  public void mainMenu() throws Exception {
    while (true) {
      //System.out.println ("	What do you want to do next\n");
      System.out.println("\n	======== MAIN MENU ========\n");
      System.out.println("	(0) Print all allowed Moves.");
      System.out.println("	(1) Print Playfield an mark Moves for the King.");
      System.out.println("	(2) Print Playfield an mark Moves for a Figur on a certain Field.");
      System.out.println("	(3) Castle.");
      System.out.println("	(4) Move Figure.");
      System.out.print("\n	Insert your choice: ");


      Scanner scanner = new Scanner(System.in);
      String choice = scanner.next();
      if (choice.equals("0")) {
        System.out.println();
        this.printListOfMovesAllowedForColour("white");
        System.out.println();
      } else if (choice.equals("1")) {
        System.out.println();
        this.printPlayingFieldAndMarkMovesForKing("white");
        System.out.println();
      } else if (choice.equals("2")) {
        System.out.println();
        System.out.print("	The moves for which figur shall be marked?	");

        String field = scanner.next();
        this.printPlayingField(field);
      } else if (choice.equals("3")) {
        System.out.println();
        System.out.print("	Insert wQ for queenside Castling or wK for kingside Castling:	");

        String field = scanner.next();
        this.castling(field);
      } else if (choice.equals("4")) {
        System.out.println();
        break;
      } else if (choice.equals("addF")) {
                    /*System.out.println();
                    System.out.print("	Insert board:	");
										Scanner fieldScanner = new Scanner(System.in);
										String board = fieldScanner.next();
										System.out.println();
										System.out.print("	Insert type:	");
										Scanner typeScanner = new Scanner(System.in);
										String type = typeScanner.next();
										System.out.println();
										System.out.print("	Insert colour:	");
										Scanner colourScanner = new Scanner(System.in);
										String colour = colourScanner.next();
										this.getBoard().setFigureWithoutValidityCheck(board, type, colour);*/

      } else if (choice.equals("4")) {
        System.out.println();
        break;
      } else if (choice.equals("4")) {
        System.out.println();
        break;
      }

      System.out.println("\n	==================\n");
      //break;

    }
  }

  /**
   * Prints the playing board and marks allowed fields to move, either for a specific figure or a colour
   * <p/>
   * checked 10.4
   *
   * @param input = "a3", "B2"... for a board and "white" or "black" for a colour
   * @throws Exception
   */
  public void printPlayingField(String input) throws Exception {
    int[] fieldHelp;
    Square[][] square2 = getBoard().getSquares();
    if (input.equals("white") || input.equals("black")) {
      System.out
              .println("\n=========================================================================");
      System.out.print("                                                          � Markus Ziller");
      PrintPlayingField
              .printCurrentSituationAndMarkPossibleFieldsForColour(
                      this.getBoard(), input);
    } else {
      fieldHelp = ChessNotationUtil.convertFieldNameToIndexes(input);

      if (!square2[fieldHelp[0]][fieldHelp[1]].isEmpty()) {
        System.out.println("\n\n============================= "
                + getBoard().getMovesCounter()
                + ". Move ===================================");
        System.out.print("                                                          � Markus Ziller");
        PrintPlayingField.printCurrentSituationAndMarkPossibleFields(
                this.getBoard(), input);
      } else {
        System.out
                .println("	No figure on "
                        + input
                        + ".\n	Therefore no reachable fields have been printed");
        printPlayingField();
      }
    }

  }

  /**
   * Prints the playing board and marks allowed fields for the king to move
   * <p/>
   * checked 10.4
   *
   * @param colour = "white" or "black"
   * @throws Exception
   */
  public void printPlayingFieldAndMarkMovesForKing(String colour) throws Exception {
    Square kingSquare = BoardUtil.getKingFieldByColour(colour, this.board);
    char s = kingSquare.getCharRow();
    int k = kingSquare.getIntColumn();
    String i = ("" + s + "" + k);
    printPlayingField(i);

  }

  /**
   * Removes the figure on the current board
   *
   * @param field
   * @throws Exception
   */
  public void removeFigure(String field) throws Exception {
    if (!fieldManipulationForbidden) {
      this.getBoard().removeFigureInterface(field);
    } else {
      System.out
              .println("Fieldmanipulation is not allowed. Therefore no figure has been removed from "
                      + field);
    }

    this.getBoard().markAttackedFields();
  }

  /**
   * Runs the following test on the current Game:
   * <p/>
   * printPlayingField();
   * printAllPossibleMovesForColour("white");
   * printAllPossibleMovesForColour("black");
   * printPlayingField("white");
   * printPlayingField("black");
   * printListOfFiguresGivingCheckToColour("white");
   * printListOfFiguresGivingCheckToColour("black");
   * printListOfThreatenedFieldsByColour("white");
   * printListOfThreatenedFieldsByColour("black");
   * printIsCheck("white");
   * printIsCheck("black");
   * printPlayingFieldAndMarkMovesForKing("white");
   * printPlayingFieldAndMarkMovesForKing("black");
   * printPlayingField();
   *
   * @throws Exception
   */
  public void runTests() throws Exception {


    //this.printPlayingField();

    this.printPlayingField("white");
    this.printPlayingField("black");
    this.printListOfFiguresGivingCheckToColour("white");
    this.printListOfFiguresGivingCheckToColour("black");
    this.printListOfMovesAllowedForColour("white");
    this.printListOfMovesAllowedForColour("black");
    this.printListOfActuallyAllowedFieldsToMoveByColour("white");
    this.printListOfActuallyAllowedFieldsToMoveByColour("black");
    //this.printIsCheck("white");
    //this.printIsCheck("black");
    this.printPlayingFieldAndMarkMovesForKing("white");
    this.printPlayingFieldAndMarkMovesForKing("black");
    this.printPlayingField();
  }

  /**
   * Saves the current Situation
   *
   * @param filename
   */
  public void saveSituation(String filename) {

    try {
      PersistenceUtil.saveGame(this, filename);


    } catch (Exception e) {
    }

  }

  /**
   * Allows or Forbiddens to manually add or remove Figures
   *
   * @param fieldManipulationForbidden
   */
  public void setFieldManipulationAllowed(boolean fieldManipulationForbidden) {
    this.fieldManipulationForbidden = fieldManipulationForbidden;
  }

  /**
   * Returns if a Move is valid at the current (checked) Situation
   *
   * @param startingField
   * @param destinationField
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public boolean validMoveAtCheckedSituation(String startingField, String destinationField) throws Exception {
    int[] startingFieldHelp = ChessNotationUtil.convertFieldNameToIndexes(startingField);
    int[] destinationFieldHelp = ChessNotationUtil.convertFieldNameToIndexes(destinationField);

    Square[][] squareArray = this.board.getSquares();
    Square startSquare = squareArray[startingFieldHelp[0]][startingFieldHelp[1]];
    Square destinationField_Square = squareArray[destinationFieldHelp[0]][destinationFieldHelp[1]];
    String colour = startSquare.getPiece().getColour();

    Move moveToBeChecked = new Move(startSquare, destinationField_Square);

    ArrayList validMoves = MoveUtil.movesAllowedAtACheckedSituation(colour, this.board);
    boolean contains = false;
    for (int i = 0; i < validMoves.size(); i++) {
      Move currentMove = (Move) validMoves.get(i);
      if (currentMove.getStartSquare().equals(startSquare) && currentMove.getDestinationSquare().equals(destinationField_Square))
        contains = true;

    }

    return contains;
  }

}
