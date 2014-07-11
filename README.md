-------------------------------------------------------------------
-/////////////////////////// Generelles \\\\\\\\\\\\\\\\\\\\\\\\\\-
-------------------------------------------------------------------
  
- Eingabe von Felder als String vom Typ "A1" oder "a1". G�ltiger
Bereich: A-H für Reihen und 1-8 für Zeilen
  
- Eingabe von Farben als String. Erlaubt sind "white" und "black"
  
- Eingabe von Figuren als String. Erlaubt sind:
  
"king" (König) "queen" (Dame) "bishop" (Läufer) "knight" (Pferd)
"rook" (Turm) "pawn" (Bauer)
  
  
- Züge werden automatisch auf Korrektheit überprüft:
  
Folgende Regeln gelten hierbei:
  
- Sie dürfen einander grundsätzlich nicht überspringen (Ausnahmen
gelten für den Springer sowie bei der Rochade). Sie dürfen also immer
nur soweit gezogen werden, wie sie nicht durch eine eigene Figur in
ihrer Bewegung blockiert werden (dann müssen sie spätestens vor dem
entsprechenden Feld stoppen) oder bis auf das Feld, auf dem der erste
gegnerische Spielstein in der Bahn steht. In diesem Falle schlagen
sie diesen und kommen auf seinem Feld zu stehen.
  
- Wird ein König von einer gegnerischen Schachfigur bedroht (d. h.,
der König könnte im nächsten Zug geschlagen werden), so steht dieser
König im Schach. Ein Schach darf nicht ignoriert werden, der
Spieler muss also entweder den Schach bietenden Spielstein schlagen,
einen eigenen in die Wirkungslinie des Schachgebots ziehen (das ist
nicht möglich, wenn ein Springer Schach bietet) oder den König aus
dem Schach bewegen (die einzige Möglichkeit bei einem Doppelschach,
wobei hier eventuell auch ein schachbietender Stein geschlagen werden
kann). Steht der König im Schach, darf er nicht mittels der Rochade
aus diesem entfliehen.
  
  
  
- Game nameOfGame = new Game() erstellt neues Spiel mit gew�hnlicher
Schach-Startaufstellung, aktivierter Erzwingung der korrekten
Zugreihenfolge, und Unterbindung manueller Feldmanipulation
  
- Game nameOfGame = new Game(Bool_Startaufstellung,
Bool_manuelleManipulationVerboten, Bool_ZugreihenfolgeErzwingen)
erstellt ein Spielfeld mit den eingebenen Parametern. Eingabe jeweils
im Format "true" oder "false"
  
- Die Ausgabe des Schachfeldes in der Konsole erfolgt nach folgenden
Regeln:
  
x = bedrohtes Feld + = Feld auf dem geworfen werden kann
  
K = king (König) Q = queen (Dame) b = bishop (Läufer) k = knight
(Pferd) r = rook (Turm) p = pawn (Bauer)
  
Schwarze Figuren haben einen Punkt vor- und nachstehen (Bsp .Q. =
schwarze Dame; Q = weiße Dame)
  
  
c Markus Ziller
