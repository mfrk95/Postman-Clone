package com.up.dao;

import com.up.domain.Url;
import com.up.jdbc.DBManager;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UrlDaoImpl implements UrlDao {
    public UrlDaoImpl()  {
    }

    @Override
    public void saveUrl(Url url) {
        try{
            Connection conn = DBManager.connect();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO url VALUES (null,?)");
            preparedStatement.setString(1,url.getUrl());
            preparedStatement.execute();

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error saving URL","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<Url> getAllUrls() {
        List<Url> urlList = new ArrayList();
        try{
            Connection conn = DBManager.connect();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM url");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Url url = new Url();
                url.setId(rs.getInt("url_id"));
                url.setUrl(rs.getString("url_name"));
                urlList.add(url);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error getting all URLS","Error",JOptionPane.ERROR_MESSAGE);
        }finally{
            return urlList;
        }
    }

    @Override
    public void updateUrl(Url url) {
        try{
            Connection conn = DBManager.connect();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE url SET url_name =? WHERE url_id=? ");
            preparedStatement.setString(1,url.getUrl());
            preparedStatement.setInt(2,url.getId());
            preparedStatement.execute();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error updating URL","Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void deleteUrl(Url url) {
        try{
            Connection conn = DBManager.connect();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM url WHERE url_id = ?");
            preparedStatement.setInt(1,url.getId());
            preparedStatement.execute();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error deleting URL","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}


