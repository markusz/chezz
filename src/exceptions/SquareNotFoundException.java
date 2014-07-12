package exceptions;

/**
 * Created by Markus on 29.06.2014.
 */
public class SquareNotFoundException extends Exception {

  public SquareNotFoundException(){

  }

  public SquareNotFoundException(String s){
    super(s);
  }
}
