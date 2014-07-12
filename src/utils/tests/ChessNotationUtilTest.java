package utils.tests;

import exceptions.SquareNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static utils.ChessNotationUtil.*;

public class ChessNotationUtilTest {

  @Test
  public void testConvertColumnLetterToArrayIndex() throws SquareNotFoundException {
    assertEquals(convertColumnLetterToArrayIndex('A'), 0);
    assertEquals(convertColumnLetterToArrayIndex('a'), 0);
    assertEquals(convertColumnLetterToArrayIndex('B'), 1);
    assertEquals(convertColumnLetterToArrayIndex('b'), 1);
    assertEquals(convertColumnLetterToArrayIndex('C'), 2);
    assertEquals(convertColumnLetterToArrayIndex('c'), 2);
    assertEquals(convertColumnLetterToArrayIndex('D'), 3);
    assertEquals(convertColumnLetterToArrayIndex('d'), 3);
    assertEquals(convertColumnLetterToArrayIndex('E'), 4);
    assertEquals(convertColumnLetterToArrayIndex('e'), 4);
    assertEquals(convertColumnLetterToArrayIndex('F'), 5);
    assertEquals(convertColumnLetterToArrayIndex('f'), 5);
    assertEquals(convertColumnLetterToArrayIndex('G'), 6);
    assertEquals(convertColumnLetterToArrayIndex('g'), 6);
    assertEquals(convertColumnLetterToArrayIndex('H'), 7);
    assertEquals(convertColumnLetterToArrayIndex('h'), 7);

  }

  @Test
  public void testConvertColumnArrayIndexToLetter() throws SquareNotFoundException {
    assertEquals(convertColumnArrayIndexToLetter(0), 'A');
    assertEquals(convertColumnArrayIndexToLetter(1), 'B');
    assertEquals(convertColumnArrayIndexToLetter(2), 'C');
    assertEquals(convertColumnArrayIndexToLetter(3), 'D');
    assertEquals(convertColumnArrayIndexToLetter(4), 'E');
    assertEquals(convertColumnArrayIndexToLetter(5), 'F');
    assertEquals(convertColumnArrayIndexToLetter(6), 'G');
    assertEquals(convertColumnArrayIndexToLetter(7), 'H');

  }

  @Test
  public void testConvertRowArrayIndexToRowNumber() throws SquareNotFoundException {
    for (int i = 0; i < 8; i++){
      assertEquals(convertRowArrayIndexToRowNumber(i), 8-i);
    }
  }

  @Test
  public void testConvertFieldIndexToChessNotationForValidInput() throws SquareNotFoundException {
    //testing edgecases only
    assertEquals(convertFieldIndexToChessNotation(0, 0), "A8");
    assertEquals(convertFieldIndexToChessNotation(7, 7), "H1");
    assertEquals(convertFieldIndexToChessNotation(0, 7), "H8");
    assertEquals(convertFieldIndexToChessNotation(7, 0), "A1");
  }

//  @Test(expected = SquareNotFoundException.class)
//  public void testConvertFieldIndexToChessNotationForInvalidInput() throws SquareNotFoundException {
//    convertFieldIndexToChessNotation(-1, -1);
//  }

  @Test
  public void testConvertFieldNameToIndexesForValidInput() throws SquareNotFoundException {
    assertArrayEquals(convertFieldNameToIndexes("A8"), new int[]{0,0});
    assertArrayEquals(convertFieldNameToIndexes("H1"), new int[]{7,7});
    assertArrayEquals(convertFieldNameToIndexes("H8"), new int[]{0,7});
    assertArrayEquals(convertFieldNameToIndexes("A1"), new int[]{7,0});
  }

  @Test(expected = SquareNotFoundException.class)
  public void testConvertFieldNameToIndexesForInvalidInput() throws SquareNotFoundException {
   convertFieldNameToIndexes("Y9");
  }

  @Test
  public void testConvertRowNumberToArrayIndex() throws SquareNotFoundException {
    assertEquals(convertRowNumberToArrayIndex('1'), 0);
    assertEquals(convertFieldIndexToChessNotation(7, 7), "H1");
    assertEquals(convertFieldIndexToChessNotation(0, 7), "H8");
    assertEquals(convertFieldIndexToChessNotation(7, 0), "A1");
  }
}