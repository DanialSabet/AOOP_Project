/*
Abboud Afram
Danial Sabet
 */
package util;

import java.io.*;

public class Disc {
    public static void writeToFile(File file, Object o) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(o);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readFromFile(File file) {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
            Object panel = stream.readObject();
            stream.close();
            return panel;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
