package chess_game;

import java.util.ArrayList;

/**
 * @author Markus
 *
 */
public class FieldsThreatendByColour {

	@SuppressWarnings("unchecked")
	public static ArrayList threatendFieldsByActuallyAllowedMoves(String colour,
			Field[][] field) throws Exception {

		FieldEnsure.fieldFiller(field);

		ArrayList moves = new ArrayList();

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				if (field[i][j].getFigure() == null) {
					;
				} else if (field[i][j].getFigure().getColour() == colour) {
					for (int counter = 0; counter < PossibleMoves.listOfActuallyAllowedMoves(i, j, field).size(); counter++) {
						if (!(moves.contains((PossibleMoves.listOfActuallyAllowedMoves(i, j,
								field).get(counter)))))
							moves.add((PossibleMoves.listOfActuallyAllowedMoves(i, j, field)
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
	public static ArrayList threatendFieldsByColourHelpClass(String colour,Field[][] field) throws Exception { /*unused*/

		FieldEnsure.fieldFiller(field);

		ArrayList moves = new ArrayList();

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				if (field[i][j].getFigure() == null) {
					;
				} else if (field[i][j].getFigure().getColour() == colour) {
					// 9.4 listOfMovesIgnoreKingHelpClass -> listOfActuallyAllowedMoves
					for (int counter = 0; counter < PossibleMoves.listOfActuallyAllowedMoves(i, j, field).size(); counter++) {
						if (!(moves.contains((PossibleMoves.listOfActuallyAllowedMoves(i, j,
								field).get(counter)))))
							moves.add((PossibleMoves.listOfActuallyAllowedMoves(i, j, field)
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
	 * 
	 * -> fields the enemy king is not allowed to move
	 * 
	 * @param colour
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList threatenedFieldsByColourConsideringEveryAttackedField(String colour,
			Field[][] field) throws Exception {

		FieldEnsure.fieldFiller(field);

		ArrayList moves = new ArrayList();

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				if (field[i][j].getEmpty()) {
					;
				} else if (field[i][j].getFigure().getColour() == colour) {
					for (int counter = 0; counter < PossibleMoves.listOfMovesConsideringAllThreatendFields(i, j, field).size(); counter++) {
						if (!(moves.contains((PossibleMoves.listOfMovesConsideringAllThreatendFields(i, j, field).get(counter)))))
							moves.add((PossibleMoves.listOfMovesConsideringAllThreatendFields(i, j, field).get(counter)));
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
			Field[][] field) throws Exception {

		FieldEnsure.fieldFiller(field);

		ArrayList moves = new ArrayList();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (field[i][j].getEmpty()) {
					;
				} else if (field[i][j].getFigure().getColour() == colour) {
					for (int counter = 0; counter < PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, field).size(); counter++) {
						if (!(moves.contains((PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j,
								field).get(counter)))))
							moves.add((PossibleMoves.listOfFieldsOnWhichAnEnemyKingIsCheck(i, j, field)
									.get(counter)));
						else
							;
					}

				}

			}
		}
		return moves;
	}

}
