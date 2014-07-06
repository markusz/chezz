package chess_game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({ "unused", "serial" })
public class Game implements Serializable {

	private boolean forceMoveOrder = true;
	private boolean fieldManipulationForbidden = true;
	private String turn = "white";
	private PlayingField field = new PlayingField();
	private String log = "";
	
	public void addToLog(String move){
		log = log + move;
	}
	
	public void clearLog(){
		log = "";
	}
	
	public String getLog(){
		return log;
	}

	public Game() throws Exception {
		this.getField().startingLineUp();
		/*System.out.println("		CHESS BY MARKUS ZILLER - ALPHA 0.5");	
		System.out
				.println("=========================================================================");
		System.out
				.println("\n\n	*** New game with common Settings created ***");
		System.out
				.println("=========================================================================");*/

	}
	

	 
	 public String getTurn(){
		 return turn;
	 }
	
	public Game(Boolean startingLineUp, Boolean fieldManipulationForbidden,
			Boolean forceMoveOrder) throws Exception {
		if (startingLineUp)
			this.getField().startingLineUp();
		this.fieldManipulationForbidden = fieldManipulationForbidden;
		this.forceMoveOrder = forceMoveOrder;
		/*System.out.println("\n\n			CHESS BY MARKUS ZILLER - ALPHA 0.5");
		System.out
				.println("=========================================================================");
		System.out
				.println("\n\n	*** New game with following Settings created ***\n\n	Common starting Lineup: 	"
						+ startingLineUp
						+ "\n	Fieldmanipulation forbidden:  	"
						+ fieldManipulationForbidden
						+ "\n	Forced Move Order:   		" + forceMoveOrder + "\n");
		System.out
				.println("=========================================================================");*/
		this.getField().markAttackedFields();
	}
	
	/**
	 * Adds a Figure of "type" and "colour" at "field" .
	 * 
	 * if FieldManipulation, which is determined by the boolean "fieldManipulationForbidden", is allowed.
	 * 
	 * @param field
	 * @param type
	 * @param colour
	 * @throws Exception
	 */
	public void addFigure(String field, String type, String colour)
			throws Exception {
		if (!fieldManipulationForbidden){
			this.getField().addFigureInterface(field, type, colour);}
		else{
			System.out
					.println("Fieldmanipulation is not allowed. Therefore the "
							+ colour + " " + type + " has not been added to"
							+ field);}
		this.getField().markAttackedFields();
	}

	public void setTurn(String colour){
		this.turn = colour;
	}
	/**
	 * This Method is used to perform Castling.
	 * 
	 * Checks every necessary Condition for Fulfilment. 
	 * 
	 * Only if every Condition for Castling is fulfilled, the Castling is performed.
	 * Else, an Error Message is printed.
	 * 
	 * Valid Inputs:
	 * 
	 * wK:	white Kingside castling
	 * wQ:	white Queenside castling
	 * bK:	black Kingside castling
	 * bQ:	black Queenside castling
	 * 
	 * @param type
	 * @throws Exception
	 */
	public void castling(String type) throws Exception {

		String errorMessage = "castling is not allowed because of at least one of the following reasons:\n\n	- King or rook may already have been moved.\n	- The king would have to move over a threatend position.\n	- One or more figures are standing between the king and the rook.\n	- You are check. ";
		Field[][] field = this.getField().getFieldArray();

		boolean wK_allowed = 
			
				(field[5][7].getEmpty()
				&& field[6][7].getEmpty()
				&& !field[7][7].getEmpty()
				&& !field[4][7].getEmpty()
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("black", field).contains(field[5][7]))
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("black", field).contains(field[6][7]))
				&& !(CheckClass.isGivenColourCheck("white", this.getField()))
				&& !(field[7][7].getFigure().getHasBeenMoved())
				&& !(field[4][7].getFigure().getHasBeenMoved()));
				
		
		boolean wQ_allowed = 
			
				(field[1][7].getEmpty()
				&& field[2][7].getEmpty()
				&& field[3][7].getEmpty()
				&& !field[0][7].getEmpty()
				&& !field[4][7].getEmpty()
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("black", field).contains(field[1][7]))
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("black", field).contains(field[2][7])) 
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("black", field).contains(field[3][7]))
				&& !(CheckClass.isGivenColourCheck("white", this.getField()))
				&& !(field[0][7].getFigure().getHasBeenMoved())
				&& !(field[4][7].getFigure().getHasBeenMoved()));
		
		boolean bK_allowed = 
				
				(field[5][0].getEmpty()
				&& field[6][0].getEmpty()
				&& !field[7][0].getEmpty()
				&& !field[4][0].getEmpty()
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("white", field).contains(field[5][0])) 
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("white", field).contains(field[6][0]))
				&& !(CheckClass.isGivenColourCheck("black", this.getField()))
				&& !(field[7][0].getFigure().getHasBeenMoved())
				&& !(field[4][0].getFigure().getHasBeenMoved()));
		
		boolean bQ_allowed = 
			
				(field[1][0].getEmpty()
				&& field[2][0].getEmpty()
				&& field[3][0].getEmpty()
				&& !field[0][0].getEmpty()
				&& !field[4][0].getEmpty()
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("white", field).contains(field[1][0]))
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("white", field).contains(field[2][0]))
				&& !(FieldsThreatendByColour.threatenedFieldsOnWhichAnEnemyKingIsCheck("white", field).contains(field[3][0]))
				&& !(CheckClass.isGivenColourCheck("black", this.getField()))
				&& !(field[0][0].getFigure().getHasBeenMoved())
				&& !(field[4][0].getFigure().getHasBeenMoved()));

		if (type == "wK"){
			if (wK_allowed) {
				this.getField().addFigureInterface("G1", "king", "white");
				this.getField().removeFigureInterface("E1");
				this.getField().addFigureInterface("F1", "rook", "white");
				this.getField().removeFigureInterface("H1");
				
				if (this.turn == "white"){
					turn = "black";
				}
				else{
					turn = "white";}
				printPlayingField();
			} else {printPlayingField();
				System.out.println("	White kingside "+errorMessage);	
				

			}
		}else if (type == "wQ") {
			if (wQ_allowed) {
				this.getField().addFigureInterface("C1", "king", "white");	
				this.getField().removeFigureInterface("E1");
				this.getField().addFigureInterface("D1", "rook", "white");
				this.getField().removeFigureInterface("A1");
				if (this.turn == "white"){
					turn = "black";
				}
				else{
					turn = "white";}
				printPlayingField();
			} else {printPlayingField();
				System.out.println("	White queenside "+errorMessage);
				
			}
		} else if (type == "bK") {
			if (bK_allowed) {
				this.getField().addFigureInterface("G8", "king", "black");
				this.getField().removeFigureInterface("E8");
				this.getField().addFigureInterface("F8", "rook", "black");
				this.getField().removeFigureInterface("H8");
				if (this.turn == "white"){
					turn = "black";
				}
				else{
					turn = "white";}
				printPlayingField();
			} else {printPlayingField();
				System.out.println("	Black kingside "+errorMessage);
				
			}
		} else if (type == "bQ") {
			if (bQ_allowed) {
				this.getField().addFigureInterface("C8", "king", "black");
				this.getField().removeFigureInterface("E8");
				this.getField().addFigureInterface("D8", "rook", "black");
				this.getField().removeFigureInterface("A8");
				if (this.turn == "white"){
					turn = "black";
				}
				else{
					turn = "white";}
				printPlayingField();
			} else {printPlayingField();
				System.out.println("	Black queenside "+errorMessage);
				
			}
		}
		this.getField().markAttackedFields();

	}

	/**
	 * Counts the Figures currently in the Game
	 * 
	 * @return
	 */
	public int countFigures(){
		Field[][] fieldArray = this.field.getFieldArray();
		int counter = 0;
		for (int i=0; i<8; i++){
			for(int j = 0; j<8;j++){
				if (!fieldArray[i][j].getEmpty())
					counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Creates a random Game Situation with a default adding Probability of 0.3. 
	 * 
	 * Nevertheless it is ensured that a King of every Colour exists.
	 * 
	 * @throws Exception if the second king is random on the firsts King field.
	 */
	public void createRandomSituation() throws Exception {

		int rowKingWhite = (int) (Math.random() * 10000 % 8);
		int columnKingWhite = (int) (Math.random() * 10000 % 8);
		this.getField().addFigure(rowKingWhite, columnKingWhite, "king", "white");

		int rowKingBlack = (int) (Math.random() * 10000 % 8);
		int columnKingBlack = (int) (Math.random() * 10000 % 8);
		this.getField().addFigure(rowKingBlack, columnKingBlack, "king", "black");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Math.random() < 0.3
						&& this.getField().getFieldArray()[i][j].getEmpty()) {
					if (Math.random() < 0.5) {
						if (Math.random() < 0.4)
							this.getField().addFigure(i, j, "pawn", "white");
						else if (Math.random() < 0.55)
							this.getField().addFigure(i, j, "rook", "white");
						else if (Math.random() < 0.7)
							this.getField().addFigure(i, j, "bishop", "white");
						else if (Math.random() < 0.85)
							this.getField().addFigure(i, j, "knight", "white");
						else if (Math.random() < 1)
							this.getField().addFigure(i, j, "queen", "white");
					} else {
						if (Math.random() < 0.4)
							this.getField().addFigure(i, j, "pawn", "black");
						else if (Math.random() < 0.55)
							this.getField().addFigure(i, j, "rook", "black");
						else if (Math.random() < 0.7)
							this.getField().addFigure(i, j, "bishop", "black");
						else if (Math.random() < 0.85)
							this.getField().addFigure(i, j, "knight", "black");
						else if (Math.random() < 1)
							this.getField().addFigure(i, j, "queen", "black");
					}

				}
			}
		}
		this.getField().markAttackedFields();
	}
	
	/**
	 * Creates a random Game Situation with a given adding Probability.
	 * @param probability		0 <= probability <= 1
	 * @throws Exception
	 */
	public void createRandomSituation(double probability) throws Exception {

		int rowKingWhite = (int) (Math.random() * 10000 % 8);
		int columnKingWhite = (int) (Math.random() * 10000 % 8);
		this.getField().addFigure(rowKingWhite, columnKingWhite, "king", "white");

		int rowKingBlack = (int) (Math.random() * 10000 % 8);
		int columnKingBlack = (int) (Math.random() * 10000 % 8);
		this.getField().addFigure(rowKingBlack, columnKingBlack, "king", "black");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Math.random() < probability
						&& this.getField().getFieldArray()[i][j].getEmpty()) {
					if (Math.random() < 0.5) {
						if (Math.random() < 0.2)
							this.getField().addFigure(i, j, "pawn", "white");
						else if (Math.random() < 0.4)
							this.getField().addFigure(i, j, "rook", "white");
						else if (Math.random() < 0.6)
							this.getField().addFigure(i, j, "bishop", "white");
						else if (Math.random() < 0.8)
							this.getField().addFigure(i, j, "knight", "white");
						else if (Math.random() < 1)
							this.getField().addFigure(i, j, "queen", "white");
					} else {
						if (Math.random() < 0.2)
							this.getField().addFigure(i, j, "pawn", "black");
						else if (Math.random() < 0.4)
							this.getField().addFigure(i, j, "rook", "black");
						else if (Math.random() < 0.6)
							this.getField().addFigure(i, j, "bishop", "black");
						else if (Math.random() < 0.8)
							this.getField().addFigure(i, j, "knight", "black");
						else if (Math.random() < 1)
							this.getField().addFigure(i, j, "queen", "black");
					}

				}
			}
		}
		this.getField().markAttackedFields();
	}

	/**
	 * Returns the PlayingField of the Current Game.
	 * 
	 * @return
	 */
	public PlayingField getField() {
		return field;
	}
	
	/**
	 * Returns if Fieldmanipulation is allowed.
	 * 
	 * @return
	 */
	public boolean isFieldManipulationForbidden() {
		return fieldManipulationForbidden;
	}
	
	/**
	 * Returns if Forced Move Order is on or off.
	 * 
	 * @return
	 */
	public boolean isForceMoveOrder() {
		return forceMoveOrder;
	}
	
	/**
	 * Loads a saved Game Situation. NOT WORKING YET.
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public Game loadSituation(String filename) throws Exception{ //buggy
		Game loadedGame = this;
		try {
           
            loadedGame = SaveAndLoad.loadGame(filename);
            
        } catch (Exception e) {
        }
        loadedGame.printPlayingField();
        return loadedGame;
        
	}
	
	/**
 	* Moves the figure on startingfield to destinationfield 
 	* 
 	* Precondition:
 	* destinationfield is not empty and Move has been validated inside moveFigure method
 	* 
 	* @param startingField
 	* @param destinationField
 	* @throws Exception
	*/
	public void moveFigure(String startingField, String destinationField)
			throws Exception {

		int[] fieldHelp = InputTranslate.InputTranslateMethod(startingField);
		String colourCurrent = null;
		String typeCurrent = null;
		Field[][] field2 = getField().getFieldArray();

		if (forceMoveOrder) {

			if (!field2[fieldHelp[0]][fieldHelp[1]].getEmpty()) {
				{
					colourCurrent = (field2[fieldHelp[0]][fieldHelp[1]])
							.getFigure().getColour();
					typeCurrent = (field2[fieldHelp[0]][fieldHelp[1]])
							.getFigure().getType();
				}
				if (colourCurrent == turn) {
					if (turn == "white") {
						this.getField().moveFigure(startingField, destinationField);
						turn = "black";
					} else {
						this.getField().moveFigure(startingField, destinationField);
						turn = "white";
					}
				} else {
					System.out.println("	Its not your turn. Therefore the "
							+ colourCurrent + " " + typeCurrent + " on\n"
							+ startingField + " cannont move to "
							+ destinationField);
					System.out.println("\n	  <No changes have been made>");
				}
			} else {
				System.out
						.println("	No figure to be moved on " + startingField);
				System.out.println("	<No changes have been made>");
			}
		} else if (!field2[fieldHelp[0]][fieldHelp[1]].getEmpty()){
			colourCurrent = (field2[fieldHelp[0]][fieldHelp[1]]).getFigure().getColour();
			
		
						this.getField().moveFigure(startingField, destinationField);
				
		} else {
			System.out.println("	No figure to be moved on " + startingField);
			System.out.println("	<No changes have been made>");
		}

		//printPlayingField();
		this.getField().markAttackedFields();
	}
	
	/**
	 * Performs random Moves until only the two Kings are left.
	 * 
	 * @throws Exception
	 */
	public void moveRandom() throws Exception{
		String turn = "white";
		
		while (this.countFigures() > 2){
			int rowStart = (int) (Math.random()*10000 % 8);
			int columnStart = (int) (Math.random()*10000 % 8);
			int rowDestination = (int) (Math.random()*10000 % 8);
			int columnDestination = (int) (Math.random()*10000 % 8);
			
			char rowCharStart = 'A';
			char rowCharDestination = 'B';
			
			if (rowDestination == 0)
				rowCharDestination = 'A';
			else if (rowDestination == 7)
				rowCharDestination = 'H';
			else if (rowDestination == 6)
				rowCharDestination = 'G';
			else if (rowDestination == 5)
				rowCharDestination = 'F';
			else if (rowDestination == 4)
				rowCharDestination = 'E';
			else if (rowDestination == 3)
				rowCharDestination = 'D';
			else if (rowDestination == 2)
				rowCharDestination = 'C';
			else if (rowDestination == 1)
				rowCharDestination = 'B';
			
			if (rowStart == 0)
				rowCharStart = 'A';
			else if (rowStart == 7)
				rowCharStart = 'H';
			else if (rowStart == 6)
				rowCharStart = 'G';
			else if (rowStart == 5)
				rowCharStart = 'F';
			else if (rowStart == 4)
				rowCharStart = 'E';
			else if (rowStart == 3)
				rowCharStart = 'D';
			else if (rowStart == 2)
				rowCharStart = 'C';
			else if (rowStart == 1)
				rowCharStart = 'B';
			
			columnStart++;
			columnDestination++;
			
			
			
			String start = (""+rowCharStart+""+columnStart);
			String destination = (""+rowCharDestination+""+columnDestination);
			
			int[] startArray = InputTranslate.InputTranslateMethod(start);
			int[] destinationArray = InputTranslate
					.InputTranslateMethod(destination);
			
			
			
			if (!(field.getFieldArray()[rowStart][8 - columnStart].getEmpty()) && (field.getFieldArray()[rowStart][8 - columnStart].getFigure().getColour() == turn) && (MoveValidation.ValidMoveByMovementRules(startArray[0], startArray[1], destinationArray[0], destinationArray[1], field))){
				System.out.println("	"+start +"->"+ destination);
				printPlayingFieldAndMarkMovesForKing("white");
				printPlayingFieldAndMarkMovesForKing("black");
				moveFigure(start, destination);
				if (turn == "white")
					turn = "black";
				else
					turn = "white";
			}
			
		}
	}
	
	/**
	 * Performs random Moves until the given Count is reached.
	 * 
	 * @param count
	 * @throws Exception
	 */
	public void moveRandom(int count) throws Exception{
		
		int k = 0;
		String turn = "white";
		
		while (k <= count){
			
			
			int rowStart = (int) (Math.random()*10000 % 8);
			int columnStart = (int) (Math.random()*10000 % 8);
			int rowDestination = (int) (Math.random()*10000 % 8);
			int columnDestination = (int) (Math.random()*10000 % 8);
			
			char rowCharStart = 'A';
			char rowCharDestination = 'B';
			
			if (rowDestination == 0)
				rowCharDestination = 'A';
			else if (rowDestination == 7)
				rowCharDestination = 'H';
			else if (rowDestination == 6)
				rowCharDestination = 'G';
			else if (rowDestination == 5)
				rowCharDestination = 'F';
			else if (rowDestination == 4)
				rowCharDestination = 'E';
			else if (rowDestination == 3)
				rowCharDestination = 'D';
			else if (rowDestination == 2)
				rowCharDestination = 'C';
			else if (rowDestination == 1)
				rowCharDestination = 'B';
			
			if (rowStart == 0)
				rowCharStart = 'A';
			else if (rowStart == 7)
				rowCharStart = 'H';
			else if (rowStart == 6)
				rowCharStart = 'G';
			else if (rowStart == 5)
				rowCharStart = 'F';
			else if (rowStart == 4)
				rowCharStart = 'E';
			else if (rowStart == 3)
				rowCharStart = 'D';
			else if (rowStart == 2)
				rowCharStart = 'C';
			else if (rowStart == 1)
				rowCharStart = 'B';
			
			columnStart++;
			columnDestination++;
			
			
			
			String start = (""+rowCharStart+""+columnStart);
			String destination = (""+rowCharDestination+""+columnDestination);
			
			int[] startArray = InputTranslate.InputTranslateMethod(start);
			int[] destinationArray = InputTranslate
					.InputTranslateMethod(destination);
			
			
			
			if (!(field.getFieldArray()[rowStart][8 - columnStart].getEmpty()) && (field.getFieldArray()[rowStart][8 - columnStart].getFigure().getColour() == turn) && (MoveValidation.ValidMoveByMovementRules(startArray[0], startArray[1], destinationArray[0], destinationArray[1], field))){
					System.out.println("	"+start +"->"+ destination);
					printPlayingFieldAndMarkMovesForKing("white");
					printPlayingFieldAndMarkMovesForKing("black");
					moveFigure(start, destination);
					
					if (turn == "white")
						turn = "black";
					else
						turn = "white";
					
					k++;
			}
		}
	}
	
	public String[] randomMove(){
		String[] moves = new String[2];
		
		int rowStart = (int) (Math.random()*10000 % 8);
		int columnStart = (int) (Math.random()*10000 % 8);
		int rowDestination = (int) (Math.random()*10000 % 8);
		int columnDestination = (int) (Math.random()*10000 % 8);
		
		char rowCharStart = 'A';
		char rowCharDestination = 'B';
		
		if (rowDestination == 0)
			rowCharDestination = 'A';
		else if (rowDestination == 7)
			rowCharDestination = 'H';
		else if (rowDestination == 6)
			rowCharDestination = 'G';
		else if (rowDestination == 5)
			rowCharDestination = 'F';
		else if (rowDestination == 4)
			rowCharDestination = 'E';
		else if (rowDestination == 3)
			rowCharDestination = 'D';
		else if (rowDestination == 2)
			rowCharDestination = 'C';
		else if (rowDestination == 1)
			rowCharDestination = 'B';
		
		if (rowStart == 0)
			rowCharStart = 'A';
		else if (rowStart == 7)
			rowCharStart = 'H';
		else if (rowStart == 6)
			rowCharStart = 'G';
		else if (rowStart == 5)
			rowCharStart = 'F';
		else if (rowStart == 4)
			rowCharStart = 'E';
		else if (rowStart == 3)
			rowCharStart = 'D';
		else if (rowStart == 2)
			rowCharStart = 'C';
		else if (rowStart == 1)
			rowCharStart = 'B';
		
		columnStart++;
		columnDestination++;
		
		
		
		moves[0] = (""+rowCharStart+""+columnStart);
		moves[1] = (""+rowCharDestination+""+columnDestination);
		return moves;
		
	}

	/**
	 * Moves Figures depending on moveManually
	 * 
	 * if moveManually is true, white Moves are determined by the User instead of being randomed
	 * else every Move is random
	 * 
	 * @throws Exception
	 */
	public void moveRandomConfirmMoves(boolean moveManually, boolean mainMenu) throws Exception{
		String turn = "white";
		printPlayingField();
		
		while (this.countFigures() > 2){
			int rowStart = (int) (Math.random()*10000 % 8);
			int columnStart = (int) (Math.random()*10000 % 8);
			int rowDestination = (int) (Math.random()*10000 % 8);
			int columnDestination = (int) (Math.random()*10000 % 8);
			
			char rowCharStart = 'A';
			char rowCharDestination = 'B';
			
			if (rowDestination == 0)
				rowCharDestination = 'A';
			else if (rowDestination == 7)
				rowCharDestination = 'H';
			else if (rowDestination == 6)
				rowCharDestination = 'G';
			else if (rowDestination == 5)
				rowCharDestination = 'F';
			else if (rowDestination == 4)
				rowCharDestination = 'E';
			else if (rowDestination == 3)
				rowCharDestination = 'D';
			else if (rowDestination == 2)
				rowCharDestination = 'C';
			else if (rowDestination == 1)
				rowCharDestination = 'B';
			
			if (rowStart == 0)
				rowCharStart = 'A';
			else if (rowStart == 7)
				rowCharStart = 'H';
			else if (rowStart == 6)
				rowCharStart = 'G';
			else if (rowStart == 5)
				rowCharStart = 'F';
			else if (rowStart == 4)
				rowCharStart = 'E';
			else if (rowStart == 3)
				rowCharStart = 'D';
			else if (rowStart == 2)
				rowCharStart = 'C';
			else if (rowStart == 1)
				rowCharStart = 'B';
			
			columnStart++;
			columnDestination++;
			
			
			
			String start = (""+rowCharStart+""+columnStart);
			String destination = (""+rowCharDestination+""+columnDestination);
			
			int[] startArray = InputTranslate.InputTranslateMethod(start);
			int[] destinationArray = InputTranslate
					.InputTranslateMethod(destination);
			
			if (CheckClass.isCheckMate("white", this.field) || CheckClass.isCheckMate("black", this.field)){
					printPlayingField();
					break;}
			if(moveManually){
				if (turn == "white"){
					//printPlayingField();
					while(true){
						if(mainMenu){
						this.mainMenu();}
				Scanner scanner = new Scanner(System.in);
				Pattern pattern = Pattern.compile("[a-hA-H][1-8]");
				 
				System.out.print("	Please Insert Starting Field Of Your Next Move: ");
				String startField = scanner.next();
				Matcher startMatcher = pattern.matcher(startField);
				 boolean startMatches = startMatcher .matches();
				 if (!startMatches){
					 System.out.println("\n	Failure. This is an invalid Input. Please insert again: \n");
					 continue;}
				System.out.print("	Please Insert Destination Field Of Your Next Move: ");
				String destinationField = scanner.next();
				Matcher destinationMatcher = pattern.matcher(destinationField);
				 boolean destinationMatches = destinationMatcher .matches();
				 if (!destinationMatches){
					 System.out.println("\n	Failure. This is an invalid Input. Please insert again: \n");
					 continue;}
			 
					int[] startingFieldHelp = InputTranslate
							.InputTranslateMethod(startField);
					int[] destinationFieldHelp = InputTranslate
							.InputTranslateMethod(destinationField);
					
					if(!(this.field.getFieldArray()[startingFieldHelp[0]][startingFieldHelp[1]].getEmpty()) && MoveValidation.ValidMoveByMovementRules(startingFieldHelp[0], startingFieldHelp[1], destinationFieldHelp[0], destinationFieldHelp[1], this.field)){
					
						Field startField_2 = this.field.getFieldArray()[startingFieldHelp[0]][startingFieldHelp[1]];
						String colourStart = startField_2.getFigure().getColour();
						String typeStart = startField_2.getFigure().getType();
						Field destinationField_2 = this.field.getFieldArray()[destinationFieldHelp[0]][destinationFieldHelp[1]];
						moveFigure(startField, destinationField);
						System.out.println("	"+startField +" -> "+ destinationField+" ("+colourStart+" "+typeStart+")");
						turn = "black";
							break;
						}
					else
						System.out.println("\n	Failure. This is an invalid Move. Please insert again: \n");}
				
				}
				else{
					while(true){
						
						rowStart = (int) (Math.random()*10000 % 8);
						columnStart = (int) (Math.random()*10000 % 8);
						rowDestination = (int) (Math.random()*10000 % 8);
						columnDestination = (int) (Math.random()*10000 % 8);
						
						rowCharStart = 'A';
						rowCharDestination = 'B';
						
						if (rowDestination == 0)
							rowCharDestination = 'A';
						else if (rowDestination == 7)
							rowCharDestination = 'H';
						else if (rowDestination == 6)
							rowCharDestination = 'G';
						else if (rowDestination == 5)
							rowCharDestination = 'F';
						else if (rowDestination == 4)
							rowCharDestination = 'E';
						else if (rowDestination == 3)
							rowCharDestination = 'D';
						else if (rowDestination == 2)
							rowCharDestination = 'C';
						else if (rowDestination == 1)
							rowCharDestination = 'B';
						
						if (rowStart == 0)
							rowCharStart = 'A';
						else if (rowStart == 7)
							rowCharStart = 'H';
						else if (rowStart == 6)
							rowCharStart = 'G';
						else if (rowStart == 5)
							rowCharStart = 'F';
						else if (rowStart == 4)
							rowCharStart = 'E';
						else if (rowStart == 3)
							rowCharStart = 'D';
						else if (rowStart == 2)
							rowCharStart = 'C';
						else if (rowStart == 1)
							rowCharStart = 'B';
						
						columnStart++;
						columnDestination++;
						
						
						
						start = (""+rowCharStart+""+columnStart);
						destination = (""+rowCharDestination+""+columnDestination);
						
						startArray = InputTranslate.InputTranslateMethod(start);
						destinationArray = InputTranslate
								.InputTranslateMethod(destination);
						
						if (!(field.getFieldArray()[rowStart][8 - columnStart].getEmpty()) && (field.getFieldArray()[rowStart][8 - columnStart].getFigure().getColour() == turn) && (MoveValidation.ValidMoveByMovementRules(startArray[0], startArray[1], destinationArray[0], destinationArray[1], field))){
					moveFigure(start, destination);
					System.out.println("	"+start +" -> "+ destination+" ("+field.getFieldArray()[rowDestination][8 - columnDestination].getFigure().getColour()+" "+field.getFieldArray()[rowDestination][8 - columnDestination].getFigure().getType()+")\n\n");
					turn = "white";
					break;}}
					
				}
				
			}
			else
			{
			
			if (!(field.getFieldArray()[rowStart][8 - columnStart].getEmpty()) && (field.getFieldArray()[rowStart][8 - columnStart].getFigure().getColour() == turn) && (MoveValidation.ValidMoveByMovementRules(startArray[0], startArray[1], destinationArray[0], destinationArray[1], field))){

				
			
				moveFigure(start, destination);
				System.out.println("	"+start +" -> "+ destination+" ("+field.getFieldArray()[rowDestination][8 - columnDestination].getFigure().getColour()+" "+field.getFieldArray()[rowDestination][8 - columnDestination].getFigure().getType()+")\n\n");	
				
				Scanner scanner = new Scanner(System.in);
				String s = scanner.nextLine();
			
				
				
				if (turn == "white")
					turn = "black";
				else
					turn = "white";
			}
			
		}
			}
	}
	
	/**
	 * UNUSED
	 * Prints all allowed moves for a Colour.
	 * 
	 * Does NOT consider the fact, that at checked Situation only Moves protecting the King are allowed
	 * 
	 * @param colour		"white" or "black"
	 * @throws Exception
	 */
	public void printAllAllowedMovesByColour(String colour) throws Exception {
		MoveClass.printAllAllowedMovesByColour(this.getField(), colour);
	}

	/**
	 * Prints all allowed Moves for a Colour at a Situation, where the given Colour is not check.
	 * 
	 * @param colour
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printAllMovesForColourNotCheck(String colour) throws Exception{
		ArrayList moves = MoveClass.moveArrayByColour(this.field, colour);
		System.out.println("\n=========================================================================");
		System.out.println("\n	A List Of Allowed Moves ("+moves.size()+"):\n");
		for (int i = 0; i < moves.size(); i++){
			Move currentMove = (Move) moves.get(i);
			System.out.println("	"+currentMove.getStartField().getCharRow()+""+currentMove.getStartField().getIntColumn()+" -> "+currentMove.getDestinationField().getCharRow()+""+currentMove.getDestinationField().getIntColumn()+" ("+currentMove.getStartField().getType()+")");
		}
	}
	
	@SuppressWarnings("unchecked")
	public String printAllMovesForColourNotCheckToString(String colour) throws Exception{
		String movesString = "";
		ArrayList moves = MoveClass.moveArrayByColour(this.field, colour);
		//movesString = movesString+("\n=========================================================================\n\n");
		//movesString = movesString+("A List Of Allowed Moves ("+moves.size()+"):\n\n");
		for (int i = 0; i < moves.size(); i++){
			Move currentMove = (Move) moves.get(i);
			movesString = movesString+(""+currentMove.getStartField().getCharRow()+""+currentMove.getStartField().getIntColumn()+" -> "+currentMove.getDestinationField().getCharRow()+""+currentMove.getDestinationField().getIntColumn()+" ("+currentMove.getStartField().getType()+")\n");
		}
		return movesString;
	}

	/**
	 * UNUSED
	 * 
	 * Prints every Field between the given field and the enemy King
	 * 
	 * Works for Bishop, Rook and Queen.
	 * 
	 * @param field
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printFieldsBetween(String field) throws Exception{
		Field enemyField = this.getField().getFieldArray()[InputTranslate.InputTranslateMethod(field)[0]][InputTranslate.InputTranslateMethod(field)[1]];
		ArrayList fields = CheckClass.listOfFieldsBetweenAttackerAndKing(enemyField, this.getField());
		for (int i=0; i < fields.size(); i++){
			Field current = (Field) fields.get(i);
			System.out.println(current.getCharRow()+" "+current.getIntColumn());
		}
	}
	
	/**
	 * Prints if the current colour is check.
	 * 
	 * @param colour
	 * @throws Exception
	 */
	public void printIsCheck(String colour) throws Exception {
		CheckClass.printIsCheck(colour, this.getField());
	}

	/**
	 * UNUSED
	 * Prints if the current colour is check mate
	 * 
	 * @param colour
	 * @throws Exception
	 */
	public void printIsCheckMate(String colour) throws Exception{
		CheckClass.printIsCheckMate(colour, this.field);
	}
	
	/**
	 * Prints a list of all Fields a Colour is actually allowed to move.
	 * 
	 * @param colour
	 * @throws Exception
	 */
	public void printListOfActuallyAllowedFieldsToMoveByColour(String colour)
			throws Exception {
		PossibleMoves.printListOfActuallyAllowedFieldsToMoveByColour(colour, this.getField()
				.getFieldArray());
	}
	
	/**
	 * Prints a List of all Fields a Figure is actually allowed to move.
	 * 
	 * @param field
	 * @throws Exception
	 */
	public void printListOfActuallyAllowedFieldsToMoveByFigure(String field) throws Exception {

		int[] fieldHelp = InputTranslate.InputTranslateMethod(field);
		int row = fieldHelp[0];
		int column = fieldHelp[1];
		Field[][] fieldArray = this.getField().getFieldArray();
		System.out
				.println("\n=========================================================================\n");
		if (!fieldArray[row][column].getEmpty())
			PossibleMoves.printListOfActuallyAllowedFieldsToMoveByFigure(field, fieldArray);
		else {
			System.out.println("	No figure on " + field
					+ ". Therefore no possible moves could be printed");
		}
		

	}
	
	/**
	 * Prints a List of Figures giving check to the given Colour.
	 * 
	 * @param colour
	 * @throws Exception
	 */
	public void printListOfFiguresGivingCheckToColour(String colour)
			throws Exception {
		printPlayingField();
		CheckClass.printListOfFiguresGivingCheckToColour(colour, this.getField());
	}
	
	/**
	 * Prints all actually allowed Moves for the given Colour.
	 * 
	 * Considers if the King is check.
	 * 
	 * @param colour
	 * @throws Exception
	 */
	public void printListOfMovesAllowedForColour(String colour) throws Exception{
		if (CheckClass.isGivenColourCheck(colour, this.field)){
			printMovesAllowedAtACheckedSituation(colour);}
		else{
			printAllMovesForColourNotCheck(colour);}
			
	}
	
	public String printListOfMovesAllowedForColourToString(String colour) throws Exception{
		String moves = "";
		if (CheckClass.isGivenColourCheck(colour, this.field)){
			moves = moves+printMovesAllowedAtACheckedSituationToString(colour);}
		else{
			moves = moves+printAllMovesForColourNotCheckToString(colour);}
		
		return moves;
	}
	
	/**
	 * UNUSED
	 * Prints a List of Moves that block the Attacking Line of an attacking Rook, Bishop or Queen.
	 * 
	 * @param attacker
	 * @throws Exception
	 */
	public void printListOfMovesBlockingAnAttackersLine(String attacker) throws Exception{
		
		int[] array = InputTranslate.InputTranslateMethod(attacker);
		Field attackerField = this.field.getFieldArray()[array[0]][array[1]];
		CheckClass.printMovesToAvoidCheckMateByBlockingAttackersPath(attackerField, this.field);
	}

	/**
	 * Prints a List of Moves that are allowed at a Check-Situation
	 * @param colour
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printMovesAllowedAtACheckedSituation(String colour) throws Exception{
		ArrayList moves = CheckClass.movesAllowedAtACheckedSituation(colour, this.field);
		System.out.println("\n=========================================================================");
		System.out.println("\n	A List Of Allowed Moves for "+colour+" ("+moves.size()+"):\n\n");
		System.out.println("	Note:");
		System.out.println("	You Are Check, Therefore Your Allowed Moves Are Limited To The Following:\n");
		for (int i = 0; i < moves.size();i++){
			Move currentMove = (Move) moves.get(i);
			Field currentStart = currentMove.getStartField();
			Field currentDestination = currentMove.getDestinationField();
			
		System.out.println(""+currentStart.getCharRow()+""+currentStart.getIntColumn()+" -> "+currentDestination.getCharRow()+""+currentDestination.getIntColumn()+" ("+currentStart.getType()+")");}
	}
	
	@SuppressWarnings("unchecked")
	public String printMovesAllowedAtACheckedSituationToString(String colour) throws Exception{
		String movesString = "";
		ArrayList moves = CheckClass.movesAllowedAtACheckedSituation(colour, this.field);
		//movesString = movesString+("\n=========================================================================\n");
		//movesString = movesString+("A List Of Allowed Moves for "+colour+" ("+moves.size()+"):\n\n");
		//movesString = movesString+("Note:\n");
		movesString = movesString+("You Are Check!\nTherefore your allowed\nMoves are limited to:\n\n");
		for (int i = 0; i < moves.size();i++){
			Move currentMove = (Move) moves.get(i);
			Field currentStart = currentMove.getStartField();
			Field currentDestination = currentMove.getDestinationField();
			
			movesString = movesString+(""+currentStart.getCharRow()+""+currentStart.getIntColumn()+" -> "+currentDestination.getCharRow()+""+currentDestination.getIntColumn()+" ("+currentStart.getType()+")\n");}
	return movesString;
	}

	/**
	 * Prints the Playingfield at the current Situation.
	 * 
	 * @throws Exception
	 */
	public void printPlayingField() throws Exception {
		
		System.out.println("\n\n============================= "+ getField().getCounter()
				+ ". Move ===================================");
		System.out.print("                                                          © Markus Ziller");
		PrintPlayingField.printCurrentSituation(this.getField());
		if(CheckClass.isCheckMate("white", getField())){
			System.out.println("\n	White is check mate!");}
		else{
		
			if (CheckClass.isGivenColourCheck("white", getField())){

			System.out.println("\n	White is check!");}}
		
		
		if(CheckClass.isCheckMate("black", getField())){
			System.out.println("\n	Black is check mate!");}
		else{
		
			if (CheckClass.isGivenColourCheck("black", getField())){

			System.out.println("\n	Black is check!");}}

	}
	
	public String printPlayingFieldString() throws Exception {
		String rep = "";
		
		rep = (rep+"\n\n============================= "+ getField().getCounter()
				+ ". Move ===================================\n");
		rep = (rep+"                                                          © Markus Ziller\n\n\n");
		rep = (rep+PrintPlayingField.printCurrentSituationString(this.getField()));
		if(CheckClass.isCheckMate("white", getField())){
			rep = (rep+"\n	White is check mate!\n");}
		else{
		
			if (CheckClass.isGivenColourCheck("white", getField())){

				rep = (rep+"\n	White is check!\n");}}
		
		
		if(CheckClass.isCheckMate("black", getField())){
			rep = (rep+"\n	Black is check mate!\n");}
		else{
		
			if (CheckClass.isGivenColourCheck("black", getField())){

				rep = (rep+"\n	Black is check!\n");}}
		
		return rep;

	}

	public void mainMenu() throws Exception{
		while (true){
			//System.out.println ("	What do you want to do next\n");
			System.out.println ("\n	======== MAIN MENU ========\n");
			System.out.println ("	(0) Print all allowed Moves.");
			System.out.println ("	(1) Print Playfield an mark Moves for the King.");
			System.out.println ("	(2) Print Playfield an mark Moves for a Figur on a certain Field.");
			System.out.println ("	(3) Castle.");
			System.out.println ("	(4) Move Figure.");
			System.out.print ("\n	Insert your choice: ");
			
			
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.next();
			if (choice.equals("0")){
				System.out.println();
				this.printListOfMovesAllowedForColour("white");
				System.out.println();}
				else
					if (choice.equals("1")){
						System.out.println();
						this.printPlayingFieldAndMarkMovesForKing("white");
						System.out.println();}
					else
						if (choice.equals("2")){
							System.out.println();
							System.out.print("	The moves for which figur shall be marked?	");
							
							String field = scanner.next();
							this.printPlayingField(field);
							}
						else
							if (choice.equals("3")){
								System.out.println();
								System.out.print("	Insert wQ for queenside Castling or wK for kingside Castling:	");
								
								String field = scanner.next();
								this.castling(field);
								}
							else
								if (choice.equals("4")){
									System.out.println();
									break;
								}
								else
									if (choice.equals("addF")){
										/*System.out.println();
										System.out.print("	Insert field:	");
										Scanner fieldScanner = new Scanner(System.in);
										String field = fieldScanner.next();
										System.out.println();
										System.out.print("	Insert type:	");
										Scanner typeScanner = new Scanner(System.in);
										String type = typeScanner.next();
										System.out.println();
										System.out.print("	Insert colour:	");
										Scanner colourScanner = new Scanner(System.in);
										String colour = colourScanner.next();
										this.getField().addFigureInterface(field, type, colour);*/
										
									}else
										if (choice.equals("4")){
											System.out.println();
											break;
										}else
											if (choice.equals("4")){
												System.out.println();
												break;
											}
			
			System.out.println ("\n	==================\n");
			//break;
			
		}
	}
	
	/**
	 * Prints the playing field and marks allowed fields to move, either for a specific figure or a colour
	 * 
	 * checked 10.4
	 * 
	 * @param input			= "a3", "B2"... for a field and "white" or "black" for a colour
	 * @throws Exception
	 */
	public void printPlayingField(String input) throws Exception {
		int[] fieldHelp;
		Field[][] field2 = getField().getFieldArray();
		if (input == "white" || input == "black") {
			System.out
					.println("\n=========================================================================");
			System.out.print("                                                          © Markus Ziller");
			PrintPlayingField
					.printCurrentSituationAndMarkPossibleFieldsForColour(
							this.getField(), input);
		} else {
			fieldHelp = InputTranslate.InputTranslateMethod(input);

			if (!field2[fieldHelp[0]][fieldHelp[1]].getEmpty()) {
				System.out.println("\n\n============================= "
						+ getField().getCounter()
						+ ". Move ===================================");
				System.out.print("                                                          © Markus Ziller");
				PrintPlayingField.printCurrentSituationAndMarkPossibleFields(
						this.getField(), input);
			} else {
				System.out
						.println("	No figure on "
								+ input
								+ ".\n	Therefore no reachable fields have been printed");
				printPlayingField();
			}
		}

	}
	
	/**
	 * Prints the playing field and marks allowed fields for the king to move
	 * 
	 * checked 10.4
	 * 
	 * @param colour		= "white" or "black"
	 * @throws Exception
	 */
	public void printPlayingFieldAndMarkMovesForKing(String colour) throws Exception{
		Field kingField = CheckClass.getKingFieldByColour(colour, this.field);
		char s = kingField.getCharRow();
		int k = kingField.getIntColumn();
		String i = (""+s+""+k);
		printPlayingField(i);
		
	}

	/**
	 * Removes the figure on the current field
	 * 
	 * @param field
	 * @throws Exception
	 */
	public void removeFigure(String field) throws Exception {
		if (!fieldManipulationForbidden){
			this.getField().removeFigureInterface(field);}
		else{
			System.out
					.println("Fieldmanipulation is not allowed. Therefore no figure has been removed from "
							+ field);}
		
		this.getField().markAttackedFields();
	}
	
	/**
	 * Runs the following test on the current Game:
	 * 
	 * 	printPlayingField();
		printAllPossibleMovesForColour("white");
		printAllPossibleMovesForColour("black");
		printPlayingField("white");
		printPlayingField("black");
		printListOfFiguresGivingCheckToColour("white");
		printListOfFiguresGivingCheckToColour("black");
		printListOfThreatenedFieldsByColour("white");
		printListOfThreatenedFieldsByColour("black");
		printIsCheck("white");
		printIsCheck("black");
		printPlayingFieldAndMarkMovesForKing("white");
		printPlayingFieldAndMarkMovesForKing("black");
		printPlayingField();
	 * 
	 * @throws Exception
	 */
	public void runTests() throws Exception {
		
		
		//this.printPlayingField();
	
		this.printPlayingField("white");
		this.printPlayingField("black");
		this.printListOfFiguresGivingCheckToColour("white");
		this.printListOfFiguresGivingCheckToColour("black");	
		this.printListOfMovesAllowedForColour("white");
		this.printListOfMovesAllowedForColour("black");
		this.printListOfActuallyAllowedFieldsToMoveByColour("white");
		this.printListOfActuallyAllowedFieldsToMoveByColour("black");
		//this.printIsCheck("white");
		//this.printIsCheck("black");
		this.printPlayingFieldAndMarkMovesForKing("white");
		this.printPlayingFieldAndMarkMovesForKing("black");
		this.printPlayingField();
	}

	/**
	 * Saves the current Situation
	 * 
	 * @param filename
	 */
	public void saveSituation(String filename){
		
		try {
            SaveAndLoad.saveGame(this, filename);
            
            
        } catch (Exception e) {
        }

	}

	/**
	 * Allows or Forbiddens to manually add or remove Figures
	 * 
	 * @param fieldManipulationForbidden
	 */
	public void setFieldManipulationAllowed(boolean fieldManipulationForbidden) {
		this.fieldManipulationForbidden = fieldManipulationForbidden;
	}

	/**
	 * Enables or disables the Forced Move Order
	 * 
	 * @param forceMoveOrder
	 */
	public void setForceMoveOrder(boolean forceMoveOrder) {
		this.forceMoveOrder = forceMoveOrder;
	}

	/**
	 * Returns if a Move is valid at the current (checked) Situation
	 * @param startingField
	 * @param destinationField
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean validMoveAtCheckedSituation(String startingField, String destinationField) throws Exception{
		int[] startingFieldHelp = InputTranslate.InputTranslateMethod(startingField);
		int[] destinationFieldHelp = InputTranslate.InputTranslateMethod(destinationField);
		
		Field[][] fieldArray = this.field.getFieldArray();
		Field startField = fieldArray[startingFieldHelp[0]][startingFieldHelp[1]];
		Field destinationField_Field = fieldArray[destinationFieldHelp[0]][destinationFieldHelp[1]];
		String colour = startField.getFigure().getColour();
		
		Move moveToBeChecked = new Move(startField, destinationField_Field);
		
		ArrayList validMoves = CheckClass.movesAllowedAtACheckedSituation(colour, this.field);
		boolean contains = false;
		for (int i = 0; i<validMoves.size(); i++){
			Move currentMove = (Move) validMoves.get(i);
			if(currentMove.getStartField().equals(startField) && currentMove.getDestinationField().equals(destinationField_Field))
				contains = true;
			
		}

		return contains;
	}

}
