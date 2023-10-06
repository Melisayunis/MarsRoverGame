package com.example.MarsRover.unit.ServiceTest.EnumsTest;

import com.example.MarsRover.Service.Enums.MartianObjectNames;
import org.junit.Test;
import static org.junit.Assert.*;

public class MartianObjectNamesTest {

    @Test
    public void martianObjectNamesValuesTest() {
        MartianObjectNames[] values = MartianObjectNames.values();

        assertEquals(10, values.length);

        assertEquals(MartianObjectNames.RED_ROCK, values[0]);
        assertEquals(MartianObjectNames.MARTIAN_CRYSTAL, values[1]);
        assertEquals(MartianObjectNames.ALIEN_FUNGUS, values[2]);
        assertEquals(MartianObjectNames.SOLAR_STONE, values[3]);
        assertEquals(MartianObjectNames.SPACE_PLANT, values[4]);
        assertEquals(MartianObjectNames.COMET_FRAGMENT, values[5]);
        assertEquals(MartianObjectNames.MARTIAN_ROVER_MODULE, values[6]);
        assertEquals(MartianObjectNames.COSMIC_RELIC, values[7]);
        assertEquals(MartianObjectNames.STARSHIP_DEBRIS, values[8]);
        assertEquals(MartianObjectNames.MOON_DUST, values[9]);
    }

    @Test
    public void martianObjectNamesToStringTest() {
        assertEquals("RED_ROCK", MartianObjectNames.RED_ROCK.toString());
        assertEquals("MARTIAN_CRYSTAL", MartianObjectNames.MARTIAN_CRYSTAL.toString());
        assertEquals("ALIEN_FUNGUS", MartianObjectNames.ALIEN_FUNGUS.toString());
        assertEquals("SOLAR_STONE", MartianObjectNames.SOLAR_STONE.toString());
        assertEquals("SPACE_PLANT", MartianObjectNames.SPACE_PLANT.toString());
        assertEquals("COMET_FRAGMENT", MartianObjectNames.COMET_FRAGMENT.toString());
        assertEquals("MARTIAN_ROVER_MODULE", MartianObjectNames.MARTIAN_ROVER_MODULE.toString());
        assertEquals("COSMIC_RELIC", MartianObjectNames.COSMIC_RELIC.toString());
        assertEquals("STARSHIP_DEBRIS", MartianObjectNames.STARSHIP_DEBRIS.toString());
        assertEquals("MOON_DUST", MartianObjectNames.MOON_DUST.toString());
    }

}
