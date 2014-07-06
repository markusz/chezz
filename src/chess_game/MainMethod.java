package chess_game;

import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * @author Markus
 * 
 */
@SuppressWarnings("unused")
public class MainMethod {

	public static void main(String args[]) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());


		Game testGame = new Game(true, false, true);
		ChessGUI theAppWindow = new ChessGUI(testGame);
		theAppWindow.setBounds(100, 100, 600, 600);
		theAppWindow.setVisible(true);/**/

		/*while (true) {
			//String infoMsg = "2";
			Scanner scanner = new Scanner(System.in);
			System.out
					.print("\n	In which mode do you want to play?\n	------------------------------------\n\n	(1) Simple Mode\n	(2) Extended Mode\n\n	(4) Let the KI Play\n\n\n	(9) Print General Information\n\n	Your Choice:");
			String choice = scanner.next();
			if (choice.equals("1")) {
				Game testGame = new Game(true, false, false);
				testGame.moveRandomConfirmMoves(true, false);
			} else {
				if (choice.equals("2")) {
					Game testGame = new Game(true, false, false);
					testGame.moveRandomConfirmMoves(true, true);
				} else {
					if (choice.equals("4")){
						Game testGame = new Game(true, false, false);
					testGame.moveRandomConfirmMoves(false, false);}
					else
					if (choice.equals("9")) {
						Scanner scanner2 = new Scanner(System.in);
						System.out.println("\n\n	Achja Komm mal her!\n");
						System.out.println("	Press Enter to Continue");
						@SuppressWarnings("unused")
						String s = scanner2.nextLine();

					} else if (choice.equals("6.10.07")) {
						break;
					}
				}
			}
			*/

		}

		/*
		 * Notizen für mich:
		 * 
		 * 
		 * 
		 * - createRandomSituation() wirft NullPointerE. wenn Feld vom weißen
		 * König = Feld vom schwarzen König gerandomed wird (Egal?)
		 * 
		 * 
		 * 
		 * - En Passant implementieren
		 */

		/**
		 * 
		 * <Schach Alpha_0.7>
		 * 
		 * -------------------------------------------------------------------
		 * -/////////////////////////// Generelles \\\\\\\\\\\\\\\\\\\\\\\\\\-
		 * -------------------------------------------------------------------
		 * 
		 * - Eingabe von Felder als String vom Typ "A1" oder "a1". Gültiger
		 * Bereich: A-H für Reihen und 1-8 für Zeilen
		 * 
		 * - Eingabe von Farben als String. Erlaubt sind "white" und "black"
		 * 
		 * - Eingabe von Figuren als String. Erlaubt sind:
		 * 
		 * "king" (König) "queen" (Dame) "bishop" (Läufer) "knight" (Pferd)
		 * "rook" (Turm) "pawn" (Bauer)
		 * 
		 * 
		 * - Züge werden automatisch auf Korrektheit überprüft:
		 * 
		 * Folgende Regeln gelten hierbei:
		 * 
		 * - Sie dürfen einander grundsätzlich nicht überspringen (Ausnahmen
		 * gelten für den Springer sowie bei der Rochade). Sie dürfen also immer
		 * nur soweit gezogen werden, wie sie nicht durch eine eigene Figur in
		 * ihrer Bewegung blockiert werden (dann müssen sie spätestens vor dem
		 * entsprechenden Feld stoppen) oder bis auf das Feld, auf dem der erste
		 * gegnerische Spielstein in der Bahn steht. In diesem Falle schlagen
		 * sie diesen und kommen auf seinem Feld zu stehen.
		 * 
		 * - Wird ein König von einer gegnerischen Schachfigur bedroht (d. h.,
		 * der König könnte im nächsten Zug geschlagen werden), so steht dieser
		 * König im Schach. Ein „Schach“ darf nicht ignoriert werden, der
		 * Spieler muss also entweder den Schach bietenden Spielstein schlagen,
		 * einen eigenen in die Wirkungslinie des Schachgebots ziehen (das ist
		 * nicht möglich, wenn ein Springer Schach bietet) oder den König aus
		 * dem Schach bewegen (die einzige Möglichkeit bei einem Doppelschach,
		 * wobei hier eventuell auch ein schachbietender Stein geschlagen werden
		 * kann). Steht der König im Schach, darf er nicht mittels der Rochade
		 * aus diesem entfliehen.
		 * 
		 * 
		 * 
		 * - Game nameOfGame = new Game() erstellt neues Spiel mit gewöhnlicher
		 * Schach-Startaufstellung, aktivierter Erzwingung der korrekten
		 * Zugreihenfolge, und Unterbindung manueller Feldmanipulation
		 * 
		 * - Game nameOfGame = new Game(Bool_Startaufstellung,
		 * Bool_manuelleManipulationVerboten, Bool_ZugreihenfolgeErzwingen)
		 * erstellt ein Spielfeld mit den eingebenen Parametern. Eingabe jeweils
		 * im Format "true" oder "false"
		 * 
		 * - Die Ausgabe des Schachfeldes in der Konsole erfolgt nach folgenden
		 * Regeln:
		 * 
		 * x = bedrohtes Feld + = Feld auf dem geworfen werden kann
		 * 
		 * K = king (König) Q = queen (Dame) b = bishop (Läufer) k = knight
		 * (Pferd) r = rook (Turm) p = pawn (Bauer)
		 * 
		 * Schwarze Figuren haben einen Punkt vor- und nachstehen (Bsp .Q. =
		 * schwarze Dame; Q = weiße Dame)
		 * 
		 * -------------------------------------------------------------------
		 * -/////////////////////// Mögliche Eingaben \\\\\\\\\\\\\\\\\\\\\\\-
		 * -------------------------------------------------------------------
		 * 
		 * 
		 * # .moveFigure(vonFeld, nachFeld) - Macht einen Zug von einem Feld zum
		 * anderen.
		 * --------------------------------------------------------------------
		 * # .moveRandom()/.moveRandom(int count) - macht zufällige Züge bis nur
		 * noch die zwei Könige übrig sind oder, bei Eingabe eines int-Wertes,
		 * solang bis die gegebene Anzahl von Zügen erreicht ist.
		 * --------------------------------------------------------------------
		 * # .moveRandomConfirmMoves() - wie .moveRandom(), nur das nach jedem
		 * Zug mit Enter bestätigt werden muss, bevor der nächste ausgeführt
		 * wird
		 * --------------------------------------------------------------------
		 * # .createRandomSituation() - erstellt eine Spielsituation per
		 * Zufallsgenerator. Standardeinstellung: 30 % Wahrscheinlichkeit, dass
		 * auf einem Feld eine Figur erstellt wird. Bei Eingabe einer Zahl x
		 * zwischen 0.0 und 1.0 ist die Wahrscheinlichkeit x*100.
		 * --------------------------------------------------------------------
		 * # .runTests() - führt folgende Befehle auf das aktuelle Spiel aus:
		 * 
		 * .printPlayingField(); .printAllPossibleMovesForColour("white");
		 * .printAllPossibleMovesForColour("black");
		 * .printPlayingField("white"); .printPlayingField("black");
		 * .printListOfFiguresGivingCheckToColour("white");
		 * .printListOfFiguresGivingCheckToColour("black");
		 * .printListOfThreatenedFieldsByColour("white");
		 * .printListOfThreatenedFieldsByColour("black");
		 * .printIsCheck("white"); .printIsCheck("black"); .printPlayingField();
		 * --------------------------------------------------------------------
		 * # .addFigure(feld, art, farbe) - Fügt eine die eingegebene Figur auf
		 * das gegebene Feld ein. FieldManipulationAllowed muss true sein
		 * --------------------------------------------------------------------
		 * # .removeFigure(feld, art, farbe) - analog zu addFigure
		 * --------------------------------------------------------------------
		 * # .setFieldManipulationForbidden(Bool) - Erlaubt (false) bzw.
		 * Unterbindet (true) das manuelle Hinzufügen oder Entfernen von Figuren
		 * --------------------------------------------------------------------
		 * # .printField() - Gibt ein Schachbrett mit der aktuellen Aufstellung
		 * auf der Konsole aus.
		 * --------------------------------------------------------------------
		 * # .printField(input) - Gibt ein Schachbrett mit der aktuellen
		 * Aufstellung auf der Konsole aus und markiert zusätzlich die
		 * erreichbaren Felder für die Figur auf einem eingegebenen Feld oder
		 * für alle Figuren der eingegebenen Farbe.
		 * 
		 * Input: Farbe oder Feld
		 * 
		 * - Felder die ohne Werfen einer gegnerischen Figur erreicht werden
		 * können, werden durch x gekennzeichnet.
		 * 
		 * - Felder auf denen eine gegnerische Figur geworfen wird, werden mit +
		 * gekennzeichnet.
		 * --------------------------------------------------------------------
		 * # .printListOfFiguresGivingCheckToColour(farbe) - Gibt eine Liste von
		 * Feldern aus, auf denen Figuren stehen die dem König der eingegebenen
		 * Farbe Schach geben.
		 * --------------------------------------------------------------------
		 * # .printListOfMovesAllowedForColour(Farbe) - Gibt eine Liste aller
		 * möglichen Züge für die gegebene Farbe aus.
		 * --------------------------------------------------------------------
		 * # .printListOfThreatenedFieldsByColour(Farbe) - Gibt eine Liste aller
		 * // deprecated Felder aus, die die gegebene Farbe in der aktuellen
		 * Spielsituation bedroht.
		 * --------------------------------------------------------------------
		 * # .printListOfMovesByFigure(Feld) - Gibt eine Liste aller Felder aus,
		 * // deprecated die die Figur auf dem Gegebenen Feld erreichen kann.
		 * --------------------------------------------------------------------
		 * # .setForceMoveOrder(Bool) - Aktiviert (true) bzw. Deaktiviert
		 * (false) die Erzwingung der korrekten Zugreihenfolge (Weiß, schwarz,
		 * weiß,...).
		 * --------------------------------------------------------------------
		 * # .printIsCheck(Farbe) - Gibt aus, ob die gegebene Farbe Schach ist.
		 * 
		 * 
		 * © Markus Ziller
		 **/

	//}

}
