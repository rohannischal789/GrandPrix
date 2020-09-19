
/**
 * Write a description of class Driver here.
 * Driver is the class which represents a Driver in the Championship.
 * It has accessor and mutator methods to return or update fields such as name, ranking, specialSkill,eligibleToRace,accumulatedScore and accumulatedTime.
 * @author Rohan Nischal
 * @version 1.3
 */
public class Driver
{
    private String name; // Represents the name of a driver
    private int ranking; // Represents the ranking of a driver
    private String specialSkill; // Represents the special skill of a driver
    private boolean eligibleToRace; // Represents the eligiblilty to race of a driver
    private int accumulatedScore; // Represents the accumulated score in the championship of a driver
    private int accumulatedTime; // Represents the accumulated time of a driver

    /**
     * Driver Constructor
     * Sets the field values to the default values of a driver in the championship (default constructor)
     */
    public Driver()
    {
        name = "";
        ranking = 0;
        specialSkill = "";
        eligibleToRace = true;
        accumulatedScore = 0;
        accumulatedTime = 0;
    }
    
    /**
     * Driver Constructor
     * Sets the field values equal to the values passed as arguments (non-default constructor)
     * @param newName A new name for the driver
     * @param newRanking A new ranking for the driver
     * @param newSpecialSkill A new special skill for the driver
     * @param newEligibleToRace A new eligbility to race for the driver
     * @param newAccumulatedScore A new accumulated score for the driver
     * @param newAccumulatedTime A new accumulated tiem for the driver
     */
    public Driver(String newName, int newRanking, String newSpecialSkill, boolean newEligibleToRace, int newAccumulatedScore, int newAccumulatedTime)
    {
        name = newName;
        ranking = newRanking >= 0 ? newRanking : 0;
        specialSkill = newSpecialSkill;
        eligibleToRace = newEligibleToRace;
        accumulatedScore = newAccumulatedScore >= 0 ? newAccumulatedScore : 0;
        accumulatedTime = newAccumulatedTime >= 0 ? newAccumulatedTime : 0;
    }
    
    /**
     * Method getName
     *
     * @return The driver's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Method getRanking
     *
     * @return The driver's ranking
     */
    public int getRanking()
    {
        return ranking;
    }
    
    /**
     * Method getSpecialSkill
     *
     * @return The driver's special skill
     */
    public String getSpecialSkill()
    {
        return specialSkill;
    }
    
    /**
     * Method getEligibleToRace
     *
     * @return The driver's eligibility to race
     */
    public boolean getEligibleToRace()
    {
        return eligibleToRace;
    }
    
    /**
     * Method getAccumulatedScore
     *
     * @return The driver's accumulated score
     */
    public int getAccumulatedScore()
    {
        return accumulatedScore;
    }
    
    /**
     * Method getAccumulatedTime
     *
     * @return The driver's accumulated time
     */
    public int getAccumulatedTime()
    {
        return accumulatedTime;
    }
    
    /**
     * Method setName
     *
     * @param newName A driver's new name in the championship
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Method setRanking
     *
     * @param newRanking A driver's new ranking in the championship
     */
    public void setRanking(int newRanking)
    {
        ranking = newRanking >= 0 ? newRanking : 0;
    }
    
    /**
     * Method setSpecialSkill
     *
     * @param newSpecialSkill A driver's new special skill in the championship
     */
    public void setSpecialSkill(String newSpecialSkill)
    {
        specialSkill = newSpecialSkill;
    }
    
    /**
     * Method setEligibleToRace
     *
     * @param newEligibleToRace A driver's new eligibility to race in the championship
     */
    public void setEligibleToRace(boolean newEligibleToRace)
    {
        eligibleToRace = newEligibleToRace;
    }
    
    /**
     * Method setAccumulatedScore
     *
     * @param newAccumulatedScore A driver's new accumulated score in the championship
     */
    public void setAccumulatedScore(int newAccumulatedScore)
    {
        accumulatedScore = newAccumulatedScore >= 0 ? newAccumulatedScore : 0;
    }
    
    /**
     * Method setAccumulatedTime
     *
     * @param newAccumulatedTime A driver's new accumulated time in the championship
     */
    public void setAccumulatedTime(int newAccumulatedTime)
    {
        accumulatedTime = newAccumulatedTime >= 0 ? newAccumulatedTime : 0;
    }
}
