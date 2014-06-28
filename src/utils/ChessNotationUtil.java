package utils;

public class ChessNotationUtil {

  public static int convertRowCharToIndex(char row) throws Exception {
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
    char rowLetter = (char) (65 + row);
    int columnNumber = 8 - column;

    return rowLetter + "" + column;
  }

  public static int[] convertFieldNameToIndexes(String fieldName) throws Exception {
    char[] temp = fieldName.toCharArray();
    int[] row_column = new int[2];
    if (temp.length == 2) {
      row_column[0] = convertRowCharToIndex(temp[0]);
      row_column[1] = convertColumnNumberToIndex(temp[1]);
      return row_column;
    }
    throw new Exception(fieldName + " is not a valid input");
  }

  public static int convertColumnNumberToIndex(char row) throws Exception {
    try {
      return 8 - Integer.valueOf(row);
    } catch (Exception e) {
      throw new Exception("Invalid Column Address: " + row);
    }
  }
}
