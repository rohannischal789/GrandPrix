
/**
 * Validation class contains methods to help in validation of various cases.
 * @author Rohan Nischal
 * @version 1.1
 */
public class Validation
{
    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {
    }

    /*
     * Method isNumberBetween
     *
     * @param minValue The minimum value to compare with
     * @param maxValue The maximum value to compare with
     * @param length The length to be compared
     * @return returns true if the length is between minimum and maximum value (inclusive)
     */
    public boolean isNumberBetween(int minValue, int maxValue, int length)
    {
        return (minValue <= length && length <= maxValue) ? true : false;
    }
}
