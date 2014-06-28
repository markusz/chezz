package model.pieces;

import model.Field;
import utils.MoveUtil;

import java.util.ArrayList;

public class Queen {

    /**
     * Adds a field if the king is actually allowed to move there
     * <p/>
     * DOES check if the king is allowed to capture the figure if non-empty
     * field DOES check if the king is allowed to move to the field, considering
     * attacked fields of the opponent
     * <p/>
     * if the field is empty, isThreatendByWhite()/isThreatendByBlack() is used
     * to determine if the king is allowed to move there
     * <p/>
     * if the field is taken by a figure of the enemy, it is checked via
     * .isKingAllowedToThrowField if the figure can be captured.
     *
     * @param row
     * @param column
     * @param colour
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList addKingMoveConsideringAttackedFields(int row,
                                                                 int column, String colour, Field[][] field) throws Exception {

        // FieldEnsure.fieldFiller(field);

        ArrayList temp = new ArrayList();
        temp.clear();

        if (!(row < 0 || column < 0 || row > 7 || column > 7)) {
            boolean opponentAttacksField = field[row][column]
                    .isThreatendByWhite();
            if (colour.equals("white"))
                opponentAttacksField = field[row][column].isThreatendByBlack();
            if (field[row][column].getEmpty()) {
                if (!opponentAttacksField)
                    temp.add(field[row][column]);

            } else {
                if (!field[row][column].getFigure().getColour().equals(colour) && MoveUtil.isKingAllowedToThrowField(field[row][column], colour, field)) {
                    temp.add(field[row][column]);

                } else
                    ;
            }

        }
        return temp;

    }

    /**
     * Adds a field if it is empty or if the colour of the figure on it is
     * different from the king's
     * <p/>
     * DOES NOT check if the king is allowed to capture the figure if non-empty
     * field DOES NOT check if the king is allowed to move to the field,
     * considering attacked fields of the opponent
     *
     * @param row
     * @param column
     * @param colour
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList addKingMoveNotConsideringAttackedFields(int row, int column,
                                                                    String colour, Field[][] field) throws Exception {

        // FieldEnsure.fieldFiller(field);

        ArrayList temp = new ArrayList();
        temp.clear();

        if (!(row < 0 || column < 0 || row > 7 || column > 7)) {
            if (field[row][column].getEmpty()) {
                temp.add(field[row][column]);
            } else {
                if (!field[row][column].getFigure().getColour().equals(colour)) {
                    temp.add(field[row][column]);
                } else
                    ;
            }
        }
        return temp;
    }

    /**
     * Lists Field the king is allowed to move NOT considering that the king is
     * not to allowed to move to a dangered field Is needed validate the others
     * king movement. ensures that a king can not move on to a field that is
     * attacked by the other king
     *
     * @param row
     * @param column
     * @param moves
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList kingMovesNotConsideringAttackedFields(int row, int column,
                                                                  ArrayList moves, Field[][] field) throws Exception {

        String colour = field[row][column].getFigure().getColour();

        moves.addAll(addKingMoveNotConsideringAttackedFields(row + 1, column, colour, field));

        moves.addAll(addKingMoveNotConsideringAttackedFields(row + 1, column + 1, colour, field));

        moves.addAll(addKingMoveNotConsideringAttackedFields(row - 1, column, colour, field));

        moves.addAll(addKingMoveNotConsideringAttackedFields(row - 1, column + 1, colour, field));

        moves.addAll(addKingMoveNotConsideringAttackedFields(row, column - 1, colour, field));

        moves.addAll(addKingMoveNotConsideringAttackedFields(row + 1, column - 1, colour, field));

        moves.addAll(addKingMoveNotConsideringAttackedFields(row, column + 1, colour, field));

        moves.addAll(addKingMoveNotConsideringAttackedFields(row - 1, column - 1, colour, field));/**/

        return moves;

    }

    /**
     * Lists fields where the king is actually allowed to move
     *
     * @param row
     * @param column
     * @param moves
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList kingMovesConsideringAttackedFields(int row, int column, ArrayList moves,
                                                               Field[][] field) throws Exception {

        String colour = field[row][column].getFigure().getColour();

        moves.addAll(addKingMoveConsideringAttackedFields(row + 1, column,
                colour, field));

        moves.addAll(addKingMoveConsideringAttackedFields(row + 1, column + 1,
                colour, field));

        moves.addAll(addKingMoveConsideringAttackedFields(row - 1, column,
                colour, field));

        moves.addAll(addKingMoveConsideringAttackedFields(row - 1, column + 1,
                colour, field));

        moves.addAll(addKingMoveConsideringAttackedFields(row, column - 1,
                colour, field));

        moves.addAll(addKingMoveConsideringAttackedFields(row + 1, column - 1,
                colour, field));

        moves.addAll(addKingMoveConsideringAttackedFields(row, column + 1,
                colour, field));

        moves.addAll(addKingMoveConsideringAttackedFields(row - 1, column - 1,
                colour, field));/**/

        return moves;

    }
}
