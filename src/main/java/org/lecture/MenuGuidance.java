package org.lecture;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * class that has all menus used in the WindSpeedDriverClass
 */
public class MenuGuidance {

    /**
     * method that gets one of two options: make new wind speed entries or show current ones
     * @return int selection
     */
    public static int getOptionAddOrShow() {
        String output = """
                    1 - Make new wind speed entries
                    2 - Show current wind speed entries
                    Every other input - End program
                    """;
        System.out.println(output);
        int option;
        try {
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            option = -1;
        }
        return option;
    }

    /**
     * method that gets the number of how many entries the user wants to make
     * @return int number of wished entries
     */
    public static int getNumberOfEntries() {
        String output = """
                    How many entries do you want to add? Please type the number of entries.
                    Every other input - End program
                    """;
        System.out.println(output);
        int numberOfEntries;
        try {
            Scanner sc = new Scanner(System.in);
            numberOfEntries = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            numberOfEntries = -1;
            System.exit(0);
        }
        return numberOfEntries;
    }

    /**
     * method that gets the number of which input the user wants to make for his wind speed entry
     * @return int number of chosen input
     */
    public static int getOptionMakeNewWindSpeedEntry() {
        String output = """
                    Please type the respective number and add/change the data:
                    #### Mandatory ####
                    1 - Wind speed
                    2 - Add current local date and time
                    3 - Measurement station
                    ------------------------
                    #### Optional ####
                    4 - Outside temperature
                    5 - Wind direction
                    6 - Weather condition
                    ------------------------
                    7 - Submit completed entry to data file
                    8 - Do not submit anything and go back to previous menu
                    Every other input - End program
                    """;
        System.out.println(output);
        int option;
        try {
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            option = -1;
        }
        return option;
    }

    /**
     * method that gets all wind speed entries typed in by the user, saving them in a list and adds them to the CSV
     * @param currentListSize type in the current list size to get the next following IDs
     * @param path type in the path where CSV file is located
     */
    public static void getAllWindSpeedEntriesAndAddThemToCSV(int currentListSize, String path) {

        List<WindSpeedEntry> entriesList;
        int numberOfEntries = getNumberOfEntries();

        for (int i = 1; i <= numberOfEntries; i++) {

            System.out.println("This is the configuration of your " + i + ". wind speed entry.");

            WindSpeedEntry entry = new WindSpeedEntry.WindSpeedEntryBuilder()
                    .withId(currentListSize+1)
                    .withWindSpeed(null)
                    .withDate(null)
                    .withStation(null)
                    .withTemperature(null)
                    .withWindDirection(null)
                    .withWeatherCondition(null)
                    .build();

            do {

                entry.setId(currentListSize+1);

                int option = getOptionMakeNewWindSpeedEntry();
                int option1 = 0;

                switch (option) {
                    case 1 -> {
                        Double windSpeed = getWindSpeed();
                        entry.setWindSpeed(windSpeed);
                    }
                    case 2 -> {
                        String date = getDate();
                        entry.setDate(date);
                    }
                    case 3 -> {
                        String station = getStation();
                        entry.setStation(station);
                    }
                    case 4 -> {
                        Double temperature = getTemperature();
                        entry.setTemperature(temperature);
                    }
                    case 5 -> {
                        String windDirection = getWindDirection();
                        entry.setWindDirection(windDirection);
                    }
                    case 6 -> {
                        String weatherCondition = getWeatherCondition();
                        entry.setWeatherCondition(weatherCondition);
                    }
                    case 7 -> {
                        if (entry.getWindSpeed() == null || entry.getDate() == null || entry.getStation() == null) {
                            System.out.println("You have not entered yet all mandatory data. Please do before submitting.");
                            if(entry.getWindSpeed()==null)
                                System.out.println("Wind Speed is still missing.");
                            if(entry.getDate()==null)
                                System.out.println("Date is still missing.");
                            if(entry.getStation()==null)
                                System.out.println("Station is still missing.");

                        } else {
                            CSVConfiguration.addWindSpeedEntryToCSV(entry,path);
                            option1 = 77;
                        }
                    }
                    case 8 -> {
                        option1 = 8;
                    }
                    default -> {
                        System.exit(0);
                    }

                }
                if (option1 == 77 || option1 == 8)
                    break;

            } while (true);
        }
    }

    /**
     * saves input from user into Double variable
     * @return Double WindSpeed
     */
    public static Double getWindSpeed() {
        String output = """
                    Please input the measured wind speed:
                    """;
        System.out.println(output);
        Double windSpeed = null;
        try {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if(input.contains(","))
                input = input.replaceAll(",", ".");

            windSpeed = Double.valueOf(input);
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return windSpeed;
    }

    /**
     * saves current date and time
     * @return String date and time
     */
    public static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String date = dtf.format(now);
        System.out.println("Your current date and time " + date + " was added.");
        return date;
    }

    /**
     * saves input from user into String variable
     * @return String station
     */
    public static String getStation() {
        String output = """
                    Please input the station where the wind speed was measured:
                    """;
        System.out.println(output);
        String station;
        try {
            Scanner sc = new Scanner(System.in);
            station = sc.nextLine();
        } catch (InputMismatchException e) {
            station = null;
        }
        return station;
    }

    /**
     * saves input from user into Double variable
     * @return Double temperature
     */
    public static Double getTemperature() {
        String output = """
                    Please input the temperature during the measured wind speed:
                    """;
        System.out.println(output);
        Double temperature = null;
        try {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if(input.contains(","))
                input = input.replaceAll(",", ".");

            temperature = Double.valueOf(input);
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return temperature;
    }

    /**
     * saves input from user into String variable
     * @return String windDirection
     */
    public static String getWindDirection() {
        String output = """
                    Please input the wind direction during the measured wind speed:
                    """;
        System.out.println(output);
        String windDirection;
        try {
            Scanner sc = new Scanner(System.in);
            windDirection = sc.nextLine();
        } catch (InputMismatchException e) {
            windDirection = null;
        }
        return windDirection;
    }

    /**
     * saves input from user into String variable
     * @return String weatherCondition
     */
    public static String getWeatherCondition() {
        String output = """
                    Please input the weather condition during the measured wind speed:
                    """;
        System.out.println(output);
        String weatherCondition;
        try {
            Scanner sc = new Scanner(System.in);
            weatherCondition = sc.nextLine();
        } catch (InputMismatchException e) {
            weatherCondition = null;
        }
        return weatherCondition;
    }
}