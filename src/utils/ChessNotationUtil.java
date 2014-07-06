package utils;

public class ChessNotationUtil {

  public static int convertColumnLetterToArrayIndex(char row) throws Exception {
    int rowInt;

    if (row == 'A' || row == 'a') {
      rowInt = 0;
    } else if (row == 'B' || row == 'b') {
      rowInt = 1;
    } else if (row == 'C' || row == 'c') {
      rowInt = 2;
    } else if (row == 'D' || row == 'd') {
      rowInt = 3;
    } else if (row == 'E' || row == 'e') {
      rowInt = 4;
    } else if (row == 'F' || row == 'f') {
      rowInt = 5;
    } else if (row == 'G' || row == 'g') {
      rowInt = 6;
    } else if (row == 'H' || row == 'h') {
      rowInt = 7;
    } else {
      throw new Exception("Invalid Row Address");
    }

    return rowInt;
  }

  public static String convertFieldIndexToChessNotation(int row, int column) {
//    char rowLetter = convertColumnArrayIndexToLetter(row);
//    int columnNumber = convertRowArrayIndexToRowNumber(column);

    int rowNumber = convertRowArrayIndexToRowNumber(row);
    char columnLetter = convertColumnArrayIndexToLetter(column);

    return columnLetter + "" + rowNumber;
  }

  public static char convertColumnArrayIndexToLetter(int row){
    return (char) (65 + row);
  }

  public static int convertRowArrayIndexToRowNumber(int column){
    return 8 - column;
  }

  public static int[] convertFieldNameToIndexes(String fieldName) throws Exception {
    char[] temp = fieldName.toCharArray();
    //indexes of row and column
    int[] indexes = new int[2];
    if (temp.length == 2) {
      indexes[0] = convertRowNumberToArrayIndex(temp[1]);
      indexes[1] = convertColumnLetterToArrayIndex(temp[0]);
      return indexes;
    }
    throw new Exception(fieldName + " is not a valid input");
  }

  public static int convertRowNumberToArrayIndex(char row) throws Exception {
    try {
      return 8 - Integer.valueOf(row);
    } catch (Exception e) {
      throw new Exception("Invalid Column Address: " + row);
    }
  }
}
