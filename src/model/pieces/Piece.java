package model.pieces;

/**
 * Created by Markus on 28.06.2014.
 */
public interface Piece {

    public boolean getHasBeenMoved();

    public void setHasBeenMoved(boolean hasBeenMoved);

    public String getType();

    public void setType(String type);

    public String getColour();

    public void setColour(String colour);
}
