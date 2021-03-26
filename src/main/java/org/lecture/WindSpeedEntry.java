package org.lecture;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * class of the main topic "org.lecture.WindSpeedEntry"
 */
@Getter
@Setter
public class WindSpeedEntry {

    @CsvBindByName
    private int id;

    @CsvBindByName
    private Double windSpeed;

    @CsvBindByName
    private String date;

    @CsvBindByName
    private String station;

    @CsvBindByName
    private Double temperature;

    @CsvBindByName
    private String windDirection;

    @CsvBindByName
    private String weatherCondition;

    public WindSpeedEntry() {
    }


    private WindSpeedEntry(int id, Double windSpeed, String date, String station, Double temperature, String windDirection, String weatherCondition) {
        this.id = id;
        this.windSpeed = windSpeed;
        this.date = date;
        this.station = station;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.weatherCondition = weatherCondition;
    }

    public static class WindSpeedEntryBuilder {

        private int id;
        private Double windSpeed;
        private String date;
        private String station;
        private Double temperature;
        private String windDirection;
        private String weatherCondition;

        public WindSpeedEntryBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public WindSpeedEntryBuilder withWindSpeed(Double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public WindSpeedEntryBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public WindSpeedEntryBuilder withStation(String station) {
            this.station = station;
            return this;
        }

        public WindSpeedEntryBuilder withTemperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        public WindSpeedEntryBuilder withWindDirection(String windDirection) {
            this.windDirection = windDirection;
            return this;
        }

        public WindSpeedEntryBuilder withWeatherCondition(String weatherCondition) {
            this.weatherCondition = weatherCondition;
            return this;
        }

        public org.lecture.WindSpeedEntry build() {

            return new org.lecture.WindSpeedEntry
                    (id, windSpeed, date, station, temperature,
                            windDirection, weatherCondition);

        }
    }

    /**
     * overrides default String method
     * @return String overriden toString
     */
    @Override
    public String toString() {

        return id + "," +
                windSpeed + "," +
                date + "," +
                station + "," +
                temperature + "," +
                windDirection + "," +
                weatherCondition
                ;
    }

    /**
     * gets extreme value "calm" or "hurricane" depending on the value of the wind speed
     * @return String Extreme
     */
    public String getExtreme() {
        String extreme = null;
        if(windSpeed<2) {
            extreme = "calm";
        } else if(windSpeed>=120) {
            extreme = "hurricane";
        }
        return extreme;
    }

    /**
     * gets beaufort value depending on the value of the knots
     * @return Double Beaufort
     */
    public Double getBeaufort() {
        Double beaufort = null;
        if(getKnots()!=null)
            beaufort = ((getKnots()+5)/5);
        return beaufort;
    }

    /**
     * gets knot value depending on the value of the wind speed
     * @return Double knots
     */
    public Double getKnots() {
        Double knots = null;
        if(windSpeed!=null)
            knots = windSpeed * 0.53996;
        return knots;
    }
}