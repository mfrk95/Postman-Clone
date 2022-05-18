package com.up.dao;

import com.up.domain.Url;

import java.util.List;

public interface UrlDao {
    public void saveUrl(Url url);
    public List<Url> getAllUrls();
    public void updateUrl(Url url);
    public void deleteUrl(Url url);
}
