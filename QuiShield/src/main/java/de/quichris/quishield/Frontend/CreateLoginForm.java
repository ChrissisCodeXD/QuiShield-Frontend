package de.quichris.quishield.Frontend;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.Exception;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;



class CreateLoginForm extends JFrame implements ActionListener
{

    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel, fieldError;
    final JTextField  textField1, textField2;


    CreateLoginForm()
    {


        userLabel = new JLabel();
        userLabel.setText("Username");

        fieldError = new JLabel();
        textField1 = new JTextField(15);


        passLabel = new JLabel();
        passLabel.setText("Password");


        textField2 = new JPasswordField(15);


        JToggleButton tb = new JToggleButton("SUBMIT");
        tb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = textField1.getText();
                String passValue = textField2.getText();
                String password =   Hashing.sha256()
                                    .hashString(passValue, StandardCharsets.UTF_8)
                                    .toString();


                if (userValue.equals("test1@gmail.com") && passValue.equals("test")) {


                    NewPage page = new NewPage();


                    page.setVisible(true);


                    JLabel wel_label = new JLabel("Welcome: "+userValue);
                    page.getContentPane().add(wel_label);
                }
                else{
                    fieldError.setText("<html><font color='red'>Please enter valid<br>username and password</font></html>");
                    System.out.println("Please enter valid username and password");
                }
            }
        });




        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(tb);
        newPanel.add(fieldError);


        add(newPanel, BorderLayout.CENTER);


        setTitle("LOGIN FORM");
    }


    public void actionPerformed(ActionEvent ae) {

    }
}
