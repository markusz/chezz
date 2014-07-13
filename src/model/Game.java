package model;

import model.pieces.*;
import utils.ChessNotationUtil;
import utils.GameUtil;
import utils.OutputUtil;
import utils.PersistenceUtil;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressWarnings({"unused", "serial"})
public class Game implements Serializable {

  private boolean forceMoveOrder = true;
  private boolean fieldManipulationForbidden = true;

  public boolean isWhiteTurn() {
    return whiteTurn;
  }

  private boolean whiteTurn = true;
  private int moveCounter = 0;
  private Board board;

  public Player getWhitePlayer() {
    return whitePlayer;
  }

  public Player getBlackPlayer() {
    return blackPlayer;
  }

  private Player whitePlayer;
  private Player blackPlayer;
  private String log = "";

  public Game(Boolean startingLineUp, Boolean fieldManipulationForbidden, Boolean forceMoveOrder) throws Exception {
    board = new Board(this);
    whitePlayer = Player.getWhitePlayer();
    blackPlayer = Player.getBlackPlayer();

    if (startingLineUp) {
      board.createInitialLineup();
    }

    whitePlayer.updateMovingOptions();
    blackPlayer.updateMovingOptions();


    this.fieldManipulationForbidden = fieldManipulationForbidden;
    this.forceMoveOrder = forceMoveOrder;
  }

  public void changeTurn() {
    whiteTurn = !whiteTurn;
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

  public void increaseMovesCounter() {
    moveCounter++;
  }

  public int getMovesCounter() {
    return moveCounter;
  }

  /**
   * Adds a Figure of "type" and "colour" at "board" .
   * <p>
   * if FieldManipulation, which is determined by the boolean "fieldManipulationForbidden", is allowed.
   *
   * @throws Exception
   */
  public void addFigure(Square square, Piece piece) throws Exception {
    if (!fieldManipulationForbidden) {
      square.setPiece(piece);
    } else {
      throw new Exception("Fieldmanipulation is not allowed.");
    }
  }

  public boolean isCastlingOnWhiteKingsideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    Set<Square> threatenedFieldsByBlack = blackPlayer.getAllThreatenedSquares();
    Square E1 = square[4][7];
    Square F1 = square[5][7];
    Square G1 = square[6][7];
    Square H1 = square[7][7];

    return F1.isEmpty() && !threatenedFieldsByBlack.contains(F1)
            && G1.isEmpty() && !threatenedFieldsByBlack.contains(G1)
            && !H1.isEmpty() && !H1.getPiece().hasBeenMoved()
            && !E1.isEmpty() && !E1.getPiece().hasBeenMoved()
            && !whitePlayer.isCheck();

  }

  public boolean isCastlingOnWhiteQueensideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    Set<Square> threatenedFieldsByBlack = blackPlayer.getAllThreatenedSquares();
    Square A1 = square[0][7];
    Square B1 = square[1][7];
    Square C1 = square[2][7];
    Square D1 = square[3][7];
    Square E1 = square[4][7];

    return B1.isEmpty() && !threatenedFieldsByBlack.contains(B1)
            && C1.isEmpty() && !threatenedFieldsByBlack.contains(C1)
            && D1.isEmpty() && !threatenedFieldsByBlack.contains(D1)
            && !A1.isEmpty() && !A1.getPiece().hasBeenMoved()
            && !E1.isEmpty() && !E1.getPiece().hasBeenMoved()
            && !whitePlayer.isCheck();


  }

  public boolean isCastlingOnBlackKingsideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    Set<Square> threatenedFieldsByWhite = whitePlayer.getAllThreatenedSquares();
    Square E8 = square[4][0];
    Square F8 = square[5][0];
    Square G8 = square[6][0];
    Square H8 = square[7][0];


    return F8.isEmpty() && !threatenedFieldsByWhite.contains(F8)
            && G8.isEmpty() && !threatenedFieldsByWhite.contains(G8)
            && !H8.isEmpty() && !H8.getPiece().hasBeenMoved()
            && !E8.isEmpty() && !E8.getPiece().hasBeenMoved()
            && !blackPlayer.isCheck();


  }

  public boolean isCastlingOnBlackQueensideAllowed() throws Exception {
    Square[][] square = this.getBoard().getSquares();
    Set<Square> threatenedFieldsByWhite = whitePlayer.getAllThreatenedSquares();
    Square A8 = square[0][0];
    Square B8 = square[1][0];
    Square C8 = square[2][0];
    Square D8 = square[3][0];
    Square E8 = square[4][0];


    return B8.isEmpty() && !threatenedFieldsByWhite.contains(B8)
            && C8.isEmpty() && !threatenedFieldsByWhite.contains(C8)
            && D8.isEmpty() && !threatenedFieldsByWhite.contains(D8)
            && !A8.isEmpty() && !A8.getPiece().hasBeenMoved()
            && !E8.isEmpty() && !E8.getPiece().hasBeenMoved()
            && !blackPlayer.isCheck();
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
      printBoard();
    }
  }

  public void castleOnWhiteQueenside() throws Exception {
    if (isCastlingOnWhiteQueensideAllowed()) {
      Square c1 = getSquareByChessNotation("C1");
      Square e1 = getSquareByChessNotation("E1");
      Square d1 = getSquareByChessNotation("D1");
      Square a1 = getSquareByChessNotation("A1");

      movePiece(e1, c1);
      movePiece(a1, d1);

      whiteTurn = !whiteTurn;
      printBoard();
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
      printBoard();
    }
  }

  public void castleOnWhiteKingside() throws Exception {
    if (isCastlingOnWhiteKingsideAllowed()) {

      Square g1 = getSquareByChessNotation("G1");
      Square e1 = getSquareByChessNotation("E1");
      Square h1 = getSquareByChessNotation("H1");
      Square f1 = getSquareByChessNotation("F1");

      movePiece(h1, f1);
      movePiece(e1, g1);

      whiteTurn = !whiteTurn;
      printBoard();
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
   * <p>
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

    squareWhite.setPiece(new King(whitePlayer, board));
    squareBlack.setPiece(new King(blackPlayer, board));


    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        Square tempSquare = board.getSquare(i, j);
        if (Math.random() < probability && tempSquare.isEmpty()) {
          if (Math.random() < 0.5) {
            if (Math.random() < 0.4)
              tempSquare.setPiece(new Pawn(whitePlayer, board));
            else if (Math.random() < 0.55)
              tempSquare.setPiece(new Rook(whitePlayer, board));
            else if (Math.random() < 0.7)
              tempSquare.setPiece(new Bishop(whitePlayer, board));
            else if (Math.random() < 0.85)
              tempSquare.setPiece(new Knight(whitePlayer, board));
            else if (Math.random() < 1)
              tempSquare.setPiece(new Queen(whitePlayer, board));
          } else {
            if (Math.random() < 0.4)
              tempSquare.setPiece(new Pawn(blackPlayer, board));
            else if (Math.random() < 0.55)
              tempSquare.setPiece(new Rook(blackPlayer, board));
            else if (Math.random() < 0.7)
              tempSquare.setPiece(new Bishop(blackPlayer, board));
            else if (Math.random() < 0.85)
              tempSquare.setPiece(new Knight(blackPlayer, board));
            else if (Math.random() < 1)
              tempSquare.setPiece(new Queen(blackPlayer, board));
          }
        }
      }
    }
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
    loadedGame.printBoard();
    return loadedGame;

  }

  /**
   * Moves the figure on startingfield to destinationfield
   * <p>
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

    if (forceMoveOrder && from.getPiece().getPlayer().isWhite() != whiteTurn) {
      //fail silently
      //TODO meaningful error message to user
    }
    board.moveFigure(from, to);
    whiteTurn = !whiteTurn;
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

      //TODO this is possibly still wrong. Write test when code compiles
      if (!from.isEmpty() && from.getPiece().getPlayer().isWhite() == whiteTurn && from.getPiece().isValidMoveDestination(to)) {
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

  public Square[] randomMove() {
    Random r = new Random();
    int fromRowIndex = r.nextInt(8);
    int fromColumnIndex = r.nextInt(8);
    int toRowIndex = r.nextInt(8);
    int toColumnIndex = r.nextInt(8);

    Square from = board.getSquare(fromRowIndex, fromColumnIndex);
    Square to = board.getSquare(toRowIndex, toColumnIndex);

    return new Square[]{from, to};
  }

  /**
   * Moves Figures depending on moveManually
   * <p>
   * if moveManually is true, white Moves are determined by the User instead of being randomed
   * else every Move is random
   *
   * @throws Exception
   */
  public void moveRandomConfirmMoves(boolean moveManually, boolean mainMenu) throws Exception {
    printBoard();

    while (this.countFigures() > 2) {
      if (GameUtil.isCheckMate("white", this.board) || GameUtil.isCheckMate("black", this.board)) {
        printBoard();
        break;
      }
      if (moveManually) {
        if (whiteTurn) {
          //printBoard();
          while (true) {
            if (mainMenu) {
              this.mainMenu();
            }
            Scanner scanner = new Scanner(System.in);
            Pattern pattern = Pattern.compile("[a-hA-H][1-8]");

            System.out.print("	Please Insert Starting Field Of Your Next Move: ");
            String fromAsString = scanner.next();
            if (!pattern.matcher(fromAsString).matches()) {
              System.out.println("\n	Failure. This is an invalid Input. Please insert again: \n");
              continue;
            }

            System.out.print("	Please Insert Destination Field Of Your Next Move: ");
            String toAsString = scanner.next();
            if (!pattern.matcher(toAsString).matches()) {
              System.out.println("\n	Failure. This is an invalid Input. Please insert again: \n");
              continue;
            }

            Square from = getSquareByChessNotation(fromAsString);
            Square to = getSquareByChessNotation(toAsString);

            if (!from.isEmpty() && from.getPiece().isValidMoveDestination(to)) {

              movePiece(from, to);
              System.out.println("	" + fromAsString + " -> " + toAsString);
              whiteTurn = !whiteTurn;
              break;
            } else
              System.out.println("\n	Failure. This is an invalid Move. Please insert again: \n");
          }

        } else {
          while (true) {
            Random r = new Random();
            int fromRowIndex = r.nextInt(8);
            int fromColumnIndex = r.nextInt(8);
            int toRowIndex = r.nextInt(8);
            int toColumnIndex = r.nextInt(8);

            Square from = board.getSquare(fromRowIndex, fromColumnIndex);
            Square to = board.getSquare(toRowIndex, toColumnIndex);

            if (!from.isEmpty() && from.getPiece().isWhite() == whiteTurn && from.getPiece().isValidMoveDestination(to)) {
              movePiece(from, to);
              System.out.println("	" + from + " -> " + to);
              whiteTurn = !whiteTurn;
              break;
            }
          }

        }
      }
    }
  }

  /**
   * Prints all allowed Moves for a Colour at a Situation, where the given Colour is not check.
   *
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public void printAllMovesForColourNotCheck(Player player) throws Exception {
    System.out.println(getAllMovesForPlayerAsString(player));
  }

  @SuppressWarnings("unchecked")
  public String getAllMovesForPlayerAsString(Player player) throws Exception {
    StringBuilder sb = new StringBuilder();
    Set<Move> moves = player.getAllMoves();

    sb.append("\n=========================================================================");
    sb.append("\n	A List Of Allowed Moves (" + moves.size() + "):\n");

    for (Move move : moves) {
      sb.append(move.toString()).append("\n");
    }

    return sb.toString();
  }

  /**
   * Prints if the current colour is check.
   *
   * @throws Exception
   */
  public void printIsCheck(Player player) throws Exception {
    OutputUtil.printIsCheck(player);
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
   * Prints the Playingfield at the current Situation.
   *
   * @throws Exception
   */
  public void printBoard() throws Exception {
    System.out.println(getBoardAsString());
  }

  public String getBoardAsString() throws Exception {

    StringBuilder sb = new StringBuilder();

    sb.append("\n\n============================= ").
            append(getMovesCounter()).
            append(". Move ===================================\n");

    sb.append("                                                          � Markus Ziller\n\n\n");
    sb.append(OutputUtil.getCurrentGameSituationAsString(this.getBoard()));
    if (GameUtil.isCheckMate("white", getBoard())) {
      sb.append("\n	White is check mate!\n");
    }
    if (whitePlayer.isCheck()) {
      sb.append("\n	White is check!\n");
    }

    if (GameUtil.isCheckMate("black", getBoard())) {
      sb.append("\n	Black is check mate!\n");
    }
    if (whitePlayer.isCheck()) {
      sb.append("\n	Black is check!\n");
    }


    return sb.toString();
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
        printListOfMovesAllowedForPlayer(whitePlayer);
      } else if (choice.equals("1")) {
        System.out.println("TODO:mainMenu");
      } else if (choice.equals("2")) {
        System.out.print("	The moves for which figur shall be marked?	");
        String field = scanner.next();
        board.toString();
      } else if (choice.equals("3")) {
        System.out.print("	Insert wQ for queenside Castling or wK for kingside Castling:	");

        String field = scanner.next();
        if (field.equalsIgnoreCase("wq")) {
          castleOnWhiteQueenside();
        }
        if (field.equalsIgnoreCase("wk")) {
          castleOnWhiteKingside();
        }
        if (field.equalsIgnoreCase("bq")) {
          castleOnBlackQueenside();
        }
        if (field.equalsIgnoreCase("bk")) {
          castleOnBlackKingside();
        }
      } else if (choice.equals("4")) {
        System.out.println();
        break;
      } else if (choice.equals("addF")) {

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

  private void printListOfMovesAllowedForPlayer(Player player) {
    System.out.println("IMPLEMENT ME:printListOfMovesAllowedForPlayer");
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

  @Override
  //TODO board to string
  public String toString() {
    return "TODO board.toString()";
  }

  public Player getPlayerInTurn() {
    return isWhiteTurn() ? whitePlayer : blackPlayer;
  }

  public void updateBoard() {
    whitePlayer.updateMovingOptions();
    blackPlayer.updateMovingOptions();
  }
}
