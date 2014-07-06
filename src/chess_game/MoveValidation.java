package chess_game;

import java.util.ArrayList;

public class MoveValidation {

	public static boolean ValidMoveByMovementRules(int startRow,
			int startColumn, int destinationRow, int destinationColumn,
			PlayingField playField) throws Exception {
		Field[][] field = playField.getFieldArray();
		Field startingField = field[startRow][startColumn];
		Field destinationField = field[destinationRow][destinationColumn];
		String colour = startingField.getFigure().getColour();
		String startingFieldString = startingField.getCharRow() + ""
				+ startingField.getIntColumn();
		String destinationFieldString = destinationField.getCharRow() + ""
				+ destinationField.getIntColumn();
		boolean allowed = false;

		if (CheckClass.isGivenColourCheck(colour, field)){
			if (validMoveAtCheckedSituation(startingFieldString,
					destinationFieldString, playField))
				allowed = true;

			else
				;}
		else {if (PossibleMoves.listOfActuallyAllowedMoves(startRow,
				startColumn, field).contains(
				field[destinationRow][destinationColumn]))
			allowed = true;}
		
		return allowed;

		
		
	}

	
	@SuppressWarnings("unchecked")
	public static boolean validMoveAtCheckedSituation(String startingField,
			String destinationField, PlayingField field) throws Exception {
		int[] startingFieldHelp = InputTranslate
				.InputTranslateMethod(startingField);
		int[] destinationFieldHelp = InputTranslate
				.InputTranslateMethod(destinationField);

		Field[][] fieldArray = field.getFieldArray();
		Field startField = fieldArray[startingFieldHelp[0]][startingFieldHelp[1]];
		Field destinationField_Field = fieldArray[destinationFieldHelp[0]][destinationFieldHelp[1]];
		String colour = startField.getFigure().getColour();


		ArrayList validMoves = CheckClass.movesAllowedAtACheckedSituation(
				colour, field);
		boolean contains = false;
		for (int i = 0; i < validMoves.size(); i++) {
			Move currentMove = (Move) validMoves.get(i);
			if (currentMove.getStartField().equals(startField)
					&& currentMove.getDestinationField().equals(
							destinationField_Field))
				contains = true;

		}

		return contains;
	}

}
