import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * ListOfVenues class represents a collection of the venues in the championship.
 * It has accessor and mutator methods to get and set the venues list. It also has a method to add a new venue.
 * @author Rohan Nischal
 * @version 1.3
 */
public class ListOfVenues
{
    private ArrayList<Venue> venues; //  a collection of all the venues in the championship
    
    /**
     * ListOfVenues Constructor
     * Sets the field values to the default values the venues in the championship (default constructor)
     */
    public ListOfVenues()
    {
        venues = new ArrayList<Venue>();
    }

    /**
     * ListOfVenues Constructor
     * Sets the field values equal to the values passed as arguments (non-default constructor)
     * @param newVenues A collection of new venues for the championship
     */
    public ListOfVenues(ArrayList<Venue> newVenues)
    {
        venues = newVenues;
    }

    /**
     * Method getVenues
     *
     * @return A collection of venues for the championship
     */
    public ArrayList<Venue> getVenues()
    {
        return venues;
    }

    /**
     * Method setVenues
     *
     * @param newVenues A collection of new venues for the championship
     */
    public void setVenues(ArrayList<Venue> newVenues)
    {
        venues = newVenues;
    }

    /**
     * Method getVenue
     *
     * @param index A integer value representing the index in the collection
     * @return The venue at this particular index
     */
    public Venue getVenue(int index)
    {
        return getVenues().get(index);
    }
    
    /**
     * Method getSize
     *
     * @return The size of the collection of venues
     */
    public int getSize()
    {
        return getVenues().size();
    }
    
    /**
     * Method deleteVenue
     * deletes a venue from the collection by index
     * @param index A integer value representing the index in the collection
     */
    public void deleteVenue(int index)
    {
        getVenues().remove(index);
    }

    /**
     * Method addVenue
     * adds a new venue to the collection of venues
     * @param newVenueName A new name for the venue
     * @param newNoOfLaps A new total number of laps for the venue
     * @param newAverageLapTime A new average lap time for the venue
     * @param newChanceOfRain A new chance of rain for the venue
     */
    public void addVenue(String newVenueName, int newNoOfLaps, int newAverageLapTime, double newChanceOfRain)
    {
        Venue addableVenue = new Venue(newVenueName, newNoOfLaps, newAverageLapTime, newChanceOfRain);
        venues.add(addableVenue);
    }

    /**
     * Method setVenue
     *
     * @param index A integer value representing the index in the collection
     * @param newVenue The venue to add at this particular index
     */
    public void setVenue(int index, Venue newVenue)
    {
        getVenues().set(index,newVenue);
    }
}
