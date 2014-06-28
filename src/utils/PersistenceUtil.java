package utils;

import model.Game;

import java.io.*;

public class PersistenceUtil {
    public static void saveGame(Serializable object, String filename) throws IOException {
        ObjectOutputStream objstream = new ObjectOutputStream(new FileOutputStream(filename));
        objstream.writeObject(object);
        objstream.close();
    }

    public static Game loadGame(String filename) throws Exception {
        ObjectInputStream objstream = new ObjectInputStream(new FileInputStream(filename));
        Game object = (Game) objstream.readObject();
        objstream.close();
        return object;
    }
}
