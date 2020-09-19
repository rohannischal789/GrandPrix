import java.util.Random;
/**
 * RNG is the class which is used to generate random numbers in order to generate probability for each lap event.
 * It has accessor and mutator methods to return or update the minimum and maximum number to generate.
 * It also has a method to generate a random number between 2 values (max exclusive)
 * @author Rohan
 * @version 1.2
 */
public class RNG
{
    private int minimumValue; // the minimum value to generate
    private int maximumValue; // the maximum value to generate(exclusive)
    
    /**
     * RNG Constructor
     * Sets the maximum and minimum values to default values
     */
    public RNG()
    {
        minimumValue = 0;
        maximumValue = 0;
    }

    /**
     * RNG Constructor
     *
     * @param minVal A minimum value that can be generated
     * @param maxVal A maximum value that can be generated(exclusive)
     */
    public RNG(int minVal, int maxVal)
    {
        minimumValue = minVal;
        maximumValue = maxVal;
    }

    /**
     * Method getMinimumValue
     *
     * @return The minimum value that can be generated
     */
    public int getMinimumValue()
    {
        return minimumValue;
    }

    /**
     * Method getMaximumValue
     *
     * @return The maximum value that can be generated
     */
    public int getMaximumValue()
    {
        return maximumValue;
    }

    /**
     * Method setMinimumValue
     *
     * @param minValue A minimum value that can be generated
     */
    public void setMinimumValue(int minValue)
    {
        minimumValue = minValue;
    }

    /**
     * Method setMaximumValue
     *
     * @param maxValue A maximum value that can be generated
     */
    public void setMaximumValue(int maxValue)
    {
        maximumValue = maxValue;
    }

    /**
     * Method generateRandomNo
     *
     * @return a random number between the minimum(inclusive) and maximum(exclusive) values 
     */
    public int generateRandomNo()
    {
        Random r = new Random();
        return r.nextInt(getMaximumValue() - getMinimumValue()) + getMinimumValue();
    }
}
