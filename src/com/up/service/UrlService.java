package com.up.service;

import com.up.dao.UrlDao;
import com.up.dao.UrlDaoImpl;
import com.up.domain.Url;

import java.util.List;

public class UrlService {
    private UrlDao urlDao = new UrlDaoImpl();

    public List<Url> getAllUrls(){
        List<Url> urlList = urlDao.getAllUrls();
        return urlList;
    }

    public void saveUrl(Url url){
        urlDao.saveUrl(url);
    }

    public void updateUrl(Url url){
        urlDao.updateUrl(url);
    }

    public void deleteUrl(Url url){
        urlDao.deleteUrl(url);
    }

}


