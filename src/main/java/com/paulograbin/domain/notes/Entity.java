package com.paulograbin.domain.notes;


public class Entity {

    public static int INITIAL_ID = -1;
    protected int id;


    public Entity() {
        id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        if(value > 0)
            id = value;
    }
}
