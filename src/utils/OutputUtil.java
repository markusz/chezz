package utils;

import model.Board;
import model.Player;
import model.Square;
import model.Move;

import java.util.ArrayList;

/**
 * Created by Markus on 28.06.2014.
 */
public class OutputUtil {
  private static String emptyField = "   |";
  private static String possibleField = " x |";
  private static String possibleFieldThrow = " + |";

  /**
     * Prints all allowed moves for a colour.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void printAllAllowedMovesByPlayer(Board board, Player player)
            throws Exception {
        ArrayList moveArrayList = MoveUtil.getMovesForPlayer(board, player);
        System.out
                .println("\n\n=========================================================================");
        System.out.println("\n	List of possible Moves (" + moveArrayList.size() + ") for " + player + ":\n");

        for (int i = 0; i < moveArrayList.size(); i++) {
            printMove((Move) moveArrayList.get(i));
        }
        System.out.println("\n\n");
    }

    /**
     * Prints a move at the following format:
     * <p/>
     * A1 -> A2 (rook)
     *
     * @param move
     * @throws Exception
     */
    public static void printMove(Move move) throws Exception {
        System.out.println("	" + move.getFrom().getCharRow() + ""
                + move.getFrom().getIntColumn() + " -> "
                + move.getTo().getCharRow() + ""
                + move.getTo().getIntColumn() + "  ("
                + move.getFrom().getPiece() + ")");
    }

    /**
     * Prints if the given colour is check
     *
     * @param colour
     * @param field
     * @throws Exception
     */
    public static void printIsCheck(String colour, Board field)
            throws Exception {
        System.out
                .println("\n\n=========================================================================\n");
        System.out.println("	Checking Threat-Status for " + colour + "...");
        if (GameUtil.isGivenColourCheck(colour, field))
            System.out.println("\n	" + colour + " is check!");
        else
            System.out.println("\n	" + colour + " is not check");
    }

    /**
     * Prints if the given colour is check mate.
     *
     * @param colour
     * @param field
     * @throws Exception
     */
    public static void printIsCheckMate(String colour, Board field)
            throws Exception {
        if (GameUtil.isCheckMate(colour, field))
            System.out.println("\n	" + colour + " is check mate!\n");
        else
            System.out.println("\n	" + colour + " is not check mate\n");
    }

    /**
     * Prints all fields that are giving check to the given colour
     *
     * @param colour
     * @param field
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void printListOfFiguresGivingCheckToColour(String colour,
                                                             Board field) throws Exception {
        ArrayList temp = GameUtil.figuresGivingCheckToColour(colour, field);
        if (temp.size() == 0)
            System.out.println("	No figures giving check to " + colour);
        else {
            System.out
                    .println("\n	The figures on the following fields\n	are giving check to "
                            + colour + ":\n");
            for (int i = 0; i < temp.size(); i++) {
                Square current = (Square) temp.get(i);
                if (!current.isEmpty())
                    System.out.println("	" + current.getCharRow() + ""
                            + current.getIntColumn());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void printMovesToAvoidCheckMateByBlockingAttackersPath(
            Square attacker, Board field) throws Exception {
        ArrayList moves = MoveUtil.MovesToAvoidCheckMateByBlockingAttackersPath(
                attacker, field);
        for (int i = 0; i < moves.size(); i++) {
            Move currentMove = (Move) moves.get(i);
            System.out.println(currentMove.getFrom().getCharRow() + ""
                    + currentMove.getFrom().getIntColumn() + " -> "
                    + currentMove.getTo().getCharRow() + ""
                    + currentMove.getTo().getIntColumn());
        }
    }

  public static void printBoard(Board board) {
    System.out.println(getCurrentGameSituationAsString(board));
  }

  public static String getCurrentGameSituationAsString(Board field) {
    StringBuilder sb = new StringBuilder();
    sb.append("      	     A     B     C     D     E     F     G    H\n");
    for (int i = 0; i < 8; i++) {
      sb.append("	    |-------------------------------------------------|\n");
      sb.append("  	" + (8 - i) + "  |");
      for (int j = 0; j < 8; j++) {
        if (field.getSquares()[j][i].isEmpty()) {
          sb.append(emptyField);
        } else {
          sb.append(field.getSquares()[j][i].getPiece().getTextualRepresentation());
        }
      }
      sb.append("  " + (8 - i) + "  \n");
    }
    sb.append("      	    ------------------------------------------------\n");
    sb.append("      	     A     B     C     D     E     F     G    H\n\n");

    return sb.toString();
  }
}
