package model.pieces;

import model.Move;
import model.Square;

import java.util.Set;

/**
 * Created by Markus on 08.07.2014.
 */
public interface AttackingEntity {

  public Set<Square> getAllowedSquaresToMoveOnto();
  public Set<Square> getAllThreatenedSquares();
  public Set<Move> getAllMoves();
  public Set<Move> getAllNormalMoves();
  public Set<Move> getAllCapturingMoves();
  public Set<Move> getAllOtherMoves();
}
