package org.example.jdbc; // Declares the package name for the class.

public class EthnicGroup { // Declares the public class EthnicGroup.
    private String name; // Declares a private instance variable for the name of the ethnic group.
    private int population; // Declares a private instance variable for the population of the ethnic group.
    private String region; // Declares a private instance variable for the region of the ethnic group.
    private String language; // Declares a private instance variable for the language spoken by the ethnic group.
    private String majorFestivals; // Declares a private instance variable for the major festivals of the ethnic group.
    private String economicActivities; // Declares a private instance variable for the economic activities of the ethnic group.

    // Constructor to initialize all instance variables.
    public EthnicGroup(String name, int population, String region, String language, String majorFestivals, String economicActivities) {
        this.name = name; // Initializes the name instance variable.
        this.population = population; // Initializes the population instance variable.
        this.region = region; // Initializes the region instance variable.
        this.language = language; // Initializes the language instance variable.
        this.majorFestivals = majorFestivals; // Initializes the majorFestivals instance variable.
        this.economicActivities = economicActivities; // Initializes the economicActivities instance variable.
    }

    public String getName() { // Getter method for the name instance variable.
        return name; // Returns the value of the name instance variable.
    }

    public int getPopulation() { // Getter method for the population instance variable.
        return population; // Returns the value of the population instance variable.
    }

    public String getRegion() { // Getter method for the region instance variable.
        return region; // Returns the value of the region instance variable.
    }

    public String getLanguage() { // Getter method for the language instance variable.
        return language; // Returns the value of the language instance variable.
    }

    public String getMajorFestivals() { // Getter method for the majorFestivals instance variable.
        return majorFestivals; // Returns the value of the majorFestivals instance variable.
    }

    public String getEconomicActivities() { // Getter method for the economicActivities instance variable.
        return economicActivities; // Returns the value of the economicActivities instance variable.
    }
}
