package com.up.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UrlBar extends JPanel {
    private JComboBox comboBox;
    private JTextField urlTextField;
    private JButton sendButton;

    public UrlBar(){
        String[] methods = {"GET","PUT","POST","DELETE"};
        comboBox = new JComboBox(methods);
        urlTextField = new JTextField(30);
        sendButton = new JButton("Send");
        this.setBorder(new EmptyBorder(10,10,20,10));
        this.add(comboBox);
        this.add(urlTextField);
        this.add(sendButton);

    }

    public JTextField getUrlTextField(){
        return urlTextField;
    }

    public JComboBox getComboBox(){
        return comboBox;
    }

}
