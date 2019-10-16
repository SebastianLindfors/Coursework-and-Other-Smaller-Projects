import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/** This class is used to represent the physical bookcases
 *  which contain the different items the system is tracking.
 *  The class uses an array to store storables.
 *  The class implements iterable and returns an iterator
 *  that iterates over the storables in order they appear in the storage array
 *   */
public class Bookcase implements Iterable<Storable> {


    // ---- Private Variables ---- //

    /** This field contians the ID-number for the bookcase */
    private int idNumber;

    /** This field keeps track of the number of storables stored in the bookcase. */
    private int numberOfStoredStorables;

    /** This array contains the storables stored in the bookcase. */
    private Storable[] listOfStoredStorables;

    // ---- Constructors ---- //

    /** This constructor creates an empty bookcase. The ID-number can not
     * be 0 or -1 as the system uses those IDs.
     * @param number The ID-NUmber for this new bookcase. */
    public Bookcase(int number) {

        idNumber = number;

        listOfStoredStorables = new Storable[20];
        numberOfStoredStorables = 0;


    }

    /** This constructor creates a bookcase and fills it with the storables
     * supplied in the list. The ID-number can not be 0 or -1 as the
     * system uses those IDs.
     * @param number The ID-NUmber for this new bookcase.
     * @param list  The list of storables you wish the new bookcase to contain.*/
    public Bookcase (int number, Storable[] list) {

        idNumber = number;

        listOfStoredStorables = new Storable[list.length + 5];
        System.arraycopy(list, 0, listOfStoredStorables, 0, list.length);
        numberOfStoredStorables = list.length;

    }

    // ---- Get/Set Methods ---- //

    /** This method gets the ID-Number of the bookcase object
     * Note: There is not corresponding "set" method for this value.
     * @return An int containing the ID-number.*/
    public int getIdNumber() {
        return idNumber;
    }


    /** This method gets the entire array of items stored in the bookcase.
     * Note: There is not corresponding "set" method for this value.
     * @return An Array of Storables.*/
    public Storable[] getListOfStoredStorables() {
        return listOfStoredStorables;
    }

    /** This method gets the number of objects stored in the bookcase.
     * @return An int containing the number of stored items.*/
    public int getNumberOfStoredStorables() {
        return numberOfStoredStorables;
    }

    /** This method gets a specific storable from the bookcase based on matching ID-number.
     * @param idNumber The ID-number of the storable you are searching for.
     * @return The storable with the matching ID-number or null if the ID-nu,ber has no match in this bookcase.*/
    public Storable getStorableFromID (int idNumber) {
        for (Storable storable : listOfStoredStorables) {
            if (storable != null) {
                if (storable.getRfidNumber() == idNumber) return storable;
            }
        }
        return null;
    }

    // ---- Interface Methods ---- //

    @Override
    public BookcaseIterator iterator() {
        return new BookcaseIterator(this);
    }


    // ---- Methods ---- //
    /** This method replaces the storage array with a larger one if the old one gets full */
    private void growStorageArray() {

        Storable[] newArray = new Storable[listOfStoredStorables.length*2];
        System.arraycopy(listOfStoredStorables, 0, newArray, 0, listOfStoredStorables.length);
        listOfStoredStorables = newArray;

    }

    /** This is used by the text search algoritm to leave out
     * one or more type of item (book, movie or game) from a search.
     * 1 = Book, 2 = Movie, 3 = Game. (0 = Unfiltered)
     * @param list  An array of integers corresponding to which items to be included in the search.
     * @return A ArrayList of storables that is a subset of all storables in this bookcase filtered by the types requested.*/
    public ArrayList<Storable> getSubsetOfStorables(Integer[] list) {

        if (list.length == 1  && list[0] == 0) {
            return new ArrayList<Storable>(Arrays.asList(listOfStoredStorables));
        }

        boolean returnBook = false;
        boolean returnMovie = false;
        boolean returnGame = false;

        for (Integer i : list) {
            switch (i) {
                case 1:
                    returnBook = true;
                    break;
                case 2:
                    returnMovie = true;
                    break;
                case 3: returnGame = true;
            }
        }
        ArrayList<Storable> output = new ArrayList<>();
        for (Storable storable : listOfStoredStorables) {
            if(returnBook && storable instanceof Book) {
                output.add(storable);
                continue;
            }
            if(returnMovie && storable instanceof Movie) {
                output.add(storable);
                continue;
            }
            if(returnGame && storable instanceof Game) {
                output.add(storable);
            }
        }
        return output;
    }

    /** This method adds a storable to the bookcase */
    public void StoreStorable(Storable s) {

        listOfStoredStorables[numberOfStoredStorables++] = s;
        if (!(numberOfStoredStorables < listOfStoredStorables.length)) this.growStorageArray();

    }

    /** This method removes a storable to the bookcase */
    public void takeOutStorable(Storable s) {

        boolean found = false;
        for (int i = 0; i < listOfStoredStorables.length; i++) {
            if (!found) {
                if (listOfStoredStorables[i] == s) {
                    found = true;
                    numberOfStoredStorables--;
                    continue;
                }
            }
            else {
                listOfStoredStorables[i-1] = listOfStoredStorables[i];
                if (listOfStoredStorables[i] == null) {
                    break;
                }
            }
        }

    }

    // ---- Inner Classes ---- //


    /** This inner class is an iterator that iterates over the storables in the bookcase.
     * It does not implement the void function!*/
    private class BookcaseIterator implements Iterator<Storable> {

        /** Keeps track of where the iterator is currently. */
        private int counter;

        /** Tracks which bookcase the iterator iterates over */
        private Bookcase bookcase;


        public BookcaseIterator(Bookcase bookcase) {

            counter = 0;

            this.bookcase = bookcase;
        }

        @Override
        public boolean hasNext() { return (counter >= bookcase.listOfStoredStorables.length); }

        @Override
        public Storable next() {
            return bookcase.listOfStoredStorables[counter++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This operation is not supported by this Iterator.");
        }
    }



}
