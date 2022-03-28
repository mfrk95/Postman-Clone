package com.up.swing.tabs;

import javax.swing.*;
import java.awt.*;

public class MainTab extends JTabbedPane {
    private ResponseTab responseTab;
    private HeaderTab headerTab;

    public MainTab(){
        responseTab = new ResponseTab();
        headerTab = new HeaderTab();
        this.add("Response", responseTab);
        this.add("Headers", headerTab);

    }

    public ResponseTab getResponseTab(){
        return responseTab;
    }

    public HeaderTab getHeaderTab(){
        return headerTab;
    }
}
