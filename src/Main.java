import model.Game;
import ui.GUI;

import javax.swing.*;

/**
 * @author Markus
 */
@SuppressWarnings("unused")
public class Main {

  public static void main(String args[]) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    Game game = new Game(true, false, true);
    GUI gui = new GUI(game);


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
         * Notizen f�r mich:
		 * 
		 * 
		 * 
		 * - createRandomSituation() wirft NullPointerE. wenn Feld vom wei�en
		 * K�nig = Feld vom schwarzen K�nig gerandomed wird (Egal?)
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
   * - Eingabe von Felder als String vom Typ "A1" oder "a1". G�ltiger
   * Bereich: A-H f�r Reihen und 1-8 f�r Zeilen
   *
   * - Eingabe von Farben als String. Erlaubt sind "white" und "black"
   *
   * - Eingabe von Figuren als String. Erlaubt sind:
   *
   * "king" (K�nig) "queen" (Dame) "bishop" (L�ufer) "knight" (Pferd)
   * "rook" (Turm) "pawn" (Bauer)
   *
   *
   * - Z�ge werden automatisch auf Korrektheit �berpr�ft:
   *
   * Folgende Regeln gelten hierbei:
   *
   * - Sie d�rfen einander grunds�tzlich nicht �berspringen (Ausnahmen
   * gelten f�r den Springer sowie bei der Rochade). Sie d�rfen also immer
   * nur soweit gezogen werden, wie sie nicht durch eine eigene Figur in
   * ihrer Bewegung blockiert werden (dann m�ssen sie sp�testens vor dem
   * entsprechenden Feld stoppen) oder bis auf das Feld, auf dem der erste
   * gegnerische Spielstein in der Bahn steht. In diesem Falle schlagen
   * sie diesen und kommen auf seinem Feld zu stehen.
   *
   * - Wird ein K�nig von einer gegnerischen Schachfigur bedroht (d. h.,
   * der K�nig k�nnte im n�chsten Zug geschlagen werden), so steht dieser
   * K�nig im Schach. Ein �Schach� darf nicht ignoriert werden, der
   * Spieler muss also entweder den Schach bietenden Spielstein schlagen,
   * einen eigenen in die Wirkungslinie des Schachgebots ziehen (das ist
   * nicht m�glich, wenn ein Springer Schach bietet) oder den K�nig aus
   * dem Schach bewegen (die einzige M�glichkeit bei einem Doppelschach,
   * wobei hier eventuell auch ein schachbietender Stein geschlagen werden
   * kann). Steht der K�nig im Schach, darf er nicht mittels der Rochade
   * aus diesem entfliehen.
   *
   *
   *
   * - Game nameOfGame = new Game() erstellt neues Spiel mit gew�hnlicher
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
   * K = king (K�nig) Q = queen (Dame) b = bishop (L�ufer) k = knight
   * (Pferd) r = rook (Turm) p = pawn (Bauer)
   *
   * Schwarze Figuren haben einen Punkt vor- und nachstehen (Bsp .Q. =
   * schwarze Dame; Q = wei�e Dame)
   *
   * -------------------------------------------------------------------
   * -/////////////////////// M�gliche Eingaben \\\\\\\\\\\\\\\\\\\\\\\-
   * -------------------------------------------------------------------
   *
   *
   * # .moveFigure(vonFeld, nachFeld) - Macht einen Zug von einem Feld zum
   * anderen.
   * --------------------------------------------------------------------
   * # .moveRandom()/.moveRandom(int count) - macht zuf�llige Z�ge bis nur
   * noch die zwei K�nige �brig sind oder, bei Eingabe eines int-Wertes,
   * solang bis die gegebene Anzahl von Z�gen erreicht ist.
   * --------------------------------------------------------------------
   * # .moveRandomConfirmMoves() - wie .moveRandom(), nur das nach jedem
   * Zug mit Enter best�tigt werden muss, bevor der n�chste ausgef�hrt
   * wird
   * --------------------------------------------------------------------
   * # .createRandomSituation() - erstellt eine Spielsituation per
   * Zufallsgenerator. Standardeinstellung: 30 % Wahrscheinlichkeit, dass
   * auf einem Feld eine Figur erstellt wird. Bei Eingabe einer Zahl x
   * zwischen 0.0 und 1.0 ist die Wahrscheinlichkeit x*100.
   * --------------------------------------------------------------------
   * # .runTests() - f�hrt folgende Befehle auf das aktuelle Spiel aus:
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
   * # .addFigure(feld, art, farbe) - F�gt eine die eingegebene Figur auf
   * das gegebene Feld ein. FieldManipulationAllowed muss true sein
   * --------------------------------------------------------------------
   * # .removeFigure(feld, art, farbe) - analog zu addFigure
   * --------------------------------------------------------------------
   * # .setFieldManipulationForbidden(Bool) - Erlaubt (false) bzw.
   * Unterbindet (true) das manuelle Hinzuf�gen oder Entfernen von Figuren
   * --------------------------------------------------------------------
   * # .printField() - Gibt ein Schachbrett mit der aktuellen Aufstellung
   * auf der Konsole aus.
   * --------------------------------------------------------------------
   * # .printField(input) - Gibt ein Schachbrett mit der aktuellen
   * Aufstellung auf der Konsole aus und markiert zus�tzlich die
   * erreichbaren Felder f�r die Figur auf einem eingegebenen Feld oder
   * f�r alle Figuren der eingegebenen Farbe.
   *
   * Input: Farbe oder Feld
   *
   * - Felder die ohne Werfen einer gegnerischen Figur erreicht werden
   * k�nnen, werden durch x gekennzeichnet.
   *
   * - Felder auf denen eine gegnerische Figur geworfen wird, werden mit +
   * gekennzeichnet.
   * --------------------------------------------------------------------
   * # .printListOfFiguresGivingCheckToColour(farbe) - Gibt eine Liste von
   * Feldern aus, auf denen Figuren stehen die dem K�nig der eingegebenen
   * Farbe Schach geben.
   * --------------------------------------------------------------------
   * # .printListOfMovesAllowedForColour(Farbe) - Gibt eine Liste aller
   * m�glichen Z�ge f�r die gegebene Farbe aus.
   * --------------------------------------------------------------------
   * # .printListOfThreatenedFieldsByColour(Farbe) - Gibt eine Liste aller
   * // deprecated Felder aus, die die gegebene Farbe in der aktuellen
   * Spielsituation bedroht.
   * --------------------------------------------------------------------
   * # .printListOfMovesByFigure(Feld) - Gibt eine Liste aller Felder aus,
   * // deprecated die die Figur auf dem Gegebenen Feld erreichen kann.
   * --------------------------------------------------------------------
   * # .setForceMoveOrder(Bool) - Aktiviert (true) bzw. Deaktiviert
   * (false) die Erzwingung der korrekten Zugreihenfolge (Wei�, schwarz,
   * wei�,...).
   * --------------------------------------------------------------------
   * # .printIsCheck(Farbe) - Gibt aus, ob die gegebene Farbe Schach ist.
   *
   *
   * � Markus Ziller
   **/

  //}

}
