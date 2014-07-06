package chess_game;

public class InputTranslate {

	public static int CharTranslate(char row) throws Exception {
		int rowInt;

		if (row == 'A' || row == 'a')
			rowInt = 0;
		else if (row == 'B' || row == 'b')
			rowInt = 1;
		else if (row == 'C' || row == 'c')
			rowInt = 2;
		else if (row == 'D' || row == 'd')
			rowInt = 3;
		else if (row == 'E' || row == 'e')
			rowInt = 4;
		else if (row == 'F' || row == 'f')
			rowInt = 5;
		else if (row == 'G' || row == 'g')
			rowInt = 6;
		else if (row == 'H' || row == 'h')
			rowInt = 7;
		else
			throw new Exception("Invalid Row Address");

		return rowInt;
	}
	
	public static String fieldIntToString(int row, int column){
		
		
		char rowChar = 0;
		String s;
		
		if (row == 0)
			rowChar = 'A';
		else if (row == 7)
			rowChar = 'H';
		else if (row == 6)
			rowChar = 'G';
		else if (row == 5)
			rowChar = 'F';
		else if (row == 4)
			rowChar = 'E';
		else if (row == 3)
			rowChar = 'D';
		else if (row == 2)
			rowChar = 'C';
		else if (row == 1)
			rowChar = 'B';
		
		column = 8-column;
		
		s = rowChar+""+column;
		
		return s;
		
	}

	public static int[] InputTranslateMethod(String Field) throws Exception {

		char[] temp = Field.toCharArray();
		int[] row_column = new int[2];
		if (temp.length == 2) {
			row_column[0] = CharTranslate(temp[0]);
			row_column[1] = IntTranslate(temp[1]);
		}

		else
			throw new Exception("Invalid Input");
		return row_column;

	}

	public static int IntTranslate(char row) throws Exception {
		int rowInt;

		if (row == '8')
			rowInt = 0;
		else if (row == '7')
			rowInt = 1;
		else if (row == '6')
			rowInt = 2;
		else if (row == '5')
			rowInt = 3;
		else if (row == '4')
			rowInt = 4;
		else if (row == '3')
			rowInt = 5;
		else if (row == '2')
			rowInt = 6;
		else if (row == '1')
			rowInt = 7;
		else
			throw new Exception("Invalid Column Address");

		return rowInt;
	}

}
