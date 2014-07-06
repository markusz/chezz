package chess_game;

import java.util.ArrayList;

/**
 * @author Markus
 *
 */
public class PossibleMoves {
	
	/**
	 * Returns a list of fields that are threatened by the colour.
	 * Important to determine Fields, the enemy King is not allowed to move on
	 * 
	 * @param row
	 * @param column
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList listOfMovesConsideringAllThreatendFields(int row, int column, Field[][] field)
			throws Exception {
		ArrayList possibleFields = new ArrayList();
		Field current = field[row][column];

		if (current.getType() == "pawn") {

			possibleFields.clear();
			
			return PawnClass.allFieldsThePawnThreateneds(row, column, possibleFields, field);

		} else

		if (current.getType() == "bishop") {
			possibleFields.clear();
			
			return BishopClass.fieldsThreatendByTheBishopSkippingKingsPosition(row, column, possibleFields, field);

		} else

		if (current.getType() == "knight") {
			possibleFields.clear();
			return KnightClass.knightMovesAllThreatenedFields(row, column, possibleFields, field);

		} else

		if (current.getType() == "king") {

			possibleFields.clear();
			return KingClass.kingMovesNotConsideringAttackedFields(row, column, possibleFields, field);

		} else

		if (current.getType() == "queen") {

			possibleFields.clear();
			
			ArrayList list = new ArrayList();
			list
					.addAll(RookClass.fieldsThreatendByTheRookSkippingKingsPosition(row, column, possibleFields,
							field));
			
			possibleFields.clear();
			list.addAll(BishopClass.fieldsThreatendByTheBishopSkippingKingsPosition(row, column, possibleFields,
					field));

			return list;

		} else

		if (current.getType() == "rook") {
			possibleFields.clear();
			
			return RookClass.fieldsThreatendByTheRookSkippingKingsPosition(row, column, possibleFields, field);
		} else
			throw new Exception(current.getType() + ": no such figure");
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList listOfMovesIgnoreKingHelpClass(int row, int column, Field[][] field)
			throws Exception {
		ArrayList possibleFields = new ArrayList();
		Field current = field[row][column];

		if (current.getType() == "pawn") {

			possibleFields.clear();
			
			return PawnClass.allFieldsThePawnThreateneds(row, column, possibleFields, field);

		} else

		if (current.getType() == "bishop") {
			possibleFields.clear();
			
			return BishopClass.fieldsThreatenedByTheBishop(row, column, possibleFields, field);

		} else

		if (current.getType() == "knight") {
			possibleFields.clear();
			return KnightClass.knightMovesActuallyAllowed(row, column, possibleFields, field);

		} else

		if (current.getType() == "king") {

			possibleFields.clear();
			return KingClass.kingMovesNotConsideringAttackedFields(row, column, possibleFields, field);

		} else

		if (current.getType() == "queen") {

			possibleFields.clear();
			//System.out.println("vor der ifqueen");
			ArrayList list = new ArrayList();
			list
					.addAll(RookClass.fieldsThreatenedByTheRook(row, column, possibleFields,
							field));
			
			possibleFields.clear();
			list.addAll(BishopClass.fieldsThreatenedByTheBishop(row, column, possibleFields,
					field));

			return list;

		} else

		if (current.getType() == "rook") {
			possibleFields.clear();
			//System.out.println("vor der ifrook");
			return RookClass.fieldsThreatenedByTheRook(row, column, possibleFields, field);
		} else
			throw new Exception(current.getType() + ": no such figure");
	}

	/**
	 * ArrayList of fields the figure is actually allowed to move
	 * @param row
	 * @param column
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList listOfActuallyAllowedMoves(int row, int column, Field[][] field)
			throws Exception {
		ArrayList possibleFields = new ArrayList();
		Field current = field[row][column];

		if (current.getType() == "pawn") {

			possibleFields.clear();
			return PawnClass.allFieldsThePawnIsActuallyAllowedToMove(row, column, possibleFields, field);

		} else

		if (current.getType() == "bishop") {
			possibleFields.clear();
			return BishopClass.fieldsActuallyAllowedForBishopToMove(row, column, possibleFields, field);

		} else

		if (current.getType() == "knight") {
			possibleFields.clear();
			return KnightClass.knightMovesActuallyAllowed(row, column, possibleFields, field);

		} else

		if (current.getType() == "king") {

			possibleFields.clear();
			return KingClass.kingMovesConsideringAttackedFields(row, column, possibleFields, field);

		} else

		if (current.getType() == "queen") {

			possibleFields.clear();
			ArrayList list = new ArrayList();
			list.addAll(BishopClass.fieldsActuallyAllowedForBishopToMove(row, column, possibleFields,
					field));
			possibleFields.clear();
			list
					.addAll(RookClass.fieldsActuallyAllowedForRookToMove(row, column, possibleFields,
							field));

			return list;

		} else

		if (current.getType() == "rook") {
			possibleFields.clear();
			return RookClass.fieldsActuallyAllowedForRookToMove(row, column, possibleFields, field);
		} else
			throw new Exception(current.getType() + ": no such figure");
	}

	@SuppressWarnings("unchecked")
	public static ArrayList listOfFieldsOnWhichAnEnemyKingIsCheck(int row, int column,
			Field[][] field) throws Exception {
		ArrayList possibleFields = new ArrayList();
		Field current = field[row][column];

		if (current.getType() == "pawn") {

			possibleFields.clear();
			return PawnClass.allFieldsThePawnThreateneds(row, column, possibleFields, field);

		} else

		if (current.getType() == "bishop") {
			possibleFields.clear();
			return BishopClass.fieldsThreatenedByTheBishop(row, column, possibleFields,
					field);

		} else

		if (current.getType() == "knight") {
			possibleFields.clear();
			return KnightClass.knightMovesCheck(row, column, possibleFields,
					field);

		} else

		if (current.getType() == "king") {

			possibleFields.clear();
			//9.4 kingMovesConsideringAttackedFields -> kingMovesNotConsideringAttackedFields
			return KingClass.kingMovesConsideringAttackedFields(row, column, possibleFields, field);

		} else

		if (current.getType() == "queen") {

			possibleFields.clear();
			ArrayList list = new ArrayList();
			list.addAll(BishopClass.fieldsThreatenedByTheBishop(row, column,
					possibleFields, field));
			possibleFields.clear();
			list.addAll(RookClass.fieldsThreatenedByTheRook(row, column, possibleFields,
					field));

			return list;

		} else

		if (current.getType() == "rook") {
			possibleFields.clear();
			return RookClass.fieldsThreatenedByTheRook(row, column, possibleFields, field);
		} else
			throw new Exception(current.getType() + ": no such figure");
	}

	public static boolean isCheckByFigure(int row_figure, int column_figure,
			int row_king, int column_king, Field[][] field) throws Exception {

		FieldEnsure.fieldFiller(field);
		if (field[row_king][column_king].getFigure() != null)
			return ((listOfFieldsOnWhichAnEnemyKingIsCheck(row_figure, column_figure, field))
					.contains(field[row_king][column_king]))
					&& ((field[row_figure][column_figure]).getFigure()
							.getColour() != (field[row_king][column_king])
							.getFigure().getColour());
		else
			return false;
	}


	public static void printListOfMovesByFigure(int row, int column, Field[][] field) throws Exception { 	/*unused*/

		FieldEnsure.fieldFiller(field);
		int counter = 0;
		System.out.println("\n	List of possible moves ("
				+ listOfActuallyAllowedMoves(row, column, field).size() + ") for the "
				+ field[row][column].getType() + " on "
				+ field[row][column].getCharRow() + ""
				+ (field[row][column].getIntColumn()) + "\n");
		while (counter < listOfActuallyAllowedMoves(row, column, field).size()) {
			Field temp = (Field) listOfActuallyAllowedMoves(row, column, field).get(counter);
			System.out.println(" " + (temp.getCharRow()) + ""
					+ ((temp.getIntColumn())));
			counter++;
		}

	}

	public static void printListOfActuallyAllowedFieldsToMoveByFigure(String fieldString,
			Field[][] field) throws Exception {

		FieldEnsure.fieldFiller(field);
		int[] fieldArray = InputTranslate.InputTranslateMethod(fieldString);
		int row = fieldArray[0];
		int column = fieldArray[1];
		int counter = 0;
		if (!(field[row][column].getEmpty())) {
			System.out.println("\n	List of possible moves ("
					+ listOfActuallyAllowedMoves(row, column, field).size() + ") for the "
					+ field[row][column].getType() + " on "
					+ field[row][column].getCharRow() + ""
					+ (field[row][column].getIntColumn()) + "\n");
			while (counter < listOfActuallyAllowedMoves(row, column, field).size()) {
				Field temp = (Field) listOfActuallyAllowedMoves(row, column, field).get(
						counter);
				System.out.println(" 	" + (temp.getCharRow()) + ""
						+ ((temp.getIntColumn())));
				counter++;
			}
		} else
			System.out.println("	<Invalid Order: No figure on " + fieldString
					+ ">");
		// System.out.println("\n");
	}

	
	public static void printListOfActuallyAllowedFieldsToMoveByColour(String colour,
			Field[][] field) throws Exception {

		FieldEnsure.fieldFiller(field);
		int counter = 0;
		System.out
				.println("\n\n=========================================================================");
		System.out.println("\n	List of reachable fields ("
				+ FieldsThreatendByColour
						.threatendFieldsByActuallyAllowedMoves(colour, field).size()
				+ ") for " + colour + "\n");
		while (counter < FieldsThreatendByColour.threatendFieldsByActuallyAllowedMoves(
				colour, field).size()) {
			Field temp = (Field) FieldsThreatendByColour
					.threatendFieldsByActuallyAllowedMoves(colour, field).get(counter);
			System.out.println("	" + (temp.getCharRow()) + ""
					+ ((temp.getIntColumn())));
			counter++;
		}
		System.out.print("\n");

	}

}
