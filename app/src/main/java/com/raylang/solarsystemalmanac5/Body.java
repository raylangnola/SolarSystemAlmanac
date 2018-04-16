package com.raylang.solarsystemalmanac5;

import java.util.Locale;

/**
 * Created by raylang on 3/26/18.
 */

public class Body {

    private static final String ACTIVITY_TAG = "MainActivity";

    private int id;
    private String name;
    private String body_type; // TODO define an enumerated type here
    private int orderFromSun;
    private double diameterRelativeToEarth; // (earth=1)
    private double diameterKilometers;
    private double massRelativeToEarth; // (earth=1)
    private double meanDistanceFromSunAU;
    private double orbitalPeriodInEarthYears;
    private double orbitalEccentricity;
    private double meanOrbitalVelocityKmPerSec;
    private double rotationPeriodInEarthDays; // (earth=1)
    private double inclinationOfAxisDegrees;
    private double meanTemperatureAtSurfaceCelsius;
    private double gravityAtEquatorRelativeToEarth; // (earth=1)
    private double escapeVelocityKmPerSec;
    private double meanDensityRelativeToWater; // (water=1),
    private String atmosphericComposition;
    private int numMoons;
    private boolean rings;
    private String parentBody;
    private String image;
    private String URI; // wikipedia page

    public Body(String data) {
        String[] tokens = data.split(",");
        id = Integer.parseInt(tokens[0]);
        name = tokens[1];
        body_type = tokens[2];
        orderFromSun = Integer.parseInt(tokens[3]);
        diameterRelativeToEarth = Double.parseDouble(tokens[4]);
        diameterKilometers = Double.parseDouble(tokens[5]);
        massRelativeToEarth = Double.parseDouble(tokens[6]);
        meanDistanceFromSunAU = Double.parseDouble(tokens[7]);
        orbitalPeriodInEarthYears = Double.parseDouble(tokens[8]);
        orbitalEccentricity = Double.parseDouble(tokens[9]);
        meanOrbitalVelocityKmPerSec = Double.parseDouble(tokens[10]);
        rotationPeriodInEarthDays = Double.parseDouble(tokens[11]);
        inclinationOfAxisDegrees = convertToDouble(tokens[12]); // returns 0 if token is empty
        meanTemperatureAtSurfaceCelsius = Double.parseDouble(tokens[13]);
        gravityAtEquatorRelativeToEarth = Double.parseDouble(tokens[14]);
        escapeVelocityKmPerSec = Double.parseDouble(tokens[15]);
        meanDensityRelativeToWater = Double.parseDouble(tokens[16]);
        atmosphericComposition = tokens[17];
        numMoons = Integer.parseInt(tokens[18]);
        rings = Boolean.parseBoolean(tokens[19]);
        parentBody = tokens[20];
        image = tokens[21];
        URI = tokens[22];
    }

    public String toString() {
        return String.format(Locale.US,
                "%s (%s)", name, body_type);
    }

    private double convertToDouble(String s) {
        return s.isEmpty() ? 0.0 : Double.parseDouble(s);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBody_type() {
        return body_type;
    }

    public int getOrderFromSun() {
        return orderFromSun;
    }

    public double getDiameterRelativeToEarth() {
        return diameterRelativeToEarth;
    }

    public double getDiameterKilometers() {
        return diameterKilometers;
    }

    public double getMassRelativeToEarth() {
        return massRelativeToEarth;
    }

    public double getMeanDistanceFromSunAU() {
        return meanDistanceFromSunAU;
    }

    public double getOrbitalPeriodInEarthYears() {
        return orbitalPeriodInEarthYears;
    }

    public double getOrbitalEccentricity() {
        return orbitalEccentricity;
    }

    public double getMeanOrbitalVelocityKmPerSec() {
        return meanOrbitalVelocityKmPerSec;
    }

    public double getRotationPeriodInEarthDays() {
        return rotationPeriodInEarthDays;
    }

    public double getInclinationOfAxisDegrees() {
        return inclinationOfAxisDegrees;
    }

    public double getMeanTemperatureAtSurfaceCelsius() {
        return meanTemperatureAtSurfaceCelsius;
    }

    public double getGravityAtEquatorRelativeToEarth() {
        return gravityAtEquatorRelativeToEarth;
    }

    public double getEscapeVelocityKmPerSec() {
        return escapeVelocityKmPerSec;
    }

    public double getMeanDensityRelativeToWater() {
        return meanDensityRelativeToWater;
    }

    public String getAtmosphericComposition() {
        return atmosphericComposition;
    }

    public int getNumMoons() {
        return numMoons;
    }

    public boolean isRings() {
        return rings;
    }

    public String getParentBody() {
        return parentBody;
    }

    public String getImageName() {
        return image;
    }

    public String getURI() {
        return URI;
    }

}
