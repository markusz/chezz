package ui;

import model.Game;
import model.Square;
import utils.BoardUtil;
import utils.ChessNotationUtil;
import utils.GameUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings({"unused", "serial"})
public class GUI extends JFrame {

  int fieldSize = 60;
  int leftDistance = 40;
  int topDistance = 40;
  JTextField textfieldStartingField;
  JTextField textfieldDestinationField;
  JTextArea playField;
  JTextArea infoField;
  JLabel label_dm;
  JScrollPane scrollPane;
  JTextArea turn;
  JLabel rowLine;
  JLabel rowLine2;
  JLabel columnLine;
  JLabel columnLine2;
  JLabel author;
  JLabel turnLabel;
  JLabel isCheck;
  JButton makeMoveButton;
  JButton confirmButton;
  JButton printFieldButton;
  JButton blackField;
  JButton whiteField;
  JFileChooser test2;
  JMenuItem menuBar1Save;
  JCheckBox forceMoveOrder;
  JCheckBox markAllowedFields;
  JDialog test4;
  JComboBox comboBoxActionChooser;
  JComboBox infoFieldChooser;
  JMenuBar menuBar;
  JMenu menuBar1;
  JMenu menuBar2;
  JMenuItem menuBar1RandomSituation;
  JMenuItem menuBar1NewGame;
  JMenuItem menuBar2Help;
  JMenuItem menuBar1SwitchPlayfield;
  JSeparator sep;
  JOptionPane about;
  Game game;
  String startField = null;
  String destinationField = null;
  String log = "";
  boolean startFieldClicked = false;
  JButton[][] buttonArray = new JButton[8][8];

  public GUI(Game game) throws Exception {
    this.game = game;
    this.getContentPane().setLayout(null);
    this.initWindow(game);
    this.setBounds(100, 100, 600, 600);
    this.setVisible(true);/**/
  }

  public void changeMoveOrderStatus() {
    if (game.isForceMoveOrder()) {
      game.setForceMoveOrder(false);
      forceMoveOrder.setSelected(false);
      forceMoveOrder.updateUI();
    } else {
      game.setForceMoveOrder(true);
      forceMoveOrder.setSelected(true);
      forceMoveOrder.updateUI();
    }
  }

  public void createRandomSituation() throws Exception {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        game.getBoard().getSquares()[i][j].setEmpty();
      }
    }
    game.createRandomSituation();
    game.clearLog();
    fieldUpdater(buttonArray);
    updateInfoField();
  }

  public void createStartingLineup() throws Exception {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        game.getBoard().getSquares()[i][j].setEmpty();
      }
    }
    game.getBoard().createInitialLineup();
    game.clearLog();
    game.changeTurn();
    fieldUpdater(buttonArray);
    updateInfoField();
  }

  public void fieldAdder() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        final int current = i;
        final int current2 = j;
          Square square = game.getBoard().getSquare(i, j);
        System.out.println("putting on "+i+" "+j+" "+ChessNotationUtil.convertFieldIndexToChessNotation(i,j)+": "+square.getPiece().getClass());

        buttonArray[i][j] = new JButton(square.getIcon());
          buttonArray[i][j].setBounds(leftDistance + i * fieldSize, topDistance + j * fieldSize, fieldSize, fieldSize);
          this.getContentPane().add(buttonArray[i][j]);
          buttonArray[i][j].addActionListener(arg0 -> {
            try {
              fieldUpdater(buttonArray);
            } catch (Exception e1) {
              e1.printStackTrace();
            }
            if (!startFieldClicked) {
              textfieldStartingField.setText(ChessNotationUtil.convertFieldIndexToChessNotation(current, current2));
              if (markAllowedFields.isSelected()) {
                try {
                  fieldMarker(buttonArray);
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
              startFieldClicked = true;
            } else {
              textfieldDestinationField.setText(ChessNotationUtil.convertFieldIndexToChessNotation(current, current2));
              try {
                moveFigureClick();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          });
      }
    }
  }

  public void fieldUpdater(JButton[][] buttonArray) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        Square square = game.getBoard().getSquare(i, j);
        buttonArray[i][j].setIcon(square.getIcon());
        buttonArray[i][j].setBorder(null);
      }
    }
  }

  public void fieldMarker(JButton[][] buttonArray) throws Exception {
    Square from = game.getBoard().getSquare(textfieldStartingField.getText());
    for (int row = 0; row < 8; row++) {
      for (int column = 0; column < 8; column++) {
        Square to = game.getBoard().getSquare(row, column);
        if (!from.isEmpty())
          if (BoardUtil.isValidMove(from, to)) {
            buttonArray[row][column].setBorder(new MatteBorder(30, 30, 30, 30, new Color(255, 80, 90, 120)));
            buttonArray[row][column].setBorderPainted(true);
          } else {
            buttonArray[row][column].setIcon(to.getIcon());
            buttonArray[row][column].setBorder(null);
          }

      }
    }
  }

  public String getFieldString(int i, int j) {
    String fieldString = ChessNotationUtil.convertFieldIndexToChessNotation(i, j);
    return fieldString;
  }

  protected void initWindow(Game test) throws Exception {
    // Instanzieren:
    this.fieldAdder();
    // ------------------------
    author = new JLabel("by Markus Ziller");
    author.setBounds(760, 560, 100, 30);
    // ------------------------
    turnLabel = new JLabel();
    turnLabel.setHorizontalTextPosition(JLabel.CENTER);
    turnLabel.setVerticalTextPosition(JLabel.CENTER);
    Font font = new Font("arial", 15, 15);
    turnLabel.setFont(font);

    if (game.isWhiteTurn()) {
      turnLabel.setText(" White's Turn");
      turnLabel.setForeground(Color.black);
      turnLabel.setBackground(Color.white);
    } else {
      turnLabel.setText(" Black's Turn");
      turnLabel.setForeground(Color.white);
      turnLabel.setBackground(Color.black);

    }
    turnLabel.setBounds(575, 10, 100, 20);
    turnLabel.setBorder(new EtchedBorder());
    turnLabel.setOpaque(true);
    // turnLabel.setBackground(Color.GRAY);
    // ------------------------
    isCheck = new JLabel();
    isCheck.setBounds(560, 30, 125, 20);
    isCheck.setBorder(new EtchedBorder());
    isCheck.setBackground(Color.red);
    isCheck.setOpaque(true);
    isCheck.setVisible(false);
    // ------------------------
    String row = "  A      B      C      D      E       F      G      H";
    String column = ("<html>8<br><br>7<br><br>6<br><br>5<br><br>4<br><br>3<br><br>2<br><br>1");
    Font new2 = new Font("arial", 26, 26);
    rowLine = new JLabel(row);
    rowLine.setFont(new2);
    rowLine.setBounds(45, 8, 480, 20);
    // ------------------------
    rowLine2 = new JLabel(row);
    rowLine2.setFont(new2);
    rowLine2.setBounds(45, 528, 480, 20);
    // ------------------------
    columnLine = new JLabel(column);
    columnLine.setFont(new2);
    columnLine.setBounds(15, 38, 20, 480);
    // ------------------------
    columnLine2 = new JLabel(column);
    columnLine2.setFont(new2);
    columnLine2.setBounds(530, 38, 20, 480);
    // ------------------------
    playField = new JTextArea();
    playField.setBounds(9, 0, 9 * fieldSize + 5, 9 * fieldSize + 15);
    // Border borderPlayfield = new EtchedBorder(1);
    Border borderPlayfield = new MatteBorder(2, 2, 2, 2, Color.DARK_GRAY);
    playField.setBorder(borderPlayfield);
    // ------------------------
    forceMoveOrder = new JCheckBox();
    forceMoveOrder.setSelected(test.isForceMoveOrder());
    forceMoveOrder.setText("Force Move Order");
    forceMoveOrder.setBounds(555, 130, 120, 15);
    forceMoveOrder.setToolTipText("mimimi");
    forceMoveOrder.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        changeMoveOrderStatus();
      }
    });
    // ------------------------
    markAllowedFields = new JCheckBox();
    markAllowedFields.setSelected(true);
    markAllowedFields.setText("Mark Possible Fields");
    markAllowedFields.setBounds(555, 150, 120, 15);
    markAllowedFields.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
      }

    });
    // ------------------------
    infoFieldChooser = new JComboBox();
    infoFieldChooser.addItem("Allowed Moves for Color");
    infoFieldChooser.addItem("Show Game History");
    infoFieldChooser.addItem("-to do-");
    infoFieldChooser.setBounds(690, 0, 180, 20);
    infoFieldChooser.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        try {
          updateInfoField();
        } catch (Exception e) {

          e.printStackTrace();
        }
      }

    });
    // ------------------------
    infoField = new JTextArea();
    scrollPane = new JScrollPane(infoField);
    // scrollBar.
    // infoField.add(scrollBar);
    infoField.setBounds(690, 20, 200, 9 * fieldSize - 5);
    scrollPane.setBounds(690, 20, 200, 9 * fieldSize - 5);
    // infoField.setFont(new Font("arial", 12, 12));
    infoField.setText("");
    // infoField.setCaretColor(Color.RED);
    //infoField.setAutoscrolls(true);
    Border borderInfofield = new EtchedBorder(1);
    infoField.setBorder(borderInfofield);
    infoField.setText(game.getAllMovesForPlayerAsString(game.getPlayerInTurn()));
    // ------------------------
    menuBar1 = new JMenu("Game Options");
    menuBar2 = new JMenu("?");

    menuBar = new JMenuBar();
    menuBar.add(menuBar1);
    menuBar.add(menuBar2);

    menuBar1NewGame = new JMenuItem("New Game");
    menuBar1.add(menuBar1NewGame);
    menuBar1NewGame.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        try {
          createStartingLineup();
        } catch (Exception e) {
        }
      }

    });

    menuBar1RandomSituation = new JMenuItem("Create Random Situation");
    menuBar1.add(menuBar1RandomSituation);
    menuBar1RandomSituation.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        try {
          createRandomSituation();
        } catch (Exception e) {
        }
      }

    });

    menuBar1Save = new JMenuItem("Save Game");
    menuBar1.add(menuBar1Save);
    menuBar1Save.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        try {
          long filename = System.currentTimeMillis();
          String s = "savegames/" + filename + ".ser";
          saveGame(s);
        } catch (Exception e) {
        }
      }

    });

    menuBar1.add(sep = new JSeparator());

    menuBar1SwitchPlayfield = new JMenuItem();
    if (infoField.isVisible()) {
      menuBar1SwitchPlayfield.setText("Hide additional Information");
    } else {
      menuBar1SwitchPlayfield.setText("Show additional Information");
    }
    menuBar1.add(menuBar1SwitchPlayfield);
    menuBar1SwitchPlayfield.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        if (infoField.isEnabled()) {
          changeSize(600, 600, false);

        } else {
          changeSize(900, 600, true);
        }
      }

    });

		/*
         * menuBar1secondItem = new JMenuItem("New Game");
		 * menuBar1.add(menuBar1secondItem);
		 * menuBar1secondItem.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent arg0) { try {
		 * createStartingLineup(); } catch (Exception e) { } }
		 * 
		 * });
		 */

    about = new JOptionPane("by morkuzz");
    about.setBounds(500, 300, 300, 100);
    about.setMessage("game");
    about.setVisible(true);
    // about.showMessageDialog("Eggs are not supposed to be green.",
    // "Message");

    menuBar2Help = new JMenuItem("Help");
    menuBar2.add(menuBar2Help);
    menuBar2Help.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        try {
          showInfo();
        } catch (Exception e) {
        }
      }

    });

    // ------------------------
    comboBoxActionChooser = new JComboBox();
    comboBoxActionChooser.addItem("Print List of Moves");
    comboBoxActionChooser.addItem("Mark Fields");
    comboBoxActionChooser.setBounds(50, 10, 150, 25);
    // ------------------------

    textfieldStartingField = new JTextField();
    textfieldDestinationField = new JTextField();
    textfieldStartingField.setBounds(595, 60, 20, 20);
    textfieldDestinationField.setBounds(620, 60, 20, 20);

    makeMoveButton = new JButton("Make Move");
    makeMoveButton.setBounds(575, 90, 90, 20);
    makeMoveButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        try {
          moveFigureClick();
          startFieldClicked = false;
          fieldUpdater(buttonArray);

        } catch (Exception e) {
          //
          e.printStackTrace();
        }

      }

    });

    this.setMinimumSize(new Dimension(910, 650));
    this.getContentPane().add(textfieldStartingField);
    this.getContentPane().add(textfieldDestinationField);
    this.getContentPane().add(makeMoveButton);
    this.getContentPane().add(markAllowedFields);
    this.getContentPane().add(author);
    this.getContentPane().add(rowLine);
    this.getContentPane().add(rowLine2);
    this.getContentPane().add(columnLine);
    this.getContentPane().add(columnLine2);
    this.getContentPane().add(playField);
    this.getContentPane().add(turnLabel);
    this.getContentPane().add(isCheck);
    // this.getContentPane().add(about);
    this.getContentPane().add(forceMoveOrder);
    // this.getContentPane().add(infoField);
    this.getContentPane().add(scrollPane);
    this.getContentPane().add(infoFieldChooser);
    this.setJMenuBar(menuBar);
    this.setTitle("		Chess by Markus Ziller - a0.7");
    this.setResizable(false);
    this.pack();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void saveGame(String filename) {
    game.saveSituation(filename);
  }

  public void changeSize(int x, int y, boolean enabled) {
    this.setResizable(true);
    infoField.setEnabled(enabled);
    infoFieldChooser.setEnabled(enabled);
    textfieldStartingField.setEnabled(enabled);
    textfieldDestinationField.setEnabled(enabled);
    makeMoveButton.setEnabled(enabled);
    turnLabel.setEnabled(enabled);
    forceMoveOrder.setEnabled(enabled);
    markAllowedFields.setEnabled(enabled);
    this.setMaximumSize(new Dimension(x, y));
    this.setSize(x, y);
    this.setResizable(false);
  }

  public void moveFigureClick() throws Exception {
    String startingField = textfieldStartingField.getText();
    String destinationField = textfieldDestinationField.getText();

    Square from = game.getSquareByChessNotation(textfieldStartingField.getText());
    Square to = game.getSquareByChessNotation(textfieldDestinationField.getText());

    if (!from.isEmpty()) {
      if (BoardUtil.isValidMove(from, to)) {
          game.addToLog((startingField + " -> " + destinationField + " (" + game.getPlayerInTurn() + " " + from.getPiece().getTextualRepresentation() + ")" + "\n"));
        game.movePiece(from, to);

        updateInfoField();
        fieldUpdater(buttonArray);
      }

      if ((startingField.equals("E8"))) {
        if (destinationField.equals("G8")) {
          game.castleOnBlackKingside();
          game.addToLog("Black Kingside Castling\n");
          updateInfoField();
          fieldUpdater(buttonArray);
        }
      }

      if ((startingField.equals("E8"))) {

        if (destinationField.equals("C8")) {
          game.castleOnBlackQueenside();
          game.addToLog("Black Queenside Castling\n");
          updateInfoField();
          fieldUpdater(buttonArray);
        }
      }

      if ((startingField.equals("E1"))) {

        if (destinationField.equals("G1")) {
          game.castleOnWhiteKingside();
          game.addToLog("White Kingside Castling\n");
          updateInfoField();
          fieldUpdater(buttonArray);
        }
      }

      if ((startingField.equals("E1"))) {

        if (destinationField.equals("C1")) {
          game.castleOnWhiteQueenside();

          game.addToLog("White Queenside Castling\n");
          updateInfoField();
          fieldUpdater(buttonArray);
        }
      }
    }
    startFieldClicked = false;
    textfieldStartingField.setText("");
    textfieldDestinationField.setText("");

  }

  public void showInfo() throws IOException {
    if (buttonArray[0][0].isVisible()) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          buttonArray[i][j].setVisible(false);
        }
      }
      rowLine.setVisible(false);
      rowLine2.setVisible(false);
      columnLine.setVisible(false);
      columnLine2.setVisible(false);

      BufferedReader f = new BufferedReader(new FileReader("Info.txt"));
      String s = "";


      String line;

      if ((line = f.readLine()) != null)
        s = line;

      while ((line = f.readLine()) != null) {
        s = s + '\n' + line;
      }
      if (s.endsWith("\n"))
        s = s + "\n";

      f.close();

      //playField.setFont(new Font("arial", 16, 16));
      playField.setText(s);

    } else {
      playField.setText("");
      rowLine.setVisible(true);
      rowLine2.setVisible(true);
      columnLine.setVisible(true);
      columnLine2.setVisible(true);
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          buttonArray[i][j].setVisible(true);
        }
      }

    }
  }

  public void updateInfoField() throws Exception {
    if (infoFieldChooser.getSelectedIndex() == 0) {
      infoField.setText(game.getAllMovesForPlayerAsString(game.getPlayerInTurn()));
    } else if (infoFieldChooser.getSelectedIndex() == 1) {
      infoField.setText(game.getLog());
    } else {
      infoField.setText("some text");
    }

    if (!game.isForceMoveOrder()) {
      turnLabel.setText(" Free Movement");
      turnLabel.setForeground(Color.DARK_GRAY);
      turnLabel.setBackground(Color.white);
    } else {
      if (game.isWhiteTurn()) {
        turnLabel.setText(" White's Turn");
        turnLabel.setForeground(Color.black);
        turnLabel.setBackground(Color.white);
      } else {
        turnLabel.setText(" Black's Turn");
        turnLabel.setForeground(Color.white);
        turnLabel.setBackground(Color.black);

      }
    }

    if (game.getWhitePlayer().isCheck() || game.getBlackPlayer().isCheck()) {
      if (game.getWhitePlayer().isCheck()) {
        isCheck.setText("White is Check");
        infoField.setBackground(new Color(255, 128, 124));
      }
      if (GameUtil.isCheckMate("white", game.getBoard())) {
        infoField.setText("White is Check Mate\nThe Game is Over");
        infoField.setBackground(new Color(255, 128, 124));
      }
      if (game.getBlackPlayer().isCheck()) {
        isCheck.setText("Black is Check");
        infoField.setBackground(new Color(255, 128, 124));
      }
      if (GameUtil.isCheckMate("black", game.getBoard())) {
        infoField.setText("Black is Check Mate\nThe Game is Over");
        infoField.setBackground(new Color(255, 128, 124));
      }
      isCheck.setVisible(false);// isCheck l�schen falls nichtmehr
      // verwendet

    } else {
      isCheck.setVisible(false);
      infoField.setBackground(new Color(255, 255, 255));
    }
  }

  public void updatePlayField() throws Exception {
    String field = game.printPlayingFieldString();
    playField.setText(field);
    // fieldAdder();

  }

}
