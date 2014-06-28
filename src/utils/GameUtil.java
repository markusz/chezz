package utils;

import logic.Moves;
import model.Board;
import model.Square;
import model.Move;

import java.util.ArrayList;

/**
 * Created by Markus on 28.06.2014.
 */
public class GameUtil {
    /**
     * Checks if the given colour is check mate.
     *
     * @param colour
     * @param field
     * @return
     * @throws Exception
     */
    public static boolean isCheckMate(String colour, Board field)
            throws Exception {

        boolean checkMate = false;
        Square kingSquare = BoardUtil.getKingFieldByColour(colour, field);
        int row = kingSquare.getRowIndex();
        int column = kingSquare.getColumnIndex();

        if ((Moves.listOfActuallyAllowedMoves(row, column, field
                .getSquares()).isEmpty())) {
            if (figuresGivingCheckToColour(colour, field).size() > 1) {
                checkMate = true;
            } else {
                if ((figuresGivingCheckToColour(colour, field).size() > 0)) {
                    Square attackingSquare = (Square) figuresGivingCheckToColour(colour, field).get(0);
                    if ((MoveUtil.allowedToThrowAttacker(colour, attackingSquare, field))
                            || !(MoveUtil.MovesToAvoidCheckMateByBlockingAttackersPath(
                            attackingSquare, field).isEmpty()))
                        checkMate = false;
                    else
                        checkMate = true;
                }
            }

        } else
            ;

        return checkMate;
    }

    /**
     * Checks if the given colour is check.
     *
     * @param colour
     * @param square
     * @return
     * @throws Exception
     */
    public static boolean isGivenColourCheck(String colour, Square[][] square)
            throws Exception {
        String colourOpposite = "white";

        if (colour.equals("white"))
            colourOpposite = "black";

        if (MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck(
                colourOpposite, square).contains(
                BoardUtil.getKingFieldByColour(colour, square)))
            return true;
        else
            return false;

    }

    public static boolean isGivenColourCheck(String colour, Board field)
            throws Exception {
        String colourOpposite = "white";

        if (colour.equals("white"))
            colourOpposite = "black";

        if (MoveUtil.threatenedFieldsOnWhichAnEnemyKingIsCheck(
                colourOpposite, field.getSquares()).contains(
                BoardUtil.getKingFieldByColour(colour, field)))
            return true;
        else
            return false;

    }

    /**
     * UNUSED
     *
     * @param row_figure
     * @param column_figure
     * @param row_king
     * @param column_king
     * @param square
     * @return
     * @throws Exception
     */
    public static boolean isCheckByFigure(int row_figure, int column_figure,
                                          int row_king, int column_king, Square[][] square) throws Exception { /* unused */

        BoardUtil.fieldFiller(square);
        if (square[row_king][column_king].getPiece() != null)
            return ((Moves.listOfMovesConsideringAllThreatendFields(
                    row_figure, column_figure, square))
                    .contains(square[row_king][column_king]))
                    && (!(square[row_figure][column_figure]).getPiece()
                    .getColour().equals((square[row_king][column_king])
                            .getPiece().getColour()));
        else
            return false;
    }

    /**
     * Returns all fields that are giving check to the given colour
     *
     * @param colour
     * @param field
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static ArrayList figuresGivingCheckToColour(String colour,
                                                       Board field) throws Exception {
        Square kingSquare = BoardUtil.getKingFieldByColour(colour, field);
        ArrayList figuresGivingCheck = new ArrayList();
        ArrayList threatedFields;
        if (colour.equals("white"))
            threatedFields = MoveUtil.moveArrayByColourOfFiguresGivingCheck(
                    field, "black");
        else
            threatedFields = MoveUtil.moveArrayByColourOfFiguresGivingCheck(
                    field, "white");

        for (int i = 0; i < threatedFields.size(); i++) {
            Move temp = (Move) threatedFields.get(i);
            Square currentSquare = temp.getDestinationSquare();
            if ((currentSquare == kingSquare))

                figuresGivingCheck.add(temp.getStartSquare());
        }
        return figuresGivingCheck;

    }
}
