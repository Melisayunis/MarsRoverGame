package com.example.MarsRover.unit.EntityTest;


import com.example.MarsRover.Entity.MartianObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MartianObjectTest {

    public MartianObject martianObject;

    @Before
    public void setUp() {
        martianObject = new MartianObject("COSMIC_RELIC", 0, 0);
    }

    @After
    public void tearDown() {
        martianObject = null;
    }

    @Test
    public void constructorDefaultTest() {
        MartianObject martianObjectDefault = new MartianObject();

        assertNotNull(martianObjectDefault);

        assertNull(martianObjectDefault.getId());
        assertNull(martianObjectDefault.getName());
        assertNull(martianObjectDefault.getPositionX());
        assertNull(martianObjectDefault.getPositionY());
    }

    @Test
    public void getterTest() {
        assertEquals(0 , (int) martianObject.getPositionX());
        assertEquals(0, (int) martianObject.getPositionY());
        assertEquals("COSMIC_RELIC", martianObject.getName());
    }

    @Test
    public void setterTest() {
        martianObject.setPositionX(5);
        martianObject.setPositionY(8);
        martianObject.setName("SPACE_PLANT");

        assertEquals(5, (int) martianObject.getPositionX());
        assertEquals(8, (int) martianObject.getPositionY());
        assertEquals("SPACE_PLANT", martianObject.getName());
    }

}
