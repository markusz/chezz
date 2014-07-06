package chess_game;

import java.util.ArrayList;

public class CheckClass {

	/**
	 * Lists all fields between an attacker and the attacked king
	 * 
	 * Can only be performed on a rook, a bishop or a queen;
	 * 
	 * Is used to determine, if check mate can be avioded by blocking an enemy
	 * figures attack path.
	 * 
	 * 
	 * @param enemyFigure
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList listOfFieldsBetweenAttackerAndKing(
			Field enemyFigure, PlayingField field) throws Exception {
		ArrayList listOfFields = new ArrayList();

		if (enemyFigure.getFigure().getType() == "rook")
			listOfFields = fieldsBetweenKingAndEnemyRook(enemyFigure, field);
		else if (enemyFigure.getFigure().getType() == "bishop")
			listOfFields = fieldsBetweenKingAndEnemyBishop(enemyFigure, field);
		else if (enemyFigure.getFigure().getType() == "queen")
			listOfFields = fieldsBetweenKingAndEnemyQueen(enemyFigure, field);

		return listOfFields;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList MovesToAvoidCheckMateByBlockingAttackersPath(
			Field attacker, PlayingField field) throws Exception {

		String attackerColour = attacker.getFigure().getColour();
		String defenderColour = "white";

		if (attackerColour == "white")
			defenderColour = "black";

		ArrayList movesAvoiding = new ArrayList();
		ArrayList movesAllowedForDefender = MoveClass.moveArrayByColour(field,
				defenderColour);
		ArrayList fieldsBetweenAttackerAndKing = listOfFieldsBetweenAttackerAndKing(
				attacker, field);

		for (int i = 0; i < movesAllowedForDefender.size(); i++) {
			Move currentMove = (Move) movesAllowedForDefender.get(i);
			Field currentField = currentMove.getDestinationField();
			if (fieldsBetweenAttackerAndKing.contains(currentField))
				movesAvoiding.add(currentMove);
		}

		return movesAvoiding;

	}

	@SuppressWarnings("unchecked")
	public static void printMovesToAvoidCheckMateByBlockingAttackersPath(
			Field attacker, PlayingField field) throws Exception {
		ArrayList moves = MovesToAvoidCheckMateByBlockingAttackersPath(
				attacker, field);
		for (int i = 0; i < moves.size(); i++) {
			Move currentMove = (Move) moves.get(i);
			System.out.println(currentMove.getStartField().getCharRow() + ""
					+ currentMove.getStartField().getIntColumn() + " -> "
					+ currentMove.getDestinationField().getCharRow() + ""
					+ currentMove.getDestinationField().getIntColumn());
		}
	}

	/**
	 * only performed on a bishop giving check
	 * 
	 * @param enemyBishopField
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsBetweenKingAndEnemyBishop(
			Field enemyBishopField, PlayingField field) throws Exception {

		Field kingField;

		if (enemyBishopField.getFigure().getColour() == "white") {
			kingField = getKingFieldByColour("black", field);
		} else {
			kingField = getKingFieldByColour("white", field);
		}

		int rowBishopField = enemyBishopField.getRow();
		int columnBishopField = enemyBishopField.getColumn();
		int rowKingField = kingField.getRow();
		int columnKingField = kingField.getColumn();
		int rowDistance;
		int columnDistance;
		ArrayList fields = new ArrayList();
		rowDistance = rowBishopField - rowKingField;
		Field[][] fieldArray = field.getFieldArray();
		columnDistance = columnBishopField - columnKingField;

		if (Math.abs(rowDistance) == Math.abs(columnDistance)) {
			if (rowDistance < 0 && columnDistance < 0) {
				for (int i = 0; i - 1 > rowDistance; i--) {
					rowBishopField++;
					columnBishopField++;
					fields.add(fieldArray[rowBishopField][columnBishopField]);

				}
			} else

			if (rowDistance < 0 && columnDistance > 0) {
				for (int i = 0; i - 1 > rowDistance; i--) {
					rowBishopField++;
					columnBishopField--;
					fields.add(fieldArray[rowBishopField][columnBishopField]);

				}
			} else

			if (rowDistance > 0 && columnDistance < 0) {
				for (int i = 0; i + 1 < rowDistance; i++) {
					rowBishopField--;
					columnBishopField++;
					fields.add(fieldArray[rowBishopField][columnBishopField]);

				}
			} else

			if (rowDistance > 0 && columnDistance > 0) {
				for (int i = 0; i + 1 < rowDistance; i++) {
					rowBishopField--;
					columnBishopField--;
					fields.add(fieldArray[rowBishopField][columnBishopField]);

				}
			}
		}

		return fields;

	}

	@SuppressWarnings("unchecked")
	public static ArrayList movesAllowedAtACheckedSituation(String colour,
			PlayingField field) throws Exception {
		
		Field fieldKing = CheckClass.getKingFieldByColour(colour, field);
		int row = fieldKing.getRow();
		int column = fieldKing.getColumn();
		ArrayList movesToFlee = new ArrayList();
		ArrayList fieldsToMove = PossibleMoves.listOfActuallyAllowedMoves(row, column, field.getFieldArray());
		
		for (int i = 0; i < fieldsToMove.size(); i++){
			movesToFlee.add(new Move(fieldKing, (Field) fieldsToMove.get(i)));
		}
		
		ArrayList movesAllowed = new ArrayList();
		ArrayList figuresGivingCheck = figuresGivingCheckToColour(colour, field);
		

		for (int i = 0; i < figuresGivingCheck.size(); i++) {
			Field currentField = (Field) figuresGivingCheck.get(i);
			movesAllowed.addAll(MovesToAvoidCheckMateByBlockingAttackersPath(
					currentField, field));
			movesAllowed.addAll(listOfMovesToThrowAttacker(colour, field));

		}
		movesAllowed.addAll(movesToFlee);

		return movesAllowed;

	}

	/**
	 * only performed on a queen giving check
	 * 
	 * @param enemyQueenField
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsBetweenKingAndEnemyQueen(
			Field enemyQueenField, PlayingField field) throws Exception {

		Field kingField;

		if (enemyQueenField.getFigure().getColour() == "white") {
			kingField = getKingFieldByColour("black", field);
		} else {
			kingField = getKingFieldByColour("white", field);
		}

		int rowQueenField = enemyQueenField.getRow();
		int columnQueenField = enemyQueenField.getColumn();
		int rowKingField = kingField.getRow();
		int columnKingField = kingField.getColumn();
		int rowDistance;
		int columnDistance;
		ArrayList fields = new ArrayList();
		rowDistance = rowQueenField - rowKingField;
		Field[][] fieldArray = field.getFieldArray();
		columnDistance = columnQueenField - columnKingField;

		if (Math.abs(rowDistance) == Math.abs(columnDistance)) {
			if (rowDistance < 0 && columnDistance < 0) {
				for (int i = 0; i - 1 > rowDistance; i--) {
					rowQueenField++;
					columnQueenField++;
					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}
			} else

			if (rowDistance < 0 && columnDistance > 0) {
				for (int i = 0; i - 1 > rowDistance; i--) {
					rowQueenField++;
					columnQueenField--;
					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}
			} else

			if (rowDistance > 0 && columnDistance < 0) {
				for (int i = 0; i + 1 < rowDistance; i++) {
					rowQueenField--;
					columnQueenField++;
					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}
			} else

			if (rowDistance > 0 && columnDistance > 0) {
				for (int i = 0; i + 1 < rowDistance; i++) {
					rowQueenField--;
					columnQueenField--;
					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}
			}
		} else {
			if (rowDistance < 0 && columnDistance == 0) {
				for (int i = 0; i - 1 > rowDistance; i--) {
					rowQueenField++;
					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}
			} else

			if (rowDistance > 0 && columnDistance == 0) {
				for (int i = 0; i + 1 < rowDistance; i++) {
					rowQueenField--;

					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}
			} else

			if (rowDistance == 0 && columnDistance < 0) {
				for (int i = 0; i - 1 > columnDistance; i--) {

					columnQueenField++;
					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}
			} else

			if (rowDistance == 0 && columnDistance > 0) {
				for (int i = 0; i + 1 < columnDistance; i++) {

					columnQueenField--;
					fields.add(fieldArray[rowQueenField][columnQueenField]);

				}

			}
		}

		return fields;

	}

	/**
	 * only performed on a rook giving check
	 * 
	 * @param enemyRookField
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsBetweenKingAndEnemyRook(Field enemyRookField,
			PlayingField field) throws Exception {

		Field kingField;

		if (enemyRookField.getFigure().getColour() == "white") {
			kingField = getKingFieldByColour("black", field);
		} else {
			kingField = getKingFieldByColour("white", field);
		}

		int rowRookField = enemyRookField.getRow();
		int colummRookField = enemyRookField.getColumn();
		int rowKingField = kingField.getRow();
		int columnKingField = kingField.getColumn();
		int rowDistance;
		int columnDistance;
		ArrayList fields = new ArrayList();
		rowDistance = rowRookField - rowKingField;
		columnDistance = colummRookField - columnKingField;
		Field[][] fieldArray = field.getFieldArray();

		if (rowDistance < 0 && columnDistance == 0) {
			for (int i = 0; i - 1 > rowDistance; i--) {
				rowRookField++;
				fields.add(fieldArray[rowRookField][colummRookField]);

			}
		} else

		if (rowDistance > 0 && columnDistance == 0) {
			for (int i = 0; i + 1 < rowDistance; i++) {
				rowRookField--;

				fields.add(fieldArray[rowRookField][colummRookField]);

			}
		} else

		if (rowDistance == 0 && columnDistance < 0) {
			for (int i = 0; i - 1 > columnDistance; i--) {

				colummRookField++;
				fields.add(fieldArray[rowRookField][colummRookField]);

			}
		} else

		if (rowDistance == 0 && columnDistance > 0) {
			for (int i = 0; i + 1 < columnDistance; i++) {

				colummRookField--;
				fields.add(fieldArray[rowRookField][colummRookField]);

			}

		}

		return fields;

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
			PlayingField field) throws Exception {
		Field kingField = getKingFieldByColour(colour, field);
		ArrayList figuresGivingCheck = new ArrayList();
		ArrayList threatedFields;
		if (colour == "white")
			threatedFields = MoveClass.moveArrayByColourOfFiguresGivingCheck(
					field, "black");
		else
			threatedFields = MoveClass.moveArrayByColourOfFiguresGivingCheck(
					field, "white");

		for (int i = 0; i < threatedFields.size(); i++) {
			Move temp = (Move) threatedFields.get(i);
			Field currentField = temp.getDestinationField();
			if ((currentField == kingField))

				figuresGivingCheck.add(temp.getStartField());
		}
		return figuresGivingCheck;

	}

	/**
	 * Returns the field of the king for the given colour.
	 * 
	 * @param colour
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static Field getKingFieldByColour(String colour, PlayingField field)
			throws Exception {
		Field[][] fieldArray = field.getFieldArray();
		Field buffer = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (fieldArray[i][j].getFigure() != null)
					if (fieldArray[i][j].getFigure().getColour() == colour)
						if (fieldArray[i][j].getFigure().getType() == "king")
							buffer = fieldArray[i][j];
						else
							;
			}
		}
		if (buffer != null)
			return buffer;
		else
			throw new Exception("No king fund for " + colour
					+ ". Check Lineup please");
	}

	@SuppressWarnings("unchecked")
	public static ArrayList listOfMovesToThrowAttacker(String colour,
			PlayingField field) throws Exception {
		ArrayList moveArray = MoveClass.moveArrayByColour(field, colour);
		ArrayList figuresGivingCheck = figuresGivingCheckToColour(colour, field);
		ArrayList allowedMoves = new ArrayList();

		for (int i = 0; i < moveArray.size(); i++) {
			Move currentMove = (Move) moveArray.get(i);
			Field currentDestinationField = currentMove.getDestinationField();
			if (figuresGivingCheck.contains(currentDestinationField))
				allowedMoves.add(currentMove);
		}

		return allowedMoves;
	}

	public static Field getKingFieldByColour(String colour, Field[][] fieldArray)
			throws Exception {

		Field buffer = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (fieldArray[i][j].getFigure() != null)
					if (fieldArray[i][j].getFigure().getColour() == colour)
						if (fieldArray[i][j].getFigure().getType() == "king")
							buffer = fieldArray[i][j];
						else
							;
			}
		}
		if (buffer != null)
			return buffer;
		else
			throw new Exception("No king fund for " + colour
					+ ". Check Lineup please");
	}

	/**
	 * UNUSED
	 * 
	 * @param row_figure
	 * @param column_figure
	 * @param row_king
	 * @param column_king
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static boolean isCheckByFigure(int row_figure, int column_figure,
			int row_king, int column_king, Field[][] field) throws Exception { /* unused */

		FieldEnsure.fieldFiller(field);
		if (field[row_king][column_king].getFigure() != null)
			return ((PossibleMoves.listOfMovesConsideringAllThreatendFields(
					row_figure, column_figure, field))
					.contains(field[row_king][column_king]))
					&& ((field[row_figure][column_figure]).getFigure()
							.getColour() != (field[row_king][column_king])
							.getFigure().getColour());
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean allowedToThrowAttacker(String colour, Field field,
			PlayingField playingField) throws Exception {
		ArrayList movesAllowed = FieldsThreatendByColour
				.threatendFieldsByActuallyAllowedMoves(colour, playingField
						.getFieldArray());

		if (movesAllowed.contains(field))
			return true;
		else
			return false;

	}

	/**
	 * Checks if the given colour is check mate.
	 * 
	 * @param colour
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static boolean isCheckMate(String colour, PlayingField field)
			throws Exception {

		boolean checkMate = false;
		Field kingField = getKingFieldByColour(colour, field);
		int row = kingField.getRow();
		int column = kingField.getColumn();

		if ((PossibleMoves.listOfActuallyAllowedMoves(row, column, field
				.getFieldArray()).isEmpty())) {
			if (figuresGivingCheckToColour(colour, field).size() > 1) {
				checkMate = true;
			} else {
				if ((figuresGivingCheckToColour(colour, field).size() > 0)) {
					Field attackingField = (Field) figuresGivingCheckToColour(colour, field).get(0);
					if ((allowedToThrowAttacker(colour, attackingField, field))
							|| !(MovesToAvoidCheckMateByBlockingAttackersPath(
									attackingField, field).isEmpty()))
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
	 * 
	 * @param colour
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static boolean isGivenColourCheck(String colour, Field[][] field)
			throws Exception {
		String colourOpposite = "white";

		if (colour == "white")
			colourOpposite = "black";

		if (FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck(
				colourOpposite, field).contains(
				getKingFieldByColour(colour, field)))
			return true;
		else
			return false;

	}

	public static boolean isGivenColourCheck(String colour, PlayingField field)
			throws Exception {
		String colourOpposite = "white";

		if (colour == "white")
			colourOpposite = "black";

		if (FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck(
				colourOpposite, field.getFieldArray()).contains(
				getKingFieldByColour(colour, field)))
			return true;
		else
			return false;

	}

	/**
	 * Checks if the king of the given colour is allowed to throw the figur on
	 * "currentField" uses threatendFieldsByColourHelpClass!
	 * 
	 * 
	 * @param currentField
	 * @param colourKing
	 * @param fieldArray
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean isKingAllowedToThrowField(Field currentField,
			String colourKing, Field[][] fieldArray) throws Exception {

		String oppositeColour = "white";
		if (colourKing == "white")
			oppositeColour = "black";
		String originalType = currentField.getFigure().getType();
		String originalColour = currentField.getFigure().getColour();
		currentField.setEmpty();
		currentField.setFigure(new Figure("pawn", colourKing));
		ArrayList Fields = FieldsThreatendByColour
				.threatenedFieldsByColourConsideringEveryAttackedField(
						oppositeColour, fieldArray);// 9.4
		// threatendFieldsByColourHelpClass
		// ->
		// threatenedFieldsByColourConsideringEveryAttackedField
		if (Fields.contains(currentField)) {
			currentField.setFigure(new Figure(originalType, originalColour));
			return false;
		} else {
			currentField.setFigure(new Figure(originalType, originalColour));
			return true;
		}

	}

	/**
	 * Prints if the given colour is check
	 * 
	 * @param colour
	 * @param field
	 * @throws Exception
	 */
	public static void printIsCheck(String colour, PlayingField field)
			throws Exception {
		System.out
				.println("\n\n=========================================================================\n");
		System.out.println("	Checking Threat-Status for " + colour + "...");
		if (isGivenColourCheck(colour, field))
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
	public static void printIsCheckMate(String colour, PlayingField field)
			throws Exception {
		if (isCheckMate(colour, field))
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
			PlayingField field) throws Exception {
		ArrayList temp = figuresGivingCheckToColour(colour, field);
		if (temp.size() == 0)
			System.out.println("	No figures giving check to " + colour);
		else {
			System.out
					.println("\n	The figures on the following fields\n	are giving check to "
							+ colour + ":\n");
			for (int i = 0; i < temp.size(); i++) {
				Field current = (Field) temp.get(i);
				if (!current.getEmpty())
					System.out.println("	" + current.getCharRow() + ""
							+ current.getIntColumn());
			}
		}
	}

}
