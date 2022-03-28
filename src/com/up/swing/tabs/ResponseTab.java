package com.up.swing.tabs;

import javax.swing.*;
import java.awt.*;

public class ResponseTab extends JPanel {
    private JTextArea responseText;

    public ResponseTab(){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.RED);
        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx=0;
        scrollPaneConstraints.gridy=0;
        scrollPaneConstraints.weightx=1;
        scrollPaneConstraints.weighty=1;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        responseText = new JTextArea();
        responseText.setLineWrap(true);
        responseText.setWrapStyleWord(true);
        responseText.setMargin(new Insets(2, 3, 2, 2));
        JScrollPane scrollPane = new JScrollPane(responseText);
        scrollPane.setPreferredSize(new Dimension(500,150));
        this.add(scrollPane,scrollPaneConstraints);

    }

    public JTextArea getResponseText(){
        return responseText;
    }
}
