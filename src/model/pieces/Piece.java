package model.pieces;

import model.Player;

/**
 * Created by Markus on 28.06.2014.
 */
public interface Piece {

  public Player getPlayer();
  public boolean hasBeenMoved();
  public void setHasBeenMoved(boolean hasBeenMoved);
  public String getType();
  public void setType(String type);
  public String getColour();
  public void setColour(String colour);
}
