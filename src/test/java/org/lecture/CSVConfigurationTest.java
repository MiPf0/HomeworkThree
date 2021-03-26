package org.lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVConfigurationTest {

    @Test
    public void parse2WindSpeedEntriesSize() throws IOException {
        List<WindSpeedEntry> list = CSVConfiguration.getWindSpeedEntriesFromCSV("src/test/resources/windspeedentries.csv");
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void parse2WindSpeedEntries() throws Exception {
        List<WindSpeedEntry> list = CSVConfiguration.getWindSpeedEntriesFromCSV("src/test/resources/windspeedentries.csv");

        WindSpeedEntry vienna = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(1)
                .withWindSpeed((double) 26)
                .withDate("26.01.2020 18:35:20")
                .withStation("Vienna")
                .withTemperature((double) 5)
                .withWindDirection("NE")
                .withWeatherCondition("cloudy")
                .build();

        WindSpeedEntry orlando = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(2)
                .withWindSpeed((double) 122)
                .withDate("30.06.1995 06:20:11")
                .withStation("Orlando")
                .withTemperature((double) 30)
                .withWindDirection("E")
                .withWeatherCondition("sunny")
                .build();

        WindSpeedEntry innsbruck = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(3)
                .withWindSpeed((double) 1)
                .withDate("21.12.2011 14:13:00")
                .withStation("Innsbruck")
                .withTemperature((double) -1)
                .withWindDirection("S")
                .withWeatherCondition("snowy")
                .build();

        List<WindSpeedEntry> expected = new ArrayList<>();
        expected.add(vienna);
        expected.add(orlando);
        expected.add(innsbruck);

        Assertions.assertEquals(expected.get(0), list.get(0));
        Assertions.assertEquals(expected.get(1), list.get(1));
        Assertions.assertEquals(expected.get(2), list.get(2));
    }
}