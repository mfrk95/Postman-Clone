package com.up.service;

import com.google.gson.*;
import com.sun.deploy.net.JARSigningException;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HttpService {
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    public List<String> executeRequest(String method, String url, String requestBody, List<Object> headers) throws IOException, ParseException {
        List<String> serverResponse = new ArrayList<>();
        Iterator it = headers.iterator();
        CloseableHttpResponse httpResponse = null;
        StringEntity requestBodyEntity =  null;

        switch (method) {

            case "GET":
                HttpGet getRequest = new HttpGet(url);
                if (!headers.isEmpty()) {
                    while (it.hasNext()) {
                        setHeaders(it.next(), getRequest);
                    }
                }
                httpResponse = httpClient.execute(getRequest);
                break;

            case "POST":
                HttpPost postRequest = new HttpPost(url);
                if (!headers.isEmpty()) {
                    while (it.hasNext()) {
                        setHeaders(it.next(), postRequest);
                    }
                }
                requestBodyEntity = new StringEntity(requestBody);
                postRequest.setEntity(requestBodyEntity);
                httpResponse = httpClient.execute(postRequest);
                break;


            case "PUT":
                HttpPut putRequest = new HttpPut(url);
                if (!headers.isEmpty()) {
                    while (it.hasNext()) {
                        setHeaders(it.next(), putRequest);
                    }
                }

                requestBodyEntity = new StringEntity(requestBody);
                putRequest.setEntity(requestBodyEntity);
                httpResponse = httpClient.execute(putRequest);
                break;


            case "DELETE":
                HttpDelete deleteRequest = new HttpDelete(url);
                if (!headers.isEmpty()) {
                    while (it.hasNext()) {
                        setHeaders(it.next(), deleteRequest);
                    }
                }
                requestBodyEntity = new StringEntity(requestBody);
                deleteRequest.setEntity(requestBodyEntity);
                httpResponse = httpClient.execute(deleteRequest);
                break;


        }


        serverResponse.add((httpResponse.getCode() + " " + httpResponse.getReasonPhrase()));
        serverResponse.add(getResponseColor(httpResponse.getCode()));
        HttpEntity entity = httpResponse.getEntity();
        String responseType = entity.getContentType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String response = EntityUtils.toString(entity, "UTF-8");

        if(responseType.contains("application/json")){
            JsonElement je = JsonParser.parseString(response);
            String responseJson = gson.toJson(je);
            serverResponse.add(responseJson);
        }else{
            serverResponse.add(response);
        }


        return serverResponse;

    }

    private String getResponseColor(int code) {
        String color = "";
        if (code >= 200 && code <= 230) {
            color = "GREEN";
        } else if (code >= 400 && code <= 530) {
            color = "RED";
        }
        return color;
    }

    private void setHeaders(Object header, HttpUriRequestBase request){
        String rawString = header.toString();
        StringBuilder sb = new StringBuilder(rawString);
        sb.deleteCharAt(0);
        sb.deleteCharAt(rawString.length() - 2);
        String headerString = sb.toString();
        String[] keyvalue = headerString.split(",");
        String key = keyvalue[0];
        String value = keyvalue[1];
        request.addHeader(key, value);
    }

}
