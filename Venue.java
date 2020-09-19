
/**
 * Write a description of class Venue here.
 * Venue is the class which represents a racing venue in the Championship.
 * It has accessor and mutator methods to return or update fields such as venueName, noOfLaps, averageLapTime and chanceOfRain.
 * @author Rohan Nischal
 * @version 1.1
 */
public class Venue
{
    private String venueName; // represents the name of a venue
    private int noOfLaps; // represents the total number of laps in a venue
    private int averageLapTime; // represents the average lap time of a venue
    private double chanceOfRain; // represents the chance of rain occuring at a venue
    
    /**
     * Venue Constructor
     * Sets the field values to the default values of a venue in the championship (default constructor)
     */
    public Venue()
    {
        venueName = "";
        noOfLaps = 0;
        averageLapTime = 0;
        chanceOfRain = 0;
    }
    
    /**
     * Venue Constructor
     * Sets the field values equal to the values passed as arguments (non-default constructor)
     * @param newVenueName A new venue name
     * @param newNoOfLaps A new total number of laps
     * @param newAverageLapTime A new average lap time
     * @param newChanceOfRain A new chance of rain
     */
    public Venue(String newVenueName, int newNoOfLaps, int newAverageLapTime, double newChanceOfRain)
    {
        venueName = newVenueName;
        noOfLaps = newNoOfLaps;
        averageLapTime = newAverageLapTime;
        chanceOfRain = newChanceOfRain;
    }
    
    /**
     * Method getVenueName
     *
     * @return The name of a venue
     */
    public String getVenueName()
    {
        return venueName;
    }
    
    /**
     * Method getNoOfLaps
     *
     * @return The total number of laps at a venue
     */
    public int getNoOfLaps()
    {
        return noOfLaps;
    }
    
    /**
     * Method getAverageLapTime
     *
     * @return The average lap time of a venue
     */
    public int getAverageLapTime()
    {
        return averageLapTime;
    }
    
    /**
     * Method getChanceOfRain
     *
     * @return The chance of rain occuring at a venue
     */
    public double getChanceOfRain()
    {
        return chanceOfRain;
    }
    
    /**
     * Method setVenueName
     *
     * @param newVenueName A new venue name
     */
    public void setVenueName(String newVenueName)
    {
        venueName = newVenueName;
    }
    
    /**
     * Method setNoOfLaps
     *
     * @param newNoOfLaps A new total number of laps
     */
    public void setNoOfLaps(int newNoOfLaps)
    {
        noOfLaps = newNoOfLaps;
    }
    
    /**
     * Method setAverageLapTime
     *
     * @param newAverageLapTime A new average lap time
     */
    public void setAverageLapTime(int newAverageLapTime)
    {
        averageLapTime = newAverageLapTime;
    }
    
    /**
     * Method setChanceOfRain
     *
     * @param newChanceOfRain A new chance of rain
     */
    public void setChanceOfRain(double newChanceOfRain)
    {
        chanceOfRain = newChanceOfRain;
    }
}
