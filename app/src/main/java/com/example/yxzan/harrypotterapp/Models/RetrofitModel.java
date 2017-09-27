package com.example.yxzan.harrypotterapp.Models;

import com.google.gson.JsonElement;

/**
 * Created by yxzan on 9/25/2016.
 */
public class RetrofitModel {

    boolean success;
    String source;
    JsonElement quotes;


    public RetrofitModel(boolean success, String source,JsonElement quotes) {
        this.success = success;
        this.source = source;
        this.quotes = quotes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public JsonElement getQuotes() {
        return quotes;
    }

    public void setQuotes(JsonElement quotes) {
        this.quotes = quotes;
    }
}

