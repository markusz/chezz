package model;

import com.google.common.collect.Sets;
import model.pieces.AttackingEntity;
import model.pieces.Piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Markus on 28.06.2014.
 */
public class Player implements AttackingEntity {
  private static int WHITE = 1;
  private static int BLACK = -1;
  private boolean isWhite;
  private List<Piece> activePieces;
  private List<Piece> capturedPieces;

  public void addPiece(Piece piece) {
    activePieces.add(piece);
  }

  public void pieceHasBeenCaptured(Piece piece) {
    activePieces.remove(piece);
    capturedPieces.add(piece);
  }

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

  public int getBoardModifier() {
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

  @Override
  public Set<Square> getAllowedSquaresToMoveOnto() {
    return null;
  }

  @Override
  public Set<Square> getAllThreatenedSquares() {
    Set<Square> squares = new TreeSet<>();
    for (AttackingEntity activePiece : activePieces) {
      squares.addAll(activePiece.getAllThreatenedSquares());
    }
    return squares;
  }

  @Override
  public Set<Move> getAllMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllNormalMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllCapturingMoves() {
    return null;
  }

  @Override
  public Set<Move> getAllOtherMoves() {
    return null;
  }
}
