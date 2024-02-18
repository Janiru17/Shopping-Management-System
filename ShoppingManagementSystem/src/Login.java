
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;


public class Login extends JFrame implements ActionListener {

    Container container;
    JLabel label,label1;
    JTextField user;
    JFrame frame;
    JPasswordField pass;
    JButton btn;




    ArrayList<String> arrayList=new ArrayList();
    Login(){
        frame=new JFrame();
        frame.setTitle("LoginForm");

        frame.setSize(600,400);





        frame.setLayout(null);

        label = new JLabel("User Name");
        label1=new JLabel("password");

        label.setBounds(10,50,100,20);
        label1.setBounds(10,100,100,20);

        frame.add(label);
        frame.add(label1);

        user = new JTextField();
        user.setBounds(120,50,120,20);
        frame.add(user);

        pass =new JPasswordField();
        pass.setBounds(120,100,120,20);
        frame.add(pass);

        btn =new JButton("Login");
        btn.setBounds(100,150,70,20);
        frame.add(btn);
        btn.addActionListener(this);
        frame.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Login();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            File myObj = new File("C:\\Users\\Dasun\\IdeaProjects\\registrotion.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                arrayList.add(data);
//                String myLog = user.getText()+"="+pass.getText();
//                if (data == user.getText()+"="+pass.getText() ){
//                    frame.dispose();
//                    GUI gui=new GUI();
//                }
                for (int i =0 ; i< arrayList.size(); i++){
                    String val= Arrays.toString(arrayList.get(i).split(","));
                    String my ="["+user.getText()+"="+pass.getText()+"]";
                    System.out.println(my);


                }

                System.out.println(data);
            }
            myReader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("An error occurred.");

        }
    }
}
