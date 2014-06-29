package model;

/**
 * Created by Markus on 28.06.2014.
 */
public class Player {
  public static int WHITE = 1;
  public static int BLACK = 2;

  public boolean isWhite() {
    return isWhite;
  }

  private boolean isWhite;

  private Player(boolean isWhite) {
    this.isWhite = isWhite;
  }

  public static Player getWhitePlayer() {
    return new Player(true);
  }

  public static Player getBlackPlayer() {
    return new Player(false);
  }

}
