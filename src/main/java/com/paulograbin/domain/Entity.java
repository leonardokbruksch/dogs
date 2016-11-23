package com.paulograbin.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected LocalDateTime lastChangedDate;
    protected final LocalDateTime creationDate;


    protected Entity(Integer id) {
        this.id = id;
        this.creationDate = new DateTimeFactory().getCurrentUTCTime();
        this.lastChangedDate = null;
    }

    protected Integer getId() {
        return id;
    }

    protected void setId(Integer value) {
        if(value >= 0)
            id = value;
    }
}
