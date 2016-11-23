package com.paulograbin.domain;

import org.junit.Test;

import static org.junit.Assert.*;


public abstract class EntityTest<T extends Entity> {

    private T entity;


    protected abstract T makeOne();

    @Test
    public void newEntityHasNullId() {
        entity = makeOne();

        assertEquals(null, entity.getId());
    }

    @Test
    public void afterIdIsSet_entityMustHaveId() {
        entity = makeOne();
        entity.setId(5);

        assertEquals(new Integer(5), entity.getId());
    }

    @Test
    public void idCannotBeNegative() {
        entity = makeOne();
        entity.setId(-5);

        assertEquals(null, entity.getId());
    }
}
