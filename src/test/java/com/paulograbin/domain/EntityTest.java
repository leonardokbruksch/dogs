package com.paulograbin.domain;

import com.paulograbin.domain.notes.Entity;
import org.junit.Test;

import static org.junit.Assert.*;


public abstract class EntityTest<T extends Entity> {

    T entity;

    protected abstract T makeOne();

    @Test
    public void newEntityHasNoId() {
        entity = makeOne();

        assertEquals(Entity.INITIAL_ID, entity.getId());
    }

    @Test
    public void afterIdIsSet_entityMustHaveId() {
        entity = makeOne();
        entity.setId(5);

        assertEquals(5, entity.getId());
    }

    @Test
    public void idCannotBeNegative() {
        entity = makeOne();
        entity.setId(-5);

        assertEquals(Entity.INITIAL_ID, entity.getId());
    }
}
