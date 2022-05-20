package com.up.swing;

import com.google.gson.JsonSyntaxException;
import com.up.domain.Url;
import com.up.service.HttpService;
import com.up.service.UrlService;
import com.up.swing.tabs.MainTab;
import org.apache.hc.core5.http.ParseException;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class App extends JFrame  {

    private final HttpService httpService = new HttpService();
    private final UrlService urlService = new UrlService();
    private UrlBar urlBar;
    private MainTab mainTab;

    public App() {
        this.setTitle("Postman");
        urlBar = new UrlBar();
        mainTab = new MainTab();
        JFrame frame = this;


        // Action listener for Send button
        urlBar.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector vector = mainTab.getHeaderTab().getHeaderTableModel().getDataVector();
                Iterator it = vector.iterator();
                List<Object> headers = new ArrayList<>();
                while (it.hasNext()) {
                        headers.add(it.next());
                }
                try {
                    List<String> response = httpService.executeRequest(
                            urlBar.getComboBox().getSelectedItem().toString(),
                            urlBar.getUrlTextField().getText(),
                            mainTab.getBodyTab().getBodyText().getText(), headers);

                    urlBar.getResponseLabel().setText(response.get(0));
                    mainTab.getResponseTab().getResponseText().setText(response.get(2));
                    if (response.get(1) == "GREEN") {
                        urlBar.getResponseLabel().setForeground(Color.GREEN);
                    } else {
                        urlBar.getResponseLabel().setForeground(Color.RED);
                    }
                    urlBar.getResponseLabel().setVisible(true);
                } catch(IOException ex){
                    if(ex.toString().contains("Target host is not specified") || ex.toString().contains("Unroutable protocol scheme")){
                        JOptionPane.showMessageDialog(frame,"Please include protocol in the URL (http/https)","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(ex.toString().contains("UnknownHostException")){
                        JOptionPane.showMessageDialog(frame,"The URL provided does not match any existing host");
                    }
                } catch (JsonSyntaxException ex){
                    JOptionPane.showMessageDialog(frame,"Error parsing response to JSON","Error", JOptionPane.ERROR_MESSAGE);
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(frame,"The URL is not correctly formed","Error", JOptionPane.ERROR_MESSAGE);
                }
                }
            }
        );

        // Action Listener for Favorites button
        urlBar.getFavoritesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urlBar.getFavoritesDialog().getFavoritesTableModel().setRowCount(0);
                List<Url> urlList = urlService.getAllUrls();
                for(int i=0 ; i< urlList.size() ; i++){
                    Object[] list = {"",""};
                    list[0] = urlList.get(i).getId();
                    list[1] = urlList.get(i).getUrl();
                    urlBar.getFavoritesDialog().getFavoritesTableModel().addRow(list);

                }
                urlBar.getFavoritesDialog().setVisible(true);
            }
        });

        // Action Listener for Use favorite button
        urlBar.getFavoritesDialog().getUseFavorite().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = urlBar.getFavoritesDialog().getFavoritesTable().getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(urlBar.getFavoritesDialog(),"Please select an URL from the table");
                }else{
                    Object selectedUrl = urlBar.getFavoritesDialog().getFavoritesTableModel().getValueAt(selectedRow,1);
                    urlBar.getUrlTextField().setText(selectedUrl.toString());
                }
            }
        });

        // Action Listener for Add favorite button
        urlBar.getFavoritesDialog().getAddFavorites().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Url url = new Url();
                url.setUrl(urlBar.getFavoritesDialog().getAddUrlFavorite().getText());
                urlService.saveUrl(url);
                JOptionPane.showMessageDialog(urlBar.getFavoritesDialog(),"Url saved successfully");
                urlBar.getFavoritesButton().doClick();

            }
        });

        // Action Listener for Edit favorite button
        urlBar.getFavoritesDialog().getEditFavorite().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Url url = new Url();
                int selectedRow = urlBar.getFavoritesDialog().getFavoritesTable().getSelectedRow();
                url.setId((int)urlBar.getFavoritesDialog().getFavoritesTable().getValueAt(selectedRow,0));
                url.setUrl(urlBar.getFavoritesDialog().getEditUrlFavorite().getText());
                urlService.updateUrl(url);
                JOptionPane.showMessageDialog(urlBar.getFavoritesDialog(),"Url updated successfully");
                urlBar.getFavoritesButton().doClick();

            }
        });

        // Action listener for Remove favorite button
        urlBar.getFavoritesDialog().getRemoveFavorite().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Url url = new Url();
                int selectedRow = urlBar.getFavoritesDialog().getFavoritesTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(urlBar.getFavoritesDialog(), "Please select an URL from the table");
                } else {
                    url.setId((int) urlBar.getFavoritesDialog().getFavoritesTable().getValueAt(selectedRow, 0));
                    url.setUrl((urlBar.getFavoritesDialog().getFavoritesTable().getValueAt(selectedRow, 0)).toString());
                    urlService.deleteUrl(url);
                    JOptionPane.showMessageDialog(urlBar.getFavoritesDialog(), "Url deleted successfully");
                    urlBar.getFavoritesButton().doClick();
                }
            }

        });
        // Action listener for Add headers button
        mainTab.getHeaderTab().getAddHeaderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainTab.getHeaderTab().getHeaderTableModel().addRow(new Object[]{"", ""});
            }
        });

        // Action listener for Remove headers button
        mainTab.getHeaderTab().getRemoveHeaderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ( mainTab.getHeaderTab().getHeaderTableModel().getRowCount() > 1) {
                    mainTab.getHeaderTab().getHeaderTableModel().removeRow(1);
                }
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(urlBar, BorderLayout.NORTH);
        this.add(mainTab, BorderLayout.CENTER);
        this.setVisible(true);
        this.pack();
    }
}