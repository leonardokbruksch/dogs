package com.leonardobruksch.domain;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("The requested entity couldn't be found.");
    }

}