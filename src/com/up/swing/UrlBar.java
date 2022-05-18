package com.up.swing;

import com.up.domain.Url;
import com.up.service.UrlService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class UrlBar extends JPanel {
    private UrlService urlService;
    private JComboBox comboBox;
    private JTextField urlTextField;
    private JButton sendButton;
    private JLabel responseLabel;
    private JButton favoritesButton;
    private FavoritesDialog favoritesDialog;

    public UrlBar(){
        String[] methods = {"GET","PUT","POST","DELETE"};
        urlService = new UrlService();
        comboBox = new JComboBox(methods);
        urlTextField = new JTextField(30);
        sendButton = new JButton("Send");
        favoritesDialog = new FavoritesDialog();
        favoritesButton = new JButton("Favorites");
        responseLabel = new JLabel();
        responseLabel.setVisible(false);
        this.setBorder(new EmptyBorder(10,10,20,10));
        this.add(comboBox);
        this.add(urlTextField);
        this.add(sendButton);
        this.add(favoritesButton);
        this.add(responseLabel);

    }

    public JTextField getUrlTextField(){
        return urlTextField;
    }
    public JComboBox getComboBox(){
        return comboBox;
    }
    public JButton getSendButton(){
        return sendButton;
    }
    public JLabel getResponseLabel(){
        return responseLabel;
    }
    public JButton getFavoritesButton(){return favoritesButton; }
    public FavoritesDialog getFavoritesDialog(){ return favoritesDialog; }

}
