import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *  This class is an abstract class used to store items in the Bookcase class
 * @author Sebastian Lindfors
 * @version 1.0
 *
 */
public abstract class Storable {
/**  */
    // ---- Static Variables ---- //
    /** This string is used to denote that a specific attribute has not been set. */
    final static String FIELD_NOT_SPECIFIED = "FIELD_NOT_SPECIFIED";

    // ---- Private Variables ----//
    /** This is the ID-number of the bookcase this storable belongs in */
    private int correctStoragePlace;
    /** This is the ID-number assigned to this storable, it must be unique to each storable in the system */
    private int rfidNumber;

    /** This string is used to indicate the title of whatever object the storable refers to */
    private String name;
    /** This array contains any user defined tags the user has added to the object */
    private String[] tags;

    // ---- Constructor ----//
    /** This constructor creates a storable with a given name, ID-number and storage location.
     *  This constructor is only used by subclasses of Storable.
     * @param title The title of the object.
     * @param number The ID-number of the object.
     * @param location The ID-number of the bookcase in which this item belongs.
     *
     * */
    public Storable(String title, int number, int location) {

        name = title;
        rfidNumber  = number;
        correctStoragePlace = location;

        tags = new String[1];

    }

    // ---- Get/Set Methods ---- //

    /** Gets the storables ID-number
     * @return An int containing the ID-number */
    public int getRfidNumber() {
        return rfidNumber;
    }
    /** Gets the storables name
     * @return A String containing the name */
    public String getName() {
        return name;
    }
    /** Gets the storables tags
     * @return An Array containing the tags. */
    public String[] getTags() {
        return tags;
    }
    /** Gets the ID-number of the bookcase where this storable belongs
     * @return The ID-number of the bookcase as an int. */
    public int getCorrectStoragePlace() {
        return correctStoragePlace;
    }

    /** Sets the storbales tag array to be replaced by a new array of tags.
     * @param tags An array of tags, this will replace the old tags. */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    // ---- Other Methods ---- //

    /** This method creates a string containing all data from the storable.
     * The method is used to write a comprehensive description of the storable to a file
     * This method is only called by subclasses of storable in their own versions
     * of this method. For the exact composition of the string see each specific
     * subclass method overriding this one*/
    public String makeStorageString() {

        String tagString = "";
        for (String s : tags) {
            tagString += s + ",";
        }

        return  "" +
               "" + this.name + "|" +
                "" + this.rfidNumber + "|" +
                "" + this.correctStoragePlace + "|" +
                "" + tagString + "|";


    }

    /** This method is simmilar to the makeStorageString() method in that it creates
     * a string cotaining data from the various fields of the storable. However this
     * method takes a list of integers used to filter out which fields are added to the string.
     * This is used in the text search feature to allow the search algoritm to ignore
     * some fields whilst still only reading one string per stored object
     * @param list An integer list of any lenth, for specifics see each subclass
     *             method overriding this one*/
    public String makeSearchString(Integer [] list) {

        if (list.length == 1  && list[0] == 0) {
            return this.makeStorageString();
        }

        String tagString = "";
        for (String s : tags) {
            tagString += s + ",";
        }

        String output = "";
        for (Integer i : list)   {
            switch(i) {
                case 1:
                    output += this.name + "|";
                    break;
                case 2:
                    output += this.rfidNumber + "|";
                    break;
                case 3:
                    output += this.correctStoragePlace + "|";
                    break;
                case 4:
                    output += tagString + "|";
                    break;
            }
        }
        return output;

    }

}
