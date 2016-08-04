package com.paulograbin.domain.notes;

import org.junit.Test;

import static org.junit.Assert.*;


public class EntityTest {

    Entity entity;

    @Test
    public void newEntityHasNoId() {
        entity = new Entity();

        assertEquals(Entity.INITIAL_ID, entity.getId());
    }

    @Test
    public void afterIdIsSet__entityMustHaveId() {
        entity = new Entity();
        entity.setId(5);

        assertEquals(5, entity.getId());
    }

    @Test
    public void idCannotBeNegative() {
        entity = new Entity();
        entity.setId(-5);

        assertEquals(Entity.INITIAL_ID, entity.getId());
    }
}
