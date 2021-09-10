import java.io.*;

public class Serial {
    public static void main(String[] args) {
        Show show = new Show("flapjack", "animated", "light", "already started", 55);

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("SaveFile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(show);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in SaveFile.ser\n");
        } catch (IOException i) {
            i.printStackTrace();
        }


        Show n = null;
        try {
            FileInputStream fileIn = new FileInputStream("SaveFile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            n = (Show) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println(n);
    }
}