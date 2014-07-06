package chess_game;

import java.util.ArrayList;

public class BishopClass {

	/**
	 * Lists fields of attacked fields considering the type of opponent figure
	 * (king or not king) standing on the field. adds field in the current
	 * direction (one while-loop for each direction) as long as the field is
	 * empty.
	 * 
	 * if a non empty field is found the method checks the colour and type of
	 * the figure on it.
	 * 
	 * if it has a different colour and is not a king the field is added.
	 * otherwise a break operation is performed and the loop ends.
	 * 
	 * @param row
	 *            = row of the starting field
	 * @param column
	 *            = column of the starting field
	 * @param moves
	 *            = the arraylist the moves are added and that is returned at
	 *            the end
	 * @param field
	 *            = the Field[][] the operations are performed on
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsActuallyAllowedForBishopToMove(int row, int column, ArrayList moves,
			Field[][] field) {
		int puffer_row = row;
		int puffer_column = column;
		String colour = field[row][column].getFigure().getColour();

		while (row + 1 < 8 && column - 1 > -1) {
			if (field[row + 1][column - 1].getEmpty()) {
				moves.add(field[row + 1][column - 1]);
				row++;
				column--;
			} else {

				if (field[row + 1][column - 1].getFigure().getColour() != colour
						&& field[row + 1][column - 1].getFigure().getType() != "king") {
					moves.add(field[row + 1][column - 1]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;
		column = puffer_column;

		while (row + 1 < 8 && column + 1 < 8) {
			if (field[row + 1][column + 1].getEmpty()) {
				moves.add(field[row + 1][column + 1]);
				row++;
				column++;
			} else {
				if (field[row + 1][column + 1].getFigure().getColour() != colour
						&& field[row + 1][column + 1].getFigure().getType() != "king") {
					moves.add(field[row + 1][column + 1]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;
		column = puffer_column;

		while (row - 1 > -1 && column + 1 < 8) {
			if (field[row - 1][column + 1].getEmpty()) {
				moves.add(field[row - 1][column + 1]);
				row--;
				column++;
			} else {
				if (field[row - 1][column + 1].getFigure().getColour() != colour
						&& field[row - 1][column + 1].getFigure().getType() != "king") {
					moves.add(field[row - 1][column + 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;
		row = puffer_row;

		while (row - 1 > -1 && column - 1 > -1) {
			if (field[row - 1][column - 1].getEmpty()) {
				moves.add(field[row - 1][column - 1]);
				row--;
				column--;
			} else {
				if (field[row - 1][column - 1].getFigure().getColour() != colour
						&& field[row - 1][column - 1].getFigure().getType() != "king") {
					moves.add(field[row - 1][column - 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;
		row = puffer_row;
		return moves;

	}

	/**
	 * Lists fields of attacked fields not considering the type of opponent
	 * figure standing on the field. adds field in the current direction (one
	 * while-loop for each direction) as long as the field is empty.
	 * 
	 * if a non empty field is found the method checks the colour of the figure
	 * on it.
	 * 
	 * if it has the same colour as the figure moving,a break operation is
	 * performed and the loop ends. if it's colour is different to the figure's,
	 * the field is added. afterwards a break operation is performed and the
	 * loop ends;
	 * 
	 * 
	 * @param row
	 *            = row of the starting field
	 * @param column
	 *            = column of the starting field
	 * @param moves
	 *            = the arraylist the moves are added and that is returned at
	 *            the end
	 * @param field
	 *            = the Field[][] the operations are performed on
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsThreatenedByTheBishop(int row, int column,
			ArrayList moves, Field[][] field) {
		int puffer_row = row;
		int puffer_column = column;
		String colour = field[row][column].getFigure().getColour();

		while (row + 1 < 8 && column - 1 > -1) {
			if (field[row + 1][column - 1].getEmpty()) {
				moves.add(field[row + 1][column - 1]);
				row++;
				column--;
			} else {
				if (field[row + 1][column - 1].getFigure().getColour() != colour) {
					moves.add(field[row + 1][column - 1]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;
		column = puffer_column;

		while (row + 1 < 8 && column + 1 < 8) {
			if (field[row + 1][column + 1].getEmpty()) {
				moves.add(field[row + 1][column + 1]);
				row++;
				column++;
			} else {
				if (field[row + 1][column + 1].getFigure().getColour() != colour) {
					moves.add(field[row + 1][column + 1]);
					break;
				} else
					break;
			}
		}

		row = puffer_row;
		column = puffer_column;

		while (row - 1 > -1 && column + 1 < 8) {
			if (field[row - 1][column + 1].getEmpty()) {
				moves.add(field[row - 1][column + 1]);
				row--;
				column++;
			} else {
				if (field[row - 1][column + 1].getFigure().getColour() != colour) {
					moves.add(field[row - 1][column + 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;
		row = puffer_row;

		while (row - 1 > -1 && column - 1 > -1) {
			if (field[row - 1][column - 1].getEmpty()) {
				moves.add(field[row - 1][column - 1]);
				row--;
				column--;
			} else {
				if (field[row - 1][column - 1].getFigure().getColour() != colour) {
					moves.add(field[row - 1][column - 1]);
					break;
				} else
					break;
			}
		}

		column = puffer_column;
		row = puffer_row;
		return moves;

	}

	/**
	 * Lists fields of attacked fields, not considering the enemy king If an
	 * enemy king is found, the field is skipped without adding it to the list
	 * Nevertheless no breaking operation is performed. Does not affect anything
	 * else. Fields, where the bishop can capture a figure that is not a king
	 * are still added.
	 * 
	 * @param row
	 *            = row of the starting field
	 * @param column
	 *            = column of the starting field
	 * @param moves
	 *            = the arraylist the moves are added and that is returned at
	 *            the end
	 * @param field
	 *            = the Field[][] the operations are performed on
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList fieldsThreatendByTheBishopSkippingKingsPosition(int row, int column,
			ArrayList moves, Field[][] field) {
		int puffer_row = row;
		int puffer_column = column;
		String colour = field[row][column].getFigure().getColour();
		
		/*while (row + 1 < 8 && column - 1 > -1) {
			if (field[row + 1][column - 1].getEmpty()) {
				moves.add(field[row + 1][column - 1]);
				row++;
				column--;
			} else {

				if (field[row + 1][column - 1].getFigure().getType() == "king"
						&& field[row + 1][column - 1].getFigure().getColour() != colour) {

					row++;
					column--;
				} else {//9.4 changed to getColour() == colour
					if (field[row + 1][column - 1].getFigure().getColour() == colour) {
						moves.add(field[row + 1][column - 1]);
						break;
					} else
						break;
				}
			}
		}*/
		
		while (row + 1 < 8 && column - 1 > -1) {
			if (field[row + 1][column-1].getEmpty()) {
				moves.add(field[row + 1][column-1]);
				row++;
				column--;
			} else {
				if (field[row + 1][column-1].getFigure().getColour() != colour) {
					if ((field[row + 1][column-1].getFigure().getType() == "king")){
						row++;
						column--;}
					else{
						moves.add(field[row + 1][column-1]);
					break;}
				} else{
					moves.add(field[row+1][column-1]); //added 9.4
					break;}
			}

		}

		row = puffer_row;
		column = puffer_column;

		/*while (row + 1 < 8 && column + 1 < 8) {
			if (field[row + 1][column + 1].getEmpty()) {
				moves.add(field[row + 1][column + 1]);
				row++;
				column++;
			} else {
				if (field[row + 1][column + 1].getFigure().getType() == "king"
						&& field[row + 1][column + 1].getFigure().getColour() != colour) {
					row++;
					column++;
				} else {//9.4 changed to getColour() == colour
					if (field[row + 1][column + 1].getFigure().getColour() == colour) {
						moves.add(field[row + 1][column + 1]);
						break;
					} else
						break;
				}
			}
		}*/
		
		while (row + 1 < 8 && column + 1 < 8) {
			if (field[row + 1][column+1].getEmpty()) {
				moves.add(field[row + 1][column+1]);
				row++;
				column++;
			} else {
				if (field[row + 1][column+1].getFigure().getColour() != colour) {
					if ((field[row + 1][column+1].getFigure().getType() == "king")){
						row++;
						column++;}
					else{
						moves.add(field[row + 1][column+1]);
					break;}
				} else{
					moves.add(field[row+1][column+1]); //added 9.4
					break;}
			}

		}

		row = puffer_row;
		column = puffer_column;

		/*while (row - 1 > -1 && column + 1 < 8) {
			if (field[row - 1][column + 1].getEmpty()) {
				moves.add(field[row - 1][column + 1]);
				row--;
				column++;
			} else {
				if (field[row - 1][column + 1].getFigure().getType() == "king"
						&& field[row - 1][column + 1].getFigure().getColour() != colour) {
					row--;
					column++;
				} else {//9.4 changed to getColour() == colour
					if (field[row - 1][column + 1].getFigure().getColour() == colour) {
						moves.add(field[row - 1][column + 1]);
						break;
					} else
						break;
				}
			}
		}*/
		
		while (row - 1 > -1 && column + 1 < 8) {
			if (field[row - 1][column+1].getEmpty()) {
				moves.add(field[row - 1][column+1]);
				row--;
				column++;
			} else {
				if (field[row - 1][column+1].getFigure().getColour() != colour) {
					if ((field[row - 1][column+1].getFigure().getType() == "king")){
						row--;
						column++;}
					else{
						moves.add(field[row - 1][column+1]);
					break;}
				} else{
					moves.add(field[row-1][column+1]); //added 9.4
					break;}
			}

		}

		column = puffer_column;
		row = puffer_row;

		/*while (row - 1 > -1 && column - 1 > -1) {
			if (field[row - 1][column - 1].getEmpty()) {
				moves.add(field[row - 1][column - 1]);
				row--;
				column--;
			} else {
				if (field[row - 1][column - 1].getFigure().getType() == "king"
						&& field[row - 1][column - 1].getFigure().getColour() != colour) {
					row--;
					column--;
				} else {//9.4 changed to getColour() == colour
					if (field[row - 1][column - 1].getFigure().getColour() == colour) { 
						//System.out.println("am i? "+row+" "+column);
						moves.add(field[row - 1][column - 1]);
						break;
					} else
						break;
				}
			}
		}*/
		
		while (row - 1 > -1 && column - 1 > -1) {
			if (field[row - 1][column-1].getEmpty()) {
				moves.add(field[row - 1][column-1]);
				row--;
				column--;
			} else {
				if (field[row - 1][column-1].getFigure().getColour() != colour) {
					if ((field[row - 1][column-1].getFigure().getType() == "king")){
						row--;
						column--;}
					else{
						moves.add(field[row - 1][column-1]);
					break;}
				} else{
					moves.add(field[row-1][column-1]); //added 9.4
					break;}
			}

		}

		column = puffer_column;
		row = puffer_row;

		return moves;

	}

}
