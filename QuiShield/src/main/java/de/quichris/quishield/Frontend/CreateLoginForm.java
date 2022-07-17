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
import java.util.HashMap;
import java.util.Map;

import com.google.common.hash.Hashing;
import com.squareup.okhttp.Response;
import de.quichris.quishield.Frontend.network.HttpClientTest;


class CreateLoginForm extends JFrame  implements ActionListener
{

    HttpClientTest http = new HttpClientTest();

    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel, fieldError;
    final JTextField  textField1, textField2;


    CreateLoginForm() throws Exception
    {


        userLabel = new JLabel();
        userLabel.setText("Username");

        fieldError = new JLabel();
        textField1 = new JTextField(15);


        passLabel = new JLabel();
        passLabel.setText("Password");


        textField2 = new JPasswordField(15);


        JToggleButton tb = new JToggleButton("SUBMIT");
        tb.addActionListener(new ActionListener()  {

            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = textField1.getText();
                String passValue = textField2.getText();
                String password =   Hashing.sha256()
                                    .hashString(passValue, StandardCharsets.UTF_8)
                                    .toString();


                Map<String,String> urlParameters = new HashMap<String, String>();
                urlParameters.put("email", userValue);
                urlParameters.put("password", password);
                try {
                    Response res = http.post("v1/login/",urlParameters);
                    if (res.code() == 200) {
                        setVisible(false);
                        dispose();

                        NewPage page = new NewPage();


                        page.setVisible(true);


                        JLabel wel_label = new JLabel("Welcome: "+userValue);
                        page.getContentPane().add(wel_label);
                    }
                    else {
                        fieldError.setText("<html><font color='red'>Please enter valid<br>username and password</font></html>");
                    }
                } catch (Exception ex) {}



            }
        }) ;




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
