package chess_game;

import java.util.ArrayList;

public class RookClass {

	/**
	 * Lists fields of attacked fields considering the type of opponent figure (king or not king) standing on the field.
	 * 
	 * adds field in the current direction (one while-loop for each direction) as long as the field is empty.
	 * 
	 * if a non empty field is found the method checks the colour and type of the figure on it.
	 * 
	 * if it has a different colour and is not a king the field is added.
	 * otherwise a break operation is performed and the loop ends.
	 * 
	 * @param row 		= row of the starting field
	 * @param column 	= column of the starting field
	 * @param moves		= the arraylist the moves are added and that is returned at the end
	 * @param field		= the Field[][] the operations are performed on
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsActuallyAllowedForRookToMove(int row, int column, ArrayList moves,
			Field[][] field) {
		String colour = field[row][column].getFigure().getColour();
		int puffer_row = row;
		int puffer_column = column;
		while (row + 1 < 8) {
			if (field[row + 1][column].getEmpty()) {
				moves.add(field[row + 1][column]);
				row++;
			} else {
				if (field[row + 1][column].getFigure().getColour() != colour
						&& field[row + 1][column].getFigure().getType() != "king") {
					moves.add(field[row + 1][column]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;

		while (row - 1 > -1) {
			if (field[row - 1][column].getEmpty()) {
				moves.add(field[row - 1][column]);
				row--;
			} else {
				if (field[row - 1][column].getFigure().getColour() != colour
						&& field[row - 1][column].getFigure().getType() != "king") {
					moves.add(field[row - 1][column]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;

		while (column + 1 < 8) {
			if (field[row][column + 1].getEmpty()) {
				moves.add(field[row][column + 1]);
				column++;
			} else {
				if (field[row][column + 1].getFigure().getColour() != colour
						&& field[row][column + 1].getFigure().getType() != "king") {
					moves.add(field[row][column + 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;

		while (column - 1 > -1) {
			if (field[row][column - 1].getEmpty()) {
				moves.add(field[row][column - 1]);
				column--;
			} else {
				if (field[row][column - 1].getFigure().getColour() != colour
						&& field[row][column - 1].getFigure().getType() != "king") {
					moves.add(field[row][column - 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;
		return moves;

	}
	
	/**
	 * Lists fields of attacked fields not considering the type of opponent figure standing on the field.
	 * 
	 * adds field in the current direction (one while-loop for each direction) as long as the field is empty.
	 * 
	 * if a non empty field is found the method checks the colour of the figure on it.
	 * 
	 * if it has the same colour like the figure moving,a break operation is performed and the loop ends.
	 * if it's colour is different to the figure's, the field is added. afterwards a break operation is performed and the loop ends;
	 * 
	 * 
	 * @param row 		= row of the starting field
	 * @param column 	= column of the starting field
	 * @param moves		= the arraylist the moves are added and that is returned at the end
	 * @param field		= the Field[][] the operations are performed on
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsThreatenedByTheRook(int row, int column,
			ArrayList moves, Field[][] field) {
		String colour = field[row][column].getFigure().getColour();
		int puffer_row = row;
		int puffer_column = column;
		while (row + 1 < 8) {
			if (field[row + 1][column].getEmpty()) {
				moves.add(field[row + 1][column]);
				row++;
			} else {
				if (field[row + 1][column].getFigure().getColour() != colour) {
					moves.add(field[row + 1][column]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;

		while (row - 1 > -1) {
			if (field[row - 1][column].getEmpty()) {
				moves.add(field[row - 1][column]);
				row--;
			} else {
				if (field[row - 1][column].getFigure().getColour() != colour) {
					moves.add(field[row - 1][column]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;

		while (column + 1 < 8) {
			if (field[row][column + 1].getEmpty()) {
				moves.add(field[row][column + 1]);
				column++;
			} else {
				if (field[row][column + 1].getFigure().getColour() != colour) {
					moves.add(field[row][column + 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;

		while (column - 1 > -1) {
			if (field[row][column - 1].getEmpty()) {
				moves.add(field[row][column - 1]);
				column--;
			} else {
				if (field[row][column - 1].getFigure().getColour() != colour) {
					moves.add(field[row][column - 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;
		return moves;

	}

	/**
	 * Lists fields of attacked fields, ignoring the position of the enemy king
	 * 
	 * If an enemy king is found, the field is skipped without adding it to the list
	 * no breaking operation is performed.
	 * 
	 * Does not affect anything else. Fields, where the rook can capture a figure that is not a king are still added.
	 * 
	 * @param row 		= row of the starting field
	 * @param column 	= column of the starting field
	 * @param moves		= the arraylist the moves are added and that is returned at the end
	 * @param field		= the Field[][] the operations are performed on
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsThreatendByTheRookSkippingKingsPosition(int row, int column,
			ArrayList moves, Field[][] field) {
		String colour = field[row][column].getFigure().getColour();
		int puffer_row = row;
		int puffer_column = column;
		

		while (row + 1 < 8) {
			if (field[row + 1][column].getEmpty()) {
				moves.add(field[row + 1][column]);
				row++;
			} else {
				if (field[row + 1][column].getFigure().getColour() != colour) {
					if ((field[row + 1][column].getFigure().getType() == "king")){
						row++;}
					else{
						moves.add(field[row + 1][column]);
					break;}
				} else{
					moves.add(field[row+1][column]); //added 9.4
					break;}
			}

		}
		column = puffer_column;
		row = puffer_row;

		while (row - 1 > -1) {
			if (field[row - 1][column].getEmpty()) {
				moves.add(field[row - 1][column]);
				row--;
			} else {
				if (field[row - 1][column].getFigure().getColour() != colour) {
					if (field[row - 1][column].getFigure().getType() == "king"){
						row--;}
					else {
						moves.add(field[row - 1][column]);
						break;
					}
				} else{
					moves.add(field[row-1][column]);//added 9.4
					break;}
			}

		}
		column = puffer_column;
		row = puffer_row;

		while (column + 1 < 8) {
			if (field[row][column + 1].getEmpty()) {
				moves.add(field[row][column + 1]);
				column++;
			} else {
				if (field[row][column + 1].getFigure().getColour() != colour) {
					if (field[row][column + 1].getFigure().getType() == "king")
						column++;
					else {
						moves.add(field[row][column + 1]);
						break;
					}
				} else{
					moves.add(field[row][column + 1]);//added 9.4
					break;}
			}

		}
		row = puffer_row;
		column = puffer_column;

		while (column - 1 > -1) {
			if (field[row][column - 1].getEmpty()) {
				moves.add(field[row][column - 1]);
				column--;
			} else {
				if (field[row][column - 1].getFigure().getColour() != colour) {
					if (field[row][column - 1].getFigure().getType() == "king")
						column--;
					else {
						moves.add(field[row][column - 1]);
						break;
					}
				} else{
					moves.add(field[row][column - 1]);//added 9.4
					break;}
			}

		}
		row = puffer_row;
		column = puffer_column;
		return moves;
	}
}
