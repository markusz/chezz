package chess_game;

import java.util.ArrayList;

public class PawnClass {

	/**
	 * adds a field if the pawn is actually allowed to throw by the rules for pawn movement 
	 * and considering the fact that a king can not be thrown.
	 * 
	 * @param row
	 * @param column
	 * @param colour
	 * @param field
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList addFieldIfPawnIsActuallyAllowedToCapture(int row, int column, String colour,
			Field[][] field) {

		ArrayList temp = new ArrayList();

		if (!(row < 0 || column < 0 || row > 7 || column > 7))
			if (field[row][column].getEmpty()) {
				;
			} else {
				if (field[row][column].getFigure().getColour() != colour && field[row][column].getFigure().getType() != "king") {
					temp.add(field[row][column]);

				} else
					;
			}
		return temp;

	}

	/**
	 * adds a field if the pawn is allowed to move to it, de facto, if the field is empty
	 * @param row
	 * @param column
	 * @param colour
	 * @param field
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList addFieldIfPawnIsActuallyAllowedToMove(int row, int column, String colour,
			Field[][] field) {

		ArrayList temp = new ArrayList();

		if (!(row < 0 || column < 0 || row > 7 || column > 7))
			if (field[row][column].getEmpty()) {
				temp.add(field[row][column]);
			}
		return temp;

	}
	

	/**
	 * adds a field if a pawn threateneds it
	 * 
	 * @param row
	 * @param column
	 * @param colour
	 * @param field
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList addFieldIfPawnThreatenedsIt(int row, int column, String colour,
			Field[][] field) {

		ArrayList temp = new ArrayList();

		if (!(row < 0 || column < 0 || row > 7 || column > 7))
			temp.add(field[row][column]);
		return temp;

	}
	
	
	
	/**
	 * returns a list of moves the current pawn is actually allowed to make.
	 * @param row
	 * @param column
	 * @param moves
	 * @param field
	 * @return ArrayList <Field>
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList allFieldsThePawnIsActuallyAllowedToMove(int row, int column, ArrayList moves,
			Field[][] field) {
		String colour = field[row][column].getFigure().getColour();

		if (field[row][column].getFigure().getColour() == "white") {
			if (column == 6) {
				if (field[row][column-1].getEmpty()){
				moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column - 2, colour, field));
				}
				
				moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column - 1, colour, field));
				
				
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column - 1, colour,
								field));
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column - 1, colour,
								field));
			} else {
				moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column - 1, colour, field));
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column - 1, colour,
								field));
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column - 1, colour,
								field));
			}

		}
		else
			if (column == 1) {
				moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column + 1, colour, field));
				if (field[row][column+1].getEmpty()){
				moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column + 2, colour, field));}
				
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column + 1, colour,
								field));
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column + 1, colour,
								field));
			} else {
				moves.addAll(addFieldIfPawnIsActuallyAllowedToMove(row, column + 1, colour, field));
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row - 1, column + 1, colour,
								field));
				moves.addAll(addFieldIfPawnIsActuallyAllowedToCapture(row + 1, column + 1, colour,
								field));
			}
		return moves;
	}

	/**
	 * returns a list of fields the current pawn attacks.
	 * 
	 * @param row
	 * @param column
	 * @param moves
	 * @param field
	 * @return ArrayList <Field>
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList allFieldsThePawnThreateneds(int row, int column, ArrayList moves,
			Field[][] field) {
		String colour = field[row][column].getFigure().getColour();

		if (colour == "white") {
			
				moves.addAll(addFieldIfPawnThreatenedsIt(row - 1, column - 1, colour,
								field));
				moves.addAll(addFieldIfPawnThreatenedsIt(row + 1, column - 1, colour,
								field));
			

		}
		else{
			
				
				moves.addAll(addFieldIfPawnThreatenedsIt(row - 1, column + 1, colour,
								field));
				moves.addAll(addFieldIfPawnThreatenedsIt(row + 1, column + 1, colour,
								field));
			}
		return moves;
	}

	

}
