package com.indyDTO;

/**
 * Data Transfer Object (DTO) for Indi 500 winners.
 */
public class IndyWinnerDTO {
    private int year;
    private String driver;
    private float averageSpeed;
    private String country;

    // Constructor
    public IndyWinnerDTO(int year, String driver, float averageSpeed, String country) {
        this.year = year;
        this.driver = driver;
        this.averageSpeed = averageSpeed;
        this.country = country;
    }

    // Getters
    public int getYear() {
        return year;
    }

    public String getDriver() {
        return driver;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public String getCountry() {
        return country;
    }
}
