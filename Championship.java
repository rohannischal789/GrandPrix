/**
 * This class represents the entire Championship. It consist of fields with collection of drivers and venue and has accessor and mutator methods for them.
 * This is the client class that contains the method to play the game.
 * It also consists of other private methods to help with the functionality internally.
 * @author Rohan Nischal
 * @version 1.3
 */
public class Championship
{
    private ListOfDrivers drivers; // an object represents all the drivers in the championship
    private ListOfVenues venues; // an object represents all the venues in the championship

    /**
     * Championship Constructor
     * Initialize the field variables to their default state
     */
    public Championship()
    {
        drivers = new ListOfDrivers();
        venues = new ListOfVenues();
    }

    /**
     * Championship Constructor
     *
     * @param newDrivers An object representing all the new drivers in the championship
     * @param newVenues An object representing all the new venues in the championship
     */
    public Championship(ListOfDrivers newDrivers, ListOfVenues newVenues)
    {
        drivers = newDrivers;
        venues = newVenues;
    }

    /**
     * Method getDrivers
     *
     * @return An object representing all the new drivers in the championship
     */
    public ListOfDrivers getDrivers()
    {
        return drivers;
    }

    /**
     * Method getVenues
     *
     * @return An object representing all the new venues in the championship
     */
    public ListOfVenues getVenues()
    {
        return venues;
    }

    /**
     * Method setDrivers
     *
     * @param newDrivers An object representing all the new drivers in the championship
     */
    public void setDrivers(ListOfDrivers newDrivers)
    {
        drivers = newDrivers;
    }

    /**
     * Method setVenues
     *
     * @param newVenues An object representing all the new venues in the championship
     */
    public void setVenues(ListOfVenues newVenues)
    {
        venues = newVenues;
    }

    /**
     * Method startGame
     * this method can be used to start the championship. It reads data from the files and initializes both the collections
     * It contains the logic for each race and working for each lap (including probability for rain and tyre type change). 
     * The drivers details are written back to the file after the championship and the winner is displayed
     */
    public void startGame()
    {
        String driversFileName = "drivers.txt";
        String venuesFileName = "venues.txt";
        drivers = new ListOfDrivers();
        venues = new ListOfVenues();
        try
        {
            readDriverFile(driversFileName); // reads data from file and initalize the collection of drivers
            readVenueFile(venuesFileName); // reads data from file and initalize the collection of venues
            displayLine();
            displayGameHeader();
            displayLine();
            Input console = new Input();
            Validation validatior = new Validation();
            int totalRaceNo = console.acceptIntegerInput("Enter the total number of races in the championship");
            while(!validatior.isNumberBetween(3, 5, totalRaceNo)) // valid input or not
            {
                totalRaceNo = console.acceptIntegerInput("Please enter the total number of races in the championship between 3 to 5");
            }
            for(int j = 1; j <= totalRaceNo ; j++)
            {
                System.out.println();
                System.out.println("Welcome to Race No " + j + " in the championship");
                System.out.println("###############SELECT A VENUE###############");
                System.out.println();
                for(int i = 0 ; i < getVenues().getSize(); i++)
                {
                    System.out.println(i + "." + getVenues().getVenue(i).getVenueName()); // shows all venues in the collection
                }
                int venueSelection = console.acceptIntegerInput("Choose a number to select a venue");
                while(!validatior.isNumberBetween(0, getVenues().getSize() - 1, venueSelection)) // valid input or not
                {
                    venueSelection = console.acceptIntegerInput("Please choose a number from above to select a venue");
                }
                String venueName = getVenues().getVenue(venueSelection).getVenueName();
                int noOfLaps = getVenues().getVenue(venueSelection).getNoOfLaps();
                int avgLapTime = getVenues().getVenue(venueSelection).getAverageLapTime();
                double chanceOfRain = getVenues().getVenue(venueSelection).getChanceOfRain();
                System.out.println("You'll be racing at " + venueName + ". This venue has " + noOfLaps + " laps in total!!");
                System.out.println(venueName + " has an average lap time of " + avgLapTime + " secs and " +(int)(chanceOfRain * 100)+ "% chance of rain");
                applyRaceStartPenalty();
                char[] allTyreTypes = getDrivers().getAllTyreTypes(); // gets the default tyre types for each driver
                getDrivers().adjustDriversListByTime(allTyreTypes);
                displayDriverDetails(1, true, false);
                for(int i = 1 ; i <= noOfLaps ; i++)
                {
                    System.out.println("####################LAP " + i + " ######################");
                    boolean isRaining = checkForRain(i,(int)(chanceOfRain * 100), allTyreTypes); // check if rained has occured depending on chance of rain
                    if(i == 2 && isRaining) // if lap number 2 and is already raining
                    {
                        checkForTyreChange(i, allTyreTypes); // 50% chance to change tyre types
                    }
                    completeLap(avgLapTime, i); // complete 1 lap and calculate time
                    updateRanking(true, allTyreTypes); // update each driver's ranking by time
                    System.out.println();
                    System.out.println(getDrivers().displayLeader()); // display the race leader
                    if(i == noOfLaps) // shows all driver's details at the end of the race
                        displayDriverDetails(i, false, true);
                }
                updateRacePoints(allTyreTypes); // update race point after each ranking
                updateRanking(false, allTyreTypes); // update each driver's ranking by score
                displayChampionshipDetails();
                getDrivers().resetEligibleToRace(); // reset all driver's eligible to race after each race
                getDrivers().resetTyreType(allTyreTypes);// reset all driver's tyre types to dry to race after each race
                getVenues().deleteVenue(venueSelection); // delete the venue from the collection as it cannot be used anymore
            }
            showChampionshipWinner();
            writeFile(driversFileName); // write driver details to the file
        }
        catch(ArrayIndexOutOfBoundsException ex) // in case of incorrect file format
        {
            System.out.println("Incorrect file");
        }
        catch(Exception ex)
        {
            System.out.println("Exception occurred" + ex);
        }
    }

    /**
     * Method applyRaceStartPenalty
     * This method can be used to apply the initial time penalties for each of the drivers in the championship by their starting positions
     */
    private void applyRaceStartPenalty()
    {
        for(int i = 0; i < getDrivers().getSize(); i++)
        {
            getDrivers().getDriver(i).setAccumulatedTime(0); // set to default
            int driverRanking = getDrivers().getDriver(i).getRanking();
            int timePenalty = 10;
            switch(driverRanking)
            {
                case 1: timePenalty = 0; break;
                case 2: timePenalty = 3; break;
                case 3: timePenalty = 5; break;
                case 4: timePenalty = 7; break;
            }
            getDrivers().getDriver(i).setAccumulatedTime(timePenalty);
        }
    }

    /**
     * Method displayDriverDetails
     *
     * @param lapNo the current lap number
     * @param isStart flag representing is this the start of the race or not
     * @param isFinish flag representing is this the finish of the race or not
     */
    private void displayDriverDetails(int lapNo, boolean isStart, boolean isFinish)
    {
        String start = isStart ? "Starting " : "After Lap " + lapNo + ", Updated ";
        start = isFinish ? "Final Race Standings \n" : start;
        System.out.println(start + "Player positions :\n" + getDrivers().displayDrivers());
    }

    /**
     * Method displayChampionshipDetails
     * This method can be used to print out the driver's championship details to the screen
     */
    private void displayChampionshipDetails()
    {
        displayLine();
        System.out.println("########Formula 9131 Championship Details########");
        displayLine();
        System.out.println(getDrivers().getChampionshipDetails());
    }

    /**
     * Method displayGameHeader
     * This method can be used to print out the welcome message to the screen
     */
    private void displayGameHeader()
    {
        System.out.println("########Welcome to Formula 9131 Championship########");
    }

    /**
     * Method displayLine
     * Display a line with Hash characters on the screen
     */
    private void displayLine()
    {
        System.out.println("#################################################");
    }

    /**
     * Method completeLap
     * this method is used to complete a single lap in the race. It takes in the average lap time and generates probability for each lap event.
     * The occuring lap events are printed out to the screen and the affected time is added to the current lap time
     * @param avgLapTime Average lap time at this venue
     * @param lapNo the current lap number
     */
    private void completeLap(int avgLapTime, int lapNo)
    {
        RNG generator = new RNG(1,9); // 9 exclusive
        RNG overtakingGenerator = new RNG(10,21); // 21 exclusive
        RNG percentageGenerator = new RNG(1,101); // 101 exclusive
        Validation validator = new Validation();
        for(int i = 0; i < getDrivers().getSize(); i++)
        {
            if(getDrivers().getDriver(i).getEligibleToRace()) // if eligible
            {
                int updatedLapTime = getDrivers().getDriver(i).getAccumulatedTime() + avgLapTime;
                int timeAffected = 0;
                String driverSpecialSkill = getDrivers().getDriver(i).getSpecialSkill();
                if(driverSpecialSkill.equalsIgnoreCase("braking") || driverSpecialSkill.equalsIgnoreCase("cornering")) // if have braking and cornering skills
                {
                    timeAffected = generator.generateRandomNo();
                    System.out.println(getDrivers().getDriver(i).getName() + " has used " + driverSpecialSkill + " to reduce lap " + lapNo + 
                        " time by " + timeAffected + "secs");
                    updatedLapTime -= timeAffected; // change lap time
                }
                else if((lapNo % 3 == 0) && driverSpecialSkill.equalsIgnoreCase("overtaking"))
                {
                    timeAffected = overtakingGenerator.generateRandomNo();
                    System.out.println(getDrivers().getDriver(i).getName() + " has used " + driverSpecialSkill + " to reduce lap " + lapNo + 
                        " time by " + timeAffected + "secs");
                    updatedLapTime -= timeAffected; // change lap time
                }

                int minorMechanicalProb = percentageGenerator.generateRandomNo();
                int majorMechanicalProb = percentageGenerator.generateRandomNo();
                int unrecovarableMechanicalProb = percentageGenerator.generateRandomNo();
                if(validator.isNumberBetween(1, 5, minorMechanicalProb)) // 5% chance of minor mechanical fault
                {
                    timeAffected = 20;
                    System.out.println(getDrivers().getDriver(i).getName() + " has suffered a minor mechanical fault to increase lap " + lapNo + 
                        " time by " + timeAffected + " secs");
                    updatedLapTime += timeAffected; // change lap time
                }
                else if(validator.isNumberBetween(1, 3, majorMechanicalProb)) // 3% chance of major mechanical fault
                {
                    timeAffected = 120;
                    System.out.println(getDrivers().getDriver(i).getName() + " has suffered a major mechanical fault to increase lap " + lapNo + 
                        " time by " + timeAffected + " secs");
                    updatedLapTime += timeAffected; // change lap time
                }
                else if(validator.isNumberBetween(1, 1, unrecovarableMechanicalProb)) // 1% chance of unrecoverable mechanical fault
                {
                    System.out.println(getDrivers().getDriver(i).getName() + " has suffered an unrecoverable mechanical fault and is no longer a part of the race");
                    getDrivers().getDriver(i).setEligibleToRace(false); // not eligible to race anymore
                }
                getDrivers().getDriver(i).setAccumulatedTime(updatedLapTime);
            }
        }
    }

    /**
     * Method updateRanking
     * sort the collection of drivers and updates the ranking of each driving
     * @param byTime flag representing to sort by accumulated time or not
     * @param allTyreTypes An array of the tyre types of all the drivers
     */
    private void updateRanking(boolean byTime, char[] allTyreTypes)
    {
        if(byTime) // if required to sort by time
        {
            getDrivers().adjustDriversListByTime(allTyreTypes);
        }
        else  // if required to sort by championship score
        {
            getDrivers().sortByScore();
        }
        for(int i = 0 ; i < getDrivers().getSize() ; i++)
        {
            getDrivers().getDriver(i).setRanking(i + 1); // update ranking
        }
    }

    /**
     * Method updateRacePoints
     * this method can be used to update the race points for each driver at the end of each race by their position in the collection after sorting
     * @param allTyreTypes An array of the tyre types of all the drivers
     */
    private void updateRacePoints(char[] allTyreTypes)
    {
        getDrivers().adjustDriversListByTime(allTyreTypes);
        for(int i = 0 ; i < getDrivers().getSize() ; i++)
        {
            int scoringPoints = 0;
            if(i == 0 && getDrivers().getDriver(i).getEligibleToRace()) // if first and eligible
            {
                scoringPoints = 8;
            }
            else if(i == 1 && getDrivers().getDriver(i).getEligibleToRace())  // if second and eligible
            {
                scoringPoints = 5;
            }
            else if(i == 2 && getDrivers().getDriver(i).getEligibleToRace()) // if third and eligible
            {
                scoringPoints = 3;
            }
            else if(i == 3 && getDrivers().getDriver(i).getEligibleToRace()) // if fourth and eligible
            {
                scoringPoints = 1;
            }
            getDrivers().getDriver(i).setAccumulatedScore(getDrivers().getDriver(i).getAccumulatedScore() + scoringPoints);
        }
    }

    /**
     * Method checkForTyreChange
     * this method can be used in case of rain to give each eligible driver a 50% chance to change tyre type
     * @param rainProbability A probability of rain at this venue
     * @param lapNo current lap number
     * @param allTyreTypes  An array of the tyre types of all the drivers
     */
    private void checkForTyreChange(int lapNo, char[] allTyreTypes)
    {
        RNG generator = new RNG(1,11);
        Validation validator = new Validation();
        for(int i = 0; i < getDrivers().getSize(); i++)
        {
            int timeAffected = 0;
            if(getDrivers().getDriver(i).getEligibleToRace()) // if driver is eligible to race
            {
                int randomNo =  generator.generateRandomNo(); // generates random number between 1 and 11(exclusive)
                if(validator.isNumberBetween(1, 5, randomNo)) // 50% chance to change tyre type
                {
                    allTyreTypes[i] = 'W';
                    timeAffected = 10;
                    System.out.println(getDrivers().getDriver(i).getName() + " has chosen to change tyres from Dry-weather to Wet-weather, which increases lap " + lapNo + 
                        " time by " + timeAffected + " secs");
                    getDrivers().getDriver(i).setAccumulatedTime(getDrivers().getDriver(i).getAccumulatedTime() + timeAffected); // update time affected
                }
            }
        }
    }

    /**
     * Method readDriverFile
     * Read details from the file containing driver details and adds it to the collection of driver
     * @param path A path or name of file to read from
     */
    public void readDriverFile(String path)
    {
        FileIO fileIO = new FileIO();
        fileIO.setFileName(path);
        String fileData = fileIO.readFile();
        String[] data = fileData.split("\\n"); // split data by new line character
        for(int i = 0 ; i < data.length ; i++)
        {
            String[] values = data[i].split(","); //split each row by ,
            getDrivers().addDriver(values[0], Integer.parseInt(values[1]), values[2], true); // add a new driver to the collection
        }        
    }

    /**
     * Method readVenueFile
     * Read details from the file containing venues and adds it to the collection of venues
     * @param path A path or name of file to read from
     */
    private void readVenueFile(String path)
    {
        FileIO fileIO = new FileIO();
        fileIO.setFileName(path);
        String fileData = fileIO.readFile();
        String[] data = fileData.split("\\n"); // split data by new line character
        for(int i = 0 ; i < data.length ; i++)
        {
            String[] values = data[i].split(","); //split each row by ,
            getVenues().addVenue(values[0], Integer.parseInt(values[1]),Integer.parseInt(values[2]), Double.parseDouble(values[3])); // add a venue to the collection
        }        
    }

    /**
     * Method writeFile
     * Writes all the driver's details to the file
     * @param path A path or name of file to write to
     */
    private void writeFile(String path)
    {
        FileIO fileIO = new FileIO();
        StringBuffer stringBuf = new StringBuffer();
        for(int i = 0; i< getDrivers().getSize(); i++) // go through each driver in the collection
        {
            stringBuf.append(getDrivers().getDriver(i).getName() + "," + getDrivers().getDriver(i).getRanking() 
                + "," + getDrivers().getDriver(i).getSpecialSkill() + ((i == (getDrivers().getSize() - 1)) ? "" :"\n")); // append details to buffer
        }
        fileIO.setFileName(path);
        fileIO.writeFile(stringBuf.toString());
    }

    /**
     * Method showChampionshipWinner
     * this method can be used to display the championship winner and print it out to the screen.
     */
    private void showChampionshipWinner()
    {
        boolean singleWinner = true;
        String winnerDrivers = getDrivers().getDriver(0).getName();
        for(int j = 1; j < getDrivers().getSize(); j++)
        {            
            if(getDrivers().getDriver(0).getAccumulatedScore() == getDrivers().getDriver(j).getAccumulatedScore()) // if multiple winners
            {
                singleWinner = false;
                winnerDrivers += " and " + getDrivers().getDriver(j).getName();
                break;
            }
            else
            {
                break;
            }

        }
        if(singleWinner)
            System.out.println(winnerDrivers + " is the winner of the championship with " + getDrivers().getDriver(0).getAccumulatedScore() + " points");
        else // if multiple winner
            System.out.println("ITS A CHAMPIONSHIP TIE!!!" + winnerDrivers + " are the winners of the championship with " + getDrivers().getDriver(0).getAccumulatedScore() + " points");
    }

    /**
     * Method checkForRain
     * This method can be used to checkForRain at a particular by passing in the venue's probability of rain.
     * All the drivers with dry weather tyres would be added a time of 5 seconds in case of rain
     * @param lapNo the current lap number
     * @param rainProbability A probability of rain at this venue
     * @param allTyreTypes An array of the tyre types of all the drivers
     * @return true if raining or false if not
     */
    private boolean checkForRain(int lapNo, int rainProbability, char[] allTyreTypes)
    {
        RNG percentageGenerator = new RNG(1,101);
        Validation validator = new Validation();
        int randomNo =  percentageGenerator.generateRandomNo(); // generates number between 1 and 101 (exclusive)
        boolean isRaining = false;
        if(validator.isNumberBetween(1, rainProbability, randomNo)) // if number is between 1 and probability of rain
        {
            isRaining = true;
            System.out.println();
            System.out.println("####################RAIN ALERT#################");
            for(int i = 0; i < getDrivers().getSize(); i++)
            {
                int timeAffected = 0;
                if(getDrivers().getDriver(i).getEligibleToRace()) // if eligible to race
                {
                    if(Character.toUpperCase(allTyreTypes[i]) == 'D') // if dry tyre type
                    {
                        timeAffected = 5;
                        System.out.println(getDrivers().getDriver(i).getName() + " has Dry-weather tyres in the Rain which increases lap " + lapNo + 
                            " time by " + timeAffected + " secs");
                        getDrivers().getDriver(i).setAccumulatedTime(getDrivers().getDriver(i).getAccumulatedTime() + timeAffected);
                    }

                }
            } 
        }
        return isRaining;
    }
}
