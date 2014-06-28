package utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChessNotationUtilTest {

  @Test
  public void testConvertRowCharToIndex() throws Exception {
    assertEquals('A', 1);
    assertEquals('a', 1);
    assertEquals('B', 2);
    assertEquals('b', 2);
    assertEquals('C', 3);
    assertEquals('c', 3);
    assertEquals('D', 4);
    assertEquals('d', 4);
    assertEquals('E', 5);
    assertEquals('e', 5);
    assertEquals('F', 6);
    assertEquals('f', 6);
    assertEquals('G', 7);
    assertEquals('g', 7);
    assertEquals('H', 8);
    assertEquals('h', 8);
  }

  @Test
  public void testConvertFieldIndexToChessNotation() throws Exception {

  }

  @Test
  public void testConvertRowIndexToChessNotation() throws Exception {

  }

  @Test
  public void testConvertColumnIndexToChessNotation() throws Exception {

  }

  @Test
  public void testConvertFieldNameToIndexes() throws Exception {

  }

  @Test
  public void testConvertColumnNumberToIndex() throws Exception {

  }
}