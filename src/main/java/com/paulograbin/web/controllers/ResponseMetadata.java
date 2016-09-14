package com.paulograbin.web.controllers;

import com.paulograbin.web.crypto.EtagGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.Transient;


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
