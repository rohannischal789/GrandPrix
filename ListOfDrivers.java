import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * ListOfDrivers class represents a collection of the drivers in the championship.
 * It has accessor and mutator methods to get and set the drivers list. It also has a method to add a new driver to the collection.
 * It also contains methods to sort the collection of drivers by each of the fields
 * @author Rohan Nischal
 * @version 1.3
 */
public class ListOfDrivers
{
    private ArrayList<Driver> drivers; //  a collection of all the drivers in the championship
    
    /**
     * ListOfDrivers Constructor
     * Sets the field values to the default values the drivers in the championship (default constructor)
     */
    public ListOfDrivers()
    {
        drivers = new ArrayList<Driver>();
    }

    /**
     * ListOfDrivers Constructor
     * Sets the field values equal to the values passed as arguments (non-default constructor)
     * @param newDrivers A collection of new drivers in the championship
     */
    public ListOfDrivers(ArrayList<Driver> newDrivers)
    {
        drivers = newDrivers;
    }

    /**
     * Method getDrivers
     *
     * @return A collection of all drivers in the championship
     */
    public ArrayList<Driver> getDrivers()
    {
        return drivers;
    }

    /**
     * Method setDrivers
     *
     * @param newDrivers A collection of new drivers in the championship
     */
    public void setDrivers(ArrayList<Driver> newDrivers)
    {
        drivers = newDrivers;
    }

    /**
     * Method getDriver
     *
     * @param index A integer value representing the index in the collection
     * @return The driver at this index
     */
    public Driver getDriver(int index)
    {
        Driver returnableDriver = new Driver();
        if(!drivers.isEmpty())
            returnableDriver = getDrivers().get(index);   
        return returnableDriver;
    }

    /**
     * Method setDriver
     *
     * @param index A integer value representing the index in the collection
     * @param newDriver A new driver to add at this index
     */
    public void setDriver(int index, Driver newDriver)
    {
        getDrivers().set(index, newDriver);
    }

    /**
     * Method addDriver
     * Used to add a new driver to the collection
     * @param newName A new name for the driver
     * @param newRanking A new ranking for the driver
     * @param newSpecialSkill A new special skill for the driver
     * @param newEligibleToRace A new eligibility to race for the driver
     */
    public void addDriver(String newName, int newRanking, String newSpecialSkill, boolean newEligibleToRace)
    {
        Driver addableDriver = new Driver();
        addableDriver.setName(newName);
        addableDriver.setRanking(newRanking);
        addableDriver.setSpecialSkill(newSpecialSkill);
        addableDriver.setEligibleToRace(newEligibleToRace);
        drivers.add(addableDriver);
    }

    /**
     * Method getSize
     *
     * @return The size of the collection of drivers
     */
    public int getSize()
    {
        return getDrivers().size();
    }

    /**
     * Method displayDrivers
     *
     * @return The concatenated values for all the drivers
     */
    public String displayDrivers()
    {
        String driverDetails = "";
        String eliminatedDrivers = "";
        for(Driver driver: getDrivers())
        {
            if(driver.getEligibleToRace())
            {
                driverDetails += "Ranking : " + driver.getRanking() + " Name : " + driver.getName() + " Time : " + driver.getAccumulatedTime() + " secs\n";
            }
            else
            {
                eliminatedDrivers += eliminatedDrivers == "" ? "Eliminated Drivers: " : "";
                eliminatedDrivers += driver.getName() + "\n"; 
            }
        }
        return driverDetails + eliminatedDrivers;
    }

    /**
     * Method displayLeader
     * 
     * @return The leader of the race
     */
    public String displayLeader()
    {
        String driverDetails = "";
        driverDetails += "Ranking : " + getDriver(0).getRanking() + " Name : " + getDriver(0).getName() + " Time : " + getDriver(0).getAccumulatedTime() + " secs\n";
        return "RACE LEADER: " +  driverDetails;
    }

    /**
     * Method getChampionshipDetails
     *
     * @return The driver's ranking and score in the championship
     */
    public String getChampionshipDetails()
    {
        String driverDetails = "";
        for(Driver driver: getDrivers())
        {
            driverDetails += "Ranking : " + driver.getRanking() + " Name : " + driver.getName() + " Score : " + driver.getAccumulatedScore() + " points\n";
        }
        return driverDetails;
    }

    /**
     * Method sortByScore
     * This can be used to sort the driver collection by score (descending)
     */
    public void sortByScore()
    {
        Collections.sort(getDrivers(), new Comparator<Driver>()
            {
                public int compare(Driver d1, Driver d2) {
                    return d2.getAccumulatedScore() - d1.getAccumulatedScore();
                }
            });
    }
    
    /**
     * Method getAllTyreTypes
     *
     * @return The default tyre types for all the drivers
     */
    public char[] getAllTyreTypes()
    {
        int size = drivers.size();
        char[] allTyreTypes = new char[size];
        for (int i = 0; i < size; i++) 
        {
            allTyreTypes[i] = 'D';
        }
        return allTyreTypes;
    }

    /**
     * Method resetEligibleToRace
     * Used to reset a driver's eligibility to race 
     */
    public void resetEligibleToRace()
    {
        for(Driver driver : getDrivers())
        {
            driver.setEligibleToRace(true);
        }
    }

    /**
     * Method resetTyreType
     * Used to reset a driver's car's tyre type 
     * @param allTypes A parameter
     */
    public void resetTyreType(char[] allTypes)
    {
        for(char type : allTypes)
        {
            type = 'D';
        }
    }

    /**
     * Method adjustDriversListByTime
     * this method uses bubble sort to sort the collection of drivers by time. It then shifts the ineligible to race drivers to the end of the collection.
     * @param tyreTypes An array containing the tyre types of all the drivers
     */
    public void adjustDriversListByTime(char[] tyreTypes) 
    { 
        int n = drivers.size();
        for (int i = 0; i < n-1; i++) 
        {
            for (int j = 0; j < n-i-1; j++) 
            {
                if (drivers.get(j).getAccumulatedTime() > drivers.get(j+1).getAccumulatedTime()) 
                { 
                    Driver temp = getDriver(j);
                    setDriver(j, getDriver(j+1));
                    setDriver(j+1, temp);
                                        
                    // tyres
                    char currentTyreType = tyreTypes[j];
                    tyreTypes[j] = tyreTypes[j+1];
                    tyreTypes[j+1] = currentTyreType;
                } 
            }
        }

        for (int i = 0; i < n-1; i++) 
        {
            for (int j = 0; j < n-i-1; j++) 
            {
                if (!drivers.get(j).getEligibleToRace()) 
                { 
                    Driver temp = getDriver(j);
                    setDriver(j, getDriver(j+1));
                    setDriver(j+1, temp);                    
                    
                    // tyre
                    char currentTyreType = tyreTypes[j];
                    tyreTypes[j] = tyreTypes[j+1];
                    tyreTypes[j+1] = currentTyreType;
                }
            }
        }
    } 
}
