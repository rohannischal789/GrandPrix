import java.io.*;
import java.util.Scanner;
/**
 * Write a description of class FileIO here.
 *
 * @author Rohan Nischal
 * @version 1.2
 */
public class FileIO
{
    private String fileName; // represents the name of a file

    /**
     * FileIO Constructor
     * Sets the field values to the default values (default constructor)
     */
    public FileIO()
    {
        fileName = "";
    }

    /**
     * FileIO Constructor
     * Sets the field values to the arguments passed (non-default constructor)
     * @param newFileName A new file name
     */
    public FileIO(String newFileName)
    {
        fileName = newFileName;
    }

    /**
     * Method getFileName
     *
     * @return The name of the file to be read/written to
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Method setFileName
     *
     * @param newFileName A name of the file to be read/written to
     */
    public void setFileName(String newFileName)
    {
        fileName = newFileName;
    }

    /**
     * Method readFile
     *
     * @return All the file's contents. Each Line end replaced by \n character.
     */
    public String readFile()
    {
        String readData = "";
        try
        {            
            File csvFile = new File(getFileName());
            if (csvFile != null && csvFile.isFile()) // check if file is null or is actually a file or not
            {  
                Scanner scanner = new Scanner(new FileReader(getFileName())); // set scanner source as the file by its name
                StringBuffer sb1 =  new StringBuffer("");
                while (scanner.hasNextLine()) // go through each line, check if file has next line
                {  
                    sb1.append(scanner.nextLine() + "\n"); // append next line with \n character into a stringbuffer
                }
                readData = sb1.toString(); // convert stringbuffer to string
            }
            else
            {
                System.out.println("File not loaded");
            }
        }
        catch(FileNotFoundException fileEx)
        {
            System.out.println("Could not find file at " + getFileName() + " \n Please create the file");
        }
        catch(IOException ioEx)
        {
            System.out.println("IO Exception occured. Possible Causes are : 1.Reading a network file and got disconnected.\n2.Reading a local file" +
                " that was no longer available.\n3.Using some stream to read data and some other process closed the stream.\n4.Trying to read/write a file" +
                " but don't have permission.\n5.Trying to write to a file but disk space was no longer available.");
        }
        catch(Exception ex)
        {
            System.out.println("Exception occured " + ex);
        }
        return readData;
    }

    /**
     * Method writeFile
     *
     * @param contents The contents to write into the file. Each line end must be represented by \n character.
     */
    public void writeFile(String contents)
    {
        PrintWriter pw = null;
        try
        {
            pw = new PrintWriter(getFileName()); // initialize a printwriter object to the file by its name
            String[] writeableContents = contents.split("\\n");
            for(int i = 0 ; i < writeableContents.length ; i++)
            {
                pw.println(writeableContents[i]); // write contents to the file line by line
            }
            System.out.println("Updated driver rankings saved to file at " + getFileName());
        }
        catch(FileNotFoundException fileEx)
        {
            System.out.println("Could not find File to write driver details to at " + getFileName() + " \n Please create the file");
        }
        catch(IOException ioEx)
        {
            System.out.println("IO Exception occured. Possible Causes are : 1.Reading a network file and got disconnected.\n2.Reading a local file" +
                " that was no longer available.\n3.Using some stream to read data and some other process closed the stream.\n4.Trying to read/write a file" +
                " but don't have permission.\n5.Trying to write to a file but disk space was no longer available.");
        }
        catch(Exception ex)
        {
            System.out.println("Exception occured " + ex);
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
    }
}
