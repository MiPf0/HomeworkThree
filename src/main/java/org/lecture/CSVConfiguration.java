package org.lecture;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * class with methods that...
 *  - read csv file and save data into windSpeedEntry Object
 *  - add given windSpeedEntry Object to csv file
 */
public class CSVConfiguration {

    /**
     * reads a csv file with headers and stores it into a Bean List<> of Objects with the help of "CsvToBean" tool
     *  -adapted from "Read / Write CSV files in Java using OpenCSV" (by Rajeev Singh, Sep 29,2017)
     *  -Link: https://www.callicoder.com/java-read-write-csv-file-opencsv/
     * @param path where csv file is located
     * @return List<org.lecture.WindSpeedEntry> LinkedList of Type org.lecture.WindSpeedEntry
     */
    public static List<WindSpeedEntry> getWindSpeedEntriesFromCSV(String path) {

        List<WindSpeedEntry> windSpeedEntries = new LinkedList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CsvToBean<WindSpeedEntry> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(WindSpeedEntry.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            windSpeedEntries = csvToBean.parse();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return windSpeedEntries;
    }

    /**
     * adds a given org.lecture.WindSpeedEntry Object to an existing CSV-File
     * @param windspeedentry which is supposed to be added to the CSV-File
     * @param path to the CSV-File
     */
    public static void addWindSpeedEntryToCSV(WindSpeedEntry windspeedentry, String path) {

        try {
            FileWriter writer = new FileWriter(path, true);

            String entry = windspeedentry.toString();

            if(entry.contains("null"))
                entry = entry.replaceAll("null", "");

            writer.write("\n");
            writer.write(entry);
            writer.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}