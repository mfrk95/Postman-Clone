package com.up.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HttpService {
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    public List<String> executeRequest(String method, String url, String requestBody, List<Object> headers) throws IOException, ParseException {
        List<String> serverResponse = new ArrayList<>();
        Iterator it = headers.iterator();
        switch (method) {

            case "GET":
                HttpGet getRequest = new HttpGet(url);
                if (!headers.isEmpty()) {
                    while (it.hasNext()) {
                        setHeaders(it.next(),getRequest);
                    }
                }
                CloseableHttpResponse getResponse = httpClient.execute(getRequest);
                serverResponse.add((getResponse.getCode() + " " + getResponse.getReasonPhrase()));
                serverResponse.add(getResponseColor(getResponse.getCode()));
                HttpEntity entity = getResponse.getEntity();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String response = EntityUtils.toString(entity, "UTF-8");
                try {
                    JsonElement je = JsonParser.parseString(response);
                    String responseJson = gson.toJson(je);
                    serverResponse.add(responseJson);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    serverResponse.add(response);
                }
                //httpClient.close();
                break;


            case "POST":
                HttpPost postRequest = new HttpPost(url);
                if (!headers.isEmpty()) {
                    while (it.hasNext()) {
                        setHeaders(it.next(),postRequest);
                    }
                }
                StringEntity requestBodyEntity = new StringEntity(requestBody);
                postRequest.setEntity(requestBodyEntity);
                CloseableHttpResponse postResponse = httpClient.execute(postRequest);
                serverResponse.add(postResponse.getCode() + " " + postResponse.getReasonPhrase());
                serverResponse.add(getResponseColor(postResponse.getCode()));
                HttpEntity entity2 = postResponse.getEntity();
                Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
                String response2 = EntityUtils.toString(entity2, "UTF-8");
                try {
                    JsonElement je = JsonParser.parseString(response2);
                    String responseJson = gson2.toJson(je);
                    serverResponse.add(responseJson);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    serverResponse.add(response2);
                }
                httpClient.close();
                break;


            case "PUT":
                HttpPut putRequest = new HttpPut(url);
                if (!headers.isEmpty()) {
                    while (it.hasNext()) {
                        setHeaders(it.next(),putRequest);
                    }
                }
                StringEntity requestBodyEntity2 = new StringEntity(requestBody);
                putRequest.setEntity(requestBodyEntity2);
                CloseableHttpResponse putResponse = httpClient.execute(putRequest);
                serverResponse.add((putResponse.getCode() + " " + putResponse.getReasonPhrase()));
                serverResponse.add(getResponseColor(putResponse.getCode()));
                HttpEntity entity3 = putResponse.getEntity();
                Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
                String response3 = EntityUtils.toString(entity3, "UTF-8");
                try {
                    JsonElement je = JsonParser.parseString(response3);
                    String responseJson = gson3.toJson(je);
                    serverResponse.add(responseJson);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    serverResponse.add(response3);
                }
                httpClient.close();


                break;


            case "DELETE":
                System.out.println("Es un DELETE. La URL: " + url);
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
        System.out.println("Key: " + key + " Value: " + value);
        request.addHeader(key, value);
    }

}
