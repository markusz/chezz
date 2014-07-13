package ui;

import model.Square;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by Markus on 13.07.2014.
 */
public class GUISquare extends JButton {

  private Square square;

  public GUISquare(Square square, int row, int column) {
    super(square.getIcon());
    square.setGuiSquare(this);
    this.square = square;
    setBounds(GUIConstants.LEFT_DISTANCE + column * GUIConstants.FIELD_SIZE, GUIConstants.TOP_DISTANCE + row * GUIConstants.FIELD_SIZE, GUIConstants.FIELD_SIZE, GUIConstants.FIELD_SIZE);
  }

  public void render() {
    setIcon(square.getIcon());
    setBorder(null);
  }

  public void markAsValidTarget(){
    setBorder(new MatteBorder(30, 30, 30, 30, new Color(255, 80, 90, 120)));
    setBorderPainted(true);
  }
}
