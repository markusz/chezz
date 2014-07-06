package chess_game;

import java.util.ArrayList;

public class KnightClass {
	
	@SuppressWarnings("unchecked")
	public static ArrayList knightMovesActuallyAllowed(int row, int column, ArrayList moves,
			Field[][] field) {

		
		//int puffer_row = row;
		//int puffer_column = column;
		String colour = field[row][column].getFigure().getColour();

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 1, column - 2, colour, field));

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 1, column + 2, colour, field));

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 1, column - 2, colour, field));

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 1, column + 2, colour, field));

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 2, column - 1, colour, field));

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row - 2, column + 1, colour, field));

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 2, column - 1, colour, field));

		moves.addAll(addMoveIsKnightIsActuallyAllowedToMove(row + 2, column + 1, colour, field));

		return moves;

	}

	@SuppressWarnings("unchecked")
	public static ArrayList addMoveIsKnightIsActuallyAllowedToMove(int row, int column, String colour,
			Field[][] field) {

		ArrayList temp = new ArrayList();

		if (!(row < 0 || column < 0 || row > 7 || column > 7))
			if (field[row][column].getEmpty()) {
				temp.add(field[row][column]);
			} else {
				if (field[row][column].getFigure().getColour() != colour && field[row][column].getFigure().getType() != "king") {
					temp.add(field[row][column]);

				} else
					;
			}
		return temp;

	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList knightMovesCheck(int row, int column, ArrayList moves,
			Field[][] field) {

		
		//int puffer_row = row;
		//int puffer_column = column;
		String colour = field[row][column].getFigure().getColour();

		moves.addAll(knigthMovesCheckHelp(row + 1, column - 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row + 1, column + 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 1, column - 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 1, column + 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 2, column - 1, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 2, column + 1, colour, field));

		moves.addAll(knigthMovesCheckHelp(row + 2, column - 1, colour, field));

		moves.addAll(knigthMovesCheckHelp(row + 2, column + 1, colour, field));

		return moves;

	}

	@SuppressWarnings("unchecked")
	public static ArrayList knigthMovesCheckHelp(int row, int column, String colour,
			Field[][] field) {

		ArrayList temp = new ArrayList();

		if (!(row < 0 || column < 0 || row > 7 || column > 7))
			if (field[row][column].getEmpty()) {
				temp.add(field[row][column]);
			} else {
				if (field[row][column].getFigure().getColour() != colour) {
					temp.add(field[row][column]);

				} else
					;
			}
		return temp;

	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList knightMovesAllThreatenedFields(int row, int column, ArrayList moves,
			Field[][] field) {

		
		//int puffer_row = row;
		//int puffer_column = column;
		String colour = field[row][column].getFigure().getColour();

		moves.addAll(knigthMovesCheckHelp(row + 1, column - 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row + 1, column + 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 1, column - 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 1, column + 2, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 2, column - 1, colour, field));

		moves.addAll(knigthMovesCheckHelp(row - 2, column + 1, colour, field));

		moves.addAll(knigthMovesCheckHelp(row + 2, column - 1, colour, field));

		moves.addAll(knigthMovesCheckHelp(row + 2, column + 1, colour, field));

		return moves;

	}

	@SuppressWarnings("unchecked")
	public static ArrayList addFieldIfKnightThreatenedsIt(int row, int column, String colour,
			Field[][] field) {

		ArrayList temp = new ArrayList();

		if (!(row < 0 || column < 0 || row > 7 || column > 7)){
			
				temp.add(field[row][column]);
			
			}
		return temp;

	}

}
