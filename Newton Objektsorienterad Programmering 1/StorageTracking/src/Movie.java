/**  This class is a subclass of storable,
 *   it represents a movie being stored in the bookcase system
 *   @author Sebastian Lindfors
 *   @version 1.0
 * */
public class Movie extends Storable {

    // ---- Private Variables ---- //

    /** A string representing the genre of the movie. */
    private String genre;
    /** A string representing the year the movie came out.*/
    private String year;

    // ---- Constructors ---- //

    /** This constructor makes a new movie object.
     * This constructor just takes the arguments needed to call the superclass constructor.
     * It does not set any values to the movie specific data fields
     * @param title The title of the object.
     * @param number The ID-number of the object.
     * @param location The ID-number of the bookcase in which this item belongs.*/
    public Movie(String title, int number, int location) {
        super(title,number,location);

        String year = Storable.FIELD_NOT_SPECIFIED;
        String genre = Storable.FIELD_NOT_SPECIFIED;

    }

    // ---- Get/Set Methods ---- //

    /** This method gets the year attrinute of the movie object
     * @return A string containing the year.*/
    public String getYear() {
        return year;
    }
    /** This method sets the year attribute of this movie object
     * @param year  A string containing the new year value */
    public void setYear(String year) {
        this.year = year;
    }


    /** This method gets the genre attrinute of the movie object
     * @return A string containing the genre.*/
    public String getGenre() {
        return genre;
    }

    /** This method sets the genre attribute of this movie object
     * @param genre  A string containing the new genre value */
    public void setGenre(String genre) {
        this.genre = genre;
    }


    // ---- Other Methods ---- //
    /** This method creates a string that contains all the data from all fields of this object.
     * The string will have the form:
     * NAME|ID_NUMBER|BOOKCASE|tag1,tag2,tag3...,tagN|Movie|YEAR|GENRE
     * This string is used to save the object to disc.*/
    public String makeStorageString(){

        return super.makeStorageString() +
                " Movie" + "|" +
                " " + this.year + "|" +
                "" + this.genre + "|" + "\n";
    }

    /** This method creates a string for searching through the data fields of this object
     * Depending on the parameter only certain fields will be contained in the string.
     * 0 = Unfiltered Search, 1 = Name, 2 = ID-number, 3 = Home Bookcase, 4 = Tags, 5 = Year and 6 = Genre.
     * @param list  A list of one or more of the above integers*/
    public String makeSearchString(Integer[] list) {

        if (list.length == 1  && list[0] == 0) {
            return this.makeStorageString();
        }

        String output = super.makeSearchString(list);
        for (Integer i : list)   {
            switch(i) {
                case 5:
                    output += this.year + "|";
                    break;
                case 6:
                    output += this.genre + "|";
                    break;
            }
        }
        return output;

    }





}
