package chess_game;

import java.util.ArrayList;

public class PlayingField {
	
	
	private int moveCounter = 0;
	private Field[][] field = new Field[8][8];
	@SuppressWarnings( { "unchecked", "unused" })
	private ArrayList threatendFieldsByWhite;
	@SuppressWarnings( { "unchecked", "unused" })
	private ArrayList threatendFieldsByBlack;

	public PlayingField() {

		FieldEnsure.fieldFiller(field);
		
	}

	public void incCounter() {
		moveCounter++;
	}

	public int getCounter() {
		return moveCounter;
	}
	
	/**
	 * marks every attacked field for both colours, regardless of the colour actually being allowed to move there.
	 * i.e the white king on E3 and a black rook on D7. Although the king on E3 is not allowed to move on any D-Row field because of the rook on D7.
	 * Nevertheless a black king on C3 would also not be allowed to move on any D-Row field because of the E3 King.
	 * 
	 * So this method is used in the king class to determine by the king class whether a move on a empty field is valid
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void markAttackedFields() throws Exception{
		ArrayList fieldWhite = FieldsThreatendByColour.threatenedFieldsByColourConsideringEveryAttackedField("white", field);
		ArrayList fieldBlack = FieldsThreatendByColour.threatenedFieldsByColourConsideringEveryAttackedField("black", field);
			for (int i = 0; i<8; i++){
				for (int j = 0; j < 8; j++){
					if (fieldWhite.contains(field[i][j]))
						field[i][j].setThreatendByWhite(true);
					else
						field[i][j].setThreatendByWhite(false);
					
					if (fieldBlack.contains(field[i][j]))
						field[i][j].setThreatendByBlack(true);
					else
						field[i][j].setThreatendByBlack(false);
				}
			}
	}
	


	public void addFigure(int row, int column, String type, String colour)
			throws Exception {
		this.field[row][column] = new Field(row, column, new Figure(type,
				colour));
	}

	public void addFigureInterface(String field, String type, String colour)
			throws Exception {
		int[] fieldArray = InputTranslate.InputTranslateMethod(field);
		this.field[fieldArray[0]][fieldArray[1]] = new Field(fieldArray[0],
				fieldArray[1], new Figure(type, colour));
	}

	/**
	 * removes the figure on the current field
	 * if the field is empty, an error message is printed
	 * 
	 * @param field
	 * @throws Exception
	 */
	public void removeFigureInterface(String field)
			throws Exception {
		int[] fieldArray = InputTranslate.InputTranslateMethod(field);
		if (!this.field[fieldArray[0]][fieldArray[1]].getEmpty())
			this.field[fieldArray[0]][fieldArray[1]].setEmpty();
		else
			System.out
			.println("	No figure on " +field+".\n 	Therefore no figure has been removed from "
					+ field+"\n\n");
	}

	public Field[][] getFieldArray() {
		return this.field;
	}

	@SuppressWarnings("unchecked") 
	public ArrayList threatendFieldsByWhite() throws Exception {/*unused*/

		return FieldsThreatendByColour.threatendFieldsByActuallyAllowedMoves("white", field);

	}

	@SuppressWarnings("unchecked") 
	public ArrayList threatendFieldsByBlack() throws Exception {/*unused*/

		return FieldsThreatendByColour.threatendFieldsByActuallyAllowedMoves("black", field);

	}

	
	/**
	 * adds the common chess starting Lineup to a PlayingField
	 * @throws Exception
	 */
	public void startingLineUp() throws Exception {

		this.addFigure(0, 1, "pawn", "black");
		this.addFigure(1, 1, "pawn", "black");
		this.addFigure(2, 1, "pawn", "black");
		this.addFigure(3, 1, "pawn", "black");
		this.addFigure(4, 1, "pawn", "black");
		this.addFigure(5, 1, "pawn", "black");
		this.addFigure(6, 1, "pawn", "black");
		this.addFigure(7, 1, "pawn", "black");
		this.addFigure(0, 0, "rook", "black");
		this.addFigure(1, 0, "knight", "black");
		this.addFigure(2, 0, "bishop", "black");
		this.addFigure(3, 0, "queen", "black");
		this.addFigure(4, 0, "king", "black");
		this.addFigure(5, 0, "bishop", "black");
		this.addFigure(6, 0, "knight", "black");
		this.addFigure(7, 0, "rook", "black");

		this.addFigure(0, 6, "pawn", "white");
		this.addFigure(1, 6, "pawn", "white");
		this.addFigure(2, 6, "pawn", "white");
		this.addFigure(3, 6, "pawn", "white");
		this.addFigure(4, 6, "pawn", "white");
		this.addFigure(5, 6, "pawn", "white");
		this.addFigure(6, 6, "pawn", "white");
		this.addFigure(7, 6, "pawn", "white");
		this.addFigure(0, 7, "rook", "white");
		this.addFigure(1, 7, "knight", "white");
		this.addFigure(2, 7, "bishop", "white");
		this.addFigure(3, 7, "queen", "white");
		this.addFigure(4, 7, "king", "white");
		this.addFigure(5, 7, "bishop", "white");
		this.addFigure(6, 7, "knight", "white");
		this.addFigure(7, 7, "rook", "white");

	}

	public void moveFigure(String startingField, String destinationField)
			throws Exception {
		int[] start = InputTranslate.InputTranslateMethod(startingField);
		int[] destination = InputTranslate
				.InputTranslateMethod(destinationField);
		if (field[start[0]][start[1]].getEmpty()) {
			System.out.println("	Invalid Move: " + startingField
					+ " is Empty.\n        <No changes have been made>");
		} else if (MoveValidation.ValidMoveByMovementRules(start[0], start[1],
				destination[0], destination[1], this)) {
			this.addFigure(destination[0], destination[1],
					this.field[start[0]][start[1]].getFigure().getType(),
					this.field[start[0]][start[1]].getFigure().getColour());
			this.field[start[0]][start[1]].setEmpty();
			incCounter();
			if (!this.field[destination[0]][destination[1]].getFigure().getHasBeenMoved())
				this.field[destination[0]][destination[1]].getFigure().setHasBeenMoved(true);
		} else {
			System.out.println("	Invalid Move: The "
					+ this.field[start[0]][start[1]].getFigure().getColour()
					+ " "
					+ this.field[start[0]][start[1]].getFigure().getType()
					+ " on " + startingField + " is not allowed to move to "
					+ destinationField);
			System.out.println("	  <No changes have been made>");
		}
	}
}
