package com.up.swing.tabs;

import javax.swing.*;

public class MainTab extends JTabbedPane {
    private ResponseTab responseTab;
    private HeaderTab headerTab;
    private BodyTab bodyTab;

    public MainTab(){
        responseTab = new ResponseTab();
        headerTab = new HeaderTab();
        bodyTab = new BodyTab();

        this.add("Response", responseTab);
        this.add("Headers", headerTab);
        this.add("Body", bodyTab);

    }

    public ResponseTab getResponseTab(){
        return responseTab;
    }

    public HeaderTab getHeaderTab(){
        return headerTab;
    }

    public BodyTab getBodyTab(){return bodyTab;}
}
