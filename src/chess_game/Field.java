package chess_game;

public class Field {
	
	
	private boolean threatendByWhite = false;
	private boolean threatendByBlack = false;
	
	private int row;
	private int column;
	private Figure figure;

	public Field(int row, int column, Figure figure) {
		this.row = row;
		this.column = column;
		this.figure = figure;
	}


	public int getRow() {
		return this.row;

	}

	public int getColumn() {
		return this.column;
	}

	public char getCharRow() throws Exception {
		char rowChar;

		if (this.row == 0)
			rowChar = 'A';
		else if (this.row == 1)
			rowChar = 'B';
		else if (this.row == 2)
			rowChar = 'C';
		else if (this.row == 3)
			rowChar = 'D';
		else if (this.row == 4)
			rowChar = 'E';
		else if (this.row == 5)
			rowChar = 'F';
		else if (this.row == 6)
			rowChar = 'G';
		else if (this.row == 7)
			rowChar = 'H';
		else
			throw new Exception(
					"error in assigning a char to the columns number");

		return rowChar;
	}

	public int getIntColumn() throws Exception {
		int intColumn;

		if (this.column == 0)
			intColumn = 8;
		else if (this.column == 1)
			intColumn = 7;
		else if (this.column == 2)
			intColumn = 6;
		else if (this.column == 3)
			intColumn = 5;
		else if (this.column == 4)
			intColumn = 4;
		else if (this.column == 5)
			intColumn = 3;
		else if (this.column == 6)
			intColumn = 2;
		else if (this.column == 7)
			intColumn = 1;
		else
			throw new Exception(
					"error in assigning a char to the columns number");

		return intColumn;
	}

	public boolean getEmpty() {
		return (this.figure == null);
	}

	public String getType() {
		return this.figure.getType();
	}

	public Figure getFigure() {
		return this.figure;
	}

	public void setEmpty() {
		this.figure = null;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}


	public void setThreatendByWhite(boolean threatendByWhite) {
		this.threatendByWhite = threatendByWhite;
	}


	public boolean isThreatendByWhite() {
		return threatendByWhite;
	}


	public void setThreatendByBlack(boolean threatendByBlack) {
		this.threatendByBlack = threatendByBlack;
	}


	public boolean isThreatendByBlack() {
		return threatendByBlack;
	}

}
