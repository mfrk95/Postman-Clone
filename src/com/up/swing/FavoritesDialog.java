package com.up.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FavoritesDialog extends JDialog {
    private JTable favoritesTable;
    private JScrollPane tableScrollPane;
    private JPanel buttonsPanel;
    private DefaultTableModel favoritesTableModel;
    private JButton useFavorite;
    private JButton addFavorites;
    private JTextField addUrlFavorite;
    private JButton editFavorite;
    private JTextField editUrlFavorite;
    private JButton removeFavorite;


    public FavoritesDialog(){
        this.setLayout(new BorderLayout());
        buttonsPanel = new JPanel(new GridBagLayout());

        useFavorite = new JButton("Use favorite");
        useFavorite.setPreferredSize(new Dimension(150,30));
        //gbc for useFavorite
        GridBagConstraints useFavoriteConstraints = new GridBagConstraints();
        useFavoriteConstraints.insets = new Insets(10,0,10,0);
        useFavoriteConstraints.gridx=0;
        useFavoriteConstraints.gridy=0;
        useFavoriteConstraints.weightx=1;

        addFavorites = new JButton("Add favorite");
        addFavorites.setPreferredSize(new Dimension(150,30));

        //gbc for addFavorites
        GridBagConstraints addFavoriteConstraints = new GridBagConstraints();
        addFavoriteConstraints.insets = new Insets(0,0,10,0);
        addFavoriteConstraints.gridx=0;
        addFavoriteConstraints.gridy=1;
        addFavoriteConstraints.weightx=1;

        addUrlFavorite = new JTextField();
        addUrlFavorite.setPreferredSize(new Dimension(300,20));

        //gbc for addUrlFavorite
        GridBagConstraints addUrlFavoriteConstraints = new GridBagConstraints();
        addUrlFavoriteConstraints.insets = new Insets(0,0,10,0);
        addUrlFavoriteConstraints.gridx=0;
        addUrlFavoriteConstraints.gridy=2;
        addUrlFavoriteConstraints.weightx=1;


        editFavorite = new JButton("Edit favorite");
        editFavorite.setPreferredSize(new Dimension(150,30));

        //gbc for editFavorite
        GridBagConstraints editFavoriteConstraints = new GridBagConstraints();
        editFavoriteConstraints.insets = new Insets(0,0,10,0);
        editFavoriteConstraints.gridx=0;
        editFavoriteConstraints.gridy=3;
        editFavoriteConstraints.weightx=1;

        editUrlFavorite = new JTextField();
        editUrlFavorite.setPreferredSize(new Dimension(300,20));

        //gbc for editUrlFavorite
        GridBagConstraints editUrlFavoriteConstraints = new GridBagConstraints();
        editUrlFavoriteConstraints.insets = new Insets(0,0,10,0);
        editUrlFavoriteConstraints.gridx=0;
        editUrlFavoriteConstraints.gridy=4;
        editUrlFavoriteConstraints.weightx=1;



        removeFavorite = new JButton("Remove favorite");
        removeFavorite.setPreferredSize(new Dimension(150,30));

        //gbc for removeFavorite
        GridBagConstraints removeFavoriteConstraints = new GridBagConstraints();
        removeFavoriteConstraints.insets = new Insets(0,0,10,0);
        removeFavoriteConstraints.gridx=0;
        removeFavoriteConstraints.gridy=5;
        removeFavoriteConstraints.weightx=1;

        favoritesTableModel = new DefaultTableModel();
        favoritesTableModel.addColumn("ID");
        favoritesTableModel.addColumn("URL");
        favoritesTableModel.addRow(new Object[]{"",""});
        favoritesTable = new JTable(favoritesTableModel);
        tableScrollPane = new JScrollPane();
        tableScrollPane.setPreferredSize(new Dimension(200,100));
        tableScrollPane.setViewportView(favoritesTable);

        buttonsPanel.add(useFavorite,useFavoriteConstraints);
        buttonsPanel.add(addFavorites,addFavoriteConstraints);
        buttonsPanel.add(addUrlFavorite,addUrlFavoriteConstraints);
        buttonsPanel.add(editFavorite,editFavoriteConstraints);
        buttonsPanel.add(editUrlFavorite,editUrlFavoriteConstraints);
        buttonsPanel.add(removeFavorite,removeFavoriteConstraints);

        this.add(tableScrollPane,BorderLayout.NORTH);
        this.add(buttonsPanel,BorderLayout.SOUTH);
        this.setTitle("Favorites");
        this.setBounds(100,50,600,370);
        this.setVisible(false);
    }

    public DefaultTableModel getFavoritesTableModel(){ return favoritesTableModel; }

    public JButton getUseFavorite() {
        return useFavorite;
    }

    public JButton getAddFavorites() {
        return addFavorites;
    }

    public JTextField getAddUrlFavorite() {
        return addUrlFavorite;
    }

    public JButton getEditFavorite() {
        return editFavorite;
    }

    public JTextField getEditUrlFavorite() {
        return editUrlFavorite;
    }


    public JButton getRemoveFavorite() {
        return removeFavorite;
    }

    public JTable getFavoritesTable(){
        return favoritesTable;
    }
}
