package chess_game;

public class Figure {

	private boolean hasBeenMoved;
	private String type;
	private String colour;

	public Figure(String type, String colour) throws Exception {
		
		hasBeenMoved = false;
		
		if (type.equalsIgnoreCase("rook") || type.equalsIgnoreCase("knight")
				|| type.equalsIgnoreCase("bishop")
				|| type.equalsIgnoreCase("queen")
				|| type.equalsIgnoreCase("king")
				|| type.equalsIgnoreCase("pawn")) {

			this.type = type;
		} else
			throw new Exception("Wrong Type: " + type + " is no valid input");
		this.type = type;
		if (colour.equalsIgnoreCase("black")
				|| colour.equalsIgnoreCase("white")) {
			this.colour = colour;
		} else
			throw new Exception("Wrong Colour: " + colour
					+ " is no valid input");

	}

	public boolean getHasBeenMoved() {
		return hasBeenMoved;
	}

	public void setHasBeenMoved(boolean hasBeenMoved) {
		this.hasBeenMoved = hasBeenMoved;
	}

	public String getType() {
		return this.type;
	}

	public String getColour() {
		return this.colour;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
}
