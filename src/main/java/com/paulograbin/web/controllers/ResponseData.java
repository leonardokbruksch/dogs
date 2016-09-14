package com.paulograbin.web.controllers;

import com.google.gson.Gson;
import com.paulograbin.domain.notes.ResponseWrapper;


public class ResponseData {

    private int offset;
    private int limit;
    private int total;
    private int count;

    private ResponseWrapper result;

    public ResponseData(ResponseWrapper responseWrapper) {
        this.result = responseWrapper;

        this.offset = 0;
        this.limit = 0;
        this.total = 0;
        this.count = responseWrapper.getItemsCount();
    }

    public ResponseWrapper getData() {
        return result;
    }

    public String getDataAsString() {
        return new Gson().toJson(result);
    }
}
