package utils;

import model.Board;
import model.Square;

/**
 * Created by Markus on 28.06.2014.
 */
public class BoardUtil {

  public static boolean isValidMove(Square from, Square to, Board board) {
    return from.getPiece().getAllowedSquaresToMoveOnto().contains(to);
  }
}
