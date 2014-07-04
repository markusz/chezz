package utils;

import logic.Moves;
import model.Move;
import model.Board;
import model.Player;
import model.Square;
import model.pieces.Piece;

import java.util.ArrayList;

public class MoveUtil {

    public static boolean isValidMove(Square from, Square to,
                                      Board playField) throws Exception {
        Square[][] square = playField.getSquares();

        String colour = from.getPiece().getColour();
        String startingFieldString = from.getCharRow() + ""
                + from.getIntColumn();
        String destinationFieldString = to.getCharRow() + ""
                + to.getIntColumn();
        boolean allowed = false;

        if (GameUtil.isGivenColourCheck(colour, square)) {
            if (validMoveAtCheckedSituation(startingFieldString,
                    destinationFieldString, playField))
                allowed = true;

            else
                ;
        } else {
            if (Moves.listOfActuallyAllowedMoves(startRow, startColumn, square)
                    .contains(square[destinationRow][destinationColumn]))
                allowed = true;
        }

        return allowed;


    }


    @SuppressWarnings("unchecked")
    public static boolean validMoveAtCheckedSituation(String startingField,
                                                      String destinationField, Board field) throws Exception {
        int[] startingFieldHelp = ChessNotationUtil
                .convertFieldNameToIndexes(startingField);
        int[] destinationFieldHelp = ChessNotationUtil
                .convertFieldNameToIndexes(destinationField);

        Square[][] squareArray = field.getSquares();
        Square startSquare = squareArray[startingFieldHelp[0]][startingFieldHelp[1]];
        Square destinationField_Square = squareArray[destinationFieldHelp[0]][destinationFieldHelp[1]];
        String colour = startSquare.getPiece().getColour();


        ArrayList validMoves = movesAllowedAtACheckedSituation(
                colour, field);
        boolean contains = false;
        for (int i = 0; i < validMoves.size(); i++) {
            Move currentMove = (Move) validMoves.get(i);
            if (currentMove.getFrom().equals(startSquare)
                    && currentMove.getTo().equals(
                    destinationField_Square))
                contains = true;

        }

        return contains;
    }

    /**
     * return an ArrayList <Moves> with moves that are actually allowed for this colour
     *
     * @param field
     * @param colour
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList getMovesForPlayer(Board field, Player player)
            throws Exception {

        Square[][] squareArray = field.getSquares();
        ArrayList moveArrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(squareArray[i][j].isEmpty()))
                    if (squareArray[i][j].getPiece().getColour().equals(colour))
                        for (int l = 0; l < 8; l++) {
                            for (int m = 0; m < 8; m++) {
                                if (Moves.listOfActuallyAllowedMoves(i, j, squareArray)
                                        .contains(squareArray[l][m]))
                                    moveArrayList
                                            .add(new Move(squareArray[i][j],
                                                    squareArray[l][m]));
                            }
                        }
            }
        }
        return moveArrayList;

    }

    /**
     * returns an ArrayList <Moves> containing moves, that would capture the enemy king.
     * used to find figures attacking the king and determining of a check mate situation
     *
     * @param field
     * @param colour
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList moveArrayByColourOfFiguresGivingCheck(Board field, String colour)
            throws Exception {

        Square[][] squareArray = field.getSquares();
        ArrayList moveArrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(squareArray[i][j].isEmpty()))
                    if (squareArray[i][j].getPiece().getColour().equals(colour))
                        for (int l = 0; l < 8; l++) {
                            for (int m = 0; m < 8; m++) {
                                if (Moves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, squareArray)
                                        .contains(squareArray[l][m]))
                                    moveArrayList
                                            .add(new Move(squareArray[i][j],
                                                    squareArray[l][m]));
                            }
                        }
            }
        }
        return moveArrayList;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList threatendFieldsByActuallyAllowedMoves(String colour,
                                                                  Square[][] square) throws Exception {


        ArrayList moves = new ArrayList();

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if (square[i][j].getPiece() == null) {
                    ;
                } else if (square[i][j].getPiece().getColour().equals(colour)) {
                    for (int counter = 0; counter < Moves.listOfActuallyAllowedMoves(i, j, square).size(); counter++) {
                        if (!(moves.contains((Moves.listOfActuallyAllowedMoves(i, j,
                                square).get(counter)))))
                            moves.add((Moves.listOfActuallyAllowedMoves(i, j, square)
                                    .get(counter)));
                        else
                            ;
                    }

                }

            }
        }
        return moves;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList threatendFieldsByColourHelpClass(String colour, Square[][] square) throws Exception { /*unused*/


        ArrayList moves = new ArrayList();

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if (square[i][j].getPiece() == null) {
                    ;
                } else if (square[i][j].getPiece().getColour().equals(colour)) {
                    // 9.4 listOfMovesIgnoreKingHelpClass -> listOfActuallyAllowedMoves
                    for (int counter = 0; counter < Moves.listOfActuallyAllowedMoves(i, j, square).size(); counter++) {
                        if (!(moves.contains((Moves.listOfActuallyAllowedMoves(i, j,
                                square).get(counter)))))
                            moves.add((Moves.listOfActuallyAllowedMoves(i, j, square)
                                    .get(counter)));
                        else
                            ;
                    }

                }

            }
        }
        return moves;
    }

    /**
     * gives a list of all fields attacked by a colour.
     * <p/>
     * -> fields the enemy king is not allowed to move
     *
     * @param colour
     * @param square
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList threatenedFieldsByColourConsideringEveryAttackedField(String colour,
                                                                                  Square[][] square) throws Exception {


        ArrayList moves = new ArrayList();

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if (square[i][j].isEmpty()) {
                    ;
                } else if (square[i][j].getPiece().getColour().equals(colour)) {
                    for (int counter = 0; counter < Moves.listOfMovesConsideringAllThreatendFields(i, j, square).size(); counter++) {
                        if (!(moves.contains((Moves.listOfMovesConsideringAllThreatendFields(i, j, square).get(counter)))))
                            moves.add((Moves.listOfMovesConsideringAllThreatendFields(i, j, square).get(counter)));
                        else
                            ;
                    }

                }

            }
        }
        return moves;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList threatenedFieldsOnWhichAnEnemyKingIsCheck(String colour,
                                                                      Square[][] square) throws Exception {


        ArrayList moves = new ArrayList();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (square[i][j].isEmpty()) {
                    ;
                } else if (square[i][j].getPiece().getColour().equals(colour)) {
                    for (int counter = 0; counter < Moves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, square).size(); counter++) {
                        if (!(moves.contains((Moves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j,
                                square).get(counter)))))
                            moves.add((Moves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, square)
                                    .get(counter)));
                        else
                            ;
                    }

                }

            }
        }
        return moves;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList MovesToAvoidCheckMateByBlockingAttackersPath(
            Square attacker, Board field) throws Exception {

        String attackerColour = attacker.getPiece().getColour();
        String defenderColour = "white";

        if (attackerColour.equals("white"))
            defenderColour = "black";

        ArrayList movesAvoiding = new ArrayList();
        ArrayList movesAllowedForDefender = getMovesForPlayer(field,
                defenderColour);
        ArrayList fieldsBetweenAttackerAndKing = BoardUtil.listOfFieldsBetweenAttackerAndKing(
                attacker, field);

        for (int i = 0; i < movesAllowedForDefender.size(); i++) {
            Move currentMove = (Move) movesAllowedForDefender.get(i);
            Square currentSquare = currentMove.getTo();
            if (fieldsBetweenAttackerAndKing.contains(currentSquare))
                movesAvoiding.add(currentMove);
        }

        return movesAvoiding;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList movesAllowedAtACheckedSituation(Player player,
                                                            Board field) throws Exception {

        Square squareKing = BoardUtil.getKingFieldByColour(colour, field);
        int row = squareKing.getRowIndex();
        int column = squareKing.getColumnIndex();
        ArrayList movesToFlee = new ArrayList();
        ArrayList fieldsToMove = Moves.listOfActuallyAllowedMoves(row, column, field.getSquares());

        for (int i = 0; i < fieldsToMove.size(); i++) {
            movesToFlee.add(new Move(squareKing, (Square) fieldsToMove.get(i)));
        }

        ArrayList movesAllowed = new ArrayList();
        ArrayList figuresGivingCheck = GameUtil.figuresGivingCheckToColour(colour, field);


        for (int i = 0; i < figuresGivingCheck.size(); i++) {
            Square currentSquare = (Square) figuresGivingCheck.get(i);
            movesAllowed.addAll(MovesToAvoidCheckMateByBlockingAttackersPath(
                    currentSquare, field));
            movesAllowed.addAll(listOfMovesToThrowAttacker(colour, field));

        }
        movesAllowed.addAll(movesToFlee);

        return movesAllowed;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList listOfMovesToThrowAttacker(String colour,
                                                       Board field) throws Exception {
        ArrayList moveArray = getMovesForPlayer(field, colour);
        ArrayList figuresGivingCheck = GameUtil.figuresGivingCheckToColour(colour, field);
        ArrayList allowedMoves = new ArrayList();

        for (int i = 0; i < moveArray.size(); i++) {
            Move currentMove = (Move) moveArray.get(i);
            Square currentDestinationSquare = currentMove.getTo();
            if (figuresGivingCheck.contains(currentDestinationSquare))
                allowedMoves.add(currentMove);
        }

        return allowedMoves;
    }

    @SuppressWarnings("unchecked")
    public static boolean allowedToThrowAttacker(String colour, Square square,
                                                 Board playingField) throws Exception {
        ArrayList movesAllowed = threatendFieldsByActuallyAllowedMoves(colour, playingField
                        .getSquares());

        return movesAllowed.contains(square);
    }

    /**
     * Checks if the king of the given colour is allowed to throw the figur on
     * "currentField" uses threatendFieldsByColourHelpClass!
     *
     * @param currentSquare
     * @param colourKing
     * @param squareArray
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static boolean isKingAllowedToThrowField(Square currentSquare,
                                                    String colourKing, Square[][] squareArray) throws Exception {

        String oppositeColour = "white";
        if (colourKing.equals("white"))
            oppositeColour = "black";
        String originalType = currentSquare.getPiece().getType();
        String originalColour = currentSquare.getPiece().getColour();
        currentSquare.setEmpty();
        currentSquare.setPiece(new Pawn());
        ArrayList Fields = threatenedFieldsByColourConsideringEveryAttackedField(
                        oppositeColour, squareArray);// 9.4
        // threatendFieldsByColourHelpClass
        // ->
        // threatenedFieldsByColourConsideringEveryAttackedField
        if (Fields.contains(currentSquare)) {
            currentSquare.setPiece(new Piece(originalType, originalColour));
            return false;
        } else {
            currentSquare.setPiece(new Piece(originalType, originalColour));
            return true;
        }

    }
}
