
/**  This class is a subclass of storable,
 *   it represents a book being stored in the bookcase system
 *   @author Sebastian Lindfors
 *   @version 1.0
 * */
public class Book extends Storable {

    // ---- Private Variables ---- //

    /** A string representing the author of the book. */
    private String author;
    /** A string representing the series the book is part of.*/
    private String series;
    /** A string representing the genre of the book.*/
    private String genre;

    // ---- Constructors ---- //

    /** This constructor makes a new book object.
     * This constructor just takes the arguments needed to call the superclass constructor.
     * It does not set any values to the movie specific data fields
     * @param title The title of the object.
     * @param number The ID-number of the object.
     * @param location The ID-number of the bookcase in which this item belongs.*/
    public Book(String title, int number, int location) {
        super(title,number,location);

        String author = Storable.FIELD_NOT_SPECIFIED;
        String series = Storable.FIELD_NOT_SPECIFIED;
        String genre = Storable.FIELD_NOT_SPECIFIED;

    }

    // ---- Get/Set Methods ---- //

    /** This method gets the author attribute of the book object
     * @return A string containing the author.*/
    public String getAuthor() {
        return author;
    }

    /** This method sets the author attribute of this book object
     * @param author  A string containing the new author value */
    public void setAuthor(String author) {
        this.author = author;
    }

    /** This method gets the series attribute of the book object
     * @return A string containing the genre.*/
    public String getGenre() {
        return genre;
    }

    /** This method sets the genre attribute of this book object
     * @param genre  A string containing the new genre value */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /** This method gets the series attribute of the book object
     * @return A string containing the series.*/
    public String getSeries() {
        return series;
    }

    /** This method sets the series attribute of this book object
     * @param series  A string containing the new series value */
    public void setSeries(String series) {
        this.series = series;
    }

    // ---- Other Methods ---- //

    /** This method creates a string that contains all the data from all fields of this object.
     * The string will have the form:
     * NAME|ID_NUMBER|BOOKCASE|tag1,tag2,tag3...,tagN|Book|AUTHOR|SERIES|GENRE|
     * This string is used to save the object to disc.*/
    public String makeStorageString(){

        return super.makeStorageString() +
                "Book" + "|" +
                "" + this.author + "|" +
                "" + this.series + "|" +
                "" + this.genre + "|" + "\n";
    }

    /** This method creates a string for searching through the data fields of this object
     * Depending on the parameter only certain fields will be contained in the string.
     * 0 = Unfiltered Search, 1 = Name, 2 = ID-number, 3 = Home Bookcase, 4 = Tags, 5 = Author 6 = Series and 7 = Genre */
    public String makeSearchString(Integer[] list) {

        if (list.length == 1  && list[0] == 0) {
            return this.makeStorageString();
        }

        String output = super.makeSearchString(list);
        for (Integer i : list)   {
            switch(i) {
                case 5:
                    output += this.author + "|";
                    break;
                case 6:
                    output += this.genre + "|";
                    break;
                case 7:
                    output += this.series + "|";
            }
        }
        return output;



    }
}
