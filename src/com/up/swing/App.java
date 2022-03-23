package com.up.swing;


import com.up.service.HttpService;
import org.apache.hc.core5.http.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class App {
    private final HttpService httpService = new HttpService();

    public App() {
        JFrame jf = new JFrame("Postman");
        JComboBox methodComboBox = new JComboBox();
        methodComboBox.addItem("GET");
        methodComboBox.addItem("PUT");
        methodComboBox.addItem("POST");
        methodComboBox.addItem("DELETE");
        JTextField urlTextField = new JTextField(30);
        JTextArea responseTextArea = new JTextArea();
        JTextArea bodyTextArea = new JTextArea();
        JButton sendButton = new JButton("Send");
        JLabel statusCodeLabel = new JLabel("Status Code: ");
        JLabel responseStatusLabel = new JLabel();
        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel headerButtonsPanel = new JPanel(new BorderLayout());
        JButton addHeaderButton = new JButton("Add Header");
        JButton removeHeaderButton = new JButton("Remove Header");
        DefaultTableModel headerTableModel = new DefaultTableModel();
        headerTableModel.addColumn("Key");
        headerTableModel.addColumn("Value");
        headerTableModel.addRow(new Object[]{"", ""});
        JTable headerTable = new JTable(headerTableModel);
        JTabbedPane tabsPane = new JTabbedPane();
        JButton clearButton = new JButton("Clear all");
        JPanel mainPanel = new JPanel(new GridBagLayout());


        GridBagConstraints comboBoxConstraints = new GridBagConstraints();
        comboBoxConstraints.insets = new Insets(5, 5, 5, 5);
        comboBoxConstraints.gridx = 0;
        comboBoxConstraints.gridy = 0;
        comboBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(methodComboBox, comboBoxConstraints);

        GridBagConstraints urlTextFieldConstraints = new GridBagConstraints();
        urlTextFieldConstraints.insets = new Insets(0, 5, 0, 0);
        urlTextFieldConstraints.gridx = 1;
        urlTextFieldConstraints.gridy = 0;
        urlTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(urlTextField, urlTextFieldConstraints);

        GridBagConstraints sendButtonContraints = new GridBagConstraints();
        sendButtonContraints.insets = new Insets(0, 5, 0, 5);
        sendButtonContraints.gridx = 2;
        sendButtonContraints.gridy = 0;
        sendButtonContraints.fill = GridBagConstraints.HORIZONTAL;
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String method = methodComboBox.getSelectedItem().toString();
                String url = urlTextField.getText();
                String requestBody = bodyTextArea.getText();
                Vector vector = headerTableModel.getDataVector();
                Iterator it = vector.iterator();
                List<Object> headers = new ArrayList<>();
                while (it.hasNext()) {
                    headers.add(it.next());
                }
                List<String> res;
                try {
                    res = httpService.executeRequest(method, url, requestBody, headers);
                    if (res.get(1) == "GREEN") {
                        responseStatusLabel.setForeground(Color.GREEN);
                    } else {
                        responseStatusLabel.setForeground(Color.RED);
                    }
                    responseStatusLabel.setText(res.get(0));
                    if (res.get(2) != null) {
                        responseTextArea.setText(res.get(2));
                    }
                } catch (IOException | ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        mainPanel.add(sendButton, sendButtonContraints);

        GridBagConstraints statusCodeLabelConstraints = new GridBagConstraints();
        statusCodeLabelConstraints.insets = new Insets(10, 10, 10, 0);
        statusCodeLabelConstraints.gridx = 0;
        statusCodeLabelConstraints.gridy = 1;
        mainPanel.add(statusCodeLabel, statusCodeLabelConstraints);

        GridBagConstraints responseStatusLabelConstraints = new GridBagConstraints();
        responseStatusLabelConstraints.insets = new Insets(10, 0, 10, 0);
        responseStatusLabelConstraints.gridx = 1;
        responseStatusLabelConstraints.gridy = 1;
        mainPanel.add(responseStatusLabel, responseStatusLabelConstraints);

        GridBagConstraints tabsPaneConstraints = new GridBagConstraints();
        tabsPaneConstraints.insets = new Insets(10, 5, 10, 5);
        tabsPaneConstraints.gridx = 0;
        tabsPaneConstraints.gridy = 2;
        tabsPaneConstraints.gridwidth = 3;
        tabsPaneConstraints.fill = GridBagConstraints.HORIZONTAL;
        responseTextArea.setLineWrap(true);
        responseTextArea.setWrapStyleWord(true);
        responseTextArea.setMargin(new Insets(2, 5, 2, 2));
        bodyTextArea.setLineWrap(true);
        bodyTextArea.setWrapStyleWord(true);
        bodyTextArea.setMargin(new Insets(2, 5, 2, 2));
        JScrollPane responseScrollPane = new JScrollPane(responseTextArea);
        JScrollPane bodyScrollPane = new JScrollPane(bodyTextArea);
        JScrollPane tableScrollPane = new JScrollPane(headerTable);
        tableScrollPane.setPreferredSize(new Dimension(200, 150));
        addHeaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                headerTableModel.addRow(new Object[]{"", ""});
            }
        });
        removeHeaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (headerTableModel.getRowCount() > 1) {
                    headerTableModel.removeRow(1);
                }
            }
        });
        headerButtonsPanel.add(addHeaderButton, BorderLayout.WEST);
        headerButtonsPanel.add(removeHeaderButton, BorderLayout.EAST);
        headerPanel.add(tableScrollPane, BorderLayout.NORTH);
        headerPanel.add(headerButtonsPanel, BorderLayout.SOUTH);


        tabsPane.setPreferredSize(new Dimension(200, 200));
        tabsPane.add("Response", responseScrollPane);
        tabsPane.add("Body", bodyScrollPane);
        tabsPane.add("Headers", headerPanel);
        mainPanel.add(tabsPane, tabsPaneConstraints);


        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                urlTextField.setText("");
                responseTextArea.setText("");
                bodyTextArea.setText("");
                responseStatusLabel.setText("");
                responseStatusLabel.setText("");
            }

        });
        GridBagConstraints clearButtonConstraints = new GridBagConstraints();
        clearButtonConstraints.insets = new Insets(10, 5, 10, 5);
        clearButtonConstraints.gridx = 0;
        clearButtonConstraints.gridy = 3;
        mainPanel.add(clearButton, clearButtonConstraints);


        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(mainPanel);
        jf.setVisible(true);
        jf.pack();
    }
}
