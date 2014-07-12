package utils;

import exceptions.SquareNotFoundException;

public class ChessNotationUtil {

  public static int convertColumnLetterToArrayIndex(char row) throws SquareNotFoundException {

    if (row == 'A' || row == 'a') {
      return 0;
    }
    if (row == 'B' || row == 'b') {
      return 1;
    }
    if (row == 'C' || row == 'c') {
      return 2;
    }
    if (row == 'D' || row == 'd') {
      return 3;
    }
    if (row == 'E' || row == 'e') {
      return 4;
    }
    if (row == 'F' || row == 'f') {
      return 5;
    }
    if (row == 'G' || row == 'g') {
      return 6;
    }
    if (row == 'H' || row == 'h') {
      return 7;
    }
    throw new SquareNotFoundException("Invalid Row Address");
  }

  public static String convertFieldIndexToChessNotation(int row, int column) {
//    char rowLetter = convertColumnArrayIndexToLetter(row);
//    int columnNumber = convertRowArrayIndexToRowNumber(column);

    int rowNumber = convertRowArrayIndexToRowNumber(row);
    char columnLetter = convertColumnArrayIndexToLetter(column);

    return columnLetter + "" + rowNumber;
  }

  public static char convertColumnArrayIndexToLetter(int row) {
    return (char) (65 + row);
  }

  public static int convertRowArrayIndexToRowNumber(int row) {
    return 8 - row;
  }

  public static int[] convertFieldNameToIndexes(String fieldName) throws SquareNotFoundException {
    char[] temp = fieldName.toCharArray();
    //indexes of row and column
    int[] indexes = new int[2];
    if (temp.length == 2) {
      indexes[0] = convertRowNumberToArrayIndex(temp[1]);
      indexes[1] = convertColumnLetterToArrayIndex(temp[0]);
      return indexes;
    }
    throw new SquareNotFoundException(fieldName + " is not a valid input");
  }

  public static int convertRowNumberToArrayIndex(char row) throws SquareNotFoundException {
    try {
      return 8 - Integer.parseInt(new String(""+row));
    } catch (Exception e) {
      throw new SquareNotFoundException("Invalid Column Address: " + row);
    }
  }
}
