package utils;

import model.Board;
import model.Square;

import java.util.ArrayList;

/**
 * Created by Markus on 28.06.2014.
 */
public class BoardUtil {


    /**
     * Lists all fields between an attacker and the attacked king
     * <p/>
     * Can only be performed on a rook, a bishop or a queen;
     * <p/>
     * Is used to determine, if check mate can be avioded by blocking an enemy
     * figures attack path.
     *
     * @param enemyFigure
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList listOfFieldsBetweenAttackerAndKing(
            Square enemyFigure, Board field) throws Exception {
        ArrayList listOfFields = new ArrayList();

        if (enemyFigure.getPiece().getType().equals("rook"))
            listOfFields = fieldsBetweenKingAndEnemyRook(enemyFigure, field);
        else if (enemyFigure.getPiece().getType().equals("bishop"))
            listOfFields = fieldsBetweenKingAndEnemyBishop(enemyFigure, field);
        else if (enemyFigure.getPiece().getType().equals("queen"))
            listOfFields = fieldsBetweenKingAndEnemyQueen(enemyFigure, field);

        return listOfFields;
    }

    /**
     * only performed on a bishop giving check
     *
     * @param enemyBishopSquare
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList fieldsBetweenKingAndEnemyBishop(
            Square enemyBishopSquare, Board field) throws Exception {

        Square kingSquare;

        if (enemyBishopSquare.getPiece().getColour().equals("white")) {
            kingSquare = getKingFieldByColour("black", field);
        } else {
            kingSquare = getKingFieldByColour("white", field);
        }

        int rowBishopField = enemyBishopSquare.getRowIndex();
        int columnBishopField = enemyBishopSquare.getColumnIndex();
        int rowKingField = kingSquare.getRowIndex();
        int columnKingField = kingSquare.getColumnIndex();
        int rowDistance;
        int columnDistance;
        ArrayList fields = new ArrayList();
        rowDistance = rowBishopField - rowKingField;
        Square[][] squareArray = field.getSquares();
        columnDistance = columnBishopField - columnKingField;

        if (Math.abs(rowDistance) == Math.abs(columnDistance)) {
            if (rowDistance < 0 && columnDistance < 0) {
                for (int i = 0; i - 1 > rowDistance; i--) {
                    rowBishopField++;
                    columnBishopField++;
                    fields.add(squareArray[rowBishopField][columnBishopField]);

                }
            } else if (rowDistance < 0 && columnDistance > 0) {
                for (int i = 0; i - 1 > rowDistance; i--) {
                    rowBishopField++;
                    columnBishopField--;
                    fields.add(squareArray[rowBishopField][columnBishopField]);

                }
            } else if (rowDistance > 0 && columnDistance < 0) {
                for (int i = 0; i + 1 < rowDistance; i++) {
                    rowBishopField--;
                    columnBishopField++;
                    fields.add(squareArray[rowBishopField][columnBishopField]);

                }
            } else if (rowDistance > 0 && columnDistance > 0) {
                for (int i = 0; i + 1 < rowDistance; i++) {
                    rowBishopField--;
                    columnBishopField--;
                    fields.add(squareArray[rowBishopField][columnBishopField]);

                }
            }
        }

        return fields;

    }

    /**
     * only performed on a queen giving check
     *
     * @param enemyQueenSquare
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList fieldsBetweenKingAndEnemyQueen(
            Square enemyQueenSquare, Board field) throws Exception {

        Square kingSquare;

        if (enemyQueenSquare.getPiece().getColour().equals("white")) {
            kingSquare = getKingFieldByColour("black", field);
        } else {
            kingSquare = getKingFieldByColour("white", field);
        }

        int rowQueenField = enemyQueenSquare.getRowIndex();
        int columnQueenField = enemyQueenSquare.getColumnIndex();
        int rowKingField = kingSquare.getRowIndex();
        int columnKingField = kingSquare.getColumnIndex();
        int rowDistance;
        int columnDistance;
        ArrayList fields = new ArrayList();
        rowDistance = rowQueenField - rowKingField;
        Square[][] squareArray = field.getSquares();
        columnDistance = columnQueenField - columnKingField;

        if (Math.abs(rowDistance) == Math.abs(columnDistance)) {
            if (rowDistance < 0 && columnDistance < 0) {
                for (int i = 0; i - 1 > rowDistance; i--) {
                    rowQueenField++;
                    columnQueenField++;
                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }
            } else if (rowDistance < 0 && columnDistance > 0) {
                for (int i = 0; i - 1 > rowDistance; i--) {
                    rowQueenField++;
                    columnQueenField--;
                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }
            } else if (rowDistance > 0 && columnDistance < 0) {
                for (int i = 0; i + 1 < rowDistance; i++) {
                    rowQueenField--;
                    columnQueenField++;
                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }
            } else if (rowDistance > 0 && columnDistance > 0) {
                for (int i = 0; i + 1 < rowDistance; i++) {
                    rowQueenField--;
                    columnQueenField--;
                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }
            }
        } else {
            if (rowDistance < 0 && columnDistance == 0) {
                for (int i = 0; i - 1 > rowDistance; i--) {
                    rowQueenField++;
                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }
            } else if (rowDistance > 0 && columnDistance == 0) {
                for (int i = 0; i + 1 < rowDistance; i++) {
                    rowQueenField--;

                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }
            } else if (rowDistance == 0 && columnDistance < 0) {
                for (int i = 0; i - 1 > columnDistance; i--) {

                    columnQueenField++;
                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }
            } else if (rowDistance == 0 && columnDistance > 0) {
                for (int i = 0; i + 1 < columnDistance; i++) {

                    columnQueenField--;
                    fields.add(squareArray[rowQueenField][columnQueenField]);

                }

            }
        }

        return fields;

    }

    /**
     * only performed on a rook giving check
     *
     * @param enemyRookSquare
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList fieldsBetweenKingAndEnemyRook(Square enemyRookSquare,
                                                          Board field) throws Exception {

        Square kingSquare;

        if (enemyRookSquare.getPiece().getColour().equals("white")) {
            kingSquare = getKingFieldByColour("black", field);
        } else {
            kingSquare = getKingFieldByColour("white", field);
        }

        int rowRookField = enemyRookSquare.getRowIndex();
        int colummRookField = enemyRookSquare.getColumnIndex();
        int rowKingField = kingSquare.getRowIndex();
        int columnKingField = kingSquare.getColumnIndex();
        int rowDistance;
        int columnDistance;
        ArrayList fields = new ArrayList();
        rowDistance = rowRookField - rowKingField;
        columnDistance = colummRookField - columnKingField;
        Square[][] squareArray = field.getSquares();

        if (rowDistance < 0 && columnDistance == 0) {
            for (int i = 0; i - 1 > rowDistance; i--) {
                rowRookField++;
                fields.add(squareArray[rowRookField][colummRookField]);

            }
        } else if (rowDistance > 0 && columnDistance == 0) {
            for (int i = 0; i + 1 < rowDistance; i++) {
                rowRookField--;

                fields.add(squareArray[rowRookField][colummRookField]);

            }
        } else if (rowDistance == 0 && columnDistance < 0) {
            for (int i = 0; i - 1 > columnDistance; i--) {

                colummRookField++;
                fields.add(squareArray[rowRookField][colummRookField]);

            }
        } else if (rowDistance == 0 && columnDistance > 0) {
            for (int i = 0; i + 1 < columnDistance; i++) {

                colummRookField--;
                fields.add(squareArray[rowRookField][colummRookField]);

            }

        }

        return fields;

    }

    /**
     * Returns the field of the king for the given colour.
     *
     * @param colour
     * @param field
     * @return
     * @throws Exception
     */
    public static Square getKingFieldByColour(String colour, Board field)
            throws Exception {
        Square[][] squareArray = field.getSquares();
        Square buffer = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squareArray[i][j].getPiece() != null)
                    if (squareArray[i][j].getPiece().getColour().equals(colour))
                        if (squareArray[i][j].getPiece().getType().equals("king"))
                            buffer = squareArray[i][j];
                        else
                            ;
            }
        }
        if (buffer != null)
            return buffer;
        else
            throw new Exception("No king fund for " + colour
                    + ". Check Lineup please");
    }

    public static Square getKingFieldByColour(String colour, Square[][] squareArray)
            throws Exception {

        Square buffer = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squareArray[i][j].getPiece() != null)
                    if (squareArray[i][j].getPiece().getColour().equals(colour))
                        if (squareArray[i][j].getPiece().getType().equals("king"))
                            buffer = squareArray[i][j];
                        else
                            ;
            }
        }
        if (buffer != null)
            return buffer;
        else
            throw new Exception("No king fund for " + colour
                    + ". Check Lineup please");
    }

  public static boolean isValidMove(Square from, Square to, Board board) {
    return from.getPiece().getAllowFieldsToMoveOnto().contains(to);
  }
}
