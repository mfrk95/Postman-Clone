package com.up.swing.tabs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HeaderTab extends JPanel{
    private JTable headerTable;
    private DefaultTableModel headerTableModel;
    private JButton addHeaderButton;
    private JButton removeHeaderButton;
    private JPanel buttonsPanel;
    private JScrollPane tableScrollPane;

    public HeaderTab(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints tableScrollConstraints = new GridBagConstraints();
        GridBagConstraints buttonsPanelConstraints = new GridBagConstraints();
        tableScrollConstraints.gridx = 0;
        tableScrollConstraints.gridy = 0;
        tableScrollConstraints.weightx = 1;
        tableScrollConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanelConstraints.gridx=0;
        buttonsPanelConstraints.gridy=3;
        buttonsPanelConstraints.weightx=1;
        buttonsPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        headerTableModel = new DefaultTableModel();
        headerTableModel.addColumn("Key");
        headerTableModel.addColumn("Value");
        headerTableModel.addRow(new Object[]{"Accept", "*"});
        headerTable = new JTable(headerTableModel);
        addHeaderButton = new JButton("Add header");
        removeHeaderButton = new JButton("Remove header");
        buttonsPanel = new JPanel();
        tableScrollPane = new JScrollPane();
        tableScrollPane.setPreferredSize(new Dimension(500,150));
        buttonsPanel.add(addHeaderButton, BorderLayout.WEST);
        buttonsPanel.add(removeHeaderButton, BorderLayout.EAST);
        tableScrollPane.add(headerTable);
        this.add(tableScrollPane, tableScrollConstraints);
        this.add(buttonsPanel, buttonsPanelConstraints);
    }

    public JTable getHeaderTable(){
        return headerTable;
    }

    public DefaultTableModel getHeaderTableModel(){
        return headerTableModel;
    }
}
