package org.lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WindSpeedEntryTest {

    @Test
    public void testExtreme() {

        WindSpeedEntry extremeHurricane = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(1)
                .withWindSpeed((double) 120)
                .withDate("26.01.2020 18:35:20")
                .withStation("Istanbul")
                .withTemperature((double) 30)
                .withWindDirection("NE")
                .withWeatherCondition("cloudy")
                .build();

        WindSpeedEntry extremeCalm = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(1)
                .withWindSpeed((double) 1)
                .withDate("26.01.2020 18:35:20")
                .withStation("Istanbul")
                .withTemperature((double) 30)
                .withWindDirection("NE")
                .withWeatherCondition("cloudy")
                .build();

        String extremeHurricaneTest = extremeHurricane.getExtreme();
        String extremeCalmTest = extremeCalm.getExtreme();
        String extremeHurricaneTestExp = "hurricane";
        String extremeCalmTestExp = "calm";

        Assertions.assertEquals(extremeHurricaneTestExp,extremeHurricaneTest);
        Assertions.assertEquals(extremeCalmTestExp,extremeCalmTest);
    }

    @Test
    public void testBeaufort() {

        WindSpeedEntry beaufortTest = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(1)
                .withWindSpeed((double) 120)
                .withDate("26.01.2020 18:35:20")
                .withStation("Istanbul")
                .withTemperature((double) 30)
                .withWindDirection("NE")
                .withWeatherCondition("cloudy")
                .build();

        Double beaufortTestResult = beaufortTest.getBeaufort();
        Double beaufortExp = 13.959039999999998;

        Assertions.assertEquals(beaufortExp,beaufortTestResult);
    }

    @Test
    public void testKnots() {

        WindSpeedEntry knotsTest = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(1)
                .withWindSpeed((double) 120)
                .withDate("26.01.2020 18:35:20")
                .withStation("Istanbul")
                .withTemperature((double) 30)
                .withWindDirection("NE")
                .withWeatherCondition("cloudy")
                .build();

        Double knotsTestResult = knotsTest.getKnots();
        Double knotsExp = 5.3996;

        Assertions.assertEquals(knotsExp,knotsTestResult);
    }
}
