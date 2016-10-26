package com.paulograbin.web.controllers;

import com.paulograbin.web.crypto.EtagGenerator;


public class ResponseMetadata {

    private int code;
    private String status;
    private String etag;
    private ResponseData data;

    private transient EtagGenerator tagGenerator;

    public ResponseMetadata(EtagGenerator generator, ResponseData data) {
        this.data = data;
        this.tagGenerator = generator;
        this.code = 200;
        this.etag = tagGenerator.calculateEtag(data.getDataAsString());
    }

}
