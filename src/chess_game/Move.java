package chess_game;

public class Move {

	private Field startField;
	private Field destinationField;

	public Move(Field startField, Field destinationField) {
		this.startField = startField;
		this.destinationField = destinationField;

	}

	public Field getDestinationField() {
		return this.destinationField;
	}

	public Field getStartField() {
		return this.startField;
	}

	public void setDestinationField(Field destinationField) {
		this.destinationField = destinationField;
	}

	public void setStartField(Field startField) {
		this.startField = startField;
	}

}
