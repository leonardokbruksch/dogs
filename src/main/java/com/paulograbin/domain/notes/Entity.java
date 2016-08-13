package com.paulograbin.domain.notes;


public abstract class Entity {

    Integer id;


    Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        if(value >= 0)
            id = value;
    }
}
