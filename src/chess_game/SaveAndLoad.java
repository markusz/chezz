package chess_game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveAndLoad {
	
	 static void saveGame(Serializable object, String filename) throws IOException {
	       ObjectOutputStream objstream = new ObjectOutputStream(new FileOutputStream(filename));
	       objstream.writeObject(object);
	       objstream.close();
	    }
	 
	   
	    static Game loadGame(String filename) throws Exception {
	       ObjectInputStream objstream = new ObjectInputStream(new FileInputStream(filename));
	       Game object = (Game) objstream.readObject();
	       objstream.close();
	       return object;
	    }


}
