package chess_game;

import java.util.ArrayList;

public class MoveClass {
	
	/**
	 * Prints all allowed moves for a colour.
	 * 
	 * @param field
	 * @param colour
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void printAllAllowedMovesByColour(PlayingField field, String colour)
			throws Exception {
		ArrayList moveArrayList = moveArrayByColour(field, colour);
		System.out
		.println("\n\n=========================================================================");
		System.out.println("\n	List of possible Moves ("+moveArrayList.size()+") for "+ colour+":\n");
		
		for (int i = 0; i < moveArrayList.size(); i++) {
			printMove((Move) moveArrayList.get(i));
		}
		System.out.println("\n\n");
	}

	/**
	 * Prints a move at the following format:
	 * 
	 * A1 -> A2 (rook)
	 * 
	 * @param move
	 * @throws Exception
	 */
	public static void printMove(Move move) throws Exception {
		System.out.println("	"+move.getStartField().getCharRow() + ""
				+ move.getStartField().getIntColumn() + " -> "
				+ move.getDestinationField().getCharRow() + ""
				+ move.getDestinationField().getIntColumn() + "  ("
				+ move.getStartField().getFigure().getType() + ")");
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
	public static ArrayList moveArrayByColour(PlayingField field, String colour)
			throws Exception {

		Field[][] fieldArray = field.getFieldArray();
		ArrayList moveArrayList = new ArrayList();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!(fieldArray[i][j].getEmpty()))
					if (fieldArray[i][j].getFigure().getColour() == colour)
						for (int l = 0; l < 8; l++) {
							for (int m = 0; m < 8; m++) {
								if (PossibleMoves.listOfActuallyAllowedMoves(i, j, fieldArray)
										.contains(fieldArray[l][m]))
									moveArrayList
											.add(new Move(fieldArray[i][j],
													fieldArray[l][m]));
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
	public static ArrayList moveArrayByColourOfFiguresGivingCheck(PlayingField field, String colour)
			throws Exception {

		Field[][] fieldArray = field.getFieldArray();
		ArrayList moveArrayList = new ArrayList();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!(fieldArray[i][j].getEmpty()))
					if (fieldArray[i][j].getFigure().getColour() == colour)
						for (int l = 0; l < 8; l++) {
							for (int m = 0; m < 8; m++) {
								if (PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, fieldArray)
										.contains(fieldArray[l][m]))
									moveArrayList
											.add(new Move(fieldArray[i][j],
													fieldArray[l][m]));
							}
						}
			}
		}
		return moveArrayList;

	}

}
