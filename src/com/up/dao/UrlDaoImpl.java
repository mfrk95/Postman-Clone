package com.up.dao;

import com.up.domain.Url;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UrlDaoImpl implements UrlDao {
    private Connection conn;
    static final String DB_URL = "jdbc:mysql://localhost:3306/labo1";
    static final String USER = "root";
    static final String PASS = "123456";

    public UrlDaoImpl()  {
    }

    @Override
    public void saveUrl(Url url) {
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO url VALUES (null,?)");
            preparedStatement.setString(1,url.getUrl());
            preparedStatement.execute();

        }catch(SQLException ex){
            ex.printStackTrace();
        }


    }

    @Override
    public List<Url> getAllUrls() {
        List<Url> urlList = new ArrayList();
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM url");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Url url = new Url();
                url.setId(rs.getInt("url_id"));
                url.setUrl(rs.getString("url_name"));
                urlList.add(url);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            return urlList;
        }
    }

    @Override
    public void updateUrl(Url url) {
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE url SET url_name =? WHERE url_id=? ");
            preparedStatement.setString(1,url.getUrl());
            preparedStatement.setInt(2,url.getId());
            preparedStatement.execute();

        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteUrl(Url url) {
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM url WHERE url_id = ?");
            preparedStatement.setInt(1,url.getId());
            preparedStatement.execute();

        }catch(SQLException ex){
            ex.printStackTrace();
        }



    }
}


