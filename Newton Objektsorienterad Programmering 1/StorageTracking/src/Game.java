
/**  This class is a subclass of storable,
 *   it represents a game being stored in the bookcase system
 *   @author Sebastian Lindfors
 *   @version 1.0
 * */
public class Game extends Storable {

    // ---- Private Variables ---- //

    /** A string representing the genre of the game. */
    private String genre;
    /** A string representing the platform you play the game on. */
    private String platform;

    // ---- Constructors ---- //
    /** This constructor makes a new game object.
     * This constructor just takes the arguments needed to call the superclass constructor.
     * It does not set any values to the movie specific data fields
     * @param title The title of the object.
     * @param number The ID-number of the object.
     * @param location The ID-number of the bookcase in which this item belongs.*/
    public Game(String title, int number, int location) {
        super(title,number,location);

        String platform = Storable.FIELD_NOT_SPECIFIED;
        String genre = Storable.FIELD_NOT_SPECIFIED;

    }

    // ---- Get/Set Methods ---- //
    /** This method gets the genre attribute of the game object
     * @return A string containing the platform.*/
    public String getPlatform() {
        return platform;
    }

    /** This method sets the genre attribute of this game object
     * @param platform  A string containing the new genre value */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /** This method gets the genre attribute of the game object
     * @return A string containing the genre.*/
    public String getGenre() {
        return genre;
    }

    /** This method sets the genre attribute of this game object
     * @param genre  A string containing the new genre value */
    public void setGenre(String genre) {
        this.genre = genre;
    }


    // ---- Other Methods ---- //

    /** This method creates a string that contains all the data from all fields of this object.
     * The string will have the form:
     * NAME|ID_NUMBER|BOOKCASE|tag1,tag2,tag3...,tagN|Game|PLATFORM|GENRE
     * This string is used to save the object to disc.*/
    public String makeStorageString(){

    return super.makeStorageString() +
            "Game" + "|" +
            "" + this.platform + "|" +
            "" + this.genre + "|" + "\n";
    }

/** This method creates a string for searching through the data fields of this object
 * Depending on the parameter only certain fields will be contained in the string.
 * 0 = Unfiltered Search, 1 = Name, 2 = ID-number, 3 = Home Bookcase, 4 = Tags, 5 = Platform and 6 = Genre. */
    public String makeSearchString(Integer[] list) {

        if (list.length == 1 && list[0] == 0) {
            return this.makeStorageString();
        }

        String output = super.makeSearchString(list);
        for (Integer i : list) {
            switch (i) {
                case 5:
                    output += this.platform + "|";
                    break;
                case 6:
                    output += this.genre + "|";
                    break;
            }
        }
        return output;
    }
}
