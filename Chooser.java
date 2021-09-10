import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Chooser implements ActionListener {

    private static JFrame frame;
    private static JPanel p1;
    private static JPanel p2;
    private static JPanel p3;
    private static JButton b1;
    private static JButton b2;
    private static JButton b3;
    private static JButton b4;
    private static JButton b5;
    private static JLabel l1;
    private static JLabel l2;
    private static JLabel l3;
    private static JLabel l4;
    private static JTextArea l5;
    private static JTextField t1;
    private static JTextField t2;
    private static int count;
    private static String name;
    private static String type;
    private static String levity;
    private static String progression;
    private static int episodes;
    private static LinkedList<Show> list;
    private static String print;
    private static Scanner file;
    private static Show n;

    public static void main(String[] args) throws IOException{

        File f = new File("SaveFile.ser");
        file = new Scanner("list.ser");
        count = 0;
        type = null;
        levity = null;
        progression = null;
        list = new LinkedList<Show>();
        print = "";
        boolean t = true;


        try {
            FileInputStream fileIn = new FileInputStream("list.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (LinkedList<Show>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
        System.out.println(list);






    //creating Jframe
        frame = new JFrame("Virtual Watchlist");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    //p1 startup screen
        p1 = new JPanel();
        p1.setLayout(null);
        frame.add(p1);

    //l1 intro label
        l1 = new JLabel("Welcome to your Watchlist");
        l1.setBounds(40, 10, 260, 30);
        p1.add(l1);

    //p2 for choosing a show
        p2 = new JPanel();
        p2.setLayout(null);

    //l2 show characteristic label
        l2 = new JLabel("Enter the characteristics of the show");
        l2.setBounds(40, 10, 460, 30);
        p2.add(l2);

    //l3 p2 dynamic label
        l3 = new JLabel("Animated or Live-Action?");
        l3.setBounds(40, 30, 460, 30);
        p2.add(l3);

        t1 = new JTextField();
        t1.setBounds(40, 50, 130, 25);
        p2.add(t1);

    //p3 adding show screen
        p3 = new JPanel();
        p3.setLayout(null);
        p3.add(l2);

    //l4 p3 dynamic label
        l4 = new JLabel("Enter the name of the show.");
        l4.setBounds(40, 30, 460, 30);
        p3.add(l4);

        t2 = new JTextField();
        t2.setBounds(40, 70, 130, 25);
        p3.add(t2);

        l5 = new JTextArea();
        l5.setBounds(40, 30, 200, 200);
        l5.setEditable(false);

    //creating b1
        b1 = new JButton(new AbstractAction("Choose a show") {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setVisible(false);
                frame.add(p2);
                p2.setVisible(true);
            }
        });
        b1.setBounds(30, 50, 200, 30);
        p1.add(b1);
    //b1.addKeyListener(new Chooser());


    //creating b2
        b2 = new JButton(new AbstractAction("Add a show") {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setVisible(false);
                frame.add(p3);
                p3.setVisible(true);
            }
        });
        b2.setBounds(30, 100, 200, 30);
        p1.add(b2);

    //b5 creation
        b5 = new JButton(new AbstractAction("back to home") {
            @Override
            public void actionPerformed(ActionEvent e) {
                p3.setVisible(false);
                p2.setVisible(false);
                p1.setVisible(true);
                l2.setText("Enter the characteristics of the show");
                l4.setText("Enter the name of the show");
                l3.setText("Animated or Live-Action");
                t2.setVisible(true);
                l4.setVisible(true);
                b4.setVisible(true);
                b5.setVisible(false);
                b3.setVisible(true);
                t1.setVisible(true);
                l2.setVisible(true);
                l5.setVisible(false);
                l3.setVisible(true);
                t1.setText("");
                t2.setText("");
                print = "";
                l3.setBounds(40, 30, 460, 30);
            }
        });
        b5.setBounds(250, 70, 180, 25);

    //creating b3
        b3 = new JButton(new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                l3.setText("Animated or Live-Action?");
                if (count == 1 ){
                    type = t1.getText();
                    t1.setText("");
                    l3.setText("Serious or Light?");
                }
                if (count == 2){
                    levity = t1.getText();
                    t1.setText("");
                    l3.setText("New or Already started?");
                }
                if (count == 3){
                    progression = t1.getText();
                    t1.setVisible(false);
                    count = 0;
                    for (int i = 0; i < list.size(); i++){
                        if (list.get(i).progression.toLowerCase(Locale.ROOT).equals(progression.toLowerCase(Locale.ROOT))&&
                                list.get(i).type.toLowerCase(Locale.ROOT).equals(type.toLowerCase(Locale.ROOT))&&
                                list.get(i).levity.toLowerCase(Locale.ROOT).equals(levity.toLowerCase(Locale.ROOT))){
                            print+=list.get(i);
                        }
                    }
                    if (print.equals("")){
                        l3.setText("No matches found");
                    }
                    else {
                        l5.setText(print);
                        p2.add(l5);
                        l5.setVisible(true);
                        l3.setVisible(false);
                    }
                    b3.setVisible(false);
                    p2.add(b5);
                    b5.setVisible(true);
                }
            }
        });
        b3.setBounds(175, 50, 60, 25);
        p2.add(b3);

    //b4 creation
        b4 = new JButton(new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                l4.setText("Enter the name of the show");
                if (count == 1 ){
                    name = t2.getText();
                    t2.setText("");
                    l4.setText("Is the show Animated or Live-Action");
                }
                if (count == 2){
                    type = t2.getText();
                    t2.setText("");
                    l4.setText("Is the show Serious or Light?");
                }
                if (count == 3){
                    levity = t2.getText();
                    t2.setText("");
                    l4.setText("Is the show New or Already started?");
                }
                if (count == 4){
                    progression = t2.getText();
                    t2.setText("");
                    l4.setText("How many episodes does the show have?");
                }
                if (count == 5){
                    episodes = Integer.parseInt(t2.getText());
                    t2.setVisible(false);
                    l2.setVisible(false);
                    b4.setVisible(false);
                    list.add(new Show(name, type, levity, progression, episodes));
                    l4.setText("Show has been added!");
                    count = 0;
                    p3.add(b5);
                    b5.setVisible(true);








                }
            }
        });
        b4.setBounds(175, 70, 60, 25);
        p3.add(b4);

        frame.setVisible(true);

//        try {
//            FileOutputStream fileOut =
//                    new FileOutputStream("list.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(list);
//            out.close();
//            fileOut.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void Save(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("list.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}