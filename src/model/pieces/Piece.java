package model.pieces;

import model.Player;

/**
 * Created by Markus on 28.06.2014.
 */
public interface Piece {

  public Player getPlayer();
  public boolean isWhite();
  public boolean hasBeenMoved();
  public void setHasBeenMoved(boolean hasBeenMoved);
}
