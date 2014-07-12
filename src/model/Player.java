package model;

import model.pieces.AttackingEntity;
import model.pieces.Piece;

import java.util.*;

/**
 * Created by Markus on 28.06.2014.
 */
public class Player implements AttackingEntity {
  private static int WHITE = 1;
  private static int BLACK = -1;
  private boolean isWhite;
  private List<Piece> activePieces = new ArrayList<>();
  private List<Piece> capturedPieces  = new ArrayList<>();

  private Set<Move> allMoves = new TreeSet<>();
  private Set<Move> allCapuringMoves = new TreeSet<>();
  private Set<Move> allNormalMoves = new TreeSet<>();
  private Set<Move> allOtherMoves = new TreeSet<>();

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
    return allMoves;
  }

  @Override
  public Set<Move> getAllNormalMoves() {
    return allNormalMoves;
  }

  @Override
  public Set<Move> getAllCapturingMoves() {
    return allCapuringMoves;
  }

  @Override
  public Set<Move> getAllOtherMoves() {
    return allOtherMoves;
  }

  private void aggregateCapturingMoves(){
    allCapuringMoves.clear();
    for(Piece piece : activePieces){
      allCapuringMoves.addAll(piece.getAllCapturingMoves());
    }
  }

  private void aggregateNormalMoves(){
    allNormalMoves.clear();
    for(Piece piece : activePieces){
      allNormalMoves.addAll(piece.getAllNormalMoves());
    }
  }

  private void aggregateOtherMoves(){
    allOtherMoves.clear();
    for(Piece piece : activePieces){
      allOtherMoves.addAll(piece.getAllOtherMoves());
    }
  }

  private void aggregateAllMoves(){
    allMoves.clear();
    for(Piece piece : activePieces){
      allMoves.addAll(piece.getAllMoves());
    }
  }

  public void updateMovingOptions(){
    aggregateAllMoves();
    aggregateNormalMoves();
    aggregateCapturingMoves();
    aggregateOtherMoves();
  }
}
