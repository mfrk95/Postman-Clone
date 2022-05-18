package com.up.swing.tabs;

import javax.swing.*;
import java.awt.*;

public class BodyTab extends JPanel {
    private JTextArea bodyText;

    public BodyTab(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx=0;
        scrollPaneConstraints.gridy=0;
        scrollPaneConstraints.weightx=1;
        scrollPaneConstraints.weighty=1;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        bodyText = new JTextArea();
        bodyText.setLineWrap(true);
        bodyText.setWrapStyleWord(true);
        bodyText.setMargin(new Insets(2, 3, 2, 2));
        JScrollPane scrollPane = new JScrollPane(bodyText);
        scrollPane.setPreferredSize(new Dimension(500,150));
        this.add(scrollPane,scrollPaneConstraints);

    }

    public JTextArea getBodyText(){
        return bodyText;
    }

}
