package model;

public class Move {

  public static int CASTLING_WHITE_KINGSIDE = 1;
  public static int CASTLING_WHITE_QUEENSIDE = 2;
  public static int CASTLING_BLACK_KINGSIDE = 3;
  public static int CASTLING_BLACK_QUEENSIDE = 4;

  private Square startSquare;
  private Square destinationSquare;

  public Move(Square startSquare, Square destinationSquare) {
    this.startSquare = startSquare;
    this.destinationSquare = destinationSquare;

  }

  public Square getDestinationSquare() {
    return this.destinationSquare;
  }

  public void setDestinationSquare(Square destinationSquare) {
    this.destinationSquare = destinationSquare;
  }

  public Square getStartSquare() {
    return this.startSquare;
  }

  public void setStartSquare(Square startSquare) {
    this.startSquare = startSquare;
  }

}
