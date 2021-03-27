package org.lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CSVConfigurationTest {

    @Test
    public void parse2WindSpeedEntriesSize() throws IOException {
        List<WindSpeedEntry> list = CSVConfiguration.getWindSpeedEntriesFromCSV("src/test/resources/windspeedentries.csv");
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void parse2WindSpeedEntriesAndToStringMethod() throws Exception {
        List<WindSpeedEntry> list = CSVConfiguration.getWindSpeedEntriesFromCSV("src/test/resources/windspeedentries.csv");

        String viennaStringCsv = list.get(0).toString();
        String orlandoStringCsv = list.get(1).toString();
        String innsbruckStringCsv = list.get(2).toString();

        WindSpeedEntry vienna = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(1)
                .withWindSpeed((double) 26)
                .withDate("26.01.2020 18:35:20")
                .withStation("Vienna")
                .withTemperature((double) 5)
                .withWindDirection("NE")
                .withWeatherCondition("cloudy")
                .build();

        String viennaString = vienna.toString();

        WindSpeedEntry orlando = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(2)
                .withWindSpeed((double) 122)
                .withDate("30.06.1995 06:20:11")
                .withStation("Orlando")
                .withTemperature((double) 30)
                .withWindDirection("E")
                .withWeatherCondition("sunny")
                .build();

        String orlandoString = orlando.toString();

        WindSpeedEntry innsbruck = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(3)
                .withWindSpeed((double) 1)
                .withDate("21.12.2011 14:13:00")
                .withStation("Innsbruck")
                .withTemperature((double) -1)
                .withWindDirection("S")
                .withWeatherCondition("snowy")
                .build();

        String innsbruckString = innsbruck.toString();

        Assertions.assertEquals(viennaStringCsv, viennaString);
        Assertions.assertEquals(orlandoStringCsv, orlandoString);
        Assertions.assertEquals(innsbruckStringCsv, innsbruckString);
    }

    @Test
    public void writeAndAdd2WindSpeedEntries() throws Exception {

        //simulate input
        WindSpeedEntry vienna = new WindSpeedEntry.WindSpeedEntryBuilder()
                .withId(1)
                .withWindSpeed((double) 26)
                .withDate("26.01.2020 18:35:20")
                .withStation("Vienna")
                .withTemperature((double) 5)
                .withWindDirection("NE")
                .withWeatherCondition("cloudy")
                .build();

        CSVConfiguration.addWindSpeedEntryToCSV(vienna, "src/test/resources/writewindspeedentries.csv");

        FileInputStream in = new FileInputStream("src/test/resources/writewindspeedentries.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLastLine = "";
        String tmp;

        while ((tmp = br.readLine()) != null) {
            strLastLine = tmp;
        }

        Assertions.assertEquals(vienna.toString(),strLastLine);
    }
}