package chess_game;

import model.Board;
import model.Square;
import utils.ChessNotationUtil;
import utils.MoveUtil;

import java.util.ArrayList;

public class PrintPlayingField {

    private static String emptyField = "   |";
    private static String king = " K |";
    private static String queen = " Q |";
    private static String knight = " k |";
    private static String rook = " r |";
    private static String bishop = " b |";
    private static String pawn = " p |";
    private static String kingBlack = ".K.|";
    private static String queenBlack = ".Q.|";
    private static String knightBlack = ".k.|";
    private static String rookBlack = ".r.|";
    private static String bishopBlack = ".b.|";
    private static String pawnBlack = ".p.|";
    private static String possibleField = " x |";
    private static String possibleFieldThrow = " + |";

    private static String kingString = " K  |";
    private static String queenString = " Q  |";
    private static String knightString = " k  |";
    private static String rookString = " r  |";
    private static String bishopString = " b  |";
    private static String kingBlackString = ".K. |";
    private static String queenBlackString = ".Q. |";
    private static String knightBlackString = ".k. |";
    private static String rookBlackString = ".r. |";
    private static String bishopBlackString = ".b. |";
    private static String pawnBlackString = ".p. |";
    private static String pawnString = " p  |";

    public static void printCurrentSituation(Board field) {
        /*if (field.getMovesCounter() == 1) {
            System.out.println("\n\n\n       Our playfield after "
					+ field.getMovesCounter() + " move\n");
		} else {
			System.out.println("\n\n\n       Our playfield after "
					+ field.getMovesCounter() + " moves\n");
		}*/
        System.out.println("\n\n       	     A   B   C   D   E   F   G   H");
        for (int i = 0; i < 8; i++) {
            System.out.println("	   |-------------------------------|");
            System.out.print("  	" + (8 - i) + "  |");
            for (int j = 0; j < 8; j++) {
                if (field.getFieldArray()[j][i].getEmpty())
                    System.out.print(emptyField);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("king"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(king);
                    else
                        System.out.print(kingBlack);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("queen"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(queen);
                    else
                        System.out.print(queenBlack);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("knight"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(knight);
                    else
                        System.out.print(knightBlack);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("rook"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(rook);
                    else
                        System.out.print(rookBlack);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("bishop"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(bishop);
                    else
                        System.out.print(bishopBlack);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("pawn"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(pawn);
                    else
                        System.out.print(pawnBlack);
                else
                    ;

            }
            System.out.print("  " + (8 - i) + "  ");
            System.out.println();
        }
        System.out.print("      	    --------------------------------\n");
        System.out.println("      	     A   B   C   D   E   F   G   H\n");
    }

    public static String printCurrentSituationString(Board field) {

        String rep = "";

        //mu.concat(rep);
        rep = (rep + "      	     A     B     C     D     E     F     G    H\n");
        for (int i = 0; i < 8; i++) {
            rep = (rep + "	    |-------------------------------------------------|\n");
            rep = (rep + "  	" + (8 - i) + "  |");

            //System.out.println(rep);
            for (int j = 0; j < 8; j++) {
                if (field.getFieldArray()[j][i].getEmpty())
                    rep = (rep + "    " + emptyField);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("king"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        rep = (rep + "  " + kingString);
                    else
                        rep = (rep + "  " + kingBlackString);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("queen"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        rep = (rep + "  " + queenString);
                    else
                        rep = (rep + "  " + queenBlackString);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("knight"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        rep = (rep + "  " + knightString);
                    else
                        rep = (rep + "  " + knightBlackString);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("rook"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        rep = (rep + "  " + rookString);
                    else
                        rep = (rep + "  " + rookBlackString);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("bishop"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        rep = (rep + "  " + bishopString);
                    else
                        rep = (rep + "  " + bishopBlackString);
                else if (field.getFieldArray()[j][i].getPiece().getType().equals("pawn"))
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        rep = (rep + "  " + pawnString);
                    else
                        rep = (rep + "  " + pawnBlackString);
                else
                    ;

            }
            rep = (rep + "  " + (8 - i) + "  \n");

        }
        rep = (rep + "      	    ------------------------------------------------\n");
        rep = (rep + "      	     A     B     C     D     E     F     G    H\n\n");

        return rep;
    }

    @SuppressWarnings("unchecked")
    public static void printCurrentSituationAndMarkPossibleFields(
            Board field, String fieldOfFigure) throws Exception {


        int[] fields = ChessNotationUtil.convertFieldNameToIndexes(fieldOfFigure);
        int row = fields[0];
        int column = fields[1];

        ArrayList possibleFields = PossibleMoves.listOfActuallyAllowedMoves(row, column, field
                .getFieldArray());

        System.out.println("\n\n       	     A   B   C   D   E   F   G   H");
        for (int i = 0; i < 8; i++) {
            System.out.println("	   |-------------------------------|");
            System.out.print("  	" + (8 - i) + "  |");
            for (int j = 0; j < 8; j++) {
                Square currentSquare = (field).getFieldArray()[j][i];
                if ((field.getFieldArray()[j][i]).getEmpty() || possibleFields.contains(currentSquare)) {
                    if (possibleFields.contains(currentSquare))
                        if ((field.getFieldArray()[j][i]).getEmpty())
                            System.out.print(possibleField);
                        else
                            System.out.print(possibleFieldThrow);
                    else
                        System.out.print(emptyField);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("king")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(king);
                    else
                        System.out.print(kingBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("queen")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(queen);
                    else
                        System.out.print(queenBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("knight")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(knight);
                    else
                        System.out.print(knightBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("rook")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(rook);
                    else
                        System.out.print(rookBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("bishop")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(bishop);
                    else
                        System.out.print(bishopBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("pawn")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(pawn);
                    else
                        System.out.print(pawnBlack);
                } else
                    ;

            }
            System.out.print("  " + (8 - i) + "  ");
            System.out.println();
        }
        System.out.print("      	    --------------------------------\n");
        System.out.println("      	     A   B   C   D   E   F   G   H");
        System.out.println("\n	Allowed moves for the " + field.getFieldArray()[row][column].getPiece().getColour() + " "
                + field.getFieldArray()[row][column].getPiece().getType() + " on " + fieldOfFigure + "\n	have been marked with x\n	Fields on which an opposite figure can be \n	thrown are marked with +.\n");
    }

    @SuppressWarnings("unchecked")
    public static void printCurrentSituationAndMarkPossibleFieldsForColour(
            Board field, String colour) throws Exception {
		/*if (field.getMovesCounter() == 1) {
			System.out.println("\n\n\n\n\n       Our playfield after "
					+ field.getMovesCounter() + " move");
		} else {
			System.out.println("\n\n\n\n\n       Our playfield after "
					+ field.getMovesCounter() + " moves");
		}*/
        //System.out.println("\n\n     --------------------------------");

		/*int[] fields = InputUtil.convertFieldNameToIndexes(colour);
		int row = fields[0];
		int column = fields[1];*/


        // System.out.print(fields[0]);
        ArrayList possibleFields = MoveUtil.threatendFieldsByActuallyAllowedMoves(colour, field.getFieldArray());
        // System.out.println(possibleFields);
        System.out.println("\n\n       	     A   B   C   D   E   F   G   H");
        for (int i = 0; i < 8; i++) {
            System.out.println("	   |-------------------------------|");
            System.out.print("  	" + (8 - i) + "  |");
            for (int j = 0; j < 8; j++) {
                Square currentSquare = (field).getFieldArray()[j][i];
                if ((field.getFieldArray()[j][i]).getEmpty() || possibleFields.contains(currentSquare)) {
                    if (possibleFields.contains(currentSquare))
                        if ((field.getFieldArray()[j][i]).getEmpty())
                            System.out.print(possibleField);
                        else
                            System.out.print(possibleFieldThrow);
                    else
                        System.out.print(emptyField);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("king")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(king);
                    else
                        System.out.print(kingBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("queen")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(queen);
                    else
                        System.out.print(queenBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("knight")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(knight);
                    else
                        System.out.print(knightBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("rook")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(rook);
                    else
                        System.out.print(rookBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("bishop")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(bishop);
                    else
                        System.out.print(bishopBlack);
                } else if (field.getFieldArray()[j][i].getPiece().getType().equals("pawn")) {
                    if (field.getFieldArray()[j][i].getPiece().getColour().equals("white"))
                        System.out.print(pawn);
                    else
                        System.out.print(pawnBlack);
                } else
                    ;

            }
            System.out.print("  " + (8 - i) + "  ");
            System.out.println();
        }
        System.out.print("      	    --------------------------------\n");
        System.out.println("      	     A   B   C   D   E   F   G   H");
        System.out.println("\n	Allowed moves for " + colour + " have been marked with x\n	Fields on which on opposite figure can be \n	thrown are marked with +.\n");
    }
}
