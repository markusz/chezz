package utils;

import model.Board;
import model.Player;
import model.Square;
import model.Move;

import java.util.ArrayList;
import java.util.Set;

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
    public static void printAllAllowedMovesByPlayer(Board board, Player player) throws Exception {
		Set<Move> moves = player.getAllMoves();
        System.out
                .println("\n\n=========================================================================");
        System.out.println("\n	List of possible Moves (" + moves.size() + ") for " + player + ":\n");

		for (Move move : moves) {
			System.out.println(move.toString());
		}
		System.out.println("\n\n");
    }

    /**
     * Prints if the given colour is check
     *
     * @throws Exception
     */
    public static void printIsCheck(Player player)
            throws Exception {
        System.out
                .println("\n\n=========================================================================\n");
        System.out.println("	Checking Threat-Status for " + player + "...");
        if (player.isCheck())
            System.out.println("\n	" + player + " is check!");
        else
            System.out.println("\n	" + player + " is not check");
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
