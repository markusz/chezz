package model;

/**
 * Created by Markus on 28.06.2014.
 */
public class Player {
  private static int WHITE = 1;
  private static int BLACK = -1;
  private boolean isWhite;

  public boolean isCheck() {
    return isCheck;
  }

  public void setCheck(boolean isCheck) {
    this.isCheck = isCheck;
  }

  private boolean isCheck;

  public boolean isWhite() {
    return isWhite;
  }

  public int getBoardModifier(){
    return isWhite ? WHITE : BLACK;
  }

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
