package org.lecture;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * driver class of the application for adding wind speed entries to CSV file and reading them
 */
public class WindSpeedsDriverClass {

    /**
     * main method of the application for adding wind speed entries to CSV file and reading them
     */
    public static void main(String[] args) {

        String path = "src/main/resources/windspeedentries.csv";
        List<WindSpeedEntry> myList;

        do {

            myList = CSVConfiguration.getWindSpeedEntriesFromCSV(path);

            //Menu appears:
            //1 Make new wind speed entries
            //2 Show current wind speed entries
            int optionAddOrShow = MenuGuidance.getOptionAddOrShow();

            switch(optionAddOrShow) {
                case 1 -> {
                    MenuGuidance.getAllWindSpeedEntriesAndAddThemToCSV(myList.size(),path);
                }
                case 2 -> {
                    for (WindSpeedEntry entry : myList) {
                        System.out.println("ID: " + entry.getId());
                        System.out.println("Wind Speed: " + entry.getWindSpeed());

                        if(entry.getExtreme()!=null) {
                            if (entry.getExtreme().equals("calm")) {
                                System.out.println("Extreme status: " + entry.getExtreme());
                            } else if (entry.getExtreme().equals("hurricane")) {
                                System.out.println("Extreme status: " + entry.getExtreme());
                            }
                        }

                        System.out.printf("Measured speed units: %.2f knots; %.2f beaufort %n", entry.getKnots(), entry.getBeaufort());

                        System.out.println("Date: " + entry.getDate());
                        System.out.println("Station: " + entry.getStation());

                        if(entry.getTemperature()!=null)
                            System.out.printf("Temperature: %.1f°C %n", entry.getTemperature());

                        if(!entry.getWindDirection().equals(""))
                            System.out.println("Wind Direction: " + entry.getWindDirection());

                        if(!entry.getWeatherCondition().equals(""))
                            System.out.println("Weather Condition: " + entry.getWeatherCondition());

                        System.out.println("==========================");
                    }
                }
                default -> System.exit(0);
            }
        } while(true);
    }
}