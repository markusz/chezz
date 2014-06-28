package utils;

import model.Move;
import chess_game.PossibleMoves;
import model.Board;
import model.Square;
import model.pieces.Piece;

import java.util.ArrayList;

public class MoveUtil {

    public static boolean ValidMoveByMovementRules(int startRow,
                                                   int startColumn, int destinationRow, int destinationColumn,
                                                   Board playField) throws Exception {
        Square[][] square = playField.getFieldArray();
        Square startingSquare = square[startRow][startColumn];
        Square destinationSquare = square[destinationRow][destinationColumn];
        String colour = startingSquare.getPiece().getColour();
        String startingFieldString = startingSquare.getCharRow() + ""
                + startingSquare.getIntColumn();
        String destinationFieldString = destinationSquare.getCharRow() + ""
                + destinationSquare.getIntColumn();
        boolean allowed = false;

        if (GameUtil.isGivenColourCheck(colour, square)) {
            if (validMoveAtCheckedSituation(startingFieldString,
                    destinationFieldString, playField))
                allowed = true;

            else
                ;
        } else {
            if (PossibleMoves.listOfActuallyAllowedMoves(startRow,
                    startColumn, square).contains(
                    square[destinationRow][destinationColumn]))
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

        Square[][] squareArray = field.getFieldArray();
        Square startSquare = squareArray[startingFieldHelp[0]][startingFieldHelp[1]];
        Square destinationField_Square = squareArray[destinationFieldHelp[0]][destinationFieldHelp[1]];
        String colour = startSquare.getPiece().getColour();


        ArrayList validMoves = movesAllowedAtACheckedSituation(
                colour, field);
        boolean contains = false;
        for (int i = 0; i < validMoves.size(); i++) {
            Move currentMove = (Move) validMoves.get(i);
            if (currentMove.getStartSquare().equals(startSquare)
                    && currentMove.getDestinationSquare().equals(
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
    public static ArrayList moveArrayByColour(Board field, String colour)
            throws Exception {

        Square[][] squareArray = field.getFieldArray();
        ArrayList moveArrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(squareArray[i][j].getEmpty()))
                    if (squareArray[i][j].getPiece().getColour().equals(colour))
                        for (int l = 0; l < 8; l++) {
                            for (int m = 0; m < 8; m++) {
                                if (PossibleMoves.listOfActuallyAllowedMoves(i, j, squareArray)
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

        Square[][] squareArray = field.getFieldArray();
        ArrayList moveArrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(squareArray[i][j].getEmpty()))
                    if (squareArray[i][j].getPiece().getColour().equals(colour))
                        for (int l = 0; l < 8; l++) {
                            for (int m = 0; m < 8; m++) {
                                if (PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, squareArray)
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
                    for (int counter = 0; counter < PossibleMoves.listOfActuallyAllowedMoves(i, j, square).size(); counter++) {
                        if (!(moves.contains((PossibleMoves.listOfActuallyAllowedMoves(i, j,
                                square).get(counter)))))
                            moves.add((PossibleMoves.listOfActuallyAllowedMoves(i, j, square)
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
                    for (int counter = 0; counter < PossibleMoves.listOfActuallyAllowedMoves(i, j, square).size(); counter++) {
                        if (!(moves.contains((PossibleMoves.listOfActuallyAllowedMoves(i, j,
                                square).get(counter)))))
                            moves.add((PossibleMoves.listOfActuallyAllowedMoves(i, j, square)
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
                if (square[i][j].getEmpty()) {
                    ;
                } else if (square[i][j].getPiece().getColour().equals(colour)) {
                    for (int counter = 0; counter < PossibleMoves.listOfMovesConsideringAllThreatendFields(i, j, square).size(); counter++) {
                        if (!(moves.contains((PossibleMoves.listOfMovesConsideringAllThreatendFields(i, j, square).get(counter)))))
                            moves.add((PossibleMoves.listOfMovesConsideringAllThreatendFields(i, j, square).get(counter)));
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
                if (square[i][j].getEmpty()) {
                    ;
                } else if (square[i][j].getPiece().getColour().equals(colour)) {
                    for (int counter = 0; counter < PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, square).size(); counter++) {
                        if (!(moves.contains((PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j,
                                square).get(counter)))))
                            moves.add((PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, square)
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
        ArrayList movesAllowedForDefender = moveArrayByColour(field,
                defenderColour);
        ArrayList fieldsBetweenAttackerAndKing = BoardUtil.listOfFieldsBetweenAttackerAndKing(
                attacker, field);

        for (int i = 0; i < movesAllowedForDefender.size(); i++) {
            Move currentMove = (Move) movesAllowedForDefender.get(i);
            Square currentSquare = currentMove.getDestinationSquare();
            if (fieldsBetweenAttackerAndKing.contains(currentSquare))
                movesAvoiding.add(currentMove);
        }

        return movesAvoiding;

    }

    @SuppressWarnings("unchecked")
    public static ArrayList movesAllowedAtACheckedSituation(String colour,
                                                            Board field) throws Exception {

        Square squareKing = BoardUtil.getKingFieldByColour(colour, field);
        int row = squareKing.getRowIndex();
        int column = squareKing.getColumnIndex();
        ArrayList movesToFlee = new ArrayList();
        ArrayList fieldsToMove = PossibleMoves.listOfActuallyAllowedMoves(row, column, field.getFieldArray());

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
        ArrayList moveArray = moveArrayByColour(field, colour);
        ArrayList figuresGivingCheck = GameUtil.figuresGivingCheckToColour(colour, field);
        ArrayList allowedMoves = new ArrayList();

        for (int i = 0; i < moveArray.size(); i++) {
            Move currentMove = (Move) moveArray.get(i);
            Square currentDestinationSquare = currentMove.getDestinationSquare();
            if (figuresGivingCheck.contains(currentDestinationSquare))
                allowedMoves.add(currentMove);
        }

        return allowedMoves;
    }

    @SuppressWarnings("unchecked")
    public static boolean allowedToThrowAttacker(String colour, Square square,
                                                 Board playingField) throws Exception {
        ArrayList movesAllowed = threatendFieldsByActuallyAllowedMoves(colour, playingField
                        .getFieldArray());

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
