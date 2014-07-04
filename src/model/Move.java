package model;

public class Move {

  public static int NORMAL = 1;
  public static int CAPTURE = 2;
  public static int EN_PASSANT = 3;
  public static int CASTLING_WHITE_KINGSIDE = 4;
  public static int CASTLING_WHITE_QUEENSIDE = 5;
  public static int CASTLING_BLACK_KINGSIDE = 6;
  public static int CASTLING_BLACK_QUEENSIDE = 7;

  private Square from;
  private Square to;
  private int moveType;

  public Move(Square from, Square to, int moveType) {
    this.from = from;
    this.to = to;
    this.moveType = moveType;
  }

  public Square getTo() {
    return this.to;
  }

  public Square getFrom() {
    return this.from;
  }
}
